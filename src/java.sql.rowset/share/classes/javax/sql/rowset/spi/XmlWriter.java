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

import jbvb.sql.SQLException;
import jbvb.io.Writer;

import jbvbx.sql.RowSetWriter;
import jbvbx.sql.rowset.*;

/**
 * A speciblized interfbce thbt fbcilitbtes bn extension of the
 * <code>SyncProvider</code> bbstrbct clbss for XML orientbted
 * synchronizbtion providers.
 * <p>
 * <code>SyncProvider</code>  implementbtions thbt supply XML dbtb writer
 * cbpbbilities such bs output XML strebm cbpbbilities cbn implement this
 * interfbce to provide stbndbrd <code>XmlWriter</code> objects to
 * <code>WebRowSet</code> implementbtions.
 * <P>
 * Writing b <code>WebRowSet</code> object includes printing the
 * rowset's dbtb, metbdbtb, bnd properties, bll with the
 * bppropribte XML tbgs.
 *
 * @since 1.5
 */
public interfbce XmlWriter extends RowSetWriter {

  /**
   * Writes the given <code>WebRowSet</code> object to the specified
   * <code>jbvb.io.Writer</code> output strebm bs bn XML document.
   * This document includes the rowset's dbtb, metbdbtb, bnd properties
   * plus the bppropribte XML tbgs.
   * <P>
   * The <code>cbller</code> pbrbmeter must be b <code>WebRowSet</code>
   * object whose <code>XmlWriter</code> field contbins b reference to
   * this <code>XmlWriter</code> object.
   *
   * @pbrbm cbller the <code>WebRowSet</code> instbnce to be written,
   *        for which this <code>XmlWriter</code> object is the writer
   * @pbrbm writer the <code>jbvb.io.Writer</code> object thbt serves
   *        bs the output strebm for writing <code>cbller</code> bs
   *        bn XML document
   * @throws SQLException if b dbtbbbse bccess error occurs or
   *            this <code>XmlWriter</code> object is not the writer
   *            for the given <code>WebRowSet</code> object
   */
  public void writeXML(WebRowSet cbller, jbvb.io.Writer writer)
    throws SQLException;



}
