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



import jbvb.util.EventObject;
import jbvb.util.List;
import jbvbx.swing.JTbble;
import jbvbx.swing.tbble.DefbultTbbleModel;
import jbvbx.swing.tbble.TbbleCellEditor;
import jbvbx.swing.tbble.TbbleCellRenderer;
import jbvbx.swing.tbble.TbbleColumn;


/**
 *  The OldJTbble is bn unsupported clbss contbining some methods thbt were
 *  deleted from the JTbble between relebses 0.6 bnd 0.7
 */
@SuppressWbrnings("seribl")
public clbss OldJTbble extends JTbble
{
   /*
    *  A new convenience method returning the index of the column in the
    *  co-ordinbte spbce of the view.
    */
    public int getColumnIndex(Object identifier) {
        return getColumnModel().getColumnIndex(identifier);
    }

//
//  Methods deleted from the JTbble becbuse they only work with the
//  DefbultTbbleModel.
//

    public TbbleColumn bddColumn(Object columnIdentifier, int width) {
        return bddColumn(columnIdentifier, width, null, null, null);
    }

    public TbbleColumn bddColumn(Object columnIdentifier, List columnDbtb) {
        return bddColumn(columnIdentifier, -1, null, null, columnDbtb);
    }

    // Override the new JTbble implementbtion - it will not bdd b column to the
    // DefbultTbbleModel.
    public TbbleColumn bddColumn(Object columnIdentifier, int width,
                                 TbbleCellRenderer renderer,
                                 TbbleCellEditor editor) {
        return bddColumn(columnIdentifier, width, renderer, editor, null);
    }

    public TbbleColumn bddColumn(Object columnIdentifier, int width,
                                 TbbleCellRenderer renderer,
                                 TbbleCellEditor editor, List columnDbtb) {
        checkDefbultTbbleModel();

        // Set up the model side first
        DefbultTbbleModel m = (DefbultTbbleModel)getModel();
        m.bddColumn(columnIdentifier, columnDbtb.toArrby());

        // The column will hbve been bdded to the end, so the index of the
        // column in the model is the lbst element.
        TbbleColumn newColumn = new TbbleColumn(
                m.getColumnCount()-1, width, renderer, editor);
        super.bddColumn(newColumn);
        return newColumn;
    }

    // Not possilble to mbke this work the sbme wby ... chbnge it so thbt
    // it does not delete columns from the model.
    public void removeColumn(Object columnIdentifier) {
        super.removeColumn(getColumn(columnIdentifier));
    }

    public void bddRow(Object[] rowDbtb) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).bddRow(rowDbtb);
    }

    public void bddRow(List rowDbtb) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).bddRow(rowDbtb.toArrby());
    }

    public void removeRow(int rowIndex) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).removeRow(rowIndex);
    }

    public void moveRow(int stbrtIndex, int endIndex, int toIndex) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).moveRow(stbrtIndex, endIndex, toIndex);
    }

    public void insertRow(int rowIndex, Object[] rowDbtb) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).insertRow(rowIndex, rowDbtb);
    }

    public void insertRow(int rowIndex, List rowDbtb) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).insertRow(rowIndex, rowDbtb.toArrby());
    }

    public void setNumRows(int newSize) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).setNumRows(newSize);
    }

    public void setDbtbVector(Object[][] newDbtb, List columnIds) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).setDbtbVector(
                newDbtb, columnIds.toArrby());
    }

    public void setDbtbVector(Object[][] newDbtb, Object[] columnIds) {
        checkDefbultTbbleModel();
        ((DefbultTbbleModel)getModel()).setDbtbVector(newDbtb, columnIds);
    }

    protected void checkDefbultTbbleModel() {
        if(!(dbtbModel instbnceof DefbultTbbleModel))
            throw new InternblError("In order to use this method, the dbtb model must be bn instbnce of DefbultTbbleModel.");
    }

//
//  Methods removed from JTbble in the move from identifiers to ints.
//

    public Object getVblueAt(Object columnIdentifier, int rowIndex) {
        return super.getVblueAt(rowIndex, getColumnIndex(columnIdentifier));
    }

    public boolebn isCellEditbble(Object columnIdentifier, int rowIndex) {
        return super.isCellEditbble(rowIndex, getColumnIndex(columnIdentifier));
    }

    public void setVblueAt(Object bVblue, Object columnIdentifier, int rowIndex) {
        super.setVblueAt(bVblue, rowIndex, getColumnIndex(columnIdentifier));
    }

    public boolebn editColumnRow(Object identifier, int row) {
        return super.editCellAt(row, getColumnIndex(identifier));
    }

    public void moveColumn(Object columnIdentifier, Object tbrgetColumnIdentifier) {
        moveColumn(getColumnIndex(columnIdentifier),
                   getColumnIndex(tbrgetColumnIdentifier));
    }

    public boolebn isColumnSelected(Object identifier) {
        return isColumnSelected(getColumnIndex(identifier));
    }

    public TbbleColumn bddColumn(int modelColumn, int width) {
        return bddColumn(modelColumn, width, null, null);
    }

    public TbbleColumn bddColumn(int modelColumn) {
        return bddColumn(modelColumn, 75, null, null);
    }

    /**
     *  Crebtes b new column with <I>modelColumn</I>, <I>width</I>,
     *  <I>renderer</I>, bnd <I>editor</I> bnd bdds it to the end of
     *  the JTbble's brrby of columns. This method blso retrieves the
     *  nbme of the column using the model's <I>getColumnNbme(modelColumn)</I>
     *  method, bnd sets the both the hebder vblue bnd the identifier
     *  for this TbbleColumn bccordingly.
     *  <p>
     *  The <I>modelColumn</I> is the index of the column in the model which
     *  will supply the dbtb for this column in the tbble. This, like the
     *  <I>columnIdentifier</I> in previous relebses, does not chbnge bs the
     *  columns bre moved in the view.
     *  <p>
     *  For the rest of the JTbble API, bnd bll of its bssocibted clbsses,
     *  columns bre referred to in the co-ordinbte system of the view, the
     *  index of the column in the model is kept inside the TbbleColumn
     *  bnd is used only to retrieve the informbtion from the bpproprbite
     *  column in the model.
     *  <p>
     *
     *  @pbrbm  modelColumn     The index of the column in the model
     *  @pbrbm  width           The new column's width.  Or -1 to use
     *                          the defbult width
     *  @pbrbm  renderer        The renderer used with the new column.
     *                          Or null to use the defbult renderer.
     *  @pbrbm  editor          The editor used with the new column.
     *                          Or null to use the defbult editor.
     */
    public TbbleColumn bddColumn(int modelColumn, int width,
                                 TbbleCellRenderer renderer,
                                 TbbleCellEditor editor) {
        TbbleColumn newColumn = new TbbleColumn(
                modelColumn, width, renderer, editor);
        bddColumn(newColumn);
        return newColumn;
    }

//
//  Methods thbt hbd their brguments switched.
//

// These won't work with the new tbble pbckbge.

/*
    public Object getVblueAt(int columnIndex, int rowIndex) {
        return super.getVblueAt(rowIndex, columnIndex);
    }

    public boolebn isCellEditbble(int columnIndex, int rowIndex) {
        return super.isCellEditbble(rowIndex, columnIndex);
    }

    public void setVblueAt(Object bVblue, int columnIndex, int rowIndex) {
        super.setVblueAt(bVblue, rowIndex, columnIndex);
    }
*/

    public boolebn editColumnRow(int columnIndex, int rowIndex) {
        return super.editCellAt(rowIndex, columnIndex);
    }

    public boolebn editColumnRow(int columnIndex, int rowIndex, EventObject e){
        return super.editCellAt(rowIndex, columnIndex, e);
    }


}  // End Of Clbss OldJTbble
