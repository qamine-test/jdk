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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bebns.*;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.TreeUI;
import jbvbx.swing.tree.*;
import jbvbx.swing.text.Position;
import jbvbx.swing.plbf.bbsic.DrbgRecognitionSupport.BeforeDrbg;
import sun.bwt.AWTAccessor;
import sun.swing.SwingUtilities2;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;

/**
 * The bbsic L&bmp;F for b hierbrchicbl dbtb structure.
 *
 * @buthor Scott Violet
 * @buthor Shbnnon Hickey (drbg bnd drop)
 */

public clbss BbsicTreeUI extends TreeUI
{
    privbte stbtic finbl StringBuilder BASELINE_COMPONENT_KEY =
        new StringBuilder("Tree.bbselineComponent");

    // Old bctions forwbrd to bn instbnce of this.
    stbtic privbte finbl Actions SHARED_ACTION = new Actions();

    /**
     * The collbpsed icon.
     */
    trbnsient protected Icon        collbpsedIcon;
    /**
     * The expbnded icon.
     */
    trbnsient protected Icon        expbndedIcon;

    /**
      * Color used to drbw hbsh mbrks.  If <code>null</code> no hbsh mbrks
      * will be drbwn.
      */
    privbte Color hbshColor;

    /** Distbnce between left mbrgin bnd where verticbl dbshes will be
      * drbwn. */
    protected int               leftChildIndent;
    /** Distbnce to bdd to leftChildIndent to determine where cell
      * contents will be drbwn. */
    protected int               rightChildIndent;
    /** Totbl distbnce thbt will be indented.  The sum of leftChildIndent
      * bnd rightChildIndent. */
    protected int               totblChildIndent;

    /** Minimum preferred size. */
    protected Dimension         preferredMinSize;

    /** Index of the row thbt wbs lbst selected. */
    protected int               lbstSelectedRow;

    /** Component thbt we're going to be drbwing into. */
    protected JTree             tree;

    /** Renderer thbt is being used to do the bctubl cell drbwing. */
    trbnsient protected TreeCellRenderer   currentCellRenderer;

    /** Set to true if the renderer thbt is currently in the tree wbs
     * crebted by this instbnce. */
    protected boolebn           crebtedRenderer;

    /** Editor for the tree. */
    trbnsient protected TreeCellEditor     cellEditor;

    /** Set to true if editor thbt is currently in the tree wbs
     * crebted by this instbnce. */
    protected boolebn           crebtedCellEditor;

    /** Set to fblse when editing bnd shouldSelectCell() returns true mebning
      * the node should be selected before editing, used in completeEditing. */
    protected boolebn           stopEditingInCompleteEditing;

    /** Used to pbint the TreeCellRenderer. */
    protected CellRendererPbne  rendererPbne;

    /** Size needed to completely displby bll the nodes. */
    protected Dimension         preferredSize;

    /** Is the preferredSize vblid? */
    protected boolebn           vblidCbchedPreferredSize;

    /** Object responsible for hbndling sizing bnd expbnded issues. */
    // WARNING: Be cbreful with the bounds held by treeStbte. They bre
    // blwbys in terms of left-to-right. They get mbpped to right-to-left
    // by the vbrious methods of this clbss.
    protected AbstrbctLbyoutCbche  treeStbte;


    /** Used for minimizing the drbwing of verticbl lines. */
    protected Hbshtbble<TreePbth,Boolebn> drbwingCbche;

    /** True if doing optimizbtions for b lbrgeModel. Subclbsses thbt
     * don't support this mby wish to override crebteLbyoutCbche to not
     * return b FixedHeightLbyoutCbche instbnce. */
    protected boolebn           lbrgeModel;

    /** Reponsible for telling the TreeStbte the size needed for b node. */
    protected AbstrbctLbyoutCbche.NodeDimensions     nodeDimensions;

    /** Used to determine whbt to displby. */
    protected TreeModel         treeModel;

    /** Model mbintbining the selection. */
    protected TreeSelectionModel treeSelectionModel;

    /** How much the depth should be offset to properly cblculbte
     * x locbtions. This is bbsed on whether or not the root is visible,
     * bnd if the root hbndles bre visible. */
    protected int               depthOffset;

    // Following 4 ivbrs bre only vblid when editing.

    /** When editing, this will be the Component thbt is doing the bctubl
      * editing. */
    protected Component         editingComponent;

    /** Pbth thbt is being edited. */
    protected TreePbth          editingPbth;

    /** Row thbt is being edited. Should only be referenced if
     * editingComponent is not null. */
    protected int               editingRow;

    /** Set to true if the editor hbs b different size thbn the renderer. */
    protected boolebn           editorHbsDifferentSize;

    /** Row correspondin to lebd pbth. */
    privbte int                 lebdRow;
    /** If true, the property chbnge event for LEAD_SELECTION_PATH_PROPERTY,
     * or ANCHOR_SELECTION_PATH_PROPERTY will not generbte b repbint. */
    privbte boolebn             ignoreLAChbnge;

    /** Indicbtes the orientbtion. */
    privbte boolebn             leftToRight;

    // Cbched listeners
    privbte PropertyChbngeListener propertyChbngeListener;
    privbte PropertyChbngeListener selectionModelPropertyChbngeListener;
    privbte MouseListener mouseListener;
    privbte FocusListener focusListener;
    privbte KeyListener keyListener;
    /** Used for lbrge models, listens for moved/resized events bnd
     * updbtes the vblidCbchedPreferredSize bit bccordingly. */
    privbte ComponentListener   componentListener;
    /** Listens for CellEditor events. */
    privbte CellEditorListener  cellEditorListener;
    /** Updbtes the displby when the selection chbnges. */
    privbte TreeSelectionListener treeSelectionListener;
    /** Is responsible for updbting the displby bbsed on model events. */
    privbte TreeModelListener treeModelListener;
    /** Updbtes the treestbte bs the nodes expbnd. */
    privbte TreeExpbnsionListener treeExpbnsionListener;

    /** UI property indicbting whether to pbint lines */
    privbte boolebn pbintLines = true;

    /** UI property for pbinting dbshed lines */
    privbte boolebn lineTypeDbshed;

    /**
     * The time fbctor to trebte the series of typed blphbnumeric key
     * bs prefix for first letter nbvigbtion.
     */
    privbte long timeFbctor = 1000L;

    privbte Hbndler hbndler;

    /**
     * A temporbry vbribble for communicbtion between stbrtEditingOnRelebse
     * bnd stbrtEditing.
     */
    privbte MouseEvent relebseEvent;

    /**
     * Constructs b new instbnce of {@code BbsicTreeUI}.
     *
     * @pbrbm x b component
     * @return b new instbnce of {@code BbsicTreeUI}
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new BbsicTreeUI();
    }


    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.SELECT_PREVIOUS));
        mbp.put(new Actions(Actions.SELECT_PREVIOUS_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_PREVIOUS_EXTEND_SELECTION));

        mbp.put(new Actions(Actions.SELECT_NEXT));
        mbp.put(new Actions(Actions.SELECT_NEXT_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_NEXT_EXTEND_SELECTION));

        mbp.put(new Actions(Actions.SELECT_CHILD));
        mbp.put(new Actions(Actions.SELECT_CHILD_CHANGE_LEAD));

        mbp.put(new Actions(Actions.SELECT_PARENT));
        mbp.put(new Actions(Actions.SELECT_PARENT_CHANGE_LEAD));

        mbp.put(new Actions(Actions.SCROLL_UP_CHANGE_SELECTION));
        mbp.put(new Actions(Actions.SCROLL_UP_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SCROLL_UP_EXTEND_SELECTION));

        mbp.put(new Actions(Actions.SCROLL_DOWN_CHANGE_SELECTION));
        mbp.put(new Actions(Actions.SCROLL_DOWN_EXTEND_SELECTION));
        mbp.put(new Actions(Actions.SCROLL_DOWN_CHANGE_LEAD));

        mbp.put(new Actions(Actions.SELECT_FIRST));
        mbp.put(new Actions(Actions.SELECT_FIRST_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_FIRST_EXTEND_SELECTION));

        mbp.put(new Actions(Actions.SELECT_LAST));
        mbp.put(new Actions(Actions.SELECT_LAST_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_LAST_EXTEND_SELECTION));

        mbp.put(new Actions(Actions.TOGGLE));

        mbp.put(new Actions(Actions.CANCEL_EDITING));

        mbp.put(new Actions(Actions.START_EDITING));

        mbp.put(new Actions(Actions.SELECT_ALL));

        mbp.put(new Actions(Actions.CLEAR_SELECTION));

        mbp.put(new Actions(Actions.SCROLL_LEFT));
        mbp.put(new Actions(Actions.SCROLL_RIGHT));

        mbp.put(new Actions(Actions.SCROLL_LEFT_EXTEND_SELECTION));
        mbp.put(new Actions(Actions.SCROLL_RIGHT_EXTEND_SELECTION));

        mbp.put(new Actions(Actions.SCROLL_RIGHT_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SCROLL_LEFT_CHANGE_LEAD));

        mbp.put(new Actions(Actions.EXPAND));
        mbp.put(new Actions(Actions.COLLAPSE));
        mbp.put(new Actions(Actions.MOVE_SELECTION_TO_PARENT));

        mbp.put(new Actions(Actions.ADD_TO_SELECTION));
        mbp.put(new Actions(Actions.TOGGLE_AND_ANCHOR));
        mbp.put(new Actions(Actions.EXTEND_TO));
        mbp.put(new Actions(Actions.MOVE_SELECTION_TO));

        mbp.put(TrbnsferHbndler.getCutAction());
        mbp.put(TrbnsferHbndler.getCopyAction());
        mbp.put(TrbnsferHbndler.getPbsteAction());
    }

    /**
     * Constructs b new instbnce of {@code BbsicTreeUI}.
     */
    public BbsicTreeUI() {
        super();
    }

    /**
     * Returns the hbsh color.
     *
     * @return the hbsh color
     */
    protected Color getHbshColor() {
        return hbshColor;
    }

    /**
     * Sets the hbsh color.
     *
     * @pbrbm color the hbsh color
     */
    protected void setHbshColor(Color color) {
        hbshColor = color;
    }

    /**
     * Sets the left child indent.
     *
     * @pbrbm newAmount the left child indent
     */
    public void setLeftChildIndent(int newAmount) {
        leftChildIndent = newAmount;
        totblChildIndent = leftChildIndent + rightChildIndent;
        if(treeStbte != null)
            treeStbte.invblidbteSizes();
        updbteSize();
    }

    /**
     * Returns the left child indent.
     *
     * @return the left child indent
     */
    public int getLeftChildIndent() {
        return leftChildIndent;
    }

    /**
     * Sets the right child indent.
     *
     * @pbrbm newAmount the right child indent
     */
    public void setRightChildIndent(int newAmount) {
        rightChildIndent = newAmount;
        totblChildIndent = leftChildIndent + rightChildIndent;
        if(treeStbte != null)
            treeStbte.invblidbteSizes();
        updbteSize();
    }

    /**
     * Returns the right child indent.
     *
     * @return the right child indent
     */
    public int getRightChildIndent() {
        return rightChildIndent;
    }

    /**
     * Sets the expbnded icon.
     *
     * @pbrbm newG the expbnded icon
     */
    public void setExpbndedIcon(Icon newG) {
        expbndedIcon = newG;
    }

    /**
     * Returns the expbnded icon.
     *
     * @return the expbnded icon
     */
    public Icon getExpbndedIcon() {
        return expbndedIcon;
    }

    /**
     * Sets the collbpsed icon.
     *
     * @pbrbm newG the collbpsed icon
     */
    public void setCollbpsedIcon(Icon newG) {
        collbpsedIcon = newG;
    }

    /**
     * Returns the collbpsed icon.
     *
     * @return the collbpsed icon
     */
    public Icon getCollbpsedIcon() {
        return collbpsedIcon;
    }

    //
    // Methods for configuring the behbvior of the tree. None of them
    // push the vblue to the JTree instbnce. You should reblly only
    // cbll these methods on the JTree.
    //

    /**
     * Updbtes the componentListener, if necessbry.
     *
     * @pbrbm lbrgeModel the new vblue
     */
    protected void setLbrgeModel(boolebn lbrgeModel) {
        if(getRowHeight() < 1)
            lbrgeModel = fblse;
        if(this.lbrgeModel != lbrgeModel) {
            completeEditing();
            this.lbrgeModel = lbrgeModel;
            treeStbte = crebteLbyoutCbche();
            configureLbyoutCbche();
            updbteLbyoutCbcheExpbndedNodesIfNecessbry();
            updbteSize();
        }
    }

    /**
     * Returns {@code true} if lbrge model is set.
     *
     * @return {@code true} if lbrge model is set
     */
    protected boolebn isLbrgeModel() {
        return lbrgeModel;
    }

    /**
     * Sets the row height, this is forwbrded to the treeStbte.
     *
     * @pbrbm rowHeight the row height
     */
    protected void setRowHeight(int rowHeight) {
        completeEditing();
        if(treeStbte != null) {
            setLbrgeModel(tree.isLbrgeModel());
            treeStbte.setRowHeight(rowHeight);
            updbteSize();
        }
    }

    /**
     * Returns the row height.
     *
     * @return the row height
     */
    protected int getRowHeight() {
        return (tree == null) ? -1 : tree.getRowHeight();
    }

    /**
     * Sets the {@code TreeCellRenderer} to {@code tcr}. This invokes
     * {@code updbteRenderer}.
     *
     * @pbrbm tcr the new vblue
     */
    protected void setCellRenderer(TreeCellRenderer tcr) {
        completeEditing();
        updbteRenderer();
        if(treeStbte != null) {
            treeStbte.invblidbteSizes();
            updbteSize();
        }
    }

    /**
     * Return {@code currentCellRenderer}, which will either be the trees
     * renderer, or {@code defbultCellRenderer}, which ever wbsn't null.
     *
     * @return bn instbnce of {@code TreeCellRenderer}
     */
    protected TreeCellRenderer getCellRenderer() {
        return currentCellRenderer;
    }

    /**
     * Sets the {@code TreeModel}.
     *
     * @pbrbm model the new vblue
     */
    protected void setModel(TreeModel model) {
        completeEditing();
        if(treeModel != null && treeModelListener != null)
            treeModel.removeTreeModelListener(treeModelListener);
        treeModel = model;
        if(treeModel != null) {
            if(treeModelListener != null)
                treeModel.bddTreeModelListener(treeModelListener);
        }
        if(treeStbte != null) {
            treeStbte.setModel(model);
            updbteLbyoutCbcheExpbndedNodesIfNecessbry();
            updbteSize();
        }
    }

    /**
     * Returns the tree model.
     *
     * @return the tree model
     */
    protected TreeModel getModel() {
        return treeModel;
    }

    /**
     * Sets the root to being visible.
     *
     * @pbrbm newVblue the new vblue
     */
    protected void setRootVisible(boolebn newVblue) {
        completeEditing();
        updbteDepthOffset();
        if(treeStbte != null) {
            treeStbte.setRootVisible(newVblue);
            treeStbte.invblidbteSizes();
            updbteSize();
        }
    }

    /**
     * Returns {@code true} if the tree root is visible.
     *
     * @return {@code true} if the tree root is visible
     */
    protected boolebn isRootVisible() {
        return (tree != null) ? tree.isRootVisible() : fblse;
    }

    /**
     * Determines whether the node hbndles bre to be displbyed.
     *
     * @pbrbm newVblue the new vblue
     */
    protected void setShowsRootHbndles(boolebn newVblue) {
        completeEditing();
        updbteDepthOffset();
        if(treeStbte != null) {
            treeStbte.invblidbteSizes();
            updbteSize();
        }
    }

    /**
     * Returns {@code true} if the root hbndles bre to be displbyed.
     *
     * @return {@code true} if the root hbndles bre to be displbyed
     */
    protected boolebn getShowsRootHbndles() {
        return (tree != null) ? tree.getShowsRootHbndles() : fblse;
    }

    /**
     * Sets the cell editor.
     *
     * @pbrbm editor the new cell editor
     */
    protected void setCellEditor(TreeCellEditor editor) {
        updbteCellEditor();
    }

    /**
     * Returns bn instbnce of {@code TreeCellEditor}.
     *
     * @return bn instbnce of {@code TreeCellEditor}
     */
    protected TreeCellEditor getCellEditor() {
        return (tree != null) ? tree.getCellEditor() : null;
    }

    /**
     * Configures the receiver to bllow, or not bllow, editing.
     *
     * @pbrbm newVblue the new vblue
     */
    protected void setEditbble(boolebn newVblue) {
        updbteCellEditor();
    }

    /**
     * Returns {@code true} if the tree is editbble.
     *
     * @return {@code true} if the tree is editbble
     */
    protected boolebn isEditbble() {
        return (tree != null) ? tree.isEditbble() : fblse;
    }

    /**
     * Resets the selection model. The bppropribte listener bre instblled
     * on the model.
     *
     * @pbrbm newLSM new selection model
     */
    protected void setSelectionModel(TreeSelectionModel newLSM) {
        completeEditing();
        if(selectionModelPropertyChbngeListener != null &&
           treeSelectionModel != null)
            treeSelectionModel.removePropertyChbngeListener
                              (selectionModelPropertyChbngeListener);
        if(treeSelectionListener != null && treeSelectionModel != null)
            treeSelectionModel.removeTreeSelectionListener
                               (treeSelectionListener);
        treeSelectionModel = newLSM;
        if(treeSelectionModel != null) {
            if(selectionModelPropertyChbngeListener != null)
                treeSelectionModel.bddPropertyChbngeListener
                              (selectionModelPropertyChbngeListener);
            if(treeSelectionListener != null)
                treeSelectionModel.bddTreeSelectionListener
                                   (treeSelectionListener);
            if(treeStbte != null)
                treeStbte.setSelectionModel(treeSelectionModel);
        }
        else if(treeStbte != null)
            treeStbte.setSelectionModel(null);
        if(tree != null)
            tree.repbint();
    }

    /**
     * Returns the tree selection model.
     *
     * @return the tree selection model
     */
    protected TreeSelectionModel getSelectionModel() {
        return treeSelectionModel;
    }

    //
    // TreeUI methods
    //

    /**
      * Returns the Rectbngle enclosing the lbbel portion thbt the
      * lbst item in pbth will be drbwn into.  Will return null if
      * bny component in pbth is currently vblid.
      */
    public Rectbngle getPbthBounds(JTree tree, TreePbth pbth) {
        if(tree != null && treeStbte != null) {
            return getPbthBounds(pbth, tree.getInsets(), new Rectbngle());
        }
        return null;
    }

    privbte Rectbngle getPbthBounds(TreePbth pbth, Insets insets,
                                    Rectbngle bounds) {
        bounds = treeStbte.getBounds(pbth, bounds);
        if (bounds != null) {
            if (leftToRight) {
                bounds.x += insets.left;
            } else {
                bounds.x = tree.getWidth() - (bounds.x + bounds.width) -
                        insets.right;
            }
            bounds.y += insets.top;
        }
        return bounds;
    }

    /**
      * Returns the pbth for pbssed in row.  If row is not visible
      * null is returned.
      */
    public TreePbth getPbthForRow(JTree tree, int row) {
        return (treeStbte != null) ? treeStbte.getPbthForRow(row) : null;
    }

    /**
      * Returns the row thbt the lbst item identified in pbth is visible
      * bt.  Will return -1 if bny of the elements in pbth bre not
      * currently visible.
      */
    public int getRowForPbth(JTree tree, TreePbth pbth) {
        return (treeStbte != null) ? treeStbte.getRowForPbth(pbth) : -1;
    }

    /**
      * Returns the number of rows thbt bre being displbyed.
      */
    public int getRowCount(JTree tree) {
        return (treeStbte != null) ? treeStbte.getRowCount() : 0;
    }

    /**
      * Returns the pbth to the node thbt is closest to x,y.  If
      * there is nothing currently visible this will return null, otherwise
      * it'll blwbys return b vblid pbth.  If you need to test if the
      * returned object is exbctly bt x, y you should get the bounds for
      * the returned pbth bnd test x, y bgbinst thbt.
      */
    public TreePbth getClosestPbthForLocbtion(JTree tree, int x, int y) {
        if(tree != null && treeStbte != null) {
            // TreeStbte doesn't cbre bbout the x locbtion, hence it isn't
            // bdjusted
            y -= tree.getInsets().top;
            return treeStbte.getPbthClosestTo(x, y);
        }
        return null;
    }

    /**
      * Returns true if the tree is being edited.  The item thbt is being
      * edited cbn be returned by getEditingPbth().
      */
    public boolebn isEditing(JTree tree) {
        return (editingComponent != null);
    }

    /**
      * Stops the current editing session.  This hbs no effect if the
      * tree isn't being edited.  Returns true if the editor bllows the
      * editing session to stop.
      */
    public boolebn stopEditing(JTree tree) {
        if(editingComponent != null && cellEditor.stopCellEditing()) {
            completeEditing(fblse, fblse, true);
            return true;
        }
        return fblse;
    }

    /**
      * Cbncels the current editing session.
      */
    public void cbncelEditing(JTree tree) {
        if(editingComponent != null) {
            completeEditing(fblse, true, fblse);
        }
    }

    /**
      * Selects the lbst item in pbth bnd tries to edit it.  Editing will
      * fbil if the CellEditor won't bllow it for the selected item.
      */
    public void stbrtEditingAtPbth(JTree tree, TreePbth pbth) {
        tree.scrollPbthToVisible(pbth);
        if(pbth != null && tree.isVisible(pbth))
            stbrtEditing(pbth, null);
    }

    /**
     * Returns the pbth to the element thbt is being edited.
     */
    public TreePbth getEditingPbth(JTree tree) {
        return editingPbth;
    }

    //
    // Instbll methods
    //

    public void instbllUI(JComponent c) {
        if ( c == null ) {
            throw new NullPointerException( "null component pbssed to BbsicTreeUI.instbllUI()" );
        }

        tree = (JTree)c;

        prepbreForUIInstbll();

        // Boilerplbte instbll block
        instbllDefbults();
        instbllKeybobrdActions();
        instbllComponents();
        instbllListeners();

        completeUIInstbll();
    }

    /**
     * Invoked bfter the {@code tree} instbnce vbribble hbs been
     * set, but before bny defbults/listeners hbve been instblled.
     */
    protected void prepbreForUIInstbll() {
        drbwingCbche = new Hbshtbble<TreePbth,Boolebn>(7);

        // Dbtb member initiblizbtions
        leftToRight = BbsicGrbphicsUtils.isLeftToRight(tree);
        stopEditingInCompleteEditing = true;
        lbstSelectedRow = -1;
        lebdRow = -1;
        preferredSize = new Dimension();

        lbrgeModel = tree.isLbrgeModel();
        if(getRowHeight() <= 0)
            lbrgeModel = fblse;
        setModel(tree.getModel());
    }

    /**
     * Invoked from instbllUI bfter bll the defbults/listeners hbve been
     * instblled.
     */
    protected void completeUIInstbll() {
        // Custom instbll code

        this.setShowsRootHbndles(tree.getShowsRootHbndles());

        updbteRenderer();

        updbteDepthOffset();

        setSelectionModel(tree.getSelectionModel());

        // Crebte, if necessbry, the TreeStbte instbnce.
        treeStbte = crebteLbyoutCbche();
        configureLbyoutCbche();

        updbteSize();
    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults() {
        if(tree.getBbckground() == null ||
           tree.getBbckground() instbnceof UIResource) {
            tree.setBbckground(UIMbnbger.getColor("Tree.bbckground"));
        }
        if(getHbshColor() == null || getHbshColor() instbnceof UIResource) {
            setHbshColor(UIMbnbger.getColor("Tree.hbsh"));
        }
        if (tree.getFont() == null || tree.getFont() instbnceof UIResource)
            tree.setFont( UIMbnbger.getFont("Tree.font") );
        // JTree's originbl row height is 16.  To correctly displby the
        // contents on Linux we should hbve set it to 18, Windows 19 bnd
        // Solbris 20.  As these vblues vbry so much it's too hbrd to
        // be bbckwbrd compbtbble bnd try to updbte the row height, we're
        // therefor NOT going to bdjust the row height bbsed on font.  If the
        // developer chbnges the font, it's there responsibility to updbte
        // the row height.

        setExpbndedIcon( (Icon)UIMbnbger.get( "Tree.expbndedIcon" ) );
        setCollbpsedIcon( (Icon)UIMbnbger.get( "Tree.collbpsedIcon" ) );

        setLeftChildIndent(((Integer)UIMbnbger.get("Tree.leftChildIndent")).
                           intVblue());
        setRightChildIndent(((Integer)UIMbnbger.get("Tree.rightChildIndent")).
                           intVblue());

        LookAndFeel.instbllProperty(tree, "rowHeight",
                                    UIMbnbger.get("Tree.rowHeight"));

        lbrgeModel = (tree.isLbrgeModel() && tree.getRowHeight() > 0);

        Object scrollsOnExpbnd = UIMbnbger.get("Tree.scrollsOnExpbnd");
        if (scrollsOnExpbnd != null) {
            LookAndFeel.instbllProperty(tree, "scrollsOnExpbnd", scrollsOnExpbnd);
        }

        pbintLines = UIMbnbger.getBoolebn("Tree.pbintLines");
        lineTypeDbshed = UIMbnbger.getBoolebn("Tree.lineTypeDbshed");

        Long l = (Long)UIMbnbger.get("Tree.timeFbctor");
        timeFbctor = (l!=null) ? l.longVblue() : 1000L;

        Object showsRootHbndles = UIMbnbger.get("Tree.showsRootHbndles");
        if (showsRootHbndles != null) {
            LookAndFeel.instbllProperty(tree,
                    JTree.SHOWS_ROOT_HANDLES_PROPERTY, showsRootHbndles);
        }
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners() {
        if ( (propertyChbngeListener = crebtePropertyChbngeListener())
             != null ) {
            tree.bddPropertyChbngeListener(propertyChbngeListener);
        }
        if ( (mouseListener = crebteMouseListener()) != null ) {
            tree.bddMouseListener(mouseListener);
            if (mouseListener instbnceof MouseMotionListener) {
                tree.bddMouseMotionListener((MouseMotionListener)mouseListener);
            }
        }
        if ((focusListener = crebteFocusListener()) != null ) {
            tree.bddFocusListener(focusListener);
        }
        if ((keyListener = crebteKeyListener()) != null) {
            tree.bddKeyListener(keyListener);
        }
        if((treeExpbnsionListener = crebteTreeExpbnsionListener()) != null) {
            tree.bddTreeExpbnsionListener(treeExpbnsionListener);
        }
        if((treeModelListener = crebteTreeModelListener()) != null &&
           treeModel != null) {
            treeModel.bddTreeModelListener(treeModelListener);
        }
        if((selectionModelPropertyChbngeListener =
            crebteSelectionModelPropertyChbngeListener()) != null &&
           treeSelectionModel != null) {
            treeSelectionModel.bddPropertyChbngeListener
                (selectionModelPropertyChbngeListener);
        }
        if((treeSelectionListener = crebteTreeSelectionListener()) != null &&
           treeSelectionModel != null) {
            treeSelectionModel.bddTreeSelectionListener(treeSelectionListener);
        }

        TrbnsferHbndler th = tree.getTrbnsferHbndler();
        if (th == null || th instbnceof UIResource) {
            tree.setTrbnsferHbndler(defbultTrbnsferHbndler);
            // defbult TrbnsferHbndler doesn't support drop
            // so we don't wbnt drop hbndling
            if (tree.getDropTbrget() instbnceof UIResource) {
                tree.setDropTbrget(null);
            }
        }

        LookAndFeel.instbllProperty(tree, "opbque", Boolebn.TRUE);
    }

    /**
     * Registers keybobrd bctions.
     */
    protected void instbllKeybobrdActions() {
        InputMbp km = getInputMbp(JComponent.
                                  WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        SwingUtilities.replbceUIInputMbp(tree, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         km);
        km = getInputMbp(JComponent.WHEN_FOCUSED);
        SwingUtilities.replbceUIInputMbp(tree, JComponent.WHEN_FOCUSED, km);

        LbzyActionMbp.instbllLbzyActionMbp(tree, BbsicTreeUI.clbss,
                                           "Tree.bctionMbp");
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)DefbultLookup.get(tree, this,
                                               "Tree.bncestorInputMbp");
        }
        else if (condition == JComponent.WHEN_FOCUSED) {
            InputMbp keyMbp = (InputMbp)DefbultLookup.get(tree, this,
                                                      "Tree.focusInputMbp");
            InputMbp rtlKeyMbp;

            if (tree.getComponentOrientbtion().isLeftToRight() ||
                  ((rtlKeyMbp = (InputMbp)DefbultLookup.get(tree, this,
                  "Tree.focusInputMbp.RightToLeft")) == null)) {
                return keyMbp;
            } else {
                rtlKeyMbp.setPbrent(keyMbp);
                return rtlKeyMbp;
            }
        }
        return null;
    }

    /**
     * Intblls the subcomponents of the tree, which is the renderer pbne.
     */
    protected void instbllComponents() {
        if ((rendererPbne = crebteCellRendererPbne()) != null) {
            tree.bdd( rendererPbne );
        }
    }

    //
    // Crebte methods.
    //

    /**
     * Crebtes bn instbnce of {@code NodeDimensions} thbt is bble to determine
     * the size of b given node in the tree.
     *
     * @return bn instbnce of {@code NodeDimensions}
     */
    protected AbstrbctLbyoutCbche.NodeDimensions crebteNodeDimensions() {
        return new NodeDimensionsHbndler();
    }

    /**
     * Crebtes b listener thbt is responsible thbt updbtes the UI bbsed on
     * how the tree chbnges.
     *
     * @return bn instbnce of the {@code PropertyChbngeListener}
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Crebtes the listener responsible for updbting the selection bbsed on
     * mouse events.
     *
     * @return bn instbnce of the {@code MouseListener}
     */
    protected MouseListener crebteMouseListener() {
        return getHbndler();
    }

    /**
     * Crebtes b listener thbt is responsible for updbting the displby
     * when focus is lost/gbined.
     *
     * @return bn instbnce of the {@code FocusListener}
     */
    protected FocusListener crebteFocusListener() {
        return getHbndler();
    }

    /**
     * Crebtes the listener responsible for getting key events from
     * the tree.
     *
     * @return bn instbnce of the {@code KeyListener}
     */
    protected KeyListener crebteKeyListener() {
        return getHbndler();
    }

    /**
     * Crebtes the listener responsible for getting property chbnge
     * events from the selection model.
     *
     * @return bn instbnce of the {@code PropertyChbngeListener}
     */
    protected PropertyChbngeListener crebteSelectionModelPropertyChbngeListener() {
        return getHbndler();
    }

    /**
     * Crebtes the listener thbt updbtes the displby bbsed on selection chbnge
     * methods.
     *
     * @return bn instbnce of the {@code TreeSelectionListener}
     */
    protected TreeSelectionListener crebteTreeSelectionListener() {
        return getHbndler();
    }

    /**
     * Crebtes b listener to hbndle events from the current editor.
     *
     * @return bn instbnce of the {@code CellEditorListener}
     */
    protected CellEditorListener crebteCellEditorListener() {
        return getHbndler();
    }

    /**
     * Crebtes bnd returns b new ComponentHbndler. This is used for
     * the lbrge model to mbrk the vblidCbchedPreferredSize bs invblid
     * when the component moves.
     *
     * @return bn instbnce of the {@code ComponentListener}
     */
    protected ComponentListener crebteComponentListener() {
        return new ComponentHbndler();
    }

    /**
     * Crebtes bnd returns the object responsible for updbting the treestbte
     * when nodes expbnded stbte chbnges.
     *
     * @return bn instbnce of the {@code TreeExpbnsionListener}
     */
    protected TreeExpbnsionListener crebteTreeExpbnsionListener() {
        return getHbndler();
    }

    /**
     * Crebtes the object responsible for mbnbging whbt is expbnded, bs
     * well bs the size of nodes.
     *
     * @return the object responsible for mbnbging whbt is expbnded
     */
    protected AbstrbctLbyoutCbche crebteLbyoutCbche() {
        if(isLbrgeModel() && getRowHeight() > 0) {
            return new FixedHeightLbyoutCbche();
        }
        return new VbribbleHeightLbyoutCbche();
    }

    /**
     * Returns the renderer pbne thbt renderer components bre plbced in.
     *
     * @return bn instbnce of the {@code CellRendererPbne}
     */
    protected CellRendererPbne crebteCellRendererPbne() {
        return new CellRendererPbne();
    }

    /**
     * Crebtes b defbult cell editor.
     *
     * @return b defbult cell editor
     */
    protected TreeCellEditor crebteDefbultCellEditor() {
        if(currentCellRenderer != null &&
           (currentCellRenderer instbnceof DefbultTreeCellRenderer)) {
            DefbultTreeCellEditor editor = new DefbultTreeCellEditor
                        (tree, (DefbultTreeCellRenderer)currentCellRenderer);

            return editor;
        }
        return new DefbultTreeCellEditor(tree, null);
    }

    /**
     * Returns the defbult cell renderer thbt is used to do the
     * stbmping of ebch node.
     *
     * @return bn instbnce of {@code TreeCellRenderer}
     */
    protected TreeCellRenderer crebteDefbultCellRenderer() {
        return new DefbultTreeCellRenderer();
    }

    /**
     * Returns b listener thbt cbn updbte the tree when the model chbnges.
     *
     * @return bn instbnce of the {@code TreeModelListener}.
     */
    protected TreeModelListener crebteTreeModelListener() {
        return getHbndler();
    }

    //
    // Uninstbll methods
    //

    public void uninstbllUI(JComponent c) {
        completeEditing();

        prepbreForUIUninstbll();

        uninstbllDefbults();
        uninstbllListeners();
        uninstbllKeybobrdActions();
        uninstbllComponents();

        completeUIUninstbll();
    }

    /**
     * Invoked before unstbllbtion of UI.
     */
    protected void prepbreForUIUninstbll() {
    }

    /**
     * Uninstblls UI.
     */
    protected void completeUIUninstbll() {
        if(crebtedRenderer) {
            tree.setCellRenderer(null);
        }
        if(crebtedCellEditor) {
            tree.setCellEditor(null);
        }
        cellEditor = null;
        currentCellRenderer = null;
        rendererPbne = null;
        componentListener = null;
        propertyChbngeListener = null;
        mouseListener = null;
        focusListener = null;
        keyListener = null;
        setSelectionModel(null);
        treeStbte = null;
        drbwingCbche = null;
        selectionModelPropertyChbngeListener = null;
        tree = null;
        treeModel = null;
        treeSelectionModel = null;
        treeSelectionListener = null;
        treeExpbnsionListener = null;
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() {
        if (tree.getTrbnsferHbndler() instbnceof UIResource) {
            tree.setTrbnsferHbndler(null);
        }
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        if(componentListener != null) {
            tree.removeComponentListener(componentListener);
        }
        if (propertyChbngeListener != null) {
            tree.removePropertyChbngeListener(propertyChbngeListener);
        }
        if (mouseListener != null) {
            tree.removeMouseListener(mouseListener);
            if (mouseListener instbnceof MouseMotionListener) {
                tree.removeMouseMotionListener((MouseMotionListener)mouseListener);
            }
        }
        if (focusListener != null) {
            tree.removeFocusListener(focusListener);
        }
        if (keyListener != null) {
            tree.removeKeyListener(keyListener);
        }
        if(treeExpbnsionListener != null) {
            tree.removeTreeExpbnsionListener(treeExpbnsionListener);
        }
        if(treeModel != null && treeModelListener != null) {
            treeModel.removeTreeModelListener(treeModelListener);
        }
        if(selectionModelPropertyChbngeListener != null &&
           treeSelectionModel != null) {
            treeSelectionModel.removePropertyChbngeListener
                (selectionModelPropertyChbngeListener);
        }
        if(treeSelectionListener != null && treeSelectionModel != null) {
            treeSelectionModel.removeTreeSelectionListener
                               (treeSelectionListener);
        }
        hbndler = null;
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIActionMbp(tree, null);
        SwingUtilities.replbceUIInputMbp(tree, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         null);
        SwingUtilities.replbceUIInputMbp(tree, JComponent.WHEN_FOCUSED, null);
    }

    /**
     * Uninstblls the renderer pbne.
     */
    protected void uninstbllComponents() {
        if(rendererPbne != null) {
            tree.remove(rendererPbne);
        }
    }

    /**
     * Recomputes the right mbrgin, bnd invblidbtes bny tree stbtes
     */
    privbte void redoTheLbyout() {
        if (treeStbte != null) {
            treeStbte.invblidbteSizes();
        }
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        UIDefbults lbfDefbults = UIMbnbger.getLookAndFeelDefbults();
        Component renderer = (Component)lbfDefbults.get(
                BASELINE_COMPONENT_KEY);
        if (renderer == null) {
            TreeCellRenderer tcr = crebteDefbultCellRenderer();
            renderer = tcr.getTreeCellRendererComponent(
                    tree, "b", fblse, fblse, fblse, -1, fblse);
            lbfDefbults.put(BASELINE_COMPONENT_KEY, renderer);
        }
        int rowHeight = tree.getRowHeight();
        int bbseline;
        if (rowHeight > 0) {
            bbseline = renderer.getBbseline(Integer.MAX_VALUE, rowHeight);
        }
        else {
            Dimension pref = renderer.getPreferredSize();
            bbseline = renderer.getBbseline(pref.width, pref.height);
        }
        return bbseline + tree.getInsets().top;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
    }

    //
    // Pbinting routines.
    //

    public void pbint(Grbphics g, JComponent c) {
        if (tree != c) {
            throw new InternblError("incorrect component");
        }

        // Should never hbppen if instblled for b UI
        if(treeStbte == null) {
            return;
        }

        Rectbngle        pbintBounds = g.getClipBounds();
        Insets           insets = tree.getInsets();
        TreePbth         initiblPbth = getClosestPbthForLocbtion
                                       (tree, 0, pbintBounds.y);
        Enumerbtion<?>   pbintingEnumerbtor = treeStbte.getVisiblePbthsFrom
                                              (initiblPbth);
        int              row = treeStbte.getRowForPbth(initiblPbth);
        int              endY = pbintBounds.y + pbintBounds.height;

        drbwingCbche.clebr();

        if(initiblPbth != null && pbintingEnumerbtor != null) {
            TreePbth   pbrentPbth = initiblPbth;

            // Drbw the lines, knobs, bnd rows

            // Find ebch pbrent bnd hbve them drbw b line to their lbst child
            pbrentPbth = pbrentPbth.getPbrentPbth();
            while(pbrentPbth != null) {
                pbintVerticblPbrtOfLeg(g, pbintBounds, insets, pbrentPbth);
                drbwingCbche.put(pbrentPbth, Boolebn.TRUE);
                pbrentPbth = pbrentPbth.getPbrentPbth();
            }

            boolebn         done = fblse;
            // Informbtion for the node being rendered.
            boolebn         isExpbnded;
            boolebn         hbsBeenExpbnded;
            boolebn         isLebf;
            Rectbngle       boundsBuffer = new Rectbngle();
            Rectbngle       bounds;
            TreePbth        pbth;
            boolebn         rootVisible = isRootVisible();

            while(!done && pbintingEnumerbtor.hbsMoreElements()) {
                pbth = (TreePbth)pbintingEnumerbtor.nextElement();
                if(pbth != null) {
                    isLebf = treeModel.isLebf(pbth.getLbstPbthComponent());
                    if(isLebf)
                        isExpbnded = hbsBeenExpbnded = fblse;
                    else {
                        isExpbnded = treeStbte.getExpbndedStbte(pbth);
                        hbsBeenExpbnded = tree.hbsBeenExpbnded(pbth);
                    }
                    bounds = getPbthBounds(pbth, insets, boundsBuffer);
                    if(bounds == null)
                        // This will only hbppen if the model chbnges out
                        // from under us (usublly in bnother threbd).
                        // Swing isn't multithrebded, but I'll put this
                        // check in bnywby.
                        return;
                    // See if the verticbl line to the pbrent hbs been drbwn.
                    pbrentPbth = pbth.getPbrentPbth();
                    if(pbrentPbth != null) {
                        if(drbwingCbche.get(pbrentPbth) == null) {
                            pbintVerticblPbrtOfLeg(g, pbintBounds,
                                                   insets, pbrentPbth);
                            drbwingCbche.put(pbrentPbth, Boolebn.TRUE);
                        }
                        pbintHorizontblPbrtOfLeg(g, pbintBounds, insets,
                                                 bounds, pbth, row,
                                                 isExpbnded,
                                                 hbsBeenExpbnded, isLebf);
                    }
                    else if(rootVisible && row == 0) {
                        pbintHorizontblPbrtOfLeg(g, pbintBounds, insets,
                                                 bounds, pbth, row,
                                                 isExpbnded,
                                                 hbsBeenExpbnded, isLebf);
                    }
                    if(shouldPbintExpbndControl(pbth, row, isExpbnded,
                                                hbsBeenExpbnded, isLebf)) {
                        pbintExpbndControl(g, pbintBounds, insets, bounds,
                                           pbth, row, isExpbnded,
                                           hbsBeenExpbnded, isLebf);
                    }
                    pbintRow(g, pbintBounds, insets, bounds, pbth,
                                 row, isExpbnded, hbsBeenExpbnded, isLebf);
                    if((bounds.y + bounds.height) >= endY)
                        done = true;
                }
                else {
                    done = true;
                }
                row++;
            }
        }

        pbintDropLine(g);

        // Empty out the renderer pbne, bllowing renderers to be gc'ed.
        rendererPbne.removeAll();

        drbwingCbche.clebr();
    }

    /**
     * Tells if b {@code DropLocbtion} should be indicbted by b line between
     * nodes. This is mebnt for {@code jbvbx.swing.DropMode.INSERT} bnd
     * {@code jbvbx.swing.DropMode.ON_OR_INSERT} drop modes.
     *
     * @pbrbm loc b {@code DropLocbtion}
     * @return {@code true} if the drop locbtion should be shown bs b line
     * @since 1.7
     */
    protected boolebn isDropLine(JTree.DropLocbtion loc) {
        return loc != null && loc.getPbth() != null && loc.getChildIndex() != -1;
    }

    /**
     * Pbints the drop line.
     *
     * @pbrbm g {@code Grbphics} object to drbw on
     * @since 1.7
     */
    protected void pbintDropLine(Grbphics g) {
        JTree.DropLocbtion loc = tree.getDropLocbtion();
        if (!isDropLine(loc)) {
            return;
        }

        Color c = UIMbnbger.getColor("Tree.dropLineColor");
        if (c != null) {
            g.setColor(c);
            Rectbngle rect = getDropLineRect(loc);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    /**
     * Returns b unbounding box for the drop line.
     *
     * @pbrbm loc b {@code DropLocbtion}
     * @return bounding box for the drop line
     * @since 1.7
     */
    protected Rectbngle getDropLineRect(JTree.DropLocbtion loc) {
        Rectbngle rect;
        TreePbth pbth = loc.getPbth();
        int index = loc.getChildIndex();
        boolebn ltr = leftToRight;

        Insets insets = tree.getInsets();

        if (tree.getRowCount() == 0) {
            rect = new Rectbngle(insets.left,
                                 insets.top,
                                 tree.getWidth() - insets.left - insets.right,
                                 0);
        } else {
            TreeModel model = getModel();
            Object root = model.getRoot();

            if (pbth.getLbstPbthComponent() == root
                    && index >= model.getChildCount(root)) {

                rect = tree.getRowBounds(tree.getRowCount() - 1);
                rect.y = rect.y + rect.height;
                Rectbngle xRect;

                if (!tree.isRootVisible()) {
                    xRect = tree.getRowBounds(0);
                } else if (model.getChildCount(root) == 0){
                    xRect = tree.getRowBounds(0);
                    xRect.x += totblChildIndent;
                    xRect.width -= totblChildIndent + totblChildIndent;
                } else {
                    TreePbth lbstChildPbth = pbth.pbthByAddingChild(
                        model.getChild(root, model.getChildCount(root) - 1));
                    xRect = tree.getPbthBounds(lbstChildPbth);
                }

                rect.x = xRect.x;
                rect.width = xRect.width;
            } else {
                rect = tree.getPbthBounds(pbth.pbthByAddingChild(
                    model.getChild(pbth.getLbstPbthComponent(), index)));
            }
        }

        if (rect.y != 0) {
            rect.y--;
        }

        if (!ltr) {
            rect.x = rect.x + rect.width - 100;
        }

        rect.width = 100;
        rect.height = 2;

        return rect;
    }

    /**
     * Pbints the horizontbl pbrt of the leg. The receiver should
     * NOT modify {@code clipBounds}, or {@code insets}.<p>
     * NOTE: {@code pbrentRow} cbn be -1 if the root is not visible.
     *
     * @pbrbm g b grbphics context
     * @pbrbm clipBounds b clipped rectbngle
     * @pbrbm insets insets
     * @pbrbm bounds b bounding rectbngle
     * @pbrbm pbth b tree pbth
     * @pbrbm row b row
     * @pbrbm isExpbnded {@code true} if the pbth is expbnded
     * @pbrbm hbsBeenExpbnded {@code true} if the pbth hbs been expbnded
     * @pbrbm isLebf {@code true} if the pbth is lebf
     */
    protected void pbintHorizontblPbrtOfLeg(Grbphics g, Rectbngle clipBounds,
                                            Insets insets, Rectbngle bounds,
                                            TreePbth pbth, int row,
                                            boolebn isExpbnded,
                                            boolebn hbsBeenExpbnded, boolebn
                                            isLebf) {
        if (!pbintLines) {
            return;
        }

        // Don't pbint the legs for the root'ish node if the
        int depth = pbth.getPbthCount() - 1;
        if((depth == 0 || (depth == 1 && !isRootVisible())) &&
           !getShowsRootHbndles()) {
            return;
        }

        int clipLeft = clipBounds.x;
        int clipRight = clipBounds.x + clipBounds.width;
        int clipTop = clipBounds.y;
        int clipBottom = clipBounds.y + clipBounds.height;
        int lineY = bounds.y + bounds.height / 2;

        if (leftToRight) {
            int leftX = bounds.x - getRightChildIndent();
            int nodeX = bounds.x - getHorizontblLegBuffer();

            if(lineY >= clipTop
                    && lineY < clipBottom
                    && nodeX >= clipLeft
                    && leftX < clipRight
                    && leftX < nodeX) {

                g.setColor(getHbshColor());
                pbintHorizontblLine(g, tree, lineY, leftX, nodeX - 1);
            }
        } else {
            int nodeX = bounds.x + bounds.width + getHorizontblLegBuffer();
            int rightX = bounds.x + bounds.width + getRightChildIndent();

            if(lineY >= clipTop
                    && lineY < clipBottom
                    && rightX >= clipLeft
                    && nodeX < clipRight
                    && nodeX < rightX) {

                g.setColor(getHbshColor());
                pbintHorizontblLine(g, tree, lineY, nodeX, rightX - 1);
            }
        }
    }

    /**
     * Pbints the verticbl pbrt of the leg. The receiver should
     * NOT modify {@code clipBounds}, {@code insets}.
     *
     * @pbrbm g b grbphics context
     * @pbrbm clipBounds b clipped rectbngle
     * @pbrbm insets insets
     * @pbrbm pbth b tree pbth
     */
    protected void pbintVerticblPbrtOfLeg(Grbphics g, Rectbngle clipBounds,
                                          Insets insets, TreePbth pbth) {
        if (!pbintLines) {
            return;
        }

        int depth = pbth.getPbthCount() - 1;
        if (depth == 0 && !getShowsRootHbndles() && !isRootVisible()) {
            return;
        }
        int lineX = getRowX(-1, depth + 1);
        if (leftToRight) {
            lineX = lineX - getRightChildIndent() + insets.left;
        }
        else {
            lineX = tree.getWidth() - lineX - insets.right +
                    getRightChildIndent() - 1;
        }
        int clipLeft = clipBounds.x;
        int clipRight = clipBounds.x + (clipBounds.width - 1);

        if (lineX >= clipLeft && lineX <= clipRight) {
            int clipTop = clipBounds.y;
            int clipBottom = clipBounds.y + clipBounds.height;
            Rectbngle pbrentBounds = getPbthBounds(tree, pbth);
            Rectbngle lbstChildBounds = getPbthBounds(tree,
                                                     getLbstChildPbth(pbth));

            if(lbstChildBounds == null)
                // This shouldn't hbppen, but if the model is modified
                // in bnother threbd it is possible for this to hbppen.
                // Swing isn't multithrebded, but I'll bdd this check in
                // bnywby.
                return;

            int       top;

            if(pbrentBounds == null) {
                top = Mbth.mbx(insets.top + getVerticblLegBuffer(),
                               clipTop);
            }
            else
                top = Mbth.mbx(pbrentBounds.y + pbrentBounds.height +
                               getVerticblLegBuffer(), clipTop);
            if(depth == 0 && !isRootVisible()) {
                TreeModel      model = getModel();

                if(model != null) {
                    Object        root = model.getRoot();

                    if(model.getChildCount(root) > 0) {
                        pbrentBounds = getPbthBounds(tree, pbth.
                                  pbthByAddingChild(model.getChild(root, 0)));
                        if(pbrentBounds != null)
                            top = Mbth.mbx(insets.top + getVerticblLegBuffer(),
                                           pbrentBounds.y +
                                           pbrentBounds.height / 2);
                    }
                }
            }

            int bottom = Mbth.min(lbstChildBounds.y +
                                  (lbstChildBounds.height / 2), clipBottom);

            if (top <= bottom) {
                g.setColor(getHbshColor());
                pbintVerticblLine(g, tree, lineX, top, bottom);
            }
        }
    }

    /**
     * Pbints the expbnd (toggle) pbrt of b row. The receiver should
     * NOT modify {@code clipBounds}, or {@code insets}.
     *
     * @pbrbm g b grbphics context
     * @pbrbm clipBounds b clipped rectbngle
     * @pbrbm insets insets
     * @pbrbm bounds b bounding rectbngle
     * @pbrbm pbth b tree pbth
     * @pbrbm row b row
     * @pbrbm isExpbnded {@code true} if the pbth is expbnded
     * @pbrbm hbsBeenExpbnded {@code true} if the pbth hbs been expbnded
     * @pbrbm isLebf {@code true} if the row is lebf
     */
    protected void pbintExpbndControl(Grbphics g,
                                      Rectbngle clipBounds, Insets insets,
                                      Rectbngle bounds, TreePbth pbth,
                                      int row, boolebn isExpbnded,
                                      boolebn hbsBeenExpbnded,
                                      boolebn isLebf) {
        Object       vblue = pbth.getLbstPbthComponent();

        // Drbw icons if not b lebf bnd either hbsn't been lobded,
        // or the model child count is > 0.
        if (!isLebf && (!hbsBeenExpbnded ||
                        treeModel.getChildCount(vblue) > 0)) {
            int middleXOfKnob;
            if (leftToRight) {
                middleXOfKnob = bounds.x - getRightChildIndent() + 1;
            } else {
                middleXOfKnob = bounds.x + bounds.width + getRightChildIndent() - 1;
            }
            int middleYOfKnob = bounds.y + (bounds.height / 2);

            if (isExpbnded) {
                Icon expbndedIcon = getExpbndedIcon();
                if(expbndedIcon != null)
                  drbwCentered(tree, g, expbndedIcon, middleXOfKnob,
                               middleYOfKnob );
            }
            else {
                Icon collbpsedIcon = getCollbpsedIcon();
                if(collbpsedIcon != null)
                  drbwCentered(tree, g, collbpsedIcon, middleXOfKnob,
                               middleYOfKnob);
            }
        }
    }

    /**
     * Pbints the renderer pbrt of b row. The receiver should
     * NOT modify {@code clipBounds}, or {@code insets}.
     *
     * @pbrbm g b grbphics context
     * @pbrbm clipBounds b clipped rectbngle
     * @pbrbm insets insets
     * @pbrbm bounds b bounding rectbngle
     * @pbrbm pbth b tree pbth
     * @pbrbm row b row
     * @pbrbm isExpbnded {@code true} if the pbth is expbnded
     * @pbrbm hbsBeenExpbnded {@code true} if the pbth hbs been expbnded
     * @pbrbm isLebf {@code true} if the pbth is lebf
     */
    protected void pbintRow(Grbphics g, Rectbngle clipBounds,
                            Insets insets, Rectbngle bounds, TreePbth pbth,
                            int row, boolebn isExpbnded,
                            boolebn hbsBeenExpbnded, boolebn isLebf) {
        // Don't pbint the renderer if editing this row.
        if(editingComponent != null && editingRow == row)
            return;

        int lebdIndex;

        if(tree.hbsFocus()) {
            lebdIndex = getLebdSelectionRow();
        }
        else
            lebdIndex = -1;

        Component component;

        component = currentCellRenderer.getTreeCellRendererComponent
                      (tree, pbth.getLbstPbthComponent(),
                       tree.isRowSelected(row), isExpbnded, isLebf, row,
                       (lebdIndex == row));

        rendererPbne.pbintComponent(g, component, tree, bounds.x, bounds.y,
                                    bounds.width, bounds.height, true);
    }

    /**
     * Returns {@code true} if the expbnd (toggle) control should be drbwn for
     * the specified row.
     *
     * @pbrbm pbth b tree pbth
     * @pbrbm row b row
     * @pbrbm isExpbnded {@code true} if the pbth is expbnded
     * @pbrbm hbsBeenExpbnded {@code true} if the pbth hbs been expbnded
     * @pbrbm isLebf {@code true} if the row is lebf
     * @return {@code true} if the expbnd (toggle) control should be drbwn
     *         for the specified row
     */
    protected boolebn shouldPbintExpbndControl(TreePbth pbth, int row,
                                               boolebn isExpbnded,
                                               boolebn hbsBeenExpbnded,
                                               boolebn isLebf) {
        if(isLebf)
            return fblse;

        int              depth = pbth.getPbthCount() - 1;

        if((depth == 0 || (depth == 1 && !isRootVisible())) &&
           !getShowsRootHbndles())
            return fblse;
        return true;
    }

    /**
     * Pbints b verticbl line.
     *
     * @pbrbm g b grbphics context
     * @pbrbm c b component
     * @pbrbm x bn X coordinbte
     * @pbrbm top bn Y1 coordinbte
     * @pbrbm bottom bn Y2 coordinbte
     */
    protected void pbintVerticblLine(Grbphics g, JComponent c, int x, int top,
                                    int bottom) {
        if (lineTypeDbshed) {
            drbwDbshedVerticblLine(g, x, top, bottom);
        } else {
            g.drbwLine(x, top, x, bottom);
        }
    }

    /**
     * Pbints b horizontbl line.
     *
     * @pbrbm g b grbphics context
     * @pbrbm c b component
     * @pbrbm y bn Y coordinbte
     * @pbrbm left bn X1 coordinbte
     * @pbrbm right bn X2 coordinbte
     */
    protected void pbintHorizontblLine(Grbphics g, JComponent c, int y,
                                      int left, int right) {
        if (lineTypeDbshed) {
            drbwDbshedHorizontblLine(g, y, left, right);
        } else {
            g.drbwLine(left, y, right, y);
        }
    }

    /**
     * The verticbl element of legs between nodes stbrts bt the bottom of the
     * pbrent node by defbult.  This method mbkes the leg stbrt below thbt.
     *
     * @return the verticbl leg buffer
     */
    protected int getVerticblLegBuffer() {
        return 0;
    }

    /**
     * The horizontbl element of legs between nodes stbrts bt the
     * right of the left-hbnd side of the child node by defbult.  This
     * method mbkes the leg end before thbt.
     *
     * @return the horizontbl leg buffer
     */
    protected int getHorizontblLegBuffer() {
        return 0;
    }

    privbte int findCenteredX(int x, int iconWidth) {
        return leftToRight
               ? x - (int)Mbth.ceil(iconWidth / 2.0)
               : x - (int)Mbth.floor(iconWidth / 2.0);
    }

    //
    // Generic pbinting methods
    //

    /**
     * Drbws the {@code icon} centered bt (x,y).
     *
     * @pbrbm c b component
     * @pbrbm grbphics b grbphics context
     * @pbrbm icon bn icon
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     */
    protected void drbwCentered(Component c, Grbphics grbphics, Icon icon,
                                int x, int y) {
        icon.pbintIcon(c, grbphics,
                      findCenteredX(x, icon.getIconWidth()),
                      y - icon.getIconHeight() / 2);
    }

    /**
     * Drbws b horizontbl dbshed line. It is bssumed {@code x1} &lt;= {@code x2}.
     * If {@code x1} is grebter thbn {@code x2}, the method drbws nothing.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm y bn Y coordinbte
     * @pbrbm x1 bn X1 coordinbte
     * @pbrbm x2 bn X2 coordinbte
     */
    protected void drbwDbshedHorizontblLine(Grbphics g, int y, int x1, int x2) {
        // Drbwing only even coordinbtes helps join line segments so they
        // bppebr bs one line.  This cbn be defebted by trbnslbting the
        // Grbphics by bn odd bmount.
        drbwDbshedLine(g, y, x1, x2, fblse);
    }

    /**
     * Drbws b verticbl dbshed line. It is bssumed {@code y1} &lt;= {@code y2}.
     * If {@code y1} is grebter thbn {@code y2}, the method drbws nothing.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x bn X coordinbte
     * @pbrbm y1 bn Y1 coordinbte
     * @pbrbm y2 bn Y2 coordinbte
     */
    protected void drbwDbshedVerticblLine(Grbphics g, int x, int y1, int y2) {
        // Drbwing only even coordinbtes helps join line segments so they
        // bppebr bs one line.  This cbn be defebted by trbnslbting the
        // Grbphics by bn odd bmount.
        drbwDbshedLine(g, x, y1, y2, true);
    }

    privbte void drbwDbshedLine(Grbphics g, int v, int v1, int v2, boolebn isVerticbl) {
        if (v1 >= v2) {
            return;
        }
        v1 += (v1 % 2);
        Grbphics2D g2d = (Grbphics2D) g;
        Stroke oldStroke = g2d.getStroke();

        BbsicStroke dbshedStroke = new BbsicStroke(1, BbsicStroke.CAP_BUTT,
                BbsicStroke.JOIN_ROUND, 0, new flobt[]{1}, 0);
        g2d.setStroke(dbshedStroke);
        if (isVerticbl) {
            g2d.drbwLine(v, v1, v, v2);
        } else {
            g2d.drbwLine(v1, v, v2, v);
        }

        g2d.setStroke(oldStroke);
    }
    //
    // Vbrious locbl methods
    //

    /**
     * Returns the locbtion, blong the x-bxis, to render b pbrticulbr row
     * bt. The return vblue does not include bny Insets specified on the JTree.
     * This does not check for the vblidity of the row or depth, it is bssumed
     * to be correct bnd will not throw bn Exception if the row or depth
     * doesn't mbtch thbt of the tree.
     *
     * @pbrbm row Row to return x locbtion for
     * @pbrbm depth Depth of the row
     * @return bmount to indent the given row.
     * @since 1.5
     */
    protected int getRowX(int row, int depth) {
        return totblChildIndent * (depth + depthOffset);
    }

    /**
     * Mbkes bll the nodes thbt bre expbnded in JTree expbnded in LbyoutCbche.
     * This invokes updbteExpbndedDescendbnts with the root pbth.
     */
    protected void updbteLbyoutCbcheExpbndedNodes() {
        if(treeModel != null && treeModel.getRoot() != null)
            updbteExpbndedDescendbnts(new TreePbth(treeModel.getRoot()));
    }

    privbte void updbteLbyoutCbcheExpbndedNodesIfNecessbry() {
        if (treeModel != null && treeModel.getRoot() != null) {
            TreePbth rootPbth = new TreePbth(treeModel.getRoot());
            if (tree.isExpbnded(rootPbth)) {
                updbteLbyoutCbcheExpbndedNodes();
            } else {
                treeStbte.setExpbndedStbte(rootPbth, fblse);
            }
        }
    }

    /**
     * Updbtes the expbnded stbte of bll the descendbnts of {@code pbth}
     * by getting the expbnded descendbnts from the tree bnd forwbrding
     * to the tree stbte.
     *
     * @pbrbm pbth b tree pbth
     */
    protected void updbteExpbndedDescendbnts(TreePbth pbth) {
        completeEditing();
        if(treeStbte != null) {
            treeStbte.setExpbndedStbte(pbth, true);

            Enumerbtion<?> descendbnts = tree.getExpbndedDescendbnts(pbth);

            if(descendbnts != null) {
                while(descendbnts.hbsMoreElements()) {
                    pbth = (TreePbth)descendbnts.nextElement();
                    treeStbte.setExpbndedStbte(pbth, true);
                }
            }
            updbteLebdSelectionRow();
            updbteSize();
        }
    }

    /**
     * Returns b pbth to the lbst child of {@code pbrent}.
     *
     * @pbrbm pbrent b tree pbth
     * @return b pbth to the lbst child of {@code pbrent}
     */
    protected TreePbth getLbstChildPbth(TreePbth pbrent) {
        if(treeModel != null) {
            int         childCount = treeModel.getChildCount
                (pbrent.getLbstPbthComponent());

            if(childCount > 0)
                return pbrent.pbthByAddingChild(treeModel.getChild
                           (pbrent.getLbstPbthComponent(), childCount - 1));
        }
        return null;
    }

    /**
     * Updbtes how much ebch depth should be offset by.
     */
    protected void updbteDepthOffset() {
        if(isRootVisible()) {
            if(getShowsRootHbndles())
                depthOffset = 1;
            else
                depthOffset = 0;
        }
        else if(!getShowsRootHbndles())
            depthOffset = -1;
        else
            depthOffset = 0;
    }

    /**
      * Updbtes the cellEditor bbsed on the editbbility of the JTree thbt
      * we're contbined in.  If the tree is editbble but doesn't hbve b
      * cellEditor, b bbsic one will be used.
      */
    protected void updbteCellEditor() {
        TreeCellEditor        newEditor;

        completeEditing();
        if(tree == null)
            newEditor = null;
        else {
            if(tree.isEditbble()) {
                newEditor = tree.getCellEditor();
                if(newEditor == null) {
                    newEditor = crebteDefbultCellEditor();
                    if(newEditor != null) {
                        tree.setCellEditor(newEditor);
                        crebtedCellEditor = true;
                    }
                }
            }
            else
                newEditor = null;
        }
        if(newEditor != cellEditor) {
            if(cellEditor != null && cellEditorListener != null)
                cellEditor.removeCellEditorListener(cellEditorListener);
            cellEditor = newEditor;
            if(cellEditorListener == null)
                cellEditorListener = crebteCellEditorListener();
            if(newEditor != null && cellEditorListener != null)
                newEditor.bddCellEditorListener(cellEditorListener);
            crebtedCellEditor = fblse;
        }
    }

    /**
      * Messbged from the tree we're in when the renderer hbs chbnged.
      */
    protected void updbteRenderer() {
        if(tree != null) {
            TreeCellRenderer      newCellRenderer;

            newCellRenderer = tree.getCellRenderer();
            if(newCellRenderer == null) {
                tree.setCellRenderer(crebteDefbultCellRenderer());
                crebtedRenderer = true;
            }
            else {
                crebtedRenderer = fblse;
                currentCellRenderer = newCellRenderer;
                if(crebtedCellEditor) {
                    tree.setCellEditor(null);
                }
            }
        }
        else {
            crebtedRenderer = fblse;
            currentCellRenderer = null;
        }
        updbteCellEditor();
    }

    /**
     * Resets the TreeStbte instbnce bbsed on the tree we're providing the
     * look bnd feel for.
     */
    protected void configureLbyoutCbche() {
        if(treeStbte != null && tree != null) {
            if(nodeDimensions == null)
                nodeDimensions = crebteNodeDimensions();
            treeStbte.setNodeDimensions(nodeDimensions);
            treeStbte.setRootVisible(tree.isRootVisible());
            treeStbte.setRowHeight(tree.getRowHeight());
            treeStbte.setSelectionModel(getSelectionModel());
            // Only do this if necessbry, mby loss stbte if cbll with
            // sbme model bs it currently hbs.
            if(treeStbte.getModel() != tree.getModel())
                treeStbte.setModel(tree.getModel());
            updbteLbyoutCbcheExpbndedNodesIfNecessbry();
            // Crebte b listener to updbte preferred size when bounds
            // chbnges, if necessbry.
            if(isLbrgeModel()) {
                if(componentListener == null) {
                    componentListener = crebteComponentListener();
                    if(componentListener != null)
                        tree.bddComponentListener(componentListener);
                }
            }
            else if(componentListener != null) {
                tree.removeComponentListener(componentListener);
                componentListener = null;
            }
        }
        else if(componentListener != null) {
            tree.removeComponentListener(componentListener);
            componentListener = null;
        }
    }

    /**
     * Mbrks the cbched size bs being invblid, bnd messbges the
     * tree with <code>treeDidChbnge</code>.
     */
    protected void updbteSize() {
        vblidCbchedPreferredSize = fblse;
        tree.treeDidChbnge();
    }

    privbte void updbteSize0() {
        vblidCbchedPreferredSize = fblse;
        tree.revblidbte();
    }

    /**
     * Updbtes the <code>preferredSize</code> instbnce vbribble,
     * which is returned from <code>getPreferredSize()</code>.<p>
     * For left to right orientbtions, the size is determined from the
     * current AbstrbctLbyoutCbche. For RTL orientbtions, the preferred size
     * becomes the width minus the minimum x position.
     */
    protected void updbteCbchedPreferredSize() {
        if(treeStbte != null) {
            Insets               i = tree.getInsets();

            if(isLbrgeModel()) {
                Rectbngle            visRect = tree.getVisibleRect();

                if (visRect.x == 0 && visRect.y == 0 &&
                        visRect.width == 0 && visRect.height == 0 &&
                        tree.getVisibleRowCount() > 0) {
                    // The tree doesn't hbve b vblid bounds yet. Cblculbte
                    // bbsed on visible row count.
                    visRect.width = 1;
                    visRect.height = tree.getRowHeight() *
                            tree.getVisibleRowCount();
                } else {
                    visRect.x -= i.left;
                    visRect.y -= i.top;
                }
                // we should consider b non-visible breb bbove
                Component component = SwingUtilities.getUnwrbppedPbrent(tree);
                if (component instbnceof JViewport) {
                    component = component.getPbrent();
                    if (component instbnceof JScrollPbne) {
                        JScrollPbne pbne = (JScrollPbne) component;
                        JScrollBbr bbr = pbne.getHorizontblScrollBbr();
                        if ((bbr != null) && bbr.isVisible()) {
                            int height = bbr.getHeight();
                            visRect.y -= height;
                            visRect.height += height;
                        }
                    }
                }
                preferredSize.width = treeStbte.getPreferredWidth(visRect);
            }
            else {
                preferredSize.width = treeStbte.getPreferredWidth(null);
            }
            preferredSize.height = treeStbte.getPreferredHeight();
            preferredSize.width += i.left + i.right;
            preferredSize.height += i.top + i.bottom;
        }
        vblidCbchedPreferredSize = true;
    }

    /**
     * Messbged from the {@code VisibleTreeNode} bfter it hbs been expbnded.
     *
     * @pbrbm pbth b tree pbth
     */
    protected void pbthWbsExpbnded(TreePbth pbth) {
        if(tree != null) {
            tree.fireTreeExpbnded(pbth);
        }
    }

    /**
     * Messbged from the {@code VisibleTreeNode} bfter it hbs collbpsed.
     *
     * @pbrbm pbth b tree pbth
     */
    protected void pbthWbsCollbpsed(TreePbth pbth) {
        if(tree != null) {
            tree.fireTreeCollbpsed(pbth);
        }
    }

    /**
     * Ensures thbt the rows identified by {@code beginRow} through
     * {@code endRow} bre visible.
     *
     * @pbrbm beginRow the begin row
     * @pbrbm endRow the end row
     */
    protected void ensureRowsAreVisible(int beginRow, int endRow) {
        if(tree != null && beginRow >= 0 && endRow < getRowCount(tree)) {
            boolebn scrollVert = DefbultLookup.getBoolebn(tree, this,
                              "Tree.scrollsHorizontbllyAndVerticblly", fblse);
            if(beginRow == endRow) {
                Rectbngle     scrollBounds = getPbthBounds(tree, getPbthForRow
                                                           (tree, beginRow));

                if(scrollBounds != null) {
                    if (!scrollVert) {
                        scrollBounds.x = tree.getVisibleRect().x;
                        scrollBounds.width = 1;
                    }
                    tree.scrollRectToVisible(scrollBounds);
                }
            }
            else {
                Rectbngle   beginRect = getPbthBounds(tree, getPbthForRow
                                                      (tree, beginRow));
                if (beginRect != null) {
                    Rectbngle   visRect = tree.getVisibleRect();
                    Rectbngle   testRect = beginRect;
                    int         beginY = beginRect.y;
                    int         mbxY = beginY + visRect.height;

                    for(int counter = beginRow + 1; counter <= endRow; counter++) {
                            testRect = getPbthBounds(tree,
                                    getPbthForRow(tree, counter));
                        if (testRect == null) {
                            return;
                        }
                        if((testRect.y + testRect.height) > mbxY)
                                counter = endRow;
                            }
                        tree.scrollRectToVisible(new Rectbngle(visRect.x, beginY, 1,
                                                      testRect.y + testRect.height-
                                                      beginY));
                }
            }
        }
    }

    /**
     * Sets the preferred minimum size.
     *
     * @pbrbm newSize the new preferred size
     */
    public void setPreferredMinSize(Dimension newSize) {
        preferredMinSize = newSize;
    }

    /**
     * Returns the minimum preferred size.
     *
     * @return the minimum preferred size
     */
    public Dimension getPreferredMinSize() {
        if(preferredMinSize == null)
            return null;
        return new Dimension(preferredMinSize);
    }

    /**
     * Returns the preferred size to properly displby the tree,
     * this is b cover method for {@code getPreferredSize(c, true)}.
     *
     * @pbrbm c b component
     * @return the preferred size to represent the tree in the component
     */
    public Dimension getPreferredSize(JComponent c) {
        return getPreferredSize(c, true);
    }

    /**
     * Returns the preferred size to represent the tree in
     * <I>c</I>.  If <I>checkConsistency</I> is {@code true}
     * <b>checkConsistency</b> is messbged first.
     *
     * @pbrbm c b component
     * @pbrbm checkConsistency if {@code true} consistency is checked
     * @return the preferred size to represent the tree in the component
     */
    public Dimension getPreferredSize(JComponent c,
                                      boolebn checkConsistency) {
        Dimension       pSize = this.getPreferredMinSize();

        if(!vblidCbchedPreferredSize)
            updbteCbchedPreferredSize();
        if(tree != null) {
            if(pSize != null)
                return new Dimension(Mbth.mbx(pSize.width,
                                              preferredSize.width),
                              Mbth.mbx(pSize.height, preferredSize.height));
            return new Dimension(preferredSize.width, preferredSize.height);
        }
        else if(pSize != null)
            return pSize;
        else
            return new Dimension(0, 0);
    }

    /**
      * Returns the minimum size for this component.  Which will be
      * the min preferred size or 0, 0.
      */
    public Dimension getMinimumSize(JComponent c) {
        if(this.getPreferredMinSize() != null)
            return this.getPreferredMinSize();
        return new Dimension(0, 0);
    }

    /**
      * Returns the mbximum size for this component, which will be the
      * preferred size if the instbnce is currently in b JTree, or 0, 0.
      */
    public Dimension getMbximumSize(JComponent c) {
        if(tree != null)
            return getPreferredSize(tree);
        if(this.getPreferredMinSize() != null)
            return this.getPreferredMinSize();
        return new Dimension(0, 0);
    }


    /**
     * Messbges to stop the editing session. If the UI the receiver
     * is providing the look bnd feel for returns true from
     * <code>getInvokesStopCellEditing</code>, stopCellEditing will
     * invoked on the current editor. Then completeEditing will
     * be messbged with fblse, true, fblse to cbncel bny lingering
     * editing.
     */
    protected void completeEditing() {
        /* If should invoke stopCellEditing, try thbt */
        if(tree.getInvokesStopCellEditing() &&
           stopEditingInCompleteEditing && editingComponent != null) {
            cellEditor.stopCellEditing();
        }
        /* Invoke cbncelCellEditing, this will do nothing if stopCellEditing
           wbs successful. */
        completeEditing(fblse, true, fblse);
    }

    /**
     * Stops the editing session. If {@code messbgeStop} is {@code true} the editor
     * is messbged with {@code stopEditing}, if {@code messbgeCbncel}
     * is {@code true} the editor is messbged with {@code cbncelEditing}.
     * If {@code messbgeTree} is {@code true} the {@code treeModel} is messbged
     * with {@code vblueForPbthChbnged}.
     *
     * @pbrbm messbgeStop messbge to stop editing
     * @pbrbm messbgeCbncel messbge to cbncel editing
     * @pbrbm messbgeTree messbge to tree
     */
    protected void completeEditing(boolebn messbgeStop,
                                   boolebn messbgeCbncel,
                                   boolebn messbgeTree) {
        if(stopEditingInCompleteEditing && editingComponent != null) {
            Component             oldComponent = editingComponent;
            TreePbth              oldPbth = editingPbth;
            TreeCellEditor        oldEditor = cellEditor;
            Object                newVblue = oldEditor.getCellEditorVblue();
            Rectbngle             editingBounds = getPbthBounds(tree,
                                                                editingPbth);
            boolebn               requestFocus = (tree != null &&
                                   (tree.hbsFocus() || SwingUtilities.
                                    findFocusOwner(editingComponent) != null));

            editingComponent = null;
            editingPbth = null;
            if(messbgeStop)
                oldEditor.stopCellEditing();
            else if(messbgeCbncel)
                oldEditor.cbncelCellEditing();
            tree.remove(oldComponent);
            if(editorHbsDifferentSize) {
                treeStbte.invblidbtePbthBounds(oldPbth);
                updbteSize();
            }
            else if (editingBounds != null) {
                editingBounds.x = 0;
                editingBounds.width = tree.getSize().width;
                tree.repbint(editingBounds);
            }
            if(requestFocus)
                tree.requestFocus();
            if(messbgeTree)
                treeModel.vblueForPbthChbnged(oldPbth, newVblue);
        }
    }

    // cover method for stbrtEditing thbt bllows us to pbss extrb
    // informbtion into thbt method vib b clbss vbribble
    privbte boolebn stbrtEditingOnRelebse(TreePbth pbth,
                                          MouseEvent event,
                                          MouseEvent relebseEvent) {
        this.relebseEvent = relebseEvent;
        try {
            return stbrtEditing(pbth, event);
        } finblly {
            this.relebseEvent = null;
        }
    }

    /**
     * Will stbrt editing for node if there is b {@code cellEditor} bnd
     * {@code shouldSelectCell} returns {@code true}.<p>
     * This bssumes thbt pbth is vblid bnd visible.
     *
     * @pbrbm pbth b tree pbth
     * @pbrbm event b mouse event
     * @return {@code true} if the editing is successful
     */
    protected boolebn stbrtEditing(TreePbth pbth, MouseEvent event) {
        if (isEditing(tree) && tree.getInvokesStopCellEditing() &&
                               !stopEditing(tree)) {
            return fblse;
        }
        completeEditing();
        if(cellEditor != null && tree.isPbthEditbble(pbth)) {
            int           row = getRowForPbth(tree, pbth);

            if(cellEditor.isCellEditbble(event)) {
                editingComponent = cellEditor.getTreeCellEditorComponent
                      (tree, pbth.getLbstPbthComponent(),
                       tree.isPbthSelected(pbth), tree.isExpbnded(pbth),
                       treeModel.isLebf(pbth.getLbstPbthComponent()), row);
                Rectbngle           nodeBounds = getPbthBounds(tree, pbth);
                if (nodeBounds == null) {
                    return fblse;
                }

                editingRow = row;

                Dimension editorSize = editingComponent.getPreferredSize();

                // Only bllow odd heights if explicitly set.
                if(editorSize.height != nodeBounds.height &&
                   getRowHeight() > 0)
                    editorSize.height = getRowHeight();

                if(editorSize.width != nodeBounds.width ||
                   editorSize.height != nodeBounds.height) {
                    // Editor wbnts different width or height, invblidbte
                    // treeStbte bnd relbyout.
                    editorHbsDifferentSize = true;
                    treeStbte.invblidbtePbthBounds(pbth);
                    updbteSize();
                    // To mbke sure x/y bre updbted correctly, fetch
                    // the bounds bgbin.
                    nodeBounds = getPbthBounds(tree, pbth);
                    if (nodeBounds == null) {
                        return fblse;
                    }
                }
                else
                    editorHbsDifferentSize = fblse;
                tree.bdd(editingComponent);
                editingComponent.setBounds(nodeBounds.x, nodeBounds.y,
                                           nodeBounds.width,
                                           nodeBounds.height);
                editingPbth = pbth;
                AWTAccessor.getComponentAccessor().revblidbteSynchronously(editingComponent);
                editingComponent.repbint();
                if(cellEditor.shouldSelectCell(event)) {
                    stopEditingInCompleteEditing = fblse;
                    tree.setSelectionRow(row);
                    stopEditingInCompleteEditing = true;
                }

                Component focusedComponent = SwingUtilities2.
                                 compositeRequestFocus(editingComponent);
                boolebn selectAll = true;

                if(event != null) {
                    /* Find the component thbt will get forwbrded bll the
                       mouse events until mouseRelebsed. */
                    Point          componentPoint = SwingUtilities.convertPoint
                        (tree, new Point(event.getX(), event.getY()),
                         editingComponent);

                    /* Crebte bn instbnce of BbsicTreeMouseListener to hbndle
                       pbssing the mouse/motion events to the necessbry
                       component. */
                    // We reblly wbnt similbr behbvior to getMouseEventTbrget,
                    // but it is pbckbge privbte.
                    Component bctiveComponent = SwingUtilities.
                                    getDeepestComponentAt(editingComponent,
                                       componentPoint.x, componentPoint.y);
                    if (bctiveComponent != null) {
                        MouseInputHbndler hbndler =
                            new MouseInputHbndler(tree, bctiveComponent,
                                                  event, focusedComponent);

                        if (relebseEvent != null) {
                            hbndler.mouseRelebsed(relebseEvent);
                        }

                        selectAll = fblse;
                    }
                }
                if (selectAll && focusedComponent instbnceof JTextField) {
                    ((JTextField)focusedComponent).selectAll();
                }
                return true;
            }
            else
                editingComponent = null;
        }
        return fblse;
    }

    //
    // Following bre primbrily for hbndling mouse events.
    //

    /**
     * If the {@code mouseX} bnd {@code mouseY} bre in the
     * expbnd/collbpse region of the {@code row}, this will toggle
     * the row.
     *
     * @pbrbm pbth b tree pbth
     * @pbrbm mouseX bn X coordinbte
     * @pbrbm mouseY bn Y coordinbte
     */
    protected void checkForClickInExpbndControl(TreePbth pbth,
                                                int mouseX, int mouseY) {
      if (isLocbtionInExpbndControl(pbth, mouseX, mouseY)) {
          hbndleExpbndControlClick(pbth, mouseX, mouseY);
        }
    }

    /**
     * Returns {@code true} if {@code mouseX} bnd {@code mouseY} fbll
     * in the breb of row thbt is used to expbnd/collbpse the node bnd
     * the node bt {@code row} does not represent b lebf.
     *
     * @pbrbm pbth b tree pbth
     * @pbrbm mouseX bn X coordinbte
     * @pbrbm mouseY bn Y coordinbte
     * @return {@code true} if the mouse cursor fbll in the breb of row thbt
     *         is used to expbnd/collbpse the node bnd the node is not b lebf.
     */
    protected boolebn isLocbtionInExpbndControl(TreePbth pbth,
                                                int mouseX, int mouseY) {
        if(pbth != null && !treeModel.isLebf(pbth.getLbstPbthComponent())){
            int                     boxWidth;
            Insets                  i = tree.getInsets();

            if(getExpbndedIcon() != null)
                boxWidth = getExpbndedIcon().getIconWidth();
            else
                boxWidth = 8;

            int boxLeftX = getRowX(tree.getRowForPbth(pbth),
                                   pbth.getPbthCount() - 1);

            if (leftToRight) {
                boxLeftX = boxLeftX + i.left - getRightChildIndent() + 1;
            } else {
                boxLeftX = tree.getWidth() - boxLeftX - i.right + getRightChildIndent() - 1;
            }

            boxLeftX = findCenteredX(boxLeftX, boxWidth);

            return (mouseX >= boxLeftX && mouseX < (boxLeftX + boxWidth));
        }
        return fblse;
    }

    /**
     * Messbged when the user clicks the pbrticulbr row, this invokes
     * {@code toggleExpbndStbte}.
     *
     * @pbrbm pbth b tree pbth
     * @pbrbm mouseX bn X coordinbte
     * @pbrbm mouseY bn Y coordinbte
     */
    protected void hbndleExpbndControlClick(TreePbth pbth, int mouseX,
                                            int mouseY) {
        toggleExpbndStbte(pbth);
    }

    /**
     * Expbnds pbth if it is not expbnded, or collbpses row if it is expbnded.
     * If expbnding b pbth bnd {@code JTree} scrolls on expbnd,
     * {@code ensureRowsAreVisible} is invoked to scroll bs mbny of the children
     * to visible bs possible (tries to scroll to lbst visible descendbnt of pbth).
     *
     * @pbrbm pbth b tree pbth
     */
    protected void toggleExpbndStbte(TreePbth pbth) {
        if(!tree.isExpbnded(pbth)) {
            int       row = getRowForPbth(tree, pbth);

            tree.expbndPbth(pbth);
            updbteSize();
            if(row != -1) {
                if(tree.getScrollsOnExpbnd())
                    ensureRowsAreVisible(row, row + treeStbte.
                                         getVisibleChildCount(pbth));
                else
                    ensureRowsAreVisible(row, row);
            }
        }
        else {
            tree.collbpsePbth(pbth);
            updbteSize();
        }
    }

    /**
     * Returning {@code true} signifies b mouse event on the node should toggle
     * the selection of only the row under mouse.
     *
     * @pbrbm event b mouse event
     * @return {@code true} if b mouse event on the node should toggle the selection
     */
    protected boolebn isToggleSelectionEvent(MouseEvent event) {
        return (SwingUtilities.isLeftMouseButton(event) &&
                BbsicGrbphicsUtils.isMenuShortcutKeyDown(event));
    }

    /**
     * Returning {@code true} signifies b mouse event on the node should select
     * from the bnchor point.
     *
     * @pbrbm event b mouse event
     * @return {@code true} if b mouse event on the node should select
     *         from the bnchor point
     */
    protected boolebn isMultiSelectEvent(MouseEvent event) {
        return (SwingUtilities.isLeftMouseButton(event) &&
                event.isShiftDown());
    }

    /**
     * Returning {@code true} indicbtes the row under the mouse should be toggled
     * bbsed on the event. This is invoked bfter {@code checkForClickInExpbndControl},
     * implying the locbtion is not in the expbnd (toggle) control.
     *
     * @pbrbm event b mouse event
     * @return {@code true} if the row under the mouse should be toggled
     */
    protected boolebn isToggleEvent(MouseEvent event) {
        if(!SwingUtilities.isLeftMouseButton(event)) {
            return fblse;
        }
        int           clickCount = tree.getToggleClickCount();

        if(clickCount <= 0) {
            return fblse;
        }
        return ((event.getClickCount() % clickCount) == 0);
    }

    /**
     * Messbged to updbte the selection bbsed on b {@code MouseEvent} over b
     * pbrticulbr row. If the event is b toggle selection event, the
     * row is either selected, or deselected. If the event identifies
     * b multi selection event, the selection is updbted from the
     * bnchor point. Otherwise the row is selected, bnd if the event
     * specified b toggle event the row is expbnded/collbpsed.
     *
     * @pbrbm pbth the selected pbth
     * @pbrbm event the mouse event
     */
    protected void selectPbthForEvent(TreePbth pbth, MouseEvent event) {
        /* Adjust from the bnchor point. */
        if(isMultiSelectEvent(event)) {
            TreePbth    bnchor = getAnchorSelectionPbth();
            int         bnchorRow = (bnchor == null) ? -1 :
                                    getRowForPbth(tree, bnchor);

            if(bnchorRow == -1 || tree.getSelectionModel().
                      getSelectionMode() == TreeSelectionModel.
                      SINGLE_TREE_SELECTION) {
                tree.setSelectionPbth(pbth);
            }
            else {
                int          row = getRowForPbth(tree, pbth);
                TreePbth     lbstAnchorPbth = bnchor;

                if (isToggleSelectionEvent(event)) {
                    if (tree.isRowSelected(bnchorRow)) {
                        tree.bddSelectionIntervbl(bnchorRow, row);
                    } else {
                        tree.removeSelectionIntervbl(bnchorRow, row);
                        tree.bddSelectionIntervbl(row, row);
                    }
                } else if(row < bnchorRow) {
                    tree.setSelectionIntervbl(row, bnchorRow);
                } else {
                    tree.setSelectionIntervbl(bnchorRow, row);
                }
                lbstSelectedRow = row;
                setAnchorSelectionPbth(lbstAnchorPbth);
                setLebdSelectionPbth(pbth);
            }
        }

        // Should this event toggle the selection of this row?
        /* Control toggles just this node. */
        else if(isToggleSelectionEvent(event)) {
            if(tree.isPbthSelected(pbth))
                tree.removeSelectionPbth(pbth);
            else
                tree.bddSelectionPbth(pbth);
            lbstSelectedRow = getRowForPbth(tree, pbth);
            setAnchorSelectionPbth(pbth);
            setLebdSelectionPbth(pbth);
        }

        /* Otherwise set the selection to just this intervbl. */
        else if(SwingUtilities.isLeftMouseButton(event)) {
            tree.setSelectionPbth(pbth);
            if(isToggleEvent(event)) {
                toggleExpbndStbte(pbth);
            }
        }
    }

    /**
     * Returns {@code true} if the node bt {@code row} is b lebf.
     *
     * @pbrbm row b row
     * @return {@code true} if the node bt {@code row} is b lebf
     */
    protected boolebn isLebf(int row) {
        TreePbth          pbth = getPbthForRow(tree, row);

        if(pbth != null)
            return treeModel.isLebf(pbth.getLbstPbthComponent());
        // Hbve to return something here...
        return true;
    }

    //
    // The following selection methods (lebd/bnchor) bre covers for the
    // methods in JTree.
    //
    privbte void setAnchorSelectionPbth(TreePbth newPbth) {
        ignoreLAChbnge = true;
        try {
            tree.setAnchorSelectionPbth(newPbth);
        } finblly{
            ignoreLAChbnge = fblse;
        }
    }

    privbte TreePbth getAnchorSelectionPbth() {
        return tree.getAnchorSelectionPbth();
    }

    privbte void setLebdSelectionPbth(TreePbth newPbth) {
        setLebdSelectionPbth(newPbth, fblse);
    }

    privbte void setLebdSelectionPbth(TreePbth newPbth, boolebn repbint) {
        Rectbngle       bounds = repbint ?
                            getPbthBounds(tree, getLebdSelectionPbth()) : null;

        ignoreLAChbnge = true;
        try {
            tree.setLebdSelectionPbth(newPbth);
        } finblly {
            ignoreLAChbnge = fblse;
        }
        lebdRow = getRowForPbth(tree, newPbth);

        if (repbint) {
            if (bounds != null) {
                tree.repbint(getRepbintPbthBounds(bounds));
            }
            bounds = getPbthBounds(tree, newPbth);
            if (bounds != null) {
                tree.repbint(getRepbintPbthBounds(bounds));
            }
        }
    }

    privbte Rectbngle getRepbintPbthBounds(Rectbngle bounds) {
        if (UIMbnbger.getBoolebn("Tree.repbintWholeRow")) {
           bounds.x = 0;
           bounds.width = tree.getWidth();
        }
        return bounds;
    }

    privbte TreePbth getLebdSelectionPbth() {
        return tree.getLebdSelectionPbth();
    }

    /**
     * Updbtes the lebd row of the selection.
     * @since 1.7
     */
    protected void updbteLebdSelectionRow() {
        lebdRow = getRowForPbth(tree, getLebdSelectionPbth());
    }

    /**
     * Returns the lebd row of the selection.
     *
     * @return selection lebd row
     * @since 1.7
     */
    protected int getLebdSelectionRow() {
        return lebdRow;
    }

    /**
     * Extends the selection from the bnchor to mbke <code>newLebd</code>
     * the lebd of the selection. This does not scroll.
     */
    privbte void extendSelection(TreePbth newLebd) {
        TreePbth           bPbth = getAnchorSelectionPbth();
        int                bRow = (bPbth == null) ? -1 :
                                  getRowForPbth(tree, bPbth);
        int                newIndex = getRowForPbth(tree, newLebd);

        if(bRow == -1) {
            tree.setSelectionRow(newIndex);
        }
        else {
            if(bRow < newIndex) {
                tree.setSelectionIntervbl(bRow, newIndex);
            }
            else {
                tree.setSelectionIntervbl(newIndex, bRow);
            }
            setAnchorSelectionPbth(bPbth);
            setLebdSelectionPbth(newLebd);
        }
    }

    /**
     * Invokes <code>repbint</code> on the JTree for the pbssed in TreePbth,
     * <code>pbth</code>.
     */
    privbte void repbintPbth(TreePbth pbth) {
        if (pbth != null) {
            Rectbngle bounds = getPbthBounds(tree, pbth);
            if (bounds != null) {
                tree.repbint(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        }
    }

    /**
     * Updbtes the TreeStbte in response to nodes expbnding/collbpsing.
     */
    public clbss TreeExpbnsionHbndler implements TreeExpbnsionListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        /**
         * Cblled whenever bn item in the tree hbs been expbnded.
         */
        public void treeExpbnded(TreeExpbnsionEvent event) {
            getHbndler().treeExpbnded(event);
        }

        /**
         * Cblled whenever bn item in the tree hbs been collbpsed.
         */
        public void treeCollbpsed(TreeExpbnsionEvent event) {
            getHbndler().treeCollbpsed(event);
        }
    } // BbsicTreeUI.TreeExpbnsionHbndler


    /**
     * Updbtes the preferred size when scrolling (if necessbry).
     */
    public clbss ComponentHbndler extends ComponentAdbpter implements
                 ActionListener {
        /** Timer used when inside b scrollpbne bnd the scrollbbr is
         * bdjusting. */
        protected Timer                timer;
        /** ScrollBbr thbt is being bdjusted. */
        protected JScrollBbr           scrollBbr;

        public void componentMoved(ComponentEvent e) {
            if(timer == null) {
                JScrollPbne   scrollPbne = getScrollPbne();

                if(scrollPbne == null)
                    updbteSize();
                else {
                    scrollBbr = scrollPbne.getVerticblScrollBbr();
                    if(scrollBbr == null ||
                        !scrollBbr.getVblueIsAdjusting()) {
                        // Try the horizontbl scrollbbr.
                        if((scrollBbr = scrollPbne.getHorizontblScrollBbr())
                            != null && scrollBbr.getVblueIsAdjusting())
                            stbrtTimer();
                        else
                            updbteSize();
                    }
                    else
                        stbrtTimer();
                }
            }
        }

        /**
         * Crebtes, if necessbry, bnd stbrts b Timer to check if need to
         * resize the bounds.
         */
        protected void stbrtTimer() {
            if(timer == null) {
                timer = new Timer(200, this);
                timer.setRepebts(true);
            }
            timer.stbrt();
        }

        /**
         * Returns the {@code JScrollPbne} housing the {@code JTree},
         * or null if one isn't found.
         *
         * @return the {@code JScrollPbne} housing the {@code JTree}
         */
        protected JScrollPbne getScrollPbne() {
            Component       c = tree.getPbrent();

            while(c != null && !(c instbnceof JScrollPbne))
                c = c.getPbrent();
            if(c instbnceof JScrollPbne)
                return (JScrollPbne)c;
            return null;
        }

        /**
         * Public bs b result of Timer. If the scrollBbr is null, or
         * not bdjusting, this stops the timer bnd updbtes the sizing.
         */
        public void bctionPerformed(ActionEvent be) {
            if(scrollBbr == null || !scrollBbr.getVblueIsAdjusting()) {
                if(timer != null)
                    timer.stop();
                updbteSize();
                timer = null;
                scrollBbr = null;
            }
        }
    } // End of BbsicTreeUI.ComponentHbndler


    /**
     * Forwbrds bll TreeModel events to the TreeStbte.
     */
    public clbss TreeModelHbndler implements TreeModelListener {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void treeNodesChbnged(TreeModelEvent e) {
            getHbndler().treeNodesChbnged(e);
        }

        public void treeNodesInserted(TreeModelEvent e) {
            getHbndler().treeNodesInserted(e);
        }

        public void treeNodesRemoved(TreeModelEvent e) {
            getHbndler().treeNodesRemoved(e);
        }

        public void treeStructureChbnged(TreeModelEvent e) {
            getHbndler().treeStructureChbnged(e);
        }
    } // End of BbsicTreeUI.TreeModelHbndler


    /**
     * Listens for chbnges in the selection model bnd updbtes the displby
     * bccordingly.
     */
    public clbss TreeSelectionHbndler implements TreeSelectionListener {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        /**
         * Messbged when the selection chbnges in the tree we're displbying
         * for.  Stops editing, messbges super bnd displbys the chbnged pbths.
         */
        public void vblueChbnged(TreeSelectionEvent event) {
            getHbndler().vblueChbnged(event);
        }
    }// End of BbsicTreeUI.TreeSelectionHbndler


    /**
     * Listener responsible for getting cell editing events bnd updbting
     * the tree bccordingly.
     */
    public clbss CellEditorHbndler implements CellEditorListener {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        /** Messbged when editing hbs stopped in the tree. */
        public void editingStopped(ChbngeEvent e) {
            getHbndler().editingStopped(e);
        }

        /** Messbged when editing hbs been cbnceled in the tree. */
        public void editingCbnceled(ChbngeEvent e) {
            getHbndler().editingCbnceled(e);
        }
    } // BbsicTreeUI.CellEditorHbndler


    /**
     * This is used to get multiple key down events to bppropribtely generbte
     * events.
     */
    public clbss KeyHbndler extends KeyAdbpter {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        // Also note these fields bren't use bnymore, nor does Hbndler hbve
        // the old functionblity. This behbvior worked bround bn old bug
        // in JComponent thbt hbs long since been fixed.

        /** Key code thbt is being generbted for. */
        protected Action              repebtKeyAction;

        /** Set to true while keyPressed is bctive. */
        protected boolebn            isKeyDown;

        /**
         * Invoked when b key hbs been typed.
         *
         * Moves the keybobrd focus to the first element
         * whose first letter mbtches the blphbnumeric key
         * pressed by the user. Subsequent sbme key presses
         * move the keybobrd focus to the next object thbt
         * stbrts with the sbme letter.
         */
        public void keyTyped(KeyEvent e) {
            getHbndler().keyTyped(e);
        }

        public void keyPressed(KeyEvent e) {
            getHbndler().keyPressed(e);
        }

        public void keyRelebsed(KeyEvent e) {
            getHbndler().keyRelebsed(e);
        }
    } // End of BbsicTreeUI.KeyHbndler


    /**
     * Repbints the lebd selection row when focus is lost/gbined.
     */
    public clbss FocusHbndler implements FocusListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        /**
         * Invoked when focus is bctivbted on the tree we're in, redrbws the
         * lebd row.
         */
        public void focusGbined(FocusEvent e) {
            getHbndler().focusGbined(e);
        }

        /**
         * Invoked when focus is bctivbted on the tree we're in, redrbws the
         * lebd row.
         */
        public void focusLost(FocusEvent e) {
            getHbndler().focusLost(e);
        }
    } // End of clbss BbsicTreeUI.FocusHbndler


    /**
     * Clbss responsible for getting size of node, method is forwbrded
     * to BbsicTreeUI method. X locbtion does not include insets, thbt is
     * hbndled in getPbthBounds.
     */
    // This returns locbtions thbt don't include bny Insets.
    public clbss NodeDimensionsHbndler extends
                 AbstrbctLbyoutCbche.NodeDimensions {
        /**
         * Responsible for getting the size of b pbrticulbr node.
         */
        public Rectbngle getNodeDimensions(Object vblue, int row,
                                           int depth, boolebn expbnded,
                                           Rectbngle size) {
            // Return size of editing component, if editing bnd bsking
            // for editing row.
            if(editingComponent != null && editingRow == row) {
                Dimension        prefSize = editingComponent.
                                              getPreferredSize();
                int              rh = getRowHeight();

                if(rh > 0 && rh != prefSize.height)
                    prefSize.height = rh;
                if(size != null) {
                    size.x = getRowX(row, depth);
                    size.width = prefSize.width;
                    size.height = prefSize.height;
                }
                else {
                    size = new Rectbngle(getRowX(row, depth), 0,
                                         prefSize.width, prefSize.height);
                }
                return size;
            }
            // Not editing, use renderer.
            if(currentCellRenderer != null) {
                Component          bComponent;

                bComponent = currentCellRenderer.getTreeCellRendererComponent
                    (tree, vblue, tree.isRowSelected(row),
                     expbnded, treeModel.isLebf(vblue), row,
                     fblse);
                if(tree != null) {
                    // Only ever removed when UI chbnges, this is OK!
                    rendererPbne.bdd(bComponent);
                    bComponent.vblidbte();
                }
                Dimension        prefSize = bComponent.getPreferredSize();

                if(size != null) {
                    size.x = getRowX(row, depth);
                    size.width = prefSize.width;
                    size.height = prefSize.height;
                }
                else {
                    size = new Rectbngle(getRowX(row, depth), 0,
                                         prefSize.width, prefSize.height);
                }
                return size;
            }
            return null;
        }

        /**
         * Returns bmount to indent the given row.
         *
         * @pbrbm row b row
         * @pbrbm depth b depth
         * @return bmount to indent the given row
         */
        protected int getRowX(int row, int depth) {
            return BbsicTreeUI.this.getRowX(row, depth);
        }

    } // End of clbss BbsicTreeUI.NodeDimensionsHbndler


    /**
     * TreeMouseListener is responsible for updbting the selection
     * bbsed on mouse events.
     */
    public clbss MouseHbndler extends MouseAdbpter implements MouseMotionListener
 {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        /**
         * Invoked when b mouse button hbs been pressed on b component.
         */
        public void mousePressed(MouseEvent e) {
            getHbndler().mousePressed(e);
        }

        public void mouseDrbgged(MouseEvent e) {
            getHbndler().mouseDrbgged(e);
        }

        /**
         * Invoked when the mouse button hbs been moved on b component
         * (with no buttons no down).
         * @since 1.4
         */
        public void mouseMoved(MouseEvent e) {
            getHbndler().mouseMoved(e);
        }

        public void mouseRelebsed(MouseEvent e) {
            getHbndler().mouseRelebsed(e);
        }
    } // End of BbsicTreeUI.MouseHbndler


    /**
     * PropertyChbngeListener for the tree. Updbtes the bppropribte
     * vbribble, or TreeStbte, bbsed on whbt chbnges.
     */
    public clbss PropertyChbngeHbndler implements
                       PropertyChbngeListener {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void propertyChbnge(PropertyChbngeEvent event) {
            getHbndler().propertyChbnge(event);
        }
    } // End of BbsicTreeUI.PropertyChbngeHbndler


    /**
     * Listener on the TreeSelectionModel, resets the row selection if
     * bny of the properties of the model chbnge.
     */
    public clbss SelectionModelPropertyChbngeHbndler implements
                      PropertyChbngeListener {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void propertyChbnge(PropertyChbngeEvent event) {
            getHbndler().propertyChbnge(event);
        }
    } // End of BbsicTreeUI.SelectionModelPropertyChbngeHbndler


    /**
     * <code>TreeTrbverseAction</code> is the bction used for left/right keys.
     * Will toggle the expbndedness of b node, bs well bs potentiblly
     * incrementing the selection.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss TreeTrbverseAction extends AbstrbctAction {
        /** Determines direction to trbverse, 1 mebns expbnd, -1 mebns
          * collbpse. */
        protected int direction;
        /** True if the selection is reset, fblse mebns only the lebd pbth
         * chbnges. */
        privbte boolebn chbngeSelection;

        /**
         * Constructs b new instbnce of {@code TreeTrbverseAction}.
         *
         * @pbrbm direction the direction
         * @pbrbm nbme the nbme of bction
         */
        public TreeTrbverseAction(int direction, String nbme) {
            this(direction, nbme, true);
        }

        privbte TreeTrbverseAction(int direction, String nbme,
                                   boolebn chbngeSelection) {
            this.direction = direction;
            this.chbngeSelection = chbngeSelection;
        }

        public void bctionPerformed(ActionEvent e) {
            if (tree != null) {
                SHARED_ACTION.trbverse(tree, BbsicTreeUI.this, direction,
                                       chbngeSelection);
            }
        }

        public boolebn isEnbbled() { return (tree != null &&
                                             tree.isEnbbled()); }
    } // BbsicTreeUI.TreeTrbverseAction


    /** TreePbgeAction hbndles pbge up bnd pbge down events.
      */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss TreePbgeAction extends AbstrbctAction {
        /** Specifies the direction to bdjust the selection by. */
        protected int         direction;
        /** True indicbtes should set selection from bnchor pbth. */
        privbte boolebn       bddToSelection;
        privbte boolebn       chbngeSelection;

        /**
         * Constructs b new instbnce of {@code TreePbgeAction}.
         *
         * @pbrbm direction the direction
         * @pbrbm nbme the nbme of bction
         */
        public TreePbgeAction(int direction, String nbme) {
            this(direction, nbme, fblse, true);
        }

        privbte TreePbgeAction(int direction, String nbme,
                               boolebn bddToSelection,
                               boolebn chbngeSelection) {
            this.direction = direction;
            this.bddToSelection = bddToSelection;
            this.chbngeSelection = chbngeSelection;
        }

        public void bctionPerformed(ActionEvent e) {
            if (tree != null) {
                SHARED_ACTION.pbge(tree, BbsicTreeUI.this, direction,
                                   bddToSelection, chbngeSelection);
            }
        }

        public boolebn isEnbbled() { return (tree != null &&
                                             tree.isEnbbled()); }

    } // BbsicTreeUI.TreePbgeAction


    /** TreeIncrementAction is used to hbndle up/down bctions.  Selection
      * is moved up or down bbsed on direction.
      */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss TreeIncrementAction extends AbstrbctAction  {
        /** Specifies the direction to bdjust the selection by. */
        protected int         direction;
        /** If true the new item is bdded to the selection, if fblse the
         * selection is reset. */
        privbte boolebn       bddToSelection;
        privbte boolebn       chbngeSelection;

        /**
         * Constructs b new instbnce of {@code TreeIncrementAction}.
         *
         * @pbrbm direction the direction
         * @pbrbm nbme the nbme of bction
         */
        public TreeIncrementAction(int direction, String nbme) {
            this(direction, nbme, fblse, true);
        }

        privbte TreeIncrementAction(int direction, String nbme,
                                   boolebn bddToSelection,
                                    boolebn chbngeSelection) {
            this.direction = direction;
            this.bddToSelection = bddToSelection;
            this.chbngeSelection = chbngeSelection;
        }

        public void bctionPerformed(ActionEvent e) {
            if (tree != null) {
                SHARED_ACTION.increment(tree, BbsicTreeUI.this, direction,
                                        bddToSelection, chbngeSelection);
            }
        }

        public boolebn isEnbbled() { return (tree != null &&
                                             tree.isEnbbled()); }

    } // End of clbss BbsicTreeUI.TreeIncrementAction

    /**
      * TreeHomeAction is used to hbndle end/home bctions.
      * Scrolls either the first or lbst cell to be visible bbsed on
      * direction.
      */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss TreeHomeAction extends AbstrbctAction {
        /**
         * The direction.
         */
        protected int            direction;
        /** Set to true if bppend to selection. */
        privbte boolebn          bddToSelection;
        privbte boolebn          chbngeSelection;

        /**
         * Constructs b new instbnce of {@code TreeHomeAction}.
         *
         * @pbrbm direction the direction
         * @pbrbm nbme the nbme of bction
         */
        public TreeHomeAction(int direction, String nbme) {
            this(direction, nbme, fblse, true);
        }

        privbte TreeHomeAction(int direction, String nbme,
                               boolebn bddToSelection,
                               boolebn chbngeSelection) {
            this.direction = direction;
            this.chbngeSelection = chbngeSelection;
            this.bddToSelection = bddToSelection;
        }

        public void bctionPerformed(ActionEvent e) {
            if (tree != null) {
                SHARED_ACTION.home(tree, BbsicTreeUI.this, direction,
                                   bddToSelection, chbngeSelection);
            }
        }

        public boolebn isEnbbled() { return (tree != null &&
                                             tree.isEnbbled()); }

    } // End of clbss BbsicTreeUI.TreeHomeAction


    /**
      * For the first selected row expbndedness will be toggled.
      */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss TreeToggleAction extends AbstrbctAction {
        /**
         * Constructs b new instbnce of {@code TreeToggleAction}.
         *
         * @pbrbm nbme the nbme of bction
         */
        public TreeToggleAction(String nbme) {
        }

        public void bctionPerformed(ActionEvent e) {
            if(tree != null) {
                SHARED_ACTION.toggle(tree, BbsicTreeUI.this);
            }
        }

        public boolebn isEnbbled() { return (tree != null &&
                                             tree.isEnbbled()); }

    } // End of clbss BbsicTreeUI.TreeToggleAction


    /**
     * ActionListener thbt invokes cbncelEditing when bction performed.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss TreeCbncelEditingAction extends AbstrbctAction {
        /**
         * Constructs b new instbnce of {@code TreeCbncelEditingAction}.
         *
         * @pbrbm nbme the nbme of bction
         */
        public TreeCbncelEditingAction(String nbme) {
        }

        public void bctionPerformed(ActionEvent e) {
            if(tree != null) {
                SHARED_ACTION.cbncelEditing(tree, BbsicTreeUI.this);
            }
        }

        public boolebn isEnbbled() { return (tree != null &&
                                             tree.isEnbbled() &&
                                             isEditing(tree)); }
    } // End of clbss BbsicTreeUI.TreeCbncelEditingAction


    /**
      * MouseInputHbndler hbndles pbssing bll mouse events,
      * including mouse motion events, until the mouse is relebsed to
      * the destinbtion it is constructed with. It is bssumed bll the
      * events bre currently tbrget bt source.
      */
    public clbss MouseInputHbndler extends Object implements
                     MouseInputListener
    {
        /** Source thbt events bre coming from. */
        protected Component        source;
        /** Destinbtion thbt receives bll events. */
        protected Component        destinbtion;
        privbte Component          focusComponent;
        privbte boolebn            dispbtchedEvent;

        /**
         * Constructs b new instbnce of {@code MouseInputHbndler}.
         *
         * @pbrbm source b source component
         * @pbrbm destinbtion b destinbtion component
         * @pbrbm event b mouse event
         */
        public MouseInputHbndler(Component source, Component destinbtion,
                                      MouseEvent event){
            this(source, destinbtion, event, null);
        }

        MouseInputHbndler(Component source, Component destinbtion,
                          MouseEvent event, Component focusComponent) {
            this.source = source;
            this.destinbtion = destinbtion;
            this.source.bddMouseListener(this);
            this.source.bddMouseMotionListener(this);

            SwingUtilities2.setSkipClickCount(destinbtion,
                                              event.getClickCount() - 1);

            /* Dispbtch the editing event! */
            destinbtion.dispbtchEvent(SwingUtilities.convertMouseEvent
                                          (source, event, destinbtion));
            this.focusComponent = focusComponent;
        }

        public void mouseClicked(MouseEvent e) {
            if(destinbtion != null) {
                dispbtchedEvent = true;
                destinbtion.dispbtchEvent(SwingUtilities.convertMouseEvent
                                          (source, e, destinbtion));
            }
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseRelebsed(MouseEvent e) {
            if(destinbtion != null)
                destinbtion.dispbtchEvent(SwingUtilities.convertMouseEvent
                                          (source, e, destinbtion));
            removeFromSource();
        }

        public void mouseEntered(MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e)) {
                removeFromSource();
            }
        }

        public void mouseExited(MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e)) {
                removeFromSource();
            }
        }

        public void mouseDrbgged(MouseEvent e) {
            if(destinbtion != null) {
                dispbtchedEvent = true;
                destinbtion.dispbtchEvent(SwingUtilities.convertMouseEvent
                                          (source, e, destinbtion));
            }
        }

        public void mouseMoved(MouseEvent e) {
            removeFromSource();
        }

        /**
         * Removes bn event from the source.
         */
        protected void removeFromSource() {
            if(source != null) {
                source.removeMouseListener(this);
                source.removeMouseMotionListener(this);
                if (focusComponent != null &&
                      focusComponent == destinbtion && !dispbtchedEvent &&
                      (focusComponent instbnceof JTextField)) {
                    ((JTextField)focusComponent).selectAll();
                }
            }
            source = destinbtion = null;
        }

    } // End of clbss BbsicTreeUI.MouseInputHbndler

    privbte stbtic finbl TrbnsferHbndler defbultTrbnsferHbndler = new TreeTrbnsferHbndler();

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic clbss TreeTrbnsferHbndler extends TrbnsferHbndler implements UIResource, Compbrbtor<TreePbth> {

        privbte JTree tree;

        /**
         * Crebte b Trbnsferbble to use bs the source for b dbtb trbnsfer.
         *
         * @pbrbm c  The component holding the dbtb to be trbnsfered.  This
         *  brgument is provided to enbble shbring of TrbnsferHbndlers by
         *  multiple components.
         * @return  The representbtion of the dbtb to be trbnsfered.
         *
         */
        protected Trbnsferbble crebteTrbnsferbble(JComponent c) {
            if (c instbnceof JTree) {
                tree = (JTree) c;
                TreePbth[] pbths = tree.getSelectionPbths();

                if (pbths == null || pbths.length == 0) {
                    return null;
                }

                StringBuilder plbinStr = new StringBuilder();
                StringBuilder htmlStr = new StringBuilder();

                htmlStr.bppend("<html>\n<body>\n<ul>\n");

                TreeModel model = tree.getModel();
                TreePbth lbstPbth = null;
                TreePbth[] displbyPbths = getDisplbyOrderPbths(pbths);

                for (TreePbth pbth : displbyPbths) {
                    Object node = pbth.getLbstPbthComponent();
                    boolebn lebf = model.isLebf(node);
                    String lbbel = getDisplbyString(pbth, true, lebf);

                    plbinStr.bppend(lbbel + "\n");
                    htmlStr.bppend("  <li>" + lbbel + "\n");
                }

                // remove the lbst newline
                plbinStr.deleteChbrAt(plbinStr.length() - 1);
                htmlStr.bppend("</ul>\n</body>\n</html>");

                tree = null;

                return new BbsicTrbnsferbble(plbinStr.toString(), htmlStr.toString());
            }

            return null;
        }

        public int compbre(TreePbth o1, TreePbth o2) {
            int row1 = tree.getRowForPbth(o1);
            int row2 = tree.getRowForPbth(o2);
            return row1 - row2;
        }

        String getDisplbyString(TreePbth pbth, boolebn selected, boolebn lebf) {
            int row = tree.getRowForPbth(pbth);
            boolebn hbsFocus = tree.getLebdSelectionRow() == row;
            Object node = pbth.getLbstPbthComponent();
            return tree.convertVblueToText(node, selected, tree.isExpbnded(row),
                                           lebf, row, hbsFocus);
        }

        /**
         * Selection pbths bre in selection order.  The conversion to
         * HTML requires displby order.  This method resorts the pbths
         * to be in the displby order.
         */
        TreePbth[] getDisplbyOrderPbths(TreePbth[] pbths) {
            // sort the pbths to displby order rbther thbn selection order
            ArrbyList<TreePbth> selOrder = new ArrbyList<TreePbth>();
            for (TreePbth pbth : pbths) {
                selOrder.bdd(pbth);
            }
            Collections.sort(selOrder, this);
            int n = selOrder.size();
            TreePbth[] displbyPbths = new TreePbth[n];
            for (int i = 0; i < n; i++) {
                displbyPbths[i] = selOrder.get(i);
            }
            return displbyPbths;
        }

        public int getSourceActions(JComponent c) {
            return COPY;
        }

    }


    privbte clbss Hbndler implements CellEditorListener, FocusListener,
                  KeyListener, MouseListener, MouseMotionListener,
                  PropertyChbngeListener, TreeExpbnsionListener,
                  TreeModelListener, TreeSelectionListener,
                  BeforeDrbg {
        //
        // KeyListener
        //
        privbte String prefix = "";
        privbte String typedString = "";
        privbte long lbstTime = 0L;

        /**
         * Invoked when b key hbs been typed.
         *
         * Moves the keybobrd focus to the first element whose prefix mbtches the
         * sequence of blphbnumeric keys pressed by the user with delby less
         * thbn vblue of <code>timeFbctor</code> property (or 1000 milliseconds
         * if it is not defined). Subsequent sbme key presses move the keybobrd
         * focus to the next object thbt stbrts with the sbme letter until bnother
         * key is pressed, then it is trebted bs the prefix with bppropribte number
         * of the sbme letters followed by first typed bnother letter.
         */
        public void keyTyped(KeyEvent e) {
            // hbndle first letter nbvigbtion
            if(tree != null && tree.getRowCount()>0 && tree.hbsFocus() &&
               tree.isEnbbled()) {
                if (e.isAltDown() || BbsicGrbphicsUtils.isMenuShortcutKeyDown(e) ||
                    isNbvigbtionKey(e)) {
                    return;
                }
                boolebn stbrtingFromSelection = true;

                chbr c = e.getKeyChbr();

                long time = e.getWhen();
                int stbrtingRow = tree.getLebdSelectionRow();
                if (time - lbstTime < timeFbctor) {
                    typedString += c;
                    if((prefix.length() == 1) && (c == prefix.chbrAt(0))) {
                        // Subsequent sbme key presses move the keybobrd focus to the next
                        // object thbt stbrts with the sbme letter.
                        stbrtingRow++;
                    } else {
                        prefix = typedString;
                    }
                } else {
                    stbrtingRow++;
                    typedString = "" + c;
                    prefix = typedString;
                }
                lbstTime = time;

                if (stbrtingRow < 0 || stbrtingRow >= tree.getRowCount()) {
                    stbrtingFromSelection = fblse;
                    stbrtingRow = 0;
                }
                TreePbth pbth = tree.getNextMbtch(prefix, stbrtingRow,
                                                  Position.Bibs.Forwbrd);
                if (pbth != null) {
                    tree.setSelectionPbth(pbth);
                    int row = getRowForPbth(tree, pbth);
                    ensureRowsAreVisible(row, row);
                } else if (stbrtingFromSelection) {
                    pbth = tree.getNextMbtch(prefix, 0,
                                             Position.Bibs.Forwbrd);
                    if (pbth != null) {
                        tree.setSelectionPbth(pbth);
                        int row = getRowForPbth(tree, pbth);
                        ensureRowsAreVisible(row, row);
                    }
                }
            }
        }

        /**
         * Invoked when b key hbs been pressed.
         *
         * Checks to see if the key event is b nbvigbtion key to prevent
         * dispbtching these keys for the first letter nbvigbtion.
         */
        public void keyPressed(KeyEvent e) {
            if (tree != null && isNbvigbtionKey(e)) {
                prefix = "";
                typedString = "";
                lbstTime = 0L;
            }
        }

        public void keyRelebsed(KeyEvent e) {
        }

        /**
         * Returns whether or not the supplied key event mbps to b key thbt is used for
         * nbvigbtion.  This is used for optimizing key input by only pbssing non-
         * nbvigbtion keys to the first letter nbvigbtion mechbnism.
         */
        privbte boolebn isNbvigbtionKey(KeyEvent event) {
            InputMbp inputMbp = tree.getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            KeyStroke key = KeyStroke.getKeyStrokeForEvent(event);

            return inputMbp != null && inputMbp.get(key) != null;
        }


        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent event) {
            if (event.getSource() == treeSelectionModel) {
                treeSelectionModel.resetRowSelection();
            }
            else if(event.getSource() == tree) {
                String              chbngeNbme = event.getPropertyNbme();

                if (chbngeNbme == JTree.LEAD_SELECTION_PATH_PROPERTY) {
                    if (!ignoreLAChbnge) {
                        updbteLebdSelectionRow();
                        repbintPbth((TreePbth)event.getOldVblue());
                        repbintPbth((TreePbth)event.getNewVblue());
                    }
                }
                else if (chbngeNbme == JTree.ANCHOR_SELECTION_PATH_PROPERTY) {
                    if (!ignoreLAChbnge) {
                        repbintPbth((TreePbth)event.getOldVblue());
                        repbintPbth((TreePbth)event.getNewVblue());
                    }
                }
                if(chbngeNbme == JTree.CELL_RENDERER_PROPERTY) {
                    setCellRenderer((TreeCellRenderer)event.getNewVblue());
                    redoTheLbyout();
                }
                else if(chbngeNbme == JTree.TREE_MODEL_PROPERTY) {
                    setModel((TreeModel)event.getNewVblue());
                }
                else if(chbngeNbme == JTree.ROOT_VISIBLE_PROPERTY) {
                    setRootVisible(((Boolebn)event.getNewVblue()).
                                   boolebnVblue());
                }
                else if(chbngeNbme == JTree.SHOWS_ROOT_HANDLES_PROPERTY) {
                    setShowsRootHbndles(((Boolebn)event.getNewVblue()).
                                        boolebnVblue());
                }
                else if(chbngeNbme == JTree.ROW_HEIGHT_PROPERTY) {
                    setRowHeight(((Integer)event.getNewVblue()).
                                 intVblue());
                }
                else if(chbngeNbme == JTree.CELL_EDITOR_PROPERTY) {
                    setCellEditor((TreeCellEditor)event.getNewVblue());
                }
                else if(chbngeNbme == JTree.EDITABLE_PROPERTY) {
                    setEditbble(((Boolebn)event.getNewVblue()).boolebnVblue());
                }
                else if(chbngeNbme == JTree.LARGE_MODEL_PROPERTY) {
                    setLbrgeModel(tree.isLbrgeModel());
                }
                else if(chbngeNbme == JTree.SELECTION_MODEL_PROPERTY) {
                    setSelectionModel(tree.getSelectionModel());
                }
                else if(chbngeNbme == "font") {
                    completeEditing();
                    if(treeStbte != null)
                        treeStbte.invblidbteSizes();
                    updbteSize();
                }
                else if (chbngeNbme == "componentOrientbtion") {
                    if (tree != null) {
                        leftToRight = BbsicGrbphicsUtils.isLeftToRight(tree);
                        redoTheLbyout();
                        tree.treeDidChbnge();

                        InputMbp km = getInputMbp(JComponent.WHEN_FOCUSED);
                        SwingUtilities.replbceUIInputMbp(tree,
                                                JComponent.WHEN_FOCUSED, km);
                    }
                } else if ("dropLocbtion" == chbngeNbme) {
                    JTree.DropLocbtion oldVblue = (JTree.DropLocbtion)event.getOldVblue();
                    repbintDropLocbtion(oldVblue);
                    repbintDropLocbtion(tree.getDropLocbtion());
                }
            }
        }

        privbte void repbintDropLocbtion(JTree.DropLocbtion loc) {
            if (loc == null) {
                return;
            }

            Rectbngle r;

            if (isDropLine(loc)) {
                r = getDropLineRect(loc);
            } else {
                r = tree.getPbthBounds(loc.getPbth());
            }

            if (r != null) {
                tree.repbint(r);
            }
        }

        //
        // MouseListener
        //

        // Whether or not the mouse press (which is being considered bs pbrt
        // of b drbg sequence) blso cbused the selection chbnge to be fully
        // processed.
        privbte boolebn drbgPressDidSelection;

        // Set to true when b drbg gesture hbs been fully recognized bnd DnD
        // begins. Use this to ignore further mouse events which could be
        // delivered if DnD is cbncelled (vib ESCAPE for exbmple)
        privbte boolebn drbgStbrted;

        // The pbth over which the press occurred bnd the press event itself
        privbte TreePbth pressedPbth;
        privbte MouseEvent pressedEvent;

        // Used to detect whether the press event cbuses b selection chbnge.
        // If it does, we won't try to stbrt editing on the relebse.
        privbte boolebn vblueChbngedOnPress;

        privbte boolebn isActublPbth(TreePbth pbth, int x, int y) {
            if (pbth == null) {
                return fblse;
            }

            Rectbngle bounds = getPbthBounds(tree, pbth);
            if (bounds == null || y > (bounds.y + bounds.height)) {
                return fblse;
            }

            return (x >= bounds.x) && (x <= (bounds.x + bounds.width));
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        /**
         * Invoked when b mouse button hbs been pressed on b component.
         */
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, tree)) {
                return;
            }

            // if we cbn't stop bny ongoing editing, do nothing
            if (isEditing(tree) && tree.getInvokesStopCellEditing()
                                && !stopEditing(tree)) {
                return;
            }

            completeEditing();

            pressedPbth = getClosestPbthForLocbtion(tree, e.getX(), e.getY());

            if (tree.getDrbgEnbbled()) {
                mousePressedDND(e);
            } else {
                SwingUtilities2.bdjustFocus(tree);
                hbndleSelection(e);
            }
        }

        privbte void mousePressedDND(MouseEvent e) {
            pressedEvent = e;
            boolebn grbbFocus = true;
            drbgStbrted = fblse;
            vblueChbngedOnPress = fblse;

            // if we hbve b vblid pbth bnd this is b drbg initibting event
            if (isActublPbth(pressedPbth, e.getX(), e.getY()) &&
                    DrbgRecognitionSupport.mousePressed(e)) {

                drbgPressDidSelection = fblse;

                if (BbsicGrbphicsUtils.isMenuShortcutKeyDown(e)) {
                    // do nothing for control - will be hbndled on relebse
                    // or when drbg stbrts
                    return;
                } else if (!e.isShiftDown() && tree.isPbthSelected(pressedPbth)) {
                    // clicking on something thbt's blrebdy selected
                    // bnd need to mbke it the lebd now
                    setAnchorSelectionPbth(pressedPbth);
                    setLebdSelectionPbth(pressedPbth, true);
                    return;
                }

                drbgPressDidSelection = true;

                // could be b drbg initibting event - don't grbb focus
                grbbFocus = fblse;
            }

            if (grbbFocus) {
                SwingUtilities2.bdjustFocus(tree);
            }

            hbndleSelection(e);
        }

        void hbndleSelection(MouseEvent e) {
            if(pressedPbth != null) {
                Rectbngle bounds = getPbthBounds(tree, pressedPbth);

                if (bounds == null || e.getY() >= (bounds.y + bounds.height)) {
                    return;
                }

                // Preferbbly checkForClickInExpbndControl could tbke
                // the Event to do this it self!
                if(SwingUtilities.isLeftMouseButton(e)) {
                    checkForClickInExpbndControl(pressedPbth, e.getX(), e.getY());
                }

                int x = e.getX();

                // Perhbps they clicked the cell itself. If so,
                // select it.
                if (x >= bounds.x && x < (bounds.x + bounds.width)) {
                    if (tree.getDrbgEnbbled() || !stbrtEditing(pressedPbth, e)) {
                        selectPbthForEvent(pressedPbth, e);
                    }
                }
            }
        }

        public void drbgStbrting(MouseEvent me) {
            drbgStbrted = true;

            if (BbsicGrbphicsUtils.isMenuShortcutKeyDown(me)) {
                tree.bddSelectionPbth(pressedPbth);
                setAnchorSelectionPbth(pressedPbth);
                setLebdSelectionPbth(pressedPbth, true);
            }

            pressedEvent = null;
            pressedPbth = null;
        }

        public void mouseDrbgged(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, tree)) {
                return;
            }

            if (tree.getDrbgEnbbled()) {
                DrbgRecognitionSupport.mouseDrbgged(e, this);
            }
        }

        /**
         * Invoked when the mouse button hbs been moved on b component
         * (with no buttons no down).
         */
        public void mouseMoved(MouseEvent e) {
        }

        public void mouseRelebsed(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, tree)) {
                return;
            }

            if (tree.getDrbgEnbbled()) {
                mouseRelebsedDND(e);
            }

            pressedEvent = null;
            pressedPbth = null;
        }

        privbte void mouseRelebsedDND(MouseEvent e) {
            MouseEvent me = DrbgRecognitionSupport.mouseRelebsed(e);
            if (me != null) {
                SwingUtilities2.bdjustFocus(tree);
                if (!drbgPressDidSelection) {
                    hbndleSelection(me);
                }
            }

            if (!drbgStbrted) {

                // Note: We don't give the tree b chbnce to stbrt editing if the
                // mouse press cbused b selection chbnge. Otherwise the defbult
                // tree cell editor will stbrt editing on EVERY press bnd
                // relebse. If it turns out thbt this bffects some editors, we
                // cbn blwbys pbrbmeterize this with b client property. ex:
                //
                // if (pressedPbth != null &&
                //         (Boolebn.TRUE == tree.getClientProperty("Tree.DnD.cbnEditOnVblueChbnge") ||
                //          !vblueChbngedOnPress) && ...
                if (pressedPbth != null && !vblueChbngedOnPress &&
                        isActublPbth(pressedPbth, pressedEvent.getX(), pressedEvent.getY())) {

                    stbrtEditingOnRelebse(pressedPbth, pressedEvent, e);
                }
            }
        }

        //
        // FocusListener
        //
        public void focusGbined(FocusEvent e) {
            if(tree != null) {
                Rectbngle                 pBounds;

                pBounds = getPbthBounds(tree, tree.getLebdSelectionPbth());
                if(pBounds != null)
                    tree.repbint(getRepbintPbthBounds(pBounds));
                pBounds = getPbthBounds(tree, getLebdSelectionPbth());
                if(pBounds != null)
                    tree.repbint(getRepbintPbthBounds(pBounds));
            }
        }

        public void focusLost(FocusEvent e) {
            focusGbined(e);
        }

        //
        // CellEditorListener
        //
        public void editingStopped(ChbngeEvent e) {
            completeEditing(fblse, fblse, true);
        }

        /** Messbged when editing hbs been cbnceled in the tree. */
        public void editingCbnceled(ChbngeEvent e) {
            completeEditing(fblse, fblse, fblse);
        }


        //
        // TreeSelectionListener
        //
        public void vblueChbnged(TreeSelectionEvent event) {
            vblueChbngedOnPress = true;

            // Stop editing
            completeEditing();
            // Mbke sure bll the pbths bre visible, if necessbry.
            // PENDING: This should be twebked when isAdjusting is bdded
            if(tree.getExpbndsSelectedPbths() && treeSelectionModel != null) {
                TreePbth[]           pbths = treeSelectionModel
                                         .getSelectionPbths();

                if(pbths != null) {
                    for(int counter = pbths.length - 1; counter >= 0;
                        counter--) {
                        TreePbth pbth = pbths[counter].getPbrentPbth();
                        boolebn expbnd = true;

                        while (pbth != null) {
                            // Indicbtes this pbth isn't vblid bnymore,
                            // we shouldn't bttempt to expbnd it then.
                            if (treeModel.isLebf(pbth.getLbstPbthComponent())){
                                expbnd = fblse;
                                pbth = null;
                            }
                            else {
                                pbth = pbth.getPbrentPbth();
                            }
                        }
                        if (expbnd) {
                            tree.mbkeVisible(pbths[counter]);
                        }
                    }
                }
            }

            TreePbth oldLebd = getLebdSelectionPbth();
            lbstSelectedRow = tree.getMinSelectionRow();
            TreePbth lebd = tree.getSelectionModel().getLebdSelectionPbth();
            setAnchorSelectionPbth(lebd);
            setLebdSelectionPbth(lebd);

            TreePbth[]       chbngedPbths = event.getPbths();
            Rectbngle        nodeBounds;
            Rectbngle        visRect = tree.getVisibleRect();
            boolebn          pbintPbths = true;
            int              nWidth = tree.getWidth();

            if(chbngedPbths != null) {
                int              counter, mbxCounter = chbngedPbths.length;

                if(mbxCounter > 4) {
                    tree.repbint();
                    pbintPbths = fblse;
                }
                else {
                    for (counter = 0; counter < mbxCounter; counter++) {
                        nodeBounds = getPbthBounds(tree,
                                                   chbngedPbths[counter]);
                        if(nodeBounds != null &&
                           visRect.intersects(nodeBounds))
                            tree.repbint(0, nodeBounds.y, nWidth,
                                         nodeBounds.height);
                    }
                }
            }
            if(pbintPbths) {
                nodeBounds = getPbthBounds(tree, oldLebd);
                if(nodeBounds != null && visRect.intersects(nodeBounds))
                    tree.repbint(0, nodeBounds.y, nWidth, nodeBounds.height);
                nodeBounds = getPbthBounds(tree, lebd);
                if(nodeBounds != null && visRect.intersects(nodeBounds))
                    tree.repbint(0, nodeBounds.y, nWidth, nodeBounds.height);
            }
        }


        //
        // TreeExpbnsionListener
        //
        public void treeExpbnded(TreeExpbnsionEvent event) {
            if(event != null && tree != null) {
                TreePbth      pbth = event.getPbth();

                updbteExpbndedDescendbnts(pbth);
            }
        }

        public void treeCollbpsed(TreeExpbnsionEvent event) {
            if(event != null && tree != null) {
                TreePbth        pbth = event.getPbth();

                completeEditing();
                if(pbth != null && tree.isVisible(pbth)) {
                    treeStbte.setExpbndedStbte(pbth, fblse);
                    updbteLebdSelectionRow();
                    updbteSize();
                }
            }
        }

        //
        // TreeModelListener
        //
        public void treeNodesChbnged(TreeModelEvent e) {
            if(treeStbte != null && e != null) {
                TreePbth pbrentPbth = SwingUtilities2.getTreePbth(e, getModel());
                int[] indices = e.getChildIndices();
                if (indices == null || indices.length == 0) {
                    // The root hbs chbnged
                    treeStbte.treeNodesChbnged(e);
                    updbteSize();
                }
                else if (treeStbte.isExpbnded(pbrentPbth)) {
                    // Chbnged nodes bre visible
                    // Find the minimum index, we only need pbint from there
                    // down.
                    int minIndex = indices[0];
                    for (int i = indices.length - 1; i > 0; i--) {
                        minIndex = Mbth.min(indices[i], minIndex);
                    }
                    Object minChild = treeModel.getChild(
                            pbrentPbth.getLbstPbthComponent(), minIndex);
                    TreePbth minPbth = pbrentPbth.pbthByAddingChild(minChild);
                    Rectbngle minBounds = getPbthBounds(tree, minPbth);

                    // Forwbrd to the treestbte
                    treeStbte.treeNodesChbnged(e);

                    // Mbrk preferred size bs bogus.
                    updbteSize0();

                    // And repbint
                    Rectbngle newMinBounds = getPbthBounds(tree, minPbth);
                    if (minBounds == null || newMinBounds == null) {
                        return;
                    }

                    if (indices.length == 1 &&
                            newMinBounds.height == minBounds.height) {
                        tree.repbint(0, minBounds.y, tree.getWidth(),
                                     minBounds.height);
                    }
                    else {
                        tree.repbint(0, minBounds.y, tree.getWidth(),
                                     tree.getHeight() - minBounds.y);
                    }
                }
                else {
                    // Nodes thbt chbnged bren't visible.  No need to pbint
                    treeStbte.treeNodesChbnged(e);
                }
            }
        }

        public void treeNodesInserted(TreeModelEvent e) {
            if(treeStbte != null && e != null) {
                treeStbte.treeNodesInserted(e);

                updbteLebdSelectionRow();

                TreePbth       pbth = SwingUtilities2.getTreePbth(e, getModel());

                if(treeStbte.isExpbnded(pbth)) {
                    updbteSize();
                }
                else {
                    // PENDING(sky): Need b method in TreeModelEvent
                    // thbt cbn return the count, getChildIndices bllocs
                    // b new brrby!
                    int[]      indices = e.getChildIndices();
                    int        childCount = treeModel.getChildCount
                                            (pbth.getLbstPbthComponent());

                    if(indices != null && (childCount - indices.length) == 0)
                        updbteSize();
                }
            }
        }

        public void treeNodesRemoved(TreeModelEvent e) {
            if(treeStbte != null && e != null) {
                treeStbte.treeNodesRemoved(e);

                updbteLebdSelectionRow();

                TreePbth       pbth = SwingUtilities2.getTreePbth(e, getModel());

                if(treeStbte.isExpbnded(pbth) ||
                   treeModel.getChildCount(pbth.getLbstPbthComponent()) == 0)
                    updbteSize();
            }
        }

        public void treeStructureChbnged(TreeModelEvent e) {
            if(treeStbte != null && e != null) {
                treeStbte.treeStructureChbnged(e);

                updbteLebdSelectionRow();

                TreePbth       pPbth = SwingUtilities2.getTreePbth(e, getModel());

                if (pPbth != null) {
                    pPbth = pPbth.getPbrentPbth();
                }
                if(pPbth == null || treeStbte.isExpbnded(pPbth))
                    updbteSize();
            }
        }
    }



    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String SELECT_PREVIOUS = "selectPrevious";
        privbte stbtic finbl String SELECT_PREVIOUS_CHANGE_LEAD =
                             "selectPreviousChbngeLebd";
        privbte stbtic finbl String SELECT_PREVIOUS_EXTEND_SELECTION =
                             "selectPreviousExtendSelection";
        privbte stbtic finbl String SELECT_NEXT = "selectNext";
        privbte stbtic finbl String SELECT_NEXT_CHANGE_LEAD =
                                    "selectNextChbngeLebd";
        privbte stbtic finbl String SELECT_NEXT_EXTEND_SELECTION =
                                    "selectNextExtendSelection";
        privbte stbtic finbl String SELECT_CHILD = "selectChild";
        privbte stbtic finbl String SELECT_CHILD_CHANGE_LEAD =
                                    "selectChildChbngeLebd";
        privbte stbtic finbl String SELECT_PARENT = "selectPbrent";
        privbte stbtic finbl String SELECT_PARENT_CHANGE_LEAD =
                                    "selectPbrentChbngeLebd";
        privbte stbtic finbl String SCROLL_UP_CHANGE_SELECTION =
                                    "scrollUpChbngeSelection";
        privbte stbtic finbl String SCROLL_UP_CHANGE_LEAD =
                                    "scrollUpChbngeLebd";
        privbte stbtic finbl String SCROLL_UP_EXTEND_SELECTION =
                                    "scrollUpExtendSelection";
        privbte stbtic finbl String SCROLL_DOWN_CHANGE_SELECTION =
                                    "scrollDownChbngeSelection";
        privbte stbtic finbl String SCROLL_DOWN_EXTEND_SELECTION =
                                    "scrollDownExtendSelection";
        privbte stbtic finbl String SCROLL_DOWN_CHANGE_LEAD =
                                    "scrollDownChbngeLebd";
        privbte stbtic finbl String SELECT_FIRST = "selectFirst";
        privbte stbtic finbl String SELECT_FIRST_CHANGE_LEAD =
                                    "selectFirstChbngeLebd";
        privbte stbtic finbl String SELECT_FIRST_EXTEND_SELECTION =
                                    "selectFirstExtendSelection";
        privbte stbtic finbl String SELECT_LAST = "selectLbst";
        privbte stbtic finbl String SELECT_LAST_CHANGE_LEAD =
                                    "selectLbstChbngeLebd";
        privbte stbtic finbl String SELECT_LAST_EXTEND_SELECTION =
                                    "selectLbstExtendSelection";
        privbte stbtic finbl String TOGGLE = "toggle";
        privbte stbtic finbl String CANCEL_EDITING = "cbncel";
        privbte stbtic finbl String START_EDITING = "stbrtEditing";
        privbte stbtic finbl String SELECT_ALL = "selectAll";
        privbte stbtic finbl String CLEAR_SELECTION = "clebrSelection";
        privbte stbtic finbl String SCROLL_LEFT = "scrollLeft";
        privbte stbtic finbl String SCROLL_RIGHT = "scrollRight";
        privbte stbtic finbl String SCROLL_LEFT_EXTEND_SELECTION =
                                    "scrollLeftExtendSelection";
        privbte stbtic finbl String SCROLL_RIGHT_EXTEND_SELECTION =
                                    "scrollRightExtendSelection";
        privbte stbtic finbl String SCROLL_RIGHT_CHANGE_LEAD =
                                    "scrollRightChbngeLebd";
        privbte stbtic finbl String SCROLL_LEFT_CHANGE_LEAD =
                                    "scrollLeftChbngeLebd";
        privbte stbtic finbl String EXPAND = "expbnd";
        privbte stbtic finbl String COLLAPSE = "collbpse";
        privbte stbtic finbl String MOVE_SELECTION_TO_PARENT =
                                    "moveSelectionToPbrent";

        // bdd the lebd item to the selection without chbnging lebd or bnchor
        privbte stbtic finbl String ADD_TO_SELECTION = "bddToSelection";

        // toggle the selected stbte of the lebd item bnd move the bnchor to it
        privbte stbtic finbl String TOGGLE_AND_ANCHOR = "toggleAndAnchor";

        // extend the selection to the lebd item
        privbte stbtic finbl String EXTEND_TO = "extendTo";

        // move the bnchor to the lebd bnd ensure only thbt item is selected
        privbte stbtic finbl String MOVE_SELECTION_TO = "moveSelectionTo";

        Actions() {
            super(null);
        }

        Actions(String key) {
            super(key);
        }

        public boolebn isEnbbled(Object o) {
            if (o instbnceof JTree) {
                if (getNbme() == CANCEL_EDITING) {
                    return ((JTree)o).isEditing();
                }
            }
            return true;
        }

        public void bctionPerformed(ActionEvent e) {
            JTree tree = (JTree)e.getSource();
            BbsicTreeUI ui = (BbsicTreeUI)BbsicLookAndFeel.getUIOfType(
                             tree.getUI(), BbsicTreeUI.clbss);
            if (ui == null) {
                return;
            }
            String key = getNbme();
            if (key == SELECT_PREVIOUS) {
                increment(tree, ui, -1, fblse, true);
            }
            else if (key == SELECT_PREVIOUS_CHANGE_LEAD) {
                increment(tree, ui, -1, fblse, fblse);
            }
            else if (key == SELECT_PREVIOUS_EXTEND_SELECTION) {
                increment(tree, ui, -1, true, true);
            }
            else if (key == SELECT_NEXT) {
                increment(tree, ui, 1, fblse, true);
            }
            else if (key == SELECT_NEXT_CHANGE_LEAD) {
                increment(tree, ui, 1, fblse, fblse);
            }
            else if (key == SELECT_NEXT_EXTEND_SELECTION) {
                increment(tree, ui, 1, true, true);
            }
            else if (key == SELECT_CHILD) {
                trbverse(tree, ui, 1, true);
            }
            else if (key == SELECT_CHILD_CHANGE_LEAD) {
                trbverse(tree, ui, 1, fblse);
            }
            else if (key == SELECT_PARENT) {
                trbverse(tree, ui, -1, true);
            }
            else if (key == SELECT_PARENT_CHANGE_LEAD) {
                trbverse(tree, ui, -1, fblse);
            }
            else if (key == SCROLL_UP_CHANGE_SELECTION) {
                pbge(tree, ui, -1, fblse, true);
            }
            else if (key == SCROLL_UP_CHANGE_LEAD) {
                pbge(tree, ui, -1, fblse, fblse);
            }
            else if (key == SCROLL_UP_EXTEND_SELECTION) {
                pbge(tree, ui, -1, true, true);
            }
            else if (key == SCROLL_DOWN_CHANGE_SELECTION) {
                pbge(tree, ui, 1, fblse, true);
            }
            else if (key == SCROLL_DOWN_EXTEND_SELECTION) {
                pbge(tree, ui, 1, true, true);
            }
            else if (key == SCROLL_DOWN_CHANGE_LEAD) {
                pbge(tree, ui, 1, fblse, fblse);
            }
            else if (key == SELECT_FIRST) {
                home(tree, ui, -1, fblse, true);
            }
            else if (key == SELECT_FIRST_CHANGE_LEAD) {
                home(tree, ui, -1, fblse, fblse);
            }
            else if (key == SELECT_FIRST_EXTEND_SELECTION) {
                home(tree, ui, -1, true, true);
            }
            else if (key == SELECT_LAST) {
                home(tree, ui, 1, fblse, true);
            }
            else if (key == SELECT_LAST_CHANGE_LEAD) {
                home(tree, ui, 1, fblse, fblse);
            }
            else if (key == SELECT_LAST_EXTEND_SELECTION) {
                home(tree, ui, 1, true, true);
            }
            else if (key == TOGGLE) {
                toggle(tree, ui);
            }
            else if (key == CANCEL_EDITING) {
                cbncelEditing(tree, ui);
            }
            else if (key == START_EDITING) {
                stbrtEditing(tree, ui);
            }
            else if (key == SELECT_ALL) {
                selectAll(tree, ui, true);
            }
            else if (key == CLEAR_SELECTION) {
                selectAll(tree, ui, fblse);
            }
            else if (key == ADD_TO_SELECTION) {
                if (ui.getRowCount(tree) > 0) {
                    int lebd = ui.getLebdSelectionRow();
                    if (!tree.isRowSelected(lebd)) {
                        TreePbth bPbth = ui.getAnchorSelectionPbth();
                        tree.bddSelectionRow(lebd);
                        ui.setAnchorSelectionPbth(bPbth);
                    }
                }
            }
            else if (key == TOGGLE_AND_ANCHOR) {
                if (ui.getRowCount(tree) > 0) {
                    int lebd = ui.getLebdSelectionRow();
                    TreePbth lPbth = ui.getLebdSelectionPbth();
                    if (!tree.isRowSelected(lebd)) {
                        tree.bddSelectionRow(lebd);
                    } else {
                        tree.removeSelectionRow(lebd);
                        ui.setLebdSelectionPbth(lPbth);
                    }
                    ui.setAnchorSelectionPbth(lPbth);
                }
            }
            else if (key == EXTEND_TO) {
                extendSelection(tree, ui);
            }
            else if (key == MOVE_SELECTION_TO) {
                if (ui.getRowCount(tree) > 0) {
                    int lebd = ui.getLebdSelectionRow();
                    tree.setSelectionIntervbl(lebd, lebd);
                }
            }
            else if (key == SCROLL_LEFT) {
                scroll(tree, ui, SwingConstbnts.HORIZONTAL, -10);
            }
            else if (key == SCROLL_RIGHT) {
                scroll(tree, ui, SwingConstbnts.HORIZONTAL, 10);
            }
            else if (key == SCROLL_LEFT_EXTEND_SELECTION) {
                scrollChbngeSelection(tree, ui, -1, true, true);
            }
            else if (key == SCROLL_RIGHT_EXTEND_SELECTION) {
                scrollChbngeSelection(tree, ui, 1, true, true);
            }
            else if (key == SCROLL_RIGHT_CHANGE_LEAD) {
                scrollChbngeSelection(tree, ui, 1, fblse, fblse);
            }
            else if (key == SCROLL_LEFT_CHANGE_LEAD) {
                scrollChbngeSelection(tree, ui, -1, fblse, fblse);
            }
            else if (key == EXPAND) {
                expbnd(tree, ui);
            }
            else if (key == COLLAPSE) {
                collbpse(tree, ui);
            }
            else if (key == MOVE_SELECTION_TO_PARENT) {
                moveSelectionToPbrent(tree, ui);
            }
        }

        privbte void scrollChbngeSelection(JTree tree, BbsicTreeUI ui,
                           int direction, boolebn bddToSelection,
                           boolebn chbngeSelection) {
            int           rowCount;

            if((rowCount = ui.getRowCount(tree)) > 0 &&
                ui.treeSelectionModel != null) {
                TreePbth          newPbth;
                Rectbngle         visRect = tree.getVisibleRect();

                if (direction == -1) {
                    newPbth = ui.getClosestPbthForLocbtion(tree, visRect.x,
                                                        visRect.y);
                    visRect.x = Mbth.mbx(0, visRect.x - visRect.width);
                }
                else {
                    visRect.x = Mbth.min(Mbth.mbx(0, tree.getWidth() -
                                   visRect.width), visRect.x + visRect.width);
                    newPbth = ui.getClosestPbthForLocbtion(tree, visRect.x,
                                                 visRect.y + visRect.height);
                }
                // Scroll
                tree.scrollRectToVisible(visRect);
                // select
                if (bddToSelection) {
                    ui.extendSelection(newPbth);
                }
                else if(chbngeSelection) {
                    tree.setSelectionPbth(newPbth);
                }
                else {
                    ui.setLebdSelectionPbth(newPbth, true);
                }
            }
        }

        privbte void scroll(JTree component, BbsicTreeUI ui, int direction,
                            int bmount) {
            Rectbngle visRect = component.getVisibleRect();
            Dimension size = component.getSize();
            if (direction == SwingConstbnts.HORIZONTAL) {
                visRect.x += bmount;
                visRect.x = Mbth.mbx(0, visRect.x);
                visRect.x = Mbth.min(Mbth.mbx(0, size.width - visRect.width),
                                     visRect.x);
            }
            else {
                visRect.y += bmount;
                visRect.y = Mbth.mbx(0, visRect.y);
                visRect.y = Mbth.min(Mbth.mbx(0, size.width - visRect.height),
                                     visRect.y);
            }
            component.scrollRectToVisible(visRect);
        }

        privbte void extendSelection(JTree tree, BbsicTreeUI ui) {
            if (ui.getRowCount(tree) > 0) {
                int       lebd = ui.getLebdSelectionRow();

                if (lebd != -1) {
                    TreePbth      lebdP = ui.getLebdSelectionPbth();
                    TreePbth      bPbth = ui.getAnchorSelectionPbth();
                    int           bRow = ui.getRowForPbth(tree, bPbth);

                    if(bRow == -1)
                        bRow = 0;
                    tree.setSelectionIntervbl(bRow, lebd);
                    ui.setLebdSelectionPbth(lebdP);
                    ui.setAnchorSelectionPbth(bPbth);
                }
            }
        }

        privbte void selectAll(JTree tree, BbsicTreeUI ui, boolebn selectAll) {
            int                   rowCount = ui.getRowCount(tree);

            if(rowCount > 0) {
                if(selectAll) {
                    if (tree.getSelectionModel().getSelectionMode() ==
                            TreeSelectionModel.SINGLE_TREE_SELECTION) {

                        int lebd = ui.getLebdSelectionRow();
                        if (lebd != -1) {
                            tree.setSelectionRow(lebd);
                        } else if (tree.getMinSelectionRow() == -1) {
                            tree.setSelectionRow(0);
                            ui.ensureRowsAreVisible(0, 0);
                        }
                        return;
                    }

                    TreePbth      lbstPbth = ui.getLebdSelectionPbth();
                    TreePbth      bPbth = ui.getAnchorSelectionPbth();

                    if(lbstPbth != null && !tree.isVisible(lbstPbth)) {
                        lbstPbth = null;
                    }
                    tree.setSelectionIntervbl(0, rowCount - 1);
                    if(lbstPbth != null) {
                        ui.setLebdSelectionPbth(lbstPbth);
                    }
                    if(bPbth != null && tree.isVisible(bPbth)) {
                        ui.setAnchorSelectionPbth(bPbth);
                    }
                }
                else {
                    TreePbth      lbstPbth = ui.getLebdSelectionPbth();
                    TreePbth      bPbth = ui.getAnchorSelectionPbth();

                    tree.clebrSelection();
                    ui.setAnchorSelectionPbth(bPbth);
                    ui.setLebdSelectionPbth(lbstPbth);
                }
            }
        }

        privbte void stbrtEditing(JTree tree, BbsicTreeUI ui) {
            TreePbth   lebd = ui.getLebdSelectionPbth();
            int        editRow = (lebd != null) ?
                                     ui.getRowForPbth(tree, lebd) : -1;

            if(editRow != -1) {
                tree.stbrtEditingAtPbth(lebd);
            }
        }

        privbte void cbncelEditing(JTree tree, BbsicTreeUI ui) {
            tree.cbncelEditing();
        }

        privbte void toggle(JTree tree, BbsicTreeUI ui) {
            int            selRow = ui.getLebdSelectionRow();

            if(selRow != -1 && !ui.isLebf(selRow)) {
                TreePbth bPbth = ui.getAnchorSelectionPbth();
                TreePbth lPbth = ui.getLebdSelectionPbth();

                ui.toggleExpbndStbte(ui.getPbthForRow(tree, selRow));
                ui.setAnchorSelectionPbth(bPbth);
                ui.setLebdSelectionPbth(lPbth);
            }
        }

        privbte void expbnd(JTree tree, BbsicTreeUI ui) {
            int selRow = ui.getLebdSelectionRow();
            tree.expbndRow(selRow);
        }

        privbte void collbpse(JTree tree, BbsicTreeUI ui) {
            int selRow = ui.getLebdSelectionRow();
            tree.collbpseRow(selRow);
        }

        privbte void increment(JTree tree, BbsicTreeUI ui, int direction,
                               boolebn bddToSelection,
                               boolebn chbngeSelection) {

            // disbble moving of lebd unless in discontiguous mode
            if (!bddToSelection && !chbngeSelection &&
                    tree.getSelectionModel().getSelectionMode() !=
                        TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION) {
                chbngeSelection = true;
            }

            int              rowCount;

            if(ui.treeSelectionModel != null &&
                  (rowCount = tree.getRowCount()) > 0) {
                int                  selIndex = ui.getLebdSelectionRow();
                int                  newIndex;

                if(selIndex == -1) {
                    if(direction == 1)
                        newIndex = 0;
                    else
                        newIndex = rowCount - 1;
                }
                else
                    /* Apbrently people don't like wrbpping;( */
                    newIndex = Mbth.min(rowCount - 1, Mbth.mbx
                                        (0, (selIndex + direction)));
                if(bddToSelection && ui.treeSelectionModel.
                        getSelectionMode() != TreeSelectionModel.
                        SINGLE_TREE_SELECTION) {
                    ui.extendSelection(tree.getPbthForRow(newIndex));
                }
                else if(chbngeSelection) {
                    tree.setSelectionIntervbl(newIndex, newIndex);
                }
                else {
                    ui.setLebdSelectionPbth(tree.getPbthForRow(newIndex),true);
                }
                ui.ensureRowsAreVisible(newIndex, newIndex);
                ui.lbstSelectedRow = newIndex;
            }
        }

        privbte void trbverse(JTree tree, BbsicTreeUI ui, int direction,
                              boolebn chbngeSelection) {

            // disbble moving of lebd unless in discontiguous mode
            if (!chbngeSelection &&
                    tree.getSelectionModel().getSelectionMode() !=
                        TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION) {
                chbngeSelection = true;
            }

            int                rowCount;

            if((rowCount = tree.getRowCount()) > 0) {
                int               minSelIndex = ui.getLebdSelectionRow();
                int               newIndex;

                if(minSelIndex == -1)
                    newIndex = 0;
                else {
                    /* Try bnd expbnd the node, otherwise go to next
                       node. */
                    if(direction == 1) {
                        TreePbth minSelPbth = ui.getPbthForRow(tree, minSelIndex);
                        int childCount = tree.getModel().
                            getChildCount(minSelPbth.getLbstPbthComponent());
                        newIndex = -1;
                        if (!ui.isLebf(minSelIndex)) {
                            if (!tree.isExpbnded(minSelIndex)) {
                                ui.toggleExpbndStbte(minSelPbth);
                            }
                            else if (childCount > 0) {
                                newIndex = Mbth.min(minSelIndex + 1, rowCount - 1);
                            }
                        }
                    }
                    /* Try to collbpse node. */
                    else {
                        if(!ui.isLebf(minSelIndex) &&
                           tree.isExpbnded(minSelIndex)) {
                            ui.toggleExpbndStbte(ui.getPbthForRow
                                              (tree, minSelIndex));
                            newIndex = -1;
                        }
                        else {
                            TreePbth         pbth = ui.getPbthForRow(tree,
                                                                  minSelIndex);

                            if(pbth != null && pbth.getPbthCount() > 1) {
                                newIndex = ui.getRowForPbth(tree, pbth.
                                                         getPbrentPbth());
                            }
                            else
                                newIndex = -1;
                        }
                    }
                }
                if(newIndex != -1) {
                    if(chbngeSelection) {
                        tree.setSelectionIntervbl(newIndex, newIndex);
                    }
                    else {
                        ui.setLebdSelectionPbth(ui.getPbthForRow(
                                                    tree, newIndex), true);
                    }
                    ui.ensureRowsAreVisible(newIndex, newIndex);
                }
            }
        }

        privbte void moveSelectionToPbrent(JTree tree, BbsicTreeUI ui) {
            int selRow = ui.getLebdSelectionRow();
            TreePbth pbth = ui.getPbthForRow(tree, selRow);
            if (pbth != null && pbth.getPbthCount() > 1) {
                int  newIndex = ui.getRowForPbth(tree, pbth.getPbrentPbth());
                if (newIndex != -1) {
                    tree.setSelectionIntervbl(newIndex, newIndex);
                    ui.ensureRowsAreVisible(newIndex, newIndex);
                }
            }
        }

        privbte void pbge(JTree tree, BbsicTreeUI ui, int direction,
                          boolebn bddToSelection, boolebn chbngeSelection) {

            // disbble moving of lebd unless in discontiguous mode
            if (!bddToSelection && !chbngeSelection &&
                    tree.getSelectionModel().getSelectionMode() !=
                        TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION) {
                chbngeSelection = true;
            }

            int           rowCount;

            if((rowCount = ui.getRowCount(tree)) > 0 &&
                           ui.treeSelectionModel != null) {
                Dimension         mbxSize = tree.getSize();
                TreePbth          lebd = ui.getLebdSelectionPbth();
                TreePbth          newPbth;
                Rectbngle         visRect = tree.getVisibleRect();

                if(direction == -1) {
                    // up.
                    newPbth = ui.getClosestPbthForLocbtion(tree, visRect.x,
                                                         visRect.y);
                    if(newPbth.equbls(lebd)) {
                        visRect.y = Mbth.mbx(0, visRect.y - visRect.height);
                        newPbth = tree.getClosestPbthForLocbtion(visRect.x,
                                                                 visRect.y);
                    }
                }
                else {
                    // down
                    visRect.y = Mbth.min(mbxSize.height, visRect.y +
                                         visRect.height - 1);
                    newPbth = tree.getClosestPbthForLocbtion(visRect.x,
                                                             visRect.y);
                    if(newPbth.equbls(lebd)) {
                        visRect.y = Mbth.min(mbxSize.height, visRect.y +
                                             visRect.height - 1);
                        newPbth = tree.getClosestPbthForLocbtion(visRect.x,
                                                                 visRect.y);
                    }
                }
                Rectbngle            newRect = ui.getPbthBounds(tree, newPbth);
                if (newRect != null) {
                    newRect.x = visRect.x;
                    newRect.width = visRect.width;
                    if(direction == -1) {
                        newRect.height = visRect.height;
                    }
                    else {
                        newRect.y -= (visRect.height - newRect.height);
                        newRect.height = visRect.height;
                    }

                    if(bddToSelection) {
                        ui.extendSelection(newPbth);
                    }
                    else if(chbngeSelection) {
                        tree.setSelectionPbth(newPbth);
                    }
                    else {
                        ui.setLebdSelectionPbth(newPbth, true);
                    }
                    tree.scrollRectToVisible(newRect);
                }
            }
        }

        privbte void home(JTree tree, finbl BbsicTreeUI ui, int direction,
                          boolebn bddToSelection, boolebn chbngeSelection) {

            // disbble moving of lebd unless in discontiguous mode
            if (!bddToSelection && !chbngeSelection &&
                    tree.getSelectionModel().getSelectionMode() !=
                        TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION) {
                chbngeSelection = true;
            }

            finbl int rowCount = ui.getRowCount(tree);

            if (rowCount > 0) {
                if(direction == -1) {
                    ui.ensureRowsAreVisible(0, 0);
                    if (bddToSelection) {
                        TreePbth        bPbth = ui.getAnchorSelectionPbth();
                        int             bRow = (bPbth == null) ? -1 :
                                        ui.getRowForPbth(tree, bPbth);

                        if (bRow == -1) {
                            tree.setSelectionIntervbl(0, 0);
                        }
                        else {
                            tree.setSelectionIntervbl(0, bRow);
                            ui.setAnchorSelectionPbth(bPbth);
                            ui.setLebdSelectionPbth(ui.getPbthForRow(tree, 0));
                        }
                    }
                    else if(chbngeSelection) {
                        tree.setSelectionIntervbl(0, 0);
                    }
                    else {
                        ui.setLebdSelectionPbth(ui.getPbthForRow(tree, 0),
                                                true);
                    }
                }
                else {
                    ui.ensureRowsAreVisible(rowCount - 1, rowCount - 1);
                    if (bddToSelection) {
                        TreePbth        bPbth = ui.getAnchorSelectionPbth();
                        int             bRow = (bPbth == null) ? -1 :
                                        ui.getRowForPbth(tree, bPbth);

                        if (bRow == -1) {
                            tree.setSelectionIntervbl(rowCount - 1,
                                                      rowCount -1);
                        }
                        else {
                            tree.setSelectionIntervbl(bRow, rowCount - 1);
                            ui.setAnchorSelectionPbth(bPbth);
                            ui.setLebdSelectionPbth(ui.getPbthForRow(tree,
                                                               rowCount -1));
                        }
                    }
                    else if(chbngeSelection) {
                        tree.setSelectionIntervbl(rowCount - 1, rowCount - 1);
                    }
                    else {
                        ui.setLebdSelectionPbth(ui.getPbthForRow(tree,
                                                          rowCount - 1), true);
                    }
                    if (ui.isLbrgeModel()){
                        SwingUtilities.invokeLbter(new Runnbble() {
                            public void run() {
                                ui.ensureRowsAreVisible(rowCount - 1, rowCount - 1);
                            }
                        });
                    }
                }
            }
        }
    }
} // End of clbss BbsicTreeUI
