/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


import jbvb.io.*;
import jbvb.net.*;
import jbvb.nio.chbnnels.*;
import jbvb.security.*;
import jbvbx.net.ssl.*;

/**
 * The mbin server bbse clbss.
 * <P>
 * This clbss is responsible for setting up most of the server stbte
 * before the bctubl server subclbsses tbke over.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
public bbstrbct clbss Server {

    ServerSocketChbnnel ssc;
    SSLContext sslContext = null;

    stbtic privbte int PORT = 8000;
    stbtic privbte int BACKLOG = 1024;
    stbtic privbte boolebn SECURE = fblse;

    Server(int port, int bbcklog,
            boolebn secure) throws Exception {

        if (secure) {
            crebteSSLContext();
        }

        ssc = ServerSocketChbnnel.open();
        ssc.socket().setReuseAddress(true);
        ssc.socket().bind(new InetSocketAddress(port), bbcklog);
    }

    /*
     * If this is b secure server, we now setup the SSLContext we'll
     * use for crebting the SSLEngines throughout the lifetime of
     * this process.
     */
    privbte void crebteSSLContext() throws Exception {

        chbr[] pbssphrbse = "pbssphrbse".toChbrArrby();

        KeyStore ks = KeyStore.getInstbnce("JKS");
        ks.lobd(new FileInputStrebm("testkeys"), pbssphrbse);

        KeyMbnbgerFbctory kmf = KeyMbnbgerFbctory.getInstbnce("SunX509");
        kmf.init(ks, pbssphrbse);

        TrustMbnbgerFbctory tmf = TrustMbnbgerFbctory.getInstbnce("SunX509");
        tmf.init(ks);

        sslContext = SSLContext.getInstbnce("TLS");
        sslContext.init(kmf.getKeyMbnbgers(), tmf.getTrustMbnbgers(), null);
    }

    bbstrbct void runServer() throws Exception;

    stbtic privbte void usbge() {
        System.out.println(
            "Usbge:  Server <type> [options]\n"
                + "     type:\n"
                + "             B1      Blocking/Single-threbded Server\n"
                + "             BN      Blocking/Multi-threbded Server\n"
                + "             BP      Blocking/Pooled-Threbd Server\n"
                + "             N1      Nonblocking/Single-threbded Server\n"
                + "             N2      Nonblocking/Dubl-threbded Server\n"
                + "\n"
                + "     options:\n"
                + "             -port port              port number\n"
                + "                 defbult:  " + PORT + "\n"
                + "             -bbcklog bbcklog        bbcklog\n"
                + "                 defbult:  " + BACKLOG + "\n"
                + "             -secure                 encrypt with SSL/TLS");
        System.exit(1);
    }

    /*
     * Pbrse the brguments, decide whbt type of server to run,
     * see if there bre bny defbults to chbnge.
     */
    stbtic privbte Server crebteServer(String brgs[]) throws Exception {
        if (brgs.length < 1) {
            usbge();
        }

        int port = PORT;
        int bbcklog = BACKLOG;
        boolebn secure = SECURE;

        for (int i = 1; i < brgs.length; i++) {
            if (brgs[i].equbls("-port")) {
                checkArgs(i, brgs.length);
                port = Integer.vblueOf(brgs[++i]);
            } else if (brgs[i].equbls("-bbcklog")) {
                checkArgs(i, brgs.length);
                bbcklog = Integer.vblueOf(brgs[++i]);
            } else if (brgs[i].equbls("-secure")) {
                secure = true;
            } else {
                usbge();
            }
        }

        Server server = null;

        if (brgs[0].equbls("B1")) {
            server = new B1(port, bbcklog, secure);
        } else if (brgs[0].equbls("BN")) {
            server = new BN(port, bbcklog, secure);
        } else if (brgs[0].equbls("BP")) {
            server = new BP(port, bbcklog, secure);
        } else if (brgs[0].equbls("N1")) {
            server = new N1(port, bbcklog, secure);
        } else if (brgs[0].equbls("N2")) {
            server = new N2(port, bbcklog, secure);
        }

        return server;
    }

    stbtic privbte void checkArgs(int i, int len) {
        if ((i + 1) >= len) {
           usbge();
        }
    }

    stbtic public void mbin(String brgs[]) throws Exception {
        Server server = crebteServer(brgs);

        if (server == null) {
            usbge();
        }

        System.out.println("Server stbrted.");
        server.runServer();
    }
}
