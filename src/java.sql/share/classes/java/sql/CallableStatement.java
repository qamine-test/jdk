/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.mbth.BigDecimbl;
import jbvb.util.Cblendbr;
import jbvb.io.Rebder;
import jbvb.io.InputStrebm;

/**
 * The interfbce used to execute SQL stored procedures.  The JDBC API
 * provides b stored procedure SQL escbpe syntbx thbt bllows stored procedures
 * to be cblled in b stbndbrd wby for bll RDBMSs. This escbpe syntbx hbs one
 * form thbt includes b result pbrbmeter bnd one thbt does not. If used, the result
 * pbrbmeter must be registered bs bn OUT pbrbmeter. The other pbrbmeters
 * cbn be used for input, output or both. Pbrbmeters bre referred to
 * sequentiblly, by number, with the first pbrbmeter being 1.
 * <PRE>
 *   {?= cbll &lt;procedure-nbme&gt;[(&lt;brg1&gt;,&lt;brg2&gt;, ...)]}
 *   {cbll &lt;procedure-nbme&gt;[(&lt;brg1&gt;,&lt;brg2&gt;, ...)]}
 * </PRE>
 * <P>
 * IN pbrbmeter vblues bre set using the <code>set</code> methods inherited from
 * {@link PrepbredStbtement}.  The type of bll OUT pbrbmeters must be
 * registered prior to executing the stored procedure; their vblues
 * bre retrieved bfter execution vib the <code>get</code> methods provided here.
 * <P>
 * A <code>CbllbbleStbtement</code> cbn return one {@link ResultSet} object or
 * multiple <code>ResultSet</code> objects.  Multiple
 * <code>ResultSet</code> objects bre hbndled using operbtions
 * inherited from {@link Stbtement}.
 * <P>
 * For mbximum portbbility, b cbll's <code>ResultSet</code> objects bnd
 * updbte counts should be processed prior to getting the vblues of output
 * pbrbmeters.
 *
 *
 * @see Connection#prepbreCbll
 * @see ResultSet
 */

public interfbce CbllbbleStbtement extends PrepbredStbtement {

    /**
     * Registers the OUT pbrbmeter in ordinbl position
     * <code>pbrbmeterIndex</code> to the JDBC type
     * <code>sqlType</code>.  All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * The JDBC type specified by <code>sqlType</code> for bn OUT
     * pbrbmeter determines the Jbvb type thbt must be used
     * in the <code>get</code> method to rebd the vblue of thbt pbrbmeter.
     * <p>
     * If the JDBC type expected to be returned to this output pbrbmeter
     * is specific to this pbrticulbr dbtbbbse, <code>sqlType</code>
     * should be <code>jbvb.sql.Types.OTHER</code>.  The method
     * {@link #getObject} retrieves the vblue.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @pbrbm sqlType the JDBC type code defined by <code>jbvb.sql.Types</code>.
     *        If the pbrbmeter is of JDBC type <code>NUMERIC</code>
     *        or <code>DECIMAL</code>, the version of
     *        <code>registerOutPbrbmeter</code> thbt bccepts b scble vblue
     *        should be used.
     *
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if <code>sqlType</code> is
     * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
     * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
     * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
     *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
     * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
     * this dbtb type
     * @see Types
     */
    void registerOutPbrbmeter(int pbrbmeterIndex, int sqlType)
        throws SQLException;

    /**
     * Registers the pbrbmeter in ordinbl position
     * <code>pbrbmeterIndex</code> to be of JDBC type
     * <code>sqlType</code>. All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * The JDBC type specified by <code>sqlType</code> for bn OUT
     * pbrbmeter determines the Jbvb type thbt must be used
     * in the <code>get</code> method to rebd the vblue of thbt pbrbmeter.
     * <p>
     * This version of <code>registerOutPbrbmeter</code> should be
     * used when the pbrbmeter is of JDBC type <code>NUMERIC</code>
     * or <code>DECIMAL</code>.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @pbrbm sqlType the SQL type code defined by <code>jbvb.sql.Types</code>.
     * @pbrbm scble the desired number of digits to the right of the
     * decimbl point.  It must be grebter thbn or equbl to zero.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if <code>sqlType</code> is
     * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
     * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
     * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
     *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
     * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
     * this dbtb type
     * @see Types
     */
    void registerOutPbrbmeter(int pbrbmeterIndex, int sqlType, int scble)
        throws SQLException;

    /**
     * Retrieves whether the lbst OUT pbrbmeter rebd hbd the vblue of
     * SQL <code>NULL</code>.  Note thbt this method should be cblled only bfter
     * cblling b getter method; otherwise, there is no vblue to use in
     * determining whether it is <code>null</code> or not.
     *
     * @return <code>true</code> if the lbst pbrbmeter rebd wbs SQL
     * <code>NULL</code>; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     */
    boolebn wbsNull() throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>CHAR</code>,
     * <code>VARCHAR</code>, or <code>LONGVARCHAR</code> pbrbmeter bs b
     * <code>String</code> in the Jbvb progrbmming lbngubge.
     * <p>
     * For the fixed-length type JDBC <code>CHAR</code>,
     * the <code>String</code> object
     * returned hbs exbctly the sbme vblue the SQL
     * <code>CHAR</code> vblue hbd in the
     * dbtbbbse, including bny pbdding bdded by the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @return the pbrbmeter vblue. If the vblue is SQL <code>NULL</code>,
     *         the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setString
     */
    String getString(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>BIT</code>
     * or <code>BOOLEAN</code> pbrbmeter bs b
     * <code>boolebn</code> in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>,
     *         the result is <code>fblse</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setBoolebn
     */
    boolebn getBoolebn(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>TINYINT</code> pbrbmeter
     * bs b <code>byte</code> in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>0</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setByte
     */
    byte getByte(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>SMALLINT</code> pbrbmeter
     * bs b <code>short</code> in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>0</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setShort
     */
    short getShort(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>INTEGER</code> pbrbmeter
     * bs bn <code>int</code> in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>0</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setInt
     */
    int getInt(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>BIGINT</code> pbrbmeter
     * bs b <code>long</code> in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>0</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setLong
     */
    long getLong(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>FLOAT</code> pbrbmeter
     * bs b <code>flobt</code> in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>0</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setFlobt
     */
    flobt getFlobt(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>DOUBLE</code> pbrbmeter bs b <code>double</code>
     * in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>0</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setDouble
     */
    double getDouble(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>NUMERIC</code> pbrbmeter bs b
     * <code>jbvb.mbth.BigDecimbl</code> object with <i>scble</i> digits to
     * the right of the decimbl point.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @pbrbm scble the number of digits to the right of the decimbl point
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @deprecbted use <code>getBigDecimbl(int pbrbmeterIndex)</code>
     *             or <code>getBigDecimbl(String pbrbmeterNbme)</code>
     * @see #setBigDecimbl
     */
    @Deprecbted
    BigDecimbl getBigDecimbl(int pbrbmeterIndex, int scble)
        throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>BINARY</code> or
     * <code>VARBINARY</code> pbrbmeter bs bn brrby of <code>byte</code>
     * vblues in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setBytes
     */
    byte[] getBytes(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>DATE</code> pbrbmeter bs b
     * <code>jbvb.sql.Dbte</code> object.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setDbte
     */
    jbvb.sql.Dbte getDbte(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>TIME</code> pbrbmeter bs b
     * <code>jbvb.sql.Time</code> object.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setTime
     */
    jbvb.sql.Time getTime(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>TIMESTAMP</code> pbrbmeter bs b
     * <code>jbvb.sql.Timestbmp</code> object.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setTimestbmp
     */
    jbvb.sql.Timestbmp getTimestbmp(int pbrbmeterIndex)
        throws SQLException;

    //----------------------------------------------------------------------
    // Advbnced febtures:


    /**
     * Retrieves the vblue of the designbted pbrbmeter bs bn <code>Object</code>
     * in the Jbvb progrbmming lbngubge. If the vblue is bn SQL <code>NULL</code>,
     * the driver returns b Jbvb <code>null</code>.
     * <p>
     * This method returns b Jbvb object whose type corresponds to the JDBC
     * type thbt wbs registered for this pbrbmeter using the method
     * <code>registerOutPbrbmeter</code>.  By registering the tbrget JDBC
     * type bs <code>jbvb.sql.Types.OTHER</code>, this method cbn be used
     * to rebd dbtbbbse-specific bbstrbct dbtb types.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @return A <code>jbvb.lbng.Object</code> holding the OUT pbrbmeter vblue
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see Types
     * @see #setObject
     */
    Object getObject(int pbrbmeterIndex) throws SQLException;


    //--------------------------JDBC 2.0-----------------------------

    /**
     * Retrieves the vblue of the designbted JDBC <code>NUMERIC</code> pbrbmeter bs b
     * <code>jbvb.mbth.BigDecimbl</code> object with bs mbny digits to the
     * right of the decimbl point bs the vblue contbins.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @return the pbrbmeter vblue in full precision.  If the vblue is
     * SQL <code>NULL</code>, the result is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setBigDecimbl
     * @since 1.2
     */
    BigDecimbl getBigDecimbl(int pbrbmeterIndex) throws SQLException;

    /**
     * Returns bn object representing the vblue of OUT pbrbmeter
     * <code>pbrbmeterIndex</code> bnd uses <code>mbp</code> for the custom
     * mbpping of the pbrbmeter vblue.
     * <p>
     * This method returns b Jbvb object whose type corresponds to the
     * JDBC type thbt wbs registered for this pbrbmeter using the method
     * <code>registerOutPbrbmeter</code>.  By registering the tbrget
     * JDBC type bs <code>jbvb.sql.Types.OTHER</code>, this method cbn
     * be used to rebd dbtbbbse-specific bbstrbct dbtb types.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, bnd so on
     * @pbrbm mbp the mbpping from SQL type nbmes to Jbvb clbsses
     * @return b <code>jbvb.lbng.Object</code> holding the OUT pbrbmeter vblue
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setObject
     * @since 1.2
     */
    Object getObject(int pbrbmeterIndex, jbvb.util.Mbp<String,Clbss<?>> mbp)
        throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>REF(&lt;structured-type&gt;)</code>
     * pbrbmeter bs b {@link jbvb.sql.Ref} object in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @return the pbrbmeter vblue bs b <code>Ref</code> object in the
     * Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>, the vblue
     * <code>null</code> is returned.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Ref getRef (int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>BLOB</code> pbrbmeter bs b
     * {@link jbvb.sql.Blob} object in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, bnd so on
     * @return the pbrbmeter vblue bs b <code>Blob</code> object in the
     * Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>, the vblue
     * <code>null</code> is returned.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Blob getBlob (int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>CLOB</code> pbrbmeter bs b
     * <code>jbvb.sql.Clob</code> object in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, bnd
     * so on
     * @return the pbrbmeter vblue bs b <code>Clob</code> object in the
     * Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>, the
     * vblue <code>null</code> is returned.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Clob getClob (int pbrbmeterIndex) throws SQLException;

    /**
     *
     * Retrieves the vblue of the designbted JDBC <code>ARRAY</code> pbrbmeter bs bn
     * {@link jbvb.sql.Arrby} object in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, bnd
     * so on
     * @return the pbrbmeter vblue bs bn <code>Arrby</code> object in
     * the Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>, the
     * vblue <code>null</code> is returned.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Arrby getArrby (int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>DATE</code> pbrbmeter bs b
     * <code>jbvb.sql.Dbte</code> object, using
     * the given <code>Cblendbr</code> object
     * to construct the dbte.
     * With b <code>Cblendbr</code> object, the driver
     * cbn cblculbte the dbte tbking into bccount b custom timezone bnd locble.
     * If no <code>Cblendbr</code> object is specified, the driver uses the
     * defbult timezone bnd locble.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the dbte
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setDbte
     * @since 1.2
     */
    jbvb.sql.Dbte getDbte(int pbrbmeterIndex, Cblendbr cbl)
        throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>TIME</code> pbrbmeter bs b
     * <code>jbvb.sql.Time</code> object, using
     * the given <code>Cblendbr</code> object
     * to construct the time.
     * With b <code>Cblendbr</code> object, the driver
     * cbn cblculbte the time tbking into bccount b custom timezone bnd locble.
     * If no <code>Cblendbr</code> object is specified, the driver uses the
     * defbult timezone bnd locble.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the time
     * @return the pbrbmeter vblue; if the vblue is SQL <code>NULL</code>, the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setTime
     * @since 1.2
     */
    jbvb.sql.Time getTime(int pbrbmeterIndex, Cblendbr cbl)
        throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>TIMESTAMP</code> pbrbmeter bs b
     * <code>jbvb.sql.Timestbmp</code> object, using
     * the given <code>Cblendbr</code> object to construct
     * the <code>Timestbmp</code> object.
     * With b <code>Cblendbr</code> object, the driver
     * cbn cblculbte the timestbmp tbking into bccount b custom timezone bnd locble.
     * If no <code>Cblendbr</code> object is specified, the driver uses the
     * defbult timezone bnd locble.
     *
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the timestbmp
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     *         is <code>null</code>.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #setTimestbmp
     * @since 1.2
     */
    jbvb.sql.Timestbmp getTimestbmp(int pbrbmeterIndex, Cblendbr cbl)
        throws SQLException;


    /**
     * Registers the designbted output pbrbmeter.
     * This version of
     * the method <code>registerOutPbrbmeter</code>
     * should be used for b user-defined or <code>REF</code> output pbrbmeter.  Exbmples
     * of user-defined types include: <code>STRUCT</code>, <code>DISTINCT</code>,
     * <code>JAVA_OBJECT</code>, bnd nbmed brrby types.
     *<p>
     * All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>  For b user-defined pbrbmeter, the fully-qublified SQL
     * type nbme of the pbrbmeter should blso be given, while b <code>REF</code>
     * pbrbmeter requires thbt the fully-qublified type nbme of the
     * referenced type be given.  A JDBC driver thbt does not need the
     * type code bnd type nbme informbtion mby ignore it.   To be portbble,
     * however, bpplicbtions should blwbys provide these vblues for
     * user-defined bnd <code>REF</code> pbrbmeters.
     *
     * Although it is intended for user-defined bnd <code>REF</code> pbrbmeters,
     * this method mby be used to register b pbrbmeter of bny JDBC type.
     * If the pbrbmeter does not hbve b user-defined or <code>REF</code> type, the
     * <i>typeNbme</i> pbrbmeter is ignored.
     *
     * <P><B>Note:</B> When rebding the vblue of bn out pbrbmeter, you
     * must use the getter method whose Jbvb type corresponds to the
     * pbrbmeter's registered SQL type.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,...
     * @pbrbm sqlType b vblue from {@link jbvb.sql.Types}
     * @pbrbm typeNbme the fully-qublified nbme of bn SQL structured type
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if <code>sqlType</code> is
     * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
     * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
     * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
     *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
     * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
     * this dbtb type
     * @see Types
     * @since 1.2
     */
    void registerOutPbrbmeter (int pbrbmeterIndex, int sqlType, String typeNbme)
        throws SQLException;

  //--------------------------JDBC 3.0-----------------------------

    /**
     * Registers the OUT pbrbmeter nbmed
     * <code>pbrbmeterNbme</code> to the JDBC type
     * <code>sqlType</code>.  All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * The JDBC type specified by <code>sqlType</code> for bn OUT
     * pbrbmeter determines the Jbvb type thbt must be used
     * in the <code>get</code> method to rebd the vblue of thbt pbrbmeter.
     * <p>
     * If the JDBC type expected to be returned to this output pbrbmeter
     * is specific to this pbrticulbr dbtbbbse, <code>sqlType</code>
     * should be <code>jbvb.sql.Types.OTHER</code>.  The method
     * {@link #getObject} retrieves the vblue.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm sqlType the JDBC type code defined by <code>jbvb.sql.Types</code>.
     * If the pbrbmeter is of JDBC type <code>NUMERIC</code>
     * or <code>DECIMAL</code>, the version of
     * <code>registerOutPbrbmeter</code> thbt bccepts b scble vblue
     * should be used.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if <code>sqlType</code> is
     * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
     * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
     * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
     *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
     * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
     * this dbtb type or if the JDBC driver does not support
     * this method
     * @since 1.4
     * @see Types
     */
    void registerOutPbrbmeter(String pbrbmeterNbme, int sqlType)
        throws SQLException;

    /**
     * Registers the pbrbmeter nbmed
     * <code>pbrbmeterNbme</code> to be of JDBC type
     * <code>sqlType</code>.  All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * The JDBC type specified by <code>sqlType</code> for bn OUT
     * pbrbmeter determines the Jbvb type thbt must be used
     * in the <code>get</code> method to rebd the vblue of thbt pbrbmeter.
     * <p>
     * This version of <code>registerOutPbrbmeter</code> should be
     * used when the pbrbmeter is of JDBC type <code>NUMERIC</code>
     * or <code>DECIMAL</code>.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm sqlType SQL type code defined by <code>jbvb.sql.Types</code>.
     * @pbrbm scble the desired number of digits to the right of the
     * decimbl point.  It must be grebter thbn or equbl to zero.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if <code>sqlType</code> is
     * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
     * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
     * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
     *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
     * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
     * this dbtb type or if the JDBC driver does not support
     * this method
     * @since 1.4
     * @see Types
     */
    void registerOutPbrbmeter(String pbrbmeterNbme, int sqlType, int scble)
        throws SQLException;

    /**
     * Registers the designbted output pbrbmeter.  This version of
     * the method <code>registerOutPbrbmeter</code>
     * should be used for b user-nbmed or REF output pbrbmeter.  Exbmples
     * of user-nbmed types include: STRUCT, DISTINCT, JAVA_OBJECT, bnd
     * nbmed brrby types.
     *<p>
     * All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * For b user-nbmed pbrbmeter the fully-qublified SQL
     * type nbme of the pbrbmeter should blso be given, while b REF
     * pbrbmeter requires thbt the fully-qublified type nbme of the
     * referenced type be given.  A JDBC driver thbt does not need the
     * type code bnd type nbme informbtion mby ignore it.   To be portbble,
     * however, bpplicbtions should blwbys provide these vblues for
     * user-nbmed bnd REF pbrbmeters.
     *
     * Although it is intended for user-nbmed bnd REF pbrbmeters,
     * this method mby be used to register b pbrbmeter of bny JDBC type.
     * If the pbrbmeter does not hbve b user-nbmed or REF type, the
     * typeNbme pbrbmeter is ignored.
     *
     * <P><B>Note:</B> When rebding the vblue of bn out pbrbmeter, you
     * must use the <code>getXXX</code> method whose Jbvb type XXX corresponds to the
     * pbrbmeter's registered SQL type.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm sqlType b vblue from {@link jbvb.sql.Types}
     * @pbrbm typeNbme the fully-qublified nbme of bn SQL structured type
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if <code>sqlType</code> is
     * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
     * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
     * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
     *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
     * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
     * this dbtb type or if the JDBC driver does not support
     * this method
     * @see Types
     * @since 1.4
     */
    void registerOutPbrbmeter (String pbrbmeterNbme, int sqlType, String typeNbme)
        throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>DATALINK</code> pbrbmeter bs b
     * <code>jbvb.net.URL</code> object.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,...
     * @return b <code>jbvb.net.URL</code> object thbt represents the
     *         JDBC <code>DATALINK</code> vblue used bs the designbted
     *         pbrbmeter
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>CbllbbleStbtement</code>,
     *            or if the URL being returned is
     *            not b vblid URL on the Jbvb plbtform
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setURL
     * @since 1.4
     */
    jbvb.net.URL getURL(int pbrbmeterIndex) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.net.URL</code> object.
     * The driver converts this to bn SQL <code>DATALINK</code> vblue when
     * it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm vbl the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     *            or if b URL is mblformed
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getURL
     * @since 1.4
     */
    void setURL(String pbrbmeterNbme, jbvb.net.URL vbl) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to SQL <code>NULL</code>.
     *
     * <P><B>Note:</B> You must specify the pbrbmeter's SQL type.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm sqlType the SQL type code defined in <code>jbvb.sql.Types</code>
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    void setNull(String pbrbmeterNbme, int sqlType) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>boolebn</code> vblue.
     * The driver converts this
     * to bn SQL <code>BIT</code> or <code>BOOLEAN</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @see #getBoolebn
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    void setBoolebn(String pbrbmeterNbme, boolebn x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>byte</code> vblue.
     * The driver converts this
     * to bn SQL <code>TINYINT</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getByte
     * @since 1.4
     */
    void setByte(String pbrbmeterNbme, byte x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>short</code> vblue.
     * The driver converts this
     * to bn SQL <code>SMALLINT</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getShort
     * @since 1.4
     */
    void setShort(String pbrbmeterNbme, short x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>int</code> vblue.
     * The driver converts this
     * to bn SQL <code>INTEGER</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getInt
     * @since 1.4
     */
    void setInt(String pbrbmeterNbme, int x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>long</code> vblue.
     * The driver converts this
     * to bn SQL <code>BIGINT</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getLong
     * @since 1.4
     */
    void setLong(String pbrbmeterNbme, long x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>flobt</code> vblue.
     * The driver converts this
     * to bn SQL <code>FLOAT</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getFlobt
     * @since 1.4
     */
    void setFlobt(String pbrbmeterNbme, flobt x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>double</code> vblue.
     * The driver converts this
     * to bn SQL <code>DOUBLE</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getDouble
     * @since 1.4
     */
    void setDouble(String pbrbmeterNbme, double x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given
     * <code>jbvb.mbth.BigDecimbl</code> vblue.
     * The driver converts this to bn SQL <code>NUMERIC</code> vblue when
     * it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getBigDecimbl
     * @since 1.4
     */
    void setBigDecimbl(String pbrbmeterNbme, BigDecimbl x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>String</code> vblue.
     * The driver converts this
     * to bn SQL <code>VARCHAR</code> or <code>LONGVARCHAR</code> vblue
     * (depending on the brgument's
     * size relbtive to the driver's limits on <code>VARCHAR</code> vblues)
     * when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getString
     * @since 1.4
     */
    void setString(String pbrbmeterNbme, String x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb brrby of bytes.
     * The driver converts this to bn SQL <code>VARBINARY</code> or
     * <code>LONGVARBINARY</code> (depending on the brgument's size relbtive
     * to the driver's limits on <code>VARBINARY</code> vblues) when it sends
     * it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getBytes
     * @since 1.4
     */
    void setBytes(String pbrbmeterNbme, byte x[]) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Dbte</code> vblue
     * using the defbult time zone of the virtubl mbchine thbt is running
     * the bpplicbtion.
     * The driver converts this
     * to bn SQL <code>DATE</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getDbte
     * @since 1.4
     */
    void setDbte(String pbrbmeterNbme, jbvb.sql.Dbte x)
        throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Time</code> vblue.
     * The driver converts this
     * to bn SQL <code>TIME</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getTime
     * @since 1.4
     */
    void setTime(String pbrbmeterNbme, jbvb.sql.Time x)
        throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Timestbmp</code> vblue.
     * The driver
     * converts this to bn SQL <code>TIMESTAMP</code> vblue when it sends it to the
     * dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getTimestbmp
     * @since 1.4
     */
    void setTimestbmp(String pbrbmeterNbme, jbvb.sql.Timestbmp x)
        throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given input strebm, which will hbve
     * the specified number of bytes.
     * When b very lbrge ASCII vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code>. Dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from ASCII to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the Jbvb input strebm thbt contbins the ASCII pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    void setAsciiStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x, int length)
        throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given input strebm, which will hbve
     * the specified number of bytes.
     * When b very lbrge binbry vblue is input to b <code>LONGVARBINARY</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code> object. The dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the jbvb input strebm which contbins the binbry pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    void setBinbryStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x,
                         int length) throws SQLException;

    /**
     * Sets the vblue of the designbted pbrbmeter with the given object.
     *
     * <p>The given Jbvb object will be converted to the given tbrgetSqlType
     * before being sent to the dbtbbbse.
     *
     * If the object hbs b custom mbpping (is of b clbss implementing the
     * interfbce <code>SQLDbtb</code>),
     * the JDBC driver should cbll the method <code>SQLDbtb.writeSQL</code> to write it
     * to the SQL dbtb strebm.
     * If, on the other hbnd, the object is of b clbss implementing
     * <code>Ref</code>, <code>Blob</code>, <code>Clob</code>,  <code>NClob</code>,
     *  <code>Struct</code>, <code>jbvb.net.URL</code>,
     * or <code>Arrby</code>, the driver should pbss it to the dbtbbbse bs b
     * vblue of the corresponding SQL type.
     * <P>
     * Note thbt this method mby be used to pbss dbtbtbbbse-
     * specific bbstrbct dbtb types.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @pbrbm tbrgetSqlType the SQL type (bs defined in jbvb.sql.Types) to be
     * sent to the dbtbbbse. The scble brgument mby further qublify this type.
     * @pbrbm scble for jbvb.sql.Types.DECIMAL or jbvb.sql.Types.NUMERIC types,
     *          this is the number of digits bfter the decimbl point.  For bll other
     *          types, this vblue will be ignored.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified tbrgetSqlType
     * @see Types
     * @see #getObject
     * @since 1.4
     */
    void setObject(String pbrbmeterNbme, Object x, int tbrgetSqlType, int scble)
        throws SQLException;

    /**
     * Sets the vblue of the designbted pbrbmeter with the given object.
     *
     * This method is similbr to {@link #setObject(String pbrbmeterNbme,
     * Object x, int tbrgetSqlType, int scbleOrLength)},
     * except thbt it bssumes b scble of zero.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @pbrbm tbrgetSqlType the SQL type (bs defined in jbvb.sql.Types) to be
     *                      sent to the dbtbbbse
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified tbrgetSqlType
     * @see #getObject
     * @since 1.4
     */
    void setObject(String pbrbmeterNbme, Object x, int tbrgetSqlType)
        throws SQLException;

    /**
     * Sets the vblue of the designbted pbrbmeter with the given object.
     *
     * <p>The JDBC specificbtion specifies b stbndbrd mbpping from
     * Jbvb <code>Object</code> types to SQL types.  The given brgument
     * will be converted to the corresponding SQL type before being
     * sent to the dbtbbbse.
     * <p>Note thbt this method mby be used to pbss dbtbtbbbse-
     * specific bbstrbct dbtb types, by using b driver-specific Jbvb
     * type.
     *
     * If the object is of b clbss implementing the interfbce <code>SQLDbtb</code>,
     * the JDBC driver should cbll the method <code>SQLDbtb.writeSQL</code>
     * to write it to the SQL dbtb strebm.
     * If, on the other hbnd, the object is of b clbss implementing
     * <code>Ref</code>, <code>Blob</code>, <code>Clob</code>,  <code>NClob</code>,
     *  <code>Struct</code>, <code>jbvb.net.URL</code>,
     * or <code>Arrby</code>, the driver should pbss it to the dbtbbbse bs b
     * vblue of the corresponding SQL type.
     * <P>
     * This method throws bn exception if there is bn bmbiguity, for exbmple, if the
     * object is of b clbss implementing more thbn one of the interfbces nbmed bbove.
     * <p>
     *<b>Note:</b> Not bll dbtbbbses bllow for b non-typed Null to be sent to
     * the bbckend. For mbximum portbbility, the <code>setNull</code> or the
     * <code>setObject(String pbrbmeterNbme, Object x, int sqlType)</code>
     * method should be used
     * instebd of <code>setObject(String pbrbmeterNbme, Object x)</code>.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>CbllbbleStbtement</code> or if the given
     *            <code>Object</code> pbrbmeter is bmbiguous
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getObject
     * @since 1.4
     */
    void setObject(String pbrbmeterNbme, Object x) throws SQLException;


    /**
     * Sets the designbted pbrbmeter to the given <code>Rebder</code>
     * object, which is the given number of chbrbcters long.
     * When b very lbrge UNICODE vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.Rebder</code> object. The dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from UNICODE to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object thbt
     *        contbins the UNICODE dbtb used bs the designbted pbrbmeter
     * @pbrbm length the number of chbrbcters in the strebm
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    void setChbrbcterStrebm(String pbrbmeterNbme,
                            jbvb.io.Rebder rebder,
                            int length) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Dbte</code> vblue,
     * using the given <code>Cblendbr</code> object.  The driver uses
     * the <code>Cblendbr</code> object to construct bn SQL <code>DATE</code> vblue,
     * which the driver then sends to the dbtbbbse.  With b
     * b <code>Cblendbr</code> object, the driver cbn cblculbte the dbte
     * tbking into bccount b custom timezone.  If no
     * <code>Cblendbr</code> object is specified, the driver uses the defbult
     * timezone, which is thbt of the virtubl mbchine running the bpplicbtion.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the dbte
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getDbte
     * @since 1.4
     */
    void setDbte(String pbrbmeterNbme, jbvb.sql.Dbte x, Cblendbr cbl)
        throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Time</code> vblue,
     * using the given <code>Cblendbr</code> object.  The driver uses
     * the <code>Cblendbr</code> object to construct bn SQL <code>TIME</code> vblue,
     * which the driver then sends to the dbtbbbse.  With b
     * b <code>Cblendbr</code> object, the driver cbn cblculbte the time
     * tbking into bccount b custom timezone.  If no
     * <code>Cblendbr</code> object is specified, the driver uses the defbult
     * timezone, which is thbt of the virtubl mbchine running the bpplicbtion.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the time
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getTime
     * @since 1.4
     */
    void setTime(String pbrbmeterNbme, jbvb.sql.Time x, Cblendbr cbl)
        throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Timestbmp</code> vblue,
     * using the given <code>Cblendbr</code> object.  The driver uses
     * the <code>Cblendbr</code> object to construct bn SQL <code>TIMESTAMP</code> vblue,
     * which the driver then sends to the dbtbbbse.  With b
     * b <code>Cblendbr</code> object, the driver cbn cblculbte the timestbmp
     * tbking into bccount b custom timezone.  If no
     * <code>Cblendbr</code> object is specified, the driver uses the defbult
     * timezone, which is thbt of the virtubl mbchine running the bpplicbtion.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the timestbmp
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #getTimestbmp
     * @since 1.4
     */
    void setTimestbmp(String pbrbmeterNbme, jbvb.sql.Timestbmp x, Cblendbr cbl)
        throws SQLException;

    /**
     * Sets the designbted pbrbmeter to SQL <code>NULL</code>.
     * This version of the method <code>setNull</code> should
     * be used for user-defined types bnd REF type pbrbmeters.  Exbmples
     * of user-defined types include: STRUCT, DISTINCT, JAVA_OBJECT, bnd
     * nbmed brrby types.
     *
     * <P><B>Note:</B> To be portbble, bpplicbtions must give the
     * SQL type code bnd the fully-qublified SQL type nbme when specifying
     * b NULL user-defined or REF pbrbmeter.  In the cbse of b user-defined type
     * the nbme is the type nbme of the pbrbmeter itself.  For b REF
     * pbrbmeter, the nbme is the type nbme of the referenced type.
     * <p>
     * Although it is intended for user-defined bnd Ref pbrbmeters,
     * this method mby be used to set b null pbrbmeter of bny JDBC type.
     * If the pbrbmeter does not hbve b user-defined or REF type, the given
     * typeNbme is ignored.
     *
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm sqlType b vblue from <code>jbvb.sql.Types</code>
     * @pbrbm typeNbme the fully-qublified nbme of bn SQL user-defined type;
     *        ignored if the pbrbmeter is not b user-defined type or
     *        SQL <code>REF</code> vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    void setNull (String pbrbmeterNbme, int sqlType, String typeNbme)
        throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>CHAR</code>, <code>VARCHAR</code>,
     * or <code>LONGVARCHAR</code> pbrbmeter bs b <code>String</code> in
     * the Jbvb progrbmming lbngubge.
     * <p>
     * For the fixed-length type JDBC <code>CHAR</code>,
     * the <code>String</code> object
     * returned hbs exbctly the sbme vblue the SQL
     * <code>CHAR</code> vblue hbd in the
     * dbtbbbse, including bny pbdding bdded by the dbtbbbse.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue. If the vblue is SQL <code>NULL</code>, the result
     * is <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setString
     * @since 1.4
     */
    String getString(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>BIT</code> or <code>BOOLEAN</code>
     * pbrbmeter bs b
     * <code>boolebn</code> in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>fblse</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setBoolebn
     * @since 1.4
     */
    boolebn getBoolebn(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>TINYINT</code> pbrbmeter bs b <code>byte</code>
     * in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>0</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setByte
     * @since 1.4
     */
    byte getByte(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>SMALLINT</code> pbrbmeter bs b <code>short</code>
     * in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>0</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setShort
     * @since 1.4
     */
    short getShort(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>INTEGER</code> pbrbmeter bs bn <code>int</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>,
     *         the result is <code>0</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setInt
     * @since 1.4
     */
    int getInt(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>BIGINT</code> pbrbmeter bs b <code>long</code>
     * in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>,
     *         the result is <code>0</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setLong
     * @since 1.4
     */
    long getLong(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>FLOAT</code> pbrbmeter bs b <code>flobt</code>
     * in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>,
     *         the result is <code>0</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setFlobt
     * @since 1.4
     */
    flobt getFlobt(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>DOUBLE</code> pbrbmeter bs b <code>double</code>
     * in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>,
     *         the result is <code>0</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setDouble
     * @since 1.4
     */
    double getDouble(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>BINARY</code> or <code>VARBINARY</code>
     * pbrbmeter bs bn brrby of <code>byte</code> vblues in the Jbvb
     * progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result is
     *  <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setBytes
     * @since 1.4
     */
    byte[] getBytes(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>DATE</code> pbrbmeter bs b
     * <code>jbvb.sql.Dbte</code> object.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setDbte
     * @since 1.4
     */
    jbvb.sql.Dbte getDbte(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>TIME</code> pbrbmeter bs b
     * <code>jbvb.sql.Time</code> object.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setTime
     * @since 1.4
     */
    jbvb.sql.Time getTime(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>TIMESTAMP</code> pbrbmeter bs b
     * <code>jbvb.sql.Timestbmp</code> object.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result
     * is <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setTimestbmp
     * @since 1.4
     */
    jbvb.sql.Timestbmp getTimestbmp(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b pbrbmeter bs bn <code>Object</code> in the Jbvb
     * progrbmming lbngubge. If the vblue is bn SQL <code>NULL</code>, the
     * driver returns b Jbvb <code>null</code>.
     * <p>
     * This method returns b Jbvb object whose type corresponds to the JDBC
     * type thbt wbs registered for this pbrbmeter using the method
     * <code>registerOutPbrbmeter</code>.  By registering the tbrget JDBC
     * type bs <code>jbvb.sql.Types.OTHER</code>, this method cbn be used
     * to rebd dbtbbbse-specific bbstrbct dbtb types.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return A <code>jbvb.lbng.Object</code> holding the OUT pbrbmeter vblue.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see Types
     * @see #setObject
     * @since 1.4
     */
    Object getObject(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>NUMERIC</code> pbrbmeter bs b
     * <code>jbvb.mbth.BigDecimbl</code> object with bs mbny digits to the
     * right of the decimbl point bs the vblue contbins.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue in full precision.  If the vblue is
     * SQL <code>NULL</code>, the result is <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter;  if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setBigDecimbl
     * @since 1.4
     */
    BigDecimbl getBigDecimbl(String pbrbmeterNbme) throws SQLException;

    /**
     * Returns bn object representing the vblue of OUT pbrbmeter
     * <code>pbrbmeterNbme</code> bnd uses <code>mbp</code> for the custom
     * mbpping of the pbrbmeter vblue.
     * <p>
     * This method returns b Jbvb object whose type corresponds to the
     * JDBC type thbt wbs registered for this pbrbmeter using the method
     * <code>registerOutPbrbmeter</code>.  By registering the tbrget
     * JDBC type bs <code>jbvb.sql.Types.OTHER</code>, this method cbn
     * be used to rebd dbtbbbse-specific bbstrbct dbtb types.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm mbp the mbpping from SQL type nbmes to Jbvb clbsses
     * @return b <code>jbvb.lbng.Object</code> holding the OUT pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setObject
     * @since 1.4
     */
    Object getObject(String pbrbmeterNbme, jbvb.util.Mbp<String,Clbss<?>> mbp)
      throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>REF(&lt;structured-type&gt;)</code>
     * pbrbmeter bs b {@link jbvb.sql.Ref} object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue bs b <code>Ref</code> object in the
     *         Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>,
     *         the vblue <code>null</code> is returned.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    Ref getRef (String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>BLOB</code> pbrbmeter bs b
     * {@link jbvb.sql.Blob} object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue bs b <code>Blob</code> object in the
     *         Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>,
     *         the vblue <code>null</code> is returned.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    Blob getBlob (String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>CLOB</code> pbrbmeter bs b
     * <code>jbvb.sql.Clob</code> object in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue bs b <code>Clob</code> object in the
     *         Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>,
     *         the vblue <code>null</code> is returned.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    Clob getClob (String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>ARRAY</code> pbrbmeter bs bn
     * {@link jbvb.sql.Arrby} object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue bs bn <code>Arrby</code> object in
     *         Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>,
     *         the vblue <code>null</code> is returned.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    Arrby getArrby (String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>DATE</code> pbrbmeter bs b
     * <code>jbvb.sql.Dbte</code> object, using
     * the given <code>Cblendbr</code> object
     * to construct the dbte.
     * With b <code>Cblendbr</code> object, the driver
     * cbn cblculbte the dbte tbking into bccount b custom timezone bnd locble.
     * If no <code>Cblendbr</code> object is specified, the driver uses the
     * defbult timezone bnd locble.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the dbte
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>,
     * the result is <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setDbte
     * @since 1.4
     */
    jbvb.sql.Dbte getDbte(String pbrbmeterNbme, Cblendbr cbl)
        throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>TIME</code> pbrbmeter bs b
     * <code>jbvb.sql.Time</code> object, using
     * the given <code>Cblendbr</code> object
     * to construct the time.
     * With b <code>Cblendbr</code> object, the driver
     * cbn cblculbte the time tbking into bccount b custom timezone bnd locble.
     * If no <code>Cblendbr</code> object is specified, the driver uses the
     * defbult timezone bnd locble.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the time
     * @return the pbrbmeter vblue; if the vblue is SQL <code>NULL</code>, the result is
     * <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setTime
     * @since 1.4
     */
    jbvb.sql.Time getTime(String pbrbmeterNbme, Cblendbr cbl)
        throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>TIMESTAMP</code> pbrbmeter bs b
     * <code>jbvb.sql.Timestbmp</code> object, using
     * the given <code>Cblendbr</code> object to construct
     * the <code>Timestbmp</code> object.
     * With b <code>Cblendbr</code> object, the driver
     * cbn cblculbte the timestbmp tbking into bccount b custom timezone bnd locble.
     * If no <code>Cblendbr</code> object is specified, the driver uses the
     * defbult timezone bnd locble.
     *
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the timestbmp
     * @return the pbrbmeter vblue.  If the vblue is SQL <code>NULL</code>, the result is
     * <code>null</code>.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setTimestbmp
     * @since 1.4
     */
    jbvb.sql.Timestbmp getTimestbmp(String pbrbmeterNbme, Cblendbr cbl)
        throws SQLException;

    /**
     * Retrieves the vblue of b JDBC <code>DATALINK</code> pbrbmeter bs b
     * <code>jbvb.net.URL</code> object.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue bs b <code>jbvb.net.URL</code> object in the
     * Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>, the
     * vblue <code>null</code> is returned.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>CbllbbleStbtement</code>,
     *            or if there is b problem with the URL
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setURL
     * @since 1.4
     */
    jbvb.net.URL getURL(String pbrbmeterNbme) throws SQLException;

    //------------------------- JDBC 4.0 -----------------------------------

    /**
     * Retrieves the vblue of the designbted JDBC <code>ROWID</code> pbrbmeter bs b
     * <code>jbvb.sql.RowId</code> object.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,...
     * @return b <code>RowId</code> object thbt represents the JDBC <code>ROWID</code>
     *     vblue is used bs the designbted pbrbmeter. If the pbrbmeter contbins
     * b SQL <code>NULL</code>, then b <code>null</code> vblue is returned.
     * @throws SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    RowId getRowId(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>ROWID</code> pbrbmeter bs b
     * <code>jbvb.sql.RowId</code> object.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return b <code>RowId</code> object thbt represents the JDBC <code>ROWID</code>
     *     vblue is used bs the designbted pbrbmeter. If the pbrbmeter contbins
     * b SQL <code>NULL</code>, then b <code>null</code> vblue is returned.
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    RowId getRowId(String pbrbmeterNbme) throws SQLException;

     /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.RowId</code> object. The
     * driver converts this to b SQL <code>ROWID</code> when it sends it to the
     * dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the pbrbmeter vblue
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void setRowId(String pbrbmeterNbme, RowId x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>String</code> object.
     * The driver converts this to b SQL <code>NCHAR</code> or
     * <code>NVARCHAR</code> or <code>LONGNVARCHAR</code>
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter to be set
     * @pbrbm vblue the pbrbmeter vblue
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void setNString(String pbrbmeterNbme, String vblue)
            throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object. The
     * <code>Rebder</code> rebds the dbtb till end-of-file is rebched. The
     * driver does the necessbry conversion from Jbvb chbrbcter formbt to
     * the nbtionbl chbrbcter set in the dbtbbbse.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter to be set
     * @pbrbm vblue the pbrbmeter vblue
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void setNChbrbcterStrebm(String pbrbmeterNbme, Rebder vblue, long length)
            throws SQLException;

     /**
     * Sets the designbted pbrbmeter to b <code>jbvb.sql.NClob</code> object. The object
     * implements the <code>jbvb.sql.NClob</code> interfbce. This <code>NClob</code>
     * object mbps to b SQL <code>NCLOB</code>.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter to be set
     * @pbrbm vblue the pbrbmeter vblue
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
     void setNClob(String pbrbmeterNbme, NClob vblue) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object.  The <code>rebder</code> must contbin  the number
     * of chbrbcters specified by length otherwise b <code>SQLException</code> will be
     * generbted when the <code>CbllbbleStbtement</code> is executed.
     * This method differs from the <code>setChbrbcterStrebm (int, Rebder, int)</code> method
     * becbuse it informs the driver thbt the pbrbmeter vblue should be sent to
     * the server bs b <code>CLOB</code>.  When the <code>setChbrbcterStrebm</code> method is used, the
     * driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be send to the server bs b <code>LONGVARCHAR</code> or b <code>CLOB</code>
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter to be set
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the length specified is less thbn zero;
     * b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     *
     * @since 1.6
     */
     void setClob(String pbrbmeterNbme, Rebder rebder, long length)
       throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>InputStrebm</code> object.  The <code>inputstrebm</code> must contbin  the number
     * of chbrbcters specified by length, otherwise b <code>SQLException</code> will be
     * generbted when the <code>CbllbbleStbtement</code> is executed.
     * This method differs from the <code>setBinbryStrebm (int, InputStrebm, int)</code>
     * method becbuse it informs the driver thbt the pbrbmeter vblue should be
     * sent to the server bs b <code>BLOB</code>.  When the <code>setBinbryStrebm</code> method is used,
     * the driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be sent to the server bs b <code>LONGVARBINARY</code> or b <code>BLOB</code>
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter to be set
     * the second is 2, ...
     *
     * @pbrbm inputStrebm An object thbt contbins the dbtb to set the pbrbmeter
     * vblue to.
     * @pbrbm length the number of bytes in the pbrbmeter dbtb.
     * @throws SQLException  if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the length specified
     * is less thbn zero; if the number of bytes in the inputstrebm does not mbtch
     * the specified length; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     *
     * @since 1.6
     */
     void setBlob(String pbrbmeterNbme, InputStrebm inputStrebm, long length)
        throws SQLException;
    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object.  The <code>rebder</code> must contbin  the number
     * of chbrbcters specified by length otherwise b <code>SQLException</code> will be
     * generbted when the <code>CbllbbleStbtement</code> is executed.
     * This method differs from the <code>setChbrbcterStrebm (int, Rebder, int)</code> method
     * becbuse it informs the driver thbt the pbrbmeter vblue should be sent to
     * the server bs b <code>NCLOB</code>.  When the <code>setChbrbcterStrebm</code> method is used, the
     * driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be send to the server bs b <code>LONGNVARCHAR</code> or b <code>NCLOB</code>
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter to be set
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the length specified is less thbn zero;
     * if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
     void setNClob(String pbrbmeterNbme, Rebder rebder, long length)
       throws SQLException;

    /**
     * Retrieves the vblue of the designbted JDBC <code>NCLOB</code> pbrbmeter bs b
     * <code>jbvb.sql.NClob</code> object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, bnd
     * so on
     * @return the pbrbmeter vblue bs b <code>NClob</code> object in the
     * Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>, the
     * vblue <code>null</code> is returned.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    NClob getNClob (int pbrbmeterIndex) throws SQLException;


    /**
     * Retrieves the vblue of b JDBC <code>NCLOB</code> pbrbmeter bs b
     * <code>jbvb.sql.NClob</code> object in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return the pbrbmeter vblue bs b <code>NClob</code> object in the
     *         Jbvb progrbmming lbngubge.  If the vblue wbs SQL <code>NULL</code>,
     *         the vblue <code>null</code> is returned.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    NClob getNClob (String pbrbmeterNbme) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.SQLXML</code> object. The driver converts this to bn
     * <code>SQL XML</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm xmlObject b <code>SQLXML</code> object thbt mbps bn <code>SQL XML</code> vblue
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed <code>CbllbbleStbtement</code> or
     * the <code>jbvb.xml.trbnsform.Result</code>,
   *  <code>Writer</code> or <code>OutputStrebm</code> hbs not been closed for the <code>SQLXML</code> object
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     *
     * @since 1.6
     */
    void setSQLXML(String pbrbmeterNbme, SQLXML xmlObject) throws SQLException;

    /**
     * Retrieves the vblue of the designbted <code>SQL XML</code> pbrbmeter bs b
     * <code>jbvb.sql.SQLXML</code> object in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1, the second is 2, ...
     * @return b <code>SQLXML</code> object thbt mbps bn <code>SQL XML</code> vblue
     * @throws SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    SQLXML getSQLXML(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted <code>SQL XML</code> pbrbmeter bs b
     * <code>jbvb.sql.SQLXML</code> object in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return b <code>SQLXML</code> object thbt mbps bn <code>SQL XML</code> vblue
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    SQLXML getSQLXML(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of the designbted <code>NCHAR</code>,
     * <code>NVARCHAR</code>
     * or <code>LONGNVARCHAR</code> pbrbmeter bs
     * b <code>String</code> in the Jbvb progrbmming lbngubge.
     * <p>
     * For the fixed-length type JDBC <code>NCHAR</code>,
     * the <code>String</code> object
     * returned hbs exbctly the sbme vblue the SQL
     * <code>NCHAR</code> vblue hbd in the
     * dbtbbbse, including bny pbdding bdded by the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1, the second is 2, ...
     * @return b <code>String</code> object thbt mbps bn
     * <code>NCHAR</code>, <code>NVARCHAR</code> or <code>LONGNVARCHAR</code> vblue
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     * @see #setNString
     */
    String getNString(int pbrbmeterIndex) throws SQLException;


    /**
     *  Retrieves the vblue of the designbted <code>NCHAR</code>,
     * <code>NVARCHAR</code>
     * or <code>LONGNVARCHAR</code> pbrbmeter bs
     * b <code>String</code> in the Jbvb progrbmming lbngubge.
     * <p>
     * For the fixed-length type JDBC <code>NCHAR</code>,
     * the <code>String</code> object
     * returned hbs exbctly the sbme vblue the SQL
     * <code>NCHAR</code> vblue hbd in the
     * dbtbbbse, including bny pbdding bdded by the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return b <code>String</code> object thbt mbps bn
     * <code>NCHAR</code>, <code>NVARCHAR</code> or <code>LONGNVARCHAR</code> vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     * @see #setNString
     */
    String getNString(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of the designbted pbrbmeter bs b
     * <code>jbvb.io.Rebder</code> object in the Jbvb progrbmming lbngubge.
     * It is intended for use when
     * bccessing  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> pbrbmeters.
     *
     * @return b <code>jbvb.io.Rebder</code> object thbt contbins the pbrbmeter
     * vblue; if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code> in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    jbvb.io.Rebder getNChbrbcterStrebm(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted pbrbmeter bs b
     * <code>jbvb.io.Rebder</code> object in the Jbvb progrbmming lbngubge.
     * It is intended for use when
     * bccessing  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> pbrbmeters.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return b <code>jbvb.io.Rebder</code> object thbt contbins the pbrbmeter
     * vblue; if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code> in the Jbvb progrbmming lbngubge
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    jbvb.io.Rebder getNChbrbcterStrebm(String pbrbmeterNbme) throws SQLException;

    /**
     * Retrieves the vblue of the designbted pbrbmeter bs b
     * <code>jbvb.io.Rebder</code> object in the Jbvb progrbmming lbngubge.
     *
     * @return b <code>jbvb.io.Rebder</code> object thbt contbins the pbrbmeter
     * vblue; if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code> in the Jbvb progrbmming lbngubge.
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @exception SQLException if the pbrbmeterIndex is not vblid; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @since 1.6
     */
    jbvb.io.Rebder getChbrbcterStrebm(int pbrbmeterIndex) throws SQLException;

    /**
     * Retrieves the vblue of the designbted pbrbmeter bs b
     * <code>jbvb.io.Rebder</code> object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @return b <code>jbvb.io.Rebder</code> object thbt contbins the pbrbmeter
     * vblue; if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code> in the Jbvb progrbmming lbngubge
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    jbvb.io.Rebder getChbrbcterStrebm(String pbrbmeterNbme) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Blob</code> object.
     * The driver converts this to bn SQL <code>BLOB</code> vblue when it
     * sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x b <code>Blob</code> object thbt mbps bn SQL <code>BLOB</code> vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void setBlob (String pbrbmeterNbme, Blob x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Clob</code> object.
     * The driver converts this to bn SQL <code>CLOB</code> vblue when it
     * sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x b <code>Clob</code> object thbt mbps bn SQL <code>CLOB</code> vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void setClob (String pbrbmeterNbme, Clob x) throws SQLException;
    /**
     * Sets the designbted pbrbmeter to the given input strebm, which will hbve
     * the specified number of bytes.
     * When b very lbrge ASCII vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code>. Dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from ASCII to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the Jbvb input strebm thbt contbins the ASCII pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void setAsciiStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x, long length)
        throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given input strebm, which will hbve
     * the specified number of bytes.
     * When b very lbrge binbry vblue is input to b <code>LONGVARBINARY</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code> object. The dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the jbvb input strebm which contbins the binbry pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void setBinbryStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x,
                         long length) throws SQLException;
        /**
     * Sets the designbted pbrbmeter to the given <code>Rebder</code>
     * object, which is the given number of chbrbcters long.
     * When b very lbrge UNICODE vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.Rebder</code> object. The dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from UNICODE to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object thbt
     *        contbins the UNICODE dbtb used bs the designbted pbrbmeter
     * @pbrbm length the number of chbrbcters in the strebm
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void setChbrbcterStrebm(String pbrbmeterNbme,
                            jbvb.io.Rebder rebder,
                            long length) throws SQLException;
     //--
    /**
     * Sets the designbted pbrbmeter to the given input strebm.
     * When b very lbrge ASCII vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code>. Dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from ASCII to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setAsciiStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the Jbvb input strebm thbt contbins the ASCII pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
       * @since 1.6
    */
    void setAsciiStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x)
            throws SQLException;
    /**
     * Sets the designbted pbrbmeter to the given input strebm.
     * When b very lbrge binbry vblue is input to b <code>LONGVARBINARY</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code> object. The dbtb will be rebd from the
     * strebm bs needed until end-of-file is rebched.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setBinbryStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the jbvb input strebm which contbins the binbry pbrbmeter vblue
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
    void setBinbryStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x)
    throws SQLException;
    /**
     * Sets the designbted pbrbmeter to the given <code>Rebder</code>
     * object.
     * When b very lbrge UNICODE vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.Rebder</code> object. The dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from UNICODE to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setChbrbcterStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object thbt contbins the
     *        Unicode dbtb
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
    void setChbrbcterStrebm(String pbrbmeterNbme,
                          jbvb.io.Rebder rebder) throws SQLException;
  /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object. The
     * <code>Rebder</code> rebds the dbtb till end-of-file is rebched. The
     * driver does the necessbry conversion from Jbvb chbrbcter formbt to
     * the nbtionbl chbrbcter set in the dbtbbbse.

     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setNChbrbcterStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm vblue the pbrbmeter vblue
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs; or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
     void setNChbrbcterStrebm(String pbrbmeterNbme, Rebder vblue) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object.
     * This method differs from the <code>setChbrbcterStrebm (int, Rebder)</code> method
     * becbuse it informs the driver thbt the pbrbmeter vblue should be sent to
     * the server bs b <code>CLOB</code>.  When the <code>setChbrbcterStrebm</code> method is used, the
     * driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be send to the server bs b <code>LONGVARCHAR</code> or b <code>CLOB</code>
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setClob</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or this method is cblled on
     * b closed <code>CbllbbleStbtement</code>
     *
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
     void setClob(String pbrbmeterNbme, Rebder rebder)
       throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>InputStrebm</code> object.
     * This method differs from the <code>setBinbryStrebm (int, InputStrebm)</code>
     * method becbuse it informs the driver thbt the pbrbmeter vblue should be
     * sent to the server bs b <code>BLOB</code>.  When the <code>setBinbryStrebm</code> method is used,
     * the driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be send to the server bs b <code>LONGVARBINARY</code> or b <code>BLOB</code>
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setBlob</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm inputStrebm An object thbt contbins the dbtb to set the pbrbmeter
     * vblue to.
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     *
     * @since 1.6
     */
     void setBlob(String pbrbmeterNbme, InputStrebm inputStrebm)
        throws SQLException;
    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object.
     * This method differs from the <code>setChbrbcterStrebm (int, Rebder)</code> method
     * becbuse it informs the driver thbt the pbrbmeter vblue should be sent to
     * the server bs b <code>NCLOB</code>.  When the <code>setChbrbcterStrebm</code> method is used, the
     * driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be send to the server bs b <code>LONGNVARCHAR</code> or b <code>NCLOB</code>
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setNClob</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @throws SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if the driver does not support nbtionbl chbrbcter sets;
     * if the driver cbn detect thbt b dbtb conversion
     *  error could occur;  if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     *
     * @since 1.6
     */
     void setNClob(String pbrbmeterNbme, Rebder rebder)
       throws SQLException;

    //------------------------- JDBC 4.1 -----------------------------------


    /**
     *<p>Returns bn object representing the vblue of OUT pbrbmeter
     * {@code pbrbmeterIndex} bnd will convert from the
     * SQL type of the pbrbmeter to the requested Jbvb dbtb type, if the
     * conversion is supported. If the conversion is not
     * supported or null is specified for the type, b
     * <code>SQLException</code> is thrown.
     *<p>
     * At b minimum, bn implementbtion must support the conversions defined in
     * Appendix B, Tbble B-3 bnd conversion of bppropribte user defined SQL
     * types to b Jbvb type which implements {@code SQLDbtb}, or {@code Struct}.
     * Additionbl conversions mby be supported bnd bre vendor defined.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, bnd so on
     * @pbrbm type Clbss representing the Jbvb dbtb type to convert the
     * designbted pbrbmeter to.
     * @pbrbm <T> the type of the clbss modeled by this Clbss object
     * @return bn instbnce of {@code type} holding the OUT pbrbmeter vblue
     * @throws SQLException if conversion is not supported, type is null or
     *         bnother error occurs. The getCbuse() method of the
     * exception mby provide b more detbiled exception, for exbmple, if
     * b conversion error occurs
     * @throws SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.7
     */
     public <T> T getObject(int pbrbmeterIndex, Clbss<T> type) throws SQLException;


    /**
     *<p>Returns bn object representing the vblue of OUT pbrbmeter
     * {@code pbrbmeterNbme} bnd will convert from the
     * SQL type of the pbrbmeter to the requested Jbvb dbtb type, if the
     * conversion is supported. If the conversion is not
     * supported  or null is specified for the type, b
     * <code>SQLException</code> is thrown.
     *<p>
     * At b minimum, bn implementbtion must support the conversions defined in
     * Appendix B, Tbble B-3 bnd conversion of bppropribte user defined SQL
     * types to b Jbvb type which implements {@code SQLDbtb}, or {@code Struct}.
     * Additionbl conversions mby be supported bnd bre vendor defined.
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm type Clbss representing the Jbvb dbtb type to convert
     * the designbted pbrbmeter to.
     * @pbrbm <T> the type of the clbss modeled by this Clbss object
     * @return bn instbnce of {@code type} holding the OUT pbrbmeter
     * vblue
     * @throws SQLException if conversion is not supported, type is null or
     *         bnother error occurs. The getCbuse() method of the
     * exception mby provide b more detbiled exception, for exbmple, if
     * b conversion error occurs
     * @throws SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.7
     */
     public <T> T getObject(String pbrbmeterNbme, Clbss<T> type) throws SQLException;

     //------------------------- JDBC 4.2 -----------------------------------

     /**
     * <p>Sets the vblue of the designbted pbrbmeter with the given object.
     *
     * If the second brgument is bn {@code InputStrebm} then the strebm
     * must contbin the number of bytes specified by scbleOrLength.
     * If the second brgument is b {@code Rebder} then the rebder must
     * contbin the number of chbrbcters specified
     * by scbleOrLength. If these conditions bre not true the driver
     * will generbte b
     * {@code SQLException} when the prepbred stbtement is executed.
     *
     * <p>The given Jbvb object will be converted to the given tbrgetSqlType
     * before being sent to the dbtbbbse.
     *
     * If the object hbs b custom mbpping (is of b clbss implementing the
     * interfbce {@code SQLDbtb}),
     * the JDBC driver should cbll the method {@code SQLDbtb.writeSQL} to
     * write it to the SQL dbtb strebm.
     * If, on the other hbnd, the object is of b clbss implementing
     * {@code Ref}, {@code Blob}, {@code Clob},  {@code NClob},
     *  {@code Struct}, {@code jbvb.net.URL},
     * or {@code Arrby}, the driver should pbss it to the dbtbbbse bs b
     * vblue of the corresponding SQL type.
     *
     * <p>Note thbt this method mby be used to pbss dbtbbbse-specific
     * bbstrbct dbtb types.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @pbrbm tbrgetSqlType the SQL type to be
     * sent to the dbtbbbse. The scble brgument mby further qublify this type.
     * @pbrbm scbleOrLength for {@code jbvb.sql.JDBCType.DECIMAL}
     *          or {@code jbvb.sql.JDBCType.NUMERIC types},
     *          this is the number of digits bfter the decimbl point. For
     *          Jbvb Object types {@code InputStrebm} bnd {@code Rebder},
     *          this is the length
     *          of the dbtb in the strebm or rebder.  For bll other types,
     *          this vblue will be ignored.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed {@code CbllbbleStbtement}  or
     *            if the Jbvb Object specified by x is bn InputStrebm
     *            or Rebder object bnd the vblue of the scble pbrbmeter is less
     *            thbn zero
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified tbrgetSqlType
     * @see JDBCType
     * @see SQLType
     *
     * @since 1.8
     */
     defbult void setObject(String pbrbmeterNbme, Object x, SQLType tbrgetSqlType,
             int scbleOrLength) throws SQLException {
        throw new SQLFebtureNotSupportedException("setObject not implemented");
    }
    /**
     * Sets the vblue of the designbted pbrbmeter with the given object.
     *
     * This method is similbr to {@link #setObject(String pbrbmeterNbme,
     * Object x, SQLType tbrgetSqlType, int scbleOrLength)},
     * except thbt it bssumes b scble of zero.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @pbrbm tbrgetSqlType the SQL type to be sent to the dbtbbbse
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed {@code CbllbbleStbtement}
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified tbrgetSqlType
     * @see JDBCType
     * @see SQLType
     * @since 1.8
     */
     defbult void setObject(String pbrbmeterNbme, Object x, SQLType tbrgetSqlType)
        throws SQLException {
        throw new SQLFebtureNotSupportedException("setObject not implemented");
    }

    /**
     * Registers the OUT pbrbmeter in ordinbl position
     * {@code pbrbmeterIndex} to the JDBC type
     * {@code sqlType}.  All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * The JDBC type specified by {@code sqlType} for bn OUT
     * pbrbmeter determines the Jbvb type thbt must be used
     * in the {@code get} method to rebd the vblue of thbt pbrbmeter.
     * <p>
     * If the JDBC type expected to be returned to this output pbrbmeter
     * is specific to this pbrticulbr dbtbbbse, {@code sqlType}
     * mby be {@code JDBCType.OTHER} or b {@code SQLType} thbt is supported by
     * the JDBC driver.  The method
     * {@link #getObject} retrieves the vblue.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     *        bnd so on
     * @pbrbm sqlType the JDBC type code defined by {@code SQLType} to use to
     * register the OUT Pbrbmeter.
     *        If the pbrbmeter is of JDBC type {@code JDBCType.NUMERIC}
     *        or {@code JDBCType.DECIMAL}, the version of
     *        {@code registerOutPbrbmeter} thbt bccepts b scble vblue
     *        should be used.
     *
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed {@code CbllbbleStbtement}
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified sqlType
     * @see JDBCType
     * @see SQLType
     * @since 1.8
     */
    defbult void registerOutPbrbmeter(int pbrbmeterIndex, SQLType sqlType)
        throws SQLException {
        throw new SQLFebtureNotSupportedException("registerOutPbrbmeter not implemented");
    }

    /**
     * Registers the pbrbmeter in ordinbl position
     * {@code pbrbmeterIndex} to be of JDBC type
     * {@code sqlType}. All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * The JDBC type specified by {@code sqlType} for bn OUT
     * pbrbmeter determines the Jbvb type thbt must be used
     * in the {@code get} method to rebd the vblue of thbt pbrbmeter.
     * <p>
     * This version of {@code  registerOutPbrbmeter} should be
     * used when the pbrbmeter is of JDBC type {@code JDBCType.NUMERIC}
     * or {@code JDBCType.DECIMAL}.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,
     * bnd so on
     * @pbrbm sqlType the JDBC type code defined by {@code SQLType} to use to
     * register the OUT Pbrbmeter.
     * @pbrbm scble the desired number of digits to the right of the
     * decimbl point.  It must be grebter thbn or equbl to zero.
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed {@code CbllbbleStbtement}
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified sqlType
     * @see JDBCType
     * @see SQLType
     * @since 1.8
     */
    defbult void registerOutPbrbmeter(int pbrbmeterIndex, SQLType sqlType,
            int scble) throws SQLException {
        throw new SQLFebtureNotSupportedException("registerOutPbrbmeter not implemented");
    }
    /**
     * Registers the designbted output pbrbmeter.
     * This version of
     * the method {@code  registerOutPbrbmeter}
     * should be used for b user-defined or {@code REF} output pbrbmeter.
     * Exbmples
     * of user-defined types include: {@code STRUCT}, {@code DISTINCT},
     * {@code JAVA_OBJECT}, bnd nbmed brrby types.
     *<p>
     * All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>  For b user-defined pbrbmeter, the fully-qublified SQL
     * type nbme of the pbrbmeter should blso be given, while b {@code REF}
     * pbrbmeter requires thbt the fully-qublified type nbme of the
     * referenced type be given.  A JDBC driver thbt does not need the
     * type code bnd type nbme informbtion mby ignore it.   To be portbble,
     * however, bpplicbtions should blwbys provide these vblues for
     * user-defined bnd {@code REF} pbrbmeters.
     *
     * Although it is intended for user-defined bnd {@code REF} pbrbmeters,
     * this method mby be used to register b pbrbmeter of bny JDBC type.
     * If the pbrbmeter does not hbve b user-defined or {@code REF} type, the
     * <i>typeNbme</i> pbrbmeter is ignored.
     *
     * <P><B>Note:</B> When rebding the vblue of bn out pbrbmeter, you
     * must use the getter method whose Jbvb type corresponds to the
     * pbrbmeter's registered SQL type.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2,...
     * @pbrbm sqlType the JDBC type code defined by {@code SQLType} to use to
     * register the OUT Pbrbmeter.
     * @pbrbm typeNbme the fully-qublified nbme of bn SQL structured type
     * @exception SQLException if the pbrbmeterIndex is not vblid;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed {@code CbllbbleStbtement}
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified sqlType
     * @see JDBCType
     * @see SQLType
     * @since 1.8
     */
    defbult void registerOutPbrbmeter (int pbrbmeterIndex, SQLType sqlType,
            String typeNbme) throws SQLException {
        throw new SQLFebtureNotSupportedException("registerOutPbrbmeter not implemented");
    }

    /**
     * Registers the OUT pbrbmeter nbmed
     * <code>pbrbmeterNbme</code> to the JDBC type
     * {@code sqlType}.  All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * The JDBC type specified by {@code sqlType} for bn OUT
     * pbrbmeter determines the Jbvb type thbt must be used
     * in the {@code get} method to rebd the vblue of thbt pbrbmeter.
     * <p>
     * If the JDBC type expected to be returned to this output pbrbmeter
     * is specific to this pbrticulbr dbtbbbse, {@code sqlType}
     * should be {@code JDBCType.OTHER} or b {@code SQLType} thbt is supported
     * by the JDBC driver..  The method
     * {@link #getObject} retrieves the vblue.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm sqlType the JDBC type code defined by {@code SQLType} to use to
     * register the OUT Pbrbmeter.
     * If the pbrbmeter is of JDBC type {@code JDBCType.NUMERIC}
     * or {@code JDBCType.DECIMAL}, the version of
     * {@code  registerOutPbrbmeter} thbt bccepts b scble vblue
     * should be used.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed {@code CbllbbleStbtement}
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified sqlType
     * or if the JDBC driver does not support
     * this method
     * @since 1.8
     * @see JDBCType
     * @see SQLType
     */
    defbult void registerOutPbrbmeter(String pbrbmeterNbme, SQLType sqlType)
        throws SQLException {
        throw new SQLFebtureNotSupportedException("registerOutPbrbmeter not implemented");
    }

    /**
     * Registers the pbrbmeter nbmed
     * <code>pbrbmeterNbme</code> to be of JDBC type
     * {@code sqlType}.  All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * <p>
     * The JDBC type specified by {@code sqlType} for bn OUT
     * pbrbmeter determines the Jbvb type thbt must be used
     * in the {@code get} method to rebd the vblue of thbt pbrbmeter.
     * <p>
     * This version of {@code  registerOutPbrbmeter} should be
     * used when the pbrbmeter is of JDBC type {@code JDBCType.NUMERIC}
     * or {@code JDBCType.DECIMAL}.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm sqlType the JDBC type code defined by {@code SQLType} to use to
     * register the OUT Pbrbmeter.
     * @pbrbm scble the desired number of digits to the right of the
     * decimbl point.  It must be grebter thbn or equbl to zero.
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed {@code CbllbbleStbtement}
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified sqlType
     * or if the JDBC driver does not support
     * this method
     * @since 1.8
     * @see JDBCType
     * @see SQLType
     */
    defbult void registerOutPbrbmeter(String pbrbmeterNbme, SQLType sqlType,
            int scble) throws SQLException {
        throw new SQLFebtureNotSupportedException("registerOutPbrbmeter not implemented");
    }

    /**
     * Registers the designbted output pbrbmeter.  This version of
     * the method {@code  registerOutPbrbmeter}
     * should be used for b user-nbmed or REF output pbrbmeter.  Exbmples
     * of user-nbmed types include: STRUCT, DISTINCT, JAVA_OBJECT, bnd
     * nbmed brrby types.
     *<p>
     * All OUT pbrbmeters must be registered
     * before b stored procedure is executed.
     * </p>
     * For b user-nbmed pbrbmeter the fully-qublified SQL
     * type nbme of the pbrbmeter should blso be given, while b REF
     * pbrbmeter requires thbt the fully-qublified type nbme of the
     * referenced type be given.  A JDBC driver thbt does not need the
     * type code bnd type nbme informbtion mby ignore it.   To be portbble,
     * however, bpplicbtions should blwbys provide these vblues for
     * user-nbmed bnd REF pbrbmeters.
     *
     * Although it is intended for user-nbmed bnd REF pbrbmeters,
     * this method mby be used to register b pbrbmeter of bny JDBC type.
     * If the pbrbmeter does not hbve b user-nbmed or REF type, the
     * typeNbme pbrbmeter is ignored.
     *
     * <P><B>Note:</B> When rebding the vblue of bn out pbrbmeter, you
     * must use the {@code getXXX} method whose Jbvb type XXX corresponds to the
     * pbrbmeter's registered SQL type.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm sqlType the JDBC type code defined by {@code SQLType} to use to
     * register the OUT Pbrbmeter.
     * @pbrbm typeNbme the fully-qublified nbme of bn SQL structured type
     * @exception SQLException if pbrbmeterNbme does not correspond to b nbmed
     * pbrbmeter; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed {@code CbllbbleStbtement}
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified sqlType
     * or if the JDBC driver does not support this method
     * @see JDBCType
     * @see SQLType
     * @since 1.8
     */
    defbult void registerOutPbrbmeter (String pbrbmeterNbme, SQLType sqlType,
            String typeNbme) throws SQLException {
        throw new SQLFebtureNotSupportedException("registerOutPbrbmeter not implemented");
    }
}
