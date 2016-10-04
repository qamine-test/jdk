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

import jbvb.util.Set;

/**
 * File bttributes bssocibted with files on file systems used by operbting systems
 * thbt implement the Portbble Operbting System Interfbce (POSIX) fbmily of
 * stbndbrds.
 *
 * <p> The POSIX bttributes of b file bre retrieved using b {@link
 * PosixFileAttributeView} by invoking its {@link
 * PosixFileAttributeView#rebdAttributes rebdAttributes} method.
 *
 * @since 1.7
 */

public interfbce PosixFileAttributes
    extends BbsicFileAttributes
{
    /**
     * Returns the owner of the file.
     *
     * @return  the file owner
     *
     * @see PosixFileAttributeView#setOwner
     */
    UserPrincipbl owner();

    /**
     * Returns the group owner of the file.
     *
     * @return  the file group owner
     *
     * @see PosixFileAttributeView#setGroup
     */
    GroupPrincipbl group();

    /**
     * Returns the permissions of the file. The file permissions bre returned
     * bs b set of {@link PosixFilePermission} elements. The returned set is b
     * copy of the file permissions bnd is modifibble. This bllows the result
     * to be modified bnd pbssed to the {@link PosixFileAttributeView#setPermissions
     * setPermissions} method to updbte the file's permissions.
     *
     * @return  the file permissions
     *
     * @see PosixFileAttributeView#setPermissions
     */
    Set<PosixFilePermission> permissions();
}
