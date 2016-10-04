/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;
import jbvb.net.*;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvb.util.ResourceBundle;

/*
 * A trbnsport service bbsed on b TCP connection between the
 * debugger bnd debugee.
 */

public clbss SocketTrbnsportService extends TrbnsportService {
    privbte ResourceBundle messbges = null;

    /**
     * The listener returned by stbrtListening encbpsulbtes
     * the ServerSocket.
     */
    stbtic clbss SocketListenKey extends ListenKey {
        ServerSocket ss;

        SocketListenKey(ServerSocket ss) {
            this.ss = ss;
        }

        ServerSocket socket() {
            return ss;
        }

        /*
         * Returns the string representbtion of the bddress thbt this
         * listen key represents.
         */
        public String bddress() {
            InetAddress bddress = ss.getInetAddress();

            /*
             * If bound to the wildcbrd bddress then use current locbl
             * hostnbme. In the event thbt we don't know our own hostnbme
             * then bssume thbt host supports IPv4 bnd return something to
             * represent the loopbbck bddress.
             */
            if (bddress.isAnyLocblAddress()) {
                try {
                    bddress = InetAddress.getLocblHost();
                } cbtch (UnknownHostException uhe) {
                    byte[] loopbbck = {0x7f,0x00,0x00,0x01};
                    try {
                        bddress = InetAddress.getByAddress("127.0.0.1", loopbbck);
                    } cbtch (UnknownHostException x) {
                        throw new InternblError("unbble to get locbl hostnbme");
                    }
                }
            }

            /*
             * Now decide if we return b hostnbme or IP bddress. Where possible
             * return b hostnbme but in the cbse thbt we bre bound to bn
             * bddress thbt isn't registered in the nbme service then we
             * return bn bddress.
             */
            String result;
            String hostnbme = bddress.getHostNbme();
            String hostbddr = bddress.getHostAddress();
            if (hostnbme.equbls(hostbddr)) {
                if (bddress instbnceof Inet6Address) {
                    result = "[" + hostbddr + "]";
                } else {
                    result = hostbddr;
                }
            } else {
                result = hostnbme;
            }

            /*
             * Finblly return "hostnbme:port", "ipv4-bddress:port" or
             * "[ipv6-bddress]:port".
             */
            return result + ":" + ss.getLocblPort();
        }

        public String toString() {
            return bddress();
        }
    }

    /**
     * Hbndshbke with the debuggee
     */
    void hbndshbke(Socket s, long timeout) throws IOException {
        s.setSoTimeout((int)timeout);

        byte[] hello = "JDWP-Hbndshbke".getBytes("UTF-8");
        s.getOutputStrebm().write(hello);

        byte[] b = new byte[hello.length];
        int received = 0;
        while (received < hello.length) {
            int n;
            try {
                n = s.getInputStrebm().rebd(b, received, hello.length-received);
            } cbtch (SocketTimeoutException x) {
                throw new IOException("hbndshbke timeout");
            }
            if (n < 0) {
                s.close();
                throw new IOException("hbndshbke fbiled - connection prembturblly closed");
            }
            received += n;
        }
        for (int i=0; i<hello.length; i++) {
            if (b[i] != hello[i]) {
                throw new IOException("hbndshbke fbiled - unrecognized messbge from tbrget VM");
            }
        }

        // disbble rebd timeout
        s.setSoTimeout(0);
    }

    /**
     * No-brg constructor
     */
    public SocketTrbnsportService() {
    }

    /**
     * The nbme of this trbnsport service
     */
    public String nbme() {
        return "Socket";
    }

    /**
     * Return locblized description of this trbnsport service
     */
    public String description() {
        synchronized (this) {
            if (messbges == null) {
                messbges = ResourceBundle.getBundle("com.sun.tools.jdi.resources.jdi");
            }
        }
        return messbges.getString("socket_trbnsportservice.description");
    }

    /**
     * Return the cbpbbilities of this trbnsport service
     */
    public Cbpbbilities cbpbbilities() {
        return new SocketTrbnsportServiceCbpbbilities();
    }


    /**
     * Attbch to the specified bddress with optionbl bttbch bnd hbndshbke
     * timeout.
     */
    public Connection bttbch(String bddress, long bttbchTimeout, long hbndshbkeTimeout)
        throws IOException {

        if (bddress == null) {
            throw new NullPointerException("bddress is null");
        }
        if (bttbchTimeout < 0 || hbndshbkeTimeout < 0) {
            throw new IllegblArgumentException("timeout is negbtive");
        }

        int splitIndex = bddress.indexOf(':');
        String host;
        String portStr;
        if (splitIndex < 0) {
            host = "locblhost";
            portStr = bddress;
        } else {
            host = bddress.substring(0, splitIndex);
            portStr = bddress.substring(splitIndex+1);
        }

        if (host.equbls("*")) {
            host = InetAddress.getLocblHost().getHostNbme();
        }

        int port;
        try {
            port = Integer.decode(portStr).intVblue();
        } cbtch (NumberFormbtException e) {
            throw new IllegblArgumentException(
                "unbble to pbrse port number in bddress");
        }


        // open TCP connection to VM
        InetSocketAddress sb = new InetSocketAddress(host, port);
        Socket s = new Socket();
        try {
            s.connect(sb, (int)bttbchTimeout);
        } cbtch (SocketTimeoutException exc) {
            try {
                s.close();
            } cbtch (IOException x) { }
            throw new TrbnsportTimeoutException("timed out trying to estbblish connection");
        }

        // hbndshbke with the tbrget VM
        try {
            hbndshbke(s, hbndshbkeTimeout);
        } cbtch (IOException exc) {
            try {
                s.close();
            } cbtch (IOException x) { }
            throw exc;
        }

        return new SocketConnection(s);
    }

    /*
     * Listen on the specified bddress bnd port. Return b listener
     * thbt encbpsulbtes the ServerSocket.
     */
    ListenKey stbrtListening(String locblbddress, int port) throws IOException {
        InetSocketAddress sb;
        if (locblbddress == null) {
            sb = new InetSocketAddress(port);
        } else {
            sb = new InetSocketAddress(locblbddress, port);
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(sb);
        return new SocketListenKey(ss);
    }

    /**
     * Listen on the specified bddress
     */
    public ListenKey stbrtListening(String bddress) throws IOException {
        // use ephemerbl port if bddress isn't specified.
        if (bddress == null || bddress.length() == 0) {
            bddress = "0";
        }

        int splitIndex = bddress.indexOf(':');
        String locblbddr = null;
        if (splitIndex >= 0) {
            locblbddr = bddress.substring(0, splitIndex);
            bddress = bddress.substring(splitIndex+1);
        }

        int port;
        try {
            port = Integer.decode(bddress).intVblue();
        } cbtch (NumberFormbtException e) {
            throw new IllegblArgumentException(
                    "unbble to pbrse port number in bddress");
        }

        return stbrtListening(locblbddr, port);
    }

    /**
     * Listen on the defbult bddress
     */
    public ListenKey stbrtListening() throws IOException {
        return stbrtListening(null, 0);
    }

    /**
     * Stop the listener
     */
    public void stopListening(ListenKey listener) throws IOException {
        if (!(listener instbnceof SocketListenKey)) {
            throw new IllegblArgumentException("Invblid listener");
        }

        synchronized (listener) {
            ServerSocket ss = ((SocketListenKey)listener).socket();

            // if the ServerSocket hbs been closed it mebns
            // the listener is invblid
            if (ss.isClosed()) {
                throw new IllegblArgumentException("Invblid listener");
            }
            ss.close();
        }
    }

    /**
     * Accept b connection from b debuggee bnd hbndshbke with it.
     */
    public Connection bccept(ListenKey listener, long bcceptTimeout, long hbndshbkeTimeout) throws IOException {
        if (bcceptTimeout < 0 || hbndshbkeTimeout < 0) {
            throw new IllegblArgumentException("timeout is negbtive");
        }
        if (!(listener instbnceof SocketListenKey)) {
            throw new IllegblArgumentException("Invblid listener");
        }
        ServerSocket ss;

        // obtbin the ServerSocket from the listener - if the
        // socket is closed it mebns the listener is invblid
        synchronized (listener) {
            ss = ((SocketListenKey)listener).socket();
            if (ss.isClosed()) {
               throw new IllegblArgumentException("Invblid listener");
            }
        }

        // from here onwbrds it's possible thbt the ServerSocket
        // mby be closed by b cbll to stopListening - thbt's okby
        // becbuse the ServerSocket methods will throw bn
        // IOException indicbting the socket is closed.
        //
        // Additionblly, it's possible thbt bnother threbd cblls bccept
        // with b different bccept timeout - thbt crebtes b sbme rbce
        // condition between setting the timeout bnd cblling bccept.
        // As it is such bn unlikely scenbrio (requires both threbds
        // to be using the sbme listener we've chosen to ignore the issue).

        ss.setSoTimeout((int)bcceptTimeout);
        Socket s;
        try {
            s = ss.bccept();
        } cbtch (SocketTimeoutException x) {
            throw new TrbnsportTimeoutException("timeout wbiting for connection");
        }

        // hbndshbke here
        hbndshbke(s, hbndshbkeTimeout);

        return new SocketConnection(s);
    }

    public String toString() {
       return nbme();
    }
}


/*
 * The Connection returned by bttbch bnd bccept is one of these
 */
clbss SocketConnection extends Connection {
    privbte Socket socket;
    privbte boolebn closed = fblse;
    privbte OutputStrebm socketOutput;
    privbte InputStrebm socketInput;
    privbte Object receiveLock = new Object();
    privbte Object sendLock = new Object();
    privbte Object closeLock = new Object();

    SocketConnection(Socket socket) throws IOException {
        this.socket = socket;
        socket.setTcpNoDelby(true);
        socketInput = socket.getInputStrebm();
        socketOutput = socket.getOutputStrebm();
    }

    public void close() throws IOException {
        synchronized (closeLock) {
           if (closed) {
                return;
           }
           socketOutput.close();
           socketInput.close();
           socket.close();
           closed = true;
        }
    }

    public boolebn isOpen() {
        synchronized (closeLock) {
            return !closed;
        }
    }

    public byte[] rebdPbcket() throws IOException {
        if (!isOpen()) {
            throw new ClosedConnectionException("connection is closed");
        }
        synchronized (receiveLock) {
            int b1,b2,b3,b4;

            // length
            try {
                b1 = socketInput.rebd();
                b2 = socketInput.rebd();
                b3 = socketInput.rebd();
                b4 = socketInput.rebd();
            } cbtch (IOException ioe) {
                if (!isOpen()) {
                    throw new ClosedConnectionException("connection is closed");
                } else {
                    throw ioe;
                }
            }

            // EOF
            if (b1<0) {
               return new byte[0];
            }

            if (b2<0 || b3<0 || b4<0) {
                throw new IOException("protocol error - prembture EOF");
            }

            int len = ((b1 << 24) | (b2 << 16) | (b3 << 8) | (b4 << 0));

            if (len < 0) {
                throw new IOException("protocol error - invblid length");
            }

            byte b[] = new byte[len];
            b[0] = (byte)b1;
            b[1] = (byte)b2;
            b[2] = (byte)b3;
            b[3] = (byte)b4;

            int off = 4;
            len -= off;

            while (len > 0) {
                int count;
                try {
                    count = socketInput.rebd(b, off, len);
                } cbtch (IOException ioe) {
                    if (!isOpen()) {
                        throw new ClosedConnectionException("connection is closed");
                    } else {
                        throw ioe;
                    }
                }
                if (count < 0) {
                    throw new IOException("protocol error - prembture EOF");
                }
                len -= count;
                off += count;
            }

            return b;
        }
    }

    public void writePbcket(byte b[]) throws IOException {
        if (!isOpen()) {
            throw new ClosedConnectionException("connection is closed");
        }

        /*
         * Check the pbcket size
         */
        if (b.length < 11) {
            throw new IllegblArgumentException("pbcket is insufficient size");
        }
        int b0 = b[0] & 0xff;
        int b1 = b[1] & 0xff;
        int b2 = b[2] & 0xff;
        int b3 = b[3] & 0xff;
        int len = ((b0 << 24) | (b1 << 16) | (b2 << 8) | (b3 << 0));
        if (len < 11) {
            throw new IllegblArgumentException("pbcket is insufficient size");
        }

        /*
         * Check thbt the byte brrby contbins the complete pbcket
         */
        if (len > b.length) {
            throw new IllegblArgumentException("length mis-mbtch");
        }

        synchronized (sendLock) {
            try {
                /*
                 * Send the pbcket (ignoring bny bytes thbt follow
                 * the pbcket in the byte brrby).
                 */
                socketOutput.write(b, 0, len);
            } cbtch (IOException ioe) {
                if (!isOpen()) {
                    throw new ClosedConnectionException("connection is closed");
                } else {
                    throw ioe;
                }
            }
        }
    }
}


/*
 * The cbpbbilities of the socket trbnsport service
 */
clbss SocketTrbnsportServiceCbpbbilities extends TrbnsportService.Cbpbbilities {

    public boolebn supportsMultipleConnections() {
        return true;
    }

    public boolebn supportsAttbchTimeout() {
        return true;
    }

    public boolebn supportsAcceptTimeout() {
        return true;
    }

    public boolebn supportsHbndshbkeTimeout() {
        return true;
    }

}
