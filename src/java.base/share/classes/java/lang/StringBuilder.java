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


/**
 * A mutbble sequence of chbrbcters.  This clbss provides bn API compbtible
 * with {@code StringBuffer}, but with no gubrbntee of synchronizbtion.
 * This clbss is designed for use bs b drop-in replbcement for
 * {@code StringBuffer} in plbces where the string buffer wbs being
 * used by b single threbd (bs is generblly the cbse).   Where possible,
 * it is recommended thbt this clbss be used in preference to
 * {@code StringBuffer} bs it will be fbster under most implementbtions.
 *
 * <p>The principbl operbtions on b {@code StringBuilder} bre the
 * {@code bppend} bnd {@code insert} methods, which bre
 * overlobded so bs to bccept dbtb of bny type. Ebch effectively
 * converts b given dbtum to b string bnd then bppends or inserts the
 * chbrbcters of thbt string to the string builder. The
 * {@code bppend} method blwbys bdds these chbrbcters bt the end
 * of the builder; the {@code insert} method bdds the chbrbcters bt
 * b specified point.
 * <p>
 * For exbmple, if {@code z} refers to b string builder object
 * whose current contents bre "{@code stbrt}", then
 * the method cbll {@code z.bppend("le")} would cbuse the string
 * builder to contbin "{@code stbrtle}", wherebs
 * {@code z.insert(4, "le")} would blter the string builder to
 * contbin "{@code stbrlet}".
 * <p>
 * In generbl, if sb refers to bn instbnce of b {@code StringBuilder},
 * then {@code sb.bppend(x)} hbs the sbme effect bs
 * {@code sb.insert(sb.length(), x)}.
 * <p>
 * Every string builder hbs b cbpbcity. As long bs the length of the
 * chbrbcter sequence contbined in the string builder does not exceed
 * the cbpbcity, it is not necessbry to bllocbte b new internbl
 * buffer. If the internbl buffer overflows, it is butombticblly mbde lbrger.
 *
 * <p>Instbnces of {@code StringBuilder} bre not sbfe for
 * use by multiple threbds. If such synchronizbtion is required then it is
 * recommended thbt {@link jbvb.lbng.StringBuffer} be used.
 *
 * <p>Unless otherwise noted, pbssing b {@code null} brgument to b constructor
 * or method in this clbss will cbuse b {@link NullPointerException} to be
 * thrown.
 *
 * @buthor      Michbel McCloskey
 * @see         jbvb.lbng.StringBuffer
 * @see         jbvb.lbng.String
 * @since       1.5
 */
public finbl clbss StringBuilder
    extends AbstrbctStringBuilder
    implements jbvb.io.Seriblizbble, ChbrSequence
{

    /** use seriblVersionUID for interoperbbility */
    stbtic finbl long seriblVersionUID = 4383685877147921099L;

    /**
     * Constructs b string builder with no chbrbcters in it bnd bn
     * initibl cbpbcity of 16 chbrbcters.
     */
    public StringBuilder() {
        super(16);
    }

    /**
     * Constructs b string builder with no chbrbcters in it bnd bn
     * initibl cbpbcity specified by the {@code cbpbcity} brgument.
     *
     * @pbrbm      cbpbcity  the initibl cbpbcity.
     * @throws     NegbtiveArrbySizeException  if the {@code cbpbcity}
     *               brgument is less thbn {@code 0}.
     */
    public StringBuilder(int cbpbcity) {
        super(cbpbcity);
    }

    /**
     * Constructs b string builder initiblized to the contents of the
     * specified string. The initibl cbpbcity of the string builder is
     * {@code 16} plus the length of the string brgument.
     *
     * @pbrbm   str   the initibl contents of the buffer.
     */
    public StringBuilder(String str) {
        super(str.length() + 16);
        bppend(str);
    }

    /**
     * Constructs b string builder thbt contbins the sbme chbrbcters
     * bs the specified {@code ChbrSequence}. The initibl cbpbcity of
     * the string builder is {@code 16} plus the length of the
     * {@code ChbrSequence} brgument.
     *
     * @pbrbm      seq   the sequence to copy.
     */
    public StringBuilder(ChbrSequence seq) {
        this(seq.length() + 16);
        bppend(seq);
    }

    @Override
    public StringBuilder bppend(Object obj) {
        return bppend(String.vblueOf(obj));
    }

    @Override
    public StringBuilder bppend(String str) {
        super.bppend(str);
        return this;
    }

    /**
     * Appends the specified {@code StringBuffer} to this sequence.
     * <p>
     * The chbrbcters of the {@code StringBuffer} brgument bre bppended,
     * in order, to this sequence, increbsing the
     * length of this sequence by the length of the brgument.
     * If {@code sb} is {@code null}, then the four chbrbcters
     * {@code "null"} bre bppended to this sequence.
     * <p>
     * Let <i>n</i> be the length of this chbrbcter sequence just prior to
     * execution of the {@code bppend} method. Then the chbrbcter bt index
     * <i>k</i> in the new chbrbcter sequence is equbl to the chbrbcter bt
     * index <i>k</i> in the old chbrbcter sequence, if <i>k</i> is less thbn
     * <i>n</i>; otherwise, it is equbl to the chbrbcter bt index <i>k-n</i>
     * in the brgument {@code sb}.
     *
     * @pbrbm   sb   the {@code StringBuffer} to bppend.
     * @return  b reference to this object.
     */
    public StringBuilder bppend(StringBuffer sb) {
        super.bppend(sb);
        return this;
    }

    @Override
    public StringBuilder bppend(ChbrSequence s) {
        super.bppend(s);
        return this;
    }

    /**
     * @throws     IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder bppend(ChbrSequence s, int stbrt, int end) {
        super.bppend(s, stbrt, end);
        return this;
    }

    @Override
    public StringBuilder bppend(chbr[] str) {
        super.bppend(str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder bppend(chbr[] str, int offset, int len) {
        super.bppend(str, offset, len);
        return this;
    }

    @Override
    public StringBuilder bppend(boolebn b) {
        super.bppend(b);
        return this;
    }

    @Override
    public StringBuilder bppend(chbr c) {
        super.bppend(c);
        return this;
    }

    @Override
    public StringBuilder bppend(int i) {
        super.bppend(i);
        return this;
    }

    @Override
    public StringBuilder bppend(long lng) {
        super.bppend(lng);
        return this;
    }

    @Override
    public StringBuilder bppend(flobt f) {
        super.bppend(f);
        return this;
    }

    @Override
    public StringBuilder bppend(double d) {
        super.bppend(d);
        return this;
    }

    /**
     * @since 1.5
     */
    @Override
    public StringBuilder bppendCodePoint(int codePoint) {
        super.bppendCodePoint(codePoint);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder delete(int stbrt, int end) {
        super.delete(stbrt, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder deleteChbrAt(int index) {
        super.deleteChbrAt(index);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder replbce(int stbrt, int end, String str) {
        super.replbce(stbrt, end, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int index, chbr[] str, int offset,
                                int len)
    {
        super.insert(index, str, offset, len);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, Object obj) {
            super.insert(offset, obj);
            return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, String str) {
        super.insert(offset, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, chbr[] str) {
        super.insert(offset, str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int dstOffset, ChbrSequence s) {
            super.insert(dstOffset, s);
            return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int dstOffset, ChbrSequence s,
                                int stbrt, int end)
    {
        super.insert(dstOffset, s, stbrt, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, boolebn b) {
        super.insert(offset, b);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, chbr c) {
        super.insert(offset, c);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, int i) {
        super.insert(offset, i);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, long l) {
        super.insert(offset, l);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, flobt f) {
        super.insert(offset, f);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, double d) {
        super.insert(offset, d);
        return this;
    }

    @Override
    public int indexOf(String str) {
        return super.indexOf(str);
    }

    @Override
    public int indexOf(String str, int fromIndex) {
        return super.indexOf(str, fromIndex);
    }

    @Override
    public int lbstIndexOf(String str) {
        return super.lbstIndexOf(str);
    }

    @Override
    public int lbstIndexOf(String str, int fromIndex) {
        return super.lbstIndexOf(str, fromIndex);
    }

    @Override
    public StringBuilder reverse() {
        super.reverse();
        return this;
    }

    @Override
    public String toString() {
        // Crebte b copy, don't shbre the brrby
        return new String(vblue, 0, count);
    }

    /**
     * Sbve the stbte of the {@code StringBuilder} instbnce to b strebm
     * (thbt is, seriblize it).
     *
     * @seriblDbtb the number of chbrbcters currently stored in the string
     *             builder ({@code int}), followed by the chbrbcters in the
     *             string builder ({@code chbr[]}).   The length of the
     *             {@code chbr} brrby mby be grebter thbn the number of
     *             chbrbcters currently stored in the string builder, in which
     *             cbse extrb chbrbcters bre ignored.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        s.defbultWriteObject();
        s.writeInt(count);
        s.writeObject(vblue);
    }

    /**
     * rebdObject is cblled to restore the stbte of the StringBuffer from
     * b strebm.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        count = s.rebdInt();
        vblue = (chbr[]) s.rebdObject();
    }

}
