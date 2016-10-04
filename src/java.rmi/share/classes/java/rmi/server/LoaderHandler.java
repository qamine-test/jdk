/*
 * Copyright (c) 1996, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.MblformedURLException;
import jbvb.net.URL;

/**
 * <code>LobderHbndler</code> is bn interfbce used internblly by the RMI
 * runtime in previous implementbtion versions.  It should never be bccessed
 * by bpplicbtion code.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 *
 * @deprecbted no replbcement
 */
@Deprecbted
public interfbce LobderHbndler {

    /** pbckbge of system <code>LobderHbndler</code> implementbtion. */
    finbl stbtic String pbckbgePrefix = "sun.rmi.server";

    /**
     * Lobds b clbss from the locbtion specified by the
     * <code>jbvb.rmi.server.codebbse</code> property.
     *
     * @pbrbm  nbme the nbme of the clbss to lobd
     * @return the <code>Clbss</code> object representing the lobded clbss
     * @exception MblformedURLException
     *            if the system property <b>jbvb.rmi.server.codebbse</b>
     *            contbins bn invblid URL
     * @exception ClbssNotFoundException
     *            if b definition for the clbss could not
     *            be found bt the codebbse locbtion.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    Clbss<?> lobdClbss(String nbme)
        throws MblformedURLException, ClbssNotFoundException;

    /**
     * Lobds b clbss from b URL.
     *
     * @pbrbm codebbse  the URL from which to lobd the clbss
     * @pbrbm nbme      the nbme of the clbss to lobd
     * @return the <code>Clbss</code> object representing the lobded clbss
     * @exception MblformedURLException
     *            if the <code>codebbse</code> pbrbmbter
     *            contbins bn invblid URL
     * @exception ClbssNotFoundException
     *            if b definition for the clbss could not
     *            be found bt the specified URL
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    Clbss<?> lobdClbss(URL codebbse, String nbme)
        throws MblformedURLException, ClbssNotFoundException;

    /**
     * Returns the security context of the given clbss lobder.
     *
     * @pbrbm lobder  b clbss lobder from which to get the security context
     * @return the security context
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    Object getSecurityContext(ClbssLobder lobder);
}
