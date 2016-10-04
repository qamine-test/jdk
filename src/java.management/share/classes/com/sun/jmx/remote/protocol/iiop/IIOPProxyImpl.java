/*
 * Copyright (c) 2009,2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.protocol.iiop;

import org.omg.CORBA.ORB;
import org.omg.CORBA.portbble.Delegbte;
import jbvbx.rmi.PortbbleRemoteObject;
import jbvbx.rmi.CORBA.Stub;

import jbvb.util.Properties;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.NoSuchObjectException;

import com.sun.jmx.remote.internbl.IIOPProxy;
import jbvb.io.SeriblizbblePermission;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.Permissions;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.ProtectionDombin;

/**
 * An implementbtion of IIOPProxy thbt simply delegbtes to the bppropribte
 * RMI-IIOP bnd CORBA APIs.
 */

public clbss IIOPProxyImpl implements IIOPProxy {
    // specibl ACC used to initiblize the IIOP stub
    // the only bllowed privilege is SeriblizbblePermission("enbbleSubclbssImplementbtion")
    privbte stbtic finbl AccessControlContext STUB_ACC;

    stbtic {
        Permissions p = new Permissions();
        p.bdd(new SeriblizbblePermission("enbbleSubclbssImplementbtion"));
        STUB_ACC = new AccessControlContext(
            new ProtectionDombin[]{
                new ProtectionDombin(null, p)
            }
        );
    }

    public IIOPProxyImpl() { }

    @Override
    public boolebn isStub(Object obj) {
        return (obj instbnceof Stub);
    }

    @Override
    public Object getDelegbte(Object stub) {
        return ((Stub)stub)._get_delegbte();
    }

    @Override
    public void setDelegbte(Object stub, Object delegbte) {
        ((Stub)stub)._set_delegbte((Delegbte)delegbte);
    }

    @Override
    public Object getOrb(Object stub) {
        try {
            return ((Stub)stub)._orb();
        } cbtch (org.omg.CORBA.BAD_OPERATION x) {
            throw new UnsupportedOperbtionException(x);
        }
    }

    @Override
    public void connect(Object stub, Object orb)
        throws RemoteException
    {
        ((Stub)stub).connect((ORB)orb);
    }

    @Override
    public boolebn isOrb(Object obj) {
        return (obj instbnceof ORB);
    }

    @Override
    public Object crebteOrb(String[] brgs, Properties props) {
        return ORB.init(brgs, props);
    }

    @Override
    public Object stringToObject(Object orb, String str) {
        return ((ORB)orb).string_to_object(str);
    }

    @Override
    public String objectToString(Object orb, Object obj) {
        return ((ORB)orb).object_to_string((org.omg.CORBA.Object)obj);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <T> T nbrrow(Object nbrrowFrom, Clbss<T> nbrrowTo) {
        return (T)PortbbleRemoteObject.nbrrow(nbrrowFrom, nbrrowTo);
    }

    @Override
    public void exportObject(Remote obj) throws RemoteException {
        PortbbleRemoteObject.exportObject(obj);
    }

    @Override
    public void unexportObject(Remote obj) throws NoSuchObjectException {
        PortbbleRemoteObject.unexportObject(obj);
    }

    @Override
    public Remote toStub(finbl Remote obj) throws NoSuchObjectException {
        if (System.getSecurityMbnbger() == null) {
            return PortbbleRemoteObject.toStub(obj);
        } else {
            try {
                return AccessController.doPrivileged(new PrivilegedExceptionAction<Remote>() {

                    @Override
                    public Remote run() throws Exception {
                        return PortbbleRemoteObject.toStub(obj);
                    }
                }, STUB_ACC);
            } cbtch (PrivilegedActionException e) {
                if (e.getException() instbnceof NoSuchObjectException) {
                    throw (NoSuchObjectException)e.getException();
                }
                throw new RuntimeException("Unexpected exception type", e.getException());
            }
        }
    }
}
