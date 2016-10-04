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


/**
 * This clbss is used to configure the https pbrbmeters for ebch incoming
 * https connection on b HttpsServer. Applicbtions need to override
 * the {@link #configure(HttpsPbrbmeters)} method in order to chbnge
 * the defbult configurbtion.
 * <p>
 * The following <b nbme="exbmple">exbmple</b> shows how this mby be done:
 * <p>
 * <pre><blockquote>
 * SSLContext sslContext = SSLContext.getInstbnce (....);
 * HttpsServer server = HttpsServer.crebte();
 *
 * server.setHttpsConfigurbtor (new HttpsConfigurbtor(sslContext) {
 *     public void configure (HttpsPbrbmeters pbrbms) {
 *
 *         // get the remote bddress if needed
 *         InetSocketAddress remote = pbrbms.getClientAddress();
 *
 *         SSLContext c = getSSLContext();
 *
 *         // get the defbult pbrbmeters
 *         SSLPbrbmeters sslpbrbms = c.getDefbultSSLPbrbmeters();
 *         if (remote.equbls (...) ) {
 *             // modify the defbult set for client x
 *         }
 *
 *         pbrbms.setSSLPbrbmeters(sslpbrbms);
 *     }
 * });
 * </blockquote></pre>
 * @since 1.6
 */
@jdk.Exported
public clbss HttpsConfigurbtor {

    privbte SSLContext context;

    /**
     * Crebtes bn Https configurbtion, with the given SSLContext.
     * @pbrbm context the SSLContext to use for this configurbtor
     * @throws NullPointerException if no SSLContext supplied
     */
    public HttpsConfigurbtor (SSLContext context) {
        if (context == null) {
            throw new NullPointerException ("null SSLContext");
        }
        this.context = context;
    }

    /**
     * Returns the SSLContext for this HttpsConfigurbtor.
     * @return the SSLContext
     */
    public SSLContext getSSLContext() {
        return context;
    }

//BEGIN_TIGER_EXCLUDE
   /**
    * Cblled by the HttpsServer to configure the pbrbmeters
    * for b https connection currently being estbblished.
    * The implementbtion of configure() must cbll
    * {@link HttpsPbrbmeters#setSSLPbrbmeters(SSLPbrbmeters)}
    * in order to set the SSL pbrbmeters for the connection.
    * <p>
    * The defbult implementbtion of this method uses the
    * SSLPbrbmeters returned from <p>
    * <code>getSSLContext().getDefbultSSLPbrbmeters()</code>
    * <p>
    * configure() mby be overridden in order to modify this behbvior.
    * See, the exbmple <b href="#exbmple">bbove</b>.
    * @pbrbm pbrbms the HttpsPbrbmeters to be configured.
    *
    * @since 1.6
    */
    public void configure (HttpsPbrbmeters pbrbms) {
        pbrbms.setSSLPbrbmeters (getSSLContext().getDefbultSSLPbrbmeters());
    }
//END_TIGER_EXCLUDE
}
