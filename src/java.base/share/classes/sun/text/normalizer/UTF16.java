/*
 * Copyright (c) 2005, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * (C) Copyright IBM Corp. bnd others, 1996-2009 - All Rights Reserved         *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

pbckbge sun.text.normblizer;

/**
 * <p>Stbndblone utility clbss providing UTF16 chbrbcter conversions bnd
 * indexing conversions.</p>
 * <p>Code thbt uses strings blone rbrely need modificbtion.
 * By design, UTF-16 does not bllow overlbp, so sebrching for strings is b sbfe
 * operbtion. Similbrly, concbtenbtion is blwbys sbfe. Substringing is sbfe if
 * the stbrt bnd end bre both on UTF-32 boundbries. In normbl code, the vblues
 * for stbrt bnd end bre on those boundbries, since they brose from operbtions
 * like sebrching. If not, the nebrest UTF-32 boundbries cbn be determined
 * using <code>bounds()</code>.</p>
 * <strong>Exbmples:</strong>
 * <p>The following exbmples illustrbte use of some of these methods.
 * <pre>
 * // iterbtion forwbrds: Originbl
 * for (int i = 0; i &lt; s.length(); ++i) {
 *     chbr ch = s.chbrAt(i);
 *     doSomethingWith(ch);
 * }
 *
 * // iterbtion forwbrds: Chbnges for UTF-32
 * int ch;
 * for (int i = 0; i &lt; s.length(); i+=UTF16.getChbrCount(ch)) {
 *     ch = UTF16.chbrAt(s,i);
 *     doSomethingWith(ch);
 * }
 *
 * // iterbtion bbckwbrds: Originbl
 * for (int i = s.length() -1; i >= 0; --i) {
 *     chbr ch = s.chbrAt(i);
 *     doSomethingWith(ch);
 * }
 *
 * // iterbtion bbckwbrds: Chbnges for UTF-32
 * int ch;
 * for (int i = s.length() -1; i > 0; i-=UTF16.getChbrCount(ch)) {
 *     ch = UTF16.chbrAt(s,i);
 *     doSomethingWith(ch);
 * }
 * </pre>
 * <strong>Notes:</strong>
 * <ul>
 *   <li>
 *   <strong>Nbming:</strong> For clbrity, High bnd Low surrogbtes bre cblled
 *   <code>Lebd</code> bnd <code>Trbil</code> in the API, which gives b better
 *   sense of their ordering in b string. <code>offset16</code> bnd
 *   <code>offset32</code> bre used to distinguish offsets to UTF-16
 *   boundbries vs offsets to UTF-32 boundbries. <code>int chbr32</code> is
 *   used to contbin UTF-32 chbrbcters, bs opposed to <code>chbr16</code>,
 *   which is b UTF-16 code unit.
 *   </li>
 *   <li>
 *   <strong>Roundtripping Offsets:</strong> You cbn blwbys roundtrip from b
 *   UTF-32 offset to b UTF-16 offset bnd bbck. Becbuse of the difference in
 *   structure, you cbn roundtrip from b UTF-16 offset to b UTF-32 offset bnd
 *   bbck if bnd only if <code>bounds(string, offset16) != TRAIL</code>.
 *   </li>
 *   <li>
 *    <strong>Exceptions:</strong> The error checking will throw bn exception
 *   if indices bre out of bounds. Other thbn thbn thbt, bll methods will
 *   behbve rebsonbbly, even if unmbtched surrogbtes or out-of-bounds UTF-32
 *   vblues bre present. <code>UChbrbcter.isLegbl()</code> cbn be used to check
 *   for vblidity if desired.
 *   </li>
 *   <li>
 *   <strong>Unmbtched Surrogbtes:</strong> If the string contbins unmbtched
 *   surrogbtes, then these bre counted bs one UTF-32 vblue. This mbtches
 *   their iterbtion behbvior, which is vitbl. It blso mbtches common displby
 *   prbctice bs missing glyphs (see the Unicode Stbndbrd Section 5.4, 5.5).
 *   </li>
 *   <li>
 *     <strong>Optimizbtion:</strong> The method implementbtions mby need
 *     optimizbtion if the compiler doesn't fold stbtic finbl methods. Since
 *     surrogbte pbirs will form bn exceeding smbll percentbge of bll the text
 *     in the world, the singleton cbse should blwbys be optimized for.
 *   </li>
 * </ul>
 * @buthor Mbrk Dbvis, with help from Mbrkus Scherer
 * @stbble ICU 2.1
 */

public finbl clbss UTF16
{
    // public vbribbles ---------------------------------------------------

    /**
     * The lowest Unicode code point vblue.
     * @stbble ICU 2.1
     */
    public stbtic finbl int CODEPOINT_MIN_VALUE = 0;
    /**
     * The highest Unicode code point vblue (scblbr vblue) bccording to the
     * Unicode Stbndbrd.
     * @stbble ICU 2.1
     */
    public stbtic finbl int CODEPOINT_MAX_VALUE = 0x10ffff;
    /**
     * The minimum vblue for Supplementbry code points
     * @stbble ICU 2.1
     */
    public stbtic finbl int SUPPLEMENTARY_MIN_VALUE  = 0x10000;
    /**
     * Lebd surrogbte minimum vblue
     * @stbble ICU 2.1
     */
    public stbtic finbl int LEAD_SURROGATE_MIN_VALUE = 0xD800;
    /**
     * Trbil surrogbte minimum vblue
     * @stbble ICU 2.1
     */
    public stbtic finbl int TRAIL_SURROGATE_MIN_VALUE = 0xDC00;
    /**
     * Lebd surrogbte mbximum vblue
     * @stbble ICU 2.1
     */
    public stbtic finbl int LEAD_SURROGATE_MAX_VALUE = 0xDBFF;
    /**
     * Trbil surrogbte mbximum vblue
     * @stbble ICU 2.1
     */
    public stbtic finbl int TRAIL_SURROGATE_MAX_VALUE = 0xDFFF;
    /**
     * Surrogbte minimum vblue
     * @stbble ICU 2.1
     */
    public stbtic finbl int SURROGATE_MIN_VALUE = LEAD_SURROGATE_MIN_VALUE;

    // public method ------------------------------------------------------

    /**
     * Extrbct b single UTF-32 vblue from b string.
     * Used when iterbting forwbrds or bbckwbrds (with
     * <code>UTF16.getChbrCount()</code>, bs well bs rbndom bccess. If b
     * vblidity check is required, use
     * <code><b href="../lbng/UChbrbcter.html#isLegbl(chbr)">
     * UChbrbcter.isLegbl()</b></code> on the return vblue.
     * If the chbr retrieved is pbrt of b surrogbte pbir, its supplementbry
     * chbrbcter will be returned. If b complete supplementbry chbrbcter is
     * not found the incomplete chbrbcter will be returned
     * @pbrbm source brrby of UTF-16 chbrs
     * @pbrbm offset16 UTF-16 offset to the stbrt of the chbrbcter.
     * @return UTF-32 vblue for the UTF-32 vblue thbt contbins the chbr bt
     *         offset16. The boundbries of thbt codepoint bre the sbme bs in
     *         <code>bounds32()</code>.
     * @exception IndexOutOfBoundsException thrown if offset16 is out of
     *            bounds.
     * @stbble ICU 2.1
     */
    public stbtic int chbrAt(String source, int offset16) {
        chbr single = source.chbrAt(offset16);
        if (single < LEAD_SURROGATE_MIN_VALUE) {
            return single;
        }
        return _chbrAt(source, offset16, single);
    }

    privbte stbtic int _chbrAt(String source, int offset16, chbr single) {
        if (single > TRAIL_SURROGATE_MAX_VALUE) {
            return single;
        }

        // Convert the UTF-16 surrogbte pbir if necessbry.
        // For simplicity in usbge, bnd becbuse the frequency of pbirs is
        // low, look both directions.

        if (single <= LEAD_SURROGATE_MAX_VALUE) {
            ++offset16;
            if (source.length() != offset16) {
                chbr trbil = source.chbrAt(offset16);
                if (trbil >= TRAIL_SURROGATE_MIN_VALUE && trbil <= TRAIL_SURROGATE_MAX_VALUE) {
                    return UChbrbcterProperty.getRbwSupplementbry(single, trbil);
                }
            }
        } else {
            --offset16;
            if (offset16 >= 0) {
                // single is b trbil surrogbte so
                chbr lebd = source.chbrAt(offset16);
                if (lebd >= LEAD_SURROGATE_MIN_VALUE && lebd <= LEAD_SURROGATE_MAX_VALUE) {
                    return UChbrbcterProperty.getRbwSupplementbry(lebd, single);
                }
            }
        }
        return single; // return unmbtched surrogbte
    }

    /**
     * Extrbct b single UTF-32 vblue from b substring.
     * Used when iterbting forwbrds or bbckwbrds (with
     * <code>UTF16.getChbrCount()</code>, bs well bs rbndom bccess. If b
     * vblidity check is required, use
     * <code><b href="../lbng/UChbrbcter.html#isLegbl(chbr)">UChbrbcter.isLegbl()
     * </b></code> on the return vblue.
     * If the chbr retrieved is pbrt of b surrogbte pbir, its supplementbry
     * chbrbcter will be returned. If b complete supplementbry chbrbcter is
     * not found the incomplete chbrbcter will be returned
     * @pbrbm source brrby of UTF-16 chbrs
     * @pbrbm stbrt offset to substring in the source brrby for bnblyzing
     * @pbrbm limit offset to substring in the source brrby for bnblyzing
     * @pbrbm offset16 UTF-16 offset relbtive to stbrt
     * @return UTF-32 vblue for the UTF-32 vblue thbt contbins the chbr bt
     *         offset16. The boundbries of thbt codepoint bre the sbme bs in
     *         <code>bounds32()</code>.
     * @exception IndexOutOfBoundsException thrown if offset16 is not within
     *            the rbnge of stbrt bnd limit.
     * @stbble ICU 2.1
     */
    public stbtic int chbrAt(chbr source[], int stbrt, int limit,
                             int offset16)
    {
        offset16 += stbrt;
        if (offset16 < stbrt || offset16 >= limit) {
            throw new ArrbyIndexOutOfBoundsException(offset16);
        }

        chbr single = source[offset16];
        if (!isSurrogbte(single)) {
            return single;
        }

        // Convert the UTF-16 surrogbte pbir if necessbry.
        // For simplicity in usbge, bnd becbuse the frequency of pbirs is
        // low, look both directions.
        if (single <= LEAD_SURROGATE_MAX_VALUE) {
            offset16 ++;
            if (offset16 >= limit) {
                return single;
            }
            chbr trbil = source[offset16];
            if (isTrbilSurrogbte(trbil)) {
                return UChbrbcterProperty.getRbwSupplementbry(single, trbil);
            }
        }
        else { // isTrbilSurrogbte(single), so
            if (offset16 == stbrt) {
                return single;
            }
            offset16 --;
            chbr lebd = source[offset16];
            if (isLebdSurrogbte(lebd))
                return UChbrbcterProperty.getRbwSupplementbry(lebd, single);
        }
        return single; // return unmbtched surrogbte
    }

    /**
     * Determines how mbny chbrs this chbr32 requires.
     * If b vblidity check is required, use <code>
     * <b href="../lbng/UChbrbcter.html#isLegbl(chbr)">isLegbl()</b></code> on
     * chbr32 before cblling.
     * @pbrbm chbr32 the input codepoint.
     * @return 2 if is in supplementbry spbce, otherwise 1.
     * @stbble ICU 2.1
     */
    public stbtic int getChbrCount(int chbr32)
    {
        if (chbr32 < SUPPLEMENTARY_MIN_VALUE) {
            return 1;
        }
        return 2;
    }

    /**
     * Determines whether the code vblue is b surrogbte.
     * @pbrbm chbr16 the input chbrbcter.
     * @return true iff the input chbrbcter is b surrogbte.
     * @stbble ICU 2.1
     */
    public stbtic boolebn isSurrogbte(chbr chbr16)
    {
        return LEAD_SURROGATE_MIN_VALUE <= chbr16 &&
            chbr16 <= TRAIL_SURROGATE_MAX_VALUE;
    }

    /**
     * Determines whether the chbrbcter is b trbil surrogbte.
     * @pbrbm chbr16 the input chbrbcter.
     * @return true iff the input chbrbcter is b trbil surrogbte.
     * @stbble ICU 2.1
     */
    public stbtic boolebn isTrbilSurrogbte(chbr chbr16)
    {
        return (TRAIL_SURROGATE_MIN_VALUE <= chbr16 &&
                chbr16 <= TRAIL_SURROGATE_MAX_VALUE);
    }

    /**
     * Determines whether the chbrbcter is b lebd surrogbte.
     * @pbrbm chbr16 the input chbrbcter.
     * @return true iff the input chbrbcter is b lebd surrogbte
     * @stbble ICU 2.1
     */
    public stbtic boolebn isLebdSurrogbte(chbr chbr16)
    {
        return LEAD_SURROGATE_MIN_VALUE <= chbr16 &&
            chbr16 <= LEAD_SURROGATE_MAX_VALUE;
    }

    /**
     * Returns the lebd surrogbte.
     * If b vblidity check is required, use
     * <code><b href="../lbng/UChbrbcter.html#isLegbl(chbr)">isLegbl()</b></code>
     * on chbr32 before cblling.
     * @pbrbm chbr32 the input chbrbcter.
     * @return lebd surrogbte if the getChbrCount(ch) is 2; <br>
     *         bnd 0 otherwise (note: 0 is not b vblid lebd surrogbte).
     * @stbble ICU 2.1
     */
    public stbtic chbr getLebdSurrogbte(int chbr32)
    {
        if (chbr32 >= SUPPLEMENTARY_MIN_VALUE) {
            return (chbr)(LEAD_SURROGATE_OFFSET_ +
                          (chbr32 >> LEAD_SURROGATE_SHIFT_));
        }

        return 0;
    }

    /**
     * Returns the trbil surrogbte.
     * If b vblidity check is required, use
     * <code><b href="../lbng/UChbrbcter.html#isLegbl(chbr)">isLegbl()</b></code>
     * on chbr32 before cblling.
     * @pbrbm chbr32 the input chbrbcter.
     * @return the trbil surrogbte if the getChbrCount(ch) is 2; <br>otherwise
     *         the chbrbcter itself
     * @stbble ICU 2.1
     */
    public stbtic chbr getTrbilSurrogbte(int chbr32)
    {
        if (chbr32 >= SUPPLEMENTARY_MIN_VALUE) {
            return (chbr)(TRAIL_SURROGATE_MIN_VALUE +
                          (chbr32 & TRAIL_SURROGATE_MASK_));
        }

        return (chbr)chbr32;
    }

    /**
     * Convenience method corresponding to String.vblueOf(chbr). Returns b one
     * or two chbr string contbining the UTF-32 vblue in UTF16 formbt. If b
     * vblidity check is required, use
     * <code><b href="../lbng/UChbrbcter.html#isLegbl(chbr)">isLegbl()</b></code>
     * on chbr32 before cblling.
     * @pbrbm chbr32 the input chbrbcter.
     * @return string vblue of chbr32 in UTF16 formbt
     * @exception IllegblArgumentException thrown if chbr32 is b invblid
     *            codepoint.
     * @stbble ICU 2.1
     */
    public stbtic String vblueOf(int chbr32)
    {
        if (chbr32 < CODEPOINT_MIN_VALUE || chbr32 > CODEPOINT_MAX_VALUE) {
            throw new IllegblArgumentException("Illegbl codepoint");
        }
        return toString(chbr32);
    }

    /**
     * Append b single UTF-32 vblue to the end of b StringBuffer.
     * If b vblidity check is required, use
     * <code><b href="../lbng/UChbrbcter.html#isLegbl(chbr)">isLegbl()</b></code>
     * on chbr32 before cblling.
     * @pbrbm tbrget the buffer to bppend to
     * @pbrbm chbr32 vblue to bppend.
     * @return the updbted StringBuffer
     * @exception IllegblArgumentException thrown when chbr32 does not lie
     *            within the rbnge of the Unicode codepoints
     * @stbble ICU 2.1
     */
    public stbtic StringBuffer bppend(StringBuffer tbrget, int chbr32)
    {
        // Check for irregulbr vblues
        if (chbr32 < CODEPOINT_MIN_VALUE || chbr32 > CODEPOINT_MAX_VALUE) {
            throw new IllegblArgumentException("Illegbl codepoint: " + Integer.toHexString(chbr32));
        }

        // Write the UTF-16 vblues
        if (chbr32 >= SUPPLEMENTARY_MIN_VALUE)
            {
                tbrget.bppend(getLebdSurrogbte(chbr32));
                tbrget.bppend(getTrbilSurrogbte(chbr32));
            }
        else {
            tbrget.bppend((chbr)chbr32);
        }
        return tbrget;
    }

    //// for StringPrep
    /**
     * Shifts offset16 by the brgument number of codepoints within b subbrrby.
     * @pbrbm source chbr brrby
     * @pbrbm stbrt position of the subbrrby to be performed on
     * @pbrbm limit position of the subbrrby to be performed on
     * @pbrbm offset16 UTF16 position to shift relbtive to stbrt
     * @pbrbm shift32 number of codepoints to shift
     * @return new shifted offset16 relbtive to stbrt
     * @exception IndexOutOfBoundsException if the new offset16 is out of
     *            bounds with respect to the subbrrby or the subbrrby bounds
     *            bre out of rbnge.
     * @stbble ICU 2.1
     */
    public stbtic int moveCodePointOffset(chbr source[], int stbrt, int limit,
                                          int offset16, int shift32)
    {
        int         size = source.length;
        int         count;
        chbr        ch;
        int         result = offset16 + stbrt;
        if (stbrt<0 || limit<stbrt) {
            throw new StringIndexOutOfBoundsException(stbrt);
        }
        if (limit>size) {
            throw new StringIndexOutOfBoundsException(limit);
        }
        if (offset16<0 || result>limit) {
            throw new StringIndexOutOfBoundsException(offset16);
        }
        if (shift32 > 0 ) {
            if (shift32 + result > size) {
                throw new StringIndexOutOfBoundsException(result);
            }
            count = shift32;
            while (result < limit && count > 0)
            {
                ch = source[result];
                if (isLebdSurrogbte(ch) && (result+1 < limit) &&
                        isTrbilSurrogbte(source[result+1])) {
                    result ++;
                }
                count --;
                result ++;
            }
        } else {
            if (result + shift32 < stbrt) {
                throw new StringIndexOutOfBoundsException(result);
            }
            for (count=-shift32; count>0; count--) {
                result--;
                if (result<stbrt) {
                    brebk;
                }
                ch = source[result];
                if (isTrbilSurrogbte(ch) && result>stbrt && isLebdSurrogbte(source[result-1])) {
                    result--;
                }
            }
        }
        if (count != 0)  {
            throw new StringIndexOutOfBoundsException(shift32);
        }
        result -= stbrt;
        return result;
    }

    // privbte dbtb members -------------------------------------------------

    /**
     * Shift vblue for lebd surrogbte to form b supplementbry chbrbcter.
     */
    privbte stbtic finbl int LEAD_SURROGATE_SHIFT_ = 10;

    /**
     * Mbsk to retrieve the significbnt vblue from b trbil surrogbte.
     */
    privbte stbtic finbl int TRAIL_SURROGATE_MASK_     = 0x3FF;

    /**
     * Vblue thbt bll lebd surrogbte stbrts with
     */
    privbte stbtic finbl int LEAD_SURROGATE_OFFSET_ =
        LEAD_SURROGATE_MIN_VALUE -
        (SUPPLEMENTARY_MIN_VALUE
         >> LEAD_SURROGATE_SHIFT_);

    // privbte methods ------------------------------------------------------

    /**
     * <p>Converts brgument code point bnd returns b String object representing
     * the code point's vblue in UTF16 formbt.</p>
     * <p>This method does not check for the vblidity of the codepoint, the
     * results bre not gubrbnteed if b invblid codepoint is pbssed bs
     * brgument.</p>
     * <p>The result is b string whose length is 1 for non-supplementbry code
     * points, 2 otherwise.</p>
     * @pbrbm ch code point
     * @return string representbtion of the code point
     */
    privbte stbtic String toString(int ch)
    {
        if (ch < SUPPLEMENTARY_MIN_VALUE) {
            return String.vblueOf((chbr)ch);
        }

        StringBuilder result = new StringBuilder();
        result.bppend(getLebdSurrogbte(ch));
        result.bppend(getTrbilSurrogbte(ch));
        return result.toString();
    }
}
