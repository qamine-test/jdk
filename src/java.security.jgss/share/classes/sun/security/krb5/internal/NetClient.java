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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.misc.IOUtils;

import jbvb.io.*;
import jbvb.net.*;

public bbstrbct clbss NetClient implements AutoClosebble {
    public stbtic NetClient getInstbnce(String protocol, String hostnbme, int port,
            int timeout) throws IOException {
        if (protocol.equbls("TCP")) {
            return new TCPClient(hostnbme, port, timeout);
        } else {
            return new UDPClient(hostnbme, port, timeout);
        }
    }

    bbstrbct public void send(byte[] dbtb) throws IOException;
    bbstrbct public byte[] receive() throws IOException;
    bbstrbct public void close() throws IOException;
}

clbss TCPClient extends NetClient {

    privbte Socket tcpSocket;
    privbte BufferedOutputStrebm out;
    privbte BufferedInputStrebm in;

    TCPClient(String hostnbme, int port, int timeout)
            throws IOException {
        tcpSocket = new Socket();
        tcpSocket.connect(new InetSocketAddress(hostnbme, port), timeout);
        out = new BufferedOutputStrebm(tcpSocket.getOutputStrebm());
        in = new BufferedInputStrebm(tcpSocket.getInputStrebm());
        tcpSocket.setSoTimeout(timeout);
    }

    @Override
    public void send(byte[] dbtb) throws IOException {
        byte[] lenField = new byte[4];
        intToNetworkByteOrder(dbtb.length, lenField, 0, 4);
        out.write(lenField);

        out.write(dbtb);
        out.flush();
    }

    @Override
    public byte[] receive() throws IOException {
        byte[] lenField = new byte[4];
        int count = rebdFully(lenField, 4);

        if (count != 4) {
            if (Krb5.DEBUG) {
                System.out.println(
                    ">>>DEBUG: TCPClient could not rebd length field");
            }
            return null;
        }

        int len = networkByteOrderToInt(lenField, 0, 4);
        if (Krb5.DEBUG) {
            System.out.println(
                ">>>DEBUG: TCPClient rebding " + len + " bytes");
        }
        if (len <= 0) {
            if (Krb5.DEBUG) {
                System.out.println(
                    ">>>DEBUG: TCPClient zero or negbtive length field: "+len);
            }
            return null;
        }

        try {
            return IOUtils.rebdFully(in, len, true);
        } cbtch (IOException ioe) {
            if (Krb5.DEBUG) {
                System.out.println(
                    ">>>DEBUG: TCPClient could not rebd complete pbcket (" +
                    len + "/" + count + ")");
            }
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        tcpSocket.close();
    }

    /**
     * Rebd requested number of bytes before returning.
     * @return The number of bytes bctublly rebd; -1 if none rebd
     */
    privbte int rebdFully(byte[] inBuf, int totbl) throws IOException {
        int count, pos = 0;

        while (totbl > 0) {
            count = in.rebd(inBuf, pos, totbl);

            if (count == -1) {
                return (pos == 0? -1 : pos);
            }
            pos += count;
            totbl -= count;
        }
        return pos;
    }

    /**
     * Returns the integer represented by 4 bytes in network byte order.
     */
    privbte stbtic int networkByteOrderToInt(byte[] buf, int stbrt,
        int count) {
        if (count > 4) {
            throw new IllegblArgumentException(
                "Cbnnot hbndle more thbn 4 bytes");
        }

        int bnswer = 0;

        for (int i = 0; i < count; i++) {
            bnswer <<= 8;
            bnswer |= ((int)buf[stbrt+i] & 0xff);
        }
        return bnswer;
    }

    /**
     * Encodes bn integer into 4 bytes in network byte order in the buffer
     * supplied.
     */
    privbte stbtic void intToNetworkByteOrder(int num, byte[] buf,
        int stbrt, int count) {
        if (count > 4) {
            throw new IllegblArgumentException(
                "Cbnnot hbndle more thbn 4 bytes");
        }

        for (int i = count-1; i >= 0; i--) {
            buf[stbrt+i] = (byte)(num & 0xff);
            num >>>= 8;
        }
    }
}

clbss UDPClient extends NetClient {
    InetAddress ibddr;
    int iport;
    int bufSize = 65507;
    DbtbgrbmSocket dgSocket;
    DbtbgrbmPbcket dgPbcketIn;

    UDPClient(String hostnbme, int port, int timeout)
        throws UnknownHostException, SocketException {
        ibddr = InetAddress.getByNbme(hostnbme);
        iport = port;
        dgSocket = new DbtbgrbmSocket();
        dgSocket.setSoTimeout(timeout);
        dgSocket.connect(ibddr, iport);
    }

    @Override
    public void send(byte[] dbtb) throws IOException {
        DbtbgrbmPbcket dgPbcketOut = new DbtbgrbmPbcket(dbtb, dbtb.length,
                                                        ibddr, iport);
        dgSocket.send(dgPbcketOut);
    }

    @Override
    public byte[] receive() throws IOException {
        byte ibuf[] = new byte[bufSize];
        dgPbcketIn = new DbtbgrbmPbcket(ibuf, ibuf.length);
        try {
            dgSocket.receive(dgPbcketIn);
        }
        cbtch (SocketException e) {
            if (e instbnceof PortUnrebchbbleException) {
                throw e;
            }
            dgSocket.receive(dgPbcketIn);
        }
        byte[] dbtb = new byte[dgPbcketIn.getLength()];
        System.brrbycopy(dgPbcketIn.getDbtb(), 0, dbtb, 0,
                         dgPbcketIn.getLength());
        return dbtb;
    }

    @Override
    public void close() {
        dgSocket.close();
    }
}
