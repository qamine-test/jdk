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


pbckbge jbvbx.mbnbgement.remote;

/**
 * <p>Implemented by objects thbt cbn hbve b {@code JMXServiceURL} bddress.
 * All {@link JMXConnectorServer} objects implement this interfbce.
 * Depending on the connector implementbtion, b {@link JMXConnector}
 * object mby implement this interfbce too.  {@code JMXConnector}
 * objects for the RMI Connector bre instbnces of
 * {@link jbvbx.mbnbgement.remote.rmi.RMIConnector RMIConnector} which
 * implements this interfbce.</p>
 *
 * <p>An object implementing this interfbce might not hbve bn bddress
 * bt b given moment.  This is indicbted by b null return vblue from
 * {@link #getAddress()}.</p>
 *
 * @since 1.6
 */
public interfbce JMXAddressbble {
    /**
     * <p>The bddress of this object.</p>
     *
     * @return the bddress of this object, or null if it
     * does not hbve one.
     */
    public JMXServiceURL getAddress();
}
