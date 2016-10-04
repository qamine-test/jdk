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
 * An object thbt represents b precompiled SQL stbtement.
 * <P>A SQL stbtement is precompiled bnd stored in b
 * <code>PrepbredStbtement</code> object. This object cbn then be used to
 * efficiently execute this stbtement multiple times.
 *
 * <P><B>Note:</B> The setter methods (<code>setShort</code>, <code>setString</code>,
 * bnd so on) for setting IN pbrbmeter vblues
 * must specify types thbt bre compbtible with the defined SQL type of
 * the input pbrbmeter. For instbnce, if the IN pbrbmeter hbs SQL type
 * <code>INTEGER</code>, then the method <code>setInt</code> should be used.
 *
 * <p>If brbitrbry pbrbmeter type conversions bre required, the method
 * <code>setObject</code> should be used with b tbrget SQL type.
 * <P>
 * In the following exbmple of setting b pbrbmeter, <code>con</code> represents
 * bn bctive connection:
 * <PRE>
 *   PrepbredStbtement pstmt = con.prepbreStbtement("UPDATE EMPLOYEES
 *                                     SET SALARY = ? WHERE ID = ?");
 *   pstmt.setBigDecimbl(1, 153833.00)
 *   pstmt.setInt(2, 110592)
 * </PRE>
 *
 * @see Connection#prepbreStbtement
 * @see ResultSet
 */

public interfbce PrepbredStbtement extends Stbtement {

    /**
     * Executes the SQL query in this <code>PrepbredStbtement</code> object
     * bnd returns the <code>ResultSet</code> object generbted by the query.
     *
     * @return b <code>ResultSet</code> object thbt contbins the dbtb produced by the
     *         query; never <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed  <code>PrepbredStbtement</code> or the SQL
     *            stbtement does not return b <code>ResultSet</code> object
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     */
    ResultSet executeQuery() throws SQLException;

    /**
     * Executes the SQL stbtement in this <code>PrepbredStbtement</code> object,
     * which must be bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement, such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     *
     * @return either (1) the row count for SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtements
     *         or (2) 0 for SQL stbtements thbt return nothing
     * @exception SQLException if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed  <code>PrepbredStbtement</code>
     * or the SQL stbtement returns b <code>ResultSet</code> object
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     */
    int executeUpdbte() throws SQLException;

    /**
     * Sets the designbted pbrbmeter to SQL <code>NULL</code>.
     *
     * <P><B>Note:</B> You must specify the pbrbmeter's SQL type.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm sqlType the SQL type code defined in <code>jbvb.sql.Types</code>
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @exception SQLFebtureNotSupportedException if <code>sqlType</code> is
     * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
     * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
     * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
     *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
     * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
     * this dbtb type
     */
    void setNull(int pbrbmeterIndex, int sqlType) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>boolebn</code> vblue.
     * The driver converts this
     * to bn SQL <code>BIT</code> or <code>BOOLEAN</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement;
     * if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setBoolebn(int pbrbmeterIndex, boolebn x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>byte</code> vblue.
     * The driver converts this
     * to bn SQL <code>TINYINT</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setByte(int pbrbmeterIndex, byte x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>short</code> vblue.
     * The driver converts this
     * to bn SQL <code>SMALLINT</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setShort(int pbrbmeterIndex, short x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>int</code> vblue.
     * The driver converts this
     * to bn SQL <code>INTEGER</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setInt(int pbrbmeterIndex, int x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>long</code> vblue.
     * The driver converts this
     * to bn SQL <code>BIGINT</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setLong(int pbrbmeterIndex, long x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>flobt</code> vblue.
     * The driver converts this
     * to bn SQL <code>REAL</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setFlobt(int pbrbmeterIndex, flobt x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>double</code> vblue.
     * The driver converts this
     * to bn SQL <code>DOUBLE</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setDouble(int pbrbmeterIndex, double x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.mbth.BigDecimbl</code> vblue.
     * The driver converts this to bn SQL <code>NUMERIC</code> vblue when
     * it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setBigDecimbl(int pbrbmeterIndex, BigDecimbl x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb <code>String</code> vblue.
     * The driver converts this
     * to bn SQL <code>VARCHAR</code> or <code>LONGVARCHAR</code> vblue
     * (depending on the brgument's
     * size relbtive to the driver's limits on <code>VARCHAR</code> vblues)
     * when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setString(int pbrbmeterIndex, String x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given Jbvb brrby of bytes.  The driver converts
     * this to bn SQL <code>VARBINARY</code> or <code>LONGVARBINARY</code>
     * (depending on the brgument's size relbtive to the driver's limits on
     * <code>VARBINARY</code> vblues) when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setBytes(int pbrbmeterIndex, byte x[]) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Dbte</code> vblue
     * using the defbult time zone of the virtubl mbchine thbt is running
     * the bpplicbtion.
     * The driver converts this
     * to bn SQL <code>DATE</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setDbte(int pbrbmeterIndex, jbvb.sql.Dbte x)
            throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Time</code> vblue.
     * The driver converts this
     * to bn SQL <code>TIME</code> vblue when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setTime(int pbrbmeterIndex, jbvb.sql.Time x)
            throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Timestbmp</code> vblue.
     * The driver
     * converts this to bn SQL <code>TIMESTAMP</code> vblue when it sends it to the
     * dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>     */
    void setTimestbmp(int pbrbmeterIndex, jbvb.sql.Timestbmp x)
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
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the Jbvb input strebm thbt contbins the ASCII pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setAsciiStrebm(int pbrbmeterIndex, jbvb.io.InputStrebm x, int length)
            throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given input strebm, which
     * will hbve the specified number of bytes.
     *
     * When b very lbrge Unicode vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code> object. The dbtb will be rebd from the
     * strebm bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from Unicode to the dbtbbbse chbr formbt.
     *
     *The byte formbt of the Unicode strebm must be b Jbvb UTF-8, bs defined in the
     *Jbvb Virtubl Mbchine Specificbtion.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x b <code>jbvb.io.InputStrebm</code> object thbt contbins the
     *        Unicode pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @deprecbted Use {@code setChbrbcterStrebm}
     */
    @Deprecbted
    void setUnicodeStrebm(int pbrbmeterIndex, jbvb.io.InputStrebm x,
                          int length) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given input strebm, which will hbve
     * the specified number of bytes.
     * When b very lbrge binbry vblue is input to b <code>LONGVARBINARY</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code> object. The dbtb will be rebd from the
     * strebm bs needed until end-of-file is rebched.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the jbvb input strebm which contbins the binbry pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void setBinbryStrebm(int pbrbmeterIndex, jbvb.io.InputStrebm x,
                         int length) throws SQLException;

    /**
     * Clebrs the current pbrbmeter vblues immedibtely.
     * <P>In generbl, pbrbmeter vblues rembin in force for repebted use of b
     * stbtement. Setting b pbrbmeter vblue butombticblly clebrs its
     * previous vblue.  However, in some cbses it is useful to immedibtely
     * relebse the resources used by the current pbrbmeter vblues; this cbn
     * be done by cblling the method <code>clebrPbrbmeters</code>.
     *
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     */
    void clebrPbrbmeters() throws SQLException;

    //----------------------------------------------------------------------
    // Advbnced febtures:

   /**
    * Sets the vblue of the designbted pbrbmeter with the given object.
    *
    * This method is similbr to {@link #setObject(int pbrbmeterIndex,
    * Object x, int tbrgetSqlType, int scbleOrLength)},
    * except thbt it bssumes b scble of zero.
    *
    * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
    * @pbrbm x the object contbining the input pbrbmeter vblue
    * @pbrbm tbrgetSqlType the SQL type (bs defined in jbvb.sql.Types) to be
    *                      sent to the dbtbbbse
    * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
    * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or this
    * method is cblled on b closed PrepbredStbtement
    * @exception SQLFebtureNotSupportedException if
    * the JDBC driver does not support the specified tbrgetSqlType
    * @see Types
    */
    void setObject(int pbrbmeterIndex, Object x, int tbrgetSqlType)
      throws SQLException;

    /**
     * <p>Sets the vblue of the designbted pbrbmeter using the given object.
     *
     * <p>The JDBC specificbtion specifies b stbndbrd mbpping from
     * Jbvb <code>Object</code> types to SQL types.  The given brgument
     * will be converted to the corresponding SQL type before being
     * sent to the dbtbbbse.
     *
     * <p>Note thbt this method mby be used to pbss dbtbtbbbse-
     * specific bbstrbct dbtb types, by using b driver-specific Jbvb
     * type.
     *
     * If the object is of b clbss implementing the interfbce <code>SQLDbtb</code>,
     * the JDBC driver should cbll the method <code>SQLDbtb.writeSQL</code>
     * to write it to the SQL dbtb strebm.
     * If, on the other hbnd, the object is of b clbss implementing
     * <code>Ref</code>, <code>Blob</code>, <code>Clob</code>,  <code>NClob</code>,
     *  <code>Struct</code>, <code>jbvb.net.URL</code>, <code>RowId</code>, <code>SQLXML</code>
     * or <code>Arrby</code>, the driver should pbss it to the dbtbbbse bs b
     * vblue of the corresponding SQL type.
     * <P>
     *<b>Note:</b> Not bll dbtbbbses bllow for b non-typed Null to be sent to
     * the bbckend. For mbximum portbbility, the <code>setNull</code> or the
     * <code>setObject(int pbrbmeterIndex, Object x, int sqlType)</code>
     * method should be used
     * instebd of <code>setObject(int pbrbmeterIndex, Object x)</code>.
     *<p>
     * <b>Note:</b> This method throws bn exception if there is bn bmbiguity, for exbmple, if the
     * object is of b clbss implementing more thbn one of the interfbces nbmed bbove.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs;
     *  this method is cblled on b closed <code>PrepbredStbtement</code>
     * or the type of the given object is bmbiguous
     */
    void setObject(int pbrbmeterIndex, Object x) throws SQLException;

    /**
     * Executes the SQL stbtement in this <code>PrepbredStbtement</code> object,
     * which mby be bny kind of SQL stbtement.
     * Some prepbred stbtements return multiple results; the <code>execute</code>
     * method hbndles these complex stbtements bs well bs the simpler
     * form of stbtements hbndled by the methods <code>executeQuery</code>
     * bnd <code>executeUpdbte</code>.
     * <P>
     * The <code>execute</code> method returns b <code>boolebn</code> to
     * indicbte the form of the first result.  You must cbll either the method
     * <code>getResultSet</code> or <code>getUpdbteCount</code>
     * to retrieve the result; you must cbll <code>getMoreResults</code> to
     * move to bny subsequent result(s).
     *
     * @return <code>true</code> if the first result is b <code>ResultSet</code>
     *         object; <code>fblse</code> if the first result is bn updbte
     *         count or there is no result
     * @exception SQLException if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * or bn brgument is supplied to this method
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @see Stbtement#execute
     * @see Stbtement#getResultSet
     * @see Stbtement#getUpdbteCount
     * @see Stbtement#getMoreResults

     */
    boolebn execute() throws SQLException;

    //--------------------------JDBC 2.0-----------------------------

    /**
     * Adds b set of pbrbmeters to this <code>PrepbredStbtement</code>
     * object's bbtch of commbnds.
     *
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @see Stbtement#bddBbtch
     * @since 1.2
     */
    void bddBbtch() throws SQLException;

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
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object thbt contbins the
     *        Unicode dbtb
     * @pbrbm length the number of chbrbcters in the strebm
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @since 1.2
     */
    void setChbrbcterStrebm(int pbrbmeterIndex,
                          jbvb.io.Rebder rebder,
                          int length) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given
     *  <code>REF(&lt;structured-type&gt;)</code> vblue.
     * The driver converts this to bn SQL <code>REF</code> vblue when it
     * sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x bn SQL <code>REF</code> vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.2
     */
    void setRef (int pbrbmeterIndex, Ref x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Blob</code> object.
     * The driver converts this to bn SQL <code>BLOB</code> vblue when it
     * sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x b <code>Blob</code> object thbt mbps bn SQL <code>BLOB</code> vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.2
     */
    void setBlob (int pbrbmeterIndex, Blob x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Clob</code> object.
     * The driver converts this to bn SQL <code>CLOB</code> vblue when it
     * sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x b <code>Clob</code> object thbt mbps bn SQL <code>CLOB</code> vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.2
     */
    void setClob (int pbrbmeterIndex, Clob x) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Arrby</code> object.
     * The driver converts this to bn SQL <code>ARRAY</code> vblue when it
     * sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x bn <code>Arrby</code> object thbt mbps bn SQL <code>ARRAY</code> vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.2
     */
    void setArrby (int pbrbmeterIndex, Arrby x) throws SQLException;

    /**
     * Retrieves b <code>ResultSetMetbDbtb</code> object thbt contbins
     * informbtion bbout the columns of the <code>ResultSet</code> object
     * thbt will be returned when this <code>PrepbredStbtement</code> object
     * is executed.
     * <P>
     * Becbuse b <code>PrepbredStbtement</code> object is precompiled, it is
     * possible to know bbout the <code>ResultSet</code> object thbt it will
     * return without hbving to execute it.  Consequently, it is possible
     * to invoke the method <code>getMetbDbtb</code> on b
     * <code>PrepbredStbtement</code> object rbther thbn wbiting to execute
     * it bnd then invoking the <code>ResultSet.getMetbDbtb</code> method
     * on the <code>ResultSet</code> object thbt is returned.
     * <P>
     * <B>NOTE:</B> Using this method mby be expensive for some drivers due
     * to the lbck of underlying DBMS support.
     *
     * @return the description of b <code>ResultSet</code> object's columns or
     *         <code>null</code> if the driver cbnnot return b
     *         <code>ResultSetMetbDbtb</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    ResultSetMetbDbtb getMetbDbtb() throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Dbte</code> vblue,
     * using the given <code>Cblendbr</code> object.  The driver uses
     * the <code>Cblendbr</code> object to construct bn SQL <code>DATE</code> vblue,
     * which the driver then sends to the dbtbbbse.  With
     * b <code>Cblendbr</code> object, the driver cbn cblculbte the dbte
     * tbking into bccount b custom timezone.  If no
     * <code>Cblendbr</code> object is specified, the driver uses the defbult
     * timezone, which is thbt of the virtubl mbchine running the bpplicbtion.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the dbte
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @since 1.2
     */
    void setDbte(int pbrbmeterIndex, jbvb.sql.Dbte x, Cblendbr cbl)
            throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Time</code> vblue,
     * using the given <code>Cblendbr</code> object.  The driver uses
     * the <code>Cblendbr</code> object to construct bn SQL <code>TIME</code> vblue,
     * which the driver then sends to the dbtbbbse.  With
     * b <code>Cblendbr</code> object, the driver cbn cblculbte the time
     * tbking into bccount b custom timezone.  If no
     * <code>Cblendbr</code> object is specified, the driver uses the defbult
     * timezone, which is thbt of the virtubl mbchine running the bpplicbtion.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the time
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @since 1.2
     */
    void setTime(int pbrbmeterIndex, jbvb.sql.Time x, Cblendbr cbl)
            throws SQLException;

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Timestbmp</code> vblue,
     * using the given <code>Cblendbr</code> object.  The driver uses
     * the <code>Cblendbr</code> object to construct bn SQL <code>TIMESTAMP</code> vblue,
     * which the driver then sends to the dbtbbbse.  With b
     *  <code>Cblendbr</code> object, the driver cbn cblculbte the timestbmp
     * tbking into bccount b custom timezone.  If no
     * <code>Cblendbr</code> object is specified, the driver uses the defbult
     * timezone, which is thbt of the virtubl mbchine running the bpplicbtion.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @pbrbm cbl the <code>Cblendbr</code> object the driver will use
     *            to construct the timestbmp
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @since 1.2
     */
    void setTimestbmp(int pbrbmeterIndex, jbvb.sql.Timestbmp x, Cblendbr cbl)
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
     * pbrbmeter, the nbme is the type nbme of the referenced type.  If
     * b JDBC driver does not need the type code or type nbme informbtion,
     * it mby ignore it.
     *
     * Although it is intended for user-defined bnd Ref pbrbmeters,
     * this method mby be used to set b null pbrbmeter of bny JDBC type.
     * If the pbrbmeter does not hbve b user-defined or REF type, the given
     * typeNbme is ignored.
     *
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm sqlType b vblue from <code>jbvb.sql.Types</code>
     * @pbrbm typeNbme the fully-qublified nbme of bn SQL user-defined type;
     *  ignored if the pbrbmeter is not b user-defined type or REF
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @exception SQLFebtureNotSupportedException if <code>sqlType</code> is
     * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
     * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
     * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
     *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
     * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
     * this dbtb type or if the JDBC driver does not support this method
     * @since 1.2
     */
  void setNull (int pbrbmeterIndex, int sqlType, String typeNbme)
    throws SQLException;

    //------------------------- JDBC 3.0 -----------------------------------

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.net.URL</code> vblue.
     * The driver converts this to bn SQL <code>DATALINK</code> vblue
     * when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the <code>jbvb.net.URL</code> object to be set
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.4
     */
    void setURL(int pbrbmeterIndex, jbvb.net.URL x) throws SQLException;

    /**
     * Retrieves the number, types bnd properties of this
     * <code>PrepbredStbtement</code> object's pbrbmeters.
     *
     * @return b <code>PbrbmeterMetbDbtb</code> object thbt contbins informbtion
     *         bbout the number, types bnd properties for ebch
     *  pbrbmeter mbrker of this <code>PrepbredStbtement</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @see PbrbmeterMetbDbtb
     * @since 1.4
     */
    PbrbmeterMetbDbtb getPbrbmeterMetbDbtb() throws SQLException;

    //------------------------- JDBC 4.0 -----------------------------------

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.RowId</code> object. The
     * driver converts this to b SQL <code>ROWID</code> vblue when it sends it
     * to the dbtbbbse
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     *
     * @since 1.6
     */
    void setRowId(int pbrbmeterIndex, RowId x) throws SQLException;


    /**
     * Sets the designbted pbrbmeter to the given <code>String</code> object.
     * The driver converts this to b SQL <code>NCHAR</code> or
     * <code>NVARCHAR</code> or <code>LONGNVARCHAR</code> vblue
     * (depending on the brgument's
     * size relbtive to the driver's limits on <code>NVARCHAR</code> vblues)
     * when it sends it to the dbtbbbse.
     *
     * @pbrbm pbrbmeterIndex of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm vblue the pbrbmeter vblue
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs; or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
     void setNString(int pbrbmeterIndex, String vblue) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object. The
     * <code>Rebder</code> rebds the dbtb till end-of-file is rebched. The
     * driver does the necessbry conversion from Jbvb chbrbcter formbt to
     * the nbtionbl chbrbcter set in the dbtbbbse.
     * @pbrbm pbrbmeterIndex of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm vblue the pbrbmeter vblue
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs; or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
     void setNChbrbcterStrebm(int pbrbmeterIndex, Rebder vblue, long length) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>jbvb.sql.NClob</code> object. The driver converts this to b
     * SQL <code>NCLOB</code> vblue when it sends it to the dbtbbbse.
     * @pbrbm pbrbmeterIndex of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm vblue the pbrbmeter vblue
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs; or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
     void setNClob(int pbrbmeterIndex, NClob vblue) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object.  The rebder must contbin  the number
     * of chbrbcters specified by length otherwise b <code>SQLException</code> will be
     * generbted when the <code>PrepbredStbtement</code> is executed.
     *This method differs from the <code>setChbrbcterStrebm (int, Rebder, int)</code> method
     * becbuse it informs the driver thbt the pbrbmeter vblue should be sent to
     * the server bs b <code>CLOB</code>.  When the <code>setChbrbcterStrebm</code> method is used, the
     * driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be sent to the server bs b <code>LONGVARCHAR</code> or b <code>CLOB</code>
     * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs; this method is cblled on
     * b closed <code>PrepbredStbtement</code> or if the length specified is less thbn zero.
     *
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
     void setClob(int pbrbmeterIndex, Rebder rebder, long length)
       throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>InputStrebm</code> object.  The inputstrebm must contbin  the number
     * of chbrbcters specified by length otherwise b <code>SQLException</code> will be
     * generbted when the <code>PrepbredStbtement</code> is executed.
     * This method differs from the <code>setBinbryStrebm (int, InputStrebm, int)</code>
     * method becbuse it informs the driver thbt the pbrbmeter vblue should be
     * sent to the server bs b <code>BLOB</code>.  When the <code>setBinbryStrebm</code> method is used,
     * the driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be sent to the server bs b <code>LONGVARBINARY</code> or b <code>BLOB</code>
     * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1,
     * the second is 2, ...
     * @pbrbm inputStrebm An object thbt contbins the dbtb to set the pbrbmeter
     * vblue to.
     * @pbrbm length the number of bytes in the pbrbmeter dbtb.
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed <code>PrepbredStbtement</code>;
     *  if the length specified
     * is less thbn zero or if the number of bytes in the inputstrebm does not mbtch
     * the specified length.
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     *
     * @since 1.6
     */
     void setBlob(int pbrbmeterIndex, InputStrebm inputStrebm, long length)
        throws SQLException;
    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object.  The rebder must contbin  the number
     * of chbrbcters specified by length otherwise b <code>SQLException</code> will be
     * generbted when the <code>PrepbredStbtement</code> is executed.
     * This method differs from the <code>setChbrbcterStrebm (int, Rebder, int)</code> method
     * becbuse it informs the driver thbt the pbrbmeter vblue should be sent to
     * the server bs b <code>NCLOB</code>.  When the <code>setChbrbcterStrebm</code> method is used, the
     * driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be sent to the server bs b <code>LONGNVARCHAR</code> or b <code>NCLOB</code>
     * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if the length specified is less thbn zero;
     * if the driver does not support nbtionbl chbrbcter sets;
     * if the driver cbn detect thbt b dbtb conversion
     *  error could occur;  if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     *
     * @since 1.6
     */
     void setNClob(int pbrbmeterIndex, Rebder rebder, long length)
       throws SQLException;

     /**
      * Sets the designbted pbrbmeter to the given <code>jbvb.sql.SQLXML</code> object.
      * The driver converts this to bn
      * SQL <code>XML</code> vblue when it sends it to the dbtbbbse.
      *
      * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1, the second is 2, ...
      * @pbrbm xmlObject b <code>SQLXML</code> object thbt mbps bn SQL <code>XML</code> vblue
      * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs;
      *  this method is cblled on b closed <code>PrepbredStbtement</code>
      * or the <code>jbvb.xml.trbnsform.Result</code>,
      *  <code>Writer</code> or <code>OutputStrebm</code> hbs not been closed for
      * the <code>SQLXML</code> object
      * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
      *
      * @since 1.6
      */
     void setSQLXML(int pbrbmeterIndex, SQLXML xmlObject) throws SQLException;

    /**
     * <p>Sets the vblue of the designbted pbrbmeter with the given object.
     *
     * If the second brgument is bn <code>InputStrebm</code> then the strebm must contbin
     * the number of bytes specified by scbleOrLength.  If the second brgument is b
     * <code>Rebder</code> then the rebder must contbin the number of chbrbcters specified
     * by scbleOrLength. If these conditions bre not true the driver will generbte b
     * <code>SQLException</code> when the prepbred stbtement is executed.
     *
     * <p>The given Jbvb object will be converted to the given tbrgetSqlType
     * before being sent to the dbtbbbse.
     *
     * If the object hbs b custom mbpping (is of b clbss implementing the
     * interfbce <code>SQLDbtb</code>),
     * the JDBC driver should cbll the method <code>SQLDbtb.writeSQL</code> to
     * write it to the SQL dbtb strebm.
     * If, on the other hbnd, the object is of b clbss implementing
     * <code>Ref</code>, <code>Blob</code>, <code>Clob</code>,  <code>NClob</code>,
     *  <code>Struct</code>, <code>jbvb.net.URL</code>,
     * or <code>Arrby</code>, the driver should pbss it to the dbtbbbse bs b
     * vblue of the corresponding SQL type.
     *
     * <p>Note thbt this method mby be used to pbss dbtbbbse-specific
     * bbstrbct dbtb types.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @pbrbm tbrgetSqlType the SQL type (bs defined in jbvb.sql.Types) to be
     * sent to the dbtbbbse. The scble brgument mby further qublify this type.
     * @pbrbm scbleOrLength for <code>jbvb.sql.Types.DECIMAL</code>
     *          or <code>jbvb.sql.Types.NUMERIC types</code>,
     *          this is the number of digits bfter the decimbl point. For
     *          Jbvb Object types <code>InputStrebm</code> bnd <code>Rebder</code>,
     *          this is the length
     *          of the dbtb in the strebm or rebder.  For bll other types,
     *          this vblue will be ignored.
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed <code>PrepbredStbtement</code> or
     *            if the Jbvb Object specified by x is bn InputStrebm
     *            or Rebder object bnd the vblue of the scble pbrbmeter is less
     *            thbn zero
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified tbrgetSqlType
     * @see Types
     *
     */
    void setObject(int pbrbmeterIndex, Object x, int tbrgetSqlType, int scbleOrLength)
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
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the Jbvb input strebm thbt contbins the ASCII pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @since 1.6
    */
    void setAsciiStrebm(int pbrbmeterIndex, jbvb.io.InputStrebm x, long length)
            throws SQLException;
    /**
     * Sets the designbted pbrbmeter to the given input strebm, which will hbve
     * the specified number of bytes.
     * When b very lbrge binbry vblue is input to b <code>LONGVARBINARY</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code> object. The dbtb will be rebd from the
     * strebm bs needed until end-of-file is rebched.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the jbvb input strebm which contbins the binbry pbrbmeter vblue
     * @pbrbm length the number of bytes in the strebm
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @since 1.6
     */
    void setBinbryStrebm(int pbrbmeterIndex, jbvb.io.InputStrebm x,
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
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object thbt contbins the
     *        Unicode dbtb
     * @pbrbm length the number of chbrbcters in the strebm
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @since 1.6
     */
    void setChbrbcterStrebm(int pbrbmeterIndex,
                          jbvb.io.Rebder rebder,
                          long length) throws SQLException;
    //-----
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
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the Jbvb input strebm thbt contbins the ASCII pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
       * @since 1.6
    */
    void setAsciiStrebm(int pbrbmeterIndex, jbvb.io.InputStrebm x)
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
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the jbvb input strebm which contbins the binbry pbrbmeter vblue
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
    void setBinbryStrebm(int pbrbmeterIndex, jbvb.io.InputStrebm x)
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
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object thbt contbins the
     *        Unicode dbtb
     * @exception SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
    void setChbrbcterStrebm(int pbrbmeterIndex,
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
     * @pbrbm pbrbmeterIndex of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm vblue the pbrbmeter vblue
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; if b dbtbbbse bccess error occurs; or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
     void setNChbrbcterStrebm(int pbrbmeterIndex, Rebder vblue) throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object.
     * This method differs from the <code>setChbrbcterStrebm (int, Rebder)</code> method
     * becbuse it informs the driver thbt the pbrbmeter vblue should be sent to
     * the server bs b <code>CLOB</code>.  When the <code>setChbrbcterStrebm</code> method is used, the
     * driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be sent to the server bs b <code>LONGVARCHAR</code> or b <code>CLOB</code>
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setClob</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs; this method is cblled on
     * b closed <code>PrepbredStbtement</code>or if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement
     *
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
     */
     void setClob(int pbrbmeterIndex, Rebder rebder)
       throws SQLException;

    /**
     * Sets the designbted pbrbmeter to b <code>InputStrebm</code> object.
     * This method differs from the <code>setBinbryStrebm (int, InputStrebm)</code>
     * method becbuse it informs the driver thbt the pbrbmeter vblue should be
     * sent to the server bs b <code>BLOB</code>.  When the <code>setBinbryStrebm</code> method is used,
     * the driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be sent to the server bs b <code>LONGVARBINARY</code> or b <code>BLOB</code>
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setBlob</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1,
     * the second is 2, ...
     * @pbrbm inputStrebm An object thbt contbins the dbtb to set the pbrbmeter
     * vblue to.
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed <code>PrepbredStbtement</code> or
     * if pbrbmeterIndex does not correspond
     * to b pbrbmeter mbrker in the SQL stbtement,
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     *
     * @since 1.6
     */
     void setBlob(int pbrbmeterIndex, InputStrebm inputStrebm)
        throws SQLException;
    /**
     * Sets the designbted pbrbmeter to b <code>Rebder</code> object.
     * This method differs from the <code>setChbrbcterStrebm (int, Rebder)</code> method
     * becbuse it informs the driver thbt the pbrbmeter vblue should be sent to
     * the server bs b <code>NCLOB</code>.  When the <code>setChbrbcterStrebm</code> method is used, the
     * driver mby hbve to do extrb work to determine whether the pbrbmeter
     * dbtb should be sent to the server bs b <code>LONGNVARCHAR</code> or b <code>NCLOB</code>
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>setNClob</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
     * mbrker in the SQL stbtement;
     * if the driver does not support nbtionbl chbrbcter sets;
     * if the driver cbn detect thbt b dbtb conversion
     *  error could occur;  if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>PrepbredStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     *
     * @since 1.6
     */
     void setNClob(int pbrbmeterIndex, Rebder rebder)
       throws SQLException;

    //------------------------- JDBC 4.2 -----------------------------------

    /**
     * <p>Sets the vblue of the designbted pbrbmeter with the given object.
     *
     * If the second brgument is bn {@code InputStrebm} then the strebm
     * must contbin the number of bytes specified by scbleOrLength.
     * If the second brgument is b {@code Rebder} then the rebder must
     * contbin the number of chbrbcters specified by scbleOrLength. If these
     * conditions bre not true the driver will generbte b
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
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @pbrbm tbrgetSqlType the SQL type to be sent to the dbtbbbse. The
     * scble brgument mby further qublify this type.
     * @pbrbm scbleOrLength for {@code jbvb.sql.JDBCType.DECIMAL}
     *          or {@code jbvb.sql.JDBCType.NUMERIC types},
     *          this is the number of digits bfter the decimbl point. For
     *          Jbvb Object types {@code InputStrebm} bnd {@code Rebder},
     *          this is the length
     *          of the dbtb in the strebm or rebder.  For bll other types,
     *          this vblue will be ignored.
     * @exception SQLException if pbrbmeterIndex does not correspond to b
     * pbrbmeter mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed {@code PrepbredStbtement}  or
     *            if the Jbvb Object specified by x is bn InputStrebm
     *            or Rebder object bnd the vblue of the scble pbrbmeter is less
     *            thbn zero
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified tbrgetSqlType
     * @see JDBCType
     * @see SQLType
     * @since 1.8
     */
    defbult void setObject(int pbrbmeterIndex, Object x, SQLType tbrgetSqlType,
             int scbleOrLength) throws SQLException {
        throw new SQLFebtureNotSupportedException("setObject not implemented");
    }

    /**
     * Sets the vblue of the designbted pbrbmeter with the given object.
     *
     * This method is similbr to {@link #setObject(int pbrbmeterIndex,
     * Object x, SQLType tbrgetSqlType, int scbleOrLength)},
     * except thbt it bssumes b scble of zero.
     *<P>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the object contbining the input pbrbmeter vblue
     * @pbrbm tbrgetSqlType the SQL type to be sent to the dbtbbbse
     * @exception SQLException if pbrbmeterIndex does not correspond to b
     * pbrbmeter mbrker in the SQL stbtement; if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed {@code PrepbredStbtement}
     * @exception SQLFebtureNotSupportedException if
     * the JDBC driver does not support the specified tbrgetSqlType
     * @see JDBCType
     * @see SQLType
     * @since 1.8
     */
    defbult void setObject(int pbrbmeterIndex, Object x, SQLType tbrgetSqlType)
      throws SQLException {
        throw new SQLFebtureNotSupportedException("setObject not implemented");
    }

    /**
     * Executes the SQL stbtement in this <code>PrepbredStbtement</code> object,
     * which must be bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement,
     * such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     * <p>
     * This method should be used when the returned row count mby exceed
     * {@link Integer#MAX_VALUE}.
     * <p>
     * The defbult implementbtion will throw {@code UnsupportedOperbtionException}
     *
     * @return either (1) the row count for SQL Dbtb Mbnipulbtion Lbngubge
     * (DML) stbtements or (2) 0 for SQL stbtements thbt return nothing
     * @exception SQLException if b dbtbbbse bccess error occurs;
     * this method is cblled on b closed  <code>PrepbredStbtement</code>
     * or the SQL stbtement returns b <code>ResultSet</code> object
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @since 1.8
     */
    defbult long executeLbrgeUpdbte() throws SQLException {
        throw new UnsupportedOperbtionException("executeLbrgeUpdbte not implemented");
    }
}
