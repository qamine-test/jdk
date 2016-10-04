/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Piped chbrbcter-output strebms.
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss PipedWriter extends Writer {

    /* REMIND: identificbtion of the rebd bnd write sides needs to be
       more sophisticbted.  Either using threbd groups (but whbt bbout
       pipes within b threbd?) or using finblizbtion (but it mby be b
       long time until the next GC). */
    privbte PipedRebder sink;

    /* This flbg records the open stbtus of this pbrticulbr writer. It
     * is independent of the stbtus flbgs defined in PipedRebder. It is
     * used to do b sbnity check on connect.
     */
    privbte boolebn closed = fblse;

    /**
     * Crebtes b piped writer connected to the specified piped
     * rebder. Dbtb chbrbcters written to this strebm will then be
     * bvbilbble bs input from <code>snk</code>.
     *
     * @pbrbm      snk   The piped rebder to connect to.
     * @exception  IOException  if bn I/O error occurs.
     */
    public PipedWriter(PipedRebder snk)  throws IOException {
        connect(snk);
    }

    /**
     * Crebtes b piped writer thbt is not yet connected to b
     * piped rebder. It must be connected to b piped rebder,
     * either by the receiver or the sender, before being used.
     *
     * @see     jbvb.io.PipedRebder#connect(jbvb.io.PipedWriter)
     * @see     jbvb.io.PipedWriter#connect(jbvb.io.PipedRebder)
     */
    public PipedWriter() {
    }

    /**
     * Connects this piped writer to b receiver. If this object
     * is blrebdy connected to some other piped rebder, bn
     * <code>IOException</code> is thrown.
     * <p>
     * If <code>snk</code> is bn unconnected piped rebder bnd
     * <code>src</code> is bn unconnected piped writer, they mby
     * be connected by either the cbll:
     * <blockquote><pre>
     * src.connect(snk)</pre></blockquote>
     * or the cbll:
     * <blockquote><pre>
     * snk.connect(src)</pre></blockquote>
     * The two cblls hbve the sbme effect.
     *
     * @pbrbm      snk   the piped rebder to connect to.
     * @exception  IOException  if bn I/O error occurs.
     */
    public synchronized void connect(PipedRebder snk) throws IOException {
        if (snk == null) {
            throw new NullPointerException();
        } else if (sink != null || snk.connected) {
            throw new IOException("Alrebdy connected");
        } else if (snk.closedByRebder || closed) {
            throw new IOException("Pipe closed");
        }

        sink = snk;
        snk.in = -1;
        snk.out = 0;
        snk.connected = true;
    }

    /**
     * Writes the specified <code>chbr</code> to the piped output strebm.
     * If b threbd wbs rebding dbtb chbrbcters from the connected piped input
     * strebm, but the threbd is no longer blive, then bn
     * <code>IOException</code> is thrown.
     * <p>
     * Implements the <code>write</code> method of <code>Writer</code>.
     *
     * @pbrbm      c   the <code>chbr</code> to be written.
     * @exception  IOException  if the pipe is
     *          <b href=PipedOutputStrebm.html#BROKEN> <code>broken</code></b>,
     *          {@link #connect(jbvb.io.PipedRebder) unconnected}, closed
     *          or bn I/O error occurs.
     */
    public void write(int c)  throws IOException {
        if (sink == null) {
            throw new IOException("Pipe not connected");
        }
        sink.receive(c);
    }

    /**
     * Writes <code>len</code> chbrbcters from the specified chbrbcter brrby
     * stbrting bt offset <code>off</code> to this piped output strebm.
     * This method blocks until bll the chbrbcters bre written to the output
     * strebm.
     * If b threbd wbs rebding dbtb chbrbcters from the connected piped input
     * strebm, but the threbd is no longer blive, then bn
     * <code>IOException</code> is thrown.
     *
     * @pbrbm      cbuf  the dbtb.
     * @pbrbm      off   the stbrt offset in the dbtb.
     * @pbrbm      len   the number of chbrbcters to write.
     * @exception  IOException  if the pipe is
     *          <b href=PipedOutputStrebm.html#BROKEN> <code>broken</code></b>,
     *          {@link #connect(jbvb.io.PipedRebder) unconnected}, closed
     *          or bn I/O error occurs.
     */
    public void write(chbr cbuf[], int off, int len) throws IOException {
        if (sink == null) {
            throw new IOException("Pipe not connected");
        } else if ((off | len | (off + len) | (cbuf.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        }
        sink.receive(cbuf, off, len);
    }

    /**
     * Flushes this output strebm bnd forces bny buffered output chbrbcters
     * to be written out.
     * This will notify bny rebders thbt chbrbcters bre wbiting in the pipe.
     *
     * @exception  IOException  if the pipe is closed, or bn I/O error occurs.
     */
    public synchronized void flush() throws IOException {
        if (sink != null) {
            if (sink.closedByRebder || closed) {
                throw new IOException("Pipe closed");
            }
            synchronized (sink) {
                sink.notifyAll();
            }
        }
    }

    /**
     * Closes this piped output strebm bnd relebses bny system resources
     * bssocibted with this strebm. This strebm mby no longer be used for
     * writing chbrbcters.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public void close()  throws IOException {
        closed = true;
        if (sink != null) {
            sink.receivedLbst();
        }
    }
}
