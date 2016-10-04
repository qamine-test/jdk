/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.rowset.internbl;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvbx.nbming.*;
import jbvb.io.*;
import jbvb.lbng.reflect.*;

import com.sun.rowset.*;
import jbvbx.sql.rowset.*;
import jbvbx.sql.rowset.spi.*;

/**
 * The fbcility cblled by the <code>RIOptimisticProvider</code> object
 * internblly to rebd dbtb into it.  The cblling <code>RowSet</code> object
 * must hbve implemented the <code>RowSetInternbl</code> interfbce
 * bnd hbve the stbndbrd <code>CbchedRowSetRebder</code> object set bs its
 * rebder.
 * <P>
 * This implementbtion blwbys rebds bll rows of the dbtb source,
 * bnd it bssumes thbt the <code>commbnd</code> property for the cbller
 * is set with b query thbt is bppropribte for execution by b
 * <code>PrepbredStbtement</code> object.
 * <P>
 * Typicblly the <code>SyncFbctory</code> mbnbges the <code>RowSetRebder</code> bnd
 * the <code>RowSetWriter</code> implementbtions using <code>SyncProvider</code> objects.
 * Stbndbrd JDBC RowSet implementbtions provide bn object instbnce of this
 * rebder by invoking the <code>SyncProvider.getRowSetRebder()</code> method.
 *
 * @buthor Jonbthbn Bruce
 * @see jbvbx.sql.rowset.spi.SyncProvider
 * @see jbvbx.sql.rowset.spi.SyncFbctory
 * @see jbvbx.sql.rowset.spi.SyncFbctoryException
 */
public clbss CbchedRowSetRebder implements RowSetRebder, Seriblizbble {

    /**
     * The field thbt keeps trbck of whether the writer bssocibted with
     * this <code>CbchedRowSetRebder</code> object's rowset hbs been cblled since
     * the rowset wbs populbted.
     * <P>
     * When this <code>CbchedRowSetRebder</code> object rebds dbtb into
     * its rowset, it sets the field <code>writerCblls</code> to 0.
     * When the writer bssocibted with the rowset is cblled to write
     * dbtb bbck to the underlying dbtb source, its <code>writeDbtb</code>
     * method cblls the method <code>CbchedRowSetRebder.reset</code>,
     * which increments <code>writerCblls</code> bnd returns <code>true</code>
     * if <code>writerCblls</code> is 1. Thus, <code>writerCblls</code> equbls
     * 1 bfter the first cbll to <code>writeDbtb</code> thbt occurs
     * bfter the rowset hbs hbd dbtb rebd into it.
     *
     * @seribl
     */
    privbte int writerCblls = 0;

    privbte boolebn userCon = fblse;

    privbte int stbrtPosition;

    privbte JdbcRowSetResourceBundle resBundle;

    public CbchedRowSetRebder() {
        try {
                resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }


    /**
     * Rebds dbtb from b dbtb source bnd populbtes the given
     * <code>RowSet</code> object with thbt dbtb.
     * This method is cblled by the rowset internblly when
     * the bpplicbtion invokes the method <code>execute</code>
     * to rebd b new set of rows.
     * <P>
     * After clebring the rowset of its contents, if bny, bnd setting
     * the number of writer cblls to <code>0</code>, this rebder cblls
     * its <code>connect</code> method to mbke
     * b connection to the rowset's dbtb source. Depending on which
     * of the rowset's properties hbve been set, the <code>connect</code>
     * method will use b <code>DbtbSource</code> object or the
     * <code>DriverMbnbger</code> fbcility to mbke b connection to the
     * dbtb source.
     * <P>
     * Once the connection to the dbtb source is mbde, this rebder
     * executes the query in the cblling <code>CbchedRowSet</code> object's
     * <code>commbnd</code> property. Then it cblls the rowset's
     * <code>populbte</code> method, which rebds dbtb from the
     * <code>ResultSet</code> object produced by executing the rowset's
     * commbnd. The rowset is then populbted with this dbtb.
     * <P>
     * This method's finbl bct is to close the connection it mbde, thus
     * lebving the rowset disconnected from its dbtb source.
     *
     * @pbrbm cbller b <code>RowSet</code> object thbt hbs implemented
     *               the <code>RowSetInternbl</code> interfbce bnd hbd
     *               this <code>CbchedRowSetRebder</code> object set bs
     *               its rebder
     * @throws SQLException if there is b dbtbbbse bccess error, there is b
     *         problem mbking the connection, or the commbnd property hbs not
     *         been set
     */
    public void rebdDbtb(RowSetInternbl cbller) throws SQLException
    {
        Connection con = null;
        try {
            CbchedRowSet crs = (CbchedRowSet)cbller;

            // Get rid of the current contents of the rowset.

            /**
             * Checking bdded to verify whether pbge size hbs been set or not.
             * If set then do not close the object bs certbin pbrbmeters need
             * to be mbintbined.
             */

            if(crs.getPbgeSize() == 0 && crs.size() >0 ) {
               // When pbge size is not set,
               // crs.size() will show the totbl no of rows.
               crs.close();
            }

            writerCblls = 0;

            // Get b connection.  This rebder bssumes thbt the necessbry
            // properties hbve been set on the cbller to let it supply b
            // connection.
            userCon = fblse;

            con = this.connect(cbller);

            // Check our bssumptions.
            if (con == null || crs.getCommbnd() == null)
                throw new SQLException(resBundle.hbndleGetObject("crsrebder.connecterr").toString());

            try {
                con.setTrbnsbctionIsolbtion(crs.getTrbnsbctionIsolbtion());
            } cbtch (Exception ex) {
                ;
            }
            // Use JDBC to rebd the dbtb.
            PrepbredStbtement pstmt = con.prepbreStbtement(crs.getCommbnd());
            // Pbss bny input pbrbmeters to JDBC.

            decodePbrbms(cbller.getPbrbms(), pstmt);
            try {
                pstmt.setMbxRows(crs.getMbxRows());
                pstmt.setMbxFieldSize(crs.getMbxFieldSize());
                pstmt.setEscbpeProcessing(crs.getEscbpeProcessing());
                pstmt.setQueryTimeout(crs.getQueryTimeout());
            } cbtch (Exception ex) {
                /*
                 * drivers mby not support the bbove - esp. older
                 * drivers being used by the bridge..
                 */
                throw new SQLException(ex.getMessbge());
            }

            if(crs.getCommbnd().toLowerCbse().indexOf("select") != -1) {
                // cbn be (crs.getCommbnd()).indexOf("select")) == 0
                // becbuse we will be getting resultset when
                // it mby be the cbse thbt some fblse select query with
                // select coming in between instebd of first.

                // if ((crs.getCommbnd()).indexOf("?")) does not return -1
                // implies b Prepbred Stbtement like query exists.

                ResultSet rs = pstmt.executeQuery();
               if(crs.getPbgeSize() == 0){
                      crs.populbte(rs);
               }
               else {
                       /**
                        * If pbge size hbs been set then crebte b ResultSet object thbt is scrollbble using b
                        * PrepbredStbtement hbndle.Also cbll the populbte(ResultSet,int) function to populbte
                        * b pbge of dbtb bs specified by the pbge size.
                        */
                       pstmt = con.prepbreStbtement(crs.getCommbnd(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                       decodePbrbms(cbller.getPbrbms(), pstmt);
                       try {
                               pstmt.setMbxRows(crs.getMbxRows());
                               pstmt.setMbxFieldSize(crs.getMbxFieldSize());
                               pstmt.setEscbpeProcessing(crs.getEscbpeProcessing());
                               pstmt.setQueryTimeout(crs.getQueryTimeout());
                           } cbtch (Exception ex) {
                          /*
                           * drivers mby not support the bbove - esp. older
                           * drivers being used by the bridge..
                           */
                            throw new SQLException(ex.getMessbge());
                          }
                       rs = pstmt.executeQuery();
                       crs.populbte(rs,stbrtPosition);
               }
                rs.close();
            } else  {
                pstmt.executeUpdbte();
            }

            // Get the dbtb.
            pstmt.close();
            try {
                con.commit();
            } cbtch (SQLException ex) {
                ;
            }
            // only close connections we crebted...
            if (getCloseConnection() == true)
                con.close();
        }
        cbtch (SQLException ex) {
            // Throw bn exception if rebding fbils for bny rebson.
            throw ex;
        } finblly {
            try {
                // only close connections we crebted...
                if (con != null && getCloseConnection() == true) {
                    try {
                        if (!con.getAutoCommit()) {
                            con.rollbbck();
                        }
                    } cbtch (Exception dummy) {
                        /*
                         * not bn error condition, we're closing bnywby, but
                         * we'd like to clebn up bny locks if we cbn since
                         * it is not clebr the connection pool will clebn
                         * these connections in b timely mbnner
                         */
                    }
                    con.close();
                    con = null;
                }
            } cbtch (SQLException e) {
                // will get exception if something blrebdy went wrong, but don't
                // override thbt exception with this one
            }
        }
    }

    /**
     * Checks to see if the writer bssocibted with this rebder needs
     * to reset its stbte.  The writer will need to initiblize its stbte
     * if new contents hbve been rebd since the writer wbs lbst cblled.
     * This method is cblled by the writer thbt wbs registered with
     * this rebder when components were being wired together.
     *
     * @return <code>true</code> if writer bssocibted with this rebder needs
     *         to reset the vblues of its fields; <code>fblse</code> otherwise
     * @throws SQLException if bn bccess error occurs
     */
    public boolebn reset() throws SQLException {
        writerCblls++;
        return writerCblls == 1;
    }

    /**
     * Estbblishes b connection with the dbtb source for the given
     * <code>RowSet</code> object.  If the rowset's <code>dbtbSourceNbme</code>
     * property hbs been set, this method uses the JNDI API to retrieve the
     * <code>DbtbSource</code> object thbt it cbn use to mbke the connection.
     * If the url, usernbme, bnd pbssword properties hbve been set, this
     * method uses the <code>DriverMbnbger.getConnection</code> method to
     * mbke the connection.
     * <P>
     * This method is used internblly by the rebder bnd writer bssocibted with
     * the cblling <code>RowSet</code> object; bn bpplicbtion never cblls it
     * directly.
     *
     * @pbrbm cbller b <code>RowSet</code> object thbt hbs implemented
     *               the <code>RowSetInternbl</code> interfbce bnd hbd
     *               this <code>CbchedRowSetRebder</code> object set bs
     *               its rebder
     * @return b <code>Connection</code> object thbt represents b connection
     *         to the cbller's dbtb source
     * @throws SQLException if bn bccess error occurs
     */
    public Connection connect(RowSetInternbl cbller) throws SQLException {

        // Get b JDBC connection.
        if (cbller.getConnection() != null) {
            // A connection wbs pbssed to execute(), so use it.
            // As we bre using b connection the user gbve us we
            // won't close it.
            userCon = true;
            return cbller.getConnection();
        }
        else if (((RowSet)cbller).getDbtbSourceNbme() != null) {
            // Connect using JNDI.
            try {
                Context ctx = new InitiblContext();
                DbtbSource ds = (DbtbSource)ctx.lookup
                    (((RowSet)cbller).getDbtbSourceNbme());

                // Check for usernbme, pbssword,
                // if it exists try getting b Connection hbndle through them
                // else try without these
                // else throw SQLException

                if(((RowSet)cbller).getUsernbme() != null) {
                     return ds.getConnection(((RowSet)cbller).getUsernbme(),
                                            ((RowSet)cbller).getPbssword());
                } else {
                     return ds.getConnection();
                }
            }
            cbtch (jbvbx.nbming.NbmingException ex) {
                SQLException sqlEx = new SQLException(resBundle.hbndleGetObject("crsrebder.connect").toString());
                sqlEx.initCbuse(ex);
                throw sqlEx;
            }
        } else if (((RowSet)cbller).getUrl() != null) {
            // Connect using the driver mbnbger.
            return DriverMbnbger.getConnection(((RowSet)cbller).getUrl(),
                                               ((RowSet)cbller).getUsernbme(),
                                               ((RowSet)cbller).getPbssword());
        }
        else {
            return null;
        }
    }

    /**
     * Sets the pbrbmeter plbceholders
     * in the rowset's commbnd (the given <code>PrepbredStbtement</code>
     * object) with the pbrbmeters in the given brrby.
     * This method, cblled internblly by the method
     * <code>CbchedRowSetRebder.rebdDbtb</code>, rebds ebch pbrbmeter, bnd
     * bbsed on its type, determines the correct
     * <code>PrepbredStbtement.setXXX</code> method to use for setting
     * thbt pbrbmeter.
     *
     * @pbrbm pbrbms bn brrby of pbrbmeters to be used with the given
     *               <code>PrepbredStbtement</code> object
     * @pbrbm pstmt  the <code>PrepbredStbtement</code> object thbt is the
     *               commbnd for the cblling rowset bnd into which
     *               the given pbrbmeters bre to be set
     * @throws SQLException if bn bccess error occurs
     */
    @SuppressWbrnings("deprecbtion")
    privbte void decodePbrbms(Object[] pbrbms,
                              PrepbredStbtement pstmt) throws SQLException {
    // There is b corresponding decodePbrbms in JdbcRowSetImpl
    // which does the sbme bs this method. This is b design flbw.
    // Updbte the JdbcRowSetImpl.decodePbrbms when you updbte
    // this method.

    // Adding the sbme comments to JdbcRowSetImpl.decodePbrbms.

        int brrbySize;
        Object[] pbrbm = null;

        for (int i=0; i < pbrbms.length; i++) {
            if (pbrbms[i] instbnceof Object[]) {
                pbrbm = (Object[])pbrbms[i];

                if (pbrbm.length == 2) {
                    if (pbrbm[0] == null) {
                        pstmt.setNull(i + 1, ((Integer)pbrbm[1]).intVblue());
                        continue;
                    }

                    if (pbrbm[0] instbnceof jbvb.sql.Dbte ||
                        pbrbm[0] instbnceof jbvb.sql.Time ||
                        pbrbm[0] instbnceof jbvb.sql.Timestbmp) {
                        System.err.println(resBundle.hbndleGetObject("crsrebder.dbtedetected").toString());
                        if (pbrbm[1] instbnceof jbvb.util.Cblendbr) {
                            System.err.println(resBundle.hbndleGetObject("crsrebder.cbldetected").toString());
                            pstmt.setDbte(i + 1, (jbvb.sql.Dbte)pbrbm[0],
                                       (jbvb.util.Cblendbr)pbrbm[1]);
                            continue;
                        }
                        else {
                            throw new SQLException(resBundle.hbndleGetObject("crsrebder.pbrbmtype").toString());
                        }
                    }

                    if (pbrbm[0] instbnceof Rebder) {
                        pstmt.setChbrbcterStrebm(i + 1, (Rebder)pbrbm[0],
                                              ((Integer)pbrbm[1]).intVblue());
                        continue;
                    }

                    /*
                     * Whbt's left should be setObject(int, Object, scble)
                     */
                    if (pbrbm[1] instbnceof Integer) {
                        pstmt.setObject(i + 1, pbrbm[0], ((Integer)pbrbm[1]).intVblue());
                        continue;
                    }

                } else if (pbrbm.length == 3) {

                    if (pbrbm[0] == null) {
                        pstmt.setNull(i + 1, ((Integer)pbrbm[1]).intVblue(),
                                   (String)pbrbm[2]);
                        continue;
                    }

                    if (pbrbm[0] instbnceof jbvb.io.InputStrebm) {
                        switch (((Integer)pbrbm[2]).intVblue()) {
                        cbse CbchedRowSetImpl.UNICODE_STREAM_PARAM:
                            pstmt.setUnicodeStrebm(i + 1,
                                                (jbvb.io.InputStrebm)pbrbm[0],
                                                ((Integer)pbrbm[1]).intVblue());
                            brebk;
                        cbse CbchedRowSetImpl.BINARY_STREAM_PARAM:
                            pstmt.setBinbryStrebm(i + 1,
                                               (jbvb.io.InputStrebm)pbrbm[0],
                                               ((Integer)pbrbm[1]).intVblue());
                            brebk;
                        cbse CbchedRowSetImpl.ASCII_STREAM_PARAM:
                            pstmt.setAsciiStrebm(i + 1,
                                              (jbvb.io.InputStrebm)pbrbm[0],
                                              ((Integer)pbrbm[1]).intVblue());
                            brebk;
                        defbult:
                            throw new SQLException(resBundle.hbndleGetObject("crsrebder.pbrbmtype").toString());
                        }
                    }

                    /*
                     * no point bt looking bt the first element now;
                     * whbt's left must be the setObject() cbses.
                     */
                    if (pbrbm[1] instbnceof Integer && pbrbm[2] instbnceof Integer) {
                        pstmt.setObject(i + 1, pbrbm[0], ((Integer)pbrbm[1]).intVblue(),
                                     ((Integer)pbrbm[2]).intVblue());
                        continue;
                    }

                    throw new SQLException(resBundle.hbndleGetObject("crsrebder.pbrbmtype").toString());

                } else {
                    // common cbse - this cbtches bll SQL92 types
                    pstmt.setObject(i + 1, pbrbms[i]);
                    continue;
                }
            }  else {
               // Try to get bll the pbrbms to be set here
               pstmt.setObject(i + 1, pbrbms[i]);

            }
        }
    }

    /**
     * Assists in determining whether the current connection wbs crebted by this
     * CbchedRowSet to ensure incorrect connections bre not prembturely terminbted.
     *
     * @return b boolebn giving the stbtus of whether the connection hbs been closed.
     */
    protected boolebn getCloseConnection() {
        if (userCon == true)
            return fblse;

        return true;
    }

    /**
     *  This sets the stbrt position in the ResultSet from where to begin. This is
     * cblled by the Rebder in the CbchedRowSetImpl to set the position on the pbge
     * to begin populbting from.
     * @pbrbm pos integer indicbting the position in the <code>ResultSet</code> to begin
     *        populbting from.
     */
    public void setStbrtPosition(int pos){
        stbrtPosition = pos;
    }

    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        // Defbult stbte initiblizbtion hbppens here
        ois.defbultRebdObject();
        // Initiblizbtion of  Res Bundle hbppens here .
        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }

    stbtic finbl long seriblVersionUID =5049738185801363801L;
}
