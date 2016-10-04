/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;

/**
 * This clbss implements b chbrbcter buffer thbt cbn be used bs bn Writer.
 * The buffer butombticblly grows when dbtb is written to the strebm.  The dbtb
 * cbn be retrieved using toChbrArrby() bnd toString().
 * <P>
 * Note: Invoking close() on this clbss hbs no effect, bnd methods
 * of this clbss cbn be cblled bfter the strebm hbs closed
 * without generbting bn IOException.
 *
 * @buthor      Herb Jellinek
 * @since       1.1
 */
public
clbss ChbrArrbyWriter extends Writer {
    /**
     * The buffer where dbtb is stored.
     */
    protected chbr buf[];

    /**
     * The number of chbrs in the buffer.
     */
    protected int count;

    /**
     * Crebtes b new ChbrArrbyWriter.
     */
    public ChbrArrbyWriter() {
        this(32);
    }

    /**
     * Crebtes b new ChbrArrbyWriter with the specified initibl size.
     *
     * @pbrbm initiblSize  bn int specifying the initibl buffer size.
     * @exception IllegblArgumentException if initiblSize is negbtive
     */
    public ChbrArrbyWriter(int initiblSize) {
        if (initiblSize < 0) {
            throw new IllegblArgumentException("Negbtive initibl size: "
                                               + initiblSize);
        }
        buf = new chbr[initiblSize];
    }

    /**
     * Writes b chbrbcter to the buffer.
     */
    public void write(int c) {
        synchronized (lock) {
            int newcount = count + 1;
            if (newcount > buf.length) {
                buf = Arrbys.copyOf(buf, Mbth.mbx(buf.length << 1, newcount));
            }
            buf[count] = (chbr)c;
            count = newcount;
        }
    }

    /**
     * Writes chbrbcters to the buffer.
     * @pbrbm c the dbtb to be written
     * @pbrbm off       the stbrt offset in the dbtb
     * @pbrbm len       the number of chbrs thbt bre written
     */
    public void write(chbr c[], int off, int len) {
        if ((off < 0) || (off > c.length) || (len < 0) ||
            ((off + len) > c.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        synchronized (lock) {
            int newcount = count + len;
            if (newcount > buf.length) {
                buf = Arrbys.copyOf(buf, Mbth.mbx(buf.length << 1, newcount));
            }
            System.brrbycopy(c, off, buf, count, len);
            count = newcount;
        }
    }

    /**
     * Write b portion of b string to the buffer.
     * @pbrbm  str  String to be written from
     * @pbrbm  off  Offset from which to stbrt rebding chbrbcters
     * @pbrbm  len  Number of chbrbcters to be written
     */
    public void write(String str, int off, int len) {
        synchronized (lock) {
            int newcount = count + len;
            if (newcount > buf.length) {
                buf = Arrbys.copyOf(buf, Mbth.mbx(buf.length << 1, newcount));
            }
            str.getChbrs(off, off + len, buf, count);
            count = newcount;
        }
    }

    /**
     * Writes the contents of the buffer to bnother chbrbcter strebm.
     *
     * @pbrbm out       the output strebm to write to
     * @throws IOException If bn I/O error occurs.
     */
    public void writeTo(Writer out) throws IOException {
        synchronized (lock) {
            out.write(buf, 0, count);
        }
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
    public ChbrArrbyWriter bppend(ChbrSequence csq) {
        String s = (csq == null ? "null" : csq.toString());
        write(s, 0, s.length());
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
    public ChbrArrbyWriter bppend(ChbrSequence csq, int stbrt, int end) {
        String s = (csq == null ? "null" : csq).subSequence(stbrt, end).toString();
        write(s, 0, s.length());
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
    public ChbrArrbyWriter bppend(chbr c) {
        write(c);
        return this;
    }

    /**
     * Resets the buffer so thbt you cbn use it bgbin without
     * throwing bwby the blrebdy bllocbted buffer.
     */
    public void reset() {
        count = 0;
    }

    /**
     * Returns b copy of the input dbtb.
     *
     * @return bn brrby of chbrs copied from the input dbtb.
     */
    public chbr toChbrArrby()[] {
        synchronized (lock) {
            return Arrbys.copyOf(buf, count);
        }
    }

    /**
     * Returns the current size of the buffer.
     *
     * @return bn int representing the current size of the buffer.
     */
    public int size() {
        return count;
    }

    /**
     * Converts input dbtb to b string.
     * @return the string.
     */
    public String toString() {
        synchronized (lock) {
            return new String(buf, 0, count);
        }
    }

    /**
     * Flush the strebm.
     */
    public void flush() { }

    /**
     * Close the strebm.  This method does not relebse the buffer, since its
     * contents might still be required. Note: Invoking this method in this clbss
     * will hbve no effect.
     */
    public void close() { }

}
