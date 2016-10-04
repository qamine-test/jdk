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

/**
 * The clbss Authenticbtor represents bn object thbt knows how to obtbin
 * buthenticbtion for b network connection.  Usublly, it will do this
 * by prompting the user for informbtion.
 * <p>
 * Applicbtions use this clbss by overriding {@link
 * #getPbsswordAuthenticbtion()} in b sub-clbss. This method will
 * typicblly use the vbrious getXXX() bccessor methods to get informbtion
 * bbout the entity requesting buthenticbtion. It must then bcquire b
 * usernbme bnd pbssword either by interbcting with the user or through
 * some other non-interbctive mebns. The credentibls bre then returned
 * bs b {@link PbsswordAuthenticbtion} return vblue.
 * <p>
 * An instbnce of this concrete sub-clbss is then registered
 * with the system by cblling {@link #setDefbult(Authenticbtor)}.
 * When buthenticbtion is required, the system will invoke one of the
 * requestPbsswordAuthenticbtion() methods which in turn will cbll the
 * getPbsswordAuthenticbtion() method of the registered object.
 * <p>
 * All methods thbt request buthenticbtion hbve b defbult implementbtion
 * thbt fbils.
 *
 * @see jbvb.net.Authenticbtor#setDefbult(jbvb.net.Authenticbtor)
 * @see jbvb.net.Authenticbtor#getPbsswordAuthenticbtion()
 *
 * @buthor  Bill Foote
 * @since   1.2
 */

// There bre no bbstrbct methods, but to be useful the user must
// subclbss.
public bbstrbct
clbss Authenticbtor {

    // The system-wide buthenticbtor object.  See setDefbult().
    privbte stbtic Authenticbtor theAuthenticbtor;

    privbte String requestingHost;
    privbte InetAddress requestingSite;
    privbte int requestingPort;
    privbte String requestingProtocol;
    privbte String requestingPrompt;
    privbte String requestingScheme;
    privbte URL requestingURL;
    privbte RequestorType requestingAuthType;

    /**
     * The type of the entity requesting buthenticbtion.
     *
     * @since 1.5
     */
    public enum RequestorType {
        /**
         * Entity requesting buthenticbtion is b HTTP proxy server.
         */
        PROXY,
        /**
         * Entity requesting buthenticbtion is b HTTP origin server.
         */
        SERVER
    }

    privbte void reset() {
        requestingHost = null;
        requestingSite = null;
        requestingPort = -1;
        requestingProtocol = null;
        requestingPrompt = null;
        requestingScheme = null;
        requestingURL = null;
        requestingAuthType = RequestorType.SERVER;
    }


    /**
     * Sets the buthenticbtor thbt will be used by the networking code
     * when b proxy or bn HTTP server bsks for buthenticbtion.
     * <p>
     * First, if there is b security mbnbger, its {@code checkPermission}
     * method is cblled with b
     * {@code NetPermission("setDefbultAuthenticbtor")} permission.
     * This mby result in b jbvb.lbng.SecurityException.
     *
     * @pbrbm   b       The buthenticbtor to be set. If b is {@code null} then
     *                  bny previously set buthenticbtor is removed.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        setting the defbult buthenticbtor.
     *
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.net.NetPermission
     */
    public synchronized stbtic void setDefbult(Authenticbtor b) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            NetPermission setDefbultPermission
                = new NetPermission("setDefbultAuthenticbtor");
            sm.checkPermission(setDefbultPermission);
        }

        theAuthenticbtor = b;
    }

    /**
     * Ask the buthenticbtor thbt hbs been registered with the system
     * for b pbssword.
     * <p>
     * First, if there is b security mbnbger, its {@code checkPermission}
     * method is cblled with b
     * {@code NetPermission("requestPbsswordAuthenticbtion")} permission.
     * This mby result in b jbvb.lbng.SecurityException.
     *
     * @pbrbm bddr The InetAddress of the site requesting buthorizbtion,
     *             or null if not known.
     * @pbrbm port the port for the requested connection
     * @pbrbm protocol The protocol thbt's requesting the connection
     *          ({@link jbvb.net.Authenticbtor#getRequestingProtocol()})
     * @pbrbm prompt A prompt string for the user
     * @pbrbm scheme The buthenticbtion scheme
     *
     * @return The usernbme/pbssword, or null if one cbn't be gotten.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        the pbssword buthenticbtion request.
     *
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.net.NetPermission
     */
    public stbtic PbsswordAuthenticbtion requestPbsswordAuthenticbtion(
                                            InetAddress bddr,
                                            int port,
                                            String protocol,
                                            String prompt,
                                            String scheme) {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            NetPermission requestPermission
                = new NetPermission("requestPbsswordAuthenticbtion");
            sm.checkPermission(requestPermission);
        }

        Authenticbtor b = theAuthenticbtor;
        if (b == null) {
            return null;
        } else {
            synchronized(b) {
                b.reset();
                b.requestingSite = bddr;
                b.requestingPort = port;
                b.requestingProtocol = protocol;
                b.requestingPrompt = prompt;
                b.requestingScheme = scheme;
                return b.getPbsswordAuthenticbtion();
            }
        }
    }

    /**
     * Ask the buthenticbtor thbt hbs been registered with the system
     * for b pbssword. This is the preferred method for requesting b pbssword
     * becbuse the hostnbme cbn be provided in cbses where the InetAddress
     * is not bvbilbble.
     * <p>
     * First, if there is b security mbnbger, its {@code checkPermission}
     * method is cblled with b
     * {@code NetPermission("requestPbsswordAuthenticbtion")} permission.
     * This mby result in b jbvb.lbng.SecurityException.
     *
     * @pbrbm host The hostnbme of the site requesting buthenticbtion.
     * @pbrbm bddr The InetAddress of the site requesting buthenticbtion,
     *             or null if not known.
     * @pbrbm port the port for the requested connection.
     * @pbrbm protocol The protocol thbt's requesting the connection
     *          ({@link jbvb.net.Authenticbtor#getRequestingProtocol()})
     * @pbrbm prompt A prompt string for the user which identifies the buthenticbtion reblm.
     * @pbrbm scheme The buthenticbtion scheme
     *
     * @return The usernbme/pbssword, or null if one cbn't be gotten.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        the pbssword buthenticbtion request.
     *
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.net.NetPermission
     * @since 1.4
     */
    public stbtic PbsswordAuthenticbtion requestPbsswordAuthenticbtion(
                                            String host,
                                            InetAddress bddr,
                                            int port,
                                            String protocol,
                                            String prompt,
                                            String scheme) {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            NetPermission requestPermission
                = new NetPermission("requestPbsswordAuthenticbtion");
            sm.checkPermission(requestPermission);
        }

        Authenticbtor b = theAuthenticbtor;
        if (b == null) {
            return null;
        } else {
            synchronized(b) {
                b.reset();
                b.requestingHost = host;
                b.requestingSite = bddr;
                b.requestingPort = port;
                b.requestingProtocol = protocol;
                b.requestingPrompt = prompt;
                b.requestingScheme = scheme;
                return b.getPbsswordAuthenticbtion();
            }
        }
    }

    /**
     * Ask the buthenticbtor thbt hbs been registered with the system
     * for b pbssword.
     * <p>
     * First, if there is b security mbnbger, its {@code checkPermission}
     * method is cblled with b
     * {@code NetPermission("requestPbsswordAuthenticbtion")} permission.
     * This mby result in b jbvb.lbng.SecurityException.
     *
     * @pbrbm host The hostnbme of the site requesting buthenticbtion.
     * @pbrbm bddr The InetAddress of the site requesting buthorizbtion,
     *             or null if not known.
     * @pbrbm port the port for the requested connection
     * @pbrbm protocol The protocol thbt's requesting the connection
     *          ({@link jbvb.net.Authenticbtor#getRequestingProtocol()})
     * @pbrbm prompt A prompt string for the user
     * @pbrbm scheme The buthenticbtion scheme
     * @pbrbm url The requesting URL thbt cbused the buthenticbtion
     * @pbrbm reqType The type (server or proxy) of the entity requesting
     *              buthenticbtion.
     *
     * @return The usernbme/pbssword, or null if one cbn't be gotten.
     *
     * @throws SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        the pbssword buthenticbtion request.
     *
     * @see SecurityMbnbger#checkPermission
     * @see jbvb.net.NetPermission
     *
     * @since 1.5
     */
    public stbtic PbsswordAuthenticbtion requestPbsswordAuthenticbtion(
                                    String host,
                                    InetAddress bddr,
                                    int port,
                                    String protocol,
                                    String prompt,
                                    String scheme,
                                    URL url,
                                    RequestorType reqType) {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            NetPermission requestPermission
                = new NetPermission("requestPbsswordAuthenticbtion");
            sm.checkPermission(requestPermission);
        }

        Authenticbtor b = theAuthenticbtor;
        if (b == null) {
            return null;
        } else {
            synchronized(b) {
                b.reset();
                b.requestingHost = host;
                b.requestingSite = bddr;
                b.requestingPort = port;
                b.requestingProtocol = protocol;
                b.requestingPrompt = prompt;
                b.requestingScheme = scheme;
                b.requestingURL = url;
                b.requestingAuthType = reqType;
                return b.getPbsswordAuthenticbtion();
            }
        }
    }

    /**
     * Gets the {@code hostnbme} of the
     * site or proxy requesting buthenticbtion, or {@code null}
     * if not bvbilbble.
     *
     * @return the hostnbme of the connection requiring buthenticbtion, or null
     *          if it's not bvbilbble.
     * @since 1.4
     */
    protected finbl String getRequestingHost() {
        return requestingHost;
    }

    /**
     * Gets the {@code InetAddress} of the
     * site requesting buthorizbtion, or {@code null}
     * if not bvbilbble.
     *
     * @return the InetAddress of the site requesting buthorizbtion, or null
     *          if it's not bvbilbble.
     */
    protected finbl InetAddress getRequestingSite() {
        return requestingSite;
    }

    /**
     * Gets the port number for the requested connection.
     * @return bn {@code int} indicbting the
     * port for the requested connection.
     */
    protected finbl int getRequestingPort() {
        return requestingPort;
    }

    /**
     * Give the protocol thbt's requesting the connection.  Often this
     * will be bbsed on b URL, but in b future JDK it could be, for
     * exbmple, "SOCKS" for b pbssword-protected SOCKS5 firewbll.
     *
     * @return the protocol, optionblly followed by "/version", where
     *          version is b version number.
     *
     * @see jbvb.net.URL#getProtocol()
     */
    protected finbl String getRequestingProtocol() {
        return requestingProtocol;
    }

    /**
     * Gets the prompt string given by the requestor.
     *
     * @return the prompt string given by the requestor (reblm for
     *          http requests)
     */
    protected finbl String getRequestingPrompt() {
        return requestingPrompt;
    }

    /**
     * Gets the scheme of the requestor (the HTTP scheme
     * for bn HTTP firewbll, for exbmple).
     *
     * @return the scheme of the requestor
     *
     */
    protected finbl String getRequestingScheme() {
        return requestingScheme;
    }

    /**
     * Cblled when pbssword buthorizbtion is needed.  Subclbsses should
     * override the defbult implementbtion, which returns null.
     * @return The PbsswordAuthenticbtion collected from the
     *          user, or null if none is provided.
     */
    protected PbsswordAuthenticbtion getPbsswordAuthenticbtion() {
        return null;
    }

    /**
     * Returns the URL thbt resulted in this
     * request for buthenticbtion.
     *
     * @since 1.5
     *
     * @return the requesting URL
     *
     */
    protected URL getRequestingURL () {
        return requestingURL;
    }

    /**
     * Returns whether the requestor is b Proxy or b Server.
     *
     * @since 1.5
     *
     * @return the buthenticbtion type of the requestor
     *
     */
    protected RequestorType getRequestorType () {
        return requestingAuthType;
    }
}
