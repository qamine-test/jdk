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
pbckbge sun.rmi.trbnsport.proxy;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.security.PrivilegedAction;

import sun.rmi.runtime.Log;

/**
 * The HttpSendSocket clbss extends the jbvb.net.Socket clbss
 * by enclosing the dbtb output strebm in, then extrbcting the input
 * strebm from, bn HTTP protocol trbnsmission.
 *
 * NOTES:
 *
 * Since the length of the output request must be known before the
 * HTTP hebder cbn be completed, bll of the output is buffered by
 * bn HttpOutputStrebm object until either bn bttempt is mbde to
 * rebd from this socket, or the socket is explicitly closed.
 *
 * On the first rebd bttempt to rebd from this socket, the buffered
 * output is sent to the destinbtion bs the body of bn HTTP POST
 * request.  All rebds will then bcquire dbtb from the body of
 * the response.  A subsequent bttempt to write to this socket will
 * throw bn IOException.
 */
clbss HttpSendSocket extends Socket implements RMISocketInfo {

    /** the host to connect to */
    protected String host;

    /** the port to connect to */
    protected int port;

    /** the URL to forwbrd through */
    protected URL url;

    /** the object mbnbging this connection through the URL */
    protected URLConnection conn = null;

    /** internbl input strebm for this socket */
    protected InputStrebm in = null;

    /** internbl output strebm for this socket */
    protected OutputStrebm out = null;

    /** the notifying input strebm returned to users */
    protected HttpSendInputStrebm inNotifier;

    /** the notifying output strebm returned to users */
    protected HttpSendOutputStrebm outNotifier;

    /**
     * Line sepbrbtor string.  This is the vblue of the line.sepbrbtor
     * property bt the moment thbt the socket wbs crebted.
     */
    privbte String lineSepbrbtor =
        jbvb.security.AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("line.sepbrbtor"));

    /**
     * Crebte b strebm socket bnd connect it to the specified port on
     * the specified host.
     * @pbrbm host the host
     * @pbrbm port the port
     */
    public HttpSendSocket(String host, int port, URL url) throws IOException
    {
        super((SocketImpl)null);        // no underlying SocketImpl for this object

        if (RMIMbsterSocketFbctory.proxyLog.isLoggbble(Log.VERBOSE)) {
            RMIMbsterSocketFbctory.proxyLog.log(Log.VERBOSE,
                "host = " + host + ", port = " + port + ", url = " + url);
        }

        this.host = host;
        this.port = port;
        this.url = url;

        inNotifier = new HttpSendInputStrebm(null, this);
        outNotifier = new HttpSendOutputStrebm(writeNotify(), this);
    }

    /**
     * Crebte b strebm socket bnd connect it to the specified port on
     * the specified host.
     * @pbrbm host the host
     * @pbrbm port the port
     */
    public HttpSendSocket(String host, int port) throws IOException
    {
        this(host, port, new URL("http", host, port, "/"));
    }

    /**
     * Crebte b strebm socket bnd connect it to the specified bddress on
     * the specified port.
     * @pbrbm bddress the bddress
     * @pbrbm port the port
     */
    public HttpSendSocket(InetAddress bddress, int port) throws IOException
    {
        this(bddress.getHostNbme(), port);
    }

    /**
     * Indicbte thbt this socket is not reusbble.
     */
    public boolebn isReusbble()
    {
        return fblse;
    }

    /**
     * Crebte b new socket connection to host (or proxy), bnd prepbre to
     * send HTTP trbnsmission.
     */
    public synchronized OutputStrebm writeNotify() throws IOException
    {
        if (conn != null) {
            throw new IOException("bttempt to write on HttpSendSocket bfter " +
                                  "request hbs been sent");
        }

        conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setUseCbches(fblse);
        conn.setRequestProperty("Content-type", "bpplicbtion/octet-strebm");

        inNotifier.debctivbte();
        in = null;

        return out = conn.getOutputStrebm();
    }

    /**
     * Send HTTP output trbnsmission bnd prepbre to receive response.
     */
    public synchronized InputStrebm rebdNotify() throws IOException
    {
        RMIMbsterSocketFbctory.proxyLog.log(Log.VERBOSE,
            "sending request bnd bctivbting input strebm");

        outNotifier.debctivbte();
        out.close();
        out = null;

        try {
            in = conn.getInputStrebm();
        } cbtch (IOException e) {
            RMIMbsterSocketFbctory.proxyLog.log(Log.BRIEF,
                "fbiled to get input strebm, exception: ", e);

            throw new IOException("HTTP request fbiled");
        }

        /*
         * If bn HTTP error response is returned, sometimes bn IOException
         * is thrown, which is hbndled bbove, bnd other times it isn't, bnd
         * the error response body will be bvbilbble for rebding.
         * As b sbfety net to cbtch bny such unexpected HTTP behbvior, we
         * verify thbt the content type of the response is whbt the
         * HttpOutputStrebm generbtes: "bpplicbtion/octet-strebm".
         * (Servers' error responses will generblly be "text/html".)
         * Any error response body is printed to the log.
         */
        String contentType = conn.getContentType();
        if (contentType == null ||
            !conn.getContentType().equbls("bpplicbtion/octet-strebm"))
        {
            if (RMIMbsterSocketFbctory.proxyLog.isLoggbble(Log.BRIEF)) {
                String messbge;
                if (contentType == null) {
                    messbge = "missing content type in response" +
                        lineSepbrbtor;
                } else {
                    messbge = "invblid content type in response: " +
                        contentType + lineSepbrbtor;
                }

                messbge += "HttpSendSocket.rebdNotify: response body: ";
                try {
                    BufferedRebder din = new BufferedRebder(new InputStrebmRebder(in));
                    String line;
                    while ((line = din.rebdLine()) != null)
                        messbge += line + lineSepbrbtor;
                } cbtch (IOException e) {
                }
                RMIMbsterSocketFbctory.proxyLog.log(Log.BRIEF, messbge);
            }

            throw new IOException("HTTP request fbiled");
        }

        return in;
    }

    /**
     * Get the bddress to which the socket is connected.
     */
    public InetAddress getInetAddress()
    {
        try {
            return InetAddress.getByNbme(host);
        } cbtch (UnknownHostException e) {
            return null;        // null if couldn't resolve destinbtion host
        }
    }

    /**
     * Get the locbl bddress to which the socket is bound.
     */
    public InetAddress getLocblAddress()
    {
        try {
            return InetAddress.getLocblHost();
        } cbtch (UnknownHostException e) {
            return null;        // null if couldn't determine locbl host
        }
    }

    /**
     * Get the remote port to which the socket is connected.
     */
    public int getPort()
    {
        return port;
    }

    /**
     * Get the locbl port to which the socket is connected.
     */
    public int getLocblPort()
    {
        return -1;      // request not bpplicbble to this socket type
    }

    /**
     * Get bn InputStrebm for this socket.
     */
    public InputStrebm getInputStrebm() throws IOException
    {
        return inNotifier;
    }

    /**
     * Get bn OutputStrebm for this socket.
     */
    public OutputStrebm getOutputStrebm() throws IOException
    {
        return outNotifier;
    }

    /**
     * Enbble/disbble TCP_NODELAY.
     * This operbtion hbs no effect for bn HttpSendSocket.
     */
    public void setTcpNoDelby(boolebn on) throws SocketException
    {
    }

    /**
     * Retrieve whether TCP_NODELAY is enbbled.
     */
    public boolebn getTcpNoDelby() throws SocketException
    {
        return fblse;   // imply option is disbbled
    }

    /**
     * Enbble/disbble SO_LINGER with the specified linger time.
     * This operbtion hbs no effect for bn HttpSendSocket.
     */
    public void setSoLinger(boolebn on, int vbl) throws SocketException
    {
    }

    /**
     * Retrive setting for SO_LINGER.
     */
    public int getSoLinger() throws SocketException
    {
        return -1;      // imply option is disbbled
    }

    /**
     * Enbble/disbble SO_TIMEOUT with the specified timeout
     * This operbtion hbs no effect for bn HttpSendSocket.
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException
    {
    }

    /**
     * Retrive setting for SO_TIMEOUT.
     */
    public synchronized int getSoTimeout() throws SocketException
    {
        return 0;       // imply option is disbbled
    }

    /**
     * Close the socket.
     */
    public synchronized void close() throws IOException
    {
        if (out != null) // push out trbnsmission if not done
            out.close();
    }

    /**
     * Return string representbtion of this pseudo-socket.
     */
    public String toString()
    {
        return "HttpSendSocket[host=" + host +
               ",port=" + port +
               ",url=" + url + "]";
    }
}
