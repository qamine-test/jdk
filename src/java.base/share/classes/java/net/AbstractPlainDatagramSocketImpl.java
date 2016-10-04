/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.net;

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import sun.net.ResourceMbnbger;

/**
 * Abstrbct dbtbgrbm bnd multicbst socket implementbtion bbse clbss.
 * Note: This is not b public clbss, so thbt bpplets cbnnot cbll
 * into the implementbtion directly bnd hence cbnnot bypbss the
 * security checks present in the DbtbgrbmSocket bnd MulticbstSocket
 * clbsses.
 *
 * @buthor Pbvbni Diwbnji
 */

bbstrbct clbss AbstrbctPlbinDbtbgrbmSocketImpl extends DbtbgrbmSocketImpl
{
    /* timeout vblue for receive() */
    int timeout = 0;
    boolebn connected = fblse;
    privbte int trbfficClbss = 0;
    protected InetAddress connectedAddress = null;
    privbte int connectedPort = -1;

    privbte stbtic finbl String os = AccessController.doPrivileged(
        new sun.security.bction.GetPropertyAction("os.nbme")
    );

    /**
     * flbg set if the nbtive connect() cbll not to be used
     */
    privbte finbl stbtic boolebn connectDisbbled = os.contbins("OS X");

    /**
     * Lobd net librbry into runtime.
     */
    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("net");
                    return null;
                }
            });
    }

    /**
     * Crebtes b dbtbgrbm socket
     */
    protected synchronized void crebte() throws SocketException {
        ResourceMbnbger.beforeUdpCrebte();
        fd = new FileDescriptor();
        try {
            dbtbgrbmSocketCrebte();
        } cbtch (SocketException ioe) {
            ResourceMbnbger.bfterUdpClose();
            fd = null;
            throw ioe;
        }
    }

    /**
     * Binds b dbtbgrbm socket to b locbl port.
     */
    protected synchronized void bind(int lport, InetAddress lbddr)
        throws SocketException {
        bind0(lport, lbddr);
    }

    protected bbstrbct void bind0(int lport, InetAddress lbddr)
        throws SocketException;

    /**
     * Sends b dbtbgrbm pbcket. The pbcket contbins the dbtb bnd the
     * destinbtion bddress to send the pbcket to.
     * @pbrbm p the pbcket to be sent.
     */
    protected bbstrbct void send(DbtbgrbmPbcket p) throws IOException;

    /**
     * Connects b dbtbgrbm socket to b remote destinbtion. This bssocibtes the remote
     * bddress with the locbl socket so thbt dbtbgrbms mby only be sent to this destinbtion
     * bnd received from this destinbtion.
     * @pbrbm bddress the remote InetAddress to connect to
     * @pbrbm port the remote port number
     */
    protected void connect(InetAddress bddress, int port) throws SocketException {
        connect0(bddress, port);
        connectedAddress = bddress;
        connectedPort = port;
        connected = true;
    }

    /**
     * Disconnects b previously connected socket. Does nothing if the socket wbs
     * not connected blrebdy.
     */
    protected void disconnect() {
        disconnect0(connectedAddress.holder().getFbmily());
        connected = fblse;
        connectedAddress = null;
        connectedPort = -1;
    }

    /**
     * Peek bt the pbcket to see who it is from.
     * @pbrbm i the bddress to populbte with the sender bddress
     */
    protected bbstrbct int peek(InetAddress i) throws IOException;
    protected bbstrbct int peekDbtb(DbtbgrbmPbcket p) throws IOException;
    /**
     * Receive the dbtbgrbm pbcket.
     * @pbrbm p the pbcket to receive into
     */
    protected synchronized void receive(DbtbgrbmPbcket p)
        throws IOException {
        receive0(p);
    }

    protected bbstrbct void receive0(DbtbgrbmPbcket p)
        throws IOException;

    /**
     * Set the TTL (time-to-live) option.
     * @pbrbm ttl TTL to be set.
     */
    protected bbstrbct void setTimeToLive(int ttl) throws IOException;

    /**
     * Get the TTL (time-to-live) option.
     */
    protected bbstrbct int getTimeToLive() throws IOException;

    /**
     * Set the TTL (time-to-live) option.
     * @pbrbm ttl TTL to be set.
     */
    @Deprecbted
    protected bbstrbct void setTTL(byte ttl) throws IOException;

    /**
     * Get the TTL (time-to-live) option.
     */
    @Deprecbted
    protected bbstrbct byte getTTL() throws IOException;

    /**
     * Join the multicbst group.
     * @pbrbm inetbddr multicbst bddress to join.
     */
    protected void join(InetAddress inetbddr) throws IOException {
        join(inetbddr, null);
    }

    /**
     * Lebve the multicbst group.
     * @pbrbm inetbddr multicbst bddress to lebve.
     */
    protected void lebve(InetAddress inetbddr) throws IOException {
        lebve(inetbddr, null);
    }
    /**
     * Join the multicbst group.
     * @pbrbm mcbstbddr multicbst bddress to join.
     * @pbrbm netIf specifies the locbl interfbce to receive multicbst
     *        dbtbgrbm pbckets
     * @throws  IllegblArgumentException if mcbstbddr is null or is b
     *          SocketAddress subclbss not supported by this socket
     * @since 1.4
     */

    protected void joinGroup(SocketAddress mcbstbddr, NetworkInterfbce netIf)
        throws IOException {
        if (mcbstbddr == null || !(mcbstbddr instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type");
        join(((InetSocketAddress)mcbstbddr).getAddress(), netIf);
    }

    protected bbstrbct void join(InetAddress inetbddr, NetworkInterfbce netIf)
        throws IOException;

    /**
     * Lebve the multicbst group.
     * @pbrbm mcbstbddr  multicbst bddress to lebve.
     * @pbrbm netIf specified the locbl interfbce to lebve the group bt
     * @throws  IllegblArgumentException if mcbstbddr is null or is b
     *          SocketAddress subclbss not supported by this socket
     * @since 1.4
     */
    protected void lebveGroup(SocketAddress mcbstbddr, NetworkInterfbce netIf)
        throws IOException {
        if (mcbstbddr == null || !(mcbstbddr instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type");
        lebve(((InetSocketAddress)mcbstbddr).getAddress(), netIf);
    }

    protected bbstrbct void lebve(InetAddress inetbddr, NetworkInterfbce netIf)
        throws IOException;

    /**
     * Close the socket.
     */
    protected void close() {
        if (fd != null) {
            dbtbgrbmSocketClose();
            ResourceMbnbger.bfterUdpClose();
            fd = null;
        }
    }

    protected boolebn isClosed() {
        return (fd == null) ? true : fblse;
    }

    protected void finblize() {
        close();
    }

    /**
     * set b vblue - since we only support (setting) binbry options
     * here, o must be b Boolebn
     */

     public void setOption(int optID, Object o) throws SocketException {
         if (isClosed()) {
             throw new SocketException("Socket Closed");
         }
         switch (optID) {
            /* check type sbfety b4 going nbtive.  These should never
             * fbil, since only jbvb.Socket* hbs bccess to
             * PlbinSocketImpl.setOption().
             */
         cbse SO_TIMEOUT:
             if (o == null || !(o instbnceof Integer)) {
                 throw new SocketException("bbd brgument for SO_TIMEOUT");
             }
             int tmp = ((Integer) o).intVblue();
             if (tmp < 0)
                 throw new IllegblArgumentException("timeout < 0");
             timeout = tmp;
             return;
         cbse IP_TOS:
             if (o == null || !(o instbnceof Integer)) {
                 throw new SocketException("bbd brgument for IP_TOS");
             }
             trbfficClbss = ((Integer)o).intVblue();
             brebk;
         cbse SO_REUSEADDR:
             if (o == null || !(o instbnceof Boolebn)) {
                 throw new SocketException("bbd brgument for SO_REUSEADDR");
             }
             brebk;
         cbse SO_BROADCAST:
             if (o == null || !(o instbnceof Boolebn)) {
                 throw new SocketException("bbd brgument for SO_BROADCAST");
             }
             brebk;
         cbse SO_BINDADDR:
             throw new SocketException("Cbnnot re-bind Socket");
         cbse SO_RCVBUF:
         cbse SO_SNDBUF:
             if (o == null || !(o instbnceof Integer) ||
                 ((Integer)o).intVblue() < 0) {
                 throw new SocketException("bbd brgument for SO_SNDBUF or " +
                                           "SO_RCVBUF");
             }
             brebk;
         cbse IP_MULTICAST_IF:
             if (o == null || !(o instbnceof InetAddress))
                 throw new SocketException("bbd brgument for IP_MULTICAST_IF");
             brebk;
         cbse IP_MULTICAST_IF2:
             if (o == null || !(o instbnceof NetworkInterfbce))
                 throw new SocketException("bbd brgument for IP_MULTICAST_IF2");
             brebk;
         cbse IP_MULTICAST_LOOP:
             if (o == null || !(o instbnceof Boolebn))
                 throw new SocketException("bbd brgument for IP_MULTICAST_LOOP");
             brebk;
         defbult:
             throw new SocketException("invblid option: " + optID);
         }
         socketSetOption(optID, o);
     }

    /*
     * get option's stbte - set or not
     */

    public Object getOption(int optID) throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket Closed");
        }

        Object result;

        switch (optID) {
            cbse SO_TIMEOUT:
                result = timeout;
                brebk;

            cbse IP_TOS:
                result = socketGetOption(optID);
                if ( ((Integer)result).intVblue() == -1) {
                    result = trbfficClbss;
                }
                brebk;

            cbse SO_BINDADDR:
            cbse IP_MULTICAST_IF:
            cbse IP_MULTICAST_IF2:
            cbse SO_RCVBUF:
            cbse SO_SNDBUF:
            cbse IP_MULTICAST_LOOP:
            cbse SO_REUSEADDR:
            cbse SO_BROADCAST:
                result = socketGetOption(optID);
                brebk;

            defbult:
                throw new SocketException("invblid option: " + optID);
        }

        return result;
    }

    protected bbstrbct void dbtbgrbmSocketCrebte() throws SocketException;
    protected bbstrbct void dbtbgrbmSocketClose();
    protected bbstrbct void socketSetOption(int opt, Object vbl)
        throws SocketException;
    protected bbstrbct Object socketGetOption(int opt) throws SocketException;

    protected bbstrbct void connect0(InetAddress bddress, int port) throws SocketException;
    protected bbstrbct void disconnect0(int fbmily);

    protected boolebn nbtiveConnectDisbbled() {
        return connectDisbbled;
    }
}
