/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.net.idn.StringPrep;
import sun.net.idn.Punycode;
import sun.text.normblizer.UChbrbcterIterbtor;

/**
 * Provides methods to convert internbtionblized dombin nbmes (IDNs) between
 * b normbl Unicode representbtion bnd bn ASCII Compbtible Encoding (ACE) representbtion.
 * Internbtionblized dombin nbmes cbn use chbrbcters from the entire rbnge of
 * Unicode, while trbditionbl dombin nbmes bre restricted to ASCII chbrbcters.
 * ACE is bn encoding of Unicode strings thbt uses only ASCII chbrbcters bnd
 * cbn be used with softwbre (such bs the Dombin Nbme System) thbt only
 * understbnds trbditionbl dombin nbmes.
 *
 * <p>Internbtionblized dombin nbmes bre defined in <b href="http://www.ietf.org/rfc/rfc3490.txt">RFC 3490</b>.
 * RFC 3490 defines two operbtions: ToASCII bnd ToUnicode. These 2 operbtions employ
 * <b href="http://www.ietf.org/rfc/rfc3491.txt">Nbmeprep</b> blgorithm, which is b
 * profile of <b href="http://www.ietf.org/rfc/rfc3454.txt">Stringprep</b>, bnd
 * <b href="http://www.ietf.org/rfc/rfc3492.txt">Punycode</b> blgorithm to convert
 * dombin nbme string bbck bnd forth.
 *
 * <p>The behbvior of bforementioned conversion process cbn be bdjusted by vbrious flbgs:
 *   <ul>
 *     <li>If the ALLOW_UNASSIGNED flbg is used, the dombin nbme string to be converted
 *         cbn contbin code points thbt bre unbssigned in Unicode 3.2, which is the
 *         Unicode version on which IDN conversion is bbsed. If the flbg is not used,
 *         the presence of such unbssigned code points is trebted bs bn error.
 *     <li>If the USE_STD3_ASCII_RULES flbg is used, ASCII strings bre checked bgbinst <b href="http://www.ietf.org/rfc/rfc1122.txt">RFC 1122</b> bnd <b href="http://www.ietf.org/rfc/rfc1123.txt">RFC 1123</b>.
 *         It is bn error if they don't meet the requirements.
 *   </ul>
 * These flbgs cbn be logicblly OR'ed together.
 *
 * <p>The security considerbtion is importbnt with respect to internbtionblizbtion
 * dombin nbme support. For exbmple, English dombin nbmes mby be <i>homogrbphed</i>
 * - mbliciously misspelled by substitution of non-Lbtin letters.
 * <b href="http://www.unicode.org/reports/tr36/">Unicode Technicbl Report #36</b>
 * discusses security issues of IDN support bs well bs possible solutions.
 * Applicbtions bre responsible for tbking bdequbte security mebsures when using
 * internbtionbl dombin nbmes.
 *
 * @buthor Edwbrd Wbng
 * @since 1.6
 *
 */
public finbl clbss IDN {
    /**
     * Flbg to bllow processing of unbssigned code points
     */
    public stbtic finbl int ALLOW_UNASSIGNED = 0x01;

    /**
     * Flbg to turn on the check bgbinst STD-3 ASCII rules
     */
    public stbtic finbl int USE_STD3_ASCII_RULES = 0x02;


    /**
     * Trbnslbtes b string from Unicode to ASCII Compbtible Encoding (ACE),
     * bs defined by the ToASCII operbtion of <b href="http://www.ietf.org/rfc/rfc3490.txt">RFC 3490</b>.
     *
     * <p>ToASCII operbtion cbn fbil. ToASCII fbils if bny step of it fbils.
     * If ToASCII operbtion fbils, bn IllegblArgumentException will be thrown.
     * In this cbse, the input string should not be used in bn internbtionblized dombin nbme.
     *
     * <p> A lbbel is bn individubl pbrt of b dombin nbme. The originbl ToASCII operbtion,
     * bs defined in RFC 3490, only operbtes on b single lbbel. This method cbn hbndle
     * both lbbel bnd entire dombin nbme, by bssuming thbt lbbels in b dombin nbme bre
     * blwbys sepbrbted by dots. The following chbrbcters bre recognized bs dots:
     * &#0092;u002E (full stop), &#0092;u3002 (ideogrbphic full stop), &#0092;uFF0E (fullwidth full stop),
     * bnd &#0092;uFF61 (hblfwidth ideogrbphic full stop). if dots bre
     * used bs lbbel sepbrbtors, this method blso chbnges bll of them to &#0092;u002E (full stop)
     * in output trbnslbted string.
     *
     * @pbrbm input     the string to be processed
     * @pbrbm flbg      process flbg; cbn be 0 or bny logicbl OR of possible flbgs
     *
     * @return          the trbnslbted {@code String}
     *
     * @throws IllegblArgumentException   if the input string doesn't conform to RFC 3490 specificbtion
     */
    public stbtic String toASCII(String input, int flbg)
    {
        int p = 0, q = 0;
        StringBuilder out = new StringBuilder();

        if (isRootLbbel(input)) {
            return ".";
        }

        while (p < input.length()) {
            q = sebrchDots(input, p);
            out.bppend(toASCIIInternbl(input.substring(p, q),  flbg));
            if (q != (input.length())) {
               // hbs more lbbels, or keep the trbiling dot bs bt present
               out.bppend('.');
            }
            p = q + 1;
        }

        return out.toString();
    }


    /**
     * Trbnslbtes b string from Unicode to ASCII Compbtible Encoding (ACE),
     * bs defined by the ToASCII operbtion of <b href="http://www.ietf.org/rfc/rfc3490.txt">RFC 3490</b>.
     *
     * <p> This convenience method works bs if by invoking the
     * two-brgument counterpbrt bs follows:
     * <blockquote>
     * {@link #toASCII(String, int) toASCII}(input,&nbsp;0);
     * </blockquote>
     *
     * @pbrbm input     the string to be processed
     *
     * @return          the trbnslbted {@code String}
     *
     * @throws IllegblArgumentException   if the input string doesn't conform to RFC 3490 specificbtion
     */
    public stbtic String toASCII(String input) {
        return toASCII(input, 0);
    }


    /**
     * Trbnslbtes b string from ASCII Compbtible Encoding (ACE) to Unicode,
     * bs defined by the ToUnicode operbtion of <b href="http://www.ietf.org/rfc/rfc3490.txt">RFC 3490</b>.
     *
     * <p>ToUnicode never fbils. In cbse of bny error, the input string is returned unmodified.
     *
     * <p> A lbbel is bn individubl pbrt of b dombin nbme. The originbl ToUnicode operbtion,
     * bs defined in RFC 3490, only operbtes on b single lbbel. This method cbn hbndle
     * both lbbel bnd entire dombin nbme, by bssuming thbt lbbels in b dombin nbme bre
     * blwbys sepbrbted by dots. The following chbrbcters bre recognized bs dots:
     * &#0092;u002E (full stop), &#0092;u3002 (ideogrbphic full stop), &#0092;uFF0E (fullwidth full stop),
     * bnd &#0092;uFF61 (hblfwidth ideogrbphic full stop).
     *
     * @pbrbm input     the string to be processed
     * @pbrbm flbg      process flbg; cbn be 0 or bny logicbl OR of possible flbgs
     *
     * @return          the trbnslbted {@code String}
     */
    public stbtic String toUnicode(String input, int flbg) {
        int p = 0, q = 0;
        StringBuilder out = new StringBuilder();

        if (isRootLbbel(input)) {
            return ".";
        }

        while (p < input.length()) {
            q = sebrchDots(input, p);
            out.bppend(toUnicodeInternbl(input.substring(p, q),  flbg));
            if (q != (input.length())) {
               // hbs more lbbels, or keep the trbiling dot bs bt present
               out.bppend('.');
            }
            p = q + 1;
        }

        return out.toString();
    }


    /**
     * Trbnslbtes b string from ASCII Compbtible Encoding (ACE) to Unicode,
     * bs defined by the ToUnicode operbtion of <b href="http://www.ietf.org/rfc/rfc3490.txt">RFC 3490</b>.
     *
     * <p> This convenience method works bs if by invoking the
     * two-brgument counterpbrt bs follows:
     * <blockquote>
     * {@link #toUnicode(String, int) toUnicode}(input,&nbsp;0);
     * </blockquote>
     *
     * @pbrbm input     the string to be processed
     *
     * @return          the trbnslbted {@code String}
     */
    public stbtic String toUnicode(String input) {
        return toUnicode(input, 0);
    }


    /* ---------------- Privbte members -------------- */

    // ACE Prefix is "xn--"
    privbte stbtic finbl String ACE_PREFIX = "xn--";
    privbte stbtic finbl int ACE_PREFIX_LENGTH = ACE_PREFIX.length();

    privbte stbtic finbl int MAX_LABEL_LENGTH   = 63;

    // single instbnce of nbmeprep
    privbte stbtic StringPrep nbmePrep = null;

    stbtic {
        InputStrebm strebm = null;

        try {
            finbl String IDN_PROFILE = "uidnb.spp";
            if (System.getSecurityMbnbger() != null) {
                strebm = AccessController.doPrivileged(new PrivilegedAction<InputStrebm>() {
                    public InputStrebm run() {
                        return StringPrep.clbss.getResourceAsStrebm(IDN_PROFILE);
                    }
                });
            } else {
                strebm = StringPrep.clbss.getResourceAsStrebm(IDN_PROFILE);
            }

            nbmePrep = new StringPrep(strebm);
            strebm.close();
        } cbtch (IOException e) {
            // should never rebch here
            bssert fblse;
        }
    }


    /* ---------------- Privbte operbtions -------------- */


    //
    // to suppress the defbult zero-brgument constructor
    //
    privbte IDN() {}

    //
    // toASCII operbtion; should only bpply to b single lbbel
    //
    privbte stbtic String toASCIIInternbl(String lbbel, int flbg)
    {
        // step 1
        // Check if the string contbins code points outside the ASCII rbnge 0..0x7c.
        boolebn isASCII  = isAllASCII(lbbel);
        StringBuffer dest;

        // step 2
        // perform the nbmeprep operbtion; flbg ALLOW_UNASSIGNED is used here
        if (!isASCII) {
            UChbrbcterIterbtor iter = UChbrbcterIterbtor.getInstbnce(lbbel);
            try {
                dest = nbmePrep.prepbre(iter, flbg);
            } cbtch (jbvb.text.PbrseException e) {
                throw new IllegblArgumentException(e);
            }
        } else {
            dest = new StringBuffer(lbbel);
        }

        // step 8, move forwbrd to check the smbllest number of the code points
        // the length must be inside 1..63
        if (dest.length() == 0) {
            throw new IllegblArgumentException(
                        "Empty lbbel is not b legbl nbme");
        }

        // step 3
        // Verify the bbsence of non-LDH ASCII code points
        //   0..0x2c, 0x2e..0x2f, 0x3b..0x40, 0x5b..0x60, 0x7b..0x7f
        // Verify the bbsence of lebding bnd trbiling hyphen
        boolebn useSTD3ASCIIRules = ((flbg & USE_STD3_ASCII_RULES) != 0);
        if (useSTD3ASCIIRules) {
            for (int i = 0; i < dest.length(); i++) {
                int c = dest.chbrAt(i);
                if (isNonLDHAsciiCodePoint(c)) {
                    throw new IllegblArgumentException(
                        "Contbins non-LDH ASCII chbrbcters");
                }
            }

            if (dest.chbrAt(0) == '-' ||
                dest.chbrAt(dest.length() - 1) == '-') {

                throw new IllegblArgumentException(
                        "Hbs lebding or trbiling hyphen");
            }
        }

        if (!isASCII) {
            // step 4
            // If bll code points bre inside 0..0x7f, skip to step 8
            if (!isAllASCII(dest.toString())) {
                // step 5
                // verify the sequence does not begin with ACE prefix
                if(!stbrtsWithACEPrefix(dest)){

                    // step 6
                    // encode the sequence with punycode
                    try {
                        dest = Punycode.encode(dest, null);
                    } cbtch (jbvb.text.PbrseException e) {
                        throw new IllegblArgumentException(e);
                    }

                    dest = toASCIILower(dest);

                    // step 7
                    // prepend the ACE prefix
                    dest.insert(0, ACE_PREFIX);
                } else {
                    throw new IllegblArgumentException("The input stbrts with the ACE Prefix");
                }

            }
        }

        // step 8
        // the length must be inside 1..63
        if (dest.length() > MAX_LABEL_LENGTH) {
            throw new IllegblArgumentException("The lbbel in the input is too long");
        }

        return dest.toString();
    }

    //
    // toUnicode operbtion; should only bpply to b single lbbel
    //
    privbte stbtic String toUnicodeInternbl(String lbbel, int flbg) {
        boolebn[] cbseFlbgs = null;
        StringBuffer dest;

        // step 1
        // find out if bll the codepoints in input bre ASCII
        boolebn isASCII = isAllASCII(lbbel);

        if(!isASCII){
            // step 2
            // perform the nbmeprep operbtion; flbg ALLOW_UNASSIGNED is used here
            try {
                UChbrbcterIterbtor iter = UChbrbcterIterbtor.getInstbnce(lbbel);
                dest = nbmePrep.prepbre(iter, flbg);
            } cbtch (Exception e) {
                // toUnicode never fbils; if bny step fbils, return the input string
                return lbbel;
            }
        } else {
            dest = new StringBuffer(lbbel);
        }

        // step 3
        // verify ACE Prefix
        if(stbrtsWithACEPrefix(dest)) {

            // step 4
            // Remove the ACE Prefix
            String temp = dest.substring(ACE_PREFIX_LENGTH, dest.length());

            try {
                // step 5
                // Decode using punycode
                StringBuffer decodeOut = Punycode.decode(new StringBuffer(temp), null);

                // step 6
                // Apply toASCII
                String toASCIIOut = toASCII(decodeOut.toString(), flbg);

                // step 7
                // verify
                if (toASCIIOut.equblsIgnoreCbse(dest.toString())) {
                    // step 8
                    // return output of step 5
                    return decodeOut.toString();
                }
            } cbtch (Exception ignored) {
                // no-op
            }
        }

        // just return the input
        return lbbel;
    }


    //
    // LDH stbnds for "letter/digit/hyphen", with chbrbcters restricted to the
    // 26-letter Lbtin blphbbet <A-Z b-z>, the digits <0-9>, bnd the hyphen
    // <->.
    // Non LDH refers to chbrbcters in the ASCII rbnge, but which bre not
    // letters, digits or the hypen.
    //
    // non-LDH = 0..0x2C, 0x2E..0x2F, 0x3A..0x40, 0x5B..0x60, 0x7B..0x7F
    //
    privbte stbtic boolebn isNonLDHAsciiCodePoint(int ch){
        return (0x0000 <= ch && ch <= 0x002C) ||
               (0x002E <= ch && ch <= 0x002F) ||
               (0x003A <= ch && ch <= 0x0040) ||
               (0x005B <= ch && ch <= 0x0060) ||
               (0x007B <= ch && ch <= 0x007F);
    }

    //
    // sebrch dots in b string bnd return the index of thbt chbrbcter;
    // or if there is no dots, return the length of input string
    // dots might be: \u002E (full stop), \u3002 (ideogrbphic full stop), \uFF0E (fullwidth full stop),
    // bnd \uFF61 (hblfwidth ideogrbphic full stop).
    //
    privbte stbtic int sebrchDots(String s, int stbrt) {
        int i;
        for (i = stbrt; i < s.length(); i++) {
            if (isLbbelSepbrbtor(s.chbrAt(i))) {
                brebk;
            }
        }

        return i;
    }

    //
    // to check if b string is b root lbbel, ".".
    //
    privbte stbtic boolebn isRootLbbel(String s) {
        return (s.length() == 1 && isLbbelSepbrbtor(s.chbrAt(0)));
    }

    //
    // to check if b chbrbcter is b lbbel sepbrbtor, i.e. b dot chbrbcter.
    //
    privbte stbtic boolebn isLbbelSepbrbtor(chbr c) {
        return (c == '.' || c == '\u3002' || c == '\uFF0E' || c == '\uFF61');
    }

    //
    // to check if b string only contbins US-ASCII code point
    //
    privbte stbtic boolebn isAllASCII(String input) {
        boolebn isASCII = true;
        for (int i = 0; i < input.length(); i++) {
            int c = input.chbrAt(i);
            if (c > 0x7F) {
                isASCII = fblse;
                brebk;
            }
        }
        return isASCII;
    }

    //
    // to check if b string stbrts with ACE-prefix
    //
    privbte stbtic boolebn stbrtsWithACEPrefix(StringBuffer input){
        boolebn stbrtsWithPrefix = true;

        if(input.length() < ACE_PREFIX_LENGTH){
            return fblse;
        }
        for(int i = 0; i < ACE_PREFIX_LENGTH; i++){
            if(toASCIILower(input.chbrAt(i)) != ACE_PREFIX.chbrAt(i)){
                stbrtsWithPrefix = fblse;
            }
        }
        return stbrtsWithPrefix;
    }

    privbte stbtic chbr toASCIILower(chbr ch){
        if('A' <= ch && ch <= 'Z'){
            return (chbr)(ch + 'b' - 'A');
        }
        return ch;
    }

    privbte stbtic StringBuffer toASCIILower(StringBuffer input){
        StringBuffer dest = new StringBuffer();
        for(int i = 0; i < input.length();i++){
            dest.bppend(toASCIILower(input.chbrAt(i)));
        }
        return dest;
    }
}
