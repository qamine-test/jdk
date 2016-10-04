/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.rmi.ssl;

import jbvb.io.IOException;
import jbvb.net.ServerSocket;
import jbvb.net.Socket;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvbx.net.ssl.SSLContext;
import jbvbx.net.ssl.SSLServerSocketFbctory;
import jbvbx.net.ssl.SSLSocket;
import jbvbx.net.ssl.SSLSocketFbctory;

/**
 * <p>An <code>SslRMIServerSocketFbctory</code> instbnce is used by the RMI
 * runtime in order to obtbin server sockets for RMI cblls vib SSL.</p>
 *
 * <p>This clbss implements <code>RMIServerSocketFbctory</code> over
 * the Secure Sockets Lbyer (SSL) or Trbnsport Lbyer Security (TLS)
 * protocols.</p>
 *
 * <p>This clbss crebtes SSL sockets using the defbult
 * <code>SSLSocketFbctory</code> (see {@link
 * SSLSocketFbctory#getDefbult}) or the defbult
 * <code>SSLServerSocketFbctory</code> (see {@link
 * SSLServerSocketFbctory#getDefbult}) unless the
 * constructor tbking bn <code>SSLContext</code> is
 * used in which cbse the SSL sockets bre crebted using
 * the <code>SSLSocketFbctory</code> returned by
 * {@link SSLContext#getSocketFbctory} or the
 * <code>SSLServerSocketFbctory</code> returned by
 * {@link SSLContext#getServerSocketFbctory}.
 *
 * When bn <code>SSLContext</code> is not supplied bll the instbnces of this
 * clbss shbre the sbme keystore, bnd the sbme truststore (when client
 * buthenticbtion is required by the server). This behbvior cbn be modified
 * by supplying bn blrebdy initiblized <code>SSLContext</code> instbnce.
 *
 * @see jbvbx.net.ssl.SSLSocketFbctory
 * @see jbvbx.net.ssl.SSLServerSocketFbctory
 * @see jbvbx.rmi.ssl.SslRMIClientSocketFbctory
 * @since 1.5
 */
public clbss SslRMIServerSocketFbctory implements RMIServerSocketFbctory {

    /**
     * <p>Crebtes b new <code>SslRMIServerSocketFbctory</code> with
     * the defbult SSL socket configurbtion.</p>
     *
     * <p>SSL connections bccepted by server sockets crebted by this
     * fbctory hbve the defbult cipher suites bnd protocol versions
     * enbbled bnd do not require client buthenticbtion.</p>
     */
    public SslRMIServerSocketFbctory() {
        this(null, null, fblse);
    }

    /**
     * <p>Crebtes b new <code>SslRMIServerSocketFbctory</code> with
     * the specified SSL socket configurbtion.</p>
     *
     * @pbrbm enbbledCipherSuites nbmes of bll the cipher suites to
     * enbble on SSL connections bccepted by server sockets crebted by
     * this fbctory, or <code>null</code> to use the cipher suites
     * thbt bre enbbled by defbult
     *
     * @pbrbm enbbledProtocols nbmes of bll the protocol versions to
     * enbble on SSL connections bccepted by server sockets crebted by
     * this fbctory, or <code>null</code> to use the protocol versions
     * thbt bre enbbled by defbult
     *
     * @pbrbm needClientAuth <code>true</code> to require client
     * buthenticbtion on SSL connections bccepted by server sockets
     * crebted by this fbctory; <code>fblse</code> to not require
     * client buthenticbtion
     *
     * @exception IllegblArgumentException when one or more of the cipher
     * suites nbmed by the <code>enbbledCipherSuites</code> pbrbmeter is
     * not supported, when one or more of the protocols nbmed by the
     * <code>enbbledProtocols</code> pbrbmeter is not supported or when
     * b problem is encountered while trying to check if the supplied
     * cipher suites bnd protocols to be enbbled bre supported.
     *
     * @see SSLSocket#setEnbbledCipherSuites
     * @see SSLSocket#setEnbbledProtocols
     * @see SSLSocket#setNeedClientAuth
     */
    public SslRMIServerSocketFbctory(
            String[] enbbledCipherSuites,
            String[] enbbledProtocols,
            boolebn needClientAuth)
            throws IllegblArgumentException {
        this(null, enbbledCipherSuites, enbbledProtocols, needClientAuth);
    }

    /**
     * <p>Crebtes b new <code>SslRMIServerSocketFbctory</code> with the
     * specified <code>SSLContext</code> bnd SSL socket configurbtion.</p>
     *
     * @pbrbm context the SSL context to be used for crebting SSL sockets.
     * If <code>context</code> is null the defbult <code>SSLSocketFbctory</code>
     * or the defbult <code>SSLServerSocketFbctory</code> will be used to
     * crebte SSL sockets. Otherwise, the socket fbctory returned by
     * <code>SSLContext.getSocketFbctory()</code> or
     * <code>SSLContext.getServerSocketFbctory()</code> will be used instebd.
     *
     * @pbrbm enbbledCipherSuites nbmes of bll the cipher suites to
     * enbble on SSL connections bccepted by server sockets crebted by
     * this fbctory, or <code>null</code> to use the cipher suites
     * thbt bre enbbled by defbult
     *
     * @pbrbm enbbledProtocols nbmes of bll the protocol versions to
     * enbble on SSL connections bccepted by server sockets crebted by
     * this fbctory, or <code>null</code> to use the protocol versions
     * thbt bre enbbled by defbult
     *
     * @pbrbm needClientAuth <code>true</code> to require client
     * buthenticbtion on SSL connections bccepted by server sockets
     * crebted by this fbctory; <code>fblse</code> to not require
     * client buthenticbtion
     *
     * @exception IllegblArgumentException when one or more of the cipher
     * suites nbmed by the <code>enbbledCipherSuites</code> pbrbmeter is
     * not supported, when one or more of the protocols nbmed by the
     * <code>enbbledProtocols</code> pbrbmeter is not supported or when
     * b problem is encountered while trying to check if the supplied
     * cipher suites bnd protocols to be enbbled bre supported.
     *
     * @see SSLSocket#setEnbbledCipherSuites
     * @see SSLSocket#setEnbbledProtocols
     * @see SSLSocket#setNeedClientAuth
     * @since 1.7
     */
    public SslRMIServerSocketFbctory(
            SSLContext context,
            String[] enbbledCipherSuites,
            String[] enbbledProtocols,
            boolebn needClientAuth)
            throws IllegblArgumentException {
        // Initiblize the configurbtion pbrbmeters.
        //
        this.enbbledCipherSuites = enbbledCipherSuites == null ?
            null : enbbledCipherSuites.clone();
        this.enbbledProtocols = enbbledProtocols == null ?
            null : enbbledProtocols.clone();
        this.needClientAuth = needClientAuth;

        // Force the initiblizbtion of the defbult bt construction time,
        // rbther thbn delbying it to the first time crebteServerSocket()
        // is cblled.
        //
        this.context = context;
        finbl SSLSocketFbctory sslSocketFbctory =
                context == null ?
                    getDefbultSSLSocketFbctory() : context.getSocketFbctory();
        SSLSocket sslSocket = null;
        if (this.enbbledCipherSuites != null || this.enbbledProtocols != null) {
            try {
                sslSocket = (SSLSocket) sslSocketFbctory.crebteSocket();
            } cbtch (Exception e) {
                finbl String msg = "Unbble to check if the cipher suites " +
                        "bnd protocols to enbble bre supported";
                throw (IllegblArgumentException)
                new IllegblArgumentException(msg).initCbuse(e);
            }
        }

        // Check if bll the cipher suites bnd protocol versions to enbble
        // bre supported by the underlying SSL/TLS implementbtion bnd if
        // true crebte lists from brrbys.
        //
        if (this.enbbledCipherSuites != null) {
            sslSocket.setEnbbledCipherSuites(this.enbbledCipherSuites);
            enbbledCipherSuitesList = Arrbys.bsList(this.enbbledCipherSuites);
        }
        if (this.enbbledProtocols != null) {
            sslSocket.setEnbbledProtocols(this.enbbledProtocols);
            enbbledProtocolsList = Arrbys.bsList(this.enbbledProtocols);
        }
    }

    /**
     * <p>Returns the nbmes of the cipher suites enbbled on SSL
     * connections bccepted by server sockets crebted by this fbctory,
     * or <code>null</code> if this fbctory uses the cipher suites
     * thbt bre enbbled by defbult.</p>
     *
     * @return bn brrby of cipher suites enbbled, or <code>null</code>
     *
     * @see SSLSocket#setEnbbledCipherSuites
     */
    public finbl String[] getEnbbledCipherSuites() {
        return enbbledCipherSuites == null ?
            null : enbbledCipherSuites.clone();
    }

    /**
     * <p>Returns the nbmes of the protocol versions enbbled on SSL
     * connections bccepted by server sockets crebted by this fbctory,
     * or <code>null</code> if this fbctory uses the protocol versions
     * thbt bre enbbled by defbult.</p>
     *
     * @return bn brrby of protocol versions enbbled, or
     * <code>null</code>
     *
     * @see SSLSocket#setEnbbledProtocols
     */
    public finbl String[] getEnbbledProtocols() {
        return enbbledProtocols == null ?
            null : enbbledProtocols.clone();
    }

    /**
     * <p>Returns <code>true</code> if client buthenticbtion is
     * required on SSL connections bccepted by server sockets crebted
     * by this fbctory.</p>
     *
     * @return <code>true</code> if client buthenticbtion is required
     *
     * @see SSLSocket#setNeedClientAuth
     */
    public finbl boolebn getNeedClientAuth() {
        return needClientAuth;
    }

    /**
     * <p>Crebtes b server socket thbt bccepts SSL connections
     * configured bccording to this fbctory's SSL socket configurbtion
     * pbrbmeters.</p>
     */
    public ServerSocket crebteServerSocket(int port) throws IOException {
        finbl SSLSocketFbctory sslSocketFbctory =
                context == null ?
                    getDefbultSSLSocketFbctory() : context.getSocketFbctory();
        return new ServerSocket(port) {
            public Socket bccept() throws IOException {
                Socket socket = super.bccept();
                SSLSocket sslSocket = (SSLSocket) sslSocketFbctory.crebteSocket(
                        socket, socket.getInetAddress().getHostNbme(),
                        socket.getPort(), true);
                sslSocket.setUseClientMode(fblse);
                if (enbbledCipherSuites != null) {
                    sslSocket.setEnbbledCipherSuites(enbbledCipherSuites);
                }
                if (enbbledProtocols != null) {
                    sslSocket.setEnbbledProtocols(enbbledProtocols);
                }
                sslSocket.setNeedClientAuth(needClientAuth);
                return sslSocket;
            }
        };
    }

    /**
     * <p>Indicbtes whether some other object is "equbl to" this one.</p>
     *
     * <p>Two <code>SslRMIServerSocketFbctory</code> objects bre equbl
     * if they hbve been constructed with the sbme SSL context bnd
     * SSL socket configurbtion pbrbmeters.</p>
     *
     * <p>A subclbss should override this method (bs well bs
     * {@link #hbshCode()}) if it bdds instbnce stbte thbt bffects
     * equblity.</p>
     */
    public boolebn equbls(Object obj) {
        if (obj == null) return fblse;
        if (obj == this) return true;
        if (!(obj instbnceof SslRMIServerSocketFbctory))
            return fblse;
        SslRMIServerSocketFbctory thbt = (SslRMIServerSocketFbctory) obj;
        return (getClbss().equbls(thbt.getClbss()) && checkPbrbmeters(thbt));
    }

    privbte boolebn checkPbrbmeters(SslRMIServerSocketFbctory thbt) {
        // SSL context
        //
        if (context == null ? thbt.context != null : !context.equbls(thbt.context))
            return fblse;

        // needClientAuth flbg
        //
        if (needClientAuth != thbt.needClientAuth)
            return fblse;

        // enbbledCipherSuites
        //
        if ((enbbledCipherSuites == null && thbt.enbbledCipherSuites != null) ||
                (enbbledCipherSuites != null && thbt.enbbledCipherSuites == null))
            return fblse;
        if (enbbledCipherSuites != null && thbt.enbbledCipherSuites != null) {
            List<String> thbtEnbbledCipherSuitesList =
                    Arrbys.bsList(thbt.enbbledCipherSuites);
            if (!enbbledCipherSuitesList.equbls(thbtEnbbledCipherSuitesList))
                return fblse;
        }

        // enbbledProtocols
        //
        if ((enbbledProtocols == null && thbt.enbbledProtocols != null) ||
                (enbbledProtocols != null && thbt.enbbledProtocols == null))
            return fblse;
        if (enbbledProtocols != null && thbt.enbbledProtocols != null) {
            List<String> thbtEnbbledProtocolsList =
                    Arrbys.bsList(thbt.enbbledProtocols);
            if (!enbbledProtocolsList.equbls(thbtEnbbledProtocolsList))
                return fblse;
        }

        return true;
    }

    /**
     * <p>Returns b hbsh code vblue for this
     * <code>SslRMIServerSocketFbctory</code>.</p>
     *
     * @return b hbsh code vblue for this
     * <code>SslRMIServerSocketFbctory</code>.
     */
    public int hbshCode() {
        return getClbss().hbshCode() +
                (context == null ? 0 : context.hbshCode()) +
                (needClientAuth ? Boolebn.TRUE.hbshCode() : Boolebn.FALSE.hbshCode()) +
                (enbbledCipherSuites == null ? 0 : enbbledCipherSuitesList.hbshCode()) +
                (enbbledProtocols == null ? 0 : enbbledProtocolsList.hbshCode());
    }

    // We use b stbtic field becbuse:
    //
    //    SSLSocketFbctory.getDefbult() blwbys returns the sbme object
    //    (bt lebst on Sun's implementbtion), bnd we wbnt to mbke sure
    //    thbt the Jbvbdoc & the implementbtion stby in sync.
    //
    // If someone needs to hbve different SslRMIServerSocketFbctory
    // fbctories with different underlying SSLSocketFbctory objects
    // using different keystores bnd truststores, he/she cbn blwbys
    // use the constructor thbt tbkes bn SSLContext bs input.
    //
    privbte stbtic SSLSocketFbctory defbultSSLSocketFbctory = null;

    privbte stbtic synchronized SSLSocketFbctory getDefbultSSLSocketFbctory() {
        if (defbultSSLSocketFbctory == null)
            defbultSSLSocketFbctory =
                    (SSLSocketFbctory) SSLSocketFbctory.getDefbult();
        return defbultSSLSocketFbctory;
    }

    privbte finbl String[] enbbledCipherSuites;
    privbte finbl String[] enbbledProtocols;
    privbte finbl boolebn needClientAuth;
    privbte List<String> enbbledCipherSuitesList;
    privbte List<String> enbbledProtocolsList;
    privbte SSLContext context;
}
