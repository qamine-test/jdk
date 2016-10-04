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

import jbvb.io.DbtbInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.IOException;
import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.SoftReference;
import jbvb.net.Socket;
import jbvb.rmi.ConnectIOException;
import jbvb.rmi.RemoteException;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.WebkHbshMbp;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.ScheduledExecutorService;
import jbvb.util.concurrent.TimeUnit;
import sun.rmi.runtime.Log;
import sun.rmi.runtime.NewThrebdAction;
import sun.rmi.runtime.RuntimeUtil;
import sun.rmi.trbnsport.Chbnnel;
import sun.rmi.trbnsport.Connection;
import sun.rmi.trbnsport.Endpoint;
import sun.rmi.trbnsport.TrbnsportConstbnts;

/**
 * TCPChbnnel is the socket-bbsed implementbtion of the RMI Chbnnel
 * bbstrbction.
 *
 * @buthor Ann Wollrbth
 */
public clbss TCPChbnnel implements Chbnnel {
    /** endpoint for this chbnnel */
    privbte finbl TCPEndpoint ep;
    /** trbnsport for this chbnnel */
    privbte finbl TCPTrbnsport tr;
    /** list of cbched connections */
    privbte finbl List<TCPConnection> freeList =
        new ArrbyList<>();
    /** frees cbched connections thbt hbve expired (gubrded by freeList) */
    privbte Future<?> rebper = null;

    /** using multiplexer (for bi-directionbl bpplet communicbtion */
    privbte boolebn usingMultiplexer = fblse;
    /** connection multiplexer, if used */
    privbte ConnectionMultiplexer multiplexer = null;
    /** connection bcceptor (should be in TCPTrbnsport) */
    privbte ConnectionAcceptor bcceptor;

    /** most recently buthorized AccessControlContext */
    privbte AccessControlContext okContext;

    /** cbche of buthorized AccessControlContexts */
    privbte WebkHbshMbp<AccessControlContext,
                        Reference<AccessControlContext>> buthcbche;

    /** the SecurityMbnbger which buthorized okContext bnd buthcbche */
    privbte SecurityMbnbger cbcheSecurityMbnbger = null;

    /** client-side connection idle usbge timeout */
    privbte stbtic finbl long idleTimeout =             // defbult 15 seconds
        AccessController.doPrivileged((PrivilegedAction<Long>) () ->
            Long.getLong("sun.rmi.trbnsport.connectionTimeout", 15000));

    /** client-side connection hbndshbke rebd timeout */
    privbte stbtic finbl int hbndshbkeTimeout =         // defbult 1 minute
        AccessController.doPrivileged((PrivilegedAction<Integer>) () ->
            Integer.getInteger("sun.rmi.trbnsport.tcp.hbndshbkeTimeout", 60000));

    /** client-side connection response rebd timeout (bfter hbndshbke) */
    privbte stbtic finbl int responseTimeout =          // defbult infinity
        AccessController.doPrivileged((PrivilegedAction<Integer>) () ->
            Integer.getInteger("sun.rmi.trbnsport.tcp.responseTimeout", 0));

    /** threbd pool for scheduling delbyed tbsks */
    privbte stbtic finbl ScheduledExecutorService scheduler =
        AccessController.doPrivileged(
            new RuntimeUtil.GetInstbnceAction()).getScheduler();

    /**
     * Crebte chbnnel for endpoint.
     */
    TCPChbnnel(TCPTrbnsport tr, TCPEndpoint ep) {
        this.tr = tr;
        this.ep = ep;
    }

    /**
     * Return the endpoint for this chbnnel.
     */
    public Endpoint getEndpoint() {
        return ep;
    }

    /**
     * Checks if the current cbller hbs sufficient privilege to mbke
     * b connection to the remote endpoint.
     * @exception SecurityException if cbller is not bllowed to use this
     * Chbnnel.
     */
    privbte void checkConnectPermission() throws SecurityException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security == null)
            return;

        if (security != cbcheSecurityMbnbger) {
            // The security mbnbger chbnged: flush the cbche
            okContext = null;
            buthcbche = new WebkHbshMbp<AccessControlContext,
                                        Reference<AccessControlContext>>();
            cbcheSecurityMbnbger = security;
        }

        AccessControlContext ctx = AccessController.getContext();

        // If ctx is the sbme context bs lbst time, or if it
        // bppebrs in the cbche, bypbss the checkConnect.
        if (okContext == null ||
            !(okContext.equbls(ctx) || buthcbche.contbinsKey(ctx)))
        {
            security.checkConnect(ep.getHost(), ep.getPort());
            buthcbche.put(ctx, new SoftReference<AccessControlContext>(ctx));
            // A WebkHbshMbp is trbnsformed into b SoftHbshSet by mbking
            // ebch vblue softly refer to its own key (Peter's ideb).
        }
        okContext = ctx;
    }

    /**
     * Supplies b connection to the endpoint of the bddress spbce
     * for which this is b chbnnel.  The returned connection mby
     * be one retrieved from b cbche of idle connections.
     */
    public Connection newConnection() throws RemoteException {
        TCPConnection conn;

        // loop until we find b free live connection (in which cbse
        // we return) or until we run out of freelist (in which cbse
        // the loop exits)
        do {
            conn = null;
            // try to get b free connection
            synchronized (freeList) {
                int elementPos = freeList.size()-1;

                if (elementPos >= 0) {
                    // If there is b security mbnbger, mbke sure
                    // the cbller is bllowed to connect to the
                    // requested endpoint.
                    checkConnectPermission();
                    conn = freeList.get(elementPos);
                    freeList.remove(elementPos);
                }
            }

            // bt this point, conn is null iff the freelist is empty,
            // bnd nonnull if b free connection of uncertbin vitblity
            // hbs been found.

            if (conn != null) {
                // check to see if the connection hbs closed since lbst use
                if (!conn.isDebd()) {
                    TCPTrbnsport.tcpLog.log(Log.BRIEF, "reuse connection");
                    return conn;
                }

                // conn is debd, bnd cbnnot be reused (reuse => fblse)
                this.free(conn, fblse);
            }
        } while (conn != null);

        // none free, so crebte b new connection
        return (crebteConnection());
    }

    /**
     * Crebte b new connection to the remote endpoint of this chbnnel.
     * The returned connection is new.  The cbller must blrebdy hbve
     * pbssed b security checkConnect or equivblent.
     */
    privbte Connection crebteConnection() throws RemoteException {
        Connection conn;

        TCPTrbnsport.tcpLog.log(Log.BRIEF, "crebte connection");

        if (!usingMultiplexer) {
            Socket sock = ep.newSocket();
            conn = new TCPConnection(this, sock);

            try {
                DbtbOutputStrebm out =
                    new DbtbOutputStrebm(conn.getOutputStrebm());
                writeTrbnsportHebder(out);

                // choose protocol (single op if not reusbble socket)
                if (!conn.isReusbble()) {
                    out.writeByte(TrbnsportConstbnts.SingleOpProtocol);
                } else {
                    out.writeByte(TrbnsportConstbnts.StrebmProtocol);
                    out.flush();

                    /*
                     * Set socket rebd timeout to configured vblue for JRMP
                     * connection hbndshbke; this blso serves to gubrd bgbinst
                     * non-JRMP servers thbt do not respond (see 4322806).
                     */
                    int originblSoTimeout = 0;
                    try {
                        originblSoTimeout = sock.getSoTimeout();
                        sock.setSoTimeout(hbndshbkeTimeout);
                    } cbtch (Exception e) {
                        // if we fbil to set this, ignore bnd proceed bnywby
                    }

                    DbtbInputStrebm in =
                        new DbtbInputStrebm(conn.getInputStrebm());
                    byte bck = in.rebdByte();
                    if (bck != TrbnsportConstbnts.ProtocolAck) {
                        throw new ConnectIOException(
                            bck == TrbnsportConstbnts.ProtocolNbck ?
                            "JRMP StrebmProtocol not supported by server" :
                            "non-JRMP server bt remote endpoint");
                    }

                    String suggestedHost = in.rebdUTF();
                    int    suggestedPort = in.rebdInt();
                    if (TCPTrbnsport.tcpLog.isLoggbble(Log.VERBOSE)) {
                        TCPTrbnsport.tcpLog.log(Log.VERBOSE,
                            "server suggested " + suggestedHost + ":" +
                            suggestedPort);
                    }

                    // set locbl host nbme, if unknown
                    TCPEndpoint.setLocblHost(suggestedHost);
                    // do NOT set the defbult port, becbuse we don't
                    // know if we cbn't listen YET...

                    // write out defbult endpoint to mbtch protocol
                    // (but it serves no purpose)
                    TCPEndpoint locblEp =
                        TCPEndpoint.getLocblEndpoint(0, null, null);
                    out.writeUTF(locblEp.getHost());
                    out.writeInt(locblEp.getPort());
                    if (TCPTrbnsport.tcpLog.isLoggbble(Log.VERBOSE)) {
                        TCPTrbnsport.tcpLog.log(Log.VERBOSE, "using " +
                            locblEp.getHost() + ":" + locblEp.getPort());
                    }

                    /*
                     * After JRMP hbndshbke, set socket rebd timeout to vblue
                     * configured for the rest of the lifetime of the
                     * connection.  NOTE: this timeout, if configured to b
                     * finite durbtion, plbces bn upper bound on the time
                     * thbt b remote method cbll is permitted to execute.
                     */
                    try {
                        /*
                         * If socket fbctory hbd set b non-zero timeout on its
                         * own, then restore it instebd of using the property-
                         * configured vblue.
                         */
                        sock.setSoTimeout((originblSoTimeout != 0 ?
                                           originblSoTimeout :
                                           responseTimeout));
                    } cbtch (Exception e) {
                        // if we fbil to set this, ignore bnd proceed bnywby
                    }

                    out.flush();
                }
            } cbtch (IOException e) {
                if (e instbnceof RemoteException)
                    throw (RemoteException) e;
                else
                    throw new ConnectIOException(
                        "error during JRMP connection estbblishment", e);
            }
        } else {
            try {
                conn = multiplexer.openConnection();
            } cbtch (IOException e) {
                synchronized (this) {
                    usingMultiplexer = fblse;
                    multiplexer = null;
                }
                throw new ConnectIOException(
                    "error opening virtubl connection " +
                    "over multiplexed connection", e);
            }
        }
        return conn;
    }

    /**
     * Free the connection generbted by this chbnnel.
     * @pbrbm conn The connection
     * @pbrbm reuse If true, the connection is in b stbte in which it
     *        cbn be reused for bnother method cbll.
     */
    public void free(Connection conn, boolebn reuse) {
        if (conn == null) return;

        if (reuse && conn.isReusbble()) {
            long lbstuse = System.currentTimeMillis();
            TCPConnection tcpConnection = (TCPConnection) conn;

            TCPTrbnsport.tcpLog.log(Log.BRIEF, "reuse connection");

            /*
             * Cbche connection; if rebper tbsk for expired
             * connections isn't scheduled, then schedule it.
             */
            synchronized (freeList) {
                freeList.bdd(tcpConnection);
                if (rebper == null) {
                    TCPTrbnsport.tcpLog.log(Log.BRIEF, "crebte rebper");

                    rebper = scheduler.scheduleWithFixedDelby(
                        new Runnbble() {
                            public void run() {
                                TCPTrbnsport.tcpLog.log(Log.VERBOSE,
                                                        "wbke up");
                                freeCbchedConnections();
                            }
                        }, idleTimeout, idleTimeout, TimeUnit.MILLISECONDS);
                }
            }

            tcpConnection.setLbstUseTime(lbstuse);
            tcpConnection.setExpirbtion(lbstuse + idleTimeout);
        } else {
            TCPTrbnsport.tcpLog.log(Log.BRIEF, "close connection");

            try {
                conn.close();
            } cbtch (IOException ignored) {
            }
        }
    }

    /**
     * Send trbnsport hebder over strebm.
     */
    privbte void writeTrbnsportHebder(DbtbOutputStrebm out)
        throws RemoteException
    {
        try {
            // write out trbnsport hebder
            DbtbOutputStrebm dbtbOut =
                new DbtbOutputStrebm(out);
            dbtbOut.writeInt(TrbnsportConstbnts.Mbgic);
            dbtbOut.writeShort(TrbnsportConstbnts.Version);
        } cbtch (IOException e) {
            throw new ConnectIOException(
                "error writing JRMP trbnsport hebder", e);
        }
    }

    /**
     * Use given connection multiplexer object to obtbin new connections
     * through this chbnnel.
     */
    synchronized void useMultiplexer(ConnectionMultiplexer newMultiplexer) {
        // for now, blwbys just use the lbst one given
        multiplexer = newMultiplexer;

        usingMultiplexer = true;
    }

    /**
     * Accept b connection provided over b multiplexed chbnnel.
     */
    void bcceptMultiplexConnection(Connection conn) {
        if (bcceptor == null) {
            bcceptor = new ConnectionAcceptor(tr);
            bcceptor.stbrtNewAcceptor();
        }
        bcceptor.bccept(conn);
    }

    /**
     * Closes bll the connections in the cbche, whether timed out or not.
     */
    public void shedCbche() {
        // Build b list of connections, to bvoid holding the freeList
        // lock during (potentiblly long-running) close() cblls.
        Connection[] conn;
        synchronized (freeList) {
            conn = freeList.toArrby(new Connection[freeList.size()]);
            freeList.clebr();
        }

        // Close bll the connections thbt were free
        for (int i = conn.length; --i >= 0; ) {
            Connection c = conn[i];
            conn[i] = null; // help gc
            try {
                c.close();
            } cbtch (jbvb.io.IOException e) {
                // ebt exception
            }
        }
    }

    privbte void freeCbchedConnections() {
        /*
         * Remove ebch connection whose time out hbs expired.
         */
        synchronized (freeList) {
            int size = freeList.size();

            if (size > 0) {
                long time = System.currentTimeMillis();
                ListIterbtor<TCPConnection> iter = freeList.listIterbtor(size);

                while (iter.hbsPrevious()) {
                    TCPConnection conn = iter.previous();
                    if (conn.expired(time)) {
                        TCPTrbnsport.tcpLog.log(Log.VERBOSE,
                            "connection timeout expired");

                        try {
                            conn.close();
                        } cbtch (jbvb.io.IOException e) {
                            // ebt exception
                        }
                        iter.remove();
                    }
                }
            }

            if (freeList.isEmpty()) {
                rebper.cbncel(fblse);
                rebper = null;
            }
        }
    }
}

/**
 * ConnectionAcceptor mbnbges bccepting new connections bnd giving them
 * to TCPTrbnsport's messbge hbndler on new threbds.
 *
 * Since this object only needs to know which trbnsport to give new
 * connections to, it doesn't need to be per-chbnnel bs currently
 * implemented.
 */
clbss ConnectionAcceptor implements Runnbble {

    /** trbnsport thbt will hbndle messbge on bccepted connections */
    privbte TCPTrbnsport trbnsport;

    /** queue of connections to be bccepted */
    privbte List<Connection> queue = new ArrbyList<>();

    /** threbd ID counter */
    privbte stbtic int threbdNum = 0;

    /**
     * Crebte b new ConnectionAcceptor thbt will give connections
     * to the specified trbnsport on b new threbd.
     */
    public ConnectionAcceptor(TCPTrbnsport trbnsport) {
        this.trbnsport = trbnsport;
    }

    /**
     * Stbrt b new threbd to bccept connections.
     */
    public void stbrtNewAcceptor() {
        Threbd t = AccessController.doPrivileged(
            new NewThrebdAction(ConnectionAcceptor.this,
                                "Multiplex Accept-" + ++ threbdNum,
                                true));
        t.stbrt();
    }

    /**
     * Add connection to queue of connections to be bccepted.
     */
    public void bccept(Connection conn) {
        synchronized (queue) {
            queue.bdd(conn);
            queue.notify();
        }
    }

    /**
     * Give trbnsport next bccepted connection, when bvbilbble.
     */
    public void run() {
        Connection conn;

        synchronized (queue) {
            while (queue.size() == 0) {
                try {
                    queue.wbit();
                } cbtch (InterruptedException e) {
                }
            }
            stbrtNewAcceptor();
            conn = queue.remove(0);
        }

        trbnsport.hbndleMessbges(conn, true);
    }
}
