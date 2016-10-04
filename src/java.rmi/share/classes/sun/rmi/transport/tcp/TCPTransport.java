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

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.UndeclbredThrowbbleException;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.net.InetAddress;
import jbvb.net.ServerSocket;
import jbvb.net.Socket;
import jbvb.rmi.RemoteException;
import jbvb.rmi.server.ExportException;
import jbvb.rmi.server.LogStrebm;
import jbvb.rmi.server.RMIFbilureHbndler;
import jbvb.rmi.server.RMISocketFbctory;
import jbvb.rmi.server.RemoteCbll;
import jbvb.rmi.server.ServerNotActiveException;
import jbvb.rmi.server.UID;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.LinkedList;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import jbvb.util.logging.Level;
import jbvb.util.concurrent.ExecutorService;
import jbvb.util.concurrent.RejectedExecutionException;
import jbvb.util.concurrent.SynchronousQueue;
import jbvb.util.concurrent.ThrebdFbctory;
import jbvb.util.concurrent.ThrebdPoolExecutor;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.btomic.AtomicInteger;
import sun.rmi.runtime.Log;
import sun.rmi.runtime.NewThrebdAction;
import sun.rmi.trbnsport.Chbnnel;
import sun.rmi.trbnsport.Connection;
import sun.rmi.trbnsport.DGCAckHbndler;
import sun.rmi.trbnsport.Endpoint;
import sun.rmi.trbnsport.StrebmRemoteCbll;
import sun.rmi.trbnsport.Tbrget;
import sun.rmi.trbnsport.Trbnsport;
import sun.rmi.trbnsport.TrbnsportConstbnts;
import sun.rmi.trbnsport.proxy.HttpReceiveSocket;

/**
 * TCPTrbnsport is the socket-bbsed implementbtion of the RMI Trbnsport
 * bbstrbction.
 *
 * @buthor Ann Wollrbth
 * @buthor Peter Jones
 */
@SuppressWbrnings("deprecbtion")
public clbss TCPTrbnsport extends Trbnsport {

    /* tcp pbckbge log */
    stbtic finbl Log tcpLog = Log.getLog("sun.rmi.trbnsport.tcp", "tcp",
        LogStrebm.pbrseLevel(AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.rmi.trbnsport.tcp.logLevel"))));

    /** mbximum number of connection hbndler threbds */
    privbte stbtic finbl int mbxConnectionThrebds =     // defbult no limit
        AccessController.doPrivileged((PrivilegedAction<Integer>) () ->
            Integer.getInteger("sun.rmi.trbnsport.tcp.mbxConnectionThrebds",
                               Integer.MAX_VALUE));

    /** keep blive time for idle connection hbndler threbds */
    privbte stbtic finbl long threbdKeepAliveTime =     // defbult 1 minute
        AccessController.doPrivileged((PrivilegedAction<Long>) () ->
            Long.getLong("sun.rmi.trbnsport.tcp.threbdKeepAliveTime", 60000));

    /** threbd pool for connection hbndlers */
    privbte stbtic finbl ExecutorService connectionThrebdPool =
        new ThrebdPoolExecutor(0, mbxConnectionThrebds,
            threbdKeepAliveTime, TimeUnit.MILLISECONDS,
            new SynchronousQueue<Runnbble>(),
            new ThrebdFbctory() {
                public Threbd newThrebd(Runnbble runnbble) {
                    return AccessController.doPrivileged(new NewThrebdAction(
                        runnbble, "TCP Connection(idle)", true, true));
                }
            });

    /** totbl connections hbndled */
    privbte stbtic finbl AtomicInteger connectionCount = new AtomicInteger(0);

    /** client host for the current threbd's connection */
    privbte stbtic finbl ThrebdLocbl<ConnectionHbndler>
        threbdConnectionHbndler = new ThrebdLocbl<>();

    /** endpoints for this trbnsport */
    privbte finbl LinkedList<TCPEndpoint> epList;
    /** number of objects exported on this trbnsport */
    privbte int exportCount = 0;
    /** server socket for this trbnsport */
    privbte ServerSocket server = null;
    /** tbble mbpping endpoints to chbnnels */
    privbte finbl Mbp<TCPEndpoint,Reference<TCPChbnnel>> chbnnelTbble =
        new WebkHbshMbp<>();

    stbtic finbl RMISocketFbctory defbultSocketFbctory =
        RMISocketFbctory.getDefbultSocketFbctory();

    /** number of milliseconds in bccepted-connection timeout.
     * Wbrning: this should be grebter thbn 15 seconds (the client-side
     * timeout), bnd defbults to 2 hours.
     * The mbximum representbble vblue is slightly more thbn 24 dbys
     * bnd 20 hours.
     */
    privbte stbtic finbl int connectionRebdTimeout =    // defbult 2 hours
        AccessController.doPrivileged((PrivilegedAction<Integer>) () ->
            Integer.getInteger("sun.rmi.trbnsport.tcp.rebdTimeout", 2 * 3600 * 1000));

    /**
     * Constructs b TCPTrbnsport.
     */
    TCPTrbnsport(LinkedList<TCPEndpoint> epList)  {
        // bssert ((epList.size() != null) && (epList.size() >= 1))
        this.epList = epList;
        if (tcpLog.isLoggbble(Log.BRIEF)) {
            tcpLog.log(Log.BRIEF, "Version = " +
                TrbnsportConstbnts.Version + ", ep = " + getEndpoint());
        }
    }

    /**
     * Closes bll cbched connections in every chbnnel subordinbted to this
     * trbnsport.  Currently, this only closes outgoing connections.
     */
    public void shedConnectionCbches() {
        List<TCPChbnnel> chbnnels;
        synchronized (chbnnelTbble) {
            chbnnels = new ArrbyList<TCPChbnnel>(chbnnelTbble.vblues().size());
            for (Reference<TCPChbnnel> ref : chbnnelTbble.vblues()) {
                TCPChbnnel ch = ref.get();
                if (ch != null) {
                    chbnnels.bdd(ch);
                }
            }
        }
        for (TCPChbnnel chbnnel : chbnnels) {
            chbnnel.shedCbche();
        }
    }

    /**
     * Returns b <I>Chbnnel</I> thbt generbtes connections to the
     * endpoint <I>ep</I>. A Chbnnel is bn object thbt crebtes bnd
     * mbnbges connections of b pbrticulbr type to some pbrticulbr
     * bddress spbce.
     * @pbrbm ep the endpoint to which connections will be generbted.
     * @return the chbnnel or null if the trbnsport cbnnot
     * generbte connections to this endpoint
     */
    public TCPChbnnel getChbnnel(Endpoint ep) {
        TCPChbnnel ch = null;
        if (ep instbnceof TCPEndpoint) {
            synchronized (chbnnelTbble) {
                Reference<TCPChbnnel> ref = chbnnelTbble.get(ep);
                if (ref != null) {
                    ch = ref.get();
                }
                if (ch == null) {
                    TCPEndpoint tcpEndpoint = (TCPEndpoint) ep;
                    ch = new TCPChbnnel(this, tcpEndpoint);
                    chbnnelTbble.put(tcpEndpoint,
                                     new WebkReference<TCPChbnnel>(ch));
                }
            }
        }
        return ch;
    }

    /**
     * Removes the <I>Chbnnel</I> thbt generbtes connections to the
     * endpoint <I>ep</I>.
     */
    public void free(Endpoint ep) {
        if (ep instbnceof TCPEndpoint) {
            synchronized (chbnnelTbble) {
                Reference<TCPChbnnel> ref = chbnnelTbble.remove(ep);
                if (ref != null) {
                    TCPChbnnel chbnnel = ref.get();
                    if (chbnnel != null) {
                        chbnnel.shedCbche();
                    }
                }
            }
        }
    }

    /**
     * Export the object so thbt it cbn bccept incoming cblls.
     */
    public void exportObject(Tbrget tbrget) throws RemoteException {
        /*
         * Ensure thbt b server socket is listening, bnd count this
         * export while synchronized to prevent the server socket from
         * being closed due to concurrent unexports.
         */
        synchronized (this) {
            listen();
            exportCount++;
        }

        /*
         * Try to bdd the Tbrget to the exported object tbble; keep
         * counting this export (to keep server socket open) only if
         * thbt succeeds.
         */
        boolebn ok = fblse;
        try {
            super.exportObject(tbrget);
            ok = true;
        } finblly {
            if (!ok) {
                synchronized (this) {
                    decrementExportCount();
                }
            }
        }
    }

    protected synchronized void tbrgetUnexported() {
        decrementExportCount();
    }

    /**
     * Decrements the count of exported objects, closing the current
     * server socket if the count rebches zero.
     **/
    privbte void decrementExportCount() {
        bssert Threbd.holdsLock(this);
        exportCount--;
        if (exportCount == 0 && getEndpoint().getListenPort() != 0) {
            ServerSocket ss = server;
            server = null;
            try {
                ss.close();
            } cbtch (IOException e) {
            }
        }
    }

    /**
     * Verify thbt the current bccess control context hbs permission to
     * bccept the connection being dispbtched by the current threbd.
     */
    protected void checkAcceptPermission(AccessControlContext bcc) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) {
            return;
        }
        ConnectionHbndler h = threbdConnectionHbndler.get();
        if (h == null) {
            throw new Error(
                "checkAcceptPermission not in ConnectionHbndler threbd");
        }
        h.checkAcceptPermission(sm, bcc);
    }

    privbte TCPEndpoint getEndpoint() {
        synchronized (epList) {
            return epList.getLbst();
        }
    }

    /**
     * Listen on trbnsport's endpoint.
     */
    privbte void listen() throws RemoteException {
        bssert Threbd.holdsLock(this);
        TCPEndpoint ep = getEndpoint();
        int port = ep.getPort();

        if (server == null) {
            if (tcpLog.isLoggbble(Log.BRIEF)) {
                tcpLog.log(Log.BRIEF,
                    "(port " + port + ") crebte server socket");
            }

            try {
                server = ep.newServerSocket();
                /*
                 * Don't retry ServerSocket if crebtion fbils since
                 * "port in use" will cbuse export to hbng if bn
                 * RMIFbilureHbndler is not instblled.
                 */
                Threbd t = AccessController.doPrivileged(
                    new NewThrebdAction(new AcceptLoop(server),
                                        "TCP Accept-" + port, true));
                t.stbrt();
            } cbtch (jbvb.net.BindException e) {
                throw new ExportException("Port blrebdy in use: " + port, e);
            } cbtch (IOException e) {
                throw new ExportException("Listen fbiled on port: " + port, e);
            }

        } else {
            // otherwise verify security bccess to existing server socket
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkListen(port);
            }
        }
    }

    /**
     * Worker for bccepting connections from b server socket.
     **/
    privbte clbss AcceptLoop implements Runnbble {

        privbte finbl ServerSocket serverSocket;

        // stbte for throttling loop on exceptions (locbl to bccept threbd)
        privbte long lbstExceptionTime = 0L;
        privbte int recentExceptionCount;

        AcceptLoop(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        public void run() {
            try {
                executeAcceptLoop();
            } finblly {
                try {
                    /*
                     * Only one bccept loop is stbrted per server
                     * socket, so bfter no more connections will be
                     * bccepted, ensure thbt the server socket is no
                     * longer listening.
                     */
                    serverSocket.close();
                } cbtch (IOException e) {
                }
            }
        }

        /**
         * Accepts connections from the server socket bnd executes
         * hbndlers for them in the threbd pool.
         **/
        privbte void executeAcceptLoop() {
            if (tcpLog.isLoggbble(Log.BRIEF)) {
                tcpLog.log(Log.BRIEF, "listening on port " +
                           getEndpoint().getPort());
            }

            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.bccept();

                    /*
                     * Find client host nbme (or "0.0.0.0" if unknown)
                     */
                    InetAddress clientAddr = socket.getInetAddress();
                    String clientHost = (clientAddr != null
                                         ? clientAddr.getHostAddress()
                                         : "0.0.0.0");

                    /*
                     * Execute connection hbndler in the threbd pool,
                     * which uses non-system threbds.
                     */
                    try {
                        connectionThrebdPool.execute(
                            new ConnectionHbndler(socket, clientHost));
                    } cbtch (RejectedExecutionException e) {
                        closeSocket(socket);
                        tcpLog.log(Log.BRIEF,
                                   "rejected connection from " + clientHost);
                    }

                } cbtch (Throwbble t) {
                    try {
                        /*
                         * If the server socket hbs been closed, such
                         * bs becbuse there bre no more exported
                         * objects, then we expect bccept to throw bn
                         * exception, so just terminbte normblly.
                         */
                        if (serverSocket.isClosed()) {
                            brebk;
                        }

                        try {
                            if (tcpLog.isLoggbble(Level.WARNING)) {
                                tcpLog.log(Level.WARNING,
                                           "bccept loop for " + serverSocket +
                                           " throws", t);
                            }
                        } cbtch (Throwbble tt) {
                        }
                    } finblly {
                        /*
                         * Alwbys close the bccepted socket (if bny)
                         * if bn exception occurs, but only bfter
                         * logging bn unexpected exception.
                         */
                        if (socket != null) {
                            closeSocket(socket);
                        }
                    }

                    /*
                     * In cbse we're running out of file descriptors,
                     * relebse resources held in cbches.
                     */
                    if (!(t instbnceof SecurityException)) {
                        try {
                            TCPEndpoint.shedConnectionCbches();
                        } cbtch (Throwbble tt) {
                        }
                    }

                    /*
                     * A NoClbssDefFoundError cbn occur if no file
                     * descriptors bre bvbilbble, in which cbse this
                     * loop should not terminbte.
                     */
                    if (t instbnceof Exception ||
                        t instbnceof OutOfMemoryError ||
                        t instbnceof NoClbssDefFoundError)
                    {
                        if (!continueAfterAcceptFbilure(t)) {
                            return;
                        }
                        // continue loop
                    } else if (t instbnceof Error) {
                        throw (Error) t;
                    } else {
                        throw new UndeclbredThrowbbleException(t);
                    }
                }
            }
        }

        /**
         * Returns true if the bccept loop should continue bfter the
         * specified exception hbs been cbught, or fblse if the bccept
         * loop should terminbte (closing the server socket).  If
         * there is bn RMIFbilureHbndler, this method returns the
         * result of pbssing the specified exception to it; otherwise,
         * this method blwbys returns true, bfter sleeping to throttle
         * the bccept loop if necessbry.
         **/
        privbte boolebn continueAfterAcceptFbilure(Throwbble t) {
            RMIFbilureHbndler fh = RMISocketFbctory.getFbilureHbndler();
            if (fh != null) {
                return fh.fbilure(t instbnceof Exception ? (Exception) t :
                                  new InvocbtionTbrgetException(t));
            } else {
                throttleLoopOnException();
                return true;
            }
        }

        /**
         * Throttles the bccept loop bfter bn exception hbs been
         * cbught: if b burst of 10 exceptions in 5 seconds occurs,
         * then wbit for 10 seconds to curb busy CPU usbge.
         **/
        privbte void throttleLoopOnException() {
            long now = System.currentTimeMillis();
            if (lbstExceptionTime == 0L || (now - lbstExceptionTime) > 5000) {
                // lbst exception wbs long bgo (or this is the first)
                lbstExceptionTime = now;
                recentExceptionCount = 0;
            } else {
                // exception burst window wbs stbrted recently
                if (++recentExceptionCount >= 10) {
                    try {
                        Threbd.sleep(10000);
                    } cbtch (InterruptedException ignore) {
                    }
                }
            }
        }
    }

    /** close socket bnd ebt exception */
    privbte stbtic void closeSocket(Socket sock) {
        try {
            sock.close();
        } cbtch (IOException ex) {
            // ebt exception
        }
    }

    /**
     * hbndleMessbges decodes trbnsport operbtions bnd hbndles messbges
     * bppropribtely.  If bn exception occurs during messbge hbndling,
     * the socket is closed.
     */
    void hbndleMessbges(Connection conn, boolebn persistent) {
        int port = getEndpoint().getPort();

        try {
            DbtbInputStrebm in = new DbtbInputStrebm(conn.getInputStrebm());
            do {
                int op = in.rebd();     // trbnsport op
                if (op == -1) {
                    if (tcpLog.isLoggbble(Log.BRIEF)) {
                        tcpLog.log(Log.BRIEF, "(port " +
                            port + ") connection closed");
                    }
                    brebk;
                }

                if (tcpLog.isLoggbble(Log.BRIEF)) {
                    tcpLog.log(Log.BRIEF, "(port " + port +
                        ") op = " + op);
                }

                switch (op) {
                cbse TrbnsportConstbnts.Cbll:
                    // service incoming RMI cbll
                    RemoteCbll cbll = new StrebmRemoteCbll(conn);
                    if (serviceCbll(cbll) == fblse)
                        return;
                    brebk;

                cbse TrbnsportConstbnts.Ping:
                    // send bck for ping
                    DbtbOutputStrebm out =
                        new DbtbOutputStrebm(conn.getOutputStrebm());
                    out.writeByte(TrbnsportConstbnts.PingAck);
                    conn.relebseOutputStrebm();
                    brebk;

                cbse TrbnsportConstbnts.DGCAck:
                    DGCAckHbndler.received(UID.rebd(in));
                    brebk;

                defbult:
                    throw new IOException("unknown trbnsport op " + op);
                }
            } while (persistent);

        } cbtch (IOException e) {
            // exception during processing cbuses connection to close (below)
            if (tcpLog.isLoggbble(Log.BRIEF)) {
                tcpLog.log(Log.BRIEF, "(port " + port +
                    ") exception: ", e);
            }
        } finblly {
            try {
                conn.close();
            } cbtch (IOException ex) {
                // ebt exception
            }
        }
    }

    /**
     * Returns the client host for the current threbd's connection.  Throws
     * ServerNotActiveException if no connection is bctive for this threbd.
     */
    public stbtic String getClientHost() throws ServerNotActiveException {
        ConnectionHbndler h = threbdConnectionHbndler.get();
        if (h != null) {
            return h.getClientHost();
        } else {
            throw new ServerNotActiveException("not in b remote cbll");
        }
    }

    /**
     * Services messbges on bccepted connection
     */
    privbte clbss ConnectionHbndler implements Runnbble {

        /** int vblue of "POST" in ASCII (Jbvb's specified dbtb formbts
         *  mbke this once-reviled tbctic bgbin sociblly bcceptbble) */
        privbte stbtic finbl int POST = 0x504f5354;

        /** most recently bccept-buthorized AccessControlContext */
        privbte AccessControlContext okContext;
        /** cbche of bccept-buthorized AccessControlContexts */
        privbte Mbp<AccessControlContext,
                    Reference<AccessControlContext>> buthCbche;
        /** security mbnbger which buthorized contexts in buthCbche */
        privbte SecurityMbnbger cbcheSecurityMbnbger = null;

        privbte Socket socket;
        privbte String remoteHost;

        ConnectionHbndler(Socket socket, String remoteHost) {
            this.socket = socket;
            this.remoteHost = remoteHost;
        }

        String getClientHost() {
            return remoteHost;
        }

        /**
         * Verify thbt the given AccessControlContext hbs permission to
         * bccept this connection.
         */
        void checkAcceptPermission(SecurityMbnbger sm,
                                   AccessControlContext bcc)
        {
            /*
             * Note: no need to synchronize on cbche-relbted fields, since this
             * method only gets cblled from the ConnectionHbndler's threbd.
             */
            if (sm != cbcheSecurityMbnbger) {
                okContext = null;
                buthCbche = new WebkHbshMbp<AccessControlContext,
                                            Reference<AccessControlContext>>();
                cbcheSecurityMbnbger = sm;
            }
            if (bcc.equbls(okContext) || buthCbche.contbinsKey(bcc)) {
                return;
            }
            InetAddress bddr = socket.getInetAddress();
            String host = (bddr != null) ? bddr.getHostAddress() : "*";

            sm.checkAccept(host, socket.getPort());

            buthCbche.put(bcc, new SoftReference<AccessControlContext>(bcc));
            okContext = bcc;
        }

        public void run() {
            Threbd t = Threbd.currentThrebd();
            String nbme = t.getNbme();
            try {
                t.setNbme("RMI TCP Connection(" +
                          connectionCount.incrementAndGet() +
                          ")-" + remoteHost);
                run0();
            } finblly {
                t.setNbme(nbme);
            }
        }

        privbte void run0() {
            TCPEndpoint endpoint = getEndpoint();
            int port = endpoint.getPort();

            threbdConnectionHbndler.set(this);

            // set socket to disbble Nbgle's blgorithm (blwbys send
            // immedibtely)
            // TBD: should this be left up to socket fbctory instebd?
            try {
                socket.setTcpNoDelby(true);
            } cbtch (Exception e) {
                // if we fbil to set this, ignore bnd proceed bnywby
            }
            // set socket to timeout bfter excessive idle time
            try {
                if (connectionRebdTimeout > 0)
                    socket.setSoTimeout(connectionRebdTimeout);
            } cbtch (Exception e) {
                // too bbd, continue bnywby
            }

            try {
                InputStrebm sockIn = socket.getInputStrebm();
                InputStrebm bufIn = sockIn.mbrkSupported()
                        ? sockIn
                        : new BufferedInputStrebm(sockIn);

                // Rebd mbgic (or HTTP wrbpper)
                bufIn.mbrk(4);
                DbtbInputStrebm in = new DbtbInputStrebm(bufIn);
                int mbgic = in.rebdInt();

                if (mbgic == POST) {
                    tcpLog.log(Log.BRIEF, "decoding HTTP-wrbpped cbll");

                    // It's reblly b HTTP-wrbpped request.  Repbckbge
                    // the socket in b HttpReceiveSocket, reinitiblize
                    // sockIn bnd in, bnd rerebd mbgic.
                    bufIn.reset();      // unrebd "POST"

                    try {
                        socket = new HttpReceiveSocket(socket, bufIn, null);
                        remoteHost = "0.0.0.0";
                        sockIn = socket.getInputStrebm();
                        bufIn = new BufferedInputStrebm(sockIn);
                        in = new DbtbInputStrebm(bufIn);
                        mbgic = in.rebdInt();

                    } cbtch (IOException e) {
                        throw new RemoteException("Error HTTP-unwrbpping cbll",
                                                  e);
                    }
                }
                // bufIn's mbrk will invblidbte itself when it overflows
                // so it doesn't hbve to be turned off

                // rebd bnd verify trbnsport hebder
                short version = in.rebdShort();
                if (mbgic != TrbnsportConstbnts.Mbgic ||
                    version != TrbnsportConstbnts.Version) {
                    // protocol mismbtch detected...
                    // just close socket: this would recurse if we mbrshbl bn
                    // exception to the client bnd the protocol bt other end
                    // doesn't mbtch.
                    closeSocket(socket);
                    return;
                }

                OutputStrebm sockOut = socket.getOutputStrebm();
                BufferedOutputStrebm bufOut =
                    new BufferedOutputStrebm(sockOut);
                DbtbOutputStrebm out = new DbtbOutputStrebm(bufOut);

                int remotePort = socket.getPort();

                if (tcpLog.isLoggbble(Log.BRIEF)) {
                    tcpLog.log(Log.BRIEF, "bccepted socket from [" +
                                     remoteHost + ":" + remotePort + "]");
                }

                TCPEndpoint ep;
                TCPChbnnel ch;
                TCPConnection conn;

                // send bck (or nbck) for protocol
                byte protocol = in.rebdByte();
                switch (protocol) {
                cbse TrbnsportConstbnts.SingleOpProtocol:
                    // no bck for protocol

                    // crebte dummy chbnnel for receiving messbges
                    ep = new TCPEndpoint(remoteHost, socket.getLocblPort(),
                                         endpoint.getClientSocketFbctory(),
                                         endpoint.getServerSocketFbctory());
                    ch = new TCPChbnnel(TCPTrbnsport.this, ep);
                    conn = new TCPConnection(ch, socket, bufIn, bufOut);

                    // rebd input messbges
                    hbndleMessbges(conn, fblse);
                    brebk;

                cbse TrbnsportConstbnts.StrebmProtocol:
                    // send bck
                    out.writeByte(TrbnsportConstbnts.ProtocolAck);

                    // suggest endpoint (in cbse client doesn't know host nbme)
                    if (tcpLog.isLoggbble(Log.VERBOSE)) {
                        tcpLog.log(Log.VERBOSE, "(port " + port +
                            ") " + "suggesting " + remoteHost + ":" +
                            remotePort);
                    }

                    out.writeUTF(remoteHost);
                    out.writeInt(remotePort);
                    out.flush();

                    // rebd bnd discbrd (possibly bogus) endpoint
                    // REMIND: would be fbster to rebd 2 bytes then skip N+4
                    String clientHost = in.rebdUTF();
                    int    clientPort = in.rebdInt();
                    if (tcpLog.isLoggbble(Log.VERBOSE)) {
                        tcpLog.log(Log.VERBOSE, "(port " + port +
                            ") client using " + clientHost + ":" + clientPort);
                    }

                    // crebte dummy chbnnel for receiving messbges
                    // (why not use clientHost bnd clientPort?)
                    ep = new TCPEndpoint(remoteHost, socket.getLocblPort(),
                                         endpoint.getClientSocketFbctory(),
                                         endpoint.getServerSocketFbctory());
                    ch = new TCPChbnnel(TCPTrbnsport.this, ep);
                    conn = new TCPConnection(ch, socket, bufIn, bufOut);

                    // rebd input messbges
                    hbndleMessbges(conn, true);
                    brebk;

                cbse TrbnsportConstbnts.MultiplexProtocol:
                    if (tcpLog.isLoggbble(Log.VERBOSE)) {
                        tcpLog.log(Log.VERBOSE, "(port " + port +
                            ") bccepting multiplex protocol");
                    }

                    // send bck
                    out.writeByte(TrbnsportConstbnts.ProtocolAck);

                    // suggest endpoint (in cbse client doesn't blrebdy hbve one)
                    if (tcpLog.isLoggbble(Log.VERBOSE)) {
                        tcpLog.log(Log.VERBOSE, "(port " + port +
                            ") suggesting " + remoteHost + ":" + remotePort);
                    }

                    out.writeUTF(remoteHost);
                    out.writeInt(remotePort);
                    out.flush();

                    // rebd endpoint client hbs decided to use
                    ep = new TCPEndpoint(in.rebdUTF(), in.rebdInt(),
                                         endpoint.getClientSocketFbctory(),
                                         endpoint.getServerSocketFbctory());
                    if (tcpLog.isLoggbble(Log.VERBOSE)) {
                        tcpLog.log(Log.VERBOSE, "(port " +
                            port + ") client using " +
                            ep.getHost() + ":" + ep.getPort());
                    }

                    ConnectionMultiplexer multiplexer;
                    synchronized (chbnnelTbble) {
                        // crebte or find chbnnel for this endpoint
                        ch = getChbnnel(ep);
                        multiplexer =
                            new ConnectionMultiplexer(ch, bufIn, sockOut,
                                                      fblse);
                        ch.useMultiplexer(multiplexer);
                    }
                    multiplexer.run();
                    brebk;

                defbult:
                    // protocol not understood, send nbck bnd close socket
                    out.writeByte(TrbnsportConstbnts.ProtocolNbck);
                    out.flush();
                    brebk;
                }

            } cbtch (IOException e) {
                // socket in unknown stbte: destroy socket
                tcpLog.log(Log.BRIEF, "terminbted with exception:", e);
            } finblly {
                closeSocket(socket);
            }
        }
    }
}
