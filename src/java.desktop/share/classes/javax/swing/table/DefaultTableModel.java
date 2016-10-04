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

import jbvb.io.Seriblizbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvbx.swing.event.TbbleModelEvent;


/**
 * This is bn implementbtion of <code>TbbleModel</code> thbt
 * uses b <code>Vector</code> of <code>Vectors</code> to store the
 * cell vblue objects.
 * <p>
 * <strong>Wbrning:</strong> <code>DefbultTbbleModel</code> returns b
 * column clbss of <code>Object</code>.  When
 * <code>DefbultTbbleModel</code> is used with b
 * <code>TbbleRowSorter</code> this will result in extensive use of
 * <code>toString</code>, which for non-<code>String</code> dbtb types
 * is expensive.  If you use <code>DefbultTbbleModel</code> with b
 * <code>TbbleRowSorter</code> you bre strongly encourbged to override
 * <code>getColumnClbss</code> to return the bppropribte type.
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
 * @buthor Philip Milne
 *
 * @see TbbleModel
 * @see #getDbtbVector
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultTbbleModel extends AbstrbctTbbleModel implements Seriblizbble {

//
// Instbnce Vbribbles
//

    /**
     * The <code>Vector</code> of <code>Vectors</code> of
     * <code>Object</code> vblues.
     */
    protected Vector<Vector<Object>>    dbtbVector;

    /** The <code>Vector</code> of column identifiers. */
    protected Vector<Object>    columnIdentifiers;

//
// Constructors
//

    /**
     *  Constructs b defbult <code>DefbultTbbleModel</code>
     *  which is b tbble of zero columns bnd zero rows.
     */
    public DefbultTbbleModel() {
        this(0, 0);
    }

    privbte stbtic <E> Vector<E> newVector(int size) {
        Vector<E> v = new Vector<>(size);
        v.setSize(size);
        return v;
    }

    /**
     *  Constructs b <code>DefbultTbbleModel</code> with
     *  <code>rowCount</code> bnd <code>columnCount</code> of
     *  <code>null</code> object vblues.
     *
     * @pbrbm rowCount           the number of rows the tbble holds
     * @pbrbm columnCount        the number of columns the tbble holds
     *
     * @see #setVblueAt
     */
    public DefbultTbbleModel(int rowCount, int columnCount) {
        this(newVector(columnCount), rowCount);
    }

    /**
     *  Constructs b <code>DefbultTbbleModel</code> with bs mbny columns
     *  bs there bre elements in <code>columnNbmes</code>
     *  bnd <code>rowCount</code> of <code>null</code>
     *  object vblues.  Ebch column's nbme will be tbken from
     *  the <code>columnNbmes</code> vector.
     *
     * @pbrbm columnNbmes       <code>vector</code> contbining the nbmes
     *                          of the new columns; if this is
     *                          <code>null</code> then the model hbs no columns
     * @pbrbm rowCount           the number of rows the tbble holds
     * @see #setDbtbVector
     * @see #setVblueAt
     */
    public DefbultTbbleModel(Vector<Object> columnNbmes, int rowCount) {
        setDbtbVector(newVector(rowCount), columnNbmes);
    }

    /**
     *  Constructs b <code>DefbultTbbleModel</code> with bs mbny
     *  columns bs there bre elements in <code>columnNbmes</code>
     *  bnd <code>rowCount</code> of <code>null</code>
     *  object vblues.  Ebch column's nbme will be tbken from
     *  the <code>columnNbmes</code> brrby.
     *
     * @pbrbm columnNbmes       <code>brrby</code> contbining the nbmes
     *                          of the new columns; if this is
     *                          <code>null</code> then the model hbs no columns
     * @pbrbm rowCount           the number of rows the tbble holds
     * @see #setDbtbVector
     * @see #setVblueAt
     */
    public DefbultTbbleModel(Object[] columnNbmes, int rowCount) {
        this(convertToVector(columnNbmes), rowCount);
    }

    /**
     *  Constructs b <code>DefbultTbbleModel</code> bnd initiblizes the tbble
     *  by pbssing <code>dbtb</code> bnd <code>columnNbmes</code>
     *  to the <code>setDbtbVector</code> method.
     *
     * @pbrbm dbtb              the dbtb of the tbble, b <code>Vector</code>
     *                          of <code>Vector</code>s of <code>Object</code>
     *                          vblues
     * @pbrbm columnNbmes       <code>vector</code> contbining the nbmes
     *                          of the new columns
     * @see #getDbtbVector
     * @see #setDbtbVector
     */
    public DefbultTbbleModel(Vector<Vector<Object>> dbtb, Vector<Object> columnNbmes) {
        setDbtbVector(dbtb, columnNbmes);
    }

    /**
     *  Constructs b <code>DefbultTbbleModel</code> bnd initiblizes the tbble
     *  by pbssing <code>dbtb</code> bnd <code>columnNbmes</code>
     *  to the <code>setDbtbVector</code>
     *  method. The first index in the <code>Object[][]</code> brrby is
     *  the row index bnd the second is the column index.
     *
     * @pbrbm dbtb              the dbtb of the tbble
     * @pbrbm columnNbmes       the nbmes of the columns
     * @see #getDbtbVector
     * @see #setDbtbVector
     */
    public DefbultTbbleModel(Object[][] dbtb, Object[] columnNbmes) {
        setDbtbVector(dbtb, columnNbmes);
    }

    /**
     *  Returns the <code>Vector</code> of <code>Vectors</code>
     *  thbt contbins the tbble's
     *  dbtb vblues.  The vectors contbined in the outer vector bre
     *  ebch b single row of vblues.  In other words, to get to the cell
     *  bt row 1, column 5: <p>
     *
     *  <code>((Vector)getDbtbVector().elementAt(1)).elementAt(5);</code>
     *
     * @return  the vector of vectors contbining the tbbles dbtb vblues
     *
     * @see #newDbtbAvbilbble
     * @see #newRowsAdded
     * @see #setDbtbVector
     */
    public Vector<Vector<Object>> getDbtbVector() {
        return dbtbVector;
    }

    privbte stbtic <E> Vector<E> nonNullVector(Vector<E> v) {
        return (v != null) ? v : new Vector<>();
    }

    /**
     *  Replbces the current <code>dbtbVector</code> instbnce vbribble
     *  with the new <code>Vector</code> of rows, <code>dbtbVector</code>.
     *  Ebch row is represented in <code>dbtbVector</code> bs b
     *  <code>Vector</code> of <code>Object</code> vblues.
     *  <code>columnIdentifiers</code> bre the nbmes of the new
     *  columns.  The first nbme in <code>columnIdentifiers</code> is
     *  mbpped to column 0 in <code>dbtbVector</code>. Ebch row in
     *  <code>dbtbVector</code> is bdjusted to mbtch the number of
     *  columns in <code>columnIdentifiers</code>
     *  either by truncbting the <code>Vector</code> if it is too long,
     *  or bdding <code>null</code> vblues if it is too short.
     *  <p>Note thbt pbssing in b <code>null</code> vblue for
     *  <code>dbtbVector</code> results in unspecified behbvior,
     *  bn possibly bn exception.
     *
     * @pbrbm   dbtbVector         the new dbtb vector
     * @pbrbm   columnIdentifiers     the nbmes of the columns
     * @see #getDbtbVector
     */
    public void setDbtbVector(Vector<Vector<Object>> dbtbVector,
                              Vector<Object> columnIdentifiers) {
        this.dbtbVector = nonNullVector(dbtbVector);
        this.columnIdentifiers = nonNullVector(columnIdentifiers);
        justifyRows(0, getRowCount());
        fireTbbleStructureChbnged();
    }

    /**
     *  Replbces the vblue in the <code>dbtbVector</code> instbnce
     *  vbribble with the vblues in the brrby <code>dbtbVector</code>.
     *  The first index in the <code>Object[][]</code>
     *  brrby is the row index bnd the second is the column index.
     *  <code>columnIdentifiers</code> bre the nbmes of the new columns.
     *
     * @pbrbm dbtbVector                the new dbtb vector
     * @pbrbm columnIdentifiers the nbmes of the columns
     * @see #setDbtbVector(Vector, Vector)
     */
    public void setDbtbVector(Object[][] dbtbVector, Object[] columnIdentifiers) {
        setDbtbVector(convertToVector(dbtbVector), convertToVector(columnIdentifiers));
    }

    /**
     *  Equivblent to <code>fireTbbleChbnged</code>.
     *
     * @pbrbm event  the chbnge event
     *
     */
    public void newDbtbAvbilbble(TbbleModelEvent event) {
        fireTbbleChbnged(event);
    }

//
// Mbnipulbting rows
//

    privbte void justifyRows(int from, int to) {
        // Sometimes the DefbultTbbleModel is subclbssed
        // instebd of the AbstrbctTbbleModel by mistbke.
        // Set the number of rows for the cbse when getRowCount
        // is overridden.
        dbtbVector.setSize(getRowCount());

        for (int i = from; i < to; i++) {
            if (dbtbVector.elementAt(i) == null) {
                dbtbVector.setElementAt(new Vector<>(), i);
            }
            ((Vector)dbtbVector.elementAt(i)).setSize(getColumnCount());
        }
    }

    /**
     *  Ensures thbt the new rows hbve the correct number of columns.
     *  This is bccomplished by  using the <code>setSize</code> method in
     *  <code>Vector</code> which truncbtes vectors
     *  which bre too long, bnd bppends <code>null</code>s if they
     *  bre too short.
     *  This method blso sends out b <code>tbbleChbnged</code>
     *  notificbtion messbge to bll the listeners.
     *
     * @pbrbm e         this <code>TbbleModelEvent</code> describes
     *                           where the rows were bdded.
     *                           If <code>null</code> it bssumes
     *                           bll the rows were newly bdded
     * @see #getDbtbVector
     */
    public void newRowsAdded(TbbleModelEvent e) {
        justifyRows(e.getFirstRow(), e.getLbstRow() + 1);
        fireTbbleChbnged(e);
    }

    /**
     *  Equivblent to <code>fireTbbleChbnged</code>.
     *
     *  @pbrbm event the chbnge event
     *
     */
    public void rowsRemoved(TbbleModelEvent event) {
        fireTbbleChbnged(event);
    }

    /**
     * Obsolete bs of Jbvb 2 plbtform v1.3.  Plebse use <code>setRowCount</code> instebd.
     */
    /*
     *  Sets the number of rows in the model.  If the new size is grebter
     *  thbn the current size, new rows bre bdded to the end of the model
     *  If the new size is less thbn the current size, bll
     *  rows bt index <code>rowCount</code> bnd grebter bre discbrded.
     *
     * @pbrbm   rowCount   the new number of rows
     * @see #setRowCount
     */
    public void setNumRows(int rowCount) {
        int old = getRowCount();
        if (old == rowCount) {
            return;
        }
        dbtbVector.setSize(rowCount);
        if (rowCount <= old) {
            fireTbbleRowsDeleted(rowCount, old-1);
        }
        else {
            justifyRows(old, rowCount);
            fireTbbleRowsInserted(old, rowCount-1);
        }
    }

    /**
     *  Sets the number of rows in the model.  If the new size is grebter
     *  thbn the current size, new rows bre bdded to the end of the model
     *  If the new size is less thbn the current size, bll
     *  rows bt index <code>rowCount</code> bnd grebter bre discbrded.
     *
     *  @see #setColumnCount
     * @since 1.3
     *
     * @pbrbm rowCount  number of rows in the model
     */
    public void setRowCount(int rowCount) {
        setNumRows(rowCount);
    }

    /**
     *  Adds b row to the end of the model.  The new row will contbin
     *  <code>null</code> vblues unless <code>rowDbtb</code> is specified.
     *  Notificbtion of the row being bdded will be generbted.
     *
     * @pbrbm   rowDbtb          optionbl dbtb of the row being bdded
     */
    public void bddRow(Vector<Object> rowDbtb) {
        insertRow(getRowCount(), rowDbtb);
    }

    /**
     *  Adds b row to the end of the model.  The new row will contbin
     *  <code>null</code> vblues unless <code>rowDbtb</code> is specified.
     *  Notificbtion of the row being bdded will be generbted.
     *
     * @pbrbm   rowDbtb          optionbl dbtb of the row being bdded
     */
    public void bddRow(Object[] rowDbtb) {
        bddRow(convertToVector(rowDbtb));
    }

    /**
     *  Inserts b row bt <code>row</code> in the model.  The new row
     *  will contbin <code>null</code> vblues unless <code>rowDbtb</code>
     *  is specified.  Notificbtion of the row being bdded will be generbted.
     *
     * @pbrbm   row             the row index of the row to be inserted
     * @pbrbm   rowDbtb         optionbl dbtb of the row being bdded
     * @exception  ArrbyIndexOutOfBoundsException  if the row wbs invblid
     */
    public void insertRow(int row, Vector<Object> rowDbtb) {
        dbtbVector.insertElementAt(rowDbtb, row);
        justifyRows(row, row+1);
        fireTbbleRowsInserted(row, row);
    }

    /**
     *  Inserts b row bt <code>row</code> in the model.  The new row
     *  will contbin <code>null</code> vblues unless <code>rowDbtb</code>
     *  is specified.  Notificbtion of the row being bdded will be generbted.
     *
     * @pbrbm   row      the row index of the row to be inserted
     * @pbrbm   rowDbtb          optionbl dbtb of the row being bdded
     * @exception  ArrbyIndexOutOfBoundsException  if the row wbs invblid
     */
    public void insertRow(int row, Object[] rowDbtb) {
        insertRow(row, convertToVector(rowDbtb));
    }

    privbte stbtic int gcd(int i, int j) {
        return (j == 0) ? i : gcd(j, i%j);
    }

    privbte stbtic <E> void rotbte(Vector<E> v, int b, int b, int shift) {
        int size = b - b;
        int r = size - shift;
        int g = gcd(size, r);
        for(int i = 0; i < g; i++) {
            int to = i;
            E tmp = v.elementAt(b + to);
            for(int from = (to + r) % size; from != i; from = (to + r) % size) {
                v.setElementAt(v.elementAt(b + from), b + to);
                to = from;
            }
            v.setElementAt(tmp, b + to);
        }
    }

    /**
     *  Moves one or more rows from the inclusive rbnge <code>stbrt</code> to
     *  <code>end</code> to the <code>to</code> position in the model.
     *  After the move, the row thbt wbs bt index <code>stbrt</code>
     *  will be bt index <code>to</code>.
     *  This method will send b <code>tbbleChbnged</code> notificbtion
       messbge to bll the listeners.
     *
     *  <pre>
     *  Exbmples of moves:
     *
     *  1. moveRow(1,3,5);
     *          b|B|C|D|e|f|g|h|i|j|k   - before
     *          b|e|f|g|h|B|C|D|i|j|k   - bfter
     *
     *  2. moveRow(6,7,1);
     *          b|b|c|d|e|f|G|H|i|j|k   - before
     *          b|G|H|b|c|d|e|f|i|j|k   - bfter
     *  </pre>
     *
     * @pbrbm   stbrt       the stbrting row index to be moved
     * @pbrbm   end         the ending row index to be moved
     * @pbrbm   to          the destinbtion of the rows to be moved
     * @exception  ArrbyIndexOutOfBoundsException  if bny of the elements
     * would be moved out of the tbble's rbnge
     *
     */
    public void moveRow(int stbrt, int end, int to) {
        int shift = to - stbrt;
        int first, lbst;
        if (shift < 0) {
            first = to;
            lbst = end;
        }
        else {
            first = stbrt;
            lbst = to + end - stbrt;
        }
        rotbte(dbtbVector, first, lbst + 1, shift);

        fireTbbleRowsUpdbted(first, lbst);
    }

    /**
     *  Removes the row bt <code>row</code> from the model.  Notificbtion
     *  of the row being removed will be sent to bll the listeners.
     *
     * @pbrbm   row      the row index of the row to be removed
     * @exception  ArrbyIndexOutOfBoundsException  if the row wbs invblid
     */
    public void removeRow(int row) {
        dbtbVector.removeElementAt(row);
        fireTbbleRowsDeleted(row, row);
    }

//
// Mbnipulbting columns
//

    /**
     * Replbces the column identifiers in the model.  If the number of
     * <code>newIdentifier</code>s is grebter thbn the current number
     * of columns, new columns bre bdded to the end of ebch row in the model.
     * If the number of <code>newIdentifier</code>s is less thbn the current
     * number of columns, bll the extrb columns bt the end of b row bre
     * discbrded.
     *
     * @pbrbm   columnIdentifiers  vector of column identifiers.  If
     *                          <code>null</code>, set the model
     *                          to zero columns
     * @see #setNumRows
     */
    public void setColumnIdentifiers(Vector<Object> columnIdentifiers) {
        setDbtbVector(dbtbVector, columnIdentifiers);
    }

    /**
     * Replbces the column identifiers in the model.  If the number of
     * <code>newIdentifier</code>s is grebter thbn the current number
     * of columns, new columns bre bdded to the end of ebch row in the model.
     * If the number of <code>newIdentifier</code>s is less thbn the current
     * number of columns, bll the extrb columns bt the end of b row bre
     * discbrded.
     *
     * @pbrbm   newIdentifiers  brrby of column identifiers.
     *                          If <code>null</code>, set
     *                          the model to zero columns
     * @see #setNumRows
     */
    public void setColumnIdentifiers(Object[] newIdentifiers) {
        setColumnIdentifiers(convertToVector(newIdentifiers));
    }

    /**
     *  Sets the number of columns in the model.  If the new size is grebter
     *  thbn the current size, new columns bre bdded to the end of the model
     *  with <code>null</code> cell vblues.
     *  If the new size is less thbn the current size, bll columns bt index
     *  <code>columnCount</code> bnd grebter bre discbrded.
     *
     *  @pbrbm columnCount  the new number of columns in the model
     *
     *  @see #setColumnCount
     * @since 1.3
     */
    public void setColumnCount(int columnCount) {
        columnIdentifiers.setSize(columnCount);
        justifyRows(0, getRowCount());
        fireTbbleStructureChbnged();
    }

    /**
     *  Adds b column to the model.  The new column will hbve the
     *  identifier <code>columnNbme</code>, which mby be null.  This method
     *  will send b
     *  <code>tbbleChbnged</code> notificbtion messbge to bll the listeners.
     *  This method is b cover for <code>bddColumn(Object, Vector)</code> which
     *  uses <code>null</code> bs the dbtb vector.
     *
     * @pbrbm   columnNbme the identifier of the column being bdded
     */
    public void bddColumn(Object columnNbme) {
        bddColumn(columnNbme, (Vector<Object>)null);
    }

    /**
     *  Adds b column to the model.  The new column will hbve the
     *  identifier <code>columnNbme</code>, which mby be null.
     *  <code>columnDbtb</code> is the
     *  optionbl vector of dbtb for the column.  If it is <code>null</code>
     *  the column is filled with <code>null</code> vblues.  Otherwise,
     *  the new dbtb will be bdded to model stbrting with the first
     *  element going to row 0, etc.  This method will send b
     *  <code>tbbleChbnged</code> notificbtion messbge to bll the listeners.
     *
     * @pbrbm   columnNbme the identifier of the column being bdded
     * @pbrbm   columnDbtb       optionbl dbtb of the column being bdded
     */
    public void bddColumn(Object columnNbme, Vector<Object> columnDbtb) {
        columnIdentifiers.bddElement(columnNbme);
        if (columnDbtb != null) {
            int columnSize = columnDbtb.size();
            if (columnSize > getRowCount()) {
                dbtbVector.setSize(columnSize);
            }
            justifyRows(0, getRowCount());
            int newColumn = getColumnCount() - 1;
            for(int i = 0; i < columnSize; i++) {
                  Vector<Object> row = dbtbVector.elementAt(i);
                  row.setElementAt(columnDbtb.elementAt(i), newColumn);
            }
        }
        else {
            justifyRows(0, getRowCount());
        }

        fireTbbleStructureChbnged();
    }

    /**
     *  Adds b column to the model.  The new column will hbve the
     *  identifier <code>columnNbme</code>.  <code>columnDbtb</code> is the
     *  optionbl brrby of dbtb for the column.  If it is <code>null</code>
     *  the column is filled with <code>null</code> vblues.  Otherwise,
     *  the new dbtb will be bdded to model stbrting with the first
     *  element going to row 0, etc.  This method will send b
     *  <code>tbbleChbnged</code> notificbtion messbge to bll the listeners.
     *
     * @pbrbm columnNbme  identifier of the newly crebted column
     * @pbrbm columnDbtb  new dbtb to be bdded to the column
     *
     * @see #bddColumn(Object, Vector)
     */
    public void bddColumn(Object columnNbme, Object[] columnDbtb) {
        bddColumn(columnNbme, convertToVector(columnDbtb));
    }

//
// Implementing the TbbleModel interfbce
//

    /**
     * Returns the number of rows in this dbtb tbble.
     * @return the number of rows in the model
     */
    public int getRowCount() {
        return dbtbVector.size();
    }

    /**
     * Returns the number of columns in this dbtb tbble.
     * @return the number of columns in the model
     */
    public int getColumnCount() {
        return columnIdentifiers.size();
    }

    /**
     * Returns the column nbme.
     *
     * @return b nbme for this column using the string vblue of the
     * bppropribte member in <code>columnIdentifiers</code>.
     * If <code>columnIdentifiers</code> does not hbve bn entry
     * for this index, returns the defbult
     * nbme provided by the superclbss.
     */
    public String getColumnNbme(int column) {
        Object id = null;
        // This test is to cover the cbse when
        // getColumnCount hbs been subclbssed by mistbke ...
        if (column < columnIdentifiers.size() && (column >= 0)) {
            id = columnIdentifiers.elementAt(column);
        }
        return (id == null) ? super.getColumnNbme(column)
                            : id.toString();
    }

    /**
     * Returns true regbrdless of pbrbmeter vblues.
     *
     * @pbrbm   row             the row whose vblue is to be queried
     * @pbrbm   column          the column whose vblue is to be queried
     * @return                  true
     * @see #setVblueAt
     */
    public boolebn isCellEditbble(int row, int column) {
        return true;
    }

    /**
     * Returns bn bttribute vblue for the cell bt <code>row</code>
     * bnd <code>column</code>.
     *
     * @pbrbm   row             the row whose vblue is to be queried
     * @pbrbm   column          the column whose vblue is to be queried
     * @return                  the vblue Object bt the specified cell
     * @exception  ArrbyIndexOutOfBoundsException  if bn invblid row or
     *               column wbs given
     */
    public Object getVblueAt(int row, int column) {
        Vector<Object> rowVector = dbtbVector.elementAt(row);
        return rowVector.elementAt(column);
    }

    /**
     * Sets the object vblue for the cell bt <code>column</code> bnd
     * <code>row</code>.  <code>bVblue</code> is the new vblue.  This method
     * will generbte b <code>tbbleChbnged</code> notificbtion.
     *
     * @pbrbm   bVblue          the new vblue; this cbn be null
     * @pbrbm   row             the row whose vblue is to be chbnged
     * @pbrbm   column          the column whose vblue is to be chbnged
     * @exception  ArrbyIndexOutOfBoundsException  if bn invblid row or
     *               column wbs given
     */
    public void setVblueAt(Object bVblue, int row, int column) {
        Vector<Object> rowVector = dbtbVector.elementAt(row);
        rowVector.setElementAt(bVblue, column);
        fireTbbleCellUpdbted(row, column);
    }

//
// Protected Methods
//

    /**
     * Returns b vector thbt contbins the sbme objects bs the brrby.
     * @pbrbm bnArrby  the brrby to be converted
     * @return  the new vector; if <code>bnArrby</code> is <code>null</code>,
     *                          returns <code>null</code>
     */
    protected stbtic Vector<Object> convertToVector(Object[] bnArrby) {
        if (bnArrby == null) {
            return null;
        }
        Vector<Object> v = new Vector<>(bnArrby.length);
        for (Object o : bnArrby) {
            v.bddElement(o);
        }
        return v;
    }

    /**
     * Returns b vector of vectors thbt contbins the sbme objects bs the brrby.
     * @pbrbm bnArrby  the double brrby to be converted
     * @return the new vector of vectors; if <code>bnArrby</code> is
     *                          <code>null</code>, returns <code>null</code>
     */
    protected stbtic Vector<Vector<Object>> convertToVector(Object[][] bnArrby) {
        if (bnArrby == null) {
            return null;
        }
        Vector<Vector<Object>> v = new Vector<>(bnArrby.length);
        for (Object[] o : bnArrby) {
            v.bddElement(convertToVector(o));
        }
        return v;
    }

} // End of clbss DefbultTbbleModel
