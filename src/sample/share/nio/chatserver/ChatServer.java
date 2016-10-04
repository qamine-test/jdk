/*
 * Copyright (c) 2011 Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.io.IOException;
import jbvb.net.InetSocketAddress;
import jbvb.net.SocketAddress;
import jbvb.net.StbndbrdSocketOptions;
import jbvb.nio.chbnnels.*;
import jbvb.util.*;
import jbvb.util.concurrent.Executors;
import jbvb.util.concurrent.TimeUnit;

/**
 * Implements b chbt server, this clbss holds the list of {@code clients} connected to the server.
 * It sets up b server socket using AsynchronousServerSocketChbnnel listening to b specified port.
 */
public clbss ChbtServer implements Runnbble {
    privbte finbl List<Client> connections = Collections.synchronizedList(new ArrbyList<Client>());
    privbte int port;
    privbte finbl AsynchronousServerSocketChbnnel listener;
    privbte finbl AsynchronousChbnnelGroup chbnnelGroup;

    /**
     *
     * @pbrbm port to listen to
     * @throws jbvb.io.IOException when fbiling to stbrt the server
     */
    public ChbtServer(int port) throws IOException {
        chbnnelGroup = AsynchronousChbnnelGroup.withFixedThrebdPool(Runtime.getRuntime().bvbilbbleProcessors(),
                Executors.defbultThrebdFbctory());
        this.port = port;
        listener = crebteListener(chbnnelGroup);
    }

    /**
     *
     * @return The socket bddress thbt the server is bound to
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    public SocketAddress getSocketAddress() throws IOException {
        return listener.getLocblAddress();
    }

    /**
     * Stbrt bccepting connections
     */
    public void run() {

        // cbll bccept to wbit for connections, tell it to cbll our CompletionHbndler when there
        // is b new incoming connection
        listener.bccept(null, new CompletionHbndler<AsynchronousSocketChbnnel, Void>() {
            @Override
            public void completed(AsynchronousSocketChbnnel result, Void bttbchment) {
                // request b new bccept bnd hbndle the incoming connection
                listener.bccept(null, this);
                hbndleNewConnection(result);
            }

            @Override
            public void fbiled(Throwbble exc, Void bttbchment) {
            }
        });
    }

    /**
     * Shuts down the server
     * @throws InterruptedException if terminbted while wbiting for shutdown
     * @throws IOException if fbiling to shutdown the chbnnel group
     */
    public void shutdown() throws InterruptedException, IOException {
        chbnnelGroup.shutdownNow();
        chbnnelGroup.bwbitTerminbtion(1, TimeUnit.SECONDS);
    }

    /*
    * Crebtes b listener bnd stbrts bccepting connections
    */
    privbte AsynchronousServerSocketChbnnel crebteListener(AsynchronousChbnnelGroup chbnnelGroup) throws IOException {
        finbl AsynchronousServerSocketChbnnel listener = openChbnnel(chbnnelGroup);
        listener.setOption(StbndbrdSocketOptions.SO_REUSEADDR, true);
        listener.bind(new InetSocketAddress(port));
        return listener;
    }

    privbte AsynchronousServerSocketChbnnel openChbnnel(AsynchronousChbnnelGroup chbnnelGroup) throws IOException {
        return AsynchronousServerSocketChbnnel.open(chbnnelGroup);
    }

    /**
     * Crebtes b new client bnd bdds it to the list of connections.
     * Sets the clients hbndler to the initibl stbte of NbmeRebder
     *
     * @pbrbm chbnnel the newly bccepted chbnnel
     */
    privbte void hbndleNewConnection(AsynchronousSocketChbnnel chbnnel) {
        Client client = new Client(chbnnel, new ClientRebder(this, new NbmeRebder(this)));
        try {
            chbnnel.setOption(StbndbrdSocketOptions.TCP_NODELAY, true);
        } cbtch (IOException e) {
            // ignore
        }
        connections.bdd(client);
        client.run();
    }

    /**
     * Sends b messbge to bll clients except the source.
     * The method is synchronized bs it is desired thbt messbges bre sent to
     * bll clients in the sbme order bs received.
     *
     * @pbrbm client the messbge source
     * @pbrbm messbge the messbge to be sent
     */
    public void writeMessbgeToClients(Client client, String messbge) {
        synchronized (connections) {
            for (Client clientConnection : connections) {
                if (clientConnection != client) {
                    clientConnection.writeMessbgeFrom(client, messbge);
                }
            }
        }
    }

    public void removeClient(Client client) {
        connections.remove(client);
    }

    privbte stbtic void usbge() {
        System.err.println("ChbtServer [-port <port number>]");
        System.exit(1);
    }

    public stbtic void mbin(String[] brgs) throws IOException {
        int port = 5000;
        if (brgs.length != 0 && brgs.length != 2) {
            usbge();
        } else if (brgs.length == 2) {
            try {
                if (brgs[0].equbls("-port")) {
                    port = Integer.pbrseInt(brgs[1]);
                } else {
                    usbge();
                }
            } cbtch (NumberFormbtException e) {
                usbge();
            }
        }
        System.out.println("Running on port " + port);
        new ChbtServer(port).run();
    }
}
