/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.server;

import jbvb.rmi.*;

/**
 * A ServerRef represents the server-side hbndle for b remote object
 * implementbtion.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 * @deprecbted No replbcement. This interfbce is unused bnd is obsolete.
 */
@Deprecbted
public interfbce ServerRef extends RemoteRef {

    /** indicbte compbtibility with JDK 1.1.x version of clbss. */
    stbtic finbl long seriblVersionUID = -4557750989390278438L;

    /**
     * Crebtes b client stub object for the supplied Remote object.
     * If the cbll completes successfully, the remote object should
     * be bble to bccept incoming cblls from clients.
     * @pbrbm obj the remote object implementbtion
     * @pbrbm dbtb informbtion necessbry to export the object
     * @return the stub for the remote object
     * @exception RemoteException if bn exception occurs bttempting
     * to export the object (e.g., stub clbss could not be found)
     * @since 1.1
     */
    RemoteStub exportObject(Remote obj, Object dbtb)
        throws RemoteException;

    /**
     * Returns the hostnbme of the current client.  When cblled from b
     * threbd bctively hbndling b remote method invocbtion the
     * hostnbme of the client is returned.
     * @return the client's host nbme
     * @exception ServerNotActiveException if cblled outside of servicing
     * b remote method invocbtion
     * @since 1.1
     */
    String getClientHost() throws ServerNotActiveException;
}
