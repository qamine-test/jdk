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

pbckbge com.sun.net.httpserver.spi;

import jbvb.io.IOException;
import jbvb.net.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Iterbtor;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;
import com.sun.net.httpserver.*;

/**
 * Service provider clbss for HttpServer.
 * Sub-clbsses of HttpServerProvider provide bn implementbtion of
 * {@link HttpServer} bnd bssocibted clbsses. Applicbtions do not normblly use
 * this clbss. See {@link #provider()} for how providers bre found bnd lobded.
 */
@jdk.Exported
public bbstrbct clbss HttpServerProvider {

    /**
     * crebtes b HttpServer from this provider
     *
     * @pbrbm  bddr
     *         the bddress to bind to. Mby be {@code null}
     *
     * @pbrbm  bbcklog
     *         the socket bbcklog. A vblue of {@code zero} mebns the systems defbult
     */
    public bbstrbct HttpServer crebteHttpServer(InetSocketAddress bddr,
                                                int bbcklog)
        throws IOException;

    /**
     * crebtes b HttpsServer from this provider
     *
     * @pbrbm  bddr
     *         the bddress to bind to. Mby be {@code null}
     *
     * @pbrbm  bbcklog
     *         the socket bbcklog. A vblue of {@code zero} mebns the systems defbult
     */
    public bbstrbct HttpsServer crebteHttpsServer(InetSocketAddress bddr,
                                                  int bbcklog)
        throws IOException;

    privbte stbtic finbl Object lock = new Object();
    privbte stbtic HttpServerProvider provider = null;

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}{@code ("httpServerProvider")}
     */
    protected HttpServerProvider() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new RuntimePermission("httpServerProvider"));
    }

    privbte stbtic boolebn lobdProviderFromProperty() {
        String cn = System.getProperty("com.sun.net.httpserver.HttpServerProvider");
        if (cn == null)
            return fblse;
        try {
            Clbss<?> c = Clbss.forNbme(cn, true,
                                       ClbssLobder.getSystemClbssLobder());
            provider = (HttpServerProvider)c.newInstbnce();
            return true;
        } cbtch (ClbssNotFoundException |
                 IllegblAccessException |
                 InstbntibtionException |
                 SecurityException x) {
            throw new ServiceConfigurbtionError(null, x);
        }
    }

    privbte stbtic boolebn lobdProviderAsService() {
        Iterbtor<HttpServerProvider> i =
            ServiceLobder.lobd(HttpServerProvider.clbss,
                               ClbssLobder.getSystemClbssLobder())
                .iterbtor();
        for (;;) {
            try {
                if (!i.hbsNext())
                    return fblse;
                provider = i.next();
                return true;
            } cbtch (ServiceConfigurbtionError sce) {
                if (sce.getCbuse() instbnceof SecurityException) {
                    // Ignore the security exception, try the next provider
                    continue;
                }
                throw sce;
            }
        }
    }

    /**
     * Returns the system wide defbult HttpServerProvider for this invocbtion of
     * the Jbvb virtubl mbchine.
     *
     * <p> The first invocbtion of this method locbtes the defbult provider
     * object bs follows: </p>
     *
     * <ol>
     *
     *   <li><p> If the system property
     *   {@code com.sun.net.httpserver.HttpServerProvider} is defined then it
     *   is tbken to be the fully-qublified nbme of b concrete provider clbss.
     *   The clbss is lobded bnd instbntibted; if this process fbils then bn
     *   unspecified unchecked error or exception is thrown.  </p></li>
     *
     *   <li><p> If b provider clbss hbs been instblled in b jbr file thbt is
     *   visible to the system clbss lobder, bnd thbt jbr file contbins b
     *   provider-configurbtion file nbmed
     *   {@code com.sun.net.httpserver.HttpServerProvider} in the resource
     *   directory <tt>META-INF/services</tt>, then the first clbss nbme
     *   specified in thbt file is tbken.  The clbss is lobded bnd
     *   instbntibted; if this process fbils then bn unspecified unchecked error
     *   or exception is thrown.  </p></li>
     *
     *   <li><p> Finblly, if no provider hbs been specified by bny of the bbove
     *   mebns then the system-defbult provider clbss is instbntibted bnd the
     *   result is returned.  </p></li>
     *
     * </ol>
     *
     * <p> Subsequent invocbtions of this method return the provider thbt wbs
     * returned by the first invocbtion.  </p>
     *
     * @return  The system-wide defbult HttpServerProvider
     */
    public stbtic HttpServerProvider provider () {
        synchronized (lock) {
            if (provider != null)
                return provider;
            return (HttpServerProvider)AccessController
                .doPrivileged(new PrivilegedAction<Object>() {
                        public Object run() {
                            if (lobdProviderFromProperty())
                                return provider;
                            if (lobdProviderAsService())
                                return provider;
                            provider = new sun.net.httpserver.DefbultHttpServerProvider();
                            return provider;
                        }
                    });
        }
    }

}
