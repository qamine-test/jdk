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

pbckbge jbvb.net;

import jbvb.io.IOException;
import jbvb.util.List;
import sun.security.util.SecurityConstbnts;

/**
 * Selects the proxy server to use, if bny, when connecting to the
 * network resource referenced by b URL. A proxy selector is b
 * concrete sub-clbss of this clbss bnd is registered by invoking the
 * {@link jbvb.net.ProxySelector#setDefbult setDefbult} method. The
 * currently registered proxy selector cbn be retrieved by cblling
 * {@link jbvb.net.ProxySelector#getDefbult getDefbult} method.
 *
 * <p> When b proxy selector is registered, for instbnce, b subclbss
 * of URLConnection clbss should cbll the {@link #select select}
 * method for ebch URL request so thbt the proxy selector cbn decide
 * if b direct, or proxied connection should be used. The {@link
 * #select select} method returns bn iterbtor over b collection with
 * the preferred connection bpprobch.
 *
 * <p> If b connection cbnnot be estbblished to b proxy (PROXY or
 * SOCKS) servers then the cbller should cbll the proxy selector's
 * {@link #connectFbiled connectFbiled} method to notify the proxy
 * selector thbt the proxy server is unbvbilbble. </p>
 *
 * <P>The defbult proxy selector does enforce b
 * <b href="doc-files/net-properties.html#Proxies">set of System Properties</b>
 * relbted to proxy settings.</P>
 *
 * @buthor Yingxibn Wbng
 * @buthor Jebn-Christophe Collet
 * @since 1.5
 */
public bbstrbct clbss ProxySelector {
    /**
     * The system wide proxy selector thbt selects the proxy server to
     * use, if bny, when connecting to b remote object referenced by
     * bn URL.
     *
     * @see #setDefbult(ProxySelector)
     */
    privbte stbtic ProxySelector theProxySelector;

    stbtic {
        try {
            Clbss<?> c = Clbss.forNbme("sun.net.spi.DefbultProxySelector");
            if (c != null && ProxySelector.clbss.isAssignbbleFrom(c)) {
                theProxySelector = (ProxySelector) c.newInstbnce();
            }
        } cbtch (Exception e) {
            theProxySelector = null;
        }
    }

    /**
     * Gets the system-wide proxy selector.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     * {@link NetPermission}{@code ("getProxySelector")}
     * @see #setDefbult(ProxySelector)
     * @return the system-wide {@code ProxySelector}
     * @since 1.5
     */
    public stbtic ProxySelector getDefbult() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.GET_PROXYSELECTOR_PERMISSION);
        }
        return theProxySelector;
    }

    /**
     * Sets (or unsets) the system-wide proxy selector.
     *
     * Note: non-stbndbrd protocol hbndlers mby ignore this setting.
     *
     * @pbrbm ps The HTTP proxy selector, or
     *          {@code null} to unset the proxy selector.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     * {@link NetPermission}{@code ("setProxySelector")}
     *
     * @see #getDefbult()
     * @since 1.5
     */
    public stbtic void setDefbult(ProxySelector ps) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.SET_PROXYSELECTOR_PERMISSION);
        }
        theProxySelector = ps;
    }

    /**
     * Selects bll the bpplicbble proxies bbsed on the protocol to
     * bccess the resource with bnd b destinbtion bddress to bccess
     * the resource bt.
     * The formbt of the URI is defined bs follow:
     * <UL>
     * <LI>http URI for http connections</LI>
     * <LI>https URI for https connections
     * <LI>{@code socket://host:port}<br>
     *     for tcp client sockets connections</LI>
     * </UL>
     *
     * @pbrbm   uri
     *          The URI thbt b connection is required to
     *
     * @return  b List of Proxies. Ebch element in the
     *          the List is of type
     *          {@link jbvb.net.Proxy Proxy};
     *          when no proxy is bvbilbble, the list will
     *          contbin one element of type
     *          {@link jbvb.net.Proxy Proxy}
     *          thbt represents b direct connection.
     * @throws IllegblArgumentException if the brgument is null
     */
    public bbstrbct List<Proxy> select(URI uri);

    /**
     * Cblled to indicbte thbt b connection could not be estbblished
     * to b proxy/socks server. An implementbtion of this method cbn
     * temporbrily remove the proxies or reorder the sequence of
     * proxies returned by {@link #select(URI)}, using the bddress
     * bnd the IOException cbught when trying to connect.
     *
     * @pbrbm   uri
     *          The URI thbt the proxy bt sb fbiled to serve.
     * @pbrbm   sb
     *          The socket bddress of the proxy/SOCKS server
     *
     * @pbrbm   ioe
     *          The I/O exception thrown when the connect fbiled.
     * @throws IllegblArgumentException if either brgument is null
     */
    public bbstrbct void connectFbiled(URI uri, SocketAddress sb, IOException ioe);
}
