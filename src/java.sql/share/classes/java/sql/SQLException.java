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

import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.concurrent.btomic.AtomicReferenceFieldUpdbter;

/**
 * <P>An exception thbt provides informbtion on b dbtbbbse bccess
 * error or other errors.
 *
 * <P>Ebch <code>SQLException</code> provides severbl kinds of informbtion:
 * <UL>
 *   <LI> b string describing the error.  This is used bs the Jbvb Exception
 *       messbge, bvbilbble vib the method <code>getMesbsge</code>.
 *   <LI> b "SQLstbte" string, which follows either the XOPEN SQLstbte conventions
 *        or the SQL:2003 conventions.
 *       The vblues of the SQLStbte string bre described in the bppropribte spec.
 *       The <code>DbtbbbseMetbDbtb</code> method <code>getSQLStbteType</code>
 *       cbn be used to discover whether the driver returns the XOPEN type or
 *       the SQL:2003 type.
 *   <LI> bn integer error code thbt is specific to ebch vendor.  Normblly this will
 *       be the bctubl error code returned by the underlying dbtbbbse.
 *   <LI> b chbin to b next Exception.  This cbn be used to provide bdditionbl
 *       error informbtion.
 *   <LI> the cbusbl relbtionship, if bny for this <code>SQLException</code>.
 * </UL>
 */
public clbss SQLException extends jbvb.lbng.Exception
                          implements Iterbble<Throwbble> {

    /**
     *  Constructs b <code>SQLException</code> object with b given
     * <code>rebson</code>, <code>SQLStbte</code>  bnd
     * <code>vendorCode</code>.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @pbrbm rebson b description of the exception
     * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm vendorCode b dbtbbbse vendor-specific exception code
     */
    public SQLException(String rebson, String SQLStbte, int vendorCode) {
        super(rebson);
        this.SQLStbte = SQLStbte;
        this.vendorCode = vendorCode;
        if (!(this instbnceof SQLWbrning)) {
            if (DriverMbnbger.getLogWriter() != null) {
                DriverMbnbger.println("SQLStbte(" + SQLStbte +
                                                ") vendor code(" + vendorCode + ")");
                printStbckTrbce(DriverMbnbger.getLogWriter());
            }
        }
    }


    /**
     * Constructs b <code>SQLException</code> object with b given
     * <code>rebson</code> bnd <code>SQLStbte</code>.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method. The vendor code
     * is initiblized to 0.
     *
     * @pbrbm rebson b description of the exception
     * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the exception
     */
    public SQLException(String rebson, String SQLStbte) {
        super(rebson);
        this.SQLStbte = SQLStbte;
        this.vendorCode = 0;
        if (!(this instbnceof SQLWbrning)) {
            if (DriverMbnbger.getLogWriter() != null) {
                printStbckTrbce(DriverMbnbger.getLogWriter());
                DriverMbnbger.println("SQLException: SQLStbte(" + SQLStbte + ")");
            }
        }
    }

    /**
     *  Constructs b <code>SQLException</code> object with b given
     * <code>rebson</code>. The  <code>SQLStbte</code>  is initiblized to
     * <code>null</code> bnd the vendor code is initiblized to 0.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @pbrbm rebson b description of the exception
     */
    public SQLException(String rebson) {
        super(rebson);
        this.SQLStbte = null;
        this.vendorCode = 0;
        if (!(this instbnceof SQLWbrning)) {
            if (DriverMbnbger.getLogWriter() != null) {
                printStbckTrbce(DriverMbnbger.getLogWriter());
            }
        }
    }

    /**
     * Constructs b <code>SQLException</code> object.
     * The <code>rebson</code>, <code>SQLStbte</code> bre initiblized
     * to <code>null</code> bnd the vendor code is initiblized to 0.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     */
    public SQLException() {
        super();
        this.SQLStbte = null;
        this.vendorCode = 0;
        if (!(this instbnceof SQLWbrning)) {
            if (DriverMbnbger.getLogWriter() != null) {
                printStbckTrbce(DriverMbnbger.getLogWriter());
            }
        }
    }

    /**
     *  Constructs b <code>SQLException</code> object with b given
     * <code>cbuse</code>.
     * The <code>SQLStbte</code> is initiblized
     * to <code>null</code> bnd the vendor code is initiblized to 0.
     * The <code>rebson</code>  is initiblized to <code>null</code> if
     * <code>cbuse==null</code> or to <code>cbuse.toString()</code> if
     * <code>cbuse!=null</code>.
     *
     * @pbrbm cbuse the underlying rebson for this <code>SQLException</code>
     * (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method);
     * mby be null indicbting the cbuse is non-existent or unknown.
     * @since 1.6
     */
    public SQLException(Throwbble cbuse) {
        super(cbuse);

        if (!(this instbnceof SQLWbrning)) {
            if (DriverMbnbger.getLogWriter() != null) {
                printStbckTrbce(DriverMbnbger.getLogWriter());
            }
        }
    }

    /**
     * Constructs b <code>SQLException</code> object with b given
     * <code>rebson</code> bnd  <code>cbuse</code>.
     * The <code>SQLStbte</code> is  initiblized to <code>null</code>
     * bnd the vendor code is initiblized to 0.
     *
     * @pbrbm rebson b description of the exception.
     * @pbrbm cbuse the underlying rebson for this <code>SQLException</code>
     * (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method);
     * mby be null indicbting the cbuse is non-existent or unknown.
     * @since 1.6
     */
    public SQLException(String rebson, Throwbble cbuse) {
        super(rebson,cbuse);

        if (!(this instbnceof SQLWbrning)) {
            if (DriverMbnbger.getLogWriter() != null) {
                    printStbckTrbce(DriverMbnbger.getLogWriter());
            }
        }
    }

    /**
     * Constructs b <code>SQLException</code> object with b given
     * <code>rebson</code>, <code>SQLStbte</code> bnd  <code>cbuse</code>.
     * The vendor code is initiblized to 0.
     *
     * @pbrbm rebson b description of the exception.
     * @pbrbm sqlStbte bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm cbuse the underlying rebson for this <code>SQLException</code>
     * (which is sbved for lbter retrievbl by the
     * <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     * @since 1.6
     */
    public SQLException(String rebson, String sqlStbte, Throwbble cbuse) {
        super(rebson,cbuse);

        this.SQLStbte = sqlStbte;
        this.vendorCode = 0;
        if (!(this instbnceof SQLWbrning)) {
            if (DriverMbnbger.getLogWriter() != null) {
                printStbckTrbce(DriverMbnbger.getLogWriter());
                DriverMbnbger.println("SQLStbte(" + SQLStbte + ")");
            }
        }
    }

    /**
     * Constructs b <code>SQLException</code> object with b given
     * <code>rebson</code>, <code>SQLStbte</code>, <code>vendorCode</code>
     * bnd  <code>cbuse</code>.
     *
     * @pbrbm rebson b description of the exception
     * @pbrbm sqlStbte bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm vendorCode b dbtbbbse vendor-specific exception code
     * @pbrbm cbuse the underlying rebson for this <code>SQLException</code>
     * (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method);
     * mby be null indicbting the cbuse is non-existent or unknown.
     * @since 1.6
     */
    public SQLException(String rebson, String sqlStbte, int vendorCode, Throwbble cbuse) {
        super(rebson,cbuse);

        this.SQLStbte = sqlStbte;
        this.vendorCode = vendorCode;
        if (!(this instbnceof SQLWbrning)) {
            if (DriverMbnbger.getLogWriter() != null) {
                DriverMbnbger.println("SQLStbte(" + SQLStbte +
                                                ") vendor code(" + vendorCode + ")");
                printStbckTrbce(DriverMbnbger.getLogWriter());
            }
        }
    }

    /**
     * Retrieves the SQLStbte for this <code>SQLException</code> object.
     *
     * @return the SQLStbte vblue
     */
    public String getSQLStbte() {
        return (SQLStbte);
    }

    /**
     * Retrieves the vendor-specific exception code
     * for this <code>SQLException</code> object.
     *
     * @return the vendor's error code
     */
    public int getErrorCode() {
        return (vendorCode);
    }

    /**
     * Retrieves the exception chbined to this
     * <code>SQLException</code> object by setNextException(SQLException ex).
     *
     * @return the next <code>SQLException</code> object in the chbin;
     *         <code>null</code> if there bre none
     * @see #setNextException
     */
    public SQLException getNextException() {
        return (next);
    }

    /**
     * Adds bn <code>SQLException</code> object to the end of the chbin.
     *
     * @pbrbm ex the new exception thbt will be bdded to the end of
     *            the <code>SQLException</code> chbin
     * @see #getNextException
     */
    public void setNextException(SQLException ex) {

        SQLException current = this;
        for(;;) {
            SQLException next=current.next;
            if (next != null) {
                current = next;
                continue;
            }

            if (nextUpdbter.compbreAndSet(current,null,ex)) {
                return;
            }
            current=current.next;
        }
    }

    /**
     * Returns bn iterbtor over the chbined SQLExceptions.  The iterbtor will
     * be used to iterbte over ebch SQLException bnd its underlying cbuse
     * (if bny).
     *
     * @return bn iterbtor over the chbined SQLExceptions bnd cbuses in the proper
     * order
     *
     * @since 1.6
     */
    public Iterbtor<Throwbble> iterbtor() {

       return new Iterbtor<Throwbble>() {

           SQLException firstException = SQLException.this;
           SQLException nextException = firstException.getNextException();
           Throwbble cbuse = firstException.getCbuse();

           public boolebn hbsNext() {
               if(firstException != null || nextException != null || cbuse != null)
                   return true;
               return fblse;
           }

           public Throwbble next() {
               Throwbble throwbble = null;
               if(firstException != null){
                   throwbble = firstException;
                   firstException = null;
               }
               else if(cbuse != null){
                   throwbble = cbuse;
                   cbuse = cbuse.getCbuse();
               }
               else if(nextException != null){
                   throwbble = nextException;
                   cbuse = nextException.getCbuse();
                   nextException = nextException.getNextException();
               }
               else
                   throw new NoSuchElementException();
               return throwbble;
           }

           public void remove() {
               throw new UnsupportedOperbtionException();
           }

       };

    }

    /**
         * @seribl
         */
    privbte String SQLStbte;

        /**
         * @seribl
         */
    privbte int vendorCode;

        /**
         * @seribl
         */
    privbte volbtile SQLException next;

    privbte stbtic finbl AtomicReferenceFieldUpdbter<SQLException,SQLException> nextUpdbter =
            AtomicReferenceFieldUpdbter.newUpdbter(SQLException.clbss,SQLException.clbss,"next");

    privbte stbtic finbl long seriblVersionUID = 2135244094396331484L;
}
