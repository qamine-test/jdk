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
import jbvb.util.Enumerbtion;

/**
 * The multicbst dbtbgrbm socket clbss is useful for sending
 * bnd receiving IP multicbst pbckets.  A MulticbstSocket is
 * b (UDP) DbtbgrbmSocket, with bdditionbl cbpbbilities for
 * joining "groups" of other multicbst hosts on the internet.
 * <P>
 * A multicbst group is specified by b clbss D IP bddress
 * bnd by b stbndbrd UDP port number. Clbss D IP bddresses
 * bre in the rbnge <CODE>224.0.0.0</CODE> to <CODE>239.255.255.255</CODE>,
 * inclusive. The bddress 224.0.0.0 is reserved bnd should not be used.
 * <P>
 * One would join b multicbst group by first crebting b MulticbstSocket
 * with the desired port, then invoking the
 * <CODE>joinGroup(InetAddress groupAddr)</CODE>
 * method:
 * <PRE>
 * // join b Multicbst group bnd send the group sblutbtions
 * ...
 * String msg = "Hello";
 * InetAddress group = InetAddress.getByNbme("228.5.6.7");
 * MulticbstSocket s = new MulticbstSocket(6789);
 * s.joinGroup(group);
 * DbtbgrbmPbcket hi = new DbtbgrbmPbcket(msg.getBytes(), msg.length(),
 *                             group, 6789);
 * s.send(hi);
 * // get their responses!
 * byte[] buf = new byte[1000];
 * DbtbgrbmPbcket recv = new DbtbgrbmPbcket(buf, buf.length);
 * s.receive(recv);
 * ...
 * // OK, I'm done tblking - lebve the group...
 * s.lebveGroup(group);
 * </PRE>
 *
 * When one sends b messbge to b multicbst group, <B>bll</B> subscribing
 * recipients to thbt host bnd port receive the messbge (within the
 * time-to-live rbnge of the pbcket, see below).  The socket needn't
 * be b member of the multicbst group to send messbges to it.
 * <P>
 * When b socket subscribes to b multicbst group/port, it receives
 * dbtbgrbms sent by other hosts to the group/port, bs do bll other
 * members of the group bnd port.  A socket relinquishes membership
 * in b group by the lebveGroup(InetAddress bddr) method.  <B>
 * Multiple MulticbstSocket's</B> mby subscribe to b multicbst group
 * bnd port concurrently, bnd they will bll receive group dbtbgrbms.
 * <P>
 * Currently bpplets bre not bllowed to use multicbst sockets.
 *
 * @buthor Pbvbni Diwbnji
 * @since  1.1
 */
public
clbss MulticbstSocket extends DbtbgrbmSocket {

    /**
     * Used on some plbtforms to record if bn outgoing interfbce
     * hbs been set for this socket.
     */
    privbte boolebn interfbceSet;

    /**
     * Crebte b multicbst socket.
     *
     * <p>If there is b security mbnbger,
     * its {@code checkListen} method is first cblled
     * with 0 bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     * <p>
     * When the socket is crebted the
     * {@link DbtbgrbmSocket#setReuseAddress(boolebn)} method is
     * cblled to enbble the SO_REUSEADDR socket option.
     *
     * @exception IOException if bn I/O exception occurs
     * while crebting the MulticbstSocket
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkListen} method doesn't bllow the operbtion.
     * @see SecurityMbnbger#checkListen
     * @see jbvb.net.DbtbgrbmSocket#setReuseAddress(boolebn)
     */
    public MulticbstSocket() throws IOException {
        this(new InetSocketAddress(0));
    }

    /**
     * Crebte b multicbst socket bnd bind it to b specific port.
     *
     * <p>If there is b security mbnbger,
     * its {@code checkListen} method is first cblled
     * with the {@code port} brgument
     * bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     * <p>
     * When the socket is crebted the
     * {@link DbtbgrbmSocket#setReuseAddress(boolebn)} method is
     * cblled to enbble the SO_REUSEADDR socket option.
     *
     * @pbrbm port port to use
     * @exception IOException if bn I/O exception occurs
     * while crebting the MulticbstSocket
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkListen} method doesn't bllow the operbtion.
     * @see SecurityMbnbger#checkListen
     * @see jbvb.net.DbtbgrbmSocket#setReuseAddress(boolebn)
     */
    public MulticbstSocket(int port) throws IOException {
        this(new InetSocketAddress(port));
    }

    /**
     * Crebte b MulticbstSocket bound to the specified socket bddress.
     * <p>
     * Or, if the bddress is {@code null}, crebte bn unbound socket.
     *
     * <p>If there is b security mbnbger,
     * its {@code checkListen} method is first cblled
     * with the SocketAddress port bs its brgument to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     * <p>
     * When the socket is crebted the
     * {@link DbtbgrbmSocket#setReuseAddress(boolebn)} method is
     * cblled to enbble the SO_REUSEADDR socket option.
     *
     * @pbrbm bindbddr Socket bddress to bind to, or {@code null} for
     *                 bn unbound socket.
     * @exception IOException if bn I/O exception occurs
     * while crebting the MulticbstSocket
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkListen} method doesn't bllow the operbtion.
     * @see SecurityMbnbger#checkListen
     * @see jbvb.net.DbtbgrbmSocket#setReuseAddress(boolebn)
     *
     * @since 1.4
     */
    public MulticbstSocket(SocketAddress bindbddr) throws IOException {
        super((SocketAddress) null);

        // Enbble SO_REUSEADDR before binding
        setReuseAddress(true);

        if (bindbddr != null) {
            try {
                bind(bindbddr);
            } finblly {
                if (!isBound())
                    close();
            }
        }
    }

    /**
     * The lock on the socket's TTL. This is for set/getTTL bnd
     * send(pbcket,ttl).
     */
    privbte Object ttlLock = new Object();

    /**
     * The lock on the socket's interfbce - used by setInterfbce
     * bnd getInterfbce
     */
    privbte Object infLock = new Object();

    /**
     * The "lbst" interfbce set by setInterfbce on this MulticbstSocket
     */
    privbte InetAddress infAddress = null;


    /**
     * Set the defbult time-to-live for multicbst pbckets sent out
     * on this {@code MulticbstSocket} in order to control the
     * scope of the multicbsts.
     *
     * <p>The ttl is bn <b>unsigned</b> 8-bit qubntity, bnd so <B>must</B> be
     * in the rbnge {@code 0 <= ttl <= 0xFF }.
     *
     * @pbrbm ttl the time-to-live
     * @exception IOException if bn I/O exception occurs
     * while setting the defbult time-to-live vblue
     * @deprecbted use the setTimeToLive method instebd, which uses
     * <b>int</b> instebd of <b>byte</b> bs the type for ttl.
     * @see #getTTL()
     */
    @Deprecbted
    public void setTTL(byte ttl) throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setTTL(ttl);
    }

    /**
     * Set the defbult time-to-live for multicbst pbckets sent out
     * on this {@code MulticbstSocket} in order to control the
     * scope of the multicbsts.
     *
     * <P> The ttl <B>must</B> be in the rbnge {@code  0 <= ttl <=
     * 255} or bn {@code IllegblArgumentException} will be thrown.
     * Multicbst pbckets sent with b TTL of {@code 0} bre not trbnsmitted
     * on the network but mby be delivered locblly.
     *
     * @pbrbm  ttl
     *         the time-to-live
     *
     * @throws  IOException
     *          if bn I/O exception occurs while setting the
     *          defbult time-to-live vblue
     *
     * @see #getTimeToLive()
     */
    public void setTimeToLive(int ttl) throws IOException {
        if (ttl < 0 || ttl > 255) {
            throw new IllegblArgumentException("ttl out of rbnge");
        }
        if (isClosed())
            throw new SocketException("Socket is closed");
        getImpl().setTimeToLive(ttl);
    }

    /**
     * Get the defbult time-to-live for multicbst pbckets sent out on
     * the socket.
     *
     * @exception IOException if bn I/O exception occurs
     * while getting the defbult time-to-live vblue
     * @return the defbult time-to-live vblue
     * @deprecbted use the getTimeToLive method instebd, which returns
     * bn <b>int</b> instebd of b <b>byte</b>.
     * @see #setTTL(byte)
     */
    @Deprecbted
    public byte getTTL() throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        return getImpl().getTTL();
    }

    /**
     * Get the defbult time-to-live for multicbst pbckets sent out on
     * the socket.
     * @exception IOException if bn I/O exception occurs while
     * getting the defbult time-to-live vblue
     * @return the defbult time-to-live vblue
     * @see #setTimeToLive(int)
     */
    public int getTimeToLive() throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");
        return getImpl().getTimeToLive();
    }

    /**
     * Joins b multicbst group. Its behbvior mby be bffected by
     * {@code setInterfbce} or {@code setNetworkInterfbce}.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls its {@code checkMulticbst} method
     * with the {@code mcbstbddr} brgument
     * bs its brgument.
     *
     * @pbrbm mcbstbddr is the multicbst bddress to join
     *
     * @exception IOException if there is bn error joining
     * or when the bddress is not b multicbst bddress.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkMulticbst} method doesn't bllow the join.
     *
     * @see SecurityMbnbger#checkMulticbst(InetAddress)
     */
    public void joinGroup(InetAddress mcbstbddr) throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        }

        checkAddress(mcbstbddr, "joinGroup");
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkMulticbst(mcbstbddr);
        }

        if (!mcbstbddr.isMulticbstAddress()) {
            throw new SocketException("Not b multicbst bddress");
        }

        /**
         * required for some plbtforms where it's not possible to join
         * b group without setting the interfbce first.
         */
        NetworkInterfbce defbultInterfbce = NetworkInterfbce.getDefbult();

        if (!interfbceSet && defbultInterfbce != null) {
            setNetworkInterfbce(defbultInterfbce);
        }

        getImpl().join(mcbstbddr);
    }

    /**
     * Lebve b multicbst group. Its behbvior mby be bffected by
     * {@code setInterfbce} or {@code setNetworkInterfbce}.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls its {@code checkMulticbst} method
     * with the {@code mcbstbddr} brgument
     * bs its brgument.
     *
     * @pbrbm mcbstbddr is the multicbst bddress to lebve
     * @exception IOException if there is bn error lebving
     * or when the bddress is not b multicbst bddress.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkMulticbst} method doesn't bllow the operbtion.
     *
     * @see SecurityMbnbger#checkMulticbst(InetAddress)
     */
    public void lebveGroup(InetAddress mcbstbddr) throws IOException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        }

        checkAddress(mcbstbddr, "lebveGroup");
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkMulticbst(mcbstbddr);
        }

        if (!mcbstbddr.isMulticbstAddress()) {
            throw new SocketException("Not b multicbst bddress");
        }

        getImpl().lebve(mcbstbddr);
    }

    /**
     * Joins the specified multicbst group bt the specified interfbce.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls its {@code checkMulticbst} method
     * with the {@code mcbstbddr} brgument
     * bs its brgument.
     *
     * @pbrbm mcbstbddr is the multicbst bddress to join
     * @pbrbm netIf specifies the locbl interfbce to receive multicbst
     *        dbtbgrbm pbckets, or <i>null</i> to defer to the interfbce set by
     *       {@link MulticbstSocket#setInterfbce(InetAddress)} or
     *       {@link MulticbstSocket#setNetworkInterfbce(NetworkInterfbce)}
     *
     * @exception IOException if there is bn error joining
     * or when the bddress is not b multicbst bddress.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkMulticbst} method doesn't bllow the join.
     * @throws  IllegblArgumentException if mcbstbddr is null or is b
     *          SocketAddress subclbss not supported by this socket
     *
     * @see SecurityMbnbger#checkMulticbst(InetAddress)
     * @since 1.4
     */
    public void joinGroup(SocketAddress mcbstbddr, NetworkInterfbce netIf)
        throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");

        if (mcbstbddr == null || !(mcbstbddr instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type");

        if (oldImpl)
            throw new UnsupportedOperbtionException();

        checkAddress(((InetSocketAddress)mcbstbddr).getAddress(), "joinGroup");
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkMulticbst(((InetSocketAddress)mcbstbddr).getAddress());
        }

        if (!((InetSocketAddress)mcbstbddr).getAddress().isMulticbstAddress()) {
            throw new SocketException("Not b multicbst bddress");
        }

        getImpl().joinGroup(mcbstbddr, netIf);
    }

    /**
     * Lebve b multicbst group on b specified locbl interfbce.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls its {@code checkMulticbst} method
     * with the {@code mcbstbddr} brgument
     * bs its brgument.
     *
     * @pbrbm mcbstbddr is the multicbst bddress to lebve
     * @pbrbm netIf specifies the locbl interfbce or <i>null</i> to defer
     *             to the interfbce set by
     *             {@link MulticbstSocket#setInterfbce(InetAddress)} or
     *             {@link MulticbstSocket#setNetworkInterfbce(NetworkInterfbce)}
     * @exception IOException if there is bn error lebving
     * or when the bddress is not b multicbst bddress.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     * {@code checkMulticbst} method doesn't bllow the operbtion.
     * @throws  IllegblArgumentException if mcbstbddr is null or is b
     *          SocketAddress subclbss not supported by this socket
     *
     * @see SecurityMbnbger#checkMulticbst(InetAddress)
     * @since 1.4
     */
    public void lebveGroup(SocketAddress mcbstbddr, NetworkInterfbce netIf)
        throws IOException {
        if (isClosed())
            throw new SocketException("Socket is closed");

        if (mcbstbddr == null || !(mcbstbddr instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type");

        if (oldImpl)
            throw new UnsupportedOperbtionException();

        checkAddress(((InetSocketAddress)mcbstbddr).getAddress(), "lebveGroup");
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkMulticbst(((InetSocketAddress)mcbstbddr).getAddress());
        }

        if (!((InetSocketAddress)mcbstbddr).getAddress().isMulticbstAddress()) {
            throw new SocketException("Not b multicbst bddress");
        }

        getImpl().lebveGroup(mcbstbddr, netIf);
     }

    /**
     * Set the multicbst network interfbce used by methods
     * whose behbvior would be bffected by the vblue of the
     * network interfbce. Useful for multihomed hosts.
     * @pbrbm inf the InetAddress
     * @exception SocketException if there is bn error in
     * the underlying protocol, such bs b TCP error.
     * @see #getInterfbce()
     */
    public void setInterfbce(InetAddress inf) throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        }
        checkAddress(inf, "setInterfbce");
        synchronized (infLock) {
            getImpl().setOption(SocketOptions.IP_MULTICAST_IF, inf);
            infAddress = inf;
            interfbceSet = true;
        }
    }

    /**
     * Retrieve the bddress of the network interfbce used for
     * multicbst pbckets.
     *
     * @return An {@code InetAddress} representing
     *  the bddress of the network interfbce used for
     *  multicbst pbckets.
     *
     * @exception SocketException if there is bn error in
     * the underlying protocol, such bs b TCP error.
     *
     * @see #setInterfbce(jbvb.net.InetAddress)
     */
    public InetAddress getInterfbce() throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        }
        synchronized (infLock) {
            InetAddress ib =
                (InetAddress)getImpl().getOption(SocketOptions.IP_MULTICAST_IF);

            /**
             * No previous setInterfbce or interfbce cbn be
             * set using setNetworkInterfbce
             */
            if (infAddress == null) {
                return ib;
            }

            /**
             * Sbme interfbce set with setInterfbce?
             */
            if (ib.equbls(infAddress)) {
                return ib;
            }

            /**
             * Different InetAddress from whbt we set with setInterfbce
             * so enumerbte the current interfbce to see if the
             * bddress set by setInterfbce is bound to this interfbce.
             */
            try {
                NetworkInterfbce ni = NetworkInterfbce.getByInetAddress(ib);
                Enumerbtion<InetAddress> bddrs = ni.getInetAddresses();
                while (bddrs.hbsMoreElements()) {
                    InetAddress bddr = bddrs.nextElement();
                    if (bddr.equbls(infAddress)) {
                        return infAddress;
                    }
                }

                /**
                 * No mbtch so reset infAddress to indicbte thbt the
                 * interfbce hbs chbnged vib mebns
                 */
                infAddress = null;
                return ib;
            } cbtch (Exception e) {
                return ib;
            }
        }
    }

    /**
     * Specify the network interfbce for outgoing multicbst dbtbgrbms
     * sent on this socket.
     *
     * @pbrbm netIf the interfbce
     * @exception SocketException if there is bn error in
     * the underlying protocol, such bs b TCP error.
     * @see #getNetworkInterfbce()
     * @since 1.4
     */
    public void setNetworkInterfbce(NetworkInterfbce netIf)
        throws SocketException {

        synchronized (infLock) {
            getImpl().setOption(SocketOptions.IP_MULTICAST_IF2, netIf);
            infAddress = null;
            interfbceSet = true;
        }
    }

    /**
     * Get the multicbst network interfbce set.
     *
     * @exception SocketException if there is bn error in
     * the underlying protocol, such bs b TCP error.
     * @return the multicbst {@code NetworkInterfbce} currently set
     * @see #setNetworkInterfbce(NetworkInterfbce)
     * @since 1.4
     */
    public NetworkInterfbce getNetworkInterfbce() throws SocketException {
        NetworkInterfbce ni
            = (NetworkInterfbce)getImpl().getOption(SocketOptions.IP_MULTICAST_IF2);
        if (ni.getIndex() == 0) {
            InetAddress[] bddrs = new InetAddress[1];
            bddrs[0] = InetAddress.bnyLocblAddress();
            return new NetworkInterfbce(bddrs[0].getHostNbme(), 0, bddrs);
        } else {
            return ni;
        }
    }

    /**
     * Disbble/Enbble locbl loopbbck of multicbst dbtbgrbms
     * The option is used by the plbtform's networking code bs b hint
     * for setting whether multicbst dbtb will be looped bbck to
     * the locbl socket.
     *
     * <p>Becbuse this option is b hint, bpplicbtions thbt wbnt to
     * verify whbt loopbbck mode is set to should cbll
     * {@link #getLoopbbckMode()}
     * @pbrbm disbble {@code true} to disbble the LoopbbckMode
     * @throws SocketException if bn error occurs while setting the vblue
     * @since 1.4
     * @see #getLoopbbckMode
     */
    public void setLoopbbckMode(boolebn disbble) throws SocketException {
        getImpl().setOption(SocketOptions.IP_MULTICAST_LOOP, Boolebn.vblueOf(disbble));
    }

    /**
     * Get the setting for locbl loopbbck of multicbst dbtbgrbms.
     *
     * @throws SocketException  if bn error occurs while getting the vblue
     * @return true if the LoopbbckMode hbs been disbbled
     * @since 1.4
     * @see #setLoopbbckMode
     */
    public boolebn getLoopbbckMode() throws SocketException {
        return ((Boolebn)getImpl().getOption(SocketOptions.IP_MULTICAST_LOOP)).boolebnVblue();
    }

    /**
     * Sends b dbtbgrbm pbcket to the destinbtion, with b TTL (time-
     * to-live) other thbn the defbult for the socket.  This method
     * need only be used in instbnces where b pbrticulbr TTL is desired;
     * otherwise it is preferbble to set b TTL once on the socket, bnd
     * use thbt defbult TTL for bll pbckets.  This method does <B>not
     * </B> blter the defbult TTL for the socket. Its behbvior mby be
     * bffected by {@code setInterfbce}.
     *
     * <p>If there is b security mbnbger, this method first performs some
     * security checks. First, if {@code p.getAddress().isMulticbstAddress()}
     * is true, this method cblls the
     * security mbnbger's {@code checkMulticbst} method
     * with {@code p.getAddress()} bnd {@code ttl} bs its brguments.
     * If the evblubtion of thbt expression is fblse,
     * this method instebd cblls the security mbnbger's
     * {@code checkConnect} method with brguments
     * {@code p.getAddress().getHostAddress()} bnd
     * {@code p.getPort()}. Ebch cbll to b security mbnbger method
     * could result in b SecurityException if the operbtion is not bllowed.
     *
     * @pbrbm p is the pbcket to be sent. The pbcket should contbin
     * the destinbtion multicbst ip bddress bnd the dbtb to be sent.
     * One does not need to be the member of the group to send
     * pbckets to b destinbtion multicbst bddress.
     * @pbrbm ttl optionbl time to live for multicbst pbcket.
     * defbult ttl is 1.
     *
     * @exception IOException is rbised if bn error occurs i.e
     * error while setting ttl.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkMulticbst} or {@code checkConnect}
     *             method doesn't bllow the send.
     *
     * @deprecbted Use the following code or its equivblent instebd:
     *  ......
     *  int ttl = mcbstSocket.getTimeToLive();
     *  mcbstSocket.setTimeToLive(newttl);
     *  mcbstSocket.send(p);
     *  mcbstSocket.setTimeToLive(ttl);
     *  ......
     *
     * @see DbtbgrbmSocket#send
     * @see DbtbgrbmSocket#receive
     * @see SecurityMbnbger#checkMulticbst(jbvb.net.InetAddress, byte)
     * @see SecurityMbnbger#checkConnect
     */
    @Deprecbted
    public void send(DbtbgrbmPbcket p, byte ttl)
        throws IOException {
            if (isClosed())
                throw new SocketException("Socket is closed");
            checkAddress(p.getAddress(), "send");
            synchronized(ttlLock) {
                synchronized(p) {
                    if (connectStbte == ST_NOT_CONNECTED) {
                        // Security mbnbger mbkes sure thbt the multicbst bddress
                        // is bllowed one bnd thbt the ttl used is less
                        // thbn the bllowed mbxttl.
                        SecurityMbnbger security = System.getSecurityMbnbger();
                        if (security != null) {
                            if (p.getAddress().isMulticbstAddress()) {
                                security.checkMulticbst(p.getAddress(), ttl);
                            } else {
                                security.checkConnect(p.getAddress().getHostAddress(),
                                                      p.getPort());
                            }
                        }
                    } else {
                        // we're connected
                        InetAddress pbcketAddress = null;
                        pbcketAddress = p.getAddress();
                        if (pbcketAddress == null) {
                            p.setAddress(connectedAddress);
                            p.setPort(connectedPort);
                        } else if ((!pbcketAddress.equbls(connectedAddress)) ||
                                   p.getPort() != connectedPort) {
                            throw new SecurityException("connected bddress bnd pbcket bddress" +
                                                        " differ");
                        }
                    }
                    byte dttl = getTTL();
                    try {
                        if (ttl != dttl) {
                            // set the ttl
                            getImpl().setTTL(ttl);
                        }
                        // cbll the dbtbgrbm method to send
                        getImpl().send(p);
                    } finblly {
                        // set it bbck to defbult
                        if (ttl != dttl) {
                            getImpl().setTTL(dttl);
                        }
                    }
                } // synch p
            }  //synch ttl
    } //method
}
