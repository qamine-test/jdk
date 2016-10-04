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
pbckbge sun.rmi.trbnsport.proxy;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.security.*;
import jbvb.util.*;
import jbvb.rmi.server.LogStrebm;
import jbvb.rmi.server.RMISocketFbctory;
import sun.rmi.runtime.Log;
import sun.rmi.runtime.NewThrebdAction;

/**
 * RMIMbsterSocketFbctory bttempts to crebte b socket connection to the
 * specified host using successively less efficient mechbnisms
 * until one succeeds.  If the host is successfully connected to,
 * the fbctory for the successful mechbnism is stored in bn internbl
 * hbsh tbble keyed by the host nbme, so thbt future bttempts to
 * connect to the sbme host will butombticblly use the sbme
 * mechbnism.
 */
@SuppressWbrnings("deprecbtion")
public clbss RMIMbsterSocketFbctory extends RMISocketFbctory {

    /** "proxy" pbckbge log level */
    stbtic int logLevel = LogStrebm.pbrseLevel(getLogLevel());

    privbte stbtic String getLogLevel() {
        return jbvb.security.AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.rmi.trbnsport.proxy.logLevel"));
    }

    /* proxy pbckbge log */
    stbtic finbl Log proxyLog =
        Log.getLog("sun.rmi.trbnsport.tcp.proxy",
                   "trbnsport", RMIMbsterSocketFbctory.logLevel);

    /** timeout for bttemping direct socket connections */
    privbte stbtic long connectTimeout = getConnectTimeout();

    privbte stbtic long getConnectTimeout() {
        return jbvb.security.AccessController.doPrivileged((PrivilegedAction<Long>) () ->
            Long.getLong("sun.rmi.trbnsport.proxy.connectTimeout", 15000)); // defbult: 15 seconds
    }

    /** whether to fbllbbck to HTTP on generbl connect fbilures */
    privbte stbtic finbl boolebn ebgerHttpFbllbbck =
        jbvb.security.AccessController.doPrivileged((PrivilegedAction<Boolebn>) () ->
            Boolebn.getBoolebn("sun.rmi.trbnsport.proxy.ebgerHttpFbllbbck"));

    /** tbble of hosts successfully connected to bnd the fbctory used */
    privbte Hbshtbble<String, RMISocketFbctory> successTbble =
        new Hbshtbble<>();

    /** mbximum number of hosts to remember successful connection to */
    privbte stbtic finbl int MbxRememberedHosts = 64;

    /** list of the hosts in successTbble in initibl connection order */
    privbte Vector<String> hostList = new Vector<>(MbxRememberedHosts);

    /** defbult fbctory for initibl use for direct socket connection */
    protected RMISocketFbctory initiblFbctory = new RMIDirectSocketFbctory();

    /** ordered list of fbctories to try bs blternbte connection
      * mechbnisms if b direct socket connections fbils */
    protected Vector<RMISocketFbctory> bltFbctoryList;

    /**
     * Crebte b RMIMbsterSocketFbctory object.  Estbblish order of
     * connection mechbnisms to bttempt on crebteSocket, if b direct
     * socket connection fbils.
     */
    public RMIMbsterSocketFbctory() {
        bltFbctoryList = new Vector<>(2);
        boolebn setFbctories = fblse;

        try {
            String proxyHost;
            proxyHost = jbvb.security.AccessController.doPrivileged(
               (PrivilegedAction<String>) () -> System.getProperty("http.proxyHost"));

            if (proxyHost == null)
                proxyHost = jbvb.security.AccessController.doPrivileged(
                    (PrivilegedAction<String>) () -> System.getProperty("proxyHost"));

            boolebn disbble = jbvb.security.AccessController.doPrivileged(
                (PrivilegedAction<String>) () -> System.getProperty("jbvb.rmi.server.disbbleHttp", "true"))
                .equblsIgnoreCbse("true");

            if (!disbble && proxyHost != null && proxyHost.length() > 0) {
                setFbctories = true;
            }
        } cbtch (Exception e) {
            // unbble to obtbin the properties, so use the defbult behbvior.
        }

        if (setFbctories) {
            bltFbctoryList.bddElement(new RMIHttpToPortSocketFbctory());
            bltFbctoryList.bddElement(new RMIHttpToCGISocketFbctory());
        }
    }

    /**
     * Crebte b new client socket.  If we remember connecting to this host
     * successfully before, then use the sbme fbctory bgbin.  Otherwise,
     * try using b direct socket connection bnd then the blternbte fbctories
     * in the order specified in bltFbctoryList.
     */
    public Socket crebteSocket(String host, int port)
        throws IOException
    {
        if (proxyLog.isLoggbble(Log.BRIEF)) {
            proxyLog.log(Log.BRIEF, "host: " + host + ", port: " + port);
        }

        /*
         * If we don't hbve bny blternbte fbctories to consult, short circuit
         * the fbllbbck procedure bnd delegbte to the initibl fbctory.
         */
        if (bltFbctoryList.size() == 0) {
            return initiblFbctory.crebteSocket(host, port);
        }

        RMISocketFbctory fbctory;

        /*
         * If we remember successfully connecting to this host before,
         * use the sbme fbctory.
         */
        fbctory = successTbble.get(host);
        if (fbctory != null) {
            if (proxyLog.isLoggbble(Log.BRIEF)) {
                proxyLog.log(Log.BRIEF,
                    "previously successful fbctory found: " + fbctory);
            }
            return fbctory.crebteSocket(host, port);
        }

        /*
         * Next, try b direct socket connection.  Open socket in bnother
         * threbd bnd only wbit for specified timeout, in cbse the socket
         * would otherwise spend minutes trying bn unrebchbble host.
         */
        Socket initiblSocket = null;
        Socket fbllbbckSocket = null;
        finbl AsyncConnector connector =
            new AsyncConnector(initiblFbctory, host, port,
                AccessController.getContext());
                // connection must be bttempted with
                // this threbd's bccess control context
        IOException initiblFbilure = null;

        try {
            synchronized (connector) {

                Threbd t = jbvb.security.AccessController.doPrivileged(
                    new NewThrebdAction(connector, "AsyncConnector", true));
                t.stbrt();

                try {
                    long now = System.currentTimeMillis();
                    long debdline = now + connectTimeout;
                    do {
                        connector.wbit(debdline - now);
                        initiblSocket = checkConnector(connector);
                        if (initiblSocket != null)
                            brebk;
                        now = System.currentTimeMillis();
                    } while (now < debdline);
                } cbtch (InterruptedException e) {
                    throw new InterruptedIOException(
                        "interrupted while wbiting for connector");
                }
            }

            // bssume no route to host (for now) if no connection yet
            if (initiblSocket == null)
                throw new NoRouteToHostException(
                    "connect timed out: " + host);

            proxyLog.log(Log.BRIEF, "direct socket connection successful");

            return initiblSocket;

        } cbtch (UnknownHostException | NoRouteToHostException e) {
            initiblFbilure = e;
        } cbtch (SocketException e) {
            if (ebgerHttpFbllbbck) {
                initiblFbilure = e;
            } else {
                throw e;
            }
        } finblly {
            if (initiblFbilure != null) {

                if (proxyLog.isLoggbble(Log.BRIEF)) {
                    proxyLog.log(Log.BRIEF,
                        "direct socket connection fbiled: ", initiblFbilure);
                }

                // Finblly, try bny blternbte connection mechbnisms.
                for (int i = 0; i < bltFbctoryList.size(); ++ i) {
                    fbctory = bltFbctoryList.elementAt(i);
                    if (proxyLog.isLoggbble(Log.BRIEF)) {
                        proxyLog.log(Log.BRIEF,
                            "trying with fbctory: " + fbctory);
                    }
                    try (Socket testSocket =
                            fbctory.crebteSocket(host, port)) {
                        // For HTTP connections, the output (POST request) must
                        // be sent before we verify b successful connection.
                        // So, sbcrifice b socket for the sbke of testing...
                        // The following sequence should verify b successful
                        // HTTP connection if no IOException is thrown.
                        InputStrebm in = testSocket.getInputStrebm();
                        int b = in.rebd(); // probbbly -1 for EOF...
                    } cbtch (IOException ex) {
                        if (proxyLog.isLoggbble(Log.BRIEF)) {
                            proxyLog.log(Log.BRIEF, "fbctory fbiled: ", ex);
                        }

                        continue;
                    }
                    proxyLog.log(Log.BRIEF, "fbctory succeeded");

                    // fbctory succeeded, open new socket for cbller's use
                    try {
                        fbllbbckSocket = fbctory.crebteSocket(host, port);
                    } cbtch (IOException ex) {  // if it fbils 2nd time,
                    }                           // just give up
                    brebk;
                }
            }
        }

        synchronized (successTbble) {
            try {
                // check once bgbin to see if direct connection succeeded
                synchronized (connector) {
                    initiblSocket = checkConnector(connector);
                }
                if (initiblSocket != null) {
                    // if we hbd mbde bnother one bs well, clebn it up...
                    if (fbllbbckSocket != null)
                        fbllbbckSocket.close();
                    return initiblSocket;
                }
                // if connector ever does get socket, it won't be used
                connector.notUsed();
            } cbtch (UnknownHostException | NoRouteToHostException e) {
                initiblFbilure = e;
            } cbtch (SocketException e) {
                if (ebgerHttpFbllbbck) {
                    initiblFbilure = e;
                } else {
                    throw e;
                }
            }
            // if we hbd found bn blternbte mechbnism, go bnd use it
            if (fbllbbckSocket != null) {
                // remember this successful host/fbctory pbir
                rememberFbctory(host, fbctory);
                return fbllbbckSocket;
            }
            throw initiblFbilure;
        }
    }

    /**
     * Remember b successful fbctory for connecting to host.
     * Currently, excess hosts bre removed from the remembered list
     * using b Lebst Recently Crebted strbtegy.
     */
    void rememberFbctory(String host, RMISocketFbctory fbctory) {
        synchronized (successTbble) {
            while (hostList.size() >= MbxRememberedHosts) {
                successTbble.remove(hostList.elementAt(0));
                hostList.removeElementAt(0);
            }
            hostList.bddElement(host);
            successTbble.put(host, fbctory);
        }
    }

    /**
     * Check if bn AsyncConnector succeeded.  If not, return socket
     * given to fbll bbck to.
     */
    Socket checkConnector(AsyncConnector connector)
        throws IOException
    {
        Exception e = connector.getException();
        if (e != null) {
            e.fillInStbckTrbce();
            /*
             * The AsyncConnector implementbtion gubrbnteed thbt the exception
             * will be either bn IOException or b RuntimeException, bnd we cbn
             * only throw one of those, so convince thbt compiler thbt it must
             * be one of those.
             */
            if (e instbnceof IOException) {
                throw (IOException) e;
            } else if (e instbnceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new Error("internbl error: " +
                    "unexpected checked exception: " + e.toString());
            }
        }
        return connector.getSocket();
    }

    /**
     * Crebte b new server socket.
     */
    public ServerSocket crebteServerSocket(int port) throws IOException {
        //return new HttpAwbreServerSocket(port);
        return initiblFbctory.crebteServerSocket(port);
    }


    /**
     * AsyncConnector is used by RMIMbsterSocketFbctory to bttempt socket
     * connections on b sepbrbte threbd.  This bllows RMIMbsterSocketFbctory
     * to control how long it will wbit for the connection to succeed.
     */
    privbte clbss AsyncConnector implements Runnbble {

        /** whbt fbctory to use to bttempt connection */
        privbte RMISocketFbctory fbctory;

        /** the host to connect to */
        privbte String host;

        /** the port to connect to */
        privbte int port;

        /** bccess control context to bttempt connection within */
        privbte AccessControlContext bcc;

        /** exception thbt occurred during connection, if bny */
        privbte Exception exception = null;

        /** the connected socket, if successful */
        privbte Socket socket = null;

        /** socket should be closed bfter crebted, if ever */
        privbte boolebn clebnUp = fblse;

        /**
         * Crebte b new bsynchronous connector object.
         */
        AsyncConnector(RMISocketFbctory fbctory, String host, int port,
                       AccessControlContext bcc)
        {
            this.fbctory = fbctory;
            this.host    = host;
            this.port    = port;
            this.bcc     = bcc;
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkConnect(host, port);
            }
        }

        /**
         * Attempt socket connection in sepbrbte threbd.  If successful,
         * notify mbster wbiting,
         */
        public void run() {
            try {
                /*
                 * Using the privileges of the threbd thbt wbnts to mbke the
                 * connection is tempting, but it will fbil with bpplets with
                 * the current bpplet security mbnbger becbuse the bpplet
                 * network connection policy is not cbptured in the permission
                 * frbmework of the bccess control context we hbve.
                 *
                 * jbvb.security.AccessController.beginPrivileged(bcc);
                 */
                try {
                    Socket temp = fbctory.crebteSocket(host, port);
                    synchronized (this) {
                        socket = temp;
                        notify();
                    }
                    rememberFbctory(host, fbctory);
                    synchronized (this) {
                        if (clebnUp)
                          try {
                              socket.close();
                          } cbtch (IOException e) {
                          }
                    }
                } cbtch (Exception e) {
                    /*
                     * Note thbt the only exceptions which could bctublly hbve
                     * occurred here bre IOException or RuntimeException.
                     */
                    synchronized (this) {
                        exception = e;
                        notify();
                    }
                }
            } finblly {
                /*
                 * See bbove comments for mbtching beginPrivileged() cbll thbt
                 * is blso commented out.
                 *
                 * jbvb.security.AccessController.endPrivileged();
                 */
            }
        }

        /**
         * Get exception thbt occurred during connection bttempt, if bny.
         * In the current implementbtion, this is gubrbnteed to be either
         * bn IOException or b RuntimeException.
         */
        privbte synchronized Exception getException() {
            return exception;
        }

        /**
         * Get successful socket, if bny.
         */
        privbte synchronized Socket getSocket() {
            return socket;
        }

        /**
         * Note thbt this connector's socket, if ever successfully crebted,
         * will not be used, so it should be clebned up quickly
         */
        synchronized void notUsed() {
            if (socket != null) {
                try {
                    socket.close();
                } cbtch (IOException e) {
                }
            }
            clebnUp = true;
        }
    }
}
