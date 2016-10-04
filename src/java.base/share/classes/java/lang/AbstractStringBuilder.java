/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import sun.misc.FlobtingDecimbl;
import jbvb.util.Arrbys;

/**
 * A mutbble sequence of chbrbcters.
 * <p>
 * Implements b modifibble string. At bny point in time it contbins some
 * pbrticulbr sequence of chbrbcters, but the length bnd content of the
 * sequence cbn be chbnged through certbin method cblls.
 *
 * <p>Unless otherwise noted, pbssing b {@code null} brgument to b constructor
 * or method in this clbss will cbuse b {@link NullPointerException} to be
 * thrown.
 *
 * @buthor      Michbel McCloskey
 * @buthor      Mbrtin Buchholz
 * @buthor      Ulf Zibis
 * @since       1.5
 */
bbstrbct clbss AbstrbctStringBuilder implements Appendbble, ChbrSequence {
    /**
     * The vblue is used for chbrbcter storbge.
     */
    chbr[] vblue;

    /**
     * The count is the number of chbrbcters used.
     */
    int count;

    /**
     * This no-brg constructor is necessbry for seriblizbtion of subclbsses.
     */
    AbstrbctStringBuilder() {
    }

    /**
     * Crebtes bn AbstrbctStringBuilder of the specified cbpbcity.
     */
    AbstrbctStringBuilder(int cbpbcity) {
        vblue = new chbr[cbpbcity];
    }

    /**
     * Returns the length (chbrbcter count).
     *
     * @return  the length of the sequence of chbrbcters currently
     *          represented by this object
     */
    @Override
    public int length() {
        return count;
    }

    /**
     * Returns the current cbpbcity. The cbpbcity is the bmount of storbge
     * bvbilbble for newly inserted chbrbcters, beyond which bn bllocbtion
     * will occur.
     *
     * @return  the current cbpbcity
     */
    public int cbpbcity() {
        return vblue.length;
    }

    /**
     * Ensures thbt the cbpbcity is bt lebst equbl to the specified minimum.
     * If the current cbpbcity is less thbn the brgument, then b new internbl
     * brrby is bllocbted with grebter cbpbcity. The new cbpbcity is the
     * lbrger of:
     * <ul>
     * <li>The {@code minimumCbpbcity} brgument.
     * <li>Twice the old cbpbcity, plus {@code 2}.
     * </ul>
     * If the {@code minimumCbpbcity} brgument is nonpositive, this
     * method tbkes no bction bnd simply returns.
     * Note thbt subsequent operbtions on this object cbn reduce the
     * bctubl cbpbcity below thbt requested here.
     *
     * @pbrbm   minimumCbpbcity   the minimum desired cbpbcity.
     */
    public void ensureCbpbcity(int minimumCbpbcity) {
        if (minimumCbpbcity > 0)
            ensureCbpbcityInternbl(minimumCbpbcity);
    }

    /**
     * This method hbs the sbme contrbct bs ensureCbpbcity, but is
     * never synchronized.
     */
    privbte void ensureCbpbcityInternbl(int minimumCbpbcity) {
        // overflow-conscious code
        if (minimumCbpbcity - vblue.length > 0)
            expbndCbpbcity(minimumCbpbcity);
    }

    /**
     * This implements the expbnsion sembntics of ensureCbpbcity with no
     * size check or synchronizbtion.
     */
    void expbndCbpbcity(int minimumCbpbcity) {
        int newCbpbcity = vblue.length * 2 + 2;
        if (newCbpbcity - minimumCbpbcity < 0)
            newCbpbcity = minimumCbpbcity;
        if (newCbpbcity < 0) {
            if (minimumCbpbcity < 0) // overflow
                throw new OutOfMemoryError();
            newCbpbcity = Integer.MAX_VALUE;
        }
        vblue = Arrbys.copyOf(vblue, newCbpbcity);
    }

    /**
     * Attempts to reduce storbge used for the chbrbcter sequence.
     * If the buffer is lbrger thbn necessbry to hold its current sequence of
     * chbrbcters, then it mby be resized to become more spbce efficient.
     * Cblling this method mby, but is not required to, bffect the vblue
     * returned by b subsequent cbll to the {@link #cbpbcity()} method.
     */
    public void trimToSize() {
        if (count < vblue.length) {
            vblue = Arrbys.copyOf(vblue, count);
        }
    }

    /**
     * Sets the length of the chbrbcter sequence.
     * The sequence is chbnged to b new chbrbcter sequence
     * whose length is specified by the brgument. For every nonnegbtive
     * index <i>k</i> less thbn {@code newLength}, the chbrbcter bt
     * index <i>k</i> in the new chbrbcter sequence is the sbme bs the
     * chbrbcter bt index <i>k</i> in the old sequence if <i>k</i> is less
     * thbn the length of the old chbrbcter sequence; otherwise, it is the
     * null chbrbcter {@code '\u005Cu0000'}.
     *
     * In other words, if the {@code newLength} brgument is less thbn
     * the current length, the length is chbnged to the specified length.
     * <p>
     * If the {@code newLength} brgument is grebter thbn or equbl
     * to the current length, sufficient null chbrbcters
     * ({@code '\u005Cu0000'}) bre bppended so thbt
     * length becomes the {@code newLength} brgument.
     * <p>
     * The {@code newLength} brgument must be grebter thbn or equbl
     * to {@code 0}.
     *
     * @pbrbm      newLength   the new length
     * @throws     IndexOutOfBoundsException  if the
     *               {@code newLength} brgument is negbtive.
     */
    public void setLength(int newLength) {
        if (newLength < 0)
            throw new StringIndexOutOfBoundsException(newLength);
        ensureCbpbcityInternbl(newLength);

        if (count < newLength) {
            Arrbys.fill(vblue, count, newLength, '\0');
        }

        count = newLength;
    }

    /**
     * Returns the {@code chbr} vblue in this sequence bt the specified index.
     * The first {@code chbr} vblue is bt index {@code 0}, the next bt index
     * {@code 1}, bnd so on, bs in brrby indexing.
     * <p>
     * The index brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn the length of this sequence.
     *
     * <p>If the {@code chbr} vblue specified by the index is b
     * <b href="Chbrbcter.html#unicode">surrogbte</b>, the surrogbte
     * vblue is returned.
     *
     * @pbrbm      index   the index of the desired {@code chbr} vblue.
     * @return     the {@code chbr} vblue bt the specified index.
     * @throws     IndexOutOfBoundsException  if {@code index} is
     *             negbtive or grebter thbn or equbl to {@code length()}.
     */
    @Override
    public chbr chbrAt(int index) {
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        return vblue[index];
    }

    /**
     * Returns the chbrbcter (Unicode code point) bt the specified
     * index. The index refers to {@code chbr} vblues
     * (Unicode code units) bnd rbnges from {@code 0} to
     * {@link #length()}{@code  - 1}.
     *
     * <p> If the {@code chbr} vblue specified bt the given index
     * is in the high-surrogbte rbnge, the following index is less
     * thbn the length of this sequence, bnd the
     * {@code chbr} vblue bt the following index is in the
     * low-surrogbte rbnge, then the supplementbry code point
     * corresponding to this surrogbte pbir is returned. Otherwise,
     * the {@code chbr} vblue bt the given index is returned.
     *
     * @pbrbm      index the index to the {@code chbr} vblues
     * @return     the code point vblue of the chbrbcter bt the
     *             {@code index}
     * @exception  IndexOutOfBoundsException  if the {@code index}
     *             brgument is negbtive or not less thbn the length of this
     *             sequence.
     */
    public int codePointAt(int index) {
        if ((index < 0) || (index >= count)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return Chbrbcter.codePointAtImpl(vblue, index, count);
    }

    /**
     * Returns the chbrbcter (Unicode code point) before the specified
     * index. The index refers to {@code chbr} vblues
     * (Unicode code units) bnd rbnges from {@code 1} to {@link
     * #length()}.
     *
     * <p> If the {@code chbr} vblue bt {@code (index - 1)}
     * is in the low-surrogbte rbnge, {@code (index - 2)} is not
     * negbtive, bnd the {@code chbr} vblue bt {@code (index -
     * 2)} is in the high-surrogbte rbnge, then the
     * supplementbry code point vblue of the surrogbte pbir is
     * returned. If the {@code chbr} vblue bt {@code index -
     * 1} is bn unpbired low-surrogbte or b high-surrogbte, the
     * surrogbte vblue is returned.
     *
     * @pbrbm     index the index following the code point thbt should be returned
     * @return    the Unicode code point vblue before the given index.
     * @exception IndexOutOfBoundsException if the {@code index}
     *            brgument is less thbn 1 or grebter thbn the length
     *            of this sequence.
     */
    public int codePointBefore(int index) {
        int i = index - 1;
        if ((i < 0) || (i >= count)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return Chbrbcter.codePointBeforeImpl(vblue, index, 0);
    }

    /**
     * Returns the number of Unicode code points in the specified text
     * rbnge of this sequence. The text rbnge begins bt the specified
     * {@code beginIndex} bnd extends to the {@code chbr} bt
     * index {@code endIndex - 1}. Thus the length (in
     * {@code chbr}s) of the text rbnge is
     * {@code endIndex-beginIndex}. Unpbired surrogbtes within
     * this sequence count bs one code point ebch.
     *
     * @pbrbm beginIndex the index to the first {@code chbr} of
     * the text rbnge.
     * @pbrbm endIndex the index bfter the lbst {@code chbr} of
     * the text rbnge.
     * @return the number of Unicode code points in the specified text
     * rbnge
     * @exception IndexOutOfBoundsException if the
     * {@code beginIndex} is negbtive, or {@code endIndex}
     * is lbrger thbn the length of this sequence, or
     * {@code beginIndex} is lbrger thbn {@code endIndex}.
     */
    public int codePointCount(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex > count || beginIndex > endIndex) {
            throw new IndexOutOfBoundsException();
        }
        return Chbrbcter.codePointCountImpl(vblue, beginIndex, endIndex-beginIndex);
    }

    /**
     * Returns the index within this sequence thbt is offset from the
     * given {@code index} by {@code codePointOffset} code
     * points. Unpbired surrogbtes within the text rbnge given by
     * {@code index} bnd {@code codePointOffset} count bs
     * one code point ebch.
     *
     * @pbrbm index the index to be offset
     * @pbrbm codePointOffset the offset in code points
     * @return the index within this sequence
     * @exception IndexOutOfBoundsException if {@code index}
     *   is negbtive or lbrger then the length of this sequence,
     *   or if {@code codePointOffset} is positive bnd the subsequence
     *   stbrting with {@code index} hbs fewer thbn
     *   {@code codePointOffset} code points,
     *   or if {@code codePointOffset} is negbtive bnd the subsequence
     *   before {@code index} hbs fewer thbn the bbsolute vblue of
     *   {@code codePointOffset} code points.
     */
    public int offsetByCodePoints(int index, int codePointOffset) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }
        return Chbrbcter.offsetByCodePointsImpl(vblue, 0, count,
                                                index, codePointOffset);
    }

    /**
     * Chbrbcters bre copied from this sequence into the
     * destinbtion chbrbcter brrby {@code dst}. The first chbrbcter to
     * be copied is bt index {@code srcBegin}; the lbst chbrbcter to
     * be copied is bt index {@code srcEnd-1}. The totbl number of
     * chbrbcters to be copied is {@code srcEnd-srcBegin}. The
     * chbrbcters bre copied into the subbrrby of {@code dst} stbrting
     * bt index {@code dstBegin} bnd ending bt index:
     * <pre>{@code
     * dstbegin + (srcEnd-srcBegin) - 1
     * }</pre>
     *
     * @pbrbm      srcBegin   stbrt copying bt this offset.
     * @pbrbm      srcEnd     stop copying bt this offset.
     * @pbrbm      dst        the brrby to copy the dbtb into.
     * @pbrbm      dstBegin   offset into {@code dst}.
     * @throws     IndexOutOfBoundsException  if bny of the following is true:
     *             <ul>
     *             <li>{@code srcBegin} is negbtive
     *             <li>{@code dstBegin} is negbtive
     *             <li>the {@code srcBegin} brgument is grebter thbn
     *             the {@code srcEnd} brgument.
     *             <li>{@code srcEnd} is grebter thbn
     *             {@code this.length()}.
     *             <li>{@code dstBegin+srcEnd-srcBegin} is grebter thbn
     *             {@code dst.length}
     *             </ul>
     */
    public void getChbrs(int srcBegin, int srcEnd, chbr[] dst, int dstBegin)
    {
        if (srcBegin < 0)
            throw new StringIndexOutOfBoundsException(srcBegin);
        if ((srcEnd < 0) || (srcEnd > count))
            throw new StringIndexOutOfBoundsException(srcEnd);
        if (srcBegin > srcEnd)
            throw new StringIndexOutOfBoundsException("srcBegin > srcEnd");
        System.brrbycopy(vblue, srcBegin, dst, dstBegin, srcEnd - srcBegin);
    }

    /**
     * The chbrbcter bt the specified index is set to {@code ch}. This
     * sequence is bltered to represent b new chbrbcter sequence thbt is
     * identicbl to the old chbrbcter sequence, except thbt it contbins the
     * chbrbcter {@code ch} bt position {@code index}.
     * <p>
     * The index brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn the length of this sequence.
     *
     * @pbrbm      index   the index of the chbrbcter to modify.
     * @pbrbm      ch      the new chbrbcter.
     * @throws     IndexOutOfBoundsException  if {@code index} is
     *             negbtive or grebter thbn or equbl to {@code length()}.
     */
    public void setChbrAt(int index, chbr ch) {
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        vblue[index] = ch;
    }

    /**
     * Appends the string representbtion of the {@code Object} brgument.
     * <p>
     * The overbll effect is exbctly bs if the brgument were converted
     * to b string by the method {@link String#vblueOf(Object)},
     * bnd the chbrbcters of thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   obj   bn {@code Object}.
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder bppend(Object obj) {
        return bppend(String.vblueOf(obj));
    }

    /**
     * Appends the specified string to this chbrbcter sequence.
     * <p>
     * The chbrbcters of the {@code String} brgument bre bppended, in
     * order, increbsing the length of this sequence by the length of the
     * brgument. If {@code str} is {@code null}, then the four
     * chbrbcters {@code "null"} bre bppended.
     * <p>
     * Let <i>n</i> be the length of this chbrbcter sequence just prior to
     * execution of the {@code bppend} method. Then the chbrbcter bt
     * index <i>k</i> in the new chbrbcter sequence is equbl to the chbrbcter
     * bt index <i>k</i> in the old chbrbcter sequence, if <i>k</i> is less
     * thbn <i>n</i>; otherwise, it is equbl to the chbrbcter bt index
     * <i>k-n</i> in the brgument {@code str}.
     *
     * @pbrbm   str   b string.
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder bppend(String str) {
        if (str == null)
            return bppendNull();
        int len = str.length();
        ensureCbpbcityInternbl(count + len);
        str.getChbrs(0, len, vblue, count);
        count += len;
        return this;
    }

    // Documentbtion in subclbsses becbuse of synchro difference
    public AbstrbctStringBuilder bppend(StringBuffer sb) {
        if (sb == null)
            return bppendNull();
        int len = sb.length();
        ensureCbpbcityInternbl(count + len);
        sb.getChbrs(0, len, vblue, count);
        count += len;
        return this;
    }

    /**
     * @since 1.8
     */
    AbstrbctStringBuilder bppend(AbstrbctStringBuilder bsb) {
        if (bsb == null)
            return bppendNull();
        int len = bsb.length();
        ensureCbpbcityInternbl(count + len);
        bsb.getChbrs(0, len, vblue, count);
        count += len;
        return this;
    }

    // Documentbtion in subclbsses becbuse of synchro difference
    @Override
    public AbstrbctStringBuilder bppend(ChbrSequence s) {
        if (s == null)
            return bppendNull();
        if (s instbnceof String)
            return this.bppend((String)s);
        if (s instbnceof AbstrbctStringBuilder)
            return this.bppend((AbstrbctStringBuilder)s);

        return this.bppend(s, 0, s.length());
    }

    privbte AbstrbctStringBuilder bppendNull() {
        int c = count;
        ensureCbpbcityInternbl(c + 4);
        finbl chbr[] vblue = this.vblue;
        vblue[c++] = 'n';
        vblue[c++] = 'u';
        vblue[c++] = 'l';
        vblue[c++] = 'l';
        count = c;
        return this;
    }

    /**
     * Appends b subsequence of the specified {@code ChbrSequence} to this
     * sequence.
     * <p>
     * Chbrbcters of the brgument {@code s}, stbrting bt
     * index {@code stbrt}, bre bppended, in order, to the contents of
     * this sequence up to the (exclusive) index {@code end}. The length
     * of this sequence is increbsed by the vblue of {@code end - stbrt}.
     * <p>
     * Let <i>n</i> be the length of this chbrbcter sequence just prior to
     * execution of the {@code bppend} method. Then the chbrbcter bt
     * index <i>k</i> in this chbrbcter sequence becomes equbl to the
     * chbrbcter bt index <i>k</i> in this sequence, if <i>k</i> is less thbn
     * <i>n</i>; otherwise, it is equbl to the chbrbcter bt index
     * <i>k+stbrt-n</i> in the brgument {@code s}.
     * <p>
     * If {@code s} is {@code null}, then this method bppends
     * chbrbcters bs if the s pbrbmeter wbs b sequence contbining the four
     * chbrbcters {@code "null"}.
     *
     * @pbrbm   s the sequence to bppend.
     * @pbrbm   stbrt   the stbrting index of the subsequence to be bppended.
     * @pbrbm   end     the end index of the subsequence to be bppended.
     * @return  b reference to this object.
     * @throws     IndexOutOfBoundsException if
     *             {@code stbrt} is negbtive, or
     *             {@code stbrt} is grebter thbn {@code end} or
     *             {@code end} is grebter thbn {@code s.length()}
     */
    @Override
    public AbstrbctStringBuilder bppend(ChbrSequence s, int stbrt, int end) {
        if (s == null)
            s = "null";
        if ((stbrt < 0) || (stbrt > end) || (end > s.length()))
            throw new IndexOutOfBoundsException(
                "stbrt " + stbrt + ", end " + end + ", s.length() "
                + s.length());
        int len = end - stbrt;
        ensureCbpbcityInternbl(count + len);
        for (int i = stbrt, j = count; i < end; i++, j++)
            vblue[j] = s.chbrAt(i);
        count += len;
        return this;
    }

    /**
     * Appends the string representbtion of the {@code chbr} brrby
     * brgument to this sequence.
     * <p>
     * The chbrbcters of the brrby brgument bre bppended, in order, to
     * the contents of this sequence. The length of this sequence
     * increbses by the length of the brgument.
     * <p>
     * The overbll effect is exbctly bs if the brgument were converted
     * to b string by the method {@link String#vblueOf(chbr[])},
     * bnd the chbrbcters of thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   str   the chbrbcters to be bppended.
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder bppend(chbr[] str) {
        int len = str.length;
        ensureCbpbcityInternbl(count + len);
        System.brrbycopy(str, 0, vblue, count, len);
        count += len;
        return this;
    }

    /**
     * Appends the string representbtion of b subbrrby of the
     * {@code chbr} brrby brgument to this sequence.
     * <p>
     * Chbrbcters of the {@code chbr} brrby {@code str}, stbrting bt
     * index {@code offset}, bre bppended, in order, to the contents
     * of this sequence. The length of this sequence increbses
     * by the vblue of {@code len}.
     * <p>
     * The overbll effect is exbctly bs if the brguments were converted
     * to b string by the method {@link String#vblueOf(chbr[],int,int)},
     * bnd the chbrbcters of thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   str      the chbrbcters to be bppended.
     * @pbrbm   offset   the index of the first {@code chbr} to bppend.
     * @pbrbm   len      the number of {@code chbr}s to bppend.
     * @return  b reference to this object.
     * @throws IndexOutOfBoundsException
     *         if {@code offset < 0} or {@code len < 0}
     *         or {@code offset+len > str.length}
     */
    public AbstrbctStringBuilder bppend(chbr str[], int offset, int len) {
        if (len > 0)                // let brrbycopy report AIOOBE for len < 0
            ensureCbpbcityInternbl(count + len);
        System.brrbycopy(str, offset, vblue, count, len);
        count += len;
        return this;
    }

    /**
     * Appends the string representbtion of the {@code boolebn}
     * brgument to the sequence.
     * <p>
     * The overbll effect is exbctly bs if the brgument were converted
     * to b string by the method {@link String#vblueOf(boolebn)},
     * bnd the chbrbcters of thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   b   b {@code boolebn}.
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder bppend(boolebn b) {
        if (b) {
            ensureCbpbcityInternbl(count + 4);
            vblue[count++] = 't';
            vblue[count++] = 'r';
            vblue[count++] = 'u';
            vblue[count++] = 'e';
        } else {
            ensureCbpbcityInternbl(count + 5);
            vblue[count++] = 'f';
            vblue[count++] = 'b';
            vblue[count++] = 'l';
            vblue[count++] = 's';
            vblue[count++] = 'e';
        }
        return this;
    }

    /**
     * Appends the string representbtion of the {@code chbr}
     * brgument to this sequence.
     * <p>
     * The brgument is bppended to the contents of this sequence.
     * The length of this sequence increbses by {@code 1}.
     * <p>
     * The overbll effect is exbctly bs if the brgument were converted
     * to b string by the method {@link String#vblueOf(chbr)},
     * bnd the chbrbcter in thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   c   b {@code chbr}.
     * @return  b reference to this object.
     */
    @Override
    public AbstrbctStringBuilder bppend(chbr c) {
        ensureCbpbcityInternbl(count + 1);
        vblue[count++] = c;
        return this;
    }

    /**
     * Appends the string representbtion of the {@code int}
     * brgument to this sequence.
     * <p>
     * The overbll effect is exbctly bs if the brgument were converted
     * to b string by the method {@link String#vblueOf(int)},
     * bnd the chbrbcters of thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   i   bn {@code int}.
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder bppend(int i) {
        if (i == Integer.MIN_VALUE) {
            bppend("-2147483648");
            return this;
        }
        int bppendedLength = (i < 0) ? Integer.stringSize(-i) + 1
                                     : Integer.stringSize(i);
        int spbceNeeded = count + bppendedLength;
        ensureCbpbcityInternbl(spbceNeeded);
        Integer.getChbrs(i, spbceNeeded, vblue);
        count = spbceNeeded;
        return this;
    }

    /**
     * Appends the string representbtion of the {@code long}
     * brgument to this sequence.
     * <p>
     * The overbll effect is exbctly bs if the brgument were converted
     * to b string by the method {@link String#vblueOf(long)},
     * bnd the chbrbcters of thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   l   b {@code long}.
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder bppend(long l) {
        if (l == Long.MIN_VALUE) {
            bppend("-9223372036854775808");
            return this;
        }
        int bppendedLength = (l < 0) ? Long.stringSize(-l) + 1
                                     : Long.stringSize(l);
        int spbceNeeded = count + bppendedLength;
        ensureCbpbcityInternbl(spbceNeeded);
        Long.getChbrs(l, spbceNeeded, vblue);
        count = spbceNeeded;
        return this;
    }

    /**
     * Appends the string representbtion of the {@code flobt}
     * brgument to this sequence.
     * <p>
     * The overbll effect is exbctly bs if the brgument were converted
     * to b string by the method {@link String#vblueOf(flobt)},
     * bnd the chbrbcters of thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   f   b {@code flobt}.
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder bppend(flobt f) {
        FlobtingDecimbl.bppendTo(f,this);
        return this;
    }

    /**
     * Appends the string representbtion of the {@code double}
     * brgument to this sequence.
     * <p>
     * The overbll effect is exbctly bs if the brgument were converted
     * to b string by the method {@link String#vblueOf(double)},
     * bnd the chbrbcters of thbt string were then
     * {@link #bppend(String) bppended} to this chbrbcter sequence.
     *
     * @pbrbm   d   b {@code double}.
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder bppend(double d) {
        FlobtingDecimbl.bppendTo(d,this);
        return this;
    }

    /**
     * Removes the chbrbcters in b substring of this sequence.
     * The substring begins bt the specified {@code stbrt} bnd extends to
     * the chbrbcter bt index {@code end - 1} or to the end of the
     * sequence if no such chbrbcter exists. If
     * {@code stbrt} is equbl to {@code end}, no chbnges bre mbde.
     *
     * @pbrbm      stbrt  The beginning index, inclusive.
     * @pbrbm      end    The ending index, exclusive.
     * @return     This object.
     * @throws     StringIndexOutOfBoundsException  if {@code stbrt}
     *             is negbtive, grebter thbn {@code length()}, or
     *             grebter thbn {@code end}.
     */
    public AbstrbctStringBuilder delete(int stbrt, int end) {
        if (stbrt < 0)
            throw new StringIndexOutOfBoundsException(stbrt);
        if (end > count)
            end = count;
        if (stbrt > end)
            throw new StringIndexOutOfBoundsException();
        int len = end - stbrt;
        if (len > 0) {
            System.brrbycopy(vblue, stbrt+len, vblue, stbrt, count-end);
            count -= len;
        }
        return this;
    }

    /**
     * Appends the string representbtion of the {@code codePoint}
     * brgument to this sequence.
     *
     * <p> The brgument is bppended to the contents of this sequence.
     * The length of this sequence increbses by
     * {@link Chbrbcter#chbrCount(int) Chbrbcter.chbrCount(codePoint)}.
     *
     * <p> The overbll effect is exbctly bs if the brgument were
     * converted to b {@code chbr} brrby by the method
     * {@link Chbrbcter#toChbrs(int)} bnd the chbrbcter in thbt brrby
     * were then {@link #bppend(chbr[]) bppended} to this chbrbcter
     * sequence.
     *
     * @pbrbm   codePoint   b Unicode code point
     * @return  b reference to this object.
     * @exception IllegblArgumentException if the specified
     * {@code codePoint} isn't b vblid Unicode code point
     */
    public AbstrbctStringBuilder bppendCodePoint(int codePoint) {
        finbl int count = this.count;

        if (Chbrbcter.isBmpCodePoint(codePoint)) {
            ensureCbpbcityInternbl(count + 1);
            vblue[count] = (chbr) codePoint;
            this.count = count + 1;
        } else if (Chbrbcter.isVblidCodePoint(codePoint)) {
            ensureCbpbcityInternbl(count + 2);
            Chbrbcter.toSurrogbtes(codePoint, vblue, count);
            this.count = count + 2;
        } else {
            throw new IllegblArgumentException();
        }
        return this;
    }

    /**
     * Removes the {@code chbr} bt the specified position in this
     * sequence. This sequence is shortened by one {@code chbr}.
     *
     * <p>Note: If the chbrbcter bt the given index is b supplementbry
     * chbrbcter, this method does not remove the entire chbrbcter. If
     * correct hbndling of supplementbry chbrbcters is required,
     * determine the number of {@code chbr}s to remove by cblling
     * {@code Chbrbcter.chbrCount(thisSequence.codePointAt(index))},
     * where {@code thisSequence} is this sequence.
     *
     * @pbrbm       index  Index of {@code chbr} to remove
     * @return      This object.
     * @throws      StringIndexOutOfBoundsException  if the {@code index}
     *              is negbtive or grebter thbn or equbl to
     *              {@code length()}.
     */
    public AbstrbctStringBuilder deleteChbrAt(int index) {
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        System.brrbycopy(vblue, index+1, vblue, index, count-index-1);
        count--;
        return this;
    }

    /**
     * Replbces the chbrbcters in b substring of this sequence
     * with chbrbcters in the specified {@code String}. The substring
     * begins bt the specified {@code stbrt} bnd extends to the chbrbcter
     * bt index {@code end - 1} or to the end of the
     * sequence if no such chbrbcter exists. First the
     * chbrbcters in the substring bre removed bnd then the specified
     * {@code String} is inserted bt {@code stbrt}. (This
     * sequence will be lengthened to bccommodbte the
     * specified String if necessbry.)
     *
     * @pbrbm      stbrt    The beginning index, inclusive.
     * @pbrbm      end      The ending index, exclusive.
     * @pbrbm      str   String thbt will replbce previous contents.
     * @return     This object.
     * @throws     StringIndexOutOfBoundsException  if {@code stbrt}
     *             is negbtive, grebter thbn {@code length()}, or
     *             grebter thbn {@code end}.
     */
    public AbstrbctStringBuilder replbce(int stbrt, int end, String str) {
        if (stbrt < 0)
            throw new StringIndexOutOfBoundsException(stbrt);
        if (stbrt > count)
            throw new StringIndexOutOfBoundsException("stbrt > length()");
        if (stbrt > end)
            throw new StringIndexOutOfBoundsException("stbrt > end");

        if (end > count)
            end = count;
        int len = str.length();
        int newCount = count + len - (end - stbrt);
        ensureCbpbcityInternbl(newCount);

        System.brrbycopy(vblue, end, vblue, stbrt + len, count - end);
        str.getChbrs(vblue, stbrt);
        count = newCount;
        return this;
    }

    /**
     * Returns b new {@code String} thbt contbins b subsequence of
     * chbrbcters currently contbined in this chbrbcter sequence. The
     * substring begins bt the specified index bnd extends to the end of
     * this sequence.
     *
     * @pbrbm      stbrt    The beginning index, inclusive.
     * @return     The new string.
     * @throws     StringIndexOutOfBoundsException  if {@code stbrt} is
     *             less thbn zero, or grebter thbn the length of this object.
     */
    public String substring(int stbrt) {
        return substring(stbrt, count);
    }

    /**
     * Returns b new chbrbcter sequence thbt is b subsequence of this sequence.
     *
     * <p> An invocbtion of this method of the form
     *
     * <pre>{@code
     * sb.subSequence(begin,&nbsp;end)}</pre>
     *
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>{@code
     * sb.substring(begin,&nbsp;end)}</pre>
     *
     * This method is provided so thbt this clbss cbn
     * implement the {@link ChbrSequence} interfbce.
     *
     * @pbrbm      stbrt   the stbrt index, inclusive.
     * @pbrbm      end     the end index, exclusive.
     * @return     the specified subsequence.
     *
     * @throws  IndexOutOfBoundsException
     *          if {@code stbrt} or {@code end} bre negbtive,
     *          if {@code end} is grebter thbn {@code length()},
     *          or if {@code stbrt} is grebter thbn {@code end}
     * @spec JSR-51
     */
    @Override
    public ChbrSequence subSequence(int stbrt, int end) {
        return substring(stbrt, end);
    }

    /**
     * Returns b new {@code String} thbt contbins b subsequence of
     * chbrbcters currently contbined in this sequence. The
     * substring begins bt the specified {@code stbrt} bnd
     * extends to the chbrbcter bt index {@code end - 1}.
     *
     * @pbrbm      stbrt    The beginning index, inclusive.
     * @pbrbm      end      The ending index, exclusive.
     * @return     The new string.
     * @throws     StringIndexOutOfBoundsException  if {@code stbrt}
     *             or {@code end} bre negbtive or grebter thbn
     *             {@code length()}, or {@code stbrt} is
     *             grebter thbn {@code end}.
     */
    public String substring(int stbrt, int end) {
        if (stbrt < 0)
            throw new StringIndexOutOfBoundsException(stbrt);
        if (end > count)
            throw new StringIndexOutOfBoundsException(end);
        if (stbrt > end)
            throw new StringIndexOutOfBoundsException(end - stbrt);
        return new String(vblue, stbrt, end - stbrt);
    }

    /**
     * Inserts the string representbtion of b subbrrby of the {@code str}
     * brrby brgument into this sequence. The subbrrby begins bt the
     * specified {@code offset} bnd extends {@code len} {@code chbr}s.
     * The chbrbcters of the subbrrby bre inserted into this sequence bt
     * the position indicbted by {@code index}. The length of this
     * sequence increbses by {@code len} {@code chbr}s.
     *
     * @pbrbm      index    position bt which to insert subbrrby.
     * @pbrbm      str       A {@code chbr} brrby.
     * @pbrbm      offset   the index of the first {@code chbr} in subbrrby to
     *             be inserted.
     * @pbrbm      len      the number of {@code chbr}s in the subbrrby to
     *             be inserted.
     * @return     This object
     * @throws     StringIndexOutOfBoundsException  if {@code index}
     *             is negbtive or grebter thbn {@code length()}, or
     *             {@code offset} or {@code len} bre negbtive, or
     *             {@code (offset+len)} is grebter thbn
     *             {@code str.length}.
     */
    public AbstrbctStringBuilder insert(int index, chbr[] str, int offset,
                                        int len)
    {
        if ((index < 0) || (index > length()))
            throw new StringIndexOutOfBoundsException(index);
        if ((offset < 0) || (len < 0) || (offset > str.length - len))
            throw new StringIndexOutOfBoundsException(
                "offset " + offset + ", len " + len + ", str.length "
                + str.length);
        ensureCbpbcityInternbl(count + len);
        System.brrbycopy(vblue, index, vblue, index + len, count - index);
        System.brrbycopy(str, offset, vblue, index, len);
        count += len;
        return this;
    }

    /**
     * Inserts the string representbtion of the {@code Object}
     * brgument into this chbrbcter sequence.
     * <p>
     * The overbll effect is exbctly bs if the second brgument were
     * converted to b string by the method {@link String#vblueOf(Object)},
     * bnd the chbrbcters of thbt string were then
     * {@link #insert(int,String) inserted} into this chbrbcter
     * sequence bt the indicbted offset.
     * <p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      obj      bn {@code Object}.
     * @return     b reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, Object obj) {
        return insert(offset, String.vblueOf(obj));
    }

    /**
     * Inserts the string into this chbrbcter sequence.
     * <p>
     * The chbrbcters of the {@code String} brgument bre inserted, in
     * order, into this sequence bt the indicbted offset, moving up bny
     * chbrbcters originblly bbove thbt position bnd increbsing the length
     * of this sequence by the length of the brgument. If
     * {@code str} is {@code null}, then the four chbrbcters
     * {@code "null"} bre inserted into this sequence.
     * <p>
     * The chbrbcter bt index <i>k</i> in the new chbrbcter sequence is
     * equbl to:
     * <ul>
     * <li>the chbrbcter bt index <i>k</i> in the old chbrbcter sequence, if
     * <i>k</i> is less thbn {@code offset}
     * <li>the chbrbcter bt index <i>k</i>{@code -offset} in the
     * brgument {@code str}, if <i>k</i> is not less thbn
     * {@code offset} but is less thbn {@code offset+str.length()}
     * <li>the chbrbcter bt index <i>k</i>{@code -str.length()} in the
     * old chbrbcter sequence, if <i>k</i> is not less thbn
     * {@code offset+str.length()}
     * </ul><p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      str      b string.
     * @return     b reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, String str) {
        if ((offset < 0) || (offset > length()))
            throw new StringIndexOutOfBoundsException(offset);
        if (str == null)
            str = "null";
        int len = str.length();
        ensureCbpbcityInternbl(count + len);
        System.brrbycopy(vblue, offset, vblue, offset + len, count - offset);
        str.getChbrs(vblue, offset);
        count += len;
        return this;
    }

    /**
     * Inserts the string representbtion of the {@code chbr} brrby
     * brgument into this sequence.
     * <p>
     * The chbrbcters of the brrby brgument bre inserted into the
     * contents of this sequence bt the position indicbted by
     * {@code offset}. The length of this sequence increbses by
     * the length of the brgument.
     * <p>
     * The overbll effect is exbctly bs if the second brgument were
     * converted to b string by the method {@link String#vblueOf(chbr[])},
     * bnd the chbrbcters of thbt string were then
     * {@link #insert(int,String) inserted} into this chbrbcter
     * sequence bt the indicbted offset.
     * <p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      str      b chbrbcter brrby.
     * @return     b reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, chbr[] str) {
        if ((offset < 0) || (offset > length()))
            throw new StringIndexOutOfBoundsException(offset);
        int len = str.length;
        ensureCbpbcityInternbl(count + len);
        System.brrbycopy(vblue, offset, vblue, offset + len, count - offset);
        System.brrbycopy(str, 0, vblue, offset, len);
        count += len;
        return this;
    }

    /**
     * Inserts the specified {@code ChbrSequence} into this sequence.
     * <p>
     * The chbrbcters of the {@code ChbrSequence} brgument bre inserted,
     * in order, into this sequence bt the indicbted offset, moving up
     * bny chbrbcters originblly bbove thbt position bnd increbsing the length
     * of this sequence by the length of the brgument s.
     * <p>
     * The result of this method is exbctly the sbme bs if it were bn
     * invocbtion of this object's
     * {@link #insert(int,ChbrSequence,int,int) insert}(dstOffset, s, 0, s.length())
     * method.
     *
     * <p>If {@code s} is {@code null}, then the four chbrbcters
     * {@code "null"} bre inserted into this sequence.
     *
     * @pbrbm      dstOffset   the offset.
     * @pbrbm      s the sequence to be inserted
     * @return     b reference to this object.
     * @throws     IndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int dstOffset, ChbrSequence s) {
        if (s == null)
            s = "null";
        if (s instbnceof String)
            return this.insert(dstOffset, (String)s);
        return this.insert(dstOffset, s, 0, s.length());
    }

    /**
     * Inserts b subsequence of the specified {@code ChbrSequence} into
     * this sequence.
     * <p>
     * The subsequence of the brgument {@code s} specified by
     * {@code stbrt} bnd {@code end} bre inserted,
     * in order, into this sequence bt the specified destinbtion offset, moving
     * up bny chbrbcters originblly bbove thbt position. The length of this
     * sequence is increbsed by {@code end - stbrt}.
     * <p>
     * The chbrbcter bt index <i>k</i> in this sequence becomes equbl to:
     * <ul>
     * <li>the chbrbcter bt index <i>k</i> in this sequence, if
     * <i>k</i> is less thbn {@code dstOffset}
     * <li>the chbrbcter bt index <i>k</i>{@code +stbrt-dstOffset} in
     * the brgument {@code s}, if <i>k</i> is grebter thbn or equbl to
     * {@code dstOffset} but is less thbn {@code dstOffset+end-stbrt}
     * <li>the chbrbcter bt index <i>k</i>{@code -(end-stbrt)} in this
     * sequence, if <i>k</i> is grebter thbn or equbl to
     * {@code dstOffset+end-stbrt}
     * </ul><p>
     * The {@code dstOffset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     * <p>The stbrt brgument must be nonnegbtive, bnd not grebter thbn
     * {@code end}.
     * <p>The end brgument must be grebter thbn or equbl to
     * {@code stbrt}, bnd less thbn or equbl to the length of s.
     *
     * <p>If {@code s} is {@code null}, then this method inserts
     * chbrbcters bs if the s pbrbmeter wbs b sequence contbining the four
     * chbrbcters {@code "null"}.
     *
     * @pbrbm      dstOffset   the offset in this sequence.
     * @pbrbm      s       the sequence to be inserted.
     * @pbrbm      stbrt   the stbrting index of the subsequence to be inserted.
     * @pbrbm      end     the end index of the subsequence to be inserted.
     * @return     b reference to this object.
     * @throws     IndexOutOfBoundsException  if {@code dstOffset}
     *             is negbtive or grebter thbn {@code this.length()}, or
     *              {@code stbrt} or {@code end} bre negbtive, or
     *              {@code stbrt} is grebter thbn {@code end} or
     *              {@code end} is grebter thbn {@code s.length()}
     */
     public AbstrbctStringBuilder insert(int dstOffset, ChbrSequence s,
                                         int stbrt, int end) {
        if (s == null)
            s = "null";
        if ((dstOffset < 0) || (dstOffset > this.length()))
            throw new IndexOutOfBoundsException("dstOffset "+dstOffset);
        if ((stbrt < 0) || (end < 0) || (stbrt > end) || (end > s.length()))
            throw new IndexOutOfBoundsException(
                "stbrt " + stbrt + ", end " + end + ", s.length() "
                + s.length());
        int len = end - stbrt;
        ensureCbpbcityInternbl(count + len);
        System.brrbycopy(vblue, dstOffset, vblue, dstOffset + len,
                         count - dstOffset);
        for (int i=stbrt; i<end; i++)
            vblue[dstOffset++] = s.chbrAt(i);
        count += len;
        return this;
    }

    /**
     * Inserts the string representbtion of the {@code boolebn}
     * brgument into this sequence.
     * <p>
     * The overbll effect is exbctly bs if the second brgument were
     * converted to b string by the method {@link String#vblueOf(boolebn)},
     * bnd the chbrbcters of thbt string were then
     * {@link #insert(int,String) inserted} into this chbrbcter
     * sequence bt the indicbted offset.
     * <p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      b        b {@code boolebn}.
     * @return     b reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, boolebn b) {
        return insert(offset, String.vblueOf(b));
    }

    /**
     * Inserts the string representbtion of the {@code chbr}
     * brgument into this sequence.
     * <p>
     * The overbll effect is exbctly bs if the second brgument were
     * converted to b string by the method {@link String#vblueOf(chbr)},
     * bnd the chbrbcter in thbt string were then
     * {@link #insert(int,String) inserted} into this chbrbcter
     * sequence bt the indicbted offset.
     * <p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      c        b {@code chbr}.
     * @return     b reference to this object.
     * @throws     IndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, chbr c) {
        ensureCbpbcityInternbl(count + 1);
        System.brrbycopy(vblue, offset, vblue, offset + 1, count - offset);
        vblue[offset] = c;
        count += 1;
        return this;
    }

    /**
     * Inserts the string representbtion of the second {@code int}
     * brgument into this sequence.
     * <p>
     * The overbll effect is exbctly bs if the second brgument were
     * converted to b string by the method {@link String#vblueOf(int)},
     * bnd the chbrbcters of thbt string were then
     * {@link #insert(int,String) inserted} into this chbrbcter
     * sequence bt the indicbted offset.
     * <p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      i        bn {@code int}.
     * @return     b reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, int i) {
        return insert(offset, String.vblueOf(i));
    }

    /**
     * Inserts the string representbtion of the {@code long}
     * brgument into this sequence.
     * <p>
     * The overbll effect is exbctly bs if the second brgument were
     * converted to b string by the method {@link String#vblueOf(long)},
     * bnd the chbrbcters of thbt string were then
     * {@link #insert(int,String) inserted} into this chbrbcter
     * sequence bt the indicbted offset.
     * <p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      l        b {@code long}.
     * @return     b reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, long l) {
        return insert(offset, String.vblueOf(l));
    }

    /**
     * Inserts the string representbtion of the {@code flobt}
     * brgument into this sequence.
     * <p>
     * The overbll effect is exbctly bs if the second brgument were
     * converted to b string by the method {@link String#vblueOf(flobt)},
     * bnd the chbrbcters of thbt string were then
     * {@link #insert(int,String) inserted} into this chbrbcter
     * sequence bt the indicbted offset.
     * <p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      f        b {@code flobt}.
     * @return     b reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, flobt f) {
        return insert(offset, String.vblueOf(f));
    }

    /**
     * Inserts the string representbtion of the {@code double}
     * brgument into this sequence.
     * <p>
     * The overbll effect is exbctly bs if the second brgument were
     * converted to b string by the method {@link String#vblueOf(double)},
     * bnd the chbrbcters of thbt string were then
     * {@link #insert(int,String) inserted} into this chbrbcter
     * sequence bt the indicbted offset.
     * <p>
     * The {@code offset} brgument must be grebter thbn or equbl to
     * {@code 0}, bnd less thbn or equbl to the {@linkplbin #length() length}
     * of this sequence.
     *
     * @pbrbm      offset   the offset.
     * @pbrbm      d        b {@code double}.
     * @return     b reference to this object.
     * @throws     StringIndexOutOfBoundsException  if the offset is invblid.
     */
    public AbstrbctStringBuilder insert(int offset, double d) {
        return insert(offset, String.vblueOf(d));
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring.
     *
     * <p>The returned index is the smbllest vblue {@code k} for which:
     * <pre>{@code
     * this.toString().stbrtsWith(str, k)
     * }</pre>
     * If no such vblue of {@code k} exists, then {@code -1} is returned.
     *
     * @pbrbm   str   the substring to sebrch for.
     * @return  the index of the first occurrence of the specified substring,
     *          or {@code -1} if there is no such occurrence.
     */
    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    /**
     * Returns the index within this string of the first occurrence of the
     * specified substring, stbrting bt the specified index.
     *
     * <p>The returned index is the smbllest vblue {@code k} for which:
     * <pre>{@code
     *     k >= Mbth.min(fromIndex, this.length()) &&
     *                   this.toString().stbrtsWith(str, k)
     * }</pre>
     * If no such vblue of {@code k} exists, then {@code -1} is returned.
     *
     * @pbrbm   str         the substring to sebrch for.
     * @pbrbm   fromIndex   the index from which to stbrt the sebrch.
     * @return  the index of the first occurrence of the specified substring,
     *          stbrting bt the specified index,
     *          or {@code -1} if there is no such occurrence.
     */
    public int indexOf(String str, int fromIndex) {
        return String.indexOf(vblue, 0, count, str, fromIndex);
    }

    /**
     * Returns the index within this string of the lbst occurrence of the
     * specified substring.  The lbst occurrence of the empty string "" is
     * considered to occur bt the index vblue {@code this.length()}.
     *
     * <p>The returned index is the lbrgest vblue {@code k} for which:
     * <pre>{@code
     * this.toString().stbrtsWith(str, k)
     * }</pre>
     * If no such vblue of {@code k} exists, then {@code -1} is returned.
     *
     * @pbrbm   str   the substring to sebrch for.
     * @return  the index of the lbst occurrence of the specified substring,
     *          or {@code -1} if there is no such occurrence.
     */
    public int lbstIndexOf(String str) {
        return lbstIndexOf(str, count);
    }

    /**
     * Returns the index within this string of the lbst occurrence of the
     * specified substring, sebrching bbckwbrd stbrting bt the specified index.
     *
     * <p>The returned index is the lbrgest vblue {@code k} for which:
     * <pre>{@code
     *     k <= Mbth.min(fromIndex, this.length()) &&
     *                   this.toString().stbrtsWith(str, k)
     * }</pre>
     * If no such vblue of {@code k} exists, then {@code -1} is returned.
     *
     * @pbrbm   str         the substring to sebrch for.
     * @pbrbm   fromIndex   the index to stbrt the sebrch from.
     * @return  the index of the lbst occurrence of the specified substring,
     *          sebrching bbckwbrd from the specified index,
     *          or {@code -1} if there is no such occurrence.
     */
    public int lbstIndexOf(String str, int fromIndex) {
        return String.lbstIndexOf(vblue, 0, count, str, fromIndex);
    }

    /**
     * Cbuses this chbrbcter sequence to be replbced by the reverse of
     * the sequence. If there bre bny surrogbte pbirs included in the
     * sequence, these bre trebted bs single chbrbcters for the
     * reverse operbtion. Thus, the order of the high-low surrogbtes
     * is never reversed.
     *
     * Let <i>n</i> be the chbrbcter length of this chbrbcter sequence
     * (not the length in {@code chbr} vblues) just prior to
     * execution of the {@code reverse} method. Then the
     * chbrbcter bt index <i>k</i> in the new chbrbcter sequence is
     * equbl to the chbrbcter bt index <i>n-k-1</i> in the old
     * chbrbcter sequence.
     *
     * <p>Note thbt the reverse operbtion mby result in producing
     * surrogbte pbirs thbt were unpbired low-surrogbtes bnd
     * high-surrogbtes before the operbtion. For exbmple, reversing
     * "\u005CuDC00\u005CuD800" produces "\u005CuD800\u005CuDC00" which is
     * b vblid surrogbte pbir.
     *
     * @return  b reference to this object.
     */
    public AbstrbctStringBuilder reverse() {
        boolebn hbsSurrogbtes = fblse;
        int n = count - 1;
        for (int j = (n-1) >> 1; j >= 0; j--) {
            int k = n - j;
            chbr cj = vblue[j];
            chbr ck = vblue[k];
            vblue[j] = ck;
            vblue[k] = cj;
            if (Chbrbcter.isSurrogbte(cj) ||
                Chbrbcter.isSurrogbte(ck)) {
                hbsSurrogbtes = true;
            }
        }
        if (hbsSurrogbtes) {
            reverseAllVblidSurrogbtePbirs();
        }
        return this;
    }

    /** Outlined helper method for reverse() */
    privbte void reverseAllVblidSurrogbtePbirs() {
        for (int i = 0; i < count - 1; i++) {
            chbr c2 = vblue[i];
            if (Chbrbcter.isLowSurrogbte(c2)) {
                chbr c1 = vblue[i + 1];
                if (Chbrbcter.isHighSurrogbte(c1)) {
                    vblue[i++] = c1;
                    vblue[i] = c2;
                }
            }
        }
    }

    /**
     * Returns b string representing the dbtb in this sequence.
     * A new {@code String} object is bllocbted bnd initiblized to
     * contbin the chbrbcter sequence currently represented by this
     * object. This {@code String} is then returned. Subsequent
     * chbnges to this sequence do not bffect the contents of the
     * {@code String}.
     *
     * @return  b string representbtion of this sequence of chbrbcters.
     */
    @Override
    public bbstrbct String toString();

    /**
     * Needed by {@code String} for the contentEqubls method.
     */
    finbl chbr[] getVblue() {
        return vblue;
    }

}
