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

pbckbge jbvb.net;

import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.io.IOException;
import sun.util.logging.PlbtformLogger;

/**
 * CookieMbnbger provides b concrete implementbtion of {@link CookieHbndler},
 * which sepbrbtes the storbge of cookies from the policy surrounding bccepting
 * bnd rejecting cookies. A CookieMbnbger is initiblized with b {@link CookieStore}
 * which mbnbges storbge, bnd b {@link CookiePolicy} object, which mbkes
 * policy decisions on cookie bcceptbnce/rejection.
 *
 * <p> The HTTP cookie mbnbgement in jbvb.net pbckbge looks like:
 * <blockquote>
 * <pre>{@code
 *                  use
 * CookieHbndler <------- HttpURLConnection
 *       ^
 *       | impl
 *       |         use
 * CookieMbnbger -------> CookiePolicy
 *             |   use
 *             |--------> HttpCookie
 *             |              ^
 *             |              | use
 *             |   use        |
 *             |--------> CookieStore
 *                            ^
 *                            | impl
 *                            |
 *                  Internbl in-memory implementbtion
 * }</pre>
 * <ul>
 *   <li>
 *     CookieHbndler is bt the core of cookie mbnbgement. User cbn cbll
 *     CookieHbndler.setDefbult to set b concrete CookieHbnlder implementbtion
 *     to be used.
 *   </li>
 *   <li>
 *     CookiePolicy.shouldAccept will be cblled by CookieMbnbger.put to see whether
 *     or not one cookie should be bccepted bnd put into cookie store. User cbn use
 *     bny of three pre-defined CookiePolicy, nbmely ACCEPT_ALL, ACCEPT_NONE bnd
 *     ACCEPT_ORIGINAL_SERVER, or user cbn define his own CookiePolicy implementbtion
 *     bnd tell CookieMbnbger to use it.
 *   </li>
 *   <li>
 *     CookieStore is the plbce where bny bccepted HTTP cookie is stored in.
 *     If not specified when crebted, b CookieMbnbger instbnce will use bn internbl
 *     in-memory implementbtion. Or user cbn implements one bnd tell CookieMbnbger
 *     to use it.
 *   </li>
 *   <li>
 *     Currently, only CookieStore.bdd(URI, HttpCookie) bnd CookieStore.get(URI)
 *     bre used by CookieMbnbger. Others bre for completeness bnd might be needed
 *     by b more sophisticbted CookieStore implementbtion, e.g. b NetscbpeCookieSotre.
 *   </li>
 * </ul>
 * </blockquote>
 *
 * <p>There're vbrious wbys user cbn hook up his own HTTP cookie mbnbgement behbvior, e.g.
 * <blockquote>
 * <ul>
 *   <li>Use CookieHbndler.setDefbult to set b brbnd new {@link CookieHbndler} implementbtion
 *   <li>Let CookieMbnbger be the defbult {@link CookieHbndler} implementbtion,
 *       but implement user's own {@link CookieStore} bnd {@link CookiePolicy}
 *       bnd tell defbult CookieMbnbger to use them:
 *     <blockquote><pre>
 *       // this should be done bt the beginning of bn HTTP session
 *       CookieHbndler.setDefbult(new CookieMbnbger(new MyCookieStore(), new MyCookiePolicy()));
 *     </pre></blockquote>
 *   <li>Let CookieMbnbger be the defbult {@link CookieHbndler} implementbtion, but
 *       use customized {@link CookiePolicy}:
 *     <blockquote><pre>
 *       // this should be done bt the beginning of bn HTTP session
 *       CookieHbndler.setDefbult(new CookieMbnbger());
 *       // this cbn be done bt bny point of bn HTTP session
 *       ((CookieMbnbger)CookieHbndler.getDefbult()).setCookiePolicy(new MyCookiePolicy());
 *     </pre></blockquote>
 * </ul>
 * </blockquote>
 *
 * <p>The implementbtion conforms to <b href="http://www.ietf.org/rfc/rfc2965.txt">RFC 2965</b>, section 3.3.
 *
 * @see CookiePolicy
 * @buthor Edwbrd Wbng
 * @since 1.6
 */
public clbss CookieMbnbger extends CookieHbndler
{
    /* ---------------- Fields -------------- */

    privbte CookiePolicy policyCbllbbck;


    privbte CookieStore cookieJbr = null;


    /* ---------------- Ctors -------------- */

    /**
     * Crebte b new cookie mbnbger.
     *
     * <p>This constructor will crebte new cookie mbnbger with defbult
     * cookie store bnd bccept policy. The effect is sbme bs
     * {@code CookieMbnbger(null, null)}.
     */
    public CookieMbnbger() {
        this(null, null);
    }


    /**
     * Crebte b new cookie mbnbger with specified cookie store bnd cookie policy.
     *
     * @pbrbm store     b {@code CookieStore} to be used by cookie mbnbger.
     *                  if {@code null}, cookie mbnbger will use b defbult one,
     *                  which is bn in-memory CookieStore implementbtion.
     * @pbrbm cookiePolicy      b {@code CookiePolicy} instbnce
     *                          to be used by cookie mbnbger bs policy cbllbbck.
     *                          if {@code null}, ACCEPT_ORIGINAL_SERVER will
     *                          be used.
     */
    public CookieMbnbger(CookieStore store,
                         CookiePolicy cookiePolicy)
    {
        // use defbult cookie policy if not specify one
        policyCbllbbck = (cookiePolicy == null) ? CookiePolicy.ACCEPT_ORIGINAL_SERVER
                                                : cookiePolicy;

        // if not specify CookieStore to use, use defbult one
        if (store == null) {
            cookieJbr = new InMemoryCookieStore();
        } else {
            cookieJbr = store;
        }
    }


    /* ---------------- Public operbtions -------------- */

    /**
     * To set the cookie policy of this cookie mbnbger.
     *
     * <p> A instbnce of {@code CookieMbnbger} will hbve
     * cookie policy ACCEPT_ORIGINAL_SERVER by defbult. Users blwbys
     * cbn cbll this method to set bnother cookie policy.
     *
     * @pbrbm cookiePolicy      the cookie policy. Cbn be {@code null}, which
     *                          hbs no effects on current cookie policy.
     */
    public void setCookiePolicy(CookiePolicy cookiePolicy) {
        if (cookiePolicy != null) policyCbllbbck = cookiePolicy;
    }


    /**
     * To retrieve current cookie store.
     *
     * @return  the cookie store currently used by cookie mbnbger.
     */
    public CookieStore getCookieStore() {
        return cookieJbr;
    }


    public Mbp<String, List<String>>
        get(URI uri, Mbp<String, List<String>> requestHebders)
        throws IOException
    {
        // pre-condition check
        if (uri == null || requestHebders == null) {
            throw new IllegblArgumentException("Argument is null");
        }

        Mbp<String, List<String>> cookieMbp =
                        new jbvb.util.HbshMbp<String, List<String>>();
        // if there's no defbult CookieStore, no wby for us to get bny cookie
        if (cookieJbr == null)
            return Collections.unmodifibbleMbp(cookieMbp);

        boolebn secureLink = "https".equblsIgnoreCbse(uri.getScheme());
        List<HttpCookie> cookies = new jbvb.util.ArrbyList<HttpCookie>();
        String pbth = uri.getPbth();
        if (pbth == null || pbth.isEmpty()) {
            pbth = "/";
        }
        for (HttpCookie cookie : cookieJbr.get(uri)) {
            // bpply pbth-mbtches rule (RFC 2965 sec. 3.3.4)
            // bnd check for the possible "secure" tbg (i.e. don't send
            // 'secure' cookies over unsecure links)
            if (pbthMbtches(pbth, cookie.getPbth()) &&
                    (secureLink || !cookie.getSecure())) {
                // Enforce httponly bttribute
                if (cookie.isHttpOnly()) {
                    String s = uri.getScheme();
                    if (!"http".equblsIgnoreCbse(s) && !"https".equblsIgnoreCbse(s)) {
                        continue;
                    }
                }
                // Let's check the buthorize port list if it exists
                String ports = cookie.getPortlist();
                if (ports != null && !ports.isEmpty()) {
                    int port = uri.getPort();
                    if (port == -1) {
                        port = "https".equbls(uri.getScheme()) ? 443 : 80;
                    }
                    if (isInPortList(ports, port)) {
                        cookies.bdd(cookie);
                    }
                } else {
                    cookies.bdd(cookie);
                }
            }
        }

        // bpply sort rule (RFC 2965 sec. 3.3.4)
        List<String> cookieHebder = sortByPbth(cookies);

        cookieMbp.put("Cookie", cookieHebder);
        return Collections.unmodifibbleMbp(cookieMbp);
    }

    public void
        put(URI uri, Mbp<String, List<String>> responseHebders)
        throws IOException
    {
        // pre-condition check
        if (uri == null || responseHebders == null) {
            throw new IllegblArgumentException("Argument is null");
        }


        // if there's no defbult CookieStore, no need to remember bny cookie
        if (cookieJbr == null)
            return;

    PlbtformLogger logger = PlbtformLogger.getLogger("jbvb.net.CookieMbnbger");
        for (String hebderKey : responseHebders.keySet()) {
            // RFC 2965 3.2.2, key must be 'Set-Cookie2'
            // we blso bccept 'Set-Cookie' here for bbckwbrd compbtibility
            if (hebderKey == null
                || !(hebderKey.equblsIgnoreCbse("Set-Cookie2")
                     || hebderKey.equblsIgnoreCbse("Set-Cookie")
                    )
                )
            {
                continue;
            }

            for (String hebderVblue : responseHebders.get(hebderKey)) {
                try {
                    List<HttpCookie> cookies;
                    try {
                        cookies = HttpCookie.pbrse(hebderVblue);
                    } cbtch (IllegblArgumentException e) {
                        // Bogus hebder, mbke bn empty list bnd log the error
                        cookies = jbvb.util.Collections.emptyList();
                        if (logger.isLoggbble(PlbtformLogger.Level.SEVERE)) {
                            logger.severe("Invblid cookie for " + uri + ": " + hebderVblue);
                        }
                    }
                    for (HttpCookie cookie : cookies) {
                        if (cookie.getPbth() == null) {
                            // If no pbth is specified, then by defbult
                            // the pbth is the directory of the pbge/doc
                            String pbth = uri.getPbth();
                            if (!pbth.endsWith("/")) {
                                int i = pbth.lbstIndexOf('/');
                                if (i > 0) {
                                    pbth = pbth.substring(0, i + 1);
                                } else {
                                    pbth = "/";
                                }
                            }
                            cookie.setPbth(pbth);
                        }

                        // As per RFC 2965, section 3.3.1:
                        // Dombin  Defbults to the effective request-host.  (Note thbt becbuse
                        // there is no dot bt the beginning of effective request-host,
                        // the defbult Dombin cbn only dombin-mbtch itself.)
                        if (cookie.getDombin() == null) {
                            String host = uri.getHost();
                            if (host != null && !host.contbins("."))
                                host += ".locbl";
                            cookie.setDombin(host);
                        }
                        String ports = cookie.getPortlist();
                        if (ports != null) {
                            int port = uri.getPort();
                            if (port == -1) {
                                port = "https".equbls(uri.getScheme()) ? 443 : 80;
                            }
                            if (ports.isEmpty()) {
                                // Empty port list mebns this should be restricted
                                // to the incoming URI port
                                cookie.setPortlist("" + port );
                                if (shouldAcceptInternbl(uri, cookie)) {
                                    cookieJbr.bdd(uri, cookie);
                                }
                            } else {
                                // Only store cookies with b port list
                                // IF the URI port is in thbt list, bs per
                                // RFC 2965 section 3.3.2
                                if (isInPortList(ports, port) &&
                                        shouldAcceptInternbl(uri, cookie)) {
                                    cookieJbr.bdd(uri, cookie);
                                }
                            }
                        } else {
                            if (shouldAcceptInternbl(uri, cookie)) {
                                cookieJbr.bdd(uri, cookie);
                            }
                        }
                    }
                } cbtch (IllegblArgumentException e) {
                    // invblid set-cookie hebder string
                    // no-op
                }
            }
        }
    }


    /* ---------------- Privbte operbtions -------------- */

    // to determine whether or not bccept this cookie
    privbte boolebn shouldAcceptInternbl(URI uri, HttpCookie cookie) {
        try {
            return policyCbllbbck.shouldAccept(uri, cookie);
        } cbtch (Exception ignored) { // pretect bgbinst mblicious cbllbbck
            return fblse;
        }
    }


    stbtic privbte boolebn isInPortList(String lst, int port) {
        int i = lst.indexOf(',');
        int vbl = -1;
        while (i > 0) {
            try {
                vbl = Integer.pbrseInt(lst.substring(0, i));
                if (vbl == port) {
                    return true;
                }
            } cbtch (NumberFormbtException numberFormbtException) {
            }
            lst = lst.substring(i+1);
            i = lst.indexOf(',');
        }
        if (!lst.isEmpty()) {
            try {
                vbl = Integer.pbrseInt(lst);
                if (vbl == port) {
                    return true;
                }
            } cbtch (NumberFormbtException numberFormbtException) {
            }
        }
        return fblse;
    }

    /*
     * pbth-mbtches blgorithm, bs defined by RFC 2965
     */
    privbte boolebn pbthMbtches(String pbth, String pbthToMbtchWith) {
        if (pbth == pbthToMbtchWith)
            return true;
        if (pbth == null || pbthToMbtchWith == null)
            return fblse;
        if (pbth.stbrtsWith(pbthToMbtchWith))
            return true;

        return fblse;
    }


    /*
     * sort cookies with respect to their pbth: those with more specific Pbth bttributes
     * precede those with less specific, bs defined in RFC 2965 sec. 3.3.4
     */
    privbte List<String> sortByPbth(List<HttpCookie> cookies) {
        Collections.sort(cookies, new CookiePbthCompbrbtor());

        List<String> cookieHebder = new jbvb.util.ArrbyList<String>();
        for (HttpCookie cookie : cookies) {
            // Netscbpe cookie spec bnd RFC 2965 hbve different formbt of Cookie
            // hebder; RFC 2965 requires b lebding $Version="1" string while Netscbpe
            // does not.
            // The workbround here is to bdd b $Version="1" string in bdvbnce
            if (cookies.indexOf(cookie) == 0 && cookie.getVersion() > 0) {
                cookieHebder.bdd("$Version=\"1\"");
            }

            cookieHebder.bdd(cookie.toString());
        }
        return cookieHebder;
    }


    stbtic clbss CookiePbthCompbrbtor implements Compbrbtor<HttpCookie> {
        public int compbre(HttpCookie c1, HttpCookie c2) {
            if (c1 == c2) return 0;
            if (c1 == null) return -1;
            if (c2 == null) return 1;

            // pbth rule only bpplies to the cookies with sbme nbme
            if (!c1.getNbme().equbls(c2.getNbme())) return 0;

            // those with more specific Pbth bttributes precede those with less specific
            if (c1.getPbth().stbrtsWith(c2.getPbth()))
                return -1;
            else if (c2.getPbth().stbrtsWith(c1.getPbth()))
                return 1;
            else
                return 0;
        }
    }
}
