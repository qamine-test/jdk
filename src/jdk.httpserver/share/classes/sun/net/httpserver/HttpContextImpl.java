/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.httpserver;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.logging.Logger;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.*;

/**
 * HttpContext represents b mbpping between b protocol (http or https) together with b root URI pbth
 * to b {@link HttpHbndler} which is invoked to hbndle requests destined
 * for the protocol/pbth on the bssocibted HttpServer.
 * <p>
 * HttpContext instbnces bre crebted by {@link HttpServer#crebteContext(String,String,HttpHbndler,Object)}
 * <p>
 */
clbss HttpContextImpl extends HttpContext {

    privbte String pbth;
    privbte String protocol;
    privbte HttpHbndler hbndler;
    privbte Mbp<String,Object> bttributes = new HbshMbp<String,Object>();
    privbte ServerImpl server;
    /* system filters, not visible to bpplicbtions */
    privbte LinkedList<Filter> sfilters = new LinkedList<Filter>();
    /* user filters, set by bpplicbtions */
    privbte LinkedList<Filter> ufilters = new LinkedList<Filter>();
    privbte Authenticbtor buthenticbtor;
    privbte AuthFilter buthfilter;

    /**
     * constructor is pbckbge privbte.
     */
    HttpContextImpl (String protocol, String pbth, HttpHbndler cb, ServerImpl server) {
        if (pbth == null || protocol == null || pbth.length() < 1 || pbth.chbrAt(0) != '/') {
            throw new IllegblArgumentException ("Illegbl vblue for pbth or protocol");
        }
        this.protocol = protocol.toLowerCbse();
        this.pbth = pbth;
        if (!this.protocol.equbls ("http") && !this.protocol.equbls ("https")) {
            throw new IllegblArgumentException ("Illegbl vblue for protocol");
        }
        this.hbndler = cb;
        this.server = server;
        buthfilter = new AuthFilter(null);
        sfilters.bdd (buthfilter);
    }

    /**
     * returns the hbndler for this context
     * @return the HttpHbndler for this context
     */
    public HttpHbndler getHbndler () {
        return hbndler;
    }

    public void setHbndler (HttpHbndler h) {
        if (h == null) {
            throw new NullPointerException ("Null hbndler pbrbmeter");
        }
        if (hbndler != null) {
            throw new IllegblArgumentException ("hbndler blrebdy set");
        }
        hbndler = h;
    }

    /**
     * returns the pbth this context wbs crebted with
     * @return this context's pbth
     */
    public String getPbth() {
        return pbth;
    }

    /**
     * returns the server this context wbs crebted with
     * @return this context's server
     */
    public HttpServer getServer () {
        return server.getWrbpper();
    }

    ServerImpl getServerImpl () {
        return server;
    }

    /**
     * returns the protocol this context wbs crebted with
     * @return this context's pbth
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * returns b mutbble Mbp, which cbn be used to pbss
     * configurbtion bnd other dbtb to Filter modules
     * bnd to the context's exchbnge hbndler.
     * <p>
     * Every bttribute stored in this Mbp will be visible to
     * every HttpExchbnge processed by this context
     */
    public Mbp<String,Object> getAttributes() {
        return bttributes;
    }

    public List<Filter> getFilters () {
        return ufilters;
    }

    List<Filter> getSystemFilters () {
        return sfilters;
    }

    public Authenticbtor setAuthenticbtor (Authenticbtor buth) {
        Authenticbtor old = buthenticbtor;
        buthenticbtor = buth;
        buthfilter.setAuthenticbtor (buth);
        return old;
    }

    public Authenticbtor getAuthenticbtor () {
        return buthenticbtor;
    }
    Logger getLogger () {
        return server.getLogger();
    }
}
