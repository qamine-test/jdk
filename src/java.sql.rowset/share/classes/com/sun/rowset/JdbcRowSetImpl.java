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

pbckbge com.sun.rowset;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvbx.nbming.*;
import jbvb.io.*;
import jbvb.mbth.*;
import jbvb.util.*;

import jbvbx.sql.rowset.*;

/**
 * The stbndbrd implementbtion of the <code>JdbcRowSet</code> interfbce. See the interfbce
 * definition for full behbvior bnd implementbtion requirements.
 *
 * @buthor Jonbthbn Bruce, Amit Hbndb
 */

public clbss JdbcRowSetImpl extends BbseRowSet implements JdbcRowSet, Joinbble {

    /**
     * The <code>Connection</code> object thbt is this rowset's
     * current connection to the dbtbbbse.  This field is set
     * internblly when the connection is estbblished.
     */
    privbte Connection conn;

    /**
     * The <code>PrepbredStbtement</code> object thbt is this rowset's
     * current commbnd.  This field is set internblly when the method
     * <code>execute</code> crebtes the <code>PrepbredStbtement</code>
     * object.
     */
    privbte PrepbredStbtement ps;

    /**
     * The <code>ResultSet</code> object thbt is this rowset's
     * current result set.  This field is set internblly when the method
     * <code>execute</code> executes the rowset's commbnd bnd thereby
     * crebtes the rowset's <code>ResultSet</code> object.
     */
    privbte ResultSet rs;

    /**
     * The <code>RowSetMetbDbtbImpl</code> object thbt is constructed when
     * b <code>ResultSet</code> object is pbssed to the <code>JdbcRowSet</code>
     * constructor. This helps in constructing bll metbdbtb bssocibted
     * with the <code>ResultSet</code> object using the setter methods of
     * <code>RowSetMetbDbtbImpl</code>.
     */
    privbte RowSetMetbDbtbImpl rowsMD;

    /**
     * The <code>ResultSetMetbDbtb</code> object from which this
     * <code>RowSetMetbDbtbImpl</code> is formed bnd which  helps in getting
     * the metbdbtb informbtion.
     */
    privbte ResultSetMetbDbtb resMD;


    /**
     * The Vector holding the Mbtch Columns
     */
    privbte Vector<Integer> iMbtchColumns;

    /**
     * The Vector thbt will hold the Mbtch Column nbmes.
     */
    privbte Vector<String> strMbtchColumns;


    protected trbnsient JdbcRowSetResourceBundle resBundle;

    /**
     * Constructs b defbult <code>JdbcRowSet</code> object.
     * The new instbnce of <code>JdbcRowSet</code> will serve bs b proxy
     * for the <code>ResultSet</code> object it crebtes, bnd by so doing,
     * it will mbke it possible to use the result set bs b JbvbBebns
     * component.
     * <P>
     * The following is true of b defbult <code>JdbcRowSet</code> instbnce:
     * <UL>
     *   <LI>Does not show deleted rows
     *   <LI>Hbs no time limit for how long b driver mby tbke to
     *       execute the rowset's commbnd
     *   <LI>Hbs no limit for the number of rows it mby contbin
     *   <LI>Hbs no limit for the number of bytes b column mby contbin
     *   <LI>Hbs b scrollbble cursor bnd does not show chbnges
     *       mbde by others
     *   <LI>Will not see uncommitted dbtb (mbke "dirty" rebds)
     *   <LI>Hbs escbpe processing turned on
     *   <LI>Hbs its connection's type mbp set to <code>null</code>
     *   <LI>Hbs bn empty <code>Hbshtbble</code> object for storing bny
     *       pbrbmeters thbt bre set
     * </UL>
     * A newly crebted <code>JdbcRowSet</code> object must hbve its
     * <code>execute</code> method invoked before other public methods
     * bre cblled on it; otherwise, such method cblls will cbuse bn
     * exception to be thrown.
     *
     * @throws SQLException [1] if bny of its public methods bre cblled prior
     * to cblling the <code>execute</code> method; [2] if invblid JDBC driver
     * properties bre set or [3] if no connection to b dbtb source exists.
     */
    public JdbcRowSetImpl() {
        conn = null;
        ps   = null;
        rs   = null;

        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }


        initPbrbms();

        // set the defbults

        try {
            setShowDeleted(fblse);
        } cbtch(SQLException sqle) {
             System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setshowdeleted").toString() +
                                sqle.getLocblizedMessbge());
        }

        try {
            setQueryTimeout(0);
        } cbtch(SQLException sqle) {
            System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setquerytimeout").toString() +
                                sqle.getLocblizedMessbge());
        }

        try {
            setMbxRows(0);
        } cbtch(SQLException sqle) {
            System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setmbxrows").toString() +
                                sqle.getLocblizedMessbge());
        }

        try {
            setMbxFieldSize(0);
        } cbtch(SQLException sqle) {
             System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setmbxfieldsize").toString() +
                                sqle.getLocblizedMessbge());
        }

        try {
            setEscbpeProcessing(true);
        } cbtch(SQLException sqle) {
             System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setescbpeprocessing").toString() +
                                sqle.getLocblizedMessbge());
        }

        try {
            setConcurrency(ResultSet.CONCUR_UPDATABLE);
        } cbtch (SQLException sqle) {
            System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setconcurrency").toString() +
                                sqle.getLocblizedMessbge());
        }

        setTypeMbp(null);

        try {
            setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        } cbtch(SQLException sqle){
          System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.settype").toString() +
                                sqle.getLocblizedMessbge());
        }

        setRebdOnly(true);

        try {
            setTrbnsbctionIsolbtion(Connection.TRANSACTION_READ_COMMITTED);
        } cbtch(SQLException sqle){
            System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.settrbnsbctionisolbtion").toString() +
                                sqle.getLocblizedMessbge());
        }

        //Instbntibting the vector for MbtchColumns

        iMbtchColumns = new Vector<Integer>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtchColumns.bdd(i,Integer.vblueOf(-1));
        }

        strMbtchColumns = new Vector<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtchColumns.bdd(j,null);
        }
    }

    /**
     * Constructs b defbult <code>JdbcRowSet</code> object given b
     * vblid <code>Connection</code> object. The new
     * instbnce of <code>JdbcRowSet</code> will serve bs b proxy for
     * the <code>ResultSet</code> object it crebtes, bnd by so doing,
     * it will mbke it possible to use the result set bs b JbvbBebns
     * component.
     * <P>
     * The following is true of b defbult <code>JdbcRowSet</code> instbnce:
     * <UL>
     *   <LI>Does not show deleted rows
     *   <LI>Hbs no time limit for how long b driver mby tbke to
     *       execute the rowset's commbnd
     *   <LI>Hbs no limit for the number of rows it mby contbin
     *   <LI>Hbs no limit for the number of bytes b column mby contbin
     *   <LI>Hbs b scrollbble cursor bnd does not show chbnges
     *       mbde by others
     *   <LI>Will not see uncommitted dbtb (mbke "dirty" rebds)
     *   <LI>Hbs escbpe processing turned on
     *   <LI>Hbs its connection's type mbp set to <code>null</code>
     *   <LI>Hbs bn empty <code>Hbshtbble</code> object for storing bny
     *       pbrbmeters thbt bre set
     * </UL>
     * A newly crebted <code>JdbcRowSet</code> object must hbve its
     * <code>execute</code> method invoked before other public methods
     * bre cblled on it; otherwise, such method cblls will cbuse bn
     * exception to be thrown.
     *
     * @throws SQLException [1] if bny of its public methods bre cblled prior
     * to cblling the <code>execute</code> method, [2] if invblid JDBC driver
     * properties bre set, or [3] if no connection to b dbtb source exists.
     */
    public JdbcRowSetImpl(Connection con) throws SQLException {

        conn = con;
        ps = null;
        rs = null;

        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }


        initPbrbms();
        // set the defbults
        setShowDeleted(fblse);
        setQueryTimeout(0);
        setMbxRows(0);
        setMbxFieldSize(0);

        setPbrbms();

        setRebdOnly(true);
        setTrbnsbctionIsolbtion(Connection.TRANSACTION_READ_COMMITTED);
        setEscbpeProcessing(true);
        setTypeMbp(null);

        //Instbntibting the vector for MbtchColumns

        iMbtchColumns = new Vector<Integer>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtchColumns.bdd(i,Integer.vblueOf(-1));
        }

        strMbtchColumns = new Vector<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtchColumns.bdd(j,null);
        }
    }

    /**
     * Constructs b defbult <code>JdbcRowSet</code> object using the
     * URL, usernbme, bnd pbssword brguments supplied. The new
     * instbnce of <code>JdbcRowSet</code> will serve bs b proxy for
     * the <code>ResultSet</code> object it crebtes, bnd by so doing,
     * it will mbke it possible to use the result set bs b JbvbBebns
     * component.
     *
     * <P>
     * The following is true of b defbult <code>JdbcRowSet</code> instbnce:
     * <UL>
     *   <LI>Does not show deleted rows
     *   <LI>Hbs no time limit for how long b driver mby tbke to
     *       execute the rowset's commbnd
     *   <LI>Hbs no limit for the number of rows it mby contbin
     *   <LI>Hbs no limit for the number of bytes b column mby contbin
     *   <LI>Hbs b scrollbble cursor bnd does not show chbnges
     *       mbde by others
     *   <LI>Will not see uncommitted dbtb (mbke "dirty" rebds)
     *   <LI>Hbs escbpe processing turned on
     *   <LI>Hbs its connection's type mbp set to <code>null</code>
     *   <LI>Hbs bn empty <code>Hbshtbble</code> object for storing bny
     *       pbrbmeters thbt bre set
     * </UL>
     *
     * @pbrbm url - b JDBC URL for the dbtbbbse to which this <code>JdbcRowSet</code>
     *        object will be connected. The form for b JDBC URL is
     *        <code>jdbc:subprotocol:subnbme</code>.
     * @pbrbm user - the dbtbbbse user on whose behblf the connection
     *        is being mbde
     * @pbrbm pbssword - the user's pbssword
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public JdbcRowSetImpl(String url, String user, String pbssword) throws SQLException {
        conn = null;
        ps = null;
        rs = null;

        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }


        initPbrbms();

        // Pbss the brguments to BbseRowSet
        // setter methods now.

        setUsernbme(user);
        setPbssword(pbssword);
        setUrl(url);

        // set the defbults
        setShowDeleted(fblse);
        setQueryTimeout(0);
        setMbxRows(0);
        setMbxFieldSize(0);

        setPbrbms();

        setRebdOnly(true);
        setTrbnsbctionIsolbtion(Connection.TRANSACTION_READ_COMMITTED);
        setEscbpeProcessing(true);
        setTypeMbp(null);

        //Instbntibting the vector for MbtchColumns

        iMbtchColumns = new Vector<Integer>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtchColumns.bdd(i,Integer.vblueOf(-1));
        }

        strMbtchColumns = new Vector<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtchColumns.bdd(j,null);
        }
    }


    /**
     * Constructs b <code>JdbcRowSet</code> object using the given vblid
     * <code>ResultSet</code> object. The new
     * instbnce of <code>JdbcRowSet</code> will serve bs b proxy for
     * the <code>ResultSet</code> object, bnd by so doing,
     * it will mbke it possible to use the result set bs b JbvbBebns
     * component.
     *
     * <P>
     * The following is true of b defbult <code>JdbcRowSet</code> instbnce:
     * <UL>
     *   <LI>Does not show deleted rows
     *   <LI>Hbs no time limit for how long b driver mby tbke to
     *       execute the rowset's commbnd
     *   <LI>Hbs no limit for the number of rows it mby contbin
     *   <LI>Hbs no limit for the number of bytes b column mby contbin
     *   <LI>Hbs b scrollbble cursor bnd does not show chbnges
     *       mbde by others
     *   <LI>Will not see uncommitted dbtb (mbke "dirty" rebds)
     *   <LI>Hbs escbpe processing turned on
     *   <LI>Hbs its connection's type mbp set to <code>null</code>
     *   <LI>Hbs bn empty <code>Hbshtbble</code> object for storing bny
     *       pbrbmeters thbt bre set
     * </UL>
     *
     * @pbrbm res b vblid <code>ResultSet</code> object
     *
     * @throws SQLException if b dbtbbbse bccess occurs due to b non
     * vblid ResultSet hbndle.
     */
    public JdbcRowSetImpl(ResultSet res) throws SQLException {

        // A ResultSet hbndle encbpsulbtes b connection hbndle.
        // But there is no wby we cbn retrieve b Connection hbndle
        // from b ResultSet object.
        // So to bvoid bny bnomblies we keep the conn = null
        // The pbssed rs hbndle will be b wrbpper bround for
        // "this" object's bll operbtions.
        conn = null;

        ps = null;

        rs = res;

        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }


        initPbrbms();

        // get the vblues from the resultset hbndle.
        setShowDeleted(fblse);
        setQueryTimeout(0);
        setMbxRows(0);
        setMbxFieldSize(0);

        setPbrbms();

        setRebdOnly(true);
        setTrbnsbctionIsolbtion(Connection.TRANSACTION_READ_COMMITTED);
        setEscbpeProcessing(true);
        setTypeMbp(null);

        // Get b hbndle to ResultSetMetbDbtb
        // Construct RowSetMetbDbtb out of it.

        resMD = rs.getMetbDbtb();

        rowsMD = new RowSetMetbDbtbImpl();

        initMetbDbtb(rowsMD, resMD);

        //Instbntibting the vector for MbtchColumns

        iMbtchColumns = new Vector<Integer>(10);
        for(int i = 0; i < 10 ; i++) {
           iMbtchColumns.bdd(i,Integer.vblueOf(-1));
        }

        strMbtchColumns = new Vector<String>(10);
        for(int j = 0; j < 10; j++) {
           strMbtchColumns.bdd(j,null);
        }
    }

    /**
     * Initiblizes the given <code>RowSetMetbDbtb</code> object with the vblues
     * in the given <code>ResultSetMetbDbtb</code> object.
     *
     * @pbrbm md the <code>RowSetMetbDbtb</code> object for this
     *           <code>JdbcRowSetImpl</code> object, which will be set with
     *           vblues from rsmd
     * @pbrbm rsmd the <code>ResultSetMetbDbtb</code> object from which new
     *             vblues for md will be rebd
     * @throws SQLException if bn error occurs
     */
    protected void initMetbDbtb(RowSetMetbDbtb md, ResultSetMetbDbtb rsmd) throws SQLException {
        int numCols = rsmd.getColumnCount();

        md.setColumnCount(numCols);
        for (int col=1; col <= numCols; col++) {
            md.setAutoIncrement(col, rsmd.isAutoIncrement(col));
            md.setCbseSensitive(col, rsmd.isCbseSensitive(col));
            md.setCurrency(col, rsmd.isCurrency(col));
            md.setNullbble(col, rsmd.isNullbble(col));
            md.setSigned(col, rsmd.isSigned(col));
            md.setSebrchbble(col, rsmd.isSebrchbble(col));
            md.setColumnDisplbySize(col, rsmd.getColumnDisplbySize(col));
            md.setColumnLbbel(col, rsmd.getColumnLbbel(col));
            md.setColumnNbme(col, rsmd.getColumnNbme(col));
            md.setSchembNbme(col, rsmd.getSchembNbme(col));
            md.setPrecision(col, rsmd.getPrecision(col));
            md.setScble(col, rsmd.getScble(col));
            md.setTbbleNbme(col, rsmd.getTbbleNbme(col));
            md.setCbtblogNbme(col, rsmd.getCbtblogNbme(col));
            md.setColumnType(col, rsmd.getColumnType(col));
            md.setColumnTypeNbme(col, rsmd.getColumnTypeNbme(col));
        }
    }


    protected void checkStbte() throws SQLException {

        // If bll the three i.e.  conn, ps & rs bre
        // simultbneously null implies we bre not connected
        // to the db, implies undesirbble stbte so throw exception

        if (conn == null && ps == null && rs == null ) {
            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.invblstbte").toString());
        }
    }

    //---------------------------------------------------------------------
    // Rebding bnd writing dbtb
    //---------------------------------------------------------------------

    /**
     * Crebtes the internbl <code>ResultSet</code> object for which this
     * <code>JdbcRowSet</code> object is b wrbpper, effectively
     * mbking the result set b JbvbBebns component.
     * <P>
     * Certbin properties must hbve been set before this method is cblled
     * so thbt it cbn estbblish b connection to b dbtbbbse bnd execute the
     * query thbt will crebte the result set.  If b <code>DbtbSource</code>
     * object will be used to crebte the connection, properties for the
     * dbtb source nbme, user nbme, bnd pbssword must be set.  If the
     * <code>DriverMbnbger</code> will be used, the properties for the
     * URL, user nbme, bnd pbssword must be set.  In either cbse, the
     * property for the commbnd must be set.  If the commbnd hbs plbceholder
     * pbrbmeters, those must blso be set. This method throws
     * bn exception if the required properties bre not set.
     * <P>
     * Other properties hbve defbult vblues thbt mby optionblly be set
     * to new vblues. The <code>execute</code> method will use the vblue
     * for the commbnd property to crebte b <code>PrepbredStbtement</code>
     * object bnd set its properties (escbpe processing, mbximum field
     * size, mbximum number of rows, bnd query timeout limit) to be those
     * of this rowset.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     * (2) bny required JDBC properties bre not set, or (3) if bn
     * invblid connection exists.
     */
    public void execute() throws SQLException {
        /*
         * To execute bbsed on the properties:
         * i) determine how to get b connection
         * ii) prepbre the stbtement
         * iii) set the properties of the stbtement
         * iv) pbrse the pbrbms. bnd set them
         * v) execute the stbtement
         *
         * During bll of this try to tolerbte bs mbny errors
         * bs possible, mbny drivers will not support bll of
         * the properties bnd will/should throw SQLException
         * bt us...
         *
         */

        prepbre();

        // set the properties of our shiny new stbtement
        setProperties(ps);


        // set the pbrbmeters
        decodePbrbms(getPbrbms(), ps);


        // execute the stbtement
        rs = ps.executeQuery();


        // notify listeners
        notifyRowSetChbnged();


    }

    protected void setProperties(PrepbredStbtement ps) throws SQLException {

        try {
            ps.setEscbpeProcessing(getEscbpeProcessing());
        } cbtch (SQLException ex) {
            System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setescbpeprocessing").toString() +
                                ex.getLocblizedMessbge());
        }

        try {
            ps.setMbxFieldSize(getMbxFieldSize());
        } cbtch (SQLException ex) {
            System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setmbxfieldsize").toString() +
                                ex.getLocblizedMessbge());
        }

        try {
            ps.setMbxRows(getMbxRows());
        } cbtch (SQLException ex) {
           System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setmbxrows").toString() +
                                ex.getLocblizedMessbge());
        }

        try {
            ps.setQueryTimeout(getQueryTimeout());
        } cbtch (SQLException ex) {
           System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.setquerytimeout").toString() +
                                ex.getLocblizedMessbge());
        }

    }

    privbte Connection connect() throws SQLException {

        // Get b JDBC connection.

        // First check for Connection hbndle object bs such if
        // "this" initiblized  using conn.

        if(conn != null) {
            return conn;

        } else if (getDbtbSourceNbme() != null) {

            // Connect using JNDI.
            try {
                Context ctx = new InitiblContext();
                DbtbSource ds = (DbtbSource)ctx.lookup
                    (getDbtbSourceNbme());
                //return ds.getConnection(getUsernbme(),getPbssword());

                if(getUsernbme() != null && !getUsernbme().equbls("")) {
                     return ds.getConnection(getUsernbme(),getPbssword());
                } else {
                     return ds.getConnection();
                }
            }
            cbtch (jbvbx.nbming.NbmingException ex) {
                throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.connect").toString());
            }

        } else if (getUrl() != null) {
            // Check only for getUrl() != null becbuse
            // user, pbsswd cbn be null
            // Connect using the driver mbnbger.

            return DriverMbnbger.getConnection
                    (getUrl(), getUsernbme(), getPbssword());
        }
        else {
            return null;
        }

    }


    protected PrepbredStbtement prepbre() throws SQLException {
        // get b connection
        conn = connect();

        try {

            Mbp<String, Clbss<?>> bMbp = getTypeMbp();
            if( bMbp != null) {
                conn.setTypeMbp(bMbp);
            }
            ps = conn.prepbreStbtement(getCommbnd(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } cbtch (SQLException ex) {
            System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.prepbre").toString() +
                                ex.getLocblizedMessbge());

            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();

            throw new SQLException(ex.getMessbge());
        }

        return ps;
    }

    @SuppressWbrnings("deprecbtion")
    privbte void decodePbrbms(Object[] pbrbms, PrepbredStbtement ps)
    throws SQLException {

    // There is b corresponding decodePbrbms in JdbcRowSetImpl
    // which does the sbme bs this method. This is b design flbw.
    // Updbte the CbchedRowsetRebder.decodePbrbms when you updbte
    // this method.

    // Adding the sbme comments to CbchedRowsetRebder.decodePbrbms.

        int brrbySize;
        Object[] pbrbm = null;

        for (int i=0; i < pbrbms.length; i++) {
            if (pbrbms[i] instbnceof Object[]) {
                pbrbm = (Object[])pbrbms[i];

                if (pbrbm.length == 2) {
                    if (pbrbm[0] == null) {
                        ps.setNull(i + 1, ((Integer)pbrbm[1]).intVblue());
                        continue;
                    }

                    if (pbrbm[0] instbnceof jbvb.sql.Dbte ||
                        pbrbm[0] instbnceof jbvb.sql.Time ||
                        pbrbm[0] instbnceof jbvb.sql.Timestbmp) {
                        System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.detecteddbte"));
                        if (pbrbm[1] instbnceof jbvb.util.Cblendbr) {
                            System.err.println(resBundle.hbndleGetObject("jdbcrowsetimpl.detectedcblendbr"));
                            ps.setDbte(i + 1, (jbvb.sql.Dbte)pbrbm[0],
                                       (jbvb.util.Cblendbr)pbrbm[1]);
                            continue;
                        }
                        else {
                            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.pbrbmtype").toString());
                        }
                    }

                    if (pbrbm[0] instbnceof Rebder) {
                        ps.setChbrbcterStrebm(i + 1, (Rebder)pbrbm[0],
                                              ((Integer)pbrbm[1]).intVblue());
                        continue;
                    }

                    /*
                     * Whbt's left should be setObject(int, Object, scble)
                     */
                    if (pbrbm[1] instbnceof Integer) {
                        ps.setObject(i + 1, pbrbm[0], ((Integer)pbrbm[1]).intVblue());
                        continue;
                    }

                } else if (pbrbm.length == 3) {

                    if (pbrbm[0] == null) {
                        ps.setNull(i + 1, ((Integer)pbrbm[1]).intVblue(),
                                   (String)pbrbm[2]);
                        continue;
                    }

                    if (pbrbm[0] instbnceof jbvb.io.InputStrebm) {
                        switch (((Integer)pbrbm[2]).intVblue()) {
                        cbse JdbcRowSetImpl.UNICODE_STREAM_PARAM:
                            ps.setUnicodeStrebm(i + 1,
                                                (jbvb.io.InputStrebm)pbrbm[0],
                                                ((Integer)pbrbm[1]).intVblue());
                            brebk;
                        cbse JdbcRowSetImpl.BINARY_STREAM_PARAM:
                            ps.setBinbryStrebm(i + 1,
                                               (jbvb.io.InputStrebm)pbrbm[0],
                                               ((Integer)pbrbm[1]).intVblue());
                            brebk;
                        cbse JdbcRowSetImpl.ASCII_STREAM_PARAM:
                            ps.setAsciiStrebm(i + 1,
                                              (jbvb.io.InputStrebm)pbrbm[0],
                                              ((Integer)pbrbm[1]).intVblue());
                            brebk;
                        defbult:
                            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.pbrbmtype").toString());
                        }
                    }

                    /*
                     * no point bt looking bt the first element now;
                     * whbt's left must be the setObject() cbses.
                     */
                    if (pbrbm[1] instbnceof Integer && pbrbm[2] instbnceof Integer) {
                        ps.setObject(i + 1, pbrbm[0], ((Integer)pbrbm[1]).intVblue(),
                                     ((Integer)pbrbm[2]).intVblue());
                        continue;
                    }

                    throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.pbrbmtype").toString());

                } else {
                    // common cbse - this cbtches bll SQL92 types
                    ps.setObject(i + 1, pbrbms[i]);
                    continue;
                }
            }  else {
               // Try to get bll the pbrbms to be set here
               ps.setObject(i + 1, pbrbms[i]);

            }
        }
    }

    /**
     * Moves the cursor for this rowset's <code>ResultSet</code>
     * object down one row from its current position.
     * A <code>ResultSet</code> cursor is initiblly positioned
     * before the first row; the first cbll to the method
     * <code>next</code> mbkes the first row the current row; the
     * second cbll mbkes the second row the current row, bnd so on.
     *
     * <P>If bn input strebm is open for the current row, b cbll
     * to the method <code>next</code> will
     * implicitly close it. A <code>ResultSet</code> object's
     * wbrning chbin is clebred when b new row is rebd.
     *
     * @return <code>true</code> if the new current row is vblid;
     *         <code>fblse</code> if there bre no more rows
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public boolebn next() throws SQLException {
        checkStbte();

        boolebn b = rs.next();
        notifyCursorMoved();
        return b;
    }

    /**
     * Relebses this rowset's <code>ResultSet</code> object's dbtbbbse bnd
     * JDBC resources immedibtely instebd of wbiting for
     * this to hbppen when it is butombticblly closed.
     *
     * <P><B>Note:</B> A <code>ResultSet</code> object
     * is butombticblly closed by the
     * <code>Stbtement</code> object thbt generbted it when
     * thbt <code>Stbtement</code> object is closed,
     * re-executed, or is used to retrieve the next result from b
     * sequence of multiple results. A <code>ResultSet</code> object
     * is blso butombticblly closed when it is gbrbbge collected.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public void close() throws SQLException {
        if (rs != null)
            rs.close();
        if (ps != null)
            ps.close();
        if (conn != null)
            conn.close();
    }

    /**
     * Reports whether the lbst column rebd from this rowset's
     * <code>ResultSet</code> object hbd b vblue of SQL <code>NULL</code>.
     * Note thbt you must first cbll one of the <code>getXXX</code> methods
     * on b column to try to rebd its vblue bnd then cbll
     * the method <code>wbsNull</code> to see if the vblue rebd wbs
     * SQL <code>NULL</code>.
     *
     * @return <code>true</code> if the lbst column vblue rebd wbs SQL
     *         <code>NULL</code> bnd <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public boolebn wbsNull() throws SQLException {
        checkStbte();

        return rs.wbsNull();
    }

    //======================================================================
    // Methods for bccessing results by column index
    //======================================================================

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>String</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public String getString(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getString(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>boolebn</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>fblse</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public boolebn getBoolebn(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getBoolebn(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>byte</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public byte getByte(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getByte(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>short</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public short getShort(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getShort(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * bn <code>int</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public int getInt(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getInt(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>long</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public long getLong(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getLong(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>flobt</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public flobt getFlobt(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getFlobt(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>double</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public double getDouble(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getDouble(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>jbvb.sql.BigDecimbl</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm scble the number of digits to the right of the decimbl point
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     * @deprecbted
     */
    @Deprecbted
    public BigDecimbl getBigDecimbl(int columnIndex, int scble) throws SQLException {
        checkStbte();

        return rs.getBigDecimbl(columnIndex, scble);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>byte</code> brrby in the Jbvb progrbmming lbngubge.
     * The bytes represent the rbw vblues returned by the driver.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public byte[] getBytes(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getBytes(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>jbvb.sql.Dbte</code> object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Dbte getDbte(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getDbte(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>jbvb.sql.Time</code> object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Time getTime(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getTime(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>jbvb.sql.Timestbmp</code> object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Timestbmp getTimestbmp(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getTimestbmp(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b strebm of ASCII chbrbcters. The vblue cbn then be rebd in chunks from the
     * strebm. This method is pbrticulbrly
     * suitbble for retrieving lbrge <code>LONGVARCHAR</code> vblues.
     * The JDBC driver will
     * do bny necessbry conversion from the dbtbbbse formbt into ASCII.
     *
     * <P><B>Note:</B> All the dbtb in the returned strebm must be
     * rebd prior to getting the vblue of bny other column. The next
     * cbll to b <code>getXXX</code> method implicitly closes the strebm.  Also, b
     * strebm mby return <code>0</code> when the method
     * <code>InputStrebm.bvbilbble</code>
     * is cblled whether there is dbtb bvbilbble or not.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return b Jbvb input strebm thbt delivers the dbtbbbse column vblue
     * bs b strebm of one-byte ASCII chbrbcters;
     * if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) dbtbbbse bccess error occurs
     *            (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.io.InputStrebm getAsciiStrebm(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getAsciiStrebm(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * bs b strebm of Unicode chbrbcters.
     * The vblue cbn then be rebd in chunks from the
     * strebm. This method is pbrticulbrly
     * suitbble for retrieving lbrge<code>LONGVARCHAR</code>vblues.  The JDBC driver will
     * do bny necessbry conversion from the dbtbbbse formbt into Unicode.
     * The byte formbt of the Unicode strebm must be Jbvb UTF-8,
     * bs specified in the Jbvb virtubl mbchine specificbtion.
     *
     * <P><B>Note:</B> All the dbtb in the returned strebm must be
     * rebd prior to getting the vblue of bny other column. The next
     * cbll to b <code>getXXX</code> method implicitly closes the strebm.  Also, b
     * strebm mby return <code>0</code> when the method
     * <code>InputStrebm.bvbilbble</code>
     * is cblled whether there is dbtb bvbilbble or not.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return b Jbvb input strebm thbt delivers the dbtbbbse column vblue
     * bs b strebm in Jbvb UTF-8 byte formbt;
     * if the vblue is SQL <code>NULL</code>, the vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     * @deprecbted use <code>getChbrbcterStrebm</code> in plbce of
     *              <code>getUnicodeStrebm</code>
     */
    @Deprecbted
    public jbvb.io.InputStrebm getUnicodeStrebm(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getUnicodeStrebm(columnIndex);
    }

    /**
     * Gets the vblue of b column in the current row bs b strebm of
     * the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b binbry strebm of
     * uninterpreted bytes. The vblue cbn then be rebd in chunks from the
     * strebm. This method is pbrticulbrly
     * suitbble for retrieving lbrge <code>LONGVARBINARY</code> vblues.
     *
     * <P><B>Note:</B> All the dbtb in the returned strebm must be
     * rebd prior to getting the vblue of bny other column. The next
     * cbll to b <code>getXXX</code> method implicitly closes the strebm.  Also, b
     * strebm mby return <code>0</code> when the method
     * <code>InputStrebm.bvbilbble</code>
     * is cblled whether there is dbtb bvbilbble or not.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return b Jbvb input strebm thbt delivers the dbtbbbse column vblue
     * bs b strebm of uninterpreted bytes;
     * if the vblue is SQL <code>NULL</code>, the vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.io.InputStrebm getBinbryStrebm(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getBinbryStrebm(columnIndex);
    }


    //======================================================================
    // Methods for bccessing results by column nbme
    //======================================================================

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>String</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public String getString(String columnNbme) throws SQLException {
        return getString(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>boolebn</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>fblse</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public boolebn getBoolebn(String columnNbme) throws SQLException {
        return getBoolebn(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>byte</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public byte getByte(String columnNbme) throws SQLException {
        return getByte(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>short</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public short getShort(String columnNbme) throws SQLException {
        return getShort(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * bn <code>int</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public int getInt(String columnNbme) throws SQLException {
        return getInt(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>long</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public long getLong(String columnNbme) throws SQLException {
        return getLong(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>flobt</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public flobt getFlobt(String columnNbme) throws SQLException {
        return getFlobt(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>double</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>0</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public double getDouble(String columnNbme) throws SQLException {
        return getDouble(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>jbvb.mbth.BigDecimbl</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @pbrbm scble the number of digits to the right of the decimbl point
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) bdbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     * @deprecbted
     */
    @Deprecbted
    public BigDecimbl getBigDecimbl(String columnNbme, int scble) throws SQLException {
        return getBigDecimbl(findColumn(columnNbme), scble);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>byte</code> brrby in the Jbvb progrbmming lbngubge.
     * The bytes represent the rbw vblues returned by the driver.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public byte[] getBytes(String columnNbme) throws SQLException {
        return getBytes(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>jbvb.sql.Dbte</code> object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Dbte getDbte(String columnNbme) throws SQLException {
        return getDbte(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>jbvb.sql.Time</code> object in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue;
     * if the vblue is SQL <code>NULL</code>,
     * the vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Time getTime(String columnNbme) throws SQLException {
        return getTime(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * b <code>jbvb.sql.Timestbmp</code> object.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Timestbmp getTimestbmp(String columnNbme) throws SQLException {
        return getTimestbmp(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b strebm of
     * ASCII chbrbcters. The vblue cbn then be rebd in chunks from the
     * strebm. This method is pbrticulbrly
     * suitbble for retrieving lbrge <code>LONGVARCHAR</code> vblues.
     * The JDBC driver will
     * do bny necessbry conversion from the dbtbbbse formbt into ASCII.
     *
     * <P><B>Note:</B> All the dbtb in the returned strebm must be
     * rebd prior to getting the vblue of bny other column. The next
     * cbll to b <code>getXXX</code> method implicitly closes the strebm. Also, b
     * strebm mby return <code>0</code> when the method <code>bvbilbble</code>
     * is cblled whether there is dbtb bvbilbble or not.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return b Jbvb input strebm thbt delivers the dbtbbbse column vblue
     * bs b strebm of one-byte ASCII chbrbcters.
     * If the vblue is SQL <code>NULL</code>,
     * the vblue returned is <code>null</code>.
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.io.InputStrebm getAsciiStrebm(String columnNbme) throws SQLException {
        return getAsciiStrebm(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b strebm of
     * Unicode chbrbcters. The vblue cbn then be rebd in chunks from the
     * strebm. This method is pbrticulbrly
     * suitbble for retrieving lbrge <code>LONGVARCHAR</code> vblues.
     * The JDBC driver will
     * do bny necessbry conversion from the dbtbbbse formbt into Unicode.
     * The byte formbt of the Unicode strebm must be Jbvb UTF-8,
     * bs defined in the Jbvb virtubl mbchine specificbtion.
     *
     * <P><B>Note:</B> All the dbtb in the returned strebm must be
     * rebd prior to getting the vblue of bny other column. The next
     * cbll to b <code>getXXX</code> method implicitly closes the strebm. Also, b
     * strebm mby return <code>0</code> when the method <code>bvbilbble</code>
     * is cblled whether there is dbtb bvbilbble or not.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return b Jbvb input strebm thbt delivers the dbtbbbse column vblue
     * bs b strebm of two-byte Unicode chbrbcters.
     * If the vblue is SQL <code>NULL</code>,
     * the vblue returned is <code>null</code>.
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     * @deprecbted
     */
    @Deprecbted
    public jbvb.io.InputStrebm getUnicodeStrebm(String columnNbme) throws SQLException {
        return getUnicodeStrebm(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b strebm of uninterpreted
     * <code>byte</code>s.
     * The vblue cbn then be rebd in chunks from the
     * strebm. This method is pbrticulbrly
     * suitbble for retrieving lbrge <code>LONGVARBINARY</code>
     * vblues.
     *
     * <P><B>Note:</B> All the dbtb in the returned strebm must be
     * rebd prior to getting the vblue of bny other column. The next
     * cbll to b <code>getXXX</code> method implicitly closes the strebm. Also, b
     * strebm mby return <code>0</code> when the method <code>bvbilbble</code>
     * is cblled whether there is dbtb bvbilbble or not.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return b Jbvb input strebm thbt delivers the dbtbbbse column vblue
     * bs b strebm of uninterpreted bytes;
     * if the vblue is SQL <code>NULL</code>, the result is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.io.InputStrebm getBinbryStrebm(String columnNbme) throws SQLException {
        return getBinbryStrebm(findColumn(columnNbme));
    }


    //=====================================================================
    // Advbnced febtures:
    //=====================================================================

    /**
     * Returns the first wbrning reported by cblls on this rowset's
     * <code>ResultSet</code> object.
     * Subsequent wbrnings on this rowset's <code>ResultSet</code> object
     * will be chbined to the <code>SQLWbrning</code> object thbt
     * this method returns.
     *
     * <P>The wbrning chbin is butombticblly clebred ebch time b new
     * row is rebd.
     *
     * <P><B>Note:</B> This wbrning chbin only covers wbrnings cbused
     * by <code>ResultSet</code> methods.  Any wbrning cbused by
     * <code>Stbtement</code> methods
     * (such bs rebding OUT pbrbmeters) will be chbined on the
     * <code>Stbtement</code> object.
     *
     * @return the first <code>SQLWbrning</code> object reported or <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public SQLWbrning getWbrnings() throws SQLException {
        checkStbte();

        return rs.getWbrnings();
    }

    /**
     * Clebrs bll wbrnings reported on this rowset's <code>ResultSet</code> object.
     * After this method is cblled, the method <code>getWbrnings</code>
     * returns <code>null</code> until b new wbrning is
     * reported for this rowset's <code>ResultSet</code> object.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public void clebrWbrnings() throws SQLException {
        checkStbte();

        rs.clebrWbrnings();
    }

    /**
     * Gets the nbme of the SQL cursor used by this rowset's <code>ResultSet</code>
     * object.
     *
     * <P>In SQL, b result tbble is retrieved through b cursor thbt is
     * nbmed. The current row of b result set cbn be updbted or deleted
     * using b positioned updbte/delete stbtement thbt references the
     * cursor nbme. To insure thbt the cursor hbs the proper isolbtion
     * level to support updbte, the cursor's <code>select</code> stbtement should be
     * of the form 'select for updbte'. If the 'for updbte' clbuse is
     * omitted, the positioned updbtes mby fbil.
     *
     * <P>The JDBC API supports this SQL febture by providing the nbme of the
     * SQL cursor used by b <code>ResultSet</code> object.
     * The current row of b <code>ResultSet</code> object
     * is blso the current row of this SQL cursor.
     *
     * <P><B>Note:</B> If positioned updbte is not supported, b
     * <code>SQLException</code> is thrown.
     *
     * @return the SQL nbme for this rowset's <code>ResultSet</code> object's cursor
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) xthis rowset does not hbve b currently vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public String getCursorNbme() throws SQLException {
        checkStbte();

        return rs.getCursorNbme();
    }

    /**
     * Retrieves the  number, types bnd properties of
     * this rowset's <code>ResultSet</code> object's columns.
     *
     * @return the description of this rowset's <code>ResultSet</code>
     *     object's columns
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *     or (2) this rowset does not hbve b currently vblid connection,
     *     prepbred stbtement, bnd result set
     */
    public ResultSetMetbDbtb getMetbDbtb() throws SQLException {

        checkStbte();

        // It mby be the cbse thbt JdbcRowSet might not hbve been
        // initiblized with ResultSet hbndle bnd mby be by PrepbredStbtement
        // internblly when we set JdbcRowSet.setCommbnd().
        // We mby require bll the bbsic properties of setEscbpeProcessing
        // setMbxFieldSize etc. which bn bpplicbtion cbn use before we cbll
        // execute.
        try {
             checkStbte();
        } cbtch(SQLException sqle) {
             prepbre();
             // will return ResultSetMetbDbtb
             return ps.getMetbDbtb();
        }
        return rs.getMetbDbtb();
    }

    /**
     * <p>Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * bn <code>Object</code>.
     *
     * <p>This method will return the vblue of the given column bs b
     * Jbvb object.  The type of the Jbvb object will be the defbult
     * Jbvb object type corresponding to the column's SQL type,
     * following the mbpping for built-in types specified in the JDBC
     * specificbtion.
     *
     * <p>This method mby blso be used to rebd dbtbtbbbse-specific
     * bbstrbct dbtb types.
     *
     * In the JDBC 3.0 API, the behbvior of method
     * <code>getObject</code> is extended to mbteriblize
     * dbtb of SQL user-defined types.  When b column contbins
     * b structured or distinct vblue, the behbvior of this method is bs
     * if it were b cbll to: <code>getObject(columnIndex,
     * this.getStbtement().getConnection().getTypeMbp())</code>.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return b <code>jbvb.lbng.Object</code> holding the column vblue
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Object getObject(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getObject(columnIndex);
    }

    /**
     * <p>Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs
     * bn <code>Object</code>.
     *
     * <p>This method will return the vblue of the given column bs b
     * Jbvb object.  The type of the Jbvb object will be the defbult
     * Jbvb object type corresponding to the column's SQL type,
     * following the mbpping for built-in types specified in the JDBC
     * specificbtion.
     *
     * <p>This method mby blso be used to rebd dbtbtbbbse-specific
     * bbstrbct dbtb types.
     *
     * In the JDBC 3.0 API, the behbvior of the method
     * <code>getObject</code> is extended to mbteriblize
     * dbtb of SQL user-defined types.  When b column contbins
     * b structured or distinct vblue, the behbvior of this method is bs
     * if it were b cbll to: <code>getObject(columnIndex,
     * this.getStbtement().getConnection().getTypeMbp())</code>.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return b <code>jbvb.lbng.Object</code> holding the column vblue
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Object getObject(String columnNbme) throws SQLException {
        return getObject(findColumn(columnNbme));
    }

    //----------------------------------------------------------------

    /**
     * Mbps the given <code>JdbcRowSetImpl</code> column nbme to its
     * <code>JdbcRowSetImpl</code> column index bnd reflects this on
     * the internbl <code>ResultSet</code> object.
     *
     * @pbrbm columnNbme the nbme of the column
     * @return the column index of the given column nbme
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     * (2) this rowset does not hbve b currently vblid connection,
     * prepbred stbtement, bnd result set
     */
    public int findColumn(String columnNbme) throws SQLException {
        checkStbte();

        return rs.findColumn(columnNbme);
    }


    //--------------------------JDBC 2.0-----------------------------------

    //---------------------------------------------------------------------
    // Getters bnd Setters
    //---------------------------------------------------------------------

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b
     * <code>jbvb.io.Rebder</code> object.
     * @return b <code>jbvb.io.Rebder</code> object thbt contbins the column
     * vblue; if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code>.
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     *
     */
    public jbvb.io.Rebder getChbrbcterStrebm(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getChbrbcterStrebm(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b
     * <code>jbvb.io.Rebder</code> object.
     *
     * @return b <code>jbvb.io.Rebder</code> object thbt contbins the column
     * vblue; if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code>.
     * @pbrbm columnNbme the nbme of the column
     * @return the vblue in the specified column bs b <code>jbvb.io.Rebder</code>
     *
     */
    public jbvb.io.Rebder getChbrbcterStrebm(String columnNbme) throws SQLException {
        return getChbrbcterStrebm(findColumn(columnNbme));
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b
     * <code>jbvb.mbth.BigDecimbl</code> with full precision.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @return the column vblue (full precision);
     * if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code>.
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public BigDecimbl getBigDecimbl(int columnIndex) throws SQLException {
        checkStbte();

        return rs.getBigDecimbl(columnIndex);
    }

    /**
     * Gets the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b
     * <code>jbvb.mbth.BigDecimbl</code> with full precision.
     *
     * @pbrbm columnNbme the column nbme
     * @return the column vblue (full precision);
     * if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code>.
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public BigDecimbl getBigDecimbl(String columnNbme) throws SQLException {
        return getBigDecimbl(findColumn(columnNbme));
    }

    //---------------------------------------------------------------------
    // Trbversbl/Positioning
    //---------------------------------------------------------------------

    /**
     * Indicbtes whether the cursor is before the first row in
     * this rowset's <code>ResultSet</code> object.
     *
     * @return <code>true</code> if the cursor is before the first row;
     * <code>fblse</code> if the cursor is bt bny other position or the
     * result set contbins no rows
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public boolebn isBeforeFirst() throws SQLException {
        checkStbte();

        return rs.isBeforeFirst();
    }

    /**
     * Indicbtes whether the cursor is bfter the lbst row in
     * this rowset's <code>ResultSet</code> object.
     *
     * @return <code>true</code> if the cursor is bfter the lbst row;
     * <code>fblse</code> if the cursor is bt bny other position or the
     * result set contbins no rows
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public boolebn isAfterLbst() throws SQLException {
        checkStbte();

        return rs.isAfterLbst();
    }

    /**
     * Indicbtes whether the cursor is on the first row of
     * this rowset's <code>ResultSet</code> object.
     *
     * @return <code>true</code> if the cursor is on the first row;
     * <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public boolebn isFirst() throws SQLException {
        checkStbte();

        return rs.isFirst();
    }

    /**
     * Indicbtes whether the cursor is on the lbst row of
     * this rowset's <code>ResultSet</code> object.
     * Note: Cblling the method <code>isLbst</code> mby be expensive
     * becbuse the JDBC driver
     * might need to fetch bhebd one row in order to determine
     * whether the current row is the lbst row in the result set.
     *
     * @return <code>true</code> if the cursor is on the lbst row;
     * <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     *
     */
    public boolebn isLbst() throws SQLException {
        checkStbte();

        return rs.isLbst();
    }

    /**
     * Moves the cursor to the front of
     * this rowset's <code>ResultSet</code> object, just before the
     * first row. This method hbs no effect if the result set contbins no rows.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) the result set type is <code>TYPE_FORWARD_ONLY</code>,
     *            or (3) this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public void beforeFirst() throws SQLException {
        checkStbte();

        rs.beforeFirst();
        notifyCursorMoved();
    }

    /**
     * Moves the cursor to the end of
     * this rowset's <code>ResultSet</code> object, just bfter the
     * lbst row. This method hbs no effect if the result set contbins no rows.
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) the result set type is <code>TYPE_FORWARD_ONLY</code>,
     *            or (3) this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public void bfterLbst() throws SQLException {
        checkStbte();

        rs.bfterLbst();
        notifyCursorMoved();
    }

    /**
     * Moves the cursor to the first row in
     * this rowset's <code>ResultSet</code> object.
     *
     * @return <code>true</code> if the cursor is on b vblid row;
     * <code>fblse</code> if there bre no rows in the result set
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) the result set type is <code>TYPE_FORWARD_ONLY</code>,
     *            or (3) this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public boolebn first() throws SQLException {
        checkStbte();

        boolebn b = rs.first();
        notifyCursorMoved();
        return b;

    }

    /**
     * Moves the cursor to the lbst row in
     * this rowset's <code>ResultSet</code> object.
     *
     * @return <code>true</code> if the cursor is on b vblid row;
     * <code>fblse</code> if there bre no rows in the result set
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) the result set type is <code>TYPE_FORWARD_ONLY</code>,
     *            or (3) this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public boolebn lbst() throws SQLException {
        checkStbte();

        boolebn b = rs.lbst();
        notifyCursorMoved();
        return b;
    }

    /**
     * Retrieves the current row number.  The first row is number 1, the
     * second is number 2, bnd so on.
     *
     * @return the current row number; <code>0</code> if there is no current row
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public int getRow() throws SQLException {
        checkStbte();

        return rs.getRow();
    }

    /**
     * Moves the cursor to the given row number in
     * this rowset's internbl <code>ResultSet</code> object.
     *
     * <p>If the row number is positive, the cursor moves to
     * the given row number with respect to the
     * beginning of the result set.  The first row is row 1, the second
     * is row 2, bnd so on.
     *
     * <p>If the given row number is negbtive, the cursor moves to
     * bn bbsolute row position with respect to
     * the end of the result set.  For exbmple, cblling the method
     * <code>bbsolute(-1)</code> positions the
     * cursor on the lbst row, cblling the method <code>bbsolute(-2)</code>
     * moves the cursor to the next-to-lbst row, bnd so on.
     *
     * <p>An bttempt to position the cursor beyond the first/lbst row in
     * the result set lebves the cursor before the first row or bfter
     * the lbst row.
     *
     * <p><B>Note:</B> Cblling <code>bbsolute(1)</code> is the sbme
     * bs cblling <code>first()</code>. Cblling <code>bbsolute(-1)</code>
     * is the sbme bs cblling <code>lbst()</code>.
     *
     * @return <code>true</code> if the cursor is on the result set;
     * <code>fblse</code> otherwise
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) the row is <code>0</code>, (3) the result set
     *            type is <code>TYPE_FORWARD_ONLY</code>, or (4) this
     *            rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public boolebn bbsolute(int row) throws SQLException {
        checkStbte();

        boolebn b = rs.bbsolute(row);
        notifyCursorMoved();
        return b;
    }

    /**
     * Moves the cursor b relbtive number of rows, either positive or negbtive.
     * Attempting to move beyond the first/lbst row in the
     * result set positions the cursor before/bfter the
     * the first/lbst row. Cblling <code>relbtive(0)</code> is vblid, but does
     * not chbnge the cursor position.
     *
     * <p>Note: Cblling the method <code>relbtive(1)</code>
     * is different from cblling the method <code>next()</code>
     * becbuse is mbkes sense to cbll <code>next()</code> when there
     * is no current row,
     * for exbmple, when the cursor is positioned before the first row
     * or bfter the lbst row of the result set.
     *
     * @return <code>true</code> if the cursor is on b row;
     * <code>fblse</code> otherwise
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) there is no current row, (3) the result set
     *            type is <code>TYPE_FORWARD_ONLY</code>, or (4) this
     *            rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public boolebn relbtive(int rows) throws SQLException {
        checkStbte();

        boolebn b = rs.relbtive(rows);
        notifyCursorMoved();
        return b;
    }

    /**
     * Moves the cursor to the previous row in this
     * <code>ResultSet</code> object.
     *
     * <p><B>Note:</B> Cblling the method <code>previous()</code> is not the sbme bs
     * cblling the method <code>relbtive(-1)</code> becbuse it
     * mbkes sense to cbll <code>previous()</code> when there is no current row.
     *
     * @return <code>true</code> if the cursor is on b vblid row;
     * <code>fblse</code> if it is off the result set
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) the result set type is <code>TYPE_FORWARD_ONLY</code>,
     *            or (3) this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     */
    public boolebn previous() throws SQLException {
        checkStbte();

        boolebn b = rs.previous();
        notifyCursorMoved();
        return b;
    }

    /**
     * Gives b hint bs to the direction in which the rows in this
     * <code>ResultSet</code> object will be processed.
     * The initibl vblue is determined by the
     * <code>Stbtement</code> object
     * thbt produced this rowset's <code>ResultSet</code> object.
     * The fetch direction mby be chbnged bt bny time.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) the result set type is <code>TYPE_FORWARD_ONLY</code>
     *            bnd the fetch direction is not <code>FETCH_FORWARD</code>,
     *            or (3) this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     * @see jbvb.sql.Stbtement#setFetchDirection
     */
    public void setFetchDirection(int direction) throws SQLException {
        checkStbte();

        rs.setFetchDirection(direction);
    }

    /**
     * Returns the fetch direction for this
     * <code>ResultSet</code> object.
     *
     * @return the current fetch direction for this rowset's
     *         <code>ResultSet</code> object
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public int getFetchDirection() throws SQLException {
        try {
             checkStbte();
        } cbtch(SQLException sqle) {
             super.getFetchDirection();
        }
        return rs.getFetchDirection();
    }

    /**
     * Gives the JDBC driver b hint bs to the number of rows thbt should
     * be fetched from the dbtbbbse when more rows bre needed for this
     * <code>ResultSet</code> object.
     * If the fetch size specified is zero, the JDBC driver
     * ignores the vblue bnd is free to mbke its own best guess bs to whbt
     * the fetch size should be.  The defbult vblue is set by the
     * <code>Stbtement</code> object
     * thbt crebted the result set.  The fetch size mby be chbnged bt bny time.
     *
     * @pbrbm rows the number of rows to fetch
     * @throws SQLException if (1) b dbtbbbse bccess error occurs, (2) the
     *            condition <code>0 <= rows <= this.getMbxRows()</code> is not
     *            sbtisfied, or (3) this rowset does not currently hbve b vblid
     *            connection, prepbred stbtement, bnd result set
     *
     */
    public void setFetchSize(int rows) throws SQLException {
        checkStbte();

        rs.setFetchSize(rows);
    }

    /**
     *
     * Returns the fetch size for this
     * <code>ResultSet</code> object.
     *
     * @return the current fetch size for this rowset's <code>ResultSet</code> object
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public int getType() throws SQLException {
        try {
             checkStbte();
        } cbtch(SQLException sqle) {
            return super.getType();
        }

        // If the ResultSet hbs not been crebted, then return the defbult type
        // otherwise return the type from the ResultSet.
        if(rs == null) {
            return super.getType();
        } else {
           int rstype = rs.getType();
            return rstype;
        }


    }

    /**
     * Returns the concurrency mode of this rowset's <code>ResultSet</code> object.
     * The concurrency used is determined by the
     * <code>Stbtement</code> object thbt crebted the result set.
     *
     * @return the concurrency type, either <code>CONCUR_READ_ONLY</code>
     * or <code>CONCUR_UPDATABLE</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public int getConcurrency() throws SQLException {
        try {
             checkStbte();
        } cbtch(SQLException sqle) {
             super.getConcurrency();
        }
        return rs.getConcurrency();
    }

    //---------------------------------------------------------------------
    // Updbtes
    //---------------------------------------------------------------------

    /**
     * Indicbtes whether the current row hbs been updbted.  The vblue returned
     * depends on whether or not the result set cbn detect updbtes.
     *
     * @return <code>true</code> if the row hbs been visibly updbted
     * by the owner or bnother, bnd updbtes bre detected
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     * @see jbvb.sql.DbtbbbseMetbDbtb#updbtesAreDetected
     */
    public boolebn rowUpdbted() throws SQLException {
        checkStbte();

        return rs.rowUpdbted();
    }

    /**
     * Indicbtes whether the current row hbs hbd bn insertion.
     * The vblue returned depends on whether or not this
     * <code>ResultSet</code> object cbn detect visible inserts.
     *
     * @return <code>true</code> if b row hbs hbd bn insertion
     * bnd insertions bre detected; <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     * @see jbvb.sql.DbtbbbseMetbDbtb#insertsAreDetected
     *
     */
    public boolebn rowInserted() throws SQLException {
        checkStbte();

        return rs.rowInserted();
    }

    /**
     * Indicbtes whether b row hbs been deleted.  A deleted row mby lebve
     * b visible "hole" in b result set.  This method cbn be used to
     * detect holes in b result set.  The vblue returned depends on whether
     * or not this rowset's <code>ResultSet</code> object cbn detect deletions.
     *
     * @return <code>true</code> if b row wbs deleted bnd deletions bre detected;
     * <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     * @see jbvb.sql.DbtbbbseMetbDbtb#deletesAreDetected
     */
    public boolebn rowDeleted() throws SQLException {
        checkStbte();

        return rs.rowDeleted();
    }

    /**
     * Gives b nullbble column b null vblue.
     *
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code>
     * or <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public void updbteNull(int columnIndex) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteNull(columnIndex);
    }

    /**
     * Updbtes the designbted column with b <code>boolebn</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteBoolebn(int columnIndex, boolebn x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteBoolebn(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>byte</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteByte(int columnIndex, byte x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteByte(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>short</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteShort(int columnIndex, short x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteShort(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with bn <code>int</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public void updbteInt(int columnIndex, int x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteInt(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>long</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteLong(int columnIndex, long x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteLong(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>flobt</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteFlobt(int columnIndex, flobt x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteFlobt(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>double</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteDouble(int columnIndex, double x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteDouble(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>jbvb.mbth.BigDecimbl</code>
     * vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteBigDecimbl(int columnIndex, BigDecimbl x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteBigDecimbl(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>String</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteString(int columnIndex, String x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteString(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>byte</code> brrby vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteBytes(int columnIndex, byte x[]) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteBytes(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>jbvb.sql.Dbte</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteDbte(int columnIndex, jbvb.sql.Dbte x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteDbte(columnIndex, x);
    }


    /**
     * Updbtes the designbted column with b <code>jbvb.sql.Time</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteTime(int columnIndex, jbvb.sql.Time x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteTime(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>jbvb.sql.Timestbmp</code>
     * vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteTimestbmp(int columnIndex, jbvb.sql.Timestbmp x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteTimestbmp(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with bn bscii strebm vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            (2) or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteAsciiStrebm(int columnIndex, jbvb.io.InputStrebm x, int length) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteAsciiStrebm(columnIndex, x, length);
    }

    /**
     * Updbtes the designbted column with b binbry strebm vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteBinbryStrebm(int columnIndex, jbvb.io.InputStrebm x, int length) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteBinbryStrebm(columnIndex, x, length);
    }

    /**
     * Updbtes the designbted column with b chbrbcter strebm vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteChbrbcterStrebm(int columnIndex, jbvb.io.Rebder x, int length) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteChbrbcterStrebm(columnIndex, x, length);
    }

    /**
     * Updbtes the designbted column with bn <code>Object</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @pbrbm scble for <code>jbvb.sql.Types.DECIMAl</code>
     *  or <code>jbvb.sql.Types.NUMERIC</code> types,
     *  this is the number of digits bfter the decimbl point.  For bll other
     *  types this vblue will be ignored.
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteObject(int columnIndex, Object x, int scble) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteObject(columnIndex, x, scble);
    }

    /**
     * Updbtes the designbted column with bn <code>Object</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteObject(int columnIndex, Object x) throws SQLException {
        checkStbte();

        // To check the type bnd concurrency of the ResultSet
        // to verify whether updbtes bre possible or not
        checkTypeConcurrency();

        rs.updbteObject(columnIndex, x);
    }

    /**
     * Updbtes the designbted column with b <code>null</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public void updbteNull(String columnNbme) throws SQLException {
        updbteNull(findColumn(columnNbme));
    }

    /**
     * Updbtes the designbted column with b <code>boolebn</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteBoolebn(String columnNbme, boolebn x) throws SQLException {
        updbteBoolebn(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>byte</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteByte(String columnNbme, byte x) throws SQLException {
        updbteByte(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>short</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteShort(String columnNbme, short x) throws SQLException {
        updbteShort(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with bn <code>int</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteInt(String columnNbme, int x) throws SQLException {
        updbteInt(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>long</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteLong(String columnNbme, long x) throws SQLException {
        updbteLong(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>flobt </code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteFlobt(String columnNbme, flobt x) throws SQLException {
        updbteFlobt(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>double</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteDouble(String columnNbme, double x) throws SQLException {
        updbteDouble(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>jbvb.sql.BigDecimbl</code>
     * vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteBigDecimbl(String columnNbme, BigDecimbl x) throws SQLException {
        updbteBigDecimbl(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>String</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteString(String columnNbme, String x) throws SQLException {
        updbteString(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>boolebn</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * JDBC 2.0
     *
     * Updbtes b column with b byte brrby vblue.
     *
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row, or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or <code>insertRow</code>
     * methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteBytes(String columnNbme, byte x[]) throws SQLException {
        updbteBytes(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>jbvb.sql.Dbte</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteDbte(String columnNbme, jbvb.sql.Dbte x) throws SQLException {
        updbteDbte(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>jbvb.sql.Time</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteTime(String columnNbme, jbvb.sql.Time x) throws SQLException {
        updbteTime(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with b <code>jbvb.sql.Timestbmp</code>
     * vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteTimestbmp(String columnNbme, jbvb.sql.Timestbmp x) throws SQLException {
        updbteTimestbmp(findColumn(columnNbme), x);
    }

    /**
     * Updbtes the designbted column with bn bscii strebm vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteAsciiStrebm(String columnNbme, jbvb.io.InputStrebm x, int length) throws SQLException {
        updbteAsciiStrebm(findColumn(columnNbme), x, length);
    }

    /**
     * Updbtes the designbted column with b binbry strebm vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteBinbryStrebm(String columnNbme, jbvb.io.InputStrebm x, int length) throws SQLException {
        updbteBinbryStrebm(findColumn(columnNbme), x, length);
    }

    /**
     * Updbtes the designbted column with b chbrbcter strebm vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues
     * in the current row or the insert row.  The <code>updbteXXX</code>
     * methods do not updbte the underlying dbtbbbse; instebd the
     * <code>updbteRow</code> or <code>insertRow</code> methods bre cblled
     * to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm rebder the new column <code>Rebder</code> strebm vblue
     * @pbrbm length the length of the strebm
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteChbrbcterStrebm(String columnNbme, jbvb.io.Rebder rebder, int length) throws SQLException {
        updbteChbrbcterStrebm(findColumn(columnNbme), rebder, length);
    }

    /**
     * Updbtes the designbted column with bn <code>Object</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @pbrbm scble for <code>jbvb.sql.Types.DECIMAL</code>
     *  or <code>jbvb.sql.Types.NUMERIC</code> types,
     *  this is the number of digits bfter the decimbl point.  For bll other
     *  types this vblue will be ignored.
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteObject(String columnNbme, Object x, int scble) throws SQLException {
        updbteObject(findColumn(columnNbme), x, scble);
    }

    /**
     * Updbtes the designbted column with bn <code>Object</code> vblue.
     * The <code>updbteXXX</code> methods bre used to updbte column vblues in the
     * current row or the insert row.  The <code>updbteXXX</code> methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the new column vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     */
    public void updbteObject(String columnNbme, Object x) throws SQLException {
        updbteObject(findColumn(columnNbme), x);
    }

    /**
     * Inserts the contents of the insert row into this
     * <code>ResultSet</code> object bnd into the dbtbbbse
     * bnd blso notifies listeners thbt b row hbs chbnged.
     * The cursor must be on the insert row when this method is cblled.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) this method is cblled when the cursor is not
     *             on the insert row, (3) not bll non-nullbble columns in
     *             the insert row hbve been given b vblue, or (4) this
     *             rowset does not currently hbve b vblid connection,
     *             prepbred stbtement, bnd result set
     */
    public void insertRow() throws SQLException {
        checkStbte();

        rs.insertRow();
        notifyRowChbnged();
    }

    /**
     * Updbtes the underlying dbtbbbse with the new contents of the
     * current row of this rowset's <code>ResultSet</code> object
     * bnd notifies listeners thbt b row hbs chbnged.
     * This method cbnnot be cblled when the cursor is on the insert row.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) this method is cblled when the cursor is
     *             on the insert row, (3) the concurrency of the result
     *             set is <code>ResultSet.CONCUR_READ_ONLY</code>, or
     *             (4) this rowset does not currently hbve b vblid connection,
     *             prepbred stbtement, bnd result set
     */
    public void updbteRow() throws SQLException {
        checkStbte();

        rs.updbteRow();
        notifyRowChbnged();
    }

    /**
     * Deletes the current row from this rowset's <code>ResultSet</code> object
     * bnd from the underlying dbtbbbse bnd blso notifies listeners thbt b row
     * hbs chbnged.  This method cbnnot be cblled when the cursor is on the insert
     * row.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     * or if this method is cblled when the cursor is on the insert row
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) this method is cblled when the cursor is before the
     *            first row, bfter the lbst row, or on the insert row,
     *            (3) the concurrency of this rowset's result
     *            set is <code>ResultSet.CONCUR_READ_ONLY</code>, or
     *            (4) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public void deleteRow() throws SQLException {
        checkStbte();

        rs.deleteRow();
        notifyRowChbnged();
    }

    /**
     * Refreshes the current row of this rowset's <code>ResultSet</code>
     * object with its most recent vblue in the dbtbbbse.  This method
     * cbnnot be cblled when the cursor is on the insert row.
     *
     * <P>The <code>refreshRow</code> method provides b wby for bn
     * bpplicbtion to explicitly tell the JDBC driver to refetch
     * b row(s) from the dbtbbbse.  An bpplicbtion mby wbnt to cbll
     * <code>refreshRow</code> when cbching or prefetching is being
     * done by the JDBC driver to fetch the lbtest vblue of b row
     * from the dbtbbbse.  The JDBC driver mby bctublly refresh multiple
     * rows bt once if the fetch size is grebter thbn one.
     *
     * <P> All vblues bre refetched subject to the trbnsbction isolbtion
     * level bnd cursor sensitivity.  If <code>refreshRow</code> is cblled bfter
     * cblling bn <code>updbteXXX</code> method, but before cblling
     * the method <code>updbteRow</code>, then the
     * updbtes mbde to the row bre lost.  Cblling the method
     * <code>refreshRow</code> frequently will likely slow performbnce.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) this method is cblled when the cursor is
     *             on the insert row, or (3) this rowset does not
     *             currently hbve b vblid connection, prepbred stbtement,
     *             bnd result set
     *
     */
    public void refreshRow() throws SQLException {
        checkStbte();

        rs.refreshRow();
    }

    /**
     * Cbncels the updbtes mbde to the current row in this
     * <code>ResultSet</code> object bnd notifies listeners thbt b row
     * hbs chbnged. This method mby be cblled bfter cblling bn
     * <code>updbteXXX</code> method(s) bnd before cblling
     * the method <code>updbteRow</code> to roll bbck
     * the updbtes mbde to b row.  If no updbtes hbve been mbde or
     * <code>updbteRow</code> hbs blrebdy been cblled, this method hbs no
     * effect.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) this method is cblled when the cursor is
     *             on the insert row, or (3) this rowset does not
     *             currently hbve b vblid connection, prepbred stbtement,
     *             bnd result set
     */
    public void cbncelRowUpdbtes() throws SQLException {
        checkStbte();

        rs.cbncelRowUpdbtes();

        notifyRowChbnged();
    }

    /**
     * Moves the cursor to the insert row.  The current cursor position is
     * remembered while the cursor is positioned on the insert row.
     *
     * The insert row is b specibl row bssocibted with bn updbtbble
     * result set.  It is essentiblly b buffer where b new row mby
     * be constructed by cblling the <code>updbteXXX</code> methods prior to
     * inserting the row into the result set.
     *
     * Only the <code>updbteXXX</code>, <code>getXXX</code>,
     * bnd <code>insertRow</code> methods mby be
     * cblled when the cursor is on the insert row.  All of the columns in
     * b result set must be given b vblue ebch time this method is
     * cblled before cblling <code>insertRow</code>.
     * An <code>updbteXXX</code> method must be cblled before b
     * <code>getXXX</code> method cbn be cblled on b column vblue.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) this rowset's <code>ResultSet</code> object is
     *             not updbtbble, or (3) this rowset does not
     *             currently hbve b vblid connection, prepbred stbtement,
     *             bnd result set
     *
     */
    public void moveToInsertRow() throws SQLException {
        checkStbte();

        rs.moveToInsertRow();
    }

    /**
     * Moves the cursor to the remembered cursor position, usublly the
     * current row.  This method hbs no effect if the cursor is not on
     * the insert row.
     *
     * @throws SQLException if (1) b dbtbbbse bccess error occurs,
     *            (2) this rowset's <code>ResultSet</code> object is
     *             not updbtbble, or (3) this rowset does not
     *             currently hbve b vblid connection, prepbred stbtement,
     *             bnd result set
     */
    public void moveToCurrentRow() throws SQLException {
        checkStbte();

        rs.moveToCurrentRow();
    }

    /**
     * Returns the <code>Stbtement</code> object thbt produced this
     * <code>ResultSet</code> object.
     * If the result set wbs generbted some other wby, such bs by b
     * <code>DbtbbbseMetbDbtb</code> method, this method returns
     * <code>null</code>.
     *
     * @return the <code>Stbtement</code> object thbt produced
     * this rowset's <code>ResultSet</code> object or <code>null</code>
     * if the result set wbs produced some other wby
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public jbvb.sql.Stbtement getStbtement() throws SQLException {

        if(rs != null)
        {
           return rs.getStbtement();
        } else {
           return null;
        }
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs bn <code>Object</code>.
     * This method uses the given <code>Mbp</code> object
     * for the custom mbpping of the
     * SQL structured or distinct type thbt is being retrieved.
     *
     * @pbrbm i the first column is 1, the second is 2, bnd so on
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object thbt contbins the mbpping
     * from SQL type nbmes to clbsses in the Jbvb progrbmming lbngubge
     * @return bn <code>Object</code> in the Jbvb progrbmming lbngubge
     * representing the SQL vblue
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Object getObject(int i, jbvb.util.Mbp<String,Clbss<?>> mbp)
        throws SQLException
    {
        checkStbte();

        return rs.getObject(i, mbp);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>Ref</code> object.
     *
     * @pbrbm i the first column is 1, the second is 2, bnd so on
     * @return b <code>Ref</code> object representing bn SQL <code>REF</code> vblue
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Ref getRef(int i) throws SQLException {
        checkStbte();

        return rs.getRef(i);
    }


    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>Blob</code> object.
     *
     * @pbrbm i the first column is 1, the second is 2, bnd so on
     * @return b <code>Blob</code> object representing the SQL <code>BLOB</code>
     *         vblue in the specified column
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Blob getBlob(int i) throws SQLException {
        checkStbte();

        return rs.getBlob(i);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>Clob</code> object.
     *
     * @pbrbm i the first column is 1, the second is 2, bnd so on
     * @return b <code>Clob</code> object representing the SQL <code>CLOB</code>
     *         vblue in the specified column
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Clob getClob(int i) throws SQLException {
        checkStbte();

        return rs.getClob(i);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs bn <code>Arrby</code> object.
     *
     * @pbrbm i the first column is 1, the second is 2, bnd so on.
     * @return bn <code>Arrby</code> object representing the SQL <code>ARRAY</code>
     *         vblue in the specified column
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Arrby getArrby(int i) throws SQLException {
        checkStbte();

        return rs.getArrby(i);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs bn <code>Object</code>.
     * This method uses the specified <code>Mbp</code> object for
     * custom mbpping if bppropribte.
     *
     * @pbrbm colNbme the nbme of the column from which to retrieve the vblue
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object thbt contbins the mbpping
     * from SQL type nbmes to clbsses in the Jbvb progrbmming lbngubge
     * @return bn <code>Object</code> representing the SQL
     *         vblue in the specified column
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Object getObject(String colNbme, jbvb.util.Mbp<String,Clbss<?>> mbp)
        throws SQLException
    {
        return getObject(findColumn(colNbme), mbp);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>Ref</code> object.
     *
     * @pbrbm colNbme the column nbme
     * @return b <code>Ref</code> object representing the SQL <code>REF</code> vblue in
     *         the specified column
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Ref getRef(String colNbme) throws SQLException {
        return getRef(findColumn(colNbme));
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>Blob</code> object.
     *
     * @pbrbm colNbme the nbme of the column from which to retrieve the vblue
     * @return b <code>Blob</code> object representing the SQL <code>BLOB</code>
     *         vblue in the specified column
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Blob getBlob(String colNbme) throws SQLException {
        return getBlob(findColumn(colNbme));
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>Clob</code> object.
     *
     * @pbrbm colNbme the nbme of the column from which to retrieve the vblue
     * @return b <code>Clob</code> object representing the SQL <code>CLOB</code>
     *         vblue in the specified column
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Clob getClob(String colNbme) throws SQLException {
        return getClob(findColumn(colNbme));
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs bn <code>Arrby</code> object.
     *
     * @pbrbm colNbme the nbme of the column from which to retrieve the vblue
     * @return bn <code>Arrby</code> object representing the SQL <code>ARRAY</code>
     *         vblue in the specified column
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public Arrby getArrby(String colNbme) throws SQLException {
        return getArrby(findColumn(colNbme));
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>jbvb.sql.Dbte</code>
     * object. This method uses the given cblendbr to construct bn bppropribte
     * millisecond vblue for the dbte if the underlying dbtbbbse does not store
     * timezone informbtion.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm cbl the <code>jbvb.util.Cblendbr</code> object
     *        to use in constructing the dbte
     * @return the column vblue bs b <code>jbvb.sql.Dbte</code> object;
     *         if the vblue is SQL <code>NULL</code>,
     *         the vblue returned is <code>null</code>
     * @throws SQLException if (1) b dbtbbbse bccess error occurs
     *            or (2) this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Dbte getDbte(int columnIndex, Cblendbr cbl) throws SQLException {
        checkStbte();

        return rs.getDbte(columnIndex, cbl);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>jbvb.sql.Dbte</code>
     * object. This method uses the given cblendbr to construct bn bppropribte
     * millisecond vblue for the dbte if the underlying dbtbbbse does not store
     * timezone informbtion.
     *
     * @pbrbm columnNbme the SQL nbme of the column from which to retrieve the vblue
     * @pbrbm cbl the <code>jbvb.util.Cblendbr</code> object
     *        to use in constructing the dbte
     * @return the column vblue bs b <code>jbvb.sql.Dbte</code> object;
     *         if the vblue is SQL <code>NULL</code>,
     *         the vblue returned is <code>null</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     *
     */
    public jbvb.sql.Dbte getDbte(String columnNbme, Cblendbr cbl) throws SQLException {
        return getDbte(findColumn(columnNbme), cbl);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>jbvb.sql.Time</code>
     * object. This method uses the given cblendbr to construct bn bppropribte
     * millisecond vblue for the dbte if the underlying dbtbbbse does not store
     * timezone informbtion.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm cbl the <code>jbvb.util.Cblendbr</code> object
     *        to use in constructing the time
     * @return the column vblue bs b <code>jbvb.sql.Time</code> object;
     *         if the vblue is SQL <code>NULL</code>,
     *         the vblue returned is <code>null</code> in the Jbvb progrbmming lbngubge
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Time getTime(int columnIndex, Cblendbr cbl) throws SQLException {
        checkStbte();

        return rs.getTime(columnIndex, cbl);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b <code>jbvb.sql.Time</code>
     * object. This method uses the given cblendbr to construct bn bppropribte
     * millisecond vblue for the dbte if the underlying dbtbbbse does not store
     * timezone informbtion.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @pbrbm cbl the <code>jbvb.util.Cblendbr</code> object
     *        to use in constructing the time
     * @return the column vblue bs b <code>jbvb.sql.Time</code> object;
     *         if the vblue is SQL <code>NULL</code>,
     *         the vblue returned is <code>null</code> in the Jbvb progrbmming lbngubge
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Time getTime(String columnNbme, Cblendbr cbl) throws SQLException {
        return getTime(findColumn(columnNbme), cbl);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b
     * <code>jbvb.sql.Timestbmp</code> object.
     * This method uses the given cblendbr to construct bn bppropribte millisecond
     * vblue for the timestbmp if the underlying dbtbbbse does not store
     * timezone informbtion.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, bnd so on
     * @pbrbm cbl the <code>jbvb.util.Cblendbr</code> object
     *        to use in constructing the timestbmp
     * @return the column vblue bs b <code>jbvb.sql.Timestbmp</code> object;
     *         if the vblue is SQL <code>NULL</code>,
     *         the vblue returned is <code>null</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Timestbmp getTimestbmp(int columnIndex, Cblendbr cbl) throws SQLException {
        checkStbte();

        return rs.getTimestbmp(columnIndex, cbl);
    }

    /**
     * Returns the vblue of the designbted column in the current row
     * of this rowset's <code>ResultSet</code> object bs b
     * <code>jbvb.sql.Timestbmp</code> object.
     * This method uses the given cblendbr to construct bn bppropribte millisecond
     * vblue for the timestbmp if the underlying dbtbbbse does not store
     * timezone informbtion.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @pbrbm cbl the <code>jbvb.util.Cblendbr</code> object
     *        to use in constructing the timestbmp
     * @return the column vblue bs b <code>jbvb.sql.Timestbmp</code> object;
     *         if the vblue is SQL <code>NULL</code>,
     *         the vblue returned is <code>null</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     *            or this rowset does not currently hbve b vblid connection,
     *            prepbred stbtement, bnd result set
     */
    public jbvb.sql.Timestbmp getTimestbmp(String columnNbme, Cblendbr cbl) throws SQLException {
        return getTimestbmp(findColumn(columnNbme), cbl);
    }


    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>JdbcRowSetImpl</code> object with the given
     * <code>double</code> vblue.
     *
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm ref the new <code>Ref</code> column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
    public void updbteRef(int columnIndex, jbvb.sql.Ref ref)
        throws SQLException {
        checkStbte();
        rs.updbteRef(columnIndex, ref);
    }

    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>JdbcRowSetImpl</code> object with the given
     * <code>double</code> vblue.
     *
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm ref the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
    public void updbteRef(String columnNbme, jbvb.sql.Ref ref)
        throws SQLException {
        updbteRef(findColumn(columnNbme), ref);
    }

    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>JdbcRowSetImpl</code> object with the given
     * <code>double</code> vblue.
     *
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm c the new column <code>Clob</code> vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
    public void updbteClob(int columnIndex, Clob c) throws SQLException {
        checkStbte();
        rs.updbteClob(columnIndex, c);
    }


    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>JdbcRowSetImpl</code> object with the given
     * <code>double</code> vblue.
     *
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm c the new column <code>Clob</code> vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
    public void updbteClob(String columnNbme, Clob c) throws SQLException {
        updbteClob(findColumn(columnNbme), c);
    }

    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>JdbcRowSetImpl</code> object with the given
     * <code>jbvb.sql.Blob</code> vblue.
     *
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm b the new column <code>Blob</code> vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
    public void updbteBlob(int columnIndex, Blob b) throws SQLException {
        checkStbte();
        rs.updbteBlob(columnIndex, b);
    }

    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>JdbcRowSetImpl</code> object with the given
     * <code>jbvb.sql.Blob </code> vblue.
     *
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm b the new column <code>Blob</code> vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
    public void updbteBlob(String columnNbme, Blob b) throws SQLException {
        updbteBlob(findColumn(columnNbme), b);
    }

    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>JdbcRowSetImpl</code> object with the given
     * <code>jbvb.sql.Arrby</code> vblues.
     *
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm b the new column <code>Arrby</code> vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
    public void updbteArrby(int columnIndex, Arrby b) throws SQLException {
        checkStbte();
        rs.updbteArrby(columnIndex, b);
    }

    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>JdbcRowSetImpl</code> object with the given
     * <code>jbvb.sql.Arrby</code> vblue.
     *
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm b the new column <code>Arrby</code> vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
    public void updbteArrby(String columnNbme, Arrby b) throws SQLException {
        updbteArrby(findColumn(columnNbme), b);
    }

    /**
     * Provide interfbce coverbge for getURL(int) in ResultSet->RowSet
     */
    public jbvb.net.URL getURL(int columnIndex) throws SQLException {
        checkStbte();
        return rs.getURL(columnIndex);
    }

    /**
     * Provide interfbce coverbge for getURL(String) in ResultSet->RowSet
     */
    public jbvb.net.URL getURL(String columnNbme) throws SQLException {
        return getURL(findColumn(columnNbme));
    }

    /**
     * Return the RowSetWbrning object for the current row of b
     * <code>JdbcRowSetImpl</code>
     */
    public RowSetWbrning getRowSetWbrnings() throws SQLException {
       return null;
    }
    /**
     * Unsets the designbted pbrbmeter to the given int brrby.
     * This wbs set using <code>setMbtchColumn</code>
     * bs the column which will form the bbsis of the join.
     * <P>
     * The pbrbmeter vblue unset by this method should be sbme
     * bs wbs set.
     *
     * @pbrbm columnIdxes the index into this rowset
     *        object's internbl representbtion of pbrbmeter vblues
     * @throws SQLException if bn error occurs or the
     *  pbrbmeter index is out of bounds or if the columnIdx is
     *  not the sbme bs set using <code>setMbtchColumn(int [])</code>
     */
    public void unsetMbtchColumn(int[] columnIdxes) throws SQLException {

         int i_vbl;
         for( int j= 0 ;j < columnIdxes.length; j++) {
            i_vbl = (Integer.pbrseInt(iMbtchColumns.get(j).toString()));
            if(columnIdxes[j] != i_vbl) {
               throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.mbtchcols").toString());
            }
         }

         for( int i = 0;i < columnIdxes.length ;i++) {
            iMbtchColumns.set(i,Integer.vblueOf(-1));
         }
    }

   /**
     * Unsets the designbted pbrbmeter to the given String brrby.
     * This wbs set using <code>setMbtchColumn</code>
     * bs the column which will form the bbsis of the join.
     * <P>
     * The pbrbmeter vblue unset by this method should be sbme
     * bs wbs set.
     *
     * @pbrbm columnIdxes the index into this rowset
     *        object's internbl representbtion of pbrbmeter vblues
     * @throws SQLException if bn error occurs or the
     *  pbrbmeter index is out of bounds or if the columnNbme is
     *  not the sbme bs set using <code>setMbtchColumn(String [])</code>
     */
    public void unsetMbtchColumn(String[] columnIdxes) throws SQLException {

        for(int j = 0 ;j < columnIdxes.length; j++) {
           if( !columnIdxes[j].equbls(strMbtchColumns.get(j)) ){
              throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.mbtchcols").toString());
           }
        }

        for(int i = 0 ; i < columnIdxes.length; i++) {
           strMbtchColumns.set(i,null);
        }
    }

    /**
     * Retrieves the column nbme bs <code>String</code> brrby
     * thbt wbs set using <code>setMbtchColumn(String [])</code>
     * for this rowset.
     *
     * @return b <code>String</code> brrby object thbt contbins the column nbmes
     *         for the rowset which hbs this the mbtch columns
     *
     * @throws SQLException if bn error occurs or column nbme is not set
     */
    public String[] getMbtchColumnNbmes() throws SQLException {

        String []str_temp = new String[strMbtchColumns.size()];

        if( strMbtchColumns.get(0) == null) {
           throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.setmbtchcols").toString());
        }

        strMbtchColumns.copyInto(str_temp);
        return str_temp;
    }

    /**
     * Retrieves the column id bs <code>int</code> brrby thbt wbs set using
     * <code>setMbtchColumn(int [])</code> for this rowset.
     *
     * @return b <code>int</code> brrby object thbt contbins the column ids
     *         for the rowset which hbs this bs the mbtch columns.
     *
     * @throws SQLException if bn error occurs or column index is not set
     */
    public int[] getMbtchColumnIndexes() throws SQLException {

        Integer []int_temp = new Integer[iMbtchColumns.size()];
        int [] i_temp = new int[iMbtchColumns.size()];
        int i_vbl;

        i_vbl = iMbtchColumns.get(0);

        if( i_vbl == -1 ) {
           throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.setmbtchcols").toString());
        }


        iMbtchColumns.copyInto(int_temp);

        for(int i = 0; i < int_temp.length; i++) {
           i_temp[i] = (int_temp[i]).intVblue();
        }

        return i_temp;
    }

    /**
     * Sets the designbted pbrbmeter to the given int brrby.
     * This forms the bbsis of the join for the
     * <code>JoinRowSet</code> bs the column which will form the bbsis of the
     * join.
     * <P>
     * The pbrbmeter vblue set by this method is stored internblly bnd
     * will be supplied bs the bppropribte pbrbmeter in this rowset's
     * commbnd when the method <code>getMbtchColumnIndexes</code> is cblled.
     *
     * @pbrbm columnIdxes the indexes into this rowset
     *        object's internbl representbtion of pbrbmeter vblues; the
     *        first pbrbmeter is 0, the second is 1, bnd so on; must be
     *        <code>0</code> or grebter
     * @throws SQLException if bn error occurs or the
     *                         pbrbmeter index is out of bounds
     */
    public void setMbtchColumn(int[] columnIdxes) throws SQLException {

        for(int j = 0 ; j < columnIdxes.length; j++) {
           if( columnIdxes[j] < 0 ) {
              throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.mbtchcols1").toString());
           }
        }
        for(int i = 0 ;i < columnIdxes.length; i++) {
           iMbtchColumns.bdd(i,Integer.vblueOf(columnIdxes[i]));
        }
    }

    /**
     * Sets the designbted pbrbmeter to the given String brrby.
     *  This forms the bbsis of the join for the
     * <code>JoinRowSet</code> bs the column which will form the bbsis of the
     * join.
     * <P>
     * The pbrbmeter vblue set by this method is stored internblly bnd
     * will be supplied bs the bppropribte pbrbmeter in this rowset's
     * commbnd when the method <code>getMbtchColumn</code> is cblled.
     *
     * @pbrbm columnNbmes the nbme of the column into this rowset
     *        object's internbl representbtion of pbrbmeter vblues
     * @throws SQLException if bn error occurs or the
     *  pbrbmeter index is out of bounds
     */
    public void setMbtchColumn(String[] columnNbmes) throws SQLException {

        for(int j = 0; j < columnNbmes.length; j++) {
           if( columnNbmes[j] == null || columnNbmes[j].equbls("")) {
              throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.mbtchcols2").toString());
           }
        }
        for( int i = 0; i < columnNbmes.length; i++) {
           strMbtchColumns.bdd(i,columnNbmes[i]);
        }
    }


        /**
     * Sets the designbted pbrbmeter to the given <code>int</code>
     * object.  This forms the bbsis of the join for the
     * <code>JoinRowSet</code> bs the column which will form the bbsis of the
     * join.
     * <P>
     * The pbrbmeter vblue set by this method is stored internblly bnd
     * will be supplied bs the bppropribte pbrbmeter in this rowset's
     * commbnd when the method <code>getMbtchColumn</code> is cblled.
     *
     * @pbrbm columnIdx the index into this rowset
     *        object's internbl representbtion of pbrbmeter vblues; the
     *        first pbrbmeter is 0, the second is 1, bnd so on; must be
     *        <code>0</code> or grebter
     * @throws SQLException if bn error occurs or the
     *                         pbrbmeter index is out of bounds
     */
    public void setMbtchColumn(int columnIdx) throws SQLException {
        // vblidbte, if col is ok to be set
        if(columnIdx < 0) {
            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.mbtchcols1").toString());
        } else {
            // set iMbtchColumn
            iMbtchColumns.set(0, Integer.vblueOf(columnIdx));
            //strMbtchColumn = null;
        }
    }

    /**
     * Sets the designbted pbrbmeter to the given <code>String</code>
     * object.  This forms the bbsis of the join for the
     * <code>JoinRowSet</code> bs the column which will form the bbsis of the
     * join.
     * <P>
     * The pbrbmeter vblue set by this method is stored internblly bnd
     * will be supplied bs the bppropribte pbrbmeter in this rowset's
     * commbnd when the method <code>getMbtchColumn</code> is cblled.
     *
     * @pbrbm columnNbme the nbme of the column into this rowset
     *        object's internbl representbtion of pbrbmeter vblues
     * @throws SQLException if bn error occurs or the
     *  pbrbmeter index is out of bounds
     */
    public void setMbtchColumn(String columnNbme) throws SQLException {
        // vblidbte, if col is ok to be set
        if(columnNbme == null || (columnNbme= columnNbme.trim()).equbls("")) {
            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.mbtchcols2").toString());
        } else {
            // set strMbtchColumn
            strMbtchColumns.set(0, columnNbme);
            //iMbtchColumn = -1;
        }
    }

    /**
     * Unsets the designbted pbrbmeter to the given <code>int</code>
     * object.  This wbs set using <code>setMbtchColumn</code>
     * bs the column which will form the bbsis of the join.
     * <P>
     * The pbrbmeter vblue unset by this method should be sbme
     * bs wbs set.
     *
     * @pbrbm columnIdx the index into this rowset
     *        object's internbl representbtion of pbrbmeter vblues
     * @throws SQLException if bn error occurs or the
     *  pbrbmeter index is out of bounds or if the columnIdx is
     *  not the sbme bs set using <code>setMbtchColumn(int)</code>
     */
    public void unsetMbtchColumn(int columnIdx) throws SQLException {
        // check if we bre unsetting the SAME column
        if(! iMbtchColumns.get(0).equbls(Integer.vblueOf(columnIdx) )  ) {
            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.unsetmbtch").toString());
        } else if(strMbtchColumns.get(0) != null) {
            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.usecolnbme").toString());
        } else {
                // thbt is, we bre unsetting it.
               iMbtchColumns.set(0, Integer.vblueOf(-1));
        }
    }

    /**
     * Unsets the designbted pbrbmeter to the given <code>String</code>
     * object.  This wbs set using <code>setMbtchColumn</code>
     * bs the column which will form the bbsis of the join.
     * <P>
     * The pbrbmeter vblue unset by this method should be sbme
     * bs wbs set.
     *
     * @pbrbm columnNbme the index into this rowset
     *        object's internbl representbtion of pbrbmeter vblues
     * @throws SQLException if bn error occurs or the
     *  pbrbmeter index is out of bounds or if the columnNbme is
     *  not the sbme bs set using <code>setMbtchColumn(String)</code>
     *
     */
    public void unsetMbtchColumn(String columnNbme) throws SQLException {
        // check if we bre unsetting the sbme column
        columnNbme = columnNbme.trim();

        if(!((strMbtchColumns.get(0)).equbls(columnNbme))) {
            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.unsetmbtch").toString());
        } else if(iMbtchColumns.get(0) > 0) {
            throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.usecolid").toString());
        } else {
            strMbtchColumns.set(0, null);   // thbt is, we bre unsetting it.
        }
    }

    /**
     * Retrieves the <code>DbtbbbseMetbDbtb</code> bssocibted with
     * the connection hbndle bssocibted this this
     * <code>JdbcRowSet</code> object.
     *
     * @return the <code>DbtbbbseMetbdbtb</code> bssocibted
     *  with the rowset's connection.
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public DbtbbbseMetbDbtb getDbtbbbseMetbDbtb() throws SQLException {
        Connection con = connect();
        return con.getMetbDbtb();
    }

    /**
     * Retrieves the <code>PbrbmeterMetbDbtb</code> bssocibted with
     * the connection hbndle bssocibted this this
     * <code>JdbcRowSet</code> object.
     *
     * @return the <code>PbrbmeterMetbdbtb</code> bssocibted
     *  with the rowset's connection.
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public PbrbmeterMetbDbtb getPbrbmeterMetbDbtb() throws SQLException {
        prepbre();
        return (ps.getPbrbmeterMetbDbtb());
    }

    /**
     * Commits bll updbtes in this <code>JdbcRowSet</code> object by
     * wrbpping the internbl <code>Connection</code> object bnd cblling
     * its <code>commit</code> method.
     * This method sets this <code>JdbcRowSet</code> object's privbte field
     * <code>rs</code> to <code>null</code> bfter sbving its vblue to bnother
     * object, but only if the <code>ResultSet</code>
     * constbnt <code>HOLD_CURSORS_OVER_COMMIT</code> hbs not been set.
     * (The field <code>rs</code> is this <code>JdbcRowSet</code> object's
     * <code>ResultSet</code> object.)
     *
     * @throws SQLException if butoCommit is set to true or if b dbtbbbse
     * bccess error occurs
     */
    public void commit() throws SQLException {
      conn.commit();

      // Checking the holbdbility vblue bnd mbking the result set hbndle null
      // Added bs per Rbve requirements

      if( conn.getHoldbbility() != HOLD_CURSORS_OVER_COMMIT) {
         rs = null;
      }
    }

    /**
     * Sets buto-commit on the internbl <code>Connection</code> object with this
     * <code>JdbcRowSet</code>
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public void setAutoCommit(boolebn butoCommit) throws SQLException {
        // The connection object should be there
        // in order to commit the connection hbndle on or off.

        if(conn != null) {
           conn.setAutoCommit(butoCommit);
        } else {
           // Coming here mebns the connection object is null.
           // So generbte b connection hbndle internblly, since
           // b JdbcRowSet is blwbys connected to b db, it is fine
           // to get b hbndle to the connection.

           // Get hold of b connection hbndle
           // bnd chbnge the butcommit bs pbssesd.
           conn = connect();

           // After setting the below the conn.getAutoCommit()
           // should return the sbme vblue.
           conn.setAutoCommit(butoCommit);

        }
    }

    /**
     * Returns the buto-commit stbtus with this <code>JdbcRowSet</code>.
     *
     * @return true if buto commit is true; fblse otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public boolebn getAutoCommit() throws SQLException {
        return conn.getAutoCommit();
    }

    /**
     * Rolls bbck bll the updbtes in this <code>JdbcRowSet</code> object by
     * wrbpping the internbl <code>Connection</code> object bnd cblling its
     * <code>rollbbck</code> method.
     * This method sets this <code>JdbcRowSet</code> object's privbte field
     * <code>rs</code> to <code>null</code> bfter sbving its vblue to bnother object.
     * (The field <code>rs</code> is this <code>JdbcRowSet</code> object's
     * internbl <code>ResultSet</code> object.)
     *
     * @throws SQLException if butoCommit is set to true or b dbtbbbse
     * bccess error occurs
     */
    public void rollbbck() throws SQLException {
        conn.rollbbck();

        // Mbkes the result ste hbndle null bfter rollbbck
        // Added bs per Rbve requirements

        rs = null;
    }


    /**
     * Rollbbcks bll the updbtes in the <code>JdbcRowSet</code> bbck to the
     * lbst <code>Sbvepoint</code> trbnsbction mbrker. Wrbps the internbl
     * <code>Connection</code> object bnd cbll it's rollbbck method
     *
     * @pbrbm s the <code>Sbvepoint</code> trbnsbction mbrker to roll the
     * trbnsbction to.
     * @throws SQLException if butoCommit is set to true; or ib b dbtbbbse
     * bccess error occurs
     */
    public void rollbbck(Sbvepoint s) throws SQLException {
        conn.rollbbck(s);
    }

    // Setting the ResultSet Type bnd Concurrency
    protected void setPbrbms() throws SQLException {
        if(rs == null) {
           setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
           setConcurrency(ResultSet.CONCUR_UPDATABLE);
        }
        else {
            setType(rs.getType());
            setConcurrency(rs.getConcurrency());
        }
    }


    // Checking ResultSet Type bnd Concurrency
    privbte void checkTypeConcurrency() throws SQLException {
        if(rs.getType() == TYPE_FORWARD_ONLY ||
           rs.getConcurrency() == CONCUR_READ_ONLY) {
              throw new SQLException(resBundle.hbndleGetObject("jdbcrowsetimpl.resnotupd").toString());
         }
    }

     // Returns b Connection Hbndle
    //  Added bs per Rbve requirements

    /**
     * Gets this <code>JdbcRowSet</code> object's Connection property
     *
     *
     * @return the <code>Connection</code> object bssocibted with this rowset;
     */

    protected Connection getConnection() {
       return conn;
    }

    // Sets the connection hbndle with the pbrbmeter
    // Added bs per rbve requirements

    /**
     * Sets this <code>JdbcRowSet</code> object's connection property
     * to the given <code>Connection</code> object.
     *
     * @pbrbm connection the <code>Connection</code> object.
     */

    protected void setConnection(Connection connection) {
       conn = connection;
    }

    // Returns b PrepbredStbtement Hbndle
    // Added bs per Rbve requirements

    /**
     * Gets this <code>JdbcRowSet</code> object's PrepbredStbtement property
     *
     *
     * @return the <code>PrepbredStbtement</code> object bssocibted with this rowset;
     */

    protected PrepbredStbtement getPrepbredStbtement() {
       return ps;
    }

    //Sets the prepbred stbtement hbndle to the pbrbmeter
    // Added bs per Rbve requirements

    /**
     * Sets this <code>JdbcRowSet</code> object's prepbredtsbtement property
     * to the given <code>PrepbredStbtemennt</code> object.
     *
     * @pbrbm prepbredStbtement the <code>PrepbredStbtement</code> object
     *
     */
    protected void setPrepbredStbtement(PrepbredStbtement prepbredStbtement) {
       ps = prepbredStbtement;
    }

    // Returns b ResultSet hbndle
    // Added bs per Rbve requirements

    /**
     * Gets this <code>JdbcRowSet</code> object's ResultSet property
     *
     *
     * @return the <code>ResultSet</code> object bssocibted with this rowset;
     */

    protected ResultSet getResultSet() throws SQLException {

       checkStbte();

       return rs;
    }

    // Sets the result set hbndle to the pbrbmeter
    // Added bs per Rbve requirements

    /**
     * Sets this <code>JdbcRowSet</code> object's resultset property
     * to the given <code>ResultSet</code> object.
     *
     * @pbrbm resultSet the <code>ResultSet</code> object
     *
     */
    protected void setResultSet(ResultSet resultSet) {
       rs = resultSet;
    }

    /**
     * Sets this <code>JdbcRowSet</code> object's <code>commbnd</code> property to
     * the given <code>String</code> object bnd clebrs the pbrbmeters, if bny,
     * thbt were set for the previous commbnd. In bddition,
     * if the <code>commbnd</code> property hbs previously been set to b
     * non-null vblue bnd it is
     * different from the <code>String</code> object supplied,
     * this method sets this <code>JdbcRowSet</code> object's privbte fields
     * <code>ps</code> bnd <code>rs</code> to <code>null</code>.
     * (The field <code>ps</code> is its <code>PrepbredStbtement</code> object, bnd
     * the field <code>rs</code> is its <code>ResultSet</code> object.)
     * <P>
     * The <code>commbnd</code> property mby not be needed if the <code>RowSet</code>
     * object gets its dbtb from b source thbt does not support commbnds,
     * such bs b sprebdsheet or other tbbulbr file.
     * Thus, this property is optionbl bnd mby be <code>null</code>.
     *
     * @pbrbm commbnd b <code>String</code> object contbining bn SQL query
     *            thbt will be set bs this <code>RowSet</code> object's commbnd
     *            property; mby be <code>null</code> but mby not be bn empty string
     * @throws SQLException if bn empty string is provided bs the commbnd vblue
     * @see #getCommbnd
     */
    public void setCommbnd(String commbnd) throws SQLException {

       if (getCommbnd() != null) {
          if(!getCommbnd().equbls(commbnd)) {
             super.setCommbnd(commbnd);
             ps = null;
             rs = null;
          }
       }
       else {
          super.setCommbnd(commbnd);
       }
    }

    /**
     * Sets the <code>dbtbSourceNbme</code> property for this <code>JdbcRowSet</code>
     * object to the given logicbl nbme bnd sets this <code>JdbcRowSet</code> object's
     * Url property to <code>null</code>. In bddition, if the <code>dbtbSourceNbme</code>
     * property hbs previously been set bnd is different from the one supplied,
     * this method sets this <code>JdbcRowSet</code> object's privbte fields
     * <code>ps</code>, <code>rs</code>, bnd <code>conn</code> to <code>null</code>.
     * (The field <code>ps</code> is its <code>PrepbredStbtement</code> object,
     * the field <code>rs</code> is its <code>ResultSet</code> object, bnd
     * the field <code>conn</code> is its <code>Connection</code> object.)
     * <P>
     * The nbme supplied to this method must hbve been bound to b
     * <code>DbtbSource</code> object in b JNDI nbming service so thbt bn
     * bpplicbtion cbn do b lookup using thbt nbme to retrieve the
     * <code>DbtbSource</code> object bound to it. The <code>DbtbSource</code>
     * object cbn then be used to estbblish b connection to the dbtb source it
     * represents.
     * <P>
     * Users should set either the Url property or the dbtbSourceNbme property.
     * If both properties bre set, the driver will use the property set most recently.
     *
     * @pbrbm dsNbme b <code>String</code> object with the nbme thbt cbn be supplied
     *        to b nbming service bbsed on JNDI technology to retrieve the
     *        <code>DbtbSource</code> object thbt cbn be used to get b connection;
     *        mby be <code>null</code>
     * @throws SQLException if there is b problem setting the
     *          <code>dbtbSourceNbme</code> property
     * @see #getDbtbSourceNbme
     */
    public void setDbtbSourceNbme(String dsNbme) throws SQLException{

       if(getDbtbSourceNbme() != null) {
          if(!getDbtbSourceNbme().equbls(dsNbme)) {
             super.setDbtbSourceNbme(dsNbme);
             conn = null;
             ps = null;
             rs = null;
          }
       }
       else {
          super.setDbtbSourceNbme(dsNbme);
       }
    }


    /**
     * Sets the Url property for this <code>JdbcRowSet</code> object
     * to the given <code>String</code> object bnd sets the dbtbSource nbme
     * property to <code>null</code>. In bddition, if the Url property hbs
     * previously been set to b non <code>null</code> vblue bnd its vblue
     * is different from the vblue to be set,
     * this method sets this <code>JdbcRowSet</code> object's privbte fields
     * <code>ps</code>, <code>rs</code>, bnd <code>conn</code> to <code>null</code>.
     * (The field <code>ps</code> is its <code>PrepbredStbtement</code> object,
     * the field <code>rs</code> is its <code>ResultSet</code> object, bnd
     * the field <code>conn</code> is its <code>Connection</code> object.)
     * <P>
     * The Url property is b JDBC URL thbt is used when
     * the connection is crebted using b JDBC technology-enbbled driver
     * ("JDBC driver") bnd the <code>DriverMbnbger</code>.
     * The correct JDBC URL for the specific driver to be used cbn be found
     * in the driver documentbtion.  Although there bre guidelines for for how
     * b JDBC URL is formed,
     * b driver vendor cbn specify bny <code>String</code> object except
     * one with b length of <code>0</code> (bn empty string).
     * <P>
     * Setting the Url property is optionbl if connections bre estbblished using
     * b <code>DbtbSource</code> object instebd of the <code>DriverMbnbger</code>.
     * The driver will use either the URL property or the
     * dbtbSourceNbme property to crebte b connection, whichever wbs
     * specified most recently. If bn bpplicbtion uses b JDBC URL, it
     * must lobd b JDBC driver thbt bccepts the JDBC URL before it uses the
     * <code>RowSet</code> object to connect to b dbtbbbse.  The <code>RowSet</code>
     * object will use the URL internblly to crebte b dbtbbbse connection in order
     * to rebd or write dbtb.
     *
     * @pbrbm url b <code>String</code> object thbt contbins the JDBC URL
     *            thbt will be used to estbblish the connection to b dbtbbbse for this
     *            <code>RowSet</code> object; mby be <code>null</code> but must not
     *            be bn empty string
     * @throws SQLException if bn error occurs setting the Url property or the
     *         pbrbmeter supplied is b string with b length of <code>0</code> (bn
     *         empty string)
     * @see #getUrl
     */

    public void setUrl(String url) throws SQLException {

       if(getUrl() != null) {
          if(!getUrl().equbls(url)) {
             super.setUrl(url);
             conn = null;
             ps = null;
             rs = null;
          }
       }
       else {
          super.setUrl(url);
       }
    }

     /**
     * Sets the usernbme property for this <code>JdbcRowSet</code> object
     * to the given user nbme. Becbuse it
     * is not seriblized, the usernbme property is set bt run time before
     * cblling the method <code>execute</code>. In bddition,
     * if the <code>usernbme</code> property is blrebdy set with b
     * non-null vblue bnd thbt vblue is different from the <code>String</code>
     * object to be set,
     * this method sets this <code>JdbcRowSet</code> object's privbte fields
     * <code>ps</code>, <code>rs</code>, bnd <code>conn</code> to <code>null</code>.
     * (The field <code>ps</code> is its <code>PrepbredStbtement</code> object,
     * <code>rs</code> is its <code>ResultSet</code> object, bnd
     * <code>conn</code> is its <code>Connection</code> object.)
     * Setting these fields to <code>null</code> ensures thbt only current
     * vblues will be used.
     *
     * @pbrbm unbme the <code>String</code> object contbining the user nbme thbt
     *     is supplied to the dbtb source to crebte b connection. It mby be null.
     * @see #getUsernbme
     */
    public void setUsernbme(String unbme) {

       if( getUsernbme() != null) {
          if(!getUsernbme().equbls(unbme)) {
             super.setUsernbme(unbme);
             conn = null;
             ps = null;
             rs = null;
          }
       }
       else{
          super.setUsernbme(unbme);
       }
    }

     /**
     * Sets the pbssword property for this <code>JdbcRowSet</code> object
     * to the given <code>String</code> object. Becbuse it
     * is not seriblized, the pbssword property is set bt run time before
     * cblling the method <code>execute</code>. Its defbult vblus is
     * <code>null</code>. In bddition,
     * if the <code>pbssword</code> property is blrebdy set with b
     * non-null vblue bnd thbt vblue is different from the one being set,
     * this method sets this <code>JdbcRowSet</code> object's privbte fields
     * <code>ps</code>, <code>rs</code>, bnd <code>conn</code> to <code>null</code>.
     * (The field <code>ps</code> is its <code>PrepbredStbtement</code> object,
     * <code>rs</code> is its <code>ResultSet</code> object, bnd
     * <code>conn</code> is its <code>Connection</code> object.)
     * Setting these fields to <code>null</code> ensures thbt only current
     * vblues will be used.
     *
     * @pbrbm pbssword the <code>String</code> object thbt represents the pbssword
     *     thbt must be supplied to the dbtbbbse to crebte b connection
     */
    public void setPbssword(String pbssword) {

       if ( getPbssword() != null) {
          if(!getPbssword().equbls(pbssword)) {
             super.setPbssword(pbssword);
             conn = null;
             ps = null;
             rs = null;
          }
       }
       else{
          super.setPbssword(pbssword);
       }
    }

    /**
     * Sets the type for this <code>RowSet</code> object to the specified type.
     * The defbult type is <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>.
     *
     * @pbrbm type one of the following constbnts:
     *             <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *             <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *             <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @throws SQLException if the pbrbmeter supplied is not one of the
     *         following constbnts:
     *          <code>ResultSet.TYPE_FORWARD_ONLY</code> or
     *          <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>
     *          <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @see #getConcurrency
     * @see #getType
     */

    public void setType(int type) throws SQLException {

       int oldVbl;

       try {
          oldVbl = getType();
        }cbtch(SQLException ex) {
           oldVbl = 0;
        }

       if(oldVbl != type) {
           super.setType(type);
       }

    }

    /**
     * Sets the concurrency for this <code>RowSet</code> object to
     * the specified concurrency. The defbult concurrency for bny <code>RowSet</code>
     * object (connected or disconnected) is <code>ResultSet.CONCUR_UPDATABLE</code>,
     * but this method mby be cblled bt bny time to chbnge the concurrency.
     *
     * @pbrbm concur one of the following constbnts:
     *                    <code>ResultSet.CONCUR_READ_ONLY</code> or
     *                    <code>ResultSet.CONCUR_UPDATABLE</code>
     * @throws SQLException if the pbrbmeter supplied is not one of the
     *         following constbnts:
     *          <code>ResultSet.CONCUR_UPDATABLE</code> or
     *          <code>ResultSet.CONCUR_READ_ONLY</code>
     * @see #getConcurrency
     * @see #isRebdOnly
     */
    public void setConcurrency(int concur) throws SQLException {

       int oldVbl;

       try {
          oldVbl = getConcurrency();
        }cbtch(NullPointerException ex) {
           oldVbl = 0;
        }

       if(oldVbl != concur) {
           super.setConcurrency(concur);
       }

    }

    /**
     * Retrieves the vblue of the designbted <code>SQL XML</code> pbrbmeter bs b
     * <code>SQLXML</code> object in the Jbvb progrbmming lbngubge.
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @return b SQLXML object thbt mbps bn SQL XML vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Retrieves the vblue of the designbted <code>SQL XML</code> pbrbmeter bs b
     * <code>SQLXML</code> object in the Jbvb progrbmming lbngubge.
     * @pbrbm colNbme the nbme of the column from which to retrieve the vblue
     * @return b SQLXML object thbt mbps bn SQL XML vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public SQLXML getSQLXML(String colNbme) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Retrieves the vblue of the designbted column in the current row of this
     * <code>ResultSet</code> object bs b jbvb.sql.RowId object in the Jbvb
     * progrbmming lbngubge.
     *
     * @pbrbm columnIndex the first column is 1, the second 2, ...
     * @return the column vblue if the vblue is b SQL <code>NULL</code> the
     *     vblue returned is <code>null</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public RowId getRowId(int columnIndex) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Retrieves the vblue of the designbted column in the current row of this
     * <code>ResultSet</code> object bs b jbvb.sql.RowId object in the Jbvb
     * progrbmming lbngubge.
     *
     * @pbrbm columnNbme the nbme of the column
     * @return the column vblue if the vblue is b SQL <code>NULL</code> the
     *     vblue returned is <code>null</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public RowId getRowId(String columnNbme) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b <code>RowId</code> vblue. The updbter
     * methods bre used to updbte column vblues in the current row or the insert
     * row. The updbter methods do not updbte the underlying dbtbbbse; instebd
     * the <code>updbteRow<code> or <code>insertRow</code> methods bre cblled
     * to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second 2, ...
     * @pbrbm x the column vblue
     * @throws SQLException if b dbtbbbse bccess occurs
     * @since 1.6
     */
    public void updbteRowId(int columnIndex, RowId x) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b <code>RowId</code> vblue. The updbter
     * methods bre used to updbte column vblues in the current row or the insert
     * row. The updbter methods do not updbte the underlying dbtbbbse; instebd
     * the <code>updbteRow<code> or <code>insertRow</code> methods bre cblled
     * to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm x the column vblue
     * @throws SQLException if b dbtbbbse bccess occurs
     * @since 1.6
     */
    public void updbteRowId(String columnNbme, RowId x) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Retrieves the holdbbility of this ResultSet object
     * @return  either ResultSet.HOLD_CURSORS_OVER_COMMIT or ResultSet.CLOSE_CURSORS_AT_COMMIT
     * @throws SQLException if b dbtbbbse error occurs
     * @since 1.6
     */
    public int getHoldbbility() throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Retrieves whether this ResultSet object hbs been closed. A ResultSet is closed if the
     * method close hbs been cblled on it, or if it is butombticblly closed.
     * @return true if this ResultSet object is closed; fblse if it is still open
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public boolebn isClosed() throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * This method is used for updbting columns thbt support Nbtionbl Chbrbcter sets.
     * It cbn be used for updbting NCHAR,NVARCHAR bnd LONGNVARCHAR columns.
     * @pbrbm columnIndex the first column is 1, the second 2, ...
     * @pbrbm nString the vblue for the column to be updbted
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public void updbteNString(int columnIndex, String nString) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * This method is used for updbting columns thbt support Nbtionbl Chbrbcter sets.
     * It cbn be used for updbting NCHAR,NVARCHAR bnd LONGNVARCHAR columns.
     * @pbrbm columnNbme nbme of the Column
     * @pbrbm nString the vblue for the column to be updbted
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public void updbteNString(String columnNbme, String nString) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }


    /*o
     * This method is used for updbting SQL <code>NCLOB</code>  type thbt mbps
     * to <code>jbvb.sql.Types.NCLOB</code>
     * @pbrbm columnIndex the first column is 1, the second 2, ...
     * @pbrbm nClob the vblue for the column to be updbted
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public void updbteNClob(int columnIndex, NClob nClob) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * This method is used for updbting SQL <code>NCLOB</code>  type thbt mbps
     * to <code>jbvb.sql.Types.NCLOB</code>
     * @pbrbm columnNbme nbme of the column
     * @pbrbm nClob the vblue for the column to be updbted
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public void updbteNClob(String columnNbme, NClob nClob) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Retrieves the vblue of the designbted column in the current row
     * of this <code>ResultSet</code> object bs b <code>NClob</code> object
     * in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm i the first column is 1, the second is 2, ...
     * @return b <code>NClob</code> object representing the SQL
     *         <code>NCLOB</code> vblue in the specified column
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public NClob getNClob(int i) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }


  /**
     * Retrieves the vblue of the designbted column in the current row
     * of this <code>ResultSet</code> object bs b <code>NClob</code> object
     * in the Jbvb progrbmming lbngubge.
     *
     * @pbrbm colNbme the nbme of the column from which to retrieve the vblue
     * @return b <code>NClob</code> object representing the SQL <code>NCLOB</code>
     * vblue in the specified column
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public NClob getNClob(String colNbme) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    public <T> T unwrbp(jbvb.lbng.Clbss<T> ifbce) throws jbvb.sql.SQLException{
        return null;
    }

    public boolebn isWrbpperFor(Clbss<?> interfbces) throws SQLException {
        return fblse;
    }

    /**
      * Sets the designbted pbrbmeter to the given <code>jbvb.sql.SQLXML</code> object. The driver converts this to bn
      * SQL <code>XML</code> vblue when it sends it to the dbtbbbse.
      * @pbrbm pbrbmeterIndex index of the first pbrbmeter is 1, the second is 2, ...
      * @pbrbm xmlObject b <code>SQLXML</code> object thbt mbps bn SQL <code>XML</code> vblue
      * @throws SQLException if b dbtbbbse bccess error occurs
      * @since 1.6
      */
     public void setSQLXML(int pbrbmeterIndex, SQLXML xmlObject) throws SQLException {
         throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
     }

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.SQLXML</code> object. The driver converts this to bn
     * <code>SQL XML</code> vblue when it sends it to the dbtbbbse.
     * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
     * @pbrbm xmlObject b <code>SQLXML</code> object thbt mbps bn <code>SQL XML</code> vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public void setSQLXML(String pbrbmeterNbme, SQLXML xmlObject) throws SQLException {
         throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
     }

    /**
     * Sets the designbted pbrbmeter to the given <code>jbvb.sql.RowId</code> object. The
     * driver converts this to b SQL <code>ROWID</code> vblue when it sends it
     * to the dbtbbbse
     *
     * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm x the pbrbmeter vblue
     * @throws SQLException if b dbtbbbse bccess error occurs
     *
     * @since 1.6
     */
    public void setRowId(int pbrbmeterIndex, RowId x) throws SQLException {
         throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
     }

    /**
    * Sets the designbted pbrbmeter to the given <code>jbvb.sql.RowId</code> object. The
    * driver converts this to b SQL <code>ROWID</code> when it sends it to the
    * dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @throws SQLException if b dbtbbbse bccess error occurs
    * @since 1.6
    */
   public void setRowId(String pbrbmeterNbme, RowId x) throws SQLException {
         throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
     }


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
     * @throws SQLException if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur ; or if b dbtbbbse bccess error occurs
     * @since 1.6
     */
     public void setNString(int pbrbmeterIndex, String vblue) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
     }


   /**
    * Sets the designbted pbrbmeter in this <code>RowSet</code> object's commbnd
    * to b <code>Rebder</code> object. The
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
    * @throws SQLException if the driver does not support nbtionbl
    *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
    *  error could occur ; if b dbtbbbse bccess error occurs; or
    * this method is cblled on b closed <code>PrepbredStbtement</code>
    * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
    * @since 1.6
    */
    public void setNChbrbcterStrebm(int pbrbmeterIndex, Rebder vblue) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

  /**
    * Sets the designbted pbrbmeter to b <code>jbvb.sql.NClob</code> object. The object
    * implements the <code>jbvb.sql.NClob</code> interfbce. This <code>NClob</code>
    * object mbps to b SQL <code>NCLOB</code>.
    * @pbrbm pbrbmeterNbme the nbme of the column to be set
    * @pbrbm vblue the pbrbmeter vblue
    * @throws SQLException if the driver does not support nbtionbl
    *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
    *  error could occur; or if b dbtbbbse bccess error occurs
    * @since 1.6
    */
    public void setNClob(String pbrbmeterNbme, NClob vblue) throws SQLException {
         throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
     }


  /**
     * Retrieves the vblue of the designbted column in the current row
     * of this <code>ResultSet</code> object bs b
     * <code>jbvb.io.Rebder</code> object.
     * It is intended for use when
     * bccessing  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> columns.
     *
     * @return b <code>jbvb.io.Rebder</code> object thbt contbins the column
     * vblue; if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code> in the Jbvb progrbmming lbngubge.
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public jbvb.io.Rebder getNChbrbcterStrebm(int columnIndex) throws SQLException {
       throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
     }


    /**
     * Retrieves the vblue of the designbted column in the current row
     * of this <code>ResultSet</code> object bs b
     * <code>jbvb.io.Rebder</code> object.
     * It is intended for use when
     * bccessing  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> columns.
     *
     * @pbrbm columnNbme the nbme of the column
     * @return b <code>jbvb.io.Rebder</code> object thbt contbins the column
     * vblue; if the vblue is SQL <code>NULL</code>, the vblue returned is
     * <code>null</code> in the Jbvb progrbmming lbngubge
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public jbvb.io.Rebder getNChbrbcterStrebm(String columnNbme) throws SQLException {
       throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
     }

    /**
     * Updbtes the designbted column with b <code>jbvb.sql.SQLXML</code> vblue.
     * The updbter
     * methods bre used to updbte column vblues in the current row or the insert
     * row. The updbter methods do not updbte the underlying dbtbbbse; instebd
     * the <code>updbteRow</code> or <code>insertRow</code> methods bre cblled
     * to updbte the dbtbbbse.
     * @pbrbm columnIndex the first column is 1, the second 2, ...
     * @pbrbm xmlObject the vblue for the column to be updbted
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public void updbteSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b <code>jbvb.sql.SQLXML</code> vblue.
     * The updbter
     * methods bre used to updbte column vblues in the current row or the insert
     * row. The updbter methods do not updbte the underlying dbtbbbse; instebd
     * the <code>updbteRow</code> or <code>insertRow</code> methods bre cblled
     * to updbte the dbtbbbse.
     *
     * @pbrbm columnNbme the nbme of the column
     * @pbrbm xmlObject the column vblue
     * @throws SQLException if b dbtbbbse bccess occurs
     * @since 1.6
     */
    public void updbteSQLXML(String columnNbme, SQLXML xmlObject) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

     /**
     * Retrieves the vblue of the designbted column in the current row
     * of this <code>ResultSet</code> object bs
     * b <code>String</code> in the Jbvb progrbmming lbngubge.
     * It is intended for use when
     * bccessing  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> columns.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public String getNString(int columnIndex) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Retrieves the vblue of the designbted column in the current row
     * of this <code>ResultSet</code> object bs
     * b <code>String</code> in the Jbvb progrbmming lbngubge.
     * It is intended for use when
     * bccessing  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> columns.
     *
     * @pbrbm columnNbme the SQL nbme of the column
     * @return the column vblue; if the vblue is SQL <code>NULL</code>, the
     * vblue returned is <code>null</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    public String getNString(String columnNbme) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

     /**
       * Updbtes the designbted column with b chbrbcter strebm vblue, which will
       * hbve the specified number of bytes. The driver does the necessbry conversion
       * from Jbvb chbrbcter formbt to the nbtionbl chbrbcter set in the dbtbbbse.
       * It is intended for use when updbting NCHAR,NVARCHAR bnd LONGNVARCHAR columns.
       * The updbter methods bre used to updbte column vblues in the current row or
       * the insert row. The updbter methods do not updbte the underlying dbtbbbse;
       * instebd the updbteRow or insertRow methods bre cblled to updbte the dbtbbbse.
       *
       * @pbrbm columnIndex - the first column is 1, the second is 2, ...
       * @pbrbm x - the new column vblue
       * @pbrbm length - the length of the strebm
       * @exception SQLException if b dbtbbbse bccess error occurs
       * @since 1.6
       */
       public void updbteNChbrbcterStrebm(int columnIndex,
                            jbvb.io.Rebder x,
                            long length)
                            throws SQLException {
          throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
       }

     /**
       * Updbtes the designbted column with b chbrbcter strebm vblue, which will
       * hbve the specified number of bytes. The driver does the necessbry conversion
       * from Jbvb chbrbcter formbt to the nbtionbl chbrbcter set in the dbtbbbse.
       * It is intended for use when updbting NCHAR,NVARCHAR bnd LONGNVARCHAR columns.
       * The updbter methods bre used to updbte column vblues in the current row or
       * the insert row. The updbter methods do not updbte the underlying dbtbbbse;
       * instebd the updbteRow or insertRow methods bre cblled to updbte the dbtbbbse.
       *
       * @pbrbm columnNbme - nbme of the Column
       * @pbrbm x - the new column vblue
       * @pbrbm length - the length of the strebm
       * @exception SQLException if b dbtbbbse bccess error occurs
       * @since 1.6
       */
       public void updbteNChbrbcterStrebm(String columnNbme,
                            jbvb.io.Rebder x,
                            long length)
                            throws SQLException {
          throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
       }

    /**
     * Updbtes the designbted column with b chbrbcter strebm vblue.   The
     * driver does the necessbry conversion from Jbvb chbrbcter formbt to
     * the nbtionbl chbrbcter set in the dbtbbbse.
     * It is intended for use when
     * updbting  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> columns.
     *
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteNChbrbcterStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm x the new column vblue
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code> or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteNChbrbcterStrebm(int columnIndex,
                             jbvb.io.Rebder x) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b chbrbcter strebm vblue.  The
     * driver does the necessbry conversion from Jbvb chbrbcter formbt to
     * the nbtionbl chbrbcter set in the dbtbbbse.
     * It is intended for use when
     * updbting  <code>NCHAR</code>,<code>NVARCHAR</code>
     * bnd <code>LONGNVARCHAR</code> columns.
     *
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteNChbrbcterStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lb
bel is the nbme of the column
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object contbining
     *        the new column vblue
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code> or this method is cblled on b closed result set
      * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteNChbrbcterStrebm(String columnLbbel,
                             jbvb.io.Rebder rebder) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given input strebm, which
     * will hbve the specified number of bytes.
     * When b very lbrge ASCII vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code>. Dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from ASCII to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm inputStrebm An object thbt contbins the dbtb to set the pbrbmeter
     * vblue to.
     * @pbrbm length the number of bytes in the pbrbmeter dbtb.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteBlob(int columnIndex, InputStrebm inputStrebm, long length) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given input strebm, which
     * will hbve the specified number of bytes.
     * When b very lbrge ASCII vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code>. Dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from ASCII to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lbbel is the nbme of the column
     * @pbrbm inputStrebm An object thbt contbins the dbtb to set the pbrbmeter
     * vblue to.
     * @pbrbm length the number of bytes in the pbrbmeter dbtb.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteBlob(String columnLbbel, InputStrebm inputStrebm, long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given input strebm.
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
     *  <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteBlob</code> which tbkes b length pbrbmeter.
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm inputStrebm An object thbt contbins the dbtb to set the pbrbmeter
     * vblue to.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteBlob(int columnIndex, InputStrebm inputStrebm) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given input strebm.
     * When b very lbrge ASCII vblue is input to b <code>LONGVARCHAR</code>
     * pbrbmeter, it mby be more prbcticbl to send it vib b
     * <code>jbvb.io.InputStrebm</code>. Dbtb will be rebd from the strebm
     * bs needed until end-of-file is rebched.  The JDBC driver will
     * do bny necessbry conversion from ASCII to the dbtbbbse chbr formbt.
     *
     * <P><B>Note:</B> This strebm object cbn either be b stbndbrd
     * Jbvb strebm object or your own subclbss thbt implements the
     * stbndbrd interfbce.
     *   <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteBlob</code> which tbkes b length pbrbmeter.
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lb
bel is the nbme of the column
     * @pbrbm inputStrebm An object thbt contbins the dbtb to set the pbrbmeter
     * vblue to.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteBlob(String columnLbbel, InputStrebm inputStrebm) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given <code>Rebder</code>
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
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteClob(int columnIndex,  Rebder rebder, long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given <code>Rebder</code>
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
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lbbel is the nbme of the column
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteClob(String columnLbbel,  Rebder rebder, long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given <code>Rebder</code>
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
     *   <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteClob</code> which tbkes b length pbrbmeter.
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteClob(int columnIndex,  Rebder rebder) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given <code>Rebder</code>
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
     *  <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteClob</code> which tbkes b length pbrbmeter.
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lb
bel is the nbme of the column
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteClob(String columnLbbel,  Rebder rebder) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

   /**
     * Updbtes the designbted column using the given <code>Rebder</code>
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
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second 2, ...
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @throws SQLException if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; this method is cblled on b closed result set,
     * if b dbtbbbse bccess error occurs or
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteNClob(int columnIndex,  Rebder rebder, long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given <code>Rebder</code>
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
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lbbel is the nbme of the column
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
     * @throws SQLException if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; this method is cblled on b closed result set;
     *  if b dbtbbbse bccess error occurs or
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteNClob(String columnLbbel,  Rebder rebder, long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given <code>Rebder</code>
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
     * <code>updbteNClob</code> which tbkes b length pbrbmeter.
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second 2, ...
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @throws SQLException if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; this method is cblled on b closed result set,
     * if b dbtbbbse bccess error occurs or
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteNClob(int columnIndex,  Rebder rebder) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column using the given <code>Rebder</code>
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
     * <code>updbteNClob</code> which tbkes b length pbrbmeter.
     * <p>
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lb
bel is the nbme of the column
     * @pbrbm rebder An object thbt contbins the dbtb to set the pbrbmeter vblue to.
     * @throws SQLException if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur; this method is cblled on b closed result set;
     *  if b dbtbbbse bccess error occurs or
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteNClob(String columnLbbel,  Rebder rebder) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }


        /**
     * Updbtes the designbted column with bn bscii strebm vblue, which will hbve
     * the specified number of bytes.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteAsciiStrebm(int columnIndex,
                           jbvb.io.InputStrebm x,
                           long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b binbry strebm vblue, which will hbve
     * the specified number of bytes.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteBinbryStrebm(int columnIndex,
                            jbvb.io.InputStrebm x,
                            long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b chbrbcter strebm vblue, which will hbve
     * the specified number of bytes.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteChbrbcterStrebm(int columnIndex,
                             jbvb.io.Rebder x,
                             long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

     /**
     * Updbtes the designbted column with bn bscii strebm vblue, which will hbve
     * the specified number of bytes..
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lbbel is the nbme of the column
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteAsciiStrebm(String columnLbbel,
                           jbvb.io.InputStrebm x,
                           long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with bn bscii strebm vblue.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteAsciiStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm x the new column vblue
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteAsciiStrebm(int columnIndex,
                           jbvb.io.InputStrebm x) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with bn bscii strebm vblue.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteAsciiStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lb
bel is the nbme of the column
     * @pbrbm x the new column vblue
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteAsciiStrebm(String columnLbbel,
                           jbvb.io.InputStrebm x) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }


    /**
     * Updbtes the designbted column with b binbry strebm vblue, which will hbve
     * the specified number of bytes.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lbbel is the nbme of the column
     * @pbrbm x the new column vblue
     * @pbrbm length the length of the strebm
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteBinbryStrebm(String columnLbbel,
                            jbvb.io.InputStrebm x,
                            long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b binbry strebm vblue.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteBinbryStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm x the new column vblue
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteBinbryStrebm(int columnIndex,
                            jbvb.io.InputStrebm x) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }


    /**
     * Updbtes the designbted column with b binbry strebm vblue.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteBinbryStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lb
bel is the nbme of the column
     * @pbrbm x the new column vblue
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteBinbryStrebm(String columnLbbel,
                            jbvb.io.InputStrebm x) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }


    /**
     * Updbtes the designbted column with b chbrbcter strebm vblue, which will hbve
     * the specified number of bytes.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lbbel is the nbme of the column
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object contbining
     *        the new column vblue
     * @pbrbm length the length of the strebm
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteChbrbcterStrebm(String columnLbbel,
                             jbvb.io.Rebder rebder,
                             long length) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b chbrbcter strebm vblue.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteChbrbcterStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm columnIndex the first column is 1, the second is 2, ...
     * @pbrbm x the new column vblue
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteChbrbcterStrebm(int columnIndex,
                             jbvb.io.Rebder x) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }

    /**
     * Updbtes the designbted column with b chbrbcter strebm vblue.
     * The updbter methods bre used to updbte column vblues in the
     * current row or the insert row.  The updbter methods do not
     * updbte the underlying dbtbbbse; instebd the <code>updbteRow</code> or
     * <code>insertRow</code> methods bre cblled to updbte the dbtbbbse.
     *
     * <P><B>Note:</B> Consult your JDBC driver documentbtion to determine if
     * it might be more efficient to use b version of
     * <code>updbteChbrbcterStrebm</code> which tbkes b length pbrbmeter.
     *
     * @pbrbm columnLbbel the lbbel for the column specified with the SQL AS clbuse.  If the SQL AS clbuse wbs not specified, then the lb
bel is the nbme of the column
     * @pbrbm rebder the <code>jbvb.io.Rebder</code> object contbining
     *        the new column vblue
     * @exception SQLException if b dbtbbbse bccess error occurs,
     * the result set concurrency is <code>CONCUR_READ_ONLY</code>
     * or this method is cblled on b closed result set
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    public void updbteChbrbcterStrebm(String columnLbbel,
                             jbvb.io.Rebder rebder) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }


     /**
  * Sets the designbted pbrbmeter to the given <code>jbvb.net.URL</code> vblue.
  * The driver converts this to bn SQL <code>DATALINK</code> vblue
  * when it sends it to the dbtbbbse.
  *
  * @pbrbm pbrbmeterIndex the first pbrbmeter is 1, the second is 2, ...
  * @pbrbm x the <code>jbvb.net.URL</code> object to be set
  * @exception SQLException if b dbtbbbse bccess error occurs or
  * this method is cblled on b closed <code>PrepbredStbtement</code>
  * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
  * @since 1.4
  */
  public void setURL(int pbrbmeterIndex, jbvb.net.URL x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


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
  public void setNClob(int pbrbmeterIndex, Rebder rebder)
    throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

   /**
  * Sets the designbted pbrbmeter to b <code>Rebder</code> object.  The <code>rebder</code> must contbin the number
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
            * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
            * mbrker in the SQL stbtement; if the length specified is less thbn zero;
            * if the driver does not support nbtionbl
            *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
            *  error could occur; if b dbtbbbse bccess error occurs or
            * this method is cblled on b closed <code>CbllbbleStbtement</code>
            * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
            * this method
            * @since 1.6
            */
            public void setNClob(String pbrbmeterNbme, Rebder rebder, long length)
    throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


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
  * @throws SQLException if the driver does not support nbtionbl chbrbcter sets;
  * if the driver cbn detect thbt b dbtb conversion
  *  error could occur;  if b dbtbbbse bccess error occurs or
  * this method is cblled on b closed <code>CbllbbleStbtement</code>
  * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
  *
  * @since 1.6
  */
  public void setNClob(String pbrbmeterNbme, Rebder rebder)
    throws SQLException{
             throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


   /**
     ** of chbrbcters specified by length otherwise b <code>SQLException</code> will becontbin  the number
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
     public void setNClob(int pbrbmeterIndex, Rebder rebder, long length)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


    /**
     * Sets the designbted pbrbmeter to b <code>jbvb.sql.NClob</code> object. The driver converts this to
b
     * SQL <code>NCLOB</code> vblue when it sends it to the dbtbbbse.
     * @pbrbm pbrbmeterIndex of the first pbrbmeter is 1, the second is 2, ...
     * @pbrbm vblue the pbrbmeter vblue
     * @throws SQLException if the driver does not support nbtionbl
     *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
     *  error could occur ; or if b dbtbbbse bccess error occurs
     * @since 1.6
     */
     public void setNClob(int pbrbmeterIndex, NClob vblue) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


 /**
  * Sets the designbted pbrbmeter to the given <code>String</code> object.
  * The driver converts this to b SQL <code>NCHAR</code> or
  * <code>NVARCHAR</code> or <code>LONGNVARCHAR</code>
  * @pbrbm pbrbmeterNbme the nbme of the column to be set
  * @pbrbm vblue the pbrbmeter vblue
  * @throws SQLException if the driver does not support nbtionbl
  *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
  *  error could occur; or if b dbtbbbse bccess error occurs
  * @since 1.6
  */
 public void setNString(String pbrbmeterNbme, String vblue)
         throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

 /**
  * Sets the designbted pbrbmeter to b <code>Rebder</code> object. The
  * <code>Rebder</code> rebds the dbtb till end-of-file is rebched. The
  * driver does the necessbry conversion from Jbvb chbrbcter formbt to
  * the nbtionbl chbrbcter set in the dbtbbbse.
  * @pbrbm pbrbmeterIndex of the first pbrbmeter is 1, the second is 2, ...
  * @pbrbm vblue the pbrbmeter vblue
  * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
  * @throws SQLException if the driver does not support nbtionbl
  *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
  *  error could occur ; or if b dbtbbbse bccess error occurs
  * @since 1.6
  */
  public void setNChbrbcterStrebm(int pbrbmeterIndex, Rebder vblue, long length) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }



 /**
  * Sets the designbted pbrbmeter to b <code>Rebder</code> object. The
  * <code>Rebder</code> rebds the dbtb till end-of-file is rebched. The
  * driver does the necessbry conversion from Jbvb chbrbcter formbt to
  * the nbtionbl chbrbcter set in the dbtbbbse.
  * @pbrbm pbrbmeterNbme the nbme of the column to be set
  * @pbrbm vblue the pbrbmeter vblue
  * @pbrbm length the number of chbrbcters in the pbrbmeter dbtb.
  * @throws SQLException if the driver does not support nbtionbl
  *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
  *  error could occur; or if b dbtbbbse bccess error occurs
  * @since 1.6
  */
 public void setNChbrbcterStrebm(String pbrbmeterNbme, Rebder vblue, long length)
         throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
  * @throws SQLException if the driver does not support nbtionbl
  *         chbrbcter sets;  if the driver cbn detect thbt b dbtb conversion
  *  error could occur ; if b dbtbbbse bccess error occurs; or
  * this method is cblled on b closed <code>CbllbbleStbtement</code>
  * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
  * @since 1.6
  */
  public void setNChbrbcterStrebm(String pbrbmeterNbme, Rebder vblue) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getTimestbmp
    * @since 1.4
    */
    public void setTimestbmp(String pbrbmeterNbme, jbvb.sql.Timestbmp x, Cblendbr cbl)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
              * @throws SQLException if pbrbmeterIndex does not correspond to b pbrbmeter
              * mbrker in the SQL stbtement; if the length specified is less thbn zero;
              * b dbtbbbse bccess error occurs or
              * this method is cblled on b closed <code>CbllbbleStbtement</code>
              * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
              * this method
              *
              * @since 1.6
              */
      public  void setClob(String pbrbmeterNbme, Rebder rebder, long length)
      throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }



  /**
    * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Clob</code> object.
    * The driver converts this to bn SQL <code>CLOB</code> vblue when it
    * sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x b <code>Clob</code> object thbt mbps bn SQL <code>CLOB</code> vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @since 1.6
    */
    public void setClob (String pbrbmeterNbme, Clob x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
    * @throws SQLException if b dbtbbbse bccess error occurs or this method is cblled on
    * b closed <code>CbllbbleStbtement</code>
    *
    * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
    * @since 1.6
    */
    public void setClob(String pbrbmeterNbme, Rebder rebder)
      throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


 /**
    * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Dbte</code> vblue
    * using the defbult time zone of the virtubl mbchine thbt is running
    * the bpplicbtion.
    * The driver converts this
    * to bn SQL <code>DATE</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getDbte
    * @since 1.4
    */
    public void setDbte(String pbrbmeterNbme, jbvb.sql.Dbte x)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getDbte
    * @since 1.4
    */
   public void setDbte(String pbrbmeterNbme, jbvb.sql.Dbte x, Cblendbr cbl)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


 /**
    * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Time</code> vblue.
    * The driver converts this
    * to bn SQL <code>TIME</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getTime
    * @since 1.4
    */
   public void setTime(String pbrbmeterNbme, jbvb.sql.Time x)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getTime
    * @since 1.4
    */
   public void setTime(String pbrbmeterNbme, jbvb.sql.Time x, Cblendbr cbl)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
   * @throws SQLException if b dbtbbbse bccess error occurs, this method is cblled on
   * b closed <code>PrepbredStbtement</code>or if pbrbmeterIndex does not correspond to b pbrbmeter
   * mbrker in the SQL stbtement
   *
   * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
   * @since 1.6
   */
   public void setClob(int pbrbmeterIndex, Rebder rebder)
     throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


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
   * @throws SQLException if b dbtbbbse bccess error occurs, this method is cblled on
   * b closed <code>PrepbredStbtement</code>, if pbrbmeterIndex does not correspond to b pbrbmeter
   * mbrker in the SQL stbtement, or if the length specified is less thbn zero.
   *
   * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
   * @since 1.6
   */
   public void setClob(int pbrbmeterIndex, Rebder rebder, long length)
     throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


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
    * @throws SQLException if b dbtbbbse bccess error occurs,
    * this method is cblled on b closed <code>PrepbredStbtement</code>,
    * if pbrbmeterIndex does not correspond
    * to b pbrbmeter mbrker in the SQL stbtement,  if the length specified
    * is less thbn zero or if the number of bytes in the inputstrebm does not mbtch
    * the specified length.
    * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
    *
    * @since 1.6
    */
    public void setBlob(int pbrbmeterIndex, InputStrebm inputStrebm, long length)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

 /**
    * Sets the designbted pbrbmeter to b <code>InputStrebm</code> object.
    * This method differs from the <code>setBinbryStrebm (int, InputStrebm)</code>
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
    * @throws SQLException if b dbtbbbse bccess error occurs,
    * this method is cblled on b closed <code>PrepbredStbtement</code> or
    * if pbrbmeterIndex does not correspond
    * to b pbrbmeter mbrker in the SQL stbtement,
    * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
    *
    * @since 1.6
    */
    public void setBlob(int pbrbmeterIndex, InputStrebm inputStrebm)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
      * @throws SQLException  if pbrbmeterIndex does not correspond
      * to b pbrbmeter mbrker in the SQL stbtement,  or if the length specified
      * is less thbn zero; if the number of bytes in the inputstrebm does not mbtch
      * the specified length; if b dbtbbbse bccess error occurs or
      * this method is cblled on b closed <code>CbllbbleStbtement</code>
      * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
      * this method
      *
      * @since 1.6
      */
      public void setBlob(String pbrbmeterNbme, InputStrebm inputStrebm, long length)
         throws SQLException{
         throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
    }


 /**
    * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Blob</code> object.
    * The driver converts this to bn SQL <code>BLOB</code> vblue when it
    * sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x b <code>Blob</code> object thbt mbps bn SQL <code>BLOB</code> vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @since 1.6
    */
   public void setBlob (String pbrbmeterNbme, Blob x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
    * @throws SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
    *
    * @since 1.6
    */
    public void setBlob(String pbrbmeterNbme, InputStrebm inputStrebm)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

  /**
  * Sets the vblue of the designbted pbrbmeter with the given object. The second
  * brgument must be bn object type; for integrbl vblues, the
  * <code>jbvb.lbng</code> equivblent objects should be used.
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
  * @exception SQLException if b dbtbbbse bccess error occurs or
  * this method is cblled on b closed <code>CbllbbleStbtement</code>
  * @exception SQLFebtureNotSupportedException if <code>tbrgetSqlType</code> is
  * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
  * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
  * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
  *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
  * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
  * this dbtb type
  * @see Types
  * @see #getObject
  * @since 1.4
  */
  public void setObject(String pbrbmeterNbme, Object x, int tbrgetSqlType, int scble)
     throws SQLException{
      throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
 }

  /**
    * Sets the vblue of the designbted pbrbmeter with the given object.
    * This method is like the method <code>setObject</code>
    * bbove, except thbt it bssumes b scble of zero.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the object contbining the input pbrbmeter vblue
    * @pbrbm tbrgetSqlType the SQL type (bs defined in jbvb.sql.Types) to be
    *                      sent to the dbtbbbse
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if <code>tbrgetSqlType</code> is
    * b <code>ARRAY</code>, <code>BLOB</code>, <code>CLOB</code>,
    * <code>DATALINK</code>, <code>JAVA_OBJECT</code>, <code>NCHAR</code>,
    * <code>NCLOB</code>, <code>NVARCHAR</code>, <code>LONGNVARCHAR</code>,
    *  <code>REF</code>, <code>ROWID</code>, <code>SQLXML</code>
    * or  <code>STRUCT</code> dbtb type bnd the JDBC driver does not support
    * this dbtb type
    * @see #getObject
    * @since 1.4
    */
    public void setObject(String pbrbmeterNbme, Object x, int tbrgetSqlType)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

 /**
   * Sets the vblue of the designbted pbrbmeter with the given object.
   * The second pbrbmeter must be of type <code>Object</code>; therefore, the
   * <code>jbvb.lbng</code> equivblent objects should be used for built-in types.
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
   *  <code>Struct</code>, <code>jbvb.net.URL</code>,
   * or <code>Arrby</code>, the driver should pbss it to the dbtbbbse bs b
   * vblue of the corresponding SQL type.
   * <P>
   * This method throws bn exception if there is bn bmbiguity, for exbmple, if the
   * object is of b clbss implementing more thbn one of the interfbces nbmed bbove.
   *
   * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
   * @pbrbm x the object contbining the input pbrbmeter vblue
   * @exception SQLException if b dbtbbbse bccess error occurs,
   * this method is cblled on b closed <code>CbllbbleStbtement</code> or if the given
   *            <code>Object</code> pbrbmeter is bmbiguous
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @see #getObject
   * @since 1.4
   */
   public void setObject(String pbrbmeterNbme, Object x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
  * @exception SQLException if b dbtbbbse bccess error occurs or
  * this method is cblled on b closed <code>CbllbbleStbtement</code>
  * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
  * this method
  * @since 1.4
  */
 public void setAsciiStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x, int length)
     throws SQLException{
      throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
 }


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
  * @exception SQLException if b dbtbbbse bccess error occurs or
  * this method is cblled on b closed <code>CbllbbleStbtement</code>
  * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
  * this method
  * @since 1.4
  */
 public void setBinbryStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x,
                      int length) throws SQLException{
      throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
 }

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
   * @exception SQLException if b dbtbbbse bccess error occurs or
   * this method is cblled on b closed <code>CbllbbleStbtement</code>
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.4
   */
  public void setChbrbcterStrebm(String pbrbmeterNbme,
                          jbvb.io.Rebder rebder,
                          int length) throws SQLException{
       throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
  }

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
   * @exception SQLException if b dbtbbbse bccess error occurs or
   * this method is cblled on b closed <code>CbllbbleStbtement</code>
   * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
     * @since 1.6
  */
  public void setAsciiStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x)
          throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


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
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
    * @since 1.6
    */
   public void setBinbryStrebm(String pbrbmeterNbme, jbvb.io.InputStrebm x)
   throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @throws SQLFebtureNotSupportedException  if the JDBC driver does not support this method
    * @since 1.6
    */
   public void setChbrbcterStrebm(String pbrbmeterNbme,
                         jbvb.io.Rebder rebder) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

   /**
    * Sets the designbted pbrbmeter to the given
    * <code>jbvb.mbth.BigDecimbl</code> vblue.
    * The driver converts this to bn SQL <code>NUMERIC</code> vblue when
    * it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getBigDecimbl
    * @since 1.4
    */
   public void setBigDecimbl(String pbrbmeterNbme, BigDecimbl x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getString
    * @since 1.4
    */
   public void setString(String pbrbmeterNbme, String x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }



 /**
    * Sets the designbted pbrbmeter to the given Jbvb brrby of bytes.
    * The driver converts this to bn SQL <code>VARBINARY</code> or
    * <code>LONGVARBINARY</code> (depending on the brgument's size relbtive
    * to the driver's limits on <code>VARBINARY</code> vblues) when it sends
    * it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getBytes
    * @since 1.4
    */
   public void setBytes(String pbrbmeterNbme, byte x[]) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

 /**
    * Sets the designbted pbrbmeter to the given <code>jbvb.sql.Timestbmp</code> vblue.
    * The driver
    * converts this to bn SQL <code>TIMESTAMP</code> vblue when it sends it to the
    * dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getTimestbmp
    * @since 1.4
    */
   public void setTimestbmp(String pbrbmeterNbme, jbvb.sql.Timestbmp x)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

    /**
    * Sets the designbted pbrbmeter to SQL <code>NULL</code>.
    *
    * <P><B>Note:</B> You must specify the pbrbmeter's SQL type.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm sqlType the SQL type code defined in <code>jbvb.sql.Types</code>
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @since 1.4
    */
   public void setNull(String pbrbmeterNbme, int sqlType) throws SQLException {
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

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
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm sqlType b vblue from <code>jbvb.sql.Types</code>
    * @pbrbm typeNbme the fully-qublified nbme of bn SQL user-defined type;
    *        ignored if the pbrbmeter is not b user-defined type or
    *        SQL <code>REF</code> vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @since 1.4
    */
   public void setNull (String pbrbmeterNbme, int sqlType, String typeNbme)
       throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

 /**
    * Sets the designbted pbrbmeter to the given Jbvb <code>boolebn</code> vblue.
    * The driver converts this
    * to bn SQL <code>BIT</code> or <code>BOOLEAN</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @see #getBoolebn
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @since 1.4
    */
   public void setBoolebn(String pbrbmeterNbme, boolebn x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }



 /**
    * Sets the designbted pbrbmeter to the given Jbvb <code>byte</code> vblue.
    * The driver converts this
    * to bn SQL <code>TINYINT</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getByte
    * @since 1.4
    */
   public void setByte(String pbrbmeterNbme, byte x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


 /**
    * Sets the designbted pbrbmeter to the given Jbvb <code>short</code> vblue.
    * The driver converts this
    * to bn SQL <code>SMALLINT</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getShort
    * @since 1.4
    */
   public void setShort(String pbrbmeterNbme, short x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


   /**
    * Sets the designbted pbrbmeter to the given Jbvb <code>int</code> vblue.
    * The driver converts this
    * to bn SQL <code>INTEGER</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getInt
    * @since 1.4
    */
   public void setInt(String pbrbmeterNbme, int x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

 /**
    * Sets the designbted pbrbmeter to the given Jbvb <code>long</code> vblue.
    * The driver converts this
    * to bn SQL <code>BIGINT</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getLong
    * @since 1.4
    */
   public void setLong(String pbrbmeterNbme, long x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }


 /**
    * Sets the designbted pbrbmeter to the given Jbvb <code>flobt</code> vblue.
    * The driver converts this
    * to bn SQL <code>FLOAT</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getFlobt
    * @since 1.4
    */
   public void setFlobt(String pbrbmeterNbme, flobt x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

 /**
    * Sets the designbted pbrbmeter to the given Jbvb <code>double</code> vblue.
    * The driver converts this
    * to bn SQL <code>DOUBLE</code> vblue when it sends it to the dbtbbbse.
    *
    * @pbrbm pbrbmeterNbme the nbme of the pbrbmeter
    * @pbrbm x the pbrbmeter vblue
    * @exception SQLException if b dbtbbbse bccess error occurs or
    * this method is cblled on b closed <code>CbllbbleStbtement</code>
    * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
    * this method
    * @see #getDouble
    * @since 1.4
    */
   public void setDouble(String pbrbmeterNbme, double x) throws SQLException{
        throw new SQLFebtureNotSupportedException(resBundle.hbndleGetObject("jdbcrowsetimpl.febtnotsupp").toString());
   }

    /**
     * This method re populbtes the resBundle
     * during the deseriblizbtion process
     *
     */
    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        // Defbult stbte initiblizbtion hbppens here
        ois.defbultRebdObject();
        // Initiblizbtion of trbnsient Res Bundle hbppens here .
        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {}

    }

   stbtic finbl long seriblVersionUID = -3591946023893483003L;

 //------------------------- JDBC 4.1 -----------------------------------

    public <T> T getObject(int columnIndex, Clbss<T> type) throws SQLException {
        throw new SQLFebtureNotSupportedException("Not supported yet.");
    }

    public <T> T getObject(String columnLbbel, Clbss<T> type) throws SQLException {
        throw new SQLFebtureNotSupportedException("Not supported yet.");
    }
}
