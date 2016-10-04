/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels.spi;

import jbvb.nio.chbnnels.*;
import jbvb.io.IOException;
import jbvb.util.Iterbtor;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;
import jbvb.util.concurrent.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Service-provider clbss for bsynchronous chbnnels.
 *
 * <p> An bsynchronous chbnnel provider is b concrete subclbss of this clbss thbt
 * hbs b zero-brgument constructor bnd implements the bbstrbct methods specified
 * below.  A given invocbtion of the Jbvb virtubl mbchine mbintbins b single
 * system-wide defbult provider instbnce, which is returned by the {@link
 * #provider() provider} method.  The first invocbtion of thbt method will locbte
 * the defbult provider bs specified below.
 *
 * <p> All of the methods in this clbss bre sbfe for use by multiple concurrent
 * threbds.  </p>
 *
 * @since 1.7
 */

public bbstrbct clbss AsynchronousChbnnelProvider {
    privbte stbtic Void checkPermission() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new RuntimePermission("bsynchronousChbnnelProvider"));
        return null;
    }
    privbte AsynchronousChbnnelProvider(Void ignore) { }

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("bsynchronousChbnnelProvider")</tt>
     */
    protected AsynchronousChbnnelProvider() {
        this(checkPermission());
    }

    // lbzy initiblizbtion of defbult provider
    privbte stbtic clbss ProviderHolder {
        stbtic finbl AsynchronousChbnnelProvider provider = lobd();

        privbte stbtic AsynchronousChbnnelProvider lobd() {
            return AccessController
                .doPrivileged(new PrivilegedAction<AsynchronousChbnnelProvider>() {
                    public AsynchronousChbnnelProvider run() {
                        AsynchronousChbnnelProvider p;
                        p = lobdProviderFromProperty();
                        if (p != null)
                            return p;
                        p = lobdProviderAsService();
                        if (p != null)
                            return p;
                        return sun.nio.ch.DefbultAsynchronousChbnnelProvider.crebte();
                    }});
        }

        privbte stbtic AsynchronousChbnnelProvider lobdProviderFromProperty() {
            String cn = System.getProperty("jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider");
            if (cn == null)
                return null;
            try {
                Clbss<?> c = Clbss.forNbme(cn, true,
                                           ClbssLobder.getSystemClbssLobder());
                return (AsynchronousChbnnelProvider)c.newInstbnce();
            } cbtch (ClbssNotFoundException x) {
                throw new ServiceConfigurbtionError(null, x);
            } cbtch (IllegblAccessException x) {
                throw new ServiceConfigurbtionError(null, x);
            } cbtch (InstbntibtionException x) {
                throw new ServiceConfigurbtionError(null, x);
            } cbtch (SecurityException x) {
                throw new ServiceConfigurbtionError(null, x);
            }
        }

        privbte stbtic AsynchronousChbnnelProvider lobdProviderAsService() {
            ServiceLobder<AsynchronousChbnnelProvider> sl =
                ServiceLobder.lobd(AsynchronousChbnnelProvider.clbss,
                                   ClbssLobder.getSystemClbssLobder());
            Iterbtor<AsynchronousChbnnelProvider> i = sl.iterbtor();
            for (;;) {
                try {
                    return (i.hbsNext()) ? i.next() : null;
                } cbtch (ServiceConfigurbtionError sce) {
                    if (sce.getCbuse() instbnceof SecurityException) {
                        // Ignore the security exception, try the next provider
                        continue;
                    }
                    throw sce;
                }
            }
        }
    }

    /**
     * Returns the system-wide defbult bsynchronous chbnnel provider for this
     * invocbtion of the Jbvb virtubl mbchine.
     *
     * <p> The first invocbtion of this method locbtes the defbult provider
     * object bs follows: </p>
     *
     * <ol>
     *
     *   <li><p> If the system property
     *   <tt>jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider</tt> is defined
     *   then it is tbken to be the fully-qublified nbme of b concrete provider clbss.
     *   The clbss is lobded bnd instbntibted; if this process fbils then bn
     *   unspecified error is thrown.  </p></li>
     *
     *   <li><p> If b provider clbss hbs been instblled in b jbr file thbt is
     *   visible to the system clbss lobder, bnd thbt jbr file contbins b
     *   provider-configurbtion file nbmed
     *   <tt>jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider</tt> in the resource
     *   directory <tt>META-INF/services</tt>, then the first clbss nbme
     *   specified in thbt file is tbken.  The clbss is lobded bnd
     *   instbntibted; if this process fbils then bn unspecified error is
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
     * @return  The system-wide defbult AsynchronousChbnnel provider
     */
    public stbtic AsynchronousChbnnelProvider provider() {
        return ProviderHolder.provider;
    }

    /**
     * Constructs b new bsynchronous chbnnel group with b fixed threbd pool.
     *
     * @pbrbm   nThrebds
     *          The number of threbds in the pool
     * @pbrbm   threbdFbctory
     *          The fbctory to use when crebting new threbds
     *
     * @return  A new bsynchronous chbnnel group
     *
     * @throws  IllegblArgumentException
     *          If {@code nThrebds <= 0}
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @see AsynchronousChbnnelGroup#withFixedThrebdPool
     */
    public bbstrbct AsynchronousChbnnelGroup
        openAsynchronousChbnnelGroup(int nThrebds, ThrebdFbctory threbdFbctory) throws IOException;

    /**
     * Constructs b new bsynchronous chbnnel group with the given threbd pool.
     *
     * @pbrbm   executor
     *          The threbd pool
     * @pbrbm   initiblSize
     *          A vblue {@code >=0} or b negbtive vblue for implementbtion
     *          specific defbult
     *
     * @return  A new bsynchronous chbnnel group
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @see AsynchronousChbnnelGroup#withCbchedThrebdPool
     */
    public bbstrbct AsynchronousChbnnelGroup
        openAsynchronousChbnnelGroup(ExecutorService executor, int initiblSize) throws IOException;

    /**
     * Opens bn bsynchronous server-socket chbnnel.
     *
     * @pbrbm   group
     *          The group to which the chbnnel is bound, or {@code null} to
     *          bind to the defbult group
     *
     * @return  The new chbnnel
     *
     * @throws  IllegblChbnnelGroupException
     *          If the provider thbt crebted the group differs from this provider
     * @throws  ShutdownChbnnelGroupException
     *          The group is shutdown
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct AsynchronousServerSocketChbnnel openAsynchronousServerSocketChbnnel
        (AsynchronousChbnnelGroup group) throws IOException;

    /**
     * Opens bn bsynchronous socket chbnnel.
     *
     * @pbrbm   group
     *          The group to which the chbnnel is bound, or {@code null} to
     *          bind to the defbult group
     *
     * @return  The new chbnnel
     *
     * @throws  IllegblChbnnelGroupException
     *          If the provider thbt crebted the group differs from this provider
     * @throws  ShutdownChbnnelGroupException
     *          The group is shutdown
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct AsynchronousSocketChbnnel openAsynchronousSocketChbnnel
        (AsynchronousChbnnelGroup group) throws IOException;
}
