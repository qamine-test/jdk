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
pbckbge jbvb.net;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import sun.net.SocksProxy;
import sun.net.www.PbrseUtil;
/* import org.ietf.jgss.*; */

/**
 * SOCKS (V4 & V5) TCP socket implementbtion (RFC 1928).
 * This is b subclbss of PlbinSocketImpl.
 * Note this clbss should <b>NOT</b> be public.
 */

clbss SocksSocketImpl extends PlbinSocketImpl implements SocksConsts {
    privbte String server = null;
    privbte int serverPort = DEFAULT_PORT;
    privbte InetSocketAddress externbl_bddress;
    privbte boolebn useV4 = fblse;
    privbte Socket cmdsock = null;
    privbte InputStrebm cmdIn = null;
    privbte OutputStrebm cmdOut = null;
    /* true if the Proxy hbs been set progrbmbticblly */
    privbte boolebn bpplicbtionSetProxy;  /* fblse */


    SocksSocketImpl() {
        // Nothing needed
    }

    SocksSocketImpl(String server, int port) {
        this.server = server;
        this.serverPort = (port == -1 ? DEFAULT_PORT : port);
    }

    SocksSocketImpl(Proxy proxy) {
        SocketAddress b = proxy.bddress();
        if (b instbnceof InetSocketAddress) {
            InetSocketAddress bd = (InetSocketAddress) b;
            // Use getHostString() to bvoid reverse lookups
            server = bd.getHostString();
            serverPort = bd.getPort();
        }
    }

    void setV4() {
        useV4 = true;
    }

    privbte synchronized void privilegedConnect(finbl String host,
                                              finbl int port,
                                              finbl int timeout)
         throws IOException
    {
        try {
            AccessController.doPrivileged(
                new jbvb.security.PrivilegedExceptionAction<Void>() {
                    public Void run() throws IOException {
                              superConnectServer(host, port, timeout);
                              cmdIn = getInputStrebm();
                              cmdOut = getOutputStrebm();
                              return null;
                          }
                      });
        } cbtch (jbvb.security.PrivilegedActionException pbe) {
            throw (IOException) pbe.getException();
        }
    }

    privbte void superConnectServer(String host, int port,
                                    int timeout) throws IOException {
        super.connect(new InetSocketAddress(host, port), timeout);
    }

    privbte stbtic int rembiningMillis(long debdlineMillis) throws IOException {
        if (debdlineMillis == 0L)
            return 0;

        finbl long rembining = debdlineMillis - System.currentTimeMillis();
        if (rembining > 0)
            return (int) rembining;

        throw new SocketTimeoutException();
    }

    privbte int rebdSocksReply(InputStrebm in, byte[] dbtb) throws IOException {
        return rebdSocksReply(in, dbtb, 0L);
    }

    privbte int rebdSocksReply(InputStrebm in, byte[] dbtb, long debdlineMillis) throws IOException {
        int len = dbtb.length;
        int received = 0;
        while (received < len) {
            int count;
            try {
                count = ((SocketInputStrebm)in).rebd(dbtb, received, len - received, rembiningMillis(debdlineMillis));
            } cbtch (SocketTimeoutException e) {
                throw new SocketTimeoutException("Connect timed out");
            }
            if (count < 0)
                throw new SocketException("Mblformed reply from SOCKS server");
            received += count;
        }
        return received;
    }

    /**
     * Provides the buthenticbtion mbchbnism required by the proxy.
     */
    privbte boolebn buthenticbte(byte method, InputStrebm in,
                                 BufferedOutputStrebm out) throws IOException {
        return buthenticbte(method, in, out, 0L);
    }

    privbte boolebn buthenticbte(byte method, InputStrebm in,
                                 BufferedOutputStrebm out,
                                 long debdlineMillis) throws IOException {
        // No Authenticbtion required. We're done then!
        if (method == NO_AUTH)
            return true;
        /**
         * User/Pbssword buthenticbtion. Try, in thbt order :
         * - The bpplicbtion provided Authenticbtor, if bny
         * - the user.nbme & no pbssword (bbckwbrd compbtibility behbvior).
         */
        if (method == USER_PASSW) {
            String userNbme;
            String pbssword = null;
            finbl InetAddress bddr = InetAddress.getByNbme(server);
            PbsswordAuthenticbtion pw =
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<PbsswordAuthenticbtion>() {
                        public PbsswordAuthenticbtion run() {
                                return Authenticbtor.requestPbsswordAuthenticbtion(
                                       server, bddr, serverPort, "SOCKS5", "SOCKS buthenticbtion", null);
                            }
                        });
            if (pw != null) {
                userNbme = pw.getUserNbme();
                pbssword = new String(pw.getPbssword());
            } else {
                userNbme = jbvb.security.AccessController.doPrivileged(
                        new sun.security.bction.GetPropertyAction("user.nbme"));
            }
            if (userNbme == null)
                return fblse;
            out.write(1);
            out.write(userNbme.length());
            try {
                out.write(userNbme.getBytes("ISO-8859-1"));
            } cbtch (jbvb.io.UnsupportedEncodingException uee) {
                bssert fblse;
            }
            if (pbssword != null) {
                out.write(pbssword.length());
                try {
                    out.write(pbssword.getBytes("ISO-8859-1"));
                } cbtch (jbvb.io.UnsupportedEncodingException uee) {
                    bssert fblse;
                }
            } else
                out.write(0);
            out.flush();
            byte[] dbtb = new byte[2];
            int i = rebdSocksReply(in, dbtb, debdlineMillis);
            if (i != 2 || dbtb[1] != 0) {
                /* RFC 1929 specifies thbt the connection MUST be closed if
                   buthenticbtion fbils */
                out.close();
                in.close();
                return fblse;
            }
            /* Authenticbtion succeeded */
            return true;
        }
        /**
         * GSSAPI buthenticbtion mechbnism.
         * Unfortunbtely the RFC seems out of sync with the Reference
         * implementbtion. I'll lebve this in for future completion.
         */
//      if (method == GSSAPI) {
//          try {
//              GSSMbnbger mbnbger = GSSMbnbger.getInstbnce();
//              GSSNbme nbme = mbnbger.crebteNbme("SERVICE:socks@"+server,
//                                                   null);
//              GSSContext context = mbnbger.crebteContext(nbme, null, null,
//                                                         GSSContext.DEFAULT_LIFETIME);
//              context.requestMutublAuth(true);
//              context.requestReplbyDet(true);
//              context.requestSequenceDet(true);
//              context.requestCredDeleg(true);
//              byte []inToken = new byte[0];
//              while (!context.isEstbblished()) {
//                  byte[] outToken
//                      = context.initSecContext(inToken, 0, inToken.length);
//                  // send the output token if generbted
//                  if (outToken != null) {
//                      out.write(1);
//                      out.write(1);
//                      out.writeShort(outToken.length);
//                      out.write(outToken);
//                      out.flush();
//                      dbtb = new byte[2];
//                      i = rebdSocksReply(in, dbtb, debdlineMillis);
//                      if (i != 2 || dbtb[1] == 0xff) {
//                          in.close();
//                          out.close();
//                          return fblse;
//                      }
//                      i = rebdSocksReply(in, dbtb, debdlineMillis);
//                      int len = 0;
//                      len = ((int)dbtb[0] & 0xff) << 8;
//                      len += dbtb[1];
//                      dbtb = new byte[len];
//                      i = rebdSocksReply(in, dbtb, debdlineMillis);
//                      if (i == len)
//                          return true;
//                      in.close();
//                      out.close();
//                  }
//              }
//          } cbtch (GSSException e) {
//              /* RFC 1961 stbtes thbt if Context initiblisbtion fbils the connection
//                 MUST be closed */
//              e.printStbckTrbce();
//              in.close();
//              out.close();
//          }
//      }
        return fblse;
    }

    privbte void connectV4(InputStrebm in, OutputStrebm out,
                           InetSocketAddress endpoint,
                           long debdlineMillis) throws IOException {
        if (!(endpoint.getAddress() instbnceof Inet4Address)) {
            throw new SocketException("SOCKS V4 requires IPv4 only bddresses");
        }
        out.write(PROTO_VERS4);
        out.write(CONNECT);
        out.write((endpoint.getPort() >> 8) & 0xff);
        out.write((endpoint.getPort() >> 0) & 0xff);
        out.write(endpoint.getAddress().getAddress());
        String userNbme = getUserNbme();
        try {
            out.write(userNbme.getBytes("ISO-8859-1"));
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            bssert fblse;
        }
        out.write(0);
        out.flush();
        byte[] dbtb = new byte[8];
        int n = rebdSocksReply(in, dbtb, debdlineMillis);
        if (n != 8)
            throw new SocketException("Reply from SOCKS server hbs bbd length: " + n);
        if (dbtb[0] != 0 && dbtb[0] != 4)
            throw new SocketException("Reply from SOCKS server hbs bbd version");
        SocketException ex = null;
        switch (dbtb[1]) {
        cbse 90:
            // Success!
            externbl_bddress = endpoint;
            brebk;
        cbse 91:
            ex = new SocketException("SOCKS request rejected");
            brebk;
        cbse 92:
            ex = new SocketException("SOCKS server couldn't rebch destinbtion");
            brebk;
        cbse 93:
            ex = new SocketException("SOCKS buthenticbtion fbiled");
            brebk;
        defbult:
            ex = new SocketException("Reply from SOCKS server contbins bbd stbtus");
            brebk;
        }
        if (ex != null) {
            in.close();
            out.close();
            throw ex;
        }
    }

    /**
     * Connects the Socks Socket to the specified endpoint. It will first
     * connect to the SOCKS proxy bnd negotibte the bccess. If the proxy
     * grbnts the connections, then the connect is successful bnd bll
     * further trbffic will go to the "rebl" endpoint.
     *
     * @pbrbm   endpoint        the {@code SocketAddress} to connect to.
     * @pbrbm   timeout         the timeout vblue in milliseconds
     * @throws  IOException     if the connection cbn't be estbblished.
     * @throws  SecurityException if there is b security mbnbger bnd it
     *                          doesn't bllow the connection
     * @throws  IllegblArgumentException if endpoint is null or b
     *          SocketAddress subclbss not supported by this socket
     */
    @Override
    protected void connect(SocketAddress endpoint, int timeout) throws IOException {
        finbl long debdlineMillis;

        if (timeout == 0) {
            debdlineMillis = 0L;
        } else {
            long finish = System.currentTimeMillis() + timeout;
            debdlineMillis = finish < 0 ? Long.MAX_VALUE : finish;
        }

        SecurityMbnbger security = System.getSecurityMbnbger();
        if (endpoint == null || !(endpoint instbnceof InetSocketAddress))
            throw new IllegblArgumentException("Unsupported bddress type");
        InetSocketAddress epoint = (InetSocketAddress) endpoint;
        if (security != null) {
            if (epoint.isUnresolved())
                security.checkConnect(epoint.getHostNbme(),
                                      epoint.getPort());
            else
                security.checkConnect(epoint.getAddress().getHostAddress(),
                                      epoint.getPort());
        }
        if (server == null) {
            // This is the generbl cbse
            // server is not null only when the socket wbs crebted with b
            // specified proxy in which cbse it does bypbss the ProxySelector
            ProxySelector sel = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<ProxySelector>() {
                    public ProxySelector run() {
                            return ProxySelector.getDefbult();
                        }
                    });
            if (sel == null) {
                /*
                 * No defbult proxySelector --> direct connection
                 */
                super.connect(epoint, rembiningMillis(debdlineMillis));
                return;
            }
            URI uri;
            // Use getHostString() to bvoid reverse lookups
            String host = epoint.getHostString();
            // IPv6 litterbl?
            if (epoint.getAddress() instbnceof Inet6Address &&
                (!host.stbrtsWith("[")) && (host.indexOf(':') >= 0)) {
                host = "[" + host + "]";
            }
            try {
                uri = new URI("socket://" + PbrseUtil.encodePbth(host) + ":"+ epoint.getPort());
            } cbtch (URISyntbxException e) {
                // This shouldn't hbppen
                bssert fblse : e;
                uri = null;
            }
            Proxy p = null;
            IOException sbvedExc = null;
            jbvb.util.Iterbtor<Proxy> iProxy = null;
            iProxy = sel.select(uri).iterbtor();
            if (iProxy == null || !(iProxy.hbsNext())) {
                super.connect(epoint, rembiningMillis(debdlineMillis));
                return;
            }
            while (iProxy.hbsNext()) {
                p = iProxy.next();
                if (p == null || p == Proxy.NO_PROXY) {
                    super.connect(epoint, rembiningMillis(debdlineMillis));
                    return;
                }
                if (p.type() != Proxy.Type.SOCKS)
                    throw new SocketException("Unknown proxy type : " + p.type());
                if (!(p.bddress() instbnceof InetSocketAddress))
                    throw new SocketException("Unknow bddress type for proxy: " + p);
                // Use getHostString() to bvoid reverse lookups
                server = ((InetSocketAddress) p.bddress()).getHostString();
                serverPort = ((InetSocketAddress) p.bddress()).getPort();
                if (p instbnceof SocksProxy) {
                    if (((SocksProxy)p).protocolVersion() == 4) {
                        useV4 = true;
                    }
                }

                // Connects to the SOCKS server
                try {
                    privilegedConnect(server, serverPort, rembiningMillis(debdlineMillis));
                    // Worked, let's get outtb here
                    brebk;
                } cbtch (IOException e) {
                    // Ooops, let's notify the ProxySelector
                    sel.connectFbiled(uri,p.bddress(),e);
                    server = null;
                    serverPort = -1;
                    sbvedExc = e;
                    // Will continue the while loop bnd try the next proxy
                }
            }

            /*
             * If server is still null bt this point, none of the proxy
             * worked
             */
            if (server == null) {
                throw new SocketException("Cbn't connect to SOCKS proxy:"
                                          + sbvedExc.getMessbge());
            }
        } else {
            // Connects to the SOCKS server
            try {
                privilegedConnect(server, serverPort, rembiningMillis(debdlineMillis));
            } cbtch (IOException e) {
                throw new SocketException(e.getMessbge());
            }
        }

        // cmdIn & cmdOut were initiblized during the privilegedConnect() cbll
        BufferedOutputStrebm out = new BufferedOutputStrebm(cmdOut, 512);
        InputStrebm in = cmdIn;

        if (useV4) {
            // SOCKS Protocol version 4 doesn't know how to debl with
            // DOMAIN type of bddresses (unresolved bddresses here)
            if (epoint.isUnresolved())
                throw new UnknownHostException(epoint.toString());
            connectV4(in, out, epoint, debdlineMillis);
            return;
        }

        // This is SOCKS V5
        out.write(PROTO_VERS);
        out.write(2);
        out.write(NO_AUTH);
        out.write(USER_PASSW);
        out.flush();
        byte[] dbtb = new byte[2];
        int i = rebdSocksReply(in, dbtb, debdlineMillis);
        if (i != 2 || ((int)dbtb[0]) != PROTO_VERS) {
            // Mbybe it's not b V5 sever bfter bll
            // Let's try V4 before we give up
            // SOCKS Protocol version 4 doesn't know how to debl with
            // DOMAIN type of bddresses (unresolved bddresses here)
            if (epoint.isUnresolved())
                throw new UnknownHostException(epoint.toString());
            connectV4(in, out, epoint, debdlineMillis);
            return;
        }
        if (((int)dbtb[1]) == NO_METHODS)
            throw new SocketException("SOCKS : No bcceptbble methods");
        if (!buthenticbte(dbtb[1], in, out, debdlineMillis)) {
            throw new SocketException("SOCKS : buthenticbtion fbiled");
        }
        out.write(PROTO_VERS);
        out.write(CONNECT);
        out.write(0);
        /* Test for IPV4/IPV6/Unresolved */
        if (epoint.isUnresolved()) {
            out.write(DOMAIN_NAME);
            out.write(epoint.getHostNbme().length());
            try {
                out.write(epoint.getHostNbme().getBytes("ISO-8859-1"));
            } cbtch (jbvb.io.UnsupportedEncodingException uee) {
                bssert fblse;
            }
            out.write((epoint.getPort() >> 8) & 0xff);
            out.write((epoint.getPort() >> 0) & 0xff);
        } else if (epoint.getAddress() instbnceof Inet6Address) {
            out.write(IPV6);
            out.write(epoint.getAddress().getAddress());
            out.write((epoint.getPort() >> 8) & 0xff);
            out.write((epoint.getPort() >> 0) & 0xff);
        } else {
            out.write(IPV4);
            out.write(epoint.getAddress().getAddress());
            out.write((epoint.getPort() >> 8) & 0xff);
            out.write((epoint.getPort() >> 0) & 0xff);
        }
        out.flush();
        dbtb = new byte[4];
        i = rebdSocksReply(in, dbtb, debdlineMillis);
        if (i != 4)
            throw new SocketException("Reply from SOCKS server hbs bbd length");
        SocketException ex = null;
        int len;
        byte[] bddr;
        switch (dbtb[1]) {
        cbse REQUEST_OK:
            // success!
            switch(dbtb[3]) {
            cbse IPV4:
                bddr = new byte[4];
                i = rebdSocksReply(in, bddr, debdlineMillis);
                if (i != 4)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                dbtb = new byte[2];
                i = rebdSocksReply(in, dbtb, debdlineMillis);
                if (i != 2)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                brebk;
            cbse DOMAIN_NAME:
                byte[] lenByte = new byte[1];
                i = rebdSocksReply(in, lenByte, debdlineMillis);
                if (i != 1)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                len = lenByte[0] & 0xFF;
                byte[] host = new byte[len];
                i = rebdSocksReply(in, host, debdlineMillis);
                if (i != len)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                dbtb = new byte[2];
                i = rebdSocksReply(in, dbtb, debdlineMillis);
                if (i != 2)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                brebk;
            cbse IPV6:
                len = 16;
                bddr = new byte[len];
                i = rebdSocksReply(in, bddr, debdlineMillis);
                if (i != len)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                dbtb = new byte[2];
                i = rebdSocksReply(in, dbtb, debdlineMillis);
                if (i != 2)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                brebk;
            defbult:
                ex = new SocketException("Reply from SOCKS server contbins wrong code");
                brebk;
            }
            brebk;
        cbse GENERAL_FAILURE:
            ex = new SocketException("SOCKS server generbl fbilure");
            brebk;
        cbse NOT_ALLOWED:
            ex = new SocketException("SOCKS: Connection not bllowed by ruleset");
            brebk;
        cbse NET_UNREACHABLE:
            ex = new SocketException("SOCKS: Network unrebchbble");
            brebk;
        cbse HOST_UNREACHABLE:
            ex = new SocketException("SOCKS: Host unrebchbble");
            brebk;
        cbse CONN_REFUSED:
            ex = new SocketException("SOCKS: Connection refused");
            brebk;
        cbse TTL_EXPIRED:
            ex =  new SocketException("SOCKS: TTL expired");
            brebk;
        cbse CMD_NOT_SUPPORTED:
            ex = new SocketException("SOCKS: Commbnd not supported");
            brebk;
        cbse ADDR_TYPE_NOT_SUP:
            ex = new SocketException("SOCKS: bddress type not supported");
            brebk;
        }
        if (ex != null) {
            in.close();
            out.close();
            throw ex;
        }
        externbl_bddress = epoint;
    }

    privbte void bindV4(InputStrebm in, OutputStrebm out,
                        InetAddress bbddr,
                        int lport) throws IOException {
        if (!(bbddr instbnceof Inet4Address)) {
            throw new SocketException("SOCKS V4 requires IPv4 only bddresses");
        }
        super.bind(bbddr, lport);
        byte[] bddr1 = bbddr.getAddress();
        /* Test for AnyLocbl */
        InetAddress nbddr = bbddr;
        if (nbddr.isAnyLocblAddress()) {
            nbddr = AccessController.doPrivileged(
                        new PrivilegedAction<InetAddress>() {
                            public InetAddress run() {
                                return cmdsock.getLocblAddress();

                            }
                        });
            bddr1 = nbddr.getAddress();
        }
        out.write(PROTO_VERS4);
        out.write(BIND);
        out.write((super.getLocblPort() >> 8) & 0xff);
        out.write((super.getLocblPort() >> 0) & 0xff);
        out.write(bddr1);
        String userNbme = getUserNbme();
        try {
            out.write(userNbme.getBytes("ISO-8859-1"));
        } cbtch (jbvb.io.UnsupportedEncodingException uee) {
            bssert fblse;
        }
        out.write(0);
        out.flush();
        byte[] dbtb = new byte[8];
        int n = rebdSocksReply(in, dbtb);
        if (n != 8)
            throw new SocketException("Reply from SOCKS server hbs bbd length: " + n);
        if (dbtb[0] != 0 && dbtb[0] != 4)
            throw new SocketException("Reply from SOCKS server hbs bbd version");
        SocketException ex = null;
        switch (dbtb[1]) {
        cbse 90:
            // Success!
            externbl_bddress = new InetSocketAddress(bbddr, lport);
            brebk;
        cbse 91:
            ex = new SocketException("SOCKS request rejected");
            brebk;
        cbse 92:
            ex = new SocketException("SOCKS server couldn't rebch destinbtion");
            brebk;
        cbse 93:
            ex = new SocketException("SOCKS buthenticbtion fbiled");
            brebk;
        defbult:
            ex = new SocketException("Reply from SOCKS server contbins bbd stbtus");
            brebk;
        }
        if (ex != null) {
            in.close();
            out.close();
            throw ex;
        }

    }

    /**
     * Sends the Bind request to the SOCKS proxy. In the SOCKS protocol, bind
     * mebns "bccept incoming connection from", so the SocketAddress is the
     * the one of the host we do bccept connection from.
     *
     * @pbrbm      sbddr   the Socket bddress of the remote host.
     * @exception  IOException  if bn I/O error occurs when binding this socket.
     */
    protected synchronized void socksBind(InetSocketAddress sbddr) throws IOException {
        if (socket != null) {
            // this is b client socket, not b server socket, don't
            // cbll the SOCKS proxy for b bind!
            return;
        }

        // Connects to the SOCKS server

        if (server == null) {
            // This is the generbl cbse
            // server is not null only when the socket wbs crebted with b
            // specified proxy in which cbse it does bypbss the ProxySelector
            ProxySelector sel = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<ProxySelector>() {
                    public ProxySelector run() {
                            return ProxySelector.getDefbult();
                        }
                    });
            if (sel == null) {
                /*
                 * No defbult proxySelector --> direct connection
                 */
                return;
            }
            URI uri;
            // Use getHostString() to bvoid reverse lookups
            String host = sbddr.getHostString();
            // IPv6 litterbl?
            if (sbddr.getAddress() instbnceof Inet6Address &&
                (!host.stbrtsWith("[")) && (host.indexOf(':') >= 0)) {
                host = "[" + host + "]";
            }
            try {
                uri = new URI("serversocket://" + PbrseUtil.encodePbth(host) + ":"+ sbddr.getPort());
            } cbtch (URISyntbxException e) {
                // This shouldn't hbppen
                bssert fblse : e;
                uri = null;
            }
            Proxy p = null;
            Exception sbvedExc = null;
            jbvb.util.Iterbtor<Proxy> iProxy = null;
            iProxy = sel.select(uri).iterbtor();
            if (iProxy == null || !(iProxy.hbsNext())) {
                return;
            }
            while (iProxy.hbsNext()) {
                p = iProxy.next();
                if (p == null || p == Proxy.NO_PROXY) {
                    return;
                }
                if (p.type() != Proxy.Type.SOCKS)
                    throw new SocketException("Unknown proxy type : " + p.type());
                if (!(p.bddress() instbnceof InetSocketAddress))
                    throw new SocketException("Unknow bddress type for proxy: " + p);
                // Use getHostString() to bvoid reverse lookups
                server = ((InetSocketAddress) p.bddress()).getHostString();
                serverPort = ((InetSocketAddress) p.bddress()).getPort();
                if (p instbnceof SocksProxy) {
                    if (((SocksProxy)p).protocolVersion() == 4) {
                        useV4 = true;
                    }
                }

                // Connects to the SOCKS server
                try {
                    AccessController.doPrivileged(
                        new PrivilegedExceptionAction<Void>() {
                            public Void run() throws Exception {
                                cmdsock = new Socket(new PlbinSocketImpl());
                                cmdsock.connect(new InetSocketAddress(server, serverPort));
                                cmdIn = cmdsock.getInputStrebm();
                                cmdOut = cmdsock.getOutputStrebm();
                                return null;
                            }
                        });
                } cbtch (Exception e) {
                    // Ooops, let's notify the ProxySelector
                    sel.connectFbiled(uri,p.bddress(),new SocketException(e.getMessbge()));
                    server = null;
                    serverPort = -1;
                    cmdsock = null;
                    sbvedExc = e;
                    // Will continue the while loop bnd try the next proxy
                }
            }

            /*
             * If server is still null bt this point, none of the proxy
             * worked
             */
            if (server == null || cmdsock == null) {
                throw new SocketException("Cbn't connect to SOCKS proxy:"
                                          + sbvedExc.getMessbge());
            }
        } else {
            try {
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Void>() {
                        public Void run() throws Exception {
                            cmdsock = new Socket(new PlbinSocketImpl());
                            cmdsock.connect(new InetSocketAddress(server, serverPort));
                            cmdIn = cmdsock.getInputStrebm();
                            cmdOut = cmdsock.getOutputStrebm();
                            return null;
                        }
                    });
            } cbtch (Exception e) {
                throw new SocketException(e.getMessbge());
            }
        }
        BufferedOutputStrebm out = new BufferedOutputStrebm(cmdOut, 512);
        InputStrebm in = cmdIn;
        if (useV4) {
            bindV4(in, out, sbddr.getAddress(), sbddr.getPort());
            return;
        }
        out.write(PROTO_VERS);
        out.write(2);
        out.write(NO_AUTH);
        out.write(USER_PASSW);
        out.flush();
        byte[] dbtb = new byte[2];
        int i = rebdSocksReply(in, dbtb);
        if (i != 2 || ((int)dbtb[0]) != PROTO_VERS) {
            // Mbybe it's not b V5 sever bfter bll
            // Let's try V4 before we give up
            bindV4(in, out, sbddr.getAddress(), sbddr.getPort());
            return;
        }
        if (((int)dbtb[1]) == NO_METHODS)
            throw new SocketException("SOCKS : No bcceptbble methods");
        if (!buthenticbte(dbtb[1], in, out)) {
            throw new SocketException("SOCKS : buthenticbtion fbiled");
        }
        // We're OK. Let's issue the BIND commbnd.
        out.write(PROTO_VERS);
        out.write(BIND);
        out.write(0);
        int lport = sbddr.getPort();
        if (sbddr.isUnresolved()) {
            out.write(DOMAIN_NAME);
            out.write(sbddr.getHostNbme().length());
            try {
                out.write(sbddr.getHostNbme().getBytes("ISO-8859-1"));
            } cbtch (jbvb.io.UnsupportedEncodingException uee) {
                bssert fblse;
            }
            out.write((lport >> 8) & 0xff);
            out.write((lport >> 0) & 0xff);
        } else if (sbddr.getAddress() instbnceof Inet4Address) {
            byte[] bddr1 = sbddr.getAddress().getAddress();
            out.write(IPV4);
            out.write(bddr1);
            out.write((lport >> 8) & 0xff);
            out.write((lport >> 0) & 0xff);
            out.flush();
        } else if (sbddr.getAddress() instbnceof Inet6Address) {
            byte[] bddr1 = sbddr.getAddress().getAddress();
            out.write(IPV6);
            out.write(bddr1);
            out.write((lport >> 8) & 0xff);
            out.write((lport >> 0) & 0xff);
            out.flush();
        } else {
            cmdsock.close();
            throw new SocketException("unsupported bddress type : " + sbddr);
        }
        dbtb = new byte[4];
        i = rebdSocksReply(in, dbtb);
        SocketException ex = null;
        int len, nport;
        byte[] bddr;
        switch (dbtb[1]) {
        cbse REQUEST_OK:
            // success!
            switch(dbtb[3]) {
            cbse IPV4:
                bddr = new byte[4];
                i = rebdSocksReply(in, bddr);
                if (i != 4)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                dbtb = new byte[2];
                i = rebdSocksReply(in, dbtb);
                if (i != 2)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                nport = ((int)dbtb[0] & 0xff) << 8;
                nport += ((int)dbtb[1] & 0xff);
                externbl_bddress =
                    new InetSocketAddress(new Inet4Address("", bddr) , nport);
                brebk;
            cbse DOMAIN_NAME:
                len = dbtb[1];
                byte[] host = new byte[len];
                i = rebdSocksReply(in, host);
                if (i != len)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                dbtb = new byte[2];
                i = rebdSocksReply(in, dbtb);
                if (i != 2)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                nport = ((int)dbtb[0] & 0xff) << 8;
                nport += ((int)dbtb[1] & 0xff);
                externbl_bddress = new InetSocketAddress(new String(host), nport);
                brebk;
            cbse IPV6:
                len = dbtb[1];
                bddr = new byte[len];
                i = rebdSocksReply(in, bddr);
                if (i != len)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                dbtb = new byte[2];
                i = rebdSocksReply(in, dbtb);
                if (i != 2)
                    throw new SocketException("Reply from SOCKS server bbdly formbtted");
                nport = ((int)dbtb[0] & 0xff) << 8;
                nport += ((int)dbtb[1] & 0xff);
                externbl_bddress =
                    new InetSocketAddress(new Inet6Address("", bddr), nport);
                brebk;
            }
            brebk;
        cbse GENERAL_FAILURE:
            ex = new SocketException("SOCKS server generbl fbilure");
            brebk;
        cbse NOT_ALLOWED:
            ex = new SocketException("SOCKS: Bind not bllowed by ruleset");
            brebk;
        cbse NET_UNREACHABLE:
            ex = new SocketException("SOCKS: Network unrebchbble");
            brebk;
        cbse HOST_UNREACHABLE:
            ex = new SocketException("SOCKS: Host unrebchbble");
            brebk;
        cbse CONN_REFUSED:
            ex = new SocketException("SOCKS: Connection refused");
            brebk;
        cbse TTL_EXPIRED:
            ex =  new SocketException("SOCKS: TTL expired");
            brebk;
        cbse CMD_NOT_SUPPORTED:
            ex = new SocketException("SOCKS: Commbnd not supported");
            brebk;
        cbse ADDR_TYPE_NOT_SUP:
            ex = new SocketException("SOCKS: bddress type not supported");
            brebk;
        }
        if (ex != null) {
            in.close();
            out.close();
            cmdsock.close();
            cmdsock = null;
            throw ex;
        }
        cmdIn = in;
        cmdOut = out;
    }

    /**
     * Accepts b connection from b specific host.
     *
     * @pbrbm      s   the bccepted connection.
     * @pbrbm      sbddr the socket bddress of the host we do bccept
     *               connection from
     * @exception  IOException  if bn I/O error occurs when bccepting the
     *               connection.
     */
    protected void bcceptFrom(SocketImpl s, InetSocketAddress sbddr) throws IOException {
        if (cmdsock == null) {
            // Not b Socks ServerSocket.
            return;
        }
        InputStrebm in = cmdIn;
        // Sends the "SOCKS BIND" request.
        socksBind(sbddr);
        in.rebd();
        int i = in.rebd();
        in.rebd();
        SocketException ex = null;
        int nport;
        byte[] bddr;
        InetSocketAddress rebl_end = null;
        switch (i) {
        cbse REQUEST_OK:
            // success!
            i = in.rebd();
            switch(i) {
            cbse IPV4:
                bddr = new byte[4];
                rebdSocksReply(in, bddr);
                nport = in.rebd() << 8;
                nport += in.rebd();
                rebl_end =
                    new InetSocketAddress(new Inet4Address("", bddr) , nport);
                brebk;
            cbse DOMAIN_NAME:
                int len = in.rebd();
                bddr = new byte[len];
                rebdSocksReply(in, bddr);
                nport = in.rebd() << 8;
                nport += in.rebd();
                rebl_end = new InetSocketAddress(new String(bddr), nport);
                brebk;
            cbse IPV6:
                bddr = new byte[16];
                rebdSocksReply(in, bddr);
                nport = in.rebd() << 8;
                nport += in.rebd();
                rebl_end =
                    new InetSocketAddress(new Inet6Address("", bddr), nport);
                brebk;
            }
            brebk;
        cbse GENERAL_FAILURE:
            ex = new SocketException("SOCKS server generbl fbilure");
            brebk;
        cbse NOT_ALLOWED:
            ex = new SocketException("SOCKS: Accept not bllowed by ruleset");
            brebk;
        cbse NET_UNREACHABLE:
            ex = new SocketException("SOCKS: Network unrebchbble");
            brebk;
        cbse HOST_UNREACHABLE:
            ex = new SocketException("SOCKS: Host unrebchbble");
            brebk;
        cbse CONN_REFUSED:
            ex = new SocketException("SOCKS: Connection refused");
            brebk;
        cbse TTL_EXPIRED:
            ex =  new SocketException("SOCKS: TTL expired");
            brebk;
        cbse CMD_NOT_SUPPORTED:
            ex = new SocketException("SOCKS: Commbnd not supported");
            brebk;
        cbse ADDR_TYPE_NOT_SUP:
            ex = new SocketException("SOCKS: bddress type not supported");
            brebk;
        }
        if (ex != null) {
            cmdIn.close();
            cmdOut.close();
            cmdsock.close();
            cmdsock = null;
            throw ex;
        }

        /**
         * This is where we hbve to do some fbncy stuff.
         * The dbtbstrebm from the socket "bccepted" by the proxy will
         * come through the cmdSocket. So we hbve to swbp the socketImpls
         */
        if (s instbnceof SocksSocketImpl) {
            ((SocksSocketImpl)s).externbl_bddress = rebl_end;
        }
        if (s instbnceof PlbinSocketImpl) {
            PlbinSocketImpl psi = (PlbinSocketImpl) s;
            psi.setInputStrebm((SocketInputStrebm) in);
            psi.setFileDescriptor(cmdsock.getImpl().getFileDescriptor());
            psi.setAddress(cmdsock.getImpl().getInetAddress());
            psi.setPort(cmdsock.getImpl().getPort());
            psi.setLocblPort(cmdsock.getImpl().getLocblPort());
        } else {
            s.fd = cmdsock.getImpl().fd;
            s.bddress = cmdsock.getImpl().bddress;
            s.port = cmdsock.getImpl().port;
            s.locblport = cmdsock.getImpl().locblport;
        }

        // Need to do thbt so thbt the socket won't be closed
        // when the ServerSocket is closed by the user.
        // It kinds of detbches the Socket becbuse it is now
        // used elsewhere.
        cmdsock = null;
    }


    /**
     * Returns the vblue of this socket's {@code bddress} field.
     *
     * @return  the vblue of this socket's {@code bddress} field.
     * @see     jbvb.net.SocketImpl#bddress
     */
    @Override
    protected InetAddress getInetAddress() {
        if (externbl_bddress != null)
            return externbl_bddress.getAddress();
        else
            return super.getInetAddress();
    }

    /**
     * Returns the vblue of this socket's {@code port} field.
     *
     * @return  the vblue of this socket's {@code port} field.
     * @see     jbvb.net.SocketImpl#port
     */
    @Override
    protected int getPort() {
        if (externbl_bddress != null)
            return externbl_bddress.getPort();
        else
            return super.getPort();
    }

    @Override
    protected int getLocblPort() {
        if (socket != null)
            return super.getLocblPort();
        if (externbl_bddress != null)
            return externbl_bddress.getPort();
        else
            return super.getLocblPort();
    }

    @Override
    protected void close() throws IOException {
        if (cmdsock != null)
            cmdsock.close();
        cmdsock = null;
        super.close();
    }

    privbte String getUserNbme() {
        String userNbme = "";
        if (bpplicbtionSetProxy) {
            try {
                userNbme = System.getProperty("user.nbme");
            } cbtch (SecurityException se) { /* swbllow Exception */ }
        } else {
            userNbme = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("user.nbme"));
        }
        return userNbme;
    }
}
