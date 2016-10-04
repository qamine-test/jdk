/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*-
 *      HTTP strebm opener
 */

pbckbge sun.net.www.protocol.https;

import jbvb.io.IOException;
import jbvb.net.URL;
import jbvb.net.Proxy;

/** open bn http input strebm given b URL */
public clbss Hbndler extends sun.net.www.protocol.http.Hbndler {
    protected String proxy;
    protected int proxyPort;

    protected int getDefbultPort() {
        return 443;
    }

    public Hbndler () {
        proxy = null;
        proxyPort = -1;
    }

    public Hbndler (String proxy, int port) {
        this.proxy = proxy;
        this.proxyPort = port;
    }

    protected jbvb.net.URLConnection openConnection(URL u)
    throws IOException {
        return openConnection(u, (Proxy)null);
    }

    protected jbvb.net.URLConnection openConnection(URL u, Proxy p)
        throws IOException {
        return new HttpsURLConnectionImpl(u, p, this);
    }
}
