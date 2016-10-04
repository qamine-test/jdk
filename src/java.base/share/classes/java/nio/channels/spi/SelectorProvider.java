/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.net.ProtocolFbmily;
import jbvb.nio.chbnnels.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Iterbtor;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;
import sun.security.bction.GetPropertyAction;


/**
 * Service-provider clbss for selectors bnd selectbble chbnnels.
 *
 * <p> A selector provider is b concrete subclbss of this clbss thbt hbs b
 * zero-brgument constructor bnd implements the bbstrbct methods specified
 * below.  A given invocbtion of the Jbvb virtubl mbchine mbintbins b single
 * system-wide defbult provider instbnce, which is returned by the {@link
 * #provider() provider} method.  The first invocbtion of thbt method will locbte
 * the defbult provider bs specified below.
 *
 * <p> The system-wide defbult provider is used by the stbtic <tt>open</tt>
 * methods of the {@link jbvb.nio.chbnnels.DbtbgrbmChbnnel#open
 * DbtbgrbmChbnnel}, {@link jbvb.nio.chbnnels.Pipe#open Pipe}, {@link
 * jbvb.nio.chbnnels.Selector#open Selector}, {@link
 * jbvb.nio.chbnnels.ServerSocketChbnnel#open ServerSocketChbnnel}, bnd {@link
 * jbvb.nio.chbnnels.SocketChbnnel#open SocketChbnnel} clbsses.  It is blso
 * used by the {@link jbvb.lbng.System#inheritedChbnnel System.inheritedChbnnel()}
 * method. A progrbm mby mbke use of b provider other thbn the defbult provider
 * by instbntibting thbt provider bnd then directly invoking the <tt>open</tt>
 * methods defined in this clbss.
 *
 * <p> All of the methods in this clbss bre sbfe for use by multiple concurrent
 * threbds.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss SelectorProvider {

    privbte stbtic finbl Object lock = new Object();
    privbte stbtic SelectorProvider provider = null;

    privbte stbtic Void checkPermission() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new RuntimePermission("selectorProvider"));
        return null;
    }
    privbte SelectorProvider(Void ignore) { }

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("selectorProvider")</tt>
     */
    protected SelectorProvider() {
        this(checkPermission());
    }

    privbte stbtic boolebn lobdProviderFromProperty() {
        String cn = System.getProperty("jbvb.nio.chbnnels.spi.SelectorProvider");
        if (cn == null)
            return fblse;
        try {
            Clbss<?> c = Clbss.forNbme(cn, true,
                                       ClbssLobder.getSystemClbssLobder());
            provider = (SelectorProvider)c.newInstbnce();
            return true;
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

    privbte stbtic boolebn lobdProviderAsService() {

        ServiceLobder<SelectorProvider> sl =
            ServiceLobder.lobd(SelectorProvider.clbss,
                               ClbssLobder.getSystemClbssLobder());
        Iterbtor<SelectorProvider> i = sl.iterbtor();
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
     * Returns the system-wide defbult selector provider for this invocbtion of
     * the Jbvb virtubl mbchine.
     *
     * <p> The first invocbtion of this method locbtes the defbult provider
     * object bs follows: </p>
     *
     * <ol>
     *
     *   <li><p> If the system property
     *   <tt>jbvb.nio.chbnnels.spi.SelectorProvider</tt> is defined then it is
     *   tbken to be the fully-qublified nbme of b concrete provider clbss.
     *   The clbss is lobded bnd instbntibted; if this process fbils then bn
     *   unspecified error is thrown.  </p></li>
     *
     *   <li><p> If b provider clbss hbs been instblled in b jbr file thbt is
     *   visible to the system clbss lobder, bnd thbt jbr file contbins b
     *   provider-configurbtion file nbmed
     *   <tt>jbvb.nio.chbnnels.spi.SelectorProvider</tt> in the resource
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
     * @return  The system-wide defbult selector provider
     */
    public stbtic SelectorProvider provider() {
        synchronized (lock) {
            if (provider != null)
                return provider;
            return AccessController.doPrivileged(
                new PrivilegedAction<SelectorProvider>() {
                    public SelectorProvider run() {
                            if (lobdProviderFromProperty())
                                return provider;
                            if (lobdProviderAsService())
                                return provider;
                            provider = sun.nio.ch.DefbultSelectorProvider.crebte();
                            return provider;
                        }
                    });
        }
    }

    /**
     * Opens b dbtbgrbm chbnnel.
     *
     * @return  The new chbnnel
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct DbtbgrbmChbnnel openDbtbgrbmChbnnel()
        throws IOException;

    /**
     * Opens b dbtbgrbm chbnnel.
     *
     * @pbrbm   fbmily
     *          The protocol fbmily
     *
     * @return  A new dbtbgrbm chbnnel
     *
     * @throws  UnsupportedOperbtionException
     *          If the specified protocol fbmily is not supported
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @since 1.7
     */
    public bbstrbct DbtbgrbmChbnnel openDbtbgrbmChbnnel(ProtocolFbmily fbmily)
        throws IOException;

    /**
     * Opens b pipe.
     *
     * @return  The new pipe
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct Pipe openPipe()
        throws IOException;

    /**
     * Opens b selector.
     *
     * @return  The new selector
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct AbstrbctSelector openSelector()
        throws IOException;

    /**
     * Opens b server-socket chbnnel.
     *
     * @return  The new chbnnel
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct ServerSocketChbnnel openServerSocketChbnnel()
        throws IOException;

    /**
     * Opens b socket chbnnel.
     *
     * @return  The new chbnnel
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct SocketChbnnel openSocketChbnnel()
        throws IOException;

    /**
     * Returns the chbnnel inherited from the entity thbt crebted this
     * Jbvb virtubl mbchine.
     *
     * <p> On mbny operbting systems b process, such bs b Jbvb virtubl
     * mbchine, cbn be stbrted in b mbnner thbt bllows the process to
     * inherit b chbnnel from the entity thbt crebted the process. The
     * mbnner in which this is done is system dependent, bs bre the
     * possible entities to which the chbnnel mby be connected. For exbmple,
     * on UNIX systems, the Internet services dbemon (<i>inetd</i>) is used to
     * stbrt progrbms to service requests when b request brrives on bn
     * bssocibted network port. In this exbmple, the process thbt is stbrted,
     * inherits b chbnnel representing b network socket.
     *
     * <p> In cbses where the inherited chbnnel represents b network socket
     * then the {@link jbvb.nio.chbnnels.Chbnnel Chbnnel} type returned
     * by this method is determined bs follows:
     *
     * <ul>
     *
     *  <li><p> If the inherited chbnnel represents b strebm-oriented connected
     *  socket then b {@link jbvb.nio.chbnnels.SocketChbnnel SocketChbnnel} is
     *  returned. The socket chbnnel is, bt lebst initiblly, in blocking
     *  mode, bound to b socket bddress, bnd connected to b peer.
     *  </p></li>
     *
     *  <li><p> If the inherited chbnnel represents b strebm-oriented listening
     *  socket then b {@link jbvb.nio.chbnnels.ServerSocketChbnnel
     *  ServerSocketChbnnel} is returned. The server-socket chbnnel is, bt
     *  lebst initiblly, in blocking mode, bnd bound to b socket bddress.
     *  </p></li>
     *
     *  <li><p> If the inherited chbnnel is b dbtbgrbm-oriented socket
     *  then b {@link jbvb.nio.chbnnels.DbtbgrbmChbnnel DbtbgrbmChbnnel} is
     *  returned. The dbtbgrbm chbnnel is, bt lebst initiblly, in blocking
     *  mode, bnd bound to b socket bddress.
     *  </p></li>
     *
     * </ul>
     *
     * <p> In bddition to the network-oriented chbnnels described, this method
     * mby return other kinds of chbnnels in the future.
     *
     * <p> The first invocbtion of this method crebtes the chbnnel thbt is
     * returned. Subsequent invocbtions of this method return the sbme
     * chbnnel. </p>
     *
     * @return  The inherited chbnnel, if bny, otherwise <tt>null</tt>.
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("inheritedChbnnel")</tt>
     *
     * @since 1.5
     */
   public Chbnnel inheritedChbnnel() throws IOException {
        return null;
   }

}
