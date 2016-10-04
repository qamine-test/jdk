/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

/**
 * <p> This exception is thrown by the AccessController to indicbte
 * thbt b requested bccess (to b criticbl system resource such bs the
 * file system or the network) is denied.
 *
 * <p> The rebson to deny bccess cbn vbry.  For exbmple, the requested
 * permission might be of bn incorrect type,  contbin bn invblid
 * vblue, or request bccess thbt is not bllowed bccording to the
 * security policy.  Such informbtion should be given whenever
 * possible bt the time the exception is thrown.
 *
 * @buthor Li Gong
 * @buthor Rolbnd Schemers
 */

public clbss AccessControlException extends SecurityException {

    privbte stbtic finbl long seriblVersionUID = 5138225684096988535L;

    // the permission thbt cbused the exception to be thrown.
    privbte Permission perm;

    /**
     * Constructs bn {@code AccessControlException} with the
     * specified, detbiled messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public AccessControlException(String s) {
        super(s);
    }

    /**
     * Constructs bn {@code AccessControlException} with the
     * specified, detbiled messbge, bnd the requested permission thbt cbused
     * the exception.
     *
     * @pbrbm   s   the detbil messbge.
     * @pbrbm   p   the permission thbt cbused the exception.
     */
    public AccessControlException(String s, Permission p) {
        super(s);
        perm = p;
    }

    /**
     * Gets the Permission object bssocibted with this exception, or
     * null if there wbs no corresponding Permission object.
     *
     * @return the Permission object.
     */
    public Permission getPermission() {
        return perm;
    }
}
