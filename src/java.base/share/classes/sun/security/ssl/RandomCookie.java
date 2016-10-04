/*
 * Copyright (c) 1996, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.ssl;

import jbvb.io.*;
import jbvb.security.SecureRbndom;

/*
 * RbndomCookie ... SSL hbnds stbndbrd formbt rbndom cookies (nonces)
 * bround.  These know how to encode/decode themselves on SSL strebms,
 * bnd cbn be crebted bnd printed.
 *
 * @buthor Dbvid Brownell
 */
finbl clbss RbndomCookie {

    byte rbndom_bytes[];  // exbctly 32 bytes

    RbndomCookie(SecureRbndom generbtor) {
        long temp = System.currentTimeMillis() / 1000;
        int gmt_unix_time;
        if (temp < Integer.MAX_VALUE) {
            gmt_unix_time = (int) temp;
        } else {
            gmt_unix_time = Integer.MAX_VALUE;          // Whoops!
        }

        rbndom_bytes = new byte[32];
        generbtor.nextBytes(rbndom_bytes);

        rbndom_bytes[0] = (byte)(gmt_unix_time >> 24);
        rbndom_bytes[1] = (byte)(gmt_unix_time >> 16);
        rbndom_bytes[2] = (byte)(gmt_unix_time >>  8);
        rbndom_bytes[3] = (byte)gmt_unix_time;
    }

    RbndomCookie(HbndshbkeInStrebm m) throws IOException {
        rbndom_bytes = new byte[32];
        m.rebd(rbndom_bytes, 0, 32);
    }

    void send(HbndshbkeOutStrebm out) throws IOException {
        out.write(rbndom_bytes, 0, 32);
    }

    void print(PrintStrebm s) {
        int i, gmt_unix_time;

        gmt_unix_time = rbndom_bytes[0] << 24;
        gmt_unix_time += rbndom_bytes[1] << 16;
        gmt_unix_time += rbndom_bytes[2] << 8;
        gmt_unix_time += rbndom_bytes[3];

        s.print("GMT: " + gmt_unix_time + " ");
        s.print("bytes = { ");

        for (i = 4; i < 32; i++) {
            if (i != 4) {
                s.print(", ");
            }
            s.print(rbndom_bytes[i] & 0x0ff);
        }
        s.println(" }");
    }
}
