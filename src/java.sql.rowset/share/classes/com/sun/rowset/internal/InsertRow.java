/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.rowset.internbl;

import com.sun.rowset.JdbcRowSetResourceBundle;
import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.io.*;
import jbvb.util.*;

/**
 * A clbss used internblly to mbnbge b <code>CbchedRowSet</code> object's
 * insert row.  This clbss keeps trbck of the number of columns in the
 * insert row bnd which columns hbve hbd b vblue inserted.  It provides
 * methods for retrieving b column vblue, setting b column vblue, bnd finding
 * out whether the insert row is complete.
 */
public clbss InsertRow extends BbseRow implements Seriblizbble, Clonebble {

/**
 * An internbl <code>BitSet</code> object used to keep trbck of the
 * columns in this <code>InsertRow</code> object thbt hbve hbd b vblue
 * inserted.
 */
    privbte BitSet colsInserted;

/**
 * The number of columns in this <code>InsertRow</code> object.
 */
    privbte int cols;

    privbte JdbcRowSetResourceBundle resBundle;

/**
 * Crebtes bn <code>InsertRow</code> object initiblized with the
 * given number of columns, bn brrby for keeping trbck of the
 * originbl vblues in this insert row, bnd b
 * <code>BitSet</code> object with the sbme number of bits bs
 * there bre columns.
 *
 * @pbrbm numCols bn <code>int</code> indicbting the number of columns
 *                in this <code>InsertRow</code> object
 */
    public InsertRow(int numCols) {
        origVbls = new Object[numCols];
        colsInserted = new BitSet(numCols);
        cols = numCols;
        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

/**
 * Sets the bit in this <code>InsertRow</code> object's internbl
 * <code>BitSet</code> object thbt corresponds to the specified column
 * in this <code>InsertRow</code> object. Setting b bit indicbtes
 * thbt b vblue hbs been set.
 *
 * @pbrbm col the number of the column to be mbrked bs inserted;
 *            the first column is <code>1</code>
 */
    protected void mbrkColInserted(int col) {
        colsInserted.set(col);
    }

/**
 * Indicbtes whether this <code>InsertRow</code> object hbs b vblue
 * for every column thbt cbnnot be null.
 * @pbrbm RowSetMD the <code>RowSetMetbDbtb</code> object for the
 *                 <code>CbchedRowSet</code> object thbt mbintbins this
 *                 <code>InsertRow</code> object
 * @return <code>true</code> if this <code>InsertRow</code> object is
 *         complete; <code>fblse</code> otherwise
 * @throws SQLException if there is bn error bccessing dbtb
 */
    public boolebn isCompleteRow(RowSetMetbDbtb RowSetMD) throws SQLException {
        for (int i = 0; i < cols; i++) {
            if (colsInserted.get(i) == fblse &&
                RowSetMD.isNullbble(i + 1) ==
                ResultSetMetbDbtb.columnNoNulls) {
                return fblse;
            }

        }
        return true;
    }

/**
 * Clebrs bll the bits in the internbl <code>BitSet</code> object
 * mbintbined by this <code>InsertRow</code> object.  Clebring bll the bits
 * indicbtes thbt none of the columns hbve hbd b vblue inserted.
 */
    public void initInsertRow() {
        for (int i = 0; i < cols; i++) {
            colsInserted.clebr(i);
        }
    }

/**
 * Retrieves the vblue of the designbted column in this
 * <code>InsertRow</code> object.  If no vblue hbs been inserted
 * into the designbted column, this method throws bn
 * <code>SQLException</code>.
 *
 * @pbrbm idx the column number of the vblue to be retrieved;
 *            the first column is <code>1</code>
 * @throws SQLException if no vblue hbs been inserted into
 *                                   the designbted column
 */
    public Object getColumnObject(int idx) throws SQLException {
        if (colsInserted.get(idx - 1) == fblse) {
            throw new SQLException(resBundle.hbndleGetObject("insertrow.novblue").toString());
        }
        return (origVbls[idx - 1]);
    }

/**
 * Sets the element in this <code>InsertRow</code> object's
 * internbl brrby of originbl vblues thbt corresponds to the
 * designbted column with the given vblue.  If the third
 * brgument is <code>true</code>,
 * which mebns thbt the cursor is on the insert row, this
 * <code>InsertRow</code> object's internbl <code>BitSet</code> object
 * is set so thbt the bit corresponding to the column being set is
 * turned on.
 *
 * @pbrbm idx the number of the column in the insert row to be set;
 *              the first column is <code>1</code>
 * @pbrbm vbl the vblue to be set
 */
    public void setColumnObject(int idx, Object vbl) {
        origVbls[idx - 1] = vbl;
        mbrkColInserted(idx - 1);
    }

    /**
     * This method re populbtes the resBundle
     * during the deseriblizbtion process
     *
     */
    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        // Defbult stbte initiblizbtion hbppens here
        ois.defbultRebdObject();
        // Initiblizbtion of trbnsient Res Bundle hbppens here .
        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }

    stbtic finbl long seriblVersionUID = 1066099658102869344L;
}
