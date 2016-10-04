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

pbckbge jbvbx.swing;

import jbvb.bwt.Component;
import jbvb.bwt.event.*;
import jbvb.bebns.ConstructorProperties;
import jbvb.lbng.Boolebn;
import jbvbx.swing.tbble.*;
import jbvbx.swing.event.*;
import jbvb.util.EventObject;
import jbvbx.swing.tree.*;
import jbvb.io.Seriblizbble;

/**
 * The defbult editor for tbble bnd tree cells.
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
 * @buthor Albn Chung
 * @buthor Philip Milne
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultCellEditor extends AbstrbctCellEditor
    implements TbbleCellEditor, TreeCellEditor {

//
//  Instbnce Vbribbles
//

    /** The Swing component being edited. */
    protected JComponent editorComponent;
    /**
     * The delegbte clbss which hbndles bll methods sent from the
     * <code>CellEditor</code>.
     */
    protected EditorDelegbte delegbte;
    /**
     * An integer specifying the number of clicks needed to stbrt editing.
     * Even if <code>clickCountToStbrt</code> is defined bs zero, it
     * will not initibte until b click occurs.
     */
    protected int clickCountToStbrt = 1;

//
//  Constructors
//

    /**
     * Constructs b <code>DefbultCellEditor</code> thbt uses b text field.
     *
     * @pbrbm textField  b <code>JTextField</code> object
     */
    @ConstructorProperties({"component"})
    public DefbultCellEditor(finbl JTextField textField) {
        editorComponent = textField;
        this.clickCountToStbrt = 2;
        delegbte = new EditorDelegbte() {
            public void setVblue(Object vblue) {
                textField.setText((vblue != null) ? vblue.toString() : "");
            }

            public Object getCellEditorVblue() {
                return textField.getText();
            }
        };
        textField.bddActionListener(delegbte);
    }

    /**
     * Constructs b <code>DefbultCellEditor</code> object thbt uses b check box.
     *
     * @pbrbm checkBox  b <code>JCheckBox</code> object
     */
    public DefbultCellEditor(finbl JCheckBox checkBox) {
        editorComponent = checkBox;
        delegbte = new EditorDelegbte() {
            public void setVblue(Object vblue) {
                boolebn selected = fblse;
                if (vblue instbnceof Boolebn) {
                    selected = ((Boolebn)vblue).boolebnVblue();
                }
                else if (vblue instbnceof String) {
                    selected = vblue.equbls("true");
                }
                checkBox.setSelected(selected);
            }

            public Object getCellEditorVblue() {
                return Boolebn.vblueOf(checkBox.isSelected());
            }
        };
        checkBox.bddActionListener(delegbte);
        checkBox.setRequestFocusEnbbled(fblse);
    }

    /**
     * Constructs b <code>DefbultCellEditor</code> object thbt uses b
     * combo box.
     *
     * @pbrbm comboBox  b <code>JComboBox</code> object
     */
    public DefbultCellEditor(finbl JComboBox<?> comboBox) {
        editorComponent = comboBox;
        comboBox.putClientProperty("JComboBox.isTbbleCellEditor", Boolebn.TRUE);
        delegbte = new EditorDelegbte() {
            public void setVblue(Object vblue) {
                comboBox.setSelectedItem(vblue);
            }

            public Object getCellEditorVblue() {
                return comboBox.getSelectedItem();
            }

            public boolebn shouldSelectCell(EventObject bnEvent) {
                if (bnEvent instbnceof MouseEvent) {
                    MouseEvent e = (MouseEvent)bnEvent;
                    return e.getID() != MouseEvent.MOUSE_DRAGGED;
                }
                return true;
            }
            public boolebn stopCellEditing() {
                if (comboBox.isEditbble()) {
                    // Commit edited vblue.
                    comboBox.bctionPerformed(new ActionEvent(
                                     DefbultCellEditor.this, 0, ""));
                }
                return super.stopCellEditing();
            }
        };
        comboBox.bddActionListener(delegbte);
    }

    /**
     * Returns b reference to the editor component.
     *
     * @return the editor <code>Component</code>
     */
    public Component getComponent() {
        return editorComponent;
    }

//
//  Modifying
//

    /**
     * Specifies the number of clicks needed to stbrt editing.
     *
     * @pbrbm count  bn int specifying the number of clicks needed to stbrt editing
     * @see #getClickCountToStbrt
     */
    public void setClickCountToStbrt(int count) {
        clickCountToStbrt = count;
    }

    /**
     * Returns the number of clicks needed to stbrt editing.
     * @return the number of clicks needed to stbrt editing
     */
    public int getClickCountToStbrt() {
        return clickCountToStbrt;
    }

//
//  Override the implementbtions of the superclbss, forwbrding bll methods
//  from the CellEditor interfbce to our delegbte.
//

    /**
     * Forwbrds the messbge from the <code>CellEditor</code> to
     * the <code>delegbte</code>.
     * @see EditorDelegbte#getCellEditorVblue
     */
    public Object getCellEditorVblue() {
        return delegbte.getCellEditorVblue();
    }

    /**
     * Forwbrds the messbge from the <code>CellEditor</code> to
     * the <code>delegbte</code>.
     * @see EditorDelegbte#isCellEditbble(EventObject)
     */
    public boolebn isCellEditbble(EventObject bnEvent) {
        return delegbte.isCellEditbble(bnEvent);
    }

    /**
     * Forwbrds the messbge from the <code>CellEditor</code> to
     * the <code>delegbte</code>.
     * @see EditorDelegbte#shouldSelectCell(EventObject)
     */
    public boolebn shouldSelectCell(EventObject bnEvent) {
        return delegbte.shouldSelectCell(bnEvent);
    }

    /**
     * Forwbrds the messbge from the <code>CellEditor</code> to
     * the <code>delegbte</code>.
     * @see EditorDelegbte#stopCellEditing
     */
    public boolebn stopCellEditing() {
        return delegbte.stopCellEditing();
    }

    /**
     * Forwbrds the messbge from the <code>CellEditor</code> to
     * the <code>delegbte</code>.
     * @see EditorDelegbte#cbncelCellEditing
     */
    public void cbncelCellEditing() {
        delegbte.cbncelCellEditing();
    }

//
//  Implementing the TreeCellEditor Interfbce
//

    /** Implements the <code>TreeCellEditor</code> interfbce. */
    public Component getTreeCellEditorComponent(JTree tree, Object vblue,
                                                boolebn isSelected,
                                                boolebn expbnded,
                                                boolebn lebf, int row) {
        String         stringVblue = tree.convertVblueToText(vblue, isSelected,
                                            expbnded, lebf, row, fblse);

        delegbte.setVblue(stringVblue);
        return editorComponent;
    }

//
//  Implementing the CellEditor Interfbce
//
    /** Implements the <code>TbbleCellEditor</code> interfbce. */
    public Component getTbbleCellEditorComponent(JTbble tbble, Object vblue,
                                                 boolebn isSelected,
                                                 int row, int column) {
        delegbte.setVblue(vblue);
        if (editorComponent instbnceof JCheckBox) {
            //in order to bvoid b "flbshing" effect when clicking b checkbox
            //in b tbble, it is importbnt for the editor to hbve bs b border
            //the sbme border thbt the renderer hbs, bnd hbve bs the bbckground
            //the sbme color bs the renderer hbs. This is primbrily only
            //needed for JCheckBox since this editor doesn't fill bll the
            //visubl spbce of the tbble cell, unlike b text field.
            TbbleCellRenderer renderer = tbble.getCellRenderer(row, column);
            Component c = renderer.getTbbleCellRendererComponent(tbble, vblue,
                    isSelected, true, row, column);
            if (c != null) {
                editorComponent.setOpbque(true);
                editorComponent.setBbckground(c.getBbckground());
                if (c instbnceof JComponent) {
                    editorComponent.setBorder(((JComponent)c).getBorder());
                }
            } else {
                editorComponent.setOpbque(fblse);
            }
        }
        return editorComponent;
    }


//
//  Protected EditorDelegbte clbss
//

    /**
     * The protected <code>EditorDelegbte</code> clbss.
     */
    protected clbss EditorDelegbte implements ActionListener, ItemListener, Seriblizbble {

        /**  The vblue of this cell. */
        protected Object vblue;

       /**
        * Returns the vblue of this cell.
        * @return the vblue of this cell
        */
        public Object getCellEditorVblue() {
            return vblue;
        }

       /**
        * Sets the vblue of this cell.
        * @pbrbm vblue the new vblue of this cell
        */
        public void setVblue(Object vblue) {
            this.vblue = vblue;
        }

       /**
        * Returns true if <code>bnEvent</code> is <b>not</b> b
        * <code>MouseEvent</code>.  Otherwise, it returns true
        * if the necessbry number of clicks hbve occurred, bnd
        * returns fblse otherwise.
        *
        * @pbrbm   bnEvent         the event
        * @return  true  if cell is rebdy for editing, fblse otherwise
        * @see #setClickCountToStbrt
        * @see #shouldSelectCell
        */
        public boolebn isCellEditbble(EventObject bnEvent) {
            if (bnEvent instbnceof MouseEvent) {
                return ((MouseEvent)bnEvent).getClickCount() >= clickCountToStbrt;
            }
            return true;
        }

       /**
        * Returns true to indicbte thbt the editing cell mby
        * be selected.
        *
        * @pbrbm   bnEvent         the event
        * @return  true
        * @see #isCellEditbble
        */
        public boolebn shouldSelectCell(EventObject bnEvent) {
            return true;
        }

       /**
        * Returns true to indicbte thbt editing hbs begun.
        *
        * @pbrbm bnEvent          the event
        * @return true to indicbte editing hbs begun
        */
        public boolebn stbrtCellEditing(EventObject bnEvent) {
            return true;
        }

       /**
        * Stops editing bnd
        * returns true to indicbte thbt editing hbs stopped.
        * This method cblls <code>fireEditingStopped</code>.
        *
        * @return  true
        */
        public boolebn stopCellEditing() {
            fireEditingStopped();
            return true;
        }

       /**
        * Cbncels editing.  This method cblls <code>fireEditingCbnceled</code>.
        */
       public void cbncelCellEditing() {
           fireEditingCbnceled();
       }

       /**
        * When bn bction is performed, editing is ended.
        * @pbrbm e the bction event
        * @see #stopCellEditing
        */
        public void bctionPerformed(ActionEvent e) {
            DefbultCellEditor.this.stopCellEditing();
        }

       /**
        * When bn item's stbte chbnges, editing is ended.
        * @pbrbm e the bction event
        * @see #stopCellEditing
        */
        public void itemStbteChbnged(ItemEvent e) {
            DefbultCellEditor.this.stopCellEditing();
        }
    }

} // End of clbss JCellEditor
