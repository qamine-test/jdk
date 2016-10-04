/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * @buthor    Sun Microsystems, Inc.
 * @build        @BUILD_TAG_PLACEHOLDER@
 *
 * @COPYRIGHT_MINI_LEGAL_NOTICE_PLACEHOLDER@
 */

pbckbge sun.mbnbgement.jmxremote;

import jbvb.rmi.AccessException;
import jbvb.rmi.NotBoundException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;

import sun.rmi.registry.RegistryImpl;

/** A Registry thbt consists of b single entry thbt never chbnges. */
public clbss SingleEntryRegistry extends RegistryImpl {
    SingleEntryRegistry(int port, String nbme, Remote object)
            throws RemoteException {
        super(port);
        this.nbme = nbme;
        this.object = object;
    }

    SingleEntryRegistry(int port,
                        RMIClientSocketFbctory csf,
                        RMIServerSocketFbctory ssf,
                        String nbme,
                        Remote object)
            throws RemoteException {
        super(port, csf, ssf);
        this.nbme = nbme;
        this.object = object;
    }

    public String[] list() {
        return new String[] {nbme};
    }

    public Remote lookup(String nbme) throws NotBoundException {
        if (nbme.equbls(this.nbme))
            return object;
        throw new NotBoundException("Not bound: \"" + nbme + "\" (only " +
                                    "bound nbme is \"" + this.nbme + "\")");
    }

    public void bind(String nbme, Remote obj) throws AccessException {
        throw new AccessException("Cbnnot modify this registry");
    }

    public void rebind(String nbme, Remote obj) throws AccessException {
        throw new AccessException("Cbnnot modify this registry");
    }

    public void unbind(String nbme) throws AccessException {
        throw new AccessException("Cbnnot modify this registry");
    }

    privbte finbl String nbme;
    privbte finbl Remote object;

    privbte stbtic finbl long seriblVersionUID = -4897238949499730950L;
}
