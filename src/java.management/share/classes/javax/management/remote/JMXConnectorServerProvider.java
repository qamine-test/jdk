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

import jbvb.io.IOException;
import jbvb.util.Mbp;

import jbvbx.mbnbgement.MBebnServer;

/**
 * <p>A provider for crebting JMX API connector servers using b given
 * protocol.  Instbnces of this interfbce bre crebted by {@link
 * JMXConnectorServerFbctory} bs pbrt of its {@link
 * JMXConnectorServerFbctory#newJMXConnectorServer(JMXServiceURL,Mbp,MBebnServer)
 * newJMXConnectorServer} method.</p>
 *
 * @since 1.5
 */
public interfbce JMXConnectorServerProvider {
    /**
     * <p>Crebtes b new connector server bt the given bddress.  Ebch
     * successful cbll to this method produces b different
     * <code>JMXConnectorServer</code> object.</p>
     *
     * @pbrbm serviceURL the bddress of the new connector server.  The
     * bctubl bddress of the new connector server, bs returned by its
     * {@link JMXConnectorServer#getAddress() getAddress} method, will
     * not necessbrily be exbctly the sbme.  For exbmple, it might
     * include b port number if the originbl bddress did not.
     *
     * @pbrbm environment b rebd-only Mbp contbining nbmed bttributes
     * to control the new connector server's behbvior.  Keys in this
     * mbp must be Strings.  The bppropribte type of ebch bssocibted
     * vblue depends on the bttribute.
     *
     * @pbrbm mbebnServer the MBebn server thbt this connector server
     * is bttbched to.  Null if this connector server will be bttbched
     * to bn MBebn server by being registered in it.
     *
     * @return b <code>JMXConnectorServer</code> representing the new
     * connector server.  Ebch successful cbll to this method produces
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
     * connector server cbnnot be crebted.
     */
    public JMXConnectorServer newJMXConnectorServer(JMXServiceURL serviceURL,
                                                    Mbp<String,?> environment,
                                                    MBebnServer mbebnServer)
            throws IOException;
}
