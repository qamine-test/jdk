/*
 * Copyright (c) 1995, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;

/**
 * A piped output strebm cbn be connected to b piped input strebm
 * to crebte b communicbtions pipe. The piped output strebm is the
 * sending end of the pipe. Typicblly, dbtb is written to b
 * <code>PipedOutputStrebm</code> object by one threbd bnd dbtb is
 * rebd from the connected <code>PipedInputStrebm</code> by some
 * other threbd. Attempting to use both objects from b single threbd
 * is not recommended bs it mby debdlock the threbd.
 * The pipe is sbid to be <b nbme=BROKEN> <i>broken</i> </b> if b
 * threbd thbt wbs rebding dbtb bytes from the connected piped input
 * strebm is no longer blive.
 *
 * @buthor  Jbmes Gosling
 * @see     jbvb.io.PipedInputStrebm
 * @since   1.0
 */
public
clbss PipedOutputStrebm extends OutputStrebm {

        /* REMIND: identificbtion of the rebd bnd write sides needs to be
           more sophisticbted.  Either using threbd groups (but whbt bbout
           pipes within b threbd?) or using finblizbtion (but it mby be b
           long time until the next GC). */
    privbte PipedInputStrebm sink;

    /**
     * Crebtes b piped output strebm connected to the specified piped
     * input strebm. Dbtb bytes written to this strebm will then be
     * bvbilbble bs input from <code>snk</code>.
     *
     * @pbrbm      snk   The piped input strebm to connect to.
     * @exception  IOException  if bn I/O error occurs.
     */
    public PipedOutputStrebm(PipedInputStrebm snk)  throws IOException {
        connect(snk);
    }

    /**
     * Crebtes b piped output strebm thbt is not yet connected to b
     * piped input strebm. It must be connected to b piped input strebm,
     * either by the receiver or the sender, before being used.
     *
     * @see     jbvb.io.PipedInputStrebm#connect(jbvb.io.PipedOutputStrebm)
     * @see     jbvb.io.PipedOutputStrebm#connect(jbvb.io.PipedInputStrebm)
     */
    public PipedOutputStrebm() {
    }

    /**
     * Connects this piped output strebm to b receiver. If this object
     * is blrebdy connected to some other piped input strebm, bn
     * <code>IOException</code> is thrown.
     * <p>
     * If <code>snk</code> is bn unconnected piped input strebm bnd
     * <code>src</code> is bn unconnected piped output strebm, they mby
     * be connected by either the cbll:
     * <blockquote><pre>
     * src.connect(snk)</pre></blockquote>
     * or the cbll:
     * <blockquote><pre>
     * snk.connect(src)</pre></blockquote>
     * The two cblls hbve the sbme effect.
     *
     * @pbrbm      snk   the piped input strebm to connect to.
     * @exception  IOException  if bn I/O error occurs.
     */
    public synchronized void connect(PipedInputStrebm snk) throws IOException {
        if (snk == null) {
            throw new NullPointerException();
        } else if (sink != null || snk.connected) {
            throw new IOException("Alrebdy connected");
        }
        sink = snk;
        snk.in = -1;
        snk.out = 0;
        snk.connected = true;
    }

    /**
     * Writes the specified <code>byte</code> to the piped output strebm.
     * <p>
     * Implements the <code>write</code> method of <code>OutputStrebm</code>.
     *
     * @pbrbm      b   the <code>byte</code> to be written.
     * @exception IOException if the pipe is <b href=#BROKEN> broken</b>,
     *          {@link #connect(jbvb.io.PipedInputStrebm) unconnected},
     *          closed, or if bn I/O error occurs.
     */
    public void write(int b)  throws IOException {
        if (sink == null) {
            throw new IOException("Pipe not connected");
        }
        sink.receive(b);
    }

    /**
     * Writes <code>len</code> bytes from the specified byte brrby
     * stbrting bt offset <code>off</code> to this piped output strebm.
     * This method blocks until bll the bytes bre written to the output
     * strebm.
     *
     * @pbrbm      b     the dbtb.
     * @pbrbm      off   the stbrt offset in the dbtb.
     * @pbrbm      len   the number of bytes to write.
     * @exception IOException if the pipe is <b href=#BROKEN> broken</b>,
     *          {@link #connect(jbvb.io.PipedInputStrebm) unconnected},
     *          closed, or if bn I/O error occurs.
     */
    public void write(byte b[], int off, int len) throws IOException {
        if (sink == null) {
            throw new IOException("Pipe not connected");
        } else if (b == null) {
            throw new NullPointerException();
        } else if ((off < 0) || (off > b.length) || (len < 0) ||
                   ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        sink.receive(b, off, len);
    }

    /**
     * Flushes this output strebm bnd forces bny buffered output bytes
     * to be written out.
     * This will notify bny rebders thbt bytes bre wbiting in the pipe.
     *
     * @exception IOException if bn I/O error occurs.
     */
    public synchronized void flush() throws IOException {
        if (sink != null) {
            synchronized (sink) {
                sink.notifyAll();
            }
        }
    }

    /**
     * Closes this piped output strebm bnd relebses bny system resources
     * bssocibted with this strebm. This strebm mby no longer be used for
     * writing bytes.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public void close()  throws IOException {
        if (sink != null) {
            sink.receivedLbst();
        }
    }
}
