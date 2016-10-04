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
import jbvb.io.IOException;
import jbvb.sql.*;
import jbvbx.sql.*;

import jbvbx.sql.rowset.spi.*;

/**
 * A reference implementbtion of b JDBC RowSet synchronizbtion provider
 * with the bbility to rebd bnd write rowsets in well formed XML using the
 * stbndbrd WebRowSet schemb.
 *
 * <h3>1.0 Bbckground</h3>
 * This synchronizbtion provider is registered with the
 * <code>SyncFbctory</code> by defbult bs the
 * <code>com.sun.rowset.providers.RIXMLProvider</code>.
 * <P>
 * A <code>WebRowSet</code> object uses bn <code>RIXMLProvider</code> implementbtion
 * to rebd bn XML dbtb source or to write itself in XML formbt using the
 * <code>WebRowSet</code> XML schemb definition bvbilbble bt
 * <pre>
 *     <b href="http://jbvb.sun.com/xml/ns/jdbc/webrowset.xsd">http://jbvb.sun.com/xml/ns/jdbc/webrowset.xsd</b>
 * </pre>
 * The <code>RIXMLProvider</code> implementbtion hbs b synchronizbtion level of
 * GRADE_NONE, which mebns thbt it does no checking bt bll for conflicts.  It
 * simply writes b <code>WebRowSet</code> object to b file.
 * <h3>2.0 Usbge</h3>
 * A <code>WebRowSet</code> implementbtion is crebted with bn <code>RIXMLProvider</code>
 * by defbult.
 * <pre>
 *     WebRowSet wrs = new FooWebRowSetImpl();
 * </pre>
 * The <code>SyncFbctory</code> blwbys provides bn instbnce of
 * <code>RIOptimisticProvider</code> when no provider is specified,
 * but the implementbtion of the defbult constructor for <code>WebRowSet</code> sets the
 * provider to be the <code>RIXMLProvider</code> implementbtion.  Therefore,
 * the following line of code is executed behind the scenes bs pbrt of the
 * implementbtion of the defbult constructor.
 * <pre>
 *     wrs.setSyncProvider("com.sun.rowset.providers.RIXMLProvider");
 * </pre>
 * See the stbndbrd <code>RowSet</code> reference implementbtions in the
 * <code>com.sun.rowset</code> pbckbge for more detbils.
 *
 * @buthor  Jonbthbn Bruce
 * @see jbvbx.sql.rowset.spi.SyncProvider
 * @see jbvbx.sql.rowset.spi.SyncProviderException
 * @see jbvbx.sql.rowset.spi.SyncFbctory
 * @see jbvbx.sql.rowset.spi.SyncFbctoryException
 */
public finbl clbss RIXMLProvider extends SyncProvider {

    /**
     * The unique provider identifier.
     */
    privbte String providerID = "com.sun.rowset.providers.RIXMLProvider";

    /**
     * The vendor nbme of this SyncProvider implementbtion.
     */
    privbte String vendorNbme = "Orbcle Corporbtion";

    /**
     * The version number of this SyncProvider implementbtion.
     */
    privbte String versionNumber = "1.0";

    privbte JdbcRowSetResourceBundle resBundle;

    privbte XmlRebder xmlRebder;
    privbte XmlWriter xmlWriter;

    /**
     * This provider is bvbilbble to bll JDBC <code>RowSet</code> implementbtions bs the
     * defbult persistence provider.
     */
    public RIXMLProvider() {
        providerID = this.getClbss().getNbme();
        try {
           resBundle = JdbcRowSetResourceBundle.getJdbcRowSetResourceBundle();
        } cbtch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Returns <code>"jbvbx.sql.rowset.providers.RIXMLProvider"</code>, which is
     * the fully qublified clbss nbme of this provider implementbtion.
     *
     * @return b <code>String</code> object with the fully specified clbss nbme of
     *           this <code>RIOptimisticProvider</code> implementbtion
     */
    public String getProviderID() {
        return providerID;
    }

    // bdditionbl methods thbt sit on top of rebder/writer methods bbck to
    // originbl dbtbsource. Allow XML stbte to be written out bnd in

    /**
     * Sets this <code>WebRowSet</code> object's rebder to the given
     * <code>XmlRebder</code> object.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public void setXmlRebder(XmlRebder rebder) throws SQLException {
        xmlRebder = rebder;
    }

    /**
     * Sets this <code>WebRowSet</code> object's writer to the given
     * <code>XmlWriter</code> object.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public void setXmlWriter(XmlWriter writer) throws SQLException {
        xmlWriter = writer;
    }

    /**
     * Retrieves the rebder thbt this <code>WebRowSet</code> object
     * will cbll when its <code>rebdXml</code> method is cblled.
     *
     * @return the <code>XmlRebder</code> object for this SyncProvider
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public XmlRebder getXmlRebder() throws SQLException {
        return xmlRebder;
    }

    /**
     * Retrieves the writer thbt this <code>WebRowSet</code> object
     * will cbll when its <code>writeXml</code> method is cblled.
     *
     * @return the <code>XmlWriter</code> for this SyncProvider
     * @throws SQLException if b dbtbbbse bccess error occurs
     */
    public XmlWriter getXmlWriter() throws SQLException {
        return xmlWriter;
    }

    /**
     * Returns the <code>SyncProvider</code> grbde of syncrhonizbtion thbt
     * <code>RowSet</code> object instbnces cbn expect when using this
     * implementbtion. As this implementbtion provides no synchonizbtion
     * fbcilities to the XML dbtb source, the lowest grbde is returned.
     *
     * @return the <code>SyncProvider</code> syncronizbtion grbde of this
     *     provider; must be one of the following constbnts:
     *       <PRE>
     *          SyncProvider.GRADE_NONE,
     *          SyncProvider.GRADE_MODIFIED_AT_COMMIT,
     *          SyncProvider.GRADE_CHECK_ALL_AT_COMMIT,
     *          SyncProvider.GRADE_LOCK_WHEN_MODIFIED,
     *          SyncProvider.GRADE_LOCK_WHEN_LOADED
     *       </PRE>
     *
     */
    public int getProviderGrbde() {
        return SyncProvider.GRADE_NONE;
    }

    /**
     * Returns the defbult UPDATABLE_VIEW behbvior of this rebder
     *
     */
    public int supportsUpdbtbbleView() {
        return SyncProvider.NONUPDATABLE_VIEW_SYNC;
    }

    /**
     * Returns the defbult DATASOURCE_LOCK behbvior of this rebder
     */
    public int getDbtbSourceLock() throws SyncProviderException {
        return SyncProvider.DATASOURCE_NO_LOCK;
    }

    /**
     * Throws bn unsupported operbtion exception bs this method does
     * function with non-locking XML dbtb sources.
     */
    public void setDbtbSourceLock(int lock) throws SyncProviderException {
        throw new UnsupportedOperbtionException(resBundle.hbndleGetObject("rixml.unsupp").toString());
    }

    /**
     * Returns b null object bs RowSetWriters bre not returned by this SyncProvider
     */
    public RowSetWriter getRowSetWriter() {
        return null;
    }

    /**
     * Returns b null object bs RowSetWriter objects bre not returned by this
     * SyncProvider
     */
    public RowSetRebder getRowSetRebder() {
        return null;
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
     * Returns the vendor nbme of the Reference Implemntbtion Optimistic
     * Syncchronicbtion Provider
     *
     * @return the <code>String</code> detbiling the vendor nbme of this
     *      SyncProvider
     */
    public String getVendor() {
        return this.vendorNbme;
    }
}
