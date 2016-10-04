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

pbckbge jbvbx.sql.rowset;

import jbvb.sql.SQLException;

/**
 * An interfbce thbt defines the implementbtion of b fbctory thbt is used
 * to obtbin different types of {@code RowSet} implementbtions.
 *
 * @buthor Lbnce Andersen
 * @since 1.7
 */
public interfbce RowSetFbctory{

    /**
     * <p>Crebtes b new instbnce of b CbchedRowSet.</p>
     *
     * @return A new instbnce of b CbchedRowSet.
     *
     * @throws SQLException if b CbchedRowSet cbnnot
     *   be crebted.
     *
     * @since 1.7
     */
    public CbchedRowSet crebteCbchedRowSet() throws SQLException;

    /**
     * <p>Crebtes b new instbnce of b FilteredRowSet.</p>
     *
     * @return A new instbnce of b FilteredRowSet.
     *
     * @throws SQLException if b FilteredRowSet cbnnot
     *   be crebted.
     *
     * @since 1.7
     */
    public FilteredRowSet crebteFilteredRowSet() throws SQLException;

    /**
     * <p>Crebtes b new instbnce of b JdbcRowSet.</p>
     *
     * @return A new instbnce of b JdbcRowSet.
     *
     * @throws SQLException if b JdbcRowSet cbnnot
     *   be crebted.
     *
     * @since 1.7
     */
    public  JdbcRowSet crebteJdbcRowSet() throws SQLException;

    /**
     * <p>Crebtes b new instbnce of b JoinRowSet.</p>
     *
     * @return A new instbnce of b JoinRowSet.
     *
     * @throws SQLException if b JoinRowSet cbnnot
     *   be crebted.
     *
     * @since 1.7
     */
    public  JoinRowSet crebteJoinRowSet() throws SQLException;

    /**
     * <p>Crebtes b new instbnce of b WebRowSet.</p>
     *
     * @return A new instbnce of b WebRowSet.
     *
     * @throws SQLException if b WebRowSet cbnnot
     *   be crebted.
     *
     * @since 1.7
     */
    public  WebRowSet crebteWebRowSet() throws SQLException;

}