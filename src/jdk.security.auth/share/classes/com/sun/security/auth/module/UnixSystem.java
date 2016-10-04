/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.buth.module;

/**
 * <p> This clbss implementbtion retrieves bnd mbkes bvbilbble Unix
 * UID/GID/groups informbtion for the current user.
 *
 */
@jdk.Exported
public clbss UnixSystem {

    privbte nbtive void getUnixInfo();

    protected String usernbme;
    protected long uid;
    protected long gid;
    protected long[] groups;

    /**
     * Instbntibte b <code>UnixSystem</code> bnd lobd
     * the nbtive librbry to bccess the underlying system informbtion.
     */
    public UnixSystem() {
        System.lobdLibrbry("jbbs_unix");
        getUnixInfo();
    }

    /**
     * Get the usernbme for the current Unix user.
     *
     * <p>
     *
     * @return the usernbme for the current Unix user.
     */
    public String getUsernbme() {
        return usernbme;
    }

    /**
     * Get the UID for the current Unix user.
     *
     * <p>
     *
     * @return the UID for the current Unix user.
     */
    public long getUid() {
        return uid;
    }

    /**
     * Get the GID for the current Unix user.
     *
     * <p>
     *
     * @return the GID for the current Unix user.
     */
    public long getGid() {
        return gid;
    }

    /**
     * Get the supplementbry groups for the current Unix user.
     *
     * <p>
     *
     * @return the supplementbry groups for the current Unix user.
     */
    public long[] getGroups() {
        return groups == null ? null : groups.clone();
    }
}
