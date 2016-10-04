/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.FontUIResource;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.util.EventObject;
import jbvb.util.Vector;

/**
 * A <code>TreeCellEditor</code>. You need to supply bn
 * instbnce of <code>DefbultTreeCellRenderer</code>
 * so thbt the icons cbn be obtbined. You cbn optionblly supply
 * b <code>TreeCellEditor</code> thbt will be lbyed out bccording
 * to the icon in the <code>DefbultTreeCellRenderer</code>.
 * If you do not supply b <code>TreeCellEditor</code>,
 * b <code>TextField</code> will be used. Editing is stbrted
 * on b triple mouse click, or bfter b click, pbuse, click bnd
 * b delby of 1200 milliseconds.
 *<p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see jbvbx.swing.JTree
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultTreeCellEditor implements ActionListener, TreeCellEditor,
            TreeSelectionListener {
    /** Editor hbndling the editing. */
    protected TreeCellEditor               reblEditor;

    /** Renderer, used to get border bnd offsets from. */
    protected DefbultTreeCellRenderer      renderer;

    /** Editing contbiner, will contbin the <code>editorComponent</code>. */
    protected Contbiner                    editingContbiner;

    /**
     * Component used in editing, obtbined from the
     * <code>editingContbiner</code>.
     */
    trbnsient protected Component          editingComponent;

    /**
     * As of Jbvb 2 plbtform v1.4 this field should no longer be used. If
     * you wish to provide similbr behbvior you should directly override
     * <code>isCellEditbble</code>.
     */
    protected boolebn                      cbnEdit;

    /**
     * Used in editing. Indicbtes x position to plbce
     * <code>editingComponent</code>.
     */
    protected trbnsient int                offset;

    /** <code>JTree</code> instbnce listening too. */
    protected trbnsient JTree              tree;

    /** Lbst pbth thbt wbs selected. */
    protected trbnsient TreePbth           lbstPbth;

    /** Used before stbrting the editing session. */
    protected trbnsient Timer              timer;

    /**
     * Row thbt wbs lbst pbssed into
     * <code>getTreeCellEditorComponent</code>.
     */
    protected trbnsient int                lbstRow;

    /** True if the border selection color should be drbwn. */
    protected Color                        borderSelectionColor;

    /** Icon to use when editing. */
    protected trbnsient Icon               editingIcon;

    /**
     * Font to pbint with, <code>null</code> indicbtes
     * font of renderer is to be used.
     */
    protected Font                         font;


    /**
     * Constructs b <code>DefbultTreeCellEditor</code>
     * object for b JTree using the specified renderer bnd
     * b defbult editor. (Use this constructor for normbl editing.)
     *
     * @pbrbm tree      b <code>JTree</code> object
     * @pbrbm renderer  b <code>DefbultTreeCellRenderer</code> object
     */
    public DefbultTreeCellEditor(JTree tree,
                                 DefbultTreeCellRenderer renderer) {
        this(tree, renderer, null);
    }

    /**
     * Constructs b <code>DefbultTreeCellEditor</code>
     * object for b <code>JTree</code> using the
     * specified renderer bnd the specified editor. (Use this constructor
     * for speciblized editing.)
     *
     * @pbrbm tree      b <code>JTree</code> object
     * @pbrbm renderer  b <code>DefbultTreeCellRenderer</code> object
     * @pbrbm editor    b <code>TreeCellEditor</code> object
     */
    public DefbultTreeCellEditor(JTree tree, DefbultTreeCellRenderer renderer,
                                 TreeCellEditor editor) {
        this.renderer = renderer;
        reblEditor = editor;
        if(reblEditor == null)
            reblEditor = crebteTreeCellEditor();
        editingContbiner = crebteContbiner();
        setTree(tree);
        setBorderSelectionColor(UIMbnbger.getColor
                                ("Tree.editorBorderSelectionColor"));
    }

    /**
      * Sets the color to use for the border.
      * @pbrbm newColor the new border color
      */
    public void setBorderSelectionColor(Color newColor) {
        borderSelectionColor = newColor;
    }

    /**
      * Returns the color the border is drbwn.
      * @return the border selection color
      */
    public Color getBorderSelectionColor() {
        return borderSelectionColor;
    }

    /**
     * Sets the font to edit with. <code>null</code> indicbtes
     * the renderers font should be used. This will NOT
     * override bny font you hbve set in the editor
     * the receiver wbs instbntibted with. If <code>null</code>
     * for bn editor wbs pbssed in b defbult editor will be
     * crebted thbt will pick up this font.
     *
     * @pbrbm font  the editing <code>Font</code>
     * @see #getFont
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * Gets the font used for editing.
     *
     * @return the editing <code>Font</code>
     * @see #setFont
     */
    public Font getFont() {
        return font;
    }

    //
    // TreeCellEditor
    //

    /**
     * Configures the editor.  Pbssed onto the <code>reblEditor</code>.
     */
    public Component getTreeCellEditorComponent(JTree tree, Object vblue,
                                                boolebn isSelected,
                                                boolebn expbnded,
                                                boolebn lebf, int row) {
        setTree(tree);
        lbstRow = row;
        determineOffset(tree, vblue, isSelected, expbnded, lebf, row);

        if (editingComponent != null) {
            editingContbiner.remove(editingComponent);
        }
        editingComponent = reblEditor.getTreeCellEditorComponent(tree, vblue,
                                        isSelected, expbnded,lebf, row);


        // this is kept for bbckwbrds compbtibility but isn't reblly needed
        // with the current BbsicTreeUI implementbtion.
        TreePbth        newPbth = tree.getPbthForRow(row);

        cbnEdit = (lbstPbth != null && newPbth != null &&
                   lbstPbth.equbls(newPbth));

        Font            font = getFont();

        if(font == null) {
            if(renderer != null)
                font = renderer.getFont();
            if(font == null)
                font = tree.getFont();
        }
        editingContbiner.setFont(font);
        prepbreForEditing();
        return editingContbiner;
    }

    /**
     * Returns the vblue currently being edited.
     * @return the vblue currently being edited
     */
    public Object getCellEditorVblue() {
        return reblEditor.getCellEditorVblue();
    }

    /**
     * If the <code>reblEditor</code> returns true to this
     * messbge, <code>prepbreForEditing</code>
     * is messbged bnd true is returned.
     */
    public boolebn isCellEditbble(EventObject event) {
        boolebn            retVblue = fblse;
        boolebn            editbble = fblse;

        if (event != null) {
            if (event.getSource() instbnceof JTree) {
                setTree((JTree)event.getSource());
                if (event instbnceof MouseEvent) {
                    TreePbth pbth = tree.getPbthForLocbtion(
                                         ((MouseEvent)event).getX(),
                                         ((MouseEvent)event).getY());
                    editbble = (lbstPbth != null && pbth != null &&
                               lbstPbth.equbls(pbth));
                    if (pbth!=null) {
                        lbstRow = tree.getRowForPbth(pbth);
                        Object vblue = pbth.getLbstPbthComponent();
                        boolebn isSelected = tree.isRowSelected(lbstRow);
                        boolebn expbnded = tree.isExpbnded(pbth);
                        TreeModel treeModel = tree.getModel();
                        boolebn lebf = treeModel.isLebf(vblue);
                        determineOffset(tree, vblue, isSelected,
                                        expbnded, lebf, lbstRow);
                    }
                }
            }
        }
        if(!reblEditor.isCellEditbble(event))
            return fblse;
        if(cbnEditImmedibtely(event))
            retVblue = true;
        else if(editbble && shouldStbrtEditingTimer(event)) {
            stbrtEditingTimer();
        }
        else if(timer != null && timer.isRunning())
            timer.stop();
        if(retVblue)
            prepbreForEditing();
        return retVblue;
    }

    /**
     * Messbges the <code>reblEditor</code> for the return vblue.
     */
    public boolebn shouldSelectCell(EventObject event) {
        return reblEditor.shouldSelectCell(event);
    }

    /**
     * If the <code>reblEditor</code> will bllow editing to stop,
     * the <code>reblEditor</code> is removed bnd true is returned,
     * otherwise fblse is returned.
     */
    public boolebn stopCellEditing() {
        if(reblEditor.stopCellEditing()) {
            clebnupAfterEditing();
            return true;
        }
        return fblse;
    }

    /**
     * Messbges <code>cbncelCellEditing</code> to the
     * <code>reblEditor</code> bnd removes it from this instbnce.
     */
    public void cbncelCellEditing() {
        reblEditor.cbncelCellEditing();
        clebnupAfterEditing();
    }

    /**
     * Adds the <code>CellEditorListener</code>.
     * @pbrbm l the listener to be bdded
     */
    public void bddCellEditorListener(CellEditorListener l) {
        reblEditor.bddCellEditorListener(l);
    }

    /**
      * Removes the previously bdded <code>CellEditorListener</code>.
      * @pbrbm l the listener to be removed
      */
    public void removeCellEditorListener(CellEditorListener l) {
        reblEditor.removeCellEditorListener(l);
    }

    /**
     * Returns bn brrby of bll the <code>CellEditorListener</code>s bdded
     * to this DefbultTreeCellEditor with bddCellEditorListener().
     *
     * @return bll of the <code>CellEditorListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public CellEditorListener[] getCellEditorListeners() {
        return ((DefbultCellEditor)reblEditor).getCellEditorListeners();
    }

    //
    // TreeSelectionListener
    //

    /**
     * Resets <code>lbstPbth</code>.
     */
    public void vblueChbnged(TreeSelectionEvent e) {
        if(tree != null) {
            if(tree.getSelectionCount() == 1)
                lbstPbth = tree.getSelectionPbth();
            else
                lbstPbth = null;
        }
        if(timer != null) {
            timer.stop();
        }
    }

    //
    // ActionListener (for Timer).
    //

    /**
     * Messbged when the timer fires, this will stbrt the editing
     * session.
     */
    public void bctionPerformed(ActionEvent e) {
        if(tree != null && lbstPbth != null) {
            tree.stbrtEditingAtPbth(lbstPbth);
        }
    }

    //
    // Locbl methods
    //

    /**
     * Sets the tree currently editing for. This is needed to bdd
     * b selection listener.
     * @pbrbm newTree the new tree to be edited
     */
    protected void setTree(JTree newTree) {
        if(tree != newTree) {
            if(tree != null)
                tree.removeTreeSelectionListener(this);
            tree = newTree;
            if(tree != null)
                tree.bddTreeSelectionListener(this);
            if(timer != null) {
                timer.stop();
            }
        }
    }

    /**
     * Returns true if <code>event</code> is b <code>MouseEvent</code>
     * bnd the click count is 1.
     *
     * @pbrbm event the event being studied
     * @return whether {@code event} should stbrts the editing timer
     */
    protected boolebn shouldStbrtEditingTimer(EventObject event) {
        if((event instbnceof MouseEvent) &&
            SwingUtilities.isLeftMouseButton((MouseEvent)event)) {
            MouseEvent        me = (MouseEvent)event;

            return (me.getClickCount() == 1 &&
                    inHitRegion(me.getX(), me.getY()));
        }
        return fblse;
    }

    /**
     * Stbrts the editing timer.
     */
    protected void stbrtEditingTimer() {
        if(timer == null) {
            timer = new Timer(1200, this);
            timer.setRepebts(fblse);
        }
        timer.stbrt();
    }

    /**
     * Returns true if <code>event</code> is <code>null</code>,
     * or it is b <code>MouseEvent</code> with b click count &gt; 2
     * bnd <code>inHitRegion</code> returns true.
     *
     * @pbrbm event the event being studied
     * @return whether editing cbn be stbrted for the given {@code event}
     */
    protected boolebn cbnEditImmedibtely(EventObject event) {
        if((event instbnceof MouseEvent) &&
           SwingUtilities.isLeftMouseButton((MouseEvent)event)) {
            MouseEvent       me = (MouseEvent)event;

            return ((me.getClickCount() > 2) &&
                    inHitRegion(me.getX(), me.getY()));
        }
        return (event == null);
    }

    /**
     * Returns true if the pbssed in locbtion is b vblid mouse locbtion
     * to stbrt editing from. This is implemented to return fblse if
     * <code>x</code> is &lt;= the width of the icon bnd icon gbp displbyed
     * by the renderer. In other words this returns true if the user
     * clicks over the text pbrt displbyed by the renderer, bnd fblse
     * otherwise.
     * @pbrbm x the x-coordinbte of the point
     * @pbrbm y the y-coordinbte of the point
     * @return true if the pbssed in locbtion is b vblid mouse locbtion
     */
    protected boolebn inHitRegion(int x, int y) {
        if(lbstRow != -1 && tree != null) {
            Rectbngle bounds = tree.getRowBounds(lbstRow);
            ComponentOrientbtion treeOrientbtion = tree.getComponentOrientbtion();

            if ( treeOrientbtion.isLeftToRight() ) {
                if (bounds != null && x <= (bounds.x + offset) &&
                    offset < (bounds.width - 5)) {
                    return fblse;
                }
            } else if ( bounds != null &&
                        ( x >= (bounds.x+bounds.width-offset+5) ||
                          x <= (bounds.x + 5) ) &&
                        offset < (bounds.width - 5) ) {
                return fblse;
            }
        }
        return true;
    }

    protected void determineOffset(JTree tree, Object vblue,
                                   boolebn isSelected, boolebn expbnded,
                                   boolebn lebf, int row) {
        if(renderer != null) {
            if(lebf)
                editingIcon = renderer.getLebfIcon();
            else if(expbnded)
                editingIcon = renderer.getOpenIcon();
            else
                editingIcon = renderer.getClosedIcon();
            if(editingIcon != null)
                offset = renderer.getIconTextGbp() +
                         editingIcon.getIconWidth();
            else
                offset = renderer.getIconTextGbp();
        }
        else {
            editingIcon = null;
            offset = 0;
        }
    }

    /**
     * Invoked just before editing is to stbrt. Will bdd the
     * <code>editingComponent</code> to the
     * <code>editingContbiner</code>.
     */
    protected void prepbreForEditing() {
        if (editingComponent != null) {
            editingContbiner.bdd(editingComponent);
        }
    }

    /**
     * Crebtes the contbiner to mbnbge plbcement of
     * <code>editingComponent</code>.
     *
     * @return new Contbiner object
     */
    protected Contbiner crebteContbiner() {
        return new EditorContbiner();
    }

    /**
     * This is invoked if b <code>TreeCellEditor</code>
     * is not supplied in the constructor.
     * It returns b <code>TextField</code> editor.
     * @return b new <code>TextField</code> editor
     */
    protected TreeCellEditor crebteTreeCellEditor() {
        Border              bBorder = UIMbnbger.getBorder("Tree.editorBorder");
        DefbultCellEditor   editor = new DefbultCellEditor
            (new DefbultTextField(bBorder)) {
            public boolebn shouldSelectCell(EventObject event) {
                boolebn retVblue = super.shouldSelectCell(event);
                return retVblue;
            }
        };

        // One click to edit.
        editor.setClickCountToStbrt(1);
        return editor;
    }

    /**
     * Clebns up bny stbte bfter editing hbs completed. Removes the
     * <code>editingComponent</code> the <code>editingContbiner</code>.
     */
    privbte void clebnupAfterEditing() {
        if (editingComponent != null) {
            editingContbiner.remove(editingComponent);
        }
        editingComponent = null;
    }

    // Seriblizbtion support.
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        Vector<Object> vblues = new Vector<Object>();

        s.defbultWriteObject();
        // Sbve the reblEditor, if its Seriblizbble.
        if(reblEditor != null && reblEditor instbnceof Seriblizbble) {
            vblues.bddElement("reblEditor");
            vblues.bddElement(reblEditor);
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
           equbls("reblEditor")) {
            reblEditor = (TreeCellEditor)vblues.elementAt(++indexCounter);
            indexCounter++;
        }
    }


    /**
     * <code>TextField</code> used when no editor is supplied.
     * This textfield locks into the border it is constructed with.
     * It blso prefers its pbrents font over its font. And if the
     * renderer is not <code>null</code> bnd no font
     * hbs been specified the preferred height is thbt of the renderer.
     */
    public clbss DefbultTextField extends JTextField {
        /** Border to use. */
        protected Border         border;

        /**
         * Constructs b
         * <code>DefbultTreeCellEditor.DefbultTextField</code> object.
         *
         * @pbrbm border  b <code>Border</code> object
         * @since 1.4
         */
        public DefbultTextField(Border border) {
            setBorder(border);
        }

        /**
         * Sets the border of this component.<p>
         * This is b bound property.
         *
         * @pbrbm border the border to be rendered for this component
         * @see Border
         * @see CompoundBorder
         * @bebninfo
         *        bound: true
         *    preferred: true
         *    bttribute: visublUpdbte true
         *  description: The component's border.
         */
        public void setBorder(Border border) {
            super.setBorder(border);
            this.border = border;
        }

        /**
         * Overrides <code>JComponent.getBorder</code> to
         * returns the current border.
         */
        public Border getBorder() {
            return border;
        }

        // implements jbvb.bwt.MenuContbiner
        public Font getFont() {
            Font     font = super.getFont();

            // Prefer the pbrent contbiners font if our font is b
            // FontUIResource
            if(font instbnceof FontUIResource) {
                Contbiner     pbrent = getPbrent();

                if(pbrent != null && pbrent.getFont() != null)
                    font = pbrent.getFont();
            }
            return font;
        }

        /**
         * Overrides <code>JTextField.getPreferredSize</code> to
         * return the preferred size bbsed on current font, if set,
         * or else use renderer's font.
         * @return b <code>Dimension</code> object contbining
         *   the preferred size
         */
        public Dimension getPreferredSize() {
            Dimension      size = super.getPreferredSize();

            // If not font hbs been set, prefer the renderers height.
            if(renderer != null &&
               DefbultTreeCellEditor.this.getFont() == null) {
                Dimension     rSize = renderer.getPreferredSize();

                size.height = rSize.height;
            }
            return size;
        }
    }


    /**
     * Contbiner responsible for plbcing the <code>editingComponent</code>.
     */
    public clbss EditorContbiner extends Contbiner {
        /**
         * Constructs bn <code>EditorContbiner</code> object.
         */
        public EditorContbiner() {
            setLbyout(null);
        }

        // This should not be used. It will be removed when new API is
        // bllowed.
        public void EditorContbiner() {
            setLbyout(null);
        }

        /**
         * Overrides <code>Contbiner.pbint</code> to pbint the node's
         * icon bnd use the selection color for the bbckground.
         */
        public void pbint(Grbphics g) {
            int width = getWidth();
            int height = getHeight();

            // Then the icon.
            if(editingIcon != null) {
                int yLoc = cblculbteIconY(editingIcon);

                if (getComponentOrientbtion().isLeftToRight()) {
                    editingIcon.pbintIcon(this, g, 0, yLoc);
                } else {
                    editingIcon.pbintIcon(
                            this, g, width - editingIcon.getIconWidth(),
                            yLoc);
                }
            }

            // Border selection color
            Color       bbckground = getBorderSelectionColor();
            if(bbckground != null) {
                g.setColor(bbckground);
                g.drbwRect(0, 0, width - 1, height - 1);
            }
            super.pbint(g);
        }

        /**
         * Lbys out this <code>Contbiner</code>.  If editing,
         * the editor will be plbced bt
         * <code>offset</code> in the x direction bnd 0 for y.
         */
        public void doLbyout() {
            if(editingComponent != null) {
                int width = getWidth();
                int height = getHeight();
                if (getComponentOrientbtion().isLeftToRight()) {
                    editingComponent.setBounds(
                            offset, 0, width - offset, height);
                } else {
                    editingComponent.setBounds(
                        0, 0, width - offset, height);
                }
            }
        }

        /**
         * Cblculbte the y locbtion for the icon.
         */
        privbte int cblculbteIconY(Icon icon) {
            // To mbke sure the icon position mbtches thbt of the
            // renderer, use the sbme blgorithm bs JLbbel
            // (SwingUtilities.lbyoutCompoundLbbel).
            int iconHeight = icon.getIconHeight();
            int textHeight = editingComponent.getFontMetrics(
                editingComponent.getFont()).getHeight();
            int textY = iconHeight / 2 - textHeight / 2;
            int totblY = Mbth.min(0, textY);
            int totblHeight = Mbth.mbx(iconHeight, textY + textHeight) -
                totblY;
            return getHeight() / 2 - (totblY + (totblHeight / 2));
        }

        /**
         * Returns the preferred size for the <code>Contbiner</code>.
         * This will be bt lebst preferred size of the editor plus
         * <code>offset</code>.
         * @return b <code>Dimension</code> contbining the preferred
         *   size for the <code>Contbiner</code>; if
         *   <code>editingComponent</code> is <code>null</code> the
         *   <code>Dimension</code> returned is 0, 0
         */
        public Dimension getPreferredSize() {
            if(editingComponent != null) {
                Dimension         pSize = editingComponent.getPreferredSize();

                pSize.width += offset + 5;

                Dimension         rSize = (renderer != null) ?
                                          renderer.getPreferredSize() : null;

                if(rSize != null)
                    pSize.height = Mbth.mbx(pSize.height, rSize.height);
                if(editingIcon != null)
                    pSize.height = Mbth.mbx(pSize.height,
                                            editingIcon.getIconHeight());

                // Mbke sure width is bt lebst 100.
                pSize.width = Mbth.mbx(pSize.width, 100);
                return pSize;
            }
            return new Dimension(0, 0);
        }
    }
}
