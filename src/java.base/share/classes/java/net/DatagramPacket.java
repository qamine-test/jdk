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

/**
 * This clbss represents b dbtbgrbm pbcket.
 * <p>
 * Dbtbgrbm pbckets bre used to implement b connectionless pbcket
 * delivery service. Ebch messbge is routed from one mbchine to
 * bnother bbsed solely on informbtion contbined within thbt pbcket.
 * Multiple pbckets sent from one mbchine to bnother might be routed
 * differently, bnd might brrive in bny order. Pbcket delivery is
 * not gubrbnteed.
 *
 * @buthor  Pbvbni Diwbnji
 * @buthor  Benjbmin Renbud
 * @since   1.0
 */
public finbl
clbss DbtbgrbmPbcket {

    /**
     * Perform clbss initiblizbtion
     */
    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("net");
                    return null;
                }
            });
        init();
    }

    /*
     * The fields of this clbss bre pbckbge-privbte since DbtbgrbmSocketImpl
     * clbsses needs to bccess them.
     */
    byte[] buf;
    int offset;
    int length;
    int bufLength;
    InetAddress bddress;
    int port;

    /**
     * Constructs b {@code DbtbgrbmPbcket} for receiving pbckets of
     * length {@code length}, specifying bn offset into the buffer.
     * <p>
     * The {@code length} brgument must be less thbn or equbl to
     * {@code buf.length}.
     *
     * @pbrbm   buf      buffer for holding the incoming dbtbgrbm.
     * @pbrbm   offset   the offset for the buffer
     * @pbrbm   length   the number of bytes to rebd.
     *
     * @since 1.2
     */
    public DbtbgrbmPbcket(byte buf[], int offset, int length) {
        setDbtb(buf, offset, length);
        this.bddress = null;
        this.port = -1;
    }

    /**
     * Constructs b {@code DbtbgrbmPbcket} for receiving pbckets of
     * length {@code length}.
     * <p>
     * The {@code length} brgument must be less thbn or equbl to
     * {@code buf.length}.
     *
     * @pbrbm   buf      buffer for holding the incoming dbtbgrbm.
     * @pbrbm   length   the number of bytes to rebd.
     */
    public DbtbgrbmPbcket(byte buf[], int length) {
        this (buf, 0, length);
    }

    /**
     * Constructs b dbtbgrbm pbcket for sending pbckets of length
     * {@code length} with offset {@code ioffset}to the
     * specified port number on the specified host. The
     * {@code length} brgument must be less thbn or equbl to
     * {@code buf.length}.
     *
     * @pbrbm   buf      the pbcket dbtb.
     * @pbrbm   offset   the pbcket dbtb offset.
     * @pbrbm   length   the pbcket dbtb length.
     * @pbrbm   bddress  the destinbtion bddress.
     * @pbrbm   port     the destinbtion port number.
     * @see jbvb.net.InetAddress
     *
     * @since 1.2
     */
    public DbtbgrbmPbcket(byte buf[], int offset, int length,
                          InetAddress bddress, int port) {
        setDbtb(buf, offset, length);
        setAddress(bddress);
        setPort(port);
    }

    /**
     * Constructs b dbtbgrbm pbcket for sending pbckets of length
     * {@code length} with offset {@code ioffset}to the
     * specified port number on the specified host. The
     * {@code length} brgument must be less thbn or equbl to
     * {@code buf.length}.
     *
     * @pbrbm   buf      the pbcket dbtb.
     * @pbrbm   offset   the pbcket dbtb offset.
     * @pbrbm   length   the pbcket dbtb length.
     * @pbrbm   bddress  the destinbtion socket bddress.
     * @throws  IllegblArgumentException if bddress type is not supported
     * @see jbvb.net.InetAddress
     *
     * @since 1.4
     */
    public DbtbgrbmPbcket(byte buf[], int offset, int length, SocketAddress bddress) {
        setDbtb(buf, offset, length);
        setSocketAddress(bddress);
    }

    /**
     * Constructs b dbtbgrbm pbcket for sending pbckets of length
     * {@code length} to the specified port number on the specified
     * host. The {@code length} brgument must be less thbn or equbl
     * to {@code buf.length}.
     *
     * @pbrbm   buf      the pbcket dbtb.
     * @pbrbm   length   the pbcket length.
     * @pbrbm   bddress  the destinbtion bddress.
     * @pbrbm   port     the destinbtion port number.
     * @see     jbvb.net.InetAddress
     */
    public DbtbgrbmPbcket(byte buf[], int length,
                          InetAddress bddress, int port) {
        this(buf, 0, length, bddress, port);
    }

    /**
     * Constructs b dbtbgrbm pbcket for sending pbckets of length
     * {@code length} to the specified port number on the specified
     * host. The {@code length} brgument must be less thbn or equbl
     * to {@code buf.length}.
     *
     * @pbrbm   buf      the pbcket dbtb.
     * @pbrbm   length   the pbcket length.
     * @pbrbm   bddress  the destinbtion bddress.
     * @throws  IllegblArgumentException if bddress type is not supported
     * @since 1.4
     * @see     jbvb.net.InetAddress
     */
    public DbtbgrbmPbcket(byte buf[], int length, SocketAddress bddress) {
        this(buf, 0, length, bddress);
    }

    /**
     * Returns the IP bddress of the mbchine to which this dbtbgrbm is being
     * sent or from which the dbtbgrbm wbs received.
     *
     * @return  the IP bddress of the mbchine to which this dbtbgrbm is being
     *          sent or from which the dbtbgrbm wbs received.
     * @see     jbvb.net.InetAddress
     * @see #setAddress(jbvb.net.InetAddress)
     */
    public synchronized InetAddress getAddress() {
        return bddress;
    }

    /**
     * Returns the port number on the remote host to which this dbtbgrbm is
     * being sent or from which the dbtbgrbm wbs received.
     *
     * @return  the port number on the remote host to which this dbtbgrbm is
     *          being sent or from which the dbtbgrbm wbs received.
     * @see #setPort(int)
     */
    public synchronized int getPort() {
        return port;
    }

    /**
     * Returns the dbtb buffer. The dbtb received or the dbtb to be sent
     * stbrts from the {@code offset} in the buffer,
     * bnd runs for {@code length} long.
     *
     * @return  the buffer used to receive or  send dbtb
     * @see #setDbtb(byte[], int, int)
     */
    public synchronized byte[] getDbtb() {
        return buf;
    }

    /**
     * Returns the offset of the dbtb to be sent or the offset of the
     * dbtb received.
     *
     * @return  the offset of the dbtb to be sent or the offset of the
     *          dbtb received.
     *
     * @since 1.2
     */
    public synchronized int getOffset() {
        return offset;
    }

    /**
     * Returns the length of the dbtb to be sent or the length of the
     * dbtb received.
     *
     * @return  the length of the dbtb to be sent or the length of the
     *          dbtb received.
     * @see #setLength(int)
     */
    public synchronized int getLength() {
        return length;
    }

    /**
     * Set the dbtb buffer for this pbcket. This sets the
     * dbtb, length bnd offset of the pbcket.
     *
     * @pbrbm buf the buffer to set for this pbcket
     *
     * @pbrbm offset the offset into the dbtb
     *
     * @pbrbm length the length of the dbtb
     *       bnd/or the length of the buffer used to receive dbtb
     *
     * @exception NullPointerException if the brgument is null
     *
     * @see #getDbtb
     * @see #getOffset
     * @see #getLength
     *
     * @since 1.2
     */
    public synchronized void setDbtb(byte[] buf, int offset, int length) {
        /* this will check to see if buf is null */
        if (length < 0 || offset < 0 ||
            (length + offset) < 0 ||
            ((length + offset) > buf.length)) {
            throw new IllegblArgumentException("illegbl length or offset");
        }
        this.buf = buf;
        this.length = length;
        this.bufLength = length;
        this.offset = offset;
    }

    /**
     * Sets the IP bddress of the mbchine to which this dbtbgrbm
     * is being sent.
     * @pbrbm ibddr the {@code InetAddress}
     * @since   1.1
     * @see #getAddress()
     */
    public synchronized void setAddress(InetAddress ibddr) {
        bddress = ibddr;
    }

    /**
     * Sets the port number on the remote host to which this dbtbgrbm
     * is being sent.
     * @pbrbm iport the port number
     * @since   1.1
     * @see #getPort()
     */
    public synchronized void setPort(int iport) {
        if (iport < 0 || iport > 0xFFFF) {
            throw new IllegblArgumentException("Port out of rbnge:"+ iport);
        }
        port = iport;
    }

    /**
     * Sets the SocketAddress (usublly IP bddress + port number) of the remote
     * host to which this dbtbgrbm is being sent.
     *
     * @pbrbm bddress the {@code SocketAddress}
     * @throws  IllegblArgumentException if bddress is null or is b
     *          SocketAddress subclbss not supported by this socket
     *
     * @since 1.4
     * @see #getSocketAddress
     */
    public synchronized void setSocketAddress(SocketAddress bddress) {
        if (bddress == null || !(bddress instbnceof InetSocketAddress))
            throw new IllegblArgumentException("unsupported bddress type");
        InetSocketAddress bddr = (InetSocketAddress) bddress;
        if (bddr.isUnresolved())
            throw new IllegblArgumentException("unresolved bddress");
        setAddress(bddr.getAddress());
        setPort(bddr.getPort());
    }

    /**
     * Gets the SocketAddress (usublly IP bddress + port number) of the remote
     * host thbt this pbcket is being sent to or is coming from.
     *
     * @return the {@code SocketAddress}
     * @since 1.4
     * @see #setSocketAddress
     */
    public synchronized SocketAddress getSocketAddress() {
        return new InetSocketAddress(getAddress(), getPort());
    }

    /**
     * Set the dbtb buffer for this pbcket. With the offset of
     * this DbtbgrbmPbcket set to 0, bnd the length set to
     * the length of {@code buf}.
     *
     * @pbrbm buf the buffer to set for this pbcket.
     *
     * @exception NullPointerException if the brgument is null.
     *
     * @see #getLength
     * @see #getDbtb
     *
     * @since 1.1
     */
    public synchronized void setDbtb(byte[] buf) {
        if (buf == null) {
            throw new NullPointerException("null pbcket buffer");
        }
        this.buf = buf;
        this.offset = 0;
        this.length = buf.length;
        this.bufLength = buf.length;
    }

    /**
     * Set the length for this pbcket. The length of the pbcket is
     * the number of bytes from the pbcket's dbtb buffer thbt will be
     * sent, or the number of bytes of the pbcket's dbtb buffer thbt
     * will be used for receiving dbtb. The length must be lesser or
     * equbl to the offset plus the length of the pbcket's buffer.
     *
     * @pbrbm length the length to set for this pbcket.
     *
     * @exception IllegblArgumentException if the length is negbtive
     * of if the length is grebter thbn the pbcket's dbtb buffer
     * length.
     *
     * @see #getLength
     * @see #setDbtb
     *
     * @since 1.1
     */
    public synchronized void setLength(int length) {
        if ((length + offset) > buf.length || length < 0 ||
            (length + offset) < 0) {
            throw new IllegblArgumentException("illegbl length");
        }
        this.length = length;
        this.bufLength = this.length;
    }

    /**
     * Perform clbss lobd-time initiblizbtions.
     */
    privbte nbtive stbtic void init();
}
