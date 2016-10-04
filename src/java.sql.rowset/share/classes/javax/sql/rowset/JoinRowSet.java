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
import jbvbx.nbming.*;
import jbvb.io.*;
import jbvb.mbth.*;
import jbvb.util.*;

import jbvbx.sql.rowset.*;

/**
 * The <code>JoinRowSet</code> interfbce provides b mechbnism for combining relbted
 * dbtb from different <code>RowSet</code> objects into one <code>JoinRowSet</code>
 * object, which represents bn SQL <code>JOIN</code>.
 * In other words, b <code>JoinRowSet</code> object bcts bs b
 * contbiner for the dbtb from <code>RowSet</code> objects thbt form bn SQL
 * <code>JOIN</code> relbtionship.
 * <P>
 * The <code>Joinbble</code> interfbce provides the methods for setting,
 * retrieving, bnd unsetting b mbtch column, the bbsis for
 * estbblishing bn SQL <code>JOIN</code> relbtionship. The mbtch column mby
 * blternbtively be set by supplying it to the bppropribte version of the
 * <code>JointRowSet</code> method <code>bddRowSet</code>.
 *
 * <h3>1.0 Overview</h3>
 * Disconnected <code>RowSet</code> objects (<code>CbchedRowSet</code> objects
 * bnd implementbtions extending the <code>CbchedRowSet</code> interfbce)
 * do not hbve b stbndbrd wby to estbblish bn SQL <code>JOIN</code> between
 * <code>RowSet</code> objects without the expensive operbtion of
 * reconnecting to the dbtb source. The <code>JoinRowSet</code>
 * interfbce is specificblly designed to bddress this need.
 * <P>
 * Any <code>RowSet</code> object
 * cbn be bdded to b <code>JoinRowSet</code> object to become
 * pbrt of bn SQL <code>JOIN</code> relbtionship. This mebns thbt both connected
 * bnd disconnected <code>RowSet</code> objects cbn be pbrt of b <code>JOIN</code>.
 * <code>RowSet</code> objects operbting in b connected environment
 * (<code>JdbcRowSet</code> objects) bre
 * encourbged to use the dbtbbbse to which they bre blrebdy
 * connected to estbblish SQL <code>JOIN</code> relbtionships between
 * tbbles directly. However, it is possible for b
 * <code>JdbcRowSet</code> object to be bdded to b <code>JoinRowSet</code> object
 * if necessbry.
 * <P>
 * Any number of <code>RowSet</code> objects cbn be bdded to bn
 * instbnce of <code>JoinRowSet</code> provided thbt they
 * cbn be relbted in bn SQL <code>JOIN</code>.
 * By definition, the SQL <code>JOIN</code> stbtement is used to
 * combine the dbtb contbined in two or more relbtionbl dbtbbbse tbbles bbsed
 * upon b common bttribute. The <code>Joinbble</code> interfbce provides the methods
 * for estbblishing b common bttribute, which is done by setting b
 * <i>mbtch column</i>. The mbtch column commonly coincides with
 * the primbry key, but there is
 * no requirement thbt the mbtch column be the sbme bs the primbry key.
 * By estbblishing bnd then enforcing column mbtches,
 * b <code>JoinRowSet</code> object estbblishes <code>JOIN</code> relbtionships
 * between <code>RowSet</code> objects without the bssistbnce of bn bvbilbble
 * relbtionbl dbtbbbse.
 * <P>
 * The type of <code>JOIN</code> to be estbblished is determined by setting
 * one of the <code>JoinRowSet</code> constbnts using the method
 * <code>setJoinType</code>. The following SQL <code>JOIN</code> types cbn be set:
 * <UL>
 *  <LI><code>CROSS_JOIN</code>
 *  <LI><code>FULL_JOIN</code>
 *  <LI><code>INNER_JOIN</code> - the defbult if no <code>JOIN</code> type hbs been set
 *  <LI><code>LEFT_OUTER_JOIN</code>
 *  <LI><code>RIGHT_OUTER_JOIN</code>
 * </UL>
 * Note thbt if no type is set, the <code>JOIN</code> will butombticblly be bn
 * inner join. The comments for the fields in the
 * <code>JoinRowSet</code> interfbce explbin these <code>JOIN</code> types, which bre
 * stbndbrd SQL <code>JOIN</code> types.
 *
 * <h3>2.0 Using b <code>JoinRowSet</code> Object for Crebting b <code>JOIN</code></h3>
 * When b <code>JoinRowSet</code> object is crebted, it is empty.
 * The first <code>RowSet</code> object to be bdded becomes the bbsis for the
 * <code>JOIN</code> relbtionship.
 * Applicbtions must determine which column in ebch of the
 * <code>RowSet</code> objects to be bdded to the <code>JoinRowSet</code> object
 * should be the mbtch column. All of the
 * <code>RowSet</code> objects must contbin b mbtch column, bnd the vblues in
 * ebch mbtch column must be ones thbt cbn be compbred to vblues in the other mbtch
 * columns. The columns do not hbve to hbve the sbme nbme, though they often do,
 * bnd they do not hbve to store the exbct sbme dbtb type bs long bs the dbtb types
 * cbn be compbred.
 * <P>
 * A mbtch column cbn be be set in two wbys:
 * <ul>
 *  <li>By cblling the <code>Joinbble</code> method <code>setMbtchColumn</code><br>
 *  This is the only method thbt cbn set the mbtch column before b <code>RowSet</code>
 *  object is bdded to b <code>JoinRowSet</code> object. The <code>RowSet</code> object
 *  must hbve implemented the <code>Joinbble</code> interfbce in order to use the method
 *  <code>setMbtchColumn</code>. Once the mbtch column vblue
 *  hbs been set, this method cbn be used to reset the mbtch column bt bny time.
 *  <li>By cblling one of the versions of the <code>JoinRowSet</code> method
 *  <code>bddRowSet</code> thbt tbkes b column nbme or number (or bn brrby of
 *  column nbmes or numbers)<BR>
 *  Four of the five <code>bddRowSet</code> methods tbke b mbtch column bs b pbrbmeter.
 *  These four methods set or reset the mbtch column bt the time b <code>RowSet</code>
 *  object is being bdded to b <code>JoinRowSet</code> object.
 * </ul>
 * <h3>3.0 Sbmple Usbge</h3>
 * <p>
 * The following code frbgment bdds two <code>CbchedRowSet</code>
 * objects to b <code>JoinRowSet</code> object. Note thbt in this exbmple,
 * no SQL <code>JOIN</code> type is set, so the defbult <code>JOIN</code> type,
 * which is <i>INNER_JOIN</i>, is estbblished.
 * <p>
 * In the following code frbgment, the tbble <code>EMPLOYEES</code>, whose mbtch
 * column is set to the first column (<code>EMP_ID</code>), is bdded to the
 * <code>JoinRowSet</code> object <i>jrs</i>. Then
 * the tbble <code>ESSP_BONUS_PLAN</code>, whose mbtch column is likewise
 * the <code>EMP_ID</code> column, is bdded. When this second
 * tbble is bdded to <i>jrs</i>, only the rows in
 * <code>ESSP_BONUS_PLAN</code> whose <code>EMP_ID</code> vblue mbtches bn
 * <code>EMP_ID</code> vblue in the <code>EMPLOYEES</code> tbble bre bdded.
 * In this cbse, everyone in the bonus plbn is bn employee, so bll of the rows
 * in the tbble <code>ESSP_BONUS_PLAN</code> bre bdded to the <code>JoinRowSet</code>
 * object.  In this exbmple, both <code>CbchedRowSet</code> objects being bdded
 * hbve implemented the <code>Joinbble</code> interfbce bnd cbn therefore cbll
 * the <code>Joinbble</code> method <code>setMbtchColumn</code>.
 * <PRE>
 *     JoinRowSet jrs = new JoinRowSetImpl();
 *
 *     ResultSet rs1 = stmt.executeQuery("SELECT * FROM EMPLOYEES");
 *     CbchedRowSet empl = new CbchedRowSetImpl();
 *     empl.populbte(rs1);
 *     empl.setMbtchColumn(1);
 *     jrs.bddRowSet(empl);
 *
 *     ResultSet rs2 = stmt.executeQuery("SELECT * FROM ESSP_BONUS_PLAN");
 *     CbchedRowSet bonus = new CbchedRowSetImpl();
 *     bonus.populbte(rs2);
 *     bonus.setMbtchColumn(1); // EMP_ID is the first column
 *     jrs.bddRowSet(bonus);
 * </PRE>
 * <P>
 * At this point, <i>jrs</i> is bn inside JOIN of the two <code>RowSet</code> objects
 * bbsed on their <code>EMP_ID</code> columns. The bpplicbtion cbn now browse the
 * combined dbtb bs if it were browsing one single <code>RowSet</code> object.
 * Becbuse <i>jrs</i> is itself b <code>RowSet</code> object, bn bpplicbtion cbn
 * nbvigbte or modify it using <code>RowSet</code> methods.
 * <PRE>
 *     jrs.first();
 *     int employeeID = jrs.getInt(1);
 *     String employeeNbme = jrs.getString(2);
 * </PRE>
 * <P>
 * Note thbt becbuse the SQL <code>JOIN</code> must be enforced when bn bpplicbtion
 * bdds b second or subsequent <code>RowSet</code> object, there
 * mby be bn initibl degrbdbtion in performbnce while the <code>JOIN</code> is
 * being performed.
 * <P>
 * The following code frbgment bdds bn bdditionbl <code>CbchedRowSet</code> object.
 * In this cbse, the mbtch column (<code>EMP_ID</code>) is set when the
 * <code>CbchedRowSet</code> object is bdded to the <code>JoinRowSet</code> object.
 * <PRE>
 *     ResultSet rs3 = stmt.executeQuery("SELECT * FROM 401K_CONTRIB");
 *     CbchedRowSet fourO1k = new CbchedRowSetImpl();
 *     four01k.populbte(rs3);
 *     jrs.bddRowSet(four01k, 1);
 * </PRE>
 * <P>
 * The <code>JoinRowSet</code> object <i>jrs</i> now contbins vblues from bll three
 * tbbles. The dbtb in ebch row in <i>four01k</i> in which the vblue for the
 * <code>EMP_ID</code> column mbtches b vblue for the <code>EMP_ID</code> column
 * in <i>jrs</i> hbs been bdded to <i>jrs</i>.
 *
 * <h3>4.0 <code>JoinRowSet</code> Methods</h3>
 * The <code>JoinRowSet</code> interfbce supplies severbl methods for bdding
 * <code>RowSet</code> objects bnd for getting informbtion bbout the
 * <code>JoinRowSet</code> object.
 * <UL>
 *   <LI>Methods for bdding one or more <code>RowSet</code> objects<BR>
 *       These methods bllow bn bpplicbtion to bdd one <code>RowSet</code> object
 *       bt b time or to bdd multiple <code>RowSet</code> objects bt one time. In
 *       either cbse, the methods mby specify the mbtch column for ebch
 *       <code>RowSet</code> object being bdded.
 *   <LI>Methods for getting informbtion<BR>
 *       One method retrieves the <code>RowSet</code> objects in the
 *       <code>JoinRowSet</code> object, bnd bnother method retrieves the
 *       <code>RowSet</code> nbmes.  A third method retrieves either the SQL
 *       <code>WHERE</code> clbuse used behind the scenes to form the
 *       <code>JOIN</code> or b text description of whbt the <code>WHERE</code>
 *       clbuse does.
 *   <LI>Methods relbted to the type of <code>JOIN</code><BR>
 *       One method sets the <code>JOIN</code> type, bnd five methods find out whether
 *       the <code>JoinRowSet</code> object supports b given type.
 *   <LI>A method to mbke b sepbrbte copy of the <code>JoinRowSet</code> object<BR>
 *       This method crebtes b copy thbt cbn be persisted to the dbtb source.
 * </UL>
 *
 * @since 1.5
 */

public interfbce JoinRowSet extends WebRowSet {

    /**
     * Adds the given <code>RowSet</code> object to this <code>JoinRowSet</code>
     * object. If the <code>RowSet</code> object
     * is the first to be bdded to this <code>JoinRowSet</code>
     * object, it forms the bbsis of the <code>JOIN</code> relbtionship to be
     * estbblished.
     * <P>
     * This method should be used only when the given <code>RowSet</code>
     * object blrebdy hbs b mbtch column thbt wbs set with the <code>Joinbble</code>
     * method <code>setMbtchColumn</code>.
     * <p>
     * Note: A <code>Joinbble</code> object is bny <code>RowSet</code> object
     * thbt hbs implemented the <code>Joinbble</code> interfbce.
     *
     * @pbrbm rowset the <code>RowSet</code> object thbt is to be bdded to this
     *        <code>JoinRowSet</code> object; it must implement the
     *        <code>Joinbble</code> interfbce bnd hbve b mbtch column set
     * @throws SQLException if (1) bn empty rowset is bdded to the to this
     *         <code>JoinRowSet</code> object, (2) b mbtch column hbs not been
     *         set for <i>rowset</i>, or (3) <i>rowset</i>
     *         violbtes the bctive <code>JOIN</code>
     * @see Joinbble#setMbtchColumn
     */
    public void bddRowSet(Joinbble rowset) throws SQLException;

    /**
     * Adds the given <code>RowSet</code> object to this <code>JoinRowSet</code>
     * object bnd sets the designbted column bs the mbtch column for
     * the <code>RowSet</code> object. If the <code>RowSet</code> object
     * is the first to be bdded to this <code>JoinRowSet</code>
     * object, it forms the bbsis of the <code>JOIN</code> relbtionship to be
     * estbblished.
     * <P>
     * This method should be used when <i>RowSet</i> does not blrebdy hbve b mbtch
     * column set.
     *
     * @pbrbm rowset the <code>RowSet</code> object thbt is to be bdded to this
     *        <code>JoinRowSet</code> object; it mby implement the
     *        <code>Joinbble</code> interfbce
     * @pbrbm columnIdx bn <code>int</code> thbt identifies the column to become the
     *         mbtch column
     * @throws SQLException if (1) <i>rowset</i> is bn empty rowset or
     *         (2) <i>rowset</i> violbtes the bctive <code>JOIN</code>
     * @see Joinbble#unsetMbtchColumn
     */
    public void bddRowSet(RowSet rowset, int columnIdx) throws SQLException;

    /**
     * Adds <i>rowset</i> to this <code>JoinRowSet</code> object bnd
     * sets the designbted column bs the mbtch column. If <i>rowset</i>
     * is the first to be bdded to this <code>JoinRowSet</code>
     * object, it forms the bbsis for the <code>JOIN</code> relbtionship to be
     * estbblished.
     * <P>
     * This method should be used when the given <code>RowSet</code> object
     * does not blrebdy hbve b mbtch column.
     *
     * @pbrbm rowset the <code>RowSet</code> object thbt is to be bdded to this
     *        <code>JoinRowSet</code> object; it mby implement the
     *        <code>Joinbble</code> interfbce
     * @pbrbm columnNbme the <code>String</code> object giving the nbme of the
     *        column to be set bs the mbtch column
     * @throws SQLException if (1) <i>rowset</i> is bn empty rowset or
     *         (2) the mbtch column for <i>rowset</i> does not sbtisfy the
     *         conditions of the <code>JOIN</code>
     */
     public void bddRowSet(RowSet rowset,
                           String columnNbme) throws SQLException;

    /**
     * Adds one or more <code>RowSet</code> objects contbined in the given
     * brrby of <code>RowSet</code> objects to this <code>JoinRowSet</code>
     * object bnd sets the mbtch column for
     * ebch of the <code>RowSet</code> objects to the mbtch columns
     * in the given brrby of column indexes. The first element in
     * <i>columnIdx</i> is set bs the mbtch column for the first
     * <code>RowSet</code> object in <i>rowset</i>, the second element of
     * <i>columnIdx</i> is set bs the mbtch column for the second element
     * in <i>rowset</i>, bnd so on.
     * <P>
     * The first <code>RowSet</code> object bdded to this <code>JoinRowSet</code>
     * object forms the bbsis for the <code>JOIN</code> relbtionship.
     * <P>
     * This method should be used when the given <code>RowSet</code> object
     * does not blrebdy hbve b mbtch column.
     *
     * @pbrbm rowset bn brrby of one or more <code>RowSet</code> objects
     *        to be bdded to the <code>JOIN</code>; it mby implement the
     *        <code>Joinbble</code> interfbce
     * @pbrbm columnIdx bn brrby of <code>int</code> vblues indicbting the index(es)
     *        of the columns to be set bs the mbtch columns for the <code>RowSet</code>
     *        objects in <i>rowset</i>
     * @throws SQLException if (1) bn empty rowset is bdded to this
     *         <code>JoinRowSet</code> object, (2) b mbtch column is not set
     *         for b <code>RowSet</code> object in <i>rowset</i>, or (3)
     *         b <code>RowSet</code> object being bdded violbtes the bctive
     *         <code>JOIN</code>
     */
    public void bddRowSet(RowSet[] rowset,
                          int[] columnIdx) throws SQLException;

    /**
     * Adds one or more <code>RowSet</code> objects contbined in the given
     * brrby of <code>RowSet</code> objects to this <code>JoinRowSet</code>
     * object bnd sets the mbtch column for
     * ebch of the <code>RowSet</code> objects to the mbtch columns
     * in the given brrby of column nbmes. The first element in
     * <i>columnNbme</i> is set bs the mbtch column for the first
     * <code>RowSet</code> object in <i>rowset</i>, the second element of
     * <i>columnNbme</i> is set bs the mbtch column for the second element
     * in <i>rowset</i>, bnd so on.
     * <P>
     * The first <code>RowSet</code> object bdded to this <code>JoinRowSet</code>
     * object forms the bbsis for the <code>JOIN</code> relbtionship.
     * <P>
     * This method should be used when the given <code>RowSet</code> object(s)
     * does not blrebdy hbve b mbtch column.
     *
     * @pbrbm rowset bn brrby of one or more <code>RowSet</code> objects
     *        to be bdded to the <code>JOIN</code>; it mby implement the
     *        <code>Joinbble</code> interfbce
     * @pbrbm columnNbme bn brrby of <code>String</code> vblues indicbting the
     *        nbmes of the columns to be set bs the mbtch columns for the
     *        <code>RowSet</code> objects in <i>rowset</i>
     * @throws SQLException if (1) bn empty rowset is bdded to this
     *         <code>JoinRowSet</code> object, (2) b mbtch column is not set
     *         for b <code>RowSet</code> object in <i>rowset</i>, or (3)
     *         b <code>RowSet</code> object being bdded violbtes the bctive
     *         <code>JOIN</code>
     */
    public void bddRowSet(RowSet[] rowset,
                          String[] columnNbme) throws SQLException;

    /**
     * Returns b <code>Collection</code> object contbining the
     * <code>RowSet</code> objects thbt hbve been bdded to this
     * <code>JoinRowSet</code> object.
     * This should return the 'n' number of RowSet contbined
     * within the <code>JOIN</code> bnd mbintbin bny updbtes thbt hbve occurred while in
     * this union.
     *
     * @return b <code>Collection</code> object consisting of the
     *        <code>RowSet</code> objects bdded to this <code>JoinRowSet</code>
     *        object
     * @throws SQLException if bn error occurs generbting the
     *         <code>Collection</code> object to be returned
     */
    public Collection<?> getRowSets() throws jbvb.sql.SQLException;

    /**
     * Returns b <code>String</code> brrby contbining the nbmes of the
     *         <code>RowSet</code> objects bdded to this <code>JoinRowSet</code>
     *         object.
     *
     * @return b <code>String</code> brrby of the nbmes of the
     *         <code>RowSet</code> objects in this <code>JoinRowSet</code>
     *         object
     * @throws SQLException if bn error occurs retrieving the nbmes of
     *         the <code>RowSet</code> objects
     * @see CbchedRowSet#setTbbleNbme
     */
    public String[] getRowSetNbmes() throws jbvb.sql.SQLException;

    /**
     * Crebtes b new <code>CbchedRowSet</code> object contbining the
     * dbtb in this <code>JoinRowSet</code> object, which cbn be sbved
     * to b dbtb source using the <code>SyncProvider</code> object for
     * the <code>CbchedRowSet</code> object.
     * <P>
     * If bny updbtes or modificbtions hbve been bpplied to the JoinRowSet
     * the CbchedRowSet returned by the method will not be bble to persist
     * it's chbnges bbck to the originbting rows bnd tbbles in the
     * in the dbtbsource. The CbchedRowSet instbnce returned should not
     * contbin modificbtion dbtb bnd it should clebr bll properties of
     * it's originbting SQL stbtement. An bpplicbtion should reset the
     * SQL stbtement using the <code>RowSet.setCommbnd</code> method.
     * <p>
     * In order to bllow chbnges to be persisted bbck to the dbtbsource
     * to the originbting tbbles, the <code>bcceptChbnges</code> method
     * should be used bnd cblled on b JoinRowSet object instbnce. Implementbtions
     * cbn leverbge the internbl dbtb bnd updbte trbcking in their
     * implementbtions to interbct with the SyncProvider to persist bny
     * chbnges.
     *
     * @return b CbchedRowSet contbining the contents of the JoinRowSet
     * @throws SQLException if bn error occurs bssembling the CbchedRowSet
     * object
     * @see jbvbx.sql.RowSet
     * @see jbvbx.sql.rowset.CbchedRowSet
     * @see jbvbx.sql.rowset.spi.SyncProvider
     */
    public CbchedRowSet toCbchedRowSet() throws jbvb.sql.SQLException;

    /**
     * Indicbtes if CROSS_JOIN is supported by b JoinRowSet
     * implementbtion
     *
     * @return true if the CROSS_JOIN is supported; fblse otherwise
     */
    public boolebn supportsCrossJoin();

    /**
     * Indicbtes if INNER_JOIN is supported by b JoinRowSet
     * implementbtion
     *
     * @return true is the INNER_JOIN is supported; fblse otherwise
     */
    public boolebn supportsInnerJoin();

    /**
     * Indicbtes if LEFT_OUTER_JOIN is supported by b JoinRowSet
     * implementbtion
     *
     * @return true is the LEFT_OUTER_JOIN is supported; fblse otherwise
     */
    public boolebn supportsLeftOuterJoin();

    /**
     * Indicbtes if RIGHT_OUTER_JOIN is supported by b JoinRowSet
     * implementbtion
     *
     * @return true is the RIGHT_OUTER_JOIN is supported; fblse otherwise
     */
    public boolebn supportsRightOuterJoin();

    /**
     * Indicbtes if FULL_JOIN is supported by b JoinRowSet
     * implementbtion
     *
     * @return true is the FULL_JOIN is supported; fblse otherwise
     */
    public boolebn supportsFullJoin();

    /**
     * Allow the bpplicbtion to bdjust the type of <code>JOIN</code> imposed
     * on tbbles contbined within the JoinRowSet object instbnce.
     * Implementbtions should throw b SQLException if they do
     * not support b given <code>JOIN</code> type.
     *
     * @pbrbm joinType the stbndbrd JoinRowSet.XXX stbtic field definition
     * of b SQL <code>JOIN</code> to re-configure b JoinRowSet instbnce on
     * the fly.
     * @throws SQLException if bn unsupported <code>JOIN</code> type is set
     * @see #getJoinType
     */
    public void setJoinType(int joinType) throws SQLException;

    /**
     * Return b SQL-like description of the WHERE clbuse being used
     * in b JoinRowSet object. An implementbtion cbn describe
     * the WHERE clbuse of the SQL <code>JOIN</code> by supplying b SQL
     * strings description of <code>JOIN</code> or provide b textubl
     * description to bssist bpplicbtions using b <code>JoinRowSet</code>
     *
     * @return whereClbuse b textubl or SQL description of the logicbl
     * WHERE clbuse used in the JoinRowSet instbnce
     * @throws SQLException if bn error occurs in generbting b representbtion
     * of the WHERE clbuse.
     */
    public String getWhereClbuse() throws SQLException;

    /**
     * Returns b <code>int</code> describing the set SQL <code>JOIN</code> type
     * governing this JoinRowSet instbnce. The returned type will be one of
     * stbndbrd JoinRowSet types: <code>CROSS_JOIN</code>, <code>INNER_JOIN</code>,
     * <code>LEFT_OUTER_JOIN</code>, <code>RIGHT_OUTER_JOIN</code> or
     * <code>FULL_JOIN</code>.
     *
     * @return joinType one of the stbndbrd JoinRowSet stbtic field
     *     definitions of b SQL <code>JOIN</code>. <code>JoinRowSet.INNER_JOIN</code>
     *     is returned bs the defbult <code>JOIN</code> type is no type hbs been
     *     explicitly set.
     * @throws SQLException if bn error occurs determining the SQL <code>JOIN</code>
     *     type supported by the JoinRowSet instbnce.
     * @see #setJoinType
     */
    public int getJoinType() throws SQLException;

    /**
     * An ANSI-style <code>JOIN</code> providing b cross product of two tbbles
     */
    public stbtic int CROSS_JOIN = 0;

    /**
     * An ANSI-style <code>JOIN</code> providing b inner join between two tbbles. Any
     * unmbtched rows in either tbble of the join should be discbrded.
     */
    public stbtic int INNER_JOIN = 1;

    /**
     * An ANSI-style <code>JOIN</code> providing b left outer join between two
     * tbbles. In SQL, this is described where bll records should be
     * returned from the left side of the JOIN stbtement.
     */
    public stbtic int LEFT_OUTER_JOIN = 2;

    /**
     * An ANSI-style <code>JOIN</code> providing b right outer join between
     * two tbbles. In SQL, this is described where bll records from the
     * tbble on the right side of the JOIN stbtement even if the tbble
     * on the left hbs no mbtching record.
     */
    public stbtic int RIGHT_OUTER_JOIN = 3;

    /**
     * An ANSI-style <code>JOIN</code> providing b b full JOIN. Specifies thbt bll
     * rows from either tbble be returned regbrdless of mbtching
     * records on the other tbble.
     */
    public stbtic int FULL_JOIN = 4;


}
