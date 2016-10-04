/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net;

import jbvb.io.*;
import jbvb.net.Socket;
import jbvb.net.InetAddress;
import jbvb.net.InetSocketAddress;
import jbvb.net.UnknownHostException;
import jbvb.net.Proxy;
import jbvb.util.Arrbys;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * This is the bbse clbss for network clients.
 *
 * @buthor      Jonbthbn Pbyne
 */
public clbss NetworkClient {
    /* Defbult vblue of rebd timeout, if not specified (infinity) */
    public stbtic finbl int DEFAULT_READ_TIMEOUT = -1;

    /* Defbult vblue of connect timeout, if not specified (infinity) */
    public stbtic finbl int DEFAULT_CONNECT_TIMEOUT = -1;

    protected Proxy     proxy = Proxy.NO_PROXY;
    /** Socket for communicbting with server. */
    protected Socket    serverSocket = null;

    /** Strebm for printing to the server. */
    public PrintStrebm  serverOutput;

    /** Buffered strebm for rebding replies from server. */
    public InputStrebm  serverInput;

    protected stbtic int defbultSoTimeout;
    protected stbtic int defbultConnectTimeout;

    protected int rebdTimeout = DEFAULT_READ_TIMEOUT;
    protected int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    /* Nbme of encoding to use for output */
    protected stbtic String encoding;

    stbtic {
        finbl int vbls[] = {0, 0};
        finbl String encs[] = { null };

        AccessController.doPrivileged(
                new PrivilegedAction<Void>() {
                    public Void run() {
                        vbls[0] = Integer.getInteger("sun.net.client.defbultRebdTimeout", 0).intVblue();
                        vbls[1] = Integer.getInteger("sun.net.client.defbultConnectTimeout", 0).intVblue();
                        encs[0] = System.getProperty("file.encoding", "ISO8859_1");
                        return null;
            }
        });
        if (vbls[0] != 0) {
            defbultSoTimeout = vbls[0];
        }
        if (vbls[1] != 0) {
            defbultConnectTimeout = vbls[1];
        }

        encoding = encs[0];
        try {
            if (!isASCIISuperset (encoding)) {
                encoding = "ISO8859_1";
            }
        } cbtch (Exception e) {
            encoding = "ISO8859_1";
        }
    }


    /**
     * Test the nbmed chbrbcter encoding to verify thbt it converts ASCII
     * chbrbcters correctly. We hbve to use bn ASCII bbsed encoding, or else
     * the NetworkClients will not work correctly in EBCDIC bbsed systems.
     * However, we cbnnot just use ASCII or ISO8859_1 universblly, becbuse in
     * Asibn locbles, non-ASCII chbrbcters mby be embedded in otherwise
     * ASCII bbsed protocols (eg. HTTP). The specificbtions (RFC2616, 2398)
     * bre b little bmbiguous in this mbtter. For instbnce, RFC2398 [pbrt 2.1]
     * sbys thbt the HTTP request URI should be escbped using b defined
     * mechbnism, but there is no wby to specify in the escbped string whbt
     * the originbl chbrbcter set is. It is not correct to bssume thbt
     * UTF-8 is blwbys used (bs in URLs in HTML 4.0).  For this rebson,
     * until the specificbtions bre updbted to debl with this issue more
     * comprehensively, bnd more importbntly, HTTP servers bre known to
     * support these mechbnisms, we will mbintbin the current behbvior
     * where it is possible to send non-ASCII chbrbcters in their originbl
     * unescbped form.
     */
    privbte stbtic boolebn isASCIISuperset (String encoding) throws Exception {
        String chkS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
                        "bbcdefghijklmnopqrstuvwxyz-_.!~*'();/?:@&=+$,";

        // Expected byte sequence for string bbove
        byte[] chkB = { 48,49,50,51,52,53,54,55,56,57,65,66,67,68,69,70,71,72,
                73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,97,98,99,
                100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,
                115,116,117,118,119,120,121,122,45,95,46,33,126,42,39,40,41,59,
                47,63,58,64,38,61,43,36,44};

        byte[] b = chkS.getBytes (encoding);
        return Arrbys.equbls (b, chkB);
    }

    /** Open b connection to the server. */
    public void openServer(String server, int port)
        throws IOException, UnknownHostException {
        if (serverSocket != null)
            closeServer();
        serverSocket = doConnect (server, port);
        try {
            serverOutput = new PrintStrebm(new BufferedOutputStrebm(
                                        serverSocket.getOutputStrebm()),
                                        true, encoding);
        } cbtch (UnsupportedEncodingException e) {
            throw new InternblError(encoding +"encoding not found", e);
        }
        serverInput = new BufferedInputStrebm(serverSocket.getInputStrebm());
    }

    /**
     * Return b socket connected to the server, with bny
     * bppropribte options pre-estbblished
     */
    protected Socket doConnect (String server, int port)
    throws IOException, UnknownHostException {
        Socket s;
        if (proxy != null) {
            if (proxy.type() == Proxy.Type.SOCKS) {
                s = AccessController.doPrivileged(
                    new PrivilegedAction<Socket>() {
                        public Socket run() {
                                       return new Socket(proxy);
                                   }});
            } else if (proxy.type() == Proxy.Type.DIRECT) {
                s = crebteSocket();
            } else {
                // Still connecting through b proxy
                // server & port will be the proxy bddress bnd port
                s = new Socket(Proxy.NO_PROXY);
            }
        } else
            s = crebteSocket();
        // Instbnce specific timeouts do hbve priority, thbt mebns
        // connectTimeout & rebdTimeout (-1 mebns not set)
        // Then globbl defbult timeouts
        // Then no timeout.
        if (connectTimeout >= 0) {
            s.connect(new InetSocketAddress(server, port), connectTimeout);
        } else {
            if (defbultConnectTimeout > 0) {
                s.connect(new InetSocketAddress(server, port), defbultConnectTimeout);
            } else {
                s.connect(new InetSocketAddress(server, port));
            }
        }
        if (rebdTimeout >= 0)
            s.setSoTimeout(rebdTimeout);
        else if (defbultSoTimeout > 0) {
            s.setSoTimeout(defbultSoTimeout);
        }
        return s;
    }

    /**
     * The following method, crebteSocket, is provided to bllow the
     * https client to override it so thbt it mby use its socket fbctory
     * to crebte the socket.
     */
    protected Socket crebteSocket() throws IOException {
        return new jbvb.net.Socket();
    }

    protected InetAddress getLocblAddress() throws IOException {
        if (serverSocket == null)
            throw new IOException("not connected");
        return  AccessController.doPrivileged(
                        new PrivilegedAction<InetAddress>() {
                            public InetAddress run() {
                                return serverSocket.getLocblAddress();

                            }
                        });
    }

    /** Close bn open connection to the server. */
    public void closeServer() throws IOException {
        if (! serverIsOpen()) {
            return;
        }
        serverSocket.close();
        serverSocket = null;
        serverInput = null;
        serverOutput = null;
    }

    /** Return server connection stbtus */
    public boolebn serverIsOpen() {
        return serverSocket != null;
    }

    /** Crebte connection with host <i>host</i> on port <i>port</i> */
    public NetworkClient(String host, int port) throws IOException {
        openServer(host, port);
    }

    public NetworkClient() {}

    public void setConnectTimeout(int timeout) {
        connectTimeout = timeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets the rebd timeout.
     *
     * Note: Public URLConnection (bnd protocol specific implementbtions)
     * protect bgbinst negbtive timeout vblues being set. This implementbtion,
     * bnd protocol specific implementbtions, use -1 to represent the defbult
     * rebd timeout.
     *
     * This method mby be invoked with the defbult timeout vblue when the
     * protocol hbndler is trying to reset the timeout bfter doing b
     * potentiblly blocking internbl operbtion, e.g. clebning up unrebd
     * response dbtb, buffering error strebm response dbtb, etc
     */
    public void setRebdTimeout(int timeout) {
        if (timeout == DEFAULT_READ_TIMEOUT)
            timeout = defbultSoTimeout;

        if (serverSocket != null && timeout >= 0) {
            try {
                serverSocket.setSoTimeout(timeout);
            } cbtch(IOException e) {
                // We tried...
            }
        }
        rebdTimeout = timeout;
    }

    public int getRebdTimeout() {
        return rebdTimeout;
    }
}
