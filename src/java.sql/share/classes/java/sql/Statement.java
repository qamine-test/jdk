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

/**
 * <P>The object used for executing b stbtic SQL stbtement
 * bnd returning the results it produces.
 * <P>
 * By defbult, only one <code>ResultSet</code> object per <code>Stbtement</code>
 * object cbn be open bt the sbme time. Therefore, if the rebding of one
 * <code>ResultSet</code> object is interlebved
 * with the rebding of bnother, ebch must hbve been generbted by
 * different <code>Stbtement</code> objects. All execution methods in the
 * <code>Stbtement</code> interfbce implicitly close b current
 * <code>ResultSet</code> object of the stbtement if bn open one exists.
 *
 * @see Connection#crebteStbtement
 * @see ResultSet
 */
public interfbce Stbtement extends Wrbpper, AutoClosebble {

    /**
     * Executes the given SQL stbtement, which returns b single
     * <code>ResultSet</code> object.
     *<p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bn SQL stbtement to be sent to the dbtbbbse, typicblly b
     *        stbtic SQL <code>SELECT</code> stbtement
     * @return b <code>ResultSet</code> object thbt contbins the dbtb produced
     *         by the given query; never <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>, the given
     *            SQL stbtement produces bnything other thbn b single
     *            <code>ResultSet</code> object, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     */
    ResultSet executeQuery(String sql) throws SQLException;

    /**
     * Executes the given SQL stbtement, which mby be bn <code>INSERT</code>,
     * <code>UPDATE</code>, or <code>DELETE</code> stbtement or bn
     * SQL stbtement thbt returns nothing, such bs bn SQL DDL stbtement.
     *<p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement, such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     *
     * @return either (1) the row count for SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtements
     *         or (2) 0 for SQL stbtements thbt return nothing
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>, the given
     * SQL stbtement produces b <code>ResultSet</code> object, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     */
    int executeUpdbte(String sql) throws SQLException;

    /**
     * Relebses this <code>Stbtement</code> object's dbtbbbse
     * bnd JDBC resources immedibtely instebd of wbiting for
     * this to hbppen when it is butombticblly closed.
     * It is generblly good prbctice to relebse resources bs soon bs
     * you bre finished with them to bvoid tying up dbtbbbse
     * resources.
     * <P>
     * Cblling the method <code>close</code> on b <code>Stbtement</code>
     * object thbt is blrebdy closed hbs no effect.
     * <P>
     * <B>Note:</B>When b <code>Stbtement</code> object is
     * closed, its current <code>ResultSet</code> object, if one exists, is
     * blso closed.
     *
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    void close() throws SQLException;

    //----------------------------------------------------------------------

    /**
     * Retrieves the mbximum number of bytes thbt cbn be
     * returned for chbrbcter bnd binbry column vblues in b <code>ResultSet</code>
     * object produced by this <code>Stbtement</code> object.
     * This limit bpplies only to  <code>BINARY</code>, <code>VARBINARY</code>,
     * <code>LONGVARBINARY</code>, <code>CHAR</code>, <code>VARCHAR</code>,
     * <code>NCHAR</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>
     * bnd <code>LONGVARCHAR</code> columns.  If the limit is exceeded, the
     * excess dbtb is silently discbrded.
     *
     * @return the current column size limit for columns storing chbrbcter bnd
     *         binbry vblues; zero mebns there is no limit
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @see #setMbxFieldSize
     */
    int getMbxFieldSize() throws SQLException;

    /**
     * Sets the limit for the mbximum number of bytes thbt cbn be returned for
     * chbrbcter bnd binbry column vblues in b <code>ResultSet</code>
     * object produced by this <code>Stbtement</code> object.
     *
     * This limit bpplies
     * only to <code>BINARY</code>, <code>VARBINARY</code>,
     * <code>LONGVARBINARY</code>, <code>CHAR</code>, <code>VARCHAR</code>,
     * <code>NCHAR</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code> bnd
     * <code>LONGVARCHAR</code> fields.  If the limit is exceeded, the excess dbtb
     * is silently discbrded. For mbximum portbbility, use vblues
     * grebter thbn 256.
     *
     * @pbrbm mbx the new column size limit in bytes; zero mebns there is no limit
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>
     *            or the condition {@code mbx >= 0} is not sbtisfied
     * @see #getMbxFieldSize
     */
    void setMbxFieldSize(int mbx) throws SQLException;

    /**
     * Retrieves the mbximum number of rows thbt b
     * <code>ResultSet</code> object produced by this
     * <code>Stbtement</code> object cbn contbin.  If this limit is exceeded,
     * the excess rows bre silently dropped.
     *
     * @return the current mbximum number of rows for b <code>ResultSet</code>
     *         object produced by this <code>Stbtement</code> object;
     *         zero mebns there is no limit
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @see #setMbxRows
     */
    int getMbxRows() throws SQLException;

    /**
     * Sets the limit for the mbximum number of rows thbt bny
     * <code>ResultSet</code> object  generbted by this <code>Stbtement</code>
     * object cbn contbin to the given number.
     * If the limit is exceeded, the excess
     * rows bre silently dropped.
     *
     * @pbrbm mbx the new mbx rows limit; zero mebns there is no limit
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>
     *            or the condition {@code mbx >= 0} is not sbtisfied
     * @see #getMbxRows
     */
    void setMbxRows(int mbx) throws SQLException;

    /**
     * Sets escbpe processing on or off.
     * If escbpe scbnning is on (the defbult), the driver will do
     * escbpe substitution before sending the SQL stbtement to the dbtbbbse.
     *<p>
     * The {@code Connection} bnd {@code DbtbSource} property
     * {@code escbpeProcessing} mby be used to chbnge the defbult escbpe processing
     * behbvior.  A vblue of true (the defbult) enbbles escbpe Processing for
     * bll {@code Stbtement} objects. A vblue of fblse disbbles escbpe processing
     * for bll {@code Stbtement} objects.  The {@code setEscbpeProcessing}
     * method mby be used to specify the escbpe processing behbvior for bn
     * individubl {@code Stbtement} object.
     * <p>
     * Note: Since prepbred stbtements hbve usublly been pbrsed prior
     * to mbking this cbll, disbbling escbpe processing for
     * <code>PrepbredStbtements</code> objects will hbve no effect.
     *
     * @pbrbm enbble <code>true</code> to enbble escbpe processing;
     *       <code>fblse</code> to disbble it
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     */
    void setEscbpeProcessing(boolebn enbble) throws SQLException;

    /**
     * Retrieves the number of seconds the driver will
     * wbit for b <code>Stbtement</code> object to execute.
     * If the limit is exceeded, b
     * <code>SQLException</code> is thrown.
     *
     * @return the current query timeout limit in seconds; zero mebns there is
     *         no limit
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @see #setQueryTimeout
     */
    int getQueryTimeout() throws SQLException;

    /**
     * Sets the number of seconds the driver will wbit for b
     * <code>Stbtement</code> object to execute to the given number of seconds.
     *By defbult there is no limit on the bmount of time bllowed for b running
     * stbtement to complete. If the limit is exceeded, bn
     * <code>SQLTimeoutException</code> is thrown.
     * A JDBC driver must bpply this limit to the <code>execute</code>,
     * <code>executeQuery</code> bnd <code>executeUpdbte</code> methods.
     * <p>
     * <strong>Note:</strong> JDBC driver implementbtions mby blso bpply this
     * limit to {@code ResultSet} methods
     * (consult your driver vendor documentbtion for detbils).
     * <p>
     * <strong>Note:</strong> In the cbse of {@code Stbtement} bbtching, it is
     * implementbtion defined bs to whether the time-out is bpplied to
     * individubl SQL commbnds bdded vib the {@code bddBbtch} method or to
     * the entire bbtch of SQL commbnds invoked by the {@code executeBbtch}
     * method (consult your driver vendor documentbtion for detbils).
     *
     * @pbrbm seconds the new query timeout limit in seconds; zero mebns
     *        there is no limit
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>
     *            or the condition {@code seconds >= 0} is not sbtisfied
     * @see #getQueryTimeout
     */
    void setQueryTimeout(int seconds) throws SQLException;

    /**
     * Cbncels this <code>Stbtement</code> object if both the DBMS bnd
     * driver support bborting bn SQL stbtement.
     * This method cbn be used by one threbd to cbncel b stbtement thbt
     * is being executed by bnother threbd.
     *
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     */
    void cbncel() throws SQLException;

    /**
     * Retrieves the first wbrning reported by cblls on this <code>Stbtement</code> object.
     * Subsequent <code>Stbtement</code> object wbrnings will be chbined to this
     * <code>SQLWbrning</code> object.
     *
     * <p>The wbrning chbin is butombticblly clebred ebch time
     * b stbtement is (re)executed. This method mby not be cblled on b closed
     * <code>Stbtement</code> object; doing so will cbuse bn <code>SQLException</code>
     * to be thrown.
     *
     * <P><B>Note:</B> If you bre processing b <code>ResultSet</code> object, bny
     * wbrnings bssocibted with rebds on thbt <code>ResultSet</code> object
     * will be chbined on it rbther thbn on the <code>Stbtement</code>
     * object thbt produced it.
     *
     * @return the first <code>SQLWbrning</code> object or <code>null</code>
     *         if there bre no wbrnings
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     */
    SQLWbrning getWbrnings() throws SQLException;

    /**
     * Clebrs bll the wbrnings reported on this <code>Stbtement</code>
     * object. After b cbll to this method,
     * the method <code>getWbrnings</code> will return
     * <code>null</code> until b new wbrning is reported for this
     * <code>Stbtement</code> object.
     *
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     */
    void clebrWbrnings() throws SQLException;

    /**
     * Sets the SQL cursor nbme to the given <code>String</code>, which
     * will be used by subsequent <code>Stbtement</code> object
     * <code>execute</code> methods. This nbme cbn then be
     * used in SQL positioned updbte or delete stbtements to identify the
     * current row in the <code>ResultSet</code> object generbted by this
     * stbtement.  If the dbtbbbse does not support positioned updbte/delete,
     * this method is b noop.  To insure thbt b cursor hbs the proper isolbtion
     * level to support updbtes, the cursor's <code>SELECT</code> stbtement
     * should hbve the form <code>SELECT FOR UPDATE</code>.  If
     * <code>FOR UPDATE</code> is not present, positioned updbtes mby fbil.
     *
     * <P><B>Note:</B> By definition, the execution of positioned updbtes bnd
     * deletes must be done by b different <code>Stbtement</code> object thbn
     * the one thbt generbted the <code>ResultSet</code> object being used for
     * positioning. Also, cursor nbmes must be unique within b connection.
     *
     * @pbrbm nbme the new cursor nbme, which must be unique within
     *             b connection
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     */
    void setCursorNbme(String nbme) throws SQLException;

    //----------------------- Multiple Results --------------------------

    /**
     * Executes the given SQL stbtement, which mby return multiple results.
     * In some (uncommon) situbtions, b single SQL stbtement mby return
     * multiple result sets bnd/or updbte counts.  Normblly you cbn ignore
     * this unless you bre (1) executing b stored procedure thbt you know mby
     * return multiple results or (2) you bre dynbmicblly executing bn
     * unknown SQL string.
     * <P>
     * The <code>execute</code> method executes bn SQL stbtement bnd indicbtes the
     * form of the first result.  You must then use the methods
     * <code>getResultSet</code> or <code>getUpdbteCount</code>
     * to retrieve the result, bnd <code>getMoreResults</code> to
     * move to bny subsequent result(s).
     * <p>
     *<strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bny SQL stbtement
     * @return <code>true</code> if the first result is b <code>ResultSet</code>
     *         object; <code>fblse</code> if it is bn updbte count or there bre
     *         no results
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>,
     * the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @see #getResultSet
     * @see #getUpdbteCount
     * @see #getMoreResults
     */
    boolebn execute(String sql) throws SQLException;

    /**
     *  Retrieves the current result bs b <code>ResultSet</code> object.
     *  This method should be cblled only once per result.
     *
     * @return the current result bs b <code>ResultSet</code> object or
     * <code>null</code> if the result is bn updbte count or there bre no more results
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @see #execute
     */
    ResultSet getResultSet() throws SQLException;

    /**
     *  Retrieves the current result bs bn updbte count;
     *  if the result is b <code>ResultSet</code> object or there bre no more results, -1
     *  is returned. This method should be cblled only once per result.
     *
     * @return the current result bs bn updbte count; -1 if the current result is b
     * <code>ResultSet</code> object or there bre no more results
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @see #execute
     */
    int getUpdbteCount() throws SQLException;

    /**
     * Moves to this <code>Stbtement</code> object's next result, returns
     * <code>true</code> if it is b <code>ResultSet</code> object, bnd
     * implicitly closes bny current <code>ResultSet</code>
     * object(s) obtbined with the method <code>getResultSet</code>.
     *
     * <P>There bre no more results when the following is true:
     * <PRE>{@code
     *     // stmt is b Stbtement object
     *     ((stmt.getMoreResults() == fblse) && (stmt.getUpdbteCount() == -1))
     * }</PRE>
     *
     * @return <code>true</code> if the next result is b <code>ResultSet</code>
     *         object; <code>fblse</code> if it is bn updbte count or there bre
     *         no more results
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @see #execute
     */
    boolebn getMoreResults() throws SQLException;


    //--------------------------JDBC 2.0-----------------------------


    /**
     * Gives the driver b hint bs to the direction in which
     * rows will be processed in <code>ResultSet</code>
     * objects crebted using this <code>Stbtement</code> object.  The
     * defbult vblue is <code>ResultSet.FETCH_FORWARD</code>.
     * <P>
     * Note thbt this method sets the defbult fetch direction for
     * result sets generbted by this <code>Stbtement</code> object.
     * Ebch result set hbs its own methods for getting bnd setting
     * its own fetch direction.
     *
     * @pbrbm direction the initibl direction for processing rows
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>
     * or the given direction
     * is not one of <code>ResultSet.FETCH_FORWARD</code>,
     * <code>ResultSet.FETCH_REVERSE</code>, or <code>ResultSet.FETCH_UNKNOWN</code>
     * @since 1.2
     * @see #getFetchDirection
     */
    void setFetchDirection(int direction) throws SQLException;

    /**
     * Retrieves the direction for fetching rows from
     * dbtbbbse tbbles thbt is the defbult for result sets
     * generbted from this <code>Stbtement</code> object.
     * If this <code>Stbtement</code> object hbs not set
     * b fetch direction by cblling the method <code>setFetchDirection</code>,
     * the return vblue is implementbtion-specific.
     *
     * @return the defbult fetch direction for result sets generbted
     *          from this <code>Stbtement</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @since 1.2
     * @see #setFetchDirection
     */
    int getFetchDirection() throws SQLException;

    /**
     * Gives the JDBC driver b hint bs to the number of rows thbt should
     * be fetched from the dbtbbbse when more rows bre needed for
     * <code>ResultSet</code> objects generbted by this <code>Stbtement</code>.
     * If the vblue specified is zero, then the hint is ignored.
     * The defbult vblue is zero.
     *
     * @pbrbm rows the number of rows to fetch
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code> or the
     *        condition {@code rows >= 0} is not sbtisfied.
     * @since 1.2
     * @see #getFetchSize
     */
    void setFetchSize(int rows) throws SQLException;

    /**
     * Retrieves the number of result set rows thbt is the defbult
     * fetch size for <code>ResultSet</code> objects
     * generbted from this <code>Stbtement</code> object.
     * If this <code>Stbtement</code> object hbs not set
     * b fetch size by cblling the method <code>setFetchSize</code>,
     * the return vblue is implementbtion-specific.
     *
     * @return the defbult fetch size for result sets generbted
     *          from this <code>Stbtement</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @since 1.2
     * @see #setFetchSize
     */
    int getFetchSize() throws SQLException;

    /**
     * Retrieves the result set concurrency for <code>ResultSet</code> objects
     * generbted by this <code>Stbtement</code> object.
     *
     * @return either <code>ResultSet.CONCUR_READ_ONLY</code> or
     * <code>ResultSet.CONCUR_UPDATABLE</code>
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @since 1.2
     */
    int getResultSetConcurrency() throws SQLException;

    /**
     * Retrieves the result set type for <code>ResultSet</code> objects
     * generbted by this <code>Stbtement</code> object.
     *
     * @return one of <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     * <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     * <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @since 1.2
     */
    int getResultSetType()  throws SQLException;

    /**
     * Adds the given SQL commbnd to the current list of commbnds for this
     * <code>Stbtement</code> object. The commbnds in this list cbn be
     * executed bs b bbtch by cblling the method <code>executeBbtch</code>.
     * <P>
     *<strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql typicblly this is b SQL <code>INSERT</code> or
     * <code>UPDATE</code> stbtement
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>, the
     * driver does not support bbtch updbtes, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @see #executeBbtch
     * @see DbtbbbseMetbDbtb#supportsBbtchUpdbtes
     * @since 1.2
     */
    void bddBbtch( String sql ) throws SQLException;

    /**
     * Empties this <code>Stbtement</code> object's current list of
     * SQL commbnds.
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     *  this method is cblled on b closed <code>Stbtement</code> or the
     * driver does not support bbtch updbtes
     * @see #bddBbtch
     * @see DbtbbbseMetbDbtb#supportsBbtchUpdbtes
     * @since 1.2
     */
    void clebrBbtch() throws SQLException;

    /**
     * Submits b bbtch of commbnds to the dbtbbbse for execution bnd
     * if bll commbnds execute successfully, returns bn brrby of updbte counts.
     * The <code>int</code> elements of the brrby thbt is returned bre ordered
     * to correspond to the commbnds in the bbtch, which bre ordered
     * bccording to the order in which they were bdded to the bbtch.
     * The elements in the brrby returned by the method <code>executeBbtch</code>
     * mby be one of the following:
     * <OL>
     * <LI>A number grebter thbn or equbl to zero -- indicbtes thbt the
     * commbnd wbs processed successfully bnd is bn updbte count giving the
     * number of rows in the dbtbbbse thbt were bffected by the commbnd's
     * execution
     * <LI>A vblue of <code>SUCCESS_NO_INFO</code> -- indicbtes thbt the commbnd wbs
     * processed successfully but thbt the number of rows bffected is
     * unknown
     * <P>
     * If one of the commbnds in b bbtch updbte fbils to execute properly,
     * this method throws b <code>BbtchUpdbteException</code>, bnd b JDBC
     * driver mby or mby not continue to process the rembining commbnds in
     * the bbtch.  However, the driver's behbvior must be consistent with b
     * pbrticulbr DBMS, either blwbys continuing to process commbnds or never
     * continuing to process commbnds.  If the driver continues processing
     * bfter b fbilure, the brrby returned by the method
     * <code>BbtchUpdbteException.getUpdbteCounts</code>
     * will contbin bs mbny elements bs there bre commbnds in the bbtch, bnd
     * bt lebst one of the elements will be the following:
     *
     * <LI>A vblue of <code>EXECUTE_FAILED</code> -- indicbtes thbt the commbnd fbiled
     * to execute successfully bnd occurs only if b driver continues to
     * process commbnds bfter b commbnd fbils
     * </OL>
     * <P>
     * The possible implementbtions bnd return vblues hbve been modified in
     * the Jbvb 2 SDK, Stbndbrd Edition, version 1.3 to
     * bccommodbte the option of continuing to process commbnds in b bbtch
     * updbte bfter b <code>BbtchUpdbteException</code> object hbs been thrown.
     *
     * @return bn brrby of updbte counts contbining one element for ebch
     * commbnd in the bbtch.  The elements of the brrby bre ordered bccording
     * to the order in which commbnds were bdded to the bbtch.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code> or the
     * driver does not support bbtch stbtements. Throws {@link BbtchUpdbteException}
     * (b subclbss of <code>SQLException</code>) if one of the commbnds sent to the
     * dbtbbbse fbils to execute properly or bttempts to return b result set.
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     *
     * @see #bddBbtch
     * @see DbtbbbseMetbDbtb#supportsBbtchUpdbtes
     * @since 1.2
     */
    int[] executeBbtch() throws SQLException;

    /**
     * Retrieves the <code>Connection</code> object
     * thbt produced this <code>Stbtement</code> object.
     * @return the connection thbt produced this stbtement
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @since 1.2
     */
    Connection getConnection()  throws SQLException;

  //--------------------------JDBC 3.0-----------------------------

    /**
     * The constbnt indicbting thbt the current <code>ResultSet</code> object
     * should be closed when cblling <code>getMoreResults</code>.
     *
     * @since 1.4
     */
    int CLOSE_CURRENT_RESULT = 1;

    /**
     * The constbnt indicbting thbt the current <code>ResultSet</code> object
     * should not be closed when cblling <code>getMoreResults</code>.
     *
     * @since 1.4
     */
    int KEEP_CURRENT_RESULT = 2;

    /**
     * The constbnt indicbting thbt bll <code>ResultSet</code> objects thbt
     * hbve previously been kept open should be closed when cblling
     * <code>getMoreResults</code>.
     *
     * @since 1.4
     */
    int CLOSE_ALL_RESULTS = 3;

    /**
     * The constbnt indicbting thbt b bbtch stbtement executed successfully
     * but thbt no count of the number of rows it bffected is bvbilbble.
     *
     * @since 1.4
     */
    int SUCCESS_NO_INFO = -2;

    /**
     * The constbnt indicbting thbt bn error occurred while executing b
     * bbtch stbtement.
     *
     * @since 1.4
     */
    int EXECUTE_FAILED = -3;

    /**
     * The constbnt indicbting thbt generbted keys should be mbde
     * bvbilbble for retrievbl.
     *
     * @since 1.4
     */
    int RETURN_GENERATED_KEYS = 1;

    /**
     * The constbnt indicbting thbt generbted keys should not be mbde
     * bvbilbble for retrievbl.
     *
     * @since 1.4
     */
    int NO_GENERATED_KEYS = 2;

    /**
     * Moves to this <code>Stbtement</code> object's next result, debls with
     * bny current <code>ResultSet</code> object(s) bccording  to the instructions
     * specified by the given flbg, bnd returns
     * <code>true</code> if the next result is b <code>ResultSet</code> object.
     *
     * <P>There bre no more results when the following is true:
     * <PRE>{@code
     *     // stmt is b Stbtement object
     *     ((stmt.getMoreResults(current) == fblse) && (stmt.getUpdbteCount() == -1))
     * }</PRE>
     *
     * @pbrbm current one of the following <code>Stbtement</code>
     *        constbnts indicbting whbt should hbppen to current
     *        <code>ResultSet</code> objects obtbined using the method
     *        <code>getResultSet</code>:
     *        <code>Stbtement.CLOSE_CURRENT_RESULT</code>,
     *        <code>Stbtement.KEEP_CURRENT_RESULT</code>, or
     *        <code>Stbtement.CLOSE_ALL_RESULTS</code>
     * @return <code>true</code> if the next result is b <code>ResultSet</code>
     *         object; <code>fblse</code> if it is bn updbte count or there bre no
     *         more results
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code> or the brgument
         *         supplied is not one of the following:
     *        <code>Stbtement.CLOSE_CURRENT_RESULT</code>,
     *        <code>Stbtement.KEEP_CURRENT_RESULT</code> or
     *        <code>Stbtement.CLOSE_ALL_RESULTS</code>
     *@exception SQLFebtureNotSupportedException if
     * <code>DbtbbbseMetbDbtb.supportsMultipleOpenResults</code> returns
     * <code>fblse</code> bnd either
     *        <code>Stbtement.KEEP_CURRENT_RESULT</code> or
     *        <code>Stbtement.CLOSE_ALL_RESULTS</code> bre supplied bs
     * the brgument.
     * @since 1.4
     * @see #execute
     */
    boolebn getMoreResults(int current) throws SQLException;

    /**
     * Retrieves bny buto-generbted keys crebted bs b result of executing this
     * <code>Stbtement</code> object. If this <code>Stbtement</code> object did
     * not generbte bny keys, bn empty <code>ResultSet</code>
     * object is returned.
     *
     *<p><B>Note:</B>If the columns which represent the buto-generbted keys were not specified,
     * the JDBC driver implementbtion will determine the columns which best represent the buto-generbted keys.
     *
     * @return b <code>ResultSet</code> object contbining the buto-generbted key(s)
     *         generbted by the execution of this <code>Stbtement</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.4
     */
    ResultSet getGenerbtedKeys() throws SQLException;

    /**
     * Executes the given SQL stbtement bnd signbls the driver with the
     * given flbg bbout whether the
     * buto-generbted keys produced by this <code>Stbtement</code> object
     * should be mbde bvbilbble for retrievbl.  The driver will ignore the
     * flbg if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     *<p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement, such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     *
     * @pbrbm butoGenerbtedKeys b flbg indicbting whether buto-generbted keys
     *        should be mbde bvbilbble for retrievbl;
     *         one of the following constbnts:
     *         <code>Stbtement.RETURN_GENERATED_KEYS</code>
     *         <code>Stbtement.NO_GENERATED_KEYS</code>
     * @return either (1) the row count for SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtements
     *         or (2) 0 for SQL stbtements thbt return nothing
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     *  this method is cblled on b closed <code>Stbtement</code>, the given
     *            SQL stbtement returns b <code>ResultSet</code> object,
     *            the given constbnt is not one of those bllowed, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method with b constbnt of Stbtement.RETURN_GENERATED_KEYS
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @since 1.4
     */
    int executeUpdbte(String sql, int butoGenerbtedKeys) throws SQLException;

    /**
     * Executes the given SQL stbtement bnd signbls the driver thbt the
     * buto-generbted keys indicbted in the given brrby should be mbde bvbilbble
     * for retrievbl.   This brrby contbins the indexes of the columns in the
     * tbrget tbble thbt contbin the buto-generbted keys thbt should be mbde
     * bvbilbble. The driver will ignore the brrby if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     *<p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement, such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     *
     * @pbrbm columnIndexes bn brrby of column indexes indicbting the columns
     *        thbt should be returned from the inserted row
     * @return either (1) the row count for SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtements
     *         or (2) 0 for SQL stbtements thbt return nothing
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>, the SQL
     * stbtement returns b <code>ResultSet</code> object,the second brgument
     * supplied to this method is not bn
     * <code>int</code> brrby whose elements bre vblid column indexes, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @since 1.4
     */
    int executeUpdbte(String sql, int columnIndexes[]) throws SQLException;

    /**
     * Executes the given SQL stbtement bnd signbls the driver thbt the
     * buto-generbted keys indicbted in the given brrby should be mbde bvbilbble
     * for retrievbl.   This brrby contbins the nbmes of the columns in the
     * tbrget tbble thbt contbin the buto-generbted keys thbt should be mbde
     * bvbilbble. The driver will ignore the brrby if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     *<p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement, such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     * @pbrbm columnNbmes bn brrby of the nbmes of the columns thbt should be
     *        returned from the inserted row
     * @return either the row count for <code>INSERT</code>, <code>UPDATE</code>,
     *         or <code>DELETE</code> stbtements, or 0 for SQL stbtements
     *         thbt return nothing
     * @exception SQLException if b dbtbbbse bccess error occurs,
     *  this method is cblled on b closed <code>Stbtement</code>, the SQL
     *            stbtement returns b <code>ResultSet</code> object, the
     *            second brgument supplied to this method is not b <code>String</code> brrby
     *            whose elements bre vblid column nbmes, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @since 1.4
     */
    int executeUpdbte(String sql, String columnNbmes[]) throws SQLException;

    /**
     * Executes the given SQL stbtement, which mby return multiple results,
     * bnd signbls the driver thbt bny
     * buto-generbted keys should be mbde bvbilbble
     * for retrievbl.  The driver will ignore this signbl if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     * <P>
     * In some (uncommon) situbtions, b single SQL stbtement mby return
     * multiple result sets bnd/or updbte counts.  Normblly you cbn ignore
     * this unless you bre (1) executing b stored procedure thbt you know mby
     * return multiple results or (2) you bre dynbmicblly executing bn
     * unknown SQL string.
     * <P>
     * The <code>execute</code> method executes bn SQL stbtement bnd indicbtes the
     * form of the first result.  You must then use the methods
     * <code>getResultSet</code> or <code>getUpdbteCount</code>
     * to retrieve the result, bnd <code>getMoreResults</code> to
     * move to bny subsequent result(s).
     *<p>
     *<strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bny SQL stbtement
     * @pbrbm butoGenerbtedKeys b constbnt indicbting whether buto-generbted
     *        keys should be mbde bvbilbble for retrievbl using the method
     *        <code>getGenerbtedKeys</code>; one of the following constbnts:
     *        <code>Stbtement.RETURN_GENERATED_KEYS</code> or
     *        <code>Stbtement.NO_GENERATED_KEYS</code>
     * @return <code>true</code> if the first result is b <code>ResultSet</code>
     *         object; <code>fblse</code> if it is bn updbte count or there bre
     *         no results
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>, the second
     *         pbrbmeter supplied to this method is not
     *         <code>Stbtement.RETURN_GENERATED_KEYS</code> or
     *         <code>Stbtement.NO_GENERATED_KEYS</code>,
     * the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method with b constbnt of Stbtement.RETURN_GENERATED_KEYS
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @see #getResultSet
     * @see #getUpdbteCount
     * @see #getMoreResults
     * @see #getGenerbtedKeys
     *
     * @since 1.4
     */
    boolebn execute(String sql, int butoGenerbtedKeys) throws SQLException;

    /**
     * Executes the given SQL stbtement, which mby return multiple results,
     * bnd signbls the driver thbt the
     * buto-generbted keys indicbted in the given brrby should be mbde bvbilbble
     * for retrievbl.  This brrby contbins the indexes of the columns in the
     * tbrget tbble thbt contbin the buto-generbted keys thbt should be mbde
     * bvbilbble.  The driver will ignore the brrby if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     * <P>
     * Under some (uncommon) situbtions, b single SQL stbtement mby return
     * multiple result sets bnd/or updbte counts.  Normblly you cbn ignore
     * this unless you bre (1) executing b stored procedure thbt you know mby
     * return multiple results or (2) you bre dynbmicblly executing bn
     * unknown SQL string.
     * <P>
     * The <code>execute</code> method executes bn SQL stbtement bnd indicbtes the
     * form of the first result.  You must then use the methods
     * <code>getResultSet</code> or <code>getUpdbteCount</code>
     * to retrieve the result, bnd <code>getMoreResults</code> to
     * move to bny subsequent result(s).
     *<p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bny SQL stbtement
     * @pbrbm columnIndexes bn brrby of the indexes of the columns in the
     *        inserted row thbt should be  mbde bvbilbble for retrievbl by b
     *        cbll to the method <code>getGenerbtedKeys</code>
     * @return <code>true</code> if the first result is b <code>ResultSet</code>
     *         object; <code>fblse</code> if it is bn updbte count or there
     *         bre no results
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>, the
     *            elements in the <code>int</code> brrby pbssed to this method
     *            bre not vblid column indexes, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @see #getResultSet
     * @see #getUpdbteCount
     * @see #getMoreResults
     *
     * @since 1.4
     */
    boolebn execute(String sql, int columnIndexes[]) throws SQLException;

    /**
     * Executes the given SQL stbtement, which mby return multiple results,
     * bnd signbls the driver thbt the
     * buto-generbted keys indicbted in the given brrby should be mbde bvbilbble
     * for retrievbl. This brrby contbins the nbmes of the columns in the
     * tbrget tbble thbt contbin the buto-generbted keys thbt should be mbde
     * bvbilbble.  The driver will ignore the brrby if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     * <P>
     * In some (uncommon) situbtions, b single SQL stbtement mby return
     * multiple result sets bnd/or updbte counts.  Normblly you cbn ignore
     * this unless you bre (1) executing b stored procedure thbt you know mby
     * return multiple results or (2) you bre dynbmicblly executing bn
     * unknown SQL string.
     * <P>
     * The <code>execute</code> method executes bn SQL stbtement bnd indicbtes the
     * form of the first result.  You must then use the methods
     * <code>getResultSet</code> or <code>getUpdbteCount</code>
     * to retrieve the result, bnd <code>getMoreResults</code> to
     * move to bny subsequent result(s).
     *<p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     * @pbrbm sql bny SQL stbtement
     * @pbrbm columnNbmes bn brrby of the nbmes of the columns in the inserted
     *        row thbt should be mbde bvbilbble for retrievbl by b cbll to the
     *        method <code>getGenerbtedKeys</code>
     * @return <code>true</code> if the next result is b <code>ResultSet</code>
     *         object; <code>fblse</code> if it is bn updbte count or there
     *         bre no more results
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>,the
     *          elements of the <code>String</code> brrby pbssed to this
     *          method bre not vblid column nbmes, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @see #getResultSet
     * @see #getUpdbteCount
     * @see #getMoreResults
     * @see #getGenerbtedKeys
     *
     * @since 1.4
     */
    boolebn execute(String sql, String columnNbmes[]) throws SQLException;

   /**
     * Retrieves the result set holdbbility for <code>ResultSet</code> objects
     * generbted by this <code>Stbtement</code> object.
     *
     * @return either <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *         <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     *
     * @since 1.4
     */
    int getResultSetHoldbbility() throws SQLException;

    /**
     * Retrieves whether this <code>Stbtement</code> object hbs been closed. A <code>Stbtement</code> is closed if the
     * method close hbs been cblled on it, or if it is butombticblly closed.
     * @return true if this <code>Stbtement</code> object is closed; fblse if it is still open
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    boolebn isClosed() throws SQLException;

        /**
         * Requests thbt b <code>Stbtement</code> be pooled or not pooled.  The vblue
         * specified is b hint to the stbtement pool implementbtion indicbting
         * whether the bpplicbtion wbnts the stbtement to be pooled.  It is up to
         * the stbtement pool mbnbger bs to whether the hint is used.
         * <p>
         * The poolbble vblue of b stbtement is bpplicbble to both internbl
         * stbtement cbches implemented by the driver bnd externbl stbtement cbches
         * implemented by bpplicbtion servers bnd other bpplicbtions.
         * <p>
         * By defbult, b <code>Stbtement</code> is not poolbble when crebted, bnd
         * b <code>PrepbredStbtement</code> bnd <code>CbllbbleStbtement</code>
         * bre poolbble when crebted.
         *
         * @pbrbm poolbble              requests thbt the stbtement be pooled if true bnd
         *                                              thbt the stbtement not be pooled if fblse
         *
         * @throws SQLException if this method is cblled on b closed
         * <code>Stbtement</code>
         *
         * @since 1.6
         */
        void setPoolbble(boolebn poolbble)
                throws SQLException;

        /**
         * Returns b  vblue indicbting whether the <code>Stbtement</code>
         * is poolbble or not.
         *
         * @return              <code>true</code> if the <code>Stbtement</code>
         * is poolbble; <code>fblse</code> otherwise
         *
         * @throws SQLException if this method is cblled on b closed
         * <code>Stbtement</code>
         *
         * @since 1.6
         *
         * @see jbvb.sql.Stbtement#setPoolbble(boolebn) setPoolbble(boolebn)
         */
        boolebn isPoolbble()
                throws SQLException;

    //--------------------------JDBC 4.1 -----------------------------

    /**
     * Specifies thbt this {@code Stbtement} will be closed when bll its
     * dependent result sets bre closed. If execution of the {@code Stbtement}
     * does not produce bny result sets, this method hbs no effect.
     * <p>
     * <strong>Note:</strong> Multiple cblls to {@code closeOnCompletion} do
     * not toggle the effect on this {@code Stbtement}. However, b cbll to
     * {@code closeOnCompletion} does effect both the subsequent execution of
     * stbtements, bnd stbtements thbt currently hbve open, dependent,
     * result sets.
     *
     * @throws SQLException if this method is cblled on b closed
     * {@code Stbtement}
     * @since 1.7
     */
    public void closeOnCompletion() throws SQLException;

    /**
     * Returns b vblue indicbting whether this {@code Stbtement} will be
     * closed when bll its dependent result sets bre closed.
     * @return {@code true} if the {@code Stbtement} will be closed when bll
     * of its dependent result sets bre closed; {@code fblse} otherwise
     * @throws SQLException if this method is cblled on b closed
     * {@code Stbtement}
     * @since 1.7
     */
    public boolebn isCloseOnCompletion() throws SQLException;


    //--------------------------JDBC 4.2 -----------------------------

    /**
     *  Retrieves the current result bs bn updbte count; if the result
     * is b <code>ResultSet</code> object or there bre no more results, -1
     *  is returned. This method should be cblled only once per result.
     * <p>
     * This method should be used when the returned row count mby exceed
     * {@link Integer#MAX_VALUE}.
     *<p>
     * The defbult implementbtion will throw {@code UnsupportedOperbtionException}
     *
     * @return the current result bs bn updbte count; -1 if the current result
     * is b <code>ResultSet</code> object or there bre no more results
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @see #execute
     * @since 1.8
     */
    defbult long getLbrgeUpdbteCount() throws SQLException {
        throw new UnsupportedOperbtionException("getLbrgeUpdbteCount not implemented");
    }

    /**
     * Sets the limit for the mbximum number of rows thbt bny
     * <code>ResultSet</code> object  generbted by this <code>Stbtement</code>
     * object cbn contbin to the given number.
     * If the limit is exceeded, the excess
     * rows bre silently dropped.
     * <p>
     * This method should be used when the row limit mby exceed
     * {@link Integer#MAX_VALUE}.
     *<p>
     * The defbult implementbtion will throw {@code UnsupportedOperbtionException}
     *
     * @pbrbm mbx the new mbx rows limit; zero mebns there is no limit
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>
     *            or the condition {@code mbx >= 0} is not sbtisfied
     * @see #getMbxRows
     * @since 1.8
     */
    defbult void setLbrgeMbxRows(long mbx) throws SQLException {
        throw new UnsupportedOperbtionException("setLbrgeMbxRows not implemented");
    }

    /**
     * Retrieves the mbximum number of rows thbt b
     * <code>ResultSet</code> object produced by this
     * <code>Stbtement</code> object cbn contbin.  If this limit is exceeded,
     * the excess rows bre silently dropped.
     * <p>
     * This method should be used when the returned row limit mby exceed
     * {@link Integer#MAX_VALUE}.
     *<p>
     * The defbult implementbtion will return {@code 0}
     *
     * @return the current mbximum number of rows for b <code>ResultSet</code>
     *         object produced by this <code>Stbtement</code> object;
     *         zero mebns there is no limit
     * @exception SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Stbtement</code>
     * @see #setMbxRows
     * @since 1.8
     */
    defbult long getLbrgeMbxRows() throws SQLException {
        return 0;
    }

    /**
     * Submits b bbtch of commbnds to the dbtbbbse for execution bnd
     * if bll commbnds execute successfully, returns bn brrby of updbte counts.
     * The <code>long</code> elements of the brrby thbt is returned bre ordered
     * to correspond to the commbnds in the bbtch, which bre ordered
     * bccording to the order in which they were bdded to the bbtch.
     * The elements in the brrby returned by the method {@code executeLbrgeBbtch}
     * mby be one of the following:
     * <OL>
     * <LI>A number grebter thbn or equbl to zero -- indicbtes thbt the
     * commbnd wbs processed successfully bnd is bn updbte count giving the
     * number of rows in the dbtbbbse thbt were bffected by the commbnd's
     * execution
     * <LI>A vblue of <code>SUCCESS_NO_INFO</code> -- indicbtes thbt the commbnd wbs
     * processed successfully but thbt the number of rows bffected is
     * unknown
     * <P>
     * If one of the commbnds in b bbtch updbte fbils to execute properly,
     * this method throws b <code>BbtchUpdbteException</code>, bnd b JDBC
     * driver mby or mby not continue to process the rembining commbnds in
     * the bbtch.  However, the driver's behbvior must be consistent with b
     * pbrticulbr DBMS, either blwbys continuing to process commbnds or never
     * continuing to process commbnds.  If the driver continues processing
     * bfter b fbilure, the brrby returned by the method
     * <code>BbtchUpdbteException.getLbrgeUpdbteCounts</code>
     * will contbin bs mbny elements bs there bre commbnds in the bbtch, bnd
     * bt lebst one of the elements will be the following:
     *
     * <LI>A vblue of <code>EXECUTE_FAILED</code> -- indicbtes thbt the commbnd fbiled
     * to execute successfully bnd occurs only if b driver continues to
     * process commbnds bfter b commbnd fbils
     * </OL>
     * <p>
     * This method should be used when the returned row count mby exceed
     * {@link Integer#MAX_VALUE}.
     *<p>
     * The defbult implementbtion will throw {@code UnsupportedOperbtionException}
     *
     * @return bn brrby of updbte counts contbining one element for ebch
     * commbnd in the bbtch.  The elements of the brrby bre ordered bccording
     * to the order in which commbnds were bdded to the bbtch.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code> or the
     * driver does not support bbtch stbtements. Throws {@link BbtchUpdbteException}
     * (b subclbss of <code>SQLException</code>) if one of the commbnds sent to the
     * dbtbbbse fbils to execute properly or bttempts to return b result set.
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     *
     * @see #bddBbtch
     * @see DbtbbbseMetbDbtb#supportsBbtchUpdbtes
     * @since 1.8
     */
    defbult long[] executeLbrgeBbtch() throws SQLException {
        throw new UnsupportedOperbtionException("executeLbrgeBbtch not implemented");
    }

    /**
     * Executes the given SQL stbtement, which mby be bn <code>INSERT</code>,
     * <code>UPDATE</code>, or <code>DELETE</code> stbtement or bn
     * SQL stbtement thbt returns nothing, such bs bn SQL DDL stbtement.
     * <p>
     * This method should be used when the returned row count mby exceed
     * {@link Integer#MAX_VALUE}.
     * <p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     *<p>
     * The defbult implementbtion will throw {@code UnsupportedOperbtionException}
     *
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement,
     * such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     *
     * @return either (1) the row count for SQL Dbtb Mbnipulbtion Lbngubge
     * (DML) stbtements or (2) 0 for SQL stbtements thbt return nothing
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>, the given
     * SQL stbtement produces b <code>ResultSet</code> object, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @since 1.8
     */
    defbult long executeLbrgeUpdbte(String sql) throws SQLException {
        throw new UnsupportedOperbtionException("executeLbrgeUpdbte not implemented");
    }

    /**
     * Executes the given SQL stbtement bnd signbls the driver with the
     * given flbg bbout whether the
     * buto-generbted keys produced by this <code>Stbtement</code> object
     * should be mbde bvbilbble for retrievbl.  The driver will ignore the
     * flbg if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     * <p>
     * This method should be used when the returned row count mby exceed
     * {@link Integer#MAX_VALUE}.
     * <p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     *<p>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement,
     * such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     *
     * @pbrbm butoGenerbtedKeys b flbg indicbting whether buto-generbted keys
     *        should be mbde bvbilbble for retrievbl;
     *         one of the following constbnts:
     *         <code>Stbtement.RETURN_GENERATED_KEYS</code>
     *         <code>Stbtement.NO_GENERATED_KEYS</code>
     * @return either (1) the row count for SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtements
     *         or (2) 0 for SQL stbtements thbt return nothing
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     *  this method is cblled on b closed <code>Stbtement</code>, the given
     *            SQL stbtement returns b <code>ResultSet</code> object,
     *            the given constbnt is not one of those bllowed, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method with b constbnt of Stbtement.RETURN_GENERATED_KEYS
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @since 1.8
     */
    defbult long executeLbrgeUpdbte(String sql, int butoGenerbtedKeys)
            throws SQLException {
        throw new SQLFebtureNotSupportedException("executeLbrgeUpdbte not implemented");
    }

    /**
     * Executes the given SQL stbtement bnd signbls the driver thbt the
     * buto-generbted keys indicbted in the given brrby should be mbde bvbilbble
     * for retrievbl.   This brrby contbins the indexes of the columns in the
     * tbrget tbble thbt contbin the buto-generbted keys thbt should be mbde
     * bvbilbble. The driver will ignore the brrby if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     * <p>
     * This method should be used when the returned row count mby exceed
     * {@link Integer#MAX_VALUE}.
     * <p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     *<p>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement,
     * such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     *
     * @pbrbm columnIndexes bn brrby of column indexes indicbting the columns
     *        thbt should be returned from the inserted row
     * @return either (1) the row count for SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtements
     *         or (2) 0 for SQL stbtements thbt return nothing
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled on b closed <code>Stbtement</code>, the SQL
     * stbtement returns b <code>ResultSet</code> object,the second brgument
     * supplied to this method is not bn
     * <code>int</code> brrby whose elements bre vblid column indexes, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @since 1.8
     */
    defbult long executeLbrgeUpdbte(String sql, int columnIndexes[]) throws SQLException {
        throw new SQLFebtureNotSupportedException("executeLbrgeUpdbte not implemented");
    }

    /**
     * Executes the given SQL stbtement bnd signbls the driver thbt the
     * buto-generbted keys indicbted in the given brrby should be mbde bvbilbble
     * for retrievbl.   This brrby contbins the nbmes of the columns in the
     * tbrget tbble thbt contbin the buto-generbted keys thbt should be mbde
     * bvbilbble. The driver will ignore the brrby if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     * <p>
     * This method should be used when the returned row count mby exceed
     * {@link Integer#MAX_VALUE}.
     * <p>
     * <strong>Note:</strong>This method cbnnot be cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>.
     *<p>
     * The defbult implementbtion will throw {@code SQLFebtureNotSupportedException}
     *
     * @pbrbm sql bn SQL Dbtb Mbnipulbtion Lbngubge (DML) stbtement,
     * such bs <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or bn SQL stbtement thbt returns nothing,
     * such bs b DDL stbtement.
     * @pbrbm columnNbmes bn brrby of the nbmes of the columns thbt should be
     *        returned from the inserted row
     * @return either the row count for <code>INSERT</code>, <code>UPDATE</code>,
     *         or <code>DELETE</code> stbtements, or 0 for SQL stbtements
     *         thbt return nothing
     * @exception SQLException if b dbtbbbse bccess error occurs,
     *  this method is cblled on b closed <code>Stbtement</code>, the SQL
     *            stbtement returns b <code>ResultSet</code> object, the
     *            second brgument supplied to this method is not b <code>String</code> brrby
     *            whose elements bre vblid column nbmes, the method is cblled on b
     * <code>PrepbredStbtement</code> or <code>CbllbbleStbtement</code>
     * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @throws SQLTimeoutException when the driver hbs determined thbt the
     * timeout vblue thbt wbs specified by the {@code setQueryTimeout}
     * method hbs been exceeded bnd hbs bt lebst bttempted to cbncel
     * the currently running {@code Stbtement}
     * @since 1.8
     */
    defbult long executeLbrgeUpdbte(String sql, String columnNbmes[])
            throws SQLException {
        throw new SQLFebtureNotSupportedException("executeLbrgeUpdbte not implemented");
    }
}
