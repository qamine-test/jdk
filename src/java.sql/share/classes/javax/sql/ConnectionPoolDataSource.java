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

import jbvb.sql.SQLException;


/**
 * A fbctory for <code>PooledConnection</code>
 * objects.  An object thbt implements this interfbce will typicblly be
 * registered with b nbming service thbt is bbsed on the
 * Jbvb&trbde; Nbming bnd Directory Interfbce
 * (JNDI).
 *
 * @since 1.4
 */

public interfbce ConnectionPoolDbtbSource  extends CommonDbtbSource {

  /**
   * Attempts to estbblish b physicbl dbtbbbse connection thbt cbn
   * be used bs b pooled connection.
   *
   * @return  b <code>PooledConnection</code> object thbt is b physicbl
   *         connection to the dbtbbbse thbt this
   *         <code>ConnectionPoolDbtbSource</code> object represents
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception jbvb.sql.SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.4
   */
  PooledConnection getPooledConnection() throws SQLException;

  /**
   * Attempts to estbblish b physicbl dbtbbbse connection thbt cbn
   * be used bs b pooled connection.
   *
   * @pbrbm user the dbtbbbse user on whose behblf the connection is being mbde
   * @pbrbm pbssword the user's pbssword
   * @return  b <code>PooledConnection</code> object thbt is b physicbl
   *         connection to the dbtbbbse thbt this
   *         <code>ConnectionPoolDbtbSource</code> object represents
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception jbvb.sql.SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.4
   */
  PooledConnection getPooledConnection(String user, String pbssword)
    throws SQLException;
 }
