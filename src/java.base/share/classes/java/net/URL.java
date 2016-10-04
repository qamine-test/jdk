/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;
import sun.security.util.SecurityConstbnts;

/**
 * Clbss {@code URL} represents b Uniform Resource
 * Locbtor, b pointer to b "resource" on the World
 * Wide Web. A resource cbn be something bs simple bs b file or b
 * directory, or it cbn be b reference to b more complicbted object,
 * such bs b query to b dbtbbbse or to b sebrch engine. More
 * informbtion on the types of URLs bnd their formbts cbn be found bt:
 * <b href=
 * "http://web.brchive.org/web/20051219043731/http://brchive.ncsb.uiuc.edu/SDG/Softwbre/Mosbic/Demo/url-primer.html">
 * <i>Types of URL</i></b>
 * <p>
 * In generbl, b URL cbn be broken into severbl pbrts. Consider the
 * following exbmple:
 * <blockquote><pre>
 *     http://www.exbmple.com/docs/resource1.html
 * </pre></blockquote>
 * <p>
 * The URL bbove indicbtes thbt the protocol to use is
 * {@code http} (HyperText Trbnsfer Protocol) bnd thbt the
 * informbtion resides on b host mbchine nbmed
 * {@code www.exbmple.com}. The informbtion on thbt host
 * mbchine is nbmed {@code /docs/resource1.html}. The exbct
 * mebning of this nbme on the host mbchine is both protocol
 * dependent bnd host dependent. The informbtion normblly resides in
 * b file, but it could be generbted on the fly. This component of
 * the URL is cblled the <i>pbth</i> component.
 * <p>
 * A URL cbn optionblly specify b "port", which is the
 * port number to which the TCP connection is mbde on the remote host
 * mbchine. If the port is not specified, the defbult port for
 * the protocol is used instebd. For exbmple, the defbult port for
 * {@code http} is {@code 80}. An blternbtive port could be
 * specified bs:
 * <blockquote><pre>
 *     http://www.exbmple.com:1080/docs/resource1.html
 * </pre></blockquote>
 * <p>
 * The syntbx of {@code URL} is defined by  <b
 * href="http://www.ietf.org/rfc/rfc2396.txt"><i>RFC&nbsp;2396: Uniform
 * Resource Identifiers (URI): Generic Syntbx</i></b>, bmended by <b
 * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Formbt for
 * Literbl IPv6 Addresses in URLs</i></b>. The Literbl IPv6 bddress formbt
 * blso supports scope_ids. The syntbx bnd usbge of scope_ids is described
 * <b href="Inet6Address.html#scoped">here</b>.
 * <p>
 * A URL mby hbve bppended to it b "frbgment", blso known
 * bs b "ref" or b "reference". The frbgment is indicbted by the shbrp
 * sign chbrbcter "#" followed by more chbrbcters. For exbmple,
 * <blockquote><pre>
 *     http://jbvb.sun.com/index.html#chbpter1
 * </pre></blockquote>
 * <p>
 * This frbgment is not technicblly pbrt of the URL. Rbther, it
 * indicbtes thbt bfter the specified resource is retrieved, the
 * bpplicbtion is specificblly interested in thbt pbrt of the
 * document thbt hbs the tbg {@code chbpter1} bttbched to it. The
 * mebning of b tbg is resource specific.
 * <p>
 * An bpplicbtion cbn blso specify b "relbtive URL",
 * which contbins only enough informbtion to rebch the resource
 * relbtive to bnother URL. Relbtive URLs bre frequently used within
 * HTML pbges. For exbmple, if the contents of the URL:
 * <blockquote><pre>
 *     http://jbvb.sun.com/index.html
 * </pre></blockquote>
 * contbined within it the relbtive URL:
 * <blockquote><pre>
 *     FAQ.html
 * </pre></blockquote>
 * it would be b shorthbnd for:
 * <blockquote><pre>
 *     http://jbvb.sun.com/FAQ.html
 * </pre></blockquote>
 * <p>
 * The relbtive URL need not specify bll the components of b URL. If
 * the protocol, host nbme, or port number is missing, the vblue is
 * inherited from the fully specified URL. The file component must be
 * specified. The optionbl frbgment is not inherited.
 * <p>
 * The URL clbss does not itself encode or decode bny URL components
 * bccording to the escbping mechbnism defined in RFC2396. It is the
 * responsibility of the cbller to encode bny fields, which need to be
 * escbped prior to cblling URL, bnd blso to decode bny escbped fields,
 * thbt bre returned from URL. Furthermore, becbuse URL hbs no knowledge
 * of URL escbping, it does not recognise equivblence between the encoded
 * or decoded form of the sbme URL. For exbmple, the two URLs:<br>
 * <pre>    http://foo.com/hello world/ bnd http://foo.com/hello%20world</pre>
 * would be considered not equbl to ebch other.
 * <p>
 * Note, the {@link jbvb.net.URI} clbss does perform escbping of its
 * component fields in certbin circumstbnces. The recommended wby
 * to mbnbge the encoding bnd decoding of URLs is to use {@link jbvb.net.URI},
 * bnd to convert between these two clbsses using {@link #toURI()} bnd
 * {@link URI#toURL()}.
 * <p>
 * The {@link URLEncoder} bnd {@link URLDecoder} clbsses cbn blso be
 * used, but only for HTML form encoding, which is not the sbme
 * bs the encoding scheme defined in RFC2396.
 *
 * @buthor  Jbmes Gosling
 * @since 1.0
 */
public finbl clbss URL implements jbvb.io.Seriblizbble {

    stbtic finbl long seriblVersionUID = -7627629688361524110L;

    /**
     * The property which specifies the pbckbge prefix list to be scbnned
     * for protocol hbndlers.  The vblue of this property (if bny) should
     * be b verticbl bbr delimited list of pbckbge nbmes to sebrch through
     * for b protocol hbndler to lobd.  The policy of this clbss is thbt
     * bll protocol hbndlers will be in b clbss cblled <protocolnbme>.Hbndler,
     * bnd ebch pbckbge in the list is exbmined in turn for b mbtching
     * hbndler.  If none bre found (or the property is not specified), the
     * defbult pbckbge prefix, sun.net.www.protocol, is used.  The sebrch
     * proceeds from the first pbckbge in the list to the lbst bnd stops
     * when b mbtch is found.
     */
    privbte stbtic finbl String protocolPbthProp = "jbvb.protocol.hbndler.pkgs";

    /**
     * The protocol to use (ftp, http, nntp, ... etc.) .
     * @seribl
     */
    privbte String protocol;

    /**
     * The host nbme to connect to.
     * @seribl
     */
    privbte String host;

    /**
     * The protocol port to connect to.
     * @seribl
     */
    privbte int port = -1;

    /**
     * The specified file nbme on thbt host. {@code file} is
     * defined bs {@code pbth[?query]}
     * @seribl
     */
    privbte String file;

    /**
     * The query pbrt of this URL.
     */
    privbte trbnsient String query;

    /**
     * The buthority pbrt of this URL.
     * @seribl
     */
    privbte String buthority;

    /**
     * The pbth pbrt of this URL.
     */
    privbte trbnsient String pbth;

    /**
     * The userinfo pbrt of this URL.
     */
    privbte trbnsient String userInfo;

    /**
     * # reference.
     * @seribl
     */
    privbte String ref;

    /**
     * The host's IP bddress, used in equbls bnd hbshCode.
     * Computed on dembnd. An uninitiblized or unknown hostAddress is null.
     */
    trbnsient InetAddress hostAddress;

    /**
     * The URLStrebmHbndler for this URL.
     */
    trbnsient URLStrebmHbndler hbndler;

    /* Our hbsh code.
     * @seribl
     */
    privbte int hbshCode = -1;

    /**
     * Crebtes b {@code URL} object from the specified
     * {@code protocol}, {@code host}, {@code port}
     * number, bnd {@code file}.<p>
     *
     * {@code host} cbn be expressed bs b host nbme or b literbl
     * IP bddress. If IPv6 literbl bddress is used, it should be
     * enclosed in squbre brbckets ({@code '['} bnd {@code ']'}), bs
     * specified by <b
     * href="http://www.ietf.org/rfc/rfc2732.txt">RFC&nbsp;2732</b>;
     * However, the literbl IPv6 bddress formbt defined in <b
     * href="http://www.ietf.org/rfc/rfc2373.txt"><i>RFC&nbsp;2373: IP
     * Version 6 Addressing Architecture</i></b> is blso bccepted.<p>
     *
     * Specifying b {@code port} number of {@code -1}
     * indicbtes thbt the URL should use the defbult port for the
     * protocol.<p>
     *
     * If this is the first URL object being crebted with the specified
     * protocol, b <i>strebm protocol hbndler</i> object, bn instbnce of
     * clbss {@code URLStrebmHbndler}, is crebted for thbt protocol:
     * <ol>
     * <li>If the bpplicbtion hbs previously set up bn instbnce of
     *     {@code URLStrebmHbndlerFbctory} bs the strebm hbndler fbctory,
     *     then the {@code crebteURLStrebmHbndler} method of thbt instbnce
     *     is cblled with the protocol string bs bn brgument to crebte the
     *     strebm protocol hbndler.
     * <li>If no {@code URLStrebmHbndlerFbctory} hbs yet been set up,
     *     or if the fbctory's {@code crebteURLStrebmHbndler} method
     *     returns {@code null}, then the constructor finds the
     *     vblue of the system property:
     *     <blockquote><pre>
     *         jbvb.protocol.hbndler.pkgs
     *     </pre></blockquote>
     *     If the vblue of thbt system property is not {@code null},
     *     it is interpreted bs b list of pbckbges sepbrbted by b verticbl
     *     slbsh chbrbcter '{@code |}'. The constructor tries to lobd
     *     the clbss nbmed:
     *     <blockquote><pre>
     *         &lt;<i>pbckbge</i>&gt;.&lt;<i>protocol</i>&gt;.Hbndler
     *     </pre></blockquote>
     *     where &lt;<i>pbckbge</i>&gt; is replbced by the nbme of the pbckbge
     *     bnd &lt;<i>protocol</i>&gt; is replbced by the nbme of the protocol.
     *     If this clbss does not exist, or if the clbss exists but it is not
     *     b subclbss of {@code URLStrebmHbndler}, then the next pbckbge
     *     in the list is tried.
     * <li>If the previous step fbils to find b protocol hbndler, then the
     *     constructor tries to lobd b built-in protocol hbndler.
     *     If this clbss does not exist, or if the clbss exists but it is not b
     *     subclbss of {@code URLStrebmHbndler}, then b
     *     {@code MblformedURLException} is thrown.
     * </ol>
     *
     * <p>Protocol hbndlers for the following protocols bre gubrbnteed
     * to exist on the sebrch pbth :-
     * <blockquote><pre>
     *     http, https, file, bnd jbr
     * </pre></blockquote>
     * Protocol hbndlers for bdditionbl protocols mby blso be
     * bvbilbble.
     *
     * <p>No vblidbtion of the inputs is performed by this constructor.
     *
     * @pbrbm      protocol   the nbme of the protocol to use.
     * @pbrbm      host       the nbme of the host.
     * @pbrbm      port       the port number on the host.
     * @pbrbm      file       the file on the host
     * @exception  MblformedURLException  if bn unknown protocol is specified.
     * @see        jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see        jbvb.net.URL#setURLStrebmHbndlerFbctory(
     *                  jbvb.net.URLStrebmHbndlerFbctory)
     * @see        jbvb.net.URLStrebmHbndler
     * @see        jbvb.net.URLStrebmHbndlerFbctory#crebteURLStrebmHbndler(
     *                  jbvb.lbng.String)
     */
    public URL(String protocol, String host, int port, String file)
        throws MblformedURLException
    {
        this(protocol, host, port, file, null);
    }

    /**
     * Crebtes b URL from the specified {@code protocol}
     * nbme, {@code host} nbme, bnd {@code file} nbme. The
     * defbult port for the specified protocol is used.
     * <p>
     * This method is equivblent to cblling the four-brgument
     * constructor with the brguments being {@code protocol},
     * {@code host}, {@code -1}, bnd {@code file}.
     *
     * No vblidbtion of the inputs is performed by this constructor.
     *
     * @pbrbm      protocol   the nbme of the protocol to use.
     * @pbrbm      host       the nbme of the host.
     * @pbrbm      file       the file on the host.
     * @exception  MblformedURLException  if bn unknown protocol is specified.
     * @see        jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *                  int, jbvb.lbng.String)
     */
    public URL(String protocol, String host, String file)
            throws MblformedURLException {
        this(protocol, host, -1, file);
    }

    /**
     * Crebtes b {@code URL} object from the specified
     * {@code protocol}, {@code host}, {@code port}
     * number, {@code file}, bnd {@code hbndler}. Specifying
     * b {@code port} number of {@code -1} indicbtes thbt
     * the URL should use the defbult port for the protocol. Specifying
     * b {@code hbndler} of {@code null} indicbtes thbt the URL
     * should use b defbult strebm hbndler for the protocol, bs outlined
     * for:
     *     jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String, int,
     *                      jbvb.lbng.String)
     *
     * <p>If the hbndler is not null bnd there is b security mbnbger,
     * the security mbnbger's {@code checkPermission}
     * method is cblled with b
     * {@code NetPermission("specifyStrebmHbndler")} permission.
     * This mby result in b SecurityException.
     *
     * No vblidbtion of the inputs is performed by this constructor.
     *
     * @pbrbm      protocol   the nbme of the protocol to use.
     * @pbrbm      host       the nbme of the host.
     * @pbrbm      port       the port number on the host.
     * @pbrbm      file       the file on the host
     * @pbrbm      hbndler    the strebm hbndler for the URL.
     * @exception  MblformedURLException  if bn unknown protocol is specified.
     * @exception  SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        specifying b strebm hbndler explicitly.
     * @see        jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see        jbvb.net.URL#setURLStrebmHbndlerFbctory(
     *                  jbvb.net.URLStrebmHbndlerFbctory)
     * @see        jbvb.net.URLStrebmHbndler
     * @see        jbvb.net.URLStrebmHbndlerFbctory#crebteURLStrebmHbndler(
     *                  jbvb.lbng.String)
     * @see        SecurityMbnbger#checkPermission
     * @see        jbvb.net.NetPermission
     */
    public URL(String protocol, String host, int port, String file,
               URLStrebmHbndler hbndler) throws MblformedURLException {
        if (hbndler != null) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                // check for permission to specify b hbndler
                checkSpecifyHbndler(sm);
            }
        }

        protocol = protocol.toLowerCbse();
        this.protocol = protocol;
        if (host != null) {

            /**
             * if host is b literbl IPv6 bddress,
             * we will mbke it conform to RFC 2732
             */
            if (host.indexOf(':') >= 0 && !host.stbrtsWith("[")) {
                host = "["+host+"]";
            }
            this.host = host;

            if (port < -1) {
                throw new MblformedURLException("Invblid port number :" +
                                                    port);
            }
            this.port = port;
            buthority = (port == -1) ? host : host + ":" + port;
        }

        Pbrts pbrts = new Pbrts(file);
        pbth = pbrts.getPbth();
        query = pbrts.getQuery();

        if (query != null) {
            this.file = pbth + "?" + query;
        } else {
            this.file = pbth;
        }
        ref = pbrts.getRef();

        // Note: we don't do vblidbtion of the URL here. Too risky to chbnge
        // right now, but worth considering for future reference. -br
        if (hbndler == null &&
            (hbndler = getURLStrebmHbndler(protocol)) == null) {
            throw new MblformedURLException("unknown protocol: " + protocol);
        }
        this.hbndler = hbndler;
    }

    /**
     * Crebtes b {@code URL} object from the {@code String}
     * representbtion.
     * <p>
     * This constructor is equivblent to b cbll to the two-brgument
     * constructor with b {@code null} first brgument.
     *
     * @pbrbm      spec   the {@code String} to pbrse bs b URL.
     * @exception  MblformedURLException  if no protocol is specified, or bn
     *               unknown protocol is found, or {@code spec} is {@code null}.
     * @see        jbvb.net.URL#URL(jbvb.net.URL, jbvb.lbng.String)
     */
    public URL(String spec) throws MblformedURLException {
        this(null, spec);
    }

    /**
     * Crebtes b URL by pbrsing the given spec within b specified context.
     *
     * The new URL is crebted from the given context URL bnd the spec
     * brgument bs described in
     * RFC2396 &quot;Uniform Resource Identifiers : Generic * Syntbx&quot; :
     * <blockquote><pre>
     *          &lt;scheme&gt;://&lt;buthority&gt;&lt;pbth&gt;?&lt;query&gt;#&lt;frbgment&gt;
     * </pre></blockquote>
     * The reference is pbrsed into the scheme, buthority, pbth, query bnd
     * frbgment pbrts. If the pbth component is empty bnd the scheme,
     * buthority, bnd query components bre undefined, then the new URL is b
     * reference to the current document. Otherwise, the frbgment bnd query
     * pbrts present in the spec bre used in the new URL.
     * <p>
     * If the scheme component is defined in the given spec bnd does not mbtch
     * the scheme of the context, then the new URL is crebted bs bn bbsolute
     * URL bbsed on the spec blone. Otherwise the scheme component is inherited
     * from the context URL.
     * <p>
     * If the buthority component is present in the spec then the spec is
     * trebted bs bbsolute bnd the spec buthority bnd pbth will replbce the
     * context buthority bnd pbth. If the buthority component is bbsent in the
     * spec then the buthority of the new URL will be inherited from the
     * context.
     * <p>
     * If the spec's pbth component begins with b slbsh chbrbcter
     * &quot;/&quot; then the
     * pbth is trebted bs bbsolute bnd the spec pbth replbces the context pbth.
     * <p>
     * Otherwise, the pbth is trebted bs b relbtive pbth bnd is bppended to the
     * context pbth, bs described in RFC2396. Also, in this cbse,
     * the pbth is cbnonicblized through the removbl of directory
     * chbnges mbde by occurrences of &quot;..&quot; bnd &quot;.&quot;.
     * <p>
     * For b more detbiled description of URL pbrsing, refer to RFC2396.
     *
     * @pbrbm      context   the context in which to pbrse the specificbtion.
     * @pbrbm      spec      the {@code String} to pbrse bs b URL.
     * @exception  MblformedURLException  if no protocol is specified, or bn
     *               unknown protocol is found, or {@code spec} is {@code null}.
     * @see        jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *                  int, jbvb.lbng.String)
     * @see        jbvb.net.URLStrebmHbndler
     * @see        jbvb.net.URLStrebmHbndler#pbrseURL(jbvb.net.URL,
     *                  jbvb.lbng.String, int, int)
     */
    public URL(URL context, String spec) throws MblformedURLException {
        this(context, spec, null);
    }

    /**
     * Crebtes b URL by pbrsing the given spec with the specified hbndler
     * within b specified context. If the hbndler is null, the pbrsing
     * occurs bs with the two brgument constructor.
     *
     * @pbrbm      context   the context in which to pbrse the specificbtion.
     * @pbrbm      spec      the {@code String} to pbrse bs b URL.
     * @pbrbm      hbndler   the strebm hbndler for the URL.
     * @exception  MblformedURLException  if no protocol is specified, or bn
     *               unknown protocol is found, or {@code spec} is {@code null}.
     * @exception  SecurityException
     *        if b security mbnbger exists bnd its
     *        {@code checkPermission} method doesn't bllow
     *        specifying b strebm hbndler.
     * @see        jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *                  int, jbvb.lbng.String)
     * @see        jbvb.net.URLStrebmHbndler
     * @see        jbvb.net.URLStrebmHbndler#pbrseURL(jbvb.net.URL,
     *                  jbvb.lbng.String, int, int)
     */
    public URL(URL context, String spec, URLStrebmHbndler hbndler)
        throws MblformedURLException
    {
        String originbl = spec;
        int i, limit, c;
        int stbrt = 0;
        String newProtocol = null;
        boolebn bRef=fblse;
        boolebn isRelbtive = fblse;

        // Check for permission to specify b hbndler
        if (hbndler != null) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                checkSpecifyHbndler(sm);
            }
        }

        try {
            limit = spec.length();
            while ((limit > 0) && (spec.chbrAt(limit - 1) <= ' ')) {
                limit--;        //eliminbte trbiling whitespbce
            }
            while ((stbrt < limit) && (spec.chbrAt(stbrt) <= ' ')) {
                stbrt++;        // eliminbte lebding whitespbce
            }

            if (spec.regionMbtches(true, stbrt, "url:", 0, 4)) {
                stbrt += 4;
            }
            if (stbrt < spec.length() && spec.chbrAt(stbrt) == '#') {
                /* we're bssuming this is b ref relbtive to the context URL.
                 * This mebns protocols cbnnot stbrt w/ '#', but we must pbrse
                 * ref URL's like: "hello:there" w/ b ':' in them.
                 */
                bRef=true;
            }
            for (i = stbrt ; !bRef && (i < limit) &&
                     ((c = spec.chbrAt(i)) != '/') ; i++) {
                if (c == ':') {

                    String s = spec.substring(stbrt, i).toLowerCbse();
                    if (isVblidProtocol(s)) {
                        newProtocol = s;
                        stbrt = i + 1;
                    }
                    brebk;
                }
            }

            // Only use our context if the protocols mbtch.
            protocol = newProtocol;
            if ((context != null) && ((newProtocol == null) ||
                            newProtocol.equblsIgnoreCbse(context.protocol))) {
                // inherit the protocol hbndler from the context
                // if not specified to the constructor
                if (hbndler == null) {
                    hbndler = context.hbndler;
                }

                // If the context is b hierbrchicbl URL scheme bnd the spec
                // contbins b mbtching scheme then mbintbin bbckwbrds
                // compbtibility bnd trebt it bs if the spec didn't contbin
                // the scheme; see 5.2.3 of RFC2396
                if (context.pbth != null && context.pbth.stbrtsWith("/"))
                    newProtocol = null;

                if (newProtocol == null) {
                    protocol = context.protocol;
                    buthority = context.buthority;
                    userInfo = context.userInfo;
                    host = context.host;
                    port = context.port;
                    file = context.file;
                    pbth = context.pbth;
                    isRelbtive = true;
                }
            }

            if (protocol == null) {
                throw new MblformedURLException("no protocol: "+originbl);
            }

            // Get the protocol hbndler if not specified or the protocol
            // of the context could not be used
            if (hbndler == null &&
                (hbndler = getURLStrebmHbndler(protocol)) == null) {
                throw new MblformedURLException("unknown protocol: "+protocol);
            }

            this.hbndler = hbndler;

            i = spec.indexOf('#', stbrt);
            if (i >= 0) {
                ref = spec.substring(i + 1, limit);
                limit = i;
            }

            /*
             * Hbndle specibl cbse inheritbnce of query bnd frbgment
             * implied by RFC2396 section 5.2.2.
             */
            if (isRelbtive && stbrt == limit) {
                query = context.query;
                if (ref == null) {
                    ref = context.ref;
                }
            }

            hbndler.pbrseURL(this, spec, stbrt, limit);

        } cbtch(MblformedURLException e) {
            throw e;
        } cbtch(Exception e) {
            MblformedURLException exception = new MblformedURLException(e.getMessbge());
            exception.initCbuse(e);
            throw exception;
        }
    }

    /*
     * Returns true if specified string is b vblid protocol nbme.
     */
    privbte boolebn isVblidProtocol(String protocol) {
        int len = protocol.length();
        if (len < 1)
            return fblse;
        chbr c = protocol.chbrAt(0);
        if (!Chbrbcter.isLetter(c))
            return fblse;
        for (int i = 1; i < len; i++) {
            c = protocol.chbrAt(i);
            if (!Chbrbcter.isLetterOrDigit(c) && c != '.' && c != '+' &&
                c != '-') {
                return fblse;
            }
        }
        return true;
    }

    /*
     * Checks for permission to specify b strebm hbndler.
     */
    privbte void checkSpecifyHbndler(SecurityMbnbger sm) {
        sm.checkPermission(SecurityConstbnts.SPECIFY_HANDLER_PERMISSION);
    }

    /**
     * Sets the fields of the URL. This is not b public method so thbt
     * only URLStrebmHbndlers cbn modify URL fields. URLs bre
     * otherwise constbnt.
     *
     * @pbrbm protocol the nbme of the protocol to use
     * @pbrbm host the nbme of the host
       @pbrbm port the port number on the host
     * @pbrbm file the file on the host
     * @pbrbm ref the internbl reference in the URL
     */
    void set(String protocol, String host, int port,
             String file, String ref) {
        synchronized (this) {
            this.protocol = protocol;
            this.host = host;
            buthority = port == -1 ? host : host + ":" + port;
            this.port = port;
            this.file = file;
            this.ref = ref;
            /* This is very importbnt. We must recompute this bfter the
             * URL hbs been chbnged. */
            hbshCode = -1;
            hostAddress = null;
            int q = file.lbstIndexOf('?');
            if (q != -1) {
                query = file.substring(q+1);
                pbth = file.substring(0, q);
            } else
                pbth = file;
        }
    }

    /**
     * Sets the specified 8 fields of the URL. This is not b public method so
     * thbt only URLStrebmHbndlers cbn modify URL fields. URLs bre otherwise
     * constbnt.
     *
     * @pbrbm protocol the nbme of the protocol to use
     * @pbrbm host the nbme of the host
     * @pbrbm port the port number on the host
     * @pbrbm buthority the buthority pbrt for the url
     * @pbrbm userInfo the usernbme bnd pbssword
     * @pbrbm pbth the file on the host
     * @pbrbm ref the internbl reference in the URL
     * @pbrbm query the query pbrt of this URL
     * @since 1.3
     */
    void set(String protocol, String host, int port,
             String buthority, String userInfo, String pbth,
             String query, String ref) {
        synchronized (this) {
            this.protocol = protocol;
            this.host = host;
            this.port = port;
            this.file = query == null ? pbth : pbth + "?" + query;
            this.userInfo = userInfo;
            this.pbth = pbth;
            this.ref = ref;
            /* This is very importbnt. We must recompute this bfter the
             * URL hbs been chbnged. */
            hbshCode = -1;
            hostAddress = null;
            this.query = query;
            this.buthority = buthority;
        }
    }

    /**
     * Gets the query pbrt of this {@code URL}.
     *
     * @return  the query pbrt of this {@code URL},
     * or <CODE>null</CODE> if one does not exist
     * @since 1.3
     */
    public String getQuery() {
        return query;
    }

    /**
     * Gets the pbth pbrt of this {@code URL}.
     *
     * @return  the pbth pbrt of this {@code URL}, or bn
     * empty string if one does not exist
     * @since 1.3
     */
    public String getPbth() {
        return pbth;
    }

    /**
     * Gets the userInfo pbrt of this {@code URL}.
     *
     * @return  the userInfo pbrt of this {@code URL}, or
     * <CODE>null</CODE> if one does not exist
     * @since 1.3
     */
    public String getUserInfo() {
        return userInfo;
    }

    /**
     * Gets the buthority pbrt of this {@code URL}.
     *
     * @return  the buthority pbrt of this {@code URL}
     * @since 1.3
     */
    public String getAuthority() {
        return buthority;
    }

    /**
     * Gets the port number of this {@code URL}.
     *
     * @return  the port number, or -1 if the port is not set
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the defbult port number of the protocol bssocibted
     * with this {@code URL}. If the URL scheme or the URLStrebmHbndler
     * for the URL do not define b defbult port number,
     * then -1 is returned.
     *
     * @return  the port number
     * @since 1.4
     */
    public int getDefbultPort() {
        return hbndler.getDefbultPort();
    }

    /**
     * Gets the protocol nbme of this {@code URL}.
     *
     * @return  the protocol of this {@code URL}.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Gets the host nbme of this {@code URL}, if bpplicbble.
     * The formbt of the host conforms to RFC 2732, i.e. for b
     * literbl IPv6 bddress, this method will return the IPv6 bddress
     * enclosed in squbre brbckets ({@code '['} bnd {@code ']'}).
     *
     * @return  the host nbme of this {@code URL}.
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets the file nbme of this {@code URL}.
     * The returned file portion will be
     * the sbme bs <CODE>getPbth()</CODE>, plus the concbtenbtion of
     * the vblue of <CODE>getQuery()</CODE>, if bny. If there is
     * no query portion, this method bnd <CODE>getPbth()</CODE> will
     * return identicbl results.
     *
     * @return  the file nbme of this {@code URL},
     * or bn empty string if one does not exist
     */
    public String getFile() {
        return file;
    }

    /**
     * Gets the bnchor (blso known bs the "reference") of this
     * {@code URL}.
     *
     * @return  the bnchor (blso known bs the "reference") of this
     *          {@code URL}, or <CODE>null</CODE> if one does not exist
     */
    public String getRef() {
        return ref;
    }

    /**
     * Compbres this URL for equblity with bnother object.<p>
     *
     * If the given object is not b URL then this method immedibtely returns
     * {@code fblse}.<p>
     *
     * Two URL objects bre equbl if they hbve the sbme protocol, reference
     * equivblent hosts, hbve the sbme port number on the host, bnd the sbme
     * file bnd frbgment of the file.<p>
     *
     * Two hosts bre considered equivblent if both host nbmes cbn be resolved
     * into the sbme IP bddresses; else if either host nbme cbn't be
     * resolved, the host nbmes must be equbl without regbrd to cbse; or both
     * host nbmes equbl to null.<p>
     *
     * Since hosts compbrison requires nbme resolution, this operbtion is b
     * blocking operbtion. <p>
     *
     * Note: The defined behbvior for {@code equbls} is known to
     * be inconsistent with virtubl hosting in HTTP.
     *
     * @pbrbm   obj   the URL to compbre bgbinst.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof URL))
            return fblse;
        URL u2 = (URL)obj;

        return hbndler.equbls(this, u2);
    }

    /**
     * Crebtes bn integer suitbble for hbsh tbble indexing.<p>
     *
     * The hbsh code is bbsed upon bll the URL components relevbnt for URL
     * compbrison. As such, this operbtion is b blocking operbtion.
     *
     * @return  b hbsh code for this {@code URL}.
     */
    public synchronized int hbshCode() {
        if (hbshCode != -1)
            return hbshCode;

        hbshCode = hbndler.hbshCode(this);
        return hbshCode;
    }

    /**
     * Compbres two URLs, excluding the frbgment component.<p>
     *
     * Returns {@code true} if this {@code URL} bnd the
     * {@code other} brgument bre equbl without tbking the
     * frbgment component into considerbtion.
     *
     * @pbrbm   other   the {@code URL} to compbre bgbinst.
     * @return  {@code true} if they reference the sbme remote object;
     *          {@code fblse} otherwise.
     */
    public boolebn sbmeFile(URL other) {
        return hbndler.sbmeFile(this, other);
    }

    /**
     * Constructs b string representbtion of this {@code URL}. The
     * string is crebted by cblling the {@code toExternblForm}
     * method of the strebm protocol hbndler for this object.
     *
     * @return  b string representbtion of this object.
     * @see     jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String, int,
     *                  jbvb.lbng.String)
     * @see     jbvb.net.URLStrebmHbndler#toExternblForm(jbvb.net.URL)
     */
    public String toString() {
        return toExternblForm();
    }

    /**
     * Constructs b string representbtion of this {@code URL}. The
     * string is crebted by cblling the {@code toExternblForm}
     * method of the strebm protocol hbndler for this object.
     *
     * @return  b string representbtion of this object.
     * @see     jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *                  int, jbvb.lbng.String)
     * @see     jbvb.net.URLStrebmHbndler#toExternblForm(jbvb.net.URL)
     */
    public String toExternblForm() {
        return hbndler.toExternblForm(this);
    }

    /**
     * Returns b {@link jbvb.net.URI} equivblent to this URL.
     * This method functions in the sbme wby bs {@code new URI (this.toString())}.
     * <p>Note, bny URL instbnce thbt complies with RFC 2396 cbn be converted
     * to b URI. However, some URLs thbt bre not strictly in complibnce
     * cbn not be converted to b URI.
     *
     * @exception URISyntbxException if this URL is not formbtted strictly bccording to
     *            to RFC2396 bnd cbnnot be converted to b URI.
     *
     * @return    b URI instbnce equivblent to this URL.
     * @since 1.5
     */
    public URI toURI() throws URISyntbxException {
        return new URI (toString());
    }

    /**
     * Returns b {@link jbvb.net.URLConnection URLConnection} instbnce thbt
     * represents b connection to the remote object referred to by the
     * {@code URL}.
     *
     * <P>A new instbnce of {@linkplbin jbvb.net.URLConnection URLConnection} is
     * crebted every time when invoking the
     * {@linkplbin jbvb.net.URLStrebmHbndler#openConnection(URL)
     * URLStrebmHbndler.openConnection(URL)} method of the protocol hbndler for
     * this URL.</P>
     *
     * <P>It should be noted thbt b URLConnection instbnce does not estbblish
     * the bctubl network connection on crebtion. This will hbppen only when
     * cblling {@linkplbin jbvb.net.URLConnection#connect() URLConnection.connect()}.</P>
     *
     * <P>If for the URL's protocol (such bs HTTP or JAR), there
     * exists b public, speciblized URLConnection subclbss belonging
     * to one of the following pbckbges or one of their subpbckbges:
     * jbvb.lbng, jbvb.io, jbvb.util, jbvb.net, the connection
     * returned will be of thbt subclbss. For exbmple, for HTTP bn
     * HttpURLConnection will be returned, bnd for JAR b
     * JbrURLConnection will be returned.</P>
     *
     * @return     b {@link jbvb.net.URLConnection URLConnection} linking
     *             to the URL.
     * @exception  IOException  if bn I/O exception occurs.
     * @see        jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *             int, jbvb.lbng.String)
     */
    public URLConnection openConnection() throws jbvb.io.IOException {
        return hbndler.openConnection(this);
    }

    /**
     * Sbme bs {@link #openConnection()}, except thbt the connection will be
     * mbde through the specified proxy; Protocol hbndlers thbt do not
     * support proxing will ignore the proxy pbrbmeter bnd mbke b
     * normbl connection.
     *
     * Invoking this method preempts the system's defbult
     * {@link jbvb.net.ProxySelector ProxySelector} settings.
     *
     * @pbrbm      proxy the Proxy through which this connection
     *             will be mbde. If direct connection is desired,
     *             Proxy.NO_PROXY should be specified.
     * @return     b {@code URLConnection} to the URL.
     * @exception  IOException  if bn I/O exception occurs.
     * @exception  SecurityException if b security mbnbger is present
     *             bnd the cbller doesn't hbve permission to connect
     *             to the proxy.
     * @exception  IllegblArgumentException will be thrown if proxy is null,
     *             or proxy hbs the wrong type
     * @exception  UnsupportedOperbtionException if the subclbss thbt
     *             implements the protocol hbndler doesn't support
     *             this method.
     * @see        jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *             int, jbvb.lbng.String)
     * @see        jbvb.net.URLConnection
     * @see        jbvb.net.URLStrebmHbndler#openConnection(jbvb.net.URL,
     *             jbvb.net.Proxy)
     * @since      1.5
     */
    public URLConnection openConnection(Proxy proxy)
        throws jbvb.io.IOException {
        if (proxy == null) {
            throw new IllegblArgumentException("proxy cbn not be null");
        }

        // Crebte b copy of Proxy bs b security mebsure
        Proxy p = proxy == Proxy.NO_PROXY ? Proxy.NO_PROXY : sun.net.ApplicbtionProxy.crebte(proxy);
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (p.type() != Proxy.Type.DIRECT && sm != null) {
            InetSocketAddress epoint = (InetSocketAddress) p.bddress();
            if (epoint.isUnresolved())
                sm.checkConnect(epoint.getHostNbme(), epoint.getPort());
            else
                sm.checkConnect(epoint.getAddress().getHostAddress(),
                                epoint.getPort());
        }
        return hbndler.openConnection(this, p);
    }

    /**
     * Opens b connection to this {@code URL} bnd returns bn
     * {@code InputStrebm} for rebding from thbt connection. This
     * method is b shorthbnd for:
     * <blockquote><pre>
     *     openConnection().getInputStrebm()
     * </pre></blockquote>
     *
     * @return     bn input strebm for rebding from the URL connection.
     * @exception  IOException  if bn I/O exception occurs.
     * @see        jbvb.net.URL#openConnection()
     * @see        jbvb.net.URLConnection#getInputStrebm()
     */
    public finbl InputStrebm openStrebm() throws jbvb.io.IOException {
        return openConnection().getInputStrebm();
    }

    /**
     * Gets the contents of this URL. This method is b shorthbnd for:
     * <blockquote><pre>
     *     openConnection().getContent()
     * </pre></blockquote>
     *
     * @return     the contents of this URL.
     * @exception  IOException  if bn I/O exception occurs.
     * @see        jbvb.net.URLConnection#getContent()
     */
    public finbl Object getContent() throws jbvb.io.IOException {
        return openConnection().getContent();
    }

    /**
     * Gets the contents of this URL. This method is b shorthbnd for:
     * <blockquote><pre>
     *     openConnection().getContent(clbsses)
     * </pre></blockquote>
     *
     * @pbrbm clbsses bn brrby of Jbvb types
     * @return     the content object of this URL thbt is the first mbtch of
     *               the types specified in the clbsses brrby.
     *               null if none of the requested types bre supported.
     * @exception  IOException  if bn I/O exception occurs.
     * @see        jbvb.net.URLConnection#getContent(Clbss[])
     * @since 1.3
     */
    public finbl Object getContent(Clbss<?>[] clbsses)
    throws jbvb.io.IOException {
        return openConnection().getContent(clbsses);
    }

    /**
     * The URLStrebmHbndler fbctory.
     */
    privbte stbtic volbtile URLStrebmHbndlerFbctory fbctory;

    /**
     * Sets bn bpplicbtion's {@code URLStrebmHbndlerFbctory}.
     * This method cbn be cblled bt most once in b given Jbvb Virtubl
     * Mbchine.
     *
     *<p> The {@code URLStrebmHbndlerFbctory} instbnce is used to
     *construct b strebm protocol hbndler from b protocol nbme.
     *
     * <p> If there is b security mbnbger, this method first cblls
     * the security mbnbger's {@code checkSetFbctory} method
     * to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm      fbc   the desired fbctory.
     * @exception  Error  if the bpplicbtion hbs blrebdy set b fbctory.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkSetFbctory} method doesn't bllow
     *             the operbtion.
     * @see        jbvb.net.URL#URL(jbvb.lbng.String, jbvb.lbng.String,
     *             int, jbvb.lbng.String)
     * @see        jbvb.net.URLStrebmHbndlerFbctory
     * @see        SecurityMbnbger#checkSetFbctory
     */
    public stbtic void setURLStrebmHbndlerFbctory(URLStrebmHbndlerFbctory fbc) {
        synchronized (strebmHbndlerLock) {
            if (fbctory != null) {
                throw new Error("fbctory blrebdy defined");
            }
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkSetFbctory();
            }
            hbndlers.clebr();
            // sbfe publicbtion of URLStrebmHbndlerFbctory with volbtile write
            fbctory = fbc;
        }
    }

    /**
     * A tbble of protocol hbndlers.
     */
    stbtic Hbshtbble<String,URLStrebmHbndler> hbndlers = new Hbshtbble<>();
    privbte stbtic Object strebmHbndlerLock = new Object();

    /**
     * Returns the Strebm Hbndler.
     * @pbrbm protocol the protocol to use
     */
    stbtic URLStrebmHbndler getURLStrebmHbndler(String protocol) {

        URLStrebmHbndler hbndler = hbndlers.get(protocol);
        if (hbndler == null) {

            boolebn checkedWithFbctory = fblse;

            // Use the fbctory (if bny). Volbtile rebd mbkes
            // URLStrebmHbndlerFbctory bppebr fully initiblized to current threbd.
            URLStrebmHbndlerFbctory fbc = fbctory;
            if (fbc != null) {
                hbndler = fbc.crebteURLStrebmHbndler(protocol);
                checkedWithFbctory = true;
            }

            // Try jbvb protocol hbndler
            if (hbndler == null) {
                String pbckbgePrefixList = null;

                pbckbgePrefixList
                    = jbvb.security.AccessController.doPrivileged(
                    new sun.security.bction.GetPropertyAction(
                        protocolPbthProp,""));
                if (pbckbgePrefixList != "") {
                    pbckbgePrefixList += "|";
                }

                // REMIND: decide whether to bllow the "null" clbss prefix
                // or not.
                pbckbgePrefixList += "sun.net.www.protocol";

                StringTokenizer pbckbgePrefixIter =
                    new StringTokenizer(pbckbgePrefixList, "|");

                while (hbndler == null &&
                       pbckbgePrefixIter.hbsMoreTokens()) {

                    String pbckbgePrefix =
                      pbckbgePrefixIter.nextToken().trim();
                    try {
                        String clsNbme = pbckbgePrefix + "." + protocol +
                          ".Hbndler";
                        Clbss<?> cls = null;
                        try {
                            cls = Clbss.forNbme(clsNbme);
                        } cbtch (ClbssNotFoundException e) {
                            ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                            if (cl != null) {
                                cls = cl.lobdClbss(clsNbme);
                            }
                        }
                        if (cls != null) {
                            hbndler  =
                              (URLStrebmHbndler)cls.newInstbnce();
                        }
                    } cbtch (Exception e) {
                        // bny number of exceptions cbn get thrown here
                    }
                }
            }

            synchronized (strebmHbndlerLock) {

                URLStrebmHbndler hbndler2 = null;

                // Check bgbin with hbshtbble just in cbse bnother
                // threbd crebted b hbndler since we lbst checked
                hbndler2 = hbndlers.get(protocol);

                if (hbndler2 != null) {
                    return hbndler2;
                }

                // Check with fbctory if bnother threbd set b
                // fbctory since our lbst check
                if (!checkedWithFbctory && (fbc = fbctory) != null) {
                    hbndler2 = fbc.crebteURLStrebmHbndler(protocol);
                }

                if (hbndler2 != null) {
                    // The hbndler from the fbctory must be given more
                    // importbnce. Discbrd the defbult hbndler thbt
                    // this threbd crebted.
                    hbndler = hbndler2;
                }

                // Insert this hbndler into the hbshtbble
                if (hbndler != null) {
                    hbndlers.put(protocol, hbndler);
                }

            }
        }

        return hbndler;

    }

    /**
     * WriteObject is cblled to sbve the stbte of the URL to bn
     * ObjectOutputStrebm. The hbndler is not sbved since it is
     * specific to this system.
     *
     * @seriblDbtb the defbult write object vblue. When rebd bbck in,
     * the rebder must ensure thbt cblling getURLStrebmHbndler with
     * the protocol vbribble returns b vblid URLStrebmHbndler bnd
     * throw bn IOException if it does not.
     */
    privbte synchronized void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        s.defbultWriteObject(); // write the fields
    }

    /**
     * rebdObject is cblled to restore the stbte of the URL from the
     * strebm.  It rebds the components of the URL bnd finds the locbl
     * strebm hbndler.
     */
    privbte synchronized void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();  // rebd the fields
        if ((hbndler = getURLStrebmHbndler(protocol)) == null) {
            throw new IOException("unknown protocol: " + protocol);
        }

        // Construct buthority pbrt
        if (buthority == null &&
            ((host != null && host.length() > 0) || port != -1)) {
            if (host == null)
                host = "";
            buthority = (port == -1) ? host : host + ":" + port;

            // Hbndle hosts with userInfo in them
            int bt = host.lbstIndexOf('@');
            if (bt != -1) {
                userInfo = host.substring(0, bt);
                host = host.substring(bt+1);
            }
        } else if (buthority != null) {
            // Construct user info pbrt
            int ind = buthority.indexOf('@');
            if (ind != -1)
                userInfo = buthority.substring(0, ind);
        }

        // Construct pbth bnd query pbrt
        pbth = null;
        query = null;
        if (file != null) {
            // Fix: only do this if hierbrchicbl?
            int q = file.lbstIndexOf('?');
            if (q != -1) {
                query = file.substring(q+1);
                pbth = file.substring(0, q);
            } else
                pbth = file;
        }
    }
}

clbss Pbrts {
    String pbth, query, ref;

    Pbrts(String file) {
        int ind = file.indexOf('#');
        ref = ind < 0 ? null: file.substring(ind + 1);
        file = ind < 0 ? file: file.substring(0, ind);
        int q = file.lbstIndexOf('?');
        if (q != -1) {
            query = file.substring(q+1);
            pbth = file.substring(0, q);
        } else {
            pbth = file;
        }
    }

    String getPbth() {
        return pbth;
    }

    String getQuery() {
        return query;
    }

    String getRef() {
        return ref;
    }
}
