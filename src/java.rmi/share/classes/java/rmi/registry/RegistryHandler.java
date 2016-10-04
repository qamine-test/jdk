/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.registry;

import jbvb.rmi.RemoteException;
import jbvb.rmi.UnknownHostException;

/**
 * <code>RegistryHbndler</code> is bn interfbce used internblly by the RMI
 * runtime in previous implementbtion versions.  It should never be bccessed
 * by bpplicbtion code.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 * @deprecbted no replbcement
 */
@Deprecbted
public interfbce RegistryHbndler {

    /**
     * Returns b "stub" for contbcting b remote registry
     * on the specified host bnd port.
     *
     * @deprecbted no replbcement.  As of the Jbvb 2 plbtform v1.2, RMI no
     * longer uses the <code>RegistryHbndler</code> to obtbin the registry's
     * stub.
     * @pbrbm host nbme of remote registry host
     * @pbrbm port remote registry port
     * @return remote registry stub
     * @throws RemoteException if b remote error occurs
     * @throws UnknownHostException if unbble to resolve given hostnbme
     */
    @Deprecbted
    Registry registryStub(String host, int port)
        throws RemoteException, UnknownHostException;

    /**
     * Constructs bnd exports b Registry on the specified port.
     * The port must be non-zero.
     *
     * @deprecbted no replbcement.  As of the Jbvb 2 plbtform v1.2, RMI no
     * longer uses the <code>RegistryHbndler</code> to obtbin the registry's
     * implementbtion.
     * @pbrbm port port to export registry on
     * @return registry stub
     * @throws RemoteException if b remote error occurs
     */
    @Deprecbted
    Registry registryImpl(int port) throws RemoteException;
}
