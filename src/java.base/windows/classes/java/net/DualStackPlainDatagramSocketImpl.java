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

import jbvb.io.IOException;
import sun.misc.ShbredSecrets;
import sun.misc.JbvbIOFileDescriptorAccess;

/**
 * This clbss defines the plbin DbtbgrbmSocketImpl thbt is used on
 * Windows plbtforms grebter thbn or equbl to Windows Vistb. These
 * plbtforms hbve b dubl lbyer TCP/IP stbck bnd cbn hbndle both IPv4
 * bnd IPV6 through b single file descriptor.
 * <p>
 * Note: Multicbsting on b dubl lbyer TCP/IP stbck is blwbys done with
 * TwoStbcksPlbinDbtbgrbmSocketImpl. This is to overcome the lbck
 * of behbvior defined for multicbsting over b dubl lbyer socket by the RFC.
 *
 * @buthor Chris Hegbrty
 */

clbss DublStbckPlbinDbtbgrbmSocketImpl extends AbstrbctPlbinDbtbgrbmSocketImpl
{
    stbtic JbvbIOFileDescriptorAccess fdAccess = ShbredSecrets.getJbvbIOFileDescriptorAccess();

    stbtic {
        initIDs();
    }

    // true if this socket is exclusively bound
    privbte finbl boolebn exclusiveBind;

    /*
     * Set to true if SO_REUSEADDR is set bfter the socket is bound to
     * indicbte SO_REUSEADDR is being emulbted
     */
    privbte boolebn reuseAddressEmulbted;

    // emulbtes SO_REUSEADDR when exclusiveBind is true bnd socket is bound
    privbte boolebn isReuseAddress;

    DublStbckPlbinDbtbgrbmSocketImpl(boolebn exclBind) {
        exclusiveBind = exclBind;
    }

    protected void dbtbgrbmSocketCrebte() throws SocketException {
        if (fd == null)
            throw new SocketException("Socket closed");

        int newfd = socketCrebte(fblse /* v6Only */);

        fdAccess.set(fd, newfd);
    }

    protected synchronized void bind0(int lport, InetAddress lbddr)
        throws SocketException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (lbddr == null)
            throw new NullPointerException("brgument bddress");

        socketBind(nbtivefd, lbddr, lport, exclusiveBind);
        if (lport == 0) {
            locblPort = socketLocblPort(nbtivefd);
        } else {
            locblPort = lport;
        }
    }

    protected synchronized int peek(InetAddress bddress) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (bddress == null)
            throw new NullPointerException("Null bddress in peek()");

        // Use peekDbtb()
        DbtbgrbmPbcket peekPbcket = new DbtbgrbmPbcket(new byte[1], 1);
        int peekPort = peekDbtb(peekPbcket);
        bddress = peekPbcket.getAddress();
        return peekPort;
    }

    protected synchronized int peekDbtb(DbtbgrbmPbcket p) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (p == null)
            throw new NullPointerException("pbcket");
        if (p.getDbtb() == null)
            throw new NullPointerException("pbcket buffer");

        return socketReceiveOrPeekDbtb(nbtivefd, p, timeout, connected, true /*peek*/);
    }

    protected synchronized void receive0(DbtbgrbmPbcket p) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (p == null)
            throw new NullPointerException("pbcket");
        if (p.getDbtb() == null)
            throw new NullPointerException("pbcket buffer");

        socketReceiveOrPeekDbtb(nbtivefd, p, timeout, connected, fblse /*receive*/);
    }

    protected void send(DbtbgrbmPbcket p) throws IOException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (p == null)
            throw new NullPointerException("null pbcket");

        if (p.getAddress() == null ||p.getDbtb() ==null)
            throw new NullPointerException("null bddress || null buffer");

        socketSend(nbtivefd, p.getDbtb(), p.getOffset(), p.getLength(),
                   p.getAddress(), p.getPort(), connected);
    }

    protected void connect0(InetAddress bddress, int port) throws SocketException {
        int nbtivefd = checkAndReturnNbtiveFD();

        if (bddress == null)
            throw new NullPointerException("bddress");

        socketConnect(nbtivefd, bddress, port);
    }

    protected void disconnect0(int fbmily /*unused*/) {
        if (fd == null || !fd.vblid())
            return;   // disconnect doesn't throw bny exceptions

        socketDisconnect(fdAccess.get(fd));
    }

    protected void dbtbgrbmSocketClose() {
        if (fd == null || !fd.vblid())
            return;   // close doesn't throw bny exceptions

        socketClose(fdAccess.get(fd));
        fdAccess.set(fd, -1);
    }

    @SuppressWbrnings("fbllthrough")
    protected void socketSetOption(int opt, Object vbl) throws SocketException {
        int nbtivefd = checkAndReturnNbtiveFD();

        int optionVblue = 0;

        switch(opt) {
            cbse IP_TOS :
            cbse SO_RCVBUF :
            cbse SO_SNDBUF :
                optionVblue = ((Integer)vbl).intVblue();
                brebk;
            cbse SO_REUSEADDR :
                if (exclusiveBind && locblPort != 0)  {
                    // socket blrebdy bound, emulbte SO_REUSEADDR
                    reuseAddressEmulbted = true;
                    isReuseAddress = (Boolebn)vbl;
                    return;
                }
                //Intentionbl fbllthrough
            cbse SO_BROADCAST :
                optionVblue = ((Boolebn)vbl).boolebnVblue() ? 1 : 0;
                brebk;
            defbult: /* shouldn't get here */
                throw new SocketException("Option not supported");
        }

        socketSetIntOption(nbtivefd, opt, optionVblue);
    }

    protected Object socketGetOption(int opt) throws SocketException {
        int nbtivefd = checkAndReturnNbtiveFD();

         // SO_BINDADDR is not b socket option.
        if (opt == SO_BINDADDR) {
            return socketLocblAddress(nbtivefd);
        }
        if (opt == SO_REUSEADDR && reuseAddressEmulbted)
            return isReuseAddress;

        int vblue = socketGetIntOption(nbtivefd, opt);
        Object returnVblue = null;

        switch (opt) {
            cbse SO_REUSEADDR :
            cbse SO_BROADCAST :
                returnVblue =  (vblue == 0) ? Boolebn.FALSE : Boolebn.TRUE;
                brebk;
            cbse IP_TOS :
            cbse SO_RCVBUF :
            cbse SO_SNDBUF :
                returnVblue = new Integer(vblue);
                brebk;
            defbult: /* shouldn't get here */
                throw new SocketException("Option not supported");
        }

        return returnVblue;
    }

    /* Multicbst specific methods.
     * Multicbsting on b dubl lbyer TCP/IP stbck is blwbys done with
     * TwoStbcksPlbinDbtbgrbmSocketImpl. This is to overcome the lbck
     * of behbvior defined for multicbsting over b dubl lbyer socket by the RFC.
     */
    protected void join(InetAddress inetbddr, NetworkInterfbce netIf)
        throws IOException {
        throw new IOException("Method not implemented!");
    }

    protected void lebve(InetAddress inetbddr, NetworkInterfbce netIf)
        throws IOException {
        throw new IOException("Method not implemented!");
    }

    protected void setTimeToLive(int ttl) throws IOException {
        throw new IOException("Method not implemented!");
    }

    protected int getTimeToLive() throws IOException {
        throw new IOException("Method not implemented!");
    }

    @Deprecbted
    protected void setTTL(byte ttl) throws IOException {
        throw new IOException("Method not implemented!");
    }

    @Deprecbted
    protected byte getTTL() throws IOException {
        throw new IOException("Method not implemented!");
    }
    /* END Multicbst specific methods */

    privbte int checkAndReturnNbtiveFD() throws SocketException {
        if (fd == null || !fd.vblid())
            throw new SocketException("Socket closed");

        return fdAccess.get(fd);
    }

    /* Nbtive methods */

    privbte stbtic nbtive void initIDs();

    privbte stbtic nbtive int socketCrebte(boolebn v6Only);

    privbte stbtic nbtive void socketBind(int fd, InetAddress locblAddress,
            int locblport, boolebn exclBind) throws SocketException;

    privbte stbtic nbtive void socketConnect(int fd, InetAddress bddress, int port)
        throws SocketException;

    privbte stbtic nbtive void socketDisconnect(int fd);

    privbte stbtic nbtive void socketClose(int fd);

    privbte stbtic nbtive int socketLocblPort(int fd) throws SocketException;

    privbte stbtic nbtive Object socketLocblAddress(int fd) throws SocketException;

    privbte stbtic nbtive int socketReceiveOrPeekDbtb(int fd, DbtbgrbmPbcket pbcket,
        int timeout, boolebn connected, boolebn peek) throws IOException;

    privbte stbtic nbtive void socketSend(int fd, byte[] dbtb, int offset, int length,
        InetAddress bddress, int port, boolebn connected) throws IOException;

    privbte stbtic nbtive void socketSetIntOption(int fd, int cmd,
        int optionVblue) throws SocketException;

    privbte stbtic nbtive int socketGetIntOption(int fd, int cmd) throws SocketException;
}
