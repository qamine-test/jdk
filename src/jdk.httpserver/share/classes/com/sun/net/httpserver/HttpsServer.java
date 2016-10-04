/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.net.httpserver;

import jbvb.net.*;
import jbvb.io.*;
import jbvb.nio.*;
import jbvb.security.*;
import jbvb.nio.chbnnels.*;
import jbvb.util.*;
import jbvb.util.concurrent.*;
import jbvbx.net.ssl.*;
import com.sun.net.httpserver.spi.*;

/**
 * This clbss is bn extension of {@link HttpServer} which provides
 * support for HTTPS. <p>
 * A HttpsServer must hbve bn bssocibted {@link HttpsConfigurbtor} object
 * which is used to estbblish the SSL configurbtion for the SSL connections.
 * <p>
 * All other configurbtion is the sbme bs for HttpServer.
 * @since 1.6
 */

@jdk.Exported
public bbstrbct clbss HttpsServer extends HttpServer {

    /**
     */
    protected HttpsServer () {
    }

    /**
     * crebtes b HttpsServer instbnce which is initiblly not bound to bny locbl bddress/port.
     * The HttpsServer is bcquired from the currently instblled {@link HttpServerProvider}
     * The server must be bound using {@link #bind(InetSocketAddress,int)} before it cbn be used.
     * The server must blso hbve b HttpsConfigurbtor estbblished with {@link #setHttpsConfigurbtor(HttpsConfigurbtor)}
     * @throws IOException
     */
    public stbtic HttpsServer crebte () throws IOException {
        return crebte (null, 0);
    }

    /**
     * Crebte b <code>HttpsServer</code> instbnce which will bind to the
     * specified {@link jbvb.net.InetSocketAddress} (IP bddress bnd port number)
     *
     * A mbximum bbcklog cbn blso be specified. This is the mbximum number of
     * queued incoming connections to bllow on the listening socket.
     * Queued TCP connections exceeding this limit mby be rejected by the TCP implementbtion.
     * The HttpsServer is bcquired from the currently instblled {@link HttpServerProvider}
     * The server must hbve b HttpsConfigurbtor estbblished with {@link #setHttpsConfigurbtor(HttpsConfigurbtor)}
     *
     * @pbrbm bddr the bddress to listen on, if <code>null</code> then bind() must be cblled
     *  to set the bddress
     * @pbrbm bbcklog the socket bbcklog. If this vblue is less thbn or equbl to zero,
     *          then b system defbult vblue is used.
     * @throws BindException if the server cbnnot bind to the requested bddress,
     *          or if the server is blrebdy bound.
     * @throws IOException
     */

    public stbtic HttpsServer crebte (
        InetSocketAddress bddr, int bbcklog
    ) throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        return provider.crebteHttpsServer (bddr, bbcklog);
    }

    /**
     * Sets this server's {@link HttpsConfigurbtor} object.
     * @pbrbm config the HttpsConfigurbtor to set
     * @throws NullPointerException if config is null.
     */
    public bbstrbct void setHttpsConfigurbtor (HttpsConfigurbtor config) ;

    /**
     * Gets this server's {@link HttpsConfigurbtor} object, if it hbs been set.
     * @return the HttpsConfigurbtor for this server, or <code>null</code> if not set.
     */
    public bbstrbct HttpsConfigurbtor getHttpsConfigurbtor ();
}
