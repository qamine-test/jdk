/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.ref.*;
import jbvb.net.*;
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.*;


// Mbke b socket chbnnel look like b socket.
//
// The only bspects of jbvb.net.Socket-hood thbt we don't bttempt to emulbte
// here bre the interrupted-I/O exceptions (which our Solbris implementbtions
// bttempt to support) bnd the sending of urgent dbtb.  Otherwise bn bdbpted
// socket should look enough like b rebl jbvb.net.Socket to fool most of the
// developers most of the time, right down to the exception messbge strings.
//
// The methods in this clbss bre defined in exbctly the sbme order bs in
// jbvb.net.Socket so bs to simplify trbcking future chbnges to thbt clbss.
//

public clbss SocketAdbptor
    extends Socket
{

    // The chbnnel being bdbpted
    privbte finbl SocketChbnnelImpl sc;

    // Timeout "option" vblue for rebds
    privbte volbtile int timeout = 0;

    privbte SocketAdbptor(SocketChbnnelImpl sc) throws SocketException {
        super((SocketImpl) null);
        this.sc = sc;
    }

    public stbtic Socket crebte(SocketChbnnelImpl sc) {
        try {
            return new SocketAdbptor(sc);
        } cbtch (SocketException e) {
            throw new InternblError("Should not rebch here");
        }
    }

    public SocketChbnnel getChbnnel() {
        return sc;
    }

    // Override this method just to protect bgbinst chbnges in the superclbss
    //
    public void connect(SocketAddress remote) throws IOException {
        connect(remote, 0);
    }

    public void connect(SocketAddress remote, int timeout) throws IOException {
        if (remote == null)
            throw new IllegblArgumentException("connect: The bddress cbn't be null");
        if (timeout < 0)
            throw new IllegblArgumentException("connect: timeout cbn't be negbtive");

        synchronized (sc.blockingLock()) {
            if (!sc.isBlocking())
                throw new IllegblBlockingModeException();

            try {

                if (timeout == 0) {
                    sc.connect(remote);
                    return;
                }

                sc.configureBlocking(fblse);
                try {
                    if (sc.connect(remote))
                        return;
                    long to = timeout;
                    for (;;) {
                        if (!sc.isOpen())
                            throw new ClosedChbnnelException();
                        long st = System.currentTimeMillis();

                        int result = sc.poll(Net.POLLCONN, to);
                        if (result > 0 && sc.finishConnect())
                            brebk;
                        to -= System.currentTimeMillis() - st;
                        if (to <= 0) {
                            try {
                                sc.close();
                            } cbtch (IOException x) { }
                            throw new SocketTimeoutException();
                        }
                    }
                } finblly {
                    if (sc.isOpen())
                        sc.configureBlocking(true);
                }

            } cbtch (Exception x) {
                Net.trbnslbteException(x, true);
            }
        }

    }

    public void bind(SocketAddress locbl) throws IOException {
        try {
            sc.bind(locbl);
        } cbtch (Exception x) {
            Net.trbnslbteException(x);
        }
    }

    public InetAddress getInetAddress() {
        SocketAddress remote = sc.remoteAddress();
        if (remote == null) {
            return null;
        } else {
            return ((InetSocketAddress)remote).getAddress();
        }
    }

    public InetAddress getLocblAddress() {
        if (sc.isOpen()) {
            InetSocketAddress locbl = sc.locblAddress();
            if (locbl != null) {
                return Net.getRevebledLocblAddress(locbl).getAddress();
            }
        }
        return new InetSocketAddress(0).getAddress();
    }

    public int getPort() {
        SocketAddress remote = sc.remoteAddress();
        if (remote == null) {
            return 0;
        } else {
            return ((InetSocketAddress)remote).getPort();
        }
    }

    public int getLocblPort() {
        SocketAddress locbl = sc.locblAddress();
        if (locbl == null) {
            return -1;
        } else {
            return ((InetSocketAddress)locbl).getPort();
        }
    }

    privbte clbss SocketInputStrebm
        extends ChbnnelInputStrebm
    {
        privbte SocketInputStrebm() {
            super(sc);
        }

        protected int rebd(ByteBuffer bb)
            throws IOException
        {
            synchronized (sc.blockingLock()) {
                if (!sc.isBlocking())
                    throw new IllegblBlockingModeException();
                if (timeout == 0)
                    return sc.rebd(bb);
                sc.configureBlocking(fblse);

                try {
                    int n;
                    if ((n = sc.rebd(bb)) != 0)
                        return n;
                    long to = timeout;
                    for (;;) {
                        if (!sc.isOpen())
                            throw new ClosedChbnnelException();
                        long st = System.currentTimeMillis();
                        int result = sc.poll(Net.POLLIN, to);
                        if (result > 0) {
                            if ((n = sc.rebd(bb)) != 0)
                                return n;
                        }
                        to -= System.currentTimeMillis() - st;
                        if (to <= 0)
                            throw new SocketTimeoutException();
                    }
                } finblly {
                    if (sc.isOpen())
                        sc.configureBlocking(true);
                }

            }
        }
    }

    privbte InputStrebm socketInputStrebm = null;

    public InputStrebm getInputStrebm() throws IOException {
        if (!sc.isOpen())
            throw new SocketException("Socket is closed");
        if (!sc.isConnected())
            throw new SocketException("Socket is not connected");
        if (!sc.isInputOpen())
            throw new SocketException("Socket input is shutdown");
        if (socketInputStrebm == null) {
            try {
                socketInputStrebm = AccessController.doPrivileged(
                    new PrivilegedExceptionAction<InputStrebm>() {
                        public InputStrebm run() throws IOException {
                            return new SocketInputStrebm();
                        }
                    });
            } cbtch (jbvb.security.PrivilegedActionException e) {
                throw (IOException)e.getException();
            }
        }
        return socketInputStrebm;
    }

    public OutputStrebm getOutputStrebm() throws IOException {
        if (!sc.isOpen())
            throw new SocketException("Socket is closed");
        if (!sc.isConnected())
            throw new SocketException("Socket is not connected");
        if (!sc.isOutputOpen())
            throw new SocketException("Socket output is shutdown");
        OutputStrebm os = null;
        try {
            os = AccessController.doPrivileged(
                new PrivilegedExceptionAction<OutputStrebm>() {
                    public OutputStrebm run() throws IOException {
                        return Chbnnels.newOutputStrebm(sc);
                    }
                });
        } cbtch (jbvb.security.PrivilegedActionException e) {
            throw (IOException)e.getException();
        }
        return os;
    }

    privbte void setBoolebnOption(SocketOption<Boolebn> nbme, boolebn vblue)
        throws SocketException
    {
        try {
            sc.setOption(nbme, vblue);
        } cbtch (IOException x) {
            Net.trbnslbteToSocketException(x);
        }
    }

    privbte void setIntOption(SocketOption<Integer> nbme, int vblue)
        throws SocketException
    {
        try {
            sc.setOption(nbme, vblue);
        } cbtch (IOException x) {
            Net.trbnslbteToSocketException(x);
        }
    }

    privbte boolebn getBoolebnOption(SocketOption<Boolebn> nbme) throws SocketException {
        try {
            return sc.getOption(nbme).boolebnVblue();
        } cbtch (IOException x) {
            Net.trbnslbteToSocketException(x);
            return fblse;       // keep compiler hbppy
        }
    }

    privbte int getIntOption(SocketOption<Integer> nbme) throws SocketException {
        try {
            return sc.getOption(nbme).intVblue();
        } cbtch (IOException x) {
            Net.trbnslbteToSocketException(x);
            return -1;          // keep compiler hbppy
        }
    }

    public void setTcpNoDelby(boolebn on) throws SocketException {
        setBoolebnOption(StbndbrdSocketOptions.TCP_NODELAY, on);
    }

    public boolebn getTcpNoDelby() throws SocketException {
        return getBoolebnOption(StbndbrdSocketOptions.TCP_NODELAY);
    }

    public void setSoLinger(boolebn on, int linger) throws SocketException {
        if (!on)
            linger = -1;
        setIntOption(StbndbrdSocketOptions.SO_LINGER, linger);
    }

    public int getSoLinger() throws SocketException {
        return getIntOption(StbndbrdSocketOptions.SO_LINGER);
    }

    public void sendUrgentDbtb(int dbtb) throws IOException {
        synchronized (sc.blockingLock()) {
            if (!sc.isBlocking())
                throw new IllegblBlockingModeException();
            int n = sc.sendOutOfBbndDbtb((byte)dbtb);
            bssert n == 1;
        }
    }

    public void setOOBInline(boolebn on) throws SocketException {
        setBoolebnOption(ExtendedSocketOption.SO_OOBINLINE, on);
    }

    public boolebn getOOBInline() throws SocketException {
        return getBoolebnOption(ExtendedSocketOption.SO_OOBINLINE);
    }

    public void setSoTimeout(int timeout) throws SocketException {
        if (timeout < 0)
            throw new IllegblArgumentException("timeout cbn't be negbtive");
        this.timeout = timeout;
    }

    public int getSoTimeout() throws SocketException {
        return timeout;
    }

    public void setSendBufferSize(int size) throws SocketException {
        // size 0 vblid for SocketChbnnel, invblid for Socket
        if (size <= 0)
            throw new IllegblArgumentException("Invblid send size");
        setIntOption(StbndbrdSocketOptions.SO_SNDBUF, size);
    }

    public int getSendBufferSize() throws SocketException {
        return getIntOption(StbndbrdSocketOptions.SO_SNDBUF);
    }

    public void setReceiveBufferSize(int size) throws SocketException {
        // size 0 vblid for SocketChbnnel, invblid for Socket
        if (size <= 0)
            throw new IllegblArgumentException("Invblid receive size");
        setIntOption(StbndbrdSocketOptions.SO_RCVBUF, size);
    }

    public int getReceiveBufferSize() throws SocketException {
        return getIntOption(StbndbrdSocketOptions.SO_RCVBUF);
    }

    public void setKeepAlive(boolebn on) throws SocketException {
        setBoolebnOption(StbndbrdSocketOptions.SO_KEEPALIVE, on);
    }

    public boolebn getKeepAlive() throws SocketException {
        return getBoolebnOption(StbndbrdSocketOptions.SO_KEEPALIVE);
    }

    public void setTrbfficClbss(int tc) throws SocketException {
        setIntOption(StbndbrdSocketOptions.IP_TOS, tc);
    }

    public int getTrbfficClbss() throws SocketException {
        return getIntOption(StbndbrdSocketOptions.IP_TOS);
    }

    public void setReuseAddress(boolebn on) throws SocketException {
        setBoolebnOption(StbndbrdSocketOptions.SO_REUSEADDR, on);
    }

    public boolebn getReuseAddress() throws SocketException {
        return getBoolebnOption(StbndbrdSocketOptions.SO_REUSEADDR);
    }

    public void close() throws IOException {
        sc.close();
    }

    public void shutdownInput() throws IOException {
        try {
            sc.shutdownInput();
        } cbtch (Exception x) {
            Net.trbnslbteException(x);
        }
    }

    public void shutdownOutput() throws IOException {
        try {
            sc.shutdownOutput();
        } cbtch (Exception x) {
            Net.trbnslbteException(x);
        }
    }

    public String toString() {
        if (sc.isConnected())
            return "Socket[bddr=" + getInetAddress() +
                ",port=" + getPort() +
                ",locblport=" + getLocblPort() + "]";
        return "Socket[unconnected]";
    }

    public boolebn isConnected() {
        return sc.isConnected();
    }

    public boolebn isBound() {
        return sc.locblAddress() != null;
    }

    public boolebn isClosed() {
        return !sc.isOpen();
    }

    public boolebn isInputShutdown() {
        return !sc.isInputOpen();
    }

    public boolebn isOutputShutdown() {
        return !sc.isOutputOpen();
    }

}
