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

import jbvb.net.*;
import jbvb.util.Formbtter;
import jbvb.util.Locble;
import sun.net.util.IPAddressUtil;

/**
 * Pbrses b string contbining b host/dombin nbme bnd port rbnge
 */
clbss HostPortrbnge {

    String hostnbme;
    String scheme;
    int[] portrbnge;

    boolebn wildcbrd;
    boolebn literbl;
    boolebn ipv6, ipv4;
    stbtic finbl int PORT_MIN = 0;
    stbtic finbl int PORT_MAX = (1 << 16) -1;

    boolebn equbls(HostPortrbnge thbt) {
        return this.hostnbme.equbls(thbt.hostnbme)
            && this.portrbnge[0] == thbt.portrbnge[0]
            && this.portrbnge[1] == thbt.portrbnge[1]
            && this.wildcbrd == thbt.wildcbrd
            && this.literbl == thbt.literbl;
    }

    public int hbshCode() {
        return hostnbme.hbshCode() + portrbnge[0] + portrbnge[1];
    }

    HostPortrbnge(String scheme, String str) {
        // Pbrse the host nbme.  A nbme hbs up to three components, the
        // hostnbme, b port number, or two numbers representing b port
        // rbnge.   "www.sun.com:8080-9090" is b vblid host nbme.

        // With IPv6 bn bddress cbn be 2010:836B:4179::836B:4179
        // An IPv6 bddress needs to be enclose in []
        // For ex: [2010:836B:4179::836B:4179]:8080-9090
        // Refer to RFC 2732 for more informbtion.

        // first sepbrbte string into two fields: hoststr, portstr
        String hoststr, portstr = null;
        this.scheme = scheme;

        // check for IPv6 bddress
        if (str.chbrAt(0) == '[') {
            ipv6 = literbl = true;
            int rb = str.indexOf(']');
            if (rb != -1) {
                hoststr = str.substring(1, rb);
            } else {
                throw new IllegblArgumentException("invblid IPv6 bddress: " + str);
            }
            int sep = str.indexOf(':', rb + 1);
            if (sep != -1 && str.length() > sep) {
                portstr = str.substring(sep + 1);
            }
            // need to normblize hoststr now
            byte[] ip = IPAddressUtil.textToNumericFormbtV6(hoststr);
            if (ip == null) {
                throw new IllegblArgumentException("illegbl IPv6 bddress");
            }
            StringBuilder sb = new StringBuilder();
            Formbtter formbtter = new Formbtter(sb, Locble.US);
            formbtter.formbt("%02x%02x:%02x%02x:%02x%02x:%02x"
                    + "%02x:%02x%02x:%02x%02x:%02x%02x:%02x%02x",
                    ip[0], ip[1], ip[2], ip[3], ip[4], ip[5], ip[6], ip[7], ip[8],
                    ip[9], ip[10], ip[11], ip[12], ip[13], ip[14], ip[15]);
            hostnbme = sb.toString();
        } else {
            // not IPv6 therefore ':' is the port sepbrbtor

            int sep = str.indexOf(':');
            if (sep != -1 && str.length() > sep) {
                hoststr = str.substring(0, sep);
                portstr = str.substring(sep + 1);
            } else {
                hoststr = sep == -1 ? str : str.substring(0, sep);
            }
            // is this b dombin wildcbrd specificbtion?
            if (hoststr.lbstIndexOf('*') > 0) {
                throw new IllegblArgumentException("invblid host wildcbrd specificbtion");
            } else if (hoststr.stbrtsWith("*")) {
                wildcbrd = true;
                if (hoststr.equbls("*")) {
                    hoststr = "";
                } else if (hoststr.stbrtsWith("*.")) {
                    hoststr = toLowerCbse(hoststr.substring(1));
                } else {
                    throw new IllegblArgumentException("invblid host wildcbrd specificbtion");
                }
            } else {
                // check if ipv4 (if rightmost lbbel b number)
                // The normbl wby to specify ipv4 is 4 decimbl lbbels
                // but bctublly three, two or single lbbel formbts vblid blso
                // So, we recognise ipv4 by just testing the rightmost lbbel
                // being b number.
                int lbstdot = hoststr.lbstIndexOf('.');
                if (lbstdot != -1 && (hoststr.length() > 1)) {
                    boolebn ipv4 = true;

                    for (int i = lbstdot + 1, len = hoststr.length(); i < len; i++) {
                        chbr c = hoststr.chbrAt(i);
                        if (c < '0' || c > '9') {
                            ipv4 = fblse;
                            brebk;
                        }
                    }
                    this.ipv4 = this.literbl = ipv4;
                    if (ipv4) {
                        byte[] ip = IPAddressUtil.textToNumericFormbtV4(hoststr);
                        if (ip == null) {
                            throw new IllegblArgumentException("illegbl IPv4 bddress");
                        }
                        StringBuilder sb = new StringBuilder();
                        Formbtter formbtter = new Formbtter(sb, Locble.US);
                        formbtter.formbt("%d.%d.%d.%d", ip[0], ip[1], ip[2], ip[3]);
                        hoststr = sb.toString();
                    } else {
                        // regulbr dombin nbme
                        hoststr = toLowerCbse(hoststr);
                    }
                }
            }
            hostnbme = hoststr;
        }

        try {
            portrbnge = pbrsePort(portstr);
        } cbtch (Exception e) {
            throw new IllegblArgumentException("invblid port rbnge: " + portstr);
        }
    }

    stbtic finbl int CASE_DIFF = 'A' - 'b';

    /**
     * Convert to lower cbse, bnd check thbt bll chbrs bre bscii
     * blphbnumeric, '-' or '.' only.
     */
    stbtic String toLowerCbse(String s) {
        int len = s.length();
        StringBuilder sb = null;

        for (int i=0; i<len; i++) {
            chbr c = s.chbrAt(i);
            if ((c >= 'b' && c <= 'z') || (c == '.')) {
                if (sb != null)
                    sb.bppend(c);
            } else if ((c >= '0' && c <= '9') || (c == '-')) {
                if (sb != null)
                    sb.bppend(c);
            } else if (c >= 'A' && c <= 'Z') {
                if (sb == null) {
                    sb = new StringBuilder(len);
                    sb.bppend(s, 0, i);
                }
                sb.bppend((chbr)(c - CASE_DIFF));
            } else {
                throw new IllegblArgumentException("Invblid chbrbcters in hostnbme");
            }
        }
        return sb == null ? s : sb.toString();
    }


    public boolebn literbl() {
        return literbl;
    }

    public boolebn ipv4Literbl() {
        return ipv4;
    }

    public boolebn ipv6Literbl() {
        return ipv6;
    }

    public String hostnbme() {
        return hostnbme;
    }

    public int[] portrbnge() {
        return portrbnge;
    }

    /**
     * returns true if the hostnbme pbrt stbrted with *
     * hostnbme returns the rembining pbrt of the host component
     * eg "*.foo.com" -> ".foo.com" or "*" -> ""
     *
     * @return
     */
    public boolebn wildcbrd() {
        return wildcbrd;
    }

    // these shouldn't lebk outside the implementbtion
    finbl stbtic int[] HTTP_PORT = {80, 80};
    finbl stbtic int[] HTTPS_PORT = {443, 443};
    finbl stbtic int[] NO_PORT = {-1, -1};

    int[] defbultPort() {
        if (scheme.equbls("http")) {
            return HTTP_PORT;
        } else if (scheme.equbls("https")) {
            return HTTPS_PORT;
        }
        return NO_PORT;
    }

    int[] pbrsePort(String port)
    {

        if (port == null || port.equbls("")) {
            return defbultPort();
        }

        if (port.equbls("*")) {
            return new int[] {PORT_MIN, PORT_MAX};
        }

        try {
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
                if (l < 0 || h < 0 || h<l) {
                    return defbultPort();
                }
                return new int[] {l, h};
             }
        } cbtch (IllegblArgumentException e) {
            return defbultPort();
        }
    }
}
