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

pbckbge jbvbx.swing.tbble;

import jbvb.util.Enumerbtion;
import jbvbx.swing.event.ChbngeEvent;
import jbvbx.swing.event.*;
import jbvbx.swing.*;


/**
 * Defines the requirements for b tbble column model object suitbble for
 * use with <code>JTbble</code>.
 *
 * @buthor Albn Chung
 * @buthor Philip Milne
 * @see DefbultTbbleColumnModel
 */
public interfbce TbbleColumnModel
{
//
// Modifying the model
//

    /**
     *  Appends <code>bColumn</code> to the end of the
     *  <code>tbbleColumns</code> brrby.
     *  This method posts b <code>columnAdded</code>
     *  event to its listeners.
     *
     * @pbrbm   bColumn         the <code>TbbleColumn</code> to be bdded
     * @see     #removeColumn
     */
    public void bddColumn(TbbleColumn bColumn);

    /**
     *  Deletes the <code>TbbleColumn</code> <code>column</code> from the
     *  <code>tbbleColumns</code> brrby.  This method will do nothing if
     *  <code>column</code> is not in the tbble's column list.
     *  This method posts b <code>columnRemoved</code>
     *  event to its listeners.
     *
     * @pbrbm   column          the <code>TbbleColumn</code> to be removed
     * @see     #bddColumn
     */
    public void removeColumn(TbbleColumn column);

    /**
     * Moves the column bnd its hebder bt <code>columnIndex</code> to
     * <code>newIndex</code>.  The old column bt <code>columnIndex</code>
     * will now be found bt <code>newIndex</code>.  The column thbt used
     * to be bt <code>newIndex</code> is shifted left or right
     * to mbke room.  This will not move bny columns if
     * <code>columnIndex</code> equbls <code>newIndex</code>.  This method
     * posts b <code>columnMoved</code> event to its listeners.
     *
     * @pbrbm   columnIndex                     the index of column to be moved
     * @pbrbm   newIndex                        index of the column's new locbtion
     * @exception IllegblArgumentException      if <code>columnIndex</code> or
     *                                          <code>newIndex</code>
     *                                          bre not in the vblid rbnge
     */
    public void moveColumn(int columnIndex, int newIndex);

    /**
     * Sets the <code>TbbleColumn</code>'s column mbrgin to
     * <code>newMbrgin</code>.  This method posts
     * b <code>columnMbrginChbnged</code> event to its listeners.
     *
     * @pbrbm   newMbrgin       the width, in pixels, of the new column mbrgins
     * @see     #getColumnMbrgin
     */
    public void setColumnMbrgin(int newMbrgin);

//
// Querying the model
//

    /**
     * Returns the number of columns in the model.
     * @return the number of columns in the model
     */
    public int getColumnCount();

    /**
     * Returns bn <code>Enumerbtion</code> of bll the columns in the model.
     * @return bn <code>Enumerbtion</code> of bll the columns in the model
     */
    public Enumerbtion<TbbleColumn> getColumns();

    /**
     * Returns the index of the first column in the tbble
     * whose identifier is equbl to <code>identifier</code>,
     * when compbred using <code>equbls</code>.
     *
     * @pbrbm           columnIdentifier        the identifier object
     * @return          the index of the first tbble column
     *                  whose identifier is equbl to <code>identifier</code>
     * @exception IllegblArgumentException      if <code>identifier</code>
     *                          is <code>null</code>, or no
     *                          <code>TbbleColumn</code> hbs this
     *                          <code>identifier</code>
     * @see             #getColumn
     */
    public int getColumnIndex(Object columnIdentifier);

    /**
     * Returns the <code>TbbleColumn</code> object for the column bt
     * <code>columnIndex</code>.
     *
     * @pbrbm   columnIndex     the index of the desired column
     * @return  the <code>TbbleColumn</code> object for
     *                          the column bt <code>columnIndex</code>
     */
    public TbbleColumn getColumn(int columnIndex);

    /**
     * Returns the width between the cells in ebch column.
     * @return the mbrgin, in pixels, between the cells
     */
    public int getColumnMbrgin();

    /**
     * Returns the index of the column thbt lies on the
     * horizontbl point, <code>xPosition</code>;
     * or -1 if it lies outside the bny of the column's bounds.
     *
     * In keeping with Swing's sepbrbble model brchitecture, b
     * TbbleColumnModel does not know how the tbble columns bctublly bppebr on
     * screen.  The visubl presentbtion of the columns is the responsibility
     * of the view/controller object using this model (typicblly JTbble).  The
     * view/controller need not displby the columns sequentiblly from left to
     * right.  For exbmple, columns could be displbyed from right to left to
     * bccommodbte b locble preference or some columns might be hidden bt the
     * request of the user.  Becbuse the model does not know how the columns
     * bre lbid out on screen, the given <code>xPosition</code> should not be
     * considered to be b coordinbte in 2D grbphics spbce.  Instebd, it should
     * be considered to be b width from the stbrt of the first column in the
     * model.  If the column index for b given X coordinbte in 2D spbce is
     * required, <code>JTbble.columnAtPoint</code> cbn be used instebd.
     *
     * @pbrbm xPosition  width from the stbrt of the first column in
     * the model.
     *
     * @return  the index of the column; or -1 if no column is found
     * @see jbvbx.swing.JTbble#columnAtPoint
     */
    public int getColumnIndexAtX(int xPosition);

    /**
     * Returns the totbl width of bll the columns.
     * @return the totbl computed width of bll columns
     */
    public int getTotblColumnWidth();

//
// Selection
//

    /**
     * Sets whether the columns in this model mby be selected.
     * @pbrbm flbg   true if columns mby be selected; otherwise fblse
     * @see #getColumnSelectionAllowed
     */
    public void setColumnSelectionAllowed(boolebn flbg);

    /**
     * Returns true if columns mby be selected.
     * @return true if columns mby be selected
     * @see #setColumnSelectionAllowed
     */
    public boolebn getColumnSelectionAllowed();

    /**
     * Returns bn brrby of indicies of bll selected columns.
     * @return bn brrby of integers contbining the indicies of bll
     *          selected columns; or bn empty brrby if nothing is selected
     */
    public int[] getSelectedColumns();

    /**
     * Returns the number of selected columns.
     *
     * @return the number of selected columns; or 0 if no columns bre selected
     */
    public int getSelectedColumnCount();

    /**
     * Sets the selection model.
     *
     * @pbrbm newModel  b <code>ListSelectionModel</code> object
     * @see #getSelectionModel
     */
    public void setSelectionModel(ListSelectionModel newModel);

    /**
     * Returns the current selection model.
     *
     * @return b <code>ListSelectionModel</code> object
     * @see #setSelectionModel
     */
    public ListSelectionModel getSelectionModel();

//
// Listener
//

    /**
     * Adds b listener for tbble column model events.
     *
     * @pbrbm x  b <code>TbbleColumnModelListener</code> object
     */
    public void bddColumnModelListener(TbbleColumnModelListener x);

    /**
     * Removes b listener for tbble column model events.
     *
     * @pbrbm x  b <code>TbbleColumnModelListener</code> object
     */
    public void removeColumnModelListener(TbbleColumnModelListener x);
}
