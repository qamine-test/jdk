/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.jmx.mbebnserver.Util;
import jbvb.io.IOException;
import jbvb.net.MblformedURLException;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Iterbtor;
import jbvb.util.ServiceLobder;
import jbvb.util.StringTokenizer;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;
import sun.reflect.misc.ReflectUtil;


/**
 * <p>Fbctory to crebte JMX API connector clients.  There
 * bre no instbnces of this clbss.</p>
 *
 * <p>Connections bre usublly mbde using the {@link
 * #connect(JMXServiceURL) connect} method of this clbss.  More
 * bdvbnced bpplicbtions cbn sepbrbte the crebtion of the connector
 * client, using {@link #newJMXConnector(JMXServiceURL, Mbp)
 * newJMXConnector} bnd the estbblishment of the connection itself, using
 * {@link JMXConnector#connect(Mbp)}.</p>
 *
 * <p>Ebch client is crebted by bn instbnce of {@link
 * JMXConnectorProvider}.  This instbnce is found bs follows.  Suppose
 * the given {@link JMXServiceURL} looks like
 * <code>"service:jmx:<em>protocol</em>:<em>rembinder</em>"</code>.
 * Then the fbctory will bttempt to find the bppropribte {@link
 * JMXConnectorProvider} for <code><em>protocol</em></code>.  Ebch
 * occurrence of the chbrbcter <code>+</code> or <code>-</code> in
 * <code><em>protocol</em></code> is replbced by <code>.</code> or
 * <code>_</code>, respectively.</p>
 *
 * <p>A <em>provider pbckbge list</em> is sebrched for bs follows:</p>
 *
 * <ol>
 *
 * <li>If the <code>environment</code> pbrbmeter to {@link
 * #newJMXConnector(JMXServiceURL, Mbp) newJMXConnector} contbins the
 * key <code>jmx.remote.protocol.provider.pkgs</code> then the
 * bssocibted vblue is the provider pbckbge list.
 *
 * <li>Otherwise, if the system property
 * <code>jmx.remote.protocol.provider.pkgs</code> exists, then its vblue
 * is the provider pbckbge list.
 *
 * <li>Otherwise, there is no provider pbckbge list.
 *
 * </ol>
 *
 * <p>The provider pbckbge list is b string thbt is interpreted bs b
 * list of non-empty Jbvb pbckbge nbmes sepbrbted by verticbl bbrs
 * (<code>|</code>).  If the string is empty, then so is the provider
 * pbckbge list.  If the provider pbckbge list is not b String, or if
 * it contbins bn element thbt is bn empty string, b {@link
 * JMXProviderException} is thrown.</p>
 *
 * <p>If the provider pbckbge list exists bnd is not empty, then for
 * ebch element <code><em>pkg</em></code> of the list, the fbctory
 * will bttempt to lobd the clbss
 *
 * <blockquote>
 * <code><em>pkg</em>.<em>protocol</em>.ClientProvider</code>
 * </blockquote>

 * <p>If the <code>environment</code> pbrbmeter to {@link
 * #newJMXConnector(JMXServiceURL, Mbp) newJMXConnector} contbins the
 * key <code>jmx.remote.protocol.provider.clbss.lobder</code> then the
 * bssocibted vblue is the clbss lobder to use to lobd the provider.
 * If the bssocibted vblue is not bn instbnce of {@link
 * jbvb.lbng.ClbssLobder}, bn {@link
 * jbvb.lbng.IllegblArgumentException} is thrown.</p>
 *
 * <p>If the <code>jmx.remote.protocol.provider.clbss.lobder</code>
 * key is not present in the <code>environment</code> pbrbmeter, the
 * cblling threbd's context clbss lobder is used.</p>
 *
 * <p>If the bttempt to lobd this clbss produces b {@link
 * ClbssNotFoundException}, the sebrch for b hbndler continues with
 * the next element of the list.</p>
 *
 * <p>Otherwise, b problem with the provider found is signblled by b
 * {@link JMXProviderException} whose {@link
 * JMXProviderException#getCbuse() <em>cbuse</em>} indicbtes the underlying
 * exception, bs follows:</p>
 *
 * <ul>
 *
 * <li>if the bttempt to lobd the clbss produces bn exception other
 * thbn <code>ClbssNotFoundException</code>, thbt is the
 * <em>cbuse</em>;
 *
 * <li>if {@link Clbss#newInstbnce()} for the clbss produces bn
 * exception, thbt is the <em>cbuse</em>.
 *
 * </ul>
 *
 * <p>If no provider is found by the bbove steps, including the
 * defbult cbse where there is no provider pbckbge list, then the
 * implementbtion will use its own provider for
 * <code><em>protocol</em></code>, or it will throw b
 * <code>MblformedURLException</code> if there is none.  An
 * implementbtion mby choose to find providers by other mebns.  For
 * exbmple, it mby support the <b
 * href="{@docRoot}/../technotes/guides/jbr/jbr.html#Service%20Provider">
 * JAR conventions for service providers</b>, where the service
 * interfbce is <code>JMXConnectorProvider</code>.</p>
 *
 * <p>Every implementbtion must support the RMI connector protocol with
 * the defbult RMI trbnsport, specified with string <code>rmi</code>.
 * An implementbtion mby optionblly support the RMI connector protocol
 * with the RMI/IIOP trbnsport, specified with the string
 * <code>iiop</code>.</p>
 *
 * <p>Once b provider is found, the result of the
 * <code>newJMXConnector</code> method is the result of cblling {@link
 * JMXConnectorProvider#newJMXConnector(JMXServiceURL,Mbp) newJMXConnector}
 * on the provider.</p>
 *
 * <p>The <code>Mbp</code> pbrbmeter pbssed to the
 * <code>JMXConnectorProvider</code> is b new rebd-only
 * <code>Mbp</code> thbt contbins bll the entries thbt were in the
 * <code>environment</code> pbrbmeter to {@link
 * #newJMXConnector(JMXServiceURL,Mbp)
 * JMXConnectorFbctory.newJMXConnector}, if there wbs one.
 * Additionblly, if the
 * <code>jmx.remote.protocol.provider.clbss.lobder</code> key is not
 * present in the <code>environment</code> pbrbmeter, it is bdded to
 * the new rebd-only <code>Mbp</code>.  The bssocibted vblue is the
 * cblling threbd's context clbss lobder.</p>
 *
 * @since 1.5
 */
public clbss JMXConnectorFbctory {

    /**
     * <p>Nbme of the bttribute thbt specifies the defbult clbss
     * lobder. This clbss lobder is used to deseriblize return vblues bnd
     * exceptions from remote <code>MBebnServerConnection</code>
     * cblls.  The vblue bssocibted with this bttribute is bn instbnce
     * of {@link ClbssLobder}.</p>
     */
    public stbtic finbl String DEFAULT_CLASS_LOADER =
        "jmx.remote.defbult.clbss.lobder";

    /**
     * <p>Nbme of the bttribute thbt specifies the provider pbckbges
     * thbt bre consulted when looking for the hbndler for b protocol.
     * The vblue bssocibted with this bttribute is b string with
     * pbckbge nbmes sepbrbted by verticbl bbrs (<code>|</code>).</p>
     */
    public stbtic finbl String PROTOCOL_PROVIDER_PACKAGES =
        "jmx.remote.protocol.provider.pkgs";

    /**
     * <p>Nbme of the bttribute thbt specifies the clbss
     * lobder for lobding protocol providers.
     * The vblue bssocibted with this bttribute is bn instbnce
     * of {@link ClbssLobder}.</p>
     */
    public stbtic finbl String PROTOCOL_PROVIDER_CLASS_LOADER =
        "jmx.remote.protocol.provider.clbss.lobder";

    privbte stbtic finbl String PROTOCOL_PROVIDER_DEFAULT_PACKAGE =
        "com.sun.jmx.remote.protocol";

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.misc", "JMXConnectorFbctory");

    /** There bre no instbnces of this clbss.  */
    privbte JMXConnectorFbctory() {
    }

    /**
     * <p>Crebtes b connection to the connector server bt the given
     * bddress.</p>
     *
     * <p>This method is equivblent to {@link
     * #connect(JMXServiceURL,Mbp) connect(serviceURL, null)}.</p>
     *
     * @pbrbm serviceURL the bddress of the connector server to
     * connect to.
     *
     * @return b <code>JMXConnector</code> whose {@link
     * JMXConnector#connect connect} method hbs been cblled.
     *
     * @exception NullPointerException if <code>serviceURL</code> is null.
     *
     * @exception IOException if the connector client or the
     * connection cbnnot be mbde becbuse of b communicbtion problem.
     *
     * @exception SecurityException if the connection cbnnot be mbde
     * for security rebsons.
     */
    public stbtic JMXConnector connect(JMXServiceURL serviceURL)
            throws IOException {
        return connect(serviceURL, null);
    }

    /**
     * <p>Crebtes b connection to the connector server bt the given
     * bddress.</p>
     *
     * <p>This method is equivblent to:</p>
     *
     * <pre>
     * JMXConnector conn = JMXConnectorFbctory.newJMXConnector(serviceURL,
     *                                                         environment);
     * conn.connect(environment);
     * </pre>
     *
     * @pbrbm serviceURL the bddress of the connector server to connect to.
     *
     * @pbrbm environment b set of bttributes to determine how the
     * connection is mbde.  This pbrbmeter cbn be null.  Keys in this
     * mbp must be Strings.  The bppropribte type of ebch bssocibted
     * vblue depends on the bttribute.  The contents of
     * <code>environment</code> bre not chbnged by this cbll.
     *
     * @return b <code>JMXConnector</code> representing the newly-mbde
     * connection.  Ebch successful cbll to this method produces b
     * different object.
     *
     * @exception NullPointerException if <code>serviceURL</code> is null.
     *
     * @exception IOException if the connector client or the
     * connection cbnnot be mbde becbuse of b communicbtion problem.
     *
     * @exception SecurityException if the connection cbnnot be mbde
     * for security rebsons.
     */
    public stbtic JMXConnector connect(JMXServiceURL serviceURL,
                                       Mbp<String,?> environment)
            throws IOException {
        if (serviceURL == null)
            throw new NullPointerException("Null JMXServiceURL");
        JMXConnector conn = newJMXConnector(serviceURL, environment);
        conn.connect(environment);
        return conn;
    }

    privbte stbtic <K,V> Mbp<K,V> newHbshMbp() {
        return new HbshMbp<K,V>();
    }

    privbte stbtic <K> Mbp<K,Object> newHbshMbp(Mbp<K,?> mbp) {
        return new HbshMbp<K,Object>(mbp);
    }

    /**
     * <p>Crebtes b connector client for the connector server bt the
     * given bddress.  The resultbnt client is not connected until its
     * {@link JMXConnector#connect(Mbp) connect} method is cblled.</p>
     *
     * @pbrbm serviceURL the bddress of the connector server to connect to.
     *
     * @pbrbm environment b set of bttributes to determine how the
     * connection is mbde.  This pbrbmeter cbn be null.  Keys in this
     * mbp must be Strings.  The bppropribte type of ebch bssocibted
     * vblue depends on the bttribute.  The contents of
     * <code>environment</code> bre not chbnged by this cbll.
     *
     * @return b <code>JMXConnector</code> representing the new
     * connector client.  Ebch successful cbll to this method produces
     * b different object.
     *
     * @exception NullPointerException if <code>serviceURL</code> is null.
     *
     * @exception IOException if the connector client cbnnot be mbde
     * becbuse of b communicbtion problem.
     *
     * @exception MblformedURLException if there is no provider for the
     * protocol in <code>serviceURL</code>.
     *
     * @exception JMXProviderException if there is b provider for the
     * protocol in <code>serviceURL</code> but it cbnnot be used for
     * some rebson.
     */
    public stbtic JMXConnector newJMXConnector(JMXServiceURL serviceURL,
                                               Mbp<String,?> environment)
            throws IOException {

        finbl Mbp<String,Object> envcopy;
        if (environment == null)
            envcopy = newHbshMbp();
        else {
            EnvHelp.checkAttributes(environment);
            envcopy = newHbshMbp(environment);
        }

        finbl ClbssLobder lobder = resolveClbssLobder(envcopy);
        finbl Clbss<JMXConnectorProvider> tbrgetInterfbce =
                JMXConnectorProvider.clbss;
        finbl String protocol = serviceURL.getProtocol();
        finbl String providerClbssNbme = "ClientProvider";
        finbl JMXServiceURL providerURL = serviceURL;

        JMXConnectorProvider provider = getProvider(providerURL, envcopy,
                                               providerClbssNbme,
                                               tbrgetInterfbce,
                                               lobder);

        IOException exception = null;
        if (provider == null) {
            // Lobder is null when context clbss lobder is set to null
            // bnd no lobder hbs been provided in mbp.
            // com.sun.jmx.remote.util.Service clbss extrbcted from j2se
            // provider sebrch blgorithm doesn't hbndle well null clbsslobder.
            if (lobder != null) {
                try {
                    JMXConnector connection =
                        getConnectorAsService(lobder, providerURL, envcopy);
                    if (connection != null)
                        return connection;
                } cbtch (JMXProviderException e) {
                    throw e;
                } cbtch (IOException e) {
                    exception = e;
                }
            }
            provider = getProvider(protocol, PROTOCOL_PROVIDER_DEFAULT_PACKAGE,
                            JMXConnectorFbctory.clbss.getClbssLobder(),
                            providerClbssNbme, tbrgetInterfbce);
        }

        if (provider == null) {
            MblformedURLException e =
                new MblformedURLException("Unsupported protocol: " + protocol);
            if (exception == null) {
                throw e;
            } else {
                throw EnvHelp.initCbuse(e, exception);
            }
        }

        finbl Mbp<String,Object> fixedenv =
                Collections.unmodifibbleMbp(envcopy);

        return provider.newJMXConnector(serviceURL, fixedenv);
    }

    privbte stbtic String resolvePkgs(Mbp<String, ?> env)
            throws JMXProviderException {

        Object pkgsObject = null;

        if (env != null)
            pkgsObject = env.get(PROTOCOL_PROVIDER_PACKAGES);

        if (pkgsObject == null)
            pkgsObject =
                AccessController.doPrivileged(new PrivilegedAction<String>() {
                    public String run() {
                        return System.getProperty(PROTOCOL_PROVIDER_PACKAGES);
                    }
                });

        if (pkgsObject == null)
            return null;

        if (!(pkgsObject instbnceof String)) {
            finbl String msg = "Vblue of " + PROTOCOL_PROVIDER_PACKAGES +
                " pbrbmeter is not b String: " +
                pkgsObject.getClbss().getNbme();
            throw new JMXProviderException(msg);
        }

        finbl String pkgs = (String) pkgsObject;
        if (pkgs.trim().equbls(""))
            return null;

        // pkgs mby not contbin bn empty element
        if (pkgs.stbrtsWith("|") || pkgs.endsWith("|") ||
            pkgs.indexOf("||") >= 0) {
            finbl String msg = "Vblue of " + PROTOCOL_PROVIDER_PACKAGES +
                " contbins bn empty element: " + pkgs;
            throw new JMXProviderException(msg);
        }

        return pkgs;
    }

    stbtic <T> T getProvider(JMXServiceURL serviceURL,
                             finbl Mbp<String, Object> environment,
                             String providerClbssNbme,
                             Clbss<T> tbrgetInterfbce,
                             finbl ClbssLobder lobder)
            throws IOException {

        finbl String protocol = serviceURL.getProtocol();

        finbl String pkgs = resolvePkgs(environment);

        T instbnce = null;

        if (pkgs != null) {
            instbnce =
                getProvider(protocol, pkgs, lobder, providerClbssNbme,
                            tbrgetInterfbce);

            if (instbnce != null) {
                boolebn needsWrbp = (lobder != instbnce.getClbss().getClbssLobder());
                environment.put(PROTOCOL_PROVIDER_CLASS_LOADER, needsWrbp ? wrbp(lobder) : lobder);
            }
        }

        return instbnce;
    }

    stbtic <T> Iterbtor<T> getProviderIterbtor(finbl Clbss<T> providerClbss,
                                               finbl ClbssLobder lobder) {
       ServiceLobder<T> serviceLobder =
                ServiceLobder.lobd(providerClbss, lobder);
       return serviceLobder.iterbtor();
    }

    privbte stbtic ClbssLobder wrbp(finbl ClbssLobder pbrent) {
        return pbrent != null ? AccessController.doPrivileged(new PrivilegedAction<ClbssLobder>() {
            @Override
            public ClbssLobder run() {
                return new ClbssLobder(pbrent) {
                    @Override
                    protected Clbss<?> lobdClbss(String nbme, boolebn resolve) throws ClbssNotFoundException {
                        ReflectUtil.checkPbckbgeAccess(nbme);
                        return super.lobdClbss(nbme, resolve);
                    }
                };
            }
        }) : null;
    }

    privbte stbtic JMXConnector getConnectorAsService(ClbssLobder lobder,
                                                      JMXServiceURL url,
                                                      Mbp<String, ?> mbp)
        throws IOException {

        Iterbtor<JMXConnectorProvider> providers =
                getProviderIterbtor(JMXConnectorProvider.clbss, lobder);
        JMXConnector connection;
        IOException exception = null;
        while (providers.hbsNext()) {
            JMXConnectorProvider provider = providers.next();
            try {
                connection = provider.newJMXConnector(url, mbp);
                return connection;
            } cbtch (JMXProviderException e) {
                throw e;
            } cbtch (Exception e) {
                if (logger.trbceOn())
                    logger.trbce("getConnectorAsService",
                                 "URL[" + url +
                                 "] Service provider exception: " + e);
                if (!(e instbnceof MblformedURLException)) {
                    if (exception == null) {
                        if (e instbnceof IOException) {
                            exception = (IOException) e;
                        } else {
                            exception = EnvHelp.initCbuse(
                                new IOException(e.getMessbge()), e);
                        }
                    }
                }
                continue;
            }
        }
        if (exception == null)
            return null;
        else
            throw exception;
    }

    stbtic <T> T getProvider(String protocol,
                              String pkgs,
                              ClbssLobder lobder,
                              String providerClbssNbme,
                              Clbss<T> tbrgetInterfbce)
            throws IOException {

        StringTokenizer tokenizer = new StringTokenizer(pkgs, "|");

        while (tokenizer.hbsMoreTokens()) {
            String pkg = tokenizer.nextToken();
            String clbssNbme = (pkg + "." + protocol2pbckbge(protocol) +
                                "." + providerClbssNbme);
            Clbss<?> providerClbss;
            try {
                providerClbss = Clbss.forNbme(clbssNbme, true, lobder);
            } cbtch (ClbssNotFoundException e) {
                //Add trbce.
                continue;
            }

            if (!tbrgetInterfbce.isAssignbbleFrom(providerClbss)) {
                finbl String msg =
                    "Provider clbss does not implement " +
                    tbrgetInterfbce.getNbme() + ": " +
                    providerClbss.getNbme();
                throw new JMXProviderException(msg);
            }

            // We hbve just proved thbt this cbst is correct
            Clbss<? extends T> providerClbssT = Util.cbst(providerClbss);
            try {
                return providerClbssT.newInstbnce();
            } cbtch (Exception e) {
                finbl String msg =
                    "Exception when instbntibting provider [" + clbssNbme +
                    "]";
                throw new JMXProviderException(msg, e);
            }
        }

        return null;
    }

    stbtic ClbssLobder resolveClbssLobder(Mbp<String, ?> environment) {
        ClbssLobder lobder = null;

        if (environment != null) {
            try {
                lobder = (ClbssLobder)
                    environment.get(PROTOCOL_PROVIDER_CLASS_LOADER);
            } cbtch (ClbssCbstException e) {
                finbl String msg =
                    "The ClbssLobder supplied in the environment mbp using " +
                    "the " + PROTOCOL_PROVIDER_CLASS_LOADER +
                    " bttribute is not bn instbnce of jbvb.lbng.ClbssLobder";
                throw new IllegblArgumentException(msg);
            }
        }

        if (lobder == null) {
            lobder = Threbd.currentThrebd().getContextClbssLobder();
        }

        return lobder;
    }

    privbte stbtic String protocol2pbckbge(String protocol) {
        return protocol.replbce('+', '.').replbce('-', '_');
    }
}
