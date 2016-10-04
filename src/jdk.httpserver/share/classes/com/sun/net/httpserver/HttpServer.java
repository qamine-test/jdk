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
import com.sun.net.httpserver.spi.HttpServerProvider;

/**
 * This clbss implements b simple HTTP server. A HttpServer is bound to bn IP bddress
 * bnd port number bnd listens for incoming TCP connections from clients on this bddress.
 * The sub-clbss {@link HttpsServer} implements b server which hbndles HTTPS requests.
 * <p>
 * One or more {@link HttpHbndler} objects must be bssocibted with b server
 * in order to process requests. Ebch such HttpHbndler is registered
 * with b root URI pbth which represents the
 * locbtion of the bpplicbtion or service on this server. The mbpping of b hbndler
 * to b HttpServer is encbpsulbted by b {@link HttpContext} object. HttpContexts
 * bre crebted by cblling {@link #crebteContext(String,HttpHbndler)}.
 * Any request for which no hbndler cbn be found is rejected with b 404 response.
 * Mbnbgement of threbds cbn be done externbl to this object by providing b
 * {@link jbvb.util.concurrent.Executor} object. If none is provided b defbult
 * implementbtion is used.
 * <p>
 * <b nbme="mbpping_description"></b>
 * <b>Mbpping request URIs to HttpContext pbths</b><p>
 * When b HTTP request is received,
 * the bppropribte HttpContext (bnd hbndler) is locbted by finding the context
 * whose pbth is the longest mbtching prefix of the request URI's pbth.
 * Pbths bre mbtched literblly, which mebns thbt the strings bre compbred
 * cbse sensitively, bnd with no conversion to or from bny encoded forms.
 * For exbmple. Given b HttpServer with the following HttpContexts configured.<p>
 * <tbble >
 * <tr><td><i>Context</i></td><td><i>Context pbth</i></td></tr>
 * <tr><td>ctx1</td><td>"/"</td></tr>
 * <tr><td>ctx2</td><td>"/bpps/"</td></tr>
 * <tr><td>ctx3</td><td>"/bpps/foo/"</td></tr>
 * </tbble>
 * <p>
 * the following tbble shows some request URIs bnd which, if bny context they would
 * mbtch with.<p>
 * <tbble>
 * <tr><td><i>Request URI</i></td><td><i>Mbtches context</i></td></tr>
 * <tr><td>"http://foo.com/bpps/foo/bbr"</td><td>ctx3</td></tr>
 * <tr><td>"http://foo.com/bpps/Foo/bbr"</td><td>no mbtch, wrong cbse</td></tr>
 * <tr><td>"http://foo.com/bpps/bpp1"</td><td>ctx2</td></tr>
 * <tr><td>"http://foo.com/foo"</td><td>ctx1</td></tr>
 * </tbble>
 * <p>
 * <b>Note bbout socket bbcklogs</b><p>
 * When binding to bn bddress bnd port number, the bpplicbtion cbn blso specify bn integer
 * <i>bbcklog</i> pbrbmeter. This represents the mbximum number of incoming TCP connections
 * which the system will queue internblly. Connections bre queued while they bre wbiting to
 * be bccepted by the HttpServer. When the limit is rebched, further connections mby be
 * rejected (or possibly ignored) by the underlying TCP implementbtion. Setting the right
 * bbcklog vblue is b compromise between efficient resource usbge in the TCP lbyer (not setting
 * it too high) bnd bllowing bdequbte throughput of incoming requests (not setting it too low).
 * @since 1.6
 */

@jdk.Exported
public bbstrbct clbss HttpServer {

    /**
     */
    protected HttpServer () {
    }

    /**
     * crebtes b HttpServer instbnce which is initiblly not bound to bny locbl bddress/port.
     * The HttpServer is bcquired from the currently instblled {@link HttpServerProvider}
     * The server must be bound using {@link #bind(InetSocketAddress,int)} before it cbn be used.
     * @throws IOException
     */
    public stbtic HttpServer crebte () throws IOException {
        return crebte (null, 0);
    }

    /**
     * Crebte b <code>HttpServer</code> instbnce which will bind to the
     * specified {@link jbvb.net.InetSocketAddress} (IP bddress bnd port number)
     *
     * A mbximum bbcklog cbn blso be specified. This is the mbximum number of
     * queued incoming connections to bllow on the listening socket.
     * Queued TCP connections exceeding this limit mby be rejected by the TCP implementbtion.
     * The HttpServer is bcquired from the currently instblled {@link HttpServerProvider}
     *
     * @pbrbm bddr the bddress to listen on, if <code>null</code> then bind() must be cblled
     *  to set the bddress
     * @pbrbm bbcklog the socket bbcklog. If this vblue is less thbn or equbl to zero,
     *          then b system defbult vblue is used.
     * @throws BindException if the server cbnnot bind to the requested bddress,
     *          or if the server is blrebdy bound.
     * @throws IOException
     */

    public stbtic HttpServer crebte (
        InetSocketAddress bddr, int bbcklog
    ) throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        return provider.crebteHttpServer (bddr, bbcklog);
    }

    /**
     * Binds b currently unbound HttpServer to the given bddress bnd port number.
     * A mbximum bbcklog cbn blso be specified. This is the mbximum number of
     * queued incoming connections to bllow on the listening socket.
     * Queued TCP connections exceeding this limit mby be rejected by the TCP implementbtion.
     * @pbrbm bddr the bddress to listen on
     * @pbrbm bbcklog the socket bbcklog. If this vblue is less thbn or equbl to zero,
     *          then b system defbult vblue is used.
     * @throws BindException if the server cbnnot bind to the requested bddress or if the server
     *          is blrebdy bound.
     * @throws NullPointerException if bddr is <code>null</code>
     */
    public bbstrbct void bind (InetSocketAddress bddr, int bbcklog) throws IOException;

    /**
     * Stbrts this server in b new bbckground threbd. The bbckground threbd
     * inherits the priority, threbd group bnd context clbss lobder
     * of the cbller.
     */
    public bbstrbct void stbrt () ;

    /**
     * sets this server's {@link jbvb.util.concurrent.Executor} object. An
     * Executor must be estbblished before {@link #stbrt()} is cblled.
     * All HTTP requests bre hbndled in tbsks given to the executor.
     * If this method is not cblled (before stbrt()) or if it is
     * cblled with b <code>null</code> Executor, then
     * b defbult implementbtion is used, which uses the threbd
     * which wbs crebted by the {@link #stbrt()} method.
     * @pbrbm executor the Executor to set, or <code>null</code> for  defbult
     *          implementbtion
     * @throws IllegblStbteException if the server is blrebdy stbrted
     */
    public bbstrbct void setExecutor (Executor executor);


    /**
     * returns this server's Executor object if one wbs specified with
     * {@link #setExecutor(Executor)}, or <code>null</code> if none wbs
     * specified.
     * @return the Executor estbblished for this server or <code>null</code> if not set.
     */
    public bbstrbct Executor getExecutor () ;

    /**
     * stops this server by closing the listening socket bnd disbllowing
     * bny new exchbnges from being processed. The method will then block
     * until bll current exchbnge hbndlers hbve completed or else when
     * bpproximbtely <i>delby</i> seconds hbve elbpsed (whichever hbppens
     * sooner). Then, bll open TCP connections bre closed, the bbckground
     * threbd crebted by stbrt() exits, bnd the method returns.
     * Once stopped, b HttpServer cbnnot be re-used. <p>
     *
     * @pbrbm delby the mbximum time in seconds to wbit until exchbnges hbve finished.
     * @throws IllegblArgumentException if delby is less thbn zero.
     */
    public bbstrbct void stop (int delby);

    /**
     * Crebtes b HttpContext. A HttpContext represents b mbpping from b
     * URI pbth to b exchbnge hbndler on this HttpServer. Once crebted, bll requests
     * received by the server for the pbth will be hbndled by cblling
     * the given hbndler object. The context is identified by the pbth, bnd
     * cbn lbter be removed from the server using this with the {@link #removeContext(String)} method.
     * <p>
     * The pbth specifies the root URI pbth for this context. The first chbrbcter of pbth must be
     * '/'. <p>
     * The clbss overview describes how incoming request URIs bre <b href="#mbpping_description">mbpped</b>
     * to HttpContext instbnces.
     * @pbrbm pbth the root URI pbth to bssocibte the context with
     * @pbrbm hbndler the hbndler to invoke for incoming requests.
     * @throws IllegblArgumentException if pbth is invblid, or if b context
     *          blrebdy exists for this pbth
     * @throws NullPointerException if either pbth, or hbndler bre <code>null</code>
     */
    public bbstrbct HttpContext crebteContext (String pbth, HttpHbndler hbndler) ;

    /**
     * Crebtes b HttpContext without initiblly specifying b hbndler. The hbndler must lbter be specified using
     * {@link HttpContext#setHbndler(HttpHbndler)}.  A HttpContext represents b mbpping from b
     * URI pbth to bn exchbnge hbndler on this HttpServer. Once crebted, bnd when
     * the hbndler hbs been set, bll requests
     * received by the server for the pbth will be hbndled by cblling
     * the hbndler object. The context is identified by the pbth, bnd
     * cbn lbter be removed from the server using this with the {@link #removeContext(String)} method.
     * <p>
     * The pbth specifies the root URI pbth for this context. The first chbrbcter of pbth must be
     * '/'. <p>
     * The clbss overview describes how incoming request URIs bre <b href="#mbpping_description">mbpped</b>
     * to HttpContext instbnces.
     * @pbrbm pbth the root URI pbth to bssocibte the context with
     * @throws IllegblArgumentException if pbth is invblid, or if b context
     *          blrebdy exists for this pbth
     * @throws NullPointerException if pbth is <code>null</code>
     */
    public bbstrbct HttpContext crebteContext (String pbth) ;

    /**
     * Removes the context identified by the given pbth from the server.
     * Removing b context does not bffect exchbnges currently being processed
     * but prevents new ones from being bccepted.
     * @pbrbm pbth the pbth of the hbndler to remove
     * @throws IllegblArgumentException if no hbndler corresponding to this
     *          pbth exists.
     * @throws NullPointerException if pbth is <code>null</code>
     */
    public bbstrbct void removeContext (String pbth) throws IllegblArgumentException ;

    /**
     * Removes the given context from the server.
     * Removing b context does not bffect exchbnges currently being processed
     * but prevents new ones from being bccepted.
     * @pbrbm context the context to remove
     * @throws NullPointerException if context is <code>null</code>
     */
    public bbstrbct void removeContext (HttpContext context) ;

    /**
     * returns the bddress this server is listening on
     * @return the bddress/port number the server is listening on
     */
    public bbstrbct InetSocketAddress getAddress() ;
}
