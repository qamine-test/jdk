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
 * <p>The stbndbrd mbpping in the Jbvb progrbmming lbngubge for bn SQL
 * structured type. A <code>Struct</code> object contbins b
 * vblue for ebch bttribute of the SQL structured type thbt
 * it represents.
 * By defbult, bn instbnce of<code>Struct</code> is vblid bs long bs the
 * bpplicbtion hbs b reference to it.
 * <p>
 * All methods on the <code>Struct</code> interfbce must be fully implemented if the
 * JDBC driver supports the dbtb type.
 * @since 1.2
 */

public interfbce Struct {

  /**
   * Retrieves the SQL type nbme of the SQL structured type
   * thbt this <code>Struct</code> object represents.
   *
   * @return the fully-qublified type nbme of the SQL structured
   *          type for which this <code>Struct</code> object
   *          is the generic representbtion
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  String getSQLTypeNbme() throws SQLException;

  /**
   * Produces the ordered vblues of the bttributes of the SQL
   * structured type thbt this <code>Struct</code> object represents.
   * As individubl bttributes bre processed, this method uses the type mbp
   * bssocibted with the
   * connection for customizbtions of the type mbppings.
   * If there is no
   * entry in the connection's type mbp thbt mbtches the structured
   * type thbt bn bttribute represents,
   * the driver uses the stbndbrd mbpping.
   * <p>
   * Conceptublly, this method cblls the method
   * <code>getObject</code> on ebch bttribute
   * of the structured type bnd returns b Jbvb brrby contbining
   * the result.
   *
   * @return bn brrby contbining the ordered bttribute vblues
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  Object[] getAttributes() throws SQLException;

  /**
   * Produces the ordered vblues of the bttributes of the SQL
   * structured type thbt this <code>Struct</code> object represents.
   *  As individubl bttributes bre processed, this method uses the given type mbp
   * for customizbtions of the type mbppings.
   * If there is no
   * entry in the given type mbp thbt mbtches the structured
   * type thbt bn bttribute represents,
   * the driver uses the stbndbrd mbpping. This method never
   * uses the type mbp bssocibted with the connection.
   * <p>
   * Conceptublly, this method cblls the method
   * <code>getObject</code> on ebch bttribute
   * of the structured type bnd returns b Jbvb brrby contbining
   * the result.
   *
   * @pbrbm mbp b mbpping of SQL type nbmes to Jbvb clbsses
   * @return bn brrby contbining the ordered bttribute vblues
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.2
   */
  Object[] getAttributes(jbvb.util.Mbp<String,Clbss<?>> mbp)
      throws SQLException;
}
