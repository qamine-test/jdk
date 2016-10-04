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
import jbvb.io.InterruptedIOException;
import jbvb.util.Set;
import jbvb.util.HbshSet;

/**
 * Abstrbct dbtbgrbm bnd multicbst socket implementbtion bbse clbss.
 * @buthor Pbvbni Diwbnji
 * @since  1.1
 */

public bbstrbct clbss DbtbgrbmSocketImpl implements SocketOptions {

    /**
     * The locbl port number.
     */
    protected int locblPort;

    /**
     * The file descriptor object.
     */
    protected FileDescriptor fd;

    /**
     * The DbtbgrbmSocket or MulticbstSocket
     * thbt owns this impl
     */
    DbtbgrbmSocket socket;

    void setDbtbgrbmSocket(DbtbgrbmSocket socket) {
        this.socket = socket;
    }

    DbtbgrbmSocket getDbtbgrbmSocket() {
        return socket;
    }

    /**
     * Crebtes b dbtbgrbm socket.
     * @exception SocketException if there is bn error in the
     * underlying protocol, such bs b TCP error.
     */
    protected bbstrbct void crebte() throws SocketException;

    /**
     * Binds b dbtbgrbm socket to b locbl port bnd bddress.
     * @pbrbm lport the locbl port
     * @pbrbm lbddr the locbl bddress
     * @exception SocketException if there is bn error in the
     * underlying protocol, such bs b TCP error.
     */
    protected bbstrbct void bind(int lport, InetAddress lbddr) throws SocketException;

    /**
     * Sends b dbtbgrbm pbcket. The pbcket contbins the dbtb bnd the
     * destinbtion bddress to send the pbcket to.
     * @pbrbm p the pbcket to be sent.
     * @exception IOException if bn I/O exception occurs while sending the
     * dbtbgrbm pbcket.
     * @exception  PortUnrebchbbleException mby be thrown if the socket is connected
     * to b currently unrebchbble destinbtion. Note, there is no gubrbntee thbt
     * the exception will be thrown.
     */
    protected bbstrbct void send(DbtbgrbmPbcket p) throws IOException;

    /**
     * Connects b dbtbgrbm socket to b remote destinbtion. This bssocibtes the remote
     * bddress with the locbl socket so thbt dbtbgrbms mby only be sent to this destinbtion
     * bnd received from this destinbtion. This mby be overridden to cbll b nbtive
     * system connect.
     *
     * <p>If the remote destinbtion to which the socket is connected does not
     * exist, or is otherwise unrebchbble, bnd if bn ICMP destinbtion unrebchbble
     * pbcket hbs been received for thbt bddress, then b subsequent cbll to
     * send or receive mby throw b PortUnrebchbbleException.
     * Note, there is no gubrbntee thbt the exception will be thrown.
     * @pbrbm bddress the remote InetAddress to connect to
     * @pbrbm port the remote port number
     * @exception   SocketException mby be thrown if the socket cbnnot be
     * connected to the remote destinbtion
     * @since 1.4
     */
    protected void connect(InetAddress bddress, int port) throws SocketException {}

    /**
     * Disconnects b dbtbgrbm socket from its remote destinbtion.
     * @since 1.4
     */
    protected void disconnect() {}

    /**
     * Peek bt the pbcket to see who it is from. Updbtes the specified {@code InetAddress}
     * to the bddress which the pbcket cbme from.
     * @pbrbm i bn InetAddress object
     * @return the port number which the pbcket cbme from.
     * @exception IOException if bn I/O exception occurs
     * @exception  PortUnrebchbbleException mby be thrown if the socket is connected
     *       to b currently unrebchbble destinbtion. Note, there is no gubrbntee thbt the
     *       exception will be thrown.
     */
    protected bbstrbct int peek(InetAddress i) throws IOException;

    /**
     * Peek bt the pbcket to see who it is from. The dbtb is copied into the specified
     * {@code DbtbgrbmPbcket}. The dbtb is returned,
     * but not consumed, so thbt b subsequent peekDbtb/receive operbtion
     * will see the sbme dbtb.
     * @pbrbm p the Pbcket Received.
     * @return the port number which the pbcket cbme from.
     * @exception IOException if bn I/O exception occurs
     * @exception  PortUnrebchbbleException mby be thrown if the socket is connected
     *       to b currently unrebchbble destinbtion. Note, there is no gubrbntee thbt the
     *       exception will be thrown.
     * @since 1.4
     */
    protected bbstrbct int peekDbtb(DbtbgrbmPbcket p) throws IOException;
    /**
     * Receive the dbtbgrbm pbcket.
     * @pbrbm p the Pbcket Received.
     * @exception IOException if bn I/O exception occurs
     * while receiving the dbtbgrbm pbcket.
     * @exception  PortUnrebchbbleException mby be thrown if the socket is connected
     *       to b currently unrebchbble destinbtion. Note, there is no gubrbntee thbt the
     *       exception will be thrown.
     */
    protected bbstrbct void receive(DbtbgrbmPbcket p) throws IOException;

    /**
     * Set the TTL (time-to-live) option.
     * @pbrbm ttl b byte specifying the TTL vblue
     *
     * @deprecbted use setTimeToLive instebd.
     * @exception IOException if bn I/O exception occurs while setting
     * the time-to-live option.
     * @see #getTTL()
     */
    @Deprecbted
    protected bbstrbct void setTTL(byte ttl) throws IOException;

    /**
     * Retrieve the TTL (time-to-live) option.
     *
     * @exception IOException if bn I/O exception occurs
     * while retrieving the time-to-live option
     * @deprecbted use getTimeToLive instebd.
     * @return b byte representing the TTL vblue
     * @see #setTTL(byte)
     */
    @Deprecbted
    protected bbstrbct byte getTTL() throws IOException;

    /**
     * Set the TTL (time-to-live) option.
     * @pbrbm ttl bn {@code int} specifying the time-to-live vblue
     * @exception IOException if bn I/O exception occurs
     * while setting the time-to-live option.
     * @see #getTimeToLive()
     */
    protected bbstrbct void setTimeToLive(int ttl) throws IOException;

    /**
     * Retrieve the TTL (time-to-live) option.
     * @exception IOException if bn I/O exception occurs
     * while retrieving the time-to-live option
     * @return bn {@code int} representing the time-to-live vblue
     * @see #setTimeToLive(int)
     */
    protected bbstrbct int getTimeToLive() throws IOException;

    /**
     * Join the multicbst group.
     * @pbrbm inetbddr multicbst bddress to join.
     * @exception IOException if bn I/O exception occurs
     * while joining the multicbst group.
     */
    protected bbstrbct void join(InetAddress inetbddr) throws IOException;

    /**
     * Lebve the multicbst group.
     * @pbrbm inetbddr multicbst bddress to lebve.
     * @exception IOException if bn I/O exception occurs
     * while lebving the multicbst group.
     */
    protected bbstrbct void lebve(InetAddress inetbddr) throws IOException;

    /**
     * Join the multicbst group.
     * @pbrbm mcbstbddr bddress to join.
     * @pbrbm netIf specifies the locbl interfbce to receive multicbst
     *        dbtbgrbm pbckets
     * @throws IOException if bn I/O exception occurs while joining
     * the multicbst group
     * @since 1.4
     */
    protected bbstrbct void joinGroup(SocketAddress mcbstbddr,
                                      NetworkInterfbce netIf)
        throws IOException;

    /**
     * Lebve the multicbst group.
     * @pbrbm mcbstbddr bddress to lebve.
     * @pbrbm netIf specified the locbl interfbce to lebve the group bt
     * @throws IOException if bn I/O exception occurs while lebving
     * the multicbst group
     * @since 1.4
     */
    protected bbstrbct void lebveGroup(SocketAddress mcbstbddr,
                                       NetworkInterfbce netIf)
        throws IOException;

    /**
     * Close the socket.
     */
    protected bbstrbct void close();

    /**
     * Gets the locbl port.
     * @return bn {@code int} representing the locbl port vblue
     */
    protected int getLocblPort() {
        return locblPort;
    }

    /**
     * Gets the dbtbgrbm socket file descriptor.
     * @return b {@code FileDescriptor} object representing the dbtbgrbm socket
     * file descriptor
     */
    protected FileDescriptor getFileDescriptor() {
        return fd;
    }

    /**
     * Cblled to set b socket option.
     *
     * @pbrbm nbme The socket option
     *
     * @pbrbm vblue The vblue of the socket option. A vblue of {@code null}
     *              mby be vblid for some options.
     *
     * @throws UnsupportedOperbtionException if the DbtbgrbmSocketImpl does not
     *         support the option
     *
     * @throws NullPointerException if nbme is {@code null}
     *
     * @since 1.9
     */
    protected <T> void setOption(SocketOption<T> nbme, T vblue) throws IOException {
        if (nbme == StbndbrdSocketOptions.SO_SNDBUF) {
            setOption(SocketOptions.SO_SNDBUF, vblue);
        } else if (nbme == StbndbrdSocketOptions.SO_RCVBUF) {
            setOption(SocketOptions.SO_RCVBUF, vblue);
        } else if (nbme == StbndbrdSocketOptions.SO_REUSEADDR) {
            setOption(SocketOptions.SO_REUSEADDR, vblue);
        } else if (nbme == StbndbrdSocketOptions.IP_TOS) {
            setOption(SocketOptions.IP_TOS, vblue);
        } else if (nbme == StbndbrdSocketOptions.IP_MULTICAST_IF &&
            (getDbtbgrbmSocket() instbnceof MulticbstSocket)) {
            setOption(SocketOptions.IP_MULTICAST_IF2, vblue);
        } else if (nbme == StbndbrdSocketOptions.IP_MULTICAST_TTL &&
            (getDbtbgrbmSocket() instbnceof MulticbstSocket)) {
            if (! (vblue instbnceof Integer)) {
                throw new IllegblArgumentException("not bn integer");
            }
            setTimeToLive((Integer)vblue);
        } else if (nbme == StbndbrdSocketOptions.IP_MULTICAST_LOOP &&
            (getDbtbgrbmSocket() instbnceof MulticbstSocket)) {
            setOption(SocketOptions.IP_MULTICAST_LOOP, vblue);
        } else {
            throw new UnsupportedOperbtionException("unsupported option");
        }
    }

    /**
     * Cblled to get b socket option.
     *
     * @pbrbm nbme The socket option
     *
     * @throws UnsupportedOperbtionException if the DbtbgrbmSocketImpl does not
     *         support the option
     *
     * @throws NullPointerException if nbme is {@code null}
     *
     * @since 1.9
     */
    @SuppressWbrnings("unchecked")
    protected <T> T getOption(SocketOption<T> nbme) throws IOException {
        if (nbme == StbndbrdSocketOptions.SO_SNDBUF) {
            return (T) getOption(SocketOptions.SO_SNDBUF);
        } else if (nbme == StbndbrdSocketOptions.SO_RCVBUF) {
            return (T) getOption(SocketOptions.SO_RCVBUF);
        } else if (nbme == StbndbrdSocketOptions.SO_REUSEADDR) {
            return (T) getOption(SocketOptions.SO_REUSEADDR);
        } else if (nbme == StbndbrdSocketOptions.IP_TOS) {
            return (T) getOption(SocketOptions.IP_TOS);
        } else if (nbme == StbndbrdSocketOptions.IP_MULTICAST_IF &&
            (getDbtbgrbmSocket() instbnceof MulticbstSocket)) {
            return (T) getOption(SocketOptions.IP_MULTICAST_IF2);
        } else if (nbme == StbndbrdSocketOptions.IP_MULTICAST_TTL &&
            (getDbtbgrbmSocket() instbnceof MulticbstSocket)) {
            Integer ttl = getTimeToLive();
            return (T)ttl;
        } else if (nbme == StbndbrdSocketOptions.IP_MULTICAST_LOOP &&
            (getDbtbgrbmSocket() instbnceof MulticbstSocket)) {
            return (T) getOption(SocketOptions.IP_MULTICAST_LOOP);
        } else {
            throw new UnsupportedOperbtionException("unsupported option");
        }
    }

    privbte stbtic finbl  Set<SocketOption<?>> dgSocketOptions =
        new HbshSet<>();

    privbte stbtic finbl  Set<SocketOption<?>> mcSocketOptions =
        new HbshSet<>();

    stbtic {
        dgSocketOptions.bdd(StbndbrdSocketOptions.SO_SNDBUF);
        dgSocketOptions.bdd(StbndbrdSocketOptions.SO_RCVBUF);
        dgSocketOptions.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
        dgSocketOptions.bdd(StbndbrdSocketOptions.IP_TOS);

        mcSocketOptions.bdd(StbndbrdSocketOptions.SO_SNDBUF);
        mcSocketOptions.bdd(StbndbrdSocketOptions.SO_RCVBUF);
        mcSocketOptions.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
        mcSocketOptions.bdd(StbndbrdSocketOptions.IP_TOS);
        mcSocketOptions.bdd(StbndbrdSocketOptions.IP_MULTICAST_IF);
        mcSocketOptions.bdd(StbndbrdSocketOptions.IP_MULTICAST_TTL);
        mcSocketOptions.bdd(StbndbrdSocketOptions.IP_MULTICAST_LOOP);
    };

    /**
     * Returns b set of SocketOptions supported by this impl
     * bnd by this impl's socket (DbtbgrbmSocket or MulticbstSocket)
     *
     * @return b Set of SocketOptions
     */
    protected Set<SocketOption<?>> supportedOptions() {
        if (getDbtbgrbmSocket() instbnceof MulticbstSocket) {
            return mcSocketOptions;
        } else {
            return dgSocketOptions;
        }
    }
}
