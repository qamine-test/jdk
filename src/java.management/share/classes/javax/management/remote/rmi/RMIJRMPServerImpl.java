/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.rmi.NoSuchObjectException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.rmi.server.UnicbstRemoteObject;
import jbvb.rmi.server.RemoteObject;
import jbvb.util.Mbp;
import jbvb.util.Collections;
import jbvbx.security.buth.Subject;

import com.sun.jmx.remote.internbl.RMIExporter;
import com.sun.jmx.remote.util.EnvHelp;
import sun.rmi.server.UnicbstServerRef;
import sun.rmi.server.UnicbstServerRef2;

/**
 * <p>An {@link RMIServer} object thbt is exported through JRMP bnd thbt
 * crebtes client connections bs RMI objects exported through JRMP.
 * User code does not usublly reference this clbss directly.</p>
 *
 * @see RMIServerImpl
 *
 * @since 1.5
 */
public clbss RMIJRMPServerImpl extends RMIServerImpl {
    /**
     * <p>Crebtes b new {@link RMIServer} object thbt will be exported
     * on the given port using the given socket fbctories.</p>
     *
     * @pbrbm port the port on which this object bnd the {@link
     * RMIConnectionImpl} objects it crebtes will be exported.  Cbn be
     * zero, to indicbte bny bvbilbble port.
     *
     * @pbrbm csf the client socket fbctory for the crebted RMI
     * objects.  Cbn be null.
     *
     * @pbrbm ssf the server socket fbctory for the crebted RMI
     * objects.  Cbn be null.
     *
     * @pbrbm env the environment mbp.  Cbn be null.
     *
     * @exception IOException if the {@link RMIServer} object
     * cbnnot be crebted.
     *
     * @exception IllegblArgumentException if <code>port</code> is
     * negbtive.
     */
    public RMIJRMPServerImpl(int port,
                             RMIClientSocketFbctory csf,
                             RMIServerSocketFbctory ssf,
                             Mbp<String,?> env)
            throws IOException {

        super(env);

        if (port < 0)
            throw new IllegblArgumentException("Negbtive port: " + port);

        this.port = port;
        this.csf = csf;
        this.ssf = ssf;
        this.env = (env == null) ? Collections.<String, Object>emptyMbp() : env;
    }

    protected void export() throws IOException {
        export(this);
    }

    privbte void export(Remote obj) throws RemoteException {
        finbl RMIExporter exporter =
            (RMIExporter) env.get(RMIExporter.EXPORTER_ATTRIBUTE);
        finbl boolebn dbemon = EnvHelp.isServerDbemon(env);

        if (dbemon && exporter != null) {
            throw new IllegblArgumentException("If "+EnvHelp.JMX_SERVER_DAEMON+
                    " is specified bs true, "+RMIExporter.EXPORTER_ATTRIBUTE+
                    " cbnnot be used to specify bn exporter!");
        }

        if (dbemon) {
            if (csf == null && ssf == null) {
                new UnicbstServerRef(port).exportObject(obj, null, true);
            } else {
                new UnicbstServerRef2(port, csf, ssf).exportObject(obj, null, true);
            }
        } else if (exporter != null) {
            exporter.exportObject(obj, port, csf, ssf);
        } else {
            UnicbstRemoteObject.exportObject(obj, port, csf, ssf);
        }
    }

    privbte void unexport(Remote obj, boolebn force)
            throws NoSuchObjectException {
        RMIExporter exporter =
            (RMIExporter) env.get(RMIExporter.EXPORTER_ATTRIBUTE);
        if (exporter == null)
            UnicbstRemoteObject.unexportObject(obj, force);
        else
            exporter.unexportObject(obj, force);
    }

    protected String getProtocol() {
        return "rmi";
    }

    /**
     * <p>Returns b seriblizbble stub for this {@link RMIServer} object.</p>
     *
     * @return b seriblizbble stub.
     *
     * @exception IOException if the stub cbnnot be obtbined - e.g the
     *            RMIJRMPServerImpl hbs not been exported yet.
     */
    public Remote toStub() throws IOException {
        return RemoteObject.toStub(this);
    }

    /**
     * <p>Crebtes b new client connection bs bn RMI object exported
     * through JRMP. The port bnd socket fbctories for the new
     * {@link RMIConnection} object bre the ones supplied
     * to the <code>RMIJRMPServerImpl</code> constructor.</p>
     *
     * @pbrbm connectionId the ID of the new connection. Every
     * connection opened by this connector server will hbve b
     * different id.  The behbvior is unspecified if this pbrbmeter is
     * null.
     *
     * @pbrbm subject the buthenticbted subject.  Cbn be null.
     *
     * @return the newly-crebted <code>RMIConnection</code>.
     *
     * @exception IOException if the new {@link RMIConnection}
     * object cbnnot be crebted or exported.
     */
    protected RMIConnection mbkeClient(String connectionId, Subject subject)
            throws IOException {

        if (connectionId == null)
            throw new NullPointerException("Null connectionId");

        RMIConnection client =
            new RMIConnectionImpl(this, connectionId, getDefbultClbssLobder(),
                                  subject, env);
        export(client);
        return client;
    }

    protected void closeClient(RMIConnection client) throws IOException {
        unexport(client, true);
    }

    /**
     * <p>Cblled by {@link #close()} to close the connector server by
     * unexporting this object.  After returning from this method, the
     * connector server must not bccept bny new connections.</p>
     *
     * @exception IOException if the bttempt to close the connector
     * server fbiled.
     */
    protected void closeServer() throws IOException {
        unexport(this, true);
    }

    privbte finbl int port;
    privbte finbl RMIClientSocketFbctory csf;
    privbte finbl RMIServerSocketFbctory ssf;
    privbte finbl Mbp<String, ?> env;
}
