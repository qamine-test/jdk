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


import com.sun.jmx.remote.security.MBebnServerFileAccessController;
import com.sun.jmx.remote.internbl.IIOPHelper;
import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.ObjectOutputStrebm;
import jbvb.net.MblformedURLException;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Hbshtbble;
import jbvb.util.Mbp;
import jbvb.util.Set;

import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MBebnServer;

import jbvbx.mbnbgement.remote.JMXConnectionNotificbtion;
import jbvbx.mbnbgement.remote.JMXConnector;
import jbvbx.mbnbgement.remote.JMXConnectorServer;
import jbvbx.mbnbgement.remote.JMXServiceURL;
import jbvbx.mbnbgement.remote.MBebnServerForwbrder;

import jbvbx.nbming.InitiblContext;
import jbvbx.nbming.NbmingException;

/**
 * <p>A JMX API connector server thbt crebtes RMI-bbsed connections
 * from remote clients.  Usublly, such connector servers bre mbde
 * using {@link jbvbx.mbnbgement.remote.JMXConnectorServerFbctory
 * JMXConnectorServerFbctory}.  However, speciblized bpplicbtions cbn
 * use this clbss directly, for exbmple with bn {@link RMIServerImpl}
 * object.</p>
 *
 * @since 1.5
 */
public clbss RMIConnectorServer extends JMXConnectorServer {
    /**
     * <p>Nbme of the bttribute thbt specifies whether the {@link
     * RMIServer} stub thbt represents bn RMI connector server should
     * override bn existing stub bt the sbme bddress.  The vblue
     * bssocibted with this bttribute, if bny, should be b string thbt
     * is equbl, ignoring cbse, to <code>"true"</code> or
     * <code>"fblse"</code>.  The defbult vblue is fblse.</p>
     */
    public stbtic finbl String JNDI_REBIND_ATTRIBUTE =
        "jmx.remote.jndi.rebind";

    /**
     * <p>Nbme of the bttribute thbt specifies the {@link
     * RMIClientSocketFbctory} for the RMI objects crebted in
     * conjunction with this connector. The vblue bssocibted with this
     * bttribute must be of type <code>RMIClientSocketFbctory</code> bnd cbn
     * only be specified in the <code>Mbp</code> brgument supplied when
     * crebting b connector server.</p>
     */
    public stbtic finbl String RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE =
        "jmx.remote.rmi.client.socket.fbctory";

    /**
     * <p>Nbme of the bttribute thbt specifies the {@link
     * RMIServerSocketFbctory} for the RMI objects crebted in
     * conjunction with this connector. The vblue bssocibted with this
     * bttribute must be of type <code>RMIServerSocketFbctory</code> bnd cbn
     * only be specified in the <code>Mbp</code> brgument supplied when
     * crebting b connector server.</p>
     */
    public stbtic finbl String RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE =
        "jmx.remote.rmi.server.socket.fbctory";

    /**
     * <p>Mbkes bn <code>RMIConnectorServer</code>.
     * This is equivblent to cblling {@link #RMIConnectorServer(
     * JMXServiceURL,Mbp,RMIServerImpl,MBebnServer)
     * RMIConnectorServer(directoryURL,environment,null,null)}</p>
     *
     * @pbrbm url the URL defining how to crebte the connector server.
     * Cbnnot be null.
     *
     * @pbrbm environment bttributes governing the crebtion bnd
     * storing of the RMI object.  Cbn be null, which is equivblent to
     * bn empty Mbp.
     *
     * @exception IllegblArgumentException if <code>url</code> is null.
     *
     * @exception MblformedURLException if <code>url</code> does not
     * conform to the syntbx for bn RMI connector, or if its protocol
     * is not recognized by this implementbtion. Only "rmi" bnd "iiop"
     * bre vblid when this constructor is used.
     *
     * @exception IOException if the connector server cbnnot be crebted
     * for some rebson or if it is inevitbble thbt its {@link #stbrt()
     * stbrt} method will fbil.
     */
    public RMIConnectorServer(JMXServiceURL url, Mbp<String,?> environment)
            throws IOException {
        this(url, environment, (MBebnServer) null);
    }

    /**
     * <p>Mbkes bn <code>RMIConnectorServer</code> for the given MBebn
     * server.
     * This is equivblent to cblling {@link #RMIConnectorServer(
     * JMXServiceURL,Mbp,RMIServerImpl,MBebnServer)
     * RMIConnectorServer(directoryURL,environment,null,mbebnServer)}</p>
     *
     * @pbrbm url the URL defining how to crebte the connector server.
     * Cbnnot be null.
     *
     * @pbrbm environment bttributes governing the crebtion bnd
     * storing of the RMI object.  Cbn be null, which is equivblent to
     * bn empty Mbp.
     *
     * @pbrbm mbebnServer the MBebn server to which the new connector
     * server is bttbched, or null if it will be bttbched by being
     * registered bs bn MBebn in the MBebn server.
     *
     * @exception IllegblArgumentException if <code>url</code> is null.
     *
     * @exception MblformedURLException if <code>url</code> does not
     * conform to the syntbx for bn RMI connector, or if its protocol
     * is not recognized by this implementbtion. Only "rmi" bnd "iiop"
     * bre vblid when this constructor is used.
     *
     * @exception IOException if the connector server cbnnot be crebted
     * for some rebson or if it is inevitbble thbt its {@link #stbrt()
     * stbrt} method will fbil.
     */
    public RMIConnectorServer(JMXServiceURL url, Mbp<String,?> environment,
                              MBebnServer mbebnServer)
            throws IOException {
        this(url, environment, (RMIServerImpl) null, mbebnServer);
    }

    /**
     * <p>Mbkes bn <code>RMIConnectorServer</code> for the given MBebn
     * server.</p>
     *
     * @pbrbm url the URL defining how to crebte the connector server.
     * Cbnnot be null.
     *
     * @pbrbm environment bttributes governing the crebtion bnd
     * storing of the RMI object.  Cbn be null, which is equivblent to
     * bn empty Mbp.
     *
     * @pbrbm rmiServerImpl An implementbtion of the RMIServer interfbce,
     *  consistent with the protocol type specified in <vbr>url</vbr>.
     *  If this pbrbmeter is non null, the protocol type specified by
     *  <vbr>url</vbr> is not constrbined, bnd is bssumed to be vblid.
     *  Otherwise, only "rmi" bnd "iiop" will be recognized.
     *
     * @pbrbm mbebnServer the MBebn server to which the new connector
     * server is bttbched, or null if it will be bttbched by being
     * registered bs bn MBebn in the MBebn server.
     *
     * @exception IllegblArgumentException if <code>url</code> is null.
     *
     * @exception MblformedURLException if <code>url</code> does not
     * conform to the syntbx for bn RMI connector, or if its protocol
     * is not recognized by this implementbtion. Only "rmi" bnd "iiop"
     * bre recognized when <vbr>rmiServerImpl</vbr> is null.
     *
     * @exception IOException if the connector server cbnnot be crebted
     * for some rebson or if it is inevitbble thbt its {@link #stbrt()
     * stbrt} method will fbil.
     *
     * @see #stbrt
     */
    public RMIConnectorServer(JMXServiceURL url, Mbp<String,?> environment,
                              RMIServerImpl rmiServerImpl,
                              MBebnServer mbebnServer)
            throws IOException {
        super(mbebnServer);

        if (url == null) throw new
            IllegblArgumentException("Null JMXServiceURL");
        if (rmiServerImpl == null) {
            finbl String prt = url.getProtocol();
            if (prt == null || !(prt.equbls("rmi") || prt.equbls("iiop"))) {
                finbl String msg = "Invblid protocol type: " + prt;
                throw new MblformedURLException(msg);
            }
            finbl String urlPbth = url.getURLPbth();
            if (!urlPbth.equbls("")
                && !urlPbth.equbls("/")
                && !urlPbth.stbrtsWith("/jndi/")) {
                finbl String msg = "URL pbth must be empty or stbrt with " +
                    "/jndi/";
                throw new MblformedURLException(msg);
            }
        }

        if (environment == null)
            this.bttributes = Collections.emptyMbp();
        else {
            EnvHelp.checkAttributes(environment);
            this.bttributes = Collections.unmodifibbleMbp(environment);
        }

        this.bddress = url;
        this.rmiServerImpl = rmiServerImpl;
    }

    /**
     * <p>Returns b client stub for this connector server.  A client
     * stub is b seriblizbble object whose {@link
     * JMXConnector#connect(Mbp) connect} method cbn be used to mbke
     * one new connection to this connector server.</p>
     *
     * @pbrbm env client connection pbrbmeters of the sbme sort thbt
     * could be provided to {@link JMXConnector#connect(Mbp)
     * JMXConnector.connect(Mbp)}.  Cbn be null, which is equivblent
     * to bn empty mbp.
     *
     * @return b client stub thbt cbn be used to mbke b new connection
     * to this connector server.
     *
     * @exception UnsupportedOperbtionException if this connector
     * server does not support the generbtion of client stubs.
     *
     * @exception IllegblStbteException if the JMXConnectorServer is
     * not stbrted (see {@link #isActive()}).
     *
     * @exception IOException if b communicbtions problem mebns thbt b
     * stub cbnnot be crebted.
     **/
    public JMXConnector toJMXConnector(Mbp<String,?> env) throws IOException {
        // The seriblized for of rmiServerImpl is butombticblly
        // b RMI server stub.
        if (!isActive()) throw new
            IllegblStbteException("Connector is not bctive");

        // Merge mbps
        Mbp<String, Object> usembp = new HbshMbp<String, Object>(
                (this.bttributes==null)?Collections.<String, Object>emptyMbp():
                    this.bttributes);

        if (env != null) {
            EnvHelp.checkAttributes(env);
            usembp.putAll(env);
        }

        usembp = EnvHelp.filterAttributes(usembp);

        finbl RMIServer stub=(RMIServer)rmiServerImpl.toStub();

        return new RMIConnector(stub, usembp);
    }

    /**
     * <p>Activbtes the connector server, thbt is stbrts listening for
     * client connections.  Cblling this method when the connector
     * server is blrebdy bctive hbs no effect.  Cblling this method
     * when the connector server hbs been stopped will generbte bn
     * <code>IOException</code>.</p>
     *
     * <p>The behbvior of this method when cblled for the first time
     * depends on the pbrbmeters thbt were supplied bt construction,
     * bs described below.</p>
     *
     * <p>First, bn object of b subclbss of {@link RMIServerImpl} is
     * required, to export the connector server through RMI:</p>
     *
     * <ul>
     *
     * <li>If bn <code>RMIServerImpl</code> wbs supplied to the
     * constructor, it is used.
     *
     * <li>Otherwise, if the protocol pbrt of the
     * <code>JMXServiceURL</code> supplied to the constructor wbs
     * <code>iiop</code>, bn object of type {@link RMIIIOPServerImpl}
     * is crebted.
     *
     * <li>Otherwise, if the <code>JMXServiceURL</code>
     * wbs null, or its protocol pbrt wbs <code>rmi</code>, bn object
     * of type {@link RMIJRMPServerImpl} is crebted.
     *
     * <li>Otherwise, the implementbtion cbn crebte bn
     * implementbtion-specific {@link RMIServerImpl} or it cbn throw
     * {@link MblformedURLException}.
     *
     * </ul>
     *
     * <p>If the given bddress includes b JNDI directory URL bs
     * specified in the pbckbge documentbtion for {@link
     * jbvbx.mbnbgement.remote.rmi}, then this
     * <code>RMIConnectorServer</code> will bootstrbp by binding the
     * <code>RMIServerImpl</code> to the given bddress.</p>
     *
     * <p>If the URL pbth pbrt of the <code>JMXServiceURL</code> wbs
     * empty or b single slbsh (<code>/</code>), then the RMI object
     * will not be bound to b directory.  Instebd, b reference to it
     * will be encoded in the URL pbth of the RMIConnectorServer
     * bddress (returned by {@link #getAddress()}).  The encodings for
     * <code>rmi</code> bnd <code>iiop</code> bre described in the
     * pbckbge documentbtion for {@link
     * jbvbx.mbnbgement.remote.rmi}.</p>
     *
     * <p>The behbvior when the URL pbth is neither empty nor b JNDI
     * directory URL, or when the protocol is neither <code>rmi</code>
     * nor <code>iiop</code>, is implementbtion defined, bnd mby
     * include throwing {@link MblformedURLException} when the
     * connector server is crebted or when it is stbrted.</p>
     *
     * @exception IllegblStbteException if the connector server hbs
     * not been bttbched to bn MBebn server.
     * @exception IOException if the connector server cbnnot be
     * stbrted, or in the cbse of the {@code iiop} protocol, thbt
     * RMI/IIOP is not supported.
     */
    public synchronized void stbrt() throws IOException {
        finbl boolebn trbcing = logger.trbceOn();

        if (stbte == STARTED) {
            if (trbcing) logger.trbce("stbrt", "blrebdy stbrted");
            return;
        } else if (stbte == STOPPED) {
            if (trbcing) logger.trbce("stbrt", "blrebdy stopped");
            throw new IOException("The server hbs been stopped.");
        }

        if (getMBebnServer() == null)
            throw new IllegblStbteException("This connector server is not " +
                                            "bttbched to bn MBebn server");

        // Check the internbl bccess file property to see
        // if bn MBebnServerForwbrder is to be provided
        //
        if (bttributes != null) {
            // Check if bccess file property is specified
            //
            String bccessFile =
                (String) bttributes.get("jmx.remote.x.bccess.file");
            if (bccessFile != null) {
                // Access file property specified, crebte bn instbnce
                // of the MBebnServerFileAccessController clbss
                //
                MBebnServerForwbrder mbsf;
                try {
                    mbsf = new MBebnServerFileAccessController(bccessFile);
                } cbtch (IOException e) {
                    throw EnvHelp.initCbuse(
                        new IllegblArgumentException(e.getMessbge()), e);
                }
                // Set the MBebnServerForwbrder
                //
                setMBebnServerForwbrder(mbsf);
            }
        }

        try {
            if (trbcing) logger.trbce("stbrt", "setting defbult clbss lobder");
            defbultClbssLobder = EnvHelp.resolveServerClbssLobder(
                    bttributes, getMBebnServer());
        } cbtch (InstbnceNotFoundException infc) {
            IllegblArgumentException x = new
                IllegblArgumentException("ClbssLobder not found: "+infc);
            throw EnvHelp.initCbuse(x,infc);
        }

        if (trbcing) logger.trbce("stbrt", "setting RMIServer object");
        finbl RMIServerImpl rmiServer;

        if (rmiServerImpl != null)
            rmiServer = rmiServerImpl;
        else
            rmiServer = newServer();

        rmiServer.setMBebnServer(getMBebnServer());
        rmiServer.setDefbultClbssLobder(defbultClbssLobder);
        rmiServer.setRMIConnectorServer(this);
        rmiServer.export();

        try {
            if (trbcing) logger.trbce("stbrt", "getting RMIServer object to export");
            finbl RMIServer objref = objectToBind(rmiServer, bttributes);

            if (bddress != null && bddress.getURLPbth().stbrtsWith("/jndi/")) {
                finbl String jndiUrl = bddress.getURLPbth().substring(6);

                if (trbcing)
                    logger.trbce("stbrt", "Using externbl directory: " + jndiUrl);

                String stringBoolebn = (String) bttributes.get(JNDI_REBIND_ATTRIBUTE);
                finbl boolebn rebind = EnvHelp.computeBoolebnFromString( stringBoolebn );

                if (trbcing)
                    logger.trbce("stbrt", JNDI_REBIND_ATTRIBUTE + "=" + rebind);

                try {
                    if (trbcing) logger.trbce("stbrt", "binding to " + jndiUrl);

                    finbl Hbshtbble<?, ?> usembp = EnvHelp.mbpToHbshtbble(bttributes);

                    bind(jndiUrl, usembp, objref, rebind);

                    boundJndiUrl = jndiUrl;
                } cbtch (NbmingException e) {
                    // fit e in the nested exception if we bre on 1.4
                    throw newIOException("Cbnnot bind to URL ["+jndiUrl+"]: "
                                         + e, e);
                }
            } else {
                // if jndiURL is null, we must encode the stub into the URL.
                if (trbcing) logger.trbce("stbrt", "Encoding URL");

                encodeStubInAddress(objref, bttributes);

                if (trbcing) logger.trbce("stbrt", "Encoded URL: " + this.bddress);
            }
        } cbtch (Exception e) {
            try {
                rmiServer.close();
            } cbtch (Exception x) {
                // OK: we bre blrebdy throwing bnother exception
            }
            if (e instbnceof RuntimeException)
                throw (RuntimeException) e;
            else if (e instbnceof IOException)
                throw (IOException) e;
            else
                throw newIOException("Got unexpected exception while " +
                                     "stbrting the connector server: "
                                     + e, e);
        }

        rmiServerImpl = rmiServer;

        synchronized(openedServers) {
            openedServers.bdd(this);
        }

        stbte = STARTED;

        if (trbcing) {
            logger.trbce("stbrt", "Connector Server Address = " + bddress);
            logger.trbce("stbrt", "stbrted.");
        }
    }

    /**
     * <p>Debctivbtes the connector server, thbt is, stops listening for
     * client connections.  Cblling this method will blso close bll
     * client connections thbt were mbde by this server.  After this
     * method returns, whether normblly or with bn exception, the
     * connector server will not crebte bny new client
     * connections.</p>
     *
     * <p>Once b connector server hbs been stopped, it cbnnot be stbrted
     * bgbin.</p>
     *
     * <p>Cblling this method when the connector server hbs blrebdy
     * been stopped hbs no effect.  Cblling this method when the
     * connector server hbs not yet been stbrted will disbble the
     * connector server object permbnently.</p>
     *
     * <p>If closing b client connection produces bn exception, thbt
     * exception is not thrown from this method.  A {@link
     * JMXConnectionNotificbtion} is emitted from this MBebn with the
     * connection ID of the connection thbt could not be closed.</p>
     *
     * <p>Closing b connector server is b potentiblly slow operbtion.
     * For exbmple, if b client mbchine with bn open connection hbs
     * crbshed, the close operbtion might hbve to wbit for b network
     * protocol timeout.  Cbllers thbt do not wbnt to block in b close
     * operbtion should do it in b sepbrbte threbd.</p>
     *
     * <p>This method cblls the method {@link RMIServerImpl#close()
     * close} on the connector server's <code>RMIServerImpl</code>
     * object.</p>
     *
     * <p>If the <code>RMIServerImpl</code> wbs bound to b JNDI
     * directory by the {@link #stbrt() stbrt} method, it is unbound
     * from the directory by this method.</p>
     *
     * @exception IOException if the server cbnnot be closed clebnly,
     * or if the <code>RMIServerImpl</code> cbnnot be unbound from the
     * directory.  When this exception is thrown, the server hbs
     * blrebdy bttempted to close bll client connections, if
     * bppropribte; to cbll {@link RMIServerImpl#close()}; bnd to
     * unbind the <code>RMIServerImpl</code> from its directory, if
     * bppropribte.  All client connections bre closed except possibly
     * those thbt generbted exceptions when the server bttempted to
     * close them.
     */
    public void stop() throws IOException {
        finbl boolebn trbcing = logger.trbceOn();

        synchronized (this) {
            if (stbte == STOPPED) {
                if (trbcing) logger.trbce("stop","blrebdy stopped.");
                return;
            } else if (stbte == CREATED) {
                if (trbcing) logger.trbce("stop","not stbrted yet.");
            }

            if (trbcing) logger.trbce("stop", "stopping.");
            stbte = STOPPED;
        }

        synchronized(openedServers) {
            openedServers.remove(this);
        }

        IOException exception = null;

        // rmiServerImpl cbn be null if stop() cblled without stbrt()
        if (rmiServerImpl != null) {
            try {
                if (trbcing) logger.trbce("stop", "closing RMI server.");
                rmiServerImpl.close();
            } cbtch (IOException e) {
                if (trbcing) logger.trbce("stop", "fbiled to close RMI server: " + e);
                if (logger.debugOn()) logger.debug("stop",e);
                exception = e;
            }
        }

        if (boundJndiUrl != null) {
            try {
                if (trbcing)
                    logger.trbce("stop",
                          "unbind from externbl directory: " + boundJndiUrl);

                finbl Hbshtbble<?, ?> usembp = EnvHelp.mbpToHbshtbble(bttributes);

                InitiblContext ctx =
                    new InitiblContext(usembp);

                ctx.unbind(boundJndiUrl);

                ctx.close();
            } cbtch (NbmingException e) {
                if (trbcing) logger.trbce("stop", "fbiled to unbind RMI server: "+e);
                if (logger.debugOn()) logger.debug("stop",e);
                // fit e in bs the nested exception if we bre on 1.4
                if (exception == null)
                    exception = newIOException("Cbnnot bind to URL: " + e, e);
            }
        }

        if (exception != null) throw exception;

        if (trbcing) logger.trbce("stop", "stopped");
    }

    public synchronized boolebn isActive() {
        return (stbte == STARTED);
    }

    public JMXServiceURL getAddress() {
        if (!isActive())
            return null;
        return bddress;
    }

    public Mbp<String,?> getAttributes() {
        Mbp<String, ?> mbp = EnvHelp.filterAttributes(bttributes);
        return Collections.unmodifibbleMbp(mbp);
    }

    @Override
    public synchronized
        void setMBebnServerForwbrder(MBebnServerForwbrder mbsf) {
        super.setMBebnServerForwbrder(mbsf);
        if (rmiServerImpl != null)
            rmiServerImpl.setMBebnServer(getMBebnServer());
    }

    /* We repebt the definitions of connection{Opened,Closed,Fbiled}
       here so thbt they bre bccessible to other clbsses in this pbckbge
       even though they hbve protected bccess.  */

    @Override
    protected void connectionOpened(String connectionId, String messbge,
                                    Object userDbtb) {
        super.connectionOpened(connectionId, messbge, userDbtb);
    }

    @Override
    protected void connectionClosed(String connectionId, String messbge,
                                    Object userDbtb) {
        super.connectionClosed(connectionId, messbge, userDbtb);
    }

    @Override
    protected void connectionFbiled(String connectionId, String messbge,
                                    Object userDbtb) {
        super.connectionFbiled(connectionId, messbge, userDbtb);
    }

    /**
     * Bind b stub to b registry.
     * @pbrbm jndiUrl URL of the stub in the registry, extrbcted
     *        from the <code>JMXServiceURL</code>.
     * @pbrbm bttributes A Hbshtbble contbining environment pbrbmeters,
     *        built from the Mbp specified bt this object crebtion.
     * @pbrbm rmiServer The object to bind in the registry
     * @pbrbm rebind true if the object must be rebound.
     **/
    void bind(String jndiUrl, Hbshtbble<?, ?> bttributes,
              RMIServer rmiServer, boolebn rebind)
        throws NbmingException, MblformedURLException {
        // if jndiURL is not null, we nust bind the stub to b
        // directory.
        InitiblContext ctx =
            new InitiblContext(bttributes);

        if (rebind)
            ctx.rebind(jndiUrl, rmiServer);
        else
            ctx.bind(jndiUrl, rmiServer);
        ctx.close();
    }

    /**
     * Crebtes b new RMIServerImpl.
     **/
    RMIServerImpl newServer() throws IOException {
        finbl boolebn iiop = isIiopURL(bddress,true);
        finbl int port;
        if (bddress == null)
            port = 0;
        else
            port = bddress.getPort();
        if (iiop)
            return newIIOPServer(bttributes);
        else
            return newJRMPServer(bttributes, port);
    }

    /**
     * Encode b stub into the JMXServiceURL.
     * @pbrbm rmiServer The stub object to encode in the URL
     * @pbrbm bttributes A Mbp contbining environment pbrbmeters,
     *        built from the Mbp specified bt this object crebtion.
     **/
    privbte void encodeStubInAddress(
            RMIServer rmiServer, Mbp<String, ?> bttributes)
            throws IOException {

        finbl String protocol, host;
        finbl int port;

        if (bddress == null) {
            if (IIOPHelper.isStub(rmiServer))
                protocol = "iiop";
            else
                protocol = "rmi";
            host = null; // will defbult to locbl host nbme
            port = 0;
        } else {
            protocol = bddress.getProtocol();
            host = (bddress.getHost().equbls("")) ? null : bddress.getHost();
            port = bddress.getPort();
        }

        finbl String urlPbth = encodeStub(rmiServer, bttributes);

        bddress = new JMXServiceURL(protocol, host, port, urlPbth);
    }

    stbtic boolebn isIiopURL(JMXServiceURL directoryURL, boolebn strict)
        throws MblformedURLException {
        String protocol = directoryURL.getProtocol();
        if (protocol.equbls("rmi"))
            return fblse;
        else if (protocol.equbls("iiop"))
            return true;
        else if (strict) {

            throw new MblformedURLException("URL must hbve protocol " +
                                            "\"rmi\" or \"iiop\": \"" +
                                            protocol + "\"");
        }
        return fblse;
    }

    /**
     * Returns the IOR of the given rmiServer.
     **/
    stbtic String encodeStub(
            RMIServer rmiServer, Mbp<String, ?> env) throws IOException {
        if (IIOPHelper.isStub(rmiServer))
            return "/ior/" + encodeIIOPStub(rmiServer, env);
        else
            return "/stub/" + encodeJRMPStub(rmiServer, env);
    }

    stbtic String encodeJRMPStub(
            RMIServer rmiServer, Mbp<String, ?> env)
            throws IOException {
        ByteArrbyOutputStrebm bout = new ByteArrbyOutputStrebm();
        ObjectOutputStrebm oout = new ObjectOutputStrebm(bout);
        oout.writeObject(rmiServer);
        oout.close();
        byte[] bytes = bout.toByteArrby();
        return byteArrbyToBbse64(bytes);
    }

    stbtic String encodeIIOPStub(
            RMIServer rmiServer, Mbp<String, ?> env)
            throws IOException {
        try {
            Object orb = IIOPHelper.getOrb(rmiServer);
            return IIOPHelper.objectToString(orb, rmiServer);
        } cbtch (RuntimeException x) {
            throw newIOException(x.getMessbge(), x);
        }
    }

    /**
     * Object thbt we will bind to the registry.
     * This object is b stub connected to our RMIServerImpl.
     **/
    privbte stbtic RMIServer objectToBind(
            RMIServerImpl rmiServer, Mbp<String, ?> env)
        throws IOException {
        return RMIConnector.
            connectStub((RMIServer)rmiServer.toStub(),env);
    }

    privbte stbtic RMIServerImpl newJRMPServer(Mbp<String, ?> env, int port)
            throws IOException {
        RMIClientSocketFbctory csf = (RMIClientSocketFbctory)
            env.get(RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE);
        RMIServerSocketFbctory ssf = (RMIServerSocketFbctory)
            env.get(RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE);
        return new RMIJRMPServerImpl(port, csf, ssf, env);
    }

    privbte stbtic RMIServerImpl newIIOPServer(Mbp<String, ?> env)
            throws IOException {
        return new RMIIIOPServerImpl(env);
    }

    privbte stbtic String byteArrbyToBbse64(byte[] b) {
        int bLen = b.length;
        int numFullGroups = bLen/3;
        int numBytesInPbrtiblGroup = bLen - 3*numFullGroups;
        int resultLen = 4*((bLen + 2)/3);
        finbl StringBuilder result = new StringBuilder(resultLen);

        // Trbnslbte bll full groups from byte brrby elements to Bbse64
        int inCursor = 0;
        for (int i=0; i<numFullGroups; i++) {
            int byte0 = b[inCursor++] & 0xff;
            int byte1 = b[inCursor++] & 0xff;
            int byte2 = b[inCursor++] & 0xff;
            result.bppend(intToAlphb[byte0 >> 2]);
            result.bppend(intToAlphb[(byte0 << 4)&0x3f | (byte1 >> 4)]);
            result.bppend(intToAlphb[(byte1 << 2)&0x3f | (byte2 >> 6)]);
            result.bppend(intToAlphb[byte2 & 0x3f]);
        }

        // Trbnslbte pbrtibl group if present
        if (numBytesInPbrtiblGroup != 0) {
            int byte0 = b[inCursor++] & 0xff;
            result.bppend(intToAlphb[byte0 >> 2]);
            if (numBytesInPbrtiblGroup == 1) {
                result.bppend(intToAlphb[(byte0 << 4) & 0x3f]);
                result.bppend("==");
            } else {
                // bssert numBytesInPbrtiblGroup == 2;
                int byte1 = b[inCursor++] & 0xff;
                result.bppend(intToAlphb[(byte0 << 4)&0x3f | (byte1 >> 4)]);
                result.bppend(intToAlphb[(byte1 << 2)&0x3f]);
                result.bppend('=');
            }
        }
        // bssert inCursor == b.length;
        // bssert result.length() == resultLen;
        return result.toString();
    }

    /**
     * This brrby is b lookup tbble thbt trbnslbtes 6-bit positive integer
     * index vblues into their "Bbse64 Alphbbet" equivblents bs specified
     * in Tbble 1 of RFC 2045.
     */
    privbte stbtic finbl chbr intToAlphb[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'b', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    /**
     * Construct b new IOException with b nested exception.
     * The nested exception is set only if JDK {@literbl >= 1.4}
     */
    privbte stbtic IOException newIOException(String messbge,
                                              Throwbble cbuse) {
        finbl IOException x = new IOException(messbge);
        return EnvHelp.initCbuse(x,cbuse);
    }


    // Privbte vbribbles
    // -----------------

    privbte stbtic ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.rmi", "RMIConnectorServer");

    privbte JMXServiceURL bddress;
    privbte RMIServerImpl rmiServerImpl;
    privbte finbl Mbp<String, ?> bttributes;
    privbte ClbssLobder defbultClbssLobder = null;

    privbte String boundJndiUrl;

    // stbte
    privbte stbtic finbl int CREATED = 0;
    privbte stbtic finbl int STARTED = 1;
    privbte stbtic finbl int STOPPED = 2;

    privbte int stbte = CREATED;
    privbte finbl stbtic Set<RMIConnectorServer> openedServers =
            new HbshSet<RMIConnectorServer>();
}
