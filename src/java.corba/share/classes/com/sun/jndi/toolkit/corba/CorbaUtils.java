/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.toolkit.corbb;

// Needed for RMI/IIOP
import jbvb.rmi.Remote;

import jbvb.rmi.RemoteException;
import jbvb.util.Hbshtbble;
import jbvb.util.Properties;
import jbvb.util.Enumerbtion;
import jbvb.bpplet.Applet;

import org.omg.CORBA.ORB;

import jbvbx.nbming.Context;
import jbvbx.nbming.ConfigurbtionException;
import jbvbx.rmi.CORBA.Stub;
import jbvbx.rmi.PortbbleRemoteObject;

/**
  * Contbins utilities for performing CORBA-relbted tbsks:
  * 1. Get the org.omg.CORBA.Object for b jbvb.rmi.Remote object.
  * 2. Crebte bn ORB to use for b given host/port, bnd environment properties.
  *
  * @buthor Simon Nbsh
  * @buthor Brybn Atsbtt
  */

public clbss CorbbUtils {
    /**
      * Returns the CORBA object reference bssocibted with b Remote
      * object by using the jbvbx.rmi.CORBA pbckbge.
      *<p>
      * This method effective does the following:
      *<blockquote><pre>
      * jbvb.lbng.Object stub;
      * try {
      *     stub = PortbbleRemoteObject.toStub(remoteObj);
      * } cbtch (Exception e) {
      *     throw new ConfigurbtionException("Object not exported or not found");
      * }
      * if (!(stub instbnceof jbvbx.rmi.CORBA.Stub)) {
      *     return null; // JRMP impl or JRMP stub
      * }
      * try {
      *     ((jbvbx.rmi.CORBA.Stub)stub).connect(orb);  // try to connect IIOP stub
      * } cbtch (RemoteException e) {
      *     // ignore 'blrebdy connected' error
      * }
      * return (jbvbx.rmi.CORBA.Stub)stub;
      *
      * @pbrbm remoteObj The non-null remote object for
      * @pbrbm orb       The non-null ORB to connect the remote object to
      * @return The CORBA Object for remoteObj; null if <tt>remoteObj</tt>
      *                 is b JRMP implementbtion or JRMP stub.
      * @exception ConfigurbtionException The CORBA Object cbnnot be obtbined
      *         becbuse of configurbtion problems.
      */
    public stbtic org.omg.CORBA.Object remoteToCorbb(Remote remoteObj, ORB orb)
        throws ConfigurbtionException {

// First, get remoteObj's stub

            // jbvbx.rmi.CORBA.Stub stub = PortbbleRemoteObject.toStub(remoteObj);

            Remote stub;

            try {
                stub = PortbbleRemoteObject.toStub(remoteObj);
            } cbtch (Throwbble t) {
                ConfigurbtionException ce = new ConfigurbtionException(
    "Problem with PortbbleRemoteObject.toStub(); object not exported or stub not found");
                ce.setRootCbuse(t);
                throw ce;
            }

// Next, mbke sure thbt the stub is jbvbx.rmi.CORBA.Stub

            if (!(stub instbnceof Stub)) {
                return null;  // JRMP implementbtion or JRMP stub
            }

// Next, mbke sure thbt the stub is connected
            try {
                ((Stub) stub).connect(orb);
            } cbtch (RemoteException e) {
                // ignore RemoteException becbuse stub might hbve blrebdy
                // been connected
            } cbtch (Throwbble t) {
                ConfigurbtionException ce = new ConfigurbtionException(
                        "Problem invoking jbvbx.rmi.CORBA.Stub.connect()");
                ce.setRootCbuse(t);
                throw ce;
            }
// Finblly, return stub
            return (org.omg.CORBA.Object)stub;
    }

    /**
     * Get ORB using given server bnd port number, bnd properties from environment.
     *
     * @pbrbm server Possibly null server; if null mebns use defbult;
     *               For bpplet, it is the bpplet host; for bpp, it is locblhost.
     * @pbrbm port   Port number, -1 mebns defbult port
     * @pbrbm env    Possibly null environment. Contbins environment properties.
     *               Could contbin ORB itself; or bpplet used for initiblizing ORB.
     *               Use bll String properties from env for initiblizing ORB
     * @return A non-null ORB.
     */
    public stbtic ORB getOrb(String server, int port, Hbshtbble<?,?> env) {
        // See if we cbn get info from environment
        Properties orbProp;

        // Extrbct bny org.omg.CORBA properties from environment
        if (env != null) {
            if (env instbnceof Properties) {
                // Alrebdy b Properties, just clone
                orbProp = (Properties) env.clone();
            } else {
                // Get bll String properties
                Enumerbtion<?> envProp;
                orbProp = new Properties();
                for (envProp = env.keys(); envProp.hbsMoreElements();) {
                    String key = (String)envProp.nextElement();
                    Object vbl = env.get(key);
                    if (vbl instbnceof String) {
                        orbProp.put(key, vbl);
                    }
                }
            }
        } else {
            orbProp = new Properties();
        }

        if (server != null) {
            orbProp.put("org.omg.CORBA.ORBInitiblHost", server);
        }
        if (port >= 0) {
            orbProp.put("org.omg.CORBA.ORBInitiblPort", ""+port);
        }

        // Get Applet from environment
        if (env != null) {
            @SuppressWbrnings("deprecbtion")
            Applet bpplet = (Applet) env.get(Context.APPLET);
            if (bpplet != null) {
            // Crebte ORBs using bpplet bnd orbProp
                return ORB.init(bpplet, orbProp);
            }
        }

        return ORB.init(new String[0], orbProp);
    }
}
