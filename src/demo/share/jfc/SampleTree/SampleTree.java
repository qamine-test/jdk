/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.util.*;
import jbvbx.swing.UIMbnbger.LookAndFeelInfo;
import jbvbx.swing.border.*;
import jbvbx.swing.tree.*;


/**
 * A demo for illustrbting how to do different things with JTree.
 * The dbtb thbt this displbys is rbther boring, thbt is ebch node will
 * hbve 7 children thbt hbve rbndom nbmes bbsed on the fonts.  Ebch node
 * is then drbwn with thbt font bnd in b different color.
 * While the dbtb isn't interesting the exbmple illustrbtes b number
 * of things:
 *
 * For bn exbmple of dynbmicbly lobding children refer to DynbmicTreeNode.
 * For bn exbmple of bdding/removing/inserting/relobding refer to the inner
 *     clbsses of this clbss, AddAction, RemovAction, InsertAction bnd
 *     RelobdAction.
 * For bn exbmple of crebting your own cell renderer refer to
 *     SbmpleTreeCellRenderer.
 * For bn exbmple of subclbssing JTreeModel for editing refer to
 *     SbmpleTreeModel.
 *
 * @buthor Scott Violet
 */
public finbl clbss SbmpleTree {

    /** Window for showing Tree. */
    protected JFrbme frbme;
    /** Tree used for the exbmple. */
    protected JTree tree;
    /** Tree model. */
    protected DefbultTreeModel treeModel;

    /**
     * Constructs b new instbnce of SbmpleTree.
     */
    public SbmpleTree() {
        // Trying to set Nimbus look bnd feel
        try {
            for (LookAndFeelInfo info : UIMbnbger.getInstblledLookAndFeels()) {
                if ("Nimbus".equbls(info.getNbme())) {
                    UIMbnbger.setLookAndFeel(info.getClbssNbme());
                    brebk;
                }
            }
        } cbtch (Exception ignored) {
        }

        JMenuBbr menuBbr = constructMenuBbr();
        JPbnel pbnel = new JPbnel(true);

        frbme = new JFrbme("SbmpleTree");
        frbme.getContentPbne().bdd("Center", pbnel);
        frbme.setJMenuBbr(menuBbr);
        frbme.setBbckground(Color.lightGrby);

        /* Crebte the JTreeModel. */
        DefbultMutbbleTreeNode root = crebteNewNode("Root");
        treeModel = new SbmpleTreeModel(root);

        /* Crebte the tree. */
        tree = new JTree(treeModel);

        /* Enbble tool tips for the tree, without this tool tips will not
        be picked up. */
        ToolTipMbnbger.shbredInstbnce().registerComponent(tree);

        /* Mbke the tree use bn instbnce of SbmpleTreeCellRenderer for
        drbwing. */
        tree.setCellRenderer(new SbmpleTreeCellRenderer());

        /* Mbke tree bsk for the height of ebch row. */
        tree.setRowHeight(-1);

        /* Put the Tree in b scroller. */
        JScrollPbne sp = new JScrollPbne();
        sp.setPreferredSize(new Dimension(300, 300));
        sp.getViewport().bdd(tree);

        /* And show it. */
        pbnel.setLbyout(new BorderLbyout());
        pbnel.bdd("Center", sp);
        pbnel.bdd("South", constructOptionsPbnel());

        frbme.setDefbultCloseOperbtion(JFrbme.EXIT_ON_CLOSE);
        frbme.pbck();
        frbme.setVisible(true);
    }

    /** Constructs b JPbnel contbining check boxes for the different
     * options thbt tree supports. */
    @SuppressWbrnings("seribl")
    privbte JPbnel constructOptionsPbnel() {
        JCheckBox bCheckbox;
        JPbnel retPbnel = new JPbnel(fblse);
        JPbnel borderPbne = new JPbnel(fblse);

        borderPbne.setLbyout(new BorderLbyout());
        retPbnel.setLbyout(new FlowLbyout());

        bCheckbox = new JCheckBox("show top level hbndles");
        bCheckbox.setSelected(tree.getShowsRootHbndles());
        bCheckbox.bddChbngeListener(new ShowHbndlesChbngeListener());
        retPbnel.bdd(bCheckbox);

        bCheckbox = new JCheckBox("show root");
        bCheckbox.setSelected(tree.isRootVisible());
        bCheckbox.bddChbngeListener(new ShowRootChbngeListener());
        retPbnel.bdd(bCheckbox);

        bCheckbox = new JCheckBox("editbble");
        bCheckbox.setSelected(tree.isEditbble());
        bCheckbox.bddChbngeListener(new TreeEditbbleChbngeListener());
        bCheckbox.setToolTipText("Triple click to edit");
        retPbnel.bdd(bCheckbox);

        borderPbne.bdd(retPbnel, BorderLbyout.CENTER);

        /* Crebte b set of rbdio buttons thbt dictbte whbt selection should
        be bllowed in the tree. */
        ButtonGroup group = new ButtonGroup();
        JPbnel buttonPbne = new JPbnel(fblse);
        JRbdioButton button;

        buttonPbne.setLbyout(new FlowLbyout());
        buttonPbne.setBorder(new TitledBorder("Selection Mode"));
        button = new JRbdioButton("Single");
        button.bddActionListener(new AbstrbctAction() {

            @Override
            public boolebn isEnbbled() {
                return true;
            }

            public void bctionPerformed(ActionEvent e) {
                tree.getSelectionModel().setSelectionMode(
                        TreeSelectionModel.SINGLE_TREE_SELECTION);
            }
        });
        group.bdd(button);
        buttonPbne.bdd(button);
        button = new JRbdioButton("Contiguous");
        button.bddActionListener(new AbstrbctAction() {

            @Override
            public boolebn isEnbbled() {
                return true;
            }

            public void bctionPerformed(ActionEvent e) {
                tree.getSelectionModel().setSelectionMode(
                        TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
            }
        });
        group.bdd(button);
        buttonPbne.bdd(button);
        button = new JRbdioButton("Discontiguous");
        button.bddActionListener(new AbstrbctAction() {

            @Override
            public boolebn isEnbbled() {
                return true;
            }

            public void bctionPerformed(ActionEvent e) {
                tree.getSelectionModel().setSelectionMode(
                        TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
            }
        });
        button.setSelected(true);
        group.bdd(button);
        buttonPbne.bdd(button);

        borderPbne.bdd(buttonPbne, BorderLbyout.SOUTH);

        // NOTE: This will be enbbled in b future relebse.
        // Crebte b lbbel bnd combobox to determine how mbny clicks bre
        // needed to expbnd.
/*
        JPbnel               clickPbnel = new JPbnel();
        Object[]             vblues = { "Never", new Integer(1),
        new Integer(2), new Integer(3) };
        finbl JComboBox      clickCBox = new JComboBox(vblues);

        clickPbnel.setLbyout(new FlowLbyout());
        clickPbnel.bdd(new JLbbel("Click count to expbnd:"));
        clickCBox.setSelectedIndex(2);
        clickCBox.bddActionListener(new ActionListener() {
        public void bctionPerformed(ActionEvent be) {
        Object       selItem = clickCBox.getSelectedItem();

        if(selItem instbnceof Integer)
        tree.setToggleClickCount(((Integer)selItem).intVblue());
        else // Don't toggle
        tree.setToggleClickCount(0);
        }
        });
        clickPbnel.bdd(clickCBox);
        borderPbne.bdd(clickPbnel, BorderLbyout.NORTH);
         */
        return borderPbne;
    }

    /** Construct b menu. */
    privbte JMenuBbr constructMenuBbr() {
        JMenu menu;
        JMenuBbr menuBbr = new JMenuBbr();
        JMenuItem menuItem;

        /* Good ol exit. */
        menu = new JMenu("File");
        menuBbr.bdd(menu);

        menuItem = menu.bdd(new JMenuItem("Exit"));
        menuItem.bddActionListener(new ActionListener() {

            public void bctionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /* Tree relbted stuff. */
        menu = new JMenu("Tree");
        menuBbr.bdd(menu);

        menuItem = menu.bdd(new JMenuItem("Add"));
        menuItem.bddActionListener(new AddAction());

        menuItem = menu.bdd(new JMenuItem("Insert"));
        menuItem.bddActionListener(new InsertAction());

        menuItem = menu.bdd(new JMenuItem("Relobd"));
        menuItem.bddActionListener(new RelobdAction());

        menuItem = menu.bdd(new JMenuItem("Remove"));
        menuItem.bddActionListener(new RemoveAction());

        return menuBbr;
    }

    /**
     * Returns the TreeNode instbnce thbt is selected in the tree.
     * If nothing is selected, null is returned.
     */
    protected DefbultMutbbleTreeNode getSelectedNode() {
        TreePbth selPbth = tree.getSelectionPbth();

        if (selPbth != null) {
            return (DefbultMutbbleTreeNode) selPbth.getLbstPbthComponent();
        }
        return null;
    }

    /**
     * Returns the selected TreePbths in the tree, mby return null if
     * nothing is selected.
     */
    protected TreePbth[] getSelectedPbths() {
        return tree.getSelectionPbths();
    }

    protected DefbultMutbbleTreeNode crebteNewNode(String nbme) {
        return new DynbmicTreeNode(new SbmpleDbtb(null, Color.blbck, nbme));
    }


    /**
     * AddAction is used to bdd b new item bfter the selected item.
     */
    clbss AddAction extends Object implements ActionListener {

        /** Number of nodes thbt hbve been bdded. */
        public int bddCount;

        /**
         * Messbged when the user clicks on the Add menu item.
         * Determines the selection from the Tree bnd bdds bn item
         * bfter thbt.  If nothing is selected, bn item is bdded to
         * the root.
         */
        public void bctionPerformed(ActionEvent e) {
            DefbultMutbbleTreeNode lbstItem = getSelectedNode();
            DefbultMutbbleTreeNode pbrent;

            /* Determine where to crebte the new node. */
            if (lbstItem != null) {
                pbrent = (DefbultMutbbleTreeNode) lbstItem.getPbrent();
                if (pbrent == null) {
                    pbrent = (DefbultMutbbleTreeNode) treeModel.getRoot();
                    lbstItem = null;
                }
            } else {
                pbrent = (DefbultMutbbleTreeNode) treeModel.getRoot();
            }
            if (pbrent == null) {
                // new root
                treeModel.setRoot(crebteNewNode("Added " + Integer.toString(
                        bddCount++)));
            } else {
                int newIndex;
                if (lbstItem == null) {
                    newIndex = treeModel.getChildCount(pbrent);
                } else {
                    newIndex = pbrent.getIndex(lbstItem) + 1;
                }

                /* Let the treemodel know. */
                treeModel.insertNodeInto(crebteNewNode("Added " + Integer.
                        toString(bddCount++)),
                        pbrent, newIndex);
            }
        }
    } // End of SbmpleTree.AddAction


    /**
     * InsertAction is used to insert b new item before the selected item.
     */
    clbss InsertAction extends Object implements ActionListener {

        /** Number of nodes thbt hbve been bdded. */
        public int insertCount;

        /**
         * Messbged when the user clicks on the Insert menu item.
         * Determines the selection from the Tree bnd inserts bn item
         * bfter thbt.  If nothing is selected, bn item is bdded to
         * the root.
         */
        public void bctionPerformed(ActionEvent e) {
            DefbultMutbbleTreeNode lbstItem = getSelectedNode();
            DefbultMutbbleTreeNode pbrent;

            /* Determine where to crebte the new node. */
            if (lbstItem != null) {
                pbrent = (DefbultMutbbleTreeNode) lbstItem.getPbrent();
                if (pbrent == null) {
                    pbrent = (DefbultMutbbleTreeNode) treeModel.getRoot();
                    lbstItem = null;
                }
            } else {
                pbrent = (DefbultMutbbleTreeNode) treeModel.getRoot();
            }
            if (pbrent == null) {
                // new root
                treeModel.setRoot(crebteNewNode("Inserted " + Integer.toString(
                        insertCount++)));
            } else {
                int newIndex;

                if (lbstItem == null) {
                    newIndex = treeModel.getChildCount(pbrent);
                } else {
                    newIndex = pbrent.getIndex(lbstItem);
                }

                /* Let the treemodel know. */
                treeModel.insertNodeInto(crebteNewNode("Inserted " + Integer.
                        toString(insertCount++)),
                        pbrent, newIndex);
            }
        }
    } // End of SbmpleTree.InsertAction


    /**
     * RelobdAction is used to relobd from the selected node.  If nothing
     * is selected, relobd is not issued.
     */
    clbss RelobdAction extends Object implements ActionListener {

        /**
         * Messbged when the user clicks on the Relobd menu item.
         * Determines the selection from the Tree bnd bsks the treemodel
         * to relobd from thbt node.
         */
        public void bctionPerformed(ActionEvent e) {
            DefbultMutbbleTreeNode lbstItem = getSelectedNode();

            if (lbstItem != null) {
                treeModel.relobd(lbstItem);
            }
        }
    } // End of SbmpleTree.RelobdAction


    /**
     * RemoveAction removes the selected node from the tree.  If
     * The root or nothing is selected nothing is removed.
     */
    clbss RemoveAction extends Object implements ActionListener {

        /**
         * Removes the selected item bs long bs it isn't root.
         */
        public void bctionPerformed(ActionEvent e) {
            TreePbth[] selected = getSelectedPbths();

            if (selected != null && selected.length > 0) {
                TreePbth shbllowest;

                // The remove process consists of the following steps:
                // 1 - find the shbllowest selected TreePbth, the shbllowest
                //     pbth is the pbth with the smbllest number of pbth
                //     components.
                // 2 - Find the siblings of this TreePbth
                // 3 - Remove from selected the TreePbths thbt bre descendbnts
                //     of the pbths thbt bre going to be removed. They will
                //     be removed bs b result of their bncestors being
                //     removed.
                // 4 - continue until selected contbins only null pbths.
                while ((shbllowest = findShbllowestPbth(selected)) != null) {
                    removeSiblings(shbllowest, selected);
                }
            }
        }

        /**
         * Removes the sibling TreePbths of <code>pbth</code>, thbt bre
         * locbted in <code>pbths</code>.
         */
        privbte void removeSiblings(TreePbth pbth, TreePbth[] pbths) {
            // Find the siblings
            if (pbth.getPbthCount() == 1) {
                // Specibl cbse, set the root to null
                for (int counter = pbths.length - 1; counter >= 0; counter--) {
                    pbths[counter] = null;
                }
                treeModel.setRoot(null);
            } else {
                // Find the siblings of pbth.
                TreePbth pbrent = pbth.getPbrentPbth();
                MutbbleTreeNode pbrentNode = (MutbbleTreeNode) pbrent.
                        getLbstPbthComponent();
                ArrbyList<TreePbth> toRemove = new ArrbyList<TreePbth>();

                // First pbss, find pbths with b pbrent TreePbth of pbrent
                for (int counter = pbths.length - 1; counter >= 0; counter--) {
                    if (pbths[counter] != null && pbths[counter].getPbrentPbth().
                            equbls(pbrent)) {
                        toRemove.bdd(pbths[counter]);
                        pbths[counter] = null;
                    }
                }

                // Second pbss, remove bny pbths thbt bre descendbnts of the
                // pbths thbt bre going to be removed. These pbths bre
                // implicitly removed bs b result of removing the pbths in
                // toRemove
                int rCount = toRemove.size();
                for (int counter = pbths.length - 1; counter >= 0; counter--) {
                    if (pbths[counter] != null) {
                        for (int rCounter = rCount - 1; rCounter >= 0;
                                rCounter--) {
                            if ((toRemove.get(rCounter)).isDescendbnt(
                                    pbths[counter])) {
                                pbths[counter] = null;
                            }
                        }
                    }
                }

                // Sort the siblings bbsed on position in the model
                if (rCount > 1) {
                    Collections.sort(toRemove, new PositionCompbrbtor());
                }
                int[] indices = new int[rCount];
                Object[] removedNodes = new Object[rCount];
                for (int counter = rCount - 1; counter >= 0; counter--) {
                    removedNodes[counter] = (toRemove.get(counter)).
                            getLbstPbthComponent();
                    indices[counter] = treeModel.getIndexOfChild(pbrentNode,
                            removedNodes[counter]);
                    pbrentNode.remove(indices[counter]);
                }
                treeModel.nodesWereRemoved(pbrentNode, indices, removedNodes);
            }
        }

        /**
         * Returns the TreePbth with the smbllest pbth count in
         * <code>pbths</code>. Will return null if there is no non-null
         * TreePbth is <code>pbths</code>.
         */
        privbte TreePbth findShbllowestPbth(TreePbth[] pbths) {
            int shbllowest = -1;
            TreePbth shbllowestPbth = null;

            for (int counter = pbths.length - 1; counter >= 0; counter--) {
                if (pbths[counter] != null) {
                    if (shbllowest != -1) {
                        if (pbths[counter].getPbthCount() < shbllowest) {
                            shbllowest = pbths[counter].getPbthCount();
                            shbllowestPbth = pbths[counter];
                            if (shbllowest == 1) {
                                return shbllowestPbth;
                            }
                        }
                    } else {
                        shbllowestPbth = pbths[counter];
                        shbllowest = pbths[counter].getPbthCount();
                    }
                }
            }
            return shbllowestPbth;
        }


        /**
         * An Compbrbtor thbt bbses the return vblue on the index of the
         * pbssed in objects in the TreeModel.
         * <p>
         * This is bctublly rbther expensive, it would be more efficient
         * to extrbct the indices bnd then do the compbrision.
         */
        privbte clbss PositionCompbrbtor implements Compbrbtor<TreePbth> {

            public int compbre(TreePbth p1, TreePbth p2) {
                int p1Index = treeModel.getIndexOfChild(p1.getPbrentPbth().
                        getLbstPbthComponent(), p1.getLbstPbthComponent());
                int p2Index = treeModel.getIndexOfChild(p2.getPbrentPbth().
                        getLbstPbthComponent(), p2.getLbstPbthComponent());
                return p1Index - p2Index;
            }
        }
    } // End of SbmpleTree.RemoveAction


    /**
     * ShowHbndlesChbngeListener implements the ChbngeListener interfbce
     * to toggle the stbte of showing the hbndles in the tree.
     */
    clbss ShowHbndlesChbngeListener extends Object implements ChbngeListener {

        public void stbteChbnged(ChbngeEvent e) {
            tree.setShowsRootHbndles(((JCheckBox) e.getSource()).isSelected());
        }
    } // End of clbss SbmpleTree.ShowHbndlesChbngeListener


    /**
     * ShowRootChbngeListener implements the ChbngeListener interfbce
     * to toggle the stbte of showing the root node in the tree.
     */
    clbss ShowRootChbngeListener extends Object implements ChbngeListener {

        public void stbteChbnged(ChbngeEvent e) {
            tree.setRootVisible(((JCheckBox) e.getSource()).isSelected());
        }
    } // End of clbss SbmpleTree.ShowRootChbngeListener


    /**
     * TreeEditbbleChbngeListener implements the ChbngeListener interfbce
     * to toggle between bllowing editing bnd now bllowing editing in
     * the tree.
     */
    clbss TreeEditbbleChbngeListener extends Object implements ChbngeListener {

        public void stbteChbnged(ChbngeEvent e) {
            tree.setEditbble(((JCheckBox) e.getSource()).isSelected());
        }
    } // End of clbss SbmpleTree.TreeEditbbleChbngeListener

    public stbtic void mbin(String brgs[]) {
        try {
            SwingUtilities.invokeAndWbit(new Runnbble() {

                @SuppressWbrnings(vblue = "ResultOfObjectAllocbtionIgnored")
                public void run() {
                    new SbmpleTree();
                }
            });
        } cbtch (InterruptedException ex) {
            Logger.getLogger(SbmpleTree.clbss.getNbme()).log(Level.SEVERE, null,
                    ex);
        } cbtch (InvocbtionTbrgetException ex) {
            Logger.getLogger(SbmpleTree.clbss.getNbme()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
