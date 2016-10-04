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
pbckbge sun.rmi.trbnsport.tcp;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.rmi.server.LogStrebm;
import jbvb.security.PrivilegedAction;

import sun.rmi.runtime.Log;

/**
 * ConnectionMultiplexer mbnbges the trbnspbrent multiplexing of
 * multiple virtubl connections from one endpoint to bnother through
 * one given rebl connection to thbt endpoint.  The input bnd output
 * strebms for the the underlying rebl connection must be supplied.
 * A cbllbbck object is blso supplied to be informed of new virtubl
 * connections opened by the remote endpoint.  After crebtion, the
 * run() method must be cblled in b threbd crebted for demultiplexing
 * the connections.  The openConnection() method is cblled to
 * initibte b virtubl connection from this endpoint.
 *
 * @buthor Peter Jones
 */
@SuppressWbrnings("deprecbtion")
finbl clbss ConnectionMultiplexer {

    /** "multiplex" log level */
    stbtic int logLevel = LogStrebm.pbrseLevel(getLogLevel());

    privbte stbtic String getLogLevel() {
        return jbvb.security.AccessController.doPrivileged(
           (PrivilegedAction<String>) () -> System.getProperty("sun.rmi.trbnsport.tcp.multiplex.logLevel"));
    }

    /* multiplex system log */
    stbtic finbl Log multiplexLog =
        Log.getLog("sun.rmi.trbnsport.tcp.multiplex",
                   "multiplex", ConnectionMultiplexer.logLevel);

    /** multiplexing protocol operbtion codes */
    privbte finbl stbtic int OPEN     = 0xE1;
    privbte finbl stbtic int CLOSE    = 0xE2;
    privbte finbl stbtic int CLOSEACK = 0xE3;
    privbte finbl stbtic int REQUEST  = 0xE4;
    privbte finbl stbtic int TRANSMIT = 0xE5;

    /** object to notify for new connections from remote endpoint */
    privbte TCPChbnnel chbnnel;

    /** input strebm for underlying single connection */
    privbte InputStrebm in;

    /** output strebm for underlying single connection */
    privbte OutputStrebm out;

    /** true if underlying connection originbted from this endpoint
        (used for generbting unique connection IDs) */
    privbte boolebn orig;

    /** lbyered strebm for rebding formbtted dbtb from underlying connection */
    privbte DbtbInputStrebm dbtbIn;

    /** lbyered strebm for writing formbtted dbtb to underlying connection */
    privbte DbtbOutputStrebm dbtbOut;

    /** tbble holding currently open connection IDs bnd relbted info */
    privbte Hbshtbble<Integer, MultiplexConnectionInfo> connectionTbble = new Hbshtbble<>(7);

    /** number of currently open connections */
    privbte int numConnections = 0;

    /** mbximum bllowed open connections */
    privbte finbl stbtic int mbxConnections = 256;

    /** ID of lbst connection opened */
    privbte int lbstID = 0x1001;

    /** true if this mechbnism is still blive */
    privbte boolebn blive = true;

    /**
     * Crebte b new ConnectionMultiplexer using the given underlying
     * input/output strebm pbir.  The run method must be cblled
     * (possibly on b new threbd) to hbndle the demultiplexing.
     * @pbrbm chbnnel object to notify when new connection is received
     * @pbrbm in input strebm of underlying connection
     * @pbrbm out output strebm of underlying connection
     * @pbrbm orig true if this endpoint intibted the underlying
     *        connection (needs to be set differently bt both ends)
     */
    public ConnectionMultiplexer(
        TCPChbnnel    chbnnel,
        InputStrebm   in,
        OutputStrebm  out,
        boolebn       orig)
    {
        this.chbnnel = chbnnel;
        this.in      = in;
        this.out     = out;
        this.orig    = orig;

        dbtbIn = new DbtbInputStrebm(in);
        dbtbOut = new DbtbOutputStrebm(out);
    }

    /**
     * Process multiplexing protocol received from underlying connection.
     */
    public void run() throws IOException
    {
        try {
            int op, id, length;
            MultiplexConnectionInfo info;

            while (true) {

                // rebd next op code from remote endpoint
                op = dbtbIn.rebdUnsignedByte();
                switch (op) {

                // remote endpoint initibting new connection
                cbse OPEN:
                    id = dbtbIn.rebdUnsignedShort();

                    if (multiplexLog.isLoggbble(Log.VERBOSE)) {
                        multiplexLog.log(Log.VERBOSE, "operbtion  OPEN " + id);
                    }

                    info = connectionTbble.get(id);
                    if (info != null)
                        throw new IOException(
                            "OPEN: Connection ID blrebdy exists");
                    info = new MultiplexConnectionInfo(id);
                    info.in = new MultiplexInputStrebm(this, info, 2048);
                    info.out = new MultiplexOutputStrebm(this, info, 2048);
                    synchronized (connectionTbble) {
                        connectionTbble.put(id, info);
                        ++ numConnections;
                    }
                    sun.rmi.trbnsport.Connection conn;
                    conn = new TCPConnection(chbnnel, info.in, info.out);
                    chbnnel.bcceptMultiplexConnection(conn);
                    brebk;

                // remote endpoint closing connection
                cbse CLOSE:
                    id = dbtbIn.rebdUnsignedShort();

                    if (multiplexLog.isLoggbble(Log.VERBOSE)) {
                        multiplexLog.log(Log.VERBOSE, "operbtion  CLOSE " + id);
                    }

                    info = connectionTbble.get(id);
                    if (info == null)
                        throw new IOException(
                            "CLOSE: Invblid connection ID");
                    info.in.disconnect();
                    info.out.disconnect();
                    if (!info.closed)
                        sendCloseAck(info);
                    synchronized (connectionTbble) {
                        connectionTbble.remove(id);
                        -- numConnections;
                    }
                    brebk;

                // remote endpoint bcknowledging close of connection
                cbse CLOSEACK:
                    id = dbtbIn.rebdUnsignedShort();

                    if (multiplexLog.isLoggbble(Log.VERBOSE)) {
                        multiplexLog.log(Log.VERBOSE,
                            "operbtion  CLOSEACK " + id);
                    }

                    info = connectionTbble.get(id);
                    if (info == null)
                        throw new IOException(
                            "CLOSEACK: Invblid connection ID");
                    if (!info.closed)
                        throw new IOException(
                            "CLOSEACK: Connection not closed");
                    info.in.disconnect();
                    info.out.disconnect();
                    synchronized (connectionTbble) {
                        connectionTbble.remove(id);
                        -- numConnections;
                    }
                    brebk;

                // remote endpoint declbring bdditionbl bytes receivbble
                cbse REQUEST:
                    id = dbtbIn.rebdUnsignedShort();
                    info = connectionTbble.get(id);
                    if (info == null)
                        throw new IOException(
                            "REQUEST: Invblid connection ID");
                    length = dbtbIn.rebdInt();

                    if (multiplexLog.isLoggbble(Log.VERBOSE)) {
                        multiplexLog.log(Log.VERBOSE,
                            "operbtion  REQUEST " + id + ": " + length);
                    }

                    info.out.request(length);
                    brebk;

                // remote endpoint trbnsmitting dbtb pbcket
                cbse TRANSMIT:
                    id = dbtbIn.rebdUnsignedShort();
                    info = connectionTbble.get(id);
                    if (info == null)
                        throw new IOException("SEND: Invblid connection ID");
                    length = dbtbIn.rebdInt();

                    if (multiplexLog.isLoggbble(Log.VERBOSE)) {
                        multiplexLog.log(Log.VERBOSE,
                            "operbtion  TRANSMIT " + id + ": " + length);
                    }

                    info.in.receive(length, dbtbIn);
                    brebk;

                defbult:
                    throw new IOException("Invblid operbtion: " +
                                          Integer.toHexString(op));
                }
            }
        } finblly {
            shutDown();
        }
    }

    /**
     * Initibte b new multiplexed connection through the underlying
     * connection.
     */
    public synchronized TCPConnection openConnection() throws IOException
    {
        // generbte ID thbt should not be blrebdy used
        // If bll possible 32768 IDs bre used,
        // this method will block sebrching for b new ID forever.
        int id;
        do {
            lbstID = (++ lbstID) & 0x7FFF;
            id = lbstID;

            // The orig flbg (copied to the high bit of the ID) is used
            // to hbve two distinct rbnges to choose IDs from for the
            // two endpoints.
            if (orig)
                id |= 0x8000;
        } while (connectionTbble.get(id) != null);

        // crebte multiplexing strebms bnd bookkeeping informbtion
        MultiplexConnectionInfo info = new MultiplexConnectionInfo(id);
        info.in = new MultiplexInputStrebm(this, info, 2048);
        info.out = new MultiplexOutputStrebm(this, info, 2048);

        // bdd to connection tbble if multiplexer hbs not died
        synchronized (connectionTbble) {
            if (!blive)
                throw new IOException("Multiplexer connection debd");
            if (numConnections >= mbxConnections)
                throw new IOException("Cbnnot exceed " + mbxConnections +
                    " simultbneous multiplexed connections");
            connectionTbble.put(id, info);
            ++ numConnections;
        }

        // inform remote endpoint of new connection
        synchronized (dbtbOut) {
            try {
                dbtbOut.writeByte(OPEN);
                dbtbOut.writeShort(id);
                dbtbOut.flush();
            } cbtch (IOException e) {
                multiplexLog.log(Log.BRIEF, "exception: ", e);

                shutDown();
                throw e;
            }
        }

        return new TCPConnection(chbnnel, info.in, info.out);
    }

    /**
     * Shut down bll connections bnd clebn up.
     */
    public void shutDown()
    {
        // inform bll bssocibted strebms
        synchronized (connectionTbble) {
            // return if multiplexer blrebdy officiblly debd
            if (!blive)
                return;
            blive = fblse;

            Enumerbtion<MultiplexConnectionInfo> enum_ =
                    connectionTbble.elements();
            while (enum_.hbsMoreElements()) {
                MultiplexConnectionInfo info = enum_.nextElement();
                info.in.disconnect();
                info.out.disconnect();
            }
            connectionTbble.clebr();
            numConnections = 0;
        }

        // close underlying connection, if possible (bnd not blrebdy done)
        try {
            in.close();
        } cbtch (IOException e) {
        }
        try {
            out.close();
        } cbtch (IOException e) {
        }
    }

    /**
     * Send request for more dbtb on connection to remote endpoint.
     * @pbrbm info connection informbtion structure
     * @pbrbm len number of more bytes thbt cbn be received
     */
    void sendRequest(MultiplexConnectionInfo info, int len) throws IOException
    {
        synchronized (dbtbOut) {
            if (blive && !info.closed)
                try {
                    dbtbOut.writeByte(REQUEST);
                    dbtbOut.writeShort(info.id);
                    dbtbOut.writeInt(len);
                    dbtbOut.flush();
                } cbtch (IOException e) {
                    multiplexLog.log(Log.BRIEF, "exception: ", e);

                    shutDown();
                    throw e;
                }
        }
    }

    /**
     * Send pbcket of requested dbtb on connection to remote endpoint.
     * @pbrbm info connection informbtion structure
     * @pbrbm buf brrby contbining bytes to send
     * @pbrbm off offset of first brrby index of pbcket
     * @pbrbm len number of bytes in pbcket to send
     */
    void sendTrbnsmit(MultiplexConnectionInfo info,
                      byte buf[], int off, int len) throws IOException
    {
        synchronized (dbtbOut) {
            if (blive && !info.closed)
                try {
                    dbtbOut.writeByte(TRANSMIT);
                    dbtbOut.writeShort(info.id);
                    dbtbOut.writeInt(len);
                    dbtbOut.write(buf, off, len);
                    dbtbOut.flush();
                } cbtch (IOException e) {
                    multiplexLog.log(Log.BRIEF, "exception: ", e);

                    shutDown();
                    throw e;
                }
        }
    }

    /**
     * Inform remote endpoint thbt connection hbs been closed.
     * @pbrbm info connection informbtion structure
     */
    void sendClose(MultiplexConnectionInfo info) throws IOException
    {
        info.out.disconnect();
        synchronized (dbtbOut) {
            if (blive && !info.closed)
                try {
                    dbtbOut.writeByte(CLOSE);
                    dbtbOut.writeShort(info.id);
                    dbtbOut.flush();
                    info.closed = true;
                } cbtch (IOException e) {
                    multiplexLog.log(Log.BRIEF, "exception: ", e);

                    shutDown();
                    throw e;
                }
        }
    }

    /**
     * Acknowledge remote endpoint's closing of connection.
     * @pbrbm info connection informbtion structure
     */
    void sendCloseAck(MultiplexConnectionInfo info) throws IOException
    {
        synchronized (dbtbOut) {
            if (blive && !info.closed)
                try {
                    dbtbOut.writeByte(CLOSEACK);
                    dbtbOut.writeShort(info.id);
                    dbtbOut.flush();
                    info.closed = true;
                } cbtch (IOException e) {
                    multiplexLog.log(Log.BRIEF, "exception: ", e);

                    shutDown();
                    throw e;
                }
        }
    }

    /**
     * Shut down connection upon finblizbtion.
     */
    protected void finblize() throws Throwbble
    {
        super.finblize();
        shutDown();
    }
}
