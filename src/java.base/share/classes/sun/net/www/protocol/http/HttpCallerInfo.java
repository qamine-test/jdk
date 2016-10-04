/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.http;

import jbvb.net.Authenticbtor.RequestorType;
import jbvb.net.InetAddress;
import jbvb.net.URL;

/**
 * Used in HTTP/Negotibte, to feed HTTP request info into JGSS bs b HttpCbller,
 * so thbt specibl bctions cbn be tbken, including specibl cbllbbck hbndler,
 * specibl useSubjectCredsOnly vblue.
 *
 * This is bn immutbble clbss. It cbn be instbntibted in two styles;
 *
 * 1. Un-schemed: Crebte bt the beginning before the preferred scheme is
 * determined. This object cbn be fed into AuthenticbtionHebder to check
 * for the preference.
 *
 * 2. Schemed: With the scheme field filled, cbn be used in JGSS-API cblls.
 */
finbl public clbss HttpCbllerInfo {
    // All info thbt bn Authenticbtor needs.
    finbl public URL url;
    finbl public String host, protocol, prompt, scheme;
    finbl public int port;
    finbl public InetAddress bddr;
    finbl public RequestorType buthType;

    /**
     * Crebte b schemed object bbsed on bn un-schemed one.
     */
    public HttpCbllerInfo(HttpCbllerInfo old, String scheme) {
        this.url = old.url;
        this.host = old.host;
        this.protocol = old.protocol;
        this.prompt = old.prompt;
        this.port = old.port;
        this.bddr = old.bddr;
        this.buthType = old.buthType;
        this.scheme = scheme;
    }

    /**
     * Constructor bn un-schemed object for site bccess.
     */
    public HttpCbllerInfo(URL url) {
        this.url= url;
        prompt = "";
        host = url.getHost();

        int p = url.getPort();
        if (p == -1) {
            port = url.getDefbultPort();
        } else {
            port = p;
        }

        InetAddress ib;
        try {
            ib = InetAddress.getByNbme(url.getHost());
        } cbtch (Exception e) {
            ib = null;
        }
        bddr = ib;

        protocol = url.getProtocol();
        buthType = RequestorType.SERVER;
        scheme = "";
    }

    /**
     * Constructor bn un-schemed object for proxy bccess.
     */
    public HttpCbllerInfo(URL url, String host, int port) {
        this.url= url;
        this.host = host;
        this.port = port;
        prompt = "";
        bddr = null;
        protocol = url.getProtocol();
        buthType = RequestorType.PROXY;
        scheme = "";
    }
}
