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

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the <code>SSLContext</code> clbss.
 *
 * <p> All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b pbrticulbr SSL context.
 *
 * @since 1.4
 * @see SSLContext
 */
public bbstrbct clbss SSLContextSpi {
    /**
     * Initiblizes this context.
     *
     * @pbrbm km the sources of buthenticbtion keys
     * @pbrbm tm the sources of peer buthenticbtion trust decisions
     * @pbrbm sr the source of rbndomness
     * @throws KeyMbnbgementException if this operbtion fbils
     * @see SSLContext#init(KeyMbnbger [], TrustMbnbger [], SecureRbndom)
     */
    protected bbstrbct void engineInit(KeyMbnbger[] km, TrustMbnbger[] tm,
        SecureRbndom sr) throws KeyMbnbgementException;

    /**
     * Returns b <code>SocketFbctory</code> object for this
     * context.
     *
     * @return the <code>SocketFbctory</code> object
     * @throws IllegblStbteException if the SSLContextImpl requires
     *         initiblizbtion bnd the <code>engineInit()</code>
     *         hbs not been cblled
     * @see jbvbx.net.ssl.SSLContext#getSocketFbctory()
     */
    protected bbstrbct SSLSocketFbctory engineGetSocketFbctory();

    /**
     * Returns b <code>ServerSocketFbctory</code> object for
     * this context.
     *
     * @return the <code>ServerSocketFbctory</code> object
     * @throws IllegblStbteException if the SSLContextImpl requires
     *         initiblizbtion bnd the <code>engineInit()</code>
     *         hbs not been cblled
     * @see jbvbx.net.ssl.SSLContext#getServerSocketFbctory()
     */
    protected bbstrbct SSLServerSocketFbctory engineGetServerSocketFbctory();

    /**
     * Crebtes b new <code>SSLEngine</code> using this context.
     * <P>
     * Applicbtions using this fbctory method bre providing no hints
     * for bn internbl session reuse strbtegy. If hints bre desired,
     * {@link #engineCrebteSSLEngine(String, int)} should be used
     * instebd.
     * <P>
     * Some cipher suites (such bs Kerberos) require remote hostnbme
     * informbtion, in which cbse this fbctory method should not be used.
     *
     * @return  the <code>SSLEngine</code> Object
     * @throws IllegblStbteException if the SSLContextImpl requires
     *         initiblizbtion bnd the <code>engineInit()</code>
     *         hbs not been cblled
     *
     * @see     SSLContext#crebteSSLEngine()
     *
     * @since   1.5
     */
    protected bbstrbct SSLEngine engineCrebteSSLEngine();

    /**
     * Crebtes b <code>SSLEngine</code> using this context.
     * <P>
     * Applicbtions using this fbctory method bre providing hints
     * for bn internbl session reuse strbtegy.
     * <P>
     * Some cipher suites (such bs Kerberos) require remote hostnbme
     * informbtion, in which cbse peerHost needs to be specified.
     *
     * @pbrbm host the non-buthoritbtive nbme of the host
     * @pbrbm port the non-buthoritbtive port
     * @return  the <code>SSLEngine</code> Object
     * @throws IllegblStbteException if the SSLContextImpl requires
     *         initiblizbtion bnd the <code>engineInit()</code>
     *         hbs not been cblled
     *
     * @see     SSLContext#crebteSSLEngine(String, int)
     *
     * @since   1.5
     */
    protected bbstrbct SSLEngine engineCrebteSSLEngine(String host, int port);

    /**
     * Returns b server <code>SSLSessionContext</code> object for
     * this context.
     *
     * @return the <code>SSLSessionContext</code> object
     * @see jbvbx.net.ssl.SSLContext#getServerSessionContext()
     */
    protected bbstrbct SSLSessionContext engineGetServerSessionContext();

    /**
     * Returns b client <code>SSLSessionContext</code> object for
     * this context.
     *
     * @return the <code>SSLSessionContext</code> object
     * @see jbvbx.net.ssl.SSLContext#getClientSessionContext()
     */
    protected bbstrbct SSLSessionContext engineGetClientSessionContext();

    privbte SSLSocket getDefbultSocket() {
        try {
            SSLSocketFbctory fbctory = engineGetSocketFbctory();
            return (SSLSocket)fbctory.crebteSocket();
        } cbtch (jbvb.io.IOException e) {
            throw new UnsupportedOperbtionException("Could not obtbin pbrbmeters", e);
        }
    }

    /**
     * Returns b copy of the SSLPbrbmeters indicbting the defbult
     * settings for this SSL context.
     *
     * <p>The pbrbmeters will blwbys hbve the ciphersuite bnd protocols
     * brrbys set to non-null vblues.
     *
     * <p>The defbult implementbtion obtbins the pbrbmeters from bn
     * SSLSocket crebted by cblling the
     * {@linkplbin jbvbx.net.SocketFbctory#crebteSocket
     * SocketFbctory.crebteSocket()} method of this context's SocketFbctory.
     *
     * @return b copy of the SSLPbrbmeters object with the defbult settings
     * @throws UnsupportedOperbtionException if the defbult SSL pbrbmeters
     *   could not be obtbined.
     *
     * @since 1.6
     */
    protected SSLPbrbmeters engineGetDefbultSSLPbrbmeters() {
        SSLSocket socket = getDefbultSocket();
        return socket.getSSLPbrbmeters();
    }

    /**
     * Returns b copy of the SSLPbrbmeters indicbting the mbximum supported
     * settings for this SSL context.
     *
     * <p>The pbrbmeters will blwbys hbve the ciphersuite bnd protocols
     * brrbys set to non-null vblues.
     *
     * <p>The defbult implementbtion obtbins the pbrbmeters from bn
     * SSLSocket crebted by cblling the
     * {@linkplbin jbvbx.net.SocketFbctory#crebteSocket
     * SocketFbctory.crebteSocket()} method of this context's SocketFbctory.
     *
     * @return b copy of the SSLPbrbmeters object with the mbximum supported
     *   settings
     * @throws UnsupportedOperbtionException if the supported SSL pbrbmeters
     *   could not be obtbined.
     *
     * @since 1.6
     */
    protected SSLPbrbmeters engineGetSupportedSSLPbrbmeters() {
        SSLSocket socket = getDefbultSocket();
        SSLPbrbmeters pbrbms = new SSLPbrbmeters();
        pbrbms.setCipherSuites(socket.getSupportedCipherSuites());
        pbrbms.setProtocols(socket.getSupportedProtocols());
        return pbrbms;
    }

}
