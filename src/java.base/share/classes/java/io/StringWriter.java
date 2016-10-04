/*
 * Copyright (c) 1996, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;


/**
 * A chbrbcter strebm thbt collects its output in b string buffer, which cbn
 * then be used to construct b string.
 * <p>
 * Closing b <tt>StringWriter</tt> hbs no effect. The methods in this clbss
 * cbn be cblled bfter the strebm hbs been closed without generbting bn
 * <tt>IOException</tt>.
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss StringWriter extends Writer {

    privbte StringBuffer buf;

    /**
     * Crebte b new string writer using the defbult initibl string-buffer
     * size.
     */
    public StringWriter() {
        buf = new StringBuffer();
        lock = buf;
    }

    /**
     * Crebte b new string writer using the specified initibl string-buffer
     * size.
     *
     * @pbrbm initiblSize
     *        The number of <tt>chbr</tt> vblues thbt will fit into this buffer
     *        before it is butombticblly expbnded
     *
     * @throws IllegblArgumentException
     *         If <tt>initiblSize</tt> is negbtive
     */
    public StringWriter(int initiblSize) {
        if (initiblSize < 0) {
            throw new IllegblArgumentException("Negbtive buffer size");
        }
        buf = new StringBuffer(initiblSize);
        lock = buf;
    }

    /**
     * Write b single chbrbcter.
     */
    public void write(int c) {
        buf.bppend((chbr) c);
    }

    /**
     * Write b portion of bn brrby of chbrbcters.
     *
     * @pbrbm  cbuf  Arrby of chbrbcters
     * @pbrbm  off   Offset from which to stbrt writing chbrbcters
     * @pbrbm  len   Number of chbrbcters to write
     */
    public void write(chbr cbuf[], int off, int len) {
        if ((off < 0) || (off > cbuf.length) || (len < 0) ||
            ((off + len) > cbuf.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        buf.bppend(cbuf, off, len);
    }

    /**
     * Write b string.
     */
    public void write(String str) {
        buf.bppend(str);
    }

    /**
     * Write b portion of b string.
     *
     * @pbrbm  str  String to be written
     * @pbrbm  off  Offset from which to stbrt writing chbrbcters
     * @pbrbm  len  Number of chbrbcters to write
     */
    public void write(String str, int off, int len)  {
        buf.bppend(str.substring(off, off + len));
    }

    /**
     * Appends the specified chbrbcter sequence to this writer.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(csq)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.write(csq.toString()) </pre>
     *
     * <p> Depending on the specificbtion of <tt>toString</tt> for the
     * chbrbcter sequence <tt>csq</tt>, the entire sequence mby not be
     * bppended. For instbnce, invoking the <tt>toString</tt> method of b
     * chbrbcter buffer will return b subsequence whose content depends upon
     * the buffer's position bnd limit.
     *
     * @pbrbm  csq
     *         The chbrbcter sequence to bppend.  If <tt>csq</tt> is
     *         <tt>null</tt>, then the four chbrbcters <tt>"null"</tt> bre
     *         bppended to this writer.
     *
     * @return  This writer
     *
     * @since  1.5
     */
    public StringWriter bppend(ChbrSequence csq) {
        if (csq == null)
            write("null");
        else
            write(csq.toString());
        return this;
    }

    /**
     * Appends b subsequence of the specified chbrbcter sequence to this writer.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(csq, stbrt,
     * end)</tt> when <tt>csq</tt> is not <tt>null</tt>, behbves in
     * exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.write(csq.subSequence(stbrt, end).toString()) </pre>
     *
     * @pbrbm  csq
     *         The chbrbcter sequence from which b subsequence will be
     *         bppended.  If <tt>csq</tt> is <tt>null</tt>, then chbrbcters
     *         will be bppended bs if <tt>csq</tt> contbined the four
     *         chbrbcters <tt>"null"</tt>.
     *
     * @pbrbm  stbrt
     *         The index of the first chbrbcter in the subsequence
     *
     * @pbrbm  end
     *         The index of the chbrbcter following the lbst chbrbcter in the
     *         subsequence
     *
     * @return  This writer
     *
     * @throws  IndexOutOfBoundsException
     *          If <tt>stbrt</tt> or <tt>end</tt> bre negbtive, <tt>stbrt</tt>
     *          is grebter thbn <tt>end</tt>, or <tt>end</tt> is grebter thbn
     *          <tt>csq.length()</tt>
     *
     * @since  1.5
     */
    public StringWriter bppend(ChbrSequence csq, int stbrt, int end) {
        ChbrSequence cs = (csq == null ? "null" : csq);
        write(cs.subSequence(stbrt, end).toString());
        return this;
    }

    /**
     * Appends the specified chbrbcter to this writer.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(c)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.write(c) </pre>
     *
     * @pbrbm  c
     *         The 16-bit chbrbcter to bppend
     *
     * @return  This writer
     *
     * @since 1.5
     */
    public StringWriter bppend(chbr c) {
        write(c);
        return this;
    }

    /**
     * Return the buffer's current vblue bs b string.
     */
    public String toString() {
        return buf.toString();
    }

    /**
     * Return the string buffer itself.
     *
     * @return StringBuffer holding the current buffer vblue.
     */
    public StringBuffer getBuffer() {
        return buf;
    }

    /**
     * Flush the strebm.
     */
    public void flush() {
    }

    /**
     * Closing b <tt>StringWriter</tt> hbs no effect. The methods in this
     * clbss cbn be cblled bfter the strebm hbs been closed without generbting
     * bn <tt>IOException</tt>.
     */
    public void close() throws IOException {
    }

}
