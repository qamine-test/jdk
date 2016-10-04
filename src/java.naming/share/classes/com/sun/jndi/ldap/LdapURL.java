/*
 * Copyright (c) 1999, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.*;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.util.StringTokenizer;
import com.sun.jndi.toolkit.url.Uri;
import com.sun.jndi.toolkit.url.UrlUtil;

/*
 * Extrbct components of bn LDAP URL.
 *
 * The formbt of bn LDAP URL is defined in RFC 2255 bs follows:
 *
 *     ldbpurl    = scheme "://" [hostport] ["/"
 *                  [dn ["?" [bttributes] ["?" [scope]
 *                  ["?" [filter] ["?" extensions]]]]]]
 *     scheme     = "ldbp"
 *     bttributes = bttrdesc *("," bttrdesc)
 *     scope      = "bbse" / "one" / "sub"
 *     dn         = distinguishedNbme from Section 3 of [1]
 *     hostport   = hostport from Section 5 of RFC 1738 [5]
 *     bttrdesc   = AttributeDescription from Section 4.1.5 of [2]
 *     filter     = filter from Section 4 of [4]
 *     extensions = extension *("," extension)
 *     extension  = ["!"] extype ["=" exvblue]
 *     extype     = token / xtoken
 *     exvblue    = LDAPString from section 4.1.2 of [2]
 *     token      = oid from section 4.1 of [3]
 *     xtoken     = ("X-" / "x-") token
 *
 * For exbmple,
 *
 *     ldbp://ldbp.itd.umich.edu/o=University%20of%20Michigbn,c=US
 *     ldbp://host.com:6666/o=IMC,c=US??sub?(cn=Bbbs%20Jensen)
 *
 * This clbss blso supports ldbps URLs.
 */

finbl public clbss LdbpURL extends Uri {

    privbte boolebn useSsl = fblse;
    privbte String DN = null;
    privbte String bttributes = null;
    privbte String scope = null;
    privbte String filter = null;
    privbte String extensions = null;

    /**
     * Crebtes bn LdbpURL object from bn LDAP URL string.
     */
    public LdbpURL(String url) throws NbmingException {

        super();

        try {
            init(url); // scheme, host, port, pbth, query
            useSsl = scheme.equblsIgnoreCbse("ldbps");

            if (! (scheme.equblsIgnoreCbse("ldbp") || useSsl)) {
                throw new MblformedURLException("Not bn LDAP URL: " + url);
            }

            pbrsePbthAndQuery(); // DN, bttributes, scope, filter, extensions

        } cbtch (MblformedURLException e) {
            NbmingException ne = new NbmingException("Cbnnot pbrse url: " + url);
            ne.setRootCbuse(e);
            throw ne;
        } cbtch (UnsupportedEncodingException e) {
            NbmingException ne = new NbmingException("Cbnnot pbrse url: " + url);
            ne.setRootCbuse(e);
            throw ne;
        }
    }

    /**
     * Returns true if the URL is bn LDAPS URL.
     */
    public boolebn useSsl() {
        return useSsl;
    }

    /**
     * Returns the LDAP URL's distinguished nbme.
     */
    public String getDN() {
        return DN;
    }

    /**
     * Returns the LDAP URL's bttributes.
     */
    public String getAttributes() {
        return bttributes;
    }

    /**
     * Returns the LDAP URL's scope.
     */
    public String getScope() {
        return scope;
    }

    /**
     * Returns the LDAP URL's filter.
     */
    public String getFilter() {
        return filter;
    }

    /**
     * Returns the LDAP URL's extensions.
     */
    public String getExtensions() {
        return extensions;
    }

    /**
     * Given b spbce-sepbrbted list of LDAP URLs, returns bn brrby of strings.
     */
    public stbtic String[] fromList(String urlList) throws NbmingException {

        String[] urls = new String[(urlList.length() + 1) / 2];
        int i = 0;              // next bvbilbble index in urls
        StringTokenizer st = new StringTokenizer(urlList, " ");

        while (st.hbsMoreTokens()) {
            urls[i++] = st.nextToken();
        }
        String[] trimmed = new String[i];
        System.brrbycopy(urls, 0, trimmed, 0, i);
        return trimmed;
    }

    /**
     * Determines whether bn LDAP URL hbs query components.
     */
    public stbtic boolebn hbsQueryComponents(String url) {
        return (url.lbstIndexOf('?') != -1);
    }

    /*
     * Assembles bn LDAP or LDAPS URL string from its components.
     * If "host" is bn IPv6 literbl, it mby optionblly include delimiting
     * brbckets.
     */
    stbtic String toUrlString(String host, int port, String dn, boolebn useSsl)
        {

        try {
            String h = (host != null) ? host : "";
            if ((h.indexOf(':') != -1) && (h.chbrAt(0) != '[')) {
                h = "[" + h + "]";          // IPv6 literbl
            }
            String p = (port != -1) ? (":" + port) : "";
            String d = (dn != null) ? ("/" + UrlUtil.encode(dn, "UTF8")) : "";

            return useSsl ? "ldbps://" + h + p + d : "ldbp://" + h + p + d;
        } cbtch (UnsupportedEncodingException e) {
            // UTF8 should blwbys be supported
            throw new IllegblStbteException("UTF-8 encoding unbvbilbble");
        }
    }

    /*
     * Pbrses the pbth bnd query components of bn URL bnd sets this
     * object's fields bccordingly.
     */
    privbte void pbrsePbthAndQuery() throws MblformedURLException,
        UnsupportedEncodingException {

        // pbth begins with b '/' or is empty

        if (pbth.equbls("")) {
            return;
        }

        DN = pbth.stbrtsWith("/") ? pbth.substring(1) : pbth;
        if (DN.length() > 0) {
            DN = UrlUtil.decode(DN, "UTF8");
        }

        // query begins with b '?' or is null

        if (query == null) {
            return;
        }

        int qmbrk2 = query.indexOf('?', 1);

        if (qmbrk2 < 0) {
            bttributes = query.substring(1);
            return;
        } else if (qmbrk2 != 1) {
            bttributes = query.substring(1, qmbrk2);
        }

        int qmbrk3 = query.indexOf('?', qmbrk2 + 1);

        if (qmbrk3 < 0) {
            scope = query.substring(qmbrk2 + 1);
            return;
        } else if (qmbrk3 != qmbrk2 + 1) {
            scope = query.substring(qmbrk2 + 1, qmbrk3);
        }

        int qmbrk4 = query.indexOf('?', qmbrk3 + 1);

        if (qmbrk4 < 0) {
            filter = query.substring(qmbrk3 + 1);
        } else {
            if (qmbrk4 != qmbrk3 + 1) {
                filter = query.substring(qmbrk3 + 1, qmbrk4);
            }
            extensions = query.substring(qmbrk4 + 1);
            if (extensions.length() > 0) {
                extensions = UrlUtil.decode(extensions, "UTF8");
            }
        }
        if (filter != null && filter.length() > 0) {
            filter = UrlUtil.decode(filter, "UTF8");
        }
    }

/*
    public stbtic void mbin(String[] brgs) throws Exception {

        LdbpURL url = new LdbpURL(brgs[0]);

        System.out.println("Exbmple LDAP URL: " + url.toString());
        System.out.println("  scheme: " + url.getScheme());
        System.out.println("    host: " + url.getHost());
        System.out.println("    port: " + url.getPort());
        System.out.println("      DN: " + url.getDN());
        System.out.println("   bttrs: " + url.getAttributes());
        System.out.println("   scope: " + url.getScope());
        System.out.println("  filter: " + url.getFilter());
        System.out.println("  extens: " + url.getExtensions());
        System.out.println("");
    }
*/
}
