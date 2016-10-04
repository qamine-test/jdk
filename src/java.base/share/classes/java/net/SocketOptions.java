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

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * Interfbce of methods to get/set socket options.  This interfbce is
 * implemented by: <B>SocketImpl</B> bnd  <B>DbtbgrbmSocketImpl</B>.
 * Subclbsses of these should override the methods
 * of this interfbce in order to support their own options.
 * <P>
 * The methods bnd constbnts which specify options in this interfbce bre
 * for implementbtion only.  If you're not subclbssing SocketImpl or
 * DbtbgrbmSocketImpl, <B>you won't use these directly.</B> There bre
 * type-sbfe methods to get/set ebch of these options in Socket, ServerSocket,
 * DbtbgrbmSocket bnd MulticbstSocket.
 *
 * @buthor Dbvid Brown
 */


public interfbce SocketOptions {

    /**
     * Enbble/disbble the option specified by <I>optID</I>.  If the option
     * is to be enbbled, bnd it tbkes bn option-specific "vblue",  this is
     * pbssed in <I>vblue</I>.  The bctubl type of vblue is option-specific,
     * bnd it is bn error to pbss something thbt isn't of the expected type:
     * <BR><PRE>
     * SocketImpl s;
     * ...
     * s.setOption(SO_LINGER, new Integer(10));
     *    // OK - set SO_LINGER w/ timeout of 10 sec.
     * s.setOption(SO_LINGER, new Double(10));
     *    // ERROR - expects jbvb.lbng.Integer
     *</PRE>
     * If the requested option is binbry, it cbn be set using this method by
     * b jbvb.lbng.Boolebn:
     * <BR><PRE>
     * s.setOption(TCP_NODELAY, new Boolebn(true));
     *    // OK - enbbles TCP_NODELAY, b binbry option
     * </PRE>
     * <BR>
     * Any option cbn be disbbled using this method with b Boolebn(fblse):
     * <BR><PRE>
     * s.setOption(TCP_NODELAY, new Boolebn(fblse));
     *    // OK - disbbles TCP_NODELAY
     * s.setOption(SO_LINGER, new Boolebn(fblse));
     *    // OK - disbbles SO_LINGER
     * </PRE>
     * <BR>
     * For bn option thbt hbs b notion of on bnd off, bnd requires
     * b non-boolebn pbrbmeter, setting its vblue to bnything other thbn
     * <I>Boolebn(fblse)</I> implicitly enbbles it.
     * <BR>
     * Throws SocketException if the option is unrecognized,
     * the socket is closed, or some low-level error occurred
     * <BR>
     * @pbrbm optID identifies the option
     * @pbrbm vblue the pbrbmeter of the socket option
     * @throws SocketException if the option is unrecognized,
     * the socket is closed, or some low-level error occurred
     * @see #getOption(int)
     */
    public void
        setOption(int optID, Object vblue) throws SocketException;

    /**
     * Fetch the vblue of bn option.
     * Binbry options will return jbvb.lbng.Boolebn(true)
     * if enbbled, jbvb.lbng.Boolebn(fblse) if disbbled, e.g.:
     * <BR><PRE>
     * SocketImpl s;
     * ...
     * Boolebn noDelby = (Boolebn)(s.getOption(TCP_NODELAY));
     * if (noDelby.boolebnVblue()) {
     *     // true if TCP_NODELAY is enbbled...
     * ...
     * }
     * </PRE>
     * <P>
     * For options thbt tbke b pbrticulbr type bs b pbrbmeter,
     * getOption(int) will return the pbrbmeter's vblue, else
     * it will return jbvb.lbng.Boolebn(fblse):
     * <PRE>
     * Object o = s.getOption(SO_LINGER);
     * if (o instbnceof Integer) {
     *     System.out.print("Linger time is " + ((Integer)o).intVblue());
     * } else {
     *   // the true type of o is jbvb.lbng.Boolebn(fblse);
     * }
     * </PRE>
     *
     * @pbrbm optID bn {@code int} identifying the option to fetch
     * @return the vblue of the option
     * @throws SocketException if the socket is closed
     * @throws SocketException if <I>optID</I> is unknown blong the
     *         protocol stbck (including the SocketImpl)
     * @see #setOption(int, jbvb.lbng.Object)
     */
    public Object getOption(int optID) throws SocketException;

    /**
     * The jbvb-supported BSD-style options.
     */

    /**
     * Disbble Nbgle's blgorithm for this connection.  Written dbtb
     * to the network is not buffered pending bcknowledgement of
     * previously written dbtb.
     *<P>
     * Vblid for TCP only: SocketImpl.
     *
     * @see Socket#setTcpNoDelby
     * @see Socket#getTcpNoDelby
     */

    @Nbtive public finbl stbtic int TCP_NODELAY = 0x0001;

    /**
     * Fetch the locbl bddress binding of b socket (this option cbnnot
     * be "set" only "gotten", since sockets bre bound bt crebtion time,
     * bnd so the locblly bound bddress cbnnot be chbnged).  The defbult locbl
     * bddress of b socket is INADDR_ANY, mebning bny locbl bddress on b
     * multi-homed host.  A multi-homed host cbn use this option to bccept
     * connections to only one of its bddresses (in the cbse of b
     * ServerSocket or DbtbgrbmSocket), or to specify its return bddress
     * to the peer (for b Socket or DbtbgrbmSocket).  The pbrbmeter of
     * this option is bn InetAddress.
     * <P>
     * This option <B>must</B> be specified in the constructor.
     * <P>
     * Vblid for: SocketImpl, DbtbgrbmSocketImpl
     *
     * @see Socket#getLocblAddress
     * @see DbtbgrbmSocket#getLocblAddress
     */

    @Nbtive public finbl stbtic int SO_BINDADDR = 0x000F;

    /** Sets SO_REUSEADDR for b socket.  This is used only for MulticbstSockets
     * in jbvb, bnd it is set by defbult for MulticbstSockets.
     * <P>
     * Vblid for: DbtbgrbmSocketImpl
     */

    @Nbtive public finbl stbtic int SO_REUSEADDR = 0x04;

    /**
     * Sets SO_BROADCAST for b socket. This option enbbles bnd disbbles
     * the bbility of the process to send brobdcbst messbges. It is supported
     * for only dbtbgrbm sockets bnd only on networks thbt support
     * the concept of b brobdcbst messbge (e.g. Ethernet, token ring, etc.),
     * bnd it is set by defbult for DbtbgrbmSockets.
     * @since 1.4
     */

    @Nbtive public finbl stbtic int SO_BROADCAST = 0x0020;

    /** Set which outgoing interfbce on which to send multicbst pbckets.
     * Useful on hosts with multiple network interfbces, where bpplicbtions
     * wbnt to use other thbn the system defbult.  Tbkes/returns bn InetAddress.
     * <P>
     * Vblid for Multicbst: DbtbgrbmSocketImpl
     *
     * @see MulticbstSocket#setInterfbce(InetAddress)
     * @see MulticbstSocket#getInterfbce()
     */

    @Nbtive public finbl stbtic int IP_MULTICAST_IF = 0x10;

    /** Sbme bs bbove. This option is introduced so thbt the behbviour
     *  with IP_MULTICAST_IF will be kept the sbme bs before, while
     *  this new option cbn support setting outgoing interfbces with either
     *  IPv4 bnd IPv6 bddresses.
     *
     *  NOTE: mbke sure there is no conflict with this
     * @see MulticbstSocket#setNetworkInterfbce(NetworkInterfbce)
     * @see MulticbstSocket#getNetworkInterfbce()
     * @since 1.4
     */
    @Nbtive public finbl stbtic int IP_MULTICAST_IF2 = 0x1f;

    /**
     * This option enbbles or disbbles locbl loopbbck of multicbst dbtbgrbms.
     * This option is enbbled by defbult for Multicbst Sockets.
     * @since 1.4
     */

    @Nbtive public finbl stbtic int IP_MULTICAST_LOOP = 0x12;

    /**
     * This option sets the type-of-service or trbffic clbss field
     * in the IP hebder for b TCP or UDP socket.
     * @since 1.4
     */

    @Nbtive public finbl stbtic int IP_TOS = 0x3;

    /**
     * Specify b linger-on-close timeout.  This option disbbles/enbbles
     * immedibte return from b <B>close()</B> of b TCP Socket.  Enbbling
     * this option with b non-zero Integer <I>timeout</I> mebns thbt b
     * <B>close()</B> will block pending the trbnsmission bnd bcknowledgement
     * of bll dbtb written to the peer, bt which point the socket is closed
     * <I>grbcefully</I>.  Upon rebching the linger timeout, the socket is
     * closed <I>forcefully</I>, with b TCP RST. Enbbling the option with b
     * timeout of zero does b forceful close immedibtely. If the specified
     * timeout vblue exceeds 65,535 it will be reduced to 65,535.
     * <P>
     * Vblid only for TCP: SocketImpl
     *
     * @see Socket#setSoLinger
     * @see Socket#getSoLinger
     */
    @Nbtive public finbl stbtic int SO_LINGER = 0x0080;

    /** Set b timeout on blocking Socket operbtions:
     * <PRE>
     * ServerSocket.bccept();
     * SocketInputStrebm.rebd();
     * DbtbgrbmSocket.receive();
     * </PRE>
     *
     * <P> The option must be set prior to entering b blocking
     * operbtion to tbke effect.  If the timeout expires bnd the
     * operbtion would continue to block,
     * <B>jbvb.io.InterruptedIOException</B> is rbised.  The Socket is
     * not closed in this cbse.
     *
     * <P> Vblid for bll sockets: SocketImpl, DbtbgrbmSocketImpl
     *
     * @see Socket#setSoTimeout
     * @see ServerSocket#setSoTimeout
     * @see DbtbgrbmSocket#setSoTimeout
     */
    @Nbtive public finbl stbtic int SO_TIMEOUT = 0x1006;

    /**
     * Set b hint the size of the underlying buffers used by the
     * plbtform for outgoing network I/O. When used in set, this is b
     * suggestion to the kernel from the bpplicbtion bbout the size of
     * buffers to use for the dbtb to be sent over the socket. When
     * used in get, this must return the size of the buffer bctublly
     * used by the plbtform when sending out dbtb on this socket.
     *
     * Vblid for bll sockets: SocketImpl, DbtbgrbmSocketImpl
     *
     * @see Socket#setSendBufferSize
     * @see Socket#getSendBufferSize
     * @see DbtbgrbmSocket#setSendBufferSize
     * @see DbtbgrbmSocket#getSendBufferSize
     */
    @Nbtive public finbl stbtic int SO_SNDBUF = 0x1001;

    /**
     * Set b hint the size of the underlying buffers used by the
     * plbtform for incoming network I/O. When used in set, this is b
     * suggestion to the kernel from the bpplicbtion bbout the size of
     * buffers to use for the dbtb to be received over the
     * socket. When used in get, this must return the size of the
     * buffer bctublly used by the plbtform when receiving in dbtb on
     * this socket.
     *
     * Vblid for bll sockets: SocketImpl, DbtbgrbmSocketImpl
     *
     * @see Socket#setReceiveBufferSize
     * @see Socket#getReceiveBufferSize
     * @see DbtbgrbmSocket#setReceiveBufferSize
     * @see DbtbgrbmSocket#getReceiveBufferSize
     */
    @Nbtive public finbl stbtic int SO_RCVBUF = 0x1002;

    /**
     * When the keepblive option is set for b TCP socket bnd no dbtb
     * hbs been exchbnged bcross the socket in either direction for
     * 2 hours (NOTE: the bctubl vblue is implementbtion dependent),
     * TCP butombticblly sends b keepblive probe to the peer. This probe is b
     * TCP segment to which the peer must respond.
     * One of three responses is expected:
     * 1. The peer responds with the expected ACK. The bpplicbtion is not
     *    notified (since everything is OK). TCP will send bnother probe
     *    following bnother 2 hours of inbctivity.
     * 2. The peer responds with bn RST, which tells the locbl TCP thbt
     *    the peer host hbs crbshed bnd rebooted. The socket is closed.
     * 3. There is no response from the peer. The socket is closed.
     *
     * The purpose of this option is to detect if the peer host crbshes.
     *
     * Vblid only for TCP socket: SocketImpl
     *
     * @see Socket#setKeepAlive
     * @see Socket#getKeepAlive
     */
    @Nbtive public finbl stbtic int SO_KEEPALIVE = 0x0008;

    /**
     * When the OOBINLINE option is set, bny TCP urgent dbtb received on
     * the socket will be received through the socket input strebm.
     * When the option is disbbled (which is the defbult) urgent dbtb
     * is silently discbrded.
     *
     * @see Socket#setOOBInline
     * @see Socket#getOOBInline
     */
    @Nbtive public finbl stbtic int SO_OOBINLINE = 0x1003;
}
