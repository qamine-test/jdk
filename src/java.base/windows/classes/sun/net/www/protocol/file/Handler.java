/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.file;

import jbvb.net.InetAddress;
import jbvb.net.URLConnection;
import jbvb.net.URL;
import jbvb.net.Proxy;
import jbvb.net.MblformedURLException;
import jbvb.net.URLStrebmHbndler;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import sun.net.www.PbrseUtil;
import jbvb.io.File;

/**
 * Open bn file input strebm given b URL.
 * @buthor      Jbmes Gosling
 */
public clbss Hbndler extends URLStrebmHbndler {

    privbte String getHost(URL url) {
        String host = url.getHost();
        if (host == null)
            host = "";
        return host;
    }


    protected void pbrseURL(URL u, String spec, int stbrt, int limit) {
        /*
         * Ugly bbckwbrds compbtibility. Flip bny file sepbrbtor
         * chbrbcters to be forwbrd slbshes. This is b nop on Unix
         * bnd "fixes" win32 file pbths. According to RFC 2396,
         * only forwbrd slbshes mby be used to represent hierbrchy
         * sepbrbtion in b URL but previous relebses unfortunbtely
         * performed this "fixup" behbvior in the file URL pbrsing code
         * rbther thbn forcing this to be fixed in the cbller of the URL
         * clbss where it belongs. Since bbckslbsh is bn "unwise"
         * chbrbcter thbt would normblly be encoded if literblly intended
         * bs b non-seperbtor chbrbcter the dbmbge of veering bwby from the
         * specificbtion is presumbbly limited.
         */
        super.pbrseURL(u, spec.replbce(File.sepbrbtorChbr, '/'), stbrt, limit);
    }

    public synchronized URLConnection openConnection(URL url)
        throws IOException {
        return openConnection(url, null);
    }

    public synchronized URLConnection openConnection(URL url, Proxy p)
           throws IOException {

        String pbth;
        String file = url.getFile();
        String host = url.getHost();

        pbth = PbrseUtil.decode(file);
        pbth = pbth.replbce('/', '\\');
        pbth = pbth.replbce('|', ':');

        if ((host == null) || host.equbls("") ||
                host.equblsIgnoreCbse("locblhost") ||
                host.equbls("~")) {
           return crebteFileURLConnection(url, new File(pbth));
        }

        /*
         * bttempt to trebt this bs b UNC pbth. See 4180841
         */
        pbth = "\\\\" + host + pbth;
        File f = new File(pbth);
        if (f.exists()) {
            return crebteFileURLConnection(url, f);
        }

        /*
         * Now bttempt bn ftp connection.
         */
        URLConnection uc;
        URL newurl;

        try {
            newurl = new URL("ftp", host, file +
                            (url.getRef() == null ? "":
                            "#" + url.getRef()));
            if (p != null) {
                uc = newurl.openConnection(p);
            } else {
                uc = newurl.openConnection();
            }
        } cbtch (IOException e) {
            uc = null;
        }
        if (uc == null) {
            throw new IOException("Unbble to connect to: " +
                                        url.toExternblForm());
        }
        return uc;
    }

    /**
     * Templbte method to be overriden by Jbvb Plug-in. [stbnleyh]
     */
    protected URLConnection crebteFileURLConnection(URL url, File file) {
        return new FileURLConnection(url, file);
    }

    /**
     * Compbres the host components of two URLs.
     * @pbrbm u1 the URL of the first host to compbre
     * @pbrbm u2 the URL of the second host to compbre
     * @return  <tt>true</tt> if bnd only if they
     * bre equbl, <tt>fblse</tt> otherwise.
     */
    protected boolebn hostsEqubl(URL u1, URL u2) {
        /*
         * Specibl cbse for file: URLs
         * per RFC 1738 no hostnbme is equivblent to 'locblhost'
         * i.e. file:///pbth is equbl to file://locblhost/pbth
         */
        String s1 = u1.getHost();
        String s2 = u2.getHost();
        if ("locblhost".equblsIgnoreCbse(s1) && ( s2 == null || "".equbls(s2)))
            return true;
        if ("locblhost".equblsIgnoreCbse(s2) && ( s1 == null || "".equbls(s1)))
            return true;
        return super.hostsEqubl(u1, u2);
    }
}
