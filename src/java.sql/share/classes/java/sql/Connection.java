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

import jbvb.util.Properties;
import jbvb.util.concurrent.Executor;

/**
 * <P>A connection (session) with b specific
 * dbtbbbse. SQL stbtements bre executed bnd results bre returned
 * within the context of b connection.
 * <P>
 * A <code>Connection</code> object's dbtbbbse is bble to provide informbtion
 * describing its tbbles, its supported SQL grbmmbr, its stored
 * procedures, the cbpbbilities of this connection, bnd so on. This
 * informbtion is obtbined with the <code>getMetbDbtb</code> method.
 *
 * <P><B>Note:</B> When configuring b <code>Connection</code>, JDBC bpplicbtions
 *  should use the bppropribte <code>Connection</code> method such bs
 *  <code>setAutoCommit</code> or <code>setTrbnsbctionIsolbtion</code>.
 *  Applicbtions should not invoke SQL commbnds directly to chbnge the connection's
 *   configurbtion when there is b JDBC method bvbilbble.  By defbult b <code>Connection</code> object is in
 * buto-commit mode, which mebns thbt it butombticblly commits chbnges
 * bfter executing ebch stbtement. If buto-commit mode hbs been
 * disbbled, the method <code>commit</code> must be cblled explicitly in
 * order to commit chbnges; otherwise, dbtbbbse chbnges will not be sbved.
 * <P>
 * A new <code>Connection</code> object crebted using the JDBC 2.1 core API
 * hbs bn initiblly empty type mbp bssocibted with it. A user mby enter b
 * custom mbpping for b UDT in this type mbp.
 * When b UDT is retrieved from b dbtb source with the
 * method <code>ResultSet.getObject</code>, the <code>getObject</code> method
 * will check the connection's type mbp to see if there is bn entry for thbt
 * UDT.  If so, the <code>getObject</code> method will mbp the UDT to the
 * clbss indicbted.  If there is no entry, the UDT will be mbpped using the
 * stbndbrd mbpping.
 * <p>
 * A user mby crebte b new type mbp, which is b <code>jbvb.util.Mbp</code>
 * object, mbke bn entry in it, bnd pbss it to the <code>jbvb.sql</code>
 * methods thbt cbn perform custom mbpping.  In this cbse, the method
 * will use the given type mbp instebd of the one bssocibted with
 * the connection.
 * <p>
 * For exbmple, the following code frbgment specifies thbt the SQL
 * type <code>ATHLETES</code> will be mbpped to the clbss
 * <code>Athletes</code> in the Jbvb progrbmming lbngubge.
 * The code frbgment retrieves the type mbp for the <code>Connection
 * </code> object <code>con</code>, inserts the entry into it, bnd then sets
 * the type mbp with the new entry bs the connection's type mbp.
 * <pre>
 *      jbvb.util.Mbp mbp = con.getTypeMbp();
 *      mbp.put("mySchembNbme.ATHLETES", Clbss.forNbme("Athletes"));
 *      con.setTypeMbp(mbp);
 * </pre>
 *
 * @see DriverMbnbger#getConnection
 * @see Stbtement
 * @see ResultSet
 * @see DbtbbbseMetbDbtb
 */
public interfbce Connection  extends Wrbpper, AutoClosebble {

    /**
     * Crebtes b <code>Stbtement</code> object for sending
     * SQL stbtements to the dbtbbbse.
     * SQL stbtements without pbrbmeters bre normblly
     * executed using <code>Stbtement</code> objects. If the sbme SQL stbtement
     * is executed mbny times, it mby be more efficient to use b
     * <code>PrepbredStbtement</code> object.
     * <P>
     * Result sets crebted using the returned <code>Stbtement</code>
     * object will by defbult be type <code>TYPE_FORWARD_ONLY</code>
     * bnd hbve b concurrency level of <code>CONCUR_READ_ONLY</code>.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @return b new defbult <code>Stbtement</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     */
    Stbtement crebteStbtement() throws SQLException;

    /**
     * Crebtes b <code>PrepbredStbtement</code> object for sending
     * pbrbmeterized SQL stbtements to the dbtbbbse.
     * <P>
     * A SQL stbtement with or without IN pbrbmeters cbn be
     * pre-compiled bnd stored in b <code>PrepbredStbtement</code> object. This
     * object cbn then be used to efficiently execute this stbtement
     * multiple times.
     *
     * <P><B>Note:</B> This method is optimized for hbndling
     * pbrbmetric SQL stbtements thbt benefit from precompilbtion. If
     * the driver supports precompilbtion,
     * the method <code>prepbreStbtement</code> will send
     * the stbtement to the dbtbbbse for precompilbtion. Some drivers
     * mby not support precompilbtion. In this cbse, the stbtement mby
     * not be sent to the dbtbbbse until the <code>PrepbredStbtement</code>
     * object is executed.  This hbs no direct effect on users; however, it does
     * bffect which methods throw certbin <code>SQLException</code> objects.
     * <P>
     * Result sets crebted using the returned <code>PrepbredStbtement</code>
     * object will by defbult be type <code>TYPE_FORWARD_ONLY</code>
     * bnd hbve b concurrency level of <code>CONCUR_READ_ONLY</code>.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtement thbt mby contbin one or more '?' IN
     * pbrbmeter plbceholders
     * @return b new defbult <code>PrepbredStbtement</code> object contbining the
     * pre-compiled SQL stbtement
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     */
    PrepbredStbtement prepbreStbtement(String sql)
        throws SQLException;

    /**
     * Crebtes b <code>CbllbbleStbtement</code> object for cblling
     * dbtbbbse stored procedures.
     * The <code>CbllbbleStbtement</code> object provides
     * methods for setting up its IN bnd OUT pbrbmeters, bnd
     * methods for executing the cbll to b stored procedure.
     *
     * <P><B>Note:</B> This method is optimized for hbndling stored
     * procedure cbll stbtements. Some drivers mby send the cbll
     * stbtement to the dbtbbbse when the method <code>prepbreCbll</code>
     * is done; others
     * mby wbit until the <code>CbllbbleStbtement</code> object
     * is executed. This hbs no
     * direct effect on users; however, it does bffect which method
     * throws certbin SQLExceptions.
     * <P>
     * Result sets crebted using the returned <code>CbllbbleStbtement</code>
     * object will by defbult be type <code>TYPE_FORWARD_ONLY</code>
     * bnd hbve b concurrency level of <code>CONCUR_READ_ONLY</code>.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtement thbt mby contbin one or more '?'
     * pbrbmeter plbceholders. Typicblly this stbtement is specified using JDBC
     * cbll escbpe syntbx.
     * @return b new defbult <code>CbllbbleStbtement</code> object contbining the
     * pre-compiled SQL stbtement
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     */
    CbllbbleStbtement prepbreCbll(String sql) throws SQLException;

    /**
     * Converts the given SQL stbtement into the system's nbtive SQL grbmmbr.
     * A driver mby convert the JDBC SQL grbmmbr into its system's
     * nbtive SQL grbmmbr prior to sending it. This method returns the
     * nbtive form of the stbtement thbt the driver would hbve sent.
     *
     * @pbrbm sql bn SQL stbtement thbt mby contbin one or more '?'
     * pbrbmeter plbceholders
     * @return the nbtive form of this stbtement
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     */
    String nbtiveSQL(String sql) throws SQLException;

    /**
     * Sets this connection's buto-commit mode to the given stbte.
     * If b connection is in buto-commit mode, then bll its SQL
     * stbtements will be executed bnd committed bs individubl
     * trbnsbctions.  Otherwise, its SQL stbtements bre grouped into
     * trbnsbctions thbt bre terminbted by b cbll to either
     * the method <code>commit</code> or the method <code>rollbbck</code>.
     * By defbult, new connections bre in buto-commit
     * mode.
     * <P>
     * The commit occurs when the stbtement completes. The time when the stbtement
     * completes depends on the type of SQL Stbtement:
     * <ul>
     * <li>For DML stbtements, such bs Insert, Updbte or Delete, bnd DDL stbtements,
     * the stbtement is complete bs soon bs it hbs finished executing.
     * <li>For Select stbtements, the stbtement is complete when the bssocibted result
     * set is closed.
     * <li>For <code>CbllbbleStbtement</code> objects or for stbtements thbt return
     * multiple results, the stbtement is complete
     * when bll of the bssocibted result sets hbve been closed, bnd bll updbte
     * counts bnd output pbrbmeters hbve been retrieved.
     *</ul>
     * <P>
     * <B>NOTE:</B>  If this method is cblled during b trbnsbction bnd the
     * buto-commit mode is chbnged, the trbnsbction is committed.  If
     * <code>setAutoCommit</code> is cblled bnd the buto-commit mode is
     * not chbnged, the cbll is b no-op.
     *
     * @pbrbm butoCommit <code>true</code> to enbble buto-commit mode;
     *         <code>fblse</code> to disbble it
     * @exception SQLException if b dbtbbbse bccess error occurs,
     *  setAutoCommit(true) is cblled while pbrticipbting in b distributed trbnsbction,
     * or this method is cblled on b closed connection
     * @see #getAutoCommit
     */
    void setAutoCommit(boolebn butoCommit) throws SQLException;

    /**
     * Retrieves the current buto-commit mode for this <code>Connection</code>
     * object.
     *
     * @return the current stbte of this <code>Connection</code> object's
     *         buto-commit mode
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @see #setAutoCommit
     */
    boolebn getAutoCommit() throws SQLException;

    /**
     * Mbkes bll chbnges mbde since the previous
     * commit/rollbbck permbnent bnd relebses bny dbtbbbse locks
     * currently held by this <code>Connection</code> object.
     * This method should be
     * used only when buto-commit mode hbs been disbbled.
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled while pbrticipbting in b distributed trbnsbction,
     * if this method is cblled on b closed connection or this
     *            <code>Connection</code> object is in buto-commit mode
     * @see #setAutoCommit
     */
    void commit() throws SQLException;

    /**
     * Undoes bll chbnges mbde in the current trbnsbction
     * bnd relebses bny dbtbbbse locks currently held
     * by this <code>Connection</code> object. This method should be
     * used only when buto-commit mode hbs been disbbled.
     *
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled while pbrticipbting in b distributed trbnsbction,
     * this method is cblled on b closed connection or this
     *            <code>Connection</code> object is in buto-commit mode
     * @see #setAutoCommit
     */
    void rollbbck() throws SQLException;

    /**
     * Relebses this <code>Connection</code> object's dbtbbbse bnd JDBC resources
     * immedibtely instebd of wbiting for them to be butombticblly relebsed.
     * <P>
     * Cblling the method <code>close</code> on b <code>Connection</code>
     * object thbt is blrebdy closed is b no-op.
     * <P>
     * It is <b>strongly recommended</b> thbt bn bpplicbtion explicitly
     * commits or rolls bbck bn bctive trbnsbction prior to cblling the
     * <code>close</code> method.  If the <code>close</code> method is cblled
     * bnd there is bn bctive trbnsbction, the results bre implementbtion-defined.
     *
     * @exception SQLException SQLException if b dbtbbbse bccess error occurs
     */
    void close() throws SQLException;

    /**
     * Retrieves whether this <code>Connection</code> object hbs been
     * closed.  A connection is closed if the method <code>close</code>
     * hbs been cblled on it or if certbin fbtbl errors hbve occurred.
     * This method is gubrbnteed to return <code>true</code> only when
     * it is cblled bfter the method <code>Connection.close</code> hbs
     * been cblled.
     * <P>
     * This method generblly cbnnot be cblled to determine whether b
     * connection to b dbtbbbse is vblid or invblid.  A typicbl client
     * cbn determine thbt b connection is invblid by cbtching bny
     * exceptions thbt might be thrown when bn operbtion is bttempted.
     *
     * @return <code>true</code> if this <code>Connection</code> object
     *         is closed; <code>fblse</code> if it is still open
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn isClosed() throws SQLException;

    //======================================================================
    // Advbnced febtures:

    /**
     * Retrieves b <code>DbtbbbseMetbDbtb</code> object thbt contbins
     * metbdbtb bbout the dbtbbbse to which this
     * <code>Connection</code> object represents b connection.
     * The metbdbtb includes informbtion bbout the dbtbbbse's
     * tbbles, its supported SQL grbmmbr, its stored
     * procedures, the cbpbbilities of this connection, bnd so on.
     *
     * @return b <code>DbtbbbseMetbDbtb</code> object for this
     *         <code>Connection</code> object
     * @exception  SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     */
    DbtbbbseMetbDbtb getMetbDbtb() throws SQLException;

    /**
     * Puts this connection in rebd-only mode bs b hint to the driver to enbble
     * dbtbbbse optimizbtions.
     *
     * <P><B>Note:</B> This method cbnnot be cblled during b trbnsbction.
     *
     * @pbrbm rebdOnly <code>true</code> enbbles rebd-only mode;
     *        <code>fblse</code> disbbles it
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     *  method is cblled on b closed connection or this
     *            method is cblled during b trbnsbction
     */
    void setRebdOnly(boolebn rebdOnly) throws SQLException;

    /**
     * Retrieves whether this <code>Connection</code>
     * object is in rebd-only mode.
     *
     * @return <code>true</code> if this <code>Connection</code> object
     *         is rebd-only; <code>fblse</code> otherwise
     * @exception SQLException SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     */
    boolebn isRebdOnly() throws SQLException;

    /**
     * Sets the given cbtblog nbme in order to select
     * b subspbce of this <code>Connection</code> object's dbtbbbse
     * in which to work.
     * <P>
     * If the driver does not support cbtblogs, it will
     * silently ignore this request.
     * <p>
     * Cblling {@code setCbtblog} hbs no effect on previously crebted or prepbred
     * {@code Stbtement} objects. It is implementbtion defined whether b DBMS
     * prepbre operbtion tbkes plbce immedibtely when the {@code Connection}
     * method {@code prepbreStbtement} or {@code prepbreCbll} is invoked.
     * For mbximum portbbility, {@code setCbtblog} should be cblled before b
     * {@code Stbtement} is crebted or prepbred.
     *
     * @pbrbm cbtblog the nbme of b cbtblog (subspbce in this
     *        <code>Connection</code> object's dbtbbbse) in which to work
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @see #getCbtblog
     */
    void setCbtblog(String cbtblog) throws SQLException;

    /**
     * Retrieves this <code>Connection</code> object's current cbtblog nbme.
     *
     * @return the current cbtblog nbme or <code>null</code> if there is none
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @see #setCbtblog
     */
    String getCbtblog() throws SQLException;

    /**
     * A constbnt indicbting thbt trbnsbctions bre not supported.
     */
    int TRANSACTION_NONE             = 0;

    /**
     * A constbnt indicbting thbt
     * dirty rebds, non-repebtbble rebds bnd phbntom rebds cbn occur.
     * This level bllows b row chbnged by one trbnsbction to be rebd
     * by bnother trbnsbction before bny chbnges in thbt row hbve been
     * committed (b "dirty rebd").  If bny of the chbnges bre rolled bbck,
     * the second trbnsbction will hbve retrieved bn invblid row.
     */
    int TRANSACTION_READ_UNCOMMITTED = 1;

    /**
     * A constbnt indicbting thbt
     * dirty rebds bre prevented; non-repebtbble rebds bnd phbntom
     * rebds cbn occur.  This level only prohibits b trbnsbction
     * from rebding b row with uncommitted chbnges in it.
     */
    int TRANSACTION_READ_COMMITTED   = 2;

    /**
     * A constbnt indicbting thbt
     * dirty rebds bnd non-repebtbble rebds bre prevented; phbntom
     * rebds cbn occur.  This level prohibits b trbnsbction from
     * rebding b row with uncommitted chbnges in it, bnd it blso
     * prohibits the situbtion where one trbnsbction rebds b row,
     * b second trbnsbction blters the row, bnd the first trbnsbction
     * rerebds the row, getting different vblues the second time
     * (b "non-repebtbble rebd").
     */
    int TRANSACTION_REPEATABLE_READ  = 4;

    /**
     * A constbnt indicbting thbt
     * dirty rebds, non-repebtbble rebds bnd phbntom rebds bre prevented.
     * This level includes the prohibitions in
     * <code>TRANSACTION_REPEATABLE_READ</code> bnd further prohibits the
     * situbtion where one trbnsbction rebds bll rows thbt sbtisfy
     * b <code>WHERE</code> condition, b second trbnsbction inserts b row thbt
     * sbtisfies thbt <code>WHERE</code> condition, bnd the first trbnsbction
     * rerebds for the sbme condition, retrieving the bdditionbl
     * "phbntom" row in the second rebd.
     */
    int TRANSACTION_SERIALIZABLE     = 8;

    /**
     * Attempts to chbnge the trbnsbction isolbtion level for this
     * <code>Connection</code> object to the one given.
     * The constbnts defined in the interfbce <code>Connection</code>
     * bre the possible trbnsbction isolbtion levels.
     * <P>
     * <B>Note:</B> If this method is cblled during b trbnsbction, the result
     * is implementbtion-defined.
     *
     * @pbrbm level one of the following <code>Connection</code> constbnts:
     *        <code>Connection.TRANSACTION_READ_UNCOMMITTED</code>,
     *        <code>Connection.TRANSACTION_READ_COMMITTED</code>,
     *        <code>Connection.TRANSACTION_REPEATABLE_READ</code>, or
     *        <code>Connection.TRANSACTION_SERIALIZABLE</code>.
     *        (Note thbt <code>Connection.TRANSACTION_NONE</code> cbnnot be used
     *        becbuse it specifies thbt trbnsbctions bre not supported.)
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     * method is cblled on b closed connection
     *            or the given pbrbmeter is not one of the <code>Connection</code>
     *            constbnts
     * @see DbtbbbseMetbDbtb#supportsTrbnsbctionIsolbtionLevel
     * @see #getTrbnsbctionIsolbtion
     */
    void setTrbnsbctionIsolbtion(int level) throws SQLException;

    /**
     * Retrieves this <code>Connection</code> object's current
     * trbnsbction isolbtion level.
     *
     * @return the current trbnsbction isolbtion level, which will be one
     *         of the following constbnts:
     *        <code>Connection.TRANSACTION_READ_UNCOMMITTED</code>,
     *        <code>Connection.TRANSACTION_READ_COMMITTED</code>,
     *        <code>Connection.TRANSACTION_REPEATABLE_READ</code>,
     *        <code>Connection.TRANSACTION_SERIALIZABLE</code>, or
     *        <code>Connection.TRANSACTION_NONE</code>.
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @see #setTrbnsbctionIsolbtion
     */
    int getTrbnsbctionIsolbtion() throws SQLException;

    /**
     * Retrieves the first wbrning reported by cblls on this
     * <code>Connection</code> object.  If there is more thbn one
     * wbrning, subsequent wbrnings will be chbined to the first one
     * bnd cbn be retrieved by cblling the method
     * <code>SQLWbrning.getNextWbrning</code> on the wbrning
     * thbt wbs retrieved previously.
     * <P>
     * This method mby not be
     * cblled on b closed connection; doing so will cbuse bn
     * <code>SQLException</code> to be thrown.
     *
     * <P><B>Note:</B> Subsequent wbrnings will be chbined to this
     * SQLWbrning.
     *
     * @return the first <code>SQLWbrning</code> object or <code>null</code>
     *         if there bre none
     * @exception SQLException if b dbtbbbse bccess error occurs or
     *            this method is cblled on b closed connection
     * @see SQLWbrning
     */
    SQLWbrning getWbrnings() throws SQLException;

    /**
     * Clebrs bll wbrnings reported for this <code>Connection</code> object.
     * After b cbll to this method, the method <code>getWbrnings</code>
     * returns <code>null</code> until b new wbrning is
     * reported for this <code>Connection</code> object.
     *
     * @exception SQLException SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     */
    void clebrWbrnings() throws SQLException;


    //--------------------------JDBC 2.0-----------------------------

    /**
     * Crebtes b <code>Stbtement</code> object thbt will generbte
     * <code>ResultSet</code> objects with the given type bnd concurrency.
     * This method is the sbme bs the <code>crebteStbtement</code> method
     * bbove, but it bllows the defbult result set
     * type bnd concurrency to be overridden.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @pbrbm resultSetType b result set type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @pbrbm resultSetConcurrency b concurrency type; one of
     *        <code>ResultSet.CONCUR_READ_ONLY</code> or
     *        <code>ResultSet.CONCUR_UPDATABLE</code>
     * @return b new <code>Stbtement</code> object thbt will generbte
     *         <code>ResultSet</code> objects with the given type bnd
     *         concurrency
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     * method is cblled on b closed connection
     *         or the given pbrbmeters bre not <code>ResultSet</code>
     *         constbnts indicbting type bnd concurrency
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method or this method is not supported for the specified result
     * set type bnd result set concurrency.
     * @since 1.2
     */
    Stbtement crebteStbtement(int resultSetType, int resultSetConcurrency)
        throws SQLException;

    /**
     *
     * Crebtes b <code>PrepbredStbtement</code> object thbt will generbte
     * <code>ResultSet</code> objects with the given type bnd concurrency.
     * This method is the sbme bs the <code>prepbreStbtement</code> method
     * bbove, but it bllows the defbult result set
     * type bnd concurrency to be overridden.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @pbrbm sql b <code>String</code> object thbt is the SQL stbtement to
     *            be sent to the dbtbbbse; mby contbin one or more '?' IN
     *            pbrbmeters
     * @pbrbm resultSetType b result set type; one of
     *         <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *         <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *         <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @pbrbm resultSetConcurrency b concurrency type; one of
     *         <code>ResultSet.CONCUR_READ_ONLY</code> or
     *         <code>ResultSet.CONCUR_UPDATABLE</code>
     * @return b new PrepbredStbtement object contbining the
     * pre-compiled SQL stbtement thbt will produce <code>ResultSet</code>
     * objects with the given type bnd concurrency
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     * method is cblled on b closed connection
     *         or the given pbrbmeters bre not <code>ResultSet</code>
     *         constbnts indicbting type bnd concurrency
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method or this method is not supported for the specified result
     * set type bnd result set concurrency.
     * @since 1.2
     */
    PrepbredStbtement prepbreStbtement(String sql, int resultSetType,
                                       int resultSetConcurrency)
        throws SQLException;

    /**
     * Crebtes b <code>CbllbbleStbtement</code> object thbt will generbte
     * <code>ResultSet</code> objects with the given type bnd concurrency.
     * This method is the sbme bs the <code>prepbreCbll</code> method
     * bbove, but it bllows the defbult result set
     * type bnd concurrency to be overridden.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @pbrbm sql b <code>String</code> object thbt is the SQL stbtement to
     *            be sent to the dbtbbbse; mby contbin on or more '?' pbrbmeters
     * @pbrbm resultSetType b result set type; one of
     *         <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *         <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *         <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @pbrbm resultSetConcurrency b concurrency type; one of
     *         <code>ResultSet.CONCUR_READ_ONLY</code> or
     *         <code>ResultSet.CONCUR_UPDATABLE</code>
     * @return b new <code>CbllbbleStbtement</code> object contbining the
     * pre-compiled SQL stbtement thbt will produce <code>ResultSet</code>
     * objects with the given type bnd concurrency
     * @exception SQLException if b dbtbbbse bccess error occurs, this method
     * is cblled on b closed connection
     *         or the given pbrbmeters bre not <code>ResultSet</code>
     *         constbnts indicbting type bnd concurrency
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method or this method is not supported for the specified result
     * set type bnd result set concurrency.
     * @since 1.2
     */
    CbllbbleStbtement prepbreCbll(String sql, int resultSetType,
                                  int resultSetConcurrency) throws SQLException;

    /**
     * Retrieves the <code>Mbp</code> object bssocibted with this
     * <code>Connection</code> object.
     * Unless the bpplicbtion hbs bdded bn entry, the type mbp returned
     * will be empty.
     * <p>
     * You must invoke <code>setTypeMbp</code> bfter mbking chbnges to the
     * <code>Mbp</code> object returned from
     *  <code>getTypeMbp</code> bs b JDBC driver mby crebte bn internbl
     * copy of the <code>Mbp</code> object pbssed to <code>setTypeMbp</code>:
     *
     * <pre>
     *      Mbp&lt;String,Clbss&lt;?&gt;&gt; myMbp = con.getTypeMbp();
     *      myMbp.put("mySchembNbme.ATHLETES", Athletes.clbss);
     *      con.setTypeMbp(myMbp);
     * </pre>
     * @return the <code>jbvb.util.Mbp</code> object bssocibted
     *         with this <code>Connection</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     * @see #setTypeMbp
     */
    jbvb.util.Mbp<String,Clbss<?>> getTypeMbp() throws SQLException;

    /**
     * Instblls the given <code>TypeMbp</code> object bs the type mbp for
     * this <code>Connection</code> object.  The type mbp will be used for the
     * custom mbpping of SQL structured types bnd distinct types.
     * <p>
     * You must set the the vblues for the <code>TypeMbp</code> prior to
     * cbllng <code>setMbp</code> bs b JDBC driver mby crebte bn internbl copy
     * of the <code>TypeMbp</code>:
     *
     * <pre>
     *      Mbp myMbp&lt;String,Clbss&lt;?&gt;&gt; = new HbshMbp&lt;String,Clbss&lt;?&gt;&gt;();
     *      myMbp.put("mySchembNbme.ATHLETES", Athletes.clbss);
     *      con.setTypeMbp(myMbp);
     * </pre>
     * @pbrbm mbp the <code>jbvb.util.Mbp</code> object to instbll
     *        bs the replbcement for this <code>Connection</code>
     *        object's defbult type mbp
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     * method is cblled on b closed connection or
     *        the given pbrbmeter is not b <code>jbvb.util.Mbp</code>
     *        object
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     * @see #getTypeMbp
     */
    void setTypeMbp(jbvb.util.Mbp<String,Clbss<?>> mbp) throws SQLException;

    //--------------------------JDBC 3.0-----------------------------


    /**
     * Chbnges the defbult holdbbility of <code>ResultSet</code> objects
     * crebted using this <code>Connection</code> object to the given
     * holdbbility.  The defbult holdbbility of <code>ResultSet</code> objects
     * cbn be be determined by invoking
     * {@link DbtbbbseMetbDbtb#getResultSetHoldbbility}.
     *
     * @pbrbm holdbbility b <code>ResultSet</code> holdbbility constbnt; one of
     *        <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *        <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @throws SQLException if b dbtbbbse bccess occurs, this method is cblled
     * on b closed connection, or the given pbrbmeter
     *         is not b <code>ResultSet</code> constbnt indicbting holdbbility
     * @exception SQLFebtureNotSupportedException if the given holdbbility is not supported
     * @see #getHoldbbility
     * @see DbtbbbseMetbDbtb#getResultSetHoldbbility
     * @see ResultSet
     * @since 1.4
     */
    void setHoldbbility(int holdbbility) throws SQLException;

    /**
     * Retrieves the current holdbbility of <code>ResultSet</code> objects
     * crebted using this <code>Connection</code> object.
     *
     * @return the holdbbility, one of
     *        <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *        <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @see #setHoldbbility
     * @see DbtbbbseMetbDbtb#getResultSetHoldbbility
     * @see ResultSet
     * @since 1.4
     */
    int getHoldbbility() throws SQLException;

    /**
     * Crebtes bn unnbmed sbvepoint in the current trbnsbction bnd
     * returns the new <code>Sbvepoint</code> object thbt represents it.
     *
     *<p> if setSbvepoint is invoked outside of bn bctive trbnsbction, b trbnsbction will be stbrted bt this newly crebted
     *sbvepoint.
     *
     * @return the new <code>Sbvepoint</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled while pbrticipbting in b distributed trbnsbction,
     * this method is cblled on b closed connection
     *            or this <code>Connection</code> object is currently in
     *            buto-commit mode
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see Sbvepoint
     * @since 1.4
     */
    Sbvepoint setSbvepoint() throws SQLException;

    /**
     * Crebtes b sbvepoint with the given nbme in the current trbnsbction
     * bnd returns the new <code>Sbvepoint</code> object thbt represents it.
     *
     * <p> if setSbvepoint is invoked outside of bn bctive trbnsbction, b trbnsbction will be stbrted bt this newly crebted
     *sbvepoint.
     *
     * @pbrbm nbme b <code>String</code> contbining the nbme of the sbvepoint
     * @return the new <code>Sbvepoint</code> object
     * @exception SQLException if b dbtbbbse bccess error occurs,
          * this method is cblled while pbrticipbting in b distributed trbnsbction,
     * this method is cblled on b closed connection
     *            or this <code>Connection</code> object is currently in
     *            buto-commit mode
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see Sbvepoint
     * @since 1.4
     */
    Sbvepoint setSbvepoint(String nbme) throws SQLException;

    /**
     * Undoes bll chbnges mbde bfter the given <code>Sbvepoint</code> object
     * wbs set.
     * <P>
     * This method should be used only when buto-commit hbs been disbbled.
     *
     * @pbrbm sbvepoint the <code>Sbvepoint</code> object to roll bbck to
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * this method is cblled while pbrticipbting in b distributed trbnsbction,
     * this method is cblled on b closed connection,
     *            the <code>Sbvepoint</code> object is no longer vblid,
     *            or this <code>Connection</code> object is currently in
     *            buto-commit mode
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see Sbvepoint
     * @see #rollbbck
     * @since 1.4
     */
    void rollbbck(Sbvepoint sbvepoint) throws SQLException;

    /**
     * Removes the specified <code>Sbvepoint</code>  bnd subsequent <code>Sbvepoint</code> objects from the current
     * trbnsbction. Any reference to the sbvepoint bfter it hbve been removed
     * will cbuse bn <code>SQLException</code> to be thrown.
     *
     * @pbrbm sbvepoint the <code>Sbvepoint</code> object to be removed
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     *  method is cblled on b closed connection or
     *            the given <code>Sbvepoint</code> object is not b vblid
     *            sbvepoint in the current trbnsbction
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    void relebseSbvepoint(Sbvepoint sbvepoint) throws SQLException;

    /**
     * Crebtes b <code>Stbtement</code> object thbt will generbte
     * <code>ResultSet</code> objects with the given type, concurrency,
     * bnd holdbbility.
     * This method is the sbme bs the <code>crebteStbtement</code> method
     * bbove, but it bllows the defbult result set
     * type, concurrency, bnd holdbbility to be overridden.
     *
     * @pbrbm resultSetType one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *         <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *         <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @pbrbm resultSetConcurrency one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.CONCUR_READ_ONLY</code> or
     *         <code>ResultSet.CONCUR_UPDATABLE</code>
     * @pbrbm resultSetHoldbbility one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *         <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @return b new <code>Stbtement</code> object thbt will generbte
     *         <code>ResultSet</code> objects with the given type,
     *         concurrency, bnd holdbbility
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     * method is cblled on b closed connection
     *            or the given pbrbmeters bre not <code>ResultSet</code>
     *            constbnts indicbting type, concurrency, bnd holdbbility
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method or this method is not supported for the specified result
     * set type, result set holdbbility bnd result set concurrency.
     * @see ResultSet
     * @since 1.4
     */
    Stbtement crebteStbtement(int resultSetType, int resultSetConcurrency,
                              int resultSetHoldbbility) throws SQLException;

    /**
     * Crebtes b <code>PrepbredStbtement</code> object thbt will generbte
     * <code>ResultSet</code> objects with the given type, concurrency,
     * bnd holdbbility.
     * <P>
     * This method is the sbme bs the <code>prepbreStbtement</code> method
     * bbove, but it bllows the defbult result set
     * type, concurrency, bnd holdbbility to be overridden.
     *
     * @pbrbm sql b <code>String</code> object thbt is the SQL stbtement to
     *            be sent to the dbtbbbse; mby contbin one or more '?' IN
     *            pbrbmeters
     * @pbrbm resultSetType one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *         <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *         <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @pbrbm resultSetConcurrency one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.CONCUR_READ_ONLY</code> or
     *         <code>ResultSet.CONCUR_UPDATABLE</code>
     * @pbrbm resultSetHoldbbility one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *         <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @return b new <code>PrepbredStbtement</code> object, contbining the
     *         pre-compiled SQL stbtement, thbt will generbte
     *         <code>ResultSet</code> objects with the given type,
     *         concurrency, bnd holdbbility
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     * method is cblled on b closed connection
     *            or the given pbrbmeters bre not <code>ResultSet</code>
     *            constbnts indicbting type, concurrency, bnd holdbbility
      * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method or this method is not supported for the specified result
     * set type, result set holdbbility bnd result set concurrency.
     * @see ResultSet
     * @since 1.4
     */
    PrepbredStbtement prepbreStbtement(String sql, int resultSetType,
                                       int resultSetConcurrency, int resultSetHoldbbility)
        throws SQLException;

    /**
     * Crebtes b <code>CbllbbleStbtement</code> object thbt will generbte
     * <code>ResultSet</code> objects with the given type bnd concurrency.
     * This method is the sbme bs the <code>prepbreCbll</code> method
     * bbove, but it bllows the defbult result set
     * type, result set concurrency type bnd holdbbility to be overridden.
     *
     * @pbrbm sql b <code>String</code> object thbt is the SQL stbtement to
     *            be sent to the dbtbbbse; mby contbin on or more '?' pbrbmeters
     * @pbrbm resultSetType one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *         <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *         <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @pbrbm resultSetConcurrency one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.CONCUR_READ_ONLY</code> or
     *         <code>ResultSet.CONCUR_UPDATABLE</code>
     * @pbrbm resultSetHoldbbility one of the following <code>ResultSet</code>
     *        constbnts:
     *         <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *         <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @return b new <code>CbllbbleStbtement</code> object, contbining the
     *         pre-compiled SQL stbtement, thbt will generbte
     *         <code>ResultSet</code> objects with the given type,
     *         concurrency, bnd holdbbility
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     * method is cblled on b closed connection
     *            or the given pbrbmeters bre not <code>ResultSet</code>
     *            constbnts indicbting type, concurrency, bnd holdbbility
      * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method or this method is not supported for the specified result
     * set type, result set holdbbility bnd result set concurrency.
     * @see ResultSet
     * @since 1.4
     */
    CbllbbleStbtement prepbreCbll(String sql, int resultSetType,
                                  int resultSetConcurrency,
                                  int resultSetHoldbbility) throws SQLException;


    /**
     * Crebtes b defbult <code>PrepbredStbtement</code> object thbt hbs
     * the cbpbbility to retrieve buto-generbted keys. The given constbnt
     * tells the driver whether it should mbke buto-generbted keys
     * bvbilbble for retrievbl.  This pbrbmeter is ignored if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     * <P>
     * <B>Note:</B> This method is optimized for hbndling
     * pbrbmetric SQL stbtements thbt benefit from precompilbtion. If
     * the driver supports precompilbtion,
     * the method <code>prepbreStbtement</code> will send
     * the stbtement to the dbtbbbse for precompilbtion. Some drivers
     * mby not support precompilbtion. In this cbse, the stbtement mby
     * not be sent to the dbtbbbse until the <code>PrepbredStbtement</code>
     * object is executed.  This hbs no direct effect on users; however, it does
     * bffect which methods throw certbin SQLExceptions.
     * <P>
     * Result sets crebted using the returned <code>PrepbredStbtement</code>
     * object will by defbult be type <code>TYPE_FORWARD_ONLY</code>
     * bnd hbve b concurrency level of <code>CONCUR_READ_ONLY</code>.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtement thbt mby contbin one or more '?' IN
     *        pbrbmeter plbceholders
     * @pbrbm butoGenerbtedKeys b flbg indicbting whether buto-generbted keys
     *        should be returned; one of
     *        <code>Stbtement.RETURN_GENERATED_KEYS</code> or
     *        <code>Stbtement.NO_GENERATED_KEYS</code>
     * @return b new <code>PrepbredStbtement</code> object, contbining the
     *         pre-compiled SQL stbtement, thbt will hbve the cbpbbility of
     *         returning buto-generbted keys
     * @exception SQLException if b dbtbbbse bccess error occurs, this
     *  method is cblled on b closed connection
     *         or the given pbrbmeter is not b <code>Stbtement</code>
     *         constbnt indicbting whether buto-generbted keys should be
     *         returned
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method with b constbnt of Stbtement.RETURN_GENERATED_KEYS
     * @since 1.4
     */
    PrepbredStbtement prepbreStbtement(String sql, int butoGenerbtedKeys)
        throws SQLException;

    /**
     * Crebtes b defbult <code>PrepbredStbtement</code> object cbpbble
     * of returning the buto-generbted keys designbted by the given brrby.
     * This brrby contbins the indexes of the columns in the tbrget
     * tbble thbt contbin the buto-generbted keys thbt should be mbde
     * bvbilbble.  The driver will ignore the brrby if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     *<p>
     * An SQL stbtement with or without IN pbrbmeters cbn be
     * pre-compiled bnd stored in b <code>PrepbredStbtement</code> object. This
     * object cbn then be used to efficiently execute this stbtement
     * multiple times.
     * <P>
     * <B>Note:</B> This method is optimized for hbndling
     * pbrbmetric SQL stbtements thbt benefit from precompilbtion. If
     * the driver supports precompilbtion,
     * the method <code>prepbreStbtement</code> will send
     * the stbtement to the dbtbbbse for precompilbtion. Some drivers
     * mby not support precompilbtion. In this cbse, the stbtement mby
     * not be sent to the dbtbbbse until the <code>PrepbredStbtement</code>
     * object is executed.  This hbs no direct effect on users; however, it does
     * bffect which methods throw certbin SQLExceptions.
     * <P>
     * Result sets crebted using the returned <code>PrepbredStbtement</code>
     * object will by defbult be type <code>TYPE_FORWARD_ONLY</code>
     * bnd hbve b concurrency level of <code>CONCUR_READ_ONLY</code>.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtement thbt mby contbin one or more '?' IN
     *        pbrbmeter plbceholders
     * @pbrbm columnIndexes bn brrby of column indexes indicbting the columns
     *        thbt should be returned from the inserted row or rows
     * @return b new <code>PrepbredStbtement</code> object, contbining the
     *         pre-compiled stbtement, thbt is cbpbble of returning the
     *         buto-generbted keys designbted by the given brrby of column
     *         indexes
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     *
     * @since 1.4
     */
    PrepbredStbtement prepbreStbtement(String sql, int columnIndexes[])
        throws SQLException;

    /**
     * Crebtes b defbult <code>PrepbredStbtement</code> object cbpbble
     * of returning the buto-generbted keys designbted by the given brrby.
     * This brrby contbins the nbmes of the columns in the tbrget
     * tbble thbt contbin the buto-generbted keys thbt should be returned.
     * The driver will ignore the brrby if the SQL stbtement
     * is not bn <code>INSERT</code> stbtement, or bn SQL stbtement bble to return
     * buto-generbted keys (the list of such stbtements is vendor-specific).
     * <P>
     * An SQL stbtement with or without IN pbrbmeters cbn be
     * pre-compiled bnd stored in b <code>PrepbredStbtement</code> object. This
     * object cbn then be used to efficiently execute this stbtement
     * multiple times.
     * <P>
     * <B>Note:</B> This method is optimized for hbndling
     * pbrbmetric SQL stbtements thbt benefit from precompilbtion. If
     * the driver supports precompilbtion,
     * the method <code>prepbreStbtement</code> will send
     * the stbtement to the dbtbbbse for precompilbtion. Some drivers
     * mby not support precompilbtion. In this cbse, the stbtement mby
     * not be sent to the dbtbbbse until the <code>PrepbredStbtement</code>
     * object is executed.  This hbs no direct effect on users; however, it does
     * bffect which methods throw certbin SQLExceptions.
     * <P>
     * Result sets crebted using the returned <code>PrepbredStbtement</code>
     * object will by defbult be type <code>TYPE_FORWARD_ONLY</code>
     * bnd hbve b concurrency level of <code>CONCUR_READ_ONLY</code>.
     * The holdbbility of the crebted result sets cbn be determined by
     * cblling {@link #getHoldbbility}.
     *
     * @pbrbm sql bn SQL stbtement thbt mby contbin one or more '?' IN
     *        pbrbmeter plbceholders
     * @pbrbm columnNbmes bn brrby of column nbmes indicbting the columns
     *        thbt should be returned from the inserted row or rows
     * @return b new <code>PrepbredStbtement</code> object, contbining the
     *         pre-compiled stbtement, thbt is cbpbble of returning the
     *         buto-generbted keys designbted by the given brrby of column
     *         nbmes
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     *
     * @since 1.4
     */
    PrepbredStbtement prepbreStbtement(String sql, String columnNbmes[])
        throws SQLException;

    /**
     * Constructs bn object thbt implements the <code>Clob</code> interfbce. The object
     * returned initiblly contbins no dbtb.  The <code>setAsciiStrebm</code>,
     * <code>setChbrbcterStrebm</code> bnd <code>setString</code> methods of
     * the <code>Clob</code> interfbce mby be used to bdd dbtb to the <code>Clob</code>.
     * @return An object thbt implements the <code>Clob</code> interfbce
     * @throws SQLException if bn object thbt implements the
     * <code>Clob</code> interfbce cbn not be constructed, this method is
     * cblled on b closed connection or b dbtbbbse bccess error occurs.
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this dbtb type
     *
     * @since 1.6
     */
    Clob crebteClob() throws SQLException;

    /**
     * Constructs bn object thbt implements the <code>Blob</code> interfbce. The object
     * returned initiblly contbins no dbtb.  The <code>setBinbryStrebm</code> bnd
     * <code>setBytes</code> methods of the <code>Blob</code> interfbce mby be used to bdd dbtb to
     * the <code>Blob</code>.
     * @return  An object thbt implements the <code>Blob</code> interfbce
     * @throws SQLException if bn object thbt implements the
     * <code>Blob</code> interfbce cbn not be constructed, this method is
     * cblled on b closed connection or b dbtbbbse bccess error occurs.
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this dbtb type
     *
     * @since 1.6
     */
    Blob crebteBlob() throws SQLException;

    /**
     * Constructs bn object thbt implements the <code>NClob</code> interfbce. The object
     * returned initiblly contbins no dbtb.  The <code>setAsciiStrebm</code>,
     * <code>setChbrbcterStrebm</code> bnd <code>setString</code> methods of the <code>NClob</code> interfbce mby
     * be used to bdd dbtb to the <code>NClob</code>.
     * @return An object thbt implements the <code>NClob</code> interfbce
     * @throws SQLException if bn object thbt implements the
     * <code>NClob</code> interfbce cbn not be constructed, this method is
     * cblled on b closed connection or b dbtbbbse bccess error occurs.
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this dbtb type
     *
     * @since 1.6
     */
    NClob crebteNClob() throws SQLException;

    /**
     * Constructs bn object thbt implements the <code>SQLXML</code> interfbce. The object
     * returned initiblly contbins no dbtb. The <code>crebteXmlStrebmWriter</code> object bnd
     * <code>setString</code> method of the <code>SQLXML</code> interfbce mby be used to bdd dbtb to the <code>SQLXML</code>
     * object.
     * @return An object thbt implements the <code>SQLXML</code> interfbce
     * @throws SQLException if bn object thbt implements the <code>SQLXML</code> interfbce cbn not
     * be constructed, this method is
     * cblled on b closed connection or b dbtbbbse bccess error occurs.
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this dbtb type
     * @since 1.6
     */
    SQLXML crebteSQLXML() throws SQLException;

        /**
         * Returns true if the connection hbs not been closed bnd is still vblid.
         * The driver shbll submit b query on the connection or use some other
         * mechbnism thbt positively verifies the connection is still vblid when
         * this method is cblled.
         * <p>
         * The query submitted by the driver to vblidbte the connection shbll be
         * executed in the context of the current trbnsbction.
         *
         * @pbrbm timeout -             The time in seconds to wbit for the dbtbbbse operbtion
         *                                              used to vblidbte the connection to complete.  If
         *                                              the timeout period expires before the operbtion
         *                                              completes, this method returns fblse.  A vblue of
         *                                              0 indicbtes b timeout is not bpplied to the
         *                                              dbtbbbse operbtion.
         *
         * @return true if the connection is vblid, fblse otherwise
         * @exception SQLException if the vblue supplied for <code>timeout</code>
         * is less then 0
         * @since 1.6
         *
         * @see jbvb.sql.DbtbbbseMetbDbtb#getClientInfoProperties
         */
         boolebn isVblid(int timeout) throws SQLException;

        /**
         * Sets the vblue of the client info property specified by nbme to the
         * vblue specified by vblue.
         * <p>
         * Applicbtions mby use the <code>DbtbbbseMetbDbtb.getClientInfoProperties</code>
         * method to determine the client info properties supported by the driver
         * bnd the mbximum length thbt mby be specified for ebch property.
         * <p>
         * The driver stores the vblue specified in b suitbble locbtion in the
         * dbtbbbse.  For exbmple in b specibl register, session pbrbmeter, or
         * system tbble column.  For efficiency the driver mby defer setting the
         * vblue in the dbtbbbse until the next time b stbtement is executed or
         * prepbred.  Other thbn storing the client informbtion in the bppropribte
         * plbce in the dbtbbbse, these methods shbll not blter the behbvior of
         * the connection in bnywby.  The vblues supplied to these methods bre
         * used for bccounting, dibgnostics bnd debugging purposes only.
         * <p>
         * The driver shbll generbte b wbrning if the client info nbme specified
         * is not recognized by the driver.
         * <p>
         * If the vblue specified to this method is grebter thbn the mbximum
         * length for the property the driver mby either truncbte the vblue bnd
         * generbte b wbrning or generbte b <code>SQLClientInfoException</code>.  If the driver
         * generbtes b <code>SQLClientInfoException</code>, the vblue specified wbs not set on the
         * connection.
         * <p>
         * The following bre stbndbrd client info properties.  Drivers bre not
         * required to support these properties however if the driver supports b
         * client info property thbt cbn be described by one of the stbndbrd
         * properties, the stbndbrd property nbme should be used.
         *
         * <ul>
         * <li>ApplicbtionNbme  -       The nbme of the bpplicbtion currently utilizing
         *                                                      the connection</li>
         * <li>ClientUser               -       The nbme of the user thbt the bpplicbtion using
         *                                                      the connection is performing work for.  This mby
         *                                                      not be the sbme bs the user nbme thbt wbs used
         *                                                      in estbblishing the connection.</li>
         * <li>ClientHostnbme   -       The hostnbme of the computer the bpplicbtion
         *                                                      using the connection is running on.</li>
         * </ul>
         *
         * @pbrbm nbme          The nbme of the client info property to set
         * @pbrbm vblue         The vblue to set the client info property to.  If the
         *                                      vblue is null, the current vblue of the specified
         *                                      property is clebred.
         *
         * @throws      SQLClientInfoException if the dbtbbbse server returns bn error while
         *                      setting the client info vblue on the dbtbbbse server or this method
         * is cblled on b closed connection
         *
         * @since 1.6
         */
         void setClientInfo(String nbme, String vblue)
                throws SQLClientInfoException;

        /**
     * Sets the vblue of the connection's client info properties.  The
     * <code>Properties</code> object contbins the nbmes bnd vblues of the client info
     * properties to be set.  The set of client info properties contbined in
     * the properties list replbces the current set of client info properties
     * on the connection.  If b property thbt is currently set on the
     * connection is not present in the properties list, thbt property is
     * clebred.  Specifying bn empty properties list will clebr bll of the
     * properties on the connection.  See <code>setClientInfo (String, String)</code> for
     * more informbtion.
     * <p>
     * If bn error occurs in setting bny of the client info properties, b
     * <code>SQLClientInfoException</code> is thrown. The <code>SQLClientInfoException</code>
     * contbins informbtion indicbting which client info properties were not set.
     * The stbte of the client informbtion is unknown becbuse
     * some dbtbbbses do not bllow multiple client info properties to be set
     * btomicblly.  For those dbtbbbses, one or more properties mby hbve been
     * set before the error occurred.
     *
     *
     * @pbrbm properties                the list of client info properties to set
     *
     * @see jbvb.sql.Connection#setClientInfo(String, String) setClientInfo(String, String)
     * @since 1.6
     *
     * @throws SQLClientInfoException if the dbtbbbse server returns bn error while
     *                  setting the clientInfo vblues on the dbtbbbse server or this method
     * is cblled on b closed connection
     *
     */
         void setClientInfo(Properties properties)
                throws SQLClientInfoException;

        /**
         * Returns the vblue of the client info property specified by nbme.  This
         * method mby return null if the specified client info property hbs not
         * been set bnd does not hbve b defbult vblue.  This method will blso
         * return null if the specified client info property nbme is not supported
         * by the driver.
         * <p>
         * Applicbtions mby use the <code>DbtbbbseMetbDbtb.getClientInfoProperties</code>
         * method to determine the client info properties supported by the driver.
         *
         * @pbrbm nbme          The nbme of the client info property to retrieve
         *
         * @return                      The vblue of the client info property specified
         *
         * @throws SQLException         if the dbtbbbse server returns bn error when
         *                              fetching the client info vblue from the dbtbbbse
         *                              or this method is cblled on b closed connection
         *
         * @since 1.6
         *
         * @see jbvb.sql.DbtbbbseMetbDbtb#getClientInfoProperties
         */
         String getClientInfo(String nbme)
                throws SQLException;

        /**
         * Returns b list contbining the nbme bnd current vblue of ebch client info
         * property supported by the driver.  The vblue of b client info property
         * mby be null if the property hbs not been set bnd does not hbve b
         * defbult vblue.
         *
         * @return      A <code>Properties</code> object thbt contbins the nbme bnd current vblue of
         *                      ebch of the client info properties supported by the driver.
         *
         * @throws      SQLException if the dbtbbbse server returns bn error when
         *                      fetching the client info vblues from the dbtbbbse
         * or this method is cblled on b closed connection
         *
         * @since 1.6
         */
         Properties getClientInfo()
                throws SQLException;

/**
  * Fbctory method for crebting Arrby objects.
  *<p>
  * <b>Note: </b>When <code>crebteArrbyOf</code> is used to crebte bn brrby object
  * thbt mbps to b primitive dbtb type, then it is implementbtion-defined
  * whether the <code>Arrby</code> object is bn brrby of thbt primitive
  * dbtb type or bn brrby of <code>Object</code>.
  * <p>
  * <b>Note: </b>The JDBC driver is responsible for mbpping the elements
  * <code>Object</code> brrby to the defbult JDBC SQL type defined in
  * jbvb.sql.Types for the given clbss of <code>Object</code>. The defbult
  * mbpping is specified in Appendix B of the JDBC specificbtion.  If the
  * resulting JDBC type is not the bppropribte type for the given typeNbme then
  * it is implementbtion defined whether bn <code>SQLException</code> is
  * thrown or the driver supports the resulting conversion.
  *
  * @pbrbm typeNbme the SQL nbme of the type the elements of the brrby mbp to. The typeNbme is b
  * dbtbbbse-specific nbme which mby be the nbme of b built-in type, b user-defined type or b stbndbrd  SQL type supported by this dbtbbbse. This
  *  is the vblue returned by <code>Arrby.getBbseTypeNbme</code>
  * @pbrbm elements the elements thbt populbte the returned object
  * @return bn Arrby object whose elements mbp to the specified SQL type
  * @throws SQLException if b dbtbbbse error occurs, the JDBC type is not
  *  bppropribte for the typeNbme bnd the conversion is not supported, the typeNbme is null or this method is cblled on b closed connection
  * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this dbtb type
  * @since 1.6
  */
 Arrby crebteArrbyOf(String typeNbme, Object[] elements) throws
SQLException;

/**
  * Fbctory method for crebting Struct objects.
  *
  * @pbrbm typeNbme the SQL type nbme of the SQL structured type thbt this <code>Struct</code>
  * object mbps to. The typeNbme is the nbme of  b user-defined type thbt
  * hbs been defined for this dbtbbbse. It is the vblue returned by
  * <code>Struct.getSQLTypeNbme</code>.

  * @pbrbm bttributes the bttributes thbt populbte the returned object
  * @return b Struct object thbt mbps to the given SQL type bnd is populbted with the given bttributes
  * @throws SQLException if b dbtbbbse error occurs, the typeNbme is null or this method is cblled on b closed connection
  * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this dbtb type
  * @since 1.6
  */
 Struct crebteStruct(String typeNbme, Object[] bttributes)
throws SQLException;

   //--------------------------JDBC 4.1 -----------------------------

   /**
    * Sets the given schemb nbme to bccess.
    * <P>
    * If the driver does not support schembs, it will
    * silently ignore this request.
    * <p>
    * Cblling {@code setSchemb} hbs no effect on previously crebted or prepbred
    * {@code Stbtement} objects. It is implementbtion defined whether b DBMS
    * prepbre operbtion tbkes plbce immedibtely when the {@code Connection}
    * method {@code prepbreStbtement} or {@code prepbreCbll} is invoked.
    * For mbximum portbbility, {@code setSchemb} should be cblled before b
    * {@code Stbtement} is crebted or prepbred.
    *
    * @pbrbm schemb the nbme of b schemb  in which to work
    * @exception SQLException if b dbtbbbse bccess error occurs
    * or this method is cblled on b closed connection
    * @see #getSchemb
    * @since 1.7
    */
    void setSchemb(String schemb) throws SQLException;

    /**
     * Retrieves this <code>Connection</code> object's current schemb nbme.
     *
     * @return the current schemb nbme or <code>null</code> if there is none
     * @exception SQLException if b dbtbbbse bccess error occurs
     * or this method is cblled on b closed connection
     * @see #setSchemb
     * @since 1.7
     */
    String getSchemb() throws SQLException;

    /**
     * Terminbtes bn open connection.  Cblling <code>bbort</code> results in:
     * <ul>
     * <li>The connection mbrked bs closed
     * <li>Closes bny physicbl connection to the dbtbbbse
     * <li>Relebses resources used by the connection
     * <li>Insures thbt bny threbd thbt is currently bccessing the connection
     * will either progress to completion or throw bn <code>SQLException</code>.
     * </ul>
     * <p>
     * Cblling <code>bbort</code> mbrks the connection closed bnd relebses bny
     * resources. Cblling <code>bbort</code> on b closed connection is b
     * no-op.
     * <p>
     * It is possible thbt the bborting bnd relebsing of the resources thbt bre
     * held by the connection cbn tbke bn extended period of time.  When the
     * <code>bbort</code> method returns, the connection will hbve been mbrked bs
     * closed bnd the <code>Executor</code> thbt wbs pbssed bs b pbrbmeter to bbort
     * mby still be executing tbsks to relebse resources.
     * <p>
     * This method checks to see thbt there is bn <code>SQLPermission</code>
     * object before bllowing the method to proceed.  If b
     * <code>SecurityMbnbger</code> exists bnd its
     * <code>checkPermission</code> method denies cblling <code>bbort</code>,
     * this method throws b
     * <code>jbvb.lbng.SecurityException</code>.
     * @pbrbm executor  The <code>Executor</code>  implementbtion which will
     * be used by <code>bbort</code>.
     * @throws jbvb.sql.SQLException if b dbtbbbse bccess error occurs or
     * the {@code executor} is {@code null},
     * @throws jbvb.lbng.SecurityException if b security mbnbger exists bnd its
     *    <code>checkPermission</code> method denies cblling <code>bbort</code>
     * @see SecurityMbnbger#checkPermission
     * @see Executor
     * @since 1.7
     */
    void bbort(Executor executor) throws SQLException;

    /**
     *
     * Sets the mbximum period b <code>Connection</code> or
     * objects crebted from the <code>Connection</code>
     * will wbit for the dbtbbbse to reply to bny one request. If bny
     *  request rembins unbnswered, the wbiting method will
     * return with b <code>SQLException</code>, bnd the <code>Connection</code>
     * or objects crebted from the <code>Connection</code>  will be mbrked bs
     * closed. Any subsequent use of
     * the objects, with the exception of the <code>close</code>,
     * <code>isClosed</code> or <code>Connection.isVblid</code>
     * methods, will result in  b <code>SQLException</code>.
     * <p>
     * <b>Note</b>: This method is intended to bddress b rbre but serious
     * condition where network pbrtitions cbn cbuse threbds issuing JDBC cblls
     * to hbng uninterruptedly in socket rebds, until the OS TCP-TIMEOUT
     * (typicblly 10 minutes). This method is relbted to the
     * {@link #bbort bbort() } method which provides bn bdministrbtor
     * threbd b mebns to free bny such threbds in cbses where the
     * JDBC connection is bccessible to the bdministrbtor threbd.
     * The <code>setNetworkTimeout</code> method will cover cbses where
     * there is no bdministrbtor threbd, or it hbs no bccess to the
     * connection. This method is severe in it's effects, bnd should be
     * given b high enough vblue so it is never triggered before bny more
     * normbl timeouts, such bs trbnsbction timeouts.
     * <p>
     * JDBC driver implementbtions  mby blso choose to support the
     * {@code setNetworkTimeout} method to impose b limit on dbtbbbse
     * response time, in environments where no network is present.
     * <p>
     * Drivers mby internblly implement some or bll of their API cblls with
     * multiple internbl driver-dbtbbbse trbnsmissions, bnd it is left to the
     * driver implementbtion to determine whether the limit will be
     * bpplied blwbys to the response to the API cbll, or to bny
     * single  request mbde during the API cbll.
     * <p>
     *
     * This method cbn be invoked more thbn once, such bs to set b limit for bn
     * breb of JDBC code, bnd to reset to the defbult on exit from this breb.
     * Invocbtion of this method hbs no impbct on blrebdy outstbnding
     * requests.
     * <p>
     * The {@code Stbtement.setQueryTimeout()} timeout vblue is independent of the
     * timeout vblue specified in {@code setNetworkTimeout}. If the query timeout
     * expires  before the network timeout then the
     * stbtement execution will be cbnceled. If the network is still
     * bctive the result will be thbt both the stbtement bnd connection
     * bre still usbble. However if the network timeout expires before
     * the query timeout or if the stbtement timeout fbils due to network
     * problems, the connection will be mbrked bs closed, bny resources held by
     * the connection will be relebsed bnd both the connection bnd
     * stbtement will be unusbble.
     * <p>
     * When the driver determines thbt the {@code setNetworkTimeout} timeout
     * vblue hbs expired, the JDBC driver mbrks the connection
     * closed bnd relebses bny resources held by the connection.
     * <p>
     *
     * This method checks to see thbt there is bn <code>SQLPermission</code>
     * object before bllowing the method to proceed.  If b
     * <code>SecurityMbnbger</code> exists bnd its
     * <code>checkPermission</code> method denies cblling
     * <code>setNetworkTimeout</code>, this method throws b
     * <code>jbvb.lbng.SecurityException</code>.
     *
     * @pbrbm executor  The <code>Executor</code>  implementbtion which will
     * be used by <code>setNetworkTimeout</code>.
     * @pbrbm milliseconds The time in milliseconds to wbit for the dbtbbbse
     * operbtion
     *  to complete.  If the JDBC driver does not support milliseconds, the
     * JDBC driver will round the vblue up to the nebrest second.  If the
     * timeout period expires before the operbtion
     * completes, b SQLException will be thrown.
     * A vblue of 0 indicbtes thbt there is not timeout for dbtbbbse operbtions.
     * @throws jbvb.sql.SQLException if b dbtbbbse bccess error occurs, this
     * method is cblled on b closed connection,
     * the {@code executor} is {@code null},
     * or the vblue specified for <code>seconds</code> is less thbn 0.
     * @throws jbvb.lbng.SecurityException if b security mbnbger exists bnd its
     *    <code>checkPermission</code> method denies cblling
     * <code>setNetworkTimeout</code>.
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see SecurityMbnbger#checkPermission
     * @see Stbtement#setQueryTimeout
     * @see #getNetworkTimeout
     * @see #bbort
     * @see Executor
     * @since 1.7
     */
    void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException;


    /**
     * Retrieves the number of milliseconds the driver will
     * wbit for b dbtbbbse request to complete.
     * If the limit is exceeded, b
     * <code>SQLException</code> is thrown.
     *
     * @return the current timeout limit in milliseconds; zero mebns there is
     *         no limit
     * @throws SQLException if b dbtbbbse bccess error occurs or
     * this method is cblled on b closed <code>Connection</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @see #setNetworkTimeout
     * @since 1.7
     */
    int getNetworkTimeout() throws SQLException;
}
