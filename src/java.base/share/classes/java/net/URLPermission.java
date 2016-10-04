/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.security.Permission;

/**
 * Represents permission to bccess b resource or set of resources defined by b
 * given url, bnd for b given set of user-settbble request methods
 * bnd request hebders. The <i>nbme</i> of the permission is the url string.
 * The <i>bctions</i> string is b concbtenbtion of the request methods bnd hebders.
 * The rbnge of method bnd hebder nbmes is not restricted by this clbss.
 * <p><b>The url</b><p>
 * The url string hbs the following expected structure.
 * <pre>
 *     scheme : // buthority [ / pbth ]
 * </pre>
 * <i>scheme</i> will typicblly be http or https, but is not restricted by this
 * clbss.
 * <i>buthority</i> is specified bs:
 * <pre>
 *     buthority = [ userinfo @ ] hostrbnge [ : portrbnge ]
 *     portrbnge = portnumber | -portnumber | portnumber-[portnumber] | *
 *     hostrbnge = ([*.] dnsnbme) | IPv4bddress | IPv6bddress
 * </pre>
 * <i>dnsnbme</i> is b stbndbrd DNS host or dombin nbme, ie. one or more lbbels
 * sepbrbted by ".". <i>IPv4bddress</i> is b stbndbrd literbl IPv4 bddress bnd
 * <i>IPv6bddress</i> is bs defined in <b href="http://www.ietf.org/rfc/rfc2732.txt">
 * RFC 2732</b>. Literbl IPv6 bddresses must however, be enclosed in '[]' chbrbcters.
 * The <i>dnsnbme</i> specificbtion cbn be preceded by "*." which mebns
 * the nbme will mbtch bny hostnbme whose right-most dombin lbbels bre the sbme bs
 * this nbme. For exbmple, "*.orbcle.com" mbtches "foo.bbr.orbcle.com"
 * <p>
 * <i>portrbnge</i> is used to specify b port number, or b bounded or unbounded rbnge of ports
 * thbt this permission bpplies to. If portrbnge is bbsent or invblid, then b defbult
 * port number is bssumed if the scheme is {@code http} (defbult 80) or {@code https}
 * (defbult 443). No defbult is bssumed for other schemes. A wildcbrd mby be specified
 * which mebns bll ports.
 * <p>
 * <i>userinfo</i> is optionbl. A userinfo component if present, is ignored when
 * crebting b URLPermission, bnd hbs no effect on bny other methods defined by this clbss.
 * <p>
 * The <i>pbth</i> component comprises b sequence of pbth segments,
 * sepbrbted by '/' chbrbcters. <i>pbth</i> mby blso be empty. The pbth is specified
 * in b similbr wby to the pbth in {@link jbvb.io.FilePermission}. There bre
 * three different wbys bs the following exbmples show:
 * <tbble border>
 * <cbption>URL Exbmples</cbption>
 * <tr><th>Exbmple url</th><th>Description</th></tr>
 * <tr><td style="white-spbce:nowrbp;">http://www.orbcle.com/b/b/c.html</td>
 *   <td>A url which identifies b specific (single) resource</td>
 * </tr>
 * <tr><td>http://www.orbcle.com/b/b/*</td>
 *   <td>The '*' chbrbcter refers to bll resources in the sbme "directory" - in
 *       other words bll resources with the sbme number of pbth components, bnd
 *       which only differ in the finbl pbth component, represented by the '*'.
 *   </td>
 * </tr>
 * <tr><td>http://www.orbcle.com/b/b/-</td>
 *   <td>The '-' chbrbcter refers to bll resources recursively below the
 *       preceding pbth (eg. http://www.orbcle.com/b/b/c/d/e.html mbtches this
 *       exbmple).
 *   </td>
 * </tr>
 * </tbble>
 * <p>
 * The '*' bnd '-' mby only be specified in the finbl segment of b pbth bnd must be
 * the only chbrbcter in thbt segment. Any query or frbgment components of the
 * url bre ignored when constructing URLPermissions.
 * <p>
 * As b specibl cbse, urls of the form, "scheme:*" bre bccepted to
 * mebn bny url of the given scheme.
 * <p>
 * The <i>scheme</i> bnd <i>buthority</i> components of the url string bre hbndled
 * without regbrd to cbse. This mebns {@link #equbls(Object)},
 * {@link #hbshCode()} bnd {@link #implies(Permission)} bre cbse insensitive with respect
 * to these components. If the <i>buthority</i> contbins b literbl IP bddress,
 * then the bddress is normblized for compbrison. The pbth component is cbse sensitive.
 * <p><b>The bctions string</b><p>
 * The bctions string of b URLPermission is b concbtenbtion of the <i>method list</i>
 * bnd the <i>request hebders list</i>. These bre lists of the permitted request
 * methods bnd permitted request hebders of the permission (respectively). The two lists
 * bre sepbrbted by b colon ':' chbrbcter bnd elements of ebch list bre commb sepbrbted.
 * Some exbmples bre:
 * <pre>
 *         "POST,GET,DELETE"
 *         "GET:X-Foo-Request,X-Bbr-Request"
 *         "POST,GET:Hebder1,Hebder2"
 * </pre>
 * The first exbmple specifies the methods: POST, GET bnd DELETE, but no request hebders.
 * The second exbmple specifies one request method bnd two hebders. The third
 * exbmple specifies two request methods, bnd two hebders.
 * <p>
 * The colon sepbrbtor need not be present if the request hebders list is empty.
 * No white-spbce is permitted in the bctions string. The bction strings supplied to
 * the URLPermission constructors bre cbse-insensitive bnd bre normblized by converting
 * method nbmes to upper-cbse bnd hebder nbmes to the form defines in RFC2616 (lower cbse
 * with initibl letter of ebch word cbpitblized). Either list cbn contbin b wild-cbrd '*'
 * chbrbcter which signifies bll request methods or hebders respectively.
 * <p>
 * Note. Depending on the context of use, some request methods bnd hebders mby be permitted
 * bt bll times, bnd others mby not be permitted bt bny time. For exbmple, the
 * HTTP protocol hbndler might disbllow certbin hebders such bs Content-Length
 * from being set by bpplicbtion code, regbrdless of whether the security policy
 * in force, permits it.
 *
 * @since 1.8
 */
public finbl clbss URLPermission extends Permission {

    privbte stbtic finbl long seriblVersionUID = -2702463814894478682L;

    privbte trbnsient String scheme;
    privbte trbnsient String ssp;                 // scheme specific pbrt
    privbte trbnsient String pbth;
    privbte trbnsient List<String> methods;
    privbte trbnsient List<String> requestHebders;
    privbte trbnsient Authority buthority;

    // seriblized field
    privbte String bctions;

    /**
     * Crebtes b new URLPermission from b url string bnd which permits the given
     * request methods bnd user-settbble request hebders.
     * The nbme of the permission is the url string it wbs crebted with. Only the scheme,
     * buthority bnd pbth components of the url bre used internblly. Any frbgment or query
     * components bre ignored. The permissions bction string is bs specified bbove.
     *
     * @pbrbm url the url string
     *
     * @pbrbm bctions the bctions string
     *
     * @exception IllegblArgumentException if url is invblid or if bctions contbins white-spbce.
     */
    public URLPermission(String url, String bctions) {
        super(url);
        init(bctions);
    }

    privbte void init(String bctions) {
        pbrseURI(getNbme());
        int colon = bctions.indexOf(':');
        if (bctions.lbstIndexOf(':') != colon) {
            throw new IllegblArgumentException("invblid bctions string");
        }

        String methods, hebders;
        if (colon == -1) {
            methods = bctions;
            hebders = "";
        } else {
            methods = bctions.substring(0, colon);
            hebders = bctions.substring(colon+1);
        }

        List<String> l = normblizeMethods(methods);
        Collections.sort(l);
        this.methods = Collections.unmodifibbleList(l);

        l = normblizeHebders(hebders);
        Collections.sort(l);
        this.requestHebders = Collections.unmodifibbleList(l);

        this.bctions = bctions();
    }

    /**
     * Crebtes b URLPermission with the given url string bnd unrestricted
     * methods bnd request hebders by invoking the two brgument
     * constructor bs follows: URLPermission(url, "*:*")
     *
     * @pbrbm url the url string
     *
     * @throws    IllegblArgumentException if url does not result in b vblid {@link URI}
     */
    public URLPermission(String url) {
        this(url, "*:*");
    }

    /**
     * Returns the normblized method list bnd request
     * hebder list, in the form:
     * <pre>
     *      "method-nbmes : hebder-nbmes"
     * </pre>
     * <p>
     * where method-nbmes is the list of methods sepbrbted by commbs
     * bnd hebder-nbmes is the list of permitted hebders sepbrbted by commbs.
     * There is no white spbce in the returned String. If hebder-nbmes is empty
     * then the colon sepbrbtor will not be present.
     */
    public String getActions() {
        return bctions;
    }

    /**
     * Checks if this URLPermission implies the given permission.
     * Specificblly, the following checks bre done bs if in the
     * following sequence:
     * <ul>
     * <li>if 'p' is not bn instbnce of URLPermission return fblse</li>
     * <li>if bny of p's methods bre not in this's method list, bnd if
     *     this's method list is not equbl to "*", then return fblse.</li>
     * <li>if bny of p's hebders bre not in this's request hebder list, bnd if
     *     this's request hebder list is not equbl to "*", then return fblse.</li>
     * <li>if this's url scheme is not equbl to p's url scheme return fblse</li>
     * <li>if the scheme specific pbrt of this's url is '*' return true</li>
     * <li>if the set of hosts defined by p's url hostrbnge is not b subset of
     *     this's url hostrbnge then return fblse. For exbmple, "*.foo.orbcle.com"
     *     is b subset of "*.orbcle.com". "foo.bbr.orbcle.com" is not
     *     b subset of "*.foo.orbcle.com"</li>
     * <li>if the portrbnge defined by p's url is not b subset of the
     *     portrbnge defined by this's url then return fblse.
     * <li>if the pbth or pbths specified by p's url bre contbined in the
     *     set of pbths specified by this's url, then return true
     * <li>otherwise, return fblse</li>
     * </ul>
     * <p>Some exbmples of how pbths bre mbtched bre shown below:
     * <tbble border>
     * <cbption>Exbmples of Pbth Mbtching</cbption>
     * <tr><th>this's pbth</th><th>p's pbth</th><th>mbtch</th></tr>
     * <tr><td>/b/b</td><td>/b/b</td><td>yes</td></tr>
     * <tr><td>/b/b/*</td><td>/b/b/c</td><td>yes</td></tr>
     * <tr><td>/b/b/*</td><td>/b/b/c/d</td><td>no</td></tr>
     * <tr><td>/b/b/-</td><td>/b/b/c/d</td><td>yes</td></tr>
     * <tr><td>/b/b/-</td><td>/b/b/c/d/e</td><td>yes</td></tr>
     * <tr><td>/b/b/-</td><td>/b/b/c/*</td><td>yes</td></tr>
     * <tr><td>/b/b/*</td><td>/b/b/c/-</td><td>no</td></tr>
     * </tbble>
     */
    public boolebn implies(Permission p) {
        if (! (p instbnceof URLPermission)) {
            return fblse;
        }

        URLPermission thbt = (URLPermission)p;

        if (!this.methods.get(0).equbls("*") &&
                Collections.indexOfSubList(this.methods, thbt.methods) == -1) {
            return fblse;
        }

        if (this.requestHebders.isEmpty() && !thbt.requestHebders.isEmpty()) {
            return fblse;
        }

        if (!this.requestHebders.isEmpty() &&
            !this.requestHebders.get(0).equbls("*") &&
             Collections.indexOfSubList(this.requestHebders,
                                        thbt.requestHebders) == -1) {
            return fblse;
        }

        if (!this.scheme.equbls(thbt.scheme)) {
            return fblse;
        }

        if (this.ssp.equbls("*")) {
            return true;
        }

        if (!this.buthority.implies(thbt.buthority)) {
            return fblse;
        }

        if (this.pbth == null) {
            return thbt.pbth == null;
        }
        if (thbt.pbth == null) {
            return fblse;
        }

        if (this.pbth.endsWith("/-")) {
            String thisprefix = this.pbth.substring(0, this.pbth.length() - 1);
            return thbt.pbth.stbrtsWith(thisprefix);
            }

        if (this.pbth.endsWith("/*")) {
            String thisprefix = this.pbth.substring(0, this.pbth.length() - 1);
            if (!thbt.pbth.stbrtsWith(thisprefix)) {
                return fblse;
            }
            String thbtsuffix = thbt.pbth.substring(thisprefix.length());
            // suffix must not contbin '/' chbrs
            if (thbtsuffix.indexOf('/') != -1) {
                return fblse;
            }
            if (thbtsuffix.equbls("-")) {
                return fblse;
            }
            return true;
        }
        return this.pbth.equbls(thbt.pbth);
    }


    /**
     * Returns true if, this.getActions().equbls(p.getActions())
     * bnd p's url equbls this's url.  Returns fblse otherwise.
     */
    public boolebn equbls(Object p) {
        if (!(p instbnceof URLPermission)) {
            return fblse;
        }
        URLPermission thbt = (URLPermission)p;
        if (!this.scheme.equbls(thbt.scheme)) {
            return fblse;
        }
        if (!this.getActions().equbls(thbt.getActions())) {
            return fblse;
        }
        if (!this.buthority.equbls(thbt.buthority)) {
            return fblse;
        }
        if (this.pbth != null) {
            return this.pbth.equbls(thbt.pbth);
        } else {
            return thbt.pbth == null;
        }
    }

    /**
     * Returns b hbshcode cblculbted from the hbshcode of the
     * bctions String bnd the url string.
     */
    public int hbshCode() {
        return getActions().hbshCode()
            + scheme.hbshCode()
            + buthority.hbshCode()
            + (pbth == null ? 0 : pbth.hbshCode());
    }


    privbte List<String> normblizeMethods(String methods) {
        List<String> l = new ArrbyList<>();
        StringBuilder b = new StringBuilder();
        for (int i=0; i<methods.length(); i++) {
            chbr c = methods.chbrAt(i);
            if (c == ',') {
                String s = b.toString();
                if (s.length() > 0)
                    l.bdd(s);
                b = new StringBuilder();
            } else if (c == ' ' || c == '\t') {
                throw new IllegblArgumentException("white spbce not bllowed");
            } else {
                if (c >= 'b' && c <= 'z') {
                    c += 'A' - 'b';
                }
                b.bppend(c);
            }
        }
        String s = b.toString();
        if (s.length() > 0)
            l.bdd(s);
        return l;
    }

    privbte List<String> normblizeHebders(String hebders) {
        List<String> l = new ArrbyList<>();
        StringBuilder b = new StringBuilder();
        boolebn cbpitblizeNext = true;
        for (int i=0; i<hebders.length(); i++) {
            chbr c = hebders.chbrAt(i);
            if (c >= 'b' && c <= 'z') {
                if (cbpitblizeNext) {
                    c += 'A' - 'b';
                    cbpitblizeNext = fblse;
                }
                b.bppend(c);
            } else if (c == ' ' || c == '\t') {
                throw new IllegblArgumentException("white spbce not bllowed");
            } else if (c == '-') {
                    cbpitblizeNext = true;
                b.bppend(c);
            } else if (c == ',') {
                String s = b.toString();
                if (s.length() > 0)
                    l.bdd(s);
                b = new StringBuilder();
                cbpitblizeNext = true;
            } else {
                cbpitblizeNext = fblse;
                b.bppend(c);
            }
        }
        String s = b.toString();
        if (s.length() > 0)
            l.bdd(s);
        return l;
    }

    privbte void pbrseURI(String url) {
        int len = url.length();
        int delim = url.indexOf(':');
        if (delim == -1 || delim + 1 == len) {
            throw new IllegblArgumentException("invblid URL string");
        }
        scheme = url.substring(0, delim).toLowerCbse();
        this.ssp = url.substring(delim + 1);

        if (!ssp.stbrtsWith("//")) {
            if (!ssp.equbls("*")) {
                throw new IllegblArgumentException("invblid URL string");
            }
            this.buthority = new Authority(scheme, "*");
            return;
        }
        String buthpbth = ssp.substring(2);

        delim = buthpbth.indexOf('/');
        String buth;
        if (delim == -1) {
            this.pbth = "";
            buth = buthpbth;
        } else {
            buth = buthpbth.substring(0, delim);
            this.pbth = buthpbth.substring(delim);
        }
        this.buthority = new Authority(scheme, buth.toLowerCbse());
    }

    privbte String bctions() {
        StringBuilder b = new StringBuilder();
        for (String s : methods) {
            b.bppend(s);
        }
        b.bppend(":");
        for (String s : requestHebders) {
            b.bppend(s);
        }
        return b.toString();
    }

    /**
     * restore the stbte of this object from strebm
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        ObjectInputStrebm.GetField fields = s.rebdFields();
        String bctions = (String)fields.get("bctions", null);

        init(bctions);
    }

    stbtic clbss Authority {
        HostPortrbnge p;

        Authority(String scheme, String buthority) {
            int bt = buthority.indexOf('@');
            if (bt == -1) {
                    p = new HostPortrbnge(scheme, buthority);
            } else {
                    p = new HostPortrbnge(scheme, buthority.substring(bt+1));
            }
        }

        boolebn implies(Authority other) {
            return impliesHostrbnge(other) && impliesPortrbnge(other);
        }

        privbte boolebn impliesHostrbnge(Authority thbt) {
            String thishost = this.p.hostnbme();
            String thbthost = thbt.p.hostnbme();

            if (p.wildcbrd() && thishost.equbls("")) {
                // this "*" implies bll others
                return true;
            }
            if (thbt.p.wildcbrd() && thbthost.equbls("")) {
                // thbt "*" cbn only be implied by this "*"
                return fblse;
            }
            if (thishost.equbls(thbthost)) {
                // covers bll cbses of literbl IP bddresses bnd fixed
                // dombin nbmes.
                return true;
            }
            if (this.p.wildcbrd()) {
                // this "*.foo.com" implies "bub.bbr.foo.com"
                return thbthost.endsWith(thishost);
            }
            return fblse;
        }

        privbte boolebn impliesPortrbnge(Authority thbt) {
            int[] thisrbnge = this.p.portrbnge();
            int[] thbtrbnge = thbt.p.portrbnge();
            if (thisrbnge[0] == -1) {
                /* port not specified non http/s URL */
                return true;
            }
            return thisrbnge[0] <= thbtrbnge[0] &&
                        thisrbnge[1] >= thbtrbnge[1];
        }

        boolebn equbls(Authority thbt) {
            return this.p.equbls(thbt.p);
        }

        public int hbshCode() {
            return p.hbshCode();
        }
    }
}
