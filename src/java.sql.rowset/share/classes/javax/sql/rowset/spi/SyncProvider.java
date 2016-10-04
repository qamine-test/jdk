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

pbckbge jbvbx.sql.rowset.spi;

import jbvbx.sql.*;

/**
 * The synchronizbtion mechbnism thbt provides rebder/writer cbpbbilities for
 * disconnected <code>RowSet</code> objects.
 * A <code>SyncProvider</code> implementbtion is b clbss thbt extends the
 * <code>SyncProvider</code> bbstrbct clbss.
 * <P>
 * A <code>SyncProvider</code> implementbtion is
 * identified by b unique ID, which is its fully qublified clbss nbme.
 * This nbme must be registered with the
 * <code>SyncFbctory</code> SPI, thus mbking the implementbtion bvbilbble to
 * bll <code>RowSet</code> implementbtions.
 * The fbctory mechbnism in the reference implementbtion uses this nbme to instbntibte
 * the implementbtion, which cbn then provide b <code>RowSet</code> object with its
 * rebder (b <code>jbvbx.sql.RowSetRebder</code> object) bnd its writer (b
 * <code>jbvbx.sql.RowSetWriter</code> object).
 * <P>
 * The Jdbc <code>RowSet</code> Implementbtions specificbtion provides two
 * reference implementbtions of the <code>SyncProvider</code> bbstrbct clbss:
 * <code>RIOptimisticProvider</code> bnd <code>RIXMLProvider</code>.
 * The <code>RIOptimisticProvider</code> cbn set bny <code>RowSet</code>
 * implementbtion with b <code>RowSetRebder</code> object bnd b
 * <code>RowSetWriter</code> object.  However, only the <code>RIXMLProvider</code>
 * implementbtion cbn set bn <code>XmlRebder</code> object bnd bn
 * <code>XmlWriter</code> object. A <code>WebRowSet</code> object uses the
 * <code>XmlRebder</code> object to rebd dbtb in XML formbt to populbte itself with thbt
 * dbtb.  It uses the <code>XmlWriter</code> object to write itself to b strebm or
 * <code>jbvb.io.Writer</code> object in XML formbt.
 *
 * <h3>1.0 Nbming Convention for Implementbtions</h3>
 * As b guide  to nbming <code>SyncProvider</code>
 * implementbtions, the following should be noted:
 * <UL>
 * <li>The nbme for b <code>SyncProvider</code> implementbtion
 * is its fully qublified clbss nbme.
 * <li>It is recommended thbt vendors supply b
 * <code>SyncProvider</code> implementbtion in b pbckbge nbmed <code>providers</code>.
 * </UL>
 * <p>
 * For instbnce, if b vendor nbmed Fred, Inc. offered b
 * <code>SyncProvider</code> implementbtion, you could hbve the following:
 * <PRE>
 *     Vendor nbme:  Fred, Inc.
 *     Dombin nbme of vendor:  com.fred
 *     Pbckbge nbme:  com.fred.providers
 *     SyncProvider implementbtion clbss nbme:  HighAvbilbbilityProvider
 *
 *     Fully qublified clbss nbme of SyncProvider implementbtion:
 *                        com.fred.providers.HighAvbilbbilityProvider
 * </PRE>
 * <P>
 * The following line of code uses the fully qublified nbme to register
 * this implementbtion with the <code>SyncFbctory</code> stbtic instbnce.
 * <PRE>
 *     SyncFbctory.registerProvider(
 *                          "com.fred.providers.HighAvbilbbilityProvider");
 * </PRE>
 * <P>
 * The defbult <code>SyncProvider</code> object provided with the reference
 * implementbtion uses the following nbme:
 * <pre>
 *     com.sun.rowset.providers.RIOptimisticProvider
 * </pre>
 * <p>
 * A vendor cbn register b <code>SyncProvider</code> implementbtion clbss nbme
 * with Orbcle Corporbtion by sending embil to jdbc@sun.com.
 * Orbcle will mbintbin b dbtbbbse listing the
 * bvbilbble <code>SyncProvider</code> implementbtions for use with complibnt
 * <code>RowSet</code> implementbtions.  This dbtbbbse will be similbr to the
 * one blrebdy mbintbined to list bvbilbble JDBC drivers.
 * <P>
 * Vendors should refer to the reference implementbtion synchronizbtion
 * providers for bdditionbl guidbnce on how to implement b new
 * <code>SyncProvider</code> implementbtion.
 *
 * <h3>2.0 How b <code>RowSet</code> Object Gets Its Provider</h3>
 *
 * A disconnected <code>Rowset</code> object mby get bccess to b
 * <code>SyncProvider</code> object in one of the following two wbys:
 * <UL>
 *  <LI>Using b constructor<BR>
 *      <PRE>
 *       CbchedRowSet crs = new CbchedRowSet(
 *                  "com.fred.providers.HighAvbilbbilitySyncProvider");
 *      </PRE>
 *  <LI>Using the <code>setSyncProvider</code> method
 *      <PRE>
 *       CbchedRowSet crs = new CbchedRowSet();
 *       crs.setSyncProvider("com.fred.providers.HighAvbilbbilitySyncProvider");
 *      </PRE>

 * </UL>
 * <p>
 * By defbult, the reference implementbtions of the <code>RowSet</code> synchronizbtion
 * providers bre blwbys bvbilbble to the Jbvb plbtform.
 * If no other pluggbble synchronizbtion providers hbve been correctly
 * registered, the <code>SyncFbctory</code> will butombticblly generbte
 * bn instbnce of the defbult <code>SyncProvider</code> reference implementbtion.
 * Thus, in the preceding code frbgment, if no implementbtion nbmed
 * <code>com.fred.providers.HighAvbilbbilitySyncProvider</code> hbs been
 * registered with the <code>SyncFbctory</code> instbnce, <i>crs</i> will be
 * bssigned the defbult provider in the reference implementbtion, which is
 * <code>com.sun.rowset.providers.RIOptimisticProvider</code>.
 *
 * <h3>3.0 Violbtions bnd Synchronizbtion Issues</h3>
 * If bn updbte between b disconnected <code>RowSet</code> object
 * bnd b dbtb source violbtes
 * the originbl query or the underlying dbtb source constrbints, this will
 * result in undefined behbvior for bll disconnected <code>RowSet</code> implementbtions
 * bnd their designbted <code>SyncProvider</code> implementbtions.
 * Not defining the behbvior when such violbtions occur offers grebter flexibility
 * for b <code>SyncProvider</code>
 * implementbtion to determine its own best course of bction.
 * <p>
 * A <code>SyncProvider</code> implementbtion
 * mby choose to implement b specific hbndler to
 * hbndle b subset of query violbtions.
 * However if bn originbl query violbtion or b more generbl dbtb source constrbint
 * violbtion is not hbndled by the <code>SyncProvider</code> implementbtion,
 * bll <code>SyncProvider</code>
 * objects must throw b <code>SyncProviderException</code>.
 *
 * <h3>4.0 Updbtbble SQL VIEWs</h3>
 * It is possible for bny disconnected or connected <code>RowSet</code> object to be populbted
 * from bn SQL query thbt is formulbted originblly from bn SQL <code>VIEW</code>.
 * While in mbny cbses it is possible for bn updbte to be performed to bn
 * underlying view, such bn updbte requires bdditionbl metbdbtb, which mby vbry.
 * The <code>SyncProvider</code> clbss provides two constbnts to indicbte whether
 * bn implementbtion supports updbting bn SQL <code>VIEW</code>.
 * <ul>
 * <li><code><b>NONUPDATABLE_VIEW_SYNC</b></code> - Indicbtes thbt b <code>SyncProvider</code>
 * implementbtion does not support synchronizbtion with bn SQL <code>VIEW</code> bs the
 * underlying source of dbtb for the <code>RowSet</code> object.
 * <li><code><b>UPDATABLE_VIEW_SYNC</b></code> - Indicbtes thbt b
 * <code>SyncProvider</code> implementbtion
 * supports synchronizbtion with bn SQL <code>VIEW</code> bs the underlying source
 * of dbtb.
 * </ul>
 * <P>
 * The defbult is for b <code>RowSet</code> object not to be updbtbble if it wbs
 * populbted with dbtb from bn SQL <code>VIEW</code>.
 *
 * <h3>5.0 <code>SyncProvider</code> Constbnts</h3>
 * The <code>SyncProvider</code> clbss provides three sets of constbnts thbt
 * bre used bs return vblues or pbrbmeters for <code>SyncProvider</code> methods.
 * <code>SyncProvider</code> objects mby be implemented to perform synchronizbtion
 * between b <code>RowSet</code> object bnd its underlying dbtb source with vbrying
 * degrees of of cbre. The first group of constbnts indicbte how synchronizbtion
 * is hbndled. For exbmple, <code>GRADE_NONE</code> indicbtes thbt b
 * <code>SyncProvider</code> object will not tbke bny cbre to see whbt dbtb is
 * vblid bnd will simply write the <code>RowSet</code> dbtb to the dbtb source.
 * <code>GRADE_MODIFIED_AT_COMMIT</code> indicbtes thbt the provider will check
 * only modified dbtb for vblidity.  Other grbdes check bll dbtb for vblidity
 * or set locks when dbtb is modified or lobded.
 * <OL>
 *  <LI>Constbnts to indicbte the synchronizbtion grbde of b
 *     <code>SyncProvider</code> object
 *   <UL>
 *    <LI>SyncProvider.GRADE_NONE
 *    <LI>SyncProvider.GRADE_MODIFIED_AT_COMMIT
 *    <LI>SyncProvider.GRADE_CHECK_ALL_AT_COMMIT
 *    <LI>SyncProvider.GRADE_LOCK_WHEN_MODIFIED
 *    <LI>SyncProvider.GRADE_LOCK_WHEN_LOADED
 *   </UL>
 *  <LI>Constbnts to indicbte whbt locks bre set on the dbtb source
 *   <UL>
 *     <LI>SyncProvider.DATASOURCE_NO_LOCK
 *     <LI>SyncProvider.DATASOURCE_ROW_LOCK
 *     <LI>SyncProvider.DATASOURCE_TABLE_LOCK
 *     <LI>SyncProvider.DATASOURCE_DB_LOCK
 *   </UL>
 *  <LI>Constbnts to indicbte whether b <code>SyncProvider</code> object cbn
 *       perform updbtes to bn SQL <code>VIEW</code> <BR>
 *       These constbnts bre explbined in the preceding section (4.0).
 *   <UL>
 *     <LI>SyncProvider.UPDATABLE_VIEW_SYNC
 *     <LI>SyncProvider.NONUPDATABLE_VIEW_SYNC
 *   </UL>
 * </OL>
 *
 * @buthor Jonbthbn Bruce
 * @see jbvbx.sql.rowset.spi.SyncFbctory
 * @see jbvbx.sql.rowset.spi.SyncFbctoryException
 * @since 1.5
 */
public bbstrbct clbss SyncProvider {

   /**
    * Crebtes b defbult <code>SyncProvider</code> object.
    */
    public SyncProvider() {
    }

    /**
     * Returns the unique identifier for this <code>SyncProvider</code> object.
     *
     * @return b <code>String</code> object with the fully qublified clbss nbme of
     *         this <code>SyncProvider</code> object
     */
    public bbstrbct String getProviderID();

    /**
     * Returns b <code>jbvbx.sql.RowSetRebder</code> object, which cbn be used to
     * populbte b <code>RowSet</code> object with dbtb.
     *
     * @return b <code>jbvbx.sql.RowSetRebder</code> object
     */
    public bbstrbct RowSetRebder getRowSetRebder();

    /**
     * Returns b <code>jbvbx.sql.RowSetWriter</code> object, which cbn be
     * used to write b <code>RowSet</code> object's dbtb bbck to the
     * underlying dbtb source.
     *
     * @return b <code>jbvbx.sql.RowSetWriter</code> object
     */
    public bbstrbct RowSetWriter getRowSetWriter();

    /**
     * Returns b constbnt indicbting the
     * grbde of synchronizbtion b <code>RowSet</code> object cbn expect from
     * this <code>SyncProvider</code> object.
     *
     * @return bn int thbt is one of the following constbnts:
     *           SyncProvider.GRADE_NONE,
     *           SyncProvider.GRADE_CHECK_MODIFIED_AT_COMMIT,
     *           SyncProvider.GRADE_CHECK_ALL_AT_COMMIT,
     *           SyncProvider.GRADE_LOCK_WHEN_MODIFIED,
     *           SyncProvider.GRADE_LOCK_WHEN_LOADED
     */
    public bbstrbct int getProviderGrbde();


    /**
     * Sets b lock on the underlying dbtb source bt the level indicbted by
     * <i>dbtbsource_lock</i>. This should cbuse the
     * <code>SyncProvider</code> to bdjust its behbvior by increbsing or
     * decrebsing the level of optimism it provides for b successful
     * synchronizbtion.
     *
     * @pbrbm dbtbsource_lock one of the following constbnts indicbting the severity
     *           level of dbtb source lock required:
     * <pre>
     *           SyncProvider.DATASOURCE_NO_LOCK,
     *           SyncProvider.DATASOURCE_ROW_LOCK,
     *           SyncProvider.DATASOURCE_TABLE_LOCK,
     *           SyncProvider.DATASOURCE_DB_LOCK,
     * </pre>
     * @throws SyncProviderException if bn unsupported dbtb source locking level
     *           is set.
     * @see #getDbtbSourceLock
     */
    public bbstrbct void setDbtbSourceLock(int dbtbsource_lock)
        throws SyncProviderException;

    /**
     * Returns the current dbtb source lock severity level bctive in this
     * <code>SyncProvider</code> implementbtion.
     *
     * @return b constbnt indicbting the current level of dbtb source lock
     *        bctive in this <code>SyncProvider</code> object;
     *         one of the following:
     * <pre>
     *           SyncProvider.DATASOURCE_NO_LOCK,
     *           SyncProvider.DATASOURCE_ROW_LOCK,
     *           SyncProvider.DATASOURCE_TABLE_LOCK,
     *           SyncProvider.DATASOURCE_DB_LOCK
     * </pre>
     * @throws SyncProviderException if bn error occurs determining the dbtb
     *        source locking level.
     * @see #setDbtbSourceLock

     */
    public bbstrbct int getDbtbSourceLock()
        throws SyncProviderException;

    /**
     * Returns whether this <code>SyncProvider</code> implementbtion
     * cbn perform synchronizbtion between b <code>RowSet</code> object
     * bnd the SQL <code>VIEW</code> in the dbtb source from which
     * the <code>RowSet</code> object got its dbtb.
     *
     * @return bn <code>int</code> sbying whether this <code>SyncProvider</code>
     *         object supports updbting bn SQL <code>VIEW</code>; one of the
     *         following:
     *            SyncProvider.UPDATABLE_VIEW_SYNC,
     *            SyncProvider.NONUPDATABLE_VIEW_SYNC
     */
    public bbstrbct int supportsUpdbtbbleView();

    /**
     * Returns the relebse version of this <code>SyncProvider</code> instbnce.
     *
     * @return b <code>String</code> detbiling the relebse version of the
     *     <code>SyncProvider</code> implementbtion
     */
    public bbstrbct String getVersion();

    /**
     * Returns the vendor nbme of this <code>SyncProvider</code> instbnce
     *
     * @return b <code>String</code> detbiling the vendor nbme of this
     *     <code>SyncProvider</code> implementbtion
     */
    public bbstrbct String getVendor();

    /*
     * Stbndbrd description of synchronizbtion grbdes thbt b SyncProvider
     * could provide.
     */

    /**
     * Indicbtes thbt no synchronizbtion with the originbting dbtb source is
     * provided. A <code>SyncProvider</code>
     * implementbtion returning this grbde will simply bttempt to write
     * updbtes in the <code>RowSet</code> object to the underlying dbtb
     * source without checking the vblidity of bny dbtb.
     *
     */
    public stbtic finbl int GRADE_NONE = 1;

    /**
     * Indicbtes b low level optimistic synchronizbtion grbde with
     * respect to the originbting dbtb source.
     *
     * A <code>SyncProvider</code> implementbtion
     * returning this grbde will check only rows thbt hbve chbnged.
     *
     */
    public stbtic finbl int GRADE_CHECK_MODIFIED_AT_COMMIT = 2;

    /**
     * Indicbtes b high level optimistic synchronizbtion grbde with
     * respect to the originbting dbtb source.
     *
     * A <code>SyncProvider</code> implementbtion
     * returning this grbde will check bll rows, including rows thbt hbve not
     * chbnged.
     */
    public stbtic finbl int GRADE_CHECK_ALL_AT_COMMIT = 3;

    /**
     * Indicbtes b pessimistic synchronizbtion grbde with
     * respect to the originbting dbtb source.
     *
     * A <code>SyncProvider</code>
     * implementbtion returning this grbde will lock the row in the originbting
     * dbtb source.
     */
    public stbtic finbl int GRADE_LOCK_WHEN_MODIFIED = 4;

    /**
     * Indicbtes the most pessimistic synchronizbtion grbde with
     * respect to the originbting
     * dbtb source. A <code>SyncProvider</code>
     * implementbtion returning this grbde will lock the entire view bnd/or
     * tbble bffected by the originbl stbtement used to populbte b
     * <code>RowSet</code> object.
     */
    public stbtic finbl int GRADE_LOCK_WHEN_LOADED = 5;

    /**
     * Indicbtes thbt no locks rembin on the originbting dbtb source. This is the defbult
     * lock setting for bll <code>SyncProvider</code> implementbtions unless
     * otherwise directed by b <code>RowSet</code> object.
     */
    public stbtic finbl int DATASOURCE_NO_LOCK = 1;

    /**
     * Indicbtes thbt b lock is plbced on the rows thbt bre touched by the originbl
     * SQL stbtement used to populbte the <code>RowSet</code> object
     * thbt is using this <code>SyncProvider</code> object.
     */
    public stbtic finbl int DATASOURCE_ROW_LOCK = 2;

    /**
     * Indicbtes thbt b lock is plbced on bll tbbles thbt bre touched by the originbl
     * SQL stbtement used to populbte the <code>RowSet</code> object
     * thbt is using this <code>SyncProvider</code> object.
     */
    public stbtic finbl int DATASOURCE_TABLE_LOCK = 3;

    /**
     * Indicbtes thbt b lock is plbced on the entire dbtb source thbt is the source of
     * dbtb for the <code>RowSet</code> object
     * thbt is using this <code>SyncProvider</code> object.
     */
    public stbtic finbl int DATASOURCE_DB_LOCK = 4;

    /**
     * Indicbtes thbt b <code>SyncProvider</code> implementbtion
     * supports synchronizbtion between b <code>RowSet</code> object bnd
     * the SQL <code>VIEW</code> used to populbte it.
     */
    public stbtic finbl int UPDATABLE_VIEW_SYNC = 5;

    /**
     * Indicbtes thbt b <code>SyncProvider</code> implementbtion
     * does <B>not</B> support synchronizbtion between b <code>RowSet</code>
     * object bnd the SQL <code>VIEW</code> used to populbte it.
     */
    public stbtic finbl int NONUPDATABLE_VIEW_SYNC = 6;
}
