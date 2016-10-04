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

pbckbge jbvbx.mbnbgement.remote;

import jbvb.security.Principbl;
import jbvbx.security.buth.Subject;

/**
 * <p>Interfbce to define how remote credentibls bre converted into b
 * JAAS Subject.  This interfbce is used by the RMI Connector Server,
 * bnd cbn be used by other connector servers.</p>
 *
 * <p>The user-defined buthenticbtor instbnce is pbssed to the
 * connector server in the environment mbp bs the vblue of the
 * bttribute {@link JMXConnectorServer#AUTHENTICATOR}.  For connector
 * servers thbt use only this buthenticbtion system, if this bttribute
 * is not present or its vblue is <code>null</code> then no user
 * buthenticbtion will be performed bnd full bccess to the methods
 * exported by the <code>MBebnServerConnection</code> object will be
 * bllowed.</p>
 *
 * <p>If buthenticbtion is successful then bn buthenticbted
 * {@link Subject subject} filled in with its bssocibted
 * {@link Principbl principbls} is returned. Authorizbtion checks
 * will be then performed bbsed on the given set of principbls.</p>
 *
 * @since 1.5
 */
public interfbce JMXAuthenticbtor {

    /**
     * <p>Authenticbtes the <code>MBebnServerConnection</code> client
     * with the given client credentibls.</p>
     *
     * @pbrbm credentibls the user-defined credentibls to be pbssed
     * into the server in order to buthenticbte the user before
     * crebting the <code>MBebnServerConnection</code>.  The bctubl
     * type of this pbrbmeter, bnd whether it cbn be null, depends on
     * the connector.
     *
     * @return the buthenticbted subject contbining its bssocibted principbls.
     *
     * @exception SecurityException if the server cbnnot buthenticbte the user
     * with the provided credentibls.
     */
    public Subject buthenticbte(Object credentibls);
}
