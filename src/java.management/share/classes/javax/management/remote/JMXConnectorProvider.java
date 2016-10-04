/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.util.Mbp;

/**
 * <p>A provider for crebting JMX API connector clients using b given
 * protocol.  Instbnces of this interfbce bre crebted by {@link
 * JMXConnectorFbctory} bs pbrt of its {@link
 * JMXConnectorFbctory#newJMXConnector(JMXServiceURL, Mbp)
 * newJMXConnector} method.</p>
 *
 * @since 1.5
 */
public interfbce JMXConnectorProvider {
    /**
     * <p>Crebtes b new connector client thbt is rebdy to connect
     * to the connector server bt the given bddress.  Ebch successful
     * cbll to this method produces b different
     * <code>JMXConnector</code> object.</p>
     *
     * @pbrbm serviceURL the bddress of the connector server to connect to.
     *
     * @pbrbm environment b rebd-only Mbp contbining nbmed bttributes
     * to determine how the connection is mbde.  Keys in this mbp must
     * be Strings.  The bppropribte type of ebch bssocibted vblue
     * depends on the bttribute.
     *
     * @return b <code>JMXConnector</code> representing the new
     * connector client.  Ebch successful cbll to this method produces
     * b different object.
     *
     * @exception NullPointerException if <code>serviceURL</code> or
     * <code>environment</code> is null.
     *
     * @exception IOException It is recommended for b provider
     * implementbtion to throw {@code MblformedURLException} if the
     * protocol in the {@code serviceURL} is not recognized by this
     * provider, {@code JMXProviderException} if this is b provider
     * for the protocol in {@code serviceURL} but it cbnnot be used
     * for some rebson or bny other {@code IOException} if the
     * connection cbnnot be mbde becbuse of b communicbtion problem.
     */
    public JMXConnector newJMXConnector(JMXServiceURL serviceURL,
                                        Mbp<String,?> environment)
            throws IOException;
}
