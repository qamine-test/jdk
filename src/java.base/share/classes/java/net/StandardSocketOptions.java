/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Defines the <em>stbndbrd</em> socket options.
 *
 * <p> The {@link SocketOption#nbme nbme} of ebch socket option defined by this
 * clbss is its field nbme.
 *
 * <p> In this relebse, the socket options defined here bre used by {@link
 * jbvb.nio.chbnnels.NetworkChbnnel network} chbnnels in the {@link
 * jbvb.nio.chbnnels chbnnels} pbckbge.
 *
 * @since 1.7
 */

public finbl clbss StbndbrdSocketOptions {
    privbte StbndbrdSocketOptions() { }

    // -- SOL_SOCKET --

    /**
     * Allow trbnsmission of brobdcbst dbtbgrbms.
     *
     * <p> The vblue of this socket option is b {@code Boolebn} thbt represents
     * whether the option is enbbled or disbbled. The option is specific to
     * dbtbgrbm-oriented sockets sending to {@link jbvb.net.Inet4Address IPv4}
     * brobdcbst bddresses. When the socket option is enbbled then the socket
     * cbn be used to send <em>brobdcbst dbtbgrbms</em>.
     *
     * <p> The initibl vblue of this socket option is {@code FALSE}. The socket
     * option mby be enbbled or disbbled bt bny time. Some operbting systems mby
     * require thbt the Jbvb virtubl mbchine be stbrted with implementbtion
     * specific privileges to enbble this option or send brobdcbst dbtbgrbms.
     *
     * @see <b href="http://www.ietf.org/rfc/rfc919.txt">RFC&nbsp;929:
     * Brobdcbsting Internet Dbtbgrbms</b>
     * @see DbtbgrbmSocket#setBrobdcbst
     */
    public stbtic finbl SocketOption<Boolebn> SO_BROADCAST =
        new StdSocketOption<Boolebn>("SO_BROADCAST", Boolebn.clbss);

    /**
     * Keep connection blive.
     *
     * <p> The vblue of this socket option is b {@code Boolebn} thbt represents
     * whether the option is enbbled or disbbled. When the {@code SO_KEEPALIVE}
     * option is enbbled the operbting system mby use b <em>keep-blive</em>
     * mechbnism to periodicblly probe the other end of b connection when the
     * connection is otherwise idle. The exbct sembntics of the keep blive
     * mechbnism is system dependent bnd therefore unspecified.
     *
     * <p> The initibl vblue of this socket option is {@code FALSE}. The socket
     * option mby be enbbled or disbbled bt bny time.
     *
     * @see <b href="http://www.ietf.org/rfc/rfc1122.txt">RFC&nbsp;1122
     * Requirements for Internet Hosts -- Communicbtion Lbyers</b>
     * @see Socket#setKeepAlive
     */
    public stbtic finbl SocketOption<Boolebn> SO_KEEPALIVE =
        new StdSocketOption<Boolebn>("SO_KEEPALIVE", Boolebn.clbss);

    /**
     * The size of the socket send buffer.
     *
     * <p> The vblue of this socket option is bn {@code Integer} thbt is the
     * size of the socket send buffer in bytes. The socket send buffer is bn
     * output buffer used by the networking implementbtion. It mby need to be
     * increbsed for high-volume connections. The vblue of the socket option is
     * b <em>hint</em> to the implementbtion to size the buffer bnd the bctubl
     * size mby differ. The socket option cbn be queried to retrieve the bctubl
     * size.
     *
     * <p> For dbtbgrbm-oriented sockets, the size of the send buffer mby limit
     * the size of the dbtbgrbms thbt mby be sent by the socket. Whether
     * dbtbgrbms lbrger thbn the buffer size bre sent or discbrded is system
     * dependent.
     *
     * <p> The initibl/defbult size of the socket send buffer bnd the rbnge of
     * bllowbble vblues is system dependent blthough b negbtive size is not
     * bllowed. An bttempt to set the socket send buffer to lbrger thbn its
     * mbximum size cbuses it to be set to its mbximum size.
     *
     * <p> An implementbtion bllows this socket option to be set before the
     * socket is bound or connected. Whether bn implementbtion bllows the
     * socket send buffer to be chbnged bfter the socket is bound is system
     * dependent.
     *
     * @see Socket#setSendBufferSize
     */
    public stbtic finbl SocketOption<Integer> SO_SNDBUF =
        new StdSocketOption<Integer>("SO_SNDBUF", Integer.clbss);


    /**
     * The size of the socket receive buffer.
     *
     * <p> The vblue of this socket option is bn {@code Integer} thbt is the
     * size of the socket receive buffer in bytes. The socket receive buffer is
     * bn input buffer used by the networking implementbtion. It mby need to be
     * increbsed for high-volume connections or decrebsed to limit the possible
     * bbcklog of incoming dbtb. The vblue of the socket option is b
     * <em>hint</em> to the implementbtion to size the buffer bnd the bctubl
     * size mby differ.
     *
     * <p> For dbtbgrbm-oriented sockets, the size of the receive buffer mby
     * limit the size of the dbtbgrbms thbt cbn be received. Whether dbtbgrbms
     * lbrger thbn the buffer size cbn be received is system dependent.
     * Increbsing the socket receive buffer mby be importbnt for cbses where
     * dbtbgrbms brrive in bursts fbster thbn they cbn be processed.
     *
     * <p> In the cbse of strebm-oriented sockets bnd the TCP/IP protocol, the
     * size of the socket receive buffer mby be used when bdvertising the size
     * of the TCP receive window to the remote peer.
     *
     * <p> The initibl/defbult size of the socket receive buffer bnd the rbnge
     * of bllowbble vblues is system dependent blthough b negbtive size is not
     * bllowed. An bttempt to set the socket receive buffer to lbrger thbn its
     * mbximum size cbuses it to be set to its mbximum size.
     *
     * <p> An implementbtion bllows this socket option to be set before the
     * socket is bound or connected. Whether bn implementbtion bllows the
     * socket receive buffer to be chbnged bfter the socket is bound is system
     * dependent.
     *
     * @see <b href="http://www.ietf.org/rfc/rfc1323.txt">RFC&nbsp;1323: TCP
     * Extensions for High Performbnce</b>
     * @see Socket#setReceiveBufferSize
     * @see ServerSocket#setReceiveBufferSize
     */
    public stbtic finbl SocketOption<Integer> SO_RCVBUF =
        new StdSocketOption<Integer>("SO_RCVBUF", Integer.clbss);

    /**
     * Re-use bddress.
     *
     * <p> The vblue of this socket option is b {@code Boolebn} thbt represents
     * whether the option is enbbled or disbbled. The exbct sembntics of this
     * socket option bre socket type bnd system dependent.
     *
     * <p> In the cbse of strebm-oriented sockets, this socket option will
     * usublly determine whether the socket cbn be bound to b socket bddress
     * when b previous connection involving thbt socket bddress is in the
     * <em>TIME_WAIT</em> stbte. On implementbtions where the sembntics differ,
     * bnd the socket option is not required to be enbbled in order to bind the
     * socket when b previous connection is in this stbte, then the
     * implementbtion mby choose to ignore this option.
     *
     * <p> For dbtbgrbm-oriented sockets the socket option is used to bllow
     * multiple progrbms bind to the sbme bddress. This option should be enbbled
     * when the socket is to be used for Internet Protocol (IP) multicbsting.
     *
     * <p> An implementbtion bllows this socket option to be set before the
     * socket is bound or connected. Chbnging the vblue of this socket option
     * bfter the socket is bound hbs no effect. The defbult vblue of this
     * socket option is system dependent.
     *
     * @see <b href="http://www.ietf.org/rfc/rfc793.txt">RFC&nbsp;793: Trbnsmission
     * Control Protocol</b>
     * @see ServerSocket#setReuseAddress
     */
    public stbtic finbl SocketOption<Boolebn> SO_REUSEADDR =
        new StdSocketOption<Boolebn>("SO_REUSEADDR", Boolebn.clbss);

    /**
     * Linger on close if dbtb is present.
     *
     * <p> The vblue of this socket option is bn {@code Integer} thbt controls
     * the bction tbken when unsent dbtb is queued on the socket bnd b method
     * to close the socket is invoked. If the vblue of the socket option is zero
     * or grebter, then it represents b timeout vblue, in seconds, known bs the
     * <em>linger intervbl</em>. The linger intervbl is the timeout for the
     * {@code close} method to block while the operbting system bttempts to
     * trbnsmit the unsent dbtb or it decides thbt it is unbble to trbnsmit the
     * dbtb. If the vblue of the socket option is less thbn zero then the option
     * is disbbled. In thbt cbse the {@code close} method does not wbit until
     * unsent dbtb is trbnsmitted; if possible the operbting system will trbnsmit
     * bny unsent dbtb before the connection is closed.
     *
     * <p> This socket option is intended for use with sockets thbt bre configured
     * in {@link jbvb.nio.chbnnels.SelectbbleChbnnel#isBlocking() blocking} mode
     * only. The behbvior of the {@code close} method when this option is
     * enbbled on b non-blocking socket is not defined.
     *
     * <p> The initibl vblue of this socket option is b negbtive vblue, mebning
     * thbt the option is disbbled. The option mby be enbbled, or the linger
     * intervbl chbnged, bt bny time. The mbximum vblue of the linger intervbl
     * is system dependent. Setting the linger intervbl to b vblue thbt is
     * grebter thbn its mbximum vblue cbuses the linger intervbl to be set to
     * its mbximum vblue.
     *
     * @see Socket#setSoLinger
     */
    public stbtic finbl SocketOption<Integer> SO_LINGER =
        new StdSocketOption<Integer>("SO_LINGER", Integer.clbss);


    // -- IPPROTO_IP --

    /**
     * The Type of Service (ToS) octet in the Internet Protocol (IP) hebder.
     *
     * <p> The vblue of this socket option is bn {@code Integer} representing
     * the vblue of the ToS octet in IP pbckets sent by sockets to bn {@link
     * StbndbrdProtocolFbmily#INET IPv4} socket. The interpretbtion of the ToS
     * octet is network specific bnd is not defined by this clbss. Further
     * informbtion on the ToS octet cbn be found in <b
     * href="http://www.ietf.org/rfc/rfc1349.txt">RFC&nbsp;1349</b> bnd <b
     * href="http://www.ietf.org/rfc/rfc2474.txt">RFC&nbsp;2474</b>. The vblue
     * of the socket option is b <em>hint</em>. An implementbtion mby ignore the
     * vblue, or ignore specific vblues.
     *
     * <p> The initibl/defbult vblue of the TOS field in the ToS octet is
     * implementbtion specific but will typicblly be {@code 0}. For
     * dbtbgrbm-oriented sockets the option mby be configured bt bny time bfter
     * the socket hbs been bound. The new vblue of the octet is used when sending
     * subsequent dbtbgrbms. It is system dependent whether this option cbn be
     * queried or chbnged prior to binding the socket.
     *
     * <p> The behbvior of this socket option on b strebm-oriented socket, or bn
     * {@link StbndbrdProtocolFbmily#INET6 IPv6} socket, is not defined in this
     * relebse.
     *
     * @see DbtbgrbmSocket#setTrbfficClbss
     */
    public stbtic finbl SocketOption<Integer> IP_TOS =
        new StdSocketOption<Integer>("IP_TOS", Integer.clbss);

    /**
     * The network interfbce for Internet Protocol (IP) multicbst dbtbgrbms.
     *
     * <p> The vblue of this socket option is b {@link NetworkInterfbce} thbt
     * represents the outgoing interfbce for multicbst dbtbgrbms sent by the
     * dbtbgrbm-oriented socket. For {@link StbndbrdProtocolFbmily#INET6 IPv6}
     * sockets then it is system dependent whether setting this option blso
     * sets the outgoing interfbce for multicbst dbtbgrbms sent to IPv4
     * bddresses.
     *
     * <p> The initibl/defbult vblue of this socket option mby be {@code null}
     * to indicbte thbt outgoing interfbce will be selected by the operbting
     * system, typicblly bbsed on the network routing tbbles. An implementbtion
     * bllows this socket option to be set bfter the socket is bound. Whether
     * the socket option cbn be queried or chbnged prior to binding the socket
     * is system dependent.
     *
     * @see jbvb.nio.chbnnels.MulticbstChbnnel
     * @see MulticbstSocket#setInterfbce
     */
    public stbtic finbl SocketOption<NetworkInterfbce> IP_MULTICAST_IF =
        new StdSocketOption<NetworkInterfbce>("IP_MULTICAST_IF", NetworkInterfbce.clbss);

    /**
     * The <em>time-to-live</em> for Internet Protocol (IP) multicbst dbtbgrbms.
     *
     * <p> The vblue of this socket option is bn {@code Integer} in the rbnge
     * {@code 0 <= vblue <= 255}. It is used to control the scope of multicbst
     * dbtbgrbms sent by the dbtbgrbm-oriented socket.
     * In the cbse of bn {@link StbndbrdProtocolFbmily#INET IPv4} socket
     * the option is the time-to-live (TTL) on multicbst dbtbgrbms sent by the
     * socket. Dbtbgrbms with b TTL of zero bre not trbnsmitted on the network
     * but mby be delivered locblly. In the cbse of bn {@link
     * StbndbrdProtocolFbmily#INET6 IPv6} socket the option is the
     * <em>hop limit</em> which is number of <em>hops</em> thbt the dbtbgrbm cbn
     * pbss through before expiring on the network. For IPv6 sockets it is
     * system dependent whether the option blso sets the <em>time-to-live</em>
     * on multicbst dbtbgrbms sent to IPv4 bddresses.
     *
     * <p> The initibl/defbult vblue of the time-to-live setting is typicblly
     * {@code 1}. An implementbtion bllows this socket option to be set bfter
     * the socket is bound. Whether the socket option cbn be queried or chbnged
     * prior to binding the socket is system dependent.
     *
     * @see jbvb.nio.chbnnels.MulticbstChbnnel
     * @see MulticbstSocket#setTimeToLive
     */
    public stbtic finbl SocketOption<Integer> IP_MULTICAST_TTL =
        new StdSocketOption<Integer>("IP_MULTICAST_TTL", Integer.clbss);

    /**
     * Loopbbck for Internet Protocol (IP) multicbst dbtbgrbms.
     *
     * <p> The vblue of this socket option is b {@code Boolebn} thbt controls
     * the <em>loopbbck</em> of multicbst dbtbgrbms. The vblue of the socket
     * option represents if the option is enbbled or disbbled.
     *
     * <p> The exbct sembntics of this socket options bre system dependent.
     * In pbrticulbr, it is system dependent whether the loopbbck bpplies to
     * multicbst dbtbgrbms sent from the socket or received by the socket.
     * For {@link StbndbrdProtocolFbmily#INET6 IPv6} sockets then it is
     * system dependent whether the option blso bpplies to multicbst dbtbgrbms
     * sent to IPv4 bddresses.
     *
     * <p> The initibl/defbult vblue of this socket option is {@code TRUE}. An
     * implementbtion bllows this socket option to be set bfter the socket is
     * bound. Whether the socket option cbn be queried or chbnged prior to
     * binding the socket is system dependent.
     *
     * @see jbvb.nio.chbnnels.MulticbstChbnnel
     *  @see MulticbstSocket#setLoopbbckMode
     */
    public stbtic finbl SocketOption<Boolebn> IP_MULTICAST_LOOP =
        new StdSocketOption<Boolebn>("IP_MULTICAST_LOOP", Boolebn.clbss);


    // -- IPPROTO_TCP --

    /**
     * Disbble the Nbgle blgorithm.
     *
     * <p> The vblue of this socket option is b {@code Boolebn} thbt represents
     * whether the option is enbbled or disbbled. The socket option is specific to
     * strebm-oriented sockets using the TCP/IP protocol. TCP/IP uses bn blgorithm
     * known bs <em>The Nbgle Algorithm</em> to coblesce short segments bnd
     * improve network efficiency.
     *
     * <p> The defbult vblue of this socket option is {@code FALSE}. The
     * socket option should only be enbbled in cbses where it is known thbt the
     * coblescing impbcts performbnce. The socket option mby be enbbled bt bny
     * time. In other words, the Nbgle Algorithm cbn be disbbled. Once the option
     * is enbbled, it is system dependent whether it cbn be subsequently
     * disbbled. If it cbnnot, then invoking the {@code setOption} method to
     * disbble the option hbs no effect.
     *
     * @see <b href="http://www.ietf.org/rfc/rfc1122.txt">RFC&nbsp;1122:
     * Requirements for Internet Hosts -- Communicbtion Lbyers</b>
     * @see Socket#setTcpNoDelby
     */
    public stbtic finbl SocketOption<Boolebn> TCP_NODELAY =
        new StdSocketOption<Boolebn>("TCP_NODELAY", Boolebn.clbss);


    privbte stbtic clbss StdSocketOption<T> implements SocketOption<T> {
        privbte finbl String nbme;
        privbte finbl Clbss<T> type;
        StdSocketOption(String nbme, Clbss<T> type) {
            this.nbme = nbme;
            this.type = type;
        }
        @Override public String nbme() { return nbme; }
        @Override public Clbss<T> type() { return type; }
        @Override public String toString() { return nbme; }
    }
}
