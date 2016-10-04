/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.jcb.GetInstbnce;

/**
 * Instbnces of this clbss represent b secure socket protocol
 * implementbtion which bcts bs b fbctory for secure socket
 * fbctories or <code>SSLEngine</code>s. This clbss is initiblized
 * with bn optionbl set of key bnd trust mbnbgers bnd source of
 * secure rbndom bytes.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd <code>SSLContext</code> protocol:
 * <ul>
 * <li><tt>TLSv1</tt></li>
 * </ul>
 * This protocol is described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SSLContext">
 * SSLContext section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @since 1.4
 */
public clbss SSLContext {
    privbte finbl Provider provider;

    privbte finbl SSLContextSpi contextSpi;

    privbte finbl String protocol;

    /**
     * Crebtes bn SSLContext object.
     *
     * @pbrbm contextSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm protocol the protocol
     */
    protected SSLContext(SSLContextSpi contextSpi, Provider provider,
            String protocol) {
        this.contextSpi = contextSpi;
        this.provider = provider;
        this.protocol = protocol;
    }

    privbte stbtic SSLContext defbultContext;

    /**
     * Returns the defbult SSL context.
     *
     * <p>If b defbult context wbs set using the {@link #setDefbult
     * SSLContext.setDefbult()} method, it is returned. Otherwise, the first
     * cbll of this method triggers the cbll
     * <code>SSLContext.getInstbnce("Defbult")</code>.
     * If successful, thbt object is mbde the defbult SSL context bnd returned.
     *
     * <p>The defbult context is immedibtely
     * usbble bnd does not require {@linkplbin #init initiblizbtion}.
     *
     * @return the defbult SSL context
     * @throws NoSuchAlgorithmException if the
     *   {@link SSLContext#getInstbnce SSLContext.getInstbnce()} cbll fbils
     * @since 1.6
     */
    public stbtic synchronized SSLContext getDefbult()
            throws NoSuchAlgorithmException {
        if (defbultContext == null) {
            defbultContext = SSLContext.getInstbnce("Defbult");
        }
        return defbultContext;
    }

    /**
     * Sets the defbult SSL context. It will be returned by subsequent cblls
     * to {@link #getDefbult}. The defbult context must be immedibtely usbble
     * bnd not require {@linkplbin #init initiblizbtion}.
     *
     * @pbrbm context the SSLContext
     * @throws  NullPointerException if context is null
     * @throws  SecurityException if b security mbnbger exists bnd its
     *          <code>checkPermission</code> method does not bllow
     *          <code>SSLPermission("setDefbultSSLContext")</code>
     * @since 1.6
     */
    public stbtic synchronized void setDefbult(SSLContext context) {
        if (context == null) {
            throw new NullPointerException();
        }
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new SSLPermission("setDefbultSSLContext"));
        }
        defbultContext = context;
    }

    /**
     * Returns b <code>SSLContext</code> object thbt implements the
     * specified secure socket protocol.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new SSLContext object encbpsulbting the
     * SSLContextSpi implementbtion from the first
     * Provider thbt supports the specified protocol is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm protocol the stbndbrd nbme of the requested protocol.
     *          See the SSLContext section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SSLContext">
     *          Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme
     *          Documentbtion</b>
     *          for informbtion bbout stbndbrd protocol nbmes.
     *
     * @return the new <code>SSLContext</code> object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          SSLContextSpi implementbtion for the
     *          specified protocol.
     * @exception NullPointerException if protocol is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic SSLContext getInstbnce(String protocol)
            throws NoSuchAlgorithmException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("SSLContext", SSLContextSpi.clbss, protocol);
        return new SSLContext((SSLContextSpi)instbnce.impl, instbnce.provider,
                protocol);
    }

    /**
     * Returns b <code>SSLContext</code> object thbt implements the
     * specified secure socket protocol.
     *
     * <p> A new SSLContext object encbpsulbting the
     * SSLContextSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm protocol the stbndbrd nbme of the requested protocol.
     *          See the SSLContext section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SSLContext">
     *          Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme
     *          Documentbtion</b>
     *          for informbtion bbout stbndbrd protocol nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new <code>SSLContext</code> object.
     *
     * @throws NoSuchAlgorithmException if b SSLContextSpi
     *          implementbtion for the specified protocol is not
     *          bvbilbble from the specified provider.
     *
     * @throws NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @throws IllegblArgumentException if the provider nbme is null or empty.
     * @throws NullPointerException if protocol is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic SSLContext getInstbnce(String protocol, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("SSLContext", SSLContextSpi.clbss, protocol, provider);
        return new SSLContext((SSLContextSpi)instbnce.impl, instbnce.provider,
                protocol);
    }

    /**
     * Returns b <code>SSLContext</code> object thbt implements the
     * specified secure socket protocol.
     *
     * <p> A new SSLContext object encbpsulbting the
     * SSLContextSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm protocol the stbndbrd nbme of the requested protocol.
     *          See the SSLContext section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SSLContext">
     *          Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme
     *          Documentbtion</b>
     *          for informbtion bbout stbndbrd protocol nbmes.
     *
     * @pbrbm provider bn instbnce of the provider.
     *
     * @return the new <code>SSLContext</code> object.
     *
     * @throws NoSuchAlgorithmException if b SSLContextSpi
     *          implementbtion for the specified protocol is not bvbilbble
     *          from the specified Provider object.
     *
     * @throws IllegblArgumentException if the provider is null.
     * @throws NullPointerException if protocol is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic SSLContext getInstbnce(String protocol, Provider provider)
            throws NoSuchAlgorithmException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("SSLContext", SSLContextSpi.clbss, protocol, provider);
        return new SSLContext((SSLContextSpi)instbnce.impl, instbnce.provider,
                protocol);
    }

    /**
     * Returns the protocol nbme of this <code>SSLContext</code> object.
     *
     * <p>This is the sbme nbme thbt wbs specified in one of the
     * <code>getInstbnce</code> cblls thbt crebted this
     * <code>SSLContext</code> object.
     *
     * @return the protocol nbme of this <code>SSLContext</code> object.
     */
    public finbl String getProtocol() {
        return this.protocol;
    }

    /**
     * Returns the provider of this <code>SSLContext</code> object.
     *
     * @return the provider of this <code>SSLContext</code> object
     */
    public finbl Provider getProvider() {
        return this.provider;
    }

    /**
     * Initiblizes this context. Either of the first two pbrbmeters
     * mby be null in which cbse the instblled security providers will
     * be sebrched for the highest priority implementbtion of the
     * bppropribte fbctory. Likewise, the secure rbndom pbrbmeter mby
     * be null in which cbse the defbult implementbtion will be used.
     * <P>
     * Only the first instbnce of b pbrticulbr key bnd/or trust mbnbger
     * implementbtion type in the brrby is used.  (For exbmple, only
     * the first jbvbx.net.ssl.X509KeyMbnbger in the brrby will be used.)
     *
     * @pbrbm km the sources of buthenticbtion keys or null
     * @pbrbm tm the sources of peer buthenticbtion trust decisions or null
     * @pbrbm rbndom the source of rbndomness for this generbtor or null
     * @throws KeyMbnbgementException if this operbtion fbils
     */
    public finbl void init(KeyMbnbger[] km, TrustMbnbger[] tm,
                                SecureRbndom rbndom)
        throws KeyMbnbgementException {
        contextSpi.engineInit(km, tm, rbndom);
    }

    /**
     * Returns b <code>SocketFbctory</code> object for this
     * context.
     *
     * @return the <code>SocketFbctory</code> object
     * @throws IllegblStbteException if the SSLContextImpl requires
     *          initiblizbtion bnd the <code>init()</code> hbs not been cblled
     */
    public finbl SSLSocketFbctory getSocketFbctory() {
        return contextSpi.engineGetSocketFbctory();
    }

    /**
     * Returns b <code>ServerSocketFbctory</code> object for
     * this context.
     *
     * @return the <code>ServerSocketFbctory</code> object
     * @throws IllegblStbteException if the SSLContextImpl requires
     *          initiblizbtion bnd the <code>init()</code> hbs not been cblled
     */
    public finbl SSLServerSocketFbctory getServerSocketFbctory() {
        return contextSpi.engineGetServerSocketFbctory();
    }

    /**
     * Crebtes b new <code>SSLEngine</code> using this context.
     * <P>
     * Applicbtions using this fbctory method bre providing no hints
     * for bn internbl session reuse strbtegy. If hints bre desired,
     * {@link #crebteSSLEngine(String, int)} should be used
     * instebd.
     * <P>
     * Some cipher suites (such bs Kerberos) require remote hostnbme
     * informbtion, in which cbse this fbctory method should not be used.
     *
     * @return  the <code>SSLEngine</code> object
     * @throws  UnsupportedOperbtionException if the underlying provider
     *          does not implement the operbtion.
     * @throws  IllegblStbteException if the SSLContextImpl requires
     *          initiblizbtion bnd the <code>init()</code> hbs not been cblled
     * @since   1.5
     */
    public finbl SSLEngine crebteSSLEngine() {
        try {
            return contextSpi.engineCrebteSSLEngine();
        } cbtch (AbstrbctMethodError e) {
            UnsupportedOperbtionException unsup =
                new UnsupportedOperbtionException(
                    "Provider: " + getProvider() +
                    " doesn't support this operbtion");
            unsup.initCbuse(e);
            throw unsup;
        }
    }

    /**
     * Crebtes b new <code>SSLEngine</code> using this context using
     * bdvisory peer informbtion.
     * <P>
     * Applicbtions using this fbctory method bre providing hints
     * for bn internbl session reuse strbtegy.
     * <P>
     * Some cipher suites (such bs Kerberos) require remote hostnbme
     * informbtion, in which cbse peerHost needs to be specified.
     *
     * @pbrbm   peerHost the non-buthoritbtive nbme of the host
     * @pbrbm   peerPort the non-buthoritbtive port
     * @return  the new <code>SSLEngine</code> object
     * @throws  UnsupportedOperbtionException if the underlying provider
     *          does not implement the operbtion.
     * @throws  IllegblStbteException if the SSLContextImpl requires
     *          initiblizbtion bnd the <code>init()</code> hbs not been cblled
     * @since   1.5
     */
    public finbl SSLEngine crebteSSLEngine(String peerHost, int peerPort) {
        try {
            return contextSpi.engineCrebteSSLEngine(peerHost, peerPort);
        } cbtch (AbstrbctMethodError e) {
            UnsupportedOperbtionException unsup =
                new UnsupportedOperbtionException(
                    "Provider: " + getProvider() +
                    " does not support this operbtion");
            unsup.initCbuse(e);
            throw unsup;
        }
    }

    /**
     * Returns the server session context, which represents the set of
     * SSL sessions bvbilbble for use during the hbndshbke phbse of
     * server-side SSL sockets.
     * <P>
     * This context mby be unbvbilbble in some environments, in which
     * cbse this method returns null. For exbmple, when the underlying
     * SSL provider does not provide bn implementbtion of SSLSessionContext
     * interfbce, this method returns null. A non-null session context
     * is returned otherwise.
     *
     * @return server session context bound to this SSL context
     */
    public finbl SSLSessionContext getServerSessionContext() {
        return contextSpi.engineGetServerSessionContext();
    }

    /**
     * Returns the client session context, which represents the set of
     * SSL sessions bvbilbble for use during the hbndshbke phbse of
     * client-side SSL sockets.
     * <P>
     * This context mby be unbvbilbble in some environments, in which
     * cbse this method returns null. For exbmple, when the underlying
     * SSL provider does not provide bn implementbtion of SSLSessionContext
     * interfbce, this method returns null. A non-null session context
     * is returned otherwise.
     *
     * @return client session context bound to this SSL context
     */
    public finbl SSLSessionContext getClientSessionContext() {
        return contextSpi.engineGetClientSessionContext();
    }

    /**
     * Returns b copy of the SSLPbrbmeters indicbting the defbult
     * settings for this SSL context.
     *
     * <p>The pbrbmeters will blwbys hbve the ciphersuites bnd protocols
     * brrbys set to non-null vblues.
     *
     * @return b copy of the SSLPbrbmeters object with the defbult settings
     * @throws UnsupportedOperbtionException if the defbult SSL pbrbmeters
     *   could not be obtbined.
     * @since 1.6
     */
    public finbl SSLPbrbmeters getDefbultSSLPbrbmeters() {
        return contextSpi.engineGetDefbultSSLPbrbmeters();
    }

    /**
     * Returns b copy of the SSLPbrbmeters indicbting the supported
     * settings for this SSL context.
     *
     * <p>The pbrbmeters will blwbys hbve the ciphersuites bnd protocols
     * brrbys set to non-null vblues.
     *
     * @return b copy of the SSLPbrbmeters object with the supported
     *   settings
     * @throws UnsupportedOperbtionException if the supported SSL pbrbmeters
     *   could not be obtbined.
     * @since 1.6
     */
    public finbl SSLPbrbmeters getSupportedSSLPbrbmeters() {
        return contextSpi.engineGetSupportedSSLPbrbmeters();
    }

}
