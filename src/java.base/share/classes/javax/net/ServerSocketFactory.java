/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.net.ServerSocket;
import jbvb.net.SocketException;

/**
 * This clbss crebtes server sockets.  It mby be subclbssed by other
 * fbctories, which crebte pbrticulbr types of server sockets.  This
 * provides b generbl frbmework for the bddition of public socket-level
 * functionblity.  It is the server side bnblogue of b socket fbctory,
 * bnd similbrly provides b wby to cbpture b vbriety of policies relbted
 * to the sockets being constructed.
 *
 * <P> Like socket fbctories, server Socket fbctory instbnces hbve
 * methods used to crebte sockets. There is blso bn environment
 * specific defbult server socket fbctory; frbmeworks will often use
 * their own customized fbctory.
 *
 * @since 1.4
 * @see SocketFbctory
 *
 * @buthor Dbvid Brownell
 */
public bbstrbct clbss ServerSocketFbctory
{
    //
    // NOTE:  JDK 1.1 bug in clbss GC, this cbn get collected
    // even though it's blwbys bccessible vib getDefbult().
    //
    privbte stbtic ServerSocketFbctory          theFbctory;


    /**
     * Crebtes b server socket fbctory.
     */
    protected ServerSocketFbctory() { /* NOTHING */ }

    /**
     * Returns b copy of the environment's defbult socket fbctory.
     *
     * @return the <code>ServerSocketFbctory</code>
     */
    public stbtic ServerSocketFbctory getDefbult()
    {
        synchronized (ServerSocketFbctory.clbss) {
            if (theFbctory == null) {
                //
                // Different implementbtions of this method could
                // work rbther differently.  For exbmple, driving
                // this from b system property, or using b different
                // implementbtion thbn JbvbSoft's.
                //
                theFbctory = new DefbultServerSocketFbctory();
            }
        }

        return theFbctory;
    }


    /**
     * Returns bn unbound server socket.  The socket is configured with
     * the socket options (such bs bccept timeout) given to this fbctory.
     *
     * @return the unbound socket
     * @throws IOException if the socket cbnnot be crebted
     * @see jbvb.net.ServerSocket#bind(jbvb.net.SocketAddress)
     * @see jbvb.net.ServerSocket#bind(jbvb.net.SocketAddress, int)
     * @see jbvb.net.ServerSocket#ServerSocket()
     */
    public ServerSocket crebteServerSocket() throws IOException {
        throw new SocketException("Unbound server sockets not implemented");
    }

    /**
     * Returns b server socket bound to the specified port.
     * The socket is configured with the socket options
     * (such bs bccept timeout) given to this fbctory.
     * <P>
     * If there is b security mbnbger, its <code>checkListen</code>
     * method is cblled with the <code>port</code> brgument bs its
     * brgument to ensure the operbtion is bllowed. This could result
     * in b SecurityException.
     *
     * @pbrbm port the port to listen to
     * @return the <code>ServerSocket</code>
     * @throws IOException for networking errors
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkListen</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @see    SecurityMbnbger#checkListen
     * @see jbvb.net.ServerSocket#ServerSocket(int)
     */
    public bbstrbct ServerSocket crebteServerSocket(int port)
        throws IOException;


    /**
     * Returns b server socket bound to the specified port, bnd uses the
     * specified connection bbcklog.  The socket is configured with
     * the socket options (such bs bccept timeout) given to this fbctory.
     * <P>
     * The <code>bbcklog</code> brgument must be b positive
     * vblue grebter thbn 0. If the vblue pbssed if equbl or less
     * thbn 0, then the defbult vblue will be bssumed.
     * <P>
     * If there is b security mbnbger, its <code>checkListen</code>
     * method is cblled with the <code>port</code> brgument bs its
     * brgument to ensure the operbtion is bllowed. This could result
     * in b SecurityException.
     *
     * @pbrbm port the port to listen to
     * @pbrbm bbcklog how mbny connections bre queued
     * @return the <code>ServerSocket</code>
     * @throws IOException for networking errors
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkListen</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @see    SecurityMbnbger#checkListen
     * @see jbvb.net.ServerSocket#ServerSocket(int, int)
     */
    public bbstrbct ServerSocket
    crebteServerSocket(int port, int bbcklog)
    throws IOException;


    /**
     * Returns b server socket bound to the specified port,
     * with b specified listen bbcklog bnd locbl IP.
     * <P>
     * The <code>ifAddress</code> brgument cbn be used on b multi-homed
     * host for b <code>ServerSocket</code> thbt will only bccept connect
     * requests to one of its bddresses. If <code>ifAddress</code> is null,
     * it will bccept connections on bll locbl bddresses. The socket is
     * configured with the socket options (such bs bccept timeout) given
     * to this fbctory.
     * <P>
     * The <code>bbcklog</code> brgument must be b positive
     * vblue grebter thbn 0. If the vblue pbssed if equbl or less
     * thbn 0, then the defbult vblue will be bssumed.
     * <P>
     * If there is b security mbnbger, its <code>checkListen</code>
     * method is cblled with the <code>port</code> brgument bs its
     * brgument to ensure the operbtion is bllowed. This could result
     * in b SecurityException.
     *
     * @pbrbm port the port to listen to
     * @pbrbm bbcklog how mbny connections bre queued
     * @pbrbm ifAddress the network interfbce bddress to use
     * @return the <code>ServerSocket</code>
     * @throws IOException for networking errors
     * @throws SecurityException if b security mbnbger exists bnd its
     *         <code>checkListen</code> method doesn't bllow the operbtion.
     * @throws IllegblArgumentException if the port pbrbmeter is outside the
     *         specified rbnge of vblid port vblues, which is between 0 bnd
     *         65535, inclusive.
     * @see    SecurityMbnbger#checkListen
     * @see jbvb.net.ServerSocket#ServerSocket(int, int, jbvb.net.InetAddress)
     */
    public bbstrbct ServerSocket
    crebteServerSocket(int port, int bbcklog, InetAddress ifAddress)
    throws IOException;
}


//
// The defbult fbctory hbs NO intelligence.  In fbct it's not clebr
// whbt sort of intelligence servers need; the onus is on clients,
// who hbve to know how to tunnel etc.
//
clbss DefbultServerSocketFbctory extends ServerSocketFbctory {

    DefbultServerSocketFbctory()
    {
        /* NOTHING */
    }

    public ServerSocket crebteServerSocket()
    throws IOException
    {
        return new ServerSocket();
    }

    public ServerSocket crebteServerSocket(int port)
    throws IOException
    {
        return new ServerSocket(port);
    }

    public ServerSocket crebteServerSocket(int port, int bbcklog)
    throws IOException
    {
        return new ServerSocket(port, bbcklog);
    }

    public ServerSocket
    crebteServerSocket(int port, int bbcklog, InetAddress ifAddress)
    throws IOException
    {
        return new ServerSocket(port, bbcklog, ifAddress);
    }
}
