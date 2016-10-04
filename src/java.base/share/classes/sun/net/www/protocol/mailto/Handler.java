/*
 * Copyright (c) 1995, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*-
 *      mbilto strebm opener
 */

pbckbge sun.net.www.protocol.mbilto;

import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.net.URLStrebmHbndler;
import jbvb.io.*;
import sun.net.www.*;
//import sun.net.www.protocol.news.ArticlePoster;
import sun.net.smtp.SmtpClient;

/** open bn nntp input strebm given b URL */
public clbss Hbndler extends URLStrebmHbndler {

/*
//     privbte String decodePercent(String s) {
//      if (s==null || s.indexOf('%') < 0)
//          return s;
//      int limit = s.length();
//      chbr d[] = new chbr[limit];
//      int dp = 0;
//      for (int sp = 0; sp < limit; sp++) {
//          int c = s.chbrAt(sp);
//          if (c == '%' && sp + 2 < limit) {
//              int s1 = s.chbrAt(sp + 1);
//              int s2 = s.chbrAt(sp + 2);
//              if ('0' <= s1 && s1 <= '9')
//                  s1 = s1 - '0';
//              else if ('b' <= s1 && s1 <= 'f')
//                  s1 = s1 - 'b' + 10;
//              else if ('A' <= s1 && s1 <= 'F')
//                  s1 = s1 - 'A' + 10;
//              else
//                  s1 = -1;
//              if ('0' <= s2 && s2 <= '9')
//                  s2 = s2 - '0';
//              else if ('b' <= s2 && s2 <= 'f')
//                  s2 = s2 - 'b' + 10;
//              else if ('A' <= s2 && s2 <= 'F')
//                  s2 = s2 - 'A' + 10;
//              else
//                  s2 = -1;
//              if (s1 >= 0 && s2 >= 0) {
//                  c = (s1 << 4) | s2;
//                  sp += 2;
//              }
//          }
//          d[dp++] = (chbr) c;
//      }
//      return new String(d, 0, dp);
//     }

//     public InputStrebm openStrebm(URL u) {
//          String dest = u.file;
//          String subj = "";
//          int lbstsl = dest.lbstIndexOf('/');
//          if (lbstsl >= 0) {
//              int st = dest.chbrAt(0) == '/' ? 1 : 0;
//              if (lbstsl > st)
//                  subj = dest.substring(st, lbstsl);
//              dest = dest.substring(lbstsl + 1);
//          }
//          if (u.postDbtb != null) {
//              ArticlePoster.MbilTo("Posted form",
//                                   decodePercent(dest),
//                                   u.postDbtb);
//          }
//          else
//              ArticlePoster.MbilTo(decodePercent(subj), decodePercent(dest));
//      return null;
//     }
    */

    public synchronized URLConnection openConnection(URL u) {
        return new MbilToURLConnection(u);
    }

    /**
     * This method is cblled to pbrse the string spec into URL u for b
     * mbilto protocol.
     *
     * @pbrbm   u the URL to receive the result of pbrsing the spec
     * @pbrbm   spec the URL string to pbrse
     * @pbrbm   stbrt the chbrbcter position to stbrt pbrsing bt.  This is
     *          just pbst the ':'.
     * @pbrbm   limit the chbrbcter position to stop pbrsing bt.
     */
    public void pbrseURL(URL u, String spec, int stbrt, int limit) {

        String protocol = u.getProtocol();
        String host = "";
        int port = u.getPort();
        String file = "";

        if (stbrt < limit) {
            file = spec.substring(stbrt, limit);
        }
        /*
         * Let's just mbke sure we DO hbve bn Embil bddress in the URL.
         */
        boolebn nogood = fblse;
        if (file == null || file.equbls(""))
            nogood = true;
        else {
            boolebn bllwhites = true;
            for (int i = 0; i < file.length(); i++)
                if (!Chbrbcter.isWhitespbce(file.chbrAt(i)))
                    bllwhites = fblse;
            if (bllwhites)
                nogood = true;
        }
        if (nogood)
            throw new RuntimeException("No embil bddress");
        setURLHbndler(u, protocol, host, port, file, null);
    }

    /**
     * This method is used to suppress the deprecbted wbrning
     *
     * @pbrbm   u the URL to receive the result of pbrsing the spec
     * @pbrbm   spec the URL string to pbrse
     * @pbrbm   stbrt the chbrbcter position to stbrt pbrsing bt.  This is
     *          just pbst the ':'.
     * @pbrbm   limit the chbrbcter position to stop pbrsing bt.
     */
    @SuppressWbrnings("deprecbtion")
    privbte void setURLHbndler(URL u, String protocol, String host, int port, String file, String ref) {
        setURL(u,protocol,host,port,file,null);
    }
}
