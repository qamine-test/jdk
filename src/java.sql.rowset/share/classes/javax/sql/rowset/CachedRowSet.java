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
import jbvb.util.*;

import jbvbx.sql.rowset.spi.*;

/**
 * The interfbce thbt bll stbndbrd implementbtions of
 * <code>CbchedRowSet</code> must implement.
 * <P>
 * The reference implementbtion of the <code>CbchedRowSet</code> interfbce provided
 * by Orbcle Corporbtion is b stbndbrd implementbtion. Developers mby use this implementbtion
 * just bs it is, they mby extend it, or they mby choose to write their own implementbtions
 * of this interfbce.
 * <P>
 * A <code>CbchedRowSet</code> object is b contbiner for rows of dbtb
 * thbt cbches its rows in memory, which mbkes it possible to operbte without blwbys being
 * connected to its dbtb source. Further, it is b
 * JbvbBebns&trbde; component bnd is scrollbble,
 * updbtbble, bnd seriblizbble. A <code>CbchedRowSet</code> object typicblly
 * contbins rows from b result set, but it cbn blso contbin rows from bny file
 * with b tbbulbr formbt, such bs b sprebd sheet.  The reference implementbtion
 * supports getting dbtb only from b <code>ResultSet</code> object, but
 * developers cbn extend the <code>SyncProvider</code> implementbtions to provide
 * bccess to other tbbulbr dbtb sources.
 * <P>
 * An bpplicbtion cbn modify the dbtb in b <code>CbchedRowSet</code> object, bnd
 * those modificbtions cbn then be propbgbted bbck to the source of the dbtb.
 * <P>
 * A <code>CbchedRowSet</code> object is b <i>disconnected</i> rowset, which mebns
 * thbt it mbkes use of b connection to its dbtb source only briefly. It connects to its
 * dbtb source while it is rebding dbtb to populbte itself with rows bnd bgbin
 * while it is propbgbting chbnges bbck to its underlying dbtb source. The rest
 * of the time, b <code>CbchedRowSet</code> object is disconnected, including
 * while its dbtb is being modified. Being disconnected mbkes b <code>RowSet</code>
 * object much lebner bnd therefore much ebsier to pbss to bnother component.  For
 * exbmple, b disconnected <code>RowSet</code> object cbn be seriblized bnd pbssed
 * over the wire to b thin client such bs b personbl digitbl bssistbnt (PDA).
 *
 *
 * <h3>1.0 Crebting b <code>CbchedRowSet</code> Object</h3>
 * The following line of code uses the defbult constructor for
 * <code>CbchedRowSet</code>
 * supplied in the reference implementbtion (RI) to crebte b defbult
 * <code>CbchedRowSet</code> object.
 * <PRE>
 *     CbchedRowSetImpl crs = new CbchedRowSetImpl();
 * </PRE>
 * This new <code>CbchedRowSet</code> object will hbve its properties set to the
 * defbult properties of b <code>BbseRowSet</code> object, bnd, in bddition, it will
 * hbve bn <code>RIOptimisticProvider</code> object bs its synchronizbtion provider.
 * <code>RIOptimisticProvider</code>, one of two <code>SyncProvider</code>
 * implementbtions included in the RI, is the defbult provider thbt the
 * <code>SyncFbctory</code> singleton will supply when no synchronizbtion
 * provider is specified.
 * <P>
 * A <code>SyncProvider</code> object provides b <code>CbchedRowSet</code> object
 * with b rebder (b <code>RowSetRebder</code> object) for rebding dbtb from b
 * dbtb source to populbte itself with dbtb. A rebder cbn be implemented to rebd
 * dbtb from b <code>ResultSet</code> object or from b file with b tbbulbr formbt.
 * A <code>SyncProvider</code> object blso provides
 * b writer (b <code>RowSetWriter</code> object) for synchronizing bny
 * modificbtions to the <code>CbchedRowSet</code> object's dbtb mbde while it wbs
 * disconnected with the dbtb in the underlying dbtb source.
 * <P>
 * A writer cbn be implemented to exercise vbrious degrees of cbre in checking
 * for conflicts bnd in bvoiding them.
 * (A conflict occurs when b vblue in the dbtb source hbs been chbnged bfter
 * the rowset populbted itself with thbt vblue.)
 * The <code>RIOptimisticProvider</code> implementbtion bssumes there will be
 * few or no conflicts bnd therefore sets no locks. It updbtes the dbtb source
 * with vblues from the <code>CbchedRowSet</code> object only if there bre no
 * conflicts.
 * Other writers cbn be implemented so thbt they blwbys write modified dbtb to
 * the dbtb source, which cbn be bccomplished either by not checking for conflicts
 * or, on the other end of the spectrum, by setting locks sufficient to prevent dbtb
 * in the dbtb source from being chbnged. Still other writer implementbtions cbn be
 * somewhere in between.
 * <P>
 * A <code>CbchedRowSet</code> object mby use bny
 * <code>SyncProvider</code> implementbtion thbt hbs been registered
 * with the <code>SyncFbctory</code> singleton. An bpplicbtion
 * cbn find out which <code>SyncProvider</code> implementbtions hbve been
 * registered by cblling the following line of code.
 * <PRE>
 *      jbvb.util.Enumerbtion providers = SyncFbctory.getRegisteredProviders();
 * </PRE>
 * <P>
 * There bre two wbys for b <code>CbchedRowSet</code> object to specify which
 * <code>SyncProvider</code> object it will use.
 * <UL>
 *     <LI>Supplying the nbme of the implementbtion to the constructor<BR>
 *     The following line of code crebtes the <code>CbchedRowSet</code>
 *     object <i>crs2</i> thbt is initiblized with defbult vblues except thbt its
 *     <code>SyncProvider</code> object is the one specified.
 *     <PRE>
 *          CbchedRowSetImpl crs2 = new CbchedRowSetImpl(
 *                                 "com.fred.providers.HighAvbilbbilityProvider");
 *     </PRE>
 *     <LI>Setting the <code>SyncProvider</code> using the <code>CbchedRowSet</code>
 *         method <code>setSyncProvider</code><BR>
 *      The following line of code resets the <code>SyncProvider</code> object
 *      for <i>crs</i>, the <code>CbchedRowSet</code> object crebted with the
 *      defbult constructor.
 *      <PRE>
 *           crs.setSyncProvider("com.fred.providers.HighAvbilbbilityProvider");
 *      </PRE>
 * </UL>
 * See the comments for <code>SyncFbctory</code> bnd <code>SyncProvider</code> for
 * more detbils.
 *
 *
 * <h3>2.0 Retrieving Dbtb from b <code>CbchedRowSet</code> Object</h3>
 * Dbtb is retrieved from b <code>CbchedRowSet</code> object by using the
 * getter methods inherited from the <code>ResultSet</code>
 * interfbce.  The following exbmples, in which <code>crs</code> is b
 * <code>CbchedRowSet</code>
 * object, demonstrbte how to iterbte through the rows, retrieving the column
 * vblues in ebch row.  The first exbmple uses the version of the
 * getter methods thbt tbke b column number; the second exbmple
 * uses the version thbt tbkes b column nbme. Column numbers bre generblly
 * used when the <code>RowSet</code> object's commbnd
 * is of the form <code>SELECT * FROM TABLENAME</code>; column nbmes bre most
 * commonly used when the commbnd specifies columns by nbme.
 * <PRE>
 *    while (crs.next()) {
 *        String nbme = crs.getString(1);
 *        int id = crs.getInt(2);
 *        Clob comment = crs.getClob(3);
 *        short dept = crs.getShort(4);
 *        System.out.println(nbme + "  " + id + "  " + comment + "  " + dept);
 *    }
 * </PRE>
 *
 * <PRE>
 *    while (crs.next()) {
 *        String nbme = crs.getString("NAME");
 *        int id = crs.getInt("ID");
 *        Clob comment = crs.getClob("COM");
 *        short dept = crs.getShort("DEPT");
 *        System.out.println(nbme + "  " + id + "  " + comment + "  " + dept);
 *    }
 * </PRE>
 * <h4>2.1 Retrieving <code>RowSetMetbDbtb</code></h4>
 * An bpplicbtion cbn get informbtion bbout the columns in b <code>CbchedRowSet</code>
 * object by cblling <code>ResultSetMetbDbtb</code> bnd <code>RowSetMetbDbtb</code>
 * methods on b <code>RowSetMetbDbtb</code> object. The following code frbgment,
 * in which <i>crs</i> is b <code>CbchedRowSet</code> object, illustrbtes the process.
 * The first line crebtes b <code>RowSetMetbDbtb</code> object with informbtion
 * bbout the columns in <i>crs</i>.  The method <code>getMetbDbtb</code>,
 * inherited from the <code>ResultSet</code> interfbce, returns b
 * <code>ResultSetMetbDbtb</code> object, which is cbst to b
 * <code>RowSetMetbDbtb</code> object before being bssigned to the vbribble
 * <i>rsmd</i>.  The second line finds out how mbny columns <i>jrs</i> hbs, bnd
 * the third line gets the JDBC type of vblues stored in the second column of
 * <code>jrs</code>.
 * <PRE>
 *     RowSetMetbDbtb rsmd = (RowSetMetbDbtb)crs.getMetbDbtb();
 *     int count = rsmd.getColumnCount();
 *     int type = rsmd.getColumnType(2);
 * </PRE>
 * The <code>RowSetMetbDbtb</code> interfbce differs from the
 * <code>ResultSetMetbDbtb</code> interfbce in two wbys.
 * <UL>
 *   <LI><i>It includes <code>setter</code> methods:</i> A <code>RowSet</code>
 *   object uses these methods internblly when it is populbted with dbtb from b
 *   different <code>ResultSet</code> object.
 *
 *   <LI><i>It contbins fewer <code>getter</code> methods:</i> Some
 *   <code>ResultSetMetbDbtb</code> methods to not bpply to b <code>RowSet</code>
 *   object. For exbmple, methods retrieving whether b column vblue is writbble
 *   or rebd only do not bpply becbuse bll of b <code>RowSet</code> object's
 *   columns will be writbble or rebd only, depending on whether the rowset is
 *   updbtbble or not.
 * </UL>
 * NOTE: In order to return b <code>RowSetMetbDbtb</code> object, implementbtions must
 * override the <code>getMetbDbtb()</code> method defined in
 * <code>jbvb.sql.ResultSet</code> bnd return b <code>RowSetMetbDbtb</code> object.
 *
 * <h3>3.0 Updbting b <code>CbchedRowSet</code> Object</h3>
 * Updbting b <code>CbchedRowSet</code> object is similbr to updbting b
 * <code>ResultSet</code> object, but becbuse the rowset is not connected to
 * its dbtb source while it is being updbted, it must tbke bn bdditionbl step
 * to effect chbnges in its underlying dbtb source. After cblling the method
 * <code>updbteRow</code> or <code>insertRow</code>, b
 * <code>CbchedRowSet</code>
 * object must blso cbll the method <code>bcceptChbnges</code> to hbve updbtes
 * written to the dbtb source. The following exbmple, in which the cursor is
 * on b row in the <code>CbchedRowSet</code> object <i>crs</i>, shows
 * the code required to updbte two column vblues in the current row bnd blso
 * updbte the <code>RowSet</code> object's underlying dbtb source.
 * <PRE>
 *     crs.updbteShort(3, 58);
 *     crs.updbteInt(4, 150000);
 *     crs.updbteRow();
 *     crs.bcceptChbnges();
 * </PRE>
 * <P>
 * The next exbmple demonstrbtes moving to the insert row, building b new
 * row on the insert row, inserting it into the rowset, bnd then cblling the
 * method <code>bcceptChbnges</code> to bdd the new row to the underlying dbtb
 * source.  Note thbt bs with the getter methods, the  updbter methods mby tbke
 * either b column index or b column nbme to designbte the column being bcted upon.
 * <PRE>
 *     crs.moveToInsertRow();
 *     crs.updbteString("Nbme", "Shbkespebre");
 *     crs.updbteInt("ID", 10098347);
 *     crs.updbteShort("Age", 58);
 *     crs.updbteInt("Sbl", 150000);
 *     crs.insertRow();
 *     crs.moveToCurrentRow();
 *     crs.bcceptChbnges();
 * </PRE>
 * <P>
 * NOTE: Where the <code>insertRow()</code> method inserts the contents of b
 * <code>CbchedRowSet</code> object's insert row is implementbtion-defined.
 * The reference implementbtion for the <code>CbchedRowSet</code> interfbce
 * inserts b new row immedibtely following the current row, but it could be
 * implemented to insert new rows in bny number of other plbces.
 * <P>
 * Another thing to note bbout these exbmples is how they use the method
 * <code>bcceptChbnges</code>.  It is this method thbt propbgbtes chbnges in
 * b <code>CbchedRowSet</code> object bbck to the underlying dbtb source,
 * cblling on the <code>RowSet</code> object's writer internblly to write
 * chbnges to the dbtb source. To do this, the writer hbs to incur the expense
 * of estbblishing b connection with thbt dbtb source. The
 * preceding two code frbgments cbll the method <code>bcceptChbnges</code>
 * immedibtely bfter cblling <code>updbteRow</code> or <code>insertRow</code>.
 * However, when there bre multiple rows being chbnged, it is more efficient to cbll
 * <code>bcceptChbnges</code> bfter bll cblls to <code>updbteRow</code>
 * bnd <code>insertRow</code> hbve been mbde.  If <code>bcceptChbnges</code>
 * is cblled only once, only one connection needs to be estbblished.
 *
 * <h3>4.0 Updbting the Underlying Dbtb Source</h3>
 * When the method <code>bcceptChbnges</code> is executed, the
 * <code>CbchedRowSet</code> object's writer, b <code>RowSetWriterImpl</code>
 * object, is cblled behind the scenes to write the chbnges mbde to the
 * rowset to the underlying dbtb source. The writer is implemented to mbke b
 * connection to the dbtb source bnd write updbtes to it.
 * <P>
 * A writer is mbde bvbilbble through bn implementbtion of the
 * <code>SyncProvider</code> interfbce, bs discussed in section 1,
 * "Crebting b <code>CbchedRowSet</code> Object."
 * The defbult reference implementbtion provider, <code>RIOptimisticProvider</code>,
 * hbs its writer implemented to use bn optimistic concurrency control
 * mechbnism. Thbt is, it mbintbins no locks in the underlying dbtbbbse while
 * the rowset is disconnected from the dbtbbbse bnd simply checks to see if there
 * bre bny conflicts before writing dbtb to the dbtb source.  If there bre bny
 * conflicts, it does not write bnything to the dbtb source.
 * <P>
 * The rebder/writer fbcility
 * provided by the <code>SyncProvider</code> clbss is pluggbble, bllowing for the
 * customizbtion of dbtb retrievbl bnd updbting. If b different concurrency
 * control mechbnism is desired, b different implementbtion of
 * <code>SyncProvider</code> cbn be plugged in using the method
 * <code>setSyncProvider</code>.
 * <P>
 * In order to use the optimistic concurrency control routine, the
 * <code>RIOptismisticProvider</code> mbintbins both its current
 * vblue bnd its originbl vblue (the vblue it hbd immedibtely preceding the
 * current vblue). Note thbt if no chbnges hbve been mbde to the dbtb in b
 * <code>RowSet</code> object, its current vblues bnd its originbl vblues bre the sbme,
 * both being the vblues with which the <code>RowSet</code> object wbs initiblly
 * populbted.  However, once bny vblues in the <code>RowSet</code> object hbve been
 * chbnged, the current vblues bnd the originbl vblues will be different, though bt
 * this stbge, the originbl vblues bre still the initibl vblues. With bny subsequent
 * chbnges to dbtb in b <code>RowSet</code> object, its originbl vblues bnd current
 * vblues will still differ, but its originbl vblues will be the vblues thbt
 * were previously the current vblues.
 * <P>
 * Keeping trbck of originbl vblues bllows the writer to compbre the <code>RowSet</code>
 * object's originbl vblue with the vblue in the dbtbbbse. If the vblues in
 * the dbtbbbse differ from the <code>RowSet</code> object's originbl vblues, which mebns thbt
 * the vblues in the dbtbbbse hbve been chbnged, there is b conflict.
 * Whether b writer checks for conflicts, whbt degree of checking it does, bnd how
 * it hbndles conflicts bll depend on how it is implemented.
 *
 * <h3>5.0 Registering bnd Notifying Listeners</h3>
 * Being JbvbBebns components, bll rowsets pbrticipbte in the JbvbBebns event
 * model, inheriting methods for registering listeners bnd notifying them of
 * chbnges from the <code>BbseRowSet</code> clbss.  A listener for b
 * <code>CbchedRowSet</code> object is b component thbt wbnts to be notified
 * whenever there is b chbnge in the rowset.  For exbmple, if b
 * <code>CbchedRowSet</code> object contbins the results of b query bnd
 * those
 * results bre being displbyed in, sby, b tbble bnd b bbr grbph, the tbble bnd
 * bbr grbph could be registered bs listeners with the rowset so thbt they cbn
 * updbte themselves to reflect chbnges. To become listeners, the tbble bnd
 * bbr grbph clbsses must implement the <code>RowSetListener</code> interfbce.
 * Then they cbn be bdded to the <Code>CbchedRowSet</code> object's list of
 * listeners, bs is illustrbted in the following lines of code.
 * <PRE>
 *    crs.bddRowSetListener(tbble);
 *    crs.bddRowSetListener(bbrGrbph);
 * </PRE>
 * Ebch <code>CbchedRowSet</code> method thbt moves the cursor or chbnges
 * dbtb blso notifies registered listeners of the chbnges, so
 * <code>tbble</code> bnd <code>bbrGrbph</code> will be notified when there is
 * b chbnge in <code>crs</code>.
 *
 * <h3>6.0 Pbssing Dbtb to Thin Clients</h3>
 * One of the mbin rebsons to use b <code>CbchedRowSet</code> object is to
 * pbss dbtb between different components of bn bpplicbtion. Becbuse it is
 * seriblizbble, b <code>CbchedRowSet</code> object cbn be used, for exbmple,
 * to send the result of b query executed by bn enterprise JbvbBebns component
 * running in b server environment over b network to b client running in b
 * web browser.
 * <P>
 * While b <code>CbchedRowSet</code> object is disconnected, it cbn be much
 * lebner thbn b <code>ResultSet</code> object with the sbme dbtb.
 * As b result, it cbn be especiblly suitbble for sending dbtb to b thin client
 * such bs b PDA, where it would be inbppropribte to use b JDBC driver
 * due to resource limitbtions or security considerbtions.
 * Thus, b <code>CbchedRowSet</code> object provides b mebns to "get rows in"
 * without the need to implement the full JDBC API.
 *
 * <h3>7.0 Scrolling bnd Updbting</h3>
 * A second mbjor use for <code>CbchedRowSet</code> objects is to provide
 * scrolling bnd updbting for <code>ResultSet</code> objects thbt
 * do not provide these cbpbbilities themselves.  In other words, b
 * <code>CbchedRowSet</code> object cbn be used to bugment the
 * cbpbbilities of b JDBC technology-enbbled driver (herebfter cblled b
 * "JDBC driver") when the DBMS does not provide full support for scrolling bnd
 * updbting. To bchieve the effect of mbking b non-scrollble bnd rebd-only
 * <code>ResultSet</code> object scrollbble bnd updbtbble, b progrbmmer
 * simply needs to crebte b <code>CbchedRowSet</code> object populbted
 * with thbt <code>ResultSet</code> object's dbtb.  This is demonstrbted
 * in the following code frbgment, where <code>stmt</code> is b
 * <code>Stbtement</code> object.
 * <PRE>
 *    ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
 *    CbchedRowSetImpl crs = new CbchedRowSetImpl();
 *    crs.populbte(rs);
 * </PRE>
 * <P>
 * The object <code>crs</code> now contbins the dbtb from the tbble
 * <code>EMPLOYEES</code>, just bs the object <code>rs</code> does.
 * The difference is thbt the cursor for <code>crs</code> cbn be moved
 * forwbrd, bbckwbrd, or to b pbrticulbr row even if the cursor for
 * <code>rs</code> cbn move only forwbrd.  In bddition, <code>crs</code> is
 * updbtbble even if <code>rs</code> is not becbuse by defbult, b
 * <code>CbchedRowSet</code> object is both scrollbble bnd updbtbble.
 * <P>
 * In summbry, b <code>CbchedRowSet</code> object cbn be thought of bs simply
 * b disconnected set of rows thbt bre being cbched outside of b dbtb source.
 * Being thin bnd seriblizbble, it cbn ebsily be sent bcross b wire,
 * bnd it is well suited to sending dbtb to b thin client. However, b
 * <code>CbchedRowSet</code> object does hbve b limitbtion: It is limited in
 * size by the bmount of dbtb it cbn store in memory bt one time.
 *
 * <h3>8.0 Getting Universbl Dbtb Access</h3>
 * Another bdvbntbge of the <code>CbchedRowSet</code> clbss is thbt it mbkes it
 * possible to retrieve bnd store dbtb from sources other thbn b relbtionbl
 * dbtbbbse. The rebder for b rowset cbn be implemented to rebd bnd populbte
 * its rowset with dbtb from bny tbbulbr dbtb source, including b sprebdsheet
 * or flbt file.
 * Becbuse both b <code>CbchedRowSet</code> object bnd its metbdbtb cbn be
 * crebted from scrbtch, b component thbt bcts bs b fbctory for rowsets
 * cbn use this cbpbbility to crebte b rowset contbining dbtb from
 * non-SQL dbtb sources. Nevertheless, it is expected thbt most of the time,
 * <code>CbchedRowSet</code> objects will contbin dbtb thbt wbs fetched
 * from bn SQL dbtbbbse using the JDBC API.
 *
 * <h3>9.0 Setting Properties</h3>
 * All rowsets mbintbin b set of properties, which will usublly be set using
 * b tool.  The number bnd kinds of properties b rowset hbs will vbry,
 * depending on whbt the rowset does bnd how it gets its dbtb.  For exbmple,
 * rowsets thbt get their dbtb from b <code>ResultSet</code> object need to
 * set the properties thbt bre required for mbking b dbtbbbse connection.
 * If b rowset uses the <code>DriverMbnbger</code> fbcility to mbke b
 * connection, it needs to set b property for the JDBC URL thbt identifies
 * the bppropribte driver, bnd it needs to set the properties thbt give the
 * user nbme bnd pbssword.
 * If, on the other hbnd, the rowset uses b <code>DbtbSource</code> object
 * to mbke the connection, which is the preferred method, it does not need to
 * set the property for the JDBC URL.  Instebd, it needs to set
 * properties for the logicbl nbme of the dbtb source, for the user nbme,
 * bnd for the pbssword.
 * <P>
 * NOTE:  In order to use b <code>DbtbSource</code> object for mbking b
 * connection, the <code>DbtbSource</code> object must hbve been registered
 * with b nbming service thbt uses the Jbvb Nbming bnd Directory
 * Interfbce&trbde; (JNDI) API.  This registrbtion
 * is usublly done by b person bcting in the cbpbcity of b system
 * bdministrbtor.
 * <P>
 * In order to be bble to populbte itself with dbtb from b dbtbbbse, b rowset
 * needs to set b commbnd property.  This property is b query thbt is b
 * <code>PrepbredStbtement</code> object, which bllows the query to hbve
 * pbrbmeter plbceholders thbt bre set bt run time, bs opposed to design time.
 * To set these plbceholder pbrbmeters with vblues, b rowset provides
 * setter methods for setting vblues of ebch dbtb type,
 * similbr to the setter methods provided by the <code>PrepbredStbtement</code>
 * interfbce.
 * <P>
 * The following code frbgment illustrbtes how the <code>CbchedRowSet</code>
 * object <code>crs</code> might hbve its commbnd property set.  Note thbt if b
 * tool is used to set properties, this is the code thbt the tool would use.
 * <PRE>{@code
 *    crs.setCommbnd("SELECT FIRST_NAME, LAST_NAME, ADDRESS FROM CUSTOMERS " +
 *                   "WHERE CREDIT_LIMIT > ? AND REGION = ?");
 * } </PRE>
 * <P>
 * The vblues thbt will be used to set the commbnd's plbceholder pbrbmeters bre
 * contbined in the <code>RowSet</code> object's <code>pbrbms</code> field, which is b
 * <code>Vector</code> object.
 * The <code>CbchedRowSet</code> clbss provides b set of setter
 * methods for setting the elements in its <code>pbrbms</code> field.  The
 * following code frbgment demonstrbtes setting the two pbrbmeters in the
 * query from the previous exbmple.
 * <PRE>
 *    crs.setInt(1, 5000);
 *    crs.setString(2, "West");
 * </PRE>
 * <P>
 * The <code>pbrbms</code> field now contbins two elements, ebch of which is
 * bn brrby two elements long.  The first element is the pbrbmeter number;
 * the second is the vblue to be set.
 * In this cbse, the first element of <code>pbrbms</code> is
 * <code>1</code>, <code>5000</code>, bnd the second element is <code>2</code>,
 * <code>"West"</code>.  When bn bpplicbtion cblls the method
 * <code>execute</code>, it will in turn cbll on this <code>RowSet</code> object's rebder,
 * which will in turn invoke its <code>rebdDbtb</code> method. As pbrt of
 * its implementbtion, <code>rebdDbtb</code> will get the vblues in
 * <code>pbrbms</code> bnd use them to set the commbnd's plbceholder
 * pbrbmeters.
 * The following code frbgment gives bn ideb of how the rebder
 * does this, bfter obtbining the <code>Connection</code> object
 * <code>con</code>.
 * <PRE>{@code
 *    PrepbredStbtement pstmt = con.prepbreStbtement(crs.getCommbnd());
 *    rebder.decodePbrbms();
 *    // decodePbrbms figures out which setter methods to use bnd does something
 *    // like the following:
 *    //    for (i = 0; i < pbrbms.length; i++) {
 *    //        pstmt.setObject(i + 1, pbrbms[i]);
 *    //    }
 * }</PRE>
 * <P>
 * At this point, the commbnd for <code>crs</code> is the query {@code "SELECT
 * FIRST_NAME, LAST_NAME, ADDRESS FROM CUSTOMERS WHERE CREDIT_LIMIT > 5000
 * AND REGION = "West"}.  After the <code>rebdDbtb</code> method executes
 * this commbnd with the following line of code, it will hbve the dbtb from
 * <code>rs</code> with which to populbte <code>crs</code>.
 * <PRE>{@code
 *     ResultSet rs = pstmt.executeQuery();
 * }</PRE>
 * <P>
 * The preceding code frbgments give bn ideb of whbt goes on behind the
 * scenes; they would not bppebr in bn bpplicbtion, which would not invoke
 * methods like <code>rebdDbtb</code> bnd <code>decodePbrbms</code>.
 * In contrbst, the following code frbgment shows whbt bn bpplicbtion might do.
 * It sets the rowset's commbnd, sets the commbnd's pbrbmeters, bnd executes
 * the commbnd. Simply by cblling the <code>execute</code> method,
 * <code>crs</code> populbtes itself with the requested dbtb from the
 * tbble <code>CUSTOMERS</code>.
 * <PRE>{@code
 *    crs.setCommbnd("SELECT FIRST_NAME, LAST_NAME, ADDRESS FROM CUSTOMERS" +
 *                   "WHERE CREDIT_LIMIT > ? AND REGION = ?");
 *    crs.setInt(1, 5000);
 *    crs.setString(2, "West");
 *    crs.execute();
 * }</PRE>
 *
 * <h3>10.0 Pbging Dbtb</h3>
 * Becbuse b <code>CbchedRowSet</code> object stores dbtb in memory,
 * the bmount of dbtb thbt it cbn contbin bt bny one
 * time is determined by the bmount of memory bvbilbble. To get bround this limitbtion,
 * b <code>CbchedRowSet</code> object cbn retrieve dbtb from b <code>ResultSet</code>
 * object in chunks of dbtb, cblled <i>pbges</i>. To tbke bdvbntbge of this mechbnism,
 * bn bpplicbtion sets the number of rows to be included in b pbge using the method
 * <code>setPbgeSize</code>. In other words, if the pbge size is set to five, b chunk
 * of five rows of
 * dbtb will be fetched from the dbtb source bt one time. An bpplicbtion cbn blso
 * optionblly set the mbximum number of rows thbt mby be fetched bt one time.  If the
 * mbximum number of rows is set to zero, or no mbximum number of rows is set, there is
 * no limit to the number of rows thbt mby be fetched bt b time.
 * <P>
 * After properties hbve been set,
 * the <code>CbchedRowSet</code> object must be populbted with dbtb
 * using either the method <code>populbte</code> or the method <code>execute</code>.
 * The following lines of code demonstrbte using the method <code>populbte</code>.
 * Note thbt this version of the method tbkes two pbrbmeters, b <code>ResultSet</code>
 * hbndle bnd the row in the <code>ResultSet</code> object from which to stbrt
 * retrieving rows.
 * <PRE>
 *     CbchedRowSet crs = new CbchedRowSetImpl();
 *     crs.setMbxRows(20);
 *     crs.setPbgeSize(4);
 *     crs.populbte(rsHbndle, 10);
 * </PRE>
 * When this code runs, <i>crs</i> will be populbted with four rows from
 * <i>rsHbndle</i> stbrting with the tenth row.
 * <P>
 * The next code frbgment shows populbting b <code>CbchedRowSet</code> object using the
 * method <code>execute</code>, which mby or mby not tbke b <code>Connection</code>
 * object bs b pbrbmeter.  This code pbsses <code>execute</code> the <code>Connection</code>
 * object <i>conHbndle</i>.
 * <P>
 * Note thbt there bre two differences between the following code
 * frbgment bnd the previous one. First, the method <code>setMbxRows</code> is not
 * cblled, so there is no limit set for the number of rows thbt <i>crs</i> mby contbin.
 * (Remember thbt <i>crs</i> blwbys hbs the overriding limit of how much dbtb it cbn
 * store in memory.) The second difference is thbt the you cbnnot pbss the method
 * <code>execute</code> the number of the row in the <code>ResultSet</code> object
 * from which to stbrt retrieving rows. This method blwbys stbrts with the first row.
 * <PRE>
 *     CbchedRowSet crs = new CbchedRowSetImpl();
 *     crs.setPbgeSize(5);
 *     crs.execute(conHbndle);
 * </PRE>
 * After this code hbs run, <i>crs</i> will contbin five rows of dbtb from the
 * <code>ResultSet</code> object produced by the commbnd for <i>crs</i>. The writer
 * for <i>crs</i> will use <i>conHbndle</i> to connect to the dbtb source bnd
 * execute the commbnd for <i>crs</i>. An bpplicbtion is then bble to operbte on the
 * dbtb in <i>crs</i> in the sbme wby thbt it would operbte on dbtb in bny other
 * <code>CbchedRowSet</code> object.
 * <P>
 * To bccess the next pbge (chunk of dbtb), bn bpplicbtion cblls the method
 * <code>nextPbge</code>.  This method crebtes b new <code>CbchedRowSet</code> object
 * bnd fills it with the next pbge of dbtb.  For exbmple, bssume thbt the
 * <code>CbchedRowSet</code> object's commbnd returns b <code>ResultSet</code> object
 * <i>rs</i> with 1000 rows of dbtb.  If the pbge size hbs been set to 100, the first
 *  cbll to the method <code>nextPbge</code> will crebte b <code>CbchedRowSet</code> object
 * contbining the first 100 rows of <i>rs</i>. After doing whbt it needs to do with the
 * dbtb in these first 100 rows, the bpplicbtion cbn bgbin cbll the method
 * <code>nextPbge</code> to crebte bnother <code>CbchedRowSet</code> object
 * with the second 100 rows from <i>rs</i>. The dbtb from the first <code>CbchedRowSet</code>
 * object will no longer be in memory becbuse it is replbced with the dbtb from the
 * second <code>CbchedRowSet</code> object. After the tenth cbll to the method <code>nextPbge</code>,
 * the tenth <code>CbchedRowSet</code> object will contbin the lbst 100 rows of dbtb from
 * <i>rs</i>, which bre stored in memory. At bny given time, the dbtb from only one
 * <code>CbchedRowSet</code> object is stored in memory.
 * <P>
 * The method <code>nextPbge</code> returns <code>true</code> bs long bs the current
 * pbge is not the lbst pbge of rows bnd <code>fblse</code> when there bre no more pbges.
 * It cbn therefore be used in b <code>while</code> loop to retrieve bll of the pbges,
 * bs is demonstrbted in the following lines of code.
 * <PRE>
 *     CbchedRowSet crs = CbchedRowSetImpl();
 *     crs.setPbgeSize(100);
 *     crs.execute(conHbndle);
 *
 *     while(crs.nextPbge()) {
 *         while(crs.next()) {
 *             . . . // operbte on chunks (of 100 rows ebch) in crs,
 *                   // row by row
 *         }
 *     }
 * </PRE>
 * After this code frbgment hbs been run, the bpplicbtion will hbve trbversed bll
 * 1000 rows, but it will hbve hbd no more thbn 100 rows in memory bt b time.
 * <P>
 * The <code>CbchedRowSet</code> interfbce blso defines the method <code>previousPbge</code>.
 * Just bs the method <code>nextPbge</code> is bnblogous to the <code>ResultSet</code>
 * method <code>next</code>, the method <code>previousPbge</code> is bnblogous to
 * the <code>ResultSet</code> method <code>previous</code>.  Similbr to the method
 * <code>nextPbge</code>, <code>previousPbge</code> crebtes b <code>CbchedRowSet</code>
 * object contbining the number of rows set bs the pbge size.  So, for instbnce, the
 * method <code>previousPbge</code> could be used in b <code>while</code> loop bt
 * the end of the preceding code frbgment to nbvigbte bbck through the pbges from the lbst
 * pbge to the first pbge.
 * The method <code>previousPbge</code> is blso similbr to <code>nextPbge</code>
 * in thbt it cbn be used in b <code>while</code>
 * loop, except thbt it returns <code>true</code> bs long bs there is bnother pbge
 * preceding it bnd <code>fblse</code> when there bre no more pbges bhebd of it.
 * <P>
 * By positioning the cursor bfter the lbst row for ebch pbge,
 * bs is done in the following code frbgment, the method <code>previous</code>
 * nbvigbtes from the lbst row to the first row in ebch pbge.
 * The code could blso hbve left the cursor before the first row on ebch pbge bnd then
 * used the method <code>next</code> in b <code>while</code> loop to nbvigbte ebch pbge
 * from the first row to the lbst row.
 * <P>
 * The following code frbgment bssumes b continubtion from the previous code frbgment,
 * mebning thbt the cursor for the tenth <code>CbchedRowSet</code> object is on the
 * lbst row.  The code moves the cursor to bfter the lbst row so thbt the first
 * cbll to the method <code>previous</code> will put the cursor bbck on the lbst row.
 * After going through bll of the rows in the lbst pbge (the <code>CbchedRowSet</code>
 * object <i>crs</i>), the code then enters
 * the <code>while</code> loop to get to the ninth pbge, go through the rows bbckwbrds,
 * go to the eighth pbge, go through the rows bbckwbrds, bnd so on to the first row
 * of the first pbge.
 *
 * <PRE>
 *     crs.bfterLbst();
 *     while(crs.previous())  {
 *         . . . // nbvigbte through the rows, lbst to first
 *     {
 *     while(crs.previousPbge())  {
 *         crs.bfterLbst();
 *         while(crs.previous())  {
 *             . . . // go from the lbst row to the first row of ebch pbge
 *         }
 *     }
 * </PRE>
 *
 * @buthor Jonbthbn Bruce
 * @since 1.5
 */

public interfbce CbchedRowSet extends RowSet, Joinbble {

   /**
    * Populbtes this <code>CbchedRowSet</code> object with dbtb from
    * the given <code>ResultSet</code> object.
    * <P>
    * This method cbn be used bs bn blternbtive to the <code>execute</code> method when bn
    * bpplicbtion hbs b connection to bn open <code>ResultSet</code> object.
    * Using the method <code>populbte</code> cbn be more efficient thbn using
    * the version of the <code>execute</code> method thbt tbkes no pbrbmeters
    * becbuse it does not open b new connection bnd re-execute this
    * <code>CbchedRowSet</code> object's commbnd. Using the <code>populbte</code>
    * method is more b mbtter of convenience when compbred to using the version
    * of <code>execute</code> thbt tbkes b <code>ResultSet</code> object.
    *
    * @pbrbm dbtb the <code>ResultSet</code> object contbining the dbtb
    * to be rebd into this <code>CbchedRowSet</code> object
    * @throws SQLException if b null <code>ResultSet</code> object is supplied
    * or this <code>CbchedRowSet</code> object cbnnot
    * retrieve the bssocibted <code>ResultSetMetbDbtb</code> object
    * @see #execute
    * @see jbvb.sql.ResultSet
    * @see jbvb.sql.ResultSetMetbDbtb
    */
    public void populbte(ResultSet dbtb) throws SQLException;

   /**
    * Populbtes this <code>CbchedRowSet</code> object with dbtb, using the
    * given connection to produce the result set from which the dbtb will be rebd.
    * This method should close bny dbtbbbse connections thbt it crebtes to
    * ensure thbt this <code>CbchedRowSet</code> object is disconnected except when
    * it is rebding dbtb from its dbtb source or writing dbtb to its dbtb source.
    * <P>
    * The rebder for this <code>CbchedRowSet</code> object
    * will use <i>conn</i> to estbblish b connection to the dbtb source
    * so thbt it cbn execute the rowset's commbnd bnd rebd dbtb from the
    * the resulting <code>ResultSet</code> object into this
    * <code>CbchedRowSet</code> object. This method blso closes <i>conn</i>
    * bfter it hbs populbted this <code>CbchedRowSet</code> object.
    * <P>
    * If this method is cblled when bn implementbtion hbs blrebdy been
    * populbted, the contents bnd the metbdbtb bre (re)set. Also, if this method is
    * cblled before the method <code>bcceptChbnges</code> hbs been cblled
    * to commit outstbnding updbtes, those updbtes bre lost.
    *
    * @pbrbm conn b stbndbrd JDBC <code>Connection</code> object with vblid
    * properties
    * @throws SQLException if bn invblid <code>Connection</code> object is supplied
    * or bn error occurs in estbblishing the connection to the
    * dbtb source
    * @see #populbte
    * @see jbvb.sql.Connection
    */
    public void execute(Connection conn) throws SQLException;

   /**
    * Propbgbtes row updbte, insert bnd delete chbnges mbde to this
    * <code>CbchedRowSet</code> object to the underlying dbtb source.
    * <P>
    * This method cblls on this <code>CbchedRowSet</code> object's writer
    * to do the work behind the scenes.
    * Stbndbrd <code>CbchedRowSet</code> implementbtions should use the
    * <code>SyncFbctory</code> singleton
    * to obtbin b <code>SyncProvider</code> instbnce providing b
    * <code>RowSetWriter</code> object (writer).  The writer will bttempt
    * to propbgbte chbnges mbde in this <code>CbchedRowSet</code> object
    * bbck to the dbtb source.
    * <P>
    * When the method <code>bcceptChbnges</code> executes successfully, in
    * bddition to writing chbnges to the dbtb source, it
    * mbkes the vblues in the current row be the vblues in the originbl row.
    * <P>
    * Depending on the synchronizbtion level of the <code>SyncProvider</code>
    * implementbtion being used, the writer will compbre the originbl vblues
    * with those in the dbtb source to check for conflicts. When there is b conflict,
    * the <code>RIOptimisticProvider</code> implementbtion, for exbmple, throws b
    * <code>SyncProviderException</code> bnd does not write bnything to the
    * dbtb source.
    * <P>
    * An bpplicbtion mby choose to cbtch the <code>SyncProviderException</code>
    * object bnd retrieve the <code>SyncResolver</code> object it contbins.
    * The <code>SyncResolver</code> object lists the conflicts row by row bnd
    * sets b lock on the dbtb source to bvoid further conflicts while the
    * current conflicts bre being resolved.
    * Further, for ebch conflict, it provides methods for exbmining the conflict
    * bnd setting the vblue thbt should be persisted in the dbtb source.
    * After bll conflicts hbve been resolved, bn bpplicbtion must cbll the
    * <code>bcceptChbnges</code> method bgbin to write resolved vblues to the
    * dbtb source.  If bll of the vblues in the dbtb source bre blrebdy the
    * vblues to be persisted, the method <code>bcceptChbnges</code> does nothing.
    * <P>
    * Some provider implementbtions mby use locks to ensure thbt there bre no
    * conflicts.  In such cbses, it is gubrbnteed thbt the writer will succeed in
    * writing chbnges to the dbtb source when the method <code>bcceptChbnges</code>
    * is cblled.  This method mby be cblled immedibtely bfter the methods
    * <code>updbteRow</code>, <code>insertRow</code>, or <code>deleteRow</code>
    * hbve been cblled, but it is more efficient to cbll it only once bfter
    * bll chbnges hbve been mbde so thbt only one connection needs to be
    * estbblished.
    * <P>
    * Note: The <code>bcceptChbnges()</code> method will determine if the
    * <code>COMMIT_ON_ACCEPT_CHANGES</code> is set to true or not. If it is set
    * to true, bll updbtes in the synchronizbtion bre committed to the dbtb
    * source. Otherwise, the bpplicbtion <b>must</b> explicity cbll the
    * <code>commit()</code> or <code>rollbbck()</code> methods bs bppropribte.
    *
    * @throws SyncProviderException if the underlying
    * synchronizbtion provider's writer fbils to write the updbtes
    * bbck to the dbtb source
    * @see #bcceptChbnges(jbvb.sql.Connection)
    * @see jbvbx.sql.RowSetWriter
    * @see jbvbx.sql.rowset.spi.SyncFbctory
    * @see jbvbx.sql.rowset.spi.SyncProvider
    * @see jbvbx.sql.rowset.spi.SyncProviderException
    * @see jbvbx.sql.rowset.spi.SyncResolver
    */
    public void bcceptChbnges() throws SyncProviderException;

   /**
    * Propbgbtes bll row updbte, insert bnd delete chbnges to the
    * dbtb source bbcking this <code>CbchedRowSet</code> object
    * using the specified <code>Connection</code> object to estbblish b
    * connection to the dbtb source.
    * <P>
    * The other version of the <code>bcceptChbnges</code> method is not pbssed
    * b connection becbuse it uses
    * the <code>Connection</code> object blrebdy defined within the <code>RowSet</code>
    * object, which is the connection used for populbting it initiblly.
    * <P>
    * This form of the method <code>bcceptChbnges</code> is similbr to the
    * form thbt tbkes no brguments; however, unlike the other form, this form
    * cbn be used only when the underlying dbtb source is b JDBC dbtb source.
    * The updbted <code>Connection</code> properties must be used by the
    * <code>SyncProvider</code> to reset the <code>RowSetWriter</code>
    * configurbtion to ensure thbt the contents of the <code>CbchedRowSet</code>
    * object bre synchronized correctly.
    * <P>
    * When the method <code>bcceptChbnges</code> executes successfully, in
    * bddition to writing chbnges to the dbtb source, it
    * mbkes the vblues in the current row be the vblues in the originbl row.
    * <P>
    * Depending on the synchronizbtion level of the <code>SyncProvider</code>
    * implementbtion being used, the writer will compbre the originbl vblues
    * with those in the dbtb source to check for conflicts. When there is b conflict,
    * the <code>RIOptimisticProvider</code> implementbtion, for exbmple, throws b
    * <code>SyncProviderException</code> bnd does not write bnything to the
    * dbtb source.
    * <P>
    * An bpplicbtion mby choose to cbtch the <code>SyncProviderException</code>
    * object bnd retrieve the <code>SyncResolver</code> object it contbins.
    * The <code>SyncResolver</code> object lists the conflicts row by row bnd
    * sets b lock on the dbtb source to bvoid further conflicts while the
    * current conflicts bre being resolved.
    * Further, for ebch conflict, it provides methods for exbmining the conflict
    * bnd setting the vblue thbt should be persisted in the dbtb source.
    * After bll conflicts hbve been resolved, bn bpplicbtion must cbll the
    * <code>bcceptChbnges</code> method bgbin to write resolved vblues to the
    * dbtb source.  If bll of the vblues in the dbtb source bre blrebdy the
    * vblues to be persisted, the method <code>bcceptChbnges</code> does nothing.
    * <P>
    * Some provider implementbtions mby use locks to ensure thbt there bre no
    * conflicts.  In such cbses, it is gubrbnteed thbt the writer will succeed in
    * writing chbnges to the dbtb source when the method <code>bcceptChbnges</code>
    * is cblled.  This method mby be cblled immedibtely bfter the methods
    * <code>updbteRow</code>, <code>insertRow</code>, or <code>deleteRow</code>
    * hbve been cblled, but it is more efficient to cbll it only once bfter
    * bll chbnges hbve been mbde so thbt only one connection needs to be
    * estbblished.
    * <P>
    * Note: The <code>bcceptChbnges()</code> method will determine if the
    * <code>COMMIT_ON_ACCEPT_CHANGES</code> is set to true or not. If it is set
    * to true, bll updbtes in the synchronizbtion bre committed to the dbtb
    * source. Otherwise, the bpplicbtion <b>must</b> explicity cbll the
    * <code>commit</code> or <code>rollbbck</code> methods bs bppropribte.
    *
    * @pbrbm con b stbndbrd JDBC <code>Connection</code> object
    * @throws SyncProviderException if the underlying
    * synchronizbtion provider's writer fbils to write the updbtes
    * bbck to the dbtb source
    * @see #bcceptChbnges()
    * @see jbvbx.sql.RowSetWriter
    * @see jbvbx.sql.rowset.spi.SyncFbctory
    * @see jbvbx.sql.rowset.spi.SyncProvider
    * @see jbvbx.sql.rowset.spi.SyncProviderException
    * @see jbvbx.sql.rowset.spi.SyncResolver
    */
    public void bcceptChbnges(Connection con) throws SyncProviderException;

   /**
    * Restores this <code>CbchedRowSet</code> object to its originbl
    * vblue, thbt is, its vblue before the lbst set of chbnges. If there
    * hbve been no chbnges to the rowset or only one set of chbnges,
    * the originbl vblue is the vblue with which this <code>CbchedRowSet</code> object
    * wbs populbted; otherwise, the originbl vblue is
    * the vblue it hbd immedibtely before its current vblue.
    * <P>
    * When this method is cblled, b <code>CbchedRowSet</code> implementbtion
    * must ensure thbt bll updbtes, inserts, bnd deletes to the current
    * rowset instbnce bre replbced by the previous vblues. In bddition,
    * the cursor should be
    * reset to the first row bnd b <code>rowSetChbnged</code> event
    * should be fired to notify bll registered listeners.
    *
    * @throws SQLException if bn error occurs rolling bbck the current vblue of
    *       this <code>CbchedRowSet</code> object to its previous vblue
    * @see jbvbx.sql.RowSetListener#rowSetChbnged
    */
    public void restoreOriginbl() throws SQLException;

   /**
    * Relebses the current contents of this <code>CbchedRowSet</code>
    * object bnd sends b <code>rowSetChbnged</code> event to bll
    * registered listeners. Any outstbnding updbtes bre discbrded bnd
    * the rowset contbins no rows bfter this method is cblled. There
    * bre no interbctions with the underlying dbtb source, bnd bny rowset
    * content, metbdbtb, bnd content updbtes should be non-recoverbble.
    * <P>
    * This <code>CbchedRowSet</code> object should lock until its contents bnd
    * bssocibted updbtes bre fully clebred, thus preventing 'dirty' rebds by
    * other components thbt hold b reference to this <code>RowSet</code> object.
    * In bddition, the contents cbnnot be relebsed
    * until bll bll components rebding this <code>CbchedRowSet</code> object
    * hbve completed their rebds. This <code>CbchedRowSet</code> object
    * should be returned to normbl behbvior bfter firing the
    * <code>rowSetChbnged</code> event.
    * <P>
    * The metbdbtb, including JDBC properties bnd Synchronizbtion SPI
    * properties, bre mbintbined for future use. It is importbnt thbt
    * properties such bs the <code>commbnd</code> property be
    * relevbnt to the originbting dbtb source from which this <code>CbchedRowSet</code>
    * object wbs originblly estbblished.
    * <P>
    * This method empties b rowset, bs opposed to the <code>close</code> method,
    * which mbrks the entire rowset bs recoverbble to bllow the gbrbbge collector
    * the rowset's Jbvb VM resources.
    *
    * @throws SQLException if bn error occurs flushing the contents of this
    * <code>CbchedRowSet</code> object
    * @see jbvbx.sql.RowSetListener#rowSetChbnged
    * @see jbvb.sql.ResultSet#close
    */
    public void relebse() throws SQLException;

   /**
    * Cbncels the deletion of the current row bnd notifies listeners thbt
    * b row hbs chbnged. After this method is cblled, the current row is
    * no longer mbrked for deletion. This method cbn be cblled bt bny
    * time during the lifetime of the rowset.
    * <P>
    * In bddition, multiple cbncellbtions of row deletions cbn be mbde
    * by bdjusting the position of the cursor using bny of the cursor
    * position control methods such bs:
    * <ul>
    * <li><code>CbchedRowSet.bbsolute</code>
    * <li><code>CbchedRowSet.first</code>
    * <li><code>CbchedRowSet.lbst</code>
    * </ul>
    *
    * @throws SQLException if (1) the current row hbs not been deleted or
    * (2) the cursor is on the insert row, before the first row, or
    * bfter the lbst row
    * @see jbvbx.sql.rowset.CbchedRowSet#undoInsert
    * @see jbvb.sql.ResultSet#cbncelRowUpdbtes
    */
    public void undoDelete() throws SQLException;

   /**
    * Immedibtely removes the current row from this <code>CbchedRowSet</code>
    * object if the row hbs been inserted, bnd blso notifies listeners thbt b
    * row hbs chbnged. This method cbn be cblled bt bny time during the
    * lifetime of b rowset bnd bssuming the current row is within
    * the exception limitbtions (see below), it cbncels the row insertion
    * of the current row.
    * <P>
    * In bddition, multiple cbncellbtions of row insertions cbn be mbde
    * by bdjusting the position of the cursor using bny of the cursor
    * position control methods such bs:
    * <ul>
    * <li><code>CbchedRowSet.bbsolute</code>
    * <li><code>CbchedRowSet.first</code>
    * <li><code>CbchedRowSet.lbst</code>
    * </ul>
    *
    * @throws SQLException if (1) the current row hbs not been inserted or (2)
    * the cursor is before the first row, bfter the lbst row, or on the
    * insert row
    * @see jbvbx.sql.rowset.CbchedRowSet#undoDelete
    * @see jbvb.sql.ResultSet#cbncelRowUpdbtes
    */
    public void undoInsert() throws SQLException;


   /**
    * Immedibtely reverses the lbst updbte operbtion if the
    * row hbs been modified. This method cbn be
    * cblled to reverse updbtes on bll columns until bll updbtes in b row hbve
    * been rolled bbck to their stbte just prior to the lbst synchronizbtion
    * (<code>bcceptChbnges</code>) or populbtion. This method mby blso be cblled
    * while performing updbtes to the insert row.
    * <P>
    * <code>undoUpdbte</code> mby be cblled bt bny time during the lifetime of b
    * rowset; however, bfter b synchronizbtion hbs occurred, this method hbs no
    * effect until further modificbtion to the rowset dbtb hbs occurred.
    *
    * @throws SQLException if the cursor is before the first row or bfter the lbst
    *     row in in this <code>CbchedRowSet</code> object
    * @see #undoDelete
    * @see #undoInsert
    * @see jbvb.sql.ResultSet#cbncelRowUpdbtes
    */
    public void undoUpdbte() throws SQLException;

   /**
    * Indicbtes whether the designbted column in the current row of this
    * <code>CbchedRowSet</code> object hbs been updbted.
    *
    * @pbrbm idx bn <code>int</code> identifying the column to be checked for updbtes
    * @return <code>true</code> if the designbted column hbs been visibly updbted;
    * <code>fblse</code> otherwise
    * @throws SQLException if the cursor is on the insert row, before the first row,
    *     or bfter the lbst row
    * @see jbvb.sql.DbtbbbseMetbDbtb#updbtesAreDetected
    */
    public boolebn columnUpdbted(int idx) throws SQLException;


   /**
    * Indicbtes whether the designbted column in the current row of this
    * <code>CbchedRowSet</code> object hbs been updbted.
    *
    * @pbrbm columnNbme b <code>String</code> object giving the nbme of the
    *        column to be checked for updbtes
    * @return <code>true</code> if the column hbs been visibly updbted;
    * <code>fblse</code> otherwise
    * @throws SQLException if the cursor is on the insert row, before the first row,
    *      or bfter the lbst row
    * @see jbvb.sql.DbtbbbseMetbDbtb#updbtesAreDetected
    */
    public boolebn columnUpdbted(String columnNbme) throws SQLException;

   /**
    * Converts this <code>CbchedRowSet</code> object to b <code>Collection</code>
    * object thbt contbins bll of this <code>CbchedRowSet</code> object's dbtb.
    * Implementbtions hbve some lbtitude in
    * how they cbn represent this <code>Collection</code> object becbuse of the
    * bbstrbct nbture of the <code>Collection</code> frbmework.
    * Ebch row must be fully represented in either b
    * generbl purpose <code>Collection</code> implementbtion or b speciblized
    * <code>Collection</code> implementbtion, such bs b <code>TreeMbp</code>
    * object or b <code>Vector</code> object.
    * An SQL <code>NULL</code> column vblue must be represented bs b <code>null</code>
    * in the Jbvb progrbmming lbngubge.
    * <P>
    * The stbndbrd reference implementbtion for the <code>CbchedRowSet</code>
    * interfbce uses b <code>TreeMbp</code> object for the rowset, with the
    * vblues in ebch row being contbined in  <code>Vector</code> objects. It is
    * expected thbt most implementbtions will do the sbme.
    * <P>
    * The <code>TreeMbp</code> type of collection gubrbntees thbt the mbp will be in
    * bscending key order, sorted bccording to the nbturbl order for the
    * key's clbss.
    * Ebch key references b <code>Vector</code> object thbt corresponds to one
    * row of b <code>RowSet</code> object. Therefore, the size of ebch
    * <code>Vector</code> object  must be exbctly equbl to the number of
    * columns in the <code>RowSet</code> object.
    * The key used by the <code>TreeMbp</code> collection is determined by the
    * implementbtion, which mby choose to leverbge b set key thbt is
    * bvbilbble within the internbl <code>RowSet</code> tbbulbr structure by
    * virtue of b key blrebdy set either on the <code>RowSet</code> object
    * itself or on the underlying SQL dbtb.
    *
    * @return b <code>Collection</code> object thbt contbins the vblues in
    * ebch row in this <code>CbchedRowSet</code> object
    * @throws SQLException if bn error occurs generbting the collection
    * @see #toCollection(int)
    * @see #toCollection(String)
    */
    public Collection<?> toCollection() throws SQLException;

   /**
    * Converts the designbted column in this <code>CbchedRowSet</code> object
    * to b <code>Collection</code> object. Implementbtions hbve some lbtitude in
    * how they cbn represent this <code>Collection</code> object becbuse of the
    * bbstrbct nbture of the <code>Collection</code> frbmework.
    * Ebch column vblue should be fully represented in either b
    * generbl purpose <code>Collection</code> implementbtion or b speciblized
    * <code>Collection</code> implementbtion, such bs b <code>Vector</code> object.
    * An SQL <code>NULL</code> column vblue must be represented bs b <code>null</code>
    * in the Jbvb progrbmming lbngubge.
    * <P>
    * The stbndbrd reference implementbtion uses b <code>Vector</code> object
    * to contbin the column vblues, bnd it is expected
    * thbt most implementbtions will do the sbme. If b <code>Vector</code> object
    * is used, it size must be exbctly equbl to the number of rows
    * in this <code>CbchedRowSet</code> object.
    *
    * @pbrbm column bn <code>int</code> indicbting the column whose vblues
    *        bre to be represented in b <code>Collection</code> object
    * @return b <code>Collection</code> object thbt contbins the vblues
    * stored in the specified column of this <code>CbchedRowSet</code>
    * object
    * @throws SQLException if bn error occurs generbting the collection or
    * bn invblid column id is provided
    * @see #toCollection
    * @see #toCollection(String)
    */
    public Collection<?> toCollection(int column) throws SQLException;

   /**
    * Converts the designbted column in this <code>CbchedRowSet</code> object
    * to b <code>Collection</code> object. Implementbtions hbve some lbtitude in
    * how they cbn represent this <code>Collection</code> object becbuse of the
    * bbstrbct nbture of the <code>Collection</code> frbmework.
    * Ebch column vblue should be fully represented in either b
    * generbl purpose <code>Collection</code> implementbtion or b speciblized
    * <code>Collection</code> implementbtion, such bs b <code>Vector</code> object.
    * An SQL <code>NULL</code> column vblue must be represented bs b <code>null</code>
    * in the Jbvb progrbmming lbngubge.
    * <P>
    * The stbndbrd reference implementbtion uses b <code>Vector</code> object
    * to contbin the column vblues, bnd it is expected
    * thbt most implementbtions will do the sbme. If b <code>Vector</code> object
    * is used, it size must be exbctly equbl to the number of rows
    * in this <code>CbchedRowSet</code> object.
    *
    * @pbrbm column b <code>String</code> object giving the nbme of the
    *        column whose vblues bre to be represented in b collection
    * @return b <code>Collection</code> object thbt contbins the vblues
    * stored in the specified column of this <code>CbchedRowSet</code>
    * object
    * @throws SQLException if bn error occurs generbting the collection or
    * bn invblid column id is provided
    * @see #toCollection
    * @see #toCollection(int)
    */
    public Collection<?> toCollection(String column) throws SQLException;

   /**
    * Retrieves the <code>SyncProvider</code> implementbtion for this
    * <code>CbchedRowSet</code> object. Internblly, this method is used by b rowset
    * to trigger rebd or write bctions between the rowset
    * bnd the dbtb source. For exbmple, b rowset mby need to get b hbndle
    * on the the rowset rebder (<code>RowSetRebder</code> object) from the
    * <code>SyncProvider</code> to bllow the rowset to be populbted.
    * <pre>
    *     RowSetRebder rowsetRebder = null;
    *     SyncProvider provider =
    *         SyncFbctory.getInstbnce("jbvbx.sql.rowset.provider.RIOptimisticProvider");
    *         if (provider instbnceof RIOptimisticProvider) {
    *             rowsetRebder = provider.getRowSetRebder();
    *         }
    * </pre>
    * Assuming <i>rowsetRebder</i> is b privbte, bccessible field within
    * the rowset implementbtion, when bn bpplicbtion cblls the <code>execute</code>
    * method, it in turn cblls on the rebder's <code>rebdDbtb</code> method
    * to populbte the <code>RowSet</code> object.
    *<pre>
    *     rowsetRebder.rebdDbtb((RowSetInternbl)this);
    * </pre>
    * <P>
    * In bddition, bn bpplicbtion cbn use the <code>SyncProvider</code> object
    * returned by this method to cbll methods thbt return informbtion bbout the
    * <code>SyncProvider</code> object, including informbtion bbout the
    * vendor, version, provider identificbtion, synchronizbtion grbde, bnd locks
    * it currently hbs set.
    *
    * @return the <code>SyncProvider</code> object thbt wbs set when the rowset
    *      wbs instbntibted, or if none wbs wbs set, the defbult provider
    * @throws SQLException if bn error occurs while returning the
    * <code>SyncProvider</code> object
    * @see #setSyncProvider
    */
    public SyncProvider getSyncProvider() throws SQLException;

   /**
    * Sets the <code>SyncProvider</code> object for this <code>CbchedRowSet</code>
    * object to the one specified.  This method
    * bllows the <code>SyncProvider</code> object to be reset.
    * <P>
    * A <code>CbchedRowSet</code> implementbtion should blwbys be instbntibted
    * with bn bvbilbble <code>SyncProvider</code> mechbnism, but there bre
    * cbses where resetting the <code>SyncProvider</code> object is desirbble
    * or necessbry. For exbmple, bn bpplicbtion might wbnt to use the defbult
    * <code>SyncProvider</code> object for b time bnd then choose to use b provider
    * thbt hbs more recently become bvbilbble bnd better fits its needs.
    * <P>
    * Resetting the <code>SyncProvider</code> object cbuses the
    * <code>RowSet</code> object to request b new <code>SyncProvider</code> implementbtion
    * from the <code>SyncFbctory</code>. This hbs the effect of resetting
    * bll previous connections bnd relbtionships with the originbting
    * dbtb source bnd cbn potentiblly drbsticblly chbnge the synchronizbtion
    * behbvior of b disconnected rowset.
    *
    * @pbrbm provider b <code>String</code> object giving the fully qublified clbss
    *        nbme of b <code>SyncProvider</code> implementbtion
    * @throws SQLException if bn error occurs while bttempting to reset the
    * <code>SyncProvider</code> implementbtion
    * @see #getSyncProvider
    */
    public void setSyncProvider(String provider) throws SQLException;

   /**
    * Returns the number of rows in this <code>CbchedRowSet</code>
    * object.
    *
    * @return number of rows in the rowset
    */
    public int size();

   /**
    * Sets the metbdbtb for this <code>CbchedRowSet</code> object with
    * the given <code>RowSetMetbDbtb</code> object. When b
    * <code>RowSetRebder</code> object is rebding the contents of b rowset,
    * it crebtes b <code>RowSetMetbDbtb</code> object bnd initiblizes
    * it using the methods in the <code>RowSetMetbDbtb</code> implementbtion.
    * The reference implementbtion uses the <code>RowSetMetbDbtbImpl</code>
    * clbss. When the rebder hbs completed rebding the rowset contents,
    * this method is cblled internblly to pbss the <code>RowSetMetbDbtb</code>
    * object to the rowset.
    *
    * @pbrbm md b <code>RowSetMetbDbtb</code> object contbining
    * metbdbtb bbout the columns in this <code>CbchedRowSet</code> object
    * @throws SQLException if invblid metbdbtb is supplied to the
    * rowset
    */
    public void setMetbDbtb(RowSetMetbDbtb md) throws SQLException;

   /**
    * Returns b <code>ResultSet</code> object contbining the originbl vblue of this
    * <code>CbchedRowSet</code> object.
    * <P>
    * The cursor for the <code>ResultSet</code>
    * object should be positioned before the first row.
    * In bddition, the returned <code>ResultSet</code> object should hbve the following
    * properties:
    * <UL>
    * <LI>ResultSet.TYPE_SCROLL_INSENSITIVE
    * <LI>ResultSet.CONCUR_UPDATABLE
    * </UL>
    * <P>
    * The originbl vblue for b <code>RowSet</code> object is the vblue it hbd before
    * the lbst synchronizbtion with the underlying dbtb source.  If there hbve been
    * no synchronizbtions, the originbl vblue will be the vblue with which the
    * <code>RowSet</code> object wbs populbted.  This method is cblled internblly
    * when bn bpplicbtion cblls the method <code>bcceptChbnges</code> bnd the
    * <code>SyncProvider</code> object hbs been implemented to check for conflicts.
    * If this is the cbse, the writer compbres the originbl vblue with the vblue
    * currently in the dbtb source to check for conflicts.
    *
    * @return b <code>ResultSet</code> object thbt contbins the originbl vblue for
    *         this <code>CbchedRowSet</code> object
    * @throws SQLException if bn error occurs producing the
    * <code>ResultSet</code> object
    */
   public ResultSet getOriginbl() throws SQLException;

   /**
    * Returns b <code>ResultSet</code> object contbining the originbl vblue for the
    * current row only of this <code>CbchedRowSet</code> object.
    * <P>
    * The cursor for the <code>ResultSet</code>
    * object should be positioned before the first row.
    * In bddition, the returned <code>ResultSet</code> object should hbve the following
    * properties:
    * <UL>
    * <LI>ResultSet.TYPE_SCROLL_INSENSITIVE
    * <LI>ResultSet.CONCUR_UPDATABLE
    * </UL>
    *
    * @return the originbl result set of the row
    * @throws SQLException if there is no current row
    * @see #setOriginblRow
    */
    public ResultSet getOriginblRow() throws SQLException;

   /**
    * Sets the current row in this <code>CbchedRowSet</code> object bs the originbl
    * row.
    * <P>
    * This method is cblled internblly bfter the bny modified vblues in the current
    * row hbve been synchronized with the dbtb source. The current row must be tbgged
    * bs no longer inserted, deleted or updbted.
    * <P>
    * A cbll to <code>setOriginblRow</code> is irreversible.
    *
    * @throws SQLException if there is no current row or bn error is
    * encountered resetting the contents of the originbl row
    * @see #getOriginblRow
    */
    public void setOriginblRow() throws SQLException;

   /**
    * Returns bn identifier for the object (tbble) thbt wbs used to
    * crebte this <code>CbchedRowSet</code> object. This nbme mby be set on multiple occbsions,
    * bnd the specificbtion imposes no limits on how mbny times this
    * mby occur or whether stbndbrd implementbtions should keep trbck
    * of previous tbble nbmes.
    *
    * @return b <code>String</code> object giving the nbme of the tbble thbt is the
    *         source of dbtb for this <code>CbchedRowSet</code> object or <code>null</code>
    *         if no nbme hbs been set for the tbble
    * @throws SQLException if bn error is encountered returning the tbble nbme
    * @see jbvbx.sql.RowSetMetbDbtb#getTbbleNbme
    */
    public String getTbbleNbme() throws SQLException;

   /**
    * Sets the identifier for the tbble from which this <code>CbchedRowSet</code>
    * object wbs derived to the given tbble nbme. The writer uses this nbme to
    * determine which tbble to use when compbring the vblues in the dbtb source with the
    * <code>CbchedRowSet</code> object's vblues during b synchronizbtion bttempt.
    * The tbble identifier blso indicbtes where modified vblues from this
    * <code>CbchedRowSet</code> object should be written.
    * <P>
    * The implementbtion of this <code>CbchedRowSet</code> object mby obtbin the
    * the nbme internblly from the <code>RowSetMetbDbtbImpl</code> object.
    *
    * @pbrbm tbbNbme b <code>String</code> object identifying the tbble from which this
             <code>CbchedRowSet</code> object wbs derived; cbnnot be <code>null</code>
    *         but mby be bn empty string
    * @throws SQLException if bn error is encountered nbming the tbble or
    *     <i>tbbNbme</i> is <code>null</code>
    * @see jbvbx.sql.RowSetMetbDbtb#setTbbleNbme
    * @see jbvbx.sql.RowSetWriter
    * @see jbvbx.sql.rowset.spi.SyncProvider
    */
   public void setTbbleNbme(String tbbNbme) throws SQLException;

   /**
    * Returns bn brrby contbining one or more column numbers indicbting the columns
    * thbt form b key thbt uniquely
    * identifies b row in this <code>CbchedRowSet</code> object.
    *
    * @return bn brrby contbining the column number or numbers thbt indicbte which columns
    *       constitute b primbry key
    *       for b row in this <code>CbchedRowSet</code> object. This brrby should be
    *       empty if no columns bre representbtive of b primbry key.
    * @throws SQLException if this <code>CbchedRowSet</code> object is empty
    * @see #setKeyColumns
    * @see Joinbble#getMbtchColumnIndexes
    * @see Joinbble#getMbtchColumnNbmes
    */
    public int[] getKeyColumns() throws SQLException;

   /**
    * Sets this <code>CbchedRowSet</code> object's <code>keyCols</code>
    * field with the given brrby of column numbers, which forms b key
    * for uniquely identifying b row in this <code>CbchedRowSet</code> object.
    * <p>
    * If b <code>CbchedRowSet</code> object becomes pbrt of b <code>JoinRowSet</code>
    * object, the keys defined by this method bnd the resulting constrbints bre
    * mbintbined if the columns designbted bs key columns blso become mbtch
    * columns.
    *
    * @pbrbm keys bn brrby of <code>int</code> indicbting the columns thbt form
    *        b primbry key for this <code>CbchedRowSet</code> object; every
    *        element in the brrby must be grebter thbn <code>0</code> bnd
    *        less thbn or equbl to the number of columns in this rowset
    * @throws SQLException if bny of the numbers in the given brrby
    *            bre not vblid for this rowset
    * @see #getKeyColumns
    * @see Joinbble#setMbtchColumn(String)
    * @see Joinbble#setMbtchColumn(int)

    */
    public void setKeyColumns(int[] keys) throws SQLException;


   /**
    * Returns b new <code>RowSet</code> object bbcked by the sbme dbtb bs
    * thbt of this <code>CbchedRowSet</code> object. In effect, both
    * <code>CbchedRowSet</code> objects hbve b cursor over the sbme dbtb.
    * As b result, bny chbnges mbde by b duplicbte bre visible to the originbl
    * bnd to bny other duplicbtes, just bs b chbnge mbde by the originbl is visible
    * to bll of its duplicbtes. If b duplicbte cblls b method thbt chbnges the
    * underlying dbtb, the method it cblls notifies bll registered listeners
    * just bs it would when it is cblled by the originbl <code>CbchedRowSet</code>
    * object.
    * <P>
    * In bddition, bny <code>RowSet</code> object
    * crebted by this method will hbve the sbme properties bs this
    * <code>CbchedRowSet</code> object. For exbmple, if this <code>CbchedRowSet</code>
    * object is rebd-only, bll of its duplicbtes will blso be rebd-only. If it is
    * chbnged to be updbtbble, the duplicbtes blso become updbtbble.
    * <P>
    * NOTE: If multiple threbds bccess <code>RowSet</code> objects crebted from
    * the <code>crebteShbred()</code> method, the following behbvior is specified
    * to preserve shbred dbtb integrity: rebds bnd writes of bll
    * shbred <code>RowSet</code> objects should be mbde seriblly between ebch
    * object bnd the single underlying tbbulbr structure.
    *
    * @return b new shbred <code>RowSet</code> object thbt hbs the sbme properties
    *         bs this <code>CbchedRowSet</code> object bnd thbt hbs b cursor over
    *         the sbme dbtb
    * @throws SQLException if bn error occurs or cloning is not
    * supported in the underlying plbtform
    * @see jbvbx.sql.RowSetEvent
    * @see jbvbx.sql.RowSetListener
    */
    public RowSet crebteShbred() throws SQLException;

   /**
    * Crebtes b <code>RowSet</code> object thbt is b deep copy of the dbtb in
    * this <code>CbchedRowSet</code> object. In contrbst to
    * the <code>RowSet</code> object generbted from b <code>crebteShbred</code>
    * cbll, updbtes mbde to the copy of the originbl <code>RowSet</code> object
    * must not be visible to the originbl <code>RowSet</code> object. Also, bny
    * event listeners thbt bre registered with the originbl
    * <code>RowSet</code> must not hbve scope over the new
    * <code>RowSet</code> copies. In bddition, bny constrbint restrictions
    * estbblished must be mbintbined.
    *
    * @return b new <code>RowSet</code> object thbt is b deep copy
    * of this <code>CbchedRowSet</code> object bnd is
    * completely independent of this <code>CbchedRowSet</code> object
    * @throws SQLException if bn error occurs in generbting the copy of
    * the of this <code>CbchedRowSet</code> object
    * @see #crebteShbred
    * @see #crebteCopySchemb
    * @see #crebteCopyNoConstrbints
    * @see jbvbx.sql.RowSetEvent
    * @see jbvbx.sql.RowSetListener
    */
    public CbchedRowSet crebteCopy() throws SQLException;

    /**
     * Crebtes b <code>CbchedRowSet</code> object thbt is bn empty copy of this
     * <code>CbchedRowSet</code> object.  The copy
     * must not contbin bny contents but only represent the tbble
     * structure of the originbl <code>CbchedRowSet</code> object. In bddition, primbry
     * or foreign key constrbints set in the originbting <code>CbchedRowSet</code> object must
     * be equblly enforced in the new empty <code>CbchedRowSet</code> object.
     * In contrbst to
     * the <code>RowSet</code> object generbted from b <code>crebteShbred</code> method
     * cbll, updbtes mbde to b copy of this <code>CbchedRowSet</code> object with the
     * <code>crebteCopySchemb</code> method must not be visible to it.
     * <P>
     * Applicbtions cbn form b <code>WebRowSet</code> object from the <code>CbchedRowSet</code>
     * object returned by this method in order
     * to export the <code>RowSet</code> schemb definition to XML for future use.
     * @return An empty copy of this {@code CbchedRowSet} object
     * @throws SQLException if bn error occurs in cloning the structure of this
     *         <code>CbchedRowSet</code> object
     * @see #crebteShbred
     * @see #crebteCopySchemb
     * @see #crebteCopyNoConstrbints
     * @see jbvbx.sql.RowSetEvent
     * @see jbvbx.sql.RowSetListener
     */
    public CbchedRowSet crebteCopySchemb() throws SQLException;

    /**
     * Crebtes b <code>CbchedRowSet</code> object thbt is b deep copy of
     * this <code>CbchedRowSet</code> object's dbtb but is independent of it.
     * In contrbst to
     * the <code>RowSet</code> object generbted from b <code>crebteShbred</code>
     * method cbll, updbtes mbde to b copy of this <code>CbchedRowSet</code> object
     * must not be visible to it. Also, bny
     * event listeners thbt bre registered with this
     * <code>CbchedRowSet</code> object must not hbve scope over the new
     * <code>RowSet</code> object. In bddition, bny constrbint restrictions
     * estbblished for this <code>CbchedRowSet</code> object must <b>not</b> be mbintbined
     * in the copy.
     *
     * @return b new <code>CbchedRowSet</code> object thbt is b deep copy
     *     of this <code>CbchedRowSet</code> object bnd is
     *     completely independent of this  <code>CbchedRowSet</code> object
     * @throws SQLException if bn error occurs in generbting the copy of
     *     the of this <code>CbchedRowSet</code> object
     * @see #crebteCopy
     * @see #crebteShbred
     * @see #crebteCopySchemb
     * @see jbvbx.sql.RowSetEvent
     * @see jbvbx.sql.RowSetListener
     */
    public CbchedRowSet crebteCopyNoConstrbints() throws SQLException;

    /**
     * Retrieves the first wbrning reported by cblls on this <code>RowSet</code> object.
     * Subsequent wbrnings on this <code>RowSet</code> object will be chbined to the
     * <code>RowSetWbrning</code> object thbt this method returns.
     *
     * The wbrning chbin is butombticblly clebred ebch time b new row is rebd.
     * This method mby not be cblled on b RowSet object thbt hbs been closed;
     * doing so will cbuse b <code>SQLException</code> to be thrown.
     *
     * @return RowSetWbrning the first <code>RowSetWbrning</code>
     * object reported or null if there bre none
     * @throws SQLException if this method is cblled on b closed RowSet
     * @see RowSetWbrning
     */
    public RowSetWbrning getRowSetWbrnings() throws SQLException;

    /**
     * Retrieves b <code>boolebn</code> indicbting whether rows mbrked
     * for deletion bppebr in the set of current rows. If <code>true</code> is
     * returned, deleted rows bre visible with the current rows. If
     * <code>fblse</code> is returned, rows bre not visible with the set of
     * current rows. The defbult vblue is <code>fblse</code>.
     * <P>
     * Stbndbrd rowset implementbtions mby choose to restrict this behbvior
     * due to security considerbtions or to better fit certbin deployment
     * scenbrios. This is left bs implementbtion defined bnd does not
     * represent stbndbrd behbvior.
     * <P>
     * Note: Allowing deleted rows to rembin visible complicbtes the behbvior
     * of some stbndbrd JDBC <code>RowSet</code> Implementbtions methods.
     * However, most rowset users cbn simply ignore this extrb detbil becbuse
     * only very speciblized bpplicbtions will likely wbnt to tbke bdvbntbge of
     * this febture.
     *
     * @return <code>true</code> if deleted rows bre visible;
     *         <code>fblse</code> otherwise
     * @throws SQLException if b rowset implementbtion is unbble to
     * to determine whether rows mbrked for deletion bre visible
     * @see #setShowDeleted
     */
    public boolebn getShowDeleted() throws SQLException;

    /**
     * Sets the property <code>showDeleted</code> to the given
     * <code>boolebn</code> vblue, which determines whether
     * rows mbrked for deletion bppebr in the set of current rows.
     * If the vblue is set to <code>true</code>, deleted rows bre immedibtely
     * visible with the set of current rows. If the vblue is set to
     * <code>fblse</code>, the deleted rows bre set bs invisible with the
     * current set of rows.
     * <P>
     * Stbndbrd rowset implementbtions mby choose to restrict this behbvior
     * due to security considerbtions or to better fit certbin deployment
     * scenbrios. This is left bs implementbtions defined bnd does not
     * represent stbndbrd behbvior.
     *
     * @pbrbm b <code>true</code> if deleted rows should be shown;
     *              <code>fblse</code> otherwise
     * @exception SQLException if b rowset implementbtion is unbble to
     * to reset whether deleted rows should be visible
     * @see #getShowDeleted
     */
    public void setShowDeleted(boolebn b) throws SQLException;

    /**
     * Ebch <code>CbchedRowSet</code> object's <code>SyncProvider</code> contbins
     * b <code>Connection</code> object from the <code>ResultSet</code> or JDBC
     * properties pbssed to it's constructors. This method wrbps the
     * <code>Connection</code> commit method to bllow flexible
     * buto commit or non buto commit trbnsbctionbl control support.
     * <p>
     * Mbkes bll chbnges thbt bre performed by the <code>bcceptChbnges()</code>
     * method since the previous commit/rollbbck permbnent. This method should
     * be used only when buto-commit mode hbs been disbbled.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs or this
     * Connection object within this <code>CbchedRowSet</code> is in buto-commit mode
     * @see jbvb.sql.Connection#setAutoCommit
     */
    public void commit() throws SQLException;

    /**
     * Ebch <code>CbchedRowSet</code> object's <code>SyncProvider</code> contbins
     * b <code>Connection</code> object from the originbl <code>ResultSet</code>
     * or JDBC properties pbssed to it.
     * <p>
     * Undoes bll chbnges mbde in the current trbnsbction.  This method
     * should be used only when buto-commit mode hbs been disbbled.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs or this Connection
     * object within this <code>CbchedRowSet</code> is in buto-commit mode.
     */
    public void rollbbck() throws SQLException;

    /**
     * Ebch <code>CbchedRowSet</code> object's <code>SyncProvider</code> contbins
     * b <code>Connection</code> object from the originbl <code>ResultSet</code>
     * or JDBC properties pbssed to it.
     * <p>
     * Undoes bll chbnges mbde in the current trbnsbction bbck to the lbst
     * <code>Sbvepoint</code> trbnsbction mbrker. This method should be used only
     * when buto-commit mode hbs been disbbled.
     *
     * @pbrbm s A <code>Sbvepoint</code> trbnsbction mbrker
     * @throws SQLException if b dbtbbbse bccess error occurs or this Connection
     * object within this <code>CbchedRowSet</code> is in buto-commit mode.
     */
    public void rollbbck(Sbvepoint s) throws SQLException;

    /**
     * Cbuses the <code>CbchedRowSet</code> object's <code>SyncProvider</code>
     * to commit the chbnges when <code>bcceptChbnges()</code> is cblled. If
     * set to fblse, the chbnges will <b>not</b> be committed until one of the
     * <code>CbchedRowSet</code> interfbce trbnsbction methods is cblled.
     *
     * @deprecbted Becbuse this field is finbl (it is pbrt of bn interfbce),
     *  its vblue cbnnot be chbnged.
     * @see #commit
     * @see #rollbbck
     */
    @Deprecbted
    public stbtic finbl boolebn COMMIT_ON_ACCEPT_CHANGES = true;

    /**
     * Notifies registered listeners thbt b RowSet object in the given RowSetEvent
     * object hbs populbted b number of bdditionbl rows. The <code>numRows</code> pbrbmeter
     * ensures thbt this event will only be fired every <code>numRow</code>.
     * <p>
     * The source of the event cbn be retrieved with the method event.getSource.
     *
     * @pbrbm event b <code>RowSetEvent</code> object thbt contbins the
     *     <code>RowSet</code> object thbt is the source of the events
     * @pbrbm numRows when populbting, the number of rows intervbl on which the
     *     <code>CbchedRowSet</code> populbted should fire; the defbult vblue
     *     is zero; cbnnot be less thbn <code>fetchSize</code> or zero
     * @throws SQLException {@code numRows < 0 or numRows < getFetchSize() }
     */
    public void rowSetPopulbted(RowSetEvent event, int numRows) throws SQLException;

    /**
     * Populbtes this <code>CbchedRowSet</code> object with dbtb from
     * the given <code>ResultSet</code> object. While relbted to the <code>populbte(ResultSet)</code>
     * method, bn bdditionbl pbrbmeter is provided to bllow stbrting position within
     * the <code>ResultSet</code> from where to populbte the CbchedRowSet
     * instbnce.
     * <P>
     * This method cbn be used bs bn blternbtive to the <code>execute</code> method when bn
     * bpplicbtion hbs b connection to bn open <code>ResultSet</code> object.
     * Using the method <code>populbte</code> cbn be more efficient thbn using
     * the version of the <code>execute</code> method thbt tbkes no pbrbmeters
     * becbuse it does not open b new connection bnd re-execute this
     * <code>CbchedRowSet</code> object's commbnd. Using the <code>populbte</code>
     *  method is more b mbtter of convenience when compbred to using the version
     * of <code>execute</code> thbt tbkes b <code>ResultSet</code> object.
     *
     * @pbrbm stbrtRow the position in the <code>ResultSet</code> from where to stbrt
     *                populbting the records in this <code>CbchedRowSet</code>
     * @pbrbm rs the <code>ResultSet</code> object contbining the dbtb
     * to be rebd into this <code>CbchedRowSet</code> object
     * @throws SQLException if b null <code>ResultSet</code> object is supplied
     * or this <code>CbchedRowSet</code> object cbnnot
     * retrieve the bssocibted <code>ResultSetMetbDbtb</code> object
     * @see #execute
     * @see #populbte(ResultSet)
     * @see jbvb.sql.ResultSet
     * @see jbvb.sql.ResultSetMetbDbtb
    */
    public void populbte(ResultSet rs, int stbrtRow) throws SQLException;

    /**
     * Sets the <code>CbchedRowSet</code> object's pbge-size. A <code>CbchedRowSet</code>
     * mby be configured to populbte itself in pbge-size sized bbtches of rows. When
     * either <code>populbte()</code> or <code>execute()</code> bre cblled, the
     * <code>CbchedRowSet</code> fetches bn bdditionbl pbge bccording to the
     * originbl SQL query used to populbte the RowSet.
     *
     * @pbrbm size the pbge-size of the <code>CbchedRowSet</code>
     * @throws SQLException if bn error occurs setting the <code>CbchedRowSet</code>
     *      pbge size or if the pbge size is less thbn 0.
     */
    public void setPbgeSize(int size) throws SQLException;

    /**
     * Returns the pbge-size for the <code>CbchedRowSet</code> object
     *
     * @return bn <code>int</code> pbge size
     */
    public int getPbgeSize();

    /**
     * Increments the current pbge of the <code>CbchedRowSet</code>. This cbuses
     * the <code>CbchedRowSet</code> implementbtion to fetch the next pbge-size
     * rows bnd populbte the RowSet, if rembining rows rembin within scope of the
     * originbl SQL query used to populbted the RowSet.
     *
     * @return true if more pbges exist; fblse if this is the lbst pbge
     * @throws SQLException if bn error occurs fetching the next pbge, or if this
     *     method is cblled prembturely before populbte or execute.
     */
    public boolebn nextPbge() throws SQLException;

    /**
     * Decrements the current pbge of the <code>CbchedRowSet</code>. This cbuses
     * the <code>CbchedRowSet</code> implementbtion to fetch the previous pbge-size
     * rows bnd populbte the RowSet. The bmount of rows returned in the previous
     * pbge must blwbys rembin within scope of the originbl SQL query used to
     * populbte the RowSet.
     *
     * @return true if the previous pbge is successfully retrieved; fblse if this
     *     is the first pbge.
     * @throws SQLException if bn error occurs fetching the previous pbge, or if
     *     this method is cblled prembturely before populbte or execute.
     */
    public boolebn previousPbge() throws SQLException;

}
