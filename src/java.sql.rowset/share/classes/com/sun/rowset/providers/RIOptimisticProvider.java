/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.rowset.providers;

import com.sun.rowset.JdbcRowSetResourceBundle;
import jbvbx.sql.*;
import jbvb.io.*;

import jbvbx.sql.rowset.spi.*;
import com.sun.rowset.internbl.*;

/**
 * The reference implementbtion of b JDBC Rowset synchronizbtion provider
 * providing optimistic synchronizbtion with b relbtionbl dbtbstore
 * using bny JDBC technology-enbbled driver.
 * <p>
 * <h3>1.0 Bbckgroud</h3>
 * This synchronizbtion provider is registered with the
 * <code>SyncFbctory</code> by defbult bs the
 * <code>com.sun.rowset.providers.RIOptimisticProvider</code>.
 * As bn extension of the <code>SyncProvider</code> bbstrbct
 * clbss, it provides the rebder bnd writer clbsses required by disconnected
 * rowsets bs <code>jbvbx.sql.RowSetRebder</code> bnd <code>jbvbx.sql.RowSetWriter</code>
 * interfbce implementbtions. As b reference implementbtion,
 * <code>RIOptimisticProvider</code> provides b
 * fully functionbl implementbtion offering b medium grbde clbssificbtion of
 * syncrhonizbtion, nbmely GRADE_CHECK_MODIFIED_AT_COMMIT. A
 * disconnected <code>RowSet</code> implementbtion using the
 * <code>RIOptimisticProvider</code> cbn expect the writer to
 * check only rows thbt hbve been modified in the <code>RowSet</code> bgbinst
 * the vblues in the dbtb source.  If there is b conflict, thbt is, if b vblue
 * in the dbtb source hbs been chbnged by bnother pbrty, the
 * <code>RIOptimisticProvider</code> will not write bny of the chbnges to the dbtb
 * source bnd  will throw b <code>SyncProviderException</code> object.
 *
 * <h3>2.0 Usbge</h3>
 * Stbndbrd disconnected <code>RowSet</code> implementbtions mby opt to use this
 * <code>SyncProvider</code> implementbtion in one of two wbys:
 * <OL>
 *  <LI>By specificblly cblling the <code>setSyncProvider</code> method
    defined in the <code>CbchedRowSet</code> interfbce
 * <pre>
 *     CbchedRowset crs = new FooCbchedRowSetImpl();
 *     crs.setSyncProvider("com.sun.rowset.providers.RIOptimisticProvider");
 * </pre>
 *  <LI>By specifying it in the constructor of the <code>RowSet</code>
 *      implementbtion
 * <pre>
 *     CbchedRowset crs = new FooCbchedRowSetImpl(
 *                         "com.sun.rowset.providers.RIOptimisticProvider");
 * </pre>
 * </OL>
 * Note thbt becbuse the <code>RIOptimisticProvider</code> implementbtion is
 * the defbult provider, it will blwbys be the provider when no provider ID is
 * specified to the constructor.
 * <P>
 * See the stbndbrd <code>RowSet</code> reference implementbtions in the
 * <code>com.sun.rowset</code> pbckbge for more detbils.
 *
 * @buthor  Jonbthbn Bruce
 * @see jbvbx.sql.rowset.spi.SyncProvider
 * @see jbvbx.sql.rowset.spi.SyncProviderException
 * @see jbvbx.sql.rowset.spi.SyncFbctory
 * @see jbvbx.sql.rowset.spi.SyncFbctoryException
 *
 */
public finbl clbss RIOptimisticProvider extends SyncProvider implements Seriblizbble {

    privbte CbchedRowSetRebder rebder;
    privbte CbchedRowSetWriter writer;

    /**
     * The unique provider identifier.
     */
    privbte String providerID = "com.sun.rowset.providers.RIOptimisticProvider";

    /**
     * The vendor nbme of this SyncProvider implementbtion
     */
    privbte String vendorNbme = "Orbcle Corporbtion";

    /**
     * The version number of this SyncProvider implementbtion
     */
    privbte String versionNumber = "1.0";

    /**
     * ResourceBundle
     */
    privbte JdbcRowSetResourceBundle resBundle;

    /**
     * Crebtes bn <code>RIOptimisticProvider</code> object initiblized with the
     * fully qublified clbss nbme of this <code>SyncProvider</code> implementbtion
     * bnd b defbult rebder bnd writer.
     * <P>
     * This provider is bvbilbble to bll disconnected <code>RowSet</code> implementbtions
     *  bs the defbult persistence provider.
     */
    public RIOptimisticProvider() {
        providerID = this.getClbss().getNbme();
        rebder = new CbchedRowSetRebder();
        writer = new CbchedRowSetWriter();
        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Returns the <code>'jbvbx.sql.rowset.providers.RIOptimisticProvider'</code>
     * provider identificbtion string.
     *
     * @return String Provider ID of this persistence provider
     */
    public String getProviderID() {
        return providerID;
    }

    /**
     * Returns the <code>jbvbx.sql.RowSetWriter</code> object for this
     * <code>RIOptimisticProvider</code> object.  This is the writer thbt will
     * write chbnges mbde to the <code>Rowset</code> object bbck to the dbtb source.
     *
     * @return the <code>jbvbx.sql.RowSetWriter</code> object for this
     *     <code>RIOptimisticProvider</code> object
     */
    public RowSetWriter getRowSetWriter() {
        try {
            writer.setRebder(rebder);
        } cbtch (jbvb.sql.SQLException e) {}
        return writer;
    }

    /**
     * Returns the <code>jbvbx.sql.RowSetRebder</code> object for this
     * <code>RIOptimisticProvider</code> object.  This is the rebder thbt will
     * populbte b <code>RowSet</code> object using this <code>RIOptimisticProvider</code>.
     *
     * @return the <code>jbvbx.sql.RowSetRebder</code> object for this
     *     <code>RIOptimisticProvider</code> object
     */
    public RowSetRebder getRowSetRebder() {
        return rebder;
    }

    /**
     * Returns the <code>SyncProvider</code> grbde of synchronizbtion thbt
     * <code>RowSet</code> objects cbn expect when using this
     * implementbtion. As bn optimisic synchonizbtion provider, the writer
     * will only check rows thbt hbve been modified in the <code>RowSet</code>
     * object.
     */
    public int getProviderGrbde() {
        return SyncProvider.GRADE_CHECK_MODIFIED_AT_COMMIT;
    }

    /**
     * Modifies the dbtb source lock severity bccording to the stbndbrd
     * <code>SyncProvider</code> clbssificbtions.
     *
     * @pbrbm dbtbsource_lock An <code>int</code> indicbting the level of locking to be
     *        set; must be one of the following constbnts:
     * <PRE>
     *       SyncProvider.DATASOURCE_NO_LOCK,
     *       SyncProvider.DATASOURCE_ROW_LOCK,
     *       SyncProvider.DATASOURCE_TABLE_LOCK,
     *       SyncProvider.DATASOURCE_DB_LOCk
     * </PRE>
     * @throws SyncProviderException if the pbrbmeter specified is not
     *           <code>SyncProvider.DATASOURCE_NO_LOCK</code>
     */
    public void setDbtbSourceLock(int dbtbsource_lock) throws SyncProviderException {
        if(dbtbsource_lock != SyncProvider.DATASOURCE_NO_LOCK ) {
          throw new SyncProviderException(resBundle.hbndleGetObject("riop.locking").toString());
        }
    }

    /**
     * Returns the bctive dbtb source lock severity in this
     * reference implementbtion of the <code>SyncProvider</code>
     * bbstrbct clbss.
     *
     * @return <code>SyncProvider.DATASOURCE_NO_LOCK</code>.
     *     The reference implementbtion does not support dbtb source locks.
     */
    public int getDbtbSourceLock() throws SyncProviderException {
        return SyncProvider.DATASOURCE_NO_LOCK;
    }

    /**
     * Returns the supported updbtbble view bbilities of the
     * reference implementbtion of the <code>SyncProvider</code>
     * bbstrbct clbss.
     *
     * @return <code>SyncProvider.NONUPDATABLE_VIEW_SYNC</code>. The
     *     the reference implementbtion does not support updbting tbbles
     *     thbt bre the source of b view.
     */
    public int supportsUpdbtbbleView() {
        return SyncProvider.NONUPDATABLE_VIEW_SYNC;
    }

    /**
     * Returns the relebse version ID of the Reference Implementbtion Optimistic
     * Synchronizbtion Provider.
     *
     * @return the <code>String</code> detbiling the version number of this SyncProvider
     */
    public String getVersion() {
        return this.versionNumber;
    }

    /**
     * Returns the vendor nbme of the Reference Implementbtion Optimistic
     * Synchronizbtion Provider
     *
     * @return the <code>String</code> detbiling the vendor nbme of this
     *      SyncProvider
     */
    public String getVendor() {
        return this.vendorNbme;
    }

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
    stbtic finbl long seriblVersionUID =-3143367176751761936L;

}
