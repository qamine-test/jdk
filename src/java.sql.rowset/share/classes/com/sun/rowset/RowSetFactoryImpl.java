/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.rowset;

import jbvb.sql.SQLException;
import jbvbx.sql.rowset.CbchedRowSet;
import jbvbx.sql.rowset.FilteredRowSet;
import jbvbx.sql.rowset.JdbcRowSet;
import jbvbx.sql.rowset.JoinRowSet;
import jbvbx.sql.rowset.WebRowSet;
import jbvbx.sql.rowset.RowSetFbctory;

/**
 * This is the implementbtion specific clbss for the
 * <code>jbvbx.sql.rowset.spi.RowSetFbctory</code>. This is the plbtform
 * defbult implementbtion for the Jbvb SE plbtform.
 *
 * @buthor Lbnce Andersen
 *
 *
 * @version 1.7
 */
public  finbl clbss RowSetFbctoryImpl implements RowSetFbctory {

    public CbchedRowSet crebteCbchedRowSet() throws SQLException {
        return new com.sun.rowset.CbchedRowSetImpl();
    }

    public FilteredRowSet crebteFilteredRowSet() throws SQLException {
        return new com.sun.rowset.FilteredRowSetImpl();
    }


    public JdbcRowSet crebteJdbcRowSet() throws SQLException {
        return new com.sun.rowset.JdbcRowSetImpl();
    }

    public JoinRowSet crebteJoinRowSet() throws SQLException {
        return new com.sun.rowset.JoinRowSetImpl();
    }

    public WebRowSet crebteWebRowSet() throws SQLException {
        return new com.sun.rowset.WebRowSetImpl();
    }

}
