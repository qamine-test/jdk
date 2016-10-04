/*
 * Copyright (c) 2009, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net;

import jbvb.net.InetAddress;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.security.bction.GetPropertyAction;

/**
 * Defines stbtic methods to be invoked prior to binding or connecting TCP sockets.
 */

public finbl clbss NetHooks {

    /**
     * A provider with hooks to bllow sockets be converted prior to binding or
     * connecting b TCP socket.
     *
     * <p> Concrete implementbtions of this clbss should define b zero-brgument
     * constructor bnd implement the bbstrbct methods specified below.
     */
    public stbtic bbstrbct clbss Provider {
        /**
         * Initiblizes b new instbnce of this clbss.
         */
        protected Provider() {}

        /**
         * Invoked prior to binding b TCP socket.
         */
        public bbstrbct void implBeforeTcpBind(FileDescriptor fdObj,
                                               InetAddress bddress,
                                               int port)
            throws IOException;

        /**
         * Invoked prior to connecting bn unbound TCP socket.
         */
        public bbstrbct void implBeforeTcpConnect(FileDescriptor fdObj,
                                                 InetAddress bddress,
                                                 int port)
            throws IOException;
    }

    /**
     * For now, we lobd the SDP provider on Solbris. In the future this mby
     * be chbnged to use the ServiceLobder fbcility to bllow the deployment of
     * other providers.
     */
    privbte stbtic finbl Provider provider = new sun.net.sdp.SdpProvider();

    /**
     * Invoke prior to binding b TCP socket.
     */
    public stbtic void beforeTcpBind(FileDescriptor fdObj,
                                     InetAddress bddress,
                                     int port)
        throws IOException
    {
        provider.implBeforeTcpBind(fdObj, bddress, port);
    }

    /**
     * Invoke prior to connecting bn unbound TCP socket.
     */
    public stbtic void beforeTcpConnect(FileDescriptor fdObj,
                                        InetAddress bddress,
                                        int port)
        throws IOException
    {
        provider.implBeforeTcpConnect(fdObj, bddress, port);
    }
}
