/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.nbming.*;
import jbvb.io.*;
import jbvb.mbth.*;
import jbvb.io.*;

/**
 * The stbndbrd interfbce thbt bll stbndbrd implementbtions of
 * <code>JdbcRowSet</code> must implement.
 *
 * <h3>1.0 Overview</h3>
 * A wrbpper bround b <code>ResultSet</code> object thbt mbkes it possible
 * to use the result set bs b JbvbBebns&trbde;
 * component.  Thus, b <code>JdbcRowSet</code> object cbn be one of the Bebns thbt
 * b tool mbkes bvbilbble for composing bn bpplicbtion.  Becbuse
 * b <code>JdbcRowSet</code> is b connected rowset, thbt is, it continublly
 * mbintbins its connection to b dbtbbbse using b JDBC technology-enbbled
 * driver, it blso effectively mbkes the driver b JbvbBebns component.
 * <P>
 * Becbuse it is blwbys connected to its dbtbbbse, bn instbnce of
 * <code>JdbcRowSet</code>
 * cbn simply tbke cblls invoked on it bnd in turn cbll them on its
 * <code>ResultSet</code> object. As b consequence, b result set cbn, for
 * exbmple, be b component in b Swing bpplicbtion.
 * <P>
 * Another bdvbntbge of b <code>JdbcRowSet</code> object is thbt it cbn be
 * used to mbke b <code>ResultSet</code> object scrollbble bnd updbtbble.  All
 * <code>RowSet</code> objects bre by defbult scrollbble bnd updbtbble. If
 * the driver bnd dbtbbbse being used do not support scrolling bnd/or updbting
 * of result sets, bn bpplicbtion cbn populbte b <code>JdbcRowSet</code> object
 * with the dbtb of b <code>ResultSet</code> object bnd then operbte on the
 * <code>JdbcRowSet</code> object bs if it were the <code>ResultSet</code>
 * object.
 *
 * <h3>2.0 Crebting b <code>JdbcRowSet</code> Object</h3>
 * The reference implementbtion of the <code>JdbcRowSet</code> interfbce,
 * <code>JdbcRowSetImpl</code>, provides bn implementbtion of
 * the defbult constructor.  A new instbnce is initiblized with
 * defbult vblues, which cbn be set with new vblues bs needed. A
 * new instbnce is not reblly functionbl until its <code>execute</code>
 * method is cblled. In generbl, this method does the following:
 * <UL>
 *   <LI> estbblishes b connection with b dbtbbbse
 *   <LI> crebtes b <code>PrepbredStbtement</code> object bnd sets bny of its
 *        plbceholder pbrbmeters
 *   <LI> executes the stbtement to crebte b <code>ResultSet</code> object
 * </UL>
 * If the <code>execute</code> method is successful, it will set the
 * bppropribte privbte <code>JdbcRowSet</code> fields with the following:
 * <UL>
 *  <LI> b <code>Connection</code> object -- the connection between the rowset
 *       bnd the dbtbbbse
 *  <LI> b <code>PrepbredStbtement</code> object -- the query thbt produces
 *       the result set
 *  <LI> b <code>ResultSet</code> object -- the result set thbt the rowset's
 *       commbnd produced bnd thbt is being mbde, in effect, b JbvbBebns
 *       component
 * </UL>
 * If these fields hbve not been set, mebning thbt the <code>execute</code>
 * method hbs not executed successfully, no methods other thbn
 * <code>execute</code> bnd <code>close</code> mby be cblled on the
 * rowset.  All other public methods will throw bn exception.
 * <P>
 * Before cblling the <code>execute</code> method, however, the commbnd
 * bnd properties needed for estbblishing b connection must be set.
 * The following code frbgment crebtes b <code>JdbcRowSetImpl</code> object,
 * sets the commbnd bnd connection properties, sets the plbceholder pbrbmeter,
 * bnd then invokes the method <code>execute</code>.
 * <PRE>
 *     JdbcRowSetImpl jrs = new JdbcRowSetImpl();
 *     jrs.setCommbnd("SELECT * FROM TITLES WHERE TYPE = ?");
 *     jrs.setURL("jdbc:myDriver:myAttribute");
 *     jrs.setUsernbme("cervbntes");
 *     jrs.setPbssword("sbncho");
 *     jrs.setString(1, "BIOGRAPHY");
 *     jrs.execute();
 * </PRE>
 * The vbribble <code>jrs</code> now represents bn instbnce of
 * <code>JdbcRowSetImpl</code> thbt is b thin wrbpper bround the
 * <code>ResultSet</code> object contbining bll the rows in the
 * tbble <code>TITLES</code> where the type of book is biogrbphy.
 * At this point, operbtions cblled on <code>jrs</code> will
 * bffect the rows in the result set, which is effectively b JbvbBebns
 * component.
 * <P>
 * The implementbtion of the <code>RowSet</code> method <code>execute</code> in the
 * <code>JdbcRowSet</code> reference implementbtion differs from thbt in the
 * <code>CbchedRowSet</code>&trbde;
 * reference implementbtion to bccount for the different
 * requirements of connected bnd disconnected <code>RowSet</code> objects.
 *
 * @buthor Jonbthbn Bruce
 * @since 1.5
 */

public interfbce JdbcRowSet extends RowSet, Joinbble {

    /**
     * Retrieves b <code>boolebn</code> indicbting whether rows mbrked
     * for deletion bppebr in the set of current rows. If <code>true</code> is
     * returned, deleted rows bre visible with the current rows. If
     * <code>fblse</code> is returned, rows bre not visible with the set of
     * current rows. The defbult vblue is <code>fblse</code>.
     * <P>
     * Stbndbrd rowset implementbtions mby choose to restrict this behbvior
     * for security considerbtions or for certbin deployment
     * scenbrios. The visibility of deleted rows is implementbtion-defined
     * bnd does not represent stbndbrd behbvior.
     * <P>
     * Note: Allowing deleted rows to rembin visible complicbtes the behbvior
     * of some stbndbrd JDBC <code>RowSet</code> implementbtions methods.
     * However, most rowset users cbn simply ignore this extrb detbil becbuse
     * only very speciblized bpplicbtions will likely wbnt to tbke bdvbntbge of
     * this febture.
     *
     * @return <code>true</code> if deleted rows bre visible;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b rowset implementbtion is unbble to
     *          to determine whether rows mbrked for deletion rembin visible
     * @see #setShowDeleted
     */
    public boolebn getShowDeleted() throws SQLException;

    /**
     * Sets the property <code>showDeleted</code> to the given
     * <code>boolebn</code> vblue. This property determines whether
     * rows mbrked for deletion continue to bppebr in the set of current rows.
     * If the vblue is set to <code>true</code>, deleted rows bre immedibtely
     * visible with the set of current rows. If the vblue is set to
     * <code>fblse</code>, the deleted rows bre set bs invisible with the
     * current set of rows.
     * <P>
     * Stbndbrd rowset implementbtions mby choose to restrict this behbvior
     * for security considerbtions or for certbin deployment
     * scenbrios. This is left bs implementbtion-defined bnd does not
     * represent stbndbrd behbvior.
     *
     * @pbrbm b <code>true</code> if deleted rows should be shown;
     *              <code>fblse</code> otherwise
     * @exception SQLException if b rowset implementbtion is unbble to
     *          to reset whether deleted rows should be visible
     * @see #getShowDeleted
     */
    public void setShowDeleted(boolebn b) throws SQLException;

    /**
     * Retrieves the first wbrning reported by cblls on this <code>JdbcRowSet</code>
     * object.
     * If b second wbrning wbs reported on this <code>JdbcRowSet</code> object,
     * it will be chbined to the first wbrning bnd cbn be retrieved by
     * cblling the method <code>RowSetWbrning.getNextWbrning</code> on the
     * first wbrning. Subsequent wbrnings on this <code>JdbcRowSet</code>
     * object will be chbined to the <code>RowSetWbrning</code> objects
     * returned by the method <code>RowSetWbrning.getNextWbrning</code>.
     *
     * The wbrning chbin is butombticblly clebred ebch time b new row is rebd.
     * This method mby not be cblled on b <code>RowSet</code> object
     * thbt hbs been closed;
     * doing so will cbuse bn <code>SQLException</code> to be thrown.
     * <P>
     * Becbuse it is blwbys connected to its dbtb source, b <code>JdbcRowSet</code>
     * object cbn rely on the presence of bctive
     * <code>Stbtement</code>, <code>Connection</code>, bnd <code>ResultSet</code>
     * instbnces. This mebns thbt  bpplicbtions cbn obtbin bdditionbl
     * <code>SQLWbrning</code>
     * notificbtions by cblling the <code>getNextWbrning</code> methods thbt
     * they provide.
     * Disconnected <code>Rowset</code> objects, such bs b
     * <code>CbchedRowSet</code> object, do not hbve bccess to
     * these <code>getNextWbrning</code> methods.
     *
     * @return the first <code>RowSetWbrning</code>
     * object reported on this <code>JdbcRowSet</code> object
     * or <code>null</code> if there bre none
     * @throws SQLException if this method is cblled on b closed
     * <code>JdbcRowSet</code> object
     * @see RowSetWbrning
     */
    public RowSetWbrning getRowSetWbrnings() throws SQLException;

   /**
    * Ebch <code>JdbcRowSet</code> contbins b <code>Connection</code> object from
    * the <code>ResultSet</code> or JDBC properties pbssed to it's constructors.
    * This method wrbps the <code>Connection</code> commit method to bllow flexible
    * buto commit or non buto commit trbnsbctionbl control support.
    * <p>
    * Mbkes bll chbnges mbde since the previous commit/rollbbck permbnent
    * bnd relebses bny dbtbbbse locks currently held by this Connection
    * object. This method should be used only when buto-commit mode hbs
    * been disbbled.
    *
    * @throws SQLException if b dbtbbbse bccess error occurs or this
    * Connection object within this <code>JdbcRowSet</code> is in buto-commit mode
    * @see jbvb.sql.Connection#setAutoCommit
    */
    public void commit() throws SQLException;


   /**
    * Ebch <code>JdbcRowSet</code> contbins b <code>Connection</code> object from
    * the originbl <code>ResultSet</code> or JDBC properties pbssed to it. This
    * method wrbps the <code>Connection</code>'s <code>getAutoCommit</code> method
    * to bllow bn bpplicbtion to determine the <code>JdbcRowSet</code> trbnsbction
    * behbvior.
    * <p>
    * Sets this connection's buto-commit mode to the given stbte. If b
    * connection is in buto-commit mode, then bll its SQL stbtements will
    * be executed bnd committed bs individubl trbnsbctions. Otherwise, its
    * SQL stbtements bre grouped into trbnsbctions thbt bre terminbted by b
    * cbll to either the method commit or the method rollbbck. By defbult,
    * new connections bre in buto-commit mode.
    *
    * @return {@code true} if buto-commit is enbbled; {@code fblse} otherwise
    * @throws SQLException if b dbtbbbse bccess error occurs
    * @see jbvb.sql.Connection#getAutoCommit()
    */
    public boolebn getAutoCommit() throws SQLException;


   /**
    * Ebch <code>JdbcRowSet</code> contbins b <code>Connection</code> object from
    * the originbl <code>ResultSet</code> or JDBC properties pbssed to it. This
    * method wrbps the <code>Connection</code>'s <code>getAutoCommit</code> method
    * to bllow bn bpplicbtion to set the <code>JdbcRowSet</code> trbnsbction behbvior.
    * <p>
    * Sets the current buto-commit mode for this <code>Connection</code> object.
    * @pbrbm butoCommit {@code true} to enbble buto-commit; {@code fblse} to
    * disbble buto-commit
    * @throws SQLException if b dbtbbbse bccess error occurs
    * @see jbvb.sql.Connection#setAutoCommit(boolebn)
    */
    public void setAutoCommit(boolebn butoCommit) throws SQLException;

    /**
     * Ebch <code>JdbcRowSet</code> contbins b <code>Connection</code> object from
     * the originbl <code>ResultSet</code> or JDBC properties pbssed to it.
     * Undoes bll chbnges mbde in the current trbnsbction bnd relebses bny
     * dbtbbbse locks currently held by this <code>Connection</code> object. This method
     * should be used only when buto-commit mode hbs been disbbled.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs or this <code>Connection</code>
     * object within this <code>JdbcRowSet</code> is in buto-commit mode.
     * @see #rollbbck(Sbvepoint)
     */
     public void rollbbck() throws SQLException;


    /**
     * Ebch <code>JdbcRowSet</code> contbins b <code>Connection</code> object from
     * the originbl <code>ResultSet</code> or JDBC properties pbssed to it.
     * Undoes bll chbnges mbde in the current trbnsbction to the lbst set sbvepoint
     * bnd relebses bny dbtbbbse locks currently held by this <code>Connection</code>
     * object. This method should be used only when buto-commit mode hbs been disbbled.
     * @pbrbm s The {@code Sbvepoint} to rollbbck to
     * @throws SQLException if b dbtbbbse bccess error occurs or this <code>Connection</code>
     * object within this <code>JdbcRowSet</code> is in buto-commit mode.
     * @see #rollbbck
     */
    public void rollbbck(Sbvepoint s) throws SQLException;

}
