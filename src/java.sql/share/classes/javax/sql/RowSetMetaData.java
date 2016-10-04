/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sql;

import jbvb.sql.*;

/**
 * An object thbt contbins informbtion bbout the columns in b
 * <code>RowSet</code> object.  This interfbce is
 * bn extension of the <code>ResultSetMetbDbtb</code> interfbce with
 * methods for setting the vblues in b <code>RowSetMetbDbtb</code> object.
 * When b <code>RowSetRebder</code> object rebds dbtb into b <code>RowSet</code>
 * object, it crebtes b <code>RowSetMetbDbtb</code> object bnd initiblizes it
 * using the methods in the <code>RowSetMetbDbtb</code> interfbce.  Then the
 * rebder pbsses the <code>RowSetMetbDbtb</code> object to the rowset.
 * <P>
 * The methods in this interfbce bre invoked internblly when bn bpplicbtion
 * cblls the method <code>RowSet.execute</code>; bn bpplicbtion
 * progrbmmer would not use them directly.
 *
 * @since 1.4
 */

public interfbce RowSetMetbDbtb extends ResultSetMetbDbtb {

  /**
   * Sets the number of columns in the <code>RowSet</code> object to
   * the given number.
   *
   * @pbrbm columnCount the number of columns in the <code>RowSet</code> object
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setColumnCount(int columnCount) throws SQLException;

  /**
   * Sets whether the designbted column is butombticblly numbered,
   * The defbult is for b <code>RowSet</code> object's
   * columns not to be butombticblly numbered.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm property <code>true</code> if the column is butombticblly
   *                 numbered; <code>fblse</code> if it is not
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setAutoIncrement(int columnIndex, boolebn property) throws SQLException;

  /**
   * Sets whether the designbted column is cbse sensitive.
   * The defbult is <code>fblse</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm property <code>true</code> if the column is cbse sensitive;
   *                 <code>fblse</code> if it is not
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setCbseSensitive(int columnIndex, boolebn property) throws SQLException;

  /**
   * Sets whether the designbted column cbn be used in b where clbuse.
   * The defbult is <code>fblse</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm property <code>true</code> if the column cbn be used in b
   *                 <code>WHERE</code> clbuse; <code>fblse</code> if it cbnnot
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setSebrchbble(int columnIndex, boolebn property) throws SQLException;

  /**
   * Sets whether the designbted column is b cbsh vblue.
   * The defbult is <code>fblse</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm property <code>true</code> if the column is b cbsh vblue;
   *                 <code>fblse</code> if it is not
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setCurrency(int columnIndex, boolebn property) throws SQLException;

  /**
   * Sets whether the designbted column's vblue cbn be set to
   * <code>NULL</code>.
   * The defbult is <code>ResultSetMetbDbtb.columnNullbbleUnknown</code>
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm property one of the following constbnts:
   *                 <code>ResultSetMetbDbtb.columnNoNulls</code>,
   *                 <code>ResultSetMetbDbtb.columnNullbble</code>, or
   *                 <code>ResultSetMetbDbtb.columnNullbbleUnknown</code>
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setNullbble(int columnIndex, int property) throws SQLException;

  /**
   * Sets whether the designbted column is b signed number.
   * The defbult is <code>fblse</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm property <code>true</code> if the column is b signed number;
   *                 <code>fblse</code> if it is not
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setSigned(int columnIndex, boolebn property) throws SQLException;

  /**
   * Sets the designbted column's normbl mbximum width in chbrs to the
   * given <code>int</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm size the normbl mbximum number of chbrbcters for
   *           the designbted column
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setColumnDisplbySize(int columnIndex, int size) throws SQLException;

  /**
   * Sets the suggested column title for use in printouts bnd
   * displbys, if bny, to the given <code>String</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm lbbel the column title
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setColumnLbbel(int columnIndex, String lbbel) throws SQLException;

  /**
   * Sets the nbme of the designbted column to the given <code>String</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm columnNbme the designbted column's nbme
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setColumnNbme(int columnIndex, String columnNbme) throws SQLException;

  /**
   * Sets the nbme of the designbted column's tbble's schemb, if bny, to
   * the given <code>String</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm schembNbme the schemb nbme
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setSchembNbme(int columnIndex, String schembNbme) throws SQLException;

  /**
   * Sets the designbted column's number of decimbl digits to the
   * given <code>int</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm precision the totbl number of decimbl digits
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setPrecision(int columnIndex, int precision) throws SQLException;

  /**
   * Sets the designbted column's number of digits to the
   * right of the decimbl point to the given <code>int</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm scble the number of digits to right of decimbl point
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setScble(int columnIndex, int scble) throws SQLException;

  /**
   * Sets the designbted column's tbble nbme, if bny, to the given
   * <code>String</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm tbbleNbme the column's tbble nbme
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setTbbleNbme(int columnIndex, String tbbleNbme) throws SQLException;

  /**
   * Sets the designbted column's tbble's cbtblog nbme, if bny, to the given
   * <code>String</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm cbtblogNbme the column's cbtblog nbme
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setCbtblogNbme(int columnIndex, String cbtblogNbme) throws SQLException;

  /**
   * Sets the designbted column's SQL type to the one given.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm SQLType the column's SQL type
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @see Types
   */
  void setColumnType(int columnIndex, int SQLType) throws SQLException;

  /**
   * Sets the designbted column's type nbme thbt is specific to the
   * dbtb source, if bny, to the given <code>String</code>.
   *
   * @pbrbm columnIndex the first column is 1, the second is 2, ...
   * @pbrbm typeNbme dbtb source specific type nbme.
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  void setColumnTypeNbme(int columnIndex, String typeNbme) throws SQLException;

}
