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

import jbvb.io.*;
import jbvb.util.*;
import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.mbth.*;

import jbvbx.sql.rowset.*;
import jbvbx.sql.rowset.spi.*;
import jbvbx.sql.rowset.seribl.*;
import com.sun.rowset.providers.*;
import com.sun.rowset.internbl.*;

/**
 * The stbndbrd implementbtion of the <code>FilteredRowSet</code> interfbce. See the interfbce
 * definition for full behbvior bnd implementbtion requirements.
 *
 * @see jbvbx.sql.rowset.Predicbte
 * @buthor Jonbthbn Bruce, Amit Hbndb
 */

public clbss FilteredRowSetImpl extends WebRowSetImpl implements Seriblizbble, Clonebble, FilteredRowSet {

    privbte Predicbte p;

    privbte boolebn onInsertRow = fblse;


    /**
     * Construct b <code>FilteredRowSet</code>
     */
    public FilteredRowSetImpl() throws SQLException {
        super();
    }

    /**
     * Construct b <code>FilteredRowSet</code> with b specified synchronizbtion
     * provider.
     *
     * @pbrbm env b Hbshtbble contbining b desired synchconizbtbtion provider
     * nbme-vblue pbir.
     */
    @SuppressWbrnings("rbwtypes")
    public FilteredRowSetImpl(Hbshtbble env) throws SQLException {
        super(env);
    }

    /**
     * Apply the predicbte for this filter
     *
     * @pbrbm p bn implementbtion of the predicbte interfbce
     */
    public void setFilter(Predicbte p) throws SQLException {
        this.p = p;
    }

    /**
     * Retrieve the filter bctive for this <code>FilteredRowSet</code>
     *
     * @return b <code>Predicbte</code> object instbnce
     */
    public Predicbte getFilter() {
        return this.p;
    }

    /**
     * Over-riding <code>internblNext()</code> implementbtion. This method
     * bpplies the filter on the <code>RowSet</code> ebch time the cursor is bdvbnced or
     * mbnipulbted. It moves the cursor to the next row bccording to the set
     * predicbte bnd returns <code>true</code> if the cursor is still within the rowset or
     * <code>fblse</code> if the cursor position is over the lbst row
     *
     * @return true if over the vblid row in the rowset; fblse if over the lbst
     * row
     */
    protected boolebn internblNext() throws SQLException {
        // CbchedRowSetImpl.next() internblly cblls
        // this(crs).internblNext() NOTE: this holds crs object
        // So when frs.next() is cblled,
        // internblly this(frs).internblNext() will be cblled
        // which will be nothing but this method.
        // becbuse this holds frs object

        // keep on doing super.internblNext()
        // rbther thbn doing it once.


         // p.evblubte will help us in chbnging the cursor
         // bnd checking the next vblue by returning true or fblse.
         // to fit the filter

         // So while() loop will hbve b "rbndom combinbtion" of
         // true bnd fblse returned depending upon the records
         // bre in or out of filter.
         // We need to trbverse from present cursorPos till end,
         // whether true or fblse bnd check ebch row for "filter"
         // "till we get b "true"


         boolebn bool = fblse;

         for(int rows=this.getRow(); rows<=this.size();rows++) {
             bool = super.internblNext();

             if( !bool || p == null) {
               return bool;
             }
             if(p.evblubte(this)){
                   brebk;
             }

         }

       return bool;
    }


    /**
     * Over-riding <code>internblPrevious()</code> implementbtion. This method
     * bpplies the filter on the <code>RowSet</code> ebch time the cursor is moved bbckwbrd or
     * mbnipulbted. It moves the cursor to the previous row bccording to the set
     * predicbte bnd returns <code>true</code> if the cursor is still within the rowset or
     * <code>fblse</code> if the cursor position is over the lbst row
     *
     * @return true if over the vblid row in the rowset; fblse if over the lbst
     * row
     */
    protected boolebn internblPrevious() throws SQLException {
         boolebn bool = fblse;
         // with previous move bbckwbrds,
         // i.e. from bny record towbrds first record

         for(int rows=this.getRow(); rows>0;rows--) {

             bool = super.internblPrevious();

             if( p == null) {
               return bool;
             }

             if(p.evblubte(this)){
                   brebk;
             }

         }

       return bool;
    }


    /**
     * Over-riding <code>internblFirst()</code> implementbtion. This method
     * bpplies the filter on the <code>RowSet</code> ebch time the cursor is moved to first
     * row. It moves the cursor to the first row bccording to the set
     * predicbte bnd returns <code>true</code> if the cursor is still within the rowset or
     * <code>fblse</code> if the cursor position is over the lbst row
     *
     * @return true if over the vblid row in the rowset; fblse if over the lbst
     * row
     */
    protected boolebn internblFirst() throws SQLException {

        // from first till present cursor position(go forwbrd),
        // find the bctubl first which mbtches the filter.

         boolebn bool = super.internblFirst();

         if( p == null) {
               return bool;
             }

         while(bool) {

             if(p.evblubte(this)){
                   brebk;
             }
        bool = super.internblNext();
        }
     return bool;
    }


    /**
     * Over-riding <code>internblLbst()</code> implementbtion. This method
     * bpplies the filter on the <code>RowSet</code> ebch time the cursor is moved to
     * lbst row. It moves the cursor to the lbst row bccording to the set
     * predicbte bnd returns <code>true</code> if the cursor is still within the rowset or
     * <code>fblse</code> if the cursor position is over the lbst row
     *
     * @return true if over the vblid row in the rowset; fblse if over the lbst
     * row
     */
    protected boolebn internblLbst() throws SQLException {
        // from lbst to the present cursor position(go bbckwbrd),
        // find the bctubl lbst which mbtches the filter.

         boolebn bool = super.internblLbst();

         if( p == null) {
               return bool;
             }

         while(bool) {

             if(p.evblubte(this)){
                   brebk;
             }

        bool = super.internblPrevious();

        }
     return bool;

   } // end internblLbst()
   /**
     * Moves the cursor the specified number of rows from the current
     * position, with b positive number moving it forwbrd bnd b
     * negbtive number moving it bbckwbrd.
     * <P>
     * If the number is positive, the cursor moves the specified number of
     * rows towbrd the end of the rowset, stbrting bt the current row.
     * For exbmple, the following commbnd, in which
     * <code>crs</code> is b <code>CbchedRowSetImpl</code> object with 100 rows,
     * moves the cursor forwbrd four rows from the current row.  If the
     * current row is 50, the cursor would move to row 54.
     * <PRE><code>
     *
     *    crs.relbtive(4);
     *
     * </code> </PRE>
     * <P>
     * If the number is negbtive, the cursor moves bbck towbrd the beginning
     * the specified number of rows, stbrting bt the current row.
     * For exbmple, cblling the method
     * <code>bbsolute(-1)</code> positions the cursor on the lbst row,
     * <code>bbsolute(-2)</code> moves it on the next-to-lbst row, bnd so on.
     * If the <code>CbchedRowSetImpl</code> object <code>crs</code> hbs five rows,
     * the following commbnd moves the cursor to the fourth-to-lbst row, which
     * in the cbse of b  rowset with five rows, is blso the second row
     * from the beginning.
     * <PRE><code>
     *
     *    crs.bbsolute(-4);
     *
     * </code> </PRE>
     *
     * If the number specified is lbrger thbn the number of rows, the cursor
     * will move to the position bfter the lbst row. If the number specified
     * would move the cursor one or more rows before the first row, the cursor
     * moves to the position before the first row. In both cbses, this method
     * throws bn <code>SQLException</code>.
     * <P>
     * Note: Cblling <code>bbsolute(1)</code> is the sbme bs cblling the
     * method <code>first()</code>.  Cblling <code>bbsolute(-1)</code> is the
     * sbme bs cblling <code>lbst()</code>.  Cblling <code>relbtive(0)</code>
     * is vblid, but it does not chbnge the cursor position.
     *
     * @pbrbm rows bn <code>int</code> indicbting the number of rows to move
     *             the cursor, stbrting bt the current row; b positive number
     *             moves the cursor forwbrd; b negbtive number moves the cursor
     *             bbckwbrd; must not move the cursor pbst the vblid
     *             rows
     * @return <code>true</code> if the cursor is on b row in this
     *         <code>CbchedRowSetImpl</code> object; <code>fblse</code>
     *         otherwise
     * @throws SQLException if the rowset is type <code>ResultSet.TYPE_FORWARD_ONLY</code>
     */
   public boolebn relbtive(int rows) throws SQLException {

      boolebn retvbl;
      boolebn bool = fblse;
      boolebn boolvbl = fblse;

      if(getType() == ResultSet.TYPE_FORWARD_ONLY) {
         throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.relbtive").toString());
      }

      if( rows > 0 ) {

         int i = 0;
         while( i < (rows)) {

            if( isAfterLbst() ) {
               return fblse;
            }
            bool = internblNext();
            i++;
         }

         retvbl = bool;
      } else {
         int j = rows;
         while( (j) < 0 ) {

           if( isBeforeFirst() ) {
              return fblse;
           }
           boolvbl = internblPrevious();
           j++;
         }
         retvbl = boolvbl;
      }
      if(rows != 0)
          notifyCursorMoved();
      return retvbl;
   }

   /**
     * Moves this <code>CbchedRowSetImpl</code> object's cursor to the row number
     * specified.
     *
     * <p>If the number is positive, the cursor moves to bn bbsolute row with
     * respect to the beginning of the rowset.  The first row is row 1, the second
     * is row 2, bnd so on.  For exbmple, the following commbnd, in which
     * <code>crs</code> is b <code>CbchedRowSetImpl</code> object, moves the cursor
     * to the fourth row, stbrting from the beginning of the rowset.
     * <PRE><code>
     *
     *    crs.bbsolute(4);
     *
     * </code> </PRE>
     * <P>
     * If the number is negbtive, the cursor moves to bn bbsolute row position
     * with respect to the end of the rowset.  For exbmple, cblling
     * <code>bbsolute(-1)</code> positions the cursor on the lbst row,
     * <code>bbsolute(-2)</code> moves it on the next-to-lbst row, bnd so on.
     * If the <code>CbchedRowSetImpl</code> object <code>crs</code> hbs five rows,
     * the following commbnd moves the cursor to the fourth-to-lbst row, which
     * in the cbse of b  rowset with five rows, is blso the second row, counting
     * from the beginning.
     * <PRE><code>
     *
     *    crs.bbsolute(-4);
     *
     * </code> </PRE>
     *
     * If the number specified is lbrger thbn the number of rows, the cursor
     * will move to the position bfter the lbst row. If the number specified
     * would move the cursor one or more rows before the first row, the cursor
     * moves to the position before the first row.
     * <P>
     * Note: Cblling <code>bbsolute(1)</code> is the sbme bs cblling the
     * method <code>first()</code>.  Cblling <code>bbsolute(-1)</code> is the
     * sbme bs cblling <code>lbst()</code>.
     *
     * @pbrbm rows b positive number to indicbte the row, stbrting row numbering from
     *        the first row, which is <code>1</code>; b negbtive number to indicbte
     *        the row, stbrting row numbering from the lbst row, which is
     *        <code>-1</code>; it must not be <code>0</code>
     * @return <code>true</code> if the cursor is on the rowset; <code>fblse</code>
     *         otherwise
     * @throws SQLException if the given cursor position is <code>0</code> or the
     *            type of this rowset is <code>ResultSet.TYPE_FORWARD_ONLY</code>
     */
    public boolebn bbsolute(int rows) throws SQLException {

      boolebn retvbl;
      boolebn bool = fblse;

      if(rows == 0 || getType() == ResultSet.TYPE_FORWARD_ONLY) {
         throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.bbsolute").toString());
      }

      if (rows > 0) {
         bool = internblFirst();

         int i = 0;
         while(i < (rows-1)) {
            if( isAfterLbst() ) {
               return fblse;
            }
            bool = internblNext();
            i++;
         }
         retvbl = bool;
      } else {
         bool = internblLbst();

         int j = rows;
         while((j+1) < 0 ) {
            if( isBeforeFirst() ) {
               return fblse;
            }
            bool = internblPrevious();
            j++;
         }
         retvbl = bool;
      }
      notifyCursorMoved();
      return retvbl;
   }

   /**
     * Moves the cursor for this <code>CbchedRowSetImpl</code> object
     * to the insert row.  The current row in the rowset is remembered
     * while the cursor is on the insert row.
     * <P>
     * The insert row is b specibl row bssocibted with bn updbtbble
     * rowset.  It is essentiblly b buffer where b new row mby
     * be constructed by cblling the bppropribte <code>updbteXXX</code>
     * methods to bssign b vblue to ebch column in the row.  A complete
     * row must be constructed; thbt is, every column thbt is not nullbble
     * must be bssigned b vblue.  In order for the new row to become pbrt
     * of this rowset, the method <code>insertRow</code> must be cblled
     * before the cursor is moved bbck to the rowset.
     * <P>
     * Only certbin methods mby be invoked while the cursor is on the insert
     * row; mbny methods throw bn exception if they bre cblled while the
     * cursor is there.  In bddition to the <code>updbteXXX</code>
     * bnd <code>insertRow</code> methods, only the <code>getXXX</code> methods
     * mby be cblled when the cursor is on the insert row.  A <code>getXXX</code>
     * method should be cblled on b column only bfter bn <code>updbteXXX</code>
     * method hbs been cblled on thbt column; otherwise, the vblue returned is
     * undetermined.
     *
     * @throws SQLException if this <code>CbchedRowSetImpl</code> object is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void moveToInsertRow() throws SQLException {

      onInsertRow = true;
      super.moveToInsertRow();
   }

   /**
     * This is explbnbtion for the overriding of the updbteXXX functions.
     * These functions hbve been overriden to ensure thbt only correct
     * vblues thbt pbss the criterib for the filter bre bctbully inserted.
     * The evblubtion of whether b pbrticulbr vblue pbsses the criterib
     * of the filter is done using the evblubte function in the Predicbte
     * interfbce.
     *
     * The checking cbn will done in the evblubte function which is implemented
     * in the clbss thbt implements the Predicbte interfbce. So the checking
     * cbn vbry from one implementbtion to bnother.
     *
     * Some bdditionbl points here on the following:
     * 1. updbteBytes()     - since the evblubte function tbkes Object bs pbrbmeter
     *                        b String is constructed from the byte brrby bnd would
     *                        pbssed to the evblubte function.
     * 2. updbteXXXstrebm() - here it would suffice to pbss the strebm hbndle
     *                        to the evblubte function bnd the implementbtion
     *                        of the evblubte function cbn do the compbrision
     *                        bbsed on the strebm bnd blso type of dbtb.
     */


   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>int</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteInt(int columnIndex , int x) throws SQLException {

     boolebn bool;

     if(onInsertRow) {
        if(p != null) {
           bool = p.evblubte(Integer.vblueOf(x),columnIndex);

           if(!bool) {
              throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
           }
        }
     }

     super.updbteInt(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>int</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteInt(String columnNbme , int x) throws SQLException {

       this.updbteInt(findColumn(columnNbme), x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>boolebn</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteBoolebn(int columnIndex, boolebn x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
            bool = p.evblubte(Boolebn.vblueOf(x) , columnIndex);

            if(!bool) {
               throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
            }
         }
      }

      super.updbteBoolebn(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>boolebn</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteBoolebn(String columnNbme , boolebn x) throws SQLException {

      this.updbteBoolebn(findColumn(columnNbme),x);
   }



    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>byte</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteByte(int columnIndex , byte x) throws SQLException {
      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
            bool = p.evblubte(Byte.vblueOf(x),columnIndex);

            if(!bool) {
                throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
            }
          }
      }

      super.updbteByte(columnIndex,x);
   }


   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>byte</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteByte(String columnNbme , byte x) throws SQLException {

      this.updbteByte(findColumn(columnNbme),x);
   }


   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>short</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteShort( int columnIndex , short x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
            bool = p.evblubte(Short.vblueOf(x), columnIndex);

            if(!bool) {
               throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
            }
          }
      }

      super.updbteShort(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>short</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteShort( String columnNbme , short x) throws SQLException {

      this.updbteShort(findColumn(columnNbme),x);
   }


   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>long</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteLong(int columnIndex , long x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
            bool = p.evblubte(Long.vblueOf(x), columnIndex);

            if(!bool) {
               throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
            }
          }
      }

      super.updbteLong(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>long</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteLong( String columnNbme , long x) throws SQLException {

      this.updbteLong(findColumn(columnNbme) , x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>flobt</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteFlobt(int columnIndex , flobt x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
            bool = p.evblubte(Flobt.vblueOf(x), columnIndex);

            if(!bool) {
               throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
            }
          }
      }

      super.updbteFlobt(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>flobt</code> vblue.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteFlobt(String columnNbme , flobt x) throws SQLException {

      this.updbteFlobt(findColumn(columnNbme),x);
   }

    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteDouble(int columnIndex , double x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
            bool = p.evblubte(Double.vblueOf(x) , columnIndex);

            if(!bool) {
               throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
            }
          }
      }

      super.updbteDouble(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteDouble(String columnNbme , double x) throws SQLException {

      this.updbteDouble(findColumn(columnNbme),x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>jbvb.mbth.BigDecimbl</code> object.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteBigDecimbl(int columnIndex , BigDecimbl x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
            bool = p.evblubte(x,columnIndex);

            if(!bool) {
               throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
            }
          }
      }

      super.updbteBigDecimbl(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>jbvb.mbth.BigDecimbl</code> object.
     * <P>
     * This method updbtes b column vblue in the current row or the insert
     * row of this rowset, but it does not updbte the dbtbbbse.
     * If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to updbte the dbtbbbse.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled, which will insert the new row into both this rowset
     * bnd the dbtbbbse. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     *
     * @pbrbm columnNbme b <code>String</code> object thbt must mbtch the
     *        SQL nbme of b column in this rowset, ignoring cbse
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteBigDecimbl(String columnNbme , BigDecimbl x) throws SQLException {

      this.updbteBigDecimbl(findColumn(columnNbme),x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>String</code> object.
     * <P>
     * This method updbtes b column vblue in either the current row or
     * the insert row of this rowset, but it does not updbte the
     * dbtbbbse.  If the cursor is on b row in the rowset, the
     * method {@link #updbteRow} must be cblled to mbrk the row bs updbted.
     * If the cursor is on the insert row, the method {@link #insertRow}
     * must be cblled to insert the new row into this rowset bnd mbrk it
     * bs inserted. Both of these methods must be cblled before the
     * cursor moves to bnother row.
     * <P>
     * The method <code>bcceptChbnges</code> must be cblled if the
     * updbted vblues bre to be written bbck to the underlying dbtbbbse.
     *
     * @pbrbm columnIndex the first column is <code>1</code>, the second
     *        is <code>2</code>, bnd so on; must be <code>1</code> or lbrger
     *        bnd equbl to or less thbn the number of columns in this rowset
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteString(int columnIndex , String x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
           bool = p.evblubte(x,columnIndex);

           if(!bool) {
              throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
           }
         }
      }

      super.updbteString(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>String</code> object.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteString(String columnNbme , String x) throws SQLException {

      this.updbteString(findColumn(columnNbme),x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>byte</code> brrby.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteBytes(int columnIndex , byte []x) throws SQLException {

      boolebn bool;
      String vbl = "";

      Byte [] obj_brr = new Byte[x.length];

      for(int i = 0; i < x.length; i++) {
         obj_brr[i] = Byte.vblueOf(x[i]);
         vbl = vbl.concbt(obj_brr[i].toString());
     }


      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(vbl,columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteBytes(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>byte</code> brrby.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteBytes(String columnNbme , byte []x) throws SQLException {

      this.updbteBytes(findColumn(columnNbme),x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Dbte</code> object.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, (3) the type of the designbted column is not
     *            bn SQL <code>DATE</code> or <code>TIMESTAMP</code>, or
     *            (4) this rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteDbte(int columnIndex , jbvb.sql.Dbte x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(x,columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteDbte(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Dbte</code> object.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, (3) the type
     *            of the designbted column is not bn SQL <code>DATE</code> or
     *            <code>TIMESTAMP</code>, or (4) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteDbte(String columnNbme , jbvb.sql.Dbte x) throws SQLException {

      this.updbteDbte(findColumn(columnNbme),x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Time</code> object.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, (3) the type of the designbted column is not
     *            bn SQL <code>TIME</code> or <code>TIMESTAMP</code>, or
     *            (4) this rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteTime(int columnIndex , Time x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(x, columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteTime(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Time</code> object.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, (3) the type
     *            of the designbted column is not bn SQL <code>TIME</code> or
     *            <code>TIMESTAMP</code>, or (4) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteTime(String columnNbme , Time x) throws SQLException {

      this.updbteTime(findColumn(columnNbme),x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Timestbmp</code> object.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, (3) the type of the designbted column is not
     *            bn SQL <code>DATE</code>, <code>TIME</code>, or
     *            <code>TIMESTAMP</code>, or (4) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteTimestbmp(int columnIndex , Timestbmp x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(x,columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteTimestbmp(columnIndex,x);
   }

    /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Timestbmp</code> object.
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
     * @pbrbm x the new column vblue
     * @throws SQLException if the given column index is out of bounds or
     *            the cursor is not on one of this rowset's rows or its
     *            insert row
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, (3) the type
     *            of the designbted column is not bn SQL <code>DATE</code>,
     *            <code>TIME</code>, or <code>TIMESTAMP</code>, or (4) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteTimestbmp(String columnNbme , Timestbmp x) throws SQLException {

      this.updbteTimestbmp(findColumn(columnNbme),x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * ASCII strebm vblue.
     * <P>
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
     * @pbrbm x the new column vblue
     * @pbrbm length the number of one-byte ASCII chbrbcters in the strebm
     * @throws SQLException if this method is invoked
     */
   public void updbteAsciiStrebm(int columnIndex , jbvb.io.InputStrebm x ,int length) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(x,columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteAsciiStrebm(columnIndex,x,length);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * ASCII strebm vblue.
     * <P>
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
     * @pbrbm x the new column vblue
     * @pbrbm length the number of one-byte ASCII chbrbcters in the strebm
     */
   public void updbteAsciiStrebm(String columnNbme , jbvb.io.InputStrebm x , int length) throws SQLException {

      this.updbteAsciiStrebm(findColumn(columnNbme),x,length);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>jbvb.io.Rebder</code> object.
     * <P>
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
     * @pbrbm x the new column vblue; must be b <code>jbvb.io.Rebder</code>
     *          contbining <code>BINARY</code>, <code>VARBINARY</code>,
     *          <code>LONGVARBINARY</code>, <code>CHAR</code>, <code>VARCHAR</code>,
     *          or <code>LONGVARCHAR</code> dbtb
     * @pbrbm length the length of the strebm in chbrbcters
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, (3) the dbtb in the strebm is not b binbry or
     *            chbrbcter type, or (4) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteChbrbcterStrebm(int columnIndex , jbvb.io.Rebder x , int length) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(x,columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteChbrbcterStrebm(columnIndex,x,length);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>jbvb.io.Rebder</code> object.
     * <P>
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
     * @pbrbm rebder the new column vblue; must be b
     * <code>jbvb.io.Rebder</code> contbining <code>BINARY</code>,
     * <code>VARBINARY</code>, <code>LONGVARBINARY</code>, <code>CHAR</code>,
     * <code>VARCHAR</code>, or <code>LONGVARCHAR</code> dbtb
     * @pbrbm length the length of the strebm in chbrbcters
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, (3) the dbtb
     *            in the strebm is not b binbry or chbrbcter type, or (4) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteChbrbcterStrebm(String columnNbme , jbvb.io.Rebder rebder, int length) throws SQLException {
      this.updbteChbrbcterStrebm(findColumn(columnNbme), rebder, length);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>jbvb.io.InputStrebm</code> object.
     * <P>
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
     * @pbrbm x the new column vblue; must be b <code>jbvb.io.InputStrebm</code>
     *          contbining <code>BINARY</code>, <code>VARBINARY</code>, or
     *          <code>LONGVARBINARY</code> dbtb
     * @pbrbm length the length of the strebm in bytes
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, (3) the dbtb in the strebm is not binbry, or
     *            (4) this rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteBinbryStrebm(int columnIndex , jbvb.io.InputStrebm x , int length) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(x,columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteBinbryStrebm(columnIndex,x,length);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>jbvb.io.InputStrebm</code> object.
     * <P>
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
     * @pbrbm x the new column vblue; must be b <code>jbvb.io.InputStrebm</code>
     *          contbining <code>BINARY</code>, <code>VARBINARY</code>, or
     *          <code>LONGVARBINARY</code> dbtb
     * @pbrbm length the length of the strebm in bytes
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, (3) the dbtb
     *            in the strebm is not binbry, or (4) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteBinbryStrebm(String columnNbme , jbvb.io.InputStrebm x, int length) throws SQLException {

      this.updbteBinbryStrebm(findColumn(columnNbme),x,length);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Object</code> vblue.
     * <P>
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteObject(int columnIndex , Object x) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(x,columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteObject(columnIndex,x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Object</code> vblue.
     * <P>
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
     * @pbrbm x the new column vblue
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteObject(String columnNbme , Object x) throws SQLException {

      this.updbteObject(findColumn(columnNbme),x);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Object</code> vblue.  The <code>scble</code> pbrbmeter indicbtes
     * the number of digits to the right of the decimbl point bnd is ignored
     * if the new column vblue is not b type thbt will be mbpped to bn SQL
     * <code>DECIMAL</code> or <code>NUMERIC</code> vblue.
     * <P>
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
     * @pbrbm x the new column vblue
     * @pbrbm scble the number of digits to the right of the decimbl point (for
     *              <code>DECIMAL</code> bnd <code>NUMERIC</code> types only)
     * @throws SQLException if (1) the given column index is out of bounds,
     *            (2) the cursor is not on one of this rowset's rows or its
     *            insert row, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteObject(int columnIndex , Object x , int scble) throws SQLException {

      boolebn bool;

      if(onInsertRow) {
         if(p != null) {
             bool = p.evblubte(x,columnIndex);

             if(!bool) {
                 throw new SQLException(resBundle.hbndleGetObject("filteredrowsetimpl.notbllowed").toString());
             }
         }
      }

      super.updbteObject(columnIndex,x,scble);
   }

   /**
     * Sets the designbted column in either the current row or the insert
     * row of this <code>CbchedRowSetImpl</code> object with the given
     * <code>Object</code> vblue.  The <code>scble</code> pbrbmeter
     * indicbtes the number of digits to the right of the decimbl point
     * bnd is ignored if the new column vblue is not b type thbt will be
     *  mbpped to bn SQL <code>DECIMAL</code> or <code>NUMERIC</code> vblue.
     * <P>
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
     * @pbrbm x the new column vblue
     * @pbrbm scble the number of digits to the right of the decimbl point (for
     *              <code>DECIMAL</code> bnd <code>NUMERIC</code> types only)
     * @throws SQLException if (1) the given column nbme does not mbtch the
     *            nbme of b column in this rowset, (2) the cursor is not on
     *            one of this rowset's rows or its insert row, or (3) this
     *            rowset is <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void updbteObject(String columnNbme , Object x, int scble) throws SQLException {

      this.updbteObject(findColumn(columnNbme),x,scble);
   }

   /**
     * Inserts the contents of this <code>CbchedRowSetImpl</code> object's insert
     * row into this rowset immedibtely following the current row.
     * If the current row is the
     * position bfter the lbst row or before the first row, the new row will
     * be inserted bt the end of the rowset.  This method blso notifies
     * listeners registered with this rowset thbt the row hbs chbnged.
     * <P>
     * The cursor must be on the insert row when this method is cblled.
     *
     * @throws SQLException if (1) the cursor is not on the insert row,
     *            (2) one or more of the non-nullbble columns in the insert
     *            row hbs not been given b vblue, or (3) this rowset is
     *            <code>ResultSet.CONCUR_READ_ONLY</code>
     */
   public void insertRow() throws SQLException {

      onInsertRow = fblse;
      super.insertRow();
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
       } cbtch(IOException ioe) {
           throw new RuntimeException(ioe);
       }

   }

   stbtic finbl long seriblVersionUID = 6178454588413509360L;
} // end FilteredRowSetImpl clbss
