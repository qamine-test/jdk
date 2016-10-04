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
import jbvb.io.Rebder;

import jbvbx.sql.RowSetRebder;
import jbvbx.sql.rowset.*;

/**
 * A speciblized interfbce thbt fbcilitbtes bn extension of the
 * <code>SyncProvider</code> bbstrbct clbss for XML orientbted
 * synchronizbtion providers.
 * <P>
 * <code>SyncProvider</code>  implementbtions thbt supply XML dbtb rebder
 * cbpbbilities such bs output XML strebm cbpbbilities cbn implement this
 * interfbce to provide stbndbrd <code>XmlRebder</code> objects to
 * <code>WebRowSet</code> implementbtions.
 * <p>
 * An <code>XmlRebder</code> object is registered bs the
 * XML rebder for b <code>WebRowSet</code> by being bssigned to the
 * rowset's <code>xmlRebder</code> field. When the <code>WebRowSet</code>
 * object's <code>rebdXml</code> method is invoked, it in turn invokes
 * its XML rebder's <code>rebdXML</code> method.
 *
 * @since 1.5
 */
public interfbce XmlRebder extends RowSetRebder {

  /**
   * Rebds bnd pbrses the given <code>WebRowSet</code> object from the given
   * input strebm in XML formbt. The <code>xmlRebder</code> field of the
   * given <code>WebRowSet</code> object must contbin this
   * <code>XmlRebder</code> object.
   * <P>
   * If b pbrsing error occurs, the exception thbt is thrown will
   * include informbtion bbout the locbtion of the error in the
   * originbl XML document.
   *
   * @pbrbm cbller the <code>WebRowSet</code> object to be pbrsed, whose
   *        <code>xmlRebder</code> field must contbin b reference to
   *        this <code>XmlRebder</code> object
   * @pbrbm rebder the <code>jbvb.io.Rebder</code> object from which
   *        <code>cbller</code> will be rebd
   * @throws SQLException if b dbtbbbse bccess error occurs or
   *            this <code>XmlRebder</code> object is not the rebder
   *            for the given rowset
   */
  public void rebdXML(WebRowSet cbller, jbvb.io.Rebder rebder)
    throws SQLException;

}
