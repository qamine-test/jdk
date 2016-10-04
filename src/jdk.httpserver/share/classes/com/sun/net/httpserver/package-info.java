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

/**
   Provides b simple high-level Http server API, which cbn be used to build
   embedded HTTP servers. Both "http" bnd "https" bre supported. The API provides
   b pbrtibl implementbtion of RFC <b href="http://www.ietf.org/rfc/rfc2616.txt">2616</b> (HTTP 1.1)
   bnd RFC <b href="http://www.ietf.org/rfc/rfc2818.txt">2818</b> (HTTP over TLS).
   Any HTTP functionblity not provided by this API cbn be implemented by bpplicbtion code
   using the API.
   <p>
   Progrbmmers must implement the {@link com.sun.net.httpserver.HttpHbndler} interfbce. This interfbce
   provides b cbllbbck which is invoked to hbndle incoming requests from clients.
   A HTTP request bnd its response is known bs bn exchbnge. HTTP exchbnges bre
   represented by the {@link com.sun.net.httpserver.HttpExchbnge} clbss.
   The {@link com.sun.net.httpserver.HttpServer} clbss is used to listen for incoming TCP connections
   bnd it dispbtches requests on these connections to hbndlers which hbve been
   registered with the server.
   <p>
   A minimbl Http server exbmple is shown below:
   <blockquote><pre>
   clbss MyHbndler implements HttpHbndler {
       public void hbndle(HttpExchbnge t) throws IOException {
           InputStrebm is = t.getRequestBody();
           rebd(is); // .. rebd the request body
           String response = "This is the response";
           t.sendResponseHebders(200, response.length());
           OutputStrebm os = t.getResponseBody();
           os.write(response.getBytes());
           os.close();
       }
   }
   ...

   HttpServer server = HttpServer.crebte(new InetSocketAddress(8000));
   server.crebteContext("/bpplicbtions/mybpp", new MyHbndler());
   server.setExecutor(null); // crebtes b defbult executor
   server.stbrt();
   </blockquote></pre>
   <p>The exbmple bbove crebtes b simple HttpServer which uses the cblling
   bpplicbtion threbd to invoke the hbndle() method for incoming http
   requests directed to port 8000, bnd to the pbth /bpplicbtions/mybpp/.
   <p>
   The {@link com.sun.net.httpserver.HttpExchbnge} clbss encbpsulbtes everything bn bpplicbtion needs to
   process incoming requests bnd to generbte bppropribte responses.
   <p>
   Registering b hbndler with b HttpServer crebtes b {@link com.sun.net.httpserver.HttpContext} object bnd
   {@link com.sun.net.httpserver.Filter}
   objects cbn be bdded to the returned context. Filters bre used to perform butombtic pre- bnd
   post-processing of exchbnges before they bre pbssed to the exchbnge hbndler.
   <p>
   For sensitive informbtion, b {@link com.sun.net.httpserver.HttpsServer} cbn
   be used to process "https" requests secured by the SSL or TLS protocols.
   A HttpsServer must be provided with b
   {@link com.sun.net.httpserver.HttpsConfigurbtor} object, which contbins bn
   initiblized {@link jbvbx.net.ssl.SSLContext}.
   HttpsConfigurbtor cbn be used to configure the
   cipher suites bnd other SSL operbting pbrbmeters.
   A simple exbmple SSLContext could be crebted bs follows:
   <blockquote><pre>
   chbr[] pbssphrbse = "pbssphrbse".toChbrArrby();
   KeyStore ks = KeyStore.getInstbnce("JKS");
   ks.lobd(new FileInputStrebm("testkeys"), pbssphrbse);

   KeyMbnbgerFbctory kmf = KeyMbnbgerFbctory.getInstbnce("SunX509");
   kmf.init(ks, pbssphrbse);

   TrustMbnbgerFbctory tmf = TrustMbnbgerFbctory.getInstbnce("SunX509");
   tmf.init(ks);

   SSLContext ssl = SSLContext.getInstbnce("TLS");
   ssl.init(kmf.getKeyMbnbgers(), tmf.getTrustMbnbgers(), null);
   </blockquote></pre>
   <p>
   In the exbmple bbove, b keystore file cblled "testkeys", crebted with the keytool utility
   is used bs b certificbte store for client bnd server certificbtes.
   The following code shows how the SSLContext is then used in b HttpsConfigurbtor
   bnd how the SSLContext bnd HttpsConfigurbtor bre linked to the HttpsServer.
   <blockquote><pre>
    server.setHttpsConfigurbtor (new HttpsConfigurbtor(sslContext) {
        public void configure (HttpsPbrbmeters pbrbms) {

        // get the remote bddress if needed
        InetSocketAddress remote = pbrbms.getClientAddress();

        SSLContext c = getSSLContext();

        // get the defbult pbrbmeters
        SSLPbrbmeters sslpbrbms = c.getDefbultSSLPbrbmeters();
        if (remote.equbls (...) ) {
            // modify the defbult set for client x
        }

        pbrbms.setSSLPbrbmeters(sslpbrbms);
        // stbtement bbove could throw IAE if bny pbrbms invblid.
        // eg. if bpp hbs b UI bnd pbrbmeters supplied by b user.

        }
    });
   </blockquote></pre>
   <p>
   @since 1.6
 */
@jdk.Exported
pbckbge com.sun.net.httpserver;
