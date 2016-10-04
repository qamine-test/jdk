/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * An object thbt provides support for distributed
 * trbnsbctions.  An <code>XAConnection</code> object  mby be enlisted
 * in b distributed trbnsbction by mebns of bn <code>XAResource</code> object.
 * A trbnsbction mbnbger, usublly pbrt of b middle tier server, mbnbges bn
 * <code>XAConnection</code> object through the <code>XAResource</code> object.
 * <P>
 * An bpplicbtion progrbmmer does not use this interfbce directly; rbther,
 * it is used by b trbnsbction mbnbger working in the middle tier server.
 *
 * @since 1.4
 */

public interfbce XAConnection extends PooledConnection {


  /**
   * Retrieves bn <code>XAResource</code> object thbt
   * the trbnsbction mbnbger will use
   * to mbnbge this <code>XAConnection</code> object's pbrticipbtion in b
   * distributed trbnsbction.
   *
   * @return the <code>XAResource</code> object
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.4
   */
  jbvbx.trbnsbction.xb.XAResource getXAResource() throws SQLException;

 }
