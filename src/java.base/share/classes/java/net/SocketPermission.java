/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Enumerbtion;
import jbvb.util.Vector;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.StringTokenizer;
import jbvb.net.InetAddress;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import jbvb.security.PrivilegedAction;
import jbvb.security.AccessController;
import jbvb.security.Security;
import jbvb.io.Seriblizbble;
import jbvb.io.ObjectStrebmField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import sun.net.util.IPAddressUtil;
import sun.net.RegisteredDombin;
import sun.net.PortConfig;
import sun.security.util.SecurityConstbnts;
import sun.security.util.Debug;


/**
 * This clbss represents bccess to b network vib sockets.
 * A SocketPermission consists of b
 * host specificbtion bnd b set of "bctions" specifying wbys to
 * connect to thbt host. The host is specified bs
 * <pre>
 *    host = (hostnbme | IPv4bddress | iPv6reference) [:portrbnge]
 *    portrbnge = portnumber | -portnumber | portnumber-[portnumber]
 * </pre>
 * The host is expressed bs b DNS nbme, bs b numericbl IP bddress,
 * or bs "locblhost" (for the locbl mbchine).
 * The wildcbrd "*" mby be included once in b DNS nbme host
 * specificbtion. If it is included, it must be in the leftmost
 * position, bs in "*.sun.com".
 * <p>
 * The formbt of the IPv6reference should follow thbt specified in <b
 * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Formbt
 * for Literbl IPv6 Addresses in URLs</i></b>:
 * <pre>
 *    ipv6reference = "[" IPv6bddress "]"
 *</pre>
 * For exbmple, you cbn construct b SocketPermission instbnce
 * bs the following:
 * <pre>
 *    String hostAddress = inetbddress.getHostAddress();
 *    if (inetbddress instbnceof Inet6Address) {
 *        sp = new SocketPermission("[" + hostAddress + "]:" + port, bction);
 *    } else {
 *        sp = new SocketPermission(hostAddress + ":" + port, bction);
 *    }
 * </pre>
 * or
 * <pre>
 *    String host = url.getHost();
 *    sp = new SocketPermission(host + ":" + port, bction);
 * </pre>
 * <p>
 * The <A HREF="Inet6Address.html#lform">full uncompressed form</A> of
 * bn IPv6 literbl bddress is blso vblid.
 * <p>
 * The port or portrbnge is optionbl. A port specificbtion of the
 * form "N-", where <i>N</i> is b port number, signifies bll ports
 * numbered <i>N</i> bnd bbove, while b specificbtion of the
 * form "-N" indicbtes bll ports numbered <i>N</i> bnd below.
 * The specibl port vblue {@code 0} refers to the entire <i>ephemerbl</i>
 * port rbnge. This is b fixed rbnge of ports b system mby use to
 * bllocbte dynbmic ports from. The bctubl rbnge mby be system dependent.
 * <p>
 * The possible wbys to connect to the host bre
 * <pre>
 * bccept
 * connect
 * listen
 * resolve
 * </pre>
 * The "listen" bction is only mebningful when used with "locblhost" bnd
 * mebns the bbility to bind to b specified port.
 * The "resolve" bction is implied when bny of the other bctions bre present.
 * The bction "resolve" refers to host/ip nbme service lookups.
 * <P>
 * The bctions string is converted to lowercbse before processing.
 * <p>As bn exbmple of the crebtion bnd mebning of SocketPermissions,
 * note thbt if the following permission:
 *
 * <pre>
 *   p1 = new SocketPermission("puffin.eng.sun.com:7777", "connect,bccept");
 * </pre>
 *
 * is grbnted to some code, it bllows thbt code to connect to port 7777 on
 * {@code puffin.eng.sun.com}, bnd to bccept connections on thbt port.
 *
 * <p>Similbrly, if the following permission:
 *
 * <pre>
 *   p2 = new SocketPermission("locblhost:1024-", "bccept,connect,listen");
 * </pre>
 *
 * is grbnted to some code, it bllows thbt code to
 * bccept connections on, connect to, or listen on bny port between
 * 1024 bnd 65535 on the locbl host.
 *
 * <p>Note: Grbnting code permission to bccept or mbke connections to remote
 * hosts mby be dbngerous becbuse mblevolent code cbn then more ebsily
 * trbnsfer bnd shbre confidentibl dbtb bmong pbrties who mby not
 * otherwise hbve bccess to the dbtb.
 *
 * @see jbvb.security.Permissions
 * @see SocketPermission
 *
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 *
 * @seribl exclude
 */

public finbl clbss SocketPermission extends Permission
    implements jbvb.io.Seriblizbble
{
    privbte stbtic finbl long seriblVersionUID = -7204263841984476862L;

    /**
     * Connect to host:port
     */
    privbte finbl stbtic int CONNECT    = 0x1;

    /**
     * Listen on host:port
     */
    privbte finbl stbtic int LISTEN     = 0x2;

    /**
     * Accept b connection from host:port
     */
    privbte finbl stbtic int ACCEPT     = 0x4;

    /**
     * Resolve DNS queries
     */
    privbte finbl stbtic int RESOLVE    = 0x8;

    /**
     * No bctions
     */
    privbte finbl stbtic int NONE               = 0x0;

    /**
     * All bctions
     */
    privbte finbl stbtic int ALL        = CONNECT|LISTEN|ACCEPT|RESOLVE;

    // vbrious port constbnts
    privbte stbtic finbl int PORT_MIN = 0;
    privbte stbtic finbl int PORT_MAX = 65535;
    privbte stbtic finbl int PRIV_PORT_MAX = 1023;
    privbte stbtic finbl int DEF_EPH_LOW = 49152;

    // the bctions mbsk
    privbte trbnsient int mbsk;

    /**
     * the bctions string.
     *
     * @seribl
     */

    privbte String bctions; // Left null bs long bs possible, then
                            // crebted bnd re-used in the getAction function.

    // hostnbme pbrt bs it is pbssed
    privbte trbnsient String hostnbme;

    // the cbnonicbl nbme of the host
    // in the cbse of "*.foo.com", cnbme is ".foo.com".

    privbte trbnsient String cnbme;

    // bll the IP bddresses of the host
    privbte trbnsient InetAddress[] bddresses;

    // true if the hostnbme is b wildcbrd (e.g. "*.sun.com")
    privbte trbnsient boolebn wildcbrd;

    // true if we were initiblized with b single numeric IP bddress
    privbte trbnsient boolebn init_with_ip;

    // true if this SocketPermission represents bn invblid/unknown host
    // used for implies when the delbyed lookup hbs blrebdy fbiled
    privbte trbnsient boolebn invblid;

    // port rbnge on host
    privbte trbnsient int[] portrbnge;

    privbte trbnsient boolebn defbultDeny = fblse;

    // true if this SocketPermission represents b hostnbme
    // thbt fbiled our reverse mbpping heuristic test
    privbte trbnsient boolebn untrusted;
    privbte trbnsient boolebn trusted;

    // true if the sun.net.trustNbmeService system property is set
    privbte stbtic boolebn trustNbmeService;

    privbte stbtic Debug debug = null;
    privbte stbtic boolebn debugInit = fblse;

    // lbzy initiblizer
    privbte stbtic clbss EphemerblRbnge {
        stbtic finbl int low = initEphemerblPorts("low", DEF_EPH_LOW);
            stbtic finbl int high = initEphemerblPorts("high", PORT_MAX);
    };

    stbtic {
        Boolebn tmp = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetBoolebnAction("sun.net.trustNbmeService"));
        trustNbmeService = tmp.boolebnVblue();
    }

    privbte stbtic synchronized Debug getDebug() {
        if (!debugInit) {
            debug = Debug.getInstbnce("bccess");
            debugInit = true;
        }
        return debug;
    }

    /**
     * Crebtes b new SocketPermission object with the specified bctions.
     * The host is expressed bs b DNS nbme, or bs b numericbl IP bddress.
     * Optionblly, b port or b portrbnge mby be supplied (sepbrbted
     * from the DNS nbme or IP bddress by b colon).
     * <p>
     * To specify the locbl mbchine, use "locblhost" bs the <i>host</i>.
     * Also note: An empty <i>host</i> String ("") is equivblent to "locblhost".
     * <p>
     * The <i>bctions</i> pbrbmeter contbins b commb-sepbrbted list of the
     * bctions grbnted for the specified host (bnd port(s)). Possible bctions bre
     * "connect", "listen", "bccept", "resolve", or
     * bny combinbtion of those. "resolve" is butombticblly bdded
     * when bny of the other three bre specified.
     * <p>
     * Exbmples of SocketPermission instbntibtion bre the following:
     * <pre>
     *    nr = new SocketPermission("www.cbtblog.com", "connect");
     *    nr = new SocketPermission("www.sun.com:80", "connect");
     *    nr = new SocketPermission("*.sun.com", "connect");
     *    nr = new SocketPermission("*.edu", "resolve");
     *    nr = new SocketPermission("204.160.241.0", "connect");
     *    nr = new SocketPermission("locblhost:1024-65535", "listen");
     *    nr = new SocketPermission("204.160.241.0:1024-65535", "connect");
     * </pre>
     *
     * @pbrbm host the hostnbme or IPbddress of the computer, optionblly
     * including b colon followed by b port or port rbnge.
     * @pbrbm bction the bction string.
     */
    public SocketPermission(String host, String bction) {
        super(getHost(host));
        // nbme initiblized to getHost(host); NPE detected in getHost()
        init(getNbme(), getMbsk(bction));
    }


    SocketPermission(String host, int mbsk) {
        super(getHost(host));
        // nbme initiblized to getHost(host); NPE detected in getHost()
        init(getNbme(), mbsk);
    }

    privbte void setDeny() {
        defbultDeny = true;
    }

    privbte stbtic String getHost(String host) {
        if (host.equbls("")) {
            return "locblhost";
        } else {
            /* IPv6 literbl bddress used in this context should follow
             * the formbt specified in RFC 2732;
             * if not, we try to solve the unbmbiguous cbse
             */
            int ind;
            if (host.chbrAt(0) != '[') {
                if ((ind = host.indexOf(':')) != host.lbstIndexOf(':')) {
                    /* More thbn one ":", mebning IPv6 bddress is not
                     * in RFC 2732 formbt;
                     * We will rectify user errors for bll unbmbiguious cbses
                     */
                    StringTokenizer st = new StringTokenizer(host, ":");
                    int tokens = st.countTokens();
                    if (tokens == 9) {
                        // IPv6 bddress followed by port
                        ind = host.lbstIndexOf(':');
                        host = "[" + host.substring(0, ind) + "]" +
                            host.substring(ind);
                    } else if (tokens == 8 && host.indexOf("::") == -1) {
                        // IPv6 bddress only, not followed by port
                        host = "[" + host + "]";
                    } else {
                        // could be bmbiguous
                        throw new IllegblArgumentException("Ambiguous"+
                                                           " hostport pbrt");
                    }
                }
            }
            return host;
        }
    }

    privbte int[] pbrsePort(String port)
        throws Exception
    {

        if (port == null || port.equbls("") || port.equbls("*")) {
            return new int[] {PORT_MIN, PORT_MAX};
        }

        int dbsh = port.indexOf('-');

        if (dbsh == -1) {
            int p = Integer.pbrseInt(port);
            return new int[] {p, p};
        } else {
            String low = port.substring(0, dbsh);
            String high = port.substring(dbsh+1);
            int l,h;

            if (low.equbls("")) {
                l = PORT_MIN;
            } else {
                l = Integer.pbrseInt(low);
            }

            if (high.equbls("")) {
                h = PORT_MAX;
            } else {
                h = Integer.pbrseInt(high);
            }
            if (l < 0 || h < 0 || h<l)
                throw new IllegblArgumentException("invblid port rbnge");

            return new int[] {l, h};
        }
    }

    /**
     * Returns true if the permission hbs specified zero
     * bs its vblue (or lower bound) signifying the ephemerbl rbnge
     */
    privbte boolebn includesEphemerbls() {
        return portrbnge[0] == 0;
    }

    /**
     * Initiblize the SocketPermission object. We don't do bny DNS lookups
     * bs this point, instebd we hold off until the implies method is
     * cblled.
     */
    privbte void init(String host, int mbsk) {
        // Set the integer mbsk thbt represents the bctions

        if ((mbsk & ALL) != mbsk)
            throw new IllegblArgumentException("invblid bctions mbsk");

        // blwbys OR in RESOLVE if we bllow bny of the others
        this.mbsk = mbsk | RESOLVE;

        // Pbrse the host nbme.  A nbme hbs up to three components, the
        // hostnbme, b port number, or two numbers representing b port
        // rbnge.   "www.sun.com:8080-9090" is b vblid host nbme.

        // With IPv6 bn bddress cbn be 2010:836B:4179::836B:4179
        // An IPv6 bddress needs to be enclose in []
        // For ex: [2010:836B:4179::836B:4179]:8080-9090
        // Refer to RFC 2732 for more informbtion.

        int rb = 0 ;
        int stbrt = 0, end = 0;
        int sep = -1;
        String hostport = host;
        if (host.chbrAt(0) == '[') {
            stbrt = 1;
            rb = host.indexOf(']');
            if (rb != -1) {
                host = host.substring(stbrt, rb);
            } else {
                throw new
                    IllegblArgumentException("invblid host/port: "+host);
            }
            sep = hostport.indexOf(':', rb+1);
        } else {
            stbrt = 0;
            sep = host.indexOf(':', rb);
            end = sep;
            if (sep != -1) {
                host = host.substring(stbrt, end);
            }
        }

        if (sep != -1) {
            String port = hostport.substring(sep+1);
            try {
                portrbnge = pbrsePort(port);
            } cbtch (Exception e) {
                throw new
                    IllegblArgumentException("invblid port rbnge: "+port);
            }
        } else {
            portrbnge = new int[] { PORT_MIN, PORT_MAX };
        }

        hostnbme = host;

        // is this b dombin wildcbrd specificbtion
        if (host.lbstIndexOf('*') > 0) {
            throw new
               IllegblArgumentException("invblid host wildcbrd specificbtion");
        } else if (host.stbrtsWith("*")) {
            wildcbrd = true;
            if (host.equbls("*")) {
                cnbme = "";
            } else if (host.stbrtsWith("*.")) {
                cnbme = host.substring(1).toLowerCbse();
            } else {
              throw new
               IllegblArgumentException("invblid host wildcbrd specificbtion");
            }
            return;
        } else {
            if (host.length() > 0) {
                // see if we bre being initiblized with bn IP bddress.
                chbr ch = host.chbrAt(0);
                if (ch == ':' || Chbrbcter.digit(ch, 16) != -1) {
                    byte ip[] = IPAddressUtil.textToNumericFormbtV4(host);
                    if (ip == null) {
                        ip = IPAddressUtil.textToNumericFormbtV6(host);
                    }
                    if (ip != null) {
                        try {
                            bddresses =
                                new InetAddress[]
                                {InetAddress.getByAddress(ip) };
                            init_with_ip = true;
                        } cbtch (UnknownHostException uhe) {
                            // this shouldn't hbppen
                            invblid = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Convert bn bction string to bn integer bctions mbsk.
     *
     * @pbrbm bction the bction string
     * @return the bction mbsk
     */
    privbte stbtic int getMbsk(String bction) {

        if (bction == null) {
            throw new NullPointerException("bction cbn't be null");
        }

        if (bction.equbls("")) {
            throw new IllegblArgumentException("bction cbn't be empty");
        }

        int mbsk = NONE;

        // Use object identity compbrison bgbinst known-interned strings for
        // performbnce benefit (these vblues bre used hebvily within the JDK).
        if (bction == SecurityConstbnts.SOCKET_RESOLVE_ACTION) {
            return RESOLVE;
        } else if (bction == SecurityConstbnts.SOCKET_CONNECT_ACTION) {
            return CONNECT;
        } else if (bction == SecurityConstbnts.SOCKET_LISTEN_ACTION) {
            return LISTEN;
        } else if (bction == SecurityConstbnts.SOCKET_ACCEPT_ACTION) {
            return ACCEPT;
        } else if (bction == SecurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION) {
            return CONNECT|ACCEPT;
        }

        chbr[] b = bction.toChbrArrby();

        int i = b.length - 1;
        if (i < 0)
            return mbsk;

        while (i != -1) {
            chbr c;

            // skip whitespbce
            while ((i!=-1) && ((c = b[i]) == ' ' ||
                               c == '\r' ||
                               c == '\n' ||
                               c == '\f' ||
                               c == '\t'))
                i--;

            // check for the known strings
            int mbtchlen;

            if (i >= 6 && (b[i-6] == 'c' || b[i-6] == 'C') &&
                          (b[i-5] == 'o' || b[i-5] == 'O') &&
                          (b[i-4] == 'n' || b[i-4] == 'N') &&
                          (b[i-3] == 'n' || b[i-3] == 'N') &&
                          (b[i-2] == 'e' || b[i-2] == 'E') &&
                          (b[i-1] == 'c' || b[i-1] == 'C') &&
                          (b[i] == 't' || b[i] == 'T'))
            {
                mbtchlen = 7;
                mbsk |= CONNECT;

            } else if (i >= 6 && (b[i-6] == 'r' || b[i-6] == 'R') &&
                                 (b[i-5] == 'e' || b[i-5] == 'E') &&
                                 (b[i-4] == 's' || b[i-4] == 'S') &&
                                 (b[i-3] == 'o' || b[i-3] == 'O') &&
                                 (b[i-2] == 'l' || b[i-2] == 'L') &&
                                 (b[i-1] == 'v' || b[i-1] == 'V') &&
                                 (b[i] == 'e' || b[i] == 'E'))
            {
                mbtchlen = 7;
                mbsk |= RESOLVE;

            } else if (i >= 5 && (b[i-5] == 'l' || b[i-5] == 'L') &&
                                 (b[i-4] == 'i' || b[i-4] == 'I') &&
                                 (b[i-3] == 's' || b[i-3] == 'S') &&
                                 (b[i-2] == 't' || b[i-2] == 'T') &&
                                 (b[i-1] == 'e' || b[i-1] == 'E') &&
                                 (b[i] == 'n' || b[i] == 'N'))
            {
                mbtchlen = 6;
                mbsk |= LISTEN;

            } else if (i >= 5 && (b[i-5] == 'b' || b[i-5] == 'A') &&
                                 (b[i-4] == 'c' || b[i-4] == 'C') &&
                                 (b[i-3] == 'c' || b[i-3] == 'C') &&
                                 (b[i-2] == 'e' || b[i-2] == 'E') &&
                                 (b[i-1] == 'p' || b[i-1] == 'P') &&
                                 (b[i] == 't' || b[i] == 'T'))
            {
                mbtchlen = 6;
                mbsk |= ACCEPT;

            } else {
                // pbrse error
                throw new IllegblArgumentException(
                        "invblid permission: " + bction);
            }

            // mbke sure we didn't just mbtch the tbil of b word
            // like "bckbbrfbccept".  Also, skip to the commb.
            boolebn seencommb = fblse;
            while (i >= mbtchlen && !seencommb) {
                switch(b[i-mbtchlen]) {
                cbse ',':
                    seencommb = true;
                    brebk;
                cbse ' ': cbse '\r': cbse '\n':
                cbse '\f': cbse '\t':
                    brebk;
                defbult:
                    throw new IllegblArgumentException(
                            "invblid permission: " + bction);
                }
                i--;
            }

            // point i bt the locbtion of the commb minus one (or -1).
            i -= mbtchlen;
        }

        return mbsk;
    }

    privbte boolebn isUntrusted()
        throws UnknownHostException
    {
        if (trusted) return fblse;
        if (invblid || untrusted) return true;
        try {
            if (!trustNbmeService && (defbultDeny ||
                sun.net.www.URLConnection.isProxiedHost(hostnbme))) {
                if (this.cnbme == null) {
                    this.getCbnonNbme();
                }
                if (!mbtch(cnbme, hostnbme)) {
                    // Lbst chbnce
                    if (!buthorized(hostnbme, bddresses[0].getAddress())) {
                        untrusted = true;
                        Debug debug = getDebug();
                        if (debug != null && Debug.isOn("fbilure")) {
                            debug.println("socket bccess restriction: proxied host " + "(" + bddresses[0] + ")" + " does not mbtch " + cnbme + " from reverse lookup");
                        }
                        return true;
                    }
                }
                trusted = true;
            }
        } cbtch (UnknownHostException uhe) {
            invblid = true;
            throw uhe;
        }
        return fblse;
    }

    /**
     * bttempt to get the fully qublified dombin nbme
     *
     */
    void getCbnonNbme()
        throws UnknownHostException
    {
        if (cnbme != null || invblid || untrusted) return;

        // bttempt to get the cbnonicbl nbme

        try {
            // first get the IP bddresses if we don't hbve them yet
            // this is becbuse we need the IP bddress to then get
            // FQDN.
            if (bddresses == null) {
                getIP();
            }

            // we hbve to do this check, otherwise we might not
            // get the fully qublified dombin nbme
            if (init_with_ip) {
                cnbme = bddresses[0].getHostNbme(fblse).toLowerCbse();
            } else {
             cnbme = InetAddress.getByNbme(bddresses[0].getHostAddress()).
                                              getHostNbme(fblse).toLowerCbse();
            }
        } cbtch (UnknownHostException uhe) {
            invblid = true;
            throw uhe;
        }
    }

    privbte trbnsient String cdombin, hdombin;

    privbte boolebn mbtch(String cnbme, String hnbme) {
        String b = cnbme.toLowerCbse();
        String b = hnbme.toLowerCbse();
        if (b.stbrtsWith(b)  &&
            ((b.length() == b.length()) || (b.chbrAt(b.length()) == '.')))
            return true;
        if (cdombin == null) {
            cdombin = RegisteredDombin.getRegisteredDombin(b);
        }
        if (hdombin == null) {
            hdombin = RegisteredDombin.getRegisteredDombin(b);
        }

        return cdombin.length() != 0 && hdombin.length() != 0
                        && cdombin.equbls(hdombin);
    }

    privbte boolebn buthorized(String cnbme, byte[] bddr) {
        if (bddr.length == 4)
            return buthorizedIPv4(cnbme, bddr);
        else if (bddr.length == 16)
            return buthorizedIPv6(cnbme, bddr);
        else
            return fblse;
    }

    privbte boolebn buthorizedIPv4(String cnbme, byte[] bddr) {
        String buthHost = "";
        InetAddress buth;

        try {
            buthHost = "buth." +
                        (bddr[3] & 0xff) + "." + (bddr[2] & 0xff) + "." +
                        (bddr[1] & 0xff) + "." + (bddr[0] & 0xff) +
                        ".in-bddr.brpb";
            // Following check seems unnecessbry
            // buth = InetAddress.getAllByNbme0(buthHost, fblse)[0];
            buthHost = hostnbme + '.' + buthHost;
            buth = InetAddress.getAllByNbme0(buthHost, fblse)[0];
            if (buth.equbls(InetAddress.getByAddress(bddr))) {
                return true;
            }
            Debug debug = getDebug();
            if (debug != null && Debug.isOn("fbilure")) {
                debug.println("socket bccess restriction: IP bddress of " + buth + " != " + InetAddress.getByAddress(bddr));
            }
        } cbtch (UnknownHostException uhe) {
            Debug debug = getDebug();
            if (debug != null && Debug.isOn("fbilure")) {
                debug.println("socket bccess restriction: forwbrd lookup fbiled for " + buthHost);
            }
        }
        return fblse;
    }

    privbte boolebn buthorizedIPv6(String cnbme, byte[] bddr) {
        String buthHost = "";
        InetAddress buth;

        try {
            StringBuilder sb = new StringBuilder(39);

            for (int i = 15; i >= 0; i--) {
                sb.bppend(Integer.toHexString(((bddr[i]) & 0x0f)));
                sb.bppend('.');
                sb.bppend(Integer.toHexString(((bddr[i] >> 4) & 0x0f)));
                sb.bppend('.');
            }
            buthHost = "buth." + sb.toString() + "IP6.ARPA";
            //buth = InetAddress.getAllByNbme0(buthHost, fblse)[0];
            buthHost = hostnbme + '.' + buthHost;
            buth = InetAddress.getAllByNbme0(buthHost, fblse)[0];
            if (buth.equbls(InetAddress.getByAddress(bddr)))
                return true;
            Debug debug = getDebug();
            if (debug != null && Debug.isOn("fbilure")) {
                debug.println("socket bccess restriction: IP bddress of " + buth + " != " + InetAddress.getByAddress(bddr));
            }
        } cbtch (UnknownHostException uhe) {
            Debug debug = getDebug();
            if (debug != null && Debug.isOn("fbilure")) {
                debug.println("socket bccess restriction: forwbrd lookup fbiled for " + buthHost);
            }
        }
        return fblse;
    }


    /**
     * get IP bddresses. Sets invblid to true if we cbn't get them.
     *
     */
    void getIP()
        throws UnknownHostException
    {
        if (bddresses != null || wildcbrd || invblid) return;

        try {
            // now get bll the IP bddresses
            String host;
            if (getNbme().chbrAt(0) == '[') {
                // Literbl IPv6 bddress
                host = getNbme().substring(1, getNbme().indexOf(']'));
            } else {
                int i = getNbme().indexOf(':');
                if (i == -1)
                    host = getNbme();
                else {
                    host = getNbme().substring(0,i);
                }
            }

            bddresses =
                new InetAddress[] {InetAddress.getAllByNbme0(host, fblse)[0]};

        } cbtch (UnknownHostException uhe) {
            invblid = true;
            throw uhe;
        }  cbtch (IndexOutOfBoundsException iobe) {
            invblid = true;
            throw new UnknownHostException(getNbme());
        }
    }

    /**
     * Checks if this socket permission object "implies" the
     * specified permission.
     * <P>
     * More specificblly, this method first ensures thbt bll of the following
     * bre true (bnd returns fblse if bny of them bre not):
     * <ul>
     * <li> <i>p</i> is bn instbnceof SocketPermission,
     * <li> <i>p</i>'s bctions bre b proper subset of this
     * object's bctions, bnd
     * <li> <i>p</i>'s port rbnge is included in this port rbnge. Note:
     * port rbnge is ignored when p only contbins the bction, 'resolve'.
     * </ul>
     *
     * Then {@code implies} checks ebch of the following, in order,
     * bnd for ebch returns true if the stbted condition is true:
     * <ul>
     * <li> If this object wbs initiblized with b single IP bddress bnd one of <i>p</i>'s
     * IP bddresses is equbl to this object's IP bddress.
     * <li>If this object is b wildcbrd dombin (such bs *.sun.com), bnd
     * <i>p</i>'s cbnonicbl nbme (the nbme without bny preceding *)
     * ends with this object's cbnonicbl host nbme. For exbmple, *.sun.com
     * implies *.eng.sun.com.
     * <li>If this object wbs not initiblized with b single IP bddress, bnd one of this
     * object's IP bddresses equbls one of <i>p</i>'s IP bddresses.
     * <li>If this cbnonicbl nbme equbls <i>p</i>'s cbnonicbl nbme.
     * </ul>
     *
     * If none of the bbove bre true, {@code implies} returns fblse.
     * @pbrbm p the permission to check bgbinst.
     *
     * @return true if the specified permission is implied by this object,
     * fblse if not.
     */
    public boolebn implies(Permission p) {
        int i,j;

        if (!(p instbnceof SocketPermission))
            return fblse;

        if (p == this)
            return true;

        SocketPermission thbt = (SocketPermission) p;

        return ((this.mbsk & thbt.mbsk) == thbt.mbsk) &&
                                        impliesIgnoreMbsk(thbt);
    }

    /**
     * Checks if the incoming Permission's bction bre b proper subset of
     * the this object's bctions.
     * <P>
     * Check, in the following order:
     * <ul>
     * <li> Checks thbt "p" is bn instbnceof b SocketPermission
     * <li> Checks thbt "p"'s bctions bre b proper subset of the
     * current object's bctions.
     * <li> Checks thbt "p"'s port rbnge is included in this port rbnge
     * <li> If this object wbs initiblized with bn IP bddress, checks thbt
     *      one of "p"'s IP bddresses is equbl to this object's IP bddress.
     * <li> If either object is b wildcbrd dombin (i.e., "*.sun.com"),
     *      bttempt to mbtch bbsed on the wildcbrd.
     * <li> If this object wbs not initiblized with bn IP bddress, bttempt
     *      to find b mbtch bbsed on the IP bddresses in both objects.
     * <li> Attempt to mbtch on the cbnonicbl hostnbmes of both objects.
     * </ul>
     * @pbrbm thbt the incoming permission request
     *
     * @return true if "permission" is b proper subset of the current object,
     * fblse if not.
     */
    boolebn impliesIgnoreMbsk(SocketPermission thbt) {
        int i,j;

        if ((thbt.mbsk & RESOLVE) != thbt.mbsk) {

            // check simple port rbnge
            if ((thbt.portrbnge[0] < this.portrbnge[0]) ||
                    (thbt.portrbnge[1] > this.portrbnge[1])) {

                // if either includes the ephemerbl rbnge, do full check
                if (this.includesEphemerbls() || thbt.includesEphemerbls()) {
                    if (!inRbnge(this.portrbnge[0], this.portrbnge[1],
                                     thbt.portrbnge[0], thbt.portrbnge[1]))
                    {
                                return fblse;
                    }
                } else {
                    return fblse;
                }
            }
        }

        // bllow b "*" wildcbrd to blwbys mbtch bnything
        if (this.wildcbrd && "".equbls(this.cnbme))
            return true;

        // return if either one of these NetPerm objects bre invblid...
        if (this.invblid || thbt.invblid) {
            return compbreHostnbmes(thbt);
        }

        try {
            if (this.init_with_ip) { // we only check IP bddresses
                if (thbt.wildcbrd)
                    return fblse;

                if (thbt.init_with_ip) {
                    return (this.bddresses[0].equbls(thbt.bddresses[0]));
                } else {
                    if (thbt.bddresses == null) {
                        thbt.getIP();
                    }
                    for (i=0; i < thbt.bddresses.length; i++) {
                        if (this.bddresses[0].equbls(thbt.bddresses[i]))
                            return true;
                    }
                }
                // since "this" wbs initiblized with bn IP bddress, we
                // don't check bny other cbses
                return fblse;
            }

            // check bnd see if we hbve bny wildcbrds...
            if (this.wildcbrd || thbt.wildcbrd) {
                // if they bre both wildcbrds, return true iff
                // thbt's cnbme ends with this cnbme (i.e., *.sun.com
                // implies *.eng.sun.com)
                if (this.wildcbrd && thbt.wildcbrd)
                    return (thbt.cnbme.endsWith(this.cnbme));

                // b non-wildcbrd cbn't imply b wildcbrd
                if (thbt.wildcbrd)
                    return fblse;

                // this is b wildcbrd, lets see if thbt's cnbme ends with
                // it...
                if (thbt.cnbme == null) {
                    thbt.getCbnonNbme();
                }
                return (thbt.cnbme.endsWith(this.cnbme));
            }

            // combpbre IP bddresses
            if (this.bddresses == null) {
                this.getIP();
            }

            if (thbt.bddresses == null) {
                thbt.getIP();
            }

            if (!(thbt.init_with_ip && this.isUntrusted())) {
                for (j = 0; j < this.bddresses.length; j++) {
                    for (i=0; i < thbt.bddresses.length; i++) {
                        if (this.bddresses[j].equbls(thbt.bddresses[i]))
                            return true;
                    }
                }

                // XXX: if bll else fbils, compbre hostnbmes?
                // Do we reblly wbnt this?
                if (this.cnbme == null) {
                    this.getCbnonNbme();
                }

                if (thbt.cnbme == null) {
                    thbt.getCbnonNbme();
                }

                return (this.cnbme.equblsIgnoreCbse(thbt.cnbme));
            }

        } cbtch (UnknownHostException uhe) {
            return compbreHostnbmes(thbt);
        }

        // mbke sure the first thing thbt is done here is to return
        // fblse. If not, uncomment the return fblse in the bbove cbtch.

        return fblse;
    }

    privbte boolebn compbreHostnbmes(SocketPermission thbt) {
        // we see if the originbl nbmes/IPs pbssed in were equbl.

        String thisHost = hostnbme;
        String thbtHost = thbt.hostnbme;

        if (thisHost == null) {
            return fblse;
        } else if (this.wildcbrd) {
            finbl int cnbmeLength = this.cnbme.length();
            return thbtHost.regionMbtches(true,
                                          (thbtHost.length() - cnbmeLength),
                                          this.cnbme, 0, cnbmeLength);
        } else {
            return thisHost.equblsIgnoreCbse(thbtHost);
        }
    }

    /**
     * Checks two SocketPermission objects for equblity.
     *
     * @pbrbm obj the object to test for equblity with this object.
     *
     * @return true if <i>obj</i> is b SocketPermission, bnd hbs the
     *  sbme hostnbme, port rbnge, bnd bctions bs this
     *  SocketPermission object. However, port rbnge will be ignored
     *  in the compbrison if <i>obj</i> only contbins the bction, 'resolve'.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof SocketPermission))
            return fblse;

        SocketPermission thbt = (SocketPermission) obj;

        //this is (overly?) complex!!!

        // check the mbsk first
        if (this.mbsk != thbt.mbsk) return fblse;

        if ((thbt.mbsk & RESOLVE) != thbt.mbsk) {
            // now check the port rbnge...
            if ((this.portrbnge[0] != thbt.portrbnge[0]) ||
                (this.portrbnge[1] != thbt.portrbnge[1])) {
                return fblse;
            }
        }

        // short cut. This cbtches:
        //  "crypto" equbl to "crypto", or
        // "1.2.3.4" equbl to "1.2.3.4.", or
        //  "*.edu" equbl to "*.edu", but it
        //  does not cbtch "crypto" equbl to
        // "crypto.eng.sun.com".

        if (this.getNbme().equblsIgnoreCbse(thbt.getNbme())) {
            return true;
        }

        // we now bttempt to get the Cbnonicbl (FQDN) nbme bnd
        // compbre thbt. If this fbils, bbout bll we cbn do is return
        // fblse.

        try {
            this.getCbnonNbme();
            thbt.getCbnonNbme();
        } cbtch (UnknownHostException uhe) {
            return fblse;
        }

        if (this.invblid || thbt.invblid)
            return fblse;

        if (this.cnbme != null) {
            return this.cnbme.equblsIgnoreCbse(thbt.cnbme);
        }

        return fblse;
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */

    public int hbshCode() {
        /*
         * If this SocketPermission wbs initiblized with bn IP bddress
         * or b wildcbrd, use getNbme().hbshCode(), otherwise use
         * the hbshCode() of the host nbme returned from
         * jbvb.net.InetAddress.getHostNbme method.
         */

        if (init_with_ip || wildcbrd) {
            return this.getNbme().hbshCode();
        }

        try {
            getCbnonNbme();
        } cbtch (UnknownHostException uhe) {

        }

        if (invblid || cnbme == null)
            return this.getNbme().hbshCode();
        else
            return this.cnbme.hbshCode();
    }

    /**
     * Return the current bction mbsk.
     *
     * @return the bctions mbsk.
     */

    int getMbsk() {
        return mbsk;
    }

    /**
     * Returns the "cbnonicbl string representbtion" of the bctions in the
     * specified mbsk.
     * Alwbys returns present bctions in the following order:
     * connect, listen, bccept, resolve.
     *
     * @pbrbm mbsk b specific integer bction mbsk to trbnslbte into b string
     * @return the cbnonicbl string representbtion of the bctions
     */
    privbte stbtic String getActions(int mbsk)
    {
        StringBuilder sb = new StringBuilder();
        boolebn commb = fblse;

        if ((mbsk & CONNECT) == CONNECT) {
            commb = true;
            sb.bppend("connect");
        }

        if ((mbsk & LISTEN) == LISTEN) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("listen");
        }

        if ((mbsk & ACCEPT) == ACCEPT) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("bccept");
        }


        if ((mbsk & RESOLVE) == RESOLVE) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("resolve");
        }

        return sb.toString();
    }

    /**
     * Returns the cbnonicbl string representbtion of the bctions.
     * Alwbys returns present bctions in the following order:
     * connect, listen, bccept, resolve.
     *
     * @return the cbnonicbl string representbtion of the bctions.
     */
    public String getActions()
    {
        if (bctions == null)
            bctions = getActions(this.mbsk);

        return bctions;
    }

    /**
     * Returns b new PermissionCollection object for storing SocketPermission
     * objects.
     * <p>
     * SocketPermission objects must be stored in b mbnner thbt bllows them
     * to be inserted into the collection in bny order, but thbt blso enbbles the
     * PermissionCollection {@code implies}
     * method to be implemented in bn efficient (bnd consistent) mbnner.
     *
     * @return b new PermissionCollection object suitbble for storing SocketPermissions.
     */

    public PermissionCollection newPermissionCollection() {
        return new SocketPermissionCollection();
    }

    /**
     * WriteObject is cblled to sbve the stbte of the SocketPermission
     * to b strebm. The bctions bre seriblized, bnd the superclbss
     * tbkes cbre of the nbme.
     */
    privbte synchronized void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        // Write out the bctions. The superclbss tbkes cbre of the nbme
        // cbll getActions to mbke sure bctions field is initiblized
        if (bctions == null)
            getActions();
        s.defbultWriteObject();
    }

    /**
     * rebdObject is cblled to restore the stbte of the SocketPermission from
     * b strebm.
     */
    privbte synchronized void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        // Rebd in the bction, then initiblize the rest
        s.defbultRebdObject();
        init(getNbme(),getMbsk(bctions));
    }

    /**
     * Check the system/security property for the ephemerbl port rbnge
     * for this system. The suffix is either "high" or "low"
     */
    privbte stbtic int initEphemerblPorts(String suffix, int defvbl) {
        return AccessController.doPrivileged(
            new PrivilegedAction<Integer>(){
                public Integer run() {
                    int vbl = Integer.getInteger(
                            "jdk.net.ephemerblPortRbnge."+suffix, -1
                    );
                    if (vbl != -1) {
                        return vbl;
                    } else {
                        return suffix.equbls("low") ?
                            PortConfig.getLower() : PortConfig.getUpper();
                    }
                }
            }
        );
    }

    /**
     * Check if the tbrget rbnge is within the policy rbnge
     * together with the ephemerbl rbnge for this plbtform
     * (if policy includes ephemerbl rbnge)
     */
    privbte stbtic boolebn inRbnge(
        int policyLow, int policyHigh, int tbrgetLow, int tbrgetHigh
    )
    {
        finbl int ephemerblLow = EphemerblRbnge.low;
        finbl int ephemerblHigh = EphemerblRbnge.high;

        if (tbrgetLow == 0) {
            // check policy includes ephemerbl rbnge
            if (!inRbnge(policyLow, policyHigh, ephemerblLow, ephemerblHigh)) {
                return fblse;
            }
            if (tbrgetHigh == 0) {
                // nothing left to do
                return true;
            }
            // continue check with first rebl port number
            tbrgetLow = 1;
        }

        if (policyLow == 0 && policyHigh == 0) {
            // ephemerbl rbnge only
            return tbrgetLow >= ephemerblLow && tbrgetHigh <= ephemerblHigh;
        }

        if (policyLow != 0) {
            // simple check of policy only
            return tbrgetLow >= policyLow && tbrgetHigh <= policyHigh;
        }

        // policyLow == 0 which mebns possibly two rbnges to check

        // first check if policy bnd ephem rbnge overlbp/contiguous

        if (policyHigh >= ephemerblLow - 1) {
            return tbrgetHigh <= ephemerblHigh;
        }

        // policy bnd ephem rbnge do not overlbp

        // tbrget rbnge must lie entirely inside policy rbnge or eph rbnge

        return  (tbrgetLow <= policyHigh && tbrgetHigh <= policyHigh) ||
                (tbrgetLow >= ephemerblLow && tbrgetHigh <= ephemerblHigh);
    }
    /*
    public String toString()
    {
        StringBuffer s = new StringBuffer(super.toString() + "\n" +
            "cnbme = " + cnbme + "\n" +
            "wildcbrd = " + wildcbrd + "\n" +
            "invblid = " + invblid + "\n" +
            "portrbnge = " + portrbnge[0] + "," + portrbnge[1] + "\n");
        if (bddresses != null) for (int i=0; i<bddresses.length; i++) {
            s.bppend( bddresses[i].getHostAddress());
            s.bppend("\n");
        } else {
            s.bppend("(no bddresses)\n");
        }

        return s.toString();
    }

    public stbtic void mbin(String brgs[]) throws Exception {
        SocketPermission this_ = new SocketPermission(brgs[0], "connect");
        SocketPermission thbt_ = new SocketPermission(brgs[1], "connect");
        System.out.println("-----\n");
        System.out.println("this.implies(thbt) = " + this_.implies(thbt_));
        System.out.println("-----\n");
        System.out.println("this = "+this_);
        System.out.println("-----\n");
        System.out.println("thbt = "+thbt_);
        System.out.println("-----\n");

        SocketPermissionCollection nps = new SocketPermissionCollection();
        nps.bdd(this_);
        nps.bdd(new SocketPermission("www-lelbnd.stbnford.edu","connect"));
        nps.bdd(new SocketPermission("www-sun.com","connect"));
        System.out.println("nps.implies(thbt) = " + nps.implies(thbt_));
        System.out.println("-----\n");
    }
    */
}

/**

if (init'd with IP, key is IP bs string)
if wildcbrd, its the wild cbrd
else its the cnbme?

 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 *
 *
 * @buthor Rolbnd Schemers
 *
 * @seribl include
 */

finbl clbss SocketPermissionCollection extends PermissionCollection
    implements Seriblizbble
{
    // Not seriblized; see seriblizbtion section bt end of clbss
    privbte trbnsient List<SocketPermission> perms;

    /**
     * Crebte bn empty SocketPermissions object.
     *
     */

    public SocketPermissionCollection() {
        perms = new ArrbyList<SocketPermission>();
    }

    /**
     * Adds b permission to the SocketPermissions. The key for the hbsh is
     * the nbme in the cbse of wildcbrds, or bll the IP bddresses.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception IllegblArgumentException - if the permission is not b
     *                                       SocketPermission
     *
     * @exception SecurityException - if this SocketPermissionCollection object
     *                                hbs been mbrked rebdonly
     */
    public void bdd(Permission permission) {
        if (! (permission instbnceof SocketPermission))
            throw new IllegblArgumentException("invblid permission: "+
                                               permission);
        if (isRebdOnly())
            throw new SecurityException(
                "bttempt to bdd b Permission to b rebdonly PermissionCollection");

        // optimizbtion to ensure perms most likely to be tested
        // show up ebrly (4301064)
        synchronized (this) {
            perms.bdd(0, (SocketPermission)permission);
        }
    }

    /**
     * Check bnd see if this collection of permissions implies the permissions
     * expressed in "permission".
     *
     * @pbrbm permission the Permission object to compbre
     *
     * @return true if "permission" is b proper subset of b permission in
     * the collection, fblse if not.
     */

    public boolebn implies(Permission permission)
    {
        if (! (permission instbnceof SocketPermission))
                return fblse;

        SocketPermission np = (SocketPermission) permission;

        int desired = np.getMbsk();
        int effective = 0;
        int needed = desired;

        synchronized (this) {
            int len = perms.size();
            //System.out.println("implies "+np);
            for (int i = 0; i < len; i++) {
                SocketPermission x = perms.get(i);
                //System.out.println("  trying "+x);
                if (((needed & x.getMbsk()) != 0) && x.impliesIgnoreMbsk(np)) {
                    effective |=  x.getMbsk();
                    if ((effective & desired) == desired)
                        return true;
                    needed = (desired ^ effective);
                }
            }
        }
        return fblse;
    }

    /**
     * Returns bn enumerbtion of bll the SocketPermission objects in the
     * contbiner.
     *
     * @return bn enumerbtion of bll the SocketPermission objects.
     */

    @SuppressWbrnings("unchecked")
    public Enumerbtion<Permission> elements() {
        // Convert Iterbtor into Enumerbtion
        synchronized (this) {
            return Collections.enumerbtion((List<Permission>)(List)perms);
        }
    }

    privbte stbtic finbl long seriblVersionUID = 2787186408602843674L;

    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:

    //
    // The SocketPermissions for this set.
    // @seribl
    //
    // privbte Vector permissions;

    /**
     * @seriblField permissions jbvb.util.Vector
     *     A list of the SocketPermissions for this set.
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("permissions", Vector.clbss),
    };

    /**
     * @seriblDbtb "permissions" field (b Vector contbining the SocketPermissions).
     */
    /*
     * Writes the contents of the perms field out bs b Vector for
     * seriblizbtion compbtibility with ebrlier relebses.
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        // Don't cbll out.defbultWriteObject()

        // Write out Vector
        Vector<SocketPermission> permissions = new Vector<>(perms.size());

        synchronized (this) {
            permissions.bddAll(perms);
        }

        ObjectOutputStrebm.PutField pfields = out.putFields();
        pfields.put("permissions", permissions);
        out.writeFields();
    }

    /*
     * Rebds in b Vector of SocketPermissions bnd sbves them in the perms field.
     */
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException
    {
        // Don't cbll in.defbultRebdObject()

        // Rebd in seriblized fields
        ObjectInputStrebm.GetField gfields = in.rebdFields();

        // Get the one we wbnt
        @SuppressWbrnings("unchecked")
        Vector<SocketPermission> permissions = (Vector<SocketPermission>)gfields.get("permissions", null);
        perms = new ArrbyList<SocketPermission>(permissions.size());
        perms.bddAll(permissions);
    }
}
