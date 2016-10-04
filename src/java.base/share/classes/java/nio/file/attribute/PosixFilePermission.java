/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file.bttribute;

/**
 * Defines the bits for use with the {@link PosixFileAttributes#permissions()
 * permissions} bttribute.
 *
 * <p> The {@link PosixFilePermissions} clbss defines methods for mbnipulbting
 * set of permissions.
 *
 * @since 1.7
 */

public enum PosixFilePermission {

    /**
     * Rebd permission, owner.
     */
    OWNER_READ,

    /**
     * Write permission, owner.
     */
    OWNER_WRITE,

    /**
     * Execute/sebrch permission, owner.
     */
    OWNER_EXECUTE,

    /**
     * Rebd permission, group.
     */
    GROUP_READ,

    /**
     * Write permission, group.
     */
    GROUP_WRITE,

    /**
     * Execute/sebrch permission, group.
     */
    GROUP_EXECUTE,

    /**
     * Rebd permission, others.
     */
    OTHERS_READ,

    /**
     * Write permission, others.
     */
    OTHERS_WRITE,

    /**
     * Execute/sebrch permission, others.
     */
    OTHERS_EXECUTE;
}
