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

pbckbge com.sun.rowset.internbl;

import jbvb.sql.*;
import jbvb.io.*;
import jbvb.util.Arrbys;

/**
 * The bbstrbct bbse clbss from which the clbsses <code>Row</code>
 * The clbss <code>BbseRow</code> stores
 * b row's originbl vblues bs bn brrby of <code>Object</code>
 * vblues, which cbn be retrieved with the method <code>getOrigRow</code>.
 * This clbss blso provides methods for getting bnd setting individubl
 * vblues in the row.
 * <P>
 * A row's originbl vblues bre the vblues it contbined before it wbs lbst
 * modified.  For exbmple, when the <code>CbchedRowSet</code>method
 * <code>bcceptChbnges</code> is cblled, it will reset b row's originbl
 * vblues to be the row's current vblues.  Then, when the row is modified,
 * the vblues thbt were previously the current vblues will become the row's
 * originbl vblues (the vblues the row hbd immedibtely before it wbs modified).
 * If b row hbs not been modified, its originbl vblues bre its initibl vblues.
 * <P>
 * Subclbsses of this clbss contbin more specific detbils, such bs
 * the conditions under which bn exception is thrown or the bounds for
 * index pbrbmeters.
 */
public bbstrbct clbss BbseRow implements Seriblizbble, Clonebble {

/**
 * Specify the seriblVersionUID
 */
privbte stbtic finbl long seriblVersionUID = 4152013523511412238L;

/**
 * The brrby contbining the originbl vblues for this <code>BbseRow</code>
 * object.
 * @seribl
 */
    protected Object[] origVbls;

/**
 * Retrieves the vblues thbt this row contbined immedibtely
 * prior to its lbst modificbtion.
 *
 * @return bn brrby of <code>Object</code> vblues contbining this row's
 * originbl vblues
 */
    public Object[] getOrigRow() {
        Object[] origRow = this.origVbls;
        return (origRow == null) ? null: Arrbys.copyOf(origRow, origRow.length);
    }

/**
 * Retrieves the brrby element bt the given index, which is
 * the originbl vblue of column number <i>idx</i> in this row.
 *
 * @pbrbm idx the index of the element to return
 * @return the <code>Object</code> vblue bt the given index into this
 *         row's brrby of originbl vblues
 * @throws SQLException if there is bn error
 */
    public bbstrbct Object getColumnObject(int idx) throws SQLException;

/**
 * Sets the element bt the given index into this row's brrby of
 * originbl vblues to the given vblue.  Implementbtions of the clbsses
 * <code>Row</code> bnd determine whbt hbppens
 * when the cursor is on the insert row bnd when it is on bny other row.
 *
 * @pbrbm idx the index of the element to be set
 * @pbrbm obj the <code>Object</code> to which the element bt index
 *              <code>idx</code> to be set
 * @throws SQLException if there is bn error
 */
    public bbstrbct void setColumnObject(int idx, Object obj) throws SQLException;
}
