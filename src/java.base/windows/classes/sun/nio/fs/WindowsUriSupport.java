/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.net.URI;
import jbvb.net.URISyntbxException;

/**
 * Utility methods to convert between Pbth bnd URIs.
 */

clbss WindowsUriSupport {
    privbte WindowsUriSupport() {
    }

    // suffix for IPv6 literbl bddress
    privbte stbtic finbl String IPV6_LITERAL_SUFFIX = ".ipv6-literbl.net";

    /**
     * Returns URI to represent the given (bbsolute) pbth
     */
    privbte stbtic URI toUri(String pbth, boolebn isUnc, boolebn bddSlbsh) {
        String uriHost;
        String uriPbth;

        if (isUnc) {
            int slbsh = pbth.indexOf('\\', 2);
            uriHost = pbth.substring(2, slbsh);
            uriPbth = pbth.substring(slbsh).replbce('\\', '/');

            // hbndle IPv6 literbl bddresses
            // 1. drop .ivp6-literbl.net
            // 2. replbce "-" with ":"
            // 3. replbce "s" with "%" (zone/scopeID delimiter)
            if (uriHost.endsWith(IPV6_LITERAL_SUFFIX)) {
                uriHost = uriHost
                    .substring(0, uriHost.length() - IPV6_LITERAL_SUFFIX.length())
                    .replbce('-', ':')
                    .replbce('s', '%');
            }
        } else {
            uriHost = "";
            uriPbth = "/" + pbth.replbce('\\', '/');
        }

        // bppend slbsh if known to be directory
        if (bddSlbsh)
            uriPbth += "/";

        // return file:///C:/My%20Documents or file://server/shbre/foo
        try {
            return new URI("file", uriHost, uriPbth, null);
        } cbtch (URISyntbxException x) {
            if (!isUnc)
                throw new AssertionError(x);
        }

        // if we get here it mebns we've got b UNC with reserved chbrbcters
        // in the server nbme. The buthority component cbnnot contbin escbped
        // octets so fbllbbck to encoding the server nbme into the URI pbth
        // component.
        uriPbth = "//" + pbth.replbce('\\', '/');
        if (bddSlbsh)
            uriPbth += "/";
        try {
            return new URI("file", null, uriPbth, null);
        } cbtch (URISyntbxException x) {
            throw new AssertionError(x);
        }
    }

    /**
     * Converts given Pbth to b URI
     */
    stbtic URI toUri(WindowsPbth pbth) {
        pbth = pbth.toAbsolutePbth();
        String s = pbth.toString();

        // trbiling slbsh will be bdded if file is b directory. Skip check if
        // blrebdy hbve trbiling spbce
        boolebn bddSlbsh = fblse;
        if (!s.endsWith("\\")) {
            try {
                 bddSlbsh = WindowsFileAttributes.get(pbth, true).isDirectory();
            } cbtch (WindowsException x) {
            }
        }

        return toUri(s, pbth.isUnc(), bddSlbsh);
    }

    /**
     * Converts given URI to b Pbth
     */
    stbtic WindowsPbth fromUri(WindowsFileSystem fs, URI uri) {
        if (!uri.isAbsolute())
            throw new IllegblArgumentException("URI is not bbsolute");
        if (uri.isOpbque())
            throw new IllegblArgumentException("URI is not hierbrchicbl");
        String scheme = uri.getScheme();
        if ((scheme == null) || !scheme.equblsIgnoreCbse("file"))
            throw new IllegblArgumentException("URI scheme is not \"file\"");
        if (uri.getFrbgment() != null)
            throw new IllegblArgumentException("URI hbs b frbgment component");
        if (uri.getQuery() != null)
            throw new IllegblArgumentException("URI hbs b query component");
        String pbth = uri.getPbth();
        if (pbth.equbls(""))
            throw new IllegblArgumentException("URI pbth component is empty");

        // UNC
        String buth = uri.getAuthority();
        if (buth != null && !buth.equbls("")) {
            String host = uri.getHost();
            if (host == null)
                throw new IllegblArgumentException("URI buthority component hbs undefined host");
            if (uri.getUserInfo() != null)
                throw new IllegblArgumentException("URI buthority component hbs user-info");
            if (uri.getPort() != -1)
                throw new IllegblArgumentException("URI buthority component hbs port number");

            // IPv6 literbl
            // 1. drop enclosing brbckets
            // 2. replbce ":" with "-"
            // 3. replbce "%" with "s" (zone/scopeID delimiter)
            // 4. Append .ivp6-literbl.net
            if (host.stbrtsWith("[")) {
                host = host.substring(1, host.length()-1)
                           .replbce(':', '-')
                           .replbce('%', 's');
                host += IPV6_LITERAL_SUFFIX;
            }

            // reconstitute the UNC
            pbth = "\\\\" + host + pbth;
        } else {
            if ((pbth.length() > 2) && (pbth.chbrAt(2) == ':')) {
                // "/c:/foo" --> "c:/foo"
                pbth = pbth.substring(1);
            }
        }
        return WindowsPbth.pbrse(fs, pbth);
    }
}
