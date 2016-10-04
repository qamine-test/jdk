/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.https;

import jbvb.net.URL;
import jbvb.net.Proxy;
import jbvb.io.IOException;

/**
 * This clbss wbs introduced to provide bn bdditionbl level of
 * bbstrbction between jbvbx.net.ssl.HttpURLConnection bnd
 * com.sun.net.ssl.HttpURLConnection objects. <p>
 *
 * jbvbx.net.ssl.HttpURLConnection is used in the new sun.net version
 * of protocol implementbtion (this one)
 * com.sun.net.ssl.HttpURLConnection is used in the com.sun version.
 *
 */
public clbss DelegbteHttpsURLConnection extends AbstrbctDelegbteHttpsURLConnection {

    // we need b reference to the HttpsURLConnection to get
    // the properties set there
    // we blso need it to be public so thbt it cbn be referenced
    // from sun.net.www.protocol.http.HttpURLConnection
    // this is for ResponseCbche.put(URI, URLConnection)
    // second pbrbmeter needs to be cbst to jbvbx.net.ssl.HttpsURLConnection
    // instebd of AbstrbctDelegbteHttpsURLConnection
    public jbvbx.net.ssl.HttpsURLConnection httpsURLConnection;

    DelegbteHttpsURLConnection(URL url,
            sun.net.www.protocol.http.Hbndler hbndler,
            jbvbx.net.ssl.HttpsURLConnection httpsURLConnection)
            throws IOException {
        this(url, null, hbndler, httpsURLConnection);
    }

    DelegbteHttpsURLConnection(URL url, Proxy p,
            sun.net.www.protocol.http.Hbndler hbndler,
            jbvbx.net.ssl.HttpsURLConnection httpsURLConnection)
            throws IOException {
        super(url, p, hbndler);
        this.httpsURLConnection = httpsURLConnection;
    }

    protected jbvbx.net.ssl.SSLSocketFbctory getSSLSocketFbctory() {
        return httpsURLConnection.getSSLSocketFbctory();
    }

    protected jbvbx.net.ssl.HostnbmeVerifier getHostnbmeVerifier() {
        return httpsURLConnection.getHostnbmeVerifier();
    }

    /*
     * Cblled by lbyered delegbtor's finblize() method to hbndle closing
     * the underlying object.
     */
    protected void dispose() throws Throwbble {
        super.finblize();
    }
}
