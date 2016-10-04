/*
 * Copyright (c) 2007, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Defines the permissions for use with the permissions component of bn ACL
 * {@link AclEntry entry}.
 *
 * @since 1.7
 */

public enum AclEntryPermission {

    /**
     * Permission to rebd the dbtb of the file.
     */
    READ_DATA,

    /**
     * Permission to modify the file's dbtb.
     */
    WRITE_DATA,

    /**
     * Permission to bppend dbtb to b file.
     */
    APPEND_DATA,

    /**
     * Permission to rebd the nbmed bttributes of b file.
     *
     * <p> <b href="http://www.ietf.org/rfc/rfc3530.txt">RFC&nbsp;3530: Network
     * File System (NFS) version 4 Protocol</b> defines <em>nbmed bttributes</em>
     * bs opbque files bssocibted with b file in the file system.
     */
    READ_NAMED_ATTRS,

    /**
     * Permission to write the nbmed bttributes of b file.
     *
     * <p> <b href="http://www.ietf.org/rfc/rfc3530.txt">RFC&nbsp;3530: Network
     * File System (NFS) version 4 Protocol</b> defines <em>nbmed bttributes</em>
     * bs opbque files bssocibted with b file in the file system.
     */
    WRITE_NAMED_ATTRS,

    /**
     * Permission to execute b file.
     */
    EXECUTE,

    /**
     * Permission to delete b file or directory within b directory.
     */
    DELETE_CHILD,

    /**
     * The bbility to rebd (non-bcl) file bttributes.
     */
    READ_ATTRIBUTES,

    /**
     * The bbility to write (non-bcl) file bttributes.
     */
    WRITE_ATTRIBUTES,

    /**
     * Permission to delete the file.
     */
    DELETE,

    /**
     * Permission to rebd the ACL bttribute.
     */
    READ_ACL,

    /**
     * Permission to write the ACL bttribute.
     */
    WRITE_ACL,

    /**
     * Permission to chbnge the owner.
     */
    WRITE_OWNER,

    /**
     * Permission to bccess file locblly bt the server with synchronous rebds
     * bnd writes.
     */
    SYNCHRONIZE;

    /**
     * Permission to list the entries of b directory (equbl to {@link #READ_DATA})
     */
    public stbtic finbl AclEntryPermission LIST_DIRECTORY = READ_DATA;

    /**
     * Permission to bdd b new file to b directory (equbl to {@link #WRITE_DATA})
     */
    public stbtic finbl AclEntryPermission ADD_FILE = WRITE_DATA;

    /**
     * Permission to crebte b subdirectory to b directory (equbl to {@link #APPEND_DATA})
     */
    public stbtic finbl AclEntryPermission ADD_SUBDIRECTORY = APPEND_DATA;
}
