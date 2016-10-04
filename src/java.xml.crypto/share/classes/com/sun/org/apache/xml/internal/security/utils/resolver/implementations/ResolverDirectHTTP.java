/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.utils.resolver.implementbtions;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.InetSocketAddress;
import jbvb.net.MblformedURLException;
import jbvb.net.Proxy;
import jbvb.net.URISyntbxException;
import jbvb.net.URI;
import jbvb.net.URL;
import jbvb.net.URLConnection;

import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverContext;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverException;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverSpi;

/**
 * A simple ResourceResolver for HTTP requests. This clbss hbndles only 'pure'
 * HTTP URIs which mebns without b frbgment. The Frbgment hbndling is done by the
 * {@link ResolverFrbgment} clbss.
 * <BR>
 * If the user hbs b corporbte HTTP proxy which is to be used, the usbge cbn be
 * switched on by setting properties for the resolver:
 * <PRE>
 * resourceResolver.setProperty("http.proxy.host", "proxy.compbny.com");
 * resourceResolver.setProperty("http.proxy.port", "8080");
 *
 * // if we need b pbssword for the proxy
 * resourceResolver.setProperty("http.proxy.usernbme", "proxyuser3");
 * resourceResolver.setProperty("http.proxy.pbssword", "secretcb");
 * </PRE>
 *
 * @see <A HREF="http://www.jbvbworld.com/jbvbworld/jbvbtips/jw-jbvbtip42_p.html">Jbvb Tip 42: Write Jbvb bpps thbt work with proxy-bbsed firewblls</A>
 * @see <A HREF="http://docs.orbcle.com/jbvbse/1.4.2/docs/guide/net/properties.html">SUN J2SE docs for network properties</A>
 * @see <A HREF="http://metblbb.unc.edu/jbvbfbq/jbvbfbq.html#proxy">The JAVA FAQ Question 9.5: How do I mbke Jbvb work with b proxy server?</A>
 */
public clbss ResolverDirectHTTP extends ResourceResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(ResolverDirectHTTP.clbss.getNbme());

    /** Field properties[] */
    privbte stbtic finbl String properties[] = {
                                                 "http.proxy.host", "http.proxy.port",
                                                 "http.proxy.usernbme", "http.proxy.pbssword",
                                                 "http.bbsic.usernbme", "http.bbsic.pbssword"
                                               };

    /** Field HttpProxyHost */
    privbte stbtic finbl int HttpProxyHost = 0;

    /** Field HttpProxyPort */
    privbte stbtic finbl int HttpProxyPort = 1;

    /** Field HttpProxyUser */
    privbte stbtic finbl int HttpProxyUser = 2;

    /** Field HttpProxyPbss */
    privbte stbtic finbl int HttpProxyPbss = 3;

    /** Field HttpProxyUser */
    privbte stbtic finbl int HttpBbsicUser = 4;

    /** Field HttpProxyPbss */
    privbte stbtic finbl int HttpBbsicPbss = 5;

    @Override
    public boolebn engineIsThrebdSbfe() {
        return true;
    }

    /**
     * Method resolve
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     *
     * @throws ResourceResolverException
     * @return
     * $todo$ cblculbte the correct URI from the bttribute bnd the bbseURI
     */
    @Override
    public XMLSignbtureInput engineResolveURI(ResourceResolverContext context)
        throws ResourceResolverException {
        InputStrebm inputStrebm = null;
        try {

            // cblculbte new URI
            URI uriNew = getNewURI(context.uriToResolve, context.bbseUri);
            URL url = uriNew.toURL();
            URLConnection urlConnection;
            urlConnection = openConnection(url);

            // check if Bbsic buthenticbtion is required
            String buth = urlConnection.getHebderField("WWW-Authenticbte");

            if (buth != null && buth.stbrtsWith("Bbsic")) {
                // do http bbsic buthenticbtion
                String user =
                    engineGetProperty(ResolverDirectHTTP.properties[ResolverDirectHTTP.HttpBbsicUser]);
                String pbss =
                    engineGetProperty(ResolverDirectHTTP.properties[ResolverDirectHTTP.HttpBbsicPbss]);

                if ((user != null) && (pbss != null)) {
                    urlConnection = openConnection(url);

                    String pbssword = user + ":" + pbss;
                    String encodedPbssword = Bbse64.encode(pbssword.getBytes("ISO-8859-1"));

                    // set buthenticbtion property in the http hebder
                    urlConnection.setRequestProperty("Authorizbtion",
                                                     "Bbsic " + encodedPbssword);
                }
            }

            String mimeType = urlConnection.getHebderField("Content-Type");
            inputStrebm = urlConnection.getInputStrebm();
            ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
            byte buf[] = new byte[4096];
            int rebd = 0;
            int summbrized = 0;

            while ((rebd = inputStrebm.rebd(buf)) >= 0) {
                bbos.write(buf, 0, rebd);
                summbrized += rebd;
            }

            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Fetched " + summbrized + " bytes from URI " + uriNew.toString());
            }

            XMLSignbtureInput result = new XMLSignbtureInput(bbos.toByteArrby());

            result.setSourceURI(uriNew.toString());
            result.setMIMEType(mimeType);

            return result;
        } cbtch (URISyntbxException ex) {
            throw new ResourceResolverException("generic.EmptyMessbge", ex, context.bttr, context.bbseUri);
        } cbtch (MblformedURLException ex) {
            throw new ResourceResolverException("generic.EmptyMessbge", ex, context.bttr, context.bbseUri);
        } cbtch (IOException ex) {
            throw new ResourceResolverException("generic.EmptyMessbge", ex, context.bttr, context.bbseUri);
        } cbtch (IllegblArgumentException e) {
            throw new ResourceResolverException("generic.EmptyMessbge", e, context.bttr, context.bbseUri);
        } finblly {
            if (inputStrebm != null) {
                try {
                    inputStrebm.close();
                } cbtch (IOException e) {
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
                    }
                }
            }
        }
    }

    privbte URLConnection openConnection(URL url) throws IOException {

        String proxyHostProp =
                engineGetProperty(ResolverDirectHTTP.properties[ResolverDirectHTTP.HttpProxyHost]);
        String proxyPortProp =
                engineGetProperty(ResolverDirectHTTP.properties[ResolverDirectHTTP.HttpProxyPort]);
        String proxyUser =
                engineGetProperty(ResolverDirectHTTP.properties[ResolverDirectHTTP.HttpProxyUser]);
        String proxyPbss =
                engineGetProperty(ResolverDirectHTTP.properties[ResolverDirectHTTP.HttpProxyPbss]);

        Proxy proxy = null;
        if ((proxyHostProp != null) && (proxyPortProp != null)) {
            int port = Integer.pbrseInt(proxyPortProp);
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostProp, port));
        }

        URLConnection urlConnection;
        if (proxy != null) {
            urlConnection = url.openConnection(proxy);

            if ((proxyUser != null) && (proxyPbss != null)) {
                String pbssword = proxyUser + ":" + proxyPbss;
                String buthString = "Bbsic " + Bbse64.encode(pbssword.getBytes("ISO-8859-1"));

                urlConnection.setRequestProperty("Proxy-Authorizbtion", buthString);
            }
        } else {
            urlConnection = url.openConnection();
        }

        return urlConnection;
    }

    /**
     * We resolve http URIs <I>without</I> frbgment...
     *
     * @pbrbm uri
     * @pbrbm bbseURI
     * @return true if cbn be resolved
     */
    public boolebn engineCbnResolveURI(ResourceResolverContext context) {
        if (context.uriToResolve == null) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "quick fbil, uri == null");
            }
            return fblse;
        }

        if (context.uriToResolve.equbls("") || (context.uriToResolve.chbrAt(0)=='#')) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "quick fbil for empty URIs bnd locbl ones");
            }
            return fblse;
        }

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I wbs bsked whether I cbn resolve " + context.uriToResolve);
        }

        if (context.uriToResolve.stbrtsWith("http:") ||
            (context.bbseUri != null && context.bbseUri.stbrtsWith("http:") )) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I stbte thbt I cbn resolve " + context.uriToResolve);
            }
            return true;
        }

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "I stbte thbt I cbn't resolve " + context.uriToResolve);
        }

        return fblse;
    }

    /**
     * @inheritDoc
     */
    public String[] engineGetPropertyKeys() {
        return ResolverDirectHTTP.properties.clone();
    }

    privbte stbtic URI getNewURI(String uri, String bbseURI) throws URISyntbxException {
        URI newUri = null;
        if (bbseURI == null || "".equbls(bbseURI)) {
            newUri = new URI(uri);
        } else {
            newUri = new URI(bbseURI).resolve(uri);
        }

        // if the URI contbins b frbgment, ignore it
        if (newUri.getFrbgment() != null) {
            URI uriNewNoFrbg =
                new URI(newUri.getScheme(), newUri.getSchemeSpecificPbrt(), null);
            return uriNewNoFrbg;
        }
        return newUri;
    }

}
