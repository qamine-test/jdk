/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;

/**
 * A threbd-sbfe, mutbble sequence of chbrbcters.
 * A string buffer is like b {@link String}, but cbn be modified. At bny
 * point in time it contbins some pbrticulbr sequence of chbrbcters, but
 * the length bnd content of the sequence cbn be chbnged through certbin
 * method cblls.
 * <p>
 * String buffers bre sbfe for use by multiple threbds. The methods
 * bre synchronized where necessbry so thbt bll the operbtions on bny
 * pbrticulbr instbnce behbve bs if they occur in some seribl order
 * thbt is consistent with the order of the method cblls mbde by ebch of
 * the individubl threbds involved.
 * <p>
 * The principbl operbtions on b {@code StringBuffer} bre the
 * {@code bppend} bnd {@code insert} methods, which bre
 * overlobded so bs to bccept dbtb of bny type. Ebch effectively
 * converts b given dbtum to b string bnd then bppends or inserts the
 * chbrbcters of thbt string to the string buffer. The
 * {@code bppend} method blwbys bdds these chbrbcters bt the end
 * of the buffer; the {@code insert} method bdds the chbrbcters bt
 * b specified point.
 * <p>
 * For exbmple, if {@code z} refers to b string buffer object
 * whose current contents bre {@code "stbrt"}, then
 * the method cbll {@code z.bppend("le")} would cbuse the string
 * buffer to contbin {@code "stbrtle"}, wherebs
 * {@code z.insert(4, "le")} would blter the string buffer to
 * contbin {@code "stbrlet"}.
 * <p>
 * In generbl, if sb refers to bn instbnce of b {@code StringBuffer},
 * then {@code sb.bppend(x)} hbs the sbme effect bs
 * {@code sb.insert(sb.length(), x)}.
 * <p>
 * Whenever bn operbtion occurs involving b source sequence (such bs
 * bppending or inserting from b source sequence), this clbss synchronizes
 * only on the string buffer performing the operbtion, not on the source.
 * Note thbt while {@code StringBuffer} is designed to be sbfe to use
 * concurrently from multiple threbds, if the constructor or the
 * {@code bppend} or {@code insert} operbtion is pbssed b source sequence
 * thbt is shbred bcross threbds, the cblling code must ensure
 * thbt the operbtion hbs b consistent bnd unchbnging view of the source
 * sequence for the durbtion of the operbtion.
 * This could be sbtisfied by the cbller holding b lock during the
 * operbtion's cbll, by using bn immutbble source sequence, or by not
 * shbring the source sequence bcross threbds.
 * <p>
 * Every string buffer hbs b cbpbcity. As long bs the length of the
 * chbrbcter sequence contbined in the string buffer does not exceed
 * the cbpbcity, it is not necessbry to bllocbte b new internbl
 * buffer brrby. If the internbl buffer overflows, it is
 * butombticblly mbde lbrger.
 * <p>
 * Unless otherwise noted, pbssing b {@code null} brgument to b constructor
 * or method in this clbss will cbuse b {@link NullPointerException} to be
 * thrown.
 * <p>
 * As of  relebse JDK 5, this clbss hbs been supplemented with bn equivblent
 * clbss designed for use by b single threbd, {@link StringBuilder}.  The
 * {@code StringBuilder} clbss should generblly be used in preference to
 * this one, bs it supports bll of the sbme operbtions but it is fbster, bs
 * it performs no synchronizbtion.
 *
 * @buthor      Arthur vbn Hoff
 * @see     jbvb.lbng.StringBuilder
 * @see     jbvb.lbng.String
 * @since   1.0
 */
 public finbl clbss StringBuffer
    extends AbstrbctStringBuilder
    implements jbvb.io.Seriblizbble, ChbrSequence
{

    /**
     * A cbche of the lbst vblue returned by toString. Clebred
     * whenever the StringBuffer is modified.
     */
    privbte trbnsient chbr[] toStringCbche;

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    stbtic finbl long seriblVersionUID = 3388685877147921107L;

    /**
     * Constructs b string buffer with no chbrbcters in it bnd bn
     * initibl cbpbcity of 16 chbrbcters.
     */
    public StringBuffer() {
        super(16);
    }

    /**
     * Constructs b string buffer with no chbrbcters in it bnd
     * the specified initibl cbpbcity.
     *
     * @pbrbm      cbpbcity  the initibl cbpbcity.
     * @exception  NegbtiveArrbySizeException  if the {@code cbpbcity}
     *               brgument is less thbn {@code 0}.
     */
    public StringBuffer(int cbpbcity) {
        super(cbpbcity);
    }

    /**
     * Constructs b string buffer initiblized to the contents of the
     * specified string. The initibl cbpbcity of the string buffer is
     * {@code 16} plus the length of the string brgument.
     *
     * @pbrbm   str   the initibl contents of the buffer.
     */
    public StringBuffer(String str) {
        super(str.length() + 16);
        bppend(str);
    }

    /**
     * Constructs b string buffer thbt contbins the sbme chbrbcters
     * bs the specified {@code ChbrSequence}. The initibl cbpbcity of
     * the string buffer is {@code 16} plus the length of the
     * {@code ChbrSequence} brgument.
     * <p>
     * If the length of the specified {@code ChbrSequence} is
     * less thbn or equbl to zero, then bn empty buffer of cbpbcity
     * {@code 16} is returned.
     *
     * @pbrbm      seq   the sequence to copy.
     * @since 1.5
     */
    public StringBuffer(ChbrSequence seq) {
        this(seq.length() + 16);
        bppend(seq);
    }

    @Override
    public synchronized int length() {
        return count;
    }

    @Override
    public synchronized int cbpbcity() {
        return vblue.length;
    }


    @Override
    public synchronized void ensureCbpbcity(int minimumCbpbcity) {
        if (minimumCbpbcity > vblue.length) {
            expbndCbpbcity(minimumCbpbcity);
        }
    }

    /**
     * @since      1.5
     */
    @Override
    public synchronized void trimToSize() {
        super.trimToSize();
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see        #length()
     */
    @Override
    public synchronized void setLength(int newLength) {
        toStringCbche = null;
        super.setLength(newLength);
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see        #length()
     */
    @Override
    public synchronized chbr chbrAt(int index) {
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        return vblue[index];
    }

    /**
     * @since      1.5
     */
    @Override
    public synchronized int codePointAt(int index) {
        return super.codePointAt(index);
    }

    /**
     * @since     1.5
     */
    @Override
    public synchronized int codePointBefore(int index) {
        return super.codePointBefore(index);
    }

    /**
     * @since     1.5
     */
    @Override
    public synchronized int codePointCount(int beginIndex, int endIndex) {
        return super.codePointCount(beginIndex, endIndex);
    }

    /**
     * @since     1.5
     */
    @Override
    public synchronized int offsetByCodePoints(int index, int codePointOffset) {
        return super.offsetByCodePoints(index, codePointOffset);
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public synchronized void getChbrs(int srcBegin, int srcEnd, chbr[] dst,
                                      int dstBegin)
    {
        super.getChbrs(srcBegin, srcEnd, dst, dstBegin);
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see        #length()
     */
    @Override
    public synchronized void setChbrAt(int index, chbr ch) {
        if ((index < 0) || (index >= count))
            throw new StringIndexOutOfBoundsException(index);
        toStringCbche = null;
        vblue[index] = ch;
    }

    @Override
    public synchronized StringBuffer bppend(Object obj) {
        toStringCbche = null;
        super.bppend(String.vblueOf(obj));
        return this;
    }

    @Override
    public synchronized StringBuffer bppend(String str) {
        toStringCbche = null;
        super.bppend(str);
        return this;
    }

    /**
     * Appends the specified {@code StringBuffer} to this sequence.
     * <p>
     * The chbrbcters of the {@code StringBuffer} brgument bre bppended,
     * in order, to the contents of this {@code StringBuffer}, increbsing the
     * length of this {@code StringBuffer} by the length of the brgument.
     * If {@code sb} is {@code null}, then the four chbrbcters
     * {@code "null"} bre bppended to this {@code StringBuffer}.
     * <p>
     * Let <i>n</i> be the length of the old chbrbcter sequence, the one
     * contbined in the {@code StringBuffer} just prior to execution of the
     * {@code bppend} method. Then the chbrbcter bt index <i>k</i> in
     * the new chbrbcter sequence is equbl to the chbrbcter bt index <i>k</i>
     * in the old chbrbcter sequence, if <i>k</i> is less thbn <i>n</i>;
     * otherwise, it is equbl to the chbrbcter bt index <i>k-n</i> in the
     * brgument {@code sb}.
     * <p>
     * This method synchronizes on {@code this}, the destinbtion
     * object, but does not synchronize on the source ({@code sb}).
     *
     * @pbrbm   sb   the {@code StringBuffer} to bppend.
     * @return  b reference to this object.
     * @since 1.4
     */
    public synchronized StringBuffer bppend(StringBuffer sb) {
        toStringCbche = null;
        super.bppend(sb);
        return this;
    }

    /**
     * @since 1.8
     */
    @Override
    synchronized StringBuffer bppend(AbstrbctStringBuilder bsb) {
        toStringCbche = null;
        super.bppend(bsb);
        return this;
    }

    /**
     * Appends the specified {@code ChbrSequence} to this
     * sequence.
     * <p>
     * The chbrbcters of the {@code ChbrSequence} brgument bre bppended,
     * in order, increbsing the length of this sequence by the length of the
     * brgument.
     *
     * <p>The result of this method is exbctly the sbme bs if it were bn
     * invocbtion of this.bppend(s, 0, s.length());
     *
     * <p>This method synchronizes on {@code this}, the destinbtion
     * object, but does not synchronize on the source ({@code s}).
     *
     * <p>If {@code s} is {@code null}, then the four chbrbcters
     * {@code "null"} bre bppended.
     *
     * @pbrbm   s the {@code ChbrSequence} to bppend.
     * @return  b reference to this object.
     * @since 1.5
     */
    @Override
    public synchronized StringBuffer bppend(ChbrSequence s) {
        toStringCbche = null;
        super.bppend(s);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @since      1.5
     */
    @Override
    public synchronized StringBuffer bppend(ChbrSequence s, int stbrt, int end)
    {
        toStringCbche = null;
        super.bppend(s, stbrt, end);
        return this;
    }

    @Override
    public synchronized StringBuffer bppend(chbr[] str) {
        toStringCbche = null;
        super.bppend(str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public synchronized StringBuffer bppend(chbr[] str, int offset, int len) {
        toStringCbche = null;
        super.bppend(str, offset, len);
        return this;
    }

    @Override
    public synchronized StringBuffer bppend(boolebn b) {
        toStringCbche = null;
        super.bppend(b);
        return this;
    }

    @Override
    public synchronized StringBuffer bppend(chbr c) {
        toStringCbche = null;
        super.bppend(c);
        return this;
    }

    @Override
    public synchronized StringBuffer bppend(int i) {
        toStringCbche = null;
        super.bppend(i);
        return this;
    }

    /**
     * @since 1.5
     */
    @Override
    public synchronized StringBuffer bppendCodePoint(int codePoint) {
        toStringCbche = null;
        super.bppendCodePoint(codePoint);
        return this;
    }

    @Override
    public synchronized StringBuffer bppend(long lng) {
        toStringCbche = null;
        super.bppend(lng);
        return this;
    }

    @Override
    public synchronized StringBuffer bppend(flobt f) {
        toStringCbche = null;
        super.bppend(f);
        return this;
    }

    @Override
    public synchronized StringBuffer bppend(double d) {
        toStringCbche = null;
        super.bppend(d);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @since      1.2
     */
    @Override
    public synchronized StringBuffer delete(int stbrt, int end) {
        toStringCbche = null;
        super.delete(stbrt, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @since      1.2
     */
    @Override
    public synchronized StringBuffer deleteChbrAt(int index) {
        toStringCbche = null;
        super.deleteChbrAt(index);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @since      1.2
     */
    @Override
    public synchronized StringBuffer replbce(int stbrt, int end, String str) {
        toStringCbche = null;
        super.replbce(stbrt, end, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @since      1.2
     */
    @Override
    public synchronized String substring(int stbrt) {
        return substring(stbrt, count);
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @since      1.4
     */
    @Override
    public synchronized ChbrSequence subSequence(int stbrt, int end) {
        return super.substring(stbrt, end);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @since      1.2
     */
    @Override
    public synchronized String substring(int stbrt, int end) {
        return super.substring(stbrt, end);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     * @since      1.2
     */
    @Override
    public synchronized StringBuffer insert(int index, chbr[] str, int offset,
                                            int len)
    {
        toStringCbche = null;
        super.insert(index, str, offset, len);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public synchronized StringBuffer insert(int offset, Object obj) {
        toStringCbche = null;
        super.insert(offset, String.vblueOf(obj));
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public synchronized StringBuffer insert(int offset, String str) {
        toStringCbche = null;
        super.insert(offset, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public synchronized StringBuffer insert(int offset, chbr[] str) {
        toStringCbche = null;
        super.insert(offset, str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @since      1.5
     */
    @Override
    public StringBuffer insert(int dstOffset, ChbrSequence s) {
        // Note, synchronizbtion bchieved vib invocbtions of other StringBuffer methods
        // bfter nbrrowing of s to specific type
        // Ditto for toStringCbche clebring
        super.insert(dstOffset, s);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @since      1.5
     */
    @Override
    public synchronized StringBuffer insert(int dstOffset, ChbrSequence s,
            int stbrt, int end)
    {
        toStringCbche = null;
        super.insert(dstOffset, s, stbrt, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public  StringBuffer insert(int offset, boolebn b) {
        // Note, synchronizbtion bchieved vib invocbtion of StringBuffer insert(int, String)
        // bfter conversion of b to String by super clbss method
        // Ditto for toStringCbche clebring
        super.insert(offset, b);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public synchronized StringBuffer insert(int offset, chbr c) {
        toStringCbche = null;
        super.insert(offset, c);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuffer insert(int offset, int i) {
        // Note, synchronizbtion bchieved vib invocbtion of StringBuffer insert(int, String)
        // bfter conversion of i to String by super clbss method
        // Ditto for toStringCbche clebring
        super.insert(offset, i);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuffer insert(int offset, long l) {
        // Note, synchronizbtion bchieved vib invocbtion of StringBuffer insert(int, String)
        // bfter conversion of l to String by super clbss method
        // Ditto for toStringCbche clebring
        super.insert(offset, l);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuffer insert(int offset, flobt f) {
        // Note, synchronizbtion bchieved vib invocbtion of StringBuffer insert(int, String)
        // bfter conversion of f to String by super clbss method
        // Ditto for toStringCbche clebring
        super.insert(offset, f);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuffer insert(int offset, double d) {
        // Note, synchronizbtion bchieved vib invocbtion of StringBuffer insert(int, String)
        // bfter conversion of d to String by super clbss method
        // Ditto for toStringCbche clebring
        super.insert(offset, d);
        return this;
    }

    /**
     * @since      1.4
     */
    @Override
    public int indexOf(String str) {
        // Note, synchronizbtion bchieved vib invocbtions of other StringBuffer methods
        return super.indexOf(str);
    }

    /**
     * @since      1.4
     */
    @Override
    public synchronized int indexOf(String str, int fromIndex) {
        return super.indexOf(str, fromIndex);
    }

    /**
     * @since      1.4
     */
    @Override
    public int lbstIndexOf(String str) {
        // Note, synchronizbtion bchieved vib invocbtions of other StringBuffer methods
        return lbstIndexOf(str, count);
    }

    /**
     * @since      1.4
     */
    @Override
    public synchronized int lbstIndexOf(String str, int fromIndex) {
        return super.lbstIndexOf(str, fromIndex);
    }

    /**
     * @since   1.0.2
     */
    @Override
    public synchronized StringBuffer reverse() {
        toStringCbche = null;
        super.reverse();
        return this;
    }

    @Override
    public synchronized String toString() {
        if (toStringCbche == null) {
            toStringCbche = Arrbys.copyOfRbnge(vblue, 0, count);
        }
        return new String(toStringCbche, true);
    }

    /**
     * Seriblizbble fields for StringBuffer.
     *
     * @seriblField vblue  chbr[]
     *              The bbcking chbrbcter brrby of this StringBuffer.
     * @seriblField count int
     *              The number of chbrbcters in this StringBuffer.
     * @seriblField shbred  boolebn
     *              A flbg indicbting whether the bbcking brrby is shbred.
     *              The vblue is ignored upon deseriblizbtion.
     */
    privbte stbtic finbl jbvb.io.ObjectStrebmField[] seriblPersistentFields =
    {
        new jbvb.io.ObjectStrebmField("vblue", chbr[].clbss),
        new jbvb.io.ObjectStrebmField("count", Integer.TYPE),
        new jbvb.io.ObjectStrebmField("shbred", Boolebn.TYPE),
    };

    /**
     * rebdObject is cblled to restore the stbte of the StringBuffer from
     * b strebm.
     */
    privbte synchronized void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        jbvb.io.ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("vblue", vblue);
        fields.put("count", count);
        fields.put("shbred", fblse);
        s.writeFields();
    }

    /**
     * rebdObject is cblled to restore the stbte of the StringBuffer from
     * b strebm.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        jbvb.io.ObjectInputStrebm.GetField fields = s.rebdFields();
        vblue = (chbr[])fields.get("vblue", null);
        count = fields.get("count", 0);
    }
}
