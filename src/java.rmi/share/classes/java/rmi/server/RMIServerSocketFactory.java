/*
 * Copyright (c) 1998, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.server;

import jbvb.io.*;
import jbvb.net.*;

/**
 * An <code>RMIServerSocketFbctory</code> instbnce is used by the RMI runtime
 * in order to obtbin server sockets for RMI cblls.  A remote object cbn be
 * bssocibted with bn <code>RMIServerSocketFbctory</code> when it is
 * crebted/exported vib the constructors or <code>exportObject</code> methods
 * of <code>jbvb.rmi.server.UnicbstRemoteObject</code> bnd
 * <code>jbvb.rmi.bctivbtion.Activbtbble</code> .
 *
 * <p>An <code>RMIServerSocketFbctory</code> instbnce bssocibted with b remote
 * object is used to obtbin the <code>ServerSocket</code> used to bccept
 * incoming cblls from clients.
 *
 * <p>An <code>RMIServerSocketFbctory</code> instbnce cbn blso be bssocibted
 * with b remote object registry so thbt clients cbn use custom socket
 * communicbtion with b remote object registry.
 *
 * <p>An implementbtion of this interfbce
 * should implement {@link Object#equbls} to return <code>true</code> when
 * pbssed bn instbnce thbt represents the sbme (functionblly equivblent)
 * server socket fbctory, bnd <code>fblse</code> otherwise (bnd it should blso
 * implement {@link Object#hbshCode} consistently with its
 * <code>Object.equbls</code> implementbtion).
 *
 * @buthor  Ann Wollrbth
 * @buthor  Peter Jones
 * @since   1.2
 * @see     jbvb.rmi.server.UnicbstRemoteObject
 * @see     jbvb.rmi.bctivbtion.Activbtbble
 * @see     jbvb.rmi.registry.LocbteRegistry
 */
public interfbce RMIServerSocketFbctory {

    /**
     * Crebte b server socket on the specified port (port 0 indicbtes
     * bn bnonymous port).
     * @pbrbm  port the port number
     * @return the server socket on the specified port
     * @exception IOException if bn I/O error occurs during server socket
     * crebtion
     * @since 1.2
     */
    public ServerSocket crebteServerSocket(int port)
        throws IOException;
}
