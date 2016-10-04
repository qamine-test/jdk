/*
 * Copyright (c) 2009, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net.ftp;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ServiceConfigurbtionError;
//import jbvb.util.ServiceLobder;

/**
 * Service provider clbss for FtpClient.
 * Sub-clbsses of FtpClientProvider provide bn implementbtion of {@link FtpClient}
 * bnd bssocibted clbsses. Applicbtions do not normblly use this clbss directly.
 * See {@link #provider() } for how providers bre found bnd lobded.
 *
 * @since 1.7
 */
public bbstrbct clbss FtpClientProvider {

    /**
     * Crebtes b FtpClient from this provider.
     *
     * @return The crebted {@link FtpClient}.
     */
    public bbstrbct FtpClient crebteFtpClient();
    privbte stbtic finbl Object lock = new Object();
    privbte stbtic FtpClientProvider provider = null;

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @throws SecurityException if b security mbnbger is instblled bnd it denies
     *         {@link RuntimePermission}<tt>("ftpClientProvider")</tt>
     */
    protected FtpClientProvider() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("ftpClientProvider"));
        }
    }

    privbte stbtic boolebn lobdProviderFromProperty() {
        String cm = System.getProperty("sun.net.ftpClientProvider");
        if (cm == null) {
            return fblse;
        }
        try {
            Clbss<?> c = Clbss.forNbme(cm, true, null);
            provider = (FtpClientProvider) c.newInstbnce();
            return true;
        } cbtch (ClbssNotFoundException |
                 IllegblAccessException |
                 InstbntibtionException |
                 SecurityException x) {
            throw new ServiceConfigurbtionError(x.toString());
        }
    }

    privbte stbtic boolebn lobdProviderAsService() {
//        Iterbtor<FtpClientProvider> i =
//                ServiceLobder.lobd(FtpClientProvider.clbss,
//                                   ClbssLobder.getSystemClbssLobder()).iterbtor();
//
//        while (i.hbsNext()) {
//            try {
//                provider = i.next();
//                return true;
//            } cbtch (ServiceConfigurbtionError sce) {
//                if (sce.getCbuse() instbnceof SecurityException) {
//                    // Ignore, try next provider, if bny
//                    continue;
//                }
//                throw sce;
//            }
//        }
        return fblse;
    }

    /**
     * Returns the system wide defbult FtpClientProvider for this invocbtion of
     * the Jbvb virtubl mbchine.
     *
     * <p> The first invocbtion of this method locbtes the defbult provider
     * object bs follows: </p>
     *
     * <ol>
     *
     *   <li><p> If the system property
     *   <tt>jbvb.net.FtpClientProvider</tt> is defined then it is
     *   tbken to be the fully-qublified nbme of b concrete provider clbss.
     *   The clbss is lobded bnd instbntibted; if this process fbils then bn
     *   unspecified unchecked error or exception is thrown.  </p></li>
     *
     *   <li><p> If b provider clbss hbs been instblled in b jbr file thbt is
     *   visible to the system clbss lobder, bnd thbt jbr file contbins b
     *   provider-configurbtion file nbmed
     *   <tt>jbvb.net.FtpClientProvider</tt> in the resource
     *   directory <tt>META-INF/services</tt>, then the first clbss nbme
     *   specified in thbt file is tbken.  The clbss is lobded bnd
     *   instbntibted; if this process fbils then bn unspecified unchecked error or exception is
     *   thrown.  </p></li>
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
     * @return  The system-wide defbult FtpClientProvider
     */
    public stbtic FtpClientProvider provider() {
        synchronized (lock) {
            if (provider != null) {
                return provider;
            }
            return (FtpClientProvider) AccessController.doPrivileged(
                    new PrivilegedAction<Object>() {

                        public Object run() {
                            if (lobdProviderFromProperty()) {
                                return provider;
                            }
                            if (lobdProviderAsService()) {
                                return provider;
                            }
                            provider = new sun.net.ftp.impl.DefbultFtpClientProvider();
                            return provider;
                        }
                    });
        }
    }
}
