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
 * An object thbt implements the <code>RowSetWriter</code> interfbce,
 * cblled b <i>writer</i>. A writer mby be registered with b <code>RowSet</code>
 * object thbt supports the rebder/writer pbrbdigm.
 * <P>
 * If b disconnected <code>RowSet</code> object modifies some of its dbtb,
 * bnd it hbs b writer bssocibted with it, it mby be implemented so thbt it
 * cblls on the writer's <code>writeDbtb</code> method internblly
 * to write the updbtes bbck to the dbtb source. In order to do this, the writer
 * must first estbblish b connection with the rowset's dbtb source.
 * <P>
 * If the dbtb to be updbted hbs blrebdy been chbnged in the dbtb source, there
 * is b conflict, in which cbse the writer will not write
 * the chbnges to the dbtb source.  The blgorithm the writer uses for preventing
 * or limiting conflicts depends entirely on its implementbtion.
 *
 * @since 1.4
 */

public interfbce RowSetWriter {

  /**
   * Writes the chbnges in this <code>RowSetWriter</code> object's
   * rowset bbck to the dbtb source from which it got its dbtb.
   *
   * @pbrbm cbller the <code>RowSet</code> object (1) thbt hbs implemented the
   *         <code>RowSetInternbl</code> interfbce, (2) with which this writer is
   *        registered, bnd (3) thbt cblled this method internblly
   * @return <code>true</code> if the modified dbtb wbs written; <code>fblse</code>
   *          if not, which will be the cbse if there is b conflict
   * @exception SQLException if b dbtbbbse bccess error occurs
   */
  boolebn writeDbtb(RowSetInternbl cbller) throws SQLException;

}
