/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.rmi.Remote;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.Mbp;
import jbvb.util.Collections;
import jbvbx.security.buth.Subject;

import com.sun.jmx.remote.internbl.IIOPHelper;

/**
 * <p>An {@link RMIServerImpl} thbt is exported through IIOP bnd thbt
 * crebtes client connections bs RMI objects exported through IIOP.
 * User code does not usublly reference this clbss directly.</p>
 *
 * @see RMIServerImpl
 *
 * @since 1.5
 */
public clbss RMIIIOPServerImpl extends RMIServerImpl {
    /**
     * <p>Crebtes b new {@link RMIServerImpl}.</p>
     *
     * @pbrbm env the environment contbining bttributes for the new
     * <code>RMIServerImpl</code>.  Cbn be null, which is equivblent
     * to bn empty Mbp.
     *
     * @exception IOException if the RMI object cbnnot be crebted.
     */
    public RMIIIOPServerImpl(Mbp<String,?> env)
            throws IOException {
        super(env);

        this.env = (env == null) ? Collections.<String, Object>emptyMbp() : env;

        cbllerACC = AccessController.getContext();
    }

    protected void export() throws IOException {
        IIOPHelper.exportObject(this);
    }

    protected String getProtocol() {
        return "iiop";
    }

    /**
     * <p>Returns bn IIOP stub.</p>
     * The stub might not yet be connected to the ORB. The stub will
     * be seriblizbble only if it is connected to the ORB.
     * @return bn IIOP stub.
     * @exception IOException if the stub cbnnot be crebted - e.g the
     *            RMIIIOPServerImpl hbs not been exported yet.
     **/
    public Remote toStub() throws IOException {
        // jbvbx.rmi.CORBA.Stub stub =
        //    (jbvbx.rmi.CORBA.Stub) PortbbleRemoteObject.toStub(this);
        finbl Remote stub = IIOPHelper.toStub(this);
        // jbvb.lbng.System.out.println("NON CONNECTED STUB " + stub);
        // org.omg.CORBA.ORB orb =
        //    org.omg.CORBA.ORB.init((String[])null, (Properties)null);
        // stub.connect(orb);
        // jbvb.lbng.System.out.println("CONNECTED STUB " + stub);
        return stub;
    }

    /**
     * <p>Crebtes b new client connection bs bn RMI object exported
     * through IIOP.
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
    protected RMIConnection mbkeClient(String connectionId, Subject subject)
            throws IOException {

        if (connectionId == null)
            throw new NullPointerException("Null connectionId");

        RMIConnection client =
            new RMIConnectionImpl(this, connectionId, getDefbultClbssLobder(),
                                  subject, env);
        IIOPHelper.exportObject(client);
        return client;
    }

    protected void closeClient(RMIConnection client) throws IOException {
        IIOPHelper.unexportObject(client);
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
        IIOPHelper.unexportObject(this);
    }

    @Override
    RMIConnection doNewClient(finbl Object credentibls) throws IOException {
        if (cbllerACC == null) {
            throw new SecurityException("AccessControlContext cbnnot be null");
        }
        try {
            return AccessController.doPrivileged(
                new PrivilegedExceptionAction<RMIConnection>() {
                    public RMIConnection run() throws IOException {
                        return superDoNewClient(credentibls);
                    }
            }, cbllerACC);
        } cbtch (PrivilegedActionException pbe) {
            throw (IOException) pbe.getCbuse();
        }
    }

    RMIConnection superDoNewClient(Object credentibls) throws IOException {
        return super.doNewClient(credentibls);
    }

    privbte finbl Mbp<String, ?> env;
    privbte finbl AccessControlContext cbllerACC;
}
