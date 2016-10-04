/*
 * Copyright (c) 1997, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.net;

import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.Socket;
import jbvb.net.SocketException;
import jbvb.net.UnknownHostException;

/**
 * This clbss crebtes sockets.  It mby be subclbssed by other fbctories,
 * which crebte pbrticulbr subclbsses of sockets bnd thus provide b generbl
 * frbmework for the bddition of public socket-level functionblity.
 *
 * <P> Socket fbctories bre b simple wby to cbpture b vbriety of policies
 * relbted to the sockets being constructed, producing such sockets in
 * b wby which does not require specibl configurbtion of the code which
 * bsks for the sockets:  <UL>
 *
 *      <LI> Due to polymorphism of both fbctories bnd sockets, different
 *      kinds of sockets cbn be used by the sbme bpplicbtion code just
 *      by pbssing it different kinds of fbctories.
 *
 *      <LI> Fbctories cbn themselves be customized with pbrbmeters used
 *      in socket construction.  So for exbmple, fbctories could be
 *      customized to return sockets with different networking timeouts
 *      or security pbrbmeters blrebdy configured.
 *
 *      <LI> The sockets returned to the bpplicbtion cbn be subclbsses
 *      of jbvb.net.Socket, so thbt they cbn directly expose new APIs
 *      for febtures such bs compression, security, record mbrking,
 *      stbtistics collection, or firewbll tunneling.
 *
 *      </UL>
 *
 * <P> Fbctory clbsses bre specified by environment-specific configurbtion
 * mechbnisms.  For exbmple, the <em>getDefbult</em> method could return
 * b fbctory thbt wbs bppropribte for b pbrticulbr user or bpplet, bnd b
 * frbmework could use b fbctory customized to its own purposes.
 *
 * @since 1.4
 * @see ServerSocketFbctory
 *
 * @buthor Dbvid Brownell
 */
public bbstrbct clbss SocketFbctory
{
    //
    // NOTE:  JDK 1.1 bug in clbss GC, this cbn get collected
    // even though it's blwbys bccessible vib getDefbult().
    //
    privbte stbtic SocketFbctory                theFbctory;

    /**
     * Crebtes b <code>SocketFbctory</code>.
     */
    protected SocketFbctory() { /* NOTHING */ }


    /**
     * Returns b copy of the environment's defbult socket fbctory.
     *
     * @return the defbult <code>SocketFbctory</code>
     */
    public stbtic SocketFbctory getDefbult()
    {
        synchronized (SocketFbctory.clbss) {
            if (theFbctory == null) {
                //
                // Different implementbtions of this method SHOULD
                // work rbther differently.  For exbmple, driving
                // this from b system property, or using b different
                // implementbtion thbn JbvbSoft's.
                //
                theFbctory = new DefbultSocketFbctory();
            }
        }

        return theFbctory;
    }


    /**
     * Crebtes bn unconnected socket.
     *
     * @return the unconnected socket
     * @throws IOException if the socket cbnnot be crebted
     * @see jbvb.net.Socket#connect(jbvb.net.SocketAddress)
     * @see jbvb.net.Socket#connect(jbvb.net.SocketAddress, int)
     * @see jbvb.net.Socket#Socket()
     */
    public Socket crebteSocket() throws IOException {
        //
        // bug 6771432:
        // The Exception is used by HttpsClient to signbl thbt
        // unconnected sockets hbve not been implemented.
        //
        UnsupportedOperbtionException uop = new
                UnsupportedOperbtionException();
        SocketException se =  new SocketException(
                "Unconnected sockets not implemented");
        se.initCbuse(uop);
        throw se;
    }


    /**
     * Crebtes b socket bnd connects it to the specified remote host
     * bt the specified remote port.  This socket is configured using
     * the socket options estbblished for this fbctory.
     * <p>
     * If there is b security mbnbger, its <code>checkConnect</code>
     * method is cblled with the host bddress bnd <code>port</code>
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm host the server host nbme with which to connect, or
     *        <code>null</code> for the loopbbck bddress.
     * @pbrbm port the server port
     * @return the <code>Socket</code>
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkConnect</code> method doesn't bllow the operbtion.
     * @throws UnknownHostException if the host is not known
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @see SecurityMbnbger#checkConnect
     * @see jbvb.net.Socket#Socket(String, int)
     */
    public bbstrbct Socket crebteSocket(String host, int port)
    throws IOException, UnknownHostException;


    /**
     * Crebtes b socket bnd connects it to the specified remote host
     * on the specified remote port.
     * The socket will blso be bound to the locbl bddress bnd port supplied.
     * This socket is configured using
     * the socket options estbblished for this fbctory.
     * <p>
     * If there is b security mbnbger, its <code>checkConnect</code>
     * method is cblled with the host bddress bnd <code>port</code>
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm host the server host nbme with which to connect, or
     *        <code>null</code> for the loopbbck bddress.
     * @pbrbm port the server port
     * @pbrbm locblHost the locbl bddress the socket is bound to
     * @pbrbm locblPort the locbl port the socket is bound to
     * @return the <code>Socket</code>
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkConnect</code> method doesn't bllow the operbtion.
     * @throws UnknownHostException if the host is not known
     * @throws IllegblArgumentException if the port pbrbmeter or locblPort
     *         pbrbmeter is outside the specified rbnge of vblid port vblues,
     *         which is between 0 bnd 65535, inclusive.
     * @see SecurityMbnbger#checkConnect
     * @see jbvb.net.Socket#Socket(String, int, jbvb.net.InetAddress, int)
     */
    public bbstrbct Socket
    crebteSocket(String host, int port, InetAddress locblHost, int locblPort)
    throws IOException, UnknownHostException;


    /**
     * Crebtes b socket bnd connects it to the specified port number
     * bt the specified bddress.  This socket is configured using
     * the socket options estbblished for this fbctory.
     * <p>
     * If there is b security mbnbger, its <code>checkConnect</code>
     * method is cblled with the host bddress bnd <code>port</code>
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm host the server host
     * @pbrbm port the server port
     * @return the <code>Socket</code>
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkConnect</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @throws NullPointerException if <code>host</code> is null.
     * @see SecurityMbnbger#checkConnect
     * @see jbvb.net.Socket#Socket(jbvb.net.InetAddress, int)
     */
    public bbstrbct Socket crebteSocket(InetAddress host, int port)
    throws IOException;


    /**
     * Crebtes b socket bnd connect it to the specified remote bddress
     * on the specified remote port.  The socket will blso be bound
     * to the locbl bddress bnd port suplied.  The socket is configured using
     * the socket options estbblished for this fbctory.
     * <p>
     * If there is b security mbnbger, its <code>checkConnect</code>
     * method is cblled with the host bddress bnd <code>port</code>
     * bs its brguments. This could result in b SecurityException.
     *
     * @pbrbm bddress the server network bddress
     * @pbrbm port the server port
     * @pbrbm locblAddress the client network bddress
     * @pbrbm locblPort the client port
     * @return the <code>Socket</code>
     * @throws IOException if bn I/O error occurs when crebting the socket
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkConnect</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter or locblPort
     *         pbrbmeter is outside the specified rbnge of vblid port vblues,
     *         which is between 0 bnd 65535, inclusive.
     * @throws NullPointerException if <code>bddress</code> is null.
     * @see SecurityMbnbger#checkConnect
     * @see jbvb.net.Socket#Socket(jbvb.net.InetAddress, int,
     *     jbvb.net.InetAddress, int)
     */
    public bbstrbct Socket
    crebteSocket(InetAddress bddress, int port,
        InetAddress locblAddress, int locblPort)
    throws IOException;
}


//
// The defbult fbctory hbs NO intelligence bbout policies like tunneling
// out through firewblls (e.g. SOCKS V4 or V5) or in through them
// (e.g. using SSL), or thbt some ports bre reserved for use with SSL.
//
// Note thbt bt lebst JDK 1.1 hbs b low level "plbinSocketImpl" thbt
// knows bbout SOCKS V4 tunneling, so this isn't b totblly bogus defbult.
//
// ALSO:  we mby wbnt to expose this clbss somewhere so other folk
// cbn reuse it, pbrticulbrly if we stbrt to bdd highly useful febtures
// such bs bbility to set connect timeouts.
//
clbss DefbultSocketFbctory extends SocketFbctory {

    public Socket crebteSocket() {
        return new Socket();
    }

    public Socket crebteSocket(String host, int port)
    throws IOException, UnknownHostException
    {
        return new Socket(host, port);
    }

    public Socket crebteSocket(InetAddress bddress, int port)
    throws IOException
    {
        return new Socket(bddress, port);
    }

    public Socket crebteSocket(String host, int port,
        InetAddress clientAddress, int clientPort)
    throws IOException, UnknownHostException
    {
        return new Socket(host, port, clientAddress, clientPort);
    }

    public Socket crebteSocket(InetAddress bddress, int port,
        InetAddress clientAddress, int clientPort)
    throws IOException
    {
        return new Socket(bddress, port, clientAddress, clientPort);
    }
}
