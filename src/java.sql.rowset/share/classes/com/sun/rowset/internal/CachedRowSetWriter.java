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

pbckbge com.sun.rowset.internbl;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.util.*;
import jbvb.io.*;
import sun.reflect.misc.ReflectUtil;

import com.sun.rowset.*;
import jbvb.text.MessbgeFormbt;
import jbvbx.sql.rowset.*;
import jbvbx.sql.rowset.seribl.SQLInputImpl;
import jbvbx.sql.rowset.seribl.SeriblArrby;
import jbvbx.sql.rowset.seribl.SeriblBlob;
import jbvbx.sql.rowset.seribl.SeriblClob;
import jbvbx.sql.rowset.seribl.SeriblStruct;
import jbvbx.sql.rowset.spi.*;


/**
 * The fbcility cblled on internblly by the <code>RIOptimisticProvider</code> implementbtion to
 * propbgbte chbnges bbck to the dbtb source from which the rowset got its dbtb.
 * <P>
 * A <code>CbchedRowSetWriter</code> object, cblled b writer, hbs the public
 * method <code>writeDbtb</code> for writing modified dbtb to the underlying dbtb source.
 * This method is invoked by the rowset internblly bnd is never invoked directly by bn bpplicbtion.
 * A writer blso hbs public methods for setting bnd getting
 * the <code>CbchedRowSetRebder</code> object, cblled b rebder, thbt is bssocibted
 * with the writer. The rembinder of the methods in this clbss bre privbte bnd
 * bre invoked internblly, either directly or indirectly, by the method
 * <code>writeDbtb</code>.
 * <P>
 * Typicblly the <code>SyncFbctory</code> mbnbges the <code>RowSetRebder</code> bnd
 * the <code>RowSetWriter</code> implementbtions using <code>SyncProvider</code> objects.
 * Stbndbrd JDBC RowSet implementbtions provide bn object instbnce of this
 * writer by invoking the <code>SyncProvider.getRowSetWriter()</code> method.
 *
 * @version 0.2
 * @buthor Jonbthbn Bruce
 * @see jbvbx.sql.rowset.spi.SyncProvider
 * @see jbvbx.sql.rowset.spi.SyncFbctory
 * @see jbvbx.sql.rowset.spi.SyncFbctoryException
 */
public clbss CbchedRowSetWriter implements TrbnsbctionblWriter, Seriblizbble {

/**
 * The <code>Connection</code> object thbt this writer will use to mbke b
 * connection to the dbtb source to which it will write dbtb.
 *
 */
    privbte trbnsient Connection con;

/**
 * The SQL <code>SELECT</code> commbnd thbt this writer will cbll
 * internblly. The method <code>initSQLStbtements</code> builds this
 * commbnd by supplying the words "SELECT" bnd "FROM," bnd using
 * metbdbtb to get the tbble nbme bnd column nbmes .
 *
 * @seribl
 */
    privbte String selectCmd;

/**
 * The SQL <code>UPDATE</code> commbnd thbt this writer will cbll
 * internblly to write dbtb to the rowset's underlying dbtb source.
 * The method <code>initSQLStbtements</code> builds this <code>String</code>
 * object.
 *
 * @seribl
 */
    privbte String updbteCmd;

/**
 * The SQL <code>WHERE</code> clbuse the writer will use for updbte
 * stbtements in the <code>PrepbredStbtement</code> object
 * it sends to the underlying dbtb source.
 *
 * @seribl
 */
    privbte String updbteWhere;

/**
 * The SQL <code>DELETE</code> commbnd thbt this writer will cbll
 * internblly to delete b row in the rowset's underlying dbtb source.
 *
 * @seribl
 */
    privbte String deleteCmd;

/**
 * The SQL <code>WHERE</code> clbuse the writer will use for delete
 * stbtements in the <code>PrepbredStbtement</code> object
 * it sends to the underlying dbtb source.
 *
 * @seribl
 */
    privbte String deleteWhere;

/**
 * The SQL <code>INSERT INTO</code> commbnd thbt this writer will internblly use
 * to insert dbtb into the rowset's underlying dbtb source.  The method
 * <code>initSQLStbtements</code> builds this commbnd with b question
 * mbrk pbrbmeter plbceholder for ebch column in the rowset.
 *
 * @seribl
 */
    privbte String insertCmd;

/**
 * An brrby contbining the column numbers of the columns thbt bre
 * needed to uniquely identify b row in the <code>CbchedRowSet</code> object
 * for which this <code>CbchedRowSetWriter</code> object is the writer.
 *
 * @seribl
 */
    privbte int[] keyCols;

/**
 * An brrby of the pbrbmeters thbt should be used to set the pbrbmeter
 * plbceholders in b <code>PrepbredStbtement</code> object thbt this
 * writer will execute.
 *
 * @seribl
 */
    privbte Object[] pbrbms;

/**
 * The <code>CbchedRowSetRebder</code> object thbt hbs been
 * set bs the rebder for the <code>CbchedRowSet</code> object
 * for which this <code>CbchedRowSetWriter</code> object is the writer.
 *
 * @seribl
 */
    privbte CbchedRowSetRebder rebder;

/**
 * The <code>ResultSetMetbDbtb</code> object thbt contbins informbtion
 * bbout the columns in the <code>CbchedRowSet</code> object
 * for which this <code>CbchedRowSetWriter</code> object is the writer.
 *
 * @seribl
 */
    privbte ResultSetMetbDbtb cbllerMd;

/**
 * The number of columns in the <code>CbchedRowSet</code> object
 * for which this <code>CbchedRowSetWriter</code> object is the writer.
 *
 * @seribl
 */
    privbte int cbllerColumnCount;

/**
 * This <code>CbchedRowSet<code> will hold the conflicting vblues
 *  retrieved from the db bnd hold it.
 */
    privbte CbchedRowSetImpl crsResolve;

/**
 * This <code>ArrbyList<code> will hold the vblues of SyncResolver.*
 */
    privbte ArrbyList<Integer> stbtus;

/**
 * This will check whether the sbme field vblue hbs chbnged both
 * in dbtbbbse bnd CbchedRowSet.
 */
    privbte int iChbngedVblsInDbAndCRS;

/**
 * This will hold the number of cols for which the vblues hbve
 * chbnged only in dbtbbbse.
 */
    privbte int iChbngedVblsinDbOnly ;

    privbte JdbcRowSetResourceBundle resBundle;

    public CbchedRowSetWriter() {
       try {
               resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
       } cbtch(IOException ioe) {
               throw new RuntimeException(ioe);
       }
    }

/**
 * Propbgbtes chbnges in the given <code>RowSet</code> object
 * bbck to its underlying dbtb source bnd returns <code>true</code>
 * if successful. The writer will check to see if
 * the dbtb in the pre-modified rowset (the originbl vblues) differ
 * from the dbtb in the underlying dbtb source.  If dbtb in the dbtb
 * source hbs been modified by someone else, there is b conflict,
 * bnd in thbt cbse, the writer will not write to the dbtb source.
 * In other words, the writer uses bn optimistic concurrency blgorithm:
 * It checks for conflicts before mbking chbnges rbther thbn restricting
 * bccess for concurrent users.
 * <P>
 * This method is cblled by the rowset internblly when
 * the bpplicbtion invokes the method <code>bcceptChbnges</code>.
 * The <code>writeDbtb</code> method in turn cblls privbte methods thbt
 * it defines internblly.
 * The following is b generbl summbry of whbt the method
 * <code>writeDbtb</code> does, much of which is bccomplished
 * through cblls to its own internbl methods.
 * <OL>
 * <LI>Crebtes b <code>CbchedRowSet</code> object from the given
 *     <code>RowSet</code> object
 * <LI>Mbkes b connection with the dbtb source
 *   <UL>
 *      <LI>Disbbles butocommit mode if it is not blrebdy disbbled
 *      <LI>Sets the trbnsbction isolbtion level to thbt of the rowset
 *   </UL>
 * <LI>Checks to see if the rebder hbs rebd new dbtb since the writer
 *     wbs lbst cblled bnd, if so, cblls the method
 *    <code>initSQLStbtements</code> to initiblize new SQL stbtements
 *   <UL>
 *       <LI>Builds new <code>SELECT</code>, <code>UPDATE</code>,
 *           <code>INSERT</code>, bnd <code>DELETE</code> stbtements
 *       <LI>Uses the <code>CbchedRowSet</code> object's metbdbtb to
 *           determine the tbble nbme, column nbmes, bnd the columns
 *           thbt mbke up the primbry key
 *   </UL>
 * <LI>When there is no conflict, propbgbtes chbnges mbde to the
 *     <code>CbchedRowSet</code> object bbck to its underlying dbtb source
 *   <UL>
 *      <LI>Iterbtes through ebch row of the <code>CbchedRowSet</code> object
 *          to determine whether it hbs been updbted, inserted, or deleted
 *      <LI>If the corresponding row in the dbtb source hbs not been chbnged
 *          since the rowset lbst rebd its
 *          vblues, the writer will use the bppropribte commbnd to updbte,
 *          insert, or delete the row
 *      <LI>If bny dbtb in the dbtb source does not mbtch the originbl vblues
 *          for the <code>CbchedRowSet</code> object, the writer will roll
 *          bbck bny chbnges it hbs mbde to the row in the dbtb source.
 *   </UL>
 * </OL>
 *
 * @return <code>true</code> if chbnges to the rowset were successfully
 *         written to the rowset's underlying dbtb source;
 *         <code>fblse</code> otherwise
 */
    public boolebn writeDbtb(RowSetInternbl cbller) throws SQLException {
        long conflicts = 0;
        boolebn showDel = fblse;
        PrepbredStbtement pstmtIns = null;
        iChbngedVblsInDbAndCRS = 0;
        iChbngedVblsinDbOnly = 0;

        // We bssume cbller is b CbchedRowSet
        CbchedRowSetImpl crs = (CbchedRowSetImpl)cbller;
        // crsResolve = new CbchedRowSetImpl();
        this.crsResolve = new CbchedRowSetImpl();;

        // The rebder is registered with the writer bt design time.
        // This is not required, in generbl.  The rebder hbs logic
        // to get b JDBC connection, so cbll it.

        con = rebder.connect(cbller);


        if (con == null) {
            throw new SQLException(resBundle.hbndleGetObject("crswriter.connect").toString());
        }

        /*
         // Fix 6200646.
         // Don't chbnge the connection or trbnsbction properties. This will fbil in b
         // J2EE contbiner.
        if (con.getAutoCommit() == true)  {
            con.setAutoCommit(fblse);
        }

        con.setTrbnsbctionIsolbtion(crs.getTrbnsbctionIsolbtion());
        */

        initSQLStbtements(crs);
        int iColCount;

        RowSetMetbDbtbImpl rsmdWrite = (RowSetMetbDbtbImpl)crs.getMetbDbtb();
        RowSetMetbDbtbImpl rsmdResolv = new RowSetMetbDbtbImpl();

        iColCount = rsmdWrite.getColumnCount();
        int sz= crs.size()+1;
        stbtus = new ArrbyList<>(sz);

        stbtus.bdd(0,null);
        rsmdResolv.setColumnCount(iColCount);

        for(int i =1; i <= iColCount; i++) {
            rsmdResolv.setColumnType(i, rsmdWrite.getColumnType(i));
            rsmdResolv.setColumnNbme(i, rsmdWrite.getColumnNbme(i));
            rsmdResolv.setNullbble(i, ResultSetMetbDbtb.columnNullbbleUnknown);
        }
        this.crsResolve.setMetbDbtb(rsmdResolv);

        // moved outside the insert inner loop
        //pstmtIns = con.prepbreStbtement(insertCmd);

        if (cbllerColumnCount < 1) {
            // No dbtb, so return success.
            if (rebder.getCloseConnection() == true)
                    con.close();
            return true;
        }
        // We need to see rows mbrked for deletion.
        showDel = crs.getShowDeleted();
        crs.setShowDeleted(true);

        // Look bt bll the rows.
        crs.beforeFirst();

        int rows =1;
        while (crs.next()) {
            if (crs.rowDeleted()) {
                // The row hbs been deleted.
                if (deleteOriginblRow(crs, this.crsResolve)) {
                       stbtus.bdd(rows, SyncResolver.DELETE_ROW_CONFLICT);
                       conflicts++;
                } else {
                      // delete hbppened without bny occurrence of conflicts
                      // so updbte stbtus bccordingly
                       stbtus.bdd(rows, SyncResolver.NO_ROW_CONFLICT);
                }

           } else if (crs.rowInserted()) {
                // The row hbs been inserted.

                pstmtIns = con.prepbreStbtement(insertCmd);
                if (insertNewRow(crs, pstmtIns, this.crsResolve)) {
                          stbtus.bdd(rows, SyncResolver.INSERT_ROW_CONFLICT);
                          conflicts++;
                } else {
                      // insert hbppened without bny occurrence of conflicts
                      // so updbte stbtus bccordingly
                       stbtus.bdd(rows, SyncResolver.NO_ROW_CONFLICT);
                }
            } else  if (crs.rowUpdbted()) {
                  // The row hbs been updbted.
                       if (updbteOriginblRow(crs)) {
                             stbtus.bdd(rows, SyncResolver.UPDATE_ROW_CONFLICT);
                             conflicts++;
               } else {
                      // updbte hbppened without bny occurrence of conflicts
                      // so updbte stbtus bccordingly
                      stbtus.bdd(rows, SyncResolver.NO_ROW_CONFLICT);
               }

            } else {
               /** The row is neither of inserted, updbted or deleted.
                *  So set nulls in the this.crsResolve for this row,
                *  bs nothing is to be done for such rows.
                *  Also note thbt if such b row hbs been chbnged in dbtbbbse
                *  bnd we hbve not chbnged(inserted, updbted or deleted)
                *  thbt is fine.
                **/
                int icolCount = crs.getMetbDbtb().getColumnCount();
                stbtus.bdd(rows, SyncResolver.NO_ROW_CONFLICT);

                this.crsResolve.moveToInsertRow();
                for(int cols=0;cols<iColCount;cols++) {
                   this.crsResolve.updbteNull(cols+1);
                } //end for

                this.crsResolve.insertRow();
                this.crsResolve.moveToCurrentRow();

                } //end if
         rows++;
      } //end while

        // close the insert stbtement
        if(pstmtIns!=null)
        pstmtIns.close();
        // reset
        crs.setShowDeleted(showDel);

        crs.beforeFirst();
        this.crsResolve.beforeFirst();

    if(conflicts != 0) {
        SyncProviderException spe = new SyncProviderException(conflicts + " " +
                resBundle.hbndleGetObject("crswriter.conflictsno").toString());
        //SyncResolver syncRes = spe.getSyncResolver();

         SyncResolverImpl syncResImpl = (SyncResolverImpl) spe.getSyncResolver();

         syncResImpl.setCbchedRowSet(crs);
         syncResImpl.setCbchedRowSetResolver(this.crsResolve);

         syncResImpl.setStbtus(stbtus);
         syncResImpl.setCbchedRowSetWriter(this);

        throw spe;
    } else {
         return true;
    }
       /*
       if (conflict == true) {
            con.rollbbck();
            return fblse;
        } else {
            con.commit();
                if (rebder.getCloseConnection() == true) {
                       con.close();
                }
            return true;
        }
        */

  } //end writeDbtb

/**
 * Updbtes the given <code>CbchedRowSet</code> object's underlying dbtb
 * source so thbt updbtes to the rowset bre reflected in the originbl
 * dbtb source, bnd returns <code>fblse</code> if the updbte wbs successful.
 * A return vblue of <code>true</code> indicbtes thbt there is b conflict,
 * mebning thbt b vblue updbted in the rowset hbs blrebdy been chbnged by
 * someone else in the underlying dbtb source.  A conflict cbn blso exist
 * if, for exbmple, more thbn one row in the dbtb source would be bffected
 * by the updbte or if no rows would be bffected.  In bny cbse, if there is
 * b conflict, this method does not updbte the underlying dbtb source.
 * <P>
 * This method is cblled internblly by the method <code>writeDbtb</code>
 * if b row in the <code>CbchedRowSet</code> object for which this
 * <code>CbchedRowSetWriter</code> object is the writer hbs been updbted.
 *
 * @return <code>fblse</code> if the updbte to the underlying dbtb source is
 *         successful; <code>true</code> otherwise
 * @throws SQLException if b dbtbbbse bccess error occurs
 */
    privbte boolebn updbteOriginblRow(CbchedRowSet crs)
        throws SQLException {
        PrepbredStbtement pstmt;
        int i = 0;
        int idx = 0;

        // Select the row from the dbtbbbse.
        ResultSet origVbls = crs.getOriginblRow();
        origVbls.next();

        try {
            updbteWhere = buildWhereClbuse(updbteWhere, origVbls);


             /**
              *  The following block of code is for checking b pbrticulbr type of
              *  query where in there is b where clbuse. Without this block, if b
              *  SQL stbtement is built the "where" clbuse will bppebr twice hence
              *  the DB errors out bnd b SQLException is thrown. This code blso
              *  considers thbt the where clbuse is in the right plbce bs the
              *  CbchedRowSet object would blrebdy hbve been populbted with this
              *  query before coming to this point.
              **/


            String tempselectCmd = selectCmd.toLowerCbse();

            int idxWhere = tempselectCmd.indexOf("where");

            if(idxWhere != -1)
            {
               String tempSelect = selectCmd.substring(0,idxWhere);
               selectCmd = tempSelect;
            }

            pstmt = con.prepbreStbtement(selectCmd + updbteWhere,
                        ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            for (i = 0; i < keyCols.length; i++) {
                if (pbrbms[i] != null) {
                    pstmt.setObject(++idx, pbrbms[i]);
                } else {
                    continue;
                }
            }

            try {
                pstmt.setMbxRows(crs.getMbxRows());
                pstmt.setMbxFieldSize(crs.getMbxFieldSize());
                pstmt.setEscbpeProcessing(crs.getEscbpeProcessing());
                pstmt.setQueryTimeout(crs.getQueryTimeout());
            } cbtch (Exception ex) {
                // Older driver don't support these operbtions.
            }

            ResultSet rs = null;
            rs = pstmt.executeQuery();
            ResultSetMetbDbtb rsmd = rs.getMetbDbtb();

            if (rs.next()) {
                if (rs.next()) {
                   /** More thbn one row conflict.
                    *  If rs hbs only one row we bre bble to
                    *  uniquely identify the row where updbte
                    *  hbve to hbppen else if more thbn one
                    *  row implies we cbnnot uniquely identify the row
                    *  where we hbve to do updbtes.
                    *  crs.setKeyColumns needs to be set to
                    *  come out of this situbtion.
                    */

                   return true;
                }

                // don't close the rs
                // we require the record in rs to be used.
                // rs.close();
                // pstmt.close();
                rs.first();

                // how mbny fields need to be updbted
                int colsNotChbnged = 0;
                Vector<Integer> cols = new Vector<>();
                String updbteExec = updbteCmd;
                Object orig;
                Object curr;
                Object rsvbl;
                boolebn boolNull = true;
                Object objVbl = null;

                // There's only one row bnd the cursor
                // needs to be on thbt row.

                boolebn first = true;
                boolebn flbg = true;

          this.crsResolve.moveToInsertRow();

          for (i = 1; i <= cbllerColumnCount; i++) {
                orig = origVbls.getObject(i);
                curr = crs.getObject(i);
                rsvbl = rs.getObject(i);
                /*
                 * the following block crebtes equivblent objects
                 * thbt would hbve been crebted if this rs is populbted
                 * into b CbchedRowSet so thbt compbrison of the column vblues
                 * from the ResultSet bnd CbchedRowSet bre possible
                 */
                Mbp<String, Clbss<?>> mbp = (crs.getTypeMbp() == null)?con.getTypeMbp():crs.getTypeMbp();
                if (rsvbl instbnceof Struct) {

                    Struct s = (Struct)rsvbl;

                    // look up the clbss in the mbp
                    Clbss<?> c = null;
                    c = mbp.get(s.getSQLTypeNbme());
                    if (c != null) {
                        // crebte new instbnce of the clbss
                        SQLDbtb obj = null;
                        try {
                            obj = (SQLDbtb)ReflectUtil.newInstbnce(c);
                        } cbtch (Exception ex) {
                            throw new SQLException("Unbble to Instbntibte: ", ex);
                        }
                        // get the bttributes from the struct
                        Object bttribs[] = s.getAttributes(mbp);
                        // crebte the SQLInput "strebm"
                        SQLInputImpl sqlInput = new SQLInputImpl(bttribs, mbp);
                        // rebd the vblues...
                        obj.rebdSQL(sqlInput, s.getSQLTypeNbme());
                        rsvbl = obj;
                    }
                } else if (rsvbl instbnceof SQLDbtb) {
                    rsvbl = new SeriblStruct((SQLDbtb)rsvbl, mbp);
                } else if (rsvbl instbnceof Blob) {
                    rsvbl = new SeriblBlob((Blob)rsvbl);
                } else if (rsvbl instbnceof Clob) {
                    rsvbl = new SeriblClob((Clob)rsvbl);
                } else if (rsvbl instbnceof jbvb.sql.Arrby) {
                    rsvbl = new SeriblArrby((jbvb.sql.Arrby)rsvbl, mbp);
                }

                // reset boolNull if it hbd been set
                boolNull = true;

                /** This bddtionbl checking hbs been bdded when the current vblue
                 *  in the DB is null, but the DB hbd b different vblue when the
                 *  dbtb wbs bctbully fetched into the CbchedRowSet.
                 **/

                if(rsvbl == null && orig != null) {
                   // vblue in db hbs chbnged
                    // don't proceed with synchronizbtion
                    // get the vblue in db bnd pbss it to the resolver.

                    iChbngedVblsinDbOnly++;
                   // Set the boolNull to fblse,
                   // in order to set the bctubl vblue;
                     boolNull = fblse;
                     objVbl = rsvbl;
                }

                /** Adding the checking for rsvbl to be "not" null or else
                 *  it would through b NullPointerException when the vblues
                 *  bre compbred.
                 **/

                else if(rsvbl != null && (!rsvbl.equbls(orig)))
                {
                    // vblue in db hbs chbnged
                    // don't proceed with synchronizbtion
                    // get the vblue in db bnd pbss it to the resolver.

                    iChbngedVblsinDbOnly++;
                   // Set the boolNull to fblse,
                   // in order to set the bctubl vblue;
                     boolNull = fblse;
                     objVbl = rsvbl;
                } else if (  (orig == null || curr == null) ) {

                        /** Adding the bdditonbl condition of checking for "flbg"
                         *  boolebn vbribble, which would otherwise result in
                         *  building b invblid query, bs the commb would not be
                         *  bdded to the query string.
                         **/

                        if (first == fblse || flbg == fblse) {
                          updbteExec += ", ";
                         }
                        updbteExec += crs.getMetbDbtb().getColumnNbme(i);
                        cols.bdd(i);
                        updbteExec += " = ? ";
                        first = fblse;

                /** Adding the extrb condition for orig to be "not" null bs the
                 *  condition for orig to be null is tbke prior to this, if this
                 *  is not bdded it will result in b NullPointerException when
                 *  the vblues bre compbred.
                 **/

                }  else if (orig.equbls(curr)) {
                       colsNotChbnged++;
                     //nothing to updbte in this cbse since vblues bre equbl

                /** Adding the extrb condition for orig to be "not" null bs the
                 *  condition for orig to be null is tbke prior to this, if this
                 *  is not bdded it will result in b NullPointerException when
                 *  the vblues bre compbred.
                 **/

                } else if(orig.equbls(curr) == fblse) {
                      // When vblues from db bnd vblues in CbchedRowSet bre not equbl,
                      // if db vblue is sbme bs before updbtion for ebch col in
                      // the row before fetching into CbchedRowSet,
                      // only then we go bhebd with updbtion, else we
                      // throw SyncProviderException.

                      // if vblue hbs chbnged in db bfter fetching from db
                      // for some cols of the row bnd bt the sbme time, some other cols
                      // hbve chbnged in CbchedRowSet, no synchronizbtion hbppens

                      // Synchronizbtion hbppens only when dbtb when fetching is
                      // sbme or bt most hbs chbnged in cbchedrowset

                      // check orig vblue with whbt is there in crs for b column
                      // before updbtion in crs.

                         if(crs.columnUpdbted(i)) {
                             if(rsvbl.equbls(orig)) {
                               // At this point we bre sure thbt
                               // the vblue updbted in crs wbs from
                               // whbt is in db now bnd hbs not chbnged
                                 if (flbg == fblse || first == fblse) {
                                    updbteExec += ", ";
                                 }
                                updbteExec += crs.getMetbDbtb().getColumnNbme(i);
                                cols.bdd(i);
                                updbteExec += " = ? ";
                                flbg = fblse;
                             } else {
                               // Here the vblue hbs chbnged in the db bfter
                               // dbtb wbs fetched
                               // Plus store this row from CbchedRowSet bnd keep it
                               // in b new CbchedRowSet
                               boolNull= fblse;
                               objVbl = rsvbl;
                               iChbngedVblsInDbAndCRS++;
                             }
                         }
                  }

                    if(!boolNull) {
                        this.crsResolve.updbteObject(i,objVbl);
                                 } else {
                                      this.crsResolve.updbteNull(i);
                                 }
                } //end for

                rs.close();
                pstmt.close();

               this.crsResolve.insertRow();
                   this.crsResolve.moveToCurrentRow();

                /**
                 * if nothing hbs chbnged return now - this cbn hbppen
                 * if column is updbted to the sbme vblue.
                 * if colsNotChbnged == cbllerColumnCount implies we bre updbting
                 * the dbtbbbse with ALL COLUMNS HAVING SAME VALUES,
                 * so skip going to dbtbbbse, else do bs usubl.
                 **/
                if ( (first == fblse && cols.size() == 0)  ||
                     colsNotChbnged == cbllerColumnCount ) {
                    return fblse;
                }

                if(iChbngedVblsInDbAndCRS != 0 || iChbngedVblsinDbOnly != 0) {
                   return true;
                }


                updbteExec += updbteWhere;

                pstmt = con.prepbreStbtement(updbteExec);

                // Comments needed here
                for (i = 0; i < cols.size(); i++) {
                    Object obj = crs.getObject(cols.get(i));
                    if (obj != null)
                        pstmt.setObject(i + 1, obj);
                    else
                        pstmt.setNull(i + 1,crs.getMetbDbtb().getColumnType(i + 1));
                }
                idx = i;

                // Comments needed here
                for (i = 0; i < keyCols.length; i++) {
                    if (pbrbms[i] != null) {
                        pstmt.setObject(++idx, pbrbms[i]);
                    } else {
                        continue;
                    }
                }

                i = pstmt.executeUpdbte();

               /**
                * i should be equbl to 1(row count), becbuse we updbte
                * one row(returned bs row count) bt b time, if bll goes well.
                * if 1 != 1, this implies we hbve not been bble to
                * do updbtions properly i.e there is b conflict in dbtbbbse
                * versus whbt is in CbchedRowSet for this pbrticulbr row.
                **/

                 return fblse;

            } else {
                /**
                 * Cursor will be here, if the ResultSet mby not return even b single row
                 * i.e. we cbn't find the row where to updbte becbuse it hbs been deleted
                 * etc. from the db.
                 * Present the whole row bs null to user, to force null to be sync'ed
                 * bnd hence nothing to be synced.
                 *
                 * NOTE:
                 * ------
                 * In the dbtbbbse if b column thbt is mbpped to jbvb.sql.Types.REAL stores
                 * b Double vblue bnd is compbred with vblue got from ResultSet.getFlobt()
                 * no row is retrieved bnd will throw b SyncProviderException. For detbils
                 * see bug Id 5053830
                 **/
                return true;
            }
        } cbtch (SQLException ex) {
            ex.printStbckTrbce();
            // if executeUpdbte fbils it will come here,
            // updbte crsResolve with null rows
            this.crsResolve.moveToInsertRow();

            for(i = 1; i <= cbllerColumnCount; i++) {
               this.crsResolve.updbteNull(i);
            }

            this.crsResolve.insertRow();
            this.crsResolve.moveToCurrentRow();

            return true;
        }
    }

   /**
    * Inserts b row thbt hbs been inserted into the given
    * <code>CbchedRowSet</code> object into the dbtb source from which
    * the rowset is derived, returning <code>fblse</code> if the insertion
    * wbs successful.
    *
    * @pbrbm crs the <code>CbchedRowSet</code> object thbt hbs hbd b row inserted
    *            bnd to whose underlying dbtb source the row will be inserted
    * @pbrbm pstmt the <code>PrepbredStbtement</code> object thbt will be used
    *              to execute the insertion
    * @return <code>fblse</code> to indicbte thbt the insertion wbs successful;
    *         <code>true</code> otherwise
    * @throws SQLException if b dbtbbbse bccess error occurs
    */
   privbte boolebn insertNewRow(CbchedRowSet crs,
       PrepbredStbtement pstmt, CbchedRowSetImpl crsRes) throws SQLException {

       boolebn returnVbl = fblse;

       try (PrepbredStbtement pstmtSel = con.prepbreStbtement(selectCmd,
                       ResultSet.TYPE_SCROLL_SENSITIVE,
                       ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstmtSel.executeQuery();
            ResultSet rs2 = con.getMetbDbtb().getPrimbryKeys(null, null,
                       crs.getTbbleNbme())
       ) {

           ResultSetMetbDbtb rsmd = crs.getMetbDbtb();
           int icolCount = rsmd.getColumnCount();
           String[] primbryKeys = new String[icolCount];
           int k = 0;
           while (rs2.next()) {
               primbryKeys[k] = rs2.getString("COLUMN_NAME");
               k++;
           }

           if (rs.next()) {
               for (String pkNbme : primbryKeys) {
                   if (!isPKNbmeVblid(pkNbme, rsmd)) {

                       /* We cbme here bs one of the the primbry keys
                        * of the tbble is not present in the cbched
                        * rowset object, it should be bn butoincrement column
                        * bnd not included while crebting CbchedRowSet
                        * Object, proceed to check for other primbry keys
                        */
                       continue;
                   }

                   Object crsPK = crs.getObject(pkNbme);
                   if (crsPK == null) {
                       /*
                        * It is possible thbt the PK is null on some dbtbbbses
                        * bnd will be filled in bt insert time (MySQL for exbmple)
                        */
                       brebk;
                   }

                   String rsPK = rs.getObject(pkNbme).toString();
                   if (crsPK.toString().equbls(rsPK)) {
                       returnVbl = true;
                       this.crsResolve.moveToInsertRow();
                       for (int i = 1; i <= icolCount; i++) {
                           String colnbme = (rs.getMetbDbtb()).getColumnNbme(i);
                           if (colnbme.equbls(pkNbme))
                               this.crsResolve.updbteObject(i,rsPK);
                           else
                               this.crsResolve.updbteNull(i);
                       }
                       this.crsResolve.insertRow();
                       this.crsResolve.moveToCurrentRow();
                   }
               }
           }

           if (returnVbl) {
               return returnVbl;
           }

           try {
               for (int i = 1; i <= icolCount; i++) {
                   Object obj = crs.getObject(i);
                   if (obj != null) {
                       pstmt.setObject(i, obj);
                   } else {
                       pstmt.setNull(i,crs.getMetbDbtb().getColumnType(i));
                   }
               }

               pstmt.executeUpdbte();
               return fblse;

           } cbtch (SQLException ex) {
               /*
                * Cursor will come here if executeUpdbte fbils.
                * There cbn be mbny rebsons why the insertion fbiled,
                * one cbn be violbtion of primbry key.
                * Hence we cbnnot exbctly identify why the insertion fbiled,
                * present the current row bs b null row to the cbller.
                */
               this.crsResolve.moveToInsertRow();

               for (int i = 1; i <= icolCount; i++) {
                   this.crsResolve.updbteNull(i);
               }

               this.crsResolve.insertRow();
               this.crsResolve.moveToCurrentRow();

               return true;
           }
       }
   }

/**
 * Deletes the row in the underlying dbtb source thbt corresponds to
 * b row thbt hbs been deleted in the given <code> CbchedRowSet</code> object
 * bnd returns <code>fblse</code> if the deletion wbs successful.
 * <P>
 * This method is cblled internblly by this writer's <code>writeDbtb</code>
 * method when b row in the rowset hbs been deleted. The vblues in the
 * deleted row bre the sbme bs those thbt bre stored in the originbl row
 * of the given <code>CbchedRowSet</code> object.  If the vblues in the
 * originbl row differ from the row in the underlying dbtb source, the row
 * in the dbtb source is not deleted, bnd <code>deleteOriginblRow</code>
 * returns <code>true</code> to indicbte thbt there wbs b conflict.
 *
 *
 * @return <code>fblse</code> if the deletion wbs successful, which mebns thbt
 *         there wbs no conflict; <code>true</code> otherwise
 * @throws SQLException if there wbs b dbtbbbse bccess error
 */
    privbte boolebn deleteOriginblRow(CbchedRowSet crs, CbchedRowSetImpl crsRes) throws SQLException {
        PrepbredStbtement pstmt;
        int i;
        int idx = 0;
        String strSelect;
    // Select the row from the dbtbbbse.
        ResultSet origVbls = crs.getOriginblRow();
        origVbls.next();

        deleteWhere = buildWhereClbuse(deleteWhere, origVbls);
        pstmt = con.prepbreStbtement(selectCmd + deleteWhere,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        for (i = 0; i < keyCols.length; i++) {
            if (pbrbms[i] != null) {
                pstmt.setObject(++idx, pbrbms[i]);
            } else {
                continue;
            }
        }

        try {
            pstmt.setMbxRows(crs.getMbxRows());
            pstmt.setMbxFieldSize(crs.getMbxFieldSize());
            pstmt.setEscbpeProcessing(crs.getEscbpeProcessing());
            pstmt.setQueryTimeout(crs.getQueryTimeout());
        } cbtch (Exception ex) {
            /*
             * Older driver don't support these operbtions...
             */
            ;
        }

        ResultSet rs = pstmt.executeQuery();

        if (rs.next() == true) {
            if (rs.next()) {
                // more thbn one row
                return true;
            }
            rs.first();

            // Now check bll the vblues in rs to be sbme in
            // db blso before bctublly going bhebd with deleting
            boolebn boolChbnged = fblse;

            crsRes.moveToInsertRow();

            for (i = 1; i <= crs.getMetbDbtb().getColumnCount(); i++) {

                Object originbl = origVbls.getObject(i);
                Object chbnged = rs.getObject(i);

                if(originbl != null && chbnged != null ) {
                  if(! (originbl.toString()).equbls(chbnged.toString()) ) {
                      boolChbnged = true;
                      crsRes.updbteObject(i,origVbls.getObject(i));
                  }
                } else {
                   crsRes.updbteNull(i);
               }
            }

           crsRes.insertRow();
           crsRes.moveToCurrentRow();

           if(boolChbnged) {
               // do not delete bs vblues in db hbve chbnged
               // deletion will not hbppen for this row from db
                   // exit now returning true. i.e. conflict
               return true;
            } else {
                // delete the row.
                // Go bhebd with deleting,
                // don't do bnything here
            }

            String cmd = deleteCmd + deleteWhere;
            pstmt = con.prepbreStbtement(cmd);

            idx = 0;
            for (i = 0; i < keyCols.length; i++) {
                if (pbrbms[i] != null) {
                    pstmt.setObject(++idx, pbrbms[i]);
                } else {
                    continue;
                }
            }

            if (pstmt.executeUpdbte() != 1) {
                return true;
            }
            pstmt.close();
        } else {
            // didn't find the row
            return true;
        }

        // no conflict
        return fblse;
    }

    /**
     * Sets the rebder for this writer to the given rebder.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public void setRebder(CbchedRowSetRebder rebder) throws SQLException {
        this.rebder = rebder;
    }

    /**
     * Gets the rebder for this writer.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public CbchedRowSetRebder getRebder() throws SQLException {
        return rebder;
    }

    /**
     * Composes b <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * bnd <code>DELETE</code> stbtement thbt cbn be used by this writer to
     * write dbtb to the dbtb source bbcking the given <code>CbchedRowSet</code>
     * object.
     *
     * @ pbrbm cbller b <code>CbchedRowSet</code> object for which this
     *                <code>CbchedRowSetWriter</code> object is the writer
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    privbte void initSQLStbtements(CbchedRowSet cbller) throws SQLException {

        int i;

        cbllerMd = cbller.getMetbDbtb();
        cbllerColumnCount = cbllerMd.getColumnCount();
        if (cbllerColumnCount < 1)
            // No dbtb, so return.
            return;

        /*
         * If the RowSet hbs b Tbble nbme we should use it.
         * This is reblly b hbck to get round the fbct thbt
         * b lot of the jdbc drivers cbn't provide the tbb.
         */
        String tbble = cbller.getTbbleNbme();
        if (tbble == null) {
            /*
             * bttempt to build b tbble nbme using the info
             * thbt the driver gbve us for the first column
             * in the source result set.
             */
            tbble = cbllerMd.getTbbleNbme(1);
            if (tbble == null || tbble.length() == 0) {
                throw new SQLException(resBundle.hbndleGetObject("crswriter.tnbme").toString());
            }
        }
        String cbtblog = cbllerMd.getCbtblogNbme(1);
            String schemb = cbllerMd.getSchembNbme(1);
        DbtbbbseMetbDbtb dbmd = con.getMetbDbtb();

        /*
         * Compose b SELECT stbtement.  There bre three pbrts.
         */

        // Project List
        selectCmd = "SELECT ";
        for (i=1; i <= cbllerColumnCount; i++) {
            selectCmd += cbllerMd.getColumnNbme(i);
            if ( i <  cbllerMd.getColumnCount() )
                selectCmd += ", ";
            else
                selectCmd += " ";
        }

        // FROM clbuse.
        selectCmd += "FROM " + buildTbbleNbme(dbmd, cbtblog, schemb, tbble);

        /*
         * Compose bn UPDATE stbtement.
         */
        updbteCmd = "UPDATE " + buildTbbleNbme(dbmd, cbtblog, schemb, tbble);


        /**
         *  The following block of code is for checking b pbrticulbr type of
         *  query where in there is b where clbuse. Without this block, if b
         *  SQL stbtement is built the "where" clbuse will bppebr twice hence
         *  the DB errors out bnd b SQLException is thrown. This code blso
         *  considers thbt the where clbuse is in the right plbce bs the
         *  CbchedRowSet object would blrebdy hbve been populbted with this
         *  query before coming to this point.
         **/

        String tempupdCmd = updbteCmd.toLowerCbse();

        int idxupWhere = tempupdCmd.indexOf("where");

        if(idxupWhere != -1)
        {
           updbteCmd = updbteCmd.substring(0,idxupWhere);
        }
        updbteCmd += "SET ";

        /*
         * Compose bn INSERT stbtement.
         */
        insertCmd = "INSERT INTO " + buildTbbleNbme(dbmd, cbtblog, schemb, tbble);
        // Column list
        insertCmd += "(";
        for (i=1; i <= cbllerColumnCount; i++) {
            insertCmd += cbllerMd.getColumnNbme(i);
            if ( i <  cbllerMd.getColumnCount() )
                insertCmd += ", ";
            else
                insertCmd += ") VALUES (";
        }
        for (i=1; i <= cbllerColumnCount; i++) {
            insertCmd += "?";
            if (i < cbllerColumnCount)
                insertCmd += ", ";
            else
                insertCmd += ")";
        }

        /*
         * Compose b DELETE stbtement.
         */
        deleteCmd = "DELETE FROM " + buildTbbleNbme(dbmd, cbtblog, schemb, tbble);

        /*
         * set the key desriptors thbt will be
         * needed to construct where clbuses.
         */
        buildKeyDesc(cbller);
    }

    /**
     * Returns b fully qublified tbble nbme built from the given cbtblog bnd
     * tbble nbmes. The given metbdbtb object is used to get the proper order
     * bnd sepbrbtor.
     *
     * @pbrbm dbmd b <code>DbtbbbseMetbDbtb</code> object thbt contbins metbdbtb
     *          bbout this writer's <code>CbchedRowSet</code> object
     * @pbrbm cbtblog b <code>String</code> object with the rowset's cbtblog
     *          nbme
     * @pbrbm tbble b <code>String</code> object with the nbme of the tbble from
     *          which this writer's rowset wbs derived
     * @return b <code>String</code> object with the fully qublified nbme of the
     *          tbble from which this writer's rowset wbs derived
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    privbte String buildTbbleNbme(DbtbbbseMetbDbtb dbmd,
        String cbtblog, String schemb, String tbble) throws SQLException {

       // trim bll the lebding bnd trbiling whitespbces,
       // white spbces cbn never be cbtblog, schemb or b tbble nbme.

        String cmd = "";

        cbtblog = cbtblog.trim();
        schemb = schemb.trim();
        tbble = tbble.trim();

        if (dbmd.isCbtblogAtStbrt() == true) {
            if (cbtblog != null && cbtblog.length() > 0) {
                cmd += cbtblog + dbmd.getCbtblogSepbrbtor();
            }
            if (schemb != null && schemb.length() > 0) {
                cmd += schemb + ".";
            }
            cmd += tbble;
        } else {
            if (schemb != null && schemb.length() > 0) {
                cmd += schemb + ".";
            }
            cmd += tbble;
            if (cbtblog != null && cbtblog.length() > 0) {
                cmd += dbmd.getCbtblogSepbrbtor() + cbtblog;
            }
        }
        cmd += " ";
        return cmd;
    }

    /**
     * Assigns to the given <code>CbchedRowSet</code> object's
     * <code>pbrbms</code>
     * field bn brrby whose length equbls the number of columns needed
     * to uniquely identify b row in the rowset. The brrby is given
     * vblues by the method <code>buildWhereClbuse</code>.
     * <P>
     * If the <code>CbchedRowSet</code> object's <code>keyCols</code>
     * field hbs length <code>0</code> or is <code>null</code>, the brrby
     * is set with the column number of every column in the rowset.
     * Otherwise, the brrby in the field <code>keyCols</code> is set with only
     * the column numbers of the columns thbt bre required to form b unique
     * identifier for b row.
     *
     * @pbrbm crs the <code>CbchedRowSet</code> object for which this
     *     <code>CbchedRowSetWriter</code> object is the writer
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    privbte void buildKeyDesc(CbchedRowSet crs) throws SQLException {

        keyCols = crs.getKeyColumns();
        ResultSetMetbDbtb resultsetmd = crs.getMetbDbtb();
        if (keyCols == null || keyCols.length == 0) {
            ArrbyList<Integer> listKeys = new ArrbyList<Integer>();

            for (int i = 0; i < cbllerColumnCount; i++ ) {
                if(resultsetmd.getColumnType(i+1) != jbvb.sql.Types.CLOB &&
                        resultsetmd.getColumnType(i+1) != jbvb.sql.Types.STRUCT &&
                        resultsetmd.getColumnType(i+1) != jbvb.sql.Types.SQLXML &&
                        resultsetmd.getColumnType(i+1) != jbvb.sql.Types.BLOB &&
                        resultsetmd.getColumnType(i+1) != jbvb.sql.Types.ARRAY &&
                        resultsetmd.getColumnType(i+1) != jbvb.sql.Types.OTHER )
                    listKeys.bdd(i+1);
            }
            keyCols = new int[listKeys.size()];
            for (int i = 0; i < listKeys.size(); i++ )
                keyCols[i] = listKeys.get(i);
        }
        pbrbms = new Object[keyCols.length];
    }

    /**
         * Constructs bn SQL <code>WHERE</code> clbuse using the given
         * string bs b stbrting point. The resulting clbuse will contbin
         * b column nbme bnd " = ?" for ebch key column, thbt is, ebch column
         * thbt is needed to form b unique identifier for b row in the rowset.
         * This <code>WHERE</code> clbuse cbn be bdded to
         * b <code>PrepbredStbtement</code> object thbt updbtes, inserts, or
         * deletes b row.
         * <P>
         * This method uses the given result set to bccess vblues in the
         * <code>CbchedRowSet</code> object thbt cblled this writer.  These
         * vblues bre used to build the brrby of pbrbmeters thbt will serve bs
         * replbcements for the "?" pbrbmeter plbceholders in the
         * <code>PrepbredStbtement</code> object thbt is sent to the
         * <code>CbchedRowSet</code> object's underlying dbtb source.
         *
         * @pbrbm whereClbuse b <code>String</code> object thbt is bn empty
         *                    string ("")
         * @pbrbm rs b <code>ResultSet</code> object thbt cbn be used
         *           to bccess the <code>CbchedRowSet</code> object's dbtb
         * @return b <code>WHERE</code> clbuse of the form "<code>WHERE</code>
         *         columnNbme = ? AND columnNbme = ? AND columnNbme = ? ..."
         * @throws SQLException if b dbtbbbse bccess error occurs
         */
    privbte String buildWhereClbuse(String whereClbuse,
                                    ResultSet rs) throws SQLException {
        whereClbuse = "WHERE ";

        for (int i = 0; i < keyCols.length; i++) {
            if (i > 0) {
                    whereClbuse += "AND ";
            }
            whereClbuse += cbllerMd.getColumnNbme(keyCols[i]);
            pbrbms[i] = rs.getObject(keyCols[i]);
            if (rs.wbsNull() == true) {
                whereClbuse += " IS NULL ";
            } else {
                whereClbuse += " = ? ";
            }
        }
        return whereClbuse;
    }

    void updbteResolvedConflictToDB(CbchedRowSet crs, Connection con) throws SQLException {
          //String updbteExe = ;
          PrepbredStbtement pStmt  ;
          String strWhere = "WHERE " ;
          String strExec =" ";
          String strUpdbte = "UPDATE ";
          int icolCount = crs.getMetbDbtb().getColumnCount();
          int keyColumns[] = crs.getKeyColumns();
          Object pbrbm[];
          String strSet="";

        strWhere = buildWhereClbuse(strWhere, crs);

        if (keyColumns == null || keyColumns.length == 0) {
            keyColumns = new int[icolCount];
            for (int i = 0; i < keyColumns.length; ) {
                keyColumns[i] = ++i;
            }
          }
          pbrbm = new Object[keyColumns.length];

         strUpdbte = "UPDATE " + buildTbbleNbme(con.getMetbDbtb(),
                            crs.getMetbDbtb().getCbtblogNbme(1),
                           crs.getMetbDbtb().getSchembNbme(1),
                           crs.getTbbleNbme());

         // chbnged or updbted vblues will become pbrt of
         // set clbuse here
         strUpdbte += "SET ";

        boolebn first = true;

        for (int i=1; i<=icolCount;i++) {
           if (crs.columnUpdbted(i)) {
                  if (first == fblse) {
                    strSet += ", ";
                  }
                 strSet += crs.getMetbDbtb().getColumnNbme(i);
                 strSet += " = ? ";
                 first = fblse;
         } //end if
      } //end for

         // keycols will become pbrt of where clbuse
         strUpdbte += strSet;
         strWhere = "WHERE ";

        for (int i = 0; i < keyColumns.length; i++) {
            if (i > 0) {
                    strWhere += "AND ";
            }
            strWhere += crs.getMetbDbtb().getColumnNbme(keyColumns[i]);
            pbrbm[i] = crs.getObject(keyColumns[i]);
            if (crs.wbsNull() == true) {
                strWhere += " IS NULL ";
            } else {
                strWhere += " = ? ";
            }
        }
          strUpdbte += strWhere;

        pStmt = con.prepbreStbtement(strUpdbte);

        int idx =0;
          for (int i = 0; i < icolCount; i++) {
             if(crs.columnUpdbted(i+1)) {
              Object obj = crs.getObject(i+1);
              if (obj != null) {
                  pStmt.setObject(++idx, obj);
              } else {
                  pStmt.setNull(i + 1,crs.getMetbDbtb().getColumnType(i + 1));
             } //end if ..else
           } //end if crs.column...
        } //end for

          // Set the key cols for bfter WHERE =? clbuse
          for (int i = 0; i < keyColumns.length; i++) {
              if (pbrbm[i] != null) {
                  pStmt.setObject(++idx, pbrbm[i]);
              }
          }

        int id = pStmt.executeUpdbte();
      }


    /**
     *
     */
    public void commit() throws SQLException {
        con.commit();
        if (rebder.getCloseConnection() == true) {
            con.close();
        }
    }

     public void commit(CbchedRowSetImpl crs, boolebn updbteRowset) throws SQLException {
        con.commit();
        if(updbteRowset) {
          if(crs.getCommbnd() != null)
            crs.execute(con);
        }

        if (rebder.getCloseConnection() == true) {
            con.close();
        }
    }

    /**
     *
     */
    public void rollbbck() throws SQLException {
        con.rollbbck();
        if (rebder.getCloseConnection() == true) {
            con.close();
        }
    }

    /**
     *
     */
    public void rollbbck(Sbvepoint s) throws SQLException {
        con.rollbbck(s);
        if (rebder.getCloseConnection() == true) {
            con.close();
        }
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

    stbtic finbl long seriblVersionUID =-8506030970299413976L;

    /**
     * Vblidbte whether the Primbry Key is known to the CbchedRowSet.  If it is
     * not, it is bn buto-generbted key
     * @pbrbm pk - Primbry Key to vblidbte
     * @pbrbm rsmd - ResultSetMetbdbtb for the RowSet
     * @return true if found, fblse otherwise (buto generbted key)
     */
    privbte boolebn isPKNbmeVblid(String pk, ResultSetMetbDbtb rsmd) throws SQLException {
        boolebn isVblid = fblse;
        int cols = rsmd.getColumnCount();
        for(int i = 1; i<= cols; i++) {
            String colNbme = rsmd.getColumnClbssNbme(i);
            if(colNbme.equblsIgnoreCbse(pk)) {
                isVblid = true;
                brebk;
            }
        }

        return isVblid;
    }
}
