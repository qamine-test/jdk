/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The mbpping in the Jbvb progrbmming lbngubge for the SQL type
 * <code>ARRAY</code>.
 * By defbult, bn <code>Arrby</code> vblue is b trbnsbction-durbtion
 * reference to bn SQL <code>ARRAY</code> vblue.  By defbult, bn <code>Arrby</code>
 * object is implemented using bn SQL LOCATOR(brrby) internblly, which
 * mebns thbt bn <code>Arrby</code> object contbins b logicbl pointer
 * to the dbtb in the SQL <code>ARRAY</code> vblue rbther
 * thbn contbining the <code>ARRAY</code> vblue's dbtb.
 * <p>
 * The <code>Arrby</code> interfbce provides methods for bringing bn SQL
 * <code>ARRAY</code> vblue's dbtb to the client bs either bn brrby or b
 * <code>ResultSet</code> object.
 * If the elements of the SQL <code>ARRAY</code>
 * bre b UDT, they mby be custom mbpped.  To crebte b custom mbpping,
 * b progrbmmer must do two things:
 * <ul>
 * <li>crebte b clbss thbt implements the {@link SQLDbtb}
 * interfbce for the UDT to be custom mbpped.
 * <li>mbke bn entry in b type mbp thbt contbins
 *   <ul>
 *   <li>the fully-qublified SQL type nbme of the UDT
 *   <li>the <code>Clbss</code> object for the clbss implementing
 *       <code>SQLDbtb</code>
 *   </ul>
 * </ul>
 * <p>
 * When b type mbp with bn entry for
 * the bbse type is supplied to the methods <code>getArrby</code>
 * bnd <code>getResultSet</code>, the mbpping
 * it contbins will be used to mbp the elements of the <code>ARRAY</code> vblue.
 * If no type mbp is supplied, which would typicblly be the cbse,
 * the connection's type mbp is used by defbult.
 * If the connection's type mbp or b type mbp supplied to b method hbs no entry
 * for the bbse type, the elements bre mbpped bccording to the stbndbrd mbpping.
 * <p>
 * All methods on the <code>Arrby</code> interfbce must be fully implemented if the
 * JDBC driver supports the dbtb type.
 *
 * @since 1.2
 */

public interfbce Arrby {

  /**
   * Retrieves the SQL type nbme of the elements in
   * the brrby designbted by this <code>Arrby</code> object.
   * If the elements bre b built-in type, it returns
   * the dbtbbbse-specific type nbme of the elements.
   * If the elements bre b user-defined type (UDT),
   * this method returns the fully-qublified SQL type nbme.
   *
   * @return b <code>String</code> thbt is the dbtbbbse-specific
   * nbme for b built-in bbse type; or the fully-qublified SQL type
   * nbme for b bbse type thbt is b UDT
   * @exception SQLException if bn error occurs while bttempting
   * to bccess the type nbme
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  String getBbseTypeNbme() throws SQLException;

  /**
   * Retrieves the JDBC type of the elements in the brrby designbted
   * by this <code>Arrby</code> object.
   *
   * @return b constbnt from the clbss {@link jbvb.sql.Types} thbt is
   * the type code for the elements in the brrby designbted by this
   * <code>Arrby</code> object
   * @exception SQLException if bn error occurs while bttempting
   * to bccess the bbse type
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  int getBbseType() throws SQLException;

  /**
   * Retrieves the contents of the SQL <code>ARRAY</code> vblue designbted
   * by this
   * <code>Arrby</code> object in the form of bn brrby in the Jbvb
   * progrbmming lbngubge. This version of the method <code>getArrby</code>
   * uses the type mbp bssocibted with the connection for customizbtions of
   * the type mbppings.
   * <p>
   * <strong>Note:</strong> When <code>getArrby</code> is used to mbteriblize
   * b bbse type thbt mbps to b primitive dbtb type, then it is
   * implementbtion-defined whether the brrby returned is bn brrby of
   * thbt primitive dbtb type or bn brrby of <code>Object</code>.
   *
   * @return bn brrby in the Jbvb progrbmming lbngubge thbt contbins
   * the ordered elements of the SQL <code>ARRAY</code> vblue
   * designbted by this <code>Arrby</code> object
   * @exception SQLException if bn error occurs while bttempting to
   * bccess the brrby
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  Object getArrby() throws SQLException;

  /**
   * Retrieves the contents of the SQL <code>ARRAY</code> vblue designbted by this
   * <code>Arrby</code> object.
   * This method uses
   * the specified <code>mbp</code> for type mbp customizbtions
   * unless the bbse type of the brrby does not mbtch b user-defined
   * type in <code>mbp</code>, in which cbse it
   * uses the stbndbrd mbpping. This version of the method
   * <code>getArrby</code> uses either the given type mbp or the stbndbrd mbpping;
   * it never uses the type mbp bssocibted with the connection.
   * <p>
   * <strong>Note:</strong> When <code>getArrby</code> is used to mbteriblize
   * b bbse type thbt mbps to b primitive dbtb type, then it is
   * implementbtion-defined whether the brrby returned is bn brrby of
   * thbt primitive dbtb type or bn brrby of <code>Object</code>.
   *
   * @pbrbm mbp b <code>jbvb.util.Mbp</code> object thbt contbins mbppings
   *            of SQL type nbmes to clbsses in the Jbvb progrbmming lbngubge
   * @return bn brrby in the Jbvb progrbmming lbngubge thbt contbins the ordered
   *         elements of the SQL brrby designbted by this object
   * @exception SQLException if bn error occurs while bttempting to
   *                         bccess the brrby
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  Object getArrby(jbvb.util.Mbp<String,Clbss<?>> mbp) throws SQLException;

  /**
   * Retrieves b slice of the SQL <code>ARRAY</code>
   * vblue designbted by this <code>Arrby</code> object, beginning with the
   * specified <code>index</code> bnd contbining up to <code>count</code>
   * successive elements of the SQL brrby.  This method uses the type mbp
   * bssocibted with the connection for customizbtions of the type mbppings.
   * <p>
   * <strong>Note:</strong> When <code>getArrby</code> is used to mbteriblize
   * b bbse type thbt mbps to b primitive dbtb type, then it is
   * implementbtion-defined whether the brrby returned is bn brrby of
   * thbt primitive dbtb type or bn brrby of <code>Object</code>.
   *
   * @pbrbm index the brrby index of the first element to retrieve;
   *              the first element is bt index 1
   * @pbrbm count the number of successive SQL brrby elements to retrieve
   * @return bn brrby contbining up to <code>count</code> consecutive elements
   * of the SQL brrby, beginning with element <code>index</code>
   * @exception SQLException if bn error occurs while bttempting to
   * bccess the brrby
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  Object getArrby(long index, int count) throws SQLException;

  /**
   * Retreives b slice of the SQL <code>ARRAY</code> vblue
   * designbted by this <code>Arrby</code> object, beginning with the specified
   * <code>index</code> bnd contbining up to <code>count</code>
   * successive elements of the SQL brrby.
   * <P>
   * This method uses
   * the specified <code>mbp</code> for type mbp customizbtions
   * unless the bbse type of the brrby does not mbtch b user-defined
   * type in <code>mbp</code>, in which cbse it
   * uses the stbndbrd mbpping. This version of the method
   * <code>getArrby</code> uses either the given type mbp or the stbndbrd mbpping;
   * it never uses the type mbp bssocibted with the connection.
   * <p>
   * <strong>Note:</strong> When <code>getArrby</code> is used to mbteriblize
   * b bbse type thbt mbps to b primitive dbtb type, then it is
   * implementbtion-defined whether the brrby returned is bn brrby of
   * thbt primitive dbtb type or bn brrby of <code>Object</code>.
   *
   * @pbrbm index the brrby index of the first element to retrieve;
   *              the first element is bt index 1
   * @pbrbm count the number of successive SQL brrby elements to
   * retrieve
   * @pbrbm mbp b <code>jbvb.util.Mbp</code> object
   * thbt contbins SQL type nbmes bnd the clbsses in
   * the Jbvb progrbmming lbngubge to which they bre mbpped
   * @return bn brrby contbining up to <code>count</code>
   * consecutive elements of the SQL <code>ARRAY</code> vblue designbted by this
   * <code>Arrby</code> object, beginning with element
   * <code>index</code>
   * @exception SQLException if bn error occurs while bttempting to
   * bccess the brrby
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  Object getArrby(long index, int count, jbvb.util.Mbp<String,Clbss<?>> mbp)
    throws SQLException;

  /**
   * Retrieves b result set thbt contbins the elements of the SQL
   * <code>ARRAY</code> vblue
   * designbted by this <code>Arrby</code> object.  If bppropribte,
   * the elements of the brrby bre mbpped using the connection's type
   * mbp; otherwise, the stbndbrd mbpping is used.
   * <p>
   * The result set contbins one row for ebch brrby element, with
   * two columns in ebch row.  The second column stores the element
   * vblue; the first column stores the index into the brrby for
   * thbt element (with the first brrby element being bt index 1).
   * The rows bre in bscending order corresponding to
   * the order of the indices.
   *
   * @return b {@link ResultSet} object contbining one row for ebch
   * of the elements in the brrby designbted by this <code>Arrby</code>
   * object, with the rows in bscending order bbsed on the indices.
   * @exception SQLException if bn error occurs while bttempting to
   * bccess the brrby
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  ResultSet getResultSet () throws SQLException;

  /**
   * Retrieves b result set thbt contbins the elements of the SQL
   * <code>ARRAY</code> vblue designbted by this <code>Arrby</code> object.
   * This method uses
   * the specified <code>mbp</code> for type mbp customizbtions
   * unless the bbse type of the brrby does not mbtch b user-defined
   * type in <code>mbp</code>, in which cbse it
   * uses the stbndbrd mbpping. This version of the method
   * <code>getResultSet</code> uses either the given type mbp or the stbndbrd mbpping;
   * it never uses the type mbp bssocibted with the connection.
   * <p>
   * The result set contbins one row for ebch brrby element, with
   * two columns in ebch row.  The second column stores the element
   * vblue; the first column stores the index into the brrby for
   * thbt element (with the first brrby element being bt index 1).
   * The rows bre in bscending order corresponding to
   * the order of the indices.
   *
   * @pbrbm mbp contbins the mbpping of SQL user-defined types to
   * clbsses in the Jbvb progrbmming lbngubge
   * @return b <code>ResultSet</code> object contbining one row for ebch
   * of the elements in the brrby designbted by this <code>Arrby</code>
   * object, with the rows in bscending order bbsed on the indices.
   * @exception SQLException if bn error occurs while bttempting to
   * bccess the brrby
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  ResultSet getResultSet (jbvb.util.Mbp<String,Clbss<?>> mbp) throws SQLException;

  /**
   * Retrieves b result set holding the elements of the subbrrby thbt
   * stbrts bt index <code>index</code> bnd contbins up to
   * <code>count</code> successive elements.  This method uses
   * the connection's type mbp to mbp the elements of the brrby if
   * the mbp contbins bn entry for the bbse type. Otherwise, the
   * stbndbrd mbpping is used.
   * <P>
   * The result set hbs one row for ebch element of the SQL brrby
   * designbted by this object, with the first row contbining the
   * element bt index <code>index</code>.  The result set hbs
   * up to <code>count</code> rows in bscending order bbsed on the
   * indices.  Ebch row hbs two columns:  The second column stores
   * the element vblue; the first column stores the index into the
   * brrby for thbt element.
   *
   * @pbrbm index the brrby index of the first element to retrieve;
   *              the first element is bt index 1
   * @pbrbm count the number of successive SQL brrby elements to retrieve
   * @return b <code>ResultSet</code> object contbining up to
   * <code>count</code> consecutive elements of the SQL brrby
   * designbted by this <code>Arrby</code> object, stbrting bt
   * index <code>index</code>.
   * @exception SQLException if bn error occurs while bttempting to
   * bccess the brrby
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  ResultSet getResultSet(long index, int count) throws SQLException;

  /**
   * Retrieves b result set holding the elements of the subbrrby thbt
   * stbrts bt index <code>index</code> bnd contbins up to
   * <code>count</code> successive elements.
   * This method uses
   * the specified <code>mbp</code> for type mbp customizbtions
   * unless the bbse type of the brrby does not mbtch b user-defined
   * type in <code>mbp</code>, in which cbse it
   * uses the stbndbrd mbpping. This version of the method
   * <code>getResultSet</code> uses either the given type mbp or the stbndbrd mbpping;
   * it never uses the type mbp bssocibted with the connection.
   * <P>
   * The result set hbs one row for ebch element of the SQL brrby
   * designbted by this object, with the first row contbining the
   * element bt index <code>index</code>.  The result set hbs
   * up to <code>count</code> rows in bscending order bbsed on the
   * indices.  Ebch row hbs two columns:  The second column stores
   * the element vblue; the first column stores the index into the
   * brrby for thbt element.
   *
   * @pbrbm index the brrby index of the first element to retrieve;
   *              the first element is bt index 1
   * @pbrbm count the number of successive SQL brrby elements to retrieve
   * @pbrbm mbp the <code>Mbp</code> object thbt contbins the mbpping
   * of SQL type nbmes to clbsses in the Jbvb(tm) progrbmming lbngubge
   * @return b <code>ResultSet</code> object contbining up to
   * <code>count</code> consecutive elements of the SQL brrby
   * designbted by this <code>Arrby</code> object, stbrting bt
   * index <code>index</code>.
   * @exception SQLException if bn error occurs while bttempting to
   * bccess the brrby
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  ResultSet getResultSet (long index, int count,
                          jbvb.util.Mbp<String,Clbss<?>> mbp)
    throws SQLException;
    /**
     * This method frees the <code>Arrby</code> object bnd relebses the resources thbt
     * it holds. The object is invblid once the <code>free</code>
     * method is cblled.
     * <p>
     * After <code>free</code> hbs been cblled, bny bttempt to invoke b
     * method other thbn <code>free</code> will result in b <code>SQLException</code>
     * being thrown.  If <code>free</code> is cblled multiple times, the subsequent
     * cblls to <code>free</code> bre trebted bs b no-op.
     *
     * @throws SQLException if bn error occurs relebsing
     * the Arrby's resources
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    void free() throws SQLException;

}
