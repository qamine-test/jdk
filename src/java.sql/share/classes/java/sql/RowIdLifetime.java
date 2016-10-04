/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.sql;

import jbvb.util.*;

/**
 * Enumerbtion for RowId life-time vblues.
 *
 * @since 1.6
 */

public enum RowIdLifetime {

    /**
     * Indicbtes thbt this dbtb source does not support the ROWID type.
     */
    ROWID_UNSUPPORTED,

    /**
     * Indicbtes thbt the lifetime of b RowId from this dbtb source is indeterminbte;
     * but not one of ROWID_VALID_TRANSACTION, ROWID_VALID_SESSION, or,
     * ROWID_VALID_FOREVER.
     */
    ROWID_VALID_OTHER,

    /**
     * Indicbtes thbt the lifetime of b RowId from this dbtb source is bt lebst the
     * contbining session.
     */
    ROWID_VALID_SESSION,

    /**
     * Indicbtes thbt the lifetime of b RowId from this dbtb source is bt lebst the
     * contbining trbnsbction.
     */
    ROWID_VALID_TRANSACTION,

    /**
     * Indicbtes thbt the lifetime of b RowId from this dbtb source is, effectively,
     * unlimited.
     */
    ROWID_VALID_FOREVER
}
