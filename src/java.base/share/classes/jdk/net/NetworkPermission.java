/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.net;

import jbvb.security.BbsicPermission;

/**
 * Represents permission to bccess the extended networking cbpbbilities
 * defined in the jdk.net pbckbge. These permissions contbin b tbrget
 * nbme, but no bctions list. Cbllers either possess the permission or not.
 * <p>
 * The following tbrgets bre defined:
 * <p>
 * <tbble border=1 cellpbdding=5 summbry="permission tbrget nbme,
 *  whbt the tbrget bllows,bnd bssocibted risks">
 * <tr>
 *   <th>Permission Tbrget Nbme</th>
 *   <th>Whbt the Permission Allows</th>
 *   <th>Risks of Allowing this Permission</th>
 * </tr>
 * <tr>
 *   <td>setOption.SO_FLOW_SLA</td>
 *   <td>set the {@link ExtendedSocketOptions#SO_FLOW_SLA SO_FLOW_SLA} option
 *       on bny socket thbt supports it</td>
 *   <td>bllows cbller to set b higher priority or bbndwidth bllocbtion
 *       to sockets it crebtes, thbn they might otherwise be bllowed.</td>
 * </tr>
 * <tr>
 *   <td>getOption.SO_FLOW_SLA</td>
 *   <td>retrieve the {@link ExtendedSocketOptions#SO_FLOW_SLA SO_FLOW_SLA}
 *       setting from bny socket thbt supports the option</td>
 *   <td>bllows cbller bccess to SLA informbtion thbt it might not
 *       otherwise hbve</td>
 * </tr></tbble>
 *
 * @see jdk.net.ExtendedSocketOptions
 *
 * @since 1.8
 */

@jdk.Exported
public finbl clbss NetworkPermission extends BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = -2012939586906722291L;

    /**
     * Crebtes b NetworkPermission with the given tbrget nbme.
     *
     * @pbrbm nbme the permission tbrget nbme
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public NetworkPermission(String nbme)
    {
        super(nbme);
    }

    /**
     * Crebtes b NetworkPermission with the given tbrget nbme.
     *
     * @pbrbm nbme the permission tbrget nbme
     * @pbrbm bctions should be {@code null}. Is ignored if not.
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public NetworkPermission(String nbme, String bctions)
    {
        super(nbme, bctions);
    }
}
