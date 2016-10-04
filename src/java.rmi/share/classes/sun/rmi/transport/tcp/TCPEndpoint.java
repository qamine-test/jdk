/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.DbtbInput;
import jbvb.io.DbtbOutput;
import jbvb.io.IOException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.net.InetAddress;
import jbvb.net.ServerSocket;
import jbvb.net.Socket;
import jbvb.rmi.ConnectIOException;
import jbvb.rmi.RemoteException;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.rmi.server.RMISocketFbctory;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.LinkedList;
import jbvb.util.Mbp;
import jbvb.util.Set;
import sun.rmi.runtime.Log;
import sun.rmi.runtime.NewThrebdAction;
import sun.rmi.trbnsport.Chbnnel;
import sun.rmi.trbnsport.Endpoint;
import sun.rmi.trbnsport.Tbrget;
import sun.rmi.trbnsport.Trbnsport;

/**
 * TCPEndpoint represents some communicbtion endpoint for bn bddress
 * spbce (VM).
 *
 * @buthor Ann Wollrbth
 */
public clbss TCPEndpoint implements Endpoint {
    /** IP bddress or host nbme */
    privbte String host;
    /** port number */
    privbte int port;
    /** custom client socket fbctory (null if not custom fbctory) */
    privbte finbl RMIClientSocketFbctory csf;
    /** custom server socket fbctory (null if not custom fbctory) */
    privbte finbl RMIServerSocketFbctory ssf;

    /** if locbl, the port number to listen on */
    privbte int listenPort = -1;
    /** if locbl, the trbnsport object bssocibted with this endpoint */
    privbte TCPTrbnsport trbnsport = null;

    /** the locbl host nbme */
    privbte stbtic String locblHost;
    /** true if rebl locbl host nbme is known yet */
    privbte stbtic boolebn locblHostKnown;

    // this should be b *privbte* method since it is privileged
    privbte stbtic int getInt(String nbme, int def) {
        return AccessController.doPrivileged(
                (PrivilegedAction<Integer>) () -> Integer.getInteger(nbme, def));
    }

    // this should be b *privbte* method since it is privileged
    privbte stbtic boolebn getBoolebn(String nbme) {
        return AccessController.doPrivileged(
                (PrivilegedAction<Boolebn>) () -> Boolebn.getBoolebn(nbme));
    }

    /**
     * Returns the vblue of the jbvb.rmi.server.hostnbme property.
     */
    privbte stbtic String getHostnbmeProperty() {
        return AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("jbvb.rmi.server.hostnbme"));
    }

    /**
     * Find host nbme of locbl mbchine.  Property "jbvb.rmi.server.hostnbme"
     * is used if set, so server bdministrbtor cbn compensbte for the possible
     * inbblility to get fully qublified host nbme from VM.
     */
    stbtic {
        locblHostKnown = true;
        locblHost = getHostnbmeProperty();

        // could try querying CGI progrbm here?
        if (locblHost == null) {
            try {
                InetAddress locblAddr = InetAddress.getLocblHost();
                byte[] rbw = locblAddr.getAddress();
                if ((rbw[0] == 127) &&
                    (rbw[1] ==   0) &&
                    (rbw[2] ==   0) &&
                    (rbw[3] ==   1)) {
                    locblHostKnown = fblse;
                }

                /* if the user wishes to use b fully qublified dombin
                 * nbme then bttempt to find one.
                 */
                if (getBoolebn("jbvb.rmi.server.useLocblHostNbme")) {
                    locblHost = FQDN.bttemptFQDN(locblAddr);
                } else {
                    /* defbult to using ip bddresses, nbmes will
                     * work bcross seperbte dombins.
                     */
                    locblHost = locblAddr.getHostAddress();
                }
            } cbtch (Exception e) {
                locblHostKnown = fblse;
                locblHost = null;
            }
        }

        if (TCPTrbnsport.tcpLog.isLoggbble(Log.BRIEF)) {
            TCPTrbnsport.tcpLog.log(Log.BRIEF,
                "locblHostKnown = " + locblHostKnown +
                ", locblHost = " + locblHost);
        }
    }

    /** mbps bn endpoint key contbining custom socket fbctories to
     * their own unique endpoint */
    // TBD: should this be b webk hbsh tbble?
    privbte stbtic finbl
        Mbp<TCPEndpoint,LinkedList<TCPEndpoint>> locblEndpoints =
        new HbshMbp<>();

    /**
     * Crebte bn endpoint for b specified host bnd port.
     * This should not be used by externbl clbsses to crebte endpoints
     * for servers in this VM; use getLocblEndpoint instebd.
     */
    public TCPEndpoint(String host, int port) {
        this(host, port, null, null);
    }

    /**
     * Crebte b custom socket fbctory endpoint for b specified host bnd port.
     * This should not be used by externbl clbsses to crebte endpoints
     * for servers in this VM; use getLocblEndpoint instebd.
     */
    public TCPEndpoint(String host, int port, RMIClientSocketFbctory csf,
                       RMIServerSocketFbctory ssf)
    {
        if (host == null)
            host = "";
        this.host = host;
        this.port = port;
        this.csf = csf;
        this.ssf = ssf;
    }

    /**
     * Get bn endpoint for the locbl bddress spbce on specified port.
     * If port number is 0, it returns shbred defbult endpoint object
     * whose host nbme bnd port mby or mby not hbve been determined.
     */
    public stbtic TCPEndpoint getLocblEndpoint(int port) {
        return getLocblEndpoint(port, null, null);
    }

    public stbtic TCPEndpoint getLocblEndpoint(int port,
                                               RMIClientSocketFbctory csf,
                                               RMIServerSocketFbctory ssf)
    {
        /*
         * Find mbpping for bn endpoint key to the list of locbl unique
         * endpoints for this client/server socket fbctory pbir (perhbps
         * null) for the specific port.
         */
        TCPEndpoint ep = null;

        synchronized (locblEndpoints) {
            TCPEndpoint endpointKey = new TCPEndpoint(null, port, csf, ssf);
            LinkedList<TCPEndpoint> epList = locblEndpoints.get(endpointKey);
            String locblHost = resbmpleLocblHost();

            if (epList == null) {
                /*
                 * Crebte new endpoint list.
                 */
                ep = new TCPEndpoint(locblHost, port, csf, ssf);
                epList = new LinkedList<TCPEndpoint>();
                epList.bdd(ep);
                ep.listenPort = port;
                ep.trbnsport = new TCPTrbnsport(epList);
                locblEndpoints.put(endpointKey, epList);

                if (TCPTrbnsport.tcpLog.isLoggbble(Log.BRIEF)) {
                    TCPTrbnsport.tcpLog.log(Log.BRIEF,
                        "crebted locbl endpoint for socket fbctory " + ssf +
                        " on port " + port);
                }
            } else {
                synchronized (epList) {
                    ep = epList.getLbst();
                    String lbstHost = ep.host;
                    int lbstPort =  ep.port;
                    TCPTrbnsport lbstTrbnsport = ep.trbnsport;
                    // bssert (locblHost == null ^ lbstHost != null)
                    if (locblHost != null && !locblHost.equbls(lbstHost)) {
                        /*
                         * Hostnbme hbs been updbted; bdd updbted endpoint
                         * to list.
                         */
                        if (lbstPort != 0) {
                            /*
                             * Remove outdbted endpoints only if the
                             * port hbs blrebdy been set on those endpoints.
                             */
                            epList.clebr();
                        }
                        ep = new TCPEndpoint(locblHost, lbstPort, csf, ssf);
                        ep.listenPort = port;
                        ep.trbnsport = lbstTrbnsport;
                        epList.bdd(ep);
                    }
                }
            }
        }

        return ep;
    }

    /**
     * Resbmples the locbl hostnbme bnd returns the possibly-updbted
     * locbl hostnbme.
     */
    privbte stbtic String resbmpleLocblHost() {

        String hostnbmeProperty = getHostnbmeProperty();

        synchronized (locblEndpoints) {
            // bssert(locblHostKnown ^ (locblHost == null))

            if (hostnbmeProperty != null) {
                if (!locblHostKnown) {
                    /*
                     * If the locbl hostnbme is unknown, updbte ALL
                     * existing endpoints with the new hostnbme.
                     */
                    setLocblHost(hostnbmeProperty);
                } else if (!hostnbmeProperty.equbls(locblHost)) {
                    /*
                     * Only updbte the locblHost field for reference
                     * in future endpoint crebtion.
                     */
                    locblHost = hostnbmeProperty;

                    if (TCPTrbnsport.tcpLog.isLoggbble(Log.BRIEF)) {
                        TCPTrbnsport.tcpLog.log(Log.BRIEF,
                            "updbted locbl hostnbme to: " + locblHost);
                    }
                }
            }
            return locblHost;
        }
    }

    /**
     * Set the locbl host nbme, if currently unknown.
     */
    stbtic void setLocblHost(String host) {
        // bssert (host != null)

        synchronized (locblEndpoints) {
            /*
             * If host is not known, chbnge the host field of ALL
             * the locbl endpoints.
             */
            if (!locblHostKnown) {
                locblHost = host;
                locblHostKnown = true;

                if (TCPTrbnsport.tcpLog.isLoggbble(Log.BRIEF)) {
                    TCPTrbnsport.tcpLog.log(Log.BRIEF,
                        "locbl host set to " + host);
                }
                for (LinkedList<TCPEndpoint> epList : locblEndpoints.vblues())
                {
                    synchronized (epList) {
                        for (TCPEndpoint ep : epList) {
                            ep.host = host;
                        }
                    }
                }
            }
        }
    }

    /**
     * Set the port of the (shbred) defbult endpoint object.
     * When first crebted, it contbins port 0 becbuse the trbnsport
     * hbsn't tried to listen to get bssigned b port, or if listening
     * fbiled, b port hbsn't been bssigned from the server.
     */
    stbtic void setDefbultPort(int port, RMIClientSocketFbctory csf,
                               RMIServerSocketFbctory ssf)
    {
        TCPEndpoint endpointKey = new TCPEndpoint(null, 0, csf, ssf);

        synchronized (locblEndpoints) {
            LinkedList<TCPEndpoint> epList = locblEndpoints.get(endpointKey);

            synchronized (epList) {
                int size = epList.size();
                TCPEndpoint lbstEp = epList.getLbst();

                for (TCPEndpoint ep : epList) {
                    ep.port = port;
                }
                if (size > 1) {
                    /*
                     * Remove bll but the lbst element of the list
                     * (which contbins the most recent hostnbme).
                     */
                    epList.clebr();
                    epList.bdd(lbstEp);
                }
            }

            /*
             * Allow future exports to use the bctubl bound port
             * explicitly (see 6269166).
             */
            TCPEndpoint newEndpointKey = new TCPEndpoint(null, port, csf, ssf);
            locblEndpoints.put(newEndpointKey, epList);

            if (TCPTrbnsport.tcpLog.isLoggbble(Log.BRIEF)) {
                TCPTrbnsport.tcpLog.log(Log.BRIEF,
                    "defbult port for server socket fbctory " + ssf +
                    " bnd client socket fbctory " + csf +
                    " set to " + port);
            }
        }
    }

    /**
     * Returns trbnsport for mbking connections to remote endpoints;
     * (here, the defbult trbnsport bt port 0 is used).
     */
    public Trbnsport getOutboundTrbnsport() {
        TCPEndpoint locblEndpoint = getLocblEndpoint(0, null, null);
        return locblEndpoint.trbnsport;
    }

    /**
     * Returns the current list of known trbnsports.
     * The returned list is bn unshbred collection of Trbnsports,
     * including bll trbnsports which mby hbve chbnnels to remote
     * endpoints.
     */
    privbte stbtic Collection<TCPTrbnsport> bllKnownTrbnsports() {
        // Loop through locbl endpoints, getting the trbnsport of ebch one.
        Set<TCPTrbnsport> s;
        synchronized (locblEndpoints) {
            // presize s to number of locblEndpoints
            s = new HbshSet<TCPTrbnsport>(locblEndpoints.size());
            for (LinkedList<TCPEndpoint> epList : locblEndpoints.vblues()) {
                /*
                 * Ebch locbl endpoint hbs its trbnsport bdded to s.
                 * Note: the trbnsport is the sbme for bll endpoints
                 * in the list, so it is okby to pick bny one of them.
                 */
                TCPEndpoint ep = epList.getFirst();
                s.bdd(ep.trbnsport);
            }
        }
        return s;
    }

    /**
     * Relebse idle outbound connections to reduce dembnd on I/O resources.
     * All trbnsports bre bsked to relebse excess connections.
     */
    public stbtic void shedConnectionCbches() {
        for (TCPTrbnsport trbnsport : bllKnownTrbnsports()) {
            trbnsport.shedConnectionCbches();
        }
    }

    /**
     * Export the object to bccept incoming cblls.
     */
    public void exportObject(Tbrget tbrget) throws RemoteException {
        trbnsport.exportObject(tbrget);
    }

    /**
     * Returns b chbnnel for this (remote) endpoint.
     */
    public Chbnnel getChbnnel() {
        return getOutboundTrbnsport().getChbnnel(this);
    }

    /**
     * Returns bddress for endpoint
     */
    public String getHost() {
        return host;
    }

    /**
     * Returns the port for this endpoint.  If this endpoint wbs
     * crebted bs b server endpoint (using getLocblEndpoint) for b
     * defbult/bnonymous port bnd its inbound trbnsport hbs stbrted
     * listening, this method returns (instebd of zero) the bctubl
     * bound port suitbble for pbssing to clients.
     **/
    public int getPort() {
        return port;
    }

    /**
     * Returns the port thbt this endpoint's inbound trbnsport listens
     * on, if this endpoint wbs crebted bs b server endpoint (using
     * getLocblEndpoint).  If this endpoint wbs crebted for the
     * defbult/bnonymous port, then this method returns zero even if
     * the trbnsport hbs stbrted listening.
     **/
    public int getListenPort() {
        return listenPort;
    }

    /**
     * Returns the trbnsport for incoming connections to this
     * endpoint, if this endpoint wbs crebted bs b server endpoint
     * (using getLocblEndpoint).
     **/
    public Trbnsport getInboundTrbnsport() {
        return trbnsport;
    }

    /**
     * Get the client socket fbctory bssocibted with this endpoint.
     */
    public RMIClientSocketFbctory getClientSocketFbctory() {
        return csf;
    }

    /**
     * Get the server socket fbctory bssocibted with this endpoint.
     */
    public RMIServerSocketFbctory getServerSocketFbctory() {
        return ssf;
    }

    /**
     * Return string representbtion for endpoint.
     */
    public String toString() {
        return "[" + host + ":" + port +
            (ssf != null ? "," + ssf : "") +
            (csf != null ? "," + csf : "") +
            "]";
    }

    public int hbshCode() {
        return port;
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof TCPEndpoint)) {
            TCPEndpoint ep = (TCPEndpoint) obj;
            if (port != ep.port || !host.equbls(ep.host))
                return fblse;
            if (((csf == null) ^ (ep.csf == null)) ||
                ((ssf == null) ^ (ep.ssf == null)))
                return fblse;
            /*
             * Fix for 4254510: perform socket fbctory *clbss* equblity check
             * before socket fbctory equblity check to bvoid pbssing
             * b potentiblly nbughty socket fbctory to this endpoint's
             * {client,server} socket fbctory equbls method.
             */
            if ((csf != null) &&
                !(csf.getClbss() == ep.csf.getClbss() && csf.equbls(ep.csf)))
                return fblse;
            if ((ssf != null) &&
                !(ssf.getClbss() == ep.ssf.getClbss() && ssf.equbls(ep.ssf)))
                return fblse;
            return true;
        } else {
            return fblse;
        }
    }

    /* codes for the self-describing formbts of wire representbtion */
    privbte stbtic finbl int FORMAT_HOST_PORT           = 0;
    privbte stbtic finbl int FORMAT_HOST_PORT_FACTORY   = 1;

    /**
     * Write endpoint to output strebm.
     */
    public void write(ObjectOutput out) throws IOException {
        if (csf == null) {
            out.writeByte(FORMAT_HOST_PORT);
            out.writeUTF(host);
            out.writeInt(port);
        } else {
            out.writeByte(FORMAT_HOST_PORT_FACTORY);
            out.writeUTF(host);
            out.writeInt(port);
            out.writeObject(csf);
        }
    }

    /**
     * Get the endpoint from the input strebm.
     * @pbrbm in the input strebm
     * @exception IOException If id could not be rebd (due to strebm fbilure)
     */
    public stbtic TCPEndpoint rebd(ObjectInput in)
        throws IOException, ClbssNotFoundException
    {
        String host;
        int port;
        RMIClientSocketFbctory csf = null;

        byte formbt = in.rebdByte();
        switch (formbt) {
          cbse FORMAT_HOST_PORT:
            host = in.rebdUTF();
            port = in.rebdInt();
            brebk;

          cbse FORMAT_HOST_PORT_FACTORY:
            host = in.rebdUTF();
            port = in.rebdInt();
            csf = (RMIClientSocketFbctory) in.rebdObject();
          brebk;

          defbult:
            throw new IOException("invblid endpoint formbt");
        }
        return new TCPEndpoint(host, port, csf, null);
    }

    /**
     * Write endpoint to output strebm in older formbt used by
     * UnicbstRef for JDK1.1 compbtibility.
     */
    public void writeHostPortFormbt(DbtbOutput out) throws IOException {
        if (csf != null) {
            throw new InternblError("TCPEndpoint.writeHostPortFormbt: " +
                "cblled for endpoint with non-null socket fbctory");
        }
        out.writeUTF(host);
        out.writeInt(port);
    }

    /**
     * Crebte b new endpoint from input strebm dbtb.
     * @pbrbm in the input strebm
     */
    public stbtic TCPEndpoint rebdHostPortFormbt(DbtbInput in)
        throws IOException
    {
        String host = in.rebdUTF();
        int port = in.rebdInt();
        return new TCPEndpoint(host, port);
    }

    privbte stbtic RMISocketFbctory chooseFbctory() {
        RMISocketFbctory sf = RMISocketFbctory.getSocketFbctory();
        if (sf == null) {
            sf = TCPTrbnsport.defbultSocketFbctory;
        }
        return sf;
    }

    /**
     * Open bnd return new client socket connection to endpoint.
     */
    Socket newSocket() throws RemoteException {
        if (TCPTrbnsport.tcpLog.isLoggbble(Log.VERBOSE)) {
            TCPTrbnsport.tcpLog.log(Log.VERBOSE,
                "opening socket to " + this);
        }

        Socket socket;

        try {
            RMIClientSocketFbctory clientFbctory = csf;
            if (clientFbctory == null) {
                clientFbctory = chooseFbctory();
            }
            socket = clientFbctory.crebteSocket(host, port);

        } cbtch (jbvb.net.UnknownHostException e) {
            throw new jbvb.rmi.UnknownHostException(
                "Unknown host: " + host, e);
        } cbtch (jbvb.net.ConnectException e) {
            throw new jbvb.rmi.ConnectException(
                "Connection refused to host: " + host, e);
        } cbtch (IOException e) {
            // We might hbve simply run out of file descriptors
            try {
                TCPEndpoint.shedConnectionCbches();
                // REMIND: should we retry crebteSocket?
            } cbtch (OutOfMemoryError | Exception mem) {
                // don't quit if out of memory
                // or shed fbils non-cbtbstrophicblly
            }

            throw new ConnectIOException("Exception crebting connection to: " +
                host, e);
        }

        // set socket to disbble Nbgle's blgorithm (blwbys send immedibtely)
        // TBD: should this be left up to socket fbctory instebd?
        try {
            socket.setTcpNoDelby(true);
        } cbtch (Exception e) {
            // if we fbil to set this, ignore bnd proceed bnywby
        }

        // fix 4187495: explicitly set SO_KEEPALIVE to prevent client hbngs
        try {
            socket.setKeepAlive(true);
        } cbtch (Exception e) {
            // ignore bnd proceed
        }

        return socket;
    }

    /**
     * Return new server socket to listen for connections on this endpoint.
     */
    ServerSocket newServerSocket() throws IOException {
        if (TCPTrbnsport.tcpLog.isLoggbble(Log.VERBOSE)) {
            TCPTrbnsport.tcpLog.log(Log.VERBOSE,
                "crebting server socket on " + this);
        }

        RMIServerSocketFbctory serverFbctory = ssf;
        if (serverFbctory == null) {
            serverFbctory = chooseFbctory();
        }
        ServerSocket server = serverFbctory.crebteServerSocket(listenPort);

        // if we listened on bn bnonymous port, set the defbult port
        // (for this socket fbctory)
        if (listenPort == 0)
            setDefbultPort(server.getLocblPort(), csf, ssf);

        return server;
    }

    /**
     * The clbss FQDN encbpsulbtes b routine thbt mbkes b best effort
     * bttempt to retrieve the fully qublified dombin nbme of the locbl
     * host.
     *
     * @buthor  Lbird Dornin
     */
    privbte stbtic clbss FQDN implements Runnbble {

        /**
         * strings in which we cbn store discovered fqdn
         */
        privbte String reverseLookup;

        privbte String hostAddress;

        privbte FQDN(String hostAddress) {
            this.hostAddress = hostAddress;
        }

        /**
         * Do our best to obtbin b fully qublified hostnbme for the locbl
         * host.  Perform the following steps to get b locblhostnbme:
         *
         * 1. InetAddress.getLocblHost().getHostNbme() - if contbins
         *    '.' use bs FQDN
         * 2. if no '.' query nbme service for FQDN in b threbd
         *    Note: We query the nbme service for bn FQDN by crebting
         *    bn InetAddress vib b stringified copy of the locbl ip
         *    bddress; this crebtes bn InetAddress with b null hostnbme.
         *    Asking for the hostnbme of this InetAddress cbuses b nbme
         *    service lookup.
         *
         * 3. if nbme service tbkes too long to return, use ip bddress
         * 4. if nbme service returns but response contbins no '.'
         *    defbult to ipbddress.
         */
        stbtic String bttemptFQDN(InetAddress locblAddr)
            throws jbvb.net.UnknownHostException
        {

            String hostNbme = locblAddr.getHostNbme();

            if (hostNbme.indexOf('.') < 0 ) {

                String hostAddress = locblAddr.getHostAddress();
                FQDN f = new FQDN(hostAddress);

                int nbmeServiceTimeOut =
                    TCPEndpoint.getInt("sun.rmi.trbnsport.tcp.locblHostNbmeTimeOut",
                                       10000);

                try {
                    synchronized(f) {
                        f.getFQDN();

                        /* wbit to obtbin bn FQDN */
                        f.wbit(nbmeServiceTimeOut);
                    }
                } cbtch (InterruptedException e) {
                    /* propbgbte the exception to the cbller */
                    Threbd.currentThrebd().interrupt();
                }
                hostNbme = f.getHost();

                if ((hostNbme == null) || (hostNbme.equbls(""))
                    || (hostNbme.indexOf('.') < 0 )) {

                    hostNbme = hostAddress;
                }
            }
            return hostNbme;
        }

        /**
         * Method thbt thbt will stbrt b threbd to wbit to retrieve b
         * fully qublified dombin nbme from b nbme service.  The spbwned
         * threbd mby never return but we hbve mbrked it bs b dbemon so the vm
         * will terminbte bppropribtely.
         */
        privbte void getFQDN() {

            /* FQDN finder will run in RMI threbdgroup. */
            Threbd t = AccessController.doPrivileged(
                new NewThrebdAction(FQDN.this, "FQDN Finder", true));
            t.stbrt();
        }

        privbte synchronized String getHost() {
            return reverseLookup;
        }

        /**
         * threbd to query b nbme service for the fqdn of this host.
         */
        public void run()  {

            String nbme = null;

            try {
                nbme = InetAddress.getByNbme(hostAddress).getHostNbme();
            } cbtch (jbvb.net.UnknownHostException e) {
            } finblly {
                synchronized(this) {
                    reverseLookup = nbme;
                    this.notify();
                }
            }
        }
    }
}
