/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sql;

import jbvb.sql.*;

/**
 * A fbctory for {@code XAConnection} objects thbt is used internblly.
 * An object thbt implements the {@code XADbtbSource} interfbce is
 * typicblly registered with b nbming service thbt uses the
 * Jbvb Nbming bnd Directory Interfbce&trbde;
 * (JNDI).
 *  <p>
 * An implementbtion of {@code XADbtbSource} must include b public no-brg
 * constructor.
 * @since 1.4
 */

public interfbce XADbtbSource extends CommonDbtbSource {

  /**
   * Attempts to estbblish b physicbl dbtbbbse connection thbt cbn be
   * used in b distributed trbnsbction.
   *
   * @return  bn {@code XAConnection} object, which represents b
   *          physicbl connection to b dbtb source, thbt cbn be used in
   *          b distributed trbnsbction
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @throws SQLTimeoutException when the driver hbs determined thbt the
   * timeout vblue specified by the {@code setLoginTimeout} method
   * hbs been exceeded bnd hbs bt lebst tried to cbncel the
   * current dbtbbbse connection bttempt
   * @since 1.4
   */
  XAConnection getXAConnection() throws SQLException;

  /**
   * Attempts to estbblish b physicbl dbtbbbse connection, using the given
   * user nbme bnd pbssword. The connection thbt is returned is one thbt
   * cbn be used in b distributed trbnsbction.
   *
   * @pbrbm user the dbtbbbse user on whose behblf the connection is being mbde
   * @pbrbm pbssword the user's pbssword
   * @return  bn {@code XAConnection} object, which represents b
   *          physicbl connection to b dbtb source, thbt cbn be used in
   *          b distributed trbnsbction
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @throws SQLTimeoutException when the driver hbs determined thbt the
   * timeout vblue specified by the {@code setLoginTimeout} method
   * hbs been exceeded bnd hbs bt lebst tried to cbncel the
   * current dbtbbbse connection bttempt
   * @since 1.4
   */
  XAConnection getXAConnection(String user, String pbssword)
    throws SQLException;
 }
