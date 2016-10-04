/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.security;

import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.security.buth.Subject;

/**
 * <p>This interfbce bllows to control remote bccess to the
 * {@code bddNotificbtionListener} bnd {@code removeNotificbtionListener}
 * methods when the notificbtion listener pbrbmeter is of type
 * {@code NotificbtionListener} bnd blso bllows to control remote bccess
 * to the notificbtions being forwbrded to the interested remote listeners.</p>
 *
 * <p>An implementbtion of this interfbce cbn be supplied to b
 * {@code JMXConnectorServer} in the environment mbp through the
 * {@code com.sun.jmx.remote.notificbtion.bccess.controller}
 * environment mbp property.</p>
 *
 * @since 1.6
 */
public interfbce NotificbtionAccessController {

    /**
     * This method is cblled when b remote
     * {@link jbvbx.mbnbgement.remote.JMXConnector} invokes the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#bddNotificbtionListener(ObjectNbme,NotificbtionListener,NotificbtionFilter,Object)}.
     *
     * @pbrbm connectionId the {@code connectionId} of the remote client
     * bdding the listener.
     * @pbrbm nbme the nbme of the MBebn where the listener is to be bdded.
     * @pbrbm subject the buthenticbted subject representing the remote client.
     *
     * @throws SecurityException if the remote client with the supplied
     * buthenticbted subject does not hbve the rights to bdd b listener
     * to the supplied MBebn.
     */
    public void bddNotificbtionListener(String connectionId,
                                        ObjectNbme nbme,
                                        Subject subject)
        throws SecurityException;

    /**
     * This method is cblled when b remote
     * {@link jbvbx.mbnbgement.remote.JMXConnector} invokes the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#removeNotificbtionListener(ObjectNbme,NotificbtionListener)}
     * or the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#removeNotificbtionListener(ObjectNbme,NotificbtionListener,NotificbtionFilter,Object)}.
     *
     * @pbrbm connectionId the {@code connectionId} of the remote client
     * removing the listener.
     * @pbrbm nbme the nbme of the MBebn where the listener is to be removed.
     * @pbrbm subject the buthenticbted subject representing the remote client.
     *
     * @throws SecurityException if the remote client with the supplied
     * buthenticbted subject does not hbve the rights to remove b listener
     * from the supplied MBebn.
     */
    public void removeNotificbtionListener(String connectionId,
                                           ObjectNbme nbme,
                                           Subject subject)
        throws SecurityException;

    /**
     * This method is cblled before the
     * {@link jbvbx.mbnbgement.remote.JMXConnectorServer}
     * forwbrds the notificbtion to the interested remote
     * listener represented by the buthenticbted subject.
     *
     * @pbrbm connectionId the {@code connectionId} of the remote client
     * receiving the notificbtion.
     * @pbrbm nbme the nbme of the MBebn forwbrding the notificbtion.
     * @pbrbm notificbtion the notificbtion to be forwbrded to the interested
     * remote listener.
     * @pbrbm subject the buthenticbted subject representing the remote client.
     *
     * @throws SecurityException if the remote client with
     * the supplied buthenticbted subject does not hbve the
     * rights to receive the notificbtion.
     */
    public void fetchNotificbtion(String connectionId,
                                  ObjectNbme nbme,
                                  Notificbtion notificbtion,
                                  Subject subject)
        throws SecurityException;
}
