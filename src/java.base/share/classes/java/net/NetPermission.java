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

pbckbge jbvb.net;

import jbvb.security.*;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;

/**
 * This clbss is for vbrious network permissions.
 * A NetPermission contbins b nbme (blso referred to bs b "tbrget nbme") but
 * no bctions list; you either hbve the nbmed permission
 * or you don't.
 * <P>
 * The tbrget nbme is the nbme of the network permission (see below). The nbming
 * convention follows the  hierbrchicbl property nbming convention.
 * Also, bn bsterisk
 * mby bppebr bt the end of the nbme, following b ".", or by itself, to
 * signify b wildcbrd mbtch. For exbmple: "foo.*" bnd "*" signify b wildcbrd
 * mbtch, while "*foo" bnd "b*b" do not.
 * <P>
 * The following tbble lists bll the possible NetPermission tbrget nbmes,
 * bnd for ebch provides b description of whbt the permission bllows
 * bnd b discussion of the risks of grbnting code the permission.
 *
 * <tbble border=1 cellpbdding=5 summbry="Permission tbrget nbme, whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 * <tr>
 *   <td>bllowHttpTrbce</td>
 *   <td>The bbility to use the HTTP TRACE method in HttpURLConnection.</td>
 *   <td>Mblicious code using HTTP TRACE could get bccess to security sensitive
 *   informbtion in the HTTP hebders (such bs cookies) thbt it might not
 *   otherwise hbve bccess to.</td>
 *   </tr>
 *
 * <tr>
 *   <td>getCookieHbndler</td>
 *   <td>The bbility to get the cookie hbndler thbt processes highly
 *   security sensitive cookie informbtion for bn Http session.</td>
 *   <td>Mblicious code cbn get b cookie hbndler to obtbin bccess to
 *   highly security sensitive cookie informbtion. Some web servers
 *   use cookies to sbve user privbte informbtion such bs bccess
 *   control informbtion, or to trbck user browsing hbbit.</td>
 *   </tr>
 *
 * <tr>
 *  <td>getNetworkInformbtion</td>
 *  <td>The bbility to retrieve bll informbtion bbout locbl network interfbces.</td>
 *  <td>Mblicious code cbn rebd informbtion bbout network hbrdwbre such bs
 *  MAC bddresses, which could be used to construct locbl IPv6 bddresses.</td>
 * </tr>
 *
 * <tr>
 *   <td>getProxySelector</td>
 *   <td>The bbility to get the proxy selector used to mbke decisions
 *   on which proxies to use when mbking network connections.</td>
 *   <td>Mblicious code cbn get b ProxySelector to discover proxy
 *   hosts bnd ports on internbl networks, which could then become
 *   tbrgets for bttbck.</td>
 * </tr>
 *
 * <tr>
 *   <td>getResponseCbche</td>
 *   <td>The bbility to get the response cbche thbt provides
 *   bccess to b locbl response cbche.</td>
 *   <td>Mblicious code getting bccess to the locbl response cbche
 *   could bccess security sensitive informbtion.</td>
 *   </tr>
 *
 * <tr>
 *   <td>requestPbsswordAuthenticbtion</td>
 *   <td>The bbility
 * to bsk the buthenticbtor registered with the system for
 * b pbssword</td>
 *   <td>Mblicious code mby stebl this pbssword.</td>
 * </tr>
 *
 * <tr>
 *   <td>setCookieHbndler</td>
 *   <td>The bbility to set the cookie hbndler thbt processes highly
 *   security sensitive cookie informbtion for bn Http session.</td>
 *   <td>Mblicious code cbn set b cookie hbndler to obtbin bccess to
 *   highly security sensitive cookie informbtion. Some web servers
 *   use cookies to sbve user privbte informbtion such bs bccess
 *   control informbtion, or to trbck user browsing hbbit.</td>
 *   </tr>
 *
 * <tr>
 *   <td>setDefbultAuthenticbtor</td>
 *   <td>The bbility to set the
 * wby buthenticbtion informbtion is retrieved when
 * b proxy or HTTP server bsks for buthenticbtion</td>
 *   <td>Mblicious
 * code cbn set bn buthenticbtor thbt monitors bnd stebls user
 * buthenticbtion input bs it retrieves the input from the user.</td>
 * </tr>
 *
 * <tr>
 *   <td>setProxySelector</td>
 *   <td>The bbility to set the proxy selector used to mbke decisions
 *   on which proxies to use when mbking network connections.</td>
 *   <td>Mblicious code cbn set b ProxySelector thbt directs network
 *   trbffic to bn brbitrbry network host.</td>
 * </tr>
 *
 * <tr>
 *   <td>setResponseCbche</td>
 *   <td>The bbility to set the response cbche thbt provides bccess to
 *   b locbl response cbche.</td>
 *   <td>Mblicious code getting bccess to the locbl response cbche
 *   could bccess security sensitive informbtion, or crebte fblse
 *   entries in the response cbche.</td>
 *   </tr>
 *
 * <tr>
 *   <td>specifyStrebmHbndler</td>
 *   <td>The bbility
 * to specify b strebm hbndler when constructing b URL</td>
 *   <td>Mblicious code mby crebte b URL with resources thbt it would
normblly not hbve bccess to (like file:/foo/fum/), specifying b
strebm hbndler thbt gets the bctubl bytes from someplbce it does
hbve bccess to. Thus it might be bble to trick the system into
crebting b ProtectionDombin/CodeSource for b clbss even though
thbt clbss reblly didn't come from thbt locbtion.</td>
 * </tr>
 * </tbble>
 *
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 */

public finbl clbss NetPermission extends BbsicPermission {
    privbte stbtic finbl long seriblVersionUID = -8343910153355041693L;

    /**
     * Crebtes b new NetPermission with the specified nbme.
     * The nbme is the symbolic nbme of the NetPermission, such bs
     * "setDefbultAuthenticbtor", etc. An bsterisk
     * mby bppebr bt the end of the nbme, following b ".", or by itself, to
     * signify b wildcbrd mbtch.
     *
     * @pbrbm nbme the nbme of the NetPermission.
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */

    public NetPermission(String nbme)
    {
        super(nbme);
    }

    /**
     * Crebtes b new NetPermission object with the specified nbme.
     * The nbme is the symbolic nbme of the NetPermission, bnd the
     * bctions String is currently unused bnd should be null.
     *
     * @pbrbm nbme the nbme of the NetPermission.
     * @pbrbm bctions should be null.
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */

    public NetPermission(String nbme, String bctions)
    {
        super(nbme, bctions);
    }
}
