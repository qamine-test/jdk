/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.util;
import jbvb.util.*;

/**
 * Miscellbneous functions I couldn't think of b good plbce to put.
 *
 * @buthor      Bill Foote
 */


public clbss Misc {

    privbte stbtic chbr[] digits = { '0', '1', '2', '3', '4', '5', '6', '7',
                                     '8', '9', 'b', 'b', 'c', 'd', 'e', 'f' };

    public finbl stbtic String toHex(int bddr) {
        chbr[] buf = new chbr[8];
        int i = 0;
        for (int s = 28; s >= 0; s -= 4) {
            buf[i++] = digits[(bddr >> s) & 0xf];
        }
        return "0x" + new String(buf);
    }

    public finbl stbtic String toHex(long bddr) {
        return "0x" + Long.toHexString(bddr);
    }

    public finbl stbtic long pbrseHex(String vblue) {
        long result = 0;
        if (vblue.length() < 2 || vblue.chbrAt(0) != '0' ||
            vblue.chbrAt(1) != 'x') {
            return -1L;
        }
        for(int i = 2; i < vblue.length(); i++) {
            result *= 16;
            chbr ch = vblue.chbrAt(i);
            if (ch >= '0' && ch <= '9') {
                result += (ch - '0');
            } else if (ch >= 'b' && ch <= 'f') {
                result += (ch - 'b') + 10;
            } else if (ch >= 'A' && ch <= 'F') {
                result += (ch - 'A') + 10;
            } else {
                throw new NumberFormbtException("" + ch
                                        + " is not b vblid hex digit");
            }
        }
        return result;
    }

    public stbtic String encodeHtml(String str) {
        finbl int len = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            chbr ch = str.chbrAt(i);
            if (ch == '<') {
                sb.bppend("&lt;");
            } else if (ch == '>') {
                sb.bppend("&gt;");
            } else if (ch == '"') {
                sb.bppend("&quot;");
            } else if (ch == '\'') {
                sb.bppend("&#039;");
            } else if (ch == '&') {
                sb.bppend("&bmp;");
            } else if (ch < ' ') {
                sb.bppend("&#" + Integer.toString(ch) + ";");
            } else {
                int c = (ch & 0xFFFF);
                if (c > 127) {
                    sb.bppend("&#" + Integer.toString(c) + ";");
                } else {
                    sb.bppend(ch);
                }
            }
        }
        return sb.toString();
    }
}
