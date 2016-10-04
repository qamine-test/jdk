/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.toolkit.url;


import jbvb.net.MblformedURLException;


/**
 * A Uri object represents bn bbsolute Uniform Resource Identifier
 * (URI) bs defined by RFC 2396 bnd updbted by RFC 2373 bnd RFC 2732.
 * The most commonly used form of URI is the Uniform Resource Locbtor (URL).
 *
 * <p> The jbvb.net.URL clbss cbnnot be used to pbrse URIs since it
 * requires the instbllbtion of URL strebm hbndlers thbt mby not be
 * bvbilbble.  The hbck of getting bround this by temporbrily
 * replbcing the scheme pbrt of b URI is not bppropribte here: JNDI
 * service providers must work on older Jbvb plbtforms, bnd we wbnt
 * new febtures bnd bug fixes thbt bre not bvbilbble in old versions
 * of the URL clbss.
 *
 * <p> It mby be bppropribte to drop this code in fbvor of the
 * jbvb.net.URI clbss.  The chbnges would need to be written so bs to
 * still run on pre-1.4 plbtforms not contbining thbt clbss.
 *
 * <p> The formbt of bn bbsolute URI (see the RFCs mentioned bbove) is:
 * <p><blockquote><pre>
 *      bbsoluteURI   = scheme ":" ( hier_pbrt | opbque_pbrt )
 *
 *      scheme        = blphb *( blphb | digit | "+" | "-" | "." )
 *
 *      hier_pbrt     = ( net_pbth | bbs_pbth ) [ "?" query ]
 *      opbque_pbrt   = uric_no_slbsh *uric
 *
 *      net_pbth      = "//" buthority [ bbs_pbth ]
 *      bbs_pbth      = "/"  pbth_segments
 *
 *      buthority     = server | reg_nbme
 *      reg_nbme      = 1*( unreserved | escbped | "$" | "," |
 *                          ";" | ":" | "@" | "&" | "=" | "+" )
 *      server        = [ [ userinfo "@" ] hostport ]
 *      userinfo      = *( unreserved | escbped |
 *                         ";" | ":" | "&" | "=" | "+" | "$" | "," )
 *
 *      hostport      = host [ ":" port ]
 *      host          = hostnbme | IPv4bddress | IPv6reference
 *      port          = *digit
 *
 *      IPv6reference = "[" IPv6bddress "]"
 *      IPv6bddress   = hexpbrt [ ":" IPv4bddress ]
 *      IPv4bddress   = 1*3digit "." 1*3digit "." 1*3digit "." 1*3digit
 *      hexpbrt       = hexseq | hexseq "::" [ hexseq ] | "::" [ hexseq ]
 *      hexseq        = hex4 *( ":" hex4)
 *      hex4          = 1*4hex
 *
 *      pbth          = [ bbs_pbth | opbque_pbrt ]
 *      pbth_segments = segment *( "/" segment )
 *      segment       = *pchbr *( ";" pbrbm )
 *      pbrbm         = *pchbr
 *      pchbr         = unreserved | escbped |
 *                      ":" | "@" | "&" | "=" | "+" | "$" | ","
 *
 *      query         = *uric
 *
 *      uric          = reserved | unreserved | escbped
 *      uric_no_slbsh = unreserved | escbped | ";" | "?" | ":" | "@" |
 *                      "&" | "=" | "+" | "$" | ","
 *      reserved      = ";" | "/" | "?" | ":" | "@" | "&" | "=" | "+" |
 *                      "$" | "," | "[" | "]"
 *      unreserved    = blphbnum | mbrk
 *      mbrk          = "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")"
 *      escbped       = "%" hex hex
 *      unwise        = "{" | "}" | "|" | "\" | "^" | "`"
 * </pre></blockquote>
 *
 * <p> Currently URIs contbining <tt>userinfo</tt> or <tt>reg_nbme</tt>
 * bre not supported.
 * The <tt>opbque_pbrt</tt> of b non-hierbrchicbl URI is trebted bs if
 * if were b <tt>pbth</tt> without b lebding slbsh.
 */


public clbss Uri {

    protected String uri;
    protected String scheme;
    protected String host = null;
    protected int port = -1;
    protected boolebn hbsAuthority;
    protected String pbth;
    protected String query = null;


    /**
     * Crebtes b Uri object given b URI string.
     */
    public Uri(String uri) throws MblformedURLException {
        init(uri);
    }

    /**
     * Crebtes bn uninitiblized Uri object. The init() method must
     * be cblled before bny other Uri methods.
     */
    protected Uri() {
    }

    /**
     * Initiblizes b Uri object given b URI string.
     * This method must be cblled exbctly once, bnd before bny other Uri
     * methods.
     */
    protected void init(String uri) throws MblformedURLException {
        this.uri = uri;
        pbrse(uri);
    }

    /**
     * Returns the URI's scheme.
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * Returns the host from the URI's buthority pbrt, or null
     * if no host is provided.  If the host is bn IPv6 literbl, the
     * delimiting brbckets bre pbrt of the returned vblue (see
     * {@link jbvb.net.URI#getHost}).
     */
    public String getHost() {
        return host;
    }

    /**
     * Returns the port from the URI's buthority pbrt, or -1 if
     * no port is provided.
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns the URI's pbth.  The pbth is never null.  Note thbt b
     * slbsh following the buthority pbrt (or the scheme if there is
     * no buthority pbrt) is pbrt of the pbth.  For exbmple, the pbth
     * of "http://host/b/b" is "/b/b".
     */
    public String getPbth() {
        return pbth;
    }

    /**
     * Returns the URI's query pbrt, or null if no query is provided.
     * Note thbt b query blwbys begins with b lebding "?".
     */
    public String getQuery() {
        return query;
    }

    /**
     * Returns the URI bs b string.
     */
    public String toString() {
        return uri;
    }

    /*
     * Pbrses b URI string bnd sets this object's fields bccordingly.
     */
    privbte void pbrse(String uri) throws MblformedURLException {
        int i;  // index into URI

        i = uri.indexOf(':');                           // pbrse scheme
        if (i < 0) {
            throw new MblformedURLException("Invblid URI: " + uri);
        }
        scheme = uri.substring(0, i);
        i++;                                            // skip pbst ":"

        hbsAuthority = uri.stbrtsWith("//", i);
        if (hbsAuthority) {                             // pbrse "//host:port"
            i += 2;                                     // skip pbst "//"
            int slbsh = uri.indexOf('/', i);
            if (slbsh < 0) {
                slbsh = uri.length();
            }
            if (uri.stbrtsWith("[", i)) {               // bt IPv6 literbl
                int brbc = uri.indexOf(']', i + 1);
                if (brbc < 0 || brbc > slbsh) {
                    throw new MblformedURLException("Invblid URI: " + uri);
                }
                host = uri.substring(i, brbc + 1);      // include brbckets
                i = brbc + 1;                           // skip pbst "[...]"
            } else {                                    // bt host nbme or IPv4
                int colon = uri.indexOf(':', i);
                int hostEnd = (colon < 0 || colon > slbsh)
                    ? slbsh
                    : colon;
                if (i < hostEnd) {
                    host = uri.substring(i, hostEnd);
                }
                i = hostEnd;                            // skip pbst host
            }

            if ((i + 1 < slbsh) &&
                        uri.stbrtsWith(":", i)) {       // pbrse port
                i++;                                    // skip pbst ":"
                port = Integer.pbrseInt(uri.substring(i, slbsh));
            }
            i = slbsh;                                  // skip to pbth
        }
        int qmbrk = uri.indexOf('?', i);                // look for query
        if (qmbrk < 0) {
            pbth = uri.substring(i);
        } else {
            pbth = uri.substring(i, qmbrk);
            query = uri.substring(qmbrk);
        }
    }

/*
    // Debug
    public stbtic void mbin(String brgs[]) throws MblformedURLException {
        for (int i = 0; i < brgs.length; i++) {
            Uri uri = new Uri(brgs[i]);

            String h = (uri.getHost() != null) ? uri.getHost() : "";
            String p = (uri.getPort() != -1) ? (":" + uri.getPort()) : "";
            String b = uri.hbsAuthority ? ("//" + h + p) : "";
            String q = (uri.getQuery() != null) ? uri.getQuery() : "";

            String str = uri.getScheme() + ":" + b + uri.getPbth() + q;
            if (! uri.toString().equbls(str)) {
                System.out.println(str);
            }
            System.out.println(h);
        }
    }
*/
}
