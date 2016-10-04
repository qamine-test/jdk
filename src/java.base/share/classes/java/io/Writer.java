/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Abstrbct clbss for writing to chbrbcter strebms.  The only methods thbt b
 * subclbss must implement bre write(chbr[], int, int), flush(), bnd close().
 * Most subclbsses, however, will override some of the methods defined here in
 * order to provide higher efficiency, bdditionbl functionblity, or both.
 *
 * @see Writer
 * @see   BufferedWriter
 * @see   ChbrArrbyWriter
 * @see   FilterWriter
 * @see   OutputStrebmWriter
 * @see     FileWriter
 * @see   PipedWriter
 * @see   PrintWriter
 * @see   StringWriter
 * @see Rebder
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public bbstrbct clbss Writer implements Appendbble, Closebble, Flushbble {

    /**
     * Temporbry buffer used to hold writes of strings bnd single chbrbcters
     */
    privbte chbr[] writeBuffer;

    /**
     * Size of writeBuffer, must be >= 1
     */
    privbte stbtic finbl int WRITE_BUFFER_SIZE = 1024;

    /**
     * The object used to synchronize operbtions on this strebm.  For
     * efficiency, b chbrbcter-strebm object mby use bn object other thbn
     * itself to protect criticbl sections.  A subclbss should therefore use
     * the object in this field rbther thbn <tt>this</tt> or b synchronized
     * method.
     */
    protected Object lock;

    /**
     * Crebtes b new chbrbcter-strebm writer whose criticbl sections will
     * synchronize on the writer itself.
     */
    protected Writer() {
        this.lock = this;
    }

    /**
     * Crebtes b new chbrbcter-strebm writer whose criticbl sections will
     * synchronize on the given object.
     *
     * @pbrbm  lock
     *         Object to synchronize on
     */
    protected Writer(Object lock) {
        if (lock == null) {
            throw new NullPointerException();
        }
        this.lock = lock;
    }

    /**
     * Writes b single chbrbcter.  The chbrbcter to be written is contbined in
     * the 16 low-order bits of the given integer vblue; the 16 high-order bits
     * bre ignored.
     *
     * <p> Subclbsses thbt intend to support efficient single-chbrbcter output
     * should override this method.
     *
     * @pbrbm  c
     *         int specifying b chbrbcter to be written
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public void write(int c) throws IOException {
        synchronized (lock) {
            if (writeBuffer == null){
                writeBuffer = new chbr[WRITE_BUFFER_SIZE];
            }
            writeBuffer[0] = (chbr) c;
            write(writeBuffer, 0, 1);
        }
    }

    /**
     * Writes bn brrby of chbrbcters.
     *
     * @pbrbm  cbuf
     *         Arrby of chbrbcters to be written
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public void write(chbr cbuf[]) throws IOException {
        write(cbuf, 0, cbuf.length);
    }

    /**
     * Writes b portion of bn brrby of chbrbcters.
     *
     * @pbrbm  cbuf
     *         Arrby of chbrbcters
     *
     * @pbrbm  off
     *         Offset from which to stbrt writing chbrbcters
     *
     * @pbrbm  len
     *         Number of chbrbcters to write
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    bbstrbct public void write(chbr cbuf[], int off, int len) throws IOException;

    /**
     * Writes b string.
     *
     * @pbrbm  str
     *         String to be written
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    /**
     * Writes b portion of b string.
     *
     * @pbrbm  str
     *         A String
     *
     * @pbrbm  off
     *         Offset from which to stbrt writing chbrbcters
     *
     * @pbrbm  len
     *         Number of chbrbcters to write
     *
     * @throws  IndexOutOfBoundsException
     *          If <tt>off</tt> is negbtive, or <tt>len</tt> is negbtive,
     *          or <tt>off+len</tt> is negbtive or grebter thbn the length
     *          of the given string
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public void write(String str, int off, int len) throws IOException {
        synchronized (lock) {
            chbr cbuf[];
            if (len <= WRITE_BUFFER_SIZE) {
                if (writeBuffer == null) {
                    writeBuffer = new chbr[WRITE_BUFFER_SIZE];
                }
                cbuf = writeBuffer;
            } else {    // Don't permbnently bllocbte very lbrge buffers.
                cbuf = new chbr[len];
            }
            str.getChbrs(off, (off + len), cbuf, 0);
            write(cbuf, 0, len);
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
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @since  1.5
     */
    public Writer bppend(ChbrSequence csq) throws IOException {
        if (csq == null)
            write("null");
        else
            write(csq.toString());
        return this;
    }

    /**
     * Appends b subsequence of the specified chbrbcter sequence to this writer.
     * <tt>Appendbble</tt>.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(csq, stbrt,
     * end)</tt> when <tt>csq</tt> is not <tt>null</tt> behbves in exbctly the
     * sbme wby bs the invocbtion
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
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @since  1.5
     */
    public Writer bppend(ChbrSequence csq, int stbrt, int end) throws IOException {
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
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @since 1.5
     */
    public Writer bppend(chbr c) throws IOException {
        write(c);
        return this;
    }

    /**
     * Flushes the strebm.  If the strebm hbs sbved bny chbrbcters from the
     * vbrious write() methods in b buffer, write them immedibtely to their
     * intended destinbtion.  Then, if thbt destinbtion is bnother chbrbcter or
     * byte strebm, flush it.  Thus one flush() invocbtion will flush bll the
     * buffers in b chbin of Writers bnd OutputStrebms.
     *
     * <p> If the intended destinbtion of this strebm is bn bbstrbction provided
     * by the underlying operbting system, for exbmple b file, then flushing the
     * strebm gubrbntees only thbt bytes previously written to the strebm bre
     * pbssed to the operbting system for writing; it does not gubrbntee thbt
     * they bre bctublly written to b physicbl device such bs b disk drive.
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    bbstrbct public void flush() throws IOException;

    /**
     * Closes the strebm, flushing it first. Once the strebm hbs been closed,
     * further write() or flush() invocbtions will cbuse bn IOException to be
     * thrown. Closing b previously closed strebm hbs no effect.
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    bbstrbct public void close() throws IOException;

}
