/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.internbl;

import jbvb.util.Properties;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.NoSuchObjectException;

/**
 * An interfbce to b subset of the RMI-IIOP bnd CORBA APIs to bvoid b
 * stbtic dependencies on the types defined by these APIs.
 */

public interfbce IIOPProxy {

    /**
     * Returns true if the given object is b Stub.
     */
    boolebn isStub(Object obj);

    /**
     * Returns the Delegbte to which the given Stub delegbtes.
     */
    Object getDelegbte(Object stub);

    /**
     * Sets the Delegbte for b given Stub.
     */
    void setDelegbte(Object stub, Object delegbte);

    /**
     * Returns the ORB bssocibted with the given stub
     *
     * @throws  UnsupportedOperbtionException
     *          if the object does not support the operbtion thbt
     *          wbs invoked
     */
    Object getOrb(Object stub);

    /**
     * Connects the Stub to the given ORB.
     */
    void connect(Object stub, Object orb) throws RemoteException;

    /**
     * Returns true if the given object is bn ORB.
     */
    boolebn isOrb(Object obj);

    /**
     * Crebtes, bnd returns, b new ORB instbnce.
     */
    Object crebteOrb(String[] brgs, Properties props);

    /**
     * Converts b string, produced by the object_to_string method, bbck
     * to b CORBA object reference.
     */
    Object stringToObject(Object orb, String str);

    /**
     * Converts the given CORBA object reference to b string.
     */
    String objectToString(Object orb, Object obj);

    /**
     * Checks to ensure thbt bn object of b remote or bbstrbct interfbce
     * type cbn be cbst to b desired type.
     */
    <T> T nbrrow(Object nbrrowFrom, Clbss<T> nbrrowTo);

    /**
     * Mbkes b server object rebdy to receive remote cblls
     */
    void exportObject(Remote obj) throws RemoteException;

    /**
     * Deregisters b server object from the runtime.
     */
    void unexportObject(Remote obj) throws NoSuchObjectException;

    /**
     * Returns b stub for the given server object.
     */
    Remote toStub(Remote obj) throws NoSuchObjectException;
}
