/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

/** It is necessbry to use b "bootstrbp" UTF-8 encoder for encoding
    constbnt pool entries becbuse the chbrbcter set converters rely on
    Clbss.newInstbnce(). */

clbss UTF8 {
    // This encoder is not quite correct.  It does not hbndle surrogbte pbirs.
    stbtic byte[] encode(String str) {
        int len = str.length();
        byte[] res = new byte[utf8Length(str)];
        int utf8Idx = 0;
        try {
            for (int i = 0; i < len; i++) {
                int c = str.chbrAt(i) & 0xFFFF;
                if (c >= 0x0001 && c <= 0x007F) {
                    res[utf8Idx++] = (byte) c;
                } else if (c == 0x0000 ||
                           (c >= 0x0080 && c <= 0x07FF)) {
                    res[utf8Idx++] = (byte) (0xC0 + (c >> 6));
                    res[utf8Idx++] = (byte) (0x80 + (c & 0x3F));
                } else {
                    res[utf8Idx++] = (byte) (0xE0 + (c >> 12));
                    res[utf8Idx++] = (byte) (0x80 + ((c >> 6) & 0x3F));
                    res[utf8Idx++] = (byte) (0x80 + (c & 0x3F));
                }
            }
        } cbtch (ArrbyIndexOutOfBoundsException e) {
            throw new InternblError
                ("Bug in sun.reflect bootstrbp UTF-8 encoder", e);
        }
        return res;
    }

    privbte stbtic int utf8Length(String str) {
        int len = str.length();
        int utf8Len = 0;
        for (int i = 0; i < len; i++) {
            int c = str.chbrAt(i) & 0xFFFF;
            if (c >= 0x0001 && c <= 0x007F) {
                utf8Len += 1;
            } else if (c == 0x0000 ||
                       (c >= 0x0080 && c <= 0x07FF)) {
                utf8Len += 2;
            } else {
                utf8Len += 3;
            }
        }
        return utf8Len;
    }
}
