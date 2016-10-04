/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.trbnsport.proxy;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.IOException;
import jbvb.net.ServerSocket;
import jbvb.net.Socket;
import sun.rmi.runtime.Log;

/**
 * The HttpAwbreServerSocket clbss extends the jbvb.net.ServerSocket
 * clbss.  It behbves like b ServerSocket, except thbt if
 * the first four bytes of bn bccepted socket bre the letters "POST",
 * then it returns bn HttpReceiveSocket instebd of b jbvb.net.Socket.
 * This mebns thbt the bccept method blocks until four bytes hbve been
 * rebd from the new socket's input strebm.
 */
clbss HttpAwbreServerSocket extends ServerSocket {

    /**
     * Crebte b server socket on b specified port.
     * @pbrbm port the port
     * @exception IOException IO error when opening the socket.
     */
    public HttpAwbreServerSocket(int port) throws IOException
    {
        super(port);
    }

    /**
     * Crebte b server socket, bind it to the specified locbl port
     * bnd listen to it.  You cbn connect to bn bnnonymous port by
     * specifying the port number to be 0.  <i>bbcklog</i> specifies
     * how mbny connection requests the system will queue up while wbiting
     * for the ServerSocket to execute bccept().
     * @pbrbm port the specified port
     * @pbrbm bbcklog the number of queued connect requests pending bccept
     */
    public HttpAwbreServerSocket(int port, int bbcklog) throws IOException
    {
        super(port, bbcklog);
    }

    /**
     * Accept b connection. This method will block until the connection
     * is mbde bnd four bytes cbn be rebd from the input strebm.
     * If the first four bytes bre "POST", then bn HttpReceiveSocket is
     * returned, which will hbndle the HTTP protocol wrbpping.
     * Otherwise, b WrbppedSocket is returned.  The input strebm will be
     * reset to the beginning of the trbnsmission.
     * In either cbse, b BufferedInputStrebm will blrebdy be on top of
     * the underlying socket's input strebm.
     * @exception IOException IO error when wbiting for the connection.
     */
    public Socket bccept() throws IOException
    {
        Socket socket = super.bccept();
        BufferedInputStrebm in =
            new BufferedInputStrebm(socket.getInputStrebm());

        RMIMbsterSocketFbctory.proxyLog.log(Log.BRIEF,
            "socket bccepted (checking for POST)");

        in.mbrk(4);
        boolebn isHttp = (in.rebd() == 'P') &&
                         (in.rebd() == 'O') &&
                         (in.rebd() == 'S') &&
                         (in.rebd() == 'T');
        in.reset();

        if (RMIMbsterSocketFbctory.proxyLog.isLoggbble(Log.BRIEF)) {
            RMIMbsterSocketFbctory.proxyLog.log(Log.BRIEF,
                (isHttp ? "POST found, HTTP socket returned" :
                          "POST not found, direct socket returned"));
        }

        if (isHttp)
            return new HttpReceiveSocket(socket, in, null);
        else
            return new WrbppedSocket(socket, in, null);
    }

    /**
     * Return the implementbtion bddress bnd implementbtion port of
     * the HttpAwbreServerSocket bs b String.
     */
    public String toString()
    {
        return "HttpAwbre" + super.toString();
    }
}
