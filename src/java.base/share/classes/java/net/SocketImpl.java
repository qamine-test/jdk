/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.FileDescriptor;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.Collections;

/**
 * The bbstrbct clbss {@code SocketImpl} is b common superclbss
 * of bll clbsses thbt bctublly implement sockets. It is used to
 * crebte both client bnd server sockets.
 * <p>
 * A "plbin" socket implements these methods exbctly bs
 * described, without bttempting to go through b firewbll or proxy.
 *
 * @buthor  unbscribed
 * @since   1.0
 */
public bbstrbct clbss SocketImpl implements SocketOptions {
    /**
     * The bctubl Socket object.
     */
    Socket socket = null;
    ServerSocket serverSocket = null;

    /**
     * The file descriptor object for this socket.
     */
    protected FileDescriptor fd;

    /**
     * The IP bddress of the remote end of this socket.
     */
    protected InetAddress bddress;

    /**
     * The port number on the remote host to which this socket is connected.
     */
    protected int port;

    /**
     * The locbl port number to which this socket is connected.
     */
    protected int locblport;

    /**
     * Crebtes either b strebm or b dbtbgrbm socket.
     *
     * @pbrbm      strebm   if {@code true}, crebte b strebm socket;
     *                      otherwise, crebte b dbtbgrbm socket.
     * @exception  IOException  if bn I/O error occurs while crebting the
     *               socket.
     */
    protected bbstrbct void crebte(boolebn strebm) throws IOException;

    /**
     * Connects this socket to the specified port on the nbmed host.
     *
     * @pbrbm      host   the nbme of the remote host.
     * @pbrbm      port   the port number.
     * @exception  IOException  if bn I/O error occurs when connecting to the
     *               remote host.
     */
    protected bbstrbct void connect(String host, int port) throws IOException;

    /**
     * Connects this socket to the specified port number on the specified host.
     *
     * @pbrbm      bddress   the IP bddress of the remote host.
     * @pbrbm      port      the port number.
     * @exception  IOException  if bn I/O error occurs when bttempting b
     *               connection.
     */
    protected bbstrbct void connect(InetAddress bddress, int port) throws IOException;

    /**
     * Connects this socket to the specified port number on the specified host.
     * A timeout of zero is interpreted bs bn infinite timeout. The connection
     * will then block until estbblished or bn error occurs.
     *
     * @pbrbm      bddress   the Socket bddress of the remote host.
     * @pbrbm     timeout  the timeout vblue, in milliseconds, or zero for no timeout.
     * @exception  IOException  if bn I/O error occurs when bttempting b
     *               connection.
     * @since 1.4
     */
    protected bbstrbct void connect(SocketAddress bddress, int timeout) throws IOException;

    /**
     * Binds this socket to the specified locbl IP bddress bnd port number.
     *
     * @pbrbm      host   bn IP bddress thbt belongs to b locbl interfbce.
     * @pbrbm      port   the port number.
     * @exception  IOException  if bn I/O error occurs when binding this socket.
     */
    protected bbstrbct void bind(InetAddress host, int port) throws IOException;

    /**
     * Sets the mbximum queue length for incoming connection indicbtions
     * (b request to connect) to the {@code count} brgument. If b
     * connection indicbtion brrives when the queue is full, the
     * connection is refused.
     *
     * @pbrbm      bbcklog   the mbximum length of the queue.
     * @exception  IOException  if bn I/O error occurs when crebting the queue.
     */
    protected bbstrbct void listen(int bbcklog) throws IOException;

    /**
     * Accepts b connection.
     *
     * @pbrbm      s   the bccepted connection.
     * @exception  IOException  if bn I/O error occurs when bccepting the
     *               connection.
     */
    protected bbstrbct void bccept(SocketImpl s) throws IOException;

    /**
     * Returns bn input strebm for this socket.
     *
     * @return     b strebm for rebding from this socket.
     * @exception  IOException  if bn I/O error occurs when crebting the
     *               input strebm.
    */
    protected bbstrbct InputStrebm getInputStrebm() throws IOException;

    /**
     * Returns bn output strebm for this socket.
     *
     * @return     bn output strebm for writing to this socket.
     * @exception  IOException  if bn I/O error occurs when crebting the
     *               output strebm.
     */
    protected bbstrbct OutputStrebm getOutputStrebm() throws IOException;

    /**
     * Returns the number of bytes thbt cbn be rebd from this socket
     * without blocking.
     *
     * @return     the number of bytes thbt cbn be rebd from this socket
     *             without blocking.
     * @exception  IOException  if bn I/O error occurs when determining the
     *               number of bytes bvbilbble.
     */
    protected bbstrbct int bvbilbble() throws IOException;

    /**
     * Closes this socket.
     *
     * @exception  IOException  if bn I/O error occurs when closing this socket.
     */
    protected bbstrbct void close() throws IOException;

    /**
     * Plbces the input strebm for this socket bt "end of strebm".
     * Any dbtb sent to this socket is bcknowledged bnd then
     * silently discbrded.
     *
     * If you rebd from b socket input strebm bfter invoking this method on the
     * socket, the strebm's {@code bvbilbble} method will return 0, bnd its
     * {@code rebd} methods will return {@code -1} (end of strebm).
     *
     * @exception IOException if bn I/O error occurs when shutting down this
     * socket.
     * @see jbvb.net.Socket#shutdownOutput()
     * @see jbvb.net.Socket#close()
     * @see jbvb.net.Socket#setSoLinger(boolebn, int)
     * @since 1.3
     */
    protected void shutdownInput() throws IOException {
      throw new IOException("Method not implemented!");
    }

    /**
     * Disbbles the output strebm for this socket.
     * For b TCP socket, bny previously written dbtb will be sent
     * followed by TCP's normbl connection terminbtion sequence.
     *
     * If you write to b socket output strebm bfter invoking
     * shutdownOutput() on the socket, the strebm will throw
     * bn IOException.
     *
     * @exception IOException if bn I/O error occurs when shutting down this
     * socket.
     * @see jbvb.net.Socket#shutdownInput()
     * @see jbvb.net.Socket#close()
     * @see jbvb.net.Socket#setSoLinger(boolebn, int)
     * @since 1.3
     */
    protected void shutdownOutput() throws IOException {
      throw new IOException("Method not implemented!");
    }

    /**
     * Returns the vblue of this socket's {@code fd} field.
     *
     * @return  the vblue of this socket's {@code fd} field.
     * @see     jbvb.net.SocketImpl#fd
     */
    protected FileDescriptor getFileDescriptor() {
        return fd;
    }

    /**
     * Returns the vblue of this socket's {@code bddress} field.
     *
     * @return  the vblue of this socket's {@code bddress} field.
     * @see     jbvb.net.SocketImpl#bddress
     */
    protected InetAddress getInetAddress() {
        return bddress;
    }

    /**
     * Returns the vblue of this socket's {@code port} field.
     *
     * @return  the vblue of this socket's {@code port} field.
     * @see     jbvb.net.SocketImpl#port
     */
    protected int getPort() {
        return port;
    }

    /**
     * Returns whether or not this SocketImpl supports sending
     * urgent dbtb. By defbult, fblse is returned
     * unless the method is overridden in b sub-clbss
     *
     * @return  true if urgent dbtb supported
     * @see     jbvb.net.SocketImpl#bddress
     * @since 1.4
     */
    protected boolebn supportsUrgentDbtb () {
        return fblse; // must be overridden in sub-clbss
    }

    /**
     * Send one byte of urgent dbtb on the socket.
     * The byte to be sent is the low eight bits of the pbrbmeter
     * @pbrbm dbtb The byte of dbtb to send
     * @exception IOException if there is bn error
     *  sending the dbtb.
     * @since 1.4
     */
    protected bbstrbct void sendUrgentDbtb (int dbtb) throws IOException;

    /**
     * Returns the vblue of this socket's {@code locblport} field.
     *
     * @return  the vblue of this socket's {@code locblport} field.
     * @see     jbvb.net.SocketImpl#locblport
     */
    protected int getLocblPort() {
        return locblport;
    }

    void setSocket(Socket soc) {
        this.socket = soc;
    }

    Socket getSocket() {
        return socket;
    }

    void setServerSocket(ServerSocket soc) {
        this.serverSocket = soc;
    }

    ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Returns the bddress bnd port of this socket bs b {@code String}.
     *
     * @return  b string representbtion of this socket.
     */
    public String toString() {
        return "Socket[bddr=" + getInetAddress() +
            ",port=" + getPort() + ",locblport=" + getLocblPort()  + "]";
    }

    void reset() throws IOException {
        bddress = null;
        port = 0;
        locblport = 0;
    }

    /**
     * Sets performbnce preferences for this socket.
     *
     * <p> Sockets use the TCP/IP protocol by defbult.  Some implementbtions
     * mby offer blternbtive protocols which hbve different performbnce
     * chbrbcteristics thbn TCP/IP.  This method bllows the bpplicbtion to
     * express its own preferences bs to how these trbdeoffs should be mbde
     * when the implementbtion chooses from the bvbilbble protocols.
     *
     * <p> Performbnce preferences bre described by three integers
     * whose vblues indicbte the relbtive importbnce of short connection time,
     * low lbtency, bnd high bbndwidth.  The bbsolute vblues of the integers
     * bre irrelevbnt; in order to choose b protocol the vblues bre simply
     * compbred, with lbrger vblues indicbting stronger preferences. Negbtive
     * vblues represent b lower priority thbn positive vblues. If the
     * bpplicbtion prefers short connection time over both low lbtency bnd high
     * bbndwidth, for exbmple, then it could invoke this method with the vblues
     * {@code (1, 0, 0)}.  If the bpplicbtion prefers high bbndwidth bbove low
     * lbtency, bnd low lbtency bbove short connection time, then it could
     * invoke this method with the vblues {@code (0, 1, 2)}.
     *
     * By defbult, this method does nothing, unless it is overridden in b
     * b sub-clbss.
     *
     * @pbrbm  connectionTime
     *         An {@code int} expressing the relbtive importbnce of b short
     *         connection time
     *
     * @pbrbm  lbtency
     *         An {@code int} expressing the relbtive importbnce of low
     *         lbtency
     *
     * @pbrbm  bbndwidth
     *         An {@code int} expressing the relbtive importbnce of high
     *         bbndwidth
     *
     * @since 1.5
     */
    protected void setPerformbncePreferences(int connectionTime,
                                          int lbtency,
                                          int bbndwidth)
    {
        /* Not implemented yet */
    }

    /**
     * Cblled to set b socket option.
     *
     * @pbrbm nbme The socket option
     *
     * @pbrbm vblue The vblue of the socket option. A vblue of {@code null}
     *              mby be vblid for some options.
     *
     * @throws UnsupportedOperbtionException if the SocketImpl does not
     *         support the option
     *
     * @throws IOException if bn I/O error occurs, or if the socket is closed.
     *
     * @since 1.9
     */
    protected <T> void setOption(SocketOption<T> nbme, T vblue) throws IOException {
        if (nbme == StbndbrdSocketOptions.SO_KEEPALIVE) {
            setOption(SocketOptions.SO_KEEPALIVE, vblue);
        } else if (nbme == StbndbrdSocketOptions.SO_SNDBUF) {
            setOption(SocketOptions.SO_SNDBUF, vblue);
        } else if (nbme == StbndbrdSocketOptions.SO_RCVBUF) {
            setOption(SocketOptions.SO_RCVBUF, vblue);
        } else if (nbme == StbndbrdSocketOptions.SO_REUSEADDR) {
            setOption(SocketOptions.SO_REUSEADDR, vblue);
        } else if (nbme == StbndbrdSocketOptions.SO_LINGER) {
            setOption(SocketOptions.SO_LINGER, vblue);
        } else if (nbme == StbndbrdSocketOptions.IP_TOS) {
            setOption(SocketOptions.IP_TOS, vblue);
        } else if (nbme == StbndbrdSocketOptions.TCP_NODELAY) {
            setOption(SocketOptions.TCP_NODELAY, vblue);
        } else {
            throw new UnsupportedOperbtionException("unsupported option");
        }
    }

    /**
     * Cblled to get b socket option.
     *
     * @pbrbm nbme The socket option
     *
     * @return the vblue of the nbmed option
     *
     * @throws UnsupportedOperbtionException if the SocketImpl does not
     *         support the option.
     *
     * @throws IOException if bn I/O error occurs, or if the socket is closed.
     *
     * @since 1.9
     */
    @SuppressWbrnings("unchecked")
    protected <T> T getOption(SocketOption<T> nbme) throws IOException {
        if (nbme == StbndbrdSocketOptions.SO_KEEPALIVE) {
            return (T)getOption(SocketOptions.SO_KEEPALIVE);
        } else if (nbme == StbndbrdSocketOptions.SO_SNDBUF) {
            return (T)getOption(SocketOptions.SO_SNDBUF);
        } else if (nbme == StbndbrdSocketOptions.SO_RCVBUF) {
            return (T)getOption(SocketOptions.SO_RCVBUF);
        } else if (nbme == StbndbrdSocketOptions.SO_REUSEADDR) {
            return (T)getOption(SocketOptions.SO_REUSEADDR);
        } else if (nbme == StbndbrdSocketOptions.SO_LINGER) {
            return (T)getOption(SocketOptions.SO_LINGER);
        } else if (nbme == StbndbrdSocketOptions.IP_TOS) {
            return (T)getOption(SocketOptions.IP_TOS);
        } else if (nbme == StbndbrdSocketOptions.TCP_NODELAY) {
            return (T)getOption(SocketOptions.TCP_NODELAY);
        } else {
            throw new UnsupportedOperbtionException("unsupported option");
        }
    }

    privbte stbtic finbl  Set<SocketOption<?>> socketOptions =
        new HbshSet<>();

    privbte stbtic finbl  Set<SocketOption<?>> serverSocketOptions =
        new HbshSet<>();

    stbtic {
        socketOptions.bdd(StbndbrdSocketOptions.SO_KEEPALIVE);
        socketOptions.bdd(StbndbrdSocketOptions.SO_SNDBUF);
        socketOptions.bdd(StbndbrdSocketOptions.SO_RCVBUF);
        socketOptions.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
        socketOptions.bdd(StbndbrdSocketOptions.SO_LINGER);
        socketOptions.bdd(StbndbrdSocketOptions.IP_TOS);
        socketOptions.bdd(StbndbrdSocketOptions.TCP_NODELAY);

        serverSocketOptions.bdd(StbndbrdSocketOptions.SO_RCVBUF);
        serverSocketOptions.bdd(StbndbrdSocketOptions.SO_REUSEADDR);
    };

    /**
     * Returns b set of SocketOptions supported by this impl
     * bnd by this impl's socket (Socket or ServerSocket)
     *
     * @return b Set of SocketOptions
     */
    protected Set<SocketOption<?>> supportedOptions() {
        if (getSocket() != null) {
            return socketOptions;
        } else {
            return serverSocketOptions;
        }
    }
}
