/*
 * Copyright (c) 2001, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;


// Mbke b dbtbgrbm-socket chbnnel look like b dbtbgrbm socket.
//
// The methods in this clbss bre defined in exbctly the sbme order bs in
// jbvb.net.DbtbgrbmSocket so bs to simplify trbcking future chbnges to thbt
// clbss.
//

public clbss DbtbgrbmSocketAdbptor
    extends DbtbgrbmSocket
{

    // The chbnnel being bdbpted
    privbte finbl DbtbgrbmChbnnelImpl dc;

    // Timeout "option" vblue for receives
    privbte volbtile int timeout = 0;

    // ## super will crebte b useless impl
    privbte DbtbgrbmSocketAdbptor(DbtbgrbmChbnnelImpl dc) throws IOException {
        // Invoke the DbtbgrbmSocketAdbptor(SocketAddress) constructor,
        // pbssing b dummy DbtbgrbmSocketImpl object to bovid bny nbtive
        // resource bllocbtion in super clbss bnd invoking our bind method
        // before the dc field is initiblized.
        super(dummyDbtbgrbmSocket);
        this.dc = dc;
    }

    public stbtic DbtbgrbmSocket crebte(DbtbgrbmChbnnelImpl dc) {
        try {
            return new DbtbgrbmSocketAdbptor(dc);
        } cbtch (IOException x) {
            throw new Error(x);
        }
    }

    privbte void connectInternbl(SocketAddress remote)
        throws SocketException
    {
        InetSocketAddress isb = Net.bsInetSocketAddress(remote);
        int port = isb.getPort();
        if (port < 0 || port > 0xFFFF)
            throw new IllegblArgumentException("connect: " + port);
        if (remote == null)
            throw new IllegblArgumentException("connect: null bddress");
        if (isClosed())
            return;
        try {
            dc.connect(remote);
        } cbtch (Exception x) {
            Net.trbnslbteToSocketException(x);
        }
    }

    public void bind(SocketAddress locbl) throws SocketException {
        try {
            if (locbl == null)
                locbl = new InetSocketAddress(0);
            dc.bind(locbl);
        } cbtch (Exception x) {
            Net.trbnslbteToSocketException(x);
        }
    }

    public void connect(InetAddress bddress, int port) {
        try {
            connectInternbl(new InetSocketAddress(bddress, port));
        } cbtch (SocketException x) {
            // Yes, j.n.DbtbgrbmSocket reblly does this
        }
    }

    public void connect(SocketAddress remote) throws SocketException {
        if (remote == null)
            throw new IllegblArgumentException("Address cbn't be null");
        connectInternbl(remote);
    }

    public void disconnect() {
        try {
            dc.disconnect();
        } cbtch (IOException x) {
            throw new Error(x);
        }
    }

    public boolebn isBound() {
        return dc.locblAddress() != null;
    }

    public boolebn isConnected() {
        return dc.remoteAddress() != null;
    }

    public InetAddress getInetAddress() {
        return (isConnected()
                ? Net.bsInetSocketAddress(dc.remoteAddress()).getAddress()
                : null);
    }

    public int getPort() {
        return (isConnected()
                ? Net.bsInetSocketAddress(dc.remoteAddress()).getPort()
                : -1);
    }

    public void send(DbtbgrbmPbcket p) throws IOException {
        synchronized (dc.blockingLock()) {
            if (!dc.isBlocking())
                throw new IllegblBlockingModeException();
            try {
                synchronized (p) {
                    ByteBuffer bb = ByteBuffer.wrbp(p.getDbtb(),
                                                    p.getOffset(),
                                                    p.getLength());
                    if (dc.isConnected()) {
                        if (p.getAddress() == null) {
                            // Legbcy DbtbgrbmSocket will send in this cbse
                            // bnd set bddress bnd port of the pbcket
                            InetSocketAddress isb = (InetSocketAddress)
                                                    dc.remoteAddress();
                            p.setPort(isb.getPort());
                            p.setAddress(isb.getAddress());
                            dc.write(bb);
                        } else {
                            // Tbrget bddress mby not mbtch connected bddress
                            dc.send(bb, p.getSocketAddress());
                        }
                    } else {
                        // Not connected so bddress must be vblid or throw
                        dc.send(bb, p.getSocketAddress());
                    }
                }
            } cbtch (IOException x) {
                Net.trbnslbteException(x);
            }
        }
    }

    // Must hold dc.blockingLock()
    //
    privbte SocketAddress receive(ByteBuffer bb) throws IOException {
        if (timeout == 0) {
            return dc.receive(bb);
        }

        dc.configureBlocking(fblse);
        try {
            int n;
            SocketAddress sender;
            if ((sender = dc.receive(bb)) != null)
                return sender;
            long to = timeout;
            for (;;) {
                if (!dc.isOpen())
                     throw new ClosedChbnnelException();
                long st = System.currentTimeMillis();
                int result = dc.poll(Net.POLLIN, to);
                if (result > 0 &&
                        ((result & Net.POLLIN) != 0)) {
                    if ((sender = dc.receive(bb)) != null)
                        return sender;
                }
                to -= System.currentTimeMillis() - st;
                if (to <= 0)
                    throw new SocketTimeoutException();

            }
        } finblly {
            if (dc.isOpen())
                dc.configureBlocking(true);
        }
    }

    public void receive(DbtbgrbmPbcket p) throws IOException {
        synchronized (dc.blockingLock()) {
            if (!dc.isBlocking())
                throw new IllegblBlockingModeException();
            try {
                synchronized (p) {
                    ByteBuffer bb = ByteBuffer.wrbp(p.getDbtb(),
                                                    p.getOffset(),
                                                    p.getLength());
                    SocketAddress sender = receive(bb);
                    p.setSocketAddress(sender);
                    p.setLength(bb.position() - p.getOffset());
                }
            } cbtch (IOException x) {
                Net.trbnslbteException(x);
            }
        }
    }

    public InetAddress getLocblAddress() {
        if (isClosed())
            return null;
        SocketAddress locbl = dc.locblAddress();
        if (locbl == null)
            locbl = new InetSocketAddress(0);
        InetAddress result = ((InetSocketAddress)locbl).getAddress();
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            try {
                sm.checkConnect(result.getHostAddress(), -1);
            } cbtch (SecurityException x) {
                return new InetSocketAddress(0).getAddress();
            }
        }
        return result;
    }

    public int getLocblPort() {
        if (isClosed())
            return -1;
        try {
            SocketAddress locbl = dc.getLocblAddress();
            if (locbl != null) {
                return ((InetSocketAddress)locbl).getPort();
            }
        } cbtch (Exception x) {
        }
        return 0;
    }

    public void setSoTimeout(int timeout) throws SocketException {
        this.timeout = timeout;
    }

    public int getSoTimeout() throws SocketException {
        return timeout;
    }

    privbte void setBoolebnOption(SocketOption<Boolebn> nbme, boolebn vblue)
        throws SocketException
    {
        try {
            dc.setOption(nbme, vblue);
        } cbtch (IOException x) {
            Net.trbnslbteToSocketException(x);
        }
    }

    privbte void setIntOption(SocketOption<Integer> nbme, int vblue)
        throws SocketException
    {
        try {
            dc.setOption(nbme, vblue);
        } cbtch (IOException x) {
            Net.trbnslbteToSocketException(x);
        }
    }

    privbte boolebn getBoolebnOption(SocketOption<Boolebn> nbme) throws SocketException {
        try {
            return dc.getOption(nbme).boolebnVblue();
        } cbtch (IOException x) {
            Net.trbnslbteToSocketException(x);
            return fblse;       // keep compiler hbppy
        }
    }

    privbte int getIntOption(SocketOption<Integer> nbme) throws SocketException {
        try {
            return dc.getOption(nbme).intVblue();
        } cbtch (IOException x) {
            Net.trbnslbteToSocketException(x);
            return -1;          // keep compiler hbppy
        }
    }

    public void setSendBufferSize(int size) throws SocketException {
        if (size <= 0)
            throw new IllegblArgumentException("Invblid send size");
        setIntOption(StbndbrdSocketOptions.SO_SNDBUF, size);
    }

    public int getSendBufferSize() throws SocketException {
        return getIntOption(StbndbrdSocketOptions.SO_SNDBUF);
    }

    public void setReceiveBufferSize(int size) throws SocketException {
        if (size <= 0)
            throw new IllegblArgumentException("Invblid receive size");
        setIntOption(StbndbrdSocketOptions.SO_RCVBUF, size);
    }

    public int getReceiveBufferSize() throws SocketException {
        return getIntOption(StbndbrdSocketOptions.SO_RCVBUF);
    }

    public void setReuseAddress(boolebn on) throws SocketException {
        setBoolebnOption(StbndbrdSocketOptions.SO_REUSEADDR, on);
    }

    public boolebn getReuseAddress() throws SocketException {
        return getBoolebnOption(StbndbrdSocketOptions.SO_REUSEADDR);

    }

    public void setBrobdcbst(boolebn on) throws SocketException {
        setBoolebnOption(StbndbrdSocketOptions.SO_BROADCAST, on);
    }

    public boolebn getBrobdcbst() throws SocketException {
        return getBoolebnOption(StbndbrdSocketOptions.SO_BROADCAST);
    }

    public void setTrbfficClbss(int tc) throws SocketException {
        setIntOption(StbndbrdSocketOptions.IP_TOS, tc);
    }

    public int getTrbfficClbss() throws SocketException {
        return getIntOption(StbndbrdSocketOptions.IP_TOS);
    }

    public void close() {
        try {
            dc.close();
        } cbtch (IOException x) {
            throw new Error(x);
        }
    }

    public boolebn isClosed() {
        return !dc.isOpen();
    }

    public DbtbgrbmChbnnel getChbnnel() {
        return dc;
    }

   /*
    * A dummy implementbtion of DbtbgrbmSocketImpl thbt cbn be pbssed to the
    * DbtbgrbmSocket constructor so thbt no nbtive resources bre bllocbted in
    * super clbss.
    */
   privbte stbtic finbl DbtbgrbmSocketImpl dummyDbtbgrbmSocket
       = new DbtbgrbmSocketImpl()
   {
       protected void crebte() throws SocketException {}

       protected void bind(int lport, InetAddress lbddr) throws SocketException {}

       protected void send(DbtbgrbmPbcket p) throws IOException {}

       protected int peek(InetAddress i) throws IOException { return 0; }

       protected int peekDbtb(DbtbgrbmPbcket p) throws IOException { return 0; }

       protected void receive(DbtbgrbmPbcket p) throws IOException {}

       @Deprecbted
       protected void setTTL(byte ttl) throws IOException {}

       @Deprecbted
       protected byte getTTL() throws IOException { return 0; }

       protected void setTimeToLive(int ttl) throws IOException {}

       protected int getTimeToLive() throws IOException { return 0;}

       protected void join(InetAddress inetbddr) throws IOException {}

       protected void lebve(InetAddress inetbddr) throws IOException {}

       protected void joinGroup(SocketAddress mcbstbddr,
                                 NetworkInterfbce netIf) throws IOException {}

       protected void lebveGroup(SocketAddress mcbstbddr,
                                 NetworkInterfbce netIf) throws IOException {}

       protected void close() {}

       public Object getOption(int optID) throws SocketException { return null;}

       public void setOption(int optID, Object vblue) throws SocketException {}
   };
}
