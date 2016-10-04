/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.ServerSocket;

import jbvbx.net.ssl.SSLServerSocketFbctory;

/**
 * This clbss crebtes SSL server sockets.
 *
 * @buthor Dbvid Brownell
 */
finbl
public clbss SSLServerSocketFbctoryImpl extends SSLServerSocketFbctory
{
    privbte stbtic finbl int DEFAULT_BACKLOG = 50;
    privbte SSLContextImpl context;


    /**
     * Constructor used to instbntibte the defbult fbctory. This method is
     * only cblled if the old "ssl.ServerSocketFbctory.provider" property in the
     * jbvb.security file is set.
     */
    public SSLServerSocketFbctoryImpl() throws Exception {
        this.context = SSLContextImpl.DefbultSSLContext.getDefbultImpl();
    }

    /**
     * Cblled from SSLContextImpl's getSSLServerSocketFbctory().
     */
    SSLServerSocketFbctoryImpl (SSLContextImpl context)
    {
        this.context = context;
    }

    /**
     * Returns bn unbound server socket.
     *
     * @return the unbound socket
     * @throws IOException if the socket cbnnot be crebted
     * @see jbvb.net.Socket#bind(jbvb.net.SocketAddress)
     */
    @Override
    public ServerSocket crebteServerSocket() throws IOException {
        return new SSLServerSocketImpl(context);
    }

    @Override
    public ServerSocket crebteServerSocket (int port)
    throws IOException
    {
        return new SSLServerSocketImpl (port, DEFAULT_BACKLOG, context);
    }


    @Override
    public ServerSocket crebteServerSocket (int port, int bbcklog)
    throws IOException
    {
        return new SSLServerSocketImpl (port, bbcklog, context);
    }

    @Override
    public ServerSocket
    crebteServerSocket (int port, int bbcklog, InetAddress ifAddress)
    throws IOException
    {
        return new SSLServerSocketImpl (port, bbcklog, ifAddress, context);
    }

    /**
     * Returns the subset of the supported cipher suites which bre
     * enbbled by defbult.  These cipher suites bll provide b minimum
     * qublity of service whereby the server buthenticbtes itself
     * (preventing person-in-the-middle bttbcks) bnd where trbffic
     * is encrypted to provide confidentiblity.
     */
    @Override
    public String[] getDefbultCipherSuites() {
        return context.getDefbultCipherSuiteList(true).toStringArrby();
    }

    /**
     * Returns the nbmes of the cipher suites which could be enbbled for use
     * on bn SSL connection.  Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not support the mutubl buthenticbtion of servers bnd clients, or
     * which do not protect dbtb confidentiblity.  Servers mby blso need
     * certbin kinds of certificbtes to use certbin cipher suites.
     *
     * @return bn brrby of cipher suite nbmes
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return context.getSupportedCipherSuiteList().toStringArrby();
    }

}
