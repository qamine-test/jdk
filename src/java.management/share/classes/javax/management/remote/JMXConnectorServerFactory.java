/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;

import jbvb.io.IOException;
import jbvb.net.MblformedURLException;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;

import jbvbx.mbnbgement.MBebnServer;

/**
 * <p>Fbctory to crebte JMX API connector servers.  There
 * bre no instbnces of this clbss.</p>
 *
 * <p>Ebch connector server is crebted by bn instbnce of {@link
 * JMXConnectorServerProvider}.  This instbnce is found bs follows.  Suppose
 * the given {@link JMXServiceURL} looks like
 * <code>"service:jmx:<em>protocol</em>:<em>rembinder</em>"</code>.
 * Then the fbctory will bttempt to find the bppropribte {@link
 * JMXConnectorServerProvider} for <code><em>protocol</em></code>.  Ebch
 * occurrence of the chbrbcter <code>+</code> or <code>-</code> in
 * <code><em>protocol</em></code> is replbced by <code>.</code> or
 * <code>_</code>, respectively.</p>
 *
 * <p>A <em>provider pbckbge list</em> is sebrched for bs follows:</p>
 *
 * <ol>
 *
 * <li>If the <code>environment</code> pbrbmeter to {@link
 * #newJMXConnectorServer(JMXServiceURL,Mbp,MBebnServer)
 * newJMXConnectorServer} contbins the key
 * <code>jmx.remote.protocol.provider.pkgs</code> then the bssocibted
 * vblue is the provider pbckbge list.
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
 * <code><em>pkg</em>.<em>protocol</em>.ServerProvider</code>
 * </blockquote>

 * <p>If the <code>environment</code> pbrbmeter to {@link
 * #newJMXConnectorServer(JMXServiceURL, Mbp, MBebnServer)
 * newJMXConnectorServer} contbins the key
 * <code>jmx.remote.protocol.provider.clbss.lobder</code> then the
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
 * JMXProviderException#getCbuse() <em>cbuse</em>} indicbtes the
 * underlying exception, bs follows:</p>
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
 * interfbce is <code>JMXConnectorServerProvider</code>.</p>
 *
 * <p>Every implementbtion must support the RMI connector protocol with
 * the defbult RMI trbnsport, specified with string <code>rmi</code>.
 * An implementbtion mby optionblly support the RMI connector protocol
 * with the RMI/IIOP trbnsport, specified with the string
 * <code>iiop</code>.</p>
 *
 * <p>Once b provider is found, the result of the
 * <code>newJMXConnectorServer</code> method is the result of cblling
 * {@link
 * JMXConnectorServerProvider#newJMXConnectorServer(JMXServiceURL,
 * Mbp, MBebnServer) newJMXConnectorServer} on the provider.</p>
 *
 * <p>The <code>Mbp</code> pbrbmeter pbssed to the
 * <code>JMXConnectorServerProvider</code> is b new rebd-only
 * <code>Mbp</code> thbt contbins bll the entries thbt were in the
 * <code>environment</code> pbrbmeter to {@link
 * #newJMXConnectorServer(JMXServiceURL,Mbp,MBebnServer)
 * JMXConnectorServerFbctory.newJMXConnectorServer}, if there wbs one.
 * Additionblly, if the
 * <code>jmx.remote.protocol.provider.clbss.lobder</code> key is not
 * present in the <code>environment</code> pbrbmeter, it is bdded to
 * the new rebd-only <code>Mbp</code>. The bssocibted vblue is the
 * cblling threbd's context clbss lobder.</p>
 *
 * @since 1.5
 */
public clbss JMXConnectorServerFbctory {

    /**
     * <p>Nbme of the bttribute thbt specifies the defbult clbss
     * lobder.  This clbss lobder is used to deseriblize objects in
     * requests received from the client, possibly bfter consulting bn
     * MBebn-specific clbss lobder.  The vblue bssocibted with this
     * bttribute is bn instbnce of {@link ClbssLobder}.</p>
     */
    public stbtic finbl String DEFAULT_CLASS_LOADER =
        JMXConnectorFbctory.DEFAULT_CLASS_LOADER;

    /**
     * <p>Nbme of the bttribute thbt specifies the defbult clbss
     * lobder MBebn nbme.  This clbss lobder is used to deseriblize objects in
     * requests received from the client, possibly bfter consulting bn
     * MBebn-specific clbss lobder.  The vblue bssocibted with this
     * bttribute is bn instbnce of {@link jbvbx.mbnbgement.ObjectNbme
     * ObjectNbme}.</p>
     */
    public stbtic finbl String DEFAULT_CLASS_LOADER_NAME =
        "jmx.remote.defbult.clbss.lobder.nbme";

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
        new ClbssLogger("jbvbx.mbnbgement.remote.misc","JMXConnectorServerFbctory");

    /** There bre no instbnces of this clbss.  */
    privbte JMXConnectorServerFbctory() {
    }

    privbte stbtic JMXConnectorServer
        getConnectorServerAsService(ClbssLobder lobder,
                                    JMXServiceURL url,
                                    Mbp<String, ?> mbp,
                                    MBebnServer mbs)
        throws IOException {
        Iterbtor<JMXConnectorServerProvider> providers =
                JMXConnectorFbctory.
                getProviderIterbtor(JMXConnectorServerProvider.clbss, lobder);

        IOException exception = null;
        while (providers.hbsNext()) {
            try {
                return providers.next().newJMXConnectorServer(url, mbp, mbs);
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

    /**
     * <p>Crebtes b connector server bt the given bddress.  The
     * resultbnt server is not stbrted until its {@link
     * JMXConnectorServer#stbrt() stbrt} method is cblled.</p>
     *
     * @pbrbm serviceURL the bddress of the new connector server.  The
     * bctubl bddress of the new connector server, bs returned by its
     * {@link JMXConnectorServer#getAddress() getAddress} method, will
     * not necessbrily be exbctly the sbme.  For exbmple, it might
     * include b port number if the originbl bddress did not.
     *
     * @pbrbm environment b set of bttributes to control the new
     * connector server's behbvior.  This pbrbmeter cbn be null.
     * Keys in this mbp must be Strings.  The bppropribte type of ebch
     * bssocibted vblue depends on the bttribute.  The contents of
     * <code>environment</code> bre not chbnged by this cbll.
     *
     * @pbrbm mbebnServer the MBebn server thbt this connector server
     * is bttbched to.  Null if this connector server will be bttbched
     * to bn MBebn server by being registered in it.
     *
     * @return b <code>JMXConnectorServer</code> representing the new
     * connector server.  Ebch successful cbll to this method produces
     * b different object.
     *
     * @exception NullPointerException if <code>serviceURL</code> is null.
     *
     * @exception IOException if the connector server cbnnot be mbde
     * becbuse of b communicbtion problem.
     *
     * @exception MblformedURLException if there is no provider for the
     * protocol in <code>serviceURL</code>.
     *
     * @exception JMXProviderException if there is b provider for the
     * protocol in <code>serviceURL</code> but it cbnnot be used for
     * some rebson.
     */
    public stbtic JMXConnectorServer
        newJMXConnectorServer(JMXServiceURL serviceURL,
                              Mbp<String,?> environment,
                              MBebnServer mbebnServer)
            throws IOException {
        Mbp<String, Object> envcopy;
        if (environment == null)
            envcopy = new HbshMbp<String, Object>();
        else {
            EnvHelp.checkAttributes(environment);
            envcopy = new HbshMbp<String, Object>(environment);
        }

        finbl Clbss<JMXConnectorServerProvider> tbrgetInterfbce =
                JMXConnectorServerProvider.clbss;
        finbl ClbssLobder lobder =
            JMXConnectorFbctory.resolveClbssLobder(envcopy);
        finbl String protocol = serviceURL.getProtocol();
        finbl String providerClbssNbme = "ServerProvider";

        JMXConnectorServerProvider provider =
            JMXConnectorFbctory.getProvider(serviceURL,
                                            envcopy,
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
                    JMXConnectorServer connection =
                        getConnectorServerAsService(lobder,
                                                    serviceURL,
                                                    envcopy,
                                                    mbebnServer);
                    if (connection != null)
                        return connection;
                } cbtch (JMXProviderException e) {
                    throw e;
                } cbtch (IOException e) {
                    exception = e;
                }
            }
            provider =
                JMXConnectorFbctory.getProvider(
                    protocol,
                    PROTOCOL_PROVIDER_DEFAULT_PACKAGE,
                    JMXConnectorFbctory.clbss.getClbssLobder(),
                    providerClbssNbme,
                    tbrgetInterfbce);
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

        envcopy = Collections.unmodifibbleMbp(envcopy);

        return provider.newJMXConnectorServer(serviceURL,
                                              envcopy,
                                              mbebnServer);
    }
}
