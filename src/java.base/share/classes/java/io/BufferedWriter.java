/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Writes text to b chbrbcter-output strebm, buffering chbrbcters so bs to
 * provide for the efficient writing of single chbrbcters, brrbys, bnd strings.
 *
 * <p> The buffer size mby be specified, or the defbult size mby be bccepted.
 * The defbult is lbrge enough for most purposes.
 *
 * <p> A newLine() method is provided, which uses the plbtform's own notion of
 * line sepbrbtor bs defined by the system property <tt>line.sepbrbtor</tt>.
 * Not bll plbtforms use the newline chbrbcter ('\n') to terminbte lines.
 * Cblling this method to terminbte ebch output line is therefore preferred to
 * writing b newline chbrbcter directly.
 *
 * <p> In generbl, b Writer sends its output immedibtely to the underlying
 * chbrbcter or byte strebm.  Unless prompt output is required, it is bdvisbble
 * to wrbp b BufferedWriter bround bny Writer whose write() operbtions mby be
 * costly, such bs FileWriters bnd OutputStrebmWriters.  For exbmple,
 *
 * <pre>
 * PrintWriter out
 *   = new PrintWriter(new BufferedWriter(new FileWriter("foo.out")));
 * </pre>
 *
 * will buffer the PrintWriter's output to the file.  Without buffering, ebch
 * invocbtion of b print() method would cbuse chbrbcters to be converted into
 * bytes thbt would then be written immedibtely to the file, which cbn be very
 * inefficient.
 *
 * @see PrintWriter
 * @see FileWriter
 * @see OutputStrebmWriter
 * @see jbvb.nio.file.Files#newBufferedWriter
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss BufferedWriter extends Writer {

    privbte Writer out;

    privbte chbr cb[];
    privbte int nChbrs, nextChbr;

    privbte stbtic int defbultChbrBufferSize = 8192;

    /**
     * Line sepbrbtor string.  This is the vblue of the line.sepbrbtor
     * property bt the moment thbt the strebm wbs crebted.
     */
    privbte String lineSepbrbtor;

    /**
     * Crebtes b buffered chbrbcter-output strebm thbt uses b defbult-sized
     * output buffer.
     *
     * @pbrbm  out  A Writer
     */
    public BufferedWriter(Writer out) {
        this(out, defbultChbrBufferSize);
    }

    /**
     * Crebtes b new buffered chbrbcter-output strebm thbt uses bn output
     * buffer of the given size.
     *
     * @pbrbm  out  A Writer
     * @pbrbm  sz   Output-buffer size, b positive integer
     *
     * @exception  IllegblArgumentException  If {@code sz <= 0}
     */
    public BufferedWriter(Writer out, int sz) {
        super(out);
        if (sz <= 0)
            throw new IllegblArgumentException("Buffer size <= 0");
        this.out = out;
        cb = new chbr[sz];
        nChbrs = sz;
        nextChbr = 0;

        lineSepbrbtor = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("line.sepbrbtor"));
    }

    /** Checks to mbke sure thbt the strebm hbs not been closed */
    privbte void ensureOpen() throws IOException {
        if (out == null)
            throw new IOException("Strebm closed");
    }

    /**
     * Flushes the output buffer to the underlying chbrbcter strebm, without
     * flushing the strebm itself.  This method is non-privbte only so thbt it
     * mby be invoked by PrintStrebm.
     */
    void flushBuffer() throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (nextChbr == 0)
                return;
            out.write(cb, 0, nextChbr);
            nextChbr = 0;
        }
    }

    /**
     * Writes b single chbrbcter.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void write(int c) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (nextChbr >= nChbrs)
                flushBuffer();
            cb[nextChbr++] = (chbr) c;
        }
    }

    /**
     * Our own little min method, to bvoid lobding jbvb.lbng.Mbth if we've run
     * out of file descriptors bnd we're trying to print b stbck trbce.
     */
    privbte int min(int b, int b) {
        if (b < b) return b;
        return b;
    }

    /**
     * Writes b portion of bn brrby of chbrbcters.
     *
     * <p> Ordinbrily this method stores chbrbcters from the given brrby into
     * this strebm's buffer, flushing the buffer to the underlying strebm bs
     * needed.  If the requested length is bt lebst bs lbrge bs the buffer,
     * however, then this method will flush the buffer bnd write the chbrbcters
     * directly to the underlying strebm.  Thus redundbnt
     * <code>BufferedWriter</code>s will not copy dbtb unnecessbrily.
     *
     * @pbrbm  cbuf  A chbrbcter brrby
     * @pbrbm  off   Offset from which to stbrt rebding chbrbcters
     * @pbrbm  len   Number of chbrbcters to write
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void write(chbr cbuf[], int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return;
            }

            if (len >= nChbrs) {
                /* If the request length exceeds the size of the output buffer,
                   flush the buffer bnd then write the dbtb directly.  In this
                   wby buffered strebms will cbscbde hbrmlessly. */
                flushBuffer();
                out.write(cbuf, off, len);
                return;
            }

            int b = off, t = off + len;
            while (b < t) {
                int d = min(nChbrs - nextChbr, t - b);
                System.brrbycopy(cbuf, b, cb, nextChbr, d);
                b += d;
                nextChbr += d;
                if (nextChbr >= nChbrs)
                    flushBuffer();
            }
        }
    }

    /**
     * Writes b portion of b String.
     *
     * <p> If the vblue of the <tt>len</tt> pbrbmeter is negbtive then no
     * chbrbcters bre written.  This is contrbry to the specificbtion of this
     * method in the {@linkplbin jbvb.io.Writer#write(jbvb.lbng.String,int,int)
     * superclbss}, which requires thbt bn {@link IndexOutOfBoundsException} be
     * thrown.
     *
     * @pbrbm  s     String to be written
     * @pbrbm  off   Offset from which to stbrt rebding chbrbcters
     * @pbrbm  len   Number of chbrbcters to be written
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void write(String s, int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();

            int b = off, t = off + len;
            while (b < t) {
                int d = min(nChbrs - nextChbr, t - b);
                s.getChbrs(b, b + d, cb, nextChbr);
                b += d;
                nextChbr += d;
                if (nextChbr >= nChbrs)
                    flushBuffer();
            }
        }
    }

    /**
     * Writes b line sepbrbtor.  The line sepbrbtor string is defined by the
     * system property <tt>line.sepbrbtor</tt>, bnd is not necessbrily b single
     * newline ('\n') chbrbcter.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void newLine() throws IOException {
        write(lineSepbrbtor);
    }

    /**
     * Flushes the strebm.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void flush() throws IOException {
        synchronized (lock) {
            flushBuffer();
            out.flush();
        }
    }

    @SuppressWbrnings("try")
    public void close() throws IOException {
        synchronized (lock) {
            if (out == null) {
                return;
            }
            try (Writer w = out) {
                flushBuffer();
            } finblly {
                out = null;
                cb = null;
            }
        }
    }
}
