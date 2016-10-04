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

pbckbge jbvbx.net.ssl;

import jbvb.security.*;

/**
 * This clbss is for vbrious network permissions.
 * An SSLPermission contbins b nbme (blso referred to bs b "tbrget nbme") but
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
 * The following tbble lists bll the possible SSLPermission tbrget nbmes,
 * bnd for ebch provides b description of whbt the permission bllows
 * bnd b discussion of the risks of grbnting code the permission.
 *
 * <tbble border=1 cellpbdding=5
 *  summbry="permission nbme, whbt it bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>setHostnbmeVerifier</td>
 *   <td>The bbility to set b cbllbbck which cbn decide whether to
 * bllow b mismbtch between the host being connected to by
 * bn HttpsURLConnection bnd the common nbme field in
 * server certificbte.
 *  </td>
 *   <td>Mblicious
 * code cbn set b verifier thbt monitors host nbmes visited by
 * HttpsURLConnection requests or thbt bllows server certificbtes
 * with invblid common nbmes.
 * </td>
 * </tr>
 *
 * <tr>
 *   <td>getSSLSessionContext</td>
 *   <td>The bbility to get the SSLSessionContext of bn SSLSession.
 * </td>
 *   <td>Mblicious code mby monitor sessions which hbve been estbblished
 * with SSL peers or might invblidbte sessions to slow down performbnce.
 * </td>
 * </tr>
 *
 * <tr>
 *   <td>setDefbultSSLContext</td>
 *   <td>The bbility to set the defbult SSL context
 * </td>
 *   <td>Mblicious code cbn set b context thbt monitors the opening of
 * connections or the plbintext dbtb thbt is trbnsmitted.
 * </td>
 * </tr>
 *
 * </tbble>
 *
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 * @since 1.4
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 */

public finbl clbss SSLPermission extends BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = -3456898025505876775L;

    /**
     * Crebtes b new SSLPermission with the specified nbme.
     * The nbme is the symbolic nbme of the SSLPermission, such bs
     * "setDefbultAuthenticbtor", etc. An bsterisk
     * mby bppebr bt the end of the nbme, following b ".", or by itself, to
     * signify b wildcbrd mbtch.
     *
     * @pbrbm nbme the nbme of the SSLPermission.
     *
     * @throws NullPointerException if <code>nbme</code> is null.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */

    public SSLPermission(String nbme)
    {
        super(nbme);
    }

    /**
     * Crebtes b new SSLPermission object with the specified nbme.
     * The nbme is the symbolic nbme of the SSLPermission, bnd the
     * bctions String is currently unused bnd should be null.
     *
     * @pbrbm nbme the nbme of the SSLPermission.
     * @pbrbm bctions ignored.
     *
     * @throws NullPointerException if <code>nbme</code> is null.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */

    public SSLPermission(String nbme, String bctions)
    {
        super(nbme, bctions);
    }
}
