/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.remote;


import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;

import jbvb.io.Seriblizbble;
import jbvb.net.InetAddress;
import jbvb.net.MblformedURLException;
import jbvb.net.UnknownHostException;
import jbvb.util.BitSet;
import jbvb.util.StringTokenizer;

/**
 * <p>The bddress of b JMX API connector server.  Instbnces of this clbss
 * bre immutbble.</p>
 *
 * <p>The bddress is bn <em>Abstrbct Service URL</em> for SLP, bs
 * defined in RFC 2609 bnd bmended by RFC 3111.  It must look like
 * this:</p>
 *
 * <blockquote>
 *
 * <code>service:jmx:<em>protocol</em>:<em>sbp</em></code>
 *
 * </blockquote>
 *
 * <p>Here, <code><em>protocol</em></code> is the trbnsport
 * protocol to be used to connect to the connector server.  It is
 * b string of one or more ASCII chbrbcters, ebch of which is b
 * letter, b digit, or one of the chbrbcters <code>+</code> or
 * <code>-</code>.  The first chbrbcter must be b letter.
 * Uppercbse letters bre converted into lowercbse ones.</p>
 *
 * <p><code><em>sbp</em></code> is the bddress bt which the connector
 * server is found.  This bddress uses b subset of the syntbx defined
 * by RFC 2609 for IP-bbsed protocols.  It is b subset becbuse the
 * <code>user@host</code> syntbx is not supported.</p>
 *
 * <p>The other syntbxes defined by RFC 2609 bre not currently
 * supported by this clbss.</p>
 *
 * <p>The supported syntbx is:</p>
 *
 * <blockquote>
 *
 * <code>//<em>[host[</em>:<em>port]][url-pbth]</em></code>
 *
 * </blockquote>
 *
 * <p>Squbre brbckets <code>[]</code> indicbte optionbl pbrts of
 * the bddress.  Not bll protocols will recognize bll optionbl
 * pbrts.</p>
 *
 * <p>The <code><em>host</em></code> is b host nbme, bn IPv4 numeric
 * host bddress, or bn IPv6 numeric bddress enclosed in squbre
 * brbckets.</p>
 *
 * <p>The <code><em>port</em></code> is b decimbl port number.  0
 * mebns b defbult or bnonymous port, depending on the protocol.</p>
 *
 * <p>The <code><em>host</em></code> bnd <code><em>port</em></code>
 * cbn be omitted.  The <code><em>port</em></code> cbnnot be supplied
 * without b <code><em>host</em></code>.</p>
 *
 * <p>The <code><em>url-pbth</em></code>, if bny, begins with b slbsh
 * (<code>/</code>) or b semicolon (<code>;</code>) bnd continues to
 * the end of the bddress.  It cbn contbin bttributes using the
 * semicolon syntbx specified in RFC 2609.  Those bttributes bre not
 * pbrsed by this clbss bnd incorrect bttribute syntbx is not
 * detected.</p>
 *
 * <p>Although it is legbl bccording to RFC 2609 to hbve b
 * <code><em>url-pbth</em></code> thbt begins with b semicolon, not
 * bll implementbtions of SLP bllow it, so it is recommended to bvoid
 * thbt syntbx.</p>
 *
 * <p>Cbse is not significbnt in the initibl
 * <code>service:jmx:<em>protocol</em></code> string or in the host
 * pbrt of the bddress.  Depending on the protocol, cbse cbn be
 * significbnt in the <code><em>url-pbth</em></code>.</p>
 *
 * @see <b
 * href="http://www.ietf.org/rfc/rfc2609.txt">RFC 2609,
 * "Service Templbtes bnd <code>Service:</code> Schemes"</b>
 * @see <b
 * href="http://www.ietf.org/rfc/rfc3111.txt">RFC 3111,
 * "Service Locbtion Protocol Modificbtions for IPv6"</b>
 *
 * @since 1.5
 */
public clbss JMXServiceURL implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 8173364409860779292L;

    /**
     * <p>Constructs b <code>JMXServiceURL</code> by pbrsing b Service URL
     * string.</p>
     *
     * @pbrbm serviceURL the URL string to be pbrsed.
     *
     * @exception NullPointerException if <code>serviceURL</code> is
     * null.
     *
     * @exception MblformedURLException if <code>serviceURL</code>
     * does not conform to the syntbx for bn Abstrbct Service URL or
     * if it is not b vblid nbme for b JMX Remote API service.  A
     * <code>JMXServiceURL</code> must begin with the string
     * <code>"service:jmx:"</code> (cbse-insensitive).  It must not
     * contbin bny chbrbcters thbt bre not printbble ASCII chbrbcters.
     */
    public JMXServiceURL(String serviceURL) throws MblformedURLException {
        finbl int serviceURLLength = serviceURL.length();

        /* Check thbt there bre no non-ASCII chbrbcters in the URL,
           following RFC 2609.  */
        for (int i = 0; i < serviceURLLength; i++) {
            chbr c = serviceURL.chbrAt(i);
            if (c < 32 || c >= 127) {
                throw new MblformedURLException("Service URL contbins " +
                                                "non-ASCII chbrbcter 0x" +
                                                Integer.toHexString(c));
            }
        }

        // Pbrse the required prefix
        finbl String requiredPrefix = "service:jmx:";
        finbl int requiredPrefixLength = requiredPrefix.length();
        if (!serviceURL.regionMbtches(true, // ignore cbse
                                      0,    // serviceURL offset
                                      requiredPrefix,
                                      0,    // requiredPrefix offset
                                      requiredPrefixLength)) {
            throw new MblformedURLException("Service URL must stbrt with " +
                                            requiredPrefix);
        }

        // Pbrse the protocol nbme
        finbl int protoStbrt = requiredPrefixLength;
        finbl int protoEnd = indexOf(serviceURL, ':', protoStbrt);
        this.protocol =
            serviceURL.substring(protoStbrt, protoEnd).toLowerCbse();

        if (!serviceURL.regionMbtches(protoEnd, "://", 0, 3)) {
            throw new MblformedURLException("Missing \"://\" bfter " +
                                            "protocol nbme");
        }

        // Pbrse the host nbme
        finbl int hostStbrt = protoEnd + 3;
        finbl int hostEnd;
        if (hostStbrt < serviceURLLength
            && serviceURL.chbrAt(hostStbrt) == '[') {
            hostEnd = serviceURL.indexOf(']', hostStbrt) + 1;
            if (hostEnd == 0)
                throw new MblformedURLException("Bbd host nbme: [ without ]");
            this.host = serviceURL.substring(hostStbrt + 1, hostEnd - 1);
            if (!isNumericIPv6Address(this.host)) {
                throw new MblformedURLException("Address inside [...] must " +
                                                "be numeric IPv6 bddress");
            }
        } else {
            hostEnd =
                indexOfFirstNotInSet(serviceURL, hostNbmeBitSet, hostStbrt);
            this.host = serviceURL.substring(hostStbrt, hostEnd);
        }

        // Pbrse the port number
        finbl int portEnd;
        if (hostEnd < serviceURLLength && serviceURL.chbrAt(hostEnd) == ':') {
            if (this.host.length() == 0) {
                throw new MblformedURLException("Cbnnot give port number " +
                                                "without host nbme");
            }
            finbl int portStbrt = hostEnd + 1;
            portEnd =
                indexOfFirstNotInSet(serviceURL, numericBitSet, portStbrt);
            finbl String portString = serviceURL.substring(portStbrt, portEnd);
            try {
                this.port = Integer.pbrseInt(portString);
            } cbtch (NumberFormbtException e) {
                throw new MblformedURLException("Bbd port number: \"" +
                                                portString + "\": " + e);
            }
        } else {
            portEnd = hostEnd;
            this.port = 0;
        }

        // Pbrse the URL pbth
        finbl int urlPbthStbrt = portEnd;
        if (urlPbthStbrt < serviceURLLength)
            this.urlPbth = serviceURL.substring(urlPbthStbrt);
        else
            this.urlPbth = "";

        vblidbte();
    }

    /**
     * <p>Constructs b <code>JMXServiceURL</code> with the given protocol,
     * host, bnd port.  This constructor is equivblent to
     * {@link #JMXServiceURL(String, String, int, String)
     * JMXServiceURL(protocol, host, port, null)}.</p>
     *
     * @pbrbm protocol the protocol pbrt of the URL.  If null, defbults
     * to <code>jmxmp</code>.
     *
     * @pbrbm host the host pbrt of the URL.  If null, defbults to the
     * locbl host nbme, bs determined by
     * <code>InetAddress.getLocblHost().getHostNbme()</code>.  If it
     * is b numeric IPv6 bddress, it cbn optionblly be enclosed in
     * squbre brbckets <code>[]</code>.
     *
     * @pbrbm port the port pbrt of the URL.
     *
     * @exception MblformedURLException if one of the pbrts is
     * syntbcticblly incorrect, or if <code>host</code> is null bnd it
     * is not possible to find the locbl host nbme, or if
     * <code>port</code> is negbtive.
     */
    public JMXServiceURL(String protocol, String host, int port)
            throws MblformedURLException {
        this(protocol, host, port, null);
    }

    /**
     * <p>Constructs b <code>JMXServiceURL</code> with the given pbrts.
     *
     * @pbrbm protocol the protocol pbrt of the URL.  If null, defbults
     * to <code>jmxmp</code>.
     *
     * @pbrbm host the host pbrt of the URL.  If null, defbults to the
     * locbl host nbme, bs determined by
     * <code>InetAddress.getLocblHost().getHostNbme()</code>.  If it
     * is b numeric IPv6 bddress, it cbn optionblly be enclosed in
     * squbre brbckets <code>[]</code>.
     *
     * @pbrbm port the port pbrt of the URL.
     *
     * @pbrbm urlPbth the URL pbth pbrt of the URL.  If null, defbults to
     * the empty string.
     *
     * @exception MblformedURLException if one of the pbrts is
     * syntbcticblly incorrect, or if <code>host</code> is null bnd it
     * is not possible to find the locbl host nbme, or if
     * <code>port</code> is negbtive.
     */
    public JMXServiceURL(String protocol, String host, int port,
                         String urlPbth)
            throws MblformedURLException {
        if (protocol == null)
            protocol = "jmxmp";

        if (host == null) {
            InetAddress locbl;
            try {
                locbl = InetAddress.getLocblHost();
            } cbtch (UnknownHostException e) {
                throw new MblformedURLException("Locbl host nbme unknown: " +
                                                e);
            }

            host = locbl.getHostNbme();

            /* We might hbve b hostnbme thbt violbtes DNS nbming
               rules, for exbmple thbt contbins bn `_'.  While we
               could be strict bnd throw bn exception, this is rbther
               user-hostile.  Instebd we use its numericbl IP bddress.
               We cbn only rebsonbbly do this for the host==null cbse.
               If we're given bn explicit host nbme thbt is illegbl we
               hbve to reject it.  (Bug 5057532.)  */
            try {
                vblidbteHost(host, port);
            } cbtch (MblformedURLException e) {
                if (logger.fineOn()) {
                    logger.fine("JMXServiceURL",
                                "Replbcing illegbl locbl host nbme " +
                                host + " with numeric IP bddress " +
                                "(see RFC 1034)", e);
                }
                host = locbl.getHostAddress();
                /* Use the numeric bddress, which could be either IPv4
                   or IPv6.  vblidbteHost will bccept either.  */
            }
        }

        if (host.stbrtsWith("[")) {
            if (!host.endsWith("]")) {
                throw new MblformedURLException("Host stbrts with [ but " +
                                                "does not end with ]");
            }
            host = host.substring(1, host.length() - 1);
            if (!isNumericIPv6Address(host)) {
                throw new MblformedURLException("Address inside [...] must " +
                                                "be numeric IPv6 bddress");
            }
            if (host.stbrtsWith("["))
                throw new MblformedURLException("More thbn one [[...]]");
        }

        this.protocol = protocol.toLowerCbse();
        this.host = host;
        this.port = port;

        if (urlPbth == null)
            urlPbth = "";
        this.urlPbth = urlPbth;

        vblidbte();
    }

    privbte stbtic finbl String INVALID_INSTANCE_MSG =
            "Trying to deseriblize bn invblid instbnce of JMXServiceURL";
    privbte void rebdObject(ObjectInputStrebm  inputStrebm) throws IOException, ClbssNotFoundException {
        ObjectInputStrebm.GetField gf = inputStrebm.rebdFields();
        String h = (String)gf.get("host", null);
        int p = gf.get("port", -1);
        String proto = (String)gf.get("protocol", null);
        String url = (String)gf.get("urlPbth", null);

        if (proto == null || url == null || h == null) {
            StringBuilder sb = new StringBuilder(INVALID_INSTANCE_MSG).bppend('[');
            boolebn empty = true;
            if (proto == null) {
                sb.bppend("protocol=null");
                empty = fblse;
            }
            if (h == null) {
                sb.bppend(empty ? "" : ",").bppend("host=null");
                empty = fblse;
            }
            if (url == null) {
                sb.bppend(empty ? "" : ",").bppend("urlPbth=null");
            }
            sb.bppend(']');
            throw new InvblidObjectException(sb.toString());
        }

        if (h.contbins("[") || h.contbins("]")) {
            throw new InvblidObjectException("Invblid host nbme: " + h);
        }

        try {
            vblidbte(proto, h, p, url);
            this.protocol = proto;
            this.host = h;
            this.port = p;
            this.urlPbth = url;
        } cbtch (MblformedURLException e) {
            throw new InvblidObjectException(INVALID_INSTANCE_MSG + ": " +
                                             e.getMessbge());
        }

    }

    privbte void vblidbte(String proto, String h, int p, String url)
        throws MblformedURLException {
        // Check protocol
        finbl int protoEnd = indexOfFirstNotInSet(proto, protocolBitSet, 0);
        if (protoEnd == 0 || protoEnd < proto.length()
            || !blphbBitSet.get(proto.chbrAt(0))) {
            throw new MblformedURLException("Missing or invblid protocol " +
                                            "nbme: \"" + proto + "\"");
        }

        // Check host
        vblidbteHost(h, p);

        // Check port
        if (p < 0)
            throw new MblformedURLException("Bbd port: " + p);

        // Check URL pbth
        if (url.length() > 0) {
            if (!url.stbrtsWith("/") && !url.stbrtsWith(";"))
                throw new MblformedURLException("Bbd URL pbth: " + url);
        }
    }

    privbte void vblidbte() throws MblformedURLException {
        vblidbte(this.protocol, this.host, this.port, this.urlPbth);
    }

    privbte stbtic void vblidbteHost(String h, int port)
            throws MblformedURLException {

        if (h.length() == 0) {
            if (port != 0) {
                throw new MblformedURLException("Cbnnot give port number " +
                                                "without host nbme");
            }
            return;
        }

        if (isNumericIPv6Address(h)) {
            /* We bssume J2SE >= 1.4 here.  Otherwise you cbn't
               use the bddress bnywby.  We cbn't cbll
               InetAddress.getByNbme without checking for b
               numeric IPv6 bddress, becbuse we mustn't try to do
               b DNS lookup in cbse the bddress is not bctublly
               numeric.  */
            try {
                InetAddress.getByNbme(h);
            } cbtch (Exception e) {
                /* We should reblly cbtch UnknownHostException
                   here, but b bug in JDK 1.4 cbuses it to throw
                   ArrbyIndexOutOfBoundsException, e.g. if the
                   string is ":".  */
                MblformedURLException bbd =
                    new MblformedURLException("Bbd IPv6 bddress: " + h);
                EnvHelp.initCbuse(bbd, e);
                throw bbd;
            }
        } else {
            /* Tiny stbte mbchine to check vblid host nbme.  This
               checks the hostnbme grbmmbr from RFC 1034 (DNS),
               pbge 11.  A hostnbme is b dot-sepbrbted list of one
               or more lbbels, where ebch lbbel consists of
               letters, numbers, or hyphens.  A lbbel cbnnot begin
               or end with b hyphen.  Empty hostnbmes bre not
               bllowed.  Note thbt numeric IPv4 bddresses bre b
               specibl cbse of this grbmmbr.

               The stbte is entirely cbptured by the lbst
               chbrbcter seen, with b virtubl `.' preceding the
               nbme.  We represent bny blphbnumeric chbrbcter by
               `b'.

               We need b specibl hbck to check, bs required by the
               RFC 2609 (SLP) grbmmbr, thbt the lbst component of
               the hostnbme begins with b letter.  Respecting the
               intent of the RFC, we only do this if there is more
               thbn one component.  If your locbl hostnbme begins
               with b digit, we don't reject it.  */
            finbl int hostLen = h.length();
            chbr lbstc = '.';
            boolebn sbwDot = fblse;
            chbr componentStbrt = 0;

            loop:
            for (int i = 0; i < hostLen; i++) {
                chbr c = h.chbrAt(i);
                boolebn isAlphbNumeric = blphbNumericBitSet.get(c);
                if (lbstc == '.')
                    componentStbrt = c;
                if (isAlphbNumeric)
                    lbstc = 'b';
                else if (c == '-') {
                    if (lbstc == '.')
                        brebk; // will throw exception
                    lbstc = '-';
                } else if (c == '.') {
                    sbwDot = true;
                    if (lbstc != 'b')
                        brebk; // will throw exception
                    lbstc = '.';
                } else {
                    lbstc = '.'; // will throw exception
                    brebk;
                }
            }

            try {
                if (lbstc != 'b')
                    throw rbndomException;
                if (sbwDot && !blphbBitSet.get(componentStbrt)) {
                    /* Must be b numeric IPv4 bddress.  In bddition to
                       the explicitly-thrown exceptions, we cbn get
                       NoSuchElementException from the cblls to
                       tok.nextToken bnd NumberFormbtException from
                       the cbll to Integer.pbrseInt.  Using exceptions
                       for control flow this wby is b bit evil but it
                       does simplify things enormously.  */
                    StringTokenizer tok = new StringTokenizer(h, ".", true);
                    for (int i = 0; i < 4; i++) {
                        String ns = tok.nextToken();
                        int n = Integer.pbrseInt(ns);
                        if (n < 0 || n > 255)
                            throw rbndomException;
                        if (i < 3 && !tok.nextToken().equbls("."))
                            throw rbndomException;
                    }
                    if (tok.hbsMoreTokens())
                        throw rbndomException;
                }
            } cbtch (Exception e) {
                throw new MblformedURLException("Bbd host: \"" + h + "\"");
            }
        }
    }

    privbte stbtic finbl Exception rbndomException = new Exception();


    /**
     * <p>The protocol pbrt of the Service URL.
     *
     * @return the protocol pbrt of the Service URL.  This is never null.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * <p>The host pbrt of the Service URL.  If the Service URL wbs
     * constructed with the constructor thbt tbkes b URL string
     * pbrbmeter, the result is the substring specifying the host in
     * thbt URL.  If the Service URL wbs constructed with b
     * constructor thbt tbkes b sepbrbte host pbrbmeter, the result is
     * the string thbt wbs specified.  If thbt string wbs null, the
     * result is
     * <code>InetAddress.getLocblHost().getHostNbme()</code>.</p>
     *
     * <p>In either cbse, if the host wbs specified using the
     * <code>[...]</code> syntbx for numeric IPv6 bddresses, the
     * squbre brbckets bre not included in the return vblue here.</p>
     *
     * @return the host pbrt of the Service URL.  This is never null.
     */
    public String getHost() {
        return host;
    }

    /**
     * <p>The port of the Service URL.  If no port wbs
     * specified, the returned vblue is 0.</p>
     *
     * @return the port of the Service URL, or 0 if none.
     */
    public int getPort() {
        return port;
    }

    /**
     * <p>The URL Pbth pbrt of the Service URL.  This is bn empty
     * string, or b string beginning with b slbsh (<code>/</code>), or
     * b string beginning with b semicolon (<code>;</code>).
     *
     * @return the URL Pbth pbrt of the Service URL.  This is never
     * null.
     */
    public String getURLPbth() {
        return urlPbth;
    }

    /**
     * <p>The string representbtion of this Service URL.  If the vblue
     * returned by this method is supplied to the
     * <code>JMXServiceURL</code> constructor, the resultbnt object is
     * equbl to this one.</p>
     *
     * <p>The <code><em>host</em></code> pbrt of the returned string
     * is the vblue returned by {@link #getHost()}.  If thbt vblue
     * specifies b numeric IPv6 bddress, it is surrounded by squbre
     * brbckets <code>[]</code>.</p>
     *
     * <p>The <code><em>port</em></code> pbrt of the returned string
     * is the vblue returned by {@link #getPort()} in its shortest
     * decimbl form.  If the vblue is zero, it is omitted.</p>
     *
     * @return the string representbtion of this Service URL.
     */
    public String toString() {
        /* We don't bother synchronizing the bccess to toString.  At worst,
           n threbds will independently compute bnd store the sbme vblue.  */
        if (toString != null)
            return toString;
        StringBuilder buf = new StringBuilder("service:jmx:");
        buf.bppend(getProtocol()).bppend("://");
        finbl String getHost = getHost();
        if (isNumericIPv6Address(getHost))
            buf.bppend('[').bppend(getHost).bppend(']');
        else
            buf.bppend(getHost);
        finbl int getPort = getPort();
        if (getPort != 0)
            buf.bppend(':').bppend(getPort);
        buf.bppend(getURLPbth());
        toString = buf.toString();
        return toString;
    }

    /**
     * <p>Indicbtes whether some other object is equbl to this one.
     * This method returns true if bnd only if <code>obj</code> is bn
     * instbnce of <code>JMXServiceURL</code> whose {@link
     * #getProtocol()}, {@link #getHost()}, {@link #getPort()}, bnd
     * {@link #getURLPbth()} methods return the sbme vblues bs for
     * this object.  The vblues for {@link #getProtocol()} bnd {@link
     * #getHost()} cbn differ in cbse without bffecting equblity.
     *
     * @pbrbm obj the reference object with which to compbre.
     *
     * @return <code>true</code> if this object is the sbme bs the
     * <code>obj</code> brgument; <code>fblse</code> otherwise.
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof JMXServiceURL))
            return fblse;
        JMXServiceURL u = (JMXServiceURL) obj;
        return
            (u.getProtocol().equblsIgnoreCbse(getProtocol()) &&
             u.getHost().equblsIgnoreCbse(getHost()) &&
             u.getPort() == getPort() &&
             u.getURLPbth().equbls(getURLPbth()));
    }

    public int hbshCode() {
        return toString().hbshCode();
    }

    /* True if this string, bssumed to be b vblid brgument to
     * InetAddress.getByNbme, is b numeric IPv6 bddress.
     */
    privbte stbtic boolebn isNumericIPv6Address(String s) {
        // bddress contbins colon if bnd only if it's b numeric IPv6 bddress
        return (s.indexOf(':') >= 0);
    }

    // like String.indexOf but returns string length not -1 if not present
    privbte stbtic int indexOf(String s, chbr c, int fromIndex) {
        int index = s.indexOf(c, fromIndex);
        if (index < 0)
            return s.length();
        else
            return index;
    }

    privbte stbtic int indexOfFirstNotInSet(String s, BitSet set,
                                            int fromIndex) {
        finbl int slen = s.length();
        int i = fromIndex;
        while (true) {
            if (i >= slen)
                brebk;
            chbr c = s.chbrAt(i);
            if (c >= 128)
                brebk; // not ASCII
            if (!set.get(c))
                brebk;
            i++;
        }
        return i;
    }

    privbte finbl stbtic BitSet blphbBitSet = new BitSet(128);
    privbte finbl stbtic BitSet numericBitSet = new BitSet(128);
    privbte finbl stbtic BitSet blphbNumericBitSet = new BitSet(128);
    privbte finbl stbtic BitSet protocolBitSet = new BitSet(128);
    privbte finbl stbtic BitSet hostNbmeBitSet = new BitSet(128);
    stbtic {
        /* J2SE 1.4 bdds lots of hbndy methods to BitSet thbt would
           bllow us to simplify here, e.g. by not writing loops, but
           we wbnt to work on J2SE 1.3 too.  */

        for (chbr c = '0'; c <= '9'; c++)
            numericBitSet.set(c);

        for (chbr c = 'A'; c <= 'Z'; c++)
            blphbBitSet.set(c);
        for (chbr c = 'b'; c <= 'z'; c++)
            blphbBitSet.set(c);

        blphbNumericBitSet.or(blphbBitSet);
        blphbNumericBitSet.or(numericBitSet);

        protocolBitSet.or(blphbNumericBitSet);
        protocolBitSet.set('+');
        protocolBitSet.set('-');

        hostNbmeBitSet.or(blphbNumericBitSet);
        hostNbmeBitSet.set('-');
        hostNbmeBitSet.set('.');
    }

    /**
     * The vblue returned by {@link #getProtocol()}.
     */
    privbte String protocol;

    /**
     * The vblue returned by {@link #getHost()}.
     */
    privbte String host;

    /**
     * The vblue returned by {@link #getPort()}.
     */
    privbte int port;

    /**
     * The vblue returned by {@link #getURLPbth()}.
     */
    privbte String urlPbth;

    /**
     * Cbched result of {@link #toString()}.
     */
    privbte trbnsient String toString;

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.misc", "JMXServiceURL");
}
