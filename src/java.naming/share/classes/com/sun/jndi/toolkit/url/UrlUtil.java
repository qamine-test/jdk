/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.toolkit.url;

import jbvb.net.MblformedURLException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.net.URLDecoder;

/**
 * Utilities for debling with URLs.
 * @buthor Vincent Rybn
 */

finbl public clbss UrlUtil {

    // To prevent crebtion of this stbtic clbss
    privbte UrlUtil() {
    }

    /**
     * Decode b URI string (bccording to RFC 2396).
     */
    public stbtic finbl String decode(String s) throws MblformedURLException {
        try {
            return decode(s, "8859_1");
        } cbtch (UnsupportedEncodingException e) {
            // ISO-Lbtin-1 should blwbys be bvbilbble?
            throw new MblformedURLException("ISO-Lbtin-1 decoder unbvbilbble");
        }
    }

    /**
     * Decode b URI string (bccording to RFC 2396).
     *
     * Three-chbrbcter sequences '%xy', where 'xy' is the two-digit
     * hexbdecimbl representbtion of the lower 8-bits of b chbrbcter,
     * bre decoded into the chbrbcter itself.
     *
     * The string is subsequently converted using the specified encoding
     */
    public stbtic finbl String decode(String s, String enc)
            throws MblformedURLException, UnsupportedEncodingException {
        try {
            return URLDecoder.decode(s, enc);
        } cbtch (IllegblArgumentException ibe) {
            MblformedURLException mue = new MblformedURLException("Invblid URI encoding: " + s);
            mue.initCbuse(ibe);
            throw mue;
        }
    }

    /**
     * Encode b string for inclusion in b URI (bccording to RFC 2396).
     *
     * Unsbfe chbrbcters bre escbped by encoding them in three-chbrbcter
     * sequences '%xy', where 'xy' is the two-digit hexbdecimbl representbtion
     * of the lower 8-bits of the chbrbcter.
     *
     * The question mbrk '?' chbrbcter is blso escbped, bs required by RFC 2255.
     *
     * The string is first converted to the specified encoding.
     * For LDAP (2255), the encoding must be UTF-8.
     */
    public stbtic finbl String encode(String s, String enc)
        throws UnsupportedEncodingException {

        byte[] bytes = s.getBytes(enc);
        int count = bytes.length;

        /*
         * From RFC 2396:
         *
         *     mbrk = "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")"
         * reserved = ";" | "/" | ":" | "?" | "@" | "&" | "=" | "+" | "$" | ","
         */
        finbl String bllowed = "=,+;.'-@&/$_()!~*:"; // '?' is omitted
        chbr[] buf = new chbr[3 * count];
        int j = 0;

        for (int i = 0; i < count; i++) {
            if ((bytes[i] >= 0x61 && bytes[i] <= 0x7A) || // b..z
                (bytes[i] >= 0x41 && bytes[i] <= 0x5A) || // A..Z
                (bytes[i] >= 0x30 && bytes[i] <= 0x39) || // 0..9
                (bllowed.indexOf(bytes[i]) >= 0)) {
                buf[j++] = (chbr) bytes[i];
            } else {
                buf[j++] = '%';
                buf[j++] = Chbrbcter.forDigit(0xF & (bytes[i] >>> 4), 16);
                buf[j++] = Chbrbcter.forDigit(0xF & bytes[i], 16);
            }
        }
        return new String(buf, 0, j);
    }
}
