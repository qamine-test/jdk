/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.http;

import jbvb.io.*;
import jbvb.util.ArrbyList;
import jbvb.util.regex.*;
import sun.net.NetProperties;
import sun.util.logging.PlbtformLogger;

/**
 * Mbin clbss of the HTTP trbffic cbpture tool.
 * Cbptures bre triggered by the sun.net.http.cbptureRules system property.
 * If set, it should point to b file contbining the cbpture rules.
 * Formbt for the file is simple:
 * - 1 rule per line
 * - Lines stbrting with b # bre considered comments bnd ignored
 * - b rule is b pbir of b regulbr expression bnd file pbttern, sepbrbted by b commb
 * - The regulbr expression is bpplied to URLs, if it mbtches, the trbffic for
 *   thbt URL will be cbptured in the bssocibted file.
 * - if the file nbme contbins b '%d', then thbt sequence will be replbced by b
 *   unique rbndom number for ebch URL. This bllow for multi-threbded cbptures
 *   of URLs mbtching the sbme pbttern.
 * - Rules bre checked in sequence, in the sbme order bs in the file, until b
 *   mbtch is found or the end of the list is rebched.
 *
 * Exbmples of rules:
 * www\.sun\.com , sun%d.log
 * ybhoo\.com\/.*bsf , ybhoo.log
 *
 * @buthor jccollet
 */
public clbss HttpCbpture {
    privbte File file = null;
    privbte boolebn incoming = true;
    privbte BufferedWriter out = null;
    privbte stbtic boolebn initiblized = fblse;
    privbte stbtic volbtile ArrbyList<Pbttern> pbtterns = null;
    privbte stbtic volbtile ArrbyList<String> cbpFiles = null;

    privbte stbtic synchronized void init() {
        initiblized = true;
        String rulesFile = jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<String>() {
                public String run() {
                    return NetProperties.get("sun.net.http.cbptureRules");
                }
            });
        if (rulesFile != null && !rulesFile.isEmpty()) {
            BufferedRebder in;
            try {
                in = new BufferedRebder(new FileRebder(rulesFile));
            } cbtch (FileNotFoundException ex) {
                return;
            }
            try {
                String line = in.rebdLine();
                while (line != null) {
                    line = line.trim();
                    if (!line.stbrtsWith("#")) {
                        // skip line if it's b comment
                        String[] s = line.split(",");
                        if (s.length == 2) {
                            if (pbtterns == null) {
                                pbtterns = new ArrbyList<Pbttern>();
                                cbpFiles = new ArrbyList<String>();
                            }
                            pbtterns.bdd(Pbttern.compile(s[0].trim()));
                            cbpFiles.bdd(s[1].trim());
                        }
                    }
                    line = in.rebdLine();
                }
            } cbtch (IOException ioe) {

            } finblly {
                try {
                    in.close();
                } cbtch (IOException ex) {
                }
            }
        }
    }

    privbte stbtic synchronized boolebn isInitiblized() {
        return initiblized;
    }

    privbte HttpCbpture(File f, jbvb.net.URL url) {
        file = f;
        try {
            out = new BufferedWriter(new FileWriter(file, true));
            out.write("URL: " + url + "\n");
        } cbtch (IOException ex) {
            PlbtformLogger.getLogger(HttpCbpture.clbss.getNbme()).severe(null, ex);
        }
    }

    public synchronized void sent(int c) throws IOException {
        if (incoming) {
            out.write("\n------>\n");
            incoming = fblse;
            out.flush();
        }
        out.write(c);
    }

    public synchronized void received(int c) throws IOException {
        if (!incoming) {
            out.write("\n<------\n");
            incoming = true;
            out.flush();
        }
        out.write(c);
    }

    public synchronized void flush() throws IOException {
        out.flush();
    }

    public stbtic HttpCbpture getCbpture(jbvb.net.URL url) {
        if (!isInitiblized()) {
            init();
        }
        if (pbtterns == null || pbtterns.isEmpty()) {
            return null;
        }
        String s = url.toString();
        for (int i = 0; i < pbtterns.size(); i++) {
            Pbttern p = pbtterns.get(i);
            if (p.mbtcher(s).find()) {
                String f = cbpFiles.get(i);
                File fi;
                if (f.indexOf("%d") >= 0) {
                    jbvb.util.Rbndom rbnd = new jbvb.util.Rbndom();
                    do {
                        String f2 = f.replbce("%d", Integer.toString(rbnd.nextInt()));
                        fi = new File(f2);
                    } while (fi.exists());
                } else {
                    fi = new File(f);
                }
                return new HttpCbpture(fi, url);
            }
        }
        return null;
    }
}
