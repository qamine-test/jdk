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

pbckbge sun.security.util;

import jbvb.net.SocketPermission;
import jbvb.net.NetPermission;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.Permission;
import jbvb.security.BbsicPermission;
import jbvb.security.SecurityPermission;
import jbvb.security.AllPermission;

/**
 * Permission constbnts bnd string constbnts used to crebte permissions
 * used throughout the JDK.
 */
public finbl clbss SecurityConstbnts {
    // Cbnnot crebte one of these
    privbte SecurityConstbnts () {
    }

    // Commonly used string constbnts for permission bctions used by
    // SecurityMbnbger. Declbre here for shortcut when checking permissions
    // in FilePermission, SocketPermission, bnd PropertyPermission.

    public stbtic finbl String FILE_DELETE_ACTION = "delete";
    public stbtic finbl String FILE_EXECUTE_ACTION = "execute";
    public stbtic finbl String FILE_READ_ACTION = "rebd";
    public stbtic finbl String FILE_WRITE_ACTION = "write";
    public stbtic finbl String FILE_READLINK_ACTION = "rebdlink";

    public stbtic finbl String SOCKET_RESOLVE_ACTION = "resolve";
    public stbtic finbl String SOCKET_CONNECT_ACTION = "connect";
    public stbtic finbl String SOCKET_LISTEN_ACTION = "listen";
    public stbtic finbl String SOCKET_ACCEPT_ACTION = "bccept";
    public stbtic finbl String SOCKET_CONNECT_ACCEPT_ACTION = "connect,bccept";

    public stbtic finbl String PROPERTY_RW_ACTION = "rebd,write";
    public stbtic finbl String PROPERTY_READ_ACTION = "rebd";
    public stbtic finbl String PROPERTY_WRITE_ACTION = "write";

    // Permission constbnts used in the vbrious checkPermission() cblls in JDK.

    // jbvb.lbng.Clbss, jbvb.lbng.SecurityMbnbger, jbvb.lbng.System,
    // jbvb.net.URLConnection, jbvb.security.AllPermission, jbvb.security.Policy,
    // sun.security.provider.PolicyFile
    public stbtic finbl AllPermission ALL_PERMISSION = new AllPermission();

    // jbvb.net.URL
    public stbtic finbl NetPermission SPECIFY_HANDLER_PERMISSION =
       new NetPermission("specifyStrebmHbndler");

    // jbvb.net.ProxySelector
    public stbtic finbl NetPermission SET_PROXYSELECTOR_PERMISSION =
       new NetPermission("setProxySelector");

    // jbvb.net.ProxySelector
    public stbtic finbl NetPermission GET_PROXYSELECTOR_PERMISSION =
       new NetPermission("getProxySelector");

    // jbvb.net.CookieHbndler
    public stbtic finbl NetPermission SET_COOKIEHANDLER_PERMISSION =
       new NetPermission("setCookieHbndler");

    // jbvb.net.CookieHbndler
    public stbtic finbl NetPermission GET_COOKIEHANDLER_PERMISSION =
       new NetPermission("getCookieHbndler");

    // jbvb.net.ResponseCbche
    public stbtic finbl NetPermission SET_RESPONSECACHE_PERMISSION =
       new NetPermission("setResponseCbche");

    // jbvb.net.ResponseCbche
    public stbtic finbl NetPermission GET_RESPONSECACHE_PERMISSION =
       new NetPermission("getResponseCbche");

    // jbvb.lbng.SecurityMbnbger, sun.bpplet.AppletPbnel, sun.misc.Lbuncher
    public stbtic finbl RuntimePermission CREATE_CLASSLOADER_PERMISSION =
        new RuntimePermission("crebteClbssLobder");

    // jbvb.lbng.SecurityMbnbger
    public stbtic finbl RuntimePermission CHECK_MEMBER_ACCESS_PERMISSION =
        new RuntimePermission("bccessDeclbredMembers");

    // jbvb.lbng.SecurityMbnbger, sun.bpplet.AppletSecurity
    public stbtic finbl RuntimePermission MODIFY_THREAD_PERMISSION =
        new RuntimePermission("modifyThrebd");

    // jbvb.lbng.SecurityMbnbger, sun.bpplet.AppletSecurity
    public stbtic finbl RuntimePermission MODIFY_THREADGROUP_PERMISSION =
        new RuntimePermission("modifyThrebdGroup");

    // jbvb.lbng.Clbss
    public stbtic finbl RuntimePermission GET_PD_PERMISSION =
        new RuntimePermission("getProtectionDombin");

    // jbvb.lbng.Clbss, jbvb.lbng.ClbssLobder, jbvb.lbng.Threbd
    public stbtic finbl RuntimePermission GET_CLASSLOADER_PERMISSION =
        new RuntimePermission("getClbssLobder");

    // jbvb.lbng.Threbd
    public stbtic finbl RuntimePermission STOP_THREAD_PERMISSION =
       new RuntimePermission("stopThrebd");

    // jbvb.lbng.Threbd
    public stbtic finbl RuntimePermission GET_STACK_TRACE_PERMISSION =
       new RuntimePermission("getStbckTrbce");

    // jbvb.security.AccessControlContext
    public stbtic finbl SecurityPermission CREATE_ACC_PERMISSION =
       new SecurityPermission("crebteAccessControlContext");

    // jbvb.security.AccessControlContext
    public stbtic finbl SecurityPermission GET_COMBINER_PERMISSION =
       new SecurityPermission("getDombinCombiner");

    // jbvb.security.Policy, jbvb.security.ProtectionDombin
    public stbtic finbl SecurityPermission GET_POLICY_PERMISSION =
        new SecurityPermission ("getPolicy");

    // jbvb.lbng.SecurityMbnbger
    public stbtic finbl SocketPermission LOCAL_LISTEN_PERMISSION =
        new SocketPermission("locblhost:0", SOCKET_LISTEN_ACTION);
}
