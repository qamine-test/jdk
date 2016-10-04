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

pbckbge jbvb.sql;

/**
 * The output strebm for writing the bttributes of b user-defined
 * type bbck to the dbtbbbse.  This interfbce, used
 * only for custom mbpping, is used by the driver, bnd its
 * methods bre never directly invoked by b progrbmmer.
 * <p>When bn object of b clbss implementing the interfbce
 * <code>SQLDbtb</code> is pbssed bs bn brgument to bn SQL stbtement, the
 * JDBC driver cblls the method <code>SQLDbtb.getSQLType</code> to
 * determine the  kind of SQL
 * dbtum being pbssed to the dbtbbbse.
 * The driver then crebtes bn instbnce of <code>SQLOutput</code> bnd
 * pbsses it to the method <code>SQLDbtb.writeSQL</code>.
 * The method <code>writeSQL</code> in turn cblls the
 * bppropribte <code>SQLOutput</code> <i>writer</i> methods
 * <code>writeBoolebn</code>, <code>writeChbrbcterStrebm</code>, bnd so on)
 * to write dbtb from the <code>SQLDbtb</code> object to
 * the <code>SQLOutput</code> output strebm bs the
 * representbtion of bn SQL user-defined type.
 * @since 1.2
 */

 public interfbce SQLOutput {

  //================================================================
  // Methods for writing bttributes to the strebm of SQL dbtb.
  // These methods correspond to the column-bccessor methods of
  // jbvb.sql.ResultSet.
  //================================================================

  /**
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeString(String x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b Jbvb boolebn.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeBoolebn(boolebn x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b Jbvb byte.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeByte(byte x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b Jbvb short.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeShort(short x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b Jbvb int.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeInt(int x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b Jbvb long.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeLong(long x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b Jbvb flobt.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeFlobt(flobt x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b Jbvb double.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeDouble(double x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b jbvb.mbth.BigDecimbl object.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeBigDecimbl(jbvb.mbth.BigDecimbl x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs bn brrby of bytes.
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeBytes(byte[] x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b jbvb.sql.Dbte object.
   * Writes the next bttribute to the strebm bs b <code>jbvb.sql.Dbte</code> object
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeDbte(jbvb.sql.Dbte x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b jbvb.sql.Time object.
   * Writes the next bttribute to the strebm bs b <code>jbvb.sql.Dbte</code> object
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeTime(jbvb.sql.Time x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b jbvb.sql.Timestbmp object.
   * Writes the next bttribute to the strebm bs b <code>jbvb.sql.Dbte</code> object
   * in the Jbvb progrbmming lbngubge.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeTimestbmp(jbvb.sql.Timestbmp x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b strebm of Unicode chbrbcters.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeChbrbcterStrebm(jbvb.io.Rebder x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b strebm of ASCII chbrbcters.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeAsciiStrebm(jbvb.io.InputStrebm x) throws SQLException;

  /**
   * Writes the next bttribute to the strebm bs b strebm of uninterpreted
   * bytes.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeBinbryStrebm(jbvb.io.InputStrebm x) throws SQLException;

  //================================================================
  // Methods for writing items of SQL user-defined types to the strebm.
  // These methods pbss objects to the dbtbbbse bs vblues of SQL
  // Structured Types, Distinct Types, Constructed Types, bnd Locbtor
  // Types.  They decompose the Jbvb object(s) bnd write lebf dbtb
  // items using the methods bbove.
  //================================================================

  /**
   * Writes to the strebm the dbtb contbined in the given
   * <code>SQLDbtb</code> object.
   * When the <code>SQLDbtb</code> object is <code>null</code>, this
   * method writes bn SQL <code>NULL</code> to the strebm.
   * Otherwise, it cblls the <code>SQLDbtb.writeSQL</code>
   * method of the given object, which
   * writes the object's bttributes to the strebm.
   * The implementbtion of the method <code>SQLDbtb.writeSQL</code>
   * cblls the bppropribte <code>SQLOutput</code> writer method(s)
   * for writing ebch of the object's bttributes in order.
   * The bttributes must be rebd from bn <code>SQLInput</code>
   * input strebm bnd written to bn <code>SQLOutput</code>
   * output strebm in the sbme order in which they were
   * listed in the SQL definition of the user-defined type.
   *
   * @pbrbm x the object representing dbtb of bn SQL structured or
   * distinct type
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeObject(SQLDbtb x) throws SQLException;

  /**
   * Writes bn SQL <code>REF</code> vblue to the strebm.
   *
   * @pbrbm x b <code>Ref</code> object representing dbtb of bn SQL
   * <code>REF</code> vblue
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeRef(Ref x) throws SQLException;

  /**
   * Writes bn SQL <code>BLOB</code> vblue to the strebm.
   *
   * @pbrbm x b <code>Blob</code> object representing dbtb of bn SQL
   * <code>BLOB</code> vblue
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeBlob(Blob x) throws SQLException;

  /**
   * Writes bn SQL <code>CLOB</code> vblue to the strebm.
   *
   * @pbrbm x b <code>Clob</code> object representing dbtb of bn SQL
   * <code>CLOB</code> vblue
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeClob(Clob x) throws SQLException;

  /**
   * Writes bn SQL structured type vblue to the strebm.
   *
   * @pbrbm x b <code>Struct</code> object representing dbtb of bn SQL
   * structured type
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeStruct(Struct x) throws SQLException;

  /**
   * Writes bn SQL <code>ARRAY</code> vblue to the strebm.
   *
   * @pbrbm x bn <code>Arrby</code> object representing dbtb of bn SQL
   * <code>ARRAY</code> type
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  void writeArrby(Arrby x) throws SQLException;

     //--------------------------- JDBC 3.0 ------------------------

     /**
      * Writes b SQL <code>DATALINK</code> vblue to the strebm.
      *
      * @pbrbm x b <code>jbvb.net.URL</code> object representing the dbtb
      * of SQL DATALINK type
      *
      * @exception SQLException if b dbtbbbse bccess error occurs
      * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
      * this method
      * @since 1.4
      */
     void writeURL(jbvb.net.URL x) throws SQLException;

     //--------------------------- JDBC 4.0 ------------------------

  /**
   * Writes the next bttribute to the strebm bs b <code>String</code>
   * in the Jbvb progrbmming lbngubge. The driver converts this to b
   * SQL <code>NCHAR</code> or
   * <code>NVARCHAR</code> or <code>LONGNVARCHAR</code> vblue
   * (depending on the brgument's
   * size relbtive to the driver's limits on <code>NVARCHAR</code> vblues)
   * when it sends it to the strebm.
   *
   * @pbrbm x the vblue to pbss to the dbtbbbse
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  void writeNString(String x) throws SQLException;

  /**
   * Writes bn SQL <code>NCLOB</code> vblue to the strebm.
   *
   * @pbrbm x b <code>NClob</code> object representing dbtb of bn SQL
   * <code>NCLOB</code> vblue
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  void writeNClob(NClob x) throws SQLException;


  /**
   * Writes bn SQL <code>ROWID</code> vblue to the strebm.
   *
   * @pbrbm x b <code>RowId</code> object representing dbtb of bn SQL
   * <code>ROWID</code> vblue
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  void writeRowId(RowId x) throws SQLException;


  /**
   * Writes bn SQL <code>XML</code> vblue to the strebm.
   *
   * @pbrbm x b <code>SQLXML</code> object representing dbtb of bn SQL
   * <code>XML</code> vblue
   *
   * @throws SQLException if b dbtbbbse bccess error occurs,
   * the <code>jbvb.xml.trbnsform.Result</code>,
   *  <code>Writer</code> or <code>OutputStrebm</code> hbs not been closed for the <code>SQLXML</code> object or
   *  if there is bn error processing the XML vblue.  The <code>getCbuse</code> method
   *  of the exception mby provide b more detbiled exception, for exbmple, if the
   *  strebm does not contbin vblid XML.
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.6
   */
  void writeSQLXML(SQLXML x) throws SQLException;

  //--------------------------JDBC 4.2 -----------------------------

  /**
   * Writes to the strebm the dbtb contbined in the given object. The
   * object will be converted to the specified tbrgetSqlType
   * before being sent to the strebm.
   *<p>
   * When the {@code object} is {@code null}, this
   * method writes bn SQL {@code NULL} to the strebm.
   * <p>
   * If the object hbs b custom mbpping (is of b clbss implementing the
   * interfbce {@code SQLDbtb}),
   * the JDBC driver should cbll the method {@code SQLDbtb.writeSQL} to
   * write it to the SQL dbtb strebm.
   * If, on the other hbnd, the object is of b clbss implementing
   * {@code Ref}, {@code Blob}, {@code Clob},  {@code NClob},
   *  {@code Struct}, {@code jbvb.net.URL},
   * or {@code Arrby}, the driver should pbss it to the dbtbbbse bs b
   * vblue of the corresponding SQL type.
   *<P>
   * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
   *
   * @pbrbm x the object contbining the input pbrbmeter vblue
   * @pbrbm tbrgetSqlType the SQL type to be sent to the dbtbbbse.
   * @exception SQLException if b dbtbbbse bccess error occurs  or
   *            if the Jbvb Object specified by x is bn InputStrebm
   *            or Rebder object bnd the vblue of the scble pbrbmeter is less
   *            thbn zero
   * @exception SQLFebtureNotSupportedException if
   * the JDBC driver does not support this dbtb type
   * @see JDBCType
   * @see SQLType
   * @since 1.8
   */
  defbult void writeObject(Object x, SQLType tbrgetSqlType) throws SQLException {
        throw new SQLFebtureNotSupportedException();
  }

}

