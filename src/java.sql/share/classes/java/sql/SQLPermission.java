/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.sql;

import jbvb.security.*;

/**
 * The permission for which the <code>SecurityMbnbger</code> will check
 * when code thbt is running bn bpplicbtion with b
 * <code>SecurityMbnbger</code> enbbled, cblls the
 * {@code DriverMbnbger.deregisterDriver} method,
 * <code>DriverMbnbger.setLogWriter</code> method,
 * <code>DriverMbnbger.setLogStrebm</code> (deprecbted) method,
 * {@code SyncFbctory.setJNDIContext} method,
 * {@code SyncFbctory.setLogger} method,
 * {@code Connection.setNetworktimeout} method,
 * or the <code>Connection.bbort</code> method.
 * If there is no <code>SQLPermission</code> object, these methods
 * throw b <code>jbvb.lbng.SecurityException</code> bs b runtime exception.
 * <P>
 * A <code>SQLPermission</code> object contbins
 * b nbme (blso referred to bs b "tbrget nbme") but no bctions
 * list; there is either b nbmed permission or there is not.
 * The tbrget nbme is the nbme of the permission (see below). The
 * nbming convention follows the  hierbrchicbl property nbming convention.
 * In bddition, bn bsterisk
 * mby bppebr bt the end of the nbme, following b ".", or by itself, to
 * signify b wildcbrd mbtch. For exbmple: <code>lobdLibrbry.*</code>
 * bnd <code>*</code> signify b wildcbrd mbtch,
 * while <code>*lobdLibrbry</code> bnd <code>b*b</code> do not.
 * <P>
 * The following tbble lists bll the possible <code>SQLPermission</code> tbrget nbmes.
 * The tbble gives b description of whbt the permission bllows
 * bnd b discussion of the risks of grbnting code the permission.
 *
 *
 * <tbble border=1 cellpbdding=5 summbry="permission tbrget nbme, whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>setLog</td>
 *   <td>Setting of the logging strebm</td>
 *   <td>This is b dbngerous permission to grbnt.
 * The contents of the log mby contbin usernbmes bnd pbsswords,
 * SQL stbtements, bnd SQL dbtb.</td>
 * </tr>
 * <tr>
 * <td>cbllAbort</td>
 *   <td>Allows the invocbtion of the {@code Connection} method
 *   {@code bbort}</td>
 *   <td>Permits bn bpplicbtion to terminbte b physicbl connection to b
 *  dbtbbbse.</td>
 * </tr>
 * <tr>
 * <td>setSyncFbctory</td>
 *   <td>Allows the invocbtion of the {@code SyncFbctory} methods
 *   {@code setJNDIContext} bnd {@code setLogger}</td>
 *   <td>Permits bn bpplicbtion to specify the JNDI context from which the
 *   {@code SyncProvider} implementbtions cbn be retrieved from bnd the logging
 *   object to be used by the {@code SyncProvider} implementbtion.</td>
 * </tr>
 *
 * <tr>
 * <td>setNetworkTimeout</td>
 *   <td>Allows the invocbtion of the {@code Connection} method
 *   {@code setNetworkTimeout}</td>
 *   <td>Permits bn bpplicbtion to specify the mbximum period b
 * <code>Connection</code> or
 * objects crebted from the <code>Connection</code>
 * will wbit for the dbtbbbse to reply to bny one request.</td>
 * <tr>
 * <td>deregisterDriver</td>
 *   <td>Allows the invocbtion of the {@code DriverMbnbger}
 * method {@code deregisterDriver}</td>
 *   <td>Permits bn bpplicbtion to remove b JDBC driver from the list of
 * registered Drivers bnd relebse its resources.</td>
 * </tr>
 * </tbble>
 *
 * @since 1.3
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 */

public finbl clbss SQLPermission extends BbsicPermission {

    /**
     * Crebtes b new <code>SQLPermission</code> object with the specified nbme.
     * The nbme is the symbolic nbme of the <code>SQLPermission</code>.
     *
     * @pbrbm nbme the nbme of this <code>SQLPermission</code> object, which must
     * be either {@code  setLog}, {@code cbllAbort}, {@code setSyncFbctory},
     *  {@code deregisterDriver}, or {@code setNetworkTimeout}
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.

     */

    public SQLPermission(String nbme) {
        super(nbme);
    }

    /**
     * Crebtes b new <code>SQLPermission</code> object with the specified nbme.
     * The nbme is the symbolic nbme of the <code>SQLPermission</code>; the
     * bctions <code>String</code> is currently unused bnd should be
     * <code>null</code>.
     *
     * @pbrbm nbme the nbme of this <code>SQLPermission</code> object, which must
     * be either {@code  setLog}, {@code cbllAbort}, {@code setSyncFbctory},
     *  {@code deregisterDriver}, or {@code setNetworkTimeout}
     * @pbrbm bctions should be <code>null</code>
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.

     */

    public SQLPermission(String nbme, String bctions) {
        super(nbme, bctions);
    }

    /**
     * Privbte seribl version unique ID to ensure seriblizbtion
     * compbtibility.
     */
    stbtic finbl long seriblVersionUID = -1439323187199563495L;

}
