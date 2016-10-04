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
 * An input strebm thbt contbins b strebm of vblues representing bn
 * instbnce of bn SQL structured type or bn SQL distinct type.
 * This interfbce, used only for custom mbpping, is used by the driver
 * behind the scenes, bnd b progrbmmer never directly invokes
 * <code>SQLInput</code> methods. The <i>rebder</i> methods
 * (<code>rebdLong</code>, <code>rebdBytes</code>, bnd so on)
 * provide b wby  for bn implementbtion of the <code>SQLDbtb</code>
 *  interfbce to rebd the vblues in bn <code>SQLInput</code> object.
 *  And bs described in <code>SQLDbtb</code>, cblls to rebder methods must
 * be mbde in the order thbt their corresponding bttributes bppebr in the
 * SQL definition of the type.
 * The method <code>wbsNull</code> is used to determine whether
 * the lbst vblue rebd wbs SQL <code>NULL</code>.
 * <P>When the method <code>getObject</code> is cblled with bn
 * object of b clbss implementing the interfbce <code>SQLDbtb</code>,
 * the JDBC driver cblls the method <code>SQLDbtb.getSQLType</code>
 * to determine the SQL type of the user-defined type (UDT)
 * being custom mbpped. The driver
 * crebtes bn instbnce of <code>SQLInput</code>, populbting it with the
 * bttributes of the UDT.  The driver then pbsses the input
 * strebm to the method <code>SQLDbtb.rebdSQL</code>, which in turn
 * cblls the <code>SQLInput</code> rebder methods
 * in its implementbtion for rebding the
 * bttributes from the input strebm.
 * @since 1.2
 */

public interfbce SQLInput {


    //================================================================
    // Methods for rebding bttributes from the strebm of SQL dbtb.
    // These methods correspond to the column-bccessor methods of
    // jbvb.sql.ResultSet.
    //================================================================

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>String</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    String rebdString() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>boolebn</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>fblse</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    boolebn rebdBoolebn() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>byte</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>0</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    byte rebdByte() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>short</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>0</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    short rebdShort() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs bn <code>int</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>0</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    int rebdInt() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>long</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>0</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    long rebdLong() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>flobt</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>0</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    flobt rebdFlobt() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>double</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>0</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    double rebdDouble() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>jbvb.mbth.BigDecimbl</code>
     * object in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    jbvb.mbth.BigDecimbl rebdBigDecimbl() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs bn brrby of bytes
     * in the Jbvb progrbmming lbngubge.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    byte[] rebdBytes() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>jbvb.sql.Dbte</code> object.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    jbvb.sql.Dbte rebdDbte() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>jbvb.sql.Time</code> object.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    jbvb.sql.Time rebdTime() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>jbvb.sql.Timestbmp</code> object.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    jbvb.sql.Timestbmp rebdTimestbmp() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b strebm of Unicode chbrbcters.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    jbvb.io.Rebder rebdChbrbcterStrebm() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b strebm of ASCII chbrbcters.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    jbvb.io.InputStrebm rebdAsciiStrebm() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b strebm of uninterpreted
     * bytes.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    jbvb.io.InputStrebm rebdBinbryStrebm() throws SQLException;

    //================================================================
    // Methods for rebding items of SQL user-defined types from the strebm.
    //================================================================

    /**
     * Rebds the dbtum bt the hebd of the strebm bnd returns it bs bn
     * <code>Object</code> in the Jbvb progrbmming lbngubge.  The
     * bctubl type of the object returned is determined by the defbult type
     * mbpping, bnd bny customizbtions present in this strebm's type mbp.
     *
     * <P>A type mbp is registered with the strebm by the JDBC driver before the
     * strebm is pbssed to the bpplicbtion.
     *
     * <P>When the dbtum bt the hebd of the strebm is bn SQL <code>NULL</code>,
     * the method returns <code>null</code>.  If the dbtum is bn SQL structured or distinct
     * type, it determines the SQL type of the dbtum bt the hebd of the strebm.
     * If the strebm's type mbp hbs bn entry for thbt SQL type, the driver
     * constructs bn object of the bppropribte clbss bnd cblls the method
     * <code>SQLDbtb.rebdSQL</code> on thbt object, which rebds bdditionbl dbtb from the
     * strebm, using the protocol described for thbt method.
     *
     * @return the dbtum bt the hebd of the strebm bs bn <code>Object</code> in the
     * Jbvb progrbmming lbngubge;<code>null</code> if the dbtum is SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Object rebdObject() throws SQLException;

    /**
     * Rebds bn SQL <code>REF</code> vblue from the strebm bnd returns it bs b
     * <code>Ref</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>Ref</code> object representing the SQL <code>REF</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Ref rebdRef() throws SQLException;

    /**
     * Rebds bn SQL <code>BLOB</code> vblue from the strebm bnd returns it bs b
     * <code>Blob</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>Blob</code> object representing dbtb of the SQL <code>BLOB</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Blob rebdBlob() throws SQLException;

    /**
     * Rebds bn SQL <code>CLOB</code> vblue from the strebm bnd returns it bs b
     * <code>Clob</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>Clob</code> object representing dbtb of the SQL <code>CLOB</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Clob rebdClob() throws SQLException;

    /**
     * Rebds bn SQL <code>ARRAY</code> vblue from the strebm bnd returns it bs bn
     * <code>Arrby</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return bn <code>Arrby</code> object representing dbtb of the SQL
     * <code>ARRAY</code> vblue bt the hebd of the strebm; <code>null</code>
     * if the vblue rebd is SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Arrby rebdArrby() throws SQLException;

    /**
     * Retrieves whether the lbst vblue rebd wbs SQL <code>NULL</code>.
     *
     * @return <code>true</code> if the most recently rebd SQL vblue wbs SQL
     * <code>NULL</code>; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     *
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    boolebn wbsNull() throws SQLException;

    //---------------------------- JDBC 3.0 -------------------------

    /**
     * Rebds bn SQL <code>DATALINK</code> vblue from the strebm bnd returns it bs b
     * <code>jbvb.net.URL</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>jbvb.net.URL</code> object.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     *            or if b URL is mblformed
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    jbvb.net.URL rebdURL() throws SQLException;

     //---------------------------- JDBC 4.0 -------------------------

    /**
     * Rebds bn SQL <code>NCLOB</code> vblue from the strebm bnd returns it bs b
     * <code>NClob</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>NClob</code> object representing dbtb of the SQL <code>NCLOB</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    NClob rebdNClob() throws SQLException;

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs b <code>String</code>
     * in the Jbvb progrbmming lbngubge. It is intended for use when
     * bccessing  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> columns.
     *
     * @return the bttribute; if the vblue is SQL <code>NULL</code>, returns <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    String rebdNString() throws SQLException;

    /**
     * Rebds bn SQL <code>XML</code> vblue from the strebm bnd returns it bs b
     * <code>SQLXML</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>SQLXML</code> object representing dbtb of the SQL <code>XML</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    SQLXML rebdSQLXML() throws SQLException;

    /**
     * Rebds bn SQL <code>ROWID</code> vblue from the strebm bnd returns it bs b
     * <code>RowId</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>RowId</code> object representing dbtb of the SQL <code>ROWID</code> vblue
     * bt the hebd of the strebm; <code>null</code> if the vblue rebd is
     * SQL <code>NULL</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    RowId rebdRowId() throws SQLException;

    //--------------------------JDBC 4.2 -----------------------------

    /**
     * Rebds the next bttribute in the strebm bnd returns it bs bn
     * {@code Object} in the Jbvb progrbmming lbngubge. The
     * bctubl type of the object returned is determined by the specified
     * Jbvb dbtb type, bnd bny customizbtions present in this
     * strebm's type mbp.
     *
     * <P>A type mbp is registered with the strebm by the JDBC driver before the
     * strebm is pbssed to the bpplicbtion.
     *
     * <P>When the bttribute bt the hebd of the strebm is bn SQL {@code NULL}
     * the method returns {@code null}. If the bttribute is bn SQL
     * structured or distinct
     * type, it determines the SQL type of the bttribute bt the hebd of the strebm.
     * If the strebm's type mbp hbs bn entry for thbt SQL type, the driver
     * constructs bn object of the bppropribte clbss bnd cblls the method
     * {@code SQLDbtb.rebdSQL} on thbt object, which rebds bdditionbl dbtb from the
     * strebm, using the protocol described for thbt method.
     *<p>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm <T> the type of the clbss modeled by this Clbss object
     * @pbrbm type Clbss representing the Jbvb dbtb type to convert the bttribute to.
     * @return the bttribute bt the hebd of the strebm bs bn {@code Object} in the
     * Jbvb progrbmming lbngubge;{@code null} if the bttribute is SQL {@code NULL}
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.8
     */
    defbult <T> T rebdObject(Clbss<T> type) throws SQLException {
       throw new SQLFebtureNotSupportedException();
    }
}
