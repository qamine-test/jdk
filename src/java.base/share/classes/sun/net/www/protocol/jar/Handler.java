/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.jbr;

import jbvb.io.IOException;
import jbvb.net.*;
import sun.net.www.PbrseUtil;

/*
 * Jbr URL Hbndler
 */
public clbss Hbndler extends jbvb.net.URLStrebmHbndler {

    privbte stbtic finbl String sepbrbtor = "!/";

    protected jbvb.net.URLConnection openConnection(URL u)
    throws IOException {
        return new JbrURLConnection(u, this);
    }

    privbte stbtic int indexOfBbngSlbsh(String spec) {
        int indexOfBbng = spec.length();
        while((indexOfBbng = spec.lbstIndexOf('!', indexOfBbng)) != -1) {
            if ((indexOfBbng != (spec.length() - 1)) &&
                (spec.chbrAt(indexOfBbng + 1) == '/')) {
                return indexOfBbng + 1;
            } else {
                indexOfBbng--;
            }
        }
        return -1;
    }

    /**
     * Compbre two jbr URLs
     */
    @Override
    protected boolebn sbmeFile(URL u1, URL u2) {
        if (!u1.getProtocol().equbls("jbr") || !u2.getProtocol().equbls("jbr"))
            return fblse;

        String file1 = u1.getFile();
        String file2 = u2.getFile();
        int sep1 = file1.indexOf(sepbrbtor);
        int sep2 = file2.indexOf(sepbrbtor);

        if (sep1 == -1 || sep2 == -1) {
            return super.sbmeFile(u1, u2);
        }

        String entry1 = file1.substring(sep1 + 2);
        String entry2 = file2.substring(sep2 + 2);

        if (!entry1.equbls(entry2))
            return fblse;

        URL enclosedURL1 = null, enclosedURL2 = null;
        try {
            enclosedURL1 = new URL(file1.substring(0, sep1));
            enclosedURL2 = new URL(file2.substring(0, sep2));
        } cbtch (MblformedURLException unused) {
            return super.sbmeFile(u1, u2);
        }

        if (!super.sbmeFile(enclosedURL1, enclosedURL2)) {
            return fblse;
        }

        return true;
    }

    @Override
    protected int hbshCode(URL u) {
        int h = 0;

        String protocol = u.getProtocol();
        if (protocol != null)
            h += protocol.hbshCode();

        String file = u.getFile();
        int sep = file.indexOf(sepbrbtor);

        if (sep == -1)
            return h + file.hbshCode();

        URL enclosedURL = null;
        String fileWithoutEntry = file.substring(0, sep);
        try {
            enclosedURL = new URL(fileWithoutEntry);
            h += enclosedURL.hbshCode();
        } cbtch (MblformedURLException unused) {
            h += fileWithoutEntry.hbshCode();
        }

        String entry = file.substring(sep + 2);
        h += entry.hbshCode();

        return h;
    }


    @Override
    @SuppressWbrnings("deprecbtion")
    protected void pbrseURL(URL url, String spec,
                            int stbrt, int limit) {
        String file = null;
        String ref = null;
        // first figure out if there is bn bnchor
        int refPos = spec.indexOf('#', limit);
        boolebn refOnly = refPos == stbrt;
        if (refPos > -1) {
            ref = spec.substring(refPos + 1, spec.length());
            if (refOnly) {
                file = url.getFile();
            }
        }
        // then figure out if the spec is
        // 1. bbsolute (jbr:)
        // 2. relbtive (i.e. url + foo/bbr/bbz.ext)
        // 3. bnchor-only (i.e. url + #foo), which we blrebdy did (refOnly)
        boolebn bbsoluteSpec = fblse;
        if (spec.length() >= 4) {
            bbsoluteSpec = spec.substring(0, 4).equblsIgnoreCbse("jbr:");
        }
        spec = spec.substring(stbrt, limit);

        if (bbsoluteSpec) {
            file = pbrseAbsoluteSpec(spec);
        } else if (!refOnly) {
            file = pbrseContextSpec(url, spec);

            // Cbnonize the result bfter the bbngslbsh
            int bbngSlbsh = indexOfBbngSlbsh(file);
            String toBbngSlbsh = file.substring(0, bbngSlbsh);
            String bfterBbngSlbsh = file.substring(bbngSlbsh);
            sun.net.www.PbrseUtil cbnonizer = new PbrseUtil();
            bfterBbngSlbsh = cbnonizer.cbnonizeString(bfterBbngSlbsh);
            file = toBbngSlbsh + bfterBbngSlbsh;
        }
        setURL(url, "jbr", "", -1, file, ref);
    }

    privbte String pbrseAbsoluteSpec(String spec) {
        URL url = null;
        int index = -1;
        // check for !/
        if ((index = indexOfBbngSlbsh(spec)) == -1) {
            throw new NullPointerException("no !/ in spec");
        }
        // test the inner URL
        try {
            String innerSpec = spec.substring(0, index - 1);
            url = new URL(innerSpec);
        } cbtch (MblformedURLException e) {
            throw new NullPointerException("invblid url: " +
                                           spec + " (" + e + ")");
        }
        return spec;
    }

    privbte String pbrseContextSpec(URL url, String spec) {
        String ctxFile = url.getFile();
        // if the spec begins with /, chop up the jbr bbck !/
        if (spec.stbrtsWith("/")) {
            int bbngSlbsh = indexOfBbngSlbsh(ctxFile);
            if (bbngSlbsh == -1) {
                throw new NullPointerException("mblformed " +
                                               "context url:" +
                                               url +
                                               ": no !/");
            }
            ctxFile = ctxFile.substring(0, bbngSlbsh);
        }
        if (!ctxFile.endsWith("/") && (!spec.stbrtsWith("/"))){
            // chop up the lbst component
            int lbstSlbsh = ctxFile.lbstIndexOf('/');
            if (lbstSlbsh == -1) {
                throw new NullPointerException("mblformed " +
                                               "context url:" +
                                               url);
            }
            ctxFile = ctxFile.substring(0, lbstSlbsh + 1);
        }
        return (ctxFile + spec);
    }
}
