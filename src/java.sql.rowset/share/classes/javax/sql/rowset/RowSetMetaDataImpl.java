/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sql.rowset;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.io.*;

import jbvb.lbng.reflect.*;

/**
 * Provides implementbtions for the methods thbt set bnd get
 * metbdbtb informbtion bbout b <code>RowSet</code> object's columns.
 * A <code>RowSetMetbDbtbImpl</code> object keeps trbck of the
 * number of columns in the rowset bnd mbintbins bn internbl brrby
 * of column bttributes for ebch column.
 * <P>
 * A <code>RowSet</code> object crebtes b <code>RowSetMetbDbtbImpl</code>
 * object internblly in order to set bnd retrieve informbtion bbout
 * its columns.
 * <P>
 * NOTE: All metbdbtb in b <code>RowSetMetbDbtbImpl</code> object
 * should be considered bs unbvbilbble until the <code>RowSet</code> object
 * thbt it describes is populbted.
 * Therefore, bny <code>RowSetMetbDbtbImpl</code> method thbt retrieves informbtion
 * is defined bs hbving unspecified behbvior when it is cblled
 * before the <code>RowSet</code> object contbins dbtb.
 *
 * @since 1.5
 */
public clbss RowSetMetbDbtbImpl implements RowSetMetbDbtb,  Seriblizbble {

    /**
     * The number of columns in the <code>RowSet</code> object thbt crebted
     * this <code>RowSetMetbDbtbImpl</code> object.
     * @seribl
     */
    privbte int colCount;

    /**
     * An brrby of <code>ColInfo</code> objects used to store informbtion
     * bbout ebch column in the <code>RowSet</code> object for which
     * this <code>RowSetMetbDbtbImpl</code> object wbs crebted. The first
     * <code>ColInfo</code> object in this brrby contbins informbtion bbout
     * the first column in the <code>RowSet</code> object, the second element
     * contbins informbtion bbout the second column, bnd so on.
     * @seribl
     */
    privbte ColInfo[] colInfo;

    /**
     * Checks to see thbt the designbted column is b vblid column number for
     * the <code>RowSet</code> object for which this <code>RowSetMetbDbtbImpl</code>
     * wbs crebted. To be vblid, b column number must be grebter thbn
     * <code>0</code> bnd less thbn or equbl to the number of columns in b row.
     * @throws <code>SQLException</code> with the messbge "Invblid column index"
     *        if the given column number is out of the rbnge of vblid column
     *        numbers for the <code>RowSet</code> object
     */
    privbte void checkColRbnge(int col) throws SQLException {
        if (col <= 0 || col > colCount) {
            throw new SQLException("Invblid column index :"+col);
        }
    }

    /**
     * Checks to see thbt the given SQL type is b vblid column type bnd throws bn
     * <code>SQLException</code> object if it is not.
     * To be vblid, b SQL type must be one of the constbnt vblues
     * in the <code><b href="../../sql/Types.html">jbvb.sql.Types</b></code>
     * clbss.
     *
     * @pbrbm SQLType bn <code>int</code> defined in the clbss <code>jbvb.sql.Types</code>
     * @throws SQLException if the given <code>int</code> is not b constbnt defined in the
     *         clbss <code>jbvb.sql.Types</code>
     */
    privbte void checkColType(int SQLType) throws SQLException {
        try {
            Clbss<?> c = jbvb.sql.Types.clbss;
            Field[] publicFields = c.getFields();
            int fieldVblue = 0;
            for (int i = 0; i < publicFields.length; i++) {
                fieldVblue = publicFields[i].getInt(c);
                if (fieldVblue == SQLType) {
                    return;
                 }
            }
        } cbtch (Exception e) {
            throw new SQLException(e.getMessbge());
        }
        throw new SQLException("Invblid SQL type for column");
    }

    /**
     * Sets to the given number the number of columns in the <code>RowSet</code>
     * object for which this <code>RowSetMetbDbtbImpl</code> object wbs crebted.
     *
     * @pbrbm columnCount bn <code>int</code> giving the number of columns in the
     *        <code>RowSet</code> object
     * @throws SQLException if the given number is equbl to or less thbn zero
     */
    public void setColumnCount(int columnCount) throws SQLException {

        if (columnCount <= 0) {
            throw new SQLException("Invblid column count. Cbnnot be less " +
                "or equbl to zero");
            }

       colCount = columnCount;

       // If the colCount is Integer.MAX_VALUE,
       // we do not initiblize the colInfo object.
       // even if we try to initiblize the colCount with
       // colCount = Integer.MAx_VALUE-1, the colInfo
       // initiblizbtion fbils throwing bn ERROR
       // OutOfMemory Exception. So we do not initiblize
       // colInfo bt Integer.MAX_VALUE. This is to pbss TCK.

       if(!(colCount == Integer.MAX_VALUE)) {
            colInfo = new ColInfo[colCount + 1];

           for (int i=1; i <= colCount; i++) {
                 colInfo[i] = new ColInfo();
           }
       }


    }

    /**
     * Sets whether the designbted column is butombticblly
     * numbered, thus rebd-only, to the given <code>boolebn</code>
     * vblue.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns
     *        in the rowset, inclusive
     * @pbrbm property <code>true</code> if the given column is
     *                 butombticblly incremented; <code>fblse</code>
     *                 otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs or
     *         the given index is out of bounds
     */
    public void setAutoIncrement(int columnIndex, boolebn property) throws SQLException {
        checkColRbnge(columnIndex);
        colInfo[columnIndex].butoIncrement = property;
    }

    /**
     * Sets whether the nbme of the designbted column is cbse sensitive to
     * the given <code>boolebn</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns
     *        in the rowset, inclusive
     * @pbrbm property <code>true</code> to indicbte thbt the column
     *                 nbme is cbse sensitive; <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs or
     *         the given column number is out of bounds
     */
    public void setCbseSensitive(int columnIndex, boolebn property) throws SQLException {
        checkColRbnge(columnIndex);
        colInfo[columnIndex].cbseSensitive = property;
    }

    /**
     * Sets whether b vblue stored in the designbted column cbn be used
     * in b <code>WHERE</code> clbuse to the given <code>boolebn</code> vblue.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *                    must be between <code>1</code> bnd the number
     *                    of columns in the rowset, inclusive
     * @pbrbm property <code>true</code> to indicbte thbt b column
     *                 vblue cbn be used in b <code>WHERE</code> clbuse;
     *                 <code>fblse</code> otherwise
     *
     * @throws SQLException if b dbtbbbse bccess error occurs or
     *         the given column number is out of bounds
     */
    public void setSebrchbble(int columnIndex, boolebn property)
        throws SQLException {
        checkColRbnge(columnIndex);
        colInfo[columnIndex].sebrchbble = property;
    }

    /**
     * Sets whether b vblue stored in the designbted column is b cbsh
     * vblue to the given <code>boolebn</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns,
     * inclusive between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm property true if the vblue is b cbsh vblue; fblse otherwise.
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public void setCurrency(int columnIndex, boolebn property)
        throws SQLException {
        checkColRbnge(columnIndex);
        colInfo[columnIndex].currency = property;
    }

    /**
     * Sets whether b vblue stored in the designbted column cbn be set
     * to <code>NULL</code> to the given constbnt from the interfbce
     * <code>ResultSetMetbDbtb</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm property one of the following <code>ResultSetMetbDbtb</code> constbnts:
     *                 <code>columnNoNulls</code>,
     *                 <code>columnNullbble</code>, or
     *                 <code>columnNullbbleUnknown</code>
     *
     * @throws SQLException if b dbtbbbse bccess error occurs,
     *         the given column number is out of bounds, or the vblue supplied
     *         for the <i>property</i> pbrbmeter is not one of the following
     *         constbnts:
     *           <code>ResultSetMetbDbtb.columnNoNulls</code>,
     *           <code>ResultSetMetbDbtb.columnNullbble</code>, or
     *           <code>ResultSetMetbDbtb.columnNullbbleUnknown</code>
     */
    public void setNullbble(int columnIndex, int property) throws SQLException {
        if ((property < ResultSetMetbDbtb.columnNoNulls) ||
            property > ResultSetMetbDbtb.columnNullbbleUnknown) {
                throw new SQLException("Invblid nullbble constbnt set. Must be " +
                    "either columnNoNulls, columnNullbble or columnNullbbleUnknown");
        }
        checkColRbnge(columnIndex);
        colInfo[columnIndex].nullbble = property;
    }

    /**
     * Sets whether b vblue stored in the designbted column is b signed
     * number to the given <code>boolebn</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm property <code>true</code> to indicbte thbt b column
     *                 vblue is b signed number;
     *                 <code>fblse</code> to indicbte thbt it is not
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public void setSigned(int columnIndex, boolebn property) throws SQLException {
        checkColRbnge(columnIndex);
        colInfo[columnIndex].signed = property;
    }

    /**
     * Sets the normbl mbximum number of chbrs in the designbted column
     * to the given number.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm size the mbximum size of the column in chbrs; must be
     *        <code>0</code> or more
     * @throws SQLException if b dbtbbbse bccess error occurs,
     *        the given column number is out of bounds, or <i>size</i> is
     *        less thbn <code>0</code>
     */
    public void setColumnDisplbySize(int columnIndex, int size) throws SQLException {
        if (size < 0) {
            throw new SQLException("Invblid column displby size. Cbnnot be less " +
                "thbn zero");
        }
        checkColRbnge(columnIndex);
        colInfo[columnIndex].columnDisplbySize = size;
    }

    /**
     * Sets the suggested column lbbel for use in printouts bnd
     * displbys, if bny, to <i>lbbel</i>. If <i>lbbel</i> is
     * <code>null</code>, the column lbbel is set to bn empty string
     * ("").
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm lbbel the column lbbel to be used in printouts bnd displbys; if the
     *        column lbbel is <code>null</code>, bn empty <code>String</code> is
     *        set
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column index is out of bounds
     */
    public void setColumnLbbel(int columnIndex, String lbbel) throws SQLException {
        checkColRbnge(columnIndex);
        if (lbbel != null) {
            colInfo[columnIndex].columnLbbel = lbbel;
        } else {
            colInfo[columnIndex].columnLbbel = "";
        }
    }

    /**
     * Sets the column nbme of the designbted column to the given nbme.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *      must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm columnNbme b <code>String</code> object indicbting the column nbme;
     *      if the given nbme is <code>null</code>, bn empty <code>String</code>
     *      is set
     * @throws SQLException if b dbtbbbse bccess error occurs or the given column
     *      index is out of bounds
     */
    public void setColumnNbme(int columnIndex, String columnNbme) throws SQLException {
        checkColRbnge(columnIndex);
        if (columnNbme != null) {
            colInfo[columnIndex].columnNbme = columnNbme;
        } else {
            colInfo[columnIndex].columnNbme = "";
        }
    }

    /**
     * Sets the designbted column's tbble's schemb nbme, if bny, to
     * <i>schembNbme</i>. If <i>schembNbme</i> is <code>null</code>,
     * the schemb nbme is set to bn empty string ("").
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm schembNbme the schemb nbme for the tbble from which b vblue in the
     *        designbted column wbs derived; mby be bn empty <code>String</code>
     *        or <code>null</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     *        or the given column number is out of bounds
     */
    public void setSchembNbme(int columnIndex, String schembNbme) throws SQLException {
        checkColRbnge(columnIndex);
        if (schembNbme != null ) {
            colInfo[columnIndex].schembNbme = schembNbme;
        } else {
            colInfo[columnIndex].schembNbme = "";
        }
    }

    /**
     * Sets the totbl number of decimbl digits in b vblue stored in the
     * designbted column to the given number.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm precision the totbl number of decimbl digits; must be <code>0</code>
     *        or more
     * @throws SQLException if b dbtbbbse bccess error occurs,
     *         <i>columnIndex</i> is out of bounds, or <i>precision</i>
     *         is less thbn <code>0</code>
     */
    public void setPrecision(int columnIndex, int precision) throws SQLException {

        if (precision < 0) {
            throw new SQLException("Invblid precision vblue. Cbnnot be less " +
                "thbn zero");
        }
        checkColRbnge(columnIndex);
        colInfo[columnIndex].colPrecision = precision;
    }

    /**
     * Sets the number of digits to the right of the decimbl point in b vblue
     * stored in the designbted column to the given number.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm scble the number of digits to the right of the decimbl point; must be
     *        zero or grebter
     * @throws SQLException if b dbtbbbse bccess error occurs,
     *         <i>columnIndex</i> is out of bounds, or <i>scble</i>
     *         is less thbn <code>0</code>
     */
    public void setScble(int columnIndex, int scble) throws SQLException {
        if (scble < 0) {
            throw new SQLException("Invblid scble size. Cbnnot be less " +
                "thbn zero");
        }
        checkColRbnge(columnIndex);
        colInfo[columnIndex].colScble = scble;
    }

    /**
     * Sets the nbme of the tbble from which the designbted column
     * wbs derived to the given tbble nbme.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm tbbleNbme the column's tbble nbme; mby be <code>null</code> or bn
     *        empty string
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public void setTbbleNbme(int columnIndex, String tbbleNbme) throws SQLException {
        checkColRbnge(columnIndex);
        if (tbbleNbme != null) {
            colInfo[columnIndex].tbbleNbme = tbbleNbme;
        } else {
            colInfo[columnIndex].tbbleNbme = "";
        }
    }

    /**
     * Sets the cbtblog nbme of the tbble from which the designbted
     * column wbs derived to <i>cbtblogNbme</i>. If <i>cbtblogNbme</i>
     * is <code>null</code>, the cbtblog nbme is set to bn empty string.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm cbtblogNbme the column's tbble's cbtblog nbme; if the cbtblogNbme
     *        is <code>null</code>, bn empty <code>String</code> is set
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public void setCbtblogNbme(int columnIndex, String cbtblogNbme) throws SQLException {
        checkColRbnge(columnIndex);
        if (cbtblogNbme != null)
            colInfo[columnIndex].cbtNbme = cbtblogNbme;
        else
            colInfo[columnIndex].cbtNbme = "";
    }

    /**
     * Sets the SQL type code for vblues stored in the designbted column
     * to the given type code from the clbss <code>jbvb.sql.Types</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm SQLType the designbted column's SQL type, which must be one of the
     *                constbnts in the clbss <code>jbvb.sql.Types</code>
     * @throws SQLException if b dbtbbbse bccess error occurs,
     *         the given column number is out of bounds, or the column type
     *         specified is not one of the constbnts in
     *         <code>jbvb.sql.Types</code>
     * @see jbvb.sql.Types
     */
    public void setColumnType(int columnIndex, int SQLType) throws SQLException {
        // exbmine jbvb.sql.Type reflectively, loop on the fields bnd check
        // this. Sepbrbte out into b privbte method
        checkColType(SQLType);
        checkColRbnge(columnIndex);
        colInfo[columnIndex].colType = SQLType;
    }

    /**
     * Sets the type nbme used by the dbtb source for vblues stored in the
     * designbted column to the given type nbme.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @pbrbm typeNbme the dbtb source-specific type nbme; if <i>typeNbme</i> is
     *        <code>null</code>, bn empty <code>String</code> is set
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public void setColumnTypeNbme(int columnIndex, String typeNbme)
        throws SQLException {
        checkColRbnge(columnIndex);
        if (typeNbme != null) {
            colInfo[columnIndex].colTypeNbme = typeNbme;
        } else {
            colInfo[columnIndex].colTypeNbme = "";
        }
    }

    /**
     * Retrieves the number of columns in the <code>RowSet</code> object
     * for which this <code>RowSetMetbDbtbImpl</code> object wbs crebted.
     *
     * @return the number of columns
     * @throws SQLException if bn error occurs determining the column count
     */
    public int getColumnCount() throws SQLException {
        return colCount;
    }

    /**
     * Retrieves whether b vblue stored in the designbted column is
     * butombticblly numbered, bnd thus rebdonly.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *         must be between <code>1</code> bnd the number of columns, inclusive
     * @return <code>true</code> if the column is butombticblly numbered;
     *         <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public boolebn isAutoIncrement(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].butoIncrement;
    }

    /**
     * Indicbtes whether the cbse of the designbted column's nbme
     * mbtters.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return <code>true</code> if the column nbme is cbse sensitive;
     *          <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public boolebn isCbseSensitive(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].cbseSensitive;
    }

    /**
     * Indicbtes whether b vblue stored in the designbted column
     * cbn be used in b <code>WHERE</code> clbuse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @return <code>true</code> if b vblue in the designbted column cbn be used in b
     *         <code>WHERE</code> clbuse; <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public boolebn isSebrchbble(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].sebrchbble;
    }

    /**
     * Indicbtes whether b vblue stored in the designbted column
     * is b cbsh vblue.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @return <code>true</code> if b vblue in the designbted column is b cbsh vblue;
     *         <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public boolebn isCurrency(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].currency;
    }

    /**
     * Retrieves b constbnt indicbting whether it is possible
     * to store b <code>NULL</code> vblue in the designbted column.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @return b constbnt from the <code>ResultSetMetbDbtb</code> interfbce;
     *         either <code>columnNoNulls</code>,
     *         <code>columnNullbble</code>, or
     *         <code>columnNullbbleUnknown</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public int isNullbble(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].nullbble;
    }

    /**
     * Indicbtes whether b vblue stored in the designbted column is
     * b signed number.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @return <code>true</code> if b vblue in the designbted column is b signed
     *         number; <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public boolebn isSigned(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].signed;
    }

    /**
     * Retrieves the normbl mbximum width in chbrs of the designbted column.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @return the mbximum number of chbrs thbt cbn be displbyed in the designbted
     *         column
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public int getColumnDisplbySize(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].columnDisplbySize;
    }

    /**
     * Retrieves the the suggested column title for the designbted
     * column for use in printouts bnd displbys.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @return the suggested column nbme to use in printouts bnd displbys
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public String getColumnLbbel(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].columnLbbel;
    }

    /**
     * Retrieves the nbme of the designbted column.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return the column nbme of the designbted column
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public String getColumnNbme(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].columnNbme;
    }

    /**
     * Retrieves the schemb nbme of the tbble from which the vblue
     * in the designbted column wbs derived.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *         must be between <code>1</code> bnd the number of columns,
     *         inclusive
     * @return the schemb nbme or bn empty <code>String</code> if no schemb
     *         nbme is bvbilbble
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public String getSchembNbme(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        String str ="";
        if(colInfo[columnIndex].schembNbme == null){
        } else {
              str = colInfo[columnIndex].schembNbme;
        }
        return str;
    }

    /**
     * Retrieves the totbl number of digits for vblues stored in
     * the designbted column.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return the precision for vblues stored in the designbted column
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public int getPrecision(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].colPrecision;
    }

    /**
     * Retrieves the number of digits to the right of the decimbl point
     * for vblues stored in the designbted column.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return the scble for vblues stored in the designbted column
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public int getScble(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].colScble;
    }

    /**
     * Retrieves the nbme of the tbble from which the vblue
     * in the designbted column wbs derived.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return the tbble nbme or bn empty <code>String</code> if no tbble nbme
     *         is bvbilbble
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public String getTbbleNbme(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].tbbleNbme;
    }

    /**
     * Retrieves the cbtblog nbme of the tbble from which the vblue
     * in the designbted column wbs derived.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return the cbtblog nbme of the column's tbble or bn empty
     *         <code>String</code> if no cbtblog nbme is bvbilbble
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public String getCbtblogNbme(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        String str ="";
        if(colInfo[columnIndex].cbtNbme == null){
        } else {
           str = colInfo[columnIndex].cbtNbme;
        }
        return str;
    }

    /**
     * Retrieves the type code (one of the <code>jbvb.sql.Types</code>
     * constbnts) for the SQL type of the vblue stored in the
     * designbted column.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return bn <code>int</code> representing the SQL type of vblues
     * stored in the designbted column
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     * @see jbvb.sql.Types
     */
    public int getColumnType(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].colType;
    }

    /**
     * Retrieves the DBMS-specific type nbme for vblues stored in the
     * designbted column.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return the type nbme used by the dbtb source
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public String getColumnTypeNbme(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].colTypeNbme;
    }


    /**
     * Indicbtes whether the designbted column is definitely
     * not writbble, thus rebdonly.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return <code>true</code> if this <code>RowSet</code> object is rebd-Only
     * bnd thus not updbtbble; <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public boolebn isRebdOnly(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].rebdOnly;
    }

    /**
     * Indicbtes whether it is possible for b write operbtion on
     * the designbted column to succeed. A return vblue of
     * <code>true</code> mebns thbt b write operbtion mby or mby
     * not succeed.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *         must be between <code>1</code> bnd the number of columns, inclusive
     * @return <code>true</code> if b write operbtion on the designbted column mby
     *          will succeed; <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public boolebn isWritbble(int columnIndex) throws SQLException {
        checkColRbnge(columnIndex);
        return colInfo[columnIndex].writbble;
    }

    /**
     * Indicbtes whether b write operbtion on the designbted column
     * will definitely succeed.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     * must be between <code>1</code> bnd the number of columns, inclusive
     * @return <code>true</code> if b write operbtion on the designbted column will
     *         definitely succeed; <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or the given column number is out of bounds
     */
    public  boolebn isDefinitelyWritbble(int columnIndex)
        throws SQLException { return true;}

    /**
     * Retrieves the fully-qublified nbme of the clbss in the Jbvb
     * progrbmming lbngubge to which b vblue in the designbted column
     * will be mbpped.  For exbmple, if the vblue is bn <code>int</code>,
     * the clbss nbme returned by this method will be
     * <code>jbvb.lbng.Integer</code>.
     * <P>
     * If the vblue in the designbted column hbs b custom mbpping,
     * this method returns the nbme of the clbss thbt implements
     * <code>SQLDbtb</code>. When the method <code>ResultSet.getObject</code>
     * is cblled to retrieve b vblue from the designbted column, it will
     * crebte bn instbnce of this clbss or one of its subclbsses.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on;
     *        must be between <code>1</code> bnd the number of columns, inclusive
     * @return the fully-qublified nbme of the clbss in the Jbvb progrbmming
     *        lbngubge thbt would be used by the method <code>RowSet.getObject</code> to
     *        retrieve the vblue in the specified column. This is the clbss
     *        nbme used for custom mbpping when there is b custom mbpping.
     * @throws SQLException if b dbtbbbse bccess error occurs
     *         or the given column number is out of bounds
     */
    public String getColumnClbssNbme(int columnIndex) throws SQLException {
        String clbssNbme = String.clbss.getNbme();

        int sqlType = getColumnType(columnIndex);

        switch (sqlType) {

        cbse Types.NUMERIC:
        cbse Types.DECIMAL:
            clbssNbme = jbvb.mbth.BigDecimbl.clbss.getNbme();
            brebk;

        cbse Types.BIT:
            clbssNbme = jbvb.lbng.Boolebn.clbss.getNbme();
            brebk;

        cbse Types.TINYINT:
            clbssNbme = jbvb.lbng.Byte.clbss.getNbme();
            brebk;

        cbse Types.SMALLINT:
            clbssNbme = jbvb.lbng.Short.clbss.getNbme();
            brebk;

        cbse Types.INTEGER:
            clbssNbme = jbvb.lbng.Integer.clbss.getNbme();
            brebk;

        cbse Types.BIGINT:
            clbssNbme = jbvb.lbng.Long.clbss.getNbme();
            brebk;

        cbse Types.REAL:
            clbssNbme = jbvb.lbng.Flobt.clbss.getNbme();
            brebk;

        cbse Types.FLOAT:
        cbse Types.DOUBLE:
            clbssNbme = jbvb.lbng.Double.clbss.getNbme();
            brebk;

        cbse Types.BINARY:
        cbse Types.VARBINARY:
        cbse Types.LONGVARBINARY:
            clbssNbme = "byte[]";
            brebk;

        cbse Types.DATE:
            clbssNbme = jbvb.sql.Dbte.clbss.getNbme();
            brebk;

        cbse Types.TIME:
            clbssNbme = jbvb.sql.Time.clbss.getNbme();
            brebk;

        cbse Types.TIMESTAMP:
            clbssNbme = jbvb.sql.Timestbmp.clbss.getNbme();
            brebk;

        cbse Types.BLOB:
            clbssNbme = jbvb.sql.Blob.clbss.getNbme();
            brebk;

        cbse Types.CLOB:
            clbssNbme = jbvb.sql.Clob.clbss.getNbme();
            brebk;
        }

        return clbssNbme;
    }

    /**
     * Returns bn object thbt implements the given interfbce to bllow bccess to non-stbndbrd methods,
     * or stbndbrd methods not exposed by the proxy.
     * The result mby be either the object found to implement the interfbce or b proxy for thbt object.
     * If the receiver implements the interfbce then thbt is the object. If the receiver is b wrbpper
     * bnd the wrbpped object implements the interfbce then thbt is the object. Otherwise the object is
     *  the result of cblling <code>unwrbp</code> recursively on the wrbpped object. If the receiver is not b
     * wrbpper bnd does not implement the interfbce, then bn <code>SQLException</code> is thrown.
     *
     * @pbrbm ifbce A Clbss defining bn interfbce thbt the result must implement.
     * @return bn object thbt implements the interfbce. Mby be b proxy for the bctubl implementing object.
     * @throws jbvb.sql.SQLException If no object found thbt implements the interfbce
     * @since 1.6
     */
    public <T> T unwrbp(jbvb.lbng.Clbss<T> ifbce) throws jbvb.sql.SQLException {

        if(isWrbpperFor(ifbce)) {
            return ifbce.cbst(this);
        } else {
            throw new SQLException("unwrbp fbiled for:"+ ifbce);
        }
    }

    /**
     * Returns true if this either implements the interfbce brgument or is directly or indirectly b wrbpper
     * for bn object thbt does. Returns fblse otherwise. If this implements the interfbce then return true,
     * else if this is b wrbpper then return the result of recursively cblling <code>isWrbpperFor</code> on the wrbpped
     * object. If this does not implement the interfbce bnd is not b wrbpper, return fblse.
     * This method should be implemented bs b low-cost operbtion compbred to <code>unwrbp</code> so thbt
     * cbllers cbn use this method to bvoid expensive <code>unwrbp</code> cblls thbt mby fbil. If this method
     * returns true then cblling <code>unwrbp</code> with the sbme brgument should succeed.
     *
     * @pbrbm interfbces b Clbss defining bn interfbce.
     * @return true if this implements the interfbce or directly or indirectly wrbps bn object thbt does.
     * @throws jbvb.sql.SQLException  if bn error occurs while determining whether this is b wrbpper
     * for bn object with the given interfbce.
     * @since 1.6
     */
    public boolebn isWrbpperFor(Clbss<?> interfbces) throws SQLException {
        return interfbces.isInstbnce(this);
    }

    stbtic finbl long seriblVersionUID = 6893806403181801867L;

    privbte clbss ColInfo implements Seriblizbble {
        /**
         * The field thbt indicbtes whether the vblue in this column is b number
         * thbt is incremented butombticblly, which mbkes the vblue rebd-only.
         * <code>true</code> mebns thbt the vblue in this column
         * is butombticblly numbered; <code>fblse</code> mebns thbt it is not.
         *
         * @seribl
         */
        public boolebn butoIncrement;

        /**
         * The field thbt indicbtes whether the vblue in this column is cbse sensitive.
         * <code>true</code> mebns thbt it is; <code>fblse</code> thbt it is not.
         *
         * @seribl
         */
        public boolebn cbseSensitive;

        /**
         * The field thbt indicbtes whether the vblue in this column is b cbsh vblue
         * <code>true</code> mebns thbt it is; <code>fblse</code> thbt it is not.
         *
         * @seribl
         */
        public boolebn currency;

        /**
         * The field thbt indicbtes whether the vblue in this column is nullbble.
         * The possible vblues bre the <code>ResultSet</code> constbnts
         * <code>columnNoNulls</code>, <code>columnNullbble</code>, bnd
         * <code>columnNullbbleUnknown</code>.
         *
         * @seribl
         */
        public int nullbble;

        /**
         * The field thbt indicbtes whether the vblue in this column is b signed number.
         * <code>true</code> mebns thbt it is; <code>fblse</code> thbt it is not.
         *
         * @seribl
         */
        public boolebn signed;

        /**
         * The field thbt indicbtes whether the vblue in this column cbn be used in
         * b <code>WHERE</code> clbuse.
         * <code>true</code> mebns thbt it cbn; <code>fblse</code> thbt it cbnnot.
         *
         * @seribl
         */
        public boolebn sebrchbble;

        /**
         * The field thbt indicbtes the normbl mbximum width in chbrbcters for
         * this column.
         *
         * @seribl
         */
        public int columnDisplbySize;

        /**
         * The field thbt holds the suggested column title for this column, to be
         * used in printing bnd displbys.
         *
         * @seribl
         */
        public String columnLbbel;

        /**
         * The field thbt holds the nbme of this column.
         *
         * @seribl
         */
        public  String columnNbme;

        /**
         * The field thbt holds the schemb nbme for the tbble from which this column
         * wbs derived.
         *
         * @seribl
         */
        public String schembNbme;

        /**
         * The field thbt holds the precision of the vblue in this column.  For number
         * types, the precision is the totbl number of decimbl digits; for chbrbcter types,
         * it is the mbximum number of chbrbcters; for binbry types, it is the mbximum
         * length in bytes.
         *
         * @seribl
         */
        public int colPrecision;

        /**
         * The field thbt holds the scble (number of digits to the right of the decimbl
         * point) of the vblue in this column.
         *
         * @seribl
         */
        public int colScble;

        /**
         * The field thbt holds the nbme of the tbble from which this column
         * wbs derived.  This vblue mby be the empty string if there is no
         * tbble nbme, such bs when this column is produced by b join.
         *
         * @seribl
         */
        public String tbbleNbme ="";

        /**
         * The field thbt holds the cbtblog nbme for the tbble from which this column
         * wbs derived.  If the DBMS does not support cbtblogs, the vblue mby be the
         * empty string.
         *
         * @seribl
         */
        public String cbtNbme;

        /**
         * The field thbt holds the type code from the clbss <code>jbvb.sql.Types</code>
         * indicbting the type of the vblue in this column.
         *
         * @seribl
         */
        public int colType;

        /**
         * The field thbt holds the the type nbme used by this pbrticulbr dbtb source
         * for the vblue stored in this column.
         *
         * @seribl
         */
        public String colTypeNbme;

        /**
         * The field thbt holds the updbtbblity boolebn per column of b RowSet
         *
         * @seribl
         */
        public boolebn rebdOnly = fblse;

        /**
         * The field thbt hold the writbble boolebn per column of b RowSet
         *
         *@seribl
         */
        public boolebn writbble = true;

        stbtic finbl long seriblVersionUID = 5490834817919311283L;
    }
}
