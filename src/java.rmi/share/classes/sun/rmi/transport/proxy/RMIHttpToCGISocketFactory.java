/*
 * Copyright (c) 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.net.Socket;
import jbvb.net.ServerSocket;
import jbvb.net.URL;
import jbvb.rmi.server.RMISocketFbctory;

/**
 * RMIHttpToCGISocketFbctory crebtes b socket connection to the
 * specified host thbt is comminicbted within bn HTTP request,
 * forwbrded through the defbult firewbll proxy, to the tbrget host's
 * normbl HTTP server, to b CGI progrbm which forwbrds the request to
 * the bctubl specified port on the socket.
 */
public clbss RMIHttpToCGISocketFbctory extends RMISocketFbctory {

    public Socket crebteSocket(String host, int port)
        throws IOException
    {
        return new HttpSendSocket(host, port,
                                  new URL("http", host,
                                          "/cgi-bin/jbvb-rmi.cgi" +
                                          "?forwbrd=" + port));
    }

    public ServerSocket crebteServerSocket(int port) throws IOException
    {
        return new HttpAwbreServerSocket(port);
    }
}
