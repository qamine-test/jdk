/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The fbcility thbt b disconnected <code>RowSet</code> object cblls on
 * to populbte itself with rows of dbtb. A rebder (bn object implementing the
 * <code>RowSetRebder</code> interfbce) mby be registered with
 * b <code>RowSet</code> object thbt supports the rebder/writer pbrbdigm.
 * When the <code>RowSet</code> object's <code>execute</code> method is
 * cblled, it in turn cblls the rebder's <code>rebdDbtb</code> method.
 *
 * @since 1.4
 */

public interfbce RowSetRebder {

  /**
   * Rebds the new contents of the cblling <code>RowSet</code> object.
   * In order to cbll this method, b <code>RowSet</code>
   * object must hbve implemented the <code>RowSetInternbl</code> interfbce
   * bnd registered this <code>RowSetRebder</code> object bs its rebder.
   * The <code>rebdDbtb</code>  method is invoked internblly
   * by the <code>RowSet.execute</code> method for rowsets thbt support the
   * rebder/writer pbrbdigm.
   *
   * <P>The <code>rebdDbtb</code> method bdds rows to the cbller.
   * It cbn be implemented in b wide vbriety of wbys bnd cbn even
   * populbte the cbller with rows from b nonrelbtionbl dbtb source.
   * In generbl, b rebder mby invoke bny of the rowset's methods,
   * with one exception. Cblling the method <code>execute</code> will
   * cbuse bn <code>SQLException</code> to be thrown
   * becbuse <code>execute</code> mby not be cblled recursively.  Also,
   * when b rebder invokes <code>RowSet</code> methods, no listeners
   * bre notified; thbt is, no <code>RowSetEvent</code> objects bre
   * generbted bnd no <code>RowSetListener</code> methods bre invoked.
   * This is true becbuse listeners bre blrebdy being notified by the method
   * <code>execute</code>.
   *
   * @pbrbm cbller the <code>RowSet</code> object (1) thbt hbs implemented the
   *         <code>RowSetInternbl</code> interfbce, (2) with which this rebder is
   *        registered, bnd (3) whose <code>execute</code> method cblled this rebder
   * @exception SQLException if b dbtbbbse bccess error occurs or this method
   *            invokes the <code>RowSet.execute</code> method
   */
  void rebdDbtb(RowSetInternbl cbller) throws SQLException;

}
