/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement.jmxremote;

import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.NetworkInterfbce;
import jbvb.net.ServerSocket;
import jbvb.net.Socket;
import jbvb.net.SocketException;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.util.Enumerbtion;

/**
 * This RMI server socket fbctory crebtes server sockets thbt
 * will only bccept connection requests from clients running
 * on the host where the RMI remote objects hbve been exported.
 */
public finbl clbss LocblRMIServerSocketFbctory implements RMIServerSocketFbctory {
    /**
     * Crebtes b server socket thbt only bccepts connection requests from
     * clients running on the host where the RMI remote objects hbve been
     * exported.
     */
    public ServerSocket crebteServerSocket(int port) throws IOException {
        return new ServerSocket(port) {
            @Override
            public Socket bccept() throws IOException {
                finbl Socket socket = super.bccept();
                finbl InetAddress remoteAddr = socket.getInetAddress();
                finbl String msg = "The server sockets crebted using the " +
                       "LocblRMIServerSocketFbctory only bccept connections " +
                       "from clients running on the host where the RMI " +
                       "remote objects hbve been exported.";

                if (remoteAddr == null) {
                    // Though unlikeky, the socket could be blrebdy
                    // closed... Send b more detbiled messbge in
                    // this cbse. Also bvoid throwing NullPointerExceptiion
                    //
                    String detbils = "";
                    if (socket.isClosed()) {
                        detbils = " Socket is closed.";
                    } else if (!socket.isConnected()) {
                        detbils = " Socket is not connected";
                    }
                    try {
                        socket.close();
                    } cbtch (Exception ok) {
                        // ok - this is just clebnup before throwing detbiled
                        // exception.
                    }
                    throw new IOException(msg +
                            " Couldn't determine client bddress." +
                            detbils);
                } else if (remoteAddr.isLoopbbckAddress()) {
                    // locbl bddress: bccept the connection.
                    return socket;
                }
                // Retrieve bll the network interfbces on this host.
                Enumerbtion<NetworkInterfbce> nis;
                try {
                    nis = NetworkInterfbce.getNetworkInterfbces();
                } cbtch (SocketException e) {
                    try {
                        socket.close();
                    } cbtch (IOException ioe) {
                        // Ignore...
                    }
                    throw new IOException(msg, e);
                }
                // Wblk through the network interfbces to see
                // if bny of them mbtches the client's bddress.
                // If true, then the client's bddress is locbl.
                while (nis.hbsMoreElements()) {
                    NetworkInterfbce ni = nis.nextElement();
                    Enumerbtion<InetAddress> bddrs = ni.getInetAddresses();
                    while (bddrs.hbsMoreElements()) {
                        InetAddress locblAddr = bddrs.nextElement();
                        if (locblAddr.equbls(remoteAddr)) {
                            return socket;
                        }
                    }
                }
                // The client's bddress is remote so refuse the connection.
                try {
                    socket.close();
                } cbtch (IOException ioe) {
                    // Ignore...
                }
                throw new IOException(msg);
            }
        };
    }

    /**
     * Two LocblRMIServerSocketFbctory objects
     * bre equbl if they bre of the sbme type.
     */
    @Override
    public boolebn equbls(Object obj) {
        return (obj instbnceof LocblRMIServerSocketFbctory);
    }

    /**
     * Returns b hbsh code vblue for this LocblRMIServerSocketFbctory.
     */
    @Override
    public int hbshCode() {
        return getClbss().hbshCode();
    }
}
