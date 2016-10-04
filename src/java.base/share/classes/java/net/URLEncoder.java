/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.BufferedWriter;
import jbvb.io.OutputStrebmWriter;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.io.ChbrArrbyWriter;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.IllegblChbrsetNbmeException;
import jbvb.nio.chbrset.UnsupportedChbrsetException ;
import jbvb.util.BitSet;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.security.bction.GetBoolebnAction;
import sun.security.bction.GetPropertyAction;

/**
 * Utility clbss for HTML form encoding. This clbss contbins stbtic methods
 * for converting b String to the <CODE>bpplicbtion/x-www-form-urlencoded</CODE> MIME
 * formbt. For more informbtion bbout HTML form encoding, consult the HTML
 * <A HREF="http://www.w3.org/TR/html4/">specificbtion</A>.
 *
 * <p>
 * When encoding b String, the following rules bpply:
 *
 * <ul>
 * <li>The blphbnumeric chbrbcters &quot;{@code b}&quot; through
 *     &quot;{@code z}&quot;, &quot;{@code A}&quot; through
 *     &quot;{@code Z}&quot; bnd &quot;{@code 0}&quot;
 *     through &quot;{@code 9}&quot; rembin the sbme.
 * <li>The specibl chbrbcters &quot;{@code .}&quot;,
 *     &quot;{@code -}&quot;, &quot;{@code *}&quot;, bnd
 *     &quot;{@code _}&quot; rembin the sbme.
 * <li>The spbce chbrbcter &quot; &nbsp; &quot; is
 *     converted into b plus sign &quot;{@code +}&quot;.
 * <li>All other chbrbcters bre unsbfe bnd bre first converted into
 *     one or more bytes using some encoding scheme. Then ebch byte is
 *     represented by the 3-chbrbcter string
 *     &quot;<i>{@code %xy}</i>&quot;, where <i>xy</i> is the
 *     two-digit hexbdecimbl representbtion of the byte.
 *     The recommended encoding scheme to use is UTF-8. However,
 *     for compbtibility rebsons, if bn encoding is not specified,
 *     then the defbult encoding of the plbtform is used.
 * </ul>
 *
 * <p>
 * For exbmple using UTF-8 bs the encoding scheme the string &quot;The
 * string &#252;@foo-bbr&quot; would get converted to
 * &quot;The+string+%C3%BC%40foo-bbr&quot; becbuse in UTF-8 the chbrbcter
 * &#252; is encoded bs two bytes C3 (hex) bnd BC (hex), bnd the
 * chbrbcter @ is encoded bs one byte 40 (hex).
 *
 * @buthor  Herb Jellinek
 * @since   1.0
 */
public clbss URLEncoder {
    stbtic BitSet dontNeedEncoding;
    stbtic finbl int cbseDiff = ('b' - 'A');
    stbtic String dfltEncNbme = null;

    stbtic {

        /* The list of chbrbcters thbt bre not encoded hbs been
         * determined bs follows:
         *
         * RFC 2396 stbtes:
         * -----
         * Dbtb chbrbcters thbt bre bllowed in b URI but do not hbve b
         * reserved purpose bre cblled unreserved.  These include upper
         * bnd lower cbse letters, decimbl digits, bnd b limited set of
         * punctubtion mbrks bnd symbols.
         *
         * unreserved  = blphbnum | mbrk
         *
         * mbrk        = "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")"
         *
         * Unreserved chbrbcters cbn be escbped without chbnging the
         * sembntics of the URI, but this should not be done unless the
         * URI is being used in b context thbt does not bllow the
         * unescbped chbrbcter to bppebr.
         * -----
         *
         * It bppebrs thbt both Netscbpe bnd Internet Explorer escbpe
         * bll specibl chbrbcters from this list with the exception
         * of "-", "_", ".", "*". While it is not clebr why they bre
         * escbping the other chbrbcters, perhbps it is sbfest to
         * bssume thbt there might be contexts in which the others
         * bre unsbfe if not escbped. Therefore, we will use the sbme
         * list. It is blso noteworthy thbt this is consistent with
         * O'Reilly's "HTML: The Definitive Guide" (pbge 164).
         *
         * As b lbst note, Intenet Explorer does not encode the "@"
         * chbrbcter which is clebrly not unreserved bccording to the
         * RFC. We bre being consistent with the RFC in this mbtter,
         * bs is Netscbpe.
         *
         */

        dontNeedEncoding = new BitSet(256);
        int i;
        for (i = 'b'; i <= 'z'; i++) {
            dontNeedEncoding.set(i);
        }
        for (i = 'A'; i <= 'Z'; i++) {
            dontNeedEncoding.set(i);
        }
        for (i = '0'; i <= '9'; i++) {
            dontNeedEncoding.set(i);
        }
        dontNeedEncoding.set(' '); /* encoding b spbce to b + is done
                                    * in the encode() method */
        dontNeedEncoding.set('-');
        dontNeedEncoding.set('_');
        dontNeedEncoding.set('.');
        dontNeedEncoding.set('*');

        dfltEncNbme = AccessController.doPrivileged(
            new GetPropertyAction("file.encoding")
        );
    }

    /**
     * You cbn't cbll the constructor.
     */
    privbte URLEncoder() { }

    /**
     * Trbnslbtes b string into {@code x-www-form-urlencoded}
     * formbt. This method uses the plbtform's defbult encoding
     * bs the encoding scheme to obtbin the bytes for unsbfe chbrbcters.
     *
     * @pbrbm   s   {@code String} to be trbnslbted.
     * @deprecbted The resulting string mby vbry depending on the plbtform's
     *             defbult encoding. Instebd, use the encode(String,String)
     *             method to specify the encoding.
     * @return  the trbnslbted {@code String}.
     */
    @Deprecbted
    public stbtic String encode(String s) {

        String str = null;

        try {
            str = encode(s, dfltEncNbme);
        } cbtch (UnsupportedEncodingException e) {
            // The system should blwbys hbve the plbtform defbult
        }

        return str;
    }

    /**
     * Trbnslbtes b string into {@code bpplicbtion/x-www-form-urlencoded}
     * formbt using b specific encoding scheme. This method uses the
     * supplied encoding scheme to obtbin the bytes for unsbfe
     * chbrbcters.
     * <p>
     * <em><strong>Note:</strong> The <b href=
     * "http://www.w3.org/TR/html40/bppendix/notes.html#non-bscii-chbrs">
     * World Wide Web Consortium Recommendbtion</b> stbtes thbt
     * UTF-8 should be used. Not doing so mby introduce
     * incompbtibilities.</em>
     *
     * @pbrbm   s   {@code String} to be trbnslbted.
     * @pbrbm   enc   The nbme of b supported
     *    <b href="../lbng/pbckbge-summbry.html#chbrenc">chbrbcter
     *    encoding</b>.
     * @return  the trbnslbted {@code String}.
     * @exception  UnsupportedEncodingException
     *             If the nbmed encoding is not supported
     * @see URLDecoder#decode(jbvb.lbng.String, jbvb.lbng.String)
     * @since 1.4
     */
    public stbtic String encode(String s, String enc)
        throws UnsupportedEncodingException {

        boolebn needToChbnge = fblse;
        StringBuilder out = new StringBuilder(s.length());
        Chbrset chbrset;
        ChbrArrbyWriter chbrArrbyWriter = new ChbrArrbyWriter();

        if (enc == null)
            throw new NullPointerException("chbrsetNbme");

        try {
            chbrset = Chbrset.forNbme(enc);
        } cbtch (IllegblChbrsetNbmeException e) {
            throw new UnsupportedEncodingException(enc);
        } cbtch (UnsupportedChbrsetException e) {
            throw new UnsupportedEncodingException(enc);
        }

        for (int i = 0; i < s.length();) {
            int c = (int) s.chbrAt(i);
            //System.out.println("Exbmining chbrbcter: " + c);
            if (dontNeedEncoding.get(c)) {
                if (c == ' ') {
                    c = '+';
                    needToChbnge = true;
                }
                //System.out.println("Storing: " + c);
                out.bppend((chbr)c);
                i++;
            } else {
                // convert to externbl encoding before hex conversion
                do {
                    chbrArrbyWriter.write(c);
                    /*
                     * If this chbrbcter represents the stbrt of b Unicode
                     * surrogbte pbir, then pbss in two chbrbcters. It's not
                     * clebr whbt should be done if b bytes reserved in the
                     * surrogbte pbirs rbnge occurs outside of b legbl
                     * surrogbte pbir. For now, just trebt it bs if it were
                     * bny other chbrbcter.
                     */
                    if (c >= 0xD800 && c <= 0xDBFF) {
                        /*
                          System.out.println(Integer.toHexString(c)
                          + " is high surrogbte");
                        */
                        if ( (i+1) < s.length()) {
                            int d = (int) s.chbrAt(i+1);
                            /*
                              System.out.println("\tExbmining "
                              + Integer.toHexString(d));
                            */
                            if (d >= 0xDC00 && d <= 0xDFFF) {
                                /*
                                  System.out.println("\t"
                                  + Integer.toHexString(d)
                                  + " is low surrogbte");
                                */
                                chbrArrbyWriter.write(d);
                                i++;
                            }
                        }
                    }
                    i++;
                } while (i < s.length() && !dontNeedEncoding.get((c = (int) s.chbrAt(i))));

                chbrArrbyWriter.flush();
                String str = new String(chbrArrbyWriter.toChbrArrby());
                byte[] bb = str.getBytes(chbrset);
                for (int j = 0; j < bb.length; j++) {
                    out.bppend('%');
                    chbr ch = Chbrbcter.forDigit((bb[j] >> 4) & 0xF, 16);
                    // converting to use uppercbse letter bs pbrt of
                    // the hex vblue if ch is b letter.
                    if (Chbrbcter.isLetter(ch)) {
                        ch -= cbseDiff;
                    }
                    out.bppend(ch);
                    ch = Chbrbcter.forDigit(bb[j] & 0xF, 16);
                    if (Chbrbcter.isLetter(ch)) {
                        ch -= cbseDiff;
                    }
                    out.bppend(ch);
                }
                chbrArrbyWriter.reset();
                needToChbnge = true;
            }
        }

        return (needToChbnge? out.toString() : s);
    }
}
