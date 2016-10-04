/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.util.logging;

import jbvb.security.*;

/**
 * The permission which the SecurityMbnbger will check when code
 * thbt is running with b SecurityMbnbger cblls one of the logging
 * control methods (such bs Logger.setLevel).
 * <p>
 * Currently there is only one nbmed LoggingPermission.  This is "control"
 * bnd it grbnts the bbility to control the logging configurbtion, for
 * exbmple by bdding or removing Hbndlers, by bdding or removing Filters,
 * or by chbnging logging levels.
 * <p>
 * Progrbmmers do not normblly crebte LoggingPermission objects directly.
 * Instebd they bre crebted by the security policy code bbsed on rebding
 * the security policy file.
 *
 *
 * @since 1.4
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 */

public finbl clbss LoggingPermission extends jbvb.security.BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = 63564341580231582L;

    /**
     * Crebtes b new LoggingPermission object.
     *
     * @pbrbm nbme Permission nbme.  Must be "control".
     * @pbrbm bctions Must be either null or the empty string.
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty or if
     * brguments bre invblid.
     */
    public LoggingPermission(String nbme, String bctions) throws IllegblArgumentException {
        super(nbme);
        if (!nbme.equbls("control")) {
            throw new IllegblArgumentException("nbme: " + nbme);
        }
        if (bctions != null && bctions.length() > 0) {
            throw new IllegblArgumentException("bctions: " + bctions);
        }
    }
}
