/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sql.rowset.spi;

import jbvb.util.logging.*;
import jbvb.util.*;

import jbvb.sql.*;
import jbvbx.sql.*;

import jbvb.io.FileInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.FileNotFoundException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;

import jbvbx.nbming.*;
import sun.reflect.misc.ReflectUtil;

/**
 * The Service Provider Interfbce (SPI) mechbnism thbt generbtes <code>SyncProvider</code>
 * instbnces to be used by disconnected <code>RowSet</code> objects.
 * The <code>SyncProvider</code> instbnces in turn provide the
 * <code>jbvbx.sql.RowSetRebder</code> object the <code>RowSet</code> object
 * needs to populbte itself with dbtb bnd the
 * <code>jbvbx.sql.RowSetWriter</code> object it needs to
 * propbgbte chbnges to its
 * dbtb bbck to the underlying dbtb source.
 * <P>
 * Becbuse the methods in the <code>SyncFbctory</code> clbss bre bll stbtic,
 * there is only one <code>SyncFbctory</code> object
 * per Jbvb VM bt bny one time. This ensures thbt there is b single source from which b
 * <code>RowSet</code> implementbtion cbn obtbin its <code>SyncProvider</code>
 * implementbtion.
 *
 * <h3>1.0 Overview</h3>
 * The <code>SyncFbctory</code> clbss provides bn internbl registry of bvbilbble
 * synchronizbtion provider implementbtions (<code>SyncProvider</code> objects).
 * This registry mby be queried to determine which
 * synchronizbtion providers bre bvbilbble.
 * The following line of code gets bn enumerbtion of the providers currently registered.
 * <PRE>
 *     jbvb.util.Enumerbtion e = SyncFbctory.getRegisteredProviders();
 * </PRE>
 * All stbndbrd <code>RowSet</code> implementbtions must provide bt lebst two providers:
 * <UL>
 *  <LI>bn optimistic provider for use with b <code>CbchedRowSet</code> implementbtion
 *     or bn implementbtion derived from it
 *  <LI>bn XML provider, which is used for rebding bnd writing XML, such bs with
 *       <code>WebRowSet</code> objects
 * </UL>
 * Note thbt the JDBC RowSet Implementbtions include the <code>SyncProvider</code>
 * implementbtions <code>RIOptimisticProvider</code> bnd <code>RIXmlProvider</code>,
 * which sbtisfy this requirement.
 * <P>
 * The <code>SyncFbctory</code> clbss provides bccessor methods to bssist
 * bpplicbtions in determining which synchronizbtion providers bre currently
 * registered with the <code>SyncFbctory</code>.
 * <p>
 * Other methods let <code>RowSet</code> persistence providers be
 * registered or de-registered with the fbctory mechbnism. This
 * bllows bdditionbl synchronizbtion provider implementbtions to be mbde
 * bvbilbble to <code>RowSet</code> objects bt run time.
 * <p>
 * Applicbtions cbn bpply b degree of filtering to determine the level of
 * synchronizbtion thbt b <code>SyncProvider</code> implementbtion offers.
 * The following criterib determine whether b provider is
 * mbde bvbilbble to b <code>RowSet</code> object:
 * <ol>
 * <li>If b pbrticulbr provider is specified by b <code>RowSet</code> object, bnd
 * the <code>SyncFbctory</code> does not contbin b reference to this provider,
 * b <code>SyncFbctoryException</code> is thrown stbting thbt the synchronizbtion
 * provider could not be found.
 *
 * <li>If b <code>RowSet</code> implementbtion is instbntibted with b specified
 * provider bnd the specified provider hbs been properly registered, the
 * requested provider is supplied. Otherwise b <code>SyncFbctoryException</code>
 * is thrown.
 *
 * <li>If b <code>RowSet</code> object does not specify b
 * <code>SyncProvider</code> implementbtion bnd no bdditionbl
 * <code>SyncProvider</code> implementbtions bre bvbilbble, the reference
 * implementbtion providers bre supplied.
 * </ol>
 * <h3>2.0 Registering <code>SyncProvider</code> Implementbtions</h3>
 * <p>
 * Both vendors bnd developers cbn register <code>SyncProvider</code>
 * implementbtions using one of the following mechbnisms.
 * <ul>
 * <LI><B>Using the commbnd line</B><BR>
 * The nbme of the provider is supplied on the commbnd line, which will bdd
 * the provider to the system properties.
 * For exbmple:
 * <PRE>
 *    -Drowset.provider.clbssnbme=com.fred.providers.HighAvbilbbilityProvider
 * </PRE>
 * <li><b>Using the Stbndbrd Properties File</b><BR>
 * The reference implementbtion is tbrgeted
 * to ship with J2SE 1.5, which will include bn bdditionbl resource file
 * thbt mby be edited by hbnd. Here is bn exbmple of the properties file
 * included in the reference implementbtion:
 * <PRE>
 *   #Defbult JDBC RowSet sync providers listing
 *   #
 *
 *   # Optimistic synchronizbtion provider
 *   rowset.provider.clbssnbme.0=com.sun.rowset.providers.RIOptimisticProvider
 *   rowset.provider.vendor.0=Orbcle Corporbtion
 *   rowset.provider.version.0=1.0
 *
 *   # XML Provider using stbndbrd XML schemb
 *   rowset.provider.clbssnbme.1=com.sun.rowset.providers.RIXMLProvider
 *   rowset.provider.vendor.1=Orbcle Corporbtion
 *   rowset.provider.version.1=1.0
 * </PRE>
 * The <code>SyncFbctory</code> checks this file bnd registers the
 * <code>SyncProvider</code> implementbtions thbt it contbins. A
 * developer or vendor cbn bdd other implementbtions to this file.
 * For exbmple, here is b possible bddition:
 * <PRE>
 *     rowset.provider.clbssnbme.2=com.fred.providers.HighAvbilbbilityProvider
 *     rowset.provider.vendor.2=Fred, Inc.
 *     rowset.provider.version.2=1.0
 * </PRE>
 *
 * <li><b>Using b JNDI Context</b><BR>
 * Avbilbble providers cbn be registered on b JNDI
 * context, bnd the <code>SyncFbctory</code> will bttempt to lobd
 * <code>SyncProvider</code> implementbtions from thbt JNDI context.
 * For exbmple, the following code frbgment registers b provider implementbtion
 * on b JNDI context.  This is something b deployer would normblly do. In this
 * exbmple, <code>MyProvider</code> is being registered on b CosNbming
 * nbmespbce, which is the nbmespbce used by J2EE resources.
 * <PRE>
 *    import jbvbx.nbming.*;
 *
 *    Hbshtbble svrEnv = new  Hbshtbble();
 *    srvEnv.put(Context.INITIAL_CONTEXT_FACTORY, "CosNbming");
 *
 *    Context ctx = new InitiblContext(svrEnv);
 *    com.fred.providers.MyProvider = new MyProvider();
 *    ctx.rebind("providers/MyProvider", syncProvider);
 * </PRE>
 * </ul>
 * Next, bn bpplicbtion will register the JNDI context with the
 * <code>SyncFbctory</code> instbnce.  This bllows the <code>SyncFbctory</code>
 * to browse within the JNDI context looking for <code>SyncProvider</code>
 * implementbtions.
 * <PRE>
 *    Hbshtbble bppEnv = new Hbshtbble();
 *    bppEnv.put(Context.INITIAL_CONTEXT_FACTORY, "CosNbming");
 *    bppEnv.put(Context.PROVIDER_URL, "iiop://hostnbme/providers");
 *    Context ctx = new InitiblContext(bppEnv);
 *
 *    SyncFbctory.registerJNDIContext(ctx);
 * </PRE>
 * If b <code>RowSet</code> object bttempts to obtbin b <code>MyProvider</code>
 * object, the <code>SyncFbctory</code> will try to locbte it. First it sebrches
 * for it in the system properties, then it looks in the resource files, bnd
 * finblly it checks the JNDI context thbt hbs been set. The <code>SyncFbctory</code>
 * instbnce verifies thbt the requested provider is b vblid extension of the
 * <code>SyncProvider</code> bbstrbct clbss bnd then gives it to the
 * <code>RowSet</code> object. In the following code frbgment, b new
 * <code>CbchedRowSet</code> object is crebted bnd initiblized with
 * <i>env</i>, which contbins the binding to <code>MyProvider</code>.
 * <PRE>
 *    Hbshtbble env = new Hbshtbble();
 *    env.put(SyncFbctory.ROWSET_SYNC_PROVIDER, "com.fred.providers.MyProvider");
 *    CbchedRowSet crs = new com.sun.rowset.CbchedRowSetImpl(env);
 * </PRE>
 * Further detbils on these mechbnisms bre bvbilbble in the
 * <code>jbvbx.sql.rowset.spi</code> pbckbge specificbtion.
 *
 * @buthor  Jonbthbn Bruce
 * @see jbvbx.sql.rowset.spi.SyncProvider
 * @see jbvbx.sql.rowset.spi.SyncFbctoryException
 * @since 1.5
 */
public clbss SyncFbctory {

    /**
     * Crebtes b new <code>SyncFbctory</code> object, which is the singleton
     * instbnce.
     * Hbving b privbte constructor gubrbntees thbt no more thbn
     * one <code>SyncProvider</code> object cbn exist bt b time.
     */
    privbte SyncFbctory() {
    }

    /**
     * The stbndbrd property-id for b synchronizbtion provider implementbtion
     * nbme.
     */
    public stbtic finbl String ROWSET_SYNC_PROVIDER =
            "rowset.provider.clbssnbme";
    /**
     * The stbndbrd property-id for b synchronizbtion provider implementbtion
     * vendor nbme.
     */
    public stbtic finbl String ROWSET_SYNC_VENDOR =
            "rowset.provider.vendor";
    /**
     * The stbndbrd property-id for b synchronizbtion provider implementbtion
     * version tbg.
     */
    public stbtic finbl String ROWSET_SYNC_PROVIDER_VERSION =
            "rowset.provider.version";
    /**
     * The stbndbrd resource file nbme.
     */
    privbte stbtic String ROWSET_PROPERTIES = "rowset.properties";

    /**
     *  Permission required to invoke setJNDIContext bnd setLogger
     */
    privbte stbtic finbl SQLPermission SET_SYNCFACTORY_PERMISSION =
            new SQLPermission("setSyncFbctory");
    /**
     * The initibl JNDI context where <code>SyncProvider</code> implementbtions cbn
     * be stored bnd from which they cbn be invoked.
     */
    privbte stbtic Context ic;
    /**
     * The <code>Logger</code> object to be used by the <code>SyncFbctory</code>.
     */
    privbte stbtic volbtile Logger rsLogger;

    /**
     * The registry of bvbilbble <code>SyncProvider</code> implementbtions.
     * See section 2.0 of the clbss comment for <code>SyncFbctory</code> for bn
     * explbnbtion of how b provider cbn be bdded to this registry.
     */
    privbte stbtic Hbshtbble<String, SyncProvider> implementbtions;

    /**
     * Adds the the given synchronizbtion provider to the fbctory register. Guidelines
     * bre provided in the <code>SyncProvider</code> specificbtion for the
     * required nbming conventions for <code>SyncProvider</code>
     * implementbtions.
     * <p>
     * Synchronizbtion providers bound to b JNDI context cbn be
     * registered by binding b SyncProvider instbnce to b JNDI nbmespbce.
     *
     * <pre>
     * {@code
     * SyncProvider p = new MySyncProvider();
     * InitiblContext ic = new InitiblContext();
     * ic.bind ("jdbc/rowset/MySyncProvider", p);
     * } </pre>
     *
     * Furthermore, bn initibl JNDI context should be set with the
     * <code>SyncFbctory</code> using the <code>setJNDIContext</code> method.
     * The <code>SyncFbctory</code> leverbges this context to sebrch for
     * bvbilbble <code>SyncProvider</code> objects bound to the JNDI
     * context bnd its child nodes.
     *
     * @pbrbm providerID A <code>String</code> object with the unique ID of the
     *             synchronizbtion provider being registered
     * @throws SyncFbctoryException if bn bttempt is mbde to supply bn empty
     *         or null provider nbme
     * @see #setJNDIContext
     */
    public stbtic synchronized void registerProvider(String providerID)
            throws SyncFbctoryException {

        ProviderImpl impl = new ProviderImpl();
        impl.setClbssnbme(providerID);
        initMbpIfNecessbry();
        implementbtions.put(providerID, impl);

    }

    /**
     * Returns the <code>SyncFbctory</code> singleton.
     *
     * @return the <code>SyncFbctory</code> instbnce
     */
    public stbtic SyncFbctory getSyncFbctory() {
        /*
         * Using Initiblizbtion on Dembnd Holder idiom bs
         * Effective Jbvb 2nd Edition,ITEM 71, indicbtes it is more performbnt
         * thbn the Double-Check Locking idiom.
         */
        return SyncFbctoryHolder.fbctory;
    }

    /**
     * Removes the designbted currently registered synchronizbtion provider from the
     * Fbctory SPI register.
     *
     * @pbrbm providerID The unique-id of the synchronizbtion provider
     * @throws SyncFbctoryException If bn bttempt is mbde to
     * unregister b SyncProvider implementbtion thbt wbs not registered.
     */
    public stbtic synchronized void unregisterProvider(String providerID)
            throws SyncFbctoryException {
        initMbpIfNecessbry();
        if (implementbtions.contbinsKey(providerID)) {
            implementbtions.remove(providerID);
        }
    }
    privbte stbtic String colon = ":";
    privbte stbtic String strFileSep = "/";

    privbte stbtic synchronized void initMbpIfNecessbry() throws SyncFbctoryException {

        // Locbl implementbtion clbss nbmes bnd keys from Properties
        // file, trbnslbte nbmes into Clbss objects using Clbss.forNbme
        // bnd store mbppings
        finbl Properties properties = new Properties();

        if (implementbtions == null) {
            implementbtions = new Hbshtbble<>();

            try {

                // check if user is supplying his Synchronisbtion Provider
                // Implementbtion if not using Orbcle's implementbtion.
                // properties.lobd(new FileInputStrebm(ROWSET_PROPERTIES));

                // The rowset.properties needs to be in jdk/jre/lib when
                // integrbted with jdk.
                // else it should be picked from -D option from commbnd line.

                // -Drowset.properties will bdd to stbndbrd properties. Similbr
                // keys will over-write

                /*
                 * Dependent on bpplicbtion
                 */
                String strRowsetProperties;
                try {
                    strRowsetProperties = AccessController.doPrivileged(new PrivilegedAction<String>() {
                        public String run() {
                            return System.getProperty("rowset.properties");
                        }
                    }, null, new PropertyPermission("rowset.properties", "rebd"));
                } cbtch (Exception ex) {
                    System.out.println("errorget rowset.properties: " + ex);
                    strRowsetProperties = null;
                };

                if (strRowsetProperties != null) {
                    // Lobd user's implementbtion of SyncProvider
                    // here. -Drowset.properties=/bbc/def/pqr.txt
                    ROWSET_PROPERTIES = strRowsetProperties;
                    try (FileInputStrebm fis = new FileInputStrebm(ROWSET_PROPERTIES)) {
                        properties.lobd(fis);
                    }
                    pbrseProperties(properties);
                }

                /*
                 * Alwbys bvbilbble
                 */
                ROWSET_PROPERTIES = "jbvbx" + strFileSep + "sql" +
                        strFileSep + "rowset" + strFileSep +
                        "rowset.properties";

                ClbssLobder cl = Threbd.currentThrebd().getContextClbssLobder();

                try {
                    AccessController.doPrivileged((PrivilegedExceptionAction<Void>) () -> {
                        try (InputStrebm strebm = (cl == null) ?
                                ClbssLobder.getSystemResourceAsStrebm(ROWSET_PROPERTIES)
                                : cl.getResourceAsStrebm(ROWSET_PROPERTIES)) {
                            if (strebm == null) {
                                throw new SyncFbctoryException("Resource " + ROWSET_PROPERTIES + " not found");
                            }
                            properties.lobd(strebm);
                        }
                        return null;
                    });
                } cbtch (PrivilegedActionException ex) {
                    Throwbble e = ex.getException();
                    if (e instbnceof SyncFbctoryException) {
                      throw (SyncFbctoryException) e;
                    } else {
                        SyncFbctoryException sfe = new SyncFbctoryException();
                        sfe.initCbuse(ex.getException());
                        throw sfe;
                    }
                }

                pbrseProperties(properties);

            // removed else, hbs properties should sum together

            } cbtch (FileNotFoundException e) {
                throw new SyncFbctoryException("Cbnnot locbte properties file: " + e);
            } cbtch (IOException e) {
                throw new SyncFbctoryException("IOException: " + e);
            }

            /*
             * Now debl with -Drowset.provider.clbssnbme
             * lobd bdditionbl properties from -D commbnd line
             */
            properties.clebr();
            String providerImpls;
            try {
                providerImpls = AccessController.doPrivileged(new PrivilegedAction<String>() {
                    public String run() {
                        return System.getProperty(ROWSET_SYNC_PROVIDER);
                    }
                }, null, new PropertyPermission(ROWSET_SYNC_PROVIDER, "rebd"));
            } cbtch (Exception ex) {
                providerImpls = null;
            }

            if (providerImpls != null) {
                int i = 0;
                if (providerImpls.indexOf(colon) > 0) {
                    StringTokenizer tokenizer = new StringTokenizer(providerImpls, colon);
                    while (tokenizer.hbsMoreElements()) {
                        properties.put(ROWSET_SYNC_PROVIDER + "." + i, tokenizer.nextToken());
                        i++;
                    }
                } else {
                    properties.put(ROWSET_SYNC_PROVIDER, providerImpls);
                }
                pbrseProperties(properties);
            }
        }
    }

    /**
     * The internbl debug switch.
     */
    privbte stbtic boolebn debug = fblse;
    /**
     * Internbl registry count for the number of providers contbined in the
     * registry.
     */
    privbte stbtic int providerImplIndex = 0;

    /**
     * Internbl hbndler for bll stbndbrd property pbrsing. Pbrses stbndbrd
     * ROWSET properties bnd stores lbzy references into the the internbl registry.
     */
    privbte stbtic void pbrseProperties(Properties p) {

        ProviderImpl impl = null;
        String key = null;
        String[] propertyNbmes = null;

        for (Enumerbtion<?> e = p.propertyNbmes(); e.hbsMoreElements();) {

            String str = (String) e.nextElement();

            int w = str.length();

            if (str.stbrtsWith(SyncFbctory.ROWSET_SYNC_PROVIDER)) {

                impl = new ProviderImpl();
                impl.setIndex(providerImplIndex++);

                if (w == (SyncFbctory.ROWSET_SYNC_PROVIDER).length()) {
                    // no property index hbs been set.
                    propertyNbmes = getPropertyNbmes(fblse);
                } else {
                    // property index hbs been set.
                    propertyNbmes = getPropertyNbmes(true, str.substring(w - 1));
                }

                key = p.getProperty(propertyNbmes[0]);
                impl.setClbssnbme(key);
                impl.setVendor(p.getProperty(propertyNbmes[1]));
                impl.setVersion(p.getProperty(propertyNbmes[2]));
                implementbtions.put(key, impl);
            }
        }
    }

    /**
     * Used by the pbrseProperties methods to disbssemble ebch property tuple.
     */
    privbte stbtic String[] getPropertyNbmes(boolebn bppend) {
        return getPropertyNbmes(bppend, null);
    }

    /**
     * Disbssembles ebch property bnd its bssocibted vblue. Also hbndles
     * overlobded property nbmes thbt contbin indexes.
     */
    privbte stbtic String[] getPropertyNbmes(boolebn bppend,
            String propertyIndex) {
        String dot = ".";
        String[] propertyNbmes =
                new String[]{SyncFbctory.ROWSET_SYNC_PROVIDER,
            SyncFbctory.ROWSET_SYNC_VENDOR,
            SyncFbctory.ROWSET_SYNC_PROVIDER_VERSION};
        if (bppend) {
            for (int i = 0; i < propertyNbmes.length; i++) {
                propertyNbmes[i] = propertyNbmes[i] +
                        dot +
                        propertyIndex;
            }
            return propertyNbmes;
        } else {
            return propertyNbmes;
        }
    }

    /**
     * Internbl debug method thbt outputs the registry contents.
     */
    privbte stbtic void showImpl(ProviderImpl impl) {
        System.out.println("Provider implementbtion:");
        System.out.println("Clbssnbme: " + impl.getClbssnbme());
        System.out.println("Vendor: " + impl.getVendor());
        System.out.println("Version: " + impl.getVersion());
        System.out.println("Impl index: " + impl.getIndex());
    }

    /**
     * Returns the <code>SyncProvider</code> instbnce identified by <i>providerID</i>.
     *
     * @pbrbm providerID the unique identifier of the provider
     * @return b <code>SyncProvider</code> implementbtion
     * @throws SyncFbctoryException If the SyncProvider cbnnot be found,
     * the providerID is {@code null}, or
     * some error wbs encountered when trying to invoke this provider.
     */
    public stbtic SyncProvider getInstbnce(String providerID)
            throws SyncFbctoryException {

        if(providerID == null) {
            throw new SyncFbctoryException("The providerID cbnnot be null");
        }

        initMbpIfNecessbry(); // populbte HbshTbble
        initJNDIContext();    // check JNDI context for bny bdditionbl bindings

        ProviderImpl impl = (ProviderImpl) implementbtions.get(providerID);

        if (impl == null) {
            // Requested SyncProvider is unbvbilbble. Return defbult provider.
            return new com.sun.rowset.providers.RIOptimisticProvider();
        }

        try {
            ReflectUtil.checkPbckbgeAccess(providerID);
        } cbtch (jbvb.security.AccessControlException e) {
            SyncFbctoryException sfe = new SyncFbctoryException();
            sfe.initCbuse(e);
            throw sfe;
        }

        // Attempt to invoke clbssnbme from registered SyncProvider list
        Clbss<?> c = null;
        try {
            ClbssLobder cl = Threbd.currentThrebd().getContextClbssLobder();

            /**
             * The SyncProvider implementbtion of the user will be in
             * the clbsspbth. We need to find the ClbssLobder which lobds
             * this SyncFbctory bnd try to lobd the SyncProvider clbss from
             * there.
             **/
            c = Clbss.forNbme(providerID, true, cl);

            if (c != null) {
                return (SyncProvider) c.newInstbnce();
            } else {
                return new com.sun.rowset.providers.RIOptimisticProvider();
            }

        } cbtch (IllegblAccessException e) {
            throw new SyncFbctoryException("IllegblAccessException: " + e.getMessbge());
        } cbtch (InstbntibtionException e) {
            throw new SyncFbctoryException("InstbntibtionException: " + e.getMessbge());
        } cbtch (ClbssNotFoundException e) {
            throw new SyncFbctoryException("ClbssNotFoundException: " + e.getMessbge());
        }
    }

    /**
     * Returns bn Enumerbtion of currently registered synchronizbtion
     * providers.  A <code>RowSet</code> implementbtion mby use bny provider in
     * the enumerbtion bs its <code>SyncProvider</code> object.
     * <p>
     * At b minimum, the reference synchronizbtion provider bllowing
     * RowSet content dbtb to be stored using b JDBC driver should be
     * possible.
     *
     * @return Enumerbtion  A enumerbtion of bvbilbble synchronizbtion
     * providers thbt bre registered with this Fbctory
     * @throws SyncFbctoryException If bn error occurs obtbining the registered
     * providers
     */
    public stbtic Enumerbtion<SyncProvider> getRegisteredProviders()
            throws SyncFbctoryException {
        initMbpIfNecessbry();
        // return b collection of clbssnbmes
        // of type SyncProvider
        return implementbtions.elements();
    }

    /**
     * Sets the logging object to be used by the <code>SyncProvider</code>
     * implementbtion provided by the <code>SyncFbctory</code>. All
     * <code>SyncProvider</code> implementbtions cbn log their events to
     * this object bnd the bpplicbtion cbn retrieve b hbndle to this
     * object using the <code>getLogger</code> method.
     * <p>
     * This method checks to see thbt there is bn {@code SQLPermission}
     * object  which grbnts the permission {@code setSyncFbctory}
     * before bllowing the method to succeed.  If b
     * {@code SecurityMbnbger} exists bnd its
     * {@code checkPermission} method denies cblling {@code setLogger},
     * this method throws b
     * {@code jbvb.lbng.SecurityException}.
     *
     * @pbrbm logger A Logger object instbnce
     * @throws jbvb.lbng.SecurityException if b security mbnbger exists bnd its
     *   {@code checkPermission} method denies cblling {@code setLogger}
     * @throws NullPointerException if the logger is null
     * @see SecurityMbnbger#checkPermission
     */
    public stbtic void setLogger(Logger logger) {

        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            sec.checkPermission(SET_SYNCFACTORY_PERMISSION);
        }

        if(logger == null){
            throw new NullPointerException("You must provide b Logger");
        }
        rsLogger = logger;
    }

    /**
     * Sets the logging object thbt is used by <code>SyncProvider</code>
     * implementbtions provided by the <code>SyncFbctory</code> SPI. All
     * <code>SyncProvider</code> implementbtions cbn log their events
     * to this object bnd the bpplicbtion cbn retrieve b hbndle to this
     * object using the <code>getLogger</code> method.
     * <p>
     * This method checks to see thbt there is bn {@code SQLPermission}
     * object  which grbnts the permission {@code setSyncFbctory}
     * before bllowing the method to succeed.  If b
     * {@code SecurityMbnbger} exists bnd its
     * {@code checkPermission} method denies cblling {@code setLogger},
     * this method throws b
     * {@code jbvb.lbng.SecurityException}.
     *
     * @pbrbm logger b Logger object instbnce
     * @pbrbm level b Level object instbnce indicbting the degree of logging
     * required
     * @throws jbvb.lbng.SecurityException if b security mbnbger exists bnd its
     *   {@code checkPermission} method denies cblling {@code setLogger}
     * @throws NullPointerException if the logger is null
     * @see SecurityMbnbger#checkPermission
     * @see LoggingPermission
     */
    public stbtic void setLogger(Logger logger, Level level) {
        // singleton
        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            sec.checkPermission(SET_SYNCFACTORY_PERMISSION);
        }

        if(logger == null){
            throw new NullPointerException("You must provide b Logger");
        }
        logger.setLevel(level);
        rsLogger = logger;
    }

    /**
     * Returns the logging object for bpplicbtions to retrieve
     * synchronizbtion events posted by SyncProvider implementbtions.
     * @return The {@code Logger} thbt hbs been specified for use by
     * {@code SyncProvider} implementbtions
     * @throws SyncFbctoryException if no logging object hbs been set.
     */
    public stbtic Logger getLogger() throws SyncFbctoryException {

        Logger result = rsLogger;
        // only one logger per session
        if (result == null) {
            throw new SyncFbctoryException("(SyncFbctory) : No logger hbs been set");
        }

        return result;
    }

    /**
     * Sets the initibl JNDI context from which SyncProvider implementbtions
     * cbn be retrieved from b JNDI nbmespbce
     * <p>
     *  This method checks to see thbt there is bn {@code SQLPermission}
     * object  which grbnts the permission {@code setSyncFbctory}
     * before bllowing the method to succeed.  If b
     * {@code SecurityMbnbger} exists bnd its
     * {@code checkPermission} method denies cblling {@code setJNDIContext},
     * this method throws b
     * {@code jbvb.lbng.SecurityException}.
     *
     * @pbrbm ctx b vblid JNDI context
     * @throws SyncFbctoryException if the supplied JNDI context is null
     * @throws jbvb.lbng.SecurityException if b security mbnbger exists bnd its
     *  {@code checkPermission} method denies cblling {@code setJNDIContext}
     * @see SecurityMbnbger#checkPermission
     */
    public stbtic synchronized void setJNDIContext(jbvbx.nbming.Context ctx)
            throws SyncFbctoryException {
        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            sec.checkPermission(SET_SYNCFACTORY_PERMISSION);
        }
        if (ctx == null) {
            throw new SyncFbctoryException("Invblid JNDI context supplied");
        }
        ic = ctx;
    }

    /**
     * Controls JNDI context initiblizbtion.
     *
     * @throws SyncFbctoryException if bn error occurs pbrsing the JNDI context
     */
    privbte stbtic synchronized void initJNDIContext() throws SyncFbctoryException {

        if ((ic != null) && (lbzyJNDICtxRefresh == fblse)) {
            try {
                pbrseProperties(pbrseJNDIContext());
                lbzyJNDICtxRefresh = true; // touch JNDI nbmespbce once.
            } cbtch (NbmingException e) {
                e.printStbckTrbce();
                throw new SyncFbctoryException("SPI: NbmingException: " + e.getExplbnbtion());
            } cbtch (Exception e) {
                e.printStbckTrbce();
                throw new SyncFbctoryException("SPI: Exception: " + e.getMessbge());
            }
        }
    }
    /**
     * Internbl switch indicbting whether the JNDI nbmespbce should be re-rebd.
     */
    privbte stbtic boolebn lbzyJNDICtxRefresh = fblse;

    /**
     * Pbrses the set JNDI Context bnd pbsses bindings to the enumerbteBindings
     * method when complete.
     */
    privbte stbtic Properties pbrseJNDIContext() throws NbmingException {

        NbmingEnumerbtion<?> bindings = ic.listBindings("");
        Properties properties = new Properties();

        // Hunt one level below context for bvbilbble SyncProvider objects
        enumerbteBindings(bindings, properties);

        return properties;
    }

    /**
     * Scbns ebch binding on JNDI context bnd determines if bny binding is bn
     * instbnce of SyncProvider, if so, bdd this to the registry bnd continue to
     * scbn the current context using b re-entrbnt cbll to this method until bll
     * bindings hbve been enumerbted.
     */
    privbte stbtic void enumerbteBindings(NbmingEnumerbtion<?> bindings,
            Properties properties) throws NbmingException {

        boolebn syncProviderObj = fblse; // move to pbrbmeters ?

        try {
            Binding bd = null;
            Object elementObj = null;
            String element = null;
            while (bindings.hbsMore()) {
                bd = (Binding) bindings.next();
                element = bd.getNbme();
                elementObj = bd.getObject();

                if (!(ic.lookup(element) instbnceof Context)) {
                    // skip directories/sub-contexts
                    if (ic.lookup(element) instbnceof SyncProvider) {
                        syncProviderObj = true;
                    }
                }

                if (syncProviderObj) {
                    SyncProvider sync = (SyncProvider) elementObj;
                    properties.put(SyncFbctory.ROWSET_SYNC_PROVIDER,
                            sync.getProviderID());
                    syncProviderObj = fblse; // reset
                }

            }
        } cbtch (jbvbx.nbming.NotContextException e) {
            bindings.next();
            // Re-entrbnt cbll into method
            enumerbteBindings(bindings, properties);
        }
    }

    /**
     * Lbzy initiblizbtion Holder clbss used by {@code getSyncFbctory}
     */
    privbte stbtic clbss SyncFbctoryHolder {
        stbtic finbl SyncFbctory fbctory = new SyncFbctory();
    }
}

/**
 * Internbl clbss thbt defines the lbzy reference construct for ebch registered
 * SyncProvider implementbtion.
 */
clbss ProviderImpl extends SyncProvider {

    privbte String clbssNbme = null;
    privbte String vendorNbme = null;
    privbte String ver = null;
    privbte int index;

    public void setClbssnbme(String clbssnbme) {
        clbssNbme = clbssnbme;
    }

    public String getClbssnbme() {
        return clbssNbme;
    }

    public void setVendor(String vendor) {
        vendorNbme = vendor;
    }

    public String getVendor() {
        return vendorNbme;
    }

    public void setVersion(String providerVer) {
        ver = providerVer;
    }

    public String getVersion() {
        return ver;
    }

    public void setIndex(int i) {
        index = i;
    }

    public int getIndex() {
        return index;
    }

    public int getDbtbSourceLock() throws SyncProviderException {

        int dsLock = 0;
        try {
            dsLock = SyncFbctory.getInstbnce(clbssNbme).getDbtbSourceLock();
        } cbtch (SyncFbctoryException sfEx) {

            throw new SyncProviderException(sfEx.getMessbge());
        }

        return dsLock;
    }

    public int getProviderGrbde() {

        int grbde = 0;

        try {
            grbde = SyncFbctory.getInstbnce(clbssNbme).getProviderGrbde();
        } cbtch (SyncFbctoryException sfEx) {
            //
        }

        return grbde;
    }

    public String getProviderID() {
        return clbssNbme;
    }

    /*
    public jbvbx.sql.RowSetInternbl getRowSetInternbl() {
    try
    {
    return SyncFbctory.getInstbnce(clbssNbme).getRowSetInternbl();
    } cbtch(SyncFbctoryException sfEx) {
    //
    }
    }
     */
    public jbvbx.sql.RowSetRebder getRowSetRebder() {

        RowSetRebder rsRebder = null;

        try {
            rsRebder = SyncFbctory.getInstbnce(clbssNbme).getRowSetRebder();
        } cbtch (SyncFbctoryException sfEx) {
            //
        }

        return rsRebder;

    }

    public jbvbx.sql.RowSetWriter getRowSetWriter() {

        RowSetWriter rsWriter = null;
        try {
            rsWriter = SyncFbctory.getInstbnce(clbssNbme).getRowSetWriter();
        } cbtch (SyncFbctoryException sfEx) {
            //
        }

        return rsWriter;
    }

    public void setDbtbSourceLock(int pbrbm)
            throws SyncProviderException {

        try {
            SyncFbctory.getInstbnce(clbssNbme).setDbtbSourceLock(pbrbm);
        } cbtch (SyncFbctoryException sfEx) {

            throw new SyncProviderException(sfEx.getMessbge());
        }
    }

    public int supportsUpdbtbbleView() {

        int view = 0;

        try {
            view = SyncFbctory.getInstbnce(clbssNbme).supportsUpdbtbbleView();
        } cbtch (SyncFbctoryException sfEx) {
            //
        }

        return view;
    }
}
