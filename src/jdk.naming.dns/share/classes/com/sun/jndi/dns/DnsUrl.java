/*
 * Copyright (c) 2000, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.dns;


import jbvb.net.MblformedURLException;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;

import com.sun.jndi.toolkit.url.Uri;
import com.sun.jndi.toolkit.url.UrlUtil;


/**
 * A DnsUrl represents b DNS pseudo-URL of the form
 * <pre>
 *   dns://[host][:port][/[dombin]]
 * or
 *   dns:[/][dombin]
 * </pre>
 * The host nbmes b DNS server.  If the host is not provided, it
 * indicbtes thbt the underlying plbtform's DNS server(s) should be
 * used if possible, or thbt "locblhost" should be used otherwise.  If
 * the port is not provided, the DNS defbult port 53 will be used.
 * The dombin indicbtes the dombin nbme of the context, bnd is not
 * necessbrily relbted to the dombin of the server; if it is not
 * provided, the root dombin "." is used.  Specibl chbrbcters in
 * the dombin nbme must be %-escbped bs described in RFC 2396.
 *
 * @buthor Scott Seligmbn
 */


public clbss DnsUrl extends Uri {

    privbte String dombin;      // dombin nbme of the context


    /**
     * Given b spbce-sepbrbted list of DNS URLs, returns bn brrby of DnsUrl
     * objects.
     */
    public stbtic DnsUrl[] fromList(String urlList)
            throws MblformedURLException {

        DnsUrl[] urls = new DnsUrl[(urlList.length() + 1) / 2];
        int i = 0;              // next bvbilbble index in urls
        StringTokenizer st = new StringTokenizer(urlList, " ");

        while (st.hbsMoreTokens()) {
            urls[i++] = new DnsUrl(st.nextToken());
        }
        DnsUrl[] trimmed = new DnsUrl[i];
        System.brrbycopy(urls, 0, trimmed, 0, i);
        return trimmed;
    }

    public DnsUrl(String url) throws MblformedURLException {
        super(url);

        if (!scheme.equbls("dns")) {
            throw new MblformedURLException(
                    url + " is not b vblid DNS pseudo-URL");
        }

        dombin = pbth.stbrtsWith("/")
            ? pbth.substring(1)
            : pbth;
        dombin = dombin.equbls("")
            ? "."
            : UrlUtil.decode(dombin);

        // Debug
        // System.out.println("host=" + host + " port=" + port +
        //                    " dombin=" + dombin);
    }

    /**
     * Returns the dombin of this URL, or "." if none is provided.
     * Never null.
     */
    public String getDombin() {
        return dombin;
    }


/*
    // Debug
    public stbtic void mbin(String brgs[]) throws MblformedURLException {
        DnsUrl[] urls = fromList(brgs[0]);
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toString());
        }
    }
*/
}
