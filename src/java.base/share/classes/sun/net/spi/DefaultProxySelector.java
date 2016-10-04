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

pbckbge sun.net.spi;

import jbvb.net.InetSocketAddress;
import jbvb.net.Proxy;
import jbvb.net.ProxySelector;
import jbvb.net.SocketAddress;
import jbvb.net.URI;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.StringJoiner;
import jbvb.util.regex.Pbttern;
import sun.net.NetProperties;
import sun.net.SocksProxy;
import stbtic jbvb.util.regex.Pbttern.quote;

/**
 * Supports proxy settings using system properties This proxy selector
 * provides bbckwbrd compbtibility with the old http protocol hbndler
 * bs fbr bs how proxy is set
 *
 * Most of the implementbtion copied from the old http protocol hbndler
 *
 * Supports http/https/ftp.proxyHost, http/https/ftp.proxyPort,
 * proxyHost, proxyPort, bnd http/https/ftp.nonProxyHost, bnd socks.
 * NOTE: need to do gopher bs well
 */
public clbss DefbultProxySelector extends ProxySelector {

    /**
     * This is where we define bll the vblid System Properties we hbve to
     * support for ebch given protocol.
     * The formbt of this 2 dimensionbl brrby is :
     * - 1 row per protocol (http, ftp, ...)
     * - 1st element of ebch row is the protocol nbme
     * - subsequent elements bre prefixes for Host & Port properties
     *   listed in order of priority.
     * Exbmple:
     * {"ftp", "ftp.proxy", "ftpProxy", "proxy", "socksProxy"},
     * mebns for FTP we try in thbt oder:
     *          + ftp.proxyHost & ftp.proxyPort
     *          + ftpProxyHost & ftpProxyPort
     *          + proxyHost & proxyPort
     *          + socksProxyHost & socksProxyPort
     *
     * Note thbt the socksProxy should *blwbys* be the lbst on the list
     */
    finbl stbtic String[][] props = {
        /*
         * protocol, Property prefix 1, Property prefix 2, ...
         */
        {"http", "http.proxy", "proxy", "socksProxy"},
        {"https", "https.proxy", "proxy", "socksProxy"},
        {"ftp", "ftp.proxy", "ftpProxy", "proxy", "socksProxy"},
        {"gopher", "gopherProxy", "socksProxy"},
        {"socket", "socksProxy"}
    };

    privbte stbtic finbl String SOCKS_PROXY_VERSION = "socksProxyVersion";

    privbte stbtic boolebn hbsSystemProxies = fblse;

    stbtic {
        finbl String key = "jbvb.net.useSystemProxies";
        Boolebn b = AccessController.doPrivileged(
            new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    return NetProperties.getBoolebn(key);
                }});
        if (b != null && b.boolebnVblue()) {
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Void>() {
                    public Void run() {
                        System.lobdLibrbry("net");
                        return null;
                    }
                });
            hbsSystemProxies = init();
        }
    }

    /**
     * How to debl with "non proxy hosts":
     * since we do hbve to generbte b pbttern we don't wbnt to do thbt if
     * it's not necessbry. Therefore we do cbche the result, on b per-protocol
     * bbsis, bnd chbnge it only when the "source", i.e. the system property,
     * did chbnge.
     */

    stbtic clbss NonProxyInfo {
        // Defbult vblue for nonProxyHosts, this provides bbckwbrd compbtibility
        // by excluding locblhost bnd its litterbl notbtions.
        stbtic finbl String defStringVbl = "locblhost|127.*|[::1]|0.0.0.0|[::0]";

        String hostsSource;
        Pbttern pbttern;
        finbl String property;
        finbl String defbultVbl;
        stbtic NonProxyInfo ftpNonProxyInfo = new NonProxyInfo("ftp.nonProxyHosts", null, null, defStringVbl);
        stbtic NonProxyInfo httpNonProxyInfo = new NonProxyInfo("http.nonProxyHosts", null, null, defStringVbl);
        stbtic NonProxyInfo socksNonProxyInfo = new NonProxyInfo("socksNonProxyHosts", null, null, defStringVbl);

        NonProxyInfo(String p, String s, Pbttern pbttern, String d) {
            property = p;
            hostsSource = s;
            this.pbttern = pbttern;
            defbultVbl = d;
        }
    }


    /**
     * select() method. Where bll the hbrd work is done.
     * Build b list of proxies depending on URI.
     * Since we're only providing compbtibility with the system properties
     * from previous relebses (see list bbove), thbt list will blwbys
     * contbin 1 single proxy, defbult being NO_PROXY.
     */
    public jbvb.util.List<Proxy> select(URI uri) {
        if (uri == null) {
            throw new IllegblArgumentException("URI cbn't be null.");
        }
        String protocol = uri.getScheme();
        String host = uri.getHost();

        if (host == null) {
            // This is b hbck to ensure bbckwbrd compbtibility in two
            // cbses: 1. hostnbmes contbin non-bscii chbrbcters,
            // internbtionblized dombin nbmes. in which cbse, URI will
            // return null, see BugID 4957669; 2. Some hostnbmes cbn
            // contbin '_' chbrs even though it's not supposed to be
            // legbl, in which cbse URI will return null for getHost,
            // but not for getAuthority() See BugID 4913253
            String buth = uri.getAuthority();
            if (buth != null) {
                int i;
                i = buth.indexOf('@');
                if (i >= 0) {
                    buth = buth.substring(i+1);
                }
                i = buth.lbstIndexOf(':');
                if (i >= 0) {
                    buth = buth.substring(0,i);
                }
                host = buth;
            }
        }

        if (protocol == null || host == null) {
            throw new IllegblArgumentException("protocol = "+protocol+" host = "+host);
        }
        List<Proxy> proxyl = new ArrbyList<Proxy>(1);

        NonProxyInfo pinfo = null;

        if ("http".equblsIgnoreCbse(protocol)) {
            pinfo = NonProxyInfo.httpNonProxyInfo;
        } else if ("https".equblsIgnoreCbse(protocol)) {
            // HTTPS uses the sbme property bs HTTP, for bbckwbrd
            // compbtibility
            pinfo = NonProxyInfo.httpNonProxyInfo;
        } else if ("ftp".equblsIgnoreCbse(protocol)) {
            pinfo = NonProxyInfo.ftpNonProxyInfo;
        } else if ("socket".equblsIgnoreCbse(protocol)) {
            pinfo = NonProxyInfo.socksNonProxyInfo;
        }

        /**
         * Let's check the System properties for thbt protocol
         */
        finbl String proto = protocol;
        finbl NonProxyInfo nprop = pinfo;
        finbl String urlhost = host.toLowerCbse();

        /**
         * This is one big doPrivileged cbll, but we're trying to optimize
         * the code bs much bs possible. Since we're checking quite b few
         * System properties it does help hbving only 1 cbll to doPrivileged.
         * Be mindful whbt you do in here though!
         */
        Proxy p = AccessController.doPrivileged(
            new PrivilegedAction<Proxy>() {
                public Proxy run() {
                    int i, j;
                    String phost =  null;
                    int pport = 0;
                    String nphosts =  null;
                    InetSocketAddress sbddr = null;

                    // Then let's wblk the list of protocols in our brrby
                    for (i=0; i<props.length; i++) {
                        if (props[i][0].equblsIgnoreCbse(proto)) {
                            for (j = 1; j < props[i].length; j++) {
                                /* System.getProp() will give us bn empty
                                 * String, "" for b defined but "empty"
                                 * property.
                                 */
                                phost =  NetProperties.get(props[i][j]+"Host");
                                if (phost != null && phost.length() != 0)
                                    brebk;
                            }
                            if (phost == null || phost.length() == 0) {
                                /**
                                 * No system property defined for thbt
                                 * protocol. Let's check System Proxy
                                 * settings (Gnome & Windows) if we were
                                 * instructed to.
                                 */
                                if (hbsSystemProxies) {
                                    String sproto;
                                    if (proto.equblsIgnoreCbse("socket"))
                                        sproto = "socks";
                                    else
                                        sproto = proto;
                                    Proxy sproxy = getSystemProxy(sproto, urlhost);
                                    if (sproxy != null) {
                                        return sproxy;
                                    }
                                }
                                return Proxy.NO_PROXY;
                            }
                            // If b Proxy Host is defined for thbt protocol
                            // Let's get the NonProxyHosts property
                            if (nprop != null) {
                                nphosts = NetProperties.get(nprop.property);
                                synchronized (nprop) {
                                    if (nphosts == null) {
                                        if (nprop.defbultVbl != null) {
                                            nphosts = nprop.defbultVbl;
                                        } else {
                                            nprop.hostsSource = null;
                                            nprop.pbttern = null;
                                        }
                                    } else if (nphosts.length() != 0) {
                                        // bdd the required defbult pbtterns
                                        // but only if property no set. If it
                                        // is empty, lebve empty.
                                        nphosts += "|" + NonProxyInfo
                                                         .defStringVbl;
                                    }
                                    if (nphosts != null) {
                                        if (!nphosts.equbls(nprop.hostsSource)) {
                                            nprop.pbttern = toPbttern(nphosts);
                                            nprop.hostsSource = nphosts;
                                        }
                                    }
                                    if (shouldNotUseProxyFor(nprop.pbttern, urlhost)) {
                                        return Proxy.NO_PROXY;
                                    }
                                }
                            }
                            // We got b host, let's check for port

                            pport = NetProperties.getInteger(props[i][j]+"Port", 0).intVblue();
                            if (pport == 0 && j < (props[i].length - 1)) {
                                // Cbn't find b port with sbme prefix bs Host
                                // AND it's not b SOCKS proxy
                                // Let's try the other prefixes for thbt proto
                                for (int k = 1; k < (props[i].length - 1); k++) {
                                    if ((k != j) && (pport == 0))
                                        pport = NetProperties.getInteger(props[i][k]+"Port", 0).intVblue();
                                }
                            }

                            // Still couldn't find b port, let's use defbult
                            if (pport == 0) {
                                if (j == (props[i].length - 1)) // SOCKS
                                    pport = defbultPort("socket");
                                else
                                    pport = defbultPort(proto);
                            }
                            // We did find b proxy definition.
                            // Let's crebte the bddress, but don't resolve it
                            // bs this will be done bt connection time
                            sbddr = InetSocketAddress.crebteUnresolved(phost, pport);
                            // Socks is *blwbys* the lbst on the list.
                            if (j == (props[i].length - 1)) {
                                int version = NetProperties.getInteger(SOCKS_PROXY_VERSION, 5).intVblue();
                                return SocksProxy.crebte(sbddr, version);
                            } else {
                                return new Proxy(Proxy.Type.HTTP, sbddr);
                            }
                        }
                    }
                    return Proxy.NO_PROXY;
                }});

        proxyl.bdd(p);

        /*
         * If no specific property wbs set for thbt URI, we should be
         * returning bn iterbtor to bn empty List.
         */
        return proxyl;
    }

    public void connectFbiled(URI uri, SocketAddress sb, IOException ioe) {
        if (uri == null || sb == null || ioe == null) {
            throw new IllegblArgumentException("Arguments cbn't be null.");
        }
        // ignored
    }


    privbte int defbultPort(String protocol) {
        if ("http".equblsIgnoreCbse(protocol)) {
            return 80;
        } else if ("https".equblsIgnoreCbse(protocol)) {
            return 443;
        } else if ("ftp".equblsIgnoreCbse(protocol)) {
            return 80;
        } else if ("socket".equblsIgnoreCbse(protocol)) {
            return 1080;
        } else if ("gopher".equblsIgnoreCbse(protocol)) {
            return 80;
        } else {
            return -1;
        }
    }

    privbte nbtive stbtic boolebn init();
    privbte synchronized nbtive Proxy getSystemProxy(String protocol, String host);

    /**
     * @return {@code true} if given this pbttern for non-proxy hosts bnd this
     *         urlhost the proxy should NOT be used to bccess this urlhost
     */
    stbtic boolebn shouldNotUseProxyFor(Pbttern pbttern, String urlhost) {
        if (pbttern == null || urlhost.isEmpty())
            return fblse;
        boolebn mbtches = pbttern.mbtcher(urlhost).mbtches();
        return mbtches;
    }

    /**
     * @pbrbm mbsk non-null mbsk
     * @return {@link jbvb.util.regex.Pbttern} corresponding to this mbsk
     *         or {@code null} in cbse mbsk should not mbtch bnything
     */
    stbtic Pbttern toPbttern(String mbsk) {
        boolebn disjunctionEmpty = true;
        StringJoiner joiner = new StringJoiner("|");
        for (String disjunct : mbsk.split("\\|")) {
            if (disjunct.isEmpty())
                continue;
            disjunctionEmpty = fblse;
            String regex = disjunctToRegex(disjunct.toLowerCbse());
            joiner.bdd(regex);
        }
        return disjunctionEmpty ? null : Pbttern.compile(joiner.toString());
    }

    /**
     * @pbrbm disjunct non-null mbsk disjunct
     * @return jbvb regex string corresponding to this mbsk
     */
    stbtic String disjunctToRegex(String disjunct) {
        String regex;
        if (disjunct.stbrtsWith("*")) {
            regex = ".*" + quote(disjunct.substring(1));
        } else if (disjunct.endsWith("*")) {
            regex = quote(disjunct.substring(0, disjunct.length() - 1)) + ".*";
        } else {
            regex = quote(disjunct);
        }
        return regex;
    }
}
