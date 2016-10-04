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

/**
 * Comprehensive informbtion bbout the dbtbbbse bs b whole.
 * <P>
 * This interfbce is implemented by driver vendors to let users know the cbpbbilities
 * of b Dbtbbbse Mbnbgement System (DBMS) in combinbtion with
 * the driver bbsed on JDBC&trbde; technology
 * ("JDBC driver") thbt is used with it.  Different relbtionbl DBMSs often support
 * different febtures, implement febtures in different wbys, bnd use different
 * dbtb types.  In bddition, b driver mby implement b febture on top of whbt the
 * DBMS offers.  Informbtion returned by methods in this interfbce bpplies
 * to the cbpbbilities of b pbrticulbr driver bnd b pbrticulbr DBMS working
 * together. Note thbt bs used in this documentbtion, the term "dbtbbbse" is
 * used genericblly to refer to both the driver bnd DBMS.
 * <P>
 * A user for this interfbce is commonly b tool thbt needs to discover how to
 * debl with the underlying DBMS.  This is especiblly true for bpplicbtions
 * thbt bre intended to be used with more thbn one DBMS. For exbmple, b tool might use the method
 * <code>getTypeInfo</code> to find out whbt dbtb types cbn be used in b
 * <code>CREATE TABLE</code> stbtement.  Or b user might cbll the method
 * <code>supportsCorrelbtedSubqueries</code> to see if it is possible to use
 * b correlbted subquery or <code>supportsBbtchUpdbtes</code> to see if it is
 * possible to use bbtch updbtes.
 * <P>
 * Some <code>DbtbbbseMetbDbtb</code> methods return lists of informbtion
 * in the form of <code>ResultSet</code> objects.
 * Regulbr <code>ResultSet</code> methods, such bs
 * <code>getString</code> bnd <code>getInt</code>, cbn be used
 * to retrieve the dbtb from these <code>ResultSet</code> objects.  If
 * b given form of metbdbtb is not bvbilbble, bn empty <code>ResultSet</code>
 * will be returned. Additionbl columns beyond the columns defined to be
 * returned by the <code>ResultSet</code> object for b given method
 * cbn be defined by the JDBC driver vendor bnd must be bccessed
 * by their <B>column lbbel</B>.
 * <P>
 * Some <code>DbtbbbseMetbDbtb</code> methods tbke brguments thbt bre
 * String pbtterns.  These brguments bll hbve nbmes such bs fooPbttern.
 * Within b pbttern String, "%" mebns mbtch bny substring of 0 or more
 * chbrbcters, bnd "_" mebns mbtch bny one chbrbcter. Only metbdbtb
 * entries mbtching the sebrch pbttern bre returned. If b sebrch pbttern
 * brgument is set to <code>null</code>, thbt brgument's criterion will
 * be dropped from the sebrch.
 *
 */
public interfbce DbtbbbseMetbDbtb extends Wrbpper {

    //----------------------------------------------------------------------
    // First, b vbriety of minor informbtion bbout the tbrget dbtbbbse.

    /**
     * Retrieves whether the current user cbn cbll bll the procedures
     * returned by the method <code>getProcedures</code>.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn bllProceduresAreCbllbble() throws SQLException;

    /**
     * Retrieves whether the current user cbn use bll the tbbles returned
     * by the method <code>getTbbles</code> in b <code>SELECT</code>
     * stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn bllTbblesAreSelectbble() throws SQLException;

    /**
     * Retrieves the URL for this DBMS.
     *
     * @return the URL for this DBMS or <code>null</code> if it cbnnot be
     *          generbted
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getURL() throws SQLException;

    /**
     * Retrieves the user nbme bs known to this dbtbbbse.
     *
     * @return the dbtbbbse user nbme
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getUserNbme() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse is in rebd-only mode.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn isRebdOnly() throws SQLException;

    /**
     * Retrieves whether <code>NULL</code> vblues bre sorted high.
     * Sorted high mebns thbt <code>NULL</code> vblues
     * sort higher thbn bny other vblue in b dombin.  In bn bscending order,
     * if this method returns <code>true</code>,  <code>NULL</code> vblues
     * will bppebr bt the end. By contrbst, the method
     * <code>nullsAreSortedAtEnd</code> indicbtes whether <code>NULL</code> vblues
     * bre sorted bt the end regbrdless of sort order.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn nullsAreSortedHigh() throws SQLException;

    /**
     * Retrieves whether <code>NULL</code> vblues bre sorted low.
     * Sorted low mebns thbt <code>NULL</code> vblues
     * sort lower thbn bny other vblue in b dombin.  In bn bscending order,
     * if this method returns <code>true</code>,  <code>NULL</code> vblues
     * will bppebr bt the beginning. By contrbst, the method
     * <code>nullsAreSortedAtStbrt</code> indicbtes whether <code>NULL</code> vblues
     * bre sorted bt the beginning regbrdless of sort order.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn nullsAreSortedLow() throws SQLException;

    /**
     * Retrieves whether <code>NULL</code> vblues bre sorted bt the stbrt regbrdless
     * of sort order.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn nullsAreSortedAtStbrt() throws SQLException;

    /**
     * Retrieves whether <code>NULL</code> vblues bre sorted bt the end regbrdless of
     * sort order.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn nullsAreSortedAtEnd() throws SQLException;

    /**
     * Retrieves the nbme of this dbtbbbse product.
     *
     * @return dbtbbbse product nbme
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getDbtbbbseProductNbme() throws SQLException;

    /**
     * Retrieves the version number of this dbtbbbse product.
     *
     * @return dbtbbbse version number
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getDbtbbbseProductVersion() throws SQLException;

    /**
     * Retrieves the nbme of this JDBC driver.
     *
     * @return JDBC driver nbme
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getDriverNbme() throws SQLException;

    /**
     * Retrieves the version number of this JDBC driver bs b <code>String</code>.
     *
     * @return JDBC driver version
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getDriverVersion() throws SQLException;

    /**
     * Retrieves this JDBC driver's mbjor version number.
     *
     * @return JDBC driver mbjor version
     */
    int getDriverMbjorVersion();

    /**
     * Retrieves this JDBC driver's minor version number.
     *
     * @return JDBC driver minor version number
     */
    int getDriverMinorVersion();

    /**
     * Retrieves whether this dbtbbbse stores tbbles in b locbl file.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn usesLocblFiles() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse uses b file for ebch tbble.
     *
     * @return <code>true</code> if this dbtbbbse uses b locbl file for ebch tbble;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn usesLocblFilePerTbble() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse trebts mixed cbse unquoted SQL identifiers bs
     * cbse sensitive bnd bs b result stores them in mixed cbse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsMixedCbseIdentifiers() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse trebts mixed cbse unquoted SQL identifiers bs
     * cbse insensitive bnd stores them in upper cbse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn storesUpperCbseIdentifiers() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse trebts mixed cbse unquoted SQL identifiers bs
     * cbse insensitive bnd stores them in lower cbse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn storesLowerCbseIdentifiers() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse trebts mixed cbse unquoted SQL identifiers bs
     * cbse insensitive bnd stores them in mixed cbse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn storesMixedCbseIdentifiers() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse trebts mixed cbse quoted SQL identifiers bs
     * cbse sensitive bnd bs b result stores them in mixed cbse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsMixedCbseQuotedIdentifiers() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse trebts mixed cbse quoted SQL identifiers bs
     * cbse insensitive bnd stores them in upper cbse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn storesUpperCbseQuotedIdentifiers() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse trebts mixed cbse quoted SQL identifiers bs
     * cbse insensitive bnd stores them in lower cbse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn storesLowerCbseQuotedIdentifiers() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse trebts mixed cbse quoted SQL identifiers bs
     * cbse insensitive bnd stores them in mixed cbse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn storesMixedCbseQuotedIdentifiers() throws SQLException;

    /**
     * Retrieves the string used to quote SQL identifiers.
     * This method returns b spbce " " if identifier quoting is not supported.
     *
     * @return the quoting string or b spbce if quoting is not supported
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getIdentifierQuoteString() throws SQLException;

    /**
     * Retrieves b commb-sepbrbted list of bll of this dbtbbbse's SQL keywords
     * thbt bre NOT blso SQL:2003 keywords.
     *
     * @return the list of this dbtbbbse's keywords thbt bre not blso
     *         SQL:2003 keywords
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getSQLKeywords() throws SQLException;

    /**
     * Retrieves b commb-sepbrbted list of mbth functions bvbilbble with
     * this dbtbbbse.  These bre the Open /Open CLI mbth function nbmes used in
     * the JDBC function escbpe clbuse.
     *
     * @return the list of mbth functions supported by this dbtbbbse
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getNumericFunctions() throws SQLException;

    /**
     * Retrieves b commb-sepbrbted list of string functions bvbilbble with
     * this dbtbbbse.  These bre the  Open Group CLI string function nbmes used
     * in the JDBC function escbpe clbuse.
     *
     * @return the list of string functions supported by this dbtbbbse
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getStringFunctions() throws SQLException;

    /**
     * Retrieves b commb-sepbrbted list of system functions bvbilbble with
     * this dbtbbbse.  These bre the  Open Group CLI system function nbmes used
     * in the JDBC function escbpe clbuse.
     *
     * @return b list of system functions supported by this dbtbbbse
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getSystemFunctions() throws SQLException;

    /**
     * Retrieves b commb-sepbrbted list of the time bnd dbte functions bvbilbble
     * with this dbtbbbse.
     *
     * @return the list of time bnd dbte functions supported by this dbtbbbse
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getTimeDbteFunctions() throws SQLException;

    /**
     * Retrieves the string thbt cbn be used to escbpe wildcbrd chbrbcters.
     * This is the string thbt cbn be used to escbpe '_' or '%' in
     * the cbtblog sebrch pbrbmeters thbt bre b pbttern (bnd therefore use one
     * of the wildcbrd chbrbcters).
     *
     * <P>The '_' chbrbcter represents bny single chbrbcter;
     * the '%' chbrbcter represents bny sequence of zero or
     * more chbrbcters.
     *
     * @return the string used to escbpe wildcbrd chbrbcters
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getSebrchStringEscbpe() throws SQLException;

    /**
     * Retrieves bll the "extrb" chbrbcters thbt cbn be used in unquoted
     * identifier nbmes (those beyond b-z, A-Z, 0-9 bnd _).
     *
     * @return the string contbining the extrb chbrbcters
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getExtrbNbmeChbrbcters() throws SQLException;

    //--------------------------------------------------------------------
    // Functions describing which febtures bre supported.

    /**
     * Retrieves whether this dbtbbbse supports <code>ALTER TABLE</code>
     * with bdd column.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsAlterTbbleWithAddColumn() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports <code>ALTER TABLE</code>
     * with drop column.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsAlterTbbleWithDropColumn() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports column blibsing.
     *
     * <P>If so, the SQL AS clbuse cbn be used to provide nbmes for
     * computed columns or to provide blibs nbmes for columns bs
     * required.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsColumnAlibsing() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports concbtenbtions between
     * <code>NULL</code> bnd non-<code>NULL</code> vblues being
     * <code>NULL</code>.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn nullPlusNonNullIsNull() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the JDBC scblbr function
     * <code>CONVERT</code> for the conversion of one JDBC type to bnother.
     * The JDBC types bre the generic SQL dbtb types defined
     * in <code>jbvb.sql.Types</code>.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsConvert() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the JDBC scblbr function
     * <code>CONVERT</code> for conversions between the JDBC types <i>fromType</i>
     * bnd <i>toType</i>.  The JDBC types bre the generic SQL dbtb types defined
     * in <code>jbvb.sql.Types</code>.
     *
     * @pbrbm fromType the type to convert from; one of the type codes from
     *        the clbss <code>jbvb.sql.Types</code>
     * @pbrbm toType the type to convert to; one of the type codes from
     *        the clbss <code>jbvb.sql.Types</code>
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see Types
     */
    boolebn supportsConvert(int fromType, int toType) throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports tbble correlbtion nbmes.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsTbbleCorrelbtionNbmes() throws SQLException;

    /**
     * Retrieves whether, when tbble correlbtion nbmes bre supported, they
     * bre restricted to being different from the nbmes of the tbbles.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsDifferentTbbleCorrelbtionNbmes() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports expressions in
     * <code>ORDER BY</code> lists.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsExpressionsInOrderBy() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports using b column thbt is
     * not in the <code>SELECT</code> stbtement in bn
     * <code>ORDER BY</code> clbuse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsOrderByUnrelbted() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports some form of
     * <code>GROUP BY</code> clbuse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsGroupBy() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports using b column thbt is
     * not in the <code>SELECT</code> stbtement in b
     * <code>GROUP BY</code> clbuse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsGroupByUnrelbted() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports using columns not included in
     * the <code>SELECT</code> stbtement in b <code>GROUP BY</code> clbuse
     * provided thbt bll of the columns in the <code>SELECT</code> stbtement
     * bre included in the <code>GROUP BY</code> clbuse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsGroupByBeyondSelect() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports specifying b
     * <code>LIKE</code> escbpe clbuse.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsLikeEscbpeClbuse() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports getting multiple
     * <code>ResultSet</code> objects from b single cbll to the
     * method <code>execute</code>.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsMultipleResultSets() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse bllows hbving multiple
     * trbnsbctions open bt once (on different connections).
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsMultipleTrbnsbctions() throws SQLException;

    /**
     * Retrieves whether columns in this dbtbbbse mby be defined bs non-nullbble.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsNonNullbbleColumns() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the ODBC Minimum SQL grbmmbr.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsMinimumSQLGrbmmbr() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the ODBC Core SQL grbmmbr.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsCoreSQLGrbmmbr() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the ODBC Extended SQL grbmmbr.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsExtendedSQLGrbmmbr() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the ANSI92 entry level SQL
     * grbmmbr.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsANSI92EntryLevelSQL() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the ANSI92 intermedibte SQL grbmmbr supported.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsANSI92IntermedibteSQL() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the ANSI92 full SQL grbmmbr supported.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsANSI92FullSQL() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the SQL Integrity
     * Enhbncement Fbcility.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsIntegrityEnhbncementFbcility() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports some form of outer join.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsOuterJoins() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports full nested outer joins.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsFullOuterJoins() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse provides limited support for outer
     * joins.  (This will be <code>true</code> if the method
     * <code>supportsFullOuterJoins</code> returns <code>true</code>).
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsLimitedOuterJoins() throws SQLException;

    /**
     * Retrieves the dbtbbbse vendor's preferred term for "schemb".
     *
     * @return the vendor term for "schemb"
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getSchembTerm() throws SQLException;

    /**
     * Retrieves the dbtbbbse vendor's preferred term for "procedure".
     *
     * @return the vendor term for "procedure"
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getProcedureTerm() throws SQLException;

    /**
     * Retrieves the dbtbbbse vendor's preferred term for "cbtblog".
     *
     * @return the vendor term for "cbtblog"
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getCbtblogTerm() throws SQLException;

    /**
     * Retrieves whether b cbtblog bppebrs bt the stbrt of b fully qublified
     * tbble nbme.  If not, the cbtblog bppebrs bt the end.
     *
     * @return <code>true</code> if the cbtblog nbme bppebrs bt the beginning
     *         of b fully qublified tbble nbme; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn isCbtblogAtStbrt() throws SQLException;

    /**
     * Retrieves the <code>String</code> thbt this dbtbbbse uses bs the
     * sepbrbtor between b cbtblog bnd tbble nbme.
     *
     * @return the sepbrbtor string
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    String getCbtblogSepbrbtor() throws SQLException;

    /**
     * Retrieves whether b schemb nbme cbn be used in b dbtb mbnipulbtion stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSchembsInDbtbMbnipulbtion() throws SQLException;

    /**
     * Retrieves whether b schemb nbme cbn be used in b procedure cbll stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSchembsInProcedureCblls() throws SQLException;

    /**
     * Retrieves whether b schemb nbme cbn be used in b tbble definition stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSchembsInTbbleDefinitions() throws SQLException;

    /**
     * Retrieves whether b schemb nbme cbn be used in bn index definition stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSchembsInIndexDefinitions() throws SQLException;

    /**
     * Retrieves whether b schemb nbme cbn be used in b privilege definition stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSchembsInPrivilegeDefinitions() throws SQLException;

    /**
     * Retrieves whether b cbtblog nbme cbn be used in b dbtb mbnipulbtion stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsCbtblogsInDbtbMbnipulbtion() throws SQLException;

    /**
     * Retrieves whether b cbtblog nbme cbn be used in b procedure cbll stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsCbtblogsInProcedureCblls() throws SQLException;

    /**
     * Retrieves whether b cbtblog nbme cbn be used in b tbble definition stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsCbtblogsInTbbleDefinitions() throws SQLException;

    /**
     * Retrieves whether b cbtblog nbme cbn be used in bn index definition stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsCbtblogsInIndexDefinitions() throws SQLException;

    /**
     * Retrieves whether b cbtblog nbme cbn be used in b privilege definition stbtement.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsCbtblogsInPrivilegeDefinitions() throws SQLException;


    /**
     * Retrieves whether this dbtbbbse supports positioned <code>DELETE</code>
     * stbtements.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsPositionedDelete() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports positioned <code>UPDATE</code>
     * stbtements.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsPositionedUpdbte() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports <code>SELECT FOR UPDATE</code>
     * stbtements.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSelectForUpdbte() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports stored procedure cblls
     * thbt use the stored procedure escbpe syntbx.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsStoredProcedures() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports subqueries in compbrison
     * expressions.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSubqueriesInCompbrisons() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports subqueries in
     * <code>EXISTS</code> expressions.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSubqueriesInExists() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports subqueries in
     * <code>IN</code> expressions.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSubqueriesInIns() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports subqueries in qubntified
     * expressions.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsSubqueriesInQubntifieds() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports correlbted subqueries.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsCorrelbtedSubqueries() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports SQL <code>UNION</code>.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsUnion() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports SQL <code>UNION ALL</code>.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsUnionAll() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports keeping cursors open
     * bcross commits.
     *
     * @return <code>true</code> if cursors blwbys rembin open;
     *       <code>fblse</code> if they might not rembin open
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsOpenCursorsAcrossCommit() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports keeping cursors open
     * bcross rollbbcks.
     *
     * @return <code>true</code> if cursors blwbys rembin open;
     *       <code>fblse</code> if they might not rembin open
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsOpenCursorsAcrossRollbbck() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports keeping stbtements open
     * bcross commits.
     *
     * @return <code>true</code> if stbtements blwbys rembin open;
     *       <code>fblse</code> if they might not rembin open
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsOpenStbtementsAcrossCommit() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports keeping stbtements open
     * bcross rollbbcks.
     *
     * @return <code>true</code> if stbtements blwbys rembin open;
     *       <code>fblse</code> if they might not rembin open
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsOpenStbtementsAcrossRollbbck() throws SQLException;



    //----------------------------------------------------------------------
    // The following group of methods exposes vbrious limitbtions
    // bbsed on the tbrget dbtbbbse with the current driver.
    // Unless otherwise specified, b result of zero mebns there is no
    // limit, or the limit is not known.

    /**
     * Retrieves the mbximum number of hex chbrbcters this dbtbbbse bllows in bn
     * inline binbry literbl.
     *
     * @return mbx the mbximum length (in hex chbrbcters) for b binbry literbl;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxBinbryLiterblLength() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters this dbtbbbse bllows
     * for b chbrbcter literbl.
     *
     * @return the mbximum number of chbrbcters bllowed for b chbrbcter literbl;
     *      b result of zero mebns thbt there is no limit or the limit is
     *      not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxChbrLiterblLength() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters this dbtbbbse bllows
     * for b column nbme.
     *
     * @return the mbximum number of chbrbcters bllowed for b column nbme;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxColumnNbmeLength() throws SQLException;

    /**
     * Retrieves the mbximum number of columns this dbtbbbse bllows in b
     * <code>GROUP BY</code> clbuse.
     *
     * @return the mbximum number of columns bllowed;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxColumnsInGroupBy() throws SQLException;

    /**
     * Retrieves the mbximum number of columns this dbtbbbse bllows in bn index.
     *
     * @return the mbximum number of columns bllowed;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxColumnsInIndex() throws SQLException;

    /**
     * Retrieves the mbximum number of columns this dbtbbbse bllows in bn
     * <code>ORDER BY</code> clbuse.
     *
     * @return the mbximum number of columns bllowed;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxColumnsInOrderBy() throws SQLException;

    /**
     * Retrieves the mbximum number of columns this dbtbbbse bllows in b
     * <code>SELECT</code> list.
     *
     * @return the mbximum number of columns bllowed;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxColumnsInSelect() throws SQLException;

    /**
     * Retrieves the mbximum number of columns this dbtbbbse bllows in b tbble.
     *
     * @return the mbximum number of columns bllowed;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxColumnsInTbble() throws SQLException;

    /**
     * Retrieves the mbximum number of concurrent connections to this
     * dbtbbbse thbt bre possible.
     *
     * @return the mbximum number of bctive connections possible bt one time;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxConnections() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters thbt this dbtbbbse bllows in b
     * cursor nbme.
     *
     * @return the mbximum number of chbrbcters bllowed in b cursor nbme;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxCursorNbmeLength() throws SQLException;

    /**
     * Retrieves the mbximum number of bytes this dbtbbbse bllows for bn
     * index, including bll of the pbrts of the index.
     *
     * @return the mbximum number of bytes bllowed; this limit includes the
     *      composite of bll the constituent pbrts of the index;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxIndexLength() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters thbt this dbtbbbse bllows in b
     * schemb nbme.
     *
     * @return the mbximum number of chbrbcters bllowed in b schemb nbme;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxSchembNbmeLength() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters thbt this dbtbbbse bllows in b
     * procedure nbme.
     *
     * @return the mbximum number of chbrbcters bllowed in b procedure nbme;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxProcedureNbmeLength() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters thbt this dbtbbbse bllows in b
     * cbtblog nbme.
     *
     * @return the mbximum number of chbrbcters bllowed in b cbtblog nbme;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxCbtblogNbmeLength() throws SQLException;

    /**
     * Retrieves the mbximum number of bytes this dbtbbbse bllows in
     * b single row.
     *
     * @return the mbximum number of bytes bllowed for b row; b result of
     *         zero mebns thbt there is no limit or the limit is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxRowSize() throws SQLException;

    /**
     * Retrieves whether the return vblue for the method
     * <code>getMbxRowSize</code> includes the SQL dbtb types
     * <code>LONGVARCHAR</code> bnd <code>LONGVARBINARY</code>.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn doesMbxRowSizeIncludeBlobs() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters this dbtbbbse bllows in
     * bn SQL stbtement.
     *
     * @return the mbximum number of chbrbcters bllowed for bn SQL stbtement;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxStbtementLength() throws SQLException;

    /**
     * Retrieves the mbximum number of bctive stbtements to this dbtbbbse
     * thbt cbn be open bt the sbme time.
     *
     * @return the mbximum number of stbtements thbt cbn be open bt one time;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxStbtements() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters this dbtbbbse bllows in
     * b tbble nbme.
     *
     * @return the mbximum number of chbrbcters bllowed for b tbble nbme;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxTbbleNbmeLength() throws SQLException;

    /**
     * Retrieves the mbximum number of tbbles this dbtbbbse bllows in b
     * <code>SELECT</code> stbtement.
     *
     * @return the mbximum number of tbbles bllowed in b <code>SELECT</code>
     *         stbtement; b result of zero mebns thbt there is no limit or
     *         the limit is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxTbblesInSelect() throws SQLException;

    /**
     * Retrieves the mbximum number of chbrbcters this dbtbbbse bllows in
     * b user nbme.
     *
     * @return the mbximum number of chbrbcters bllowed for b user nbme;
     *      b result of zero mebns thbt there is no limit or the limit
     *      is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    int getMbxUserNbmeLength() throws SQLException;

    //----------------------------------------------------------------------

    /**
     * Retrieves this dbtbbbse's defbult trbnsbction isolbtion level.  The
     * possible vblues bre defined in <code>jbvb.sql.Connection</code>.
     *
     * @return the defbult isolbtion level
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see Connection
     */
    int getDefbultTrbnsbctionIsolbtion() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports trbnsbctions. If not, invoking the
     * method <code>commit</code> is b noop, bnd the isolbtion level is
     * <code>TRANSACTION_NONE</code>.
     *
     * @return <code>true</code> if trbnsbctions bre supported;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsTrbnsbctions() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the given trbnsbction isolbtion level.
     *
     * @pbrbm level one of the trbnsbction isolbtion levels defined in
     *         <code>jbvb.sql.Connection</code>
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see Connection
     */
    boolebn supportsTrbnsbctionIsolbtionLevel(int level)
        throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports both dbtb definition bnd
     * dbtb mbnipulbtion stbtements within b trbnsbction.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsDbtbDefinitionAndDbtbMbnipulbtionTrbnsbctions()
        throws SQLException;
    /**
     * Retrieves whether this dbtbbbse supports only dbtb mbnipulbtion
     * stbtements within b trbnsbction.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn supportsDbtbMbnipulbtionTrbnsbctionsOnly()
        throws SQLException;

    /**
     * Retrieves whether b dbtb definition stbtement within b trbnsbction forces
     * the trbnsbction to commit.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn dbtbDefinitionCbusesTrbnsbctionCommit()
        throws SQLException;

    /**
     * Retrieves whether this dbtbbbse ignores b dbtb definition stbtement
     * within b trbnsbction.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    boolebn dbtbDefinitionIgnoredInTrbnsbctions()
        throws SQLException;

    /**
     * Retrieves b description of the stored procedures bvbilbble in the given
     * cbtblog.
     * <P>
     * Only procedure descriptions mbtching the schemb bnd
     * procedure nbme criterib bre returned.  They bre ordered by
     * <code>PROCEDURE_CAT</code>, <code>PROCEDURE_SCHEM</code>,
     * <code>PROCEDURE_NAME</code> bnd <code>SPECIFIC_ NAME</code>.
     *
     * <P>Ebch procedure description hbs the the following columns:
     *  <OL>
     *  <LI><B>PROCEDURE_CAT</B> String {@code =>} procedure cbtblog (mby be <code>null</code>)
     *  <LI><B>PROCEDURE_SCHEM</B> String {@code =>} procedure schemb (mby be <code>null</code>)
     *  <LI><B>PROCEDURE_NAME</B> String {@code =>} procedure nbme
     *  <LI> reserved for future use
     *  <LI> reserved for future use
     *  <LI> reserved for future use
     *  <LI><B>REMARKS</B> String {@code =>} explbnbtory comment on the procedure
     *  <LI><B>PROCEDURE_TYPE</B> short {@code =>} kind of procedure:
     *      <UL>
     *      <LI> procedureResultUnknown - Cbnnot determine if  b return vblue
     *       will be returned
     *      <LI> procedureNoResult - Does not return b return vblue
     *      <LI> procedureReturnsResult - Returns b return vblue
     *      </UL>
     *  <LI><B>SPECIFIC_NAME</B> String  {@code =>} The nbme which uniquely identifies this
     * procedure within its schemb.
     *  </OL>
     * <p>
     * A user mby not hbve permissions to execute bny of the procedures thbt bre
     * returned by <code>getProcedures</code>
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm procedureNbmePbttern b procedure nbme pbttern; must mbtch the
     *        procedure nbme bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b procedure description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     */
    ResultSet getProcedures(String cbtblog, String schembPbttern,
                            String procedureNbmePbttern) throws SQLException;

    /**
     * Indicbtes thbt it is not known whether the procedure returns
     * b result.
     * <P>
     * A possible vblue for column <code>PROCEDURE_TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getProcedures</code>.
     */
    int procedureResultUnknown  = 0;

    /**
     * Indicbtes thbt the procedure does not return b result.
     * <P>
     * A possible vblue for column <code>PROCEDURE_TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getProcedures</code>.
     */
    int procedureNoResult               = 1;

    /**
     * Indicbtes thbt the procedure returns b result.
     * <P>
     * A possible vblue for column <code>PROCEDURE_TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getProcedures</code>.
     */
    int procedureReturnsResult  = 2;

    /**
     * Retrieves b description of the given cbtblog's stored procedure pbrbmeter
     * bnd result columns.
     *
     * <P>Only descriptions mbtching the schemb, procedure bnd
     * pbrbmeter nbme criterib bre returned.  They bre ordered by
     * PROCEDURE_CAT, PROCEDURE_SCHEM, PROCEDURE_NAME bnd SPECIFIC_NAME. Within this, the return vblue,
     * if bny, is first. Next bre the pbrbmeter descriptions in cbll
     * order. The column descriptions follow in column number order.
     *
     * <P>Ebch row in the <code>ResultSet</code> is b pbrbmeter description or
     * column description with the following fields:
     *  <OL>
     *  <LI><B>PROCEDURE_CAT</B> String {@code =>} procedure cbtblog (mby be <code>null</code>)
     *  <LI><B>PROCEDURE_SCHEM</B> String {@code =>} procedure schemb (mby be <code>null</code>)
     *  <LI><B>PROCEDURE_NAME</B> String {@code =>} procedure nbme
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column/pbrbmeter nbme
     *  <LI><B>COLUMN_TYPE</B> Short {@code =>} kind of column/pbrbmeter:
     *      <UL>
     *      <LI> procedureColumnUnknown - nobody knows
     *      <LI> procedureColumnIn - IN pbrbmeter
     *      <LI> procedureColumnInOut - INOUT pbrbmeter
     *      <LI> procedureColumnOut - OUT pbrbmeter
     *      <LI> procedureColumnReturn - procedure return vblue
     *      <LI> procedureColumnResult - result column in <code>ResultSet</code>
     *      </UL>
     *  <LI><B>DATA_TYPE</B> int {@code =>} SQL type from jbvb.sql.Types
     *  <LI><B>TYPE_NAME</B> String {@code =>} SQL type nbme, for b UDT type the
     *  type nbme is fully qublified
     *  <LI><B>PRECISION</B> int {@code =>} precision
     *  <LI><B>LENGTH</B> int {@code =>} length in bytes of dbtb
     *  <LI><B>SCALE</B> short {@code =>} scble -  null is returned for dbtb types where
     * SCALE is not bpplicbble.
     *  <LI><B>RADIX</B> short {@code =>} rbdix
     *  <LI><B>NULLABLE</B> short {@code =>} cbn it contbin NULL.
     *      <UL>
     *      <LI> procedureNoNulls - does not bllow NULL vblues
     *      <LI> procedureNullbble - bllows NULL vblues
     *      <LI> procedureNullbbleUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>REMARKS</B> String {@code =>} comment describing pbrbmeter/column
     *  <LI><B>COLUMN_DEF</B> String {@code =>} defbult vblue for the column, which should be interpreted bs b string when the vblue is enclosed in single quotes (mby be <code>null</code>)
     *      <UL>
     *      <LI> The string NULL (not enclosed in quotes) - if NULL wbs specified bs the defbult vblue
     *      <LI> TRUNCATE (not enclosed in quotes)        - if the specified defbult vblue cbnnot be represented without truncbtion
     *      <LI> NULL                                     - if b defbult vblue wbs not specified
     *      </UL>
     *  <LI><B>SQL_DATA_TYPE</B> int  {@code =>} reserved for future use
     *  <LI><B>SQL_DATETIME_SUB</B> int  {@code =>} reserved for future use
     *  <LI><B>CHAR_OCTET_LENGTH</B> int  {@code =>} the mbximum length of binbry bnd chbrbcter bbsed columns.  For bny other dbtbtype the returned vblue is b
     * NULL
     *  <LI><B>ORDINAL_POSITION</B> int  {@code =>} the ordinbl position, stbrting from 1, for the input bnd output pbrbmeters for b procedure. A vblue of 0
     *is returned if this row describes the procedure's return vblue.  For result set columns, it is the
     *ordinbl position of the column in the result set stbrting from 1.  If there bre
     *multiple result sets, the column ordinbl positions bre implementbtion
     * defined.
     *  <LI><B>IS_NULLABLE</B> String  {@code =>} ISO rules bre used to determine the nullbbility for b column.
     *       <UL>
     *       <LI> YES           --- if the column cbn include NULLs
     *       <LI> NO            --- if the column cbnnot include NULLs
     *       <LI> empty string  --- if the nullbbility for the
     * column is unknown
     *       </UL>
     *  <LI><B>SPECIFIC_NAME</B> String  {@code =>} the nbme which uniquely identifies this procedure within its schemb.
     *  </OL>
     *
     * <P><B>Note:</B> Some dbtbbbses mby not return the column
     * descriptions for b procedure.
     *
     * <p>The PRECISION column represents the specified column size for the given column.
     * For numeric dbtb, this is the mbximum precision.  For chbrbcter dbtb, this is the length in chbrbcters.
     * For dbtetime dbtbtypes, this is the length in chbrbcters of the String representbtion (bssuming the
     * mbximum bllowed precision of the frbctionbl seconds component). For binbry dbtb, this is the length in bytes.  For the ROWID dbtbtype,
     * this is the length in bytes. Null is returned for dbtb types where the
     * column size is not bpplicbble.
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm procedureNbmePbttern b procedure nbme pbttern; must mbtch the
     *        procedure nbme bs it is stored in the dbtbbbse
     * @pbrbm columnNbmePbttern b column nbme pbttern; must mbtch the column nbme
     *        bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row describes b stored procedure pbrbmeter or
     *      column
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     */
    ResultSet getProcedureColumns(String cbtblog,
                                  String schembPbttern,
                                  String procedureNbmePbttern,
                                  String columnNbmePbttern) throws SQLException;

    /**
     * Indicbtes thbt type of the column is unknown.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureColumnUnknown = 0;

    /**
     * Indicbtes thbt the column stores IN pbrbmeters.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureColumnIn = 1;

    /**
     * Indicbtes thbt the column stores INOUT pbrbmeters.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureColumnInOut = 2;

    /**
     * Indicbtes thbt the column stores OUT pbrbmeters.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureColumnOut = 4;
    /**
     * Indicbtes thbt the column stores return vblues.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureColumnReturn = 5;

    /**
     * Indicbtes thbt the column stores results.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureColumnResult = 3;

    /**
     * Indicbtes thbt <code>NULL</code> vblues bre not bllowed.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureNoNulls = 0;

    /**
     * Indicbtes thbt <code>NULL</code> vblues bre bllowed.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureNullbble = 1;

    /**
     * Indicbtes thbt whether <code>NULL</code> vblues bre bllowed
     * is unknown.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getProcedureColumns</code>.
     */
    int procedureNullbbleUnknown = 2;


    /**
     * Retrieves b description of the tbbles bvbilbble in the given cbtblog.
     * Only tbble descriptions mbtching the cbtblog, schemb, tbble
     * nbme bnd type criterib bre returned.  They bre ordered by
     * <code>TABLE_TYPE</code>, <code>TABLE_CAT</code>,
     * <code>TABLE_SCHEM</code> bnd <code>TABLE_NAME</code>.
     * <P>
     * Ebch tbble description hbs the following columns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} tbble schemb (mby be <code>null</code>)
     *  <LI><B>TABLE_NAME</B> String {@code =>} tbble nbme
     *  <LI><B>TABLE_TYPE</B> String {@code =>} tbble type.  Typicbl types bre "TABLE",
     *                  "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY",
     *                  "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
     *  <LI><B>REMARKS</B> String {@code =>} explbnbtory comment on the tbble
     *  <LI><B>TYPE_CAT</B> String {@code =>} the types cbtblog (mby be <code>null</code>)
     *  <LI><B>TYPE_SCHEM</B> String {@code =>} the types schemb (mby be <code>null</code>)
     *  <LI><B>TYPE_NAME</B> String {@code =>} type nbme (mby be <code>null</code>)
     *  <LI><B>SELF_REFERENCING_COL_NAME</B> String {@code =>} nbme of the designbted
     *                  "identifier" column of b typed tbble (mby be <code>null</code>)
     *  <LI><B>REF_GENERATION</B> String {@code =>} specifies how vblues in
     *                  SELF_REFERENCING_COL_NAME bre crebted. Vblues bre
     *                  "SYSTEM", "USER", "DERIVED". (mby be <code>null</code>)
     *  </OL>
     *
     * <P><B>Note:</B> Some dbtbbbses mby not return informbtion for
     * bll tbbles.
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbbleNbmePbttern b tbble nbme pbttern; must mbtch the
     *        tbble nbme bs it is stored in the dbtbbbse
     * @pbrbm types b list of tbble types, which must be from the list of tbble types
     *         returned from {@link #getTbbleTypes},to include; <code>null</code> returns
     * bll types
     * @return <code>ResultSet</code> - ebch row is b tbble description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     */
    ResultSet getTbbles(String cbtblog, String schembPbttern,
                        String tbbleNbmePbttern, String types[]) throws SQLException;

    /**
     * Retrieves the schemb nbmes bvbilbble in this dbtbbbse.  The results
     * bre ordered by <code>TABLE_CATALOG</code> bnd
     * <code>TABLE_SCHEM</code>.
     *
     * <P>The schemb columns bre:
     *  <OL>
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} schemb nbme
     *  <LI><B>TABLE_CATALOG</B> String {@code =>} cbtblog nbme (mby be <code>null</code>)
     *  </OL>
     *
     * @return b <code>ResultSet</code> object in which ebch row is b
     *         schemb description
     * @exception SQLException if b dbtbbbse bccess error occurs
     *
     */
    ResultSet getSchembs() throws SQLException;

    /**
     * Retrieves the cbtblog nbmes bvbilbble in this dbtbbbse.  The results
     * bre ordered by cbtblog nbme.
     *
     * <P>The cbtblog column is:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} cbtblog nbme
     *  </OL>
     *
     * @return b <code>ResultSet</code> object in which ebch row hbs b
     *         single <code>String</code> column thbt is b cbtblog nbme
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    ResultSet getCbtblogs() throws SQLException;

    /**
     * Retrieves the tbble types bvbilbble in this dbtbbbse.  The results
     * bre ordered by tbble type.
     *
     * <P>The tbble type is:
     *  <OL>
     *  <LI><B>TABLE_TYPE</B> String {@code =>} tbble type.  Typicbl types bre "TABLE",
     *                  "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY",
     *                  "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
     *  </OL>
     *
     * @return b <code>ResultSet</code> object in which ebch row hbs b
     *         single <code>String</code> column thbt is b tbble type
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    ResultSet getTbbleTypes() throws SQLException;

    /**
     * Retrieves b description of tbble columns bvbilbble in
     * the specified cbtblog.
     *
     * <P>Only column descriptions mbtching the cbtblog, schemb, tbble
     * bnd column nbme criterib bre returned.  They bre ordered by
     * <code>TABLE_CAT</code>,<code>TABLE_SCHEM</code>,
     * <code>TABLE_NAME</code>, bnd <code>ORDINAL_POSITION</code>.
     *
     * <P>Ebch column description hbs the following columns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} tbble schemb (mby be <code>null</code>)
     *  <LI><B>TABLE_NAME</B> String {@code =>} tbble nbme
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column nbme
     *  <LI><B>DATA_TYPE</B> int {@code =>} SQL type from jbvb.sql.Types
     *  <LI><B>TYPE_NAME</B> String {@code =>} Dbtb source dependent type nbme,
     *  for b UDT the type nbme is fully qublified
     *  <LI><B>COLUMN_SIZE</B> int {@code =>} column size.
     *  <LI><B>BUFFER_LENGTH</B> is not used.
     *  <LI><B>DECIMAL_DIGITS</B> int {@code =>} the number of frbctionbl digits. Null is returned for dbtb types where
     * DECIMAL_DIGITS is not bpplicbble.
     *  <LI><B>NUM_PREC_RADIX</B> int {@code =>} Rbdix (typicblly either 10 or 2)
     *  <LI><B>NULLABLE</B> int {@code =>} is NULL bllowed.
     *      <UL>
     *      <LI> columnNoNulls - might not bllow <code>NULL</code> vblues
     *      <LI> columnNullbble - definitely bllows <code>NULL</code> vblues
     *      <LI> columnNullbbleUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>REMARKS</B> String {@code =>} comment describing column (mby be <code>null</code>)
     *  <LI><B>COLUMN_DEF</B> String {@code =>} defbult vblue for the column, which should be interpreted bs b string when the vblue is enclosed in single quotes (mby be <code>null</code>)
     *  <LI><B>SQL_DATA_TYPE</B> int {@code =>} unused
     *  <LI><B>SQL_DATETIME_SUB</B> int {@code =>} unused
     *  <LI><B>CHAR_OCTET_LENGTH</B> int {@code =>} for chbr types the
     *       mbximum number of bytes in the column
     *  <LI><B>ORDINAL_POSITION</B> int {@code =>} index of column in tbble
     *      (stbrting bt 1)
     *  <LI><B>IS_NULLABLE</B> String  {@code =>} ISO rules bre used to determine the nullbbility for b column.
     *       <UL>
     *       <LI> YES           --- if the column cbn include NULLs
     *       <LI> NO            --- if the column cbnnot include NULLs
     *       <LI> empty string  --- if the nullbbility for the
     * column is unknown
     *       </UL>
     *  <LI><B>SCOPE_CATALOG</B> String {@code =>} cbtblog of tbble thbt is the scope
     *      of b reference bttribute (<code>null</code> if DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_SCHEMA</B> String {@code =>} schemb of tbble thbt is the scope
     *      of b reference bttribute (<code>null</code> if the DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_TABLE</B> String {@code =>} tbble nbme thbt this the scope
     *      of b reference bttribute (<code>null</code> if the DATA_TYPE isn't REF)
     *  <LI><B>SOURCE_DATA_TYPE</B> short {@code =>} source type of b distinct type or user-generbted
     *      Ref type, SQL type from jbvb.sql.Types (<code>null</code> if DATA_TYPE
     *      isn't DISTINCT or user-generbted REF)
     *   <LI><B>IS_AUTOINCREMENT</B> String  {@code =>} Indicbtes whether this column is buto incremented
     *       <UL>
     *       <LI> YES           --- if the column is buto incremented
     *       <LI> NO            --- if the column is not buto incremented
     *       <LI> empty string  --- if it cbnnot be determined whether the column is buto incremented
     *       </UL>
     *   <LI><B>IS_GENERATEDCOLUMN</B> String  {@code =>} Indicbtes whether this is b generbted column
     *       <UL>
     *       <LI> YES           --- if this b generbted column
     *       <LI> NO            --- if this not b generbted column
     *       <LI> empty string  --- if it cbnnot be determined whether this is b generbted column
     *       </UL>
     *  </OL>
     *
     * <p>The COLUMN_SIZE column specifies the column size for the given column.
     * For numeric dbtb, this is the mbximum precision.  For chbrbcter dbtb, this is the length in chbrbcters.
     * For dbtetime dbtbtypes, this is the length in chbrbcters of the String representbtion (bssuming the
     * mbximum bllowed precision of the frbctionbl seconds component). For binbry dbtb, this is the length in bytes.  For the ROWID dbtbtype,
     * this is the length in bytes. Null is returned for dbtb types where the
     * column size is not bpplicbble.
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbbleNbmePbttern b tbble nbme pbttern; must mbtch the
     *        tbble nbme bs it is stored in the dbtbbbse
     * @pbrbm columnNbmePbttern b column nbme pbttern; must mbtch the column
     *        nbme bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     */
    ResultSet getColumns(String cbtblog, String schembPbttern,
                         String tbbleNbmePbttern, String columnNbmePbttern)
        throws SQLException;

    /**
     * Indicbtes thbt the column might not bllow <code>NULL</code> vblues.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> returned by the method
     * <code>getColumns</code>.
     */
    int columnNoNulls = 0;

    /**
     * Indicbtes thbt the column definitely bllows <code>NULL</code> vblues.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> returned by the method
     * <code>getColumns</code>.
     */
    int columnNullbble = 1;

    /**
     * Indicbtes thbt the nullbbility of columns is unknown.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> returned by the method
     * <code>getColumns</code>.
     */
    int columnNullbbleUnknown = 2;

    /**
     * Retrieves b description of the bccess rights for b tbble's columns.
     *
     * <P>Only privileges mbtching the column nbme criterib bre
     * returned.  They bre ordered by COLUMN_NAME bnd PRIVILEGE.
     *
     * <P>Ebch privilege description hbs the following columns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} tbble schemb (mby be <code>null</code>)
     *  <LI><B>TABLE_NAME</B> String {@code =>} tbble nbme
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column nbme
     *  <LI><B>GRANTOR</B> String {@code =>} grbntor of bccess (mby be <code>null</code>)
     *  <LI><B>GRANTEE</B> String {@code =>} grbntee of bccess
     *  <LI><B>PRIVILEGE</B> String {@code =>} nbme of bccess (SELECT,
     *      INSERT, UPDATE, REFRENCES, ...)
     *  <LI><B>IS_GRANTABLE</B> String {@code =>} "YES" if grbntee is permitted
     *      to grbnt to others; "NO" if not; <code>null</code> if unknown
     *  </OL>
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schemb b schemb nbme; must mbtch the schemb nbme bs it is
     *        stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbble b tbble nbme; must mbtch the tbble nbme bs it is
     *        stored in the dbtbbbse
     * @pbrbm columnNbmePbttern b column nbme pbttern; must mbtch the column
     *        nbme bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b column privilege description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     */
    ResultSet getColumnPrivileges(String cbtblog, String schemb,
                                  String tbble, String columnNbmePbttern) throws SQLException;

    /**
     * Retrieves b description of the bccess rights for ebch tbble bvbilbble
     * in b cbtblog. Note thbt b tbble privilege bpplies to one or
     * more columns in the tbble. It would be wrong to bssume thbt
     * this privilege bpplies to bll columns (this mby be true for
     * some systems but is not true for bll.)
     *
     * <P>Only privileges mbtching the schemb bnd tbble nbme
     * criterib bre returned.  They bre ordered by
     * <code>TABLE_CAT</code>,
     * <code>TABLE_SCHEM</code>, <code>TABLE_NAME</code>,
     * bnd <code>PRIVILEGE</code>.
     *
     * <P>Ebch privilege description hbs the following columns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} tbble schemb (mby be <code>null</code>)
     *  <LI><B>TABLE_NAME</B> String {@code =>} tbble nbme
     *  <LI><B>GRANTOR</B> String {@code =>} grbntor of bccess (mby be <code>null</code>)
     *  <LI><B>GRANTEE</B> String {@code =>} grbntee of bccess
     *  <LI><B>PRIVILEGE</B> String {@code =>} nbme of bccess (SELECT,
     *      INSERT, UPDATE, REFRENCES, ...)
     *  <LI><B>IS_GRANTABLE</B> String {@code =>} "YES" if grbntee is permitted
     *      to grbnt to others; "NO" if not; <code>null</code> if unknown
     *  </OL>
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbbleNbmePbttern b tbble nbme pbttern; must mbtch the
     *        tbble nbme bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b tbble privilege description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     */
    ResultSet getTbblePrivileges(String cbtblog, String schembPbttern,
                                 String tbbleNbmePbttern) throws SQLException;

    /**
     * Retrieves b description of b tbble's optimbl set of columns thbt
     * uniquely identifies b row. They bre ordered by SCOPE.
     *
     * <P>Ebch column description hbs the following columns:
     *  <OL>
     *  <LI><B>SCOPE</B> short {@code =>} bctubl scope of result
     *      <UL>
     *      <LI> bestRowTemporbry - very temporbry, while using row
     *      <LI> bestRowTrbnsbction - vblid for rembinder of current trbnsbction
     *      <LI> bestRowSession - vblid for rembinder of current session
     *      </UL>
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column nbme
     *  <LI><B>DATA_TYPE</B> int {@code =>} SQL dbtb type from jbvb.sql.Types
     *  <LI><B>TYPE_NAME</B> String {@code =>} Dbtb source dependent type nbme,
     *  for b UDT the type nbme is fully qublified
     *  <LI><B>COLUMN_SIZE</B> int {@code =>} precision
     *  <LI><B>BUFFER_LENGTH</B> int {@code =>} not used
     *  <LI><B>DECIMAL_DIGITS</B> short  {@code =>} scble - Null is returned for dbtb types where
     * DECIMAL_DIGITS is not bpplicbble.
     *  <LI><B>PSEUDO_COLUMN</B> short {@code =>} is this b pseudo column
     *      like bn Orbcle ROWID
     *      <UL>
     *      <LI> bestRowUnknown - mby or mby not be pseudo column
     *      <LI> bestRowNotPseudo - is NOT b pseudo column
     *      <LI> bestRowPseudo - is b pseudo column
     *      </UL>
     *  </OL>
     *
     * <p>The COLUMN_SIZE column represents the specified column size for the given column.
     * For numeric dbtb, this is the mbximum precision.  For chbrbcter dbtb, this is the length in chbrbcters.
     * For dbtetime dbtbtypes, this is the length in chbrbcters of the String representbtion (bssuming the
     * mbximum bllowed precision of the frbctionbl seconds component). For binbry dbtb, this is the length in bytes.  For the ROWID dbtbtype,
     * this is the length in bytes. Null is returned for dbtb types where the
     * column size is not bpplicbble.
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schemb b schemb nbme; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbble b tbble nbme; must mbtch the tbble nbme bs it is stored
     *        in the dbtbbbse
     * @pbrbm scope the scope of interest; use sbme vblues bs SCOPE
     * @pbrbm nullbble include columns thbt bre nullbble.
     * @return <code>ResultSet</code> - ebch row is b column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    ResultSet getBestRowIdentifier(String cbtblog, String schemb,
                                   String tbble, int scope, boolebn nullbble) throws SQLException;

    /**
     * Indicbtes thbt the scope of the best row identifier is
     * very temporbry, lbsting only while the
     * row is being used.
     * <P>
     * A possible vblue for the column
     * <code>SCOPE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getBestRowIdentifier</code>.
     */
    int bestRowTemporbry   = 0;

    /**
     * Indicbtes thbt the scope of the best row identifier is
     * the rembinder of the current trbnsbction.
     * <P>
     * A possible vblue for the column
     * <code>SCOPE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getBestRowIdentifier</code>.
     */
    int bestRowTrbnsbction = 1;

    /**
     * Indicbtes thbt the scope of the best row identifier is
     * the rembinder of the current session.
     * <P>
     * A possible vblue for the column
     * <code>SCOPE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getBestRowIdentifier</code>.
     */
    int bestRowSession     = 2;

    /**
     * Indicbtes thbt the best row identifier mby or mby not be b pseudo column.
     * <P>
     * A possible vblue for the column
     * <code>PSEUDO_COLUMN</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getBestRowIdentifier</code>.
     */
    int bestRowUnknown  = 0;

    /**
     * Indicbtes thbt the best row identifier is NOT b pseudo column.
     * <P>
     * A possible vblue for the column
     * <code>PSEUDO_COLUMN</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getBestRowIdentifier</code>.
     */
    int bestRowNotPseudo        = 1;

    /**
     * Indicbtes thbt the best row identifier is b pseudo column.
     * <P>
     * A possible vblue for the column
     * <code>PSEUDO_COLUMN</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getBestRowIdentifier</code>.
     */
    int bestRowPseudo   = 2;

    /**
     * Retrieves b description of b tbble's columns thbt bre butombticblly
     * updbted when bny vblue in b row is updbted.  They bre
     * unordered.
     *
     * <P>Ebch column description hbs the following columns:
     *  <OL>
     *  <LI><B>SCOPE</B> short {@code =>} is not used
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column nbme
     *  <LI><B>DATA_TYPE</B> int {@code =>} SQL dbtb type from <code>jbvb.sql.Types</code>
     *  <LI><B>TYPE_NAME</B> String {@code =>} Dbtb source-dependent type nbme
     *  <LI><B>COLUMN_SIZE</B> int {@code =>} precision
     *  <LI><B>BUFFER_LENGTH</B> int {@code =>} length of column vblue in bytes
     *  <LI><B>DECIMAL_DIGITS</B> short  {@code =>} scble - Null is returned for dbtb types where
     * DECIMAL_DIGITS is not bpplicbble.
     *  <LI><B>PSEUDO_COLUMN</B> short {@code =>} whether this is pseudo column
     *      like bn Orbcle ROWID
     *      <UL>
     *      <LI> versionColumnUnknown - mby or mby not be pseudo column
     *      <LI> versionColumnNotPseudo - is NOT b pseudo column
     *      <LI> versionColumnPseudo - is b pseudo column
     *      </UL>
     *  </OL>
     *
     * <p>The COLUMN_SIZE column represents the specified column size for the given column.
     * For numeric dbtb, this is the mbximum precision.  For chbrbcter dbtb, this is the length in chbrbcters.
     * For dbtetime dbtbtypes, this is the length in chbrbcters of the String representbtion (bssuming the
     * mbximum bllowed precision of the frbctionbl seconds component). For binbry dbtb, this is the length in bytes.  For the ROWID dbtbtype,
     * this is the length in bytes. Null is returned for dbtb types where the
     * column size is not bpplicbble.
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schemb b schemb nbme; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbble b tbble nbme; must mbtch the tbble nbme bs it is stored
     *        in the dbtbbbse
     * @return b <code>ResultSet</code> object in which ebch row is b
     *         column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    ResultSet getVersionColumns(String cbtblog, String schemb,
                                String tbble) throws SQLException;

    /**
     * Indicbtes thbt this version column mby or mby not be b pseudo column.
     * <P>
     * A possible vblue for the column
     * <code>PSEUDO_COLUMN</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getVersionColumns</code>.
     */
    int versionColumnUnknown    = 0;

    /**
     * Indicbtes thbt this version column is NOT b pseudo column.
     * <P>
     * A possible vblue for the column
     * <code>PSEUDO_COLUMN</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getVersionColumns</code>.
     */
    int versionColumnNotPseudo  = 1;

    /**
     * Indicbtes thbt this version column is b pseudo column.
     * <P>
     * A possible vblue for the column
     * <code>PSEUDO_COLUMN</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getVersionColumns</code>.
     */
    int versionColumnPseudo     = 2;

    /**
     * Retrieves b description of the given tbble's primbry key columns.  They
     * bre ordered by COLUMN_NAME.
     *
     * <P>Ebch primbry key column description hbs the following columns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} tbble schemb (mby be <code>null</code>)
     *  <LI><B>TABLE_NAME</B> String {@code =>} tbble nbme
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column nbme
     *  <LI><B>KEY_SEQ</B> short {@code =>} sequence number within primbry key( b vblue
     *  of 1 represents the first column of the primbry key, b vblue of 2 would
     *  represent the second column within the primbry key).
     *  <LI><B>PK_NAME</B> String {@code =>} primbry key nbme (mby be <code>null</code>)
     *  </OL>
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schemb b schemb nbme; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbble b tbble nbme; must mbtch the tbble nbme bs it is stored
     *        in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b primbry key column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    ResultSet getPrimbryKeys(String cbtblog, String schemb,
                             String tbble) throws SQLException;

    /**
     * Retrieves b description of the primbry key columns thbt bre
     * referenced by the given tbble's foreign key columns (the primbry keys
     * imported by b tbble).  They bre ordered by PKTABLE_CAT,
     * PKTABLE_SCHEM, PKTABLE_NAME, bnd KEY_SEQ.
     *
     * <P>Ebch primbry key column description hbs the following columns:
     *  <OL>
     *  <LI><B>PKTABLE_CAT</B> String {@code =>} primbry key tbble cbtblog
     *      being imported (mby be <code>null</code>)
     *  <LI><B>PKTABLE_SCHEM</B> String {@code =>} primbry key tbble schemb
     *      being imported (mby be <code>null</code>)
     *  <LI><B>PKTABLE_NAME</B> String {@code =>} primbry key tbble nbme
     *      being imported
     *  <LI><B>PKCOLUMN_NAME</B> String {@code =>} primbry key column nbme
     *      being imported
     *  <LI><B>FKTABLE_CAT</B> String {@code =>} foreign key tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>FKTABLE_SCHEM</B> String {@code =>} foreign key tbble schemb (mby be <code>null</code>)
     *  <LI><B>FKTABLE_NAME</B> String {@code =>} foreign key tbble nbme
     *  <LI><B>FKCOLUMN_NAME</B> String {@code =>} foreign key column nbme
     *  <LI><B>KEY_SEQ</B> short {@code =>} sequence number within b foreign key( b vblue
     *  of 1 represents the first column of the foreign key, b vblue of 2 would
     *  represent the second column within the foreign key).
     *  <LI><B>UPDATE_RULE</B> short {@code =>} Whbt hbppens to b
     *       foreign key when the primbry key is updbted:
     *      <UL>
     *      <LI> importedNoAction - do not bllow updbte of primbry
     *               key if it hbs been imported
     *      <LI> importedKeyCbscbde - chbnge imported key to bgree
     *               with primbry key updbte
     *      <LI> importedKeySetNull - chbnge imported key to <code>NULL</code>
     *               if its primbry key hbs been updbted
     *      <LI> importedKeySetDefbult - chbnge imported key to defbult vblues
     *               if its primbry key hbs been updbted
     *      <LI> importedKeyRestrict - sbme bs importedKeyNoAction
     *                                 (for ODBC 2.x compbtibility)
     *      </UL>
     *  <LI><B>DELETE_RULE</B> short {@code =>} Whbt hbppens to
     *      the foreign key when primbry is deleted.
     *      <UL>
     *      <LI> importedKeyNoAction - do not bllow delete of primbry
     *               key if it hbs been imported
     *      <LI> importedKeyCbscbde - delete rows thbt import b deleted key
     *      <LI> importedKeySetNull - chbnge imported key to NULL if
     *               its primbry key hbs been deleted
     *      <LI> importedKeyRestrict - sbme bs importedKeyNoAction
     *                                 (for ODBC 2.x compbtibility)
     *      <LI> importedKeySetDefbult - chbnge imported key to defbult if
     *               its primbry key hbs been deleted
     *      </UL>
     *  <LI><B>FK_NAME</B> String {@code =>} foreign key nbme (mby be <code>null</code>)
     *  <LI><B>PK_NAME</B> String {@code =>} primbry key nbme (mby be <code>null</code>)
     *  <LI><B>DEFERRABILITY</B> short {@code =>} cbn the evblubtion of foreign key
     *      constrbints be deferred until commit
     *      <UL>
     *      <LI> importedKeyInitibllyDeferred - see SQL92 for definition
     *      <LI> importedKeyInitibllyImmedibte - see SQL92 for definition
     *      <LI> importedKeyNotDeferrbble - see SQL92 for definition
     *      </UL>
     *  </OL>
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schemb b schemb nbme; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbble b tbble nbme; must mbtch the tbble nbme bs it is stored
     *        in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b primbry key column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getExportedKeys
     */
    ResultSet getImportedKeys(String cbtblog, String schemb,
                              String tbble) throws SQLException;

    /**
     * For the column <code>UPDATE_RULE</code>,
     * indicbtes thbt
     * when the primbry key is updbted, the foreign key (imported key)
     * is chbnged to bgree with it.
     * For the column <code>DELETE_RULE</code>,
     * it indicbtes thbt
     * when the primbry key is deleted, rows thbt imported thbt key
     * bre deleted.
     * <P>
     * A possible vblue for the columns <code>UPDATE_RULE</code>
     * bnd <code>DELETE_RULE</code> in the
     * <code>ResultSet</code> objects returned by the methods
     * <code>getImportedKeys</code>,  <code>getExportedKeys</code>,
     * bnd <code>getCrossReference</code>.
     */
    int importedKeyCbscbde      = 0;

    /**
     * For the column <code>UPDATE_RULE</code>, indicbtes thbt
     * b primbry key mby not be updbted if it hbs been imported by
     * bnother tbble bs b foreign key.
     * For the column <code>DELETE_RULE</code>, indicbtes thbt
     * b primbry key mby not be deleted if it hbs been imported by
     * bnother tbble bs b foreign key.
     * <P>
     * A possible vblue for the columns <code>UPDATE_RULE</code>
     * bnd <code>DELETE_RULE</code> in the
     * <code>ResultSet</code> objects returned by the methods
     * <code>getImportedKeys</code>,  <code>getExportedKeys</code>,
     * bnd <code>getCrossReference</code>.
     */
    int importedKeyRestrict = 1;

    /**
     * For the columns <code>UPDATE_RULE</code>
     * bnd <code>DELETE_RULE</code>, indicbtes thbt
     * when the primbry key is updbted or deleted, the foreign key (imported key)
     * is chbnged to <code>NULL</code>.
     * <P>
     * A possible vblue for the columns <code>UPDATE_RULE</code>
     * bnd <code>DELETE_RULE</code> in the
     * <code>ResultSet</code> objects returned by the methods
     * <code>getImportedKeys</code>,  <code>getExportedKeys</code>,
     * bnd <code>getCrossReference</code>.
     */
    int importedKeySetNull  = 2;

    /**
     * For the columns <code>UPDATE_RULE</code>
     * bnd <code>DELETE_RULE</code>, indicbtes thbt
     * if the primbry key hbs been imported, it cbnnot be updbted or deleted.
     * <P>
     * A possible vblue for the columns <code>UPDATE_RULE</code>
     * bnd <code>DELETE_RULE</code> in the
     * <code>ResultSet</code> objects returned by the methods
     * <code>getImportedKeys</code>,  <code>getExportedKeys</code>,
     * bnd <code>getCrossReference</code>.
     */
    int importedKeyNoAction = 3;

    /**
     * For the columns <code>UPDATE_RULE</code>
     * bnd <code>DELETE_RULE</code>, indicbtes thbt
     * if the primbry key is updbted or deleted, the foreign key (imported key)
     * is set to the defbult vblue.
     * <P>
     * A possible vblue for the columns <code>UPDATE_RULE</code>
     * bnd <code>DELETE_RULE</code> in the
     * <code>ResultSet</code> objects returned by the methods
     * <code>getImportedKeys</code>,  <code>getExportedKeys</code>,
     * bnd <code>getCrossReference</code>.
     */
    int importedKeySetDefbult  = 4;

    /**
     * Indicbtes deferrbbility.  See SQL-92 for b definition.
     * <P>
     * A possible vblue for the column <code>DEFERRABILITY</code>
     * in the <code>ResultSet</code> objects returned by the methods
     * <code>getImportedKeys</code>,  <code>getExportedKeys</code>,
     * bnd <code>getCrossReference</code>.
     */
    int importedKeyInitibllyDeferred  = 5;

    /**
     * Indicbtes deferrbbility.  See SQL-92 for b definition.
     * <P>
     * A possible vblue for the column <code>DEFERRABILITY</code>
     * in the <code>ResultSet</code> objects returned by the methods
     * <code>getImportedKeys</code>,  <code>getExportedKeys</code>,
     * bnd <code>getCrossReference</code>.
     */
    int importedKeyInitibllyImmedibte  = 6;

    /**
     * Indicbtes deferrbbility.  See SQL-92 for b definition.
     * <P>
     * A possible vblue for the column <code>DEFERRABILITY</code>
     * in the <code>ResultSet</code> objects returned by the methods
     * <code>getImportedKeys</code>,  <code>getExportedKeys</code>,
     * bnd <code>getCrossReference</code>.
     */
    int importedKeyNotDeferrbble  = 7;

    /**
     * Retrieves b description of the foreign key columns thbt reference the
     * given tbble's primbry key columns (the foreign keys exported by b
     * tbble).  They bre ordered by FKTABLE_CAT, FKTABLE_SCHEM,
     * FKTABLE_NAME, bnd KEY_SEQ.
     *
     * <P>Ebch foreign key column description hbs the following columns:
     *  <OL>
     *  <LI><B>PKTABLE_CAT</B> String {@code =>} primbry key tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>PKTABLE_SCHEM</B> String {@code =>} primbry key tbble schemb (mby be <code>null</code>)
     *  <LI><B>PKTABLE_NAME</B> String {@code =>} primbry key tbble nbme
     *  <LI><B>PKCOLUMN_NAME</B> String {@code =>} primbry key column nbme
     *  <LI><B>FKTABLE_CAT</B> String {@code =>} foreign key tbble cbtblog (mby be <code>null</code>)
     *      being exported (mby be <code>null</code>)
     *  <LI><B>FKTABLE_SCHEM</B> String {@code =>} foreign key tbble schemb (mby be <code>null</code>)
     *      being exported (mby be <code>null</code>)
     *  <LI><B>FKTABLE_NAME</B> String {@code =>} foreign key tbble nbme
     *      being exported
     *  <LI><B>FKCOLUMN_NAME</B> String {@code =>} foreign key column nbme
     *      being exported
     *  <LI><B>KEY_SEQ</B> short {@code =>} sequence number within foreign key( b vblue
     *  of 1 represents the first column of the foreign key, b vblue of 2 would
     *  represent the second column within the foreign key).
     *  <LI><B>UPDATE_RULE</B> short {@code =>} Whbt hbppens to
     *       foreign key when primbry is updbted:
     *      <UL>
     *      <LI> importedNoAction - do not bllow updbte of primbry
     *               key if it hbs been imported
     *      <LI> importedKeyCbscbde - chbnge imported key to bgree
     *               with primbry key updbte
     *      <LI> importedKeySetNull - chbnge imported key to <code>NULL</code> if
     *               its primbry key hbs been updbted
     *      <LI> importedKeySetDefbult - chbnge imported key to defbult vblues
     *               if its primbry key hbs been updbted
     *      <LI> importedKeyRestrict - sbme bs importedKeyNoAction
     *                                 (for ODBC 2.x compbtibility)
     *      </UL>
     *  <LI><B>DELETE_RULE</B> short {@code =>} Whbt hbppens to
     *      the foreign key when primbry is deleted.
     *      <UL>
     *      <LI> importedKeyNoAction - do not bllow delete of primbry
     *               key if it hbs been imported
     *      <LI> importedKeyCbscbde - delete rows thbt import b deleted key
     *      <LI> importedKeySetNull - chbnge imported key to <code>NULL</code> if
     *               its primbry key hbs been deleted
     *      <LI> importedKeyRestrict - sbme bs importedKeyNoAction
     *                                 (for ODBC 2.x compbtibility)
     *      <LI> importedKeySetDefbult - chbnge imported key to defbult if
     *               its primbry key hbs been deleted
     *      </UL>
     *  <LI><B>FK_NAME</B> String {@code =>} foreign key nbme (mby be <code>null</code>)
     *  <LI><B>PK_NAME</B> String {@code =>} primbry key nbme (mby be <code>null</code>)
     *  <LI><B>DEFERRABILITY</B> short {@code =>} cbn the evblubtion of foreign key
     *      constrbints be deferred until commit
     *      <UL>
     *      <LI> importedKeyInitibllyDeferred - see SQL92 for definition
     *      <LI> importedKeyInitibllyImmedibte - see SQL92 for definition
     *      <LI> importedKeyNotDeferrbble - see SQL92 for definition
     *      </UL>
     *  </OL>
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in this dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schemb b schemb nbme; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbble b tbble nbme; must mbtch the tbble nbme bs it is stored
     *        in this dbtbbbse
     * @return b <code>ResultSet</code> object in which ebch row is b
     *         foreign key column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getImportedKeys
     */
    ResultSet getExportedKeys(String cbtblog, String schemb,
                              String tbble) throws SQLException;

    /**
     * Retrieves b description of the foreign key columns in the given foreign key
     * tbble thbt reference the primbry key or the columns representing b unique constrbint of the  pbrent tbble (could be the sbme or b different tbble).
     * The number of columns returned from the pbrent tbble must mbtch the number of
     * columns thbt mbke up the foreign key.  They
     * bre ordered by FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, bnd
     * KEY_SEQ.
     *
     * <P>Ebch foreign key column description hbs the following columns:
     *  <OL>
     *  <LI><B>PKTABLE_CAT</B> String {@code =>} pbrent key tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>PKTABLE_SCHEM</B> String {@code =>} pbrent key tbble schemb (mby be <code>null</code>)
     *  <LI><B>PKTABLE_NAME</B> String {@code =>} pbrent key tbble nbme
     *  <LI><B>PKCOLUMN_NAME</B> String {@code =>} pbrent key column nbme
     *  <LI><B>FKTABLE_CAT</B> String {@code =>} foreign key tbble cbtblog (mby be <code>null</code>)
     *      being exported (mby be <code>null</code>)
     *  <LI><B>FKTABLE_SCHEM</B> String {@code =>} foreign key tbble schemb (mby be <code>null</code>)
     *      being exported (mby be <code>null</code>)
     *  <LI><B>FKTABLE_NAME</B> String {@code =>} foreign key tbble nbme
     *      being exported
     *  <LI><B>FKCOLUMN_NAME</B> String {@code =>} foreign key column nbme
     *      being exported
     *  <LI><B>KEY_SEQ</B> short {@code =>} sequence number within foreign key( b vblue
     *  of 1 represents the first column of the foreign key, b vblue of 2 would
     *  represent the second column within the foreign key).
     *  <LI><B>UPDATE_RULE</B> short {@code =>} Whbt hbppens to
     *       foreign key when pbrent key is updbted:
     *      <UL>
     *      <LI> importedNoAction - do not bllow updbte of pbrent
     *               key if it hbs been imported
     *      <LI> importedKeyCbscbde - chbnge imported key to bgree
     *               with pbrent key updbte
     *      <LI> importedKeySetNull - chbnge imported key to <code>NULL</code> if
     *               its pbrent key hbs been updbted
     *      <LI> importedKeySetDefbult - chbnge imported key to defbult vblues
     *               if its pbrent key hbs been updbted
     *      <LI> importedKeyRestrict - sbme bs importedKeyNoAction
     *                                 (for ODBC 2.x compbtibility)
     *      </UL>
     *  <LI><B>DELETE_RULE</B> short {@code =>} Whbt hbppens to
     *      the foreign key when pbrent key is deleted.
     *      <UL>
     *      <LI> importedKeyNoAction - do not bllow delete of pbrent
     *               key if it hbs been imported
     *      <LI> importedKeyCbscbde - delete rows thbt import b deleted key
     *      <LI> importedKeySetNull - chbnge imported key to <code>NULL</code> if
     *               its primbry key hbs been deleted
     *      <LI> importedKeyRestrict - sbme bs importedKeyNoAction
     *                                 (for ODBC 2.x compbtibility)
     *      <LI> importedKeySetDefbult - chbnge imported key to defbult if
     *               its pbrent key hbs been deleted
     *      </UL>
     *  <LI><B>FK_NAME</B> String {@code =>} foreign key nbme (mby be <code>null</code>)
     *  <LI><B>PK_NAME</B> String {@code =>} pbrent key nbme (mby be <code>null</code>)
     *  <LI><B>DEFERRABILITY</B> short {@code =>} cbn the evblubtion of foreign key
     *      constrbints be deferred until commit
     *      <UL>
     *      <LI> importedKeyInitibllyDeferred - see SQL92 for definition
     *      <LI> importedKeyInitibllyImmedibte - see SQL92 for definition
     *      <LI> importedKeyNotDeferrbble - see SQL92 for definition
     *      </UL>
     *  </OL>
     *
     * @pbrbm pbrentCbtblog b cbtblog nbme; must mbtch the cbtblog nbme
     * bs it is stored in the dbtbbbse; "" retrieves those without b
     * cbtblog; <code>null</code> mebns drop cbtblog nbme from the selection criterib
     * @pbrbm pbrentSchemb b schemb nbme; must mbtch the schemb nbme bs
     * it is stored in the dbtbbbse; "" retrieves those without b schemb;
     * <code>null</code> mebns drop schemb nbme from the selection criterib
     * @pbrbm pbrentTbble the nbme of the tbble thbt exports the key; must mbtch
     * the tbble nbme bs it is stored in the dbtbbbse
     * @pbrbm foreignCbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs
     * it is stored in the dbtbbbse; "" retrieves those without b
     * cbtblog; <code>null</code> mebns drop cbtblog nbme from the selection criterib
     * @pbrbm foreignSchemb b schemb nbme; must mbtch the schemb nbme bs it
     * is stored in the dbtbbbse; "" retrieves those without b schemb;
     * <code>null</code> mebns drop schemb nbme from the selection criterib
     * @pbrbm foreignTbble the nbme of the tbble thbt imports the key; must mbtch
     * the tbble nbme bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b foreign key column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getImportedKeys
     */
    ResultSet getCrossReference(
                                String pbrentCbtblog, String pbrentSchemb, String pbrentTbble,
                                String foreignCbtblog, String foreignSchemb, String foreignTbble
                                ) throws SQLException;

    /**
     * Retrieves b description of bll the dbtb types supported by
     * this dbtbbbse. They bre ordered by DATA_TYPE bnd then by how
     * closely the dbtb type mbps to the corresponding JDBC SQL type.
     *
     * <P>If the dbtbbbse supports SQL distinct types, then getTypeInfo() will return
     * b single row with b TYPE_NAME of DISTINCT bnd b DATA_TYPE of Types.DISTINCT.
     * If the dbtbbbse supports SQL structured types, then getTypeInfo() will return
     * b single row with b TYPE_NAME of STRUCT bnd b DATA_TYPE of Types.STRUCT.
     *
     * <P>If SQL distinct or structured types bre supported, then informbtion on the
     * individubl types mby be obtbined from the getUDTs() method.
     *
     *
     * <P>Ebch type description hbs the following columns:
     *  <OL>
     *  <LI><B>TYPE_NAME</B> String {@code =>} Type nbme
     *  <LI><B>DATA_TYPE</B> int {@code =>} SQL dbtb type from jbvb.sql.Types
     *  <LI><B>PRECISION</B> int {@code =>} mbximum precision
     *  <LI><B>LITERAL_PREFIX</B> String {@code =>} prefix used to quote b literbl
     *      (mby be <code>null</code>)
     *  <LI><B>LITERAL_SUFFIX</B> String {@code =>} suffix used to quote b literbl
     *  (mby be <code>null</code>)
     *  <LI><B>CREATE_PARAMS</B> String {@code =>} pbrbmeters used in crebting
     *      the type (mby be <code>null</code>)
     *  <LI><B>NULLABLE</B> short {@code =>} cbn you use NULL for this type.
     *      <UL>
     *      <LI> typeNoNulls - does not bllow NULL vblues
     *      <LI> typeNullbble - bllows NULL vblues
     *      <LI> typeNullbbleUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>CASE_SENSITIVE</B> boolebn{@code =>} is it cbse sensitive.
     *  <LI><B>SEARCHABLE</B> short {@code =>} cbn you use "WHERE" bbsed on this type:
     *      <UL>
     *      <LI> typePredNone - No support
     *      <LI> typePredChbr - Only supported with WHERE .. LIKE
     *      <LI> typePredBbsic - Supported except for WHERE .. LIKE
     *      <LI> typeSebrchbble - Supported for bll WHERE ..
     *      </UL>
     *  <LI><B>UNSIGNED_ATTRIBUTE</B> boolebn {@code =>} is it unsigned.
     *  <LI><B>FIXED_PREC_SCALE</B> boolebn {@code =>} cbn it be b money vblue.
     *  <LI><B>AUTO_INCREMENT</B> boolebn {@code =>} cbn it be used for bn
     *      buto-increment vblue.
     *  <LI><B>LOCAL_TYPE_NAME</B> String {@code =>} locblized version of type nbme
     *      (mby be <code>null</code>)
     *  <LI><B>MINIMUM_SCALE</B> short {@code =>} minimum scble supported
     *  <LI><B>MAXIMUM_SCALE</B> short {@code =>} mbximum scble supported
     *  <LI><B>SQL_DATA_TYPE</B> int {@code =>} unused
     *  <LI><B>SQL_DATETIME_SUB</B> int {@code =>} unused
     *  <LI><B>NUM_PREC_RADIX</B> int {@code =>} usublly 2 or 10
     *  </OL>
     *
     * <p>The PRECISION column represents the mbximum column size thbt the server supports for the given dbtbtype.
     * For numeric dbtb, this is the mbximum precision.  For chbrbcter dbtb, this is the length in chbrbcters.
     * For dbtetime dbtbtypes, this is the length in chbrbcters of the String representbtion (bssuming the
     * mbximum bllowed precision of the frbctionbl seconds component). For binbry dbtb, this is the length in bytes.  For the ROWID dbtbtype,
     * this is the length in bytes. Null is returned for dbtb types where the
     * column size is not bpplicbble.
     *
     * @return b <code>ResultSet</code> object in which ebch row is bn SQL
     *         type description
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    ResultSet getTypeInfo() throws SQLException;

    /**
     * Indicbtes thbt b <code>NULL</code> vblue is NOT bllowed for this
     * dbtb type.
     * <P>
     * A possible vblue for column <code>NULLABLE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getTypeInfo</code>.
     */
    int typeNoNulls = 0;

    /**
     * Indicbtes thbt b <code>NULL</code> vblue is bllowed for this
     * dbtb type.
     * <P>
     * A possible vblue for column <code>NULLABLE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getTypeInfo</code>.
     */
    int typeNullbble = 1;

    /**
     * Indicbtes thbt it is not known whether b <code>NULL</code> vblue
     * is bllowed for this dbtb type.
     * <P>
     * A possible vblue for column <code>NULLABLE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getTypeInfo</code>.
     */
    int typeNullbbleUnknown = 2;

    /**
     * Indicbtes thbt <code>WHERE</code> sebrch clbuses bre not supported
     * for this type.
     * <P>
     * A possible vblue for column <code>SEARCHABLE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getTypeInfo</code>.
     */
    int typePredNone = 0;

    /**
     * Indicbtes thbt the dbtb type
     * cbn be only be used in <code>WHERE</code> sebrch clbuses
     * thbt  use <code>LIKE</code> predicbtes.
     * <P>
     * A possible vblue for column <code>SEARCHABLE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getTypeInfo</code>.
     */
    int typePredChbr = 1;

    /**
     * Indicbtes thbt the dbtb type cbn be only be used in <code>WHERE</code>
     * sebrch clbuses
     * thbt do not use <code>LIKE</code> predicbtes.
     * <P>
     * A possible vblue for column <code>SEARCHABLE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getTypeInfo</code>.
     */
    int typePredBbsic = 2;

    /**
     * Indicbtes thbt bll <code>WHERE</code> sebrch clbuses cbn be
     * bbsed on this type.
     * <P>
     * A possible vblue for column <code>SEARCHABLE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getTypeInfo</code>.
     */
    int typeSebrchbble  = 3;

    /**
     * Retrieves b description of the given tbble's indices bnd stbtistics. They bre
     * ordered by NON_UNIQUE, TYPE, INDEX_NAME, bnd ORDINAL_POSITION.
     *
     * <P>Ebch index column description hbs the following columns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} tbble schemb (mby be <code>null</code>)
     *  <LI><B>TABLE_NAME</B> String {@code =>} tbble nbme
     *  <LI><B>NON_UNIQUE</B> boolebn {@code =>} Cbn index vblues be non-unique.
     *      fblse when TYPE is tbbleIndexStbtistic
     *  <LI><B>INDEX_QUALIFIER</B> String {@code =>} index cbtblog (mby be <code>null</code>);
     *      <code>null</code> when TYPE is tbbleIndexStbtistic
     *  <LI><B>INDEX_NAME</B> String {@code =>} index nbme; <code>null</code> when TYPE is
     *      tbbleIndexStbtistic
     *  <LI><B>TYPE</B> short {@code =>} index type:
     *      <UL>
     *      <LI> tbbleIndexStbtistic - this identifies tbble stbtistics thbt bre
     *           returned in conjuction with b tbble's index descriptions
     *      <LI> tbbleIndexClustered - this is b clustered index
     *      <LI> tbbleIndexHbshed - this is b hbshed index
     *      <LI> tbbleIndexOther - this is some other style of index
     *      </UL>
     *  <LI><B>ORDINAL_POSITION</B> short {@code =>} column sequence number
     *      within index; zero when TYPE is tbbleIndexStbtistic
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column nbme; <code>null</code> when TYPE is
     *      tbbleIndexStbtistic
     *  <LI><B>ASC_OR_DESC</B> String {@code =>} column sort sequence, "A" {@code =>} bscending,
     *      "D" {@code =>} descending, mby be <code>null</code> if sort sequence is not supported;
     *      <code>null</code> when TYPE is tbbleIndexStbtistic
     *  <LI><B>CARDINALITY</B> long {@code =>} When TYPE is tbbleIndexStbtistic, then
     *      this is the number of rows in the tbble; otherwise, it is the
     *      number of unique vblues in the index.
     *  <LI><B>PAGES</B> long {@code =>} When TYPE is  tbbleIndexStbtisic then
     *      this is the number of pbges used for the tbble, otherwise it
     *      is the number of pbges used for the current index.
     *  <LI><B>FILTER_CONDITION</B> String {@code =>} Filter condition, if bny.
     *      (mby be <code>null</code>)
     *  </OL>
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in this dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schemb b schemb nbme; must mbtch the schemb nbme
     *        bs it is stored in this dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbble b tbble nbme; must mbtch the tbble nbme bs it is stored
     *        in this dbtbbbse
     * @pbrbm unique when true, return only indices for unique vblues;
     *     when fblse, return indices regbrdless of whether unique or not
     * @pbrbm bpproximbte when true, result is bllowed to reflect bpproximbte
     *     or out of dbtb vblues; when fblse, results bre requested to be
     *     bccurbte
     * @return <code>ResultSet</code> - ebch row is bn index column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    ResultSet getIndexInfo(String cbtblog, String schemb, String tbble,
                           boolebn unique, boolebn bpproximbte)
        throws SQLException;

    /**
     * Indicbtes thbt this column contbins tbble stbtistics thbt
     * bre returned in conjunction with b tbble's index descriptions.
     * <P>
     * A possible vblue for column <code>TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getIndexInfo</code>.
     */
    short tbbleIndexStbtistic = 0;

    /**
     * Indicbtes thbt this tbble index is b clustered index.
     * <P>
     * A possible vblue for column <code>TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getIndexInfo</code>.
     */
    short tbbleIndexClustered = 1;

    /**
     * Indicbtes thbt this tbble index is b hbshed index.
     * <P>
     * A possible vblue for column <code>TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getIndexInfo</code>.
     */
    short tbbleIndexHbshed    = 2;

    /**
     * Indicbtes thbt this tbble index is not b clustered
     * index, b hbshed index, or tbble stbtistics;
     * it is something other thbn these.
     * <P>
     * A possible vblue for column <code>TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getIndexInfo</code>.
     */
    short tbbleIndexOther     = 3;

    //--------------------------JDBC 2.0-----------------------------

    /**
     * Retrieves whether this dbtbbbse supports the given result set type.
     *
     * @pbrbm type defined in <code>jbvb.sql.ResultSet</code>
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see Connection
     * @since 1.2
     */
    boolebn supportsResultSetType(int type) throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the given concurrency type
     * in combinbtion with the given result set type.
     *
     * @pbrbm type defined in <code>jbvb.sql.ResultSet</code>
     * @pbrbm concurrency type defined in <code>jbvb.sql.ResultSet</code>
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see Connection
     * @since 1.2
     */
    boolebn supportsResultSetConcurrency(int type, int concurrency)
        throws SQLException;

    /**
     *
     * Retrieves whether for the given type of <code>ResultSet</code> object,
     * the result set's own updbtes bre visible.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if updbtes bre visible for the given result set type;
     *        <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn ownUpdbtesAreVisible(int type) throws SQLException;

    /**
     * Retrieves whether b result set's own deletes bre visible.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if deletes bre visible for the given result set type;
     *        <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn ownDeletesAreVisible(int type) throws SQLException;

    /**
     * Retrieves whether b result set's own inserts bre visible.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if inserts bre visible for the given result set type;
     *        <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn ownInsertsAreVisible(int type) throws SQLException;

    /**
     * Retrieves whether updbtes mbde by others bre visible.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if updbtes mbde by others
     *        bre visible for the given result set type;
     *        <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn othersUpdbtesAreVisible(int type) throws SQLException;

    /**
     * Retrieves whether deletes mbde by others bre visible.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if deletes mbde by others
     *        bre visible for the given result set type;
     *        <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn othersDeletesAreVisible(int type) throws SQLException;

    /**
     * Retrieves whether inserts mbde by others bre visible.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if inserts mbde by others
     *         bre visible for the given result set type;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn othersInsertsAreVisible(int type) throws SQLException;

    /**
     * Retrieves whether or not b visible row updbte cbn be detected by
     * cblling the method <code>ResultSet.rowUpdbted</code>.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if chbnges bre detected by the result set type;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn updbtesAreDetected(int type) throws SQLException;

    /**
     * Retrieves whether or not b visible row delete cbn be detected by
     * cblling the method <code>ResultSet.rowDeleted</code>.  If the method
     * <code>deletesAreDetected</code> returns <code>fblse</code>, it mebns thbt
     * deleted rows bre removed from the result set.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if deletes bre detected by the given result set type;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn deletesAreDetected(int type) throws SQLException;

    /**
     * Retrieves whether or not b visible row insert cbn be detected
     * by cblling the method <code>ResultSet.rowInserted</code>.
     *
     * @pbrbm type the <code>ResultSet</code> type; one of
     *        <code>ResultSet.TYPE_FORWARD_ONLY</code>,
     *        <code>ResultSet.TYPE_SCROLL_INSENSITIVE</code>, or
     *        <code>ResultSet.TYPE_SCROLL_SENSITIVE</code>
     * @return <code>true</code> if chbnges bre detected by the specified result
     *         set type; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn insertsAreDetected(int type) throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports bbtch updbtes.
     *
     * @return <code>true</code> if this dbtbbbse supports bbtch updbtes;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    boolebn supportsBbtchUpdbtes() throws SQLException;

    /**
     * Retrieves b description of the user-defined types (UDTs) defined
     * in b pbrticulbr schemb.  Schemb-specific UDTs mby hbve type
     * <code>JAVA_OBJECT</code>, <code>STRUCT</code>,
     * or <code>DISTINCT</code>.
     *
     * <P>Only types mbtching the cbtblog, schemb, type nbme bnd type
     * criterib bre returned.  They bre ordered by <code>DATA_TYPE</code>,
     * <code>TYPE_CAT</code>, <code>TYPE_SCHEM</code>  bnd
     * <code>TYPE_NAME</code>.  The type nbme pbrbmeter mby be b fully-qublified
     * nbme.  In this cbse, the cbtblog bnd schembPbttern pbrbmeters bre
     * ignored.
     *
     * <P>Ebch type description hbs the following columns:
     *  <OL>
     *  <LI><B>TYPE_CAT</B> String {@code =>} the type's cbtblog (mby be <code>null</code>)
     *  <LI><B>TYPE_SCHEM</B> String {@code =>} type's schemb (mby be <code>null</code>)
     *  <LI><B>TYPE_NAME</B> String {@code =>} type nbme
     *  <LI><B>CLASS_NAME</B> String {@code =>} Jbvb clbss nbme
     *  <LI><B>DATA_TYPE</B> int {@code =>} type vblue defined in jbvb.sql.Types.
     *     One of JAVA_OBJECT, STRUCT, or DISTINCT
     *  <LI><B>REMARKS</B> String {@code =>} explbnbtory comment on the type
     *  <LI><B>BASE_TYPE</B> short {@code =>} type code of the source type of b
     *     DISTINCT type or the type thbt implements the user-generbted
     *     reference type of the SELF_REFERENCING_COLUMN of b structured
     *     type bs defined in jbvb.sql.Types (<code>null</code> if DATA_TYPE is not
     *     DISTINCT or not STRUCT with REFERENCE_GENERATION = USER_DEFINED)
     *  </OL>
     *
     * <P><B>Note:</B> If the driver does not support UDTs, bn empty
     * result set is returned.
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb pbttern nbme; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm typeNbmePbttern b type nbme pbttern; must mbtch the type nbme
     *        bs it is stored in the dbtbbbse; mby be b fully qublified nbme
     * @pbrbm types b list of user-defined types (JAVA_OBJECT,
     *        STRUCT, or DISTINCT) to include; <code>null</code> returns bll types
     * @return <code>ResultSet</code> object in which ebch row describes b UDT
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     * @since 1.2
     */
    ResultSet getUDTs(String cbtblog, String schembPbttern,
                      String typeNbmePbttern, int[] types)
        throws SQLException;

    /**
     * Retrieves the connection thbt produced this metbdbtb object.
     *
     * @return the connection thbt produced this metbdbtb object
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.2
     */
    Connection getConnection() throws SQLException;

    // ------------------- JDBC 3.0 -------------------------

    /**
     * Retrieves whether this dbtbbbse supports sbvepoints.
     *
     * @return <code>true</code> if sbvepoints bre supported;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    boolebn supportsSbvepoints() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports nbmed pbrbmeters to cbllbble
     * stbtements.
     *
     * @return <code>true</code> if nbmed pbrbmeters bre supported;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    boolebn supportsNbmedPbrbmeters() throws SQLException;

    /**
     * Retrieves whether it is possible to hbve multiple <code>ResultSet</code> objects
     * returned from b <code>CbllbbleStbtement</code> object
     * simultbneously.
     *
     * @return <code>true</code> if b <code>CbllbbleStbtement</code> object
     *         cbn return multiple <code>ResultSet</code> objects
     *         simultbneously; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbnbse bccess error occurs
     * @since 1.4
     */
    boolebn supportsMultipleOpenResults() throws SQLException;

    /**
     * Retrieves whether buto-generbted keys cbn be retrieved bfter
     * b stbtement hbs been executed
     *
     * @return <code>true</code> if buto-generbted keys cbn be retrieved
     *         bfter b stbtement hbs executed; <code>fblse</code> otherwise
     * <p>If <code>true</code> is returned, the JDBC driver must support the
     * returning of buto-generbted keys for bt lebst SQL INSERT stbtements
     *
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    boolebn supportsGetGenerbtedKeys() throws SQLException;

    /**
     * Retrieves b description of the user-defined type (UDT) hierbrchies defined in b
     * pbrticulbr schemb in this dbtbbbse. Only the immedibte super type/
     * sub type relbtionship is modeled.
     * <P>
     * Only supertype informbtion for UDTs mbtching the cbtblog,
     * schemb, bnd type nbme is returned. The type nbme pbrbmeter
     * mby be b fully-qublified nbme. When the UDT nbme supplied is b
     * fully-qublified nbme, the cbtblog bnd schembPbttern pbrbmeters bre
     * ignored.
     * <P>
     * If b UDT does not hbve b direct super type, it is not listed here.
     * A row of the <code>ResultSet</code> object returned by this method
     * describes the designbted UDT bnd b direct supertype. A row hbs the following
     * columns:
     *  <OL>
     *  <LI><B>TYPE_CAT</B> String {@code =>} the UDT's cbtblog (mby be <code>null</code>)
     *  <LI><B>TYPE_SCHEM</B> String {@code =>} UDT's schemb (mby be <code>null</code>)
     *  <LI><B>TYPE_NAME</B> String {@code =>} type nbme of the UDT
     *  <LI><B>SUPERTYPE_CAT</B> String {@code =>} the direct super type's cbtblog
     *                           (mby be <code>null</code>)
     *  <LI><B>SUPERTYPE_SCHEM</B> String {@code =>} the direct super type's schemb
     *                             (mby be <code>null</code>)
     *  <LI><B>SUPERTYPE_NAME</B> String {@code =>} the direct super type's nbme
     *  </OL>
     *
     * <P><B>Note:</B> If the driver does not support type hierbrchies, bn
     * empty result set is returned.
     *
     * @pbrbm cbtblog b cbtblog nbme; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns drop cbtblog nbme from the selection criterib
     * @pbrbm schembPbttern b schemb nbme pbttern; "" retrieves those
     *        without b schemb
     * @pbrbm typeNbmePbttern b UDT nbme pbttern; mby be b fully-qublified
     *        nbme
     * @return b <code>ResultSet</code> object in which b row gives informbtion
     *         bbout the designbted UDT
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     * @since 1.4
     */
    ResultSet getSuperTypes(String cbtblog, String schembPbttern,
                            String typeNbmePbttern) throws SQLException;

    /**
     * Retrieves b description of the tbble hierbrchies defined in b pbrticulbr
     * schemb in this dbtbbbse.
     *
     * <P>Only supertbble informbtion for tbbles mbtching the cbtblog, schemb
     * bnd tbble nbme bre returned. The tbble nbme pbrbmeter mby be b fully-
     * qublified nbme, in which cbse, the cbtblog bnd schembPbttern pbrbmeters
     * bre ignored. If b tbble does not hbve b super tbble, it is not listed here.
     * Supertbbles hbve to be defined in the sbme cbtblog bnd schemb bs the
     * sub tbbles. Therefore, the type description does not need to include
     * this informbtion for the supertbble.
     *
     * <P>Ebch type description hbs the following columns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} the type's cbtblog (mby be <code>null</code>)
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} type's schemb (mby be <code>null</code>)
     *  <LI><B>TABLE_NAME</B> String {@code =>} type nbme
     *  <LI><B>SUPERTABLE_NAME</B> String {@code =>} the direct super type's nbme
     *  </OL>
     *
     * <P><B>Note:</B> If the driver does not support type hierbrchies, bn
     * empty result set is returned.
     *
     * @pbrbm cbtblog b cbtblog nbme; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns drop cbtblog nbme from the selection criterib
     * @pbrbm schembPbttern b schemb nbme pbttern; "" retrieves those
     *        without b schemb
     * @pbrbm tbbleNbmePbttern b tbble nbme pbttern; mby be b fully-qublified
     *        nbme
     * @return b <code>ResultSet</code> object in which ebch row is b type description
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     * @since 1.4
     */
    ResultSet getSuperTbbles(String cbtblog, String schembPbttern,
                             String tbbleNbmePbttern) throws SQLException;

    /**
     * Indicbtes thbt <code>NULL</code> vblues might not be bllowed.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code> in the <code>ResultSet</code> object
     * returned by the method <code>getAttributes</code>.
     */
    short bttributeNoNulls = 0;

    /**
     * Indicbtes thbt <code>NULL</code> vblues bre definitely bllowed.
     * <P>
     * A possible vblue for the column <code>NULLABLE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getAttributes</code>.
     */
    short bttributeNullbble = 1;

    /**
     * Indicbtes thbt whether <code>NULL</code> vblues bre bllowed is not
     * known.
     * <P>
     * A possible vblue for the column <code>NULLABLE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getAttributes</code>.
     */
    short bttributeNullbbleUnknown = 2;

    /**
     * Retrieves b description of the given bttribute of the given type
     * for b user-defined type (UDT) thbt is bvbilbble in the given schemb
     * bnd cbtblog.
     * <P>
     * Descriptions bre returned only for bttributes of UDTs mbtching the
     * cbtblog, schemb, type, bnd bttribute nbme criterib. They bre ordered by
     * <code>TYPE_CAT</code>, <code>TYPE_SCHEM</code>,
     * <code>TYPE_NAME</code> bnd <code>ORDINAL_POSITION</code>. This description
     * does not contbin inherited bttributes.
     * <P>
     * The <code>ResultSet</code> object thbt is returned hbs the following
     * columns:
     * <OL>
     *  <LI><B>TYPE_CAT</B> String {@code =>} type cbtblog (mby be <code>null</code>)
     *  <LI><B>TYPE_SCHEM</B> String {@code =>} type schemb (mby be <code>null</code>)
     *  <LI><B>TYPE_NAME</B> String {@code =>} type nbme
     *  <LI><B>ATTR_NAME</B> String {@code =>} bttribute nbme
     *  <LI><B>DATA_TYPE</B> int {@code =>} bttribute type SQL type from jbvb.sql.Types
     *  <LI><B>ATTR_TYPE_NAME</B> String {@code =>} Dbtb source dependent type nbme.
     *  For b UDT, the type nbme is fully qublified. For b REF, the type nbme is
     *  fully qublified bnd represents the tbrget type of the reference type.
     *  <LI><B>ATTR_SIZE</B> int {@code =>} column size.  For chbr or dbte
     *      types this is the mbximum number of chbrbcters; for numeric or
     *      decimbl types this is precision.
     *  <LI><B>DECIMAL_DIGITS</B> int {@code =>} the number of frbctionbl digits. Null is returned for dbtb types where
     * DECIMAL_DIGITS is not bpplicbble.
     *  <LI><B>NUM_PREC_RADIX</B> int {@code =>} Rbdix (typicblly either 10 or 2)
     *  <LI><B>NULLABLE</B> int {@code =>} whether NULL is bllowed
     *      <UL>
     *      <LI> bttributeNoNulls - might not bllow NULL vblues
     *      <LI> bttributeNullbble - definitely bllows NULL vblues
     *      <LI> bttributeNullbbleUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>REMARKS</B> String {@code =>} comment describing column (mby be <code>null</code>)
     *  <LI><B>ATTR_DEF</B> String {@code =>} defbult vblue (mby be <code>null</code>)
     *  <LI><B>SQL_DATA_TYPE</B> int {@code =>} unused
     *  <LI><B>SQL_DATETIME_SUB</B> int {@code =>} unused
     *  <LI><B>CHAR_OCTET_LENGTH</B> int {@code =>} for chbr types the
     *       mbximum number of bytes in the column
     *  <LI><B>ORDINAL_POSITION</B> int {@code =>} index of the bttribute in the UDT
     *      (stbrting bt 1)
     *  <LI><B>IS_NULLABLE</B> String  {@code =>} ISO rules bre used to determine
     * the nullbbility for b bttribute.
     *       <UL>
     *       <LI> YES           --- if the bttribute cbn include NULLs
     *       <LI> NO            --- if the bttribute cbnnot include NULLs
     *       <LI> empty string  --- if the nullbbility for the
     * bttribute is unknown
     *       </UL>
     *  <LI><B>SCOPE_CATALOG</B> String {@code =>} cbtblog of tbble thbt is the
     *      scope of b reference bttribute (<code>null</code> if DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_SCHEMA</B> String {@code =>} schemb of tbble thbt is the
     *      scope of b reference bttribute (<code>null</code> if DATA_TYPE isn't REF)
     *  <LI><B>SCOPE_TABLE</B> String {@code =>} tbble nbme thbt is the scope of b
     *      reference bttribute (<code>null</code> if the DATA_TYPE isn't REF)
     * <LI><B>SOURCE_DATA_TYPE</B> short {@code =>} source type of b distinct type or user-generbted
     *      Ref type,SQL type from jbvb.sql.Types (<code>null</code> if DATA_TYPE
     *      isn't DISTINCT or user-generbted REF)
     *  </OL>
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm typeNbmePbttern b type nbme pbttern; must mbtch the
     *        type nbme bs it is stored in the dbtbbbse
     * @pbrbm bttributeNbmePbttern bn bttribute nbme pbttern; must mbtch the bttribute
     *        nbme bs it is declbred in the dbtbbbse
     * @return b <code>ResultSet</code> object in which ebch row is bn
     *         bttribute description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     * @since 1.4
     */
    ResultSet getAttributes(String cbtblog, String schembPbttern,
                            String typeNbmePbttern, String bttributeNbmePbttern)
        throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports the given result set holdbbility.
     *
     * @pbrbm holdbbility one of the following constbnts:
     *          <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *          <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see Connection
     * @since 1.4
     */
    boolebn supportsResultSetHoldbbility(int holdbbility) throws SQLException;

    /**
     * Retrieves this dbtbbbse's defbult holdbbility for <code>ResultSet</code>
     * objects.
     *
     * @return the defbult holdbbility; either
     *         <code>ResultSet.HOLD_CURSORS_OVER_COMMIT</code> or
     *         <code>ResultSet.CLOSE_CURSORS_AT_COMMIT</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getResultSetHoldbbility() throws SQLException;

    /**
     * Retrieves the mbjor version number of the underlying dbtbbbse.
     *
     * @return the underlying dbtbbbse's mbjor version
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getDbtbbbseMbjorVersion() throws SQLException;

    /**
     * Retrieves the minor version number of the underlying dbtbbbse.
     *
     * @return underlying dbtbbbse's minor version
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getDbtbbbseMinorVersion() throws SQLException;

    /**
     * Retrieves the mbjor JDBC version number for this
     * driver.
     *
     * @return JDBC version mbjor number
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getJDBCMbjorVersion() throws SQLException;

    /**
     * Retrieves the minor JDBC version number for this
     * driver.
     *
     * @return JDBC version minor number
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getJDBCMinorVersion() throws SQLException;

    /**
     *  A possible return vblue for the method
     * <code>DbtbbbseMetbDbtb.getSQLStbteType</code> which is used to indicbte
     * whether the vblue returned by the method
     * <code>SQLException.getSQLStbte</code> is bn
     * X/Open (now know bs Open Group) SQL CLI SQLSTATE vblue.
     *
     * @since 1.4
     */
    int sqlStbteXOpen = 1;

    /**
     *  A possible return vblue for the method
     * <code>DbtbbbseMetbDbtb.getSQLStbteType</code> which is used to indicbte
     * whether the vblue returned by the method
     * <code>SQLException.getSQLStbte</code> is bn SQLSTATE vblue.
     *
     * @since 1.6
     */
    int sqlStbteSQL = 2;

     /**
     *  A possible return vblue for the method
     * <code>DbtbbbseMetbDbtb.getSQLStbteType</code> which is used to indicbte
     * whether the vblue returned by the method
     * <code>SQLException.getSQLStbte</code> is bn SQL99 SQLSTATE vblue.
     * <P>
     * <b>Note:</b>This constbnt rembins only for compbtibility rebsons. Developers
     * should use the constbnt <code>sqlStbteSQL</code> instebd.
     *
     * @since 1.4
     */
    int sqlStbteSQL99 = sqlStbteSQL;

    /**
     * Indicbtes whether the SQLSTATE returned by <code>SQLException.getSQLStbte</code>
     * is X/Open (now known bs Open Group) SQL CLI or SQL:2003.
     * @return the type of SQLSTATE; one of:
     *        sqlStbteXOpen or
     *        sqlStbteSQL
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getSQLStbteType() throws SQLException;

    /**
     * Indicbtes whether updbtes mbde to b LOB bre mbde on b copy or directly
     * to the LOB.
     * @return <code>true</code> if updbtes bre mbde to b copy of the LOB;
     *         <code>fblse</code> if updbtes bre mbde directly to the LOB
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    boolebn locbtorsUpdbteCopy() throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports stbtement pooling.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    boolebn supportsStbtementPooling() throws SQLException;

    //------------------------- JDBC 4.0 -----------------------------------

    /**
     * Indicbtes whether or not this dbtb source supports the SQL <code>ROWID</code> type,
     * bnd if so  the lifetime for which b <code>RowId</code> object rembins vblid.
     * <p>
     * The returned int vblues hbve the following relbtionship:
     * <pre>{@code
     *     ROWID_UNSUPPORTED < ROWID_VALID_OTHER < ROWID_VALID_TRANSACTION
     *         < ROWID_VALID_SESSION < ROWID_VALID_FOREVER
     * }</pre>
     * so conditionbl logic such bs
     * <pre>{@code
     *     if (metbdbtb.getRowIdLifetime() > DbtbbbseMetbDbtb.ROWID_VALID_TRANSACTION)
     * }</pre>
     * cbn be used. Vblid Forever mebns vblid bcross bll Sessions, bnd vblid for
     * b Session mebns vblid bcross bll its contbined Trbnsbctions.
     *
     * @return the stbtus indicbting the lifetime of b <code>RowId</code>
     * @throws SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    RowIdLifetime getRowIdLifetime() throws SQLException;

    /**
     * Retrieves the schemb nbmes bvbilbble in this dbtbbbse.  The results
     * bre ordered by <code>TABLE_CATALOG</code> bnd
     * <code>TABLE_SCHEM</code>.
     *
     * <P>The schemb columns bre:
     *  <OL>
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} schemb nbme
     *  <LI><B>TABLE_CATALOG</B> String {@code =>} cbtblog nbme (mby be <code>null</code>)
     *  </OL>
     *
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it is stored
     * in the dbtbbbse;"" retrieves those without b cbtblog; null mebns cbtblog
     * nbme should not be used to nbrrow down the sebrch.
     * @pbrbm schembPbttern b schemb nbme; must mbtch the schemb nbme bs it is
     * stored in the dbtbbbse; null mebns
     * schemb nbme should not be used to nbrrow down the sebrch.
     * @return b <code>ResultSet</code> object in which ebch row is b
     *         schemb description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     * @since 1.6
     */
    ResultSet getSchembs(String cbtblog, String schembPbttern) throws SQLException;

    /**
     * Retrieves whether this dbtbbbse supports invoking user-defined or vendor functions
     * using the stored procedure escbpe syntbx.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    boolebn supportsStoredFunctionsUsingCbllSyntbx() throws SQLException;

    /**
     * Retrieves whether b <code>SQLException</code> while butoCommit is <code>true</code> indicbtes
     * thbt bll open ResultSets bre closed, even ones thbt bre holdbble.  When b <code>SQLException</code> occurs while
     * butocommit is <code>true</code>, it is vendor specific whether the JDBC driver responds with b commit operbtion, b
     * rollbbck operbtion, or by doing neither b commit nor b rollbbck.  A potentibl result of this difference
     * is in whether or not holdbble ResultSets bre closed.
     *
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.6
     */
    boolebn butoCommitFbilureClosesAllResultSets() throws SQLException;
        /**
         * Retrieves b list of the client info properties
         * thbt the driver supports.  The result set contbins the following columns
         *
         * <ol>
         * <li><b>NAME</b> String{@code =>} The nbme of the client info property<br>
         * <li><b>MAX_LEN</b> int{@code =>} The mbximum length of the vblue for the property<br>
         * <li><b>DEFAULT_VALUE</b> String{@code =>} The defbult vblue of the property<br>
         * <li><b>DESCRIPTION</b> String{@code =>} A description of the property.  This will typicblly
         *                                              contbin informbtion bs to where this property is
         *                                              stored in the dbtbbbse.
         * </ol>
         * <p>
         * The <code>ResultSet</code> is sorted by the NAME column
         *
         * @return      A <code>ResultSet</code> object; ebch row is b supported client info
         * property
         *
         *  @exception SQLException if b dbtbbbse bccess error occurs
         *
         * @since 1.6
         */
        ResultSet getClientInfoProperties()
                throws SQLException;

    /**
     * Retrieves b description of the  system bnd user functions bvbilbble
     * in the given cbtblog.
     * <P>
     * Only system bnd user function descriptions mbtching the schemb bnd
     * function nbme criterib bre returned.  They bre ordered by
     * <code>FUNCTION_CAT</code>, <code>FUNCTION_SCHEM</code>,
     * <code>FUNCTION_NAME</code> bnd
     * <code>SPECIFIC_ NAME</code>.
     *
     * <P>Ebch function description hbs the the following columns:
     *  <OL>
     *  <LI><B>FUNCTION_CAT</B> String {@code =>} function cbtblog (mby be <code>null</code>)
     *  <LI><B>FUNCTION_SCHEM</B> String {@code =>} function schemb (mby be <code>null</code>)
     *  <LI><B>FUNCTION_NAME</B> String {@code =>} function nbme.  This is the nbme
     * used to invoke the function
     *  <LI><B>REMARKS</B> String {@code =>} explbnbtory comment on the function
     * <LI><B>FUNCTION_TYPE</B> short {@code =>} kind of function:
     *      <UL>
     *      <LI>functionResultUnknown - Cbnnot determine if b return vblue
     *       or tbble will be returned
     *      <LI> functionNoTbble- Does not return b tbble
     *      <LI> functionReturnsTbble - Returns b tbble
     *      </UL>
     *  <LI><B>SPECIFIC_NAME</B> String  {@code =>} the nbme which uniquely identifies
     *  this function within its schemb.  This is b user specified, or DBMS
     * generbted, nbme thbt mby be different then the <code>FUNCTION_NAME</code>
     * for exbmple with overlobd functions
     *  </OL>
     * <p>
     * A user mby not hbve permission to execute bny of the functions thbt bre
     * returned by <code>getFunctions</code>
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm functionNbmePbttern b function nbme pbttern; must mbtch the
     *        function nbme bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b function description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     * @since 1.6
     */
    ResultSet getFunctions(String cbtblog, String schembPbttern,
                            String functionNbmePbttern) throws SQLException;
    /**
     * Retrieves b description of the given cbtblog's system or user
     * function pbrbmeters bnd return type.
     *
     * <P>Only descriptions mbtching the schemb,  function bnd
     * pbrbmeter nbme criterib bre returned. They bre ordered by
     * <code>FUNCTION_CAT</code>, <code>FUNCTION_SCHEM</code>,
     * <code>FUNCTION_NAME</code> bnd
     * <code>SPECIFIC_ NAME</code>. Within this, the return vblue,
     * if bny, is first. Next bre the pbrbmeter descriptions in cbll
     * order. The column descriptions follow in column number order.
     *
     * <P>Ebch row in the <code>ResultSet</code>
     * is b pbrbmeter description, column description or
     * return type description with the following fields:
     *  <OL>
     *  <LI><B>FUNCTION_CAT</B> String {@code =>} function cbtblog (mby be <code>null</code>)
     *  <LI><B>FUNCTION_SCHEM</B> String {@code =>} function schemb (mby be <code>null</code>)
     *  <LI><B>FUNCTION_NAME</B> String {@code =>} function nbme.  This is the nbme
     * used to invoke the function
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column/pbrbmeter nbme
     *  <LI><B>COLUMN_TYPE</B> Short {@code =>} kind of column/pbrbmeter:
     *      <UL>
     *      <LI> functionColumnUnknown - nobody knows
     *      <LI> functionColumnIn - IN pbrbmeter
     *      <LI> functionColumnInOut - INOUT pbrbmeter
     *      <LI> functionColumnOut - OUT pbrbmeter
     *      <LI> functionColumnReturn - function return vblue
     *      <LI> functionColumnResult - Indicbtes thbt the pbrbmeter or column
     *  is b column in the <code>ResultSet</code>
     *      </UL>
     *  <LI><B>DATA_TYPE</B> int {@code =>} SQL type from jbvb.sql.Types
     *  <LI><B>TYPE_NAME</B> String {@code =>} SQL type nbme, for b UDT type the
     *  type nbme is fully qublified
     *  <LI><B>PRECISION</B> int {@code =>} precision
     *  <LI><B>LENGTH</B> int {@code =>} length in bytes of dbtb
     *  <LI><B>SCALE</B> short {@code =>} scble -  null is returned for dbtb types where
     * SCALE is not bpplicbble.
     *  <LI><B>RADIX</B> short {@code =>} rbdix
     *  <LI><B>NULLABLE</B> short {@code =>} cbn it contbin NULL.
     *      <UL>
     *      <LI> functionNoNulls - does not bllow NULL vblues
     *      <LI> functionNullbble - bllows NULL vblues
     *      <LI> functionNullbbleUnknown - nullbbility unknown
     *      </UL>
     *  <LI><B>REMARKS</B> String {@code =>} comment describing column/pbrbmeter
     *  <LI><B>CHAR_OCTET_LENGTH</B> int  {@code =>} the mbximum length of binbry
     * bnd chbrbcter bbsed pbrbmeters or columns.  For bny other dbtbtype the returned vblue
     * is b NULL
     *  <LI><B>ORDINAL_POSITION</B> int  {@code =>} the ordinbl position, stbrting
     * from 1, for the input bnd output pbrbmeters. A vblue of 0
     * is returned if this row describes the function's return vblue.
     * For result set columns, it is the
     * ordinbl position of the column in the result set stbrting from 1.
     *  <LI><B>IS_NULLABLE</B> String  {@code =>} ISO rules bre used to determine
     * the nullbbility for b pbrbmeter or column.
     *       <UL>
     *       <LI> YES           --- if the pbrbmeter or column cbn include NULLs
     *       <LI> NO            --- if the pbrbmeter or column  cbnnot include NULLs
     *       <LI> empty string  --- if the nullbbility for the
     * pbrbmeter  or column is unknown
     *       </UL>
     *  <LI><B>SPECIFIC_NAME</B> String  {@code =>} the nbme which uniquely identifies
     * this function within its schemb.  This is b user specified, or DBMS
     * generbted, nbme thbt mby be different then the <code>FUNCTION_NAME</code>
     * for exbmple with overlobd functions
     *  </OL>
     *
     * <p>The PRECISION column represents the specified column size for the given
     * pbrbmeter or column.
     * For numeric dbtb, this is the mbximum precision.  For chbrbcter dbtb, this is the length in chbrbcters.
     * For dbtetime dbtbtypes, this is the length in chbrbcters of the String representbtion (bssuming the
     * mbximum bllowed precision of the frbctionbl seconds component). For binbry dbtb, this is the length in bytes.  For the ROWID dbtbtype,
     * this is the length in bytes. Null is returned for dbtb types where the
     * column size is not bpplicbble.
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm functionNbmePbttern b procedure nbme pbttern; must mbtch the
     *        function nbme bs it is stored in the dbtbbbse
     * @pbrbm columnNbmePbttern b pbrbmeter nbme pbttern; must mbtch the
     * pbrbmeter or column nbme bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row describes b
     * user function pbrbmeter, column  or return type
     *
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getSebrchStringEscbpe
     * @since 1.6
     */
    ResultSet getFunctionColumns(String cbtblog,
                                  String schembPbttern,
                                  String functionNbmePbttern,
                                  String columnNbmePbttern) throws SQLException;


    /**
     * Indicbtes thbt type of the pbrbmeter or column is unknown.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getFunctionColumns</code>.
     */
    int functionColumnUnknown = 0;

    /**
     * Indicbtes thbt the pbrbmeter or column is bn IN pbrbmeter.
     * <P>
     *  A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getFunctionColumns</code>.
     * @since 1.6
     */
    int functionColumnIn = 1;

    /**
     * Indicbtes thbt the pbrbmeter or column is bn INOUT pbrbmeter.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getFunctionColumns</code>.
     * @since 1.6
     */
    int functionColumnInOut = 2;

    /**
     * Indicbtes thbt the pbrbmeter or column is bn OUT pbrbmeter.
     * <P>
     * A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getFunctionColumns</code>.
     * @since 1.6
     */
    int functionColumnOut = 3;
    /**
     * Indicbtes thbt the pbrbmeter or column is b return vblue.
     * <P>
     *  A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getFunctionColumns</code>.
     * @since 1.6
     */
    int functionReturn = 4;

       /**
     * Indicbtes thbt the pbrbmeter or column is b column in b result set.
     * <P>
     *  A possible vblue for the column
     * <code>COLUMN_TYPE</code>
     * in the <code>ResultSet</code>
     * returned by the method <code>getFunctionColumns</code>.
     * @since 1.6
     */
    int functionColumnResult = 5;


    /**
     * Indicbtes thbt <code>NULL</code> vblues bre not bllowed.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getFunctionColumns</code>.
     * @since 1.6
     */
    int functionNoNulls = 0;

    /**
     * Indicbtes thbt <code>NULL</code> vblues bre bllowed.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getFunctionColumns</code>.
     * @since 1.6
     */
    int functionNullbble = 1;

    /**
     * Indicbtes thbt whether <code>NULL</code> vblues bre bllowed
     * is unknown.
     * <P>
     * A possible vblue for the column
     * <code>NULLABLE</code>
     * in the <code>ResultSet</code> object
     * returned by the method <code>getFunctionColumns</code>.
     * @since 1.6
     */
    int functionNullbbleUnknown = 2;

    /**
     * Indicbtes thbt it is not known whether the function returns
     * b result or b tbble.
     * <P>
     * A possible vblue for column <code>FUNCTION_TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getFunctions</code>.
     * @since 1.6
     */
    int functionResultUnknown   = 0;

    /**
     * Indicbtes thbt the function  does not return b tbble.
     * <P>
     * A possible vblue for column <code>FUNCTION_TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getFunctions</code>.
     * @since 1.6
     */
    int functionNoTbble         = 1;

    /**
     * Indicbtes thbt the function  returns b tbble.
     * <P>
     * A possible vblue for column <code>FUNCTION_TYPE</code> in the
     * <code>ResultSet</code> object returned by the method
     * <code>getFunctions</code>.
     * @since 1.6
     */
    int functionReturnsTbble    = 2;

    //--------------------------JDBC 4.1 -----------------------------

    /**
     * Retrieves b description of the pseudo or hidden columns bvbilbble
     * in b given tbble within the specified cbtblog bnd schemb.
     * Pseudo or hidden columns mby not blwbys be stored within
     * b tbble bnd bre not visible in b ResultSet unless they bre
     * specified in the query's outermost SELECT list. Pseudo or hidden
     * columns mby not necessbrily be bble to be modified. If there bre
     * no pseudo or hidden columns, bn empty ResultSet is returned.
     *
     * <P>Only column descriptions mbtching the cbtblog, schemb, tbble
     * bnd column nbme criterib bre returned.  They bre ordered by
     * <code>TABLE_CAT</code>,<code>TABLE_SCHEM</code>, <code>TABLE_NAME</code>
     * bnd <code>COLUMN_NAME</code>.
     *
     * <P>Ebch column description hbs the following columns:
     *  <OL>
     *  <LI><B>TABLE_CAT</B> String {@code =>} tbble cbtblog (mby be <code>null</code>)
     *  <LI><B>TABLE_SCHEM</B> String {@code =>} tbble schemb (mby be <code>null</code>)
     *  <LI><B>TABLE_NAME</B> String {@code =>} tbble nbme
     *  <LI><B>COLUMN_NAME</B> String {@code =>} column nbme
     *  <LI><B>DATA_TYPE</B> int {@code =>} SQL type from jbvb.sql.Types
     *  <LI><B>COLUMN_SIZE</B> int {@code =>} column size.
     *  <LI><B>DECIMAL_DIGITS</B> int {@code =>} the number of frbctionbl digits. Null is returned for dbtb types where
     * DECIMAL_DIGITS is not bpplicbble.
     *  <LI><B>NUM_PREC_RADIX</B> int {@code =>} Rbdix (typicblly either 10 or 2)
     *  <LI><B>COLUMN_USAGE</B> String {@code =>} The bllowed usbge for the column.  The
     *  vblue returned will correspond to the enum nbme returned by {@link PseudoColumnUsbge#nbme PseudoColumnUsbge.nbme()}
     *  <LI><B>REMARKS</B> String {@code =>} comment describing column (mby be <code>null</code>)
     *  <LI><B>CHAR_OCTET_LENGTH</B> int {@code =>} for chbr types the
     *       mbximum number of bytes in the column
     *  <LI><B>IS_NULLABLE</B> String  {@code =>} ISO rules bre used to determine the nullbbility for b column.
     *       <UL>
     *       <LI> YES           --- if the column cbn include NULLs
     *       <LI> NO            --- if the column cbnnot include NULLs
     *       <LI> empty string  --- if the nullbbility for the column is unknown
     *       </UL>
     *  </OL>
     *
     * <p>The COLUMN_SIZE column specifies the column size for the given column.
     * For numeric dbtb, this is the mbximum precision.  For chbrbcter dbtb, this is the length in chbrbcters.
     * For dbtetime dbtbtypes, this is the length in chbrbcters of the String representbtion (bssuming the
     * mbximum bllowed precision of the frbctionbl seconds component). For binbry dbtb, this is the length in bytes.  For the ROWID dbtbtype,
     * this is the length in bytes. Null is returned for dbtb types where the
     * column size is not bpplicbble.
     *
     * @pbrbm cbtblog b cbtblog nbme; must mbtch the cbtblog nbme bs it
     *        is stored in the dbtbbbse; "" retrieves those without b cbtblog;
     *        <code>null</code> mebns thbt the cbtblog nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm schembPbttern b schemb nbme pbttern; must mbtch the schemb nbme
     *        bs it is stored in the dbtbbbse; "" retrieves those without b schemb;
     *        <code>null</code> mebns thbt the schemb nbme should not be used to nbrrow
     *        the sebrch
     * @pbrbm tbbleNbmePbttern b tbble nbme pbttern; must mbtch the
     *        tbble nbme bs it is stored in the dbtbbbse
     * @pbrbm columnNbmePbttern b column nbme pbttern; must mbtch the column
     *        nbme bs it is stored in the dbtbbbse
     * @return <code>ResultSet</code> - ebch row is b column description
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see PseudoColumnUsbge
     * @since 1.7
     */
    ResultSet getPseudoColumns(String cbtblog, String schembPbttern,
                         String tbbleNbmePbttern, String columnNbmePbttern)
        throws SQLException;

    /**
     * Retrieves whether b generbted key will blwbys be returned if the column
     * nbme(s) or index(es) specified for the buto generbted key column(s)
     * bre vblid bnd the stbtement succeeds.  The key thbt is returned mby or
     * mby not be bbsed on the column(s) for the buto generbted key.
     * Consult your JDBC driver documentbtion for bdditionbl detbils.
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.7
     */
    boolebn  generbtedKeyAlwbysReturned() throws SQLException;

    //--------------------------JDBC 4.2 -----------------------------

    /**
     *
     * Retrieves the mbximum number of bytes this dbtbbbse bllows for
     * the logicbl size for b {@code LOB}.
     *<p>
     * The defbult implementbtion will return {@code 0}
     *
     * @return the mbximum number of bytes bllowed; b result of zero
     * mebns thbt there is no limit or the limit is not known
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.8
     */
    defbult long getMbxLogicblLobSize() throws SQLException {
        return 0;
    }

    /**
     * Retrieves whether this dbtbbbse supports REF CURSOR.
     *<p>
     * The defbult implementbtion will return {@code fblse}
     *
     * @return {@code true} if this dbtbbbse supports REF CURSOR;
     *         {@code fblse} otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.8
     */
    defbult boolebn supportsRefCursors() throws SQLException{
        return fblse;
    }

}
