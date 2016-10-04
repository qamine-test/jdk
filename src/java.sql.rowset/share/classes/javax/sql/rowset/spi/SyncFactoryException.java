/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Indicbtes bn error with <code>SyncFbctory</code> mechbnism. A disconnected
 * RowSet implementbtion cbnnot be used  without b <code>SyncProvider</code>
 * being successfully instbntibted
 *
 * @buthor Jonbthbn Bruce
 * @see jbvbx.sql.rowset.spi.SyncFbctory
 * @see jbvbx.sql.rowset.spi.SyncFbctoryException
 * @since 1.5
 */
public clbss SyncFbctoryException extends jbvb.sql.SQLException {

    /**
     * Crebtes new <code>SyncFbctoryException</code> without detbil messbge.
     */
    public SyncFbctoryException() {
    }

    /**
     * Constructs bn <code>SyncFbctoryException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm msg the detbil messbge.
     */
    public SyncFbctoryException(String msg) {
        super(msg);
    }

    stbtic finbl long seriblVersionUID = -4354595476433200352L;
}
