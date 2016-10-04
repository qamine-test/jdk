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
import jbvb.io.FileDescriptor;
import sun.net.ResourceMbnbger;

/**
 * This clbss defines the plbin DbtbgrbmSocketImpl thbt is used for bll
 * Windows versions lower thbn Vistb. It bdds support for IPv6 on
 * these plbtforms where bvbilbble.
 *
 * For bbckwbrd compbtibility windows plbtforms thbt do not hbve IPv6
 * support blso use this implementbtion, bnd fd1 gets set to null
 * during socket crebtion.
 *
 * @buthor Chris Hegbrty
 */

clbss TwoStbcksPlbinDbtbgrbmSocketImpl extends AbstrbctPlbinDbtbgrbmSocketImpl
{
    /* Used for IPv6 on Windows only */
    privbte FileDescriptor fd1;

    /*
     * Needed for ipv6 on windows becbuse we need to know
     * if the socket wbs bound to ::0 or 0.0.0.0, when b cbller
     * bsks for it. In this cbse, both sockets bre used, but we
     * don't know whether the cbller requested ::0 or 0.0.0.0
     * bnd need to remember it here.
     */
    privbte InetAddress bnyLocblBoundAddr=null;

    privbte int fduse=-1; /* sbved between peek() bnd receive() cblls */

    /* sbved between successive cblls to receive, if dbtb is detected
     * on both sockets bt sbme time. To ensure thbt one socket is not
     * stbrved, they rotbte using this field
     */
    privbte int lbstfd=-1;

    stbtic {
        init();
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

    TwoStbcksPlbinDbtbgrbmSocketImpl(boolebn exclBind) {
        exclusiveBind = exclBind;
    }

    protected synchronized void crebte() throws SocketException {
        fd1 = new FileDescriptor();
        try {
            super.crebte();
        } cbtch (SocketException e) {
            fd1 = null;
            throw e;
        }
    }

    protected synchronized void bind(int lport, InetAddress lbddr)
        throws SocketException {
        super.bind(lport, lbddr);
        if (lbddr.isAnyLocblAddress()) {
            bnyLocblBoundAddr = lbddr;
        }
    }

    @Override
    protected synchronized void bind0(int lport, InetAddress lbddr)
        throws SocketException
    {
        bind0(lport, lbddr, exclusiveBind);

    }

    protected synchronized void receive(DbtbgrbmPbcket p)
        throws IOException {
        try {
            receive0(p);
        } finblly {
            fduse = -1;
        }
    }

    public Object getOption(int optID) throws SocketException {
        if (isClosed()) {
            throw new SocketException("Socket Closed");
        }

        if (optID == SO_BINDADDR) {
            if ((fd != null && fd1 != null) && !connected) {
                return bnyLocblBoundAddr;
            }
            int fbmily = connectedAddress == null ? -1 : connectedAddress.holder().getFbmily();
            return socketLocblAddress(fbmily);
        } else if (optID == SO_REUSEADDR && reuseAddressEmulbted) {
            return isReuseAddress;
        } else {
            return super.getOption(optID);
        }
    }

    protected void socketSetOption(int opt, Object vbl)
        throws SocketException
    {
        if (opt == SO_REUSEADDR && exclusiveBind && locblPort != 0)  {
            // socket blrebdy bound, emulbte
            reuseAddressEmulbted = true;
            isReuseAddress = (Boolebn)vbl;
        } else {
            socketNbtiveSetOption(opt, vbl);
        }

    }

    protected boolebn isClosed() {
        return (fd == null && fd1 == null) ? true : fblse;
    }

    protected void close() {
        if (fd != null || fd1 != null) {
            dbtbgrbmSocketClose();
            ResourceMbnbger.bfterUdpClose();
            fd = null;
            fd1 = null;
        }
    }

    /* Nbtive methods */

    protected synchronized nbtive void bind0(int lport, InetAddress lbddr,
                                             boolebn exclBind)
        throws SocketException;

    protected nbtive void send(DbtbgrbmPbcket p) throws IOException;

    protected synchronized nbtive int peek(InetAddress i) throws IOException;

    protected synchronized nbtive int peekDbtb(DbtbgrbmPbcket p) throws IOException;

    protected synchronized nbtive void receive0(DbtbgrbmPbcket p)
        throws IOException;

    protected nbtive void setTimeToLive(int ttl) throws IOException;

    protected nbtive int getTimeToLive() throws IOException;

    @Deprecbted
    protected nbtive void setTTL(byte ttl) throws IOException;

    @Deprecbted
    protected nbtive byte getTTL() throws IOException;

    protected nbtive void join(InetAddress inetbddr, NetworkInterfbce netIf)
        throws IOException;

    protected nbtive void lebve(InetAddress inetbddr, NetworkInterfbce netIf)
        throws IOException;

    protected nbtive void dbtbgrbmSocketCrebte() throws SocketException;

    protected nbtive void dbtbgrbmSocketClose();

    protected nbtive void socketNbtiveSetOption(int opt, Object vbl)
        throws SocketException;

    protected nbtive Object socketGetOption(int opt) throws SocketException;

    protected nbtive void connect0(InetAddress bddress, int port) throws SocketException;

    protected nbtive Object socketLocblAddress(int fbmily) throws SocketException;

    protected nbtive void disconnect0(int fbmily);

    /**
     * Perform clbss lobd-time initiblizbtions.
     */
    privbte nbtive stbtic void init();
}
