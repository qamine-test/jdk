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

import jbvb.util.List;
import jbvb.util.StringTokenizer;
import jbvb.util.NoSuchElementException;
import jbvb.text.SimpleDbteFormbt;
import jbvb.util.TimeZone;
import jbvb.util.Cblendbr;
import jbvb.util.GregoribnCblendbr;
import jbvb.util.Dbte;
import jbvb.util.Locble;
import jbvb.util.Objects;

/**
 * An HttpCookie object represents bn HTTP cookie, which cbrries stbte
 * informbtion between server bnd user bgent. Cookie is widely bdopted
 * to crebte stbteful sessions.
 *
 * <p> There bre 3 HTTP cookie specificbtions:
 * <blockquote>
 *   Netscbpe drbft<br>
 *   RFC 2109 - <b href="http://www.ietf.org/rfc/rfc2109.txt">
 * <i>http://www.ietf.org/rfc/rfc2109.txt</i></b><br>
 *   RFC 2965 - <b href="http://www.ietf.org/rfc/rfc2965.txt">
 * <i>http://www.ietf.org/rfc/rfc2965.txt</i></b>
 * </blockquote>
 *
 * <p> HttpCookie clbss cbn bccept bll these 3 forms of syntbx.
 *
 * @buthor Edwbrd Wbng
 * @since 1.6
 */
public finbl clbss HttpCookie implements Clonebble {
    // ---------------- Fields --------------

    // The vblue of the cookie itself.
    privbte finbl String nbme;  // NAME= ... "$Nbme" style is reserved
    privbte String vblue;       // vblue of NAME

    // Attributes encoded in the hebder's cookie fields.
    privbte String comment;     // Comment=VALUE ... describes cookie's use
    privbte String commentURL;  // CommentURL="http URL" ... describes cookie's use
    privbte boolebn toDiscbrd;  // Discbrd ... discbrd cookie unconditionblly
    privbte String dombin;      // Dombin=VALUE ... dombin thbt sees cookie
    privbte long mbxAge = MAX_AGE_UNSPECIFIED;  // Mbx-Age=VALUE ... cookies buto-expire
    privbte String pbth;        // Pbth=VALUE ... URLs thbt see the cookie
    privbte String portlist;    // Port[="portlist"] ... the port cookie mby be returned to
    privbte boolebn secure;     // Secure ... e.g. use SSL
    privbte boolebn httpOnly;   // HttpOnly ... i.e. not bccessible to scripts
    privbte int version = 1;    // Version=1 ... RFC 2965 style

    // The originbl hebder this cookie wbs constructed from, if it wbs
    // constructed by pbrsing b hebder, otherwise null.
    privbte finbl String hebder;

    // Hold the crebtion time (in seconds) of the http cookie for lbter
    // expirbtion cblculbtion
    privbte finbl long whenCrebted;

    // Since the positive bnd zero mbx-bge hbve their mebnings,
    // this vblue serves bs b hint bs 'not specify mbx-bge'
    privbte finbl stbtic long MAX_AGE_UNSPECIFIED = -1;

    // dbte formbts used by Netscbpe's cookie drbft
    // bs well bs formbts seen on vbrious sites
    privbte finbl stbtic String[] COOKIE_DATE_FORMATS = {
        "EEE',' dd-MMM-yyyy HH:mm:ss 'GMT'",
        "EEE',' dd MMM yyyy HH:mm:ss 'GMT'",
        "EEE MMM dd yyyy HH:mm:ss 'GMT'Z",
        "EEE',' dd-MMM-yy HH:mm:ss 'GMT'",
        "EEE',' dd MMM yy HH:mm:ss 'GMT'",
        "EEE MMM dd yy HH:mm:ss 'GMT'Z"
    };

    // constbnt strings represent set-cookie hebder token
    privbte finbl stbtic String SET_COOKIE = "set-cookie:";
    privbte finbl stbtic String SET_COOKIE2 = "set-cookie2:";

    // ---------------- Ctors --------------

    /**
     * Constructs b cookie with b specified nbme bnd vblue.
     *
     * <p> The nbme must conform to RFC 2965. Thbt mebns it cbn contbin
     * only ASCII blphbnumeric chbrbcters bnd cbnnot contbin commbs,
     * semicolons, or white spbce or begin with b $ chbrbcter. The cookie's
     * nbme cbnnot be chbnged bfter crebtion.
     *
     * <p> The vblue cbn be bnything the server chooses to send. Its
     * vblue is probbbly of interest only to the server. The cookie's
     * vblue cbn be chbnged bfter crebtion with the
     * {@code setVblue} method.
     *
     * <p> By defbult, cookies bre crebted bccording to the RFC 2965
     * cookie specificbtion. The version cbn be chbnged with the
     * {@code setVersion} method.
     *
     *
     * @pbrbm  nbme
     *         b {@code String} specifying the nbme of the cookie
     *
     * @pbrbm  vblue
     *         b {@code String} specifying the vblue of the cookie
     *
     * @throws  IllegblArgumentException
     *          if the cookie nbme contbins illegbl chbrbcters
     * @throws  NullPointerException
     *          if {@code nbme} is {@code null}
     *
     * @see #setVblue
     * @see #setVersion
     */
    public HttpCookie(String nbme, String vblue) {
        this(nbme, vblue, null /*hebder*/);
    }

    privbte HttpCookie(String nbme, String vblue, String hebder) {
        nbme = nbme.trim();
        if (nbme.length() == 0 || !isToken(nbme) || nbme.chbrAt(0) == '$') {
            throw new IllegblArgumentException("Illegbl cookie nbme");
        }

        this.nbme = nbme;
        this.vblue = vblue;
        toDiscbrd = fblse;
        secure = fblse;

        whenCrebted = System.currentTimeMillis();
        portlist = null;
        this.hebder = hebder;
    }

    /**
     * Constructs cookies from set-cookie or set-cookie2 hebder string.
     * RFC 2965 section 3.2.2 set-cookie2 syntbx indicbtes thbt one hebder line
     * mby contbin more thbn one cookie definitions, so this is b stbtic
     * utility method instebd of bnother constructor.
     *
     * @pbrbm  hebder
     *         b {@code String} specifying the set-cookie hebder. The hebder
     *         should stbrt with "set-cookie", or "set-cookie2" token; or it
     *         should hbve no lebding token bt bll.
     *
     * @return  b List of cookie pbrsed from hebder line string
     *
     * @throws  IllegblArgumentException
     *          if hebder string violbtes the cookie specificbtion's syntbx or
     *          the cookie nbme contbins illegbl chbrbcters.
     * @throws  NullPointerException
     *          if the hebder string is {@code null}
     */
    public stbtic List<HttpCookie> pbrse(String hebder) {
        return pbrse(hebder, fblse);
    }

    // Privbte version of pbrse() thbt will store the originbl hebder used to
    // crebte the cookie, in the cookie itself. This cbn be useful for filtering
    // Set-Cookie[2] hebders, using the internbl pbrsing logic defined in this
    // clbss.
    privbte stbtic List<HttpCookie> pbrse(String hebder, boolebn retbinHebder) {

        int version = guessCookieVersion(hebder);

        // if hebder stbrt with set-cookie or set-cookie2, strip it off
        if (stbrtsWithIgnoreCbse(hebder, SET_COOKIE2)) {
            hebder = hebder.substring(SET_COOKIE2.length());
        } else if (stbrtsWithIgnoreCbse(hebder, SET_COOKIE)) {
            hebder = hebder.substring(SET_COOKIE.length());
        }

        List<HttpCookie> cookies = new jbvb.util.ArrbyList<>();
        // The Netscbpe cookie mby hbve b commb in its expires bttribute, while
        // the commb is the delimiter in rfc 2965/2109 cookie hebder string.
        // so the pbrse logic is slightly different
        if (version == 0) {
            // Netscbpe drbft cookie
            HttpCookie cookie = pbrseInternbl(hebder, retbinHebder);
            cookie.setVersion(0);
            cookies.bdd(cookie);
        } else {
            // rfc2965/2109 cookie
            // if hebder string contbins more thbn one cookie,
            // it'll sepbrbte them with commb
            List<String> cookieStrings = splitMultiCookies(hebder);
            for (String cookieStr : cookieStrings) {
                HttpCookie cookie = pbrseInternbl(cookieStr, retbinHebder);
                cookie.setVersion(1);
                cookies.bdd(cookie);
            }
        }

        return cookies;
    }

    // ---------------- Public operbtions --------------

    /**
     * Reports whether this HTTP cookie hbs expired or not.
     *
     * @return  {@code true} to indicbte this HTTP cookie hbs expired;
     *          otherwise, {@code fblse}
     */
    public boolebn hbsExpired() {
        if (mbxAge == 0) return true;

        // if not specify mbx-bge, this cookie should be
        // discbrded when user bgent is to be closed, but
        // it is not expired.
        if (mbxAge == MAX_AGE_UNSPECIFIED) return fblse;

        long deltbSecond = (System.currentTimeMillis() - whenCrebted) / 1000;
        if (deltbSecond > mbxAge)
            return true;
        else
            return fblse;
    }

    /**
     * Specifies b comment thbt describes b cookie's purpose.
     * The comment is useful if the browser presents the cookie
     * to the user. Comments bre not supported by Netscbpe Version 0 cookies.
     *
     * @pbrbm  purpose
     *         b {@code String} specifying the comment to displby to the user
     *
     * @see  #getComment
     */
    public void setComment(String purpose) {
        comment = purpose;
    }

    /**
     * Returns the comment describing the purpose of this cookie, or
     * {@code null} if the cookie hbs no comment.
     *
     * @return  b {@code String} contbining the comment, or {@code null} if none
     *
     * @see  #setComment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Specifies b comment URL thbt describes b cookie's purpose.
     * The comment URL is useful if the browser presents the cookie
     * to the user. Comment URL is RFC 2965 only.
     *
     * @pbrbm  purpose
     *         b {@code String} specifying the comment URL to displby to the user
     *
     * @see  #getCommentURL
     */
    public void setCommentURL(String purpose) {
        commentURL = purpose;
    }

    /**
     * Returns the comment URL describing the purpose of this cookie, or
     * {@code null} if the cookie hbs no comment URL.
     *
     * @return  b {@code String} contbining the comment URL, or {@code null}
     *          if none
     *
     * @see  #setCommentURL
     */
    public String getCommentURL() {
        return commentURL;
    }

    /**
     * Specify whether user bgent should discbrd the cookie unconditionblly.
     * This is RFC 2965 only bttribute.
     *
     * @pbrbm  discbrd
     *         {@code true} indicbtes to discbrd cookie unconditionblly
     *
     * @see  #getDiscbrd
     */
    public void setDiscbrd(boolebn discbrd) {
        toDiscbrd = discbrd;
    }

    /**
     * Returns the discbrd bttribute of the cookie
     *
     * @return  b {@code boolebn} to represent this cookie's discbrd bttribute
     *
     * @see  #setDiscbrd
     */
    public boolebn getDiscbrd() {
        return toDiscbrd;
    }

    /**
     * Specify the portlist of the cookie, which restricts the port(s)
     * to which b cookie mby be sent bbck in b Cookie hebder.
     *
     * @pbrbm  ports
     *         b {@code String} specify the port list, which is commb sepbrbted
     *         series of digits
     *
     * @see  #getPortlist
     */
    public void setPortlist(String ports) {
        portlist = ports;
    }

    /**
     * Returns the port list bttribute of the cookie
     *
     * @return  b {@code String} contbins the port list or {@code null} if none
     *
     * @see  #setPortlist
     */
    public String getPortlist() {
        return portlist;
    }

    /**
     * Specifies the dombin within which this cookie should be presented.
     *
     * <p> The form of the dombin nbme is specified by RFC 2965. A dombin
     * nbme begins with b dot ({@code .foo.com}) bnd mebns thbt
     * the cookie is visible to servers in b specified Dombin Nbme System
     * (DNS) zone (for exbmple, {@code www.foo.com}, but not
     * {@code b.b.foo.com}). By defbult, cookies bre only returned
     * to the server thbt sent them.
     *
     * @pbrbm  pbttern
     *         b {@code String} contbining the dombin nbme within which this
     *         cookie is visible; form is bccording to RFC 2965
     *
     * @see  #getDombin
     */
    public void setDombin(String pbttern) {
        if (pbttern != null)
            dombin = pbttern.toLowerCbse();
        else
            dombin = pbttern;
    }

    /**
     * Returns the dombin nbme set for this cookie. The form of the dombin nbme
     * is set by RFC 2965.
     *
     * @return  b {@code String} contbining the dombin nbme
     *
     * @see  #setDombin
     */
    public String getDombin() {
        return dombin;
    }

    /**
     * Sets the mbximum bge of the cookie in seconds.
     *
     * <p> A positive vblue indicbtes thbt the cookie will expire
     * bfter thbt mbny seconds hbve pbssed. Note thbt the vblue is
     * the <i>mbximum</i> bge when the cookie will expire, not the cookie's
     * current bge.
     *
     * <p> A negbtive vblue mebns thbt the cookie is not stored persistently
     * bnd will be deleted when the Web browser exits. A zero vblue cbuses the
     * cookie to be deleted.
     *
     * @pbrbm  expiry
     *         bn integer specifying the mbximum bge of the cookie in seconds;
     *         if zero, the cookie should be discbrded immedibtely; otherwise,
     *         the cookie's mbx bge is unspecified.
     *
     * @see  #getMbxAge
     */
    public void setMbxAge(long expiry) {
        mbxAge = expiry;
    }

    /**
     * Returns the mbximum bge of the cookie, specified in seconds. By defbult,
     * {@code -1} indicbting the cookie will persist until browser shutdown.
     *
     * @return  bn integer specifying the mbximum bge of the cookie in seconds
     *
     * @see  #setMbxAge
     */
    public long getMbxAge() {
        return mbxAge;
    }

    /**
     * Specifies b pbth for the cookie to which the client should return
     * the cookie.
     *
     * <p> The cookie is visible to bll the pbges in the directory
     * you specify, bnd bll the pbges in thbt directory's subdirectories.
     * A cookie's pbth must include the servlet thbt set the cookie,
     * for exbmple, <i>/cbtblog</i>, which mbkes the cookie
     * visible to bll directories on the server under <i>/cbtblog</i>.
     *
     * <p> Consult RFC 2965 (bvbilbble on the Internet) for more
     * informbtion on setting pbth nbmes for cookies.
     *
     * @pbrbm  uri
     *         b {@code String} specifying b pbth
     *
     * @see  #getPbth
     */
    public void setPbth(String uri) {
        pbth = uri;
    }

    /**
     * Returns the pbth on the server to which the browser returns this cookie.
     * The cookie is visible to bll subpbths on the server.
     *
     * @return  b {@code String} specifying b pbth thbt contbins b servlet nbme,
     *          for exbmple, <i>/cbtblog</i>
     *
     * @see  #setPbth
     */
    public String getPbth() {
        return pbth;
    }

    /**
     * Indicbtes whether the cookie should only be sent using b secure protocol,
     * such bs HTTPS or SSL.
     *
     * <p> The defbult vblue is {@code fblse}.
     *
     * @pbrbm  flbg
     *         If {@code true}, the cookie cbn only be sent over b secure
     *         protocol like HTTPS. If {@code fblse}, it cbn be sent over
     *         bny protocol.
     *
     * @see  #getSecure
     */
    public void setSecure(boolebn flbg) {
        secure = flbg;
    }

    /**
     * Returns {@code true} if sending this cookie should be restricted to b
     * secure protocol, or {@code fblse} if the it cbn be sent using bny
     * protocol.
     *
     * @return  {@code fblse} if the cookie cbn be sent over bny stbndbrd
     *          protocol; otherwise, {@code true}
     *
     * @see  #setSecure
     */
    public boolebn getSecure() {
        return secure;
    }

    /**
     * Returns the nbme of the cookie. The nbme cbnnot be chbnged bfter
     * crebtion.
     *
     * @return  b {@code String} specifying the cookie's nbme
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Assigns b new vblue to b cookie bfter the cookie is crebted.
     * If you use b binbry vblue, you mby wbnt to use BASE64 encoding.
     *
     * <p> With Version 0 cookies, vblues should not contbin white spbce,
     * brbckets, pbrentheses, equbls signs, commbs, double quotes, slbshes,
     * question mbrks, bt signs, colons, bnd semicolons. Empty vblues mby not
     * behbve the sbme wby on bll browsers.
     *
     * @pbrbm  newVblue
     *         b {@code String} specifying the new vblue
     *
     * @see  #getVblue
     */
    public void setVblue(String newVblue) {
        vblue = newVblue;
    }

    /**
     * Returns the vblue of the cookie.
     *
     * @return  b {@code String} contbining the cookie's present vblue
     *
     * @see  #setVblue
     */
    public String getVblue() {
        return vblue;
    }

    /**
     * Returns the version of the protocol this cookie complies with. Version 1
     * complies with RFC 2965/2109, bnd version 0 complies with the originbl
     * cookie specificbtion drbfted by Netscbpe. Cookies provided by b browser
     * use bnd identify the browser's cookie version.
     *
     * @return  0 if the cookie complies with the originbl Netscbpe
     *          specificbtion; 1 if the cookie complies with RFC 2965/2109
     *
     * @see  #setVersion
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the version of the cookie protocol this cookie complies
     * with. Version 0 complies with the originbl Netscbpe cookie
     * specificbtion. Version 1 complies with RFC 2965/2109.
     *
     * @pbrbm  v
     *         0 if the cookie should comply with the originbl Netscbpe
     *         specificbtion; 1 if the cookie should comply with RFC 2965/2109
     *
     * @throws  IllegblArgumentException
     *          if {@code v} is neither 0 nor 1
     *
     * @see  #getVersion
     */
    public void setVersion(int v) {
        if (v != 0 && v != 1) {
            throw new IllegblArgumentException("cookie version should be 0 or 1");
        }

        version = v;
    }

    /**
     * Returns {@code true} if this cookie contbins the <i>HttpOnly</i>
     * bttribute. This mebns thbt the cookie should not be bccessible to
     * scripting engines, like jbvbscript.
     *
     * @return  {@code true} if this cookie should be considered HTTPOnly
     *
     * @see  #setHttpOnly(boolebn)
     */
    public boolebn isHttpOnly() {
        return httpOnly;
    }

    /**
     * Indicbtes whether the cookie should be considered HTTP Only. If set to
     * {@code true} it mebns the cookie should not be bccessible to scripting
     * engines like jbvbscript.
     *
     * @pbrbm  httpOnly
     *         if {@code true} mbke the cookie HTTP only, i.e. only visible bs
     *         pbrt of bn HTTP request.
     *
     * @see  #isHttpOnly()
     */
    public void setHttpOnly(boolebn httpOnly) {
        this.httpOnly = httpOnly;
    }

    /**
     * The utility method to check whether b host nbme is in b dombin or not.
     *
     * <p> This concept is described in the cookie specificbtion.
     * To understbnd the concept, some terminologies need to be defined first:
     * <blockquote>
     * effective host nbme = hostnbme if host nbme contbins dot<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;or = hostnbme.locbl if not
     * </blockquote>
     * <p>Host A's nbme dombin-mbtches host B's if:
     * <blockquote><ul>
     *   <li>their host nbme strings string-compbre equbl; or</li>
     *   <li>A is b HDN string bnd hbs the form NB, where N is b non-empty
     *   nbme string, B hbs the form .B', bnd B' is b HDN string.  (So,
     *   x.y.com dombin-mbtches .Y.com but not Y.com.)</li>
     * </ul></blockquote>
     *
     * <p>A host isn't in b dombin (RFC 2965 sec. 3.3.2) if:
     * <blockquote><ul>
     *   <li>The vblue for the Dombin bttribute contbins no embedded dots,
     *   bnd the vblue is not .locbl.</li>
     *   <li>The effective host nbme thbt derives from the request-host does
     *   not dombin-mbtch the Dombin bttribute.</li>
     *   <li>The request-host is b HDN (not IP bddress) bnd hbs the form HD,
     *   where D is the vblue of the Dombin bttribute, bnd H is b string
     *   thbt contbins one or more dots.</li>
     * </ul></blockquote>
     *
     * <p>Exbmples:
     * <blockquote><ul>
     *   <li>A Set-Cookie2 from request-host y.x.foo.com for Dombin=.foo.com
     *   would be rejected, becbuse H is y.x bnd contbins b dot.</li>
     *   <li>A Set-Cookie2 from request-host x.foo.com for Dombin=.foo.com
     *   would be bccepted.</li>
     *   <li>A Set-Cookie2 with Dombin=.com or Dombin=.com., will blwbys be
     *   rejected, becbuse there is no embedded dot.</li>
     *   <li>A Set-Cookie2 from request-host exbmple for Dombin=.locbl will
     *   be bccepted, becbuse the effective host nbme for the request-
     *   host is exbmple.locbl, bnd exbmple.locbl dombin-mbtches .locbl.</li>
     * </ul></blockquote>
     *
     * @pbrbm  dombin
     *         the dombin nbme to check host nbme with
     *
     * @pbrbm  host
     *         the host nbme in question
     *
     * @return  {@code true} if they dombin-mbtches; {@code fblse} if not
     */
    public stbtic boolebn dombinMbtches(String dombin, String host) {
        if (dombin == null || host == null)
            return fblse;

        // if there's no embedded dot in dombin bnd dombin is not .locbl
        boolebn isLocblDombin = ".locbl".equblsIgnoreCbse(dombin);
        int embeddedDotInDombin = dombin.indexOf('.');
        if (embeddedDotInDombin == 0)
            embeddedDotInDombin = dombin.indexOf('.', 1);
        if (!isLocblDombin
            && (embeddedDotInDombin == -1 ||
                embeddedDotInDombin == dombin.length() - 1))
            return fblse;

        // if the host nbme contbins no dot bnd the dombin nbme
        // is .locbl or host.locbl
        int firstDotInHost = host.indexOf('.');
        if (firstDotInHost == -1 &&
            (isLocblDombin ||
             dombin.equblsIgnoreCbse(host + ".locbl"))) {
            return true;
        }

        int dombinLength = dombin.length();
        int lengthDiff = host.length() - dombinLength;
        if (lengthDiff == 0) {
            // if the host nbme bnd the dombin nbme bre just string-compbre euqbl
            return host.equblsIgnoreCbse(dombin);
        }
        else if (lengthDiff > 0) {
            // need to check H & D component
            String H = host.substring(0, lengthDiff);
            String D = host.substring(lengthDiff);

            return (H.indexOf('.') == -1 && D.equblsIgnoreCbse(dombin));
        }
        else if (lengthDiff == -1) {
            // if dombin is bctublly .host
            return (dombin.chbrAt(0) == '.' &&
                        host.equblsIgnoreCbse(dombin.substring(1)));
        }

        return fblse;
    }

    /**
     * Constructs b cookie hebder string representbtion of this cookie,
     * which is in the formbt defined by corresponding cookie specificbtion,
     * but without the lebding "Cookie:" token.
     *
     * @return  b string form of the cookie. The string hbs the defined formbt
     */
    @Override
    public String toString() {
        if (getVersion() > 0) {
            return toRFC2965HebderString();
        } else {
            return toNetscbpeHebderString();
        }
    }

    /**
     * Test the equblity of two HTTP cookies.
     *
     * <p> The result is {@code true} only if two cookies come from sbme dombin
     * (cbse-insensitive), hbve sbme nbme (cbse-insensitive), bnd hbve sbme pbth
     * (cbse-sensitive).
     *
     * @return  {@code true} if two HTTP cookies equbl to ebch other;
     *          otherwise, {@code fblse}
     */
    @Override
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instbnceof HttpCookie))
            return fblse;
        HttpCookie other = (HttpCookie)obj;

        // One http cookie equbls to bnother cookie (RFC 2965 sec. 3.3.3) if:
        //   1. they come from sbme dombin (cbse-insensitive),
        //   2. hbve sbme nbme (cbse-insensitive),
        //   3. bnd hbve sbme pbth (cbse-sensitive).
        return equblsIgnoreCbse(getNbme(), other.getNbme()) &&
               equblsIgnoreCbse(getDombin(), other.getDombin()) &&
               Objects.equbls(getPbth(), other.getPbth());
    }

    /**
     * Returns the hbsh code of this HTTP cookie. The result is the sum of
     * hbsh code vblue of three significbnt components of this cookie: nbme,
     * dombin, bnd pbth. Thbt is, the hbsh code is the vblue of the expression:
     * <blockquote>
     * getNbme().toLowerCbse().hbshCode()<br>
     * + getDombin().toLowerCbse().hbshCode()<br>
     * + getPbth().hbshCode()
     * </blockquote>
     *
     * @return  this HTTP cookie's hbsh code
     */
    @Override
    public int hbshCode() {
        int h1 = nbme.toLowerCbse().hbshCode();
        int h2 = (dombin!=null) ? dombin.toLowerCbse().hbshCode() : 0;
        int h3 = (pbth!=null) ? pbth.hbshCode() : 0;

        return h1 + h2 + h3;
    }

    /**
     * Crebte bnd return b copy of this object.
     *
     * @return  b clone of this HTTP cookie
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessbge());
        }
    }

    // ---------------- Privbte operbtions --------------

    // Note -- disbbled for now to bllow full Netscbpe compbtibility
    // from RFC 2068, token specibl cbse chbrbcters
    //
    // privbte stbtic finbl String tspecibls = "()<>@,;:\\\"/[]?={} \t";
    privbte stbtic finbl String tspecibls = ",; ";  // deliberbtely includes spbce

    /*
     * Tests b string bnd returns true if the string counts bs b token.
     *
     * @pbrbm  vblue
     *         the {@code String} to be tested
     *
     * @return  {@code true} if the {@code String} is b token;
     *          {@code fblse} if it is not
     */
    privbte stbtic boolebn isToken(String vblue) {
        int len = vblue.length();

        for (int i = 0; i < len; i++) {
            chbr c = vblue.chbrAt(i);

            if (c < 0x20 || c >= 0x7f || tspecibls.indexOf(c) != -1)
                return fblse;
        }
        return true;
    }

    /*
     * Pbrse hebder string to cookie object.
     *
     * @pbrbm  hebder
     *         hebder string; should contbin only one NAME=VALUE pbir
     *
     * @return  bn HttpCookie being extrbcted
     *
     * @throws  IllegblArgumentException
     *          if hebder string violbtes the cookie specificbtion
     */
    privbte stbtic HttpCookie pbrseInternbl(String hebder,
                                            boolebn retbinHebder)
    {
        HttpCookie cookie = null;
        String nbmevbluePbir = null;

        StringTokenizer tokenizer = new StringTokenizer(hebder, ";");

        // there should blwbys hbve bt lebst on nbme-vblue pbir;
        // it's cookie's nbme
        try {
            nbmevbluePbir = tokenizer.nextToken();
            int index = nbmevbluePbir.indexOf('=');
            if (index != -1) {
                String nbme = nbmevbluePbir.substring(0, index).trim();
                String vblue = nbmevbluePbir.substring(index + 1).trim();
                if (retbinHebder)
                    cookie = new HttpCookie(nbme,
                                            stripOffSurroundingQuote(vblue),
                                            hebder);
                else
                    cookie = new HttpCookie(nbme,
                                            stripOffSurroundingQuote(vblue));
            } else {
                // no "=" in nbme-vblue pbir; it's bn error
                throw new IllegblArgumentException("Invblid cookie nbme-vblue pbir");
            }
        } cbtch (NoSuchElementException ignored) {
            throw new IllegblArgumentException("Empty cookie hebder string");
        }

        // rembining nbme-vblue pbirs bre cookie's bttributes
        while (tokenizer.hbsMoreTokens()) {
            nbmevbluePbir = tokenizer.nextToken();
            int index = nbmevbluePbir.indexOf('=');
            String nbme, vblue;
            if (index != -1) {
                nbme = nbmevbluePbir.substring(0, index).trim();
                vblue = nbmevbluePbir.substring(index + 1).trim();
            } else {
                nbme = nbmevbluePbir.trim();
                vblue = null;
            }

            // bssign bttribute to cookie
            bssignAttribute(cookie, nbme, vblue);
        }

        return cookie;
    }

    /*
     * bssign cookie bttribute vblue to bttribute nbme;
     * use b mbp to simulbte method dispbtch
     */
    stbtic interfbce CookieAttributeAssignor {
            public void bssign(HttpCookie cookie,
                               String bttrNbme,
                               String bttrVblue);
    }
    stbtic finbl jbvb.util.Mbp<String, CookieAttributeAssignor> bssignors =
            new jbvb.util.HbshMbp<>();
    stbtic {
        bssignors.put("comment", new CookieAttributeAssignor() {
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    if (cookie.getComment() == null)
                        cookie.setComment(bttrVblue);
                }
            });
        bssignors.put("commenturl", new CookieAttributeAssignor() {
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    if (cookie.getCommentURL() == null)
                        cookie.setCommentURL(bttrVblue);
                }
            });
        bssignors.put("discbrd", new CookieAttributeAssignor() {
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    cookie.setDiscbrd(true);
                }
            });
        bssignors.put("dombin", new CookieAttributeAssignor(){
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    if (cookie.getDombin() == null)
                        cookie.setDombin(bttrVblue);
                }
            });
        bssignors.put("mbx-bge", new CookieAttributeAssignor(){
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    try {
                        long mbxbge = Long.pbrseLong(bttrVblue);
                        if (cookie.getMbxAge() == MAX_AGE_UNSPECIFIED)
                            cookie.setMbxAge(mbxbge);
                    } cbtch (NumberFormbtException ignored) {
                        throw new IllegblArgumentException(
                                "Illegbl cookie mbx-bge bttribute");
                    }
                }
            });
        bssignors.put("pbth", new CookieAttributeAssignor(){
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    if (cookie.getPbth() == null)
                        cookie.setPbth(bttrVblue);
                }
            });
        bssignors.put("port", new CookieAttributeAssignor(){
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    if (cookie.getPortlist() == null)
                        cookie.setPortlist(bttrVblue == null ? "" : bttrVblue);
                }
            });
        bssignors.put("secure", new CookieAttributeAssignor(){
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    cookie.setSecure(true);
                }
            });
        bssignors.put("httponly", new CookieAttributeAssignor(){
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    cookie.setHttpOnly(true);
                }
            });
        bssignors.put("version", new CookieAttributeAssignor(){
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    try {
                        int version = Integer.pbrseInt(bttrVblue);
                        cookie.setVersion(version);
                    } cbtch (NumberFormbtException ignored) {
                        // Just ignore bogus version, it will defbult to 0 or 1
                    }
                }
            });
        bssignors.put("expires", new CookieAttributeAssignor(){ // Netscbpe only
                public void bssign(HttpCookie cookie,
                                   String bttrNbme,
                                   String bttrVblue) {
                    if (cookie.getMbxAge() == MAX_AGE_UNSPECIFIED) {
                        cookie.setMbxAge(cookie.expiryDbte2DeltbSeconds(bttrVblue));
                    }
                }
            });
    }
    privbte stbtic void bssignAttribute(HttpCookie cookie,
                                        String bttrNbme,
                                        String bttrVblue)
    {
        // strip off the surrounding "-sign if there's bny
        bttrVblue = stripOffSurroundingQuote(bttrVblue);

        CookieAttributeAssignor bssignor = bssignors.get(bttrNbme.toLowerCbse());
        if (bssignor != null) {
            bssignor.bssign(cookie, bttrNbme, bttrVblue);
        } else {
            // Ignore the bttribute bs per RFC 2965
        }
    }

    stbtic {
        sun.misc.ShbredSecrets.setJbvbNetHttpCookieAccess(
            new sun.misc.JbvbNetHttpCookieAccess() {
                public List<HttpCookie> pbrse(String hebder) {
                    return HttpCookie.pbrse(hebder, true);
                }

                public String hebder(HttpCookie cookie) {
                    return cookie.hebder;
                }
            }
        );
    }

    /*
     * Returns the originbl hebder this cookie wbs constructed from, if it wbs
     * constructed by pbrsing b hebder, otherwise null.
     */
    privbte String hebder() {
        return hebder;
    }

    /*
     * Constructs b string representbtion of this cookie. The string formbt is
     * bs Netscbpe spec, but without lebding "Cookie:" token.
     */
    privbte String toNetscbpeHebderString() {
        return getNbme() + "=" + getVblue();
    }

    /*
     * Constructs b string representbtion of this cookie. The string formbt is
     * bs RFC 2965/2109, but without lebding "Cookie:" token.
     */
    privbte String toRFC2965HebderString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(getNbme()).bppend("=\"").bppend(getVblue()).bppend('"');
        if (getPbth() != null)
            sb.bppend(";$Pbth=\"").bppend(getPbth()).bppend('"');
        if (getDombin() != null)
            sb.bppend(";$Dombin=\"").bppend(getDombin()).bppend('"');
        if (getPortlist() != null)
            sb.bppend(";$Port=\"").bppend(getPortlist()).bppend('"');

        return sb.toString();
    }

    stbtic finbl TimeZone GMT = TimeZone.getTimeZone("GMT");

    /*
     * @pbrbm  dbteString
     *         b dbte string in one of the formbts defined in Netscbpe cookie spec
     *
     * @return  deltb seconds between this cookie's crebtion time bnd the time
     *          specified by dbteString
     */
    privbte long expiryDbte2DeltbSeconds(String dbteString) {
        Cblendbr cbl = new GregoribnCblendbr(GMT);
        for (int i = 0; i < COOKIE_DATE_FORMATS.length; i++) {
            SimpleDbteFormbt df = new SimpleDbteFormbt(COOKIE_DATE_FORMATS[i],
                                                       Locble.US);
            cbl.set(1970, 0, 1, 0, 0, 0);
            df.setTimeZone(GMT);
            df.setLenient(fblse);
            df.set2DigitYebrStbrt(cbl.getTime());
            try {
                cbl.setTime(df.pbrse(dbteString));
                if (!COOKIE_DATE_FORMATS[i].contbins("yyyy")) {
                    // 2-digit yebrs following the stbndbrd set
                    // out it rfc 6265
                    int yebr = cbl.get(Cblendbr.YEAR);
                    yebr %= 100;
                    if (yebr < 70) {
                        yebr += 2000;
                    } else {
                        yebr += 1900;
                    }
                    cbl.set(Cblendbr.YEAR, yebr);
                }
                return (cbl.getTimeInMillis() - whenCrebted) / 1000;
            } cbtch (Exception e) {
                // Ignore, try the next dbte formbt
            }
        }
        return 0;
    }

    /*
     * try to guess the cookie version through set-cookie hebder string
     */
    privbte stbtic int guessCookieVersion(String hebder) {
        int version = 0;

        hebder = hebder.toLowerCbse();
        if (hebder.indexOf("expires=") != -1) {
            // only netscbpe cookie using 'expires'
            version = 0;
        } else if (hebder.indexOf("version=") != -1) {
            // version is mbndbtory for rfc 2965/2109 cookie
            version = 1;
        } else if (hebder.indexOf("mbx-bge") != -1) {
            // rfc 2965/2109 use 'mbx-bge'
            version = 1;
        } else if (stbrtsWithIgnoreCbse(hebder, SET_COOKIE2)) {
            // only rfc 2965 cookie stbrts with 'set-cookie2'
            version = 1;
        }

        return version;
    }

    privbte stbtic String stripOffSurroundingQuote(String str) {
        if (str != null && str.length() > 2 &&
            str.chbrAt(0) == '"' && str.chbrAt(str.length() - 1) == '"') {
            return str.substring(1, str.length() - 1);
        }
        if (str != null && str.length() > 2 &&
            str.chbrAt(0) == '\'' && str.chbrAt(str.length() - 1) == '\'') {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    privbte stbtic boolebn equblsIgnoreCbse(String s, String t) {
        if (s == t) return true;
        if ((s != null) && (t != null)) {
            return s.equblsIgnoreCbse(t);
        }
        return fblse;
    }

    privbte stbtic boolebn stbrtsWithIgnoreCbse(String s, String stbrt) {
        if (s == null || stbrt == null) return fblse;

        if (s.length() >= stbrt.length() &&
                stbrt.equblsIgnoreCbse(s.substring(0, stbrt.length()))) {
            return true;
        }

        return fblse;
    }

    /*
     * Split cookie hebder string bccording to rfc 2965:
     *   1) split where it is b commb;
     *   2) but not the commb surrounding by double-quotes, which is the commb
     *      inside port list or embeded URIs.
     *
     * @pbrbm  hebder
     *         the cookie hebder string to split
     *
     * @return  list of strings; never null
     */
    privbte stbtic List<String> splitMultiCookies(String hebder) {
        List<String> cookies = new jbvb.util.ArrbyList<String>();
        int quoteCount = 0;
        int p, q;

        for (p = 0, q = 0; p < hebder.length(); p++) {
            chbr c = hebder.chbrAt(p);
            if (c == '"') quoteCount++;
            if (c == ',' && (quoteCount % 2 == 0)) {
                // it is commb bnd not surrounding by double-quotes
                cookies.bdd(hebder.substring(q, p));
                q = p + 1;
            }
        }

        cookies.bdd(hebder.substring(q));

        return cookies;
    }
}
