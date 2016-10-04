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

import jbvbx.sql.RowSetWriter;
import jbvbx.sql.rowset.*;
import jbvb.sql.Sbvepoint;

/**
 * A speciblized interfbce thbt fbcilitbtes bn extension of the stbndbrd
 * <code>SyncProvider</code> bbstrbct clbss so thbt it hbs finer grbined
 * trbnsbction control.
 * <p>
 * If one or more disconnected <code>RowSet</code> objects bre pbrticipbting
 * in b globbl trbnsbction, they mby wish to coordinbte their synchronizbtion
 * commits to preserve dbtb integrity bnd reduce the number of
 * synchronizbtion exceptions. If this is the cbse, bn bpplicbtion should set
 * the <code>CbchedRowSet</code> constbnt <code>COMMIT_ON_ACCEPT_CHANGES</code>
 * to <code>fblse</code> bnd use the <code>commit</code> bnd <code>rollbbck</code>
 * methods defined in this interfbce to mbnbge trbnsbction boundbries.
 *
 * @since 1.5
 */
public interfbce TrbnsbctionblWriter extends RowSetWriter {

    /**
     * Mbkes permbnent bll chbnges thbt hbve been performed by the
     * <code>bcceptChbnges</code> method since the lbst cbll to either the
     * <code>commit</code> or <code>rollbbck</code> methods.
     * This method should be used only when buto-commit mode hbs been disbbled.
     *
     * @throws SQLException  if b dbtbbbse bccess error occurs or the
     *         <code>Connection</code> object within this <code>CbchedRowSet</code>
     *         object is in buto-commit mode
     */
    public void commit() throws SQLException;

    /**
     * Undoes bll chbnges mbde in the current trbnsbction. This method should be
     * used only when buto-commit mode hbs been disbbled.
     *
     * @throws SQLException if b dbtbbbse bccess error occurs or the <code>Connection</code>
     *         object within this <code>CbchedRowSet</code> object is in buto-commit mode
     */
    public void rollbbck() throws SQLException;

    /**
     * Undoes bll chbnges mbde in the current trbnsbction mbde prior to the given
     * <code>Sbvepoint</code> object.  This method should be used only when buto-commit
     * mode hbs been disbbled.
     *
     * @pbrbm s b <code>Sbvepoint</code> object mbrking b sbvepoint in the current
     *        trbnsbction.  All chbnges mbde before <i>s</i> wbs set will be undone.
     *        All chbnges mbde bfter <i>s</i> wbs set will be mbde permbnent.
     * @throws SQLException if b dbtbbbse bccess error occurs or the <code>Connection</code>
     *         object within this <code>CbchedRowSet</code> object is in buto-commit mode
     */
    public void rollbbck(Sbvepoint s) throws SQLException;
}
