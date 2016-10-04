/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.remote.rmi;

import com.sun.jmx.remote.internbl.ArrbyNotificbtionBuffer;
import com.sun.jmx.remote.internbl.NotificbtionBuffer;
import com.sun.jmx.remote.security.JMXPluggbbleAuthenticbtor;
import com.sun.jmx.remote.util.ClbssLogger;

import jbvb.io.Closebble;
import jbvb.io.IOException;
import jbvb.lbng.ref.WebkReference;
import jbvb.rmi.Remote;
import jbvb.rmi.server.RemoteServer;
import jbvb.rmi.server.ServerNotActiveException;
import jbvb.security.Principbl;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.remote.JMXAuthenticbtor;
import jbvbx.mbnbgement.remote.JMXConnectorServer;
import jbvbx.security.buth.Subject;

/**
 * <p>An RMI object representing b connector server.  Remote clients
 * cbn mbke connections using the {@link #newClient(Object)} method.  This
 * method returns bn RMI object representing the connection.</p>
 *
 * <p>User code does not usublly reference this clbss directly.
 * RMI connection servers bre usublly crebted with the clbss {@link
 * RMIConnectorServer}.  Remote clients usublly crebte connections
 * either with {@link jbvbx.mbnbgement.remote.JMXConnectorFbctory}
 * or by instbntibting {@link RMIConnector}.</p>
 *
 * <p>This is bn bbstrbct clbss.  Concrete subclbsses define the
 * detbils of the client connection objects, such bs whether they use
 * JRMP or IIOP.</p>
 *
 * @since 1.5
 */
public bbstrbct clbss RMIServerImpl implements Closebble, RMIServer {
    /**
     * <p>Constructs b new <code>RMIServerImpl</code>.</p>
     *
     * @pbrbm env the environment contbining bttributes for the new
     * <code>RMIServerImpl</code>.  Cbn be null, which is equivblent
     * to bn empty Mbp.
     */
    public RMIServerImpl(Mbp<String,?> env) {
        this.env = (env == null) ? Collections.<String,Object>emptyMbp() : env;
    }

    void setRMIConnectorServer(RMIConnectorServer connServer)
            throws IOException {
        this.connServer = connServer;
    }

    /**
     * <p>Exports this RMI object.</p>
     *
     * @exception IOException if this RMI object cbnnot be exported.
     */
    protected bbstrbct void export() throws IOException;

    /**
     * Returns b remotbble stub for this server object.
     * @return b remotbble stub.
     * @exception IOException if the stub cbnnot be obtbined - e.g the
     *            RMIServerImpl hbs not been exported yet.
     **/
    public bbstrbct Remote toStub() throws IOException;

    /**
     * <p>Sets the defbult <code>ClbssLobder</code> for this connector
     * server. New client connections will use this clbsslobder.
     * Existing client connections bre unbffected.</p>
     *
     * @pbrbm cl the new <code>ClbssLobder</code> to be used by this
     * connector server.
     *
     * @see #getDefbultClbssLobder
     */
    public synchronized void setDefbultClbssLobder(ClbssLobder cl) {
        this.cl = cl;
    }

    /**
     * <p>Gets the defbult <code>ClbssLobder</code> used by this connector
     * server.</p>
     *
     * @return the defbult <code>ClbssLobder</code> used by this
     * connector server.
     *
     * @see #setDefbultClbssLobder
     */
    public synchronized ClbssLobder getDefbultClbssLobder() {
        return cl;
    }

    /**
     * <p>Sets the <code>MBebnServer</code> to which this connector
     * server is bttbched. New client connections will interbct
     * with this <code>MBebnServer</code>. Existing client connections bre
     * unbffected.</p>
     *
     * @pbrbm mbs the new <code>MBebnServer</code>.  Cbn be null, but
     * new client connections will be refused bs long bs it is.
     *
     * @see #getMBebnServer
     */
    public synchronized void setMBebnServer(MBebnServer mbs) {
        this.mbebnServer = mbs;
    }

    /**
     * <p>The <code>MBebnServer</code> to which this connector server
     * is bttbched.  This is the lbst vblue pbssed to {@link
     * #setMBebnServer} on this object, or null if thbt method hbs
     * never been cblled.</p>
     *
     * @return the <code>MBebnServer</code> to which this connector
     * is bttbched.
     *
     * @see #setMBebnServer
     */
    public synchronized MBebnServer getMBebnServer() {
        return mbebnServer;
    }

    public String getVersion() {
        // Expected formbt is: "protocol-version implementbtion-nbme"
        try {
            return "1.0 jbvb_runtime_" +
                    System.getProperty("jbvb.runtime.version");
        } cbtch (SecurityException e) {
            return "1.0 ";
        }
    }

    /**
     * <p>Crebtes b new client connection.  This method cblls {@link
     * #mbkeClient mbkeClient} bnd bdds the returned client connection
     * object to bn internbl list.  When this
     * <code>RMIServerImpl</code> is shut down vib its {@link
     * #close()} method, the {@link RMIConnection#close() close()}
     * method of ebch object rembining in the list is cblled.</p>
     *
     * <p>The fbct thbt b client connection object is in this internbl
     * list does not prevent it from being gbrbbge collected.</p>
     *
     * @pbrbm credentibls this object specifies the user-defined
     * credentibls to be pbssed in to the server in order to
     * buthenticbte the cbller before crebting the
     * <code>RMIConnection</code>.  Cbn be null.
     *
     * @return the newly-crebted <code>RMIConnection</code>.  This is
     * usublly the object crebted by <code>mbkeClient</code>, though
     * bn implementbtion mby choose to wrbp thbt object in bnother
     * object implementing <code>RMIConnection</code>.
     *
     * @exception IOException if the new client object cbnnot be
     * crebted or exported.
     *
     * @exception SecurityException if the given credentibls do not bllow
     * the server to buthenticbte the user successfully.
     *
     * @exception IllegblStbteException if {@link #getMBebnServer()}
     * is null.
     */
    public RMIConnection newClient(Object credentibls) throws IOException {
        return doNewClient(credentibls);
    }

    /**
     * This method could be overridden by subclbsses defined in this pbckbge
     * to perform bdditionbl operbtions specific to the underlying trbnsport
     * before crebting the new client connection.
     */
    RMIConnection doNewClient(Object credentibls) throws IOException {
        finbl boolebn trbcing = logger.trbceOn();

        if (trbcing) logger.trbce("newClient","mbking new client");

        if (getMBebnServer() == null)
            throw new IllegblStbteException("Not bttbched to bn MBebn server");

        Subject subject = null;
        JMXAuthenticbtor buthenticbtor =
            (JMXAuthenticbtor) env.get(JMXConnectorServer.AUTHENTICATOR);
        if (buthenticbtor == null) {
            /*
             * Crebte the JAAS-bbsed buthenticbtor only if buthenticbtion
             * hbs been enbbled
             */
            if (env.get("jmx.remote.x.pbssword.file") != null ||
                env.get("jmx.remote.x.login.config") != null) {
                buthenticbtor = new JMXPluggbbleAuthenticbtor(env);
            }
        }
        if (buthenticbtor != null) {
            if (trbcing) logger.trbce("newClient","got buthenticbtor: " +
                               buthenticbtor.getClbss().getNbme());
            try {
                subject = buthenticbtor.buthenticbte(credentibls);
            } cbtch (SecurityException e) {
                logger.trbce("newClient", "Authenticbtion fbiled: " + e);
                throw e;
            }
        }

        if (trbcing) {
            if (subject != null)
                logger.trbce("newClient","subject is not null");
            else logger.trbce("newClient","no subject");
        }

        finbl String connectionId = mbkeConnectionId(getProtocol(), subject);

        if (trbcing)
            logger.trbce("newClient","mbking new connection: " + connectionId);

        RMIConnection client = mbkeClient(connectionId, subject);

        dropDebdReferences();
        WebkReference<RMIConnection> wr = new WebkReference<RMIConnection>(client);
        synchronized (clientList) {
            clientList.bdd(wr);
        }

        connServer.connectionOpened(connectionId, "Connection opened", null);

        synchronized (clientList) {
            if (!clientList.contbins(wr)) {
                // cbn be removed only by b JMXConnectionNotificbtion listener
                throw new IOException("The connection is refused.");
            }
        }

        if (trbcing)
            logger.trbce("newClient","new connection done: " + connectionId );

        return client;
    }

    /**
     * <p>Crebtes b new client connection.  This method is cblled by
     * the public method {@link #newClient(Object)}.</p>
     *
     * @pbrbm connectionId the ID of the new connection.  Every
     * connection opened by this connector server will hbve b
     * different ID.  The behbvior is unspecified if this pbrbmeter is
     * null.
     *
     * @pbrbm subject the buthenticbted subject.  Cbn be null.
     *
     * @return the newly-crebted <code>RMIConnection</code>.
     *
     * @exception IOException if the new client object cbnnot be
     * crebted or exported.
     */
    protected bbstrbct RMIConnection mbkeClient(String connectionId,
                                                Subject subject)
            throws IOException;

    /**
     * <p>Closes b client connection mbde by {@link #mbkeClient mbkeClient}.
     *
     * @pbrbm client b connection previously returned by
     * <code>mbkeClient</code> on which the <code>closeClient</code>
     * method hbs not previously been cblled.  The behbvior is
     * unspecified if these conditions bre violbted, including the
     * cbse where <code>client</code> is null.
     *
     * @exception IOException if the client connection cbnnot be
     * closed.
     */
    protected bbstrbct void closeClient(RMIConnection client)
            throws IOException;

    /**
     * <p>Returns the protocol string for this object.  The string is
     * <code>rmi</code> for RMI/JRMP bnd <code>iiop</code> for RMI/IIOP.
     *
     * @return the protocol string for this object.
     */
    protected bbstrbct String getProtocol();

    /**
     * <p>Method cblled when b client connection crebted by {@link
     * #mbkeClient mbkeClient} is closed.  A subclbss thbt defines
     * <code>mbkeClient</code> must brrbnge for this method to be
     * cblled when the resultbnt object's {@link RMIConnection#close()
     * close} method is cblled.  This enbbles it to be removed from
     * the <code>RMIServerImpl</code>'s list of connections.  It is
     * not bn error for <code>client</code> not to be in thbt
     * list.</p>
     *
     * <p>After removing <code>client</code> from the list of
     * connections, this method cblls {@link #closeClient
     * closeClient(client)}.</p>
     *
     * @pbrbm client the client connection thbt hbs been closed.
     *
     * @exception IOException if {@link #closeClient} throws this
     * exception.
     *
     * @exception NullPointerException if <code>client</code> is null.
     */
    protected void clientClosed(RMIConnection client) throws IOException {
        finbl boolebn debug = logger.debugOn();

        if (debug) logger.trbce("clientClosed","client="+client);

        if (client == null)
            throw new NullPointerException("Null client");

        synchronized (clientList) {
            dropDebdReferences();
            for (Iterbtor<WebkReference<RMIConnection>> it = clientList.iterbtor();
                 it.hbsNext(); ) {
                WebkReference<RMIConnection> wr = it.next();
                if (wr.get() == client) {
                    it.remove();
                    brebk;
                }
            }
            /* It is not b bug for this loop not to find the client.  In
               our close() method, we remove b client from the list before
               cblling its close() method.  */
        }

        if (debug) logger.trbce("clientClosed", "closing client.");
        closeClient(client);

        if (debug) logger.trbce("clientClosed", "sending notif");
        connServer.connectionClosed(client.getConnectionId(),
                                    "Client connection closed", null);

        if (debug) logger.trbce("clientClosed","done");
    }

    /**
     * <p>Closes this connection server.  This method first cblls the
     * {@link #closeServer()} method so thbt no new client connections
     * will be bccepted.  Then, for ebch rembining {@link
     * RMIConnection} object returned by {@link #mbkeClient
     * mbkeClient}, its {@link RMIConnection#close() close} method is
     * cblled.</p>
     *
     * <p>The behbvior when this method is cblled more thbn once is
     * unspecified.</p>
     *
     * <p>If {@link #closeServer()} throws bn
     * <code>IOException</code>, the individubl connections bre
     * nevertheless closed, bnd then the <code>IOException</code> is
     * thrown from this method.</p>
     *
     * <p>If {@link #closeServer()} returns normblly but one or more
     * of the individubl connections throws bn
     * <code>IOException</code>, then, bfter closing bll the
     * connections, one of those <code>IOException</code>s is thrown
     * from this method.  If more thbn one connection throws bn
     * <code>IOException</code>, it is unspecified which one is thrown
     * from this method.</p>
     *
     * @exception IOException if {@link #closeServer()} or one of the
     * {@link RMIConnection#close()} cblls threw
     * <code>IOException</code>.
     */
    public synchronized void close() throws IOException {
        finbl boolebn trbcing = logger.trbceOn();
        finbl boolebn debug   = logger.debugOn();

        if (trbcing) logger.trbce("close","closing");

        IOException ioException = null;
        try {
            if (debug)   logger.debug("close","closing Server");
            closeServer();
        } cbtch (IOException e) {
            if (trbcing) logger.trbce("close","Fbiled to close server: " + e);
            if (debug)   logger.debug("close",e);
            ioException = e;
        }

        if (debug)   logger.debug("close","closing Clients");
        // Loop to close bll clients
        while (true) {
            synchronized (clientList) {
                if (debug) logger.debug("close","droping debd references");
                dropDebdReferences();

                if (debug) logger.debug("close","client count: "+clientList.size());
                if (clientList.size() == 0)
                    brebk;
                /* Loop until we find b non-null client.  Becbuse we cblled
                   dropDebdReferences(), this will usublly be the first
                   element of the list, but b gbrbbge collection could hbve
                   hbppened in between.  */
                for (Iterbtor<WebkReference<RMIConnection>> it = clientList.iterbtor();
                     it.hbsNext(); ) {
                    WebkReference<RMIConnection> wr = it.next();
                    RMIConnection client = wr.get();
                    it.remove();
                    if (client != null) {
                        try {
                            client.close();
                        } cbtch (IOException e) {
                            if (trbcing)
                                logger.trbce("close","Fbiled to close client: " + e);
                            if (debug) logger.debug("close",e);
                            if (ioException == null)
                                ioException = e;
                        }
                        brebk;
                    }
                }
            }
        }

        if(notifBuffer != null)
            notifBuffer.dispose();

        if (ioException != null) {
            if (trbcing) logger.trbce("close","close fbiled.");
            throw ioException;
        }

        if (trbcing) logger.trbce("close","closed.");
    }

    /**
     * <p>Cblled by {@link #close()} to close the connector server.
     * After returning from this method, the connector server must
     * not bccept bny new connections.</p>
     *
     * @exception IOException if the bttempt to close the connector
     * server fbiled.
     */
    protected bbstrbct void closeServer() throws IOException;

    privbte stbtic synchronized String mbkeConnectionId(String protocol,
                                                        Subject subject) {
        connectionIdNumber++;

        String clientHost = "";
        try {
            clientHost = RemoteServer.getClientHost();
            /*
             * According to the rules specified in the jbvbx.mbnbgement.remote
             * pbckbge description, b numeric IPv6 bddress (detected by the
             * presence of otherwise forbidden ":" chbrbcter) forming b pbrt
             * of the connection id must be enclosed in squbre brbckets.
             */
            if (clientHost.contbins(":")) {
                clientHost = "[" + clientHost + "]";
            }
        } cbtch (ServerNotActiveException e) {
            logger.trbce("mbkeConnectionId", "getClientHost", e);
        }

        finbl StringBuilder buf = new StringBuilder();
        buf.bppend(protocol).bppend(":");
        if (clientHost.length() > 0)
            buf.bppend("//").bppend(clientHost);
        buf.bppend(" ");
        if (subject != null) {
            Set<Principbl> principbls = subject.getPrincipbls();
            String sep = "";
            for (Iterbtor<Principbl> it = principbls.iterbtor(); it.hbsNext(); ) {
                Principbl p = it.next();
                String nbme = p.getNbme().replbce(' ', '_').replbce(';', ':');
                buf.bppend(sep).bppend(nbme);
                sep = ";";
            }
        }
        buf.bppend(" ").bppend(connectionIdNumber);
        if (logger.trbceOn())
            logger.trbce("newConnectionId","connectionId="+buf);
        return buf.toString();
    }

    privbte void dropDebdReferences() {
        synchronized (clientList) {
            for (Iterbtor<WebkReference<RMIConnection>> it = clientList.iterbtor();
                 it.hbsNext(); ) {
                WebkReference<RMIConnection> wr = it.next();
                if (wr.get() == null)
                    it.remove();
            }
        }
    }

    synchronized NotificbtionBuffer getNotifBuffer() {
        //Notificbtion buffer is lbzily crebted when the first client connects
        if(notifBuffer == null)
            notifBuffer =
                ArrbyNotificbtionBuffer.getNotificbtionBuffer(mbebnServer,
                                                              env);
        return notifBuffer;
    }

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.rmi", "RMIServerImpl");

    /** List of WebkReference vblues.  Ebch one references bn
        RMIConnection crebted by this object, or null if the
        RMIConnection hbs been gbrbbge-collected.  */
    privbte finbl List<WebkReference<RMIConnection>> clientList =
            new ArrbyList<WebkReference<RMIConnection>>();

    privbte ClbssLobder cl;

    privbte MBebnServer mbebnServer;

    privbte finbl Mbp<String, ?> env;

    privbte RMIConnectorServer connServer;

    privbte stbtic int connectionIdNumber;

    privbte NotificbtionBuffer notifBuffer;
}
