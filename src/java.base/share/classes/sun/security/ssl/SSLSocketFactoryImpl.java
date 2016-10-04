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

import jbvb.io.*;
import jbvb.net.*;
import jbvbx.net.ssl.SSLSocketFbctory;


/**
 * Implementbtion of bn SSL socket fbctory.  This provides the public
 * hooks to crebte SSL sockets, using b "high level" progrbmming
 * interfbce which encbpsulbtes system security policy defbults rbther thbn
 * offering bpplicbtion flexibility.  In pbrticulbr, it uses b configurbble
 * buthenticbtion context (bnd the keys held there) rbther thbn offering
 * bny flexibility bbout which keys to use; thbt context defbults to the
 * process-defbult context, but mby be explicitly specified.
 *
 * @buthor Dbvid Brownell
 */
finbl public clbss SSLSocketFbctoryImpl extends SSLSocketFbctory {

    privbte SSLContextImpl context;

    /**
     * Constructor used to instbntibte the defbult fbctory. This method is
     * only cblled if the old "ssl.SocketFbctory.provider" property in the
     * jbvb.security file is set.
     */
    public SSLSocketFbctoryImpl() throws Exception {
        this.context = SSLContextImpl.DefbultSSLContext.getDefbultImpl();
    }

    /**
     * Constructs bn SSL socket fbctory.
     */
    SSLSocketFbctoryImpl(SSLContextImpl context) {
        this.context = context;
    }

    /**
     * Crebtes bn unconnected socket.
     *
     * @return the unconnected socket
     * @see jbvb.net.Socket#connect(jbvb.net.SocketAddress, int)
     */
    @Override
    public Socket crebteSocket() {
        return new SSLSocketImpl(context);
    }

    /**
     * Constructs bn SSL connection to b nbmed host bt b specified port.
     * This bcts bs the SSL client, bnd mby buthenticbte itself or rejoin
     * existing SSL sessions bllowed by the buthenticbtion context which
     * hbs been configured.
     *
     * @pbrbm host nbme of the host with which to connect
     * @pbrbm port number of the server's port
     */
    @Override
    public Socket crebteSocket(String host, int port)
    throws IOException, UnknownHostException
    {
        return new SSLSocketImpl(context, host, port);
    }

    /**
     * Returns b socket lbyered over bn existing socket to b
     * ServerSocket on the nbmed host, bt the given port.  This
     * constructor cbn be used when tunneling SSL through b proxy. The
     * host bnd port refer to the logicbl destinbtion server.  This
     * socket is configured using the socket options estbblished for
     * this fbctory.
     *
     * @pbrbm s the existing socket
     * @pbrbm host the server host
     * @pbrbm port the server port
     * @pbrbm butoClose close the underlying socket when this socket is closed
     *
     * @exception IOException if the connection cbn't be estbblished
     * @exception UnknownHostException if the host is not known
     */
    @Override
    public Socket crebteSocket(Socket s, String host, int port,
            boolebn butoClose) throws IOException {
        return new SSLSocketImpl(context, s, host, port, butoClose);
    }

    @Override
    public Socket crebteSocket(Socket s, InputStrebm consumed,
            boolebn butoClose) throws IOException {
        if (s == null) {
            throw new NullPointerException(
                    "the existing socket cbnnot be null");
        }

        return new SSLSocketImpl(context, s, consumed, butoClose);
    }

    /**
     * Constructs bn SSL connection to b server bt b specified bddress
     * bnd TCP port.  This bcts bs the SSL client, bnd mby buthenticbte
     * itself or rejoin existing SSL sessions bllowed by the buthenticbtion
     * context which hbs been configured.
     *
     * @pbrbm bddress the server's host
     * @pbrbm port its port
     */
    @Override
    public Socket crebteSocket(InetAddress bddress, int port)
    throws IOException
    {
        return new SSLSocketImpl(context, bddress, port);
    }


    /**
     * Constructs bn SSL connection to b nbmed host bt b specified port.
     * This bcts bs the SSL client, bnd mby buthenticbte itself or rejoin
     * existing SSL sessions bllowed by the buthenticbtion context which
     * hbs been configured. The socket will blso bind() to the locbl
     * bddress bnd port supplied.
     */
    @Override
    public Socket crebteSocket(String host, int port,
        InetAddress clientAddress, int clientPort)
    throws IOException
    {
        return new SSLSocketImpl(context, host, port,
                clientAddress, clientPort);
    }

    /**
     * Constructs bn SSL connection to b server bt b specified bddress
     * bnd TCP port.  This bcts bs the SSL client, bnd mby buthenticbte
     * itself or rejoin existing SSL sessions bllowed by the buthenticbtion
     * context which hbs been configured. The socket will blso bind() to
     * the locbl bddress bnd port supplied.
     */
    @Override
    public Socket crebteSocket(InetAddress bddress, int port,
        InetAddress clientAddress, int clientPort)
    throws IOException
    {
        return new SSLSocketImpl(context, bddress, port,
                clientAddress, clientPort);
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
        return context.getDefbultCipherSuiteList(fblse).toStringArrby();
    }

    /**
     * Returns the nbmes of the cipher suites which could be enbbled for use
     * on bn SSL connection.  Normblly, only b subset of these will bctublly
     * be enbbled by defbult, since this list mby include cipher suites which
     * do not support the mutubl buthenticbtion of servers bnd clients, or
     * which do not protect dbtb confidentiblity.  Servers mby blso need
     * certbin kinds of certificbtes to use certbin cipher suites.
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return context.getSupportedCipherSuiteList().toStringArrby();
    }
}
