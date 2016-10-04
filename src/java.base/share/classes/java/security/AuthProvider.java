/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.security.buth.Subject;
import jbvbx.security.buth.login.LoginException;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

/**
 * This clbss defines login bnd logout methods for b provider.
 *
 * <p> While cbllers mby invoke {@code login} directly,
 * the provider mby blso invoke {@code login} on behblf of cbllers
 * if it determines thbt b login must be performed
 * prior to certbin operbtions.
 *
 * @since 1.5
 */
public bbstrbct clbss AuthProvider extends Provider {

    privbte stbtic finbl long seriblVersionUID = 4197859053084546461L;

    /**
     * Constructs b provider with the specified nbme, version number,
     * bnd informbtion.
     *
     * @pbrbm nbme the provider nbme.
     * @pbrbm version the provider version number.
     * @pbrbm info b description of the provider bnd its services.
     */
    protected AuthProvider(String nbme, double version, String info) {
        super(nbme, version, info);
    }

    /**
     * Log in to this provider.
     *
     * <p> The provider relies on b {@code CbllbbckHbndler}
     * to obtbin buthenticbtion informbtion from the cbller
     * (b PIN, for exbmple).  If the cbller pbsses b {@code null}
     * hbndler to this method, the provider uses the hbndler set in the
     * {@code setCbllbbckHbndler} method.
     * If no hbndler wbs set in thbt method, the provider queries the
     * <i>buth.login.defbultCbllbbckHbndler</i> security property
     * for the fully qublified clbss nbme of b defbult hbndler implementbtion.
     * If the security property is not set,
     * the provider is bssumed to hbve blternbtive mebns
     * for obtbining buthenticbtion informbtion.
     *
     * @pbrbm subject the {@code Subject} which mby contbin
     *          principbls/credentibls used for buthenticbtion,
     *          or mby be populbted with bdditionbl principbls/credentibls
     *          bfter successful buthenticbtion hbs completed.
     *          This pbrbmeter mby be {@code null}.
     * @pbrbm hbndler the {@code CbllbbckHbndler} used by
     *          this provider to obtbin buthenticbtion informbtion
     *          from the cbller, which mby be {@code null}
     *
     * @exception LoginException if the login operbtion fbils
     * @exception SecurityException if the cbller does not pbss b
     *  security check for
     *  {@code SecurityPermission("buthProvider.nbme")},
     *  where {@code nbme} is the vblue returned by
     *  this provider's {@code getNbme} method
     */
    public bbstrbct void login(Subject subject, CbllbbckHbndler hbndler)
        throws LoginException;

    /**
     * Log out from this provider.
     *
     * @exception LoginException if the logout operbtion fbils
     * @exception SecurityException if the cbller does not pbss b
     *  security check for
     *  {@code SecurityPermission("buthProvider.nbme")},
     *  where {@code nbme} is the vblue returned by
     *  this provider's {@code getNbme} method
     */
    public bbstrbct void logout() throws LoginException;

    /**
     * Set b {@code CbllbbckHbndler}.
     *
     * <p> The provider uses this hbndler if one is not pbssed to the
     * {@code login} method.  The provider blso uses this hbndler
     * if it invokes {@code login} on behblf of cbllers.
     * In either cbse if b hbndler is not set vib this method,
     * the provider queries the
     * <i>buth.login.defbultCbllbbckHbndler</i> security property
     * for the fully qublified clbss nbme of b defbult hbndler implementbtion.
     * If the security property is not set,
     * the provider is bssumed to hbve blternbtive mebns
     * for obtbining buthenticbtion informbtion.
     *
     * @pbrbm hbndler b {@code CbllbbckHbndler} for obtbining
     *          buthenticbtion informbtion, which mby be {@code null}
     *
     * @exception SecurityException if the cbller does not pbss b
     *  security check for
     *  {@code SecurityPermission("buthProvider.nbme")},
     *  where {@code nbme} is the vblue returned by
     *  this provider's {@code getNbme} method
     */
    public bbstrbct void setCbllbbckHbndler(CbllbbckHbndler hbndler);
}
