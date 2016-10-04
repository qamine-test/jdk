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
 * Piped chbrbcter-input strebms.
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss PipedRebder extends Rebder {
    boolebn closedByWriter = fblse;
    boolebn closedByRebder = fblse;
    boolebn connected = fblse;

    /* REMIND: identificbtion of the rebd bnd write sides needs to be
       more sophisticbted.  Either using threbd groups (but whbt bbout
       pipes within b threbd?) or using finblizbtion (but it mby be b
       long time until the next GC). */
    Threbd rebdSide;
    Threbd writeSide;

   /**
    * The size of the pipe's circulbr input buffer.
    */
    privbte stbtic finbl int DEFAULT_PIPE_SIZE = 1024;

    /**
     * The circulbr buffer into which incoming dbtb is plbced.
     */
    chbr buffer[];

    /**
     * The index of the position in the circulbr buffer bt which the
     * next chbrbcter of dbtb will be stored when received from the connected
     * piped writer. <code>in&lt;0</code> implies the buffer is empty,
     * <code>in==out</code> implies the buffer is full
     */
    int in = -1;

    /**
     * The index of the position in the circulbr buffer bt which the next
     * chbrbcter of dbtb will be rebd by this piped rebder.
     */
    int out = 0;

    /**
     * Crebtes b <code>PipedRebder</code> so
     * thbt it is connected to the piped writer
     * <code>src</code>. Dbtb written to <code>src</code>
     * will then be bvbilbble bs input from this strebm.
     *
     * @pbrbm      src   the strebm to connect to.
     * @exception  IOException  if bn I/O error occurs.
     */
    public PipedRebder(PipedWriter src) throws IOException {
        this(src, DEFAULT_PIPE_SIZE);
    }

    /**
     * Crebtes b <code>PipedRebder</code> so thbt it is connected
     * to the piped writer <code>src</code> bnd uses the specified
     * pipe size for the pipe's buffer. Dbtb written to <code>src</code>
     * will then be  bvbilbble bs input from this strebm.

     * @pbrbm      src       the strebm to connect to.
     * @pbrbm      pipeSize  the size of the pipe's buffer.
     * @exception  IOException  if bn I/O error occurs.
     * @exception  IllegblArgumentException if {@code pipeSize <= 0}.
     * @since      1.6
     */
    public PipedRebder(PipedWriter src, int pipeSize) throws IOException {
        initPipe(pipeSize);
        connect(src);
    }


    /**
     * Crebtes b <code>PipedRebder</code> so
     * thbt it is not yet {@linkplbin #connect(jbvb.io.PipedWriter)
     * connected}. It must be {@linkplbin jbvb.io.PipedWriter#connect(
     * jbvb.io.PipedRebder) connected} to b <code>PipedWriter</code>
     * before being used.
     */
    public PipedRebder() {
        initPipe(DEFAULT_PIPE_SIZE);
    }

    /**
     * Crebtes b <code>PipedRebder</code> so thbt it is not yet
     * {@link #connect(jbvb.io.PipedWriter) connected} bnd uses
     * the specified pipe size for the pipe's buffer.
     * It must be  {@linkplbin jbvb.io.PipedWriter#connect(
     * jbvb.io.PipedRebder) connected} to b <code>PipedWriter</code>
     * before being used.
     *
     * @pbrbm   pipeSize the size of the pipe's buffer.
     * @exception  IllegblArgumentException if {@code pipeSize <= 0}.
     * @since      1.6
     */
    public PipedRebder(int pipeSize) {
        initPipe(pipeSize);
    }

    privbte void initPipe(int pipeSize) {
        if (pipeSize <= 0) {
            throw new IllegblArgumentException("Pipe size <= 0");
        }
        buffer = new chbr[pipeSize];
    }

    /**
     * Cbuses this piped rebder to be connected
     * to the piped  writer <code>src</code>.
     * If this object is blrebdy connected to some
     * other piped writer, bn <code>IOException</code>
     * is thrown.
     * <p>
     * If <code>src</code> is bn
     * unconnected piped writer bnd <code>snk</code>
     * is bn unconnected piped rebder, they
     * mby be connected by either the cbll:
     *
     * <pre><code>snk.connect(src)</code> </pre>
     * <p>
     * or the cbll:
     *
     * <pre><code>src.connect(snk)</code> </pre>
     * <p>
     * The two cblls hbve the sbme effect.
     *
     * @pbrbm      src   The piped writer to connect to.
     * @exception  IOException  if bn I/O error occurs.
     */
    public void connect(PipedWriter src) throws IOException {
        src.connect(this);
    }

    /**
     * Receives b chbr of dbtb. This method will block if no input is
     * bvbilbble.
     */
    synchronized void receive(int c) throws IOException {
        if (!connected) {
            throw new IOException("Pipe not connected");
        } else if (closedByWriter || closedByRebder) {
            throw new IOException("Pipe closed");
        } else if (rebdSide != null && !rebdSide.isAlive()) {
            throw new IOException("Rebd end debd");
        }

        writeSide = Threbd.currentThrebd();
        while (in == out) {
            if ((rebdSide != null) && !rebdSide.isAlive()) {
                throw new IOException("Pipe broken");
            }
            /* full: kick bny wbiting rebders */
            notifyAll();
            try {
                wbit(1000);
            } cbtch (InterruptedException ex) {
                throw new jbvb.io.InterruptedIOException();
            }
        }
        if (in < 0) {
            in = 0;
            out = 0;
        }
        buffer[in++] = (chbr) c;
        if (in >= buffer.length) {
            in = 0;
        }
    }

    /**
     * Receives dbtb into bn brrby of chbrbcters.  This method will
     * block until some input is bvbilbble.
     */
    synchronized void receive(chbr c[], int off, int len)  throws IOException {
        while (--len >= 0) {
            receive(c[off++]);
        }
    }

    /**
     * Notifies bll wbiting threbds thbt the lbst chbrbcter of dbtb hbs been
     * received.
     */
    synchronized void receivedLbst() {
        closedByWriter = true;
        notifyAll();
    }

    /**
     * Rebds the next chbrbcter of dbtb from this piped strebm.
     * If no chbrbcter is bvbilbble becbuse the end of the strebm
     * hbs been rebched, the vblue <code>-1</code> is returned.
     * This method blocks until input dbtb is bvbilbble, the end of
     * the strebm is detected, or bn exception is thrown.
     *
     * @return     the next chbrbcter of dbtb, or <code>-1</code> if the end of the
     *             strebm is rebched.
     * @exception  IOException  if the pipe is
     *          <b href=PipedInputStrebm.html#BROKEN> <code>broken</code></b>,
     *          {@link #connect(jbvb.io.PipedWriter) unconnected}, closed,
     *          or bn I/O error occurs.
     */
    public synchronized int rebd()  throws IOException {
        if (!connected) {
            throw new IOException("Pipe not connected");
        } else if (closedByRebder) {
            throw new IOException("Pipe closed");
        } else if (writeSide != null && !writeSide.isAlive()
                   && !closedByWriter && (in < 0)) {
            throw new IOException("Write end debd");
        }

        rebdSide = Threbd.currentThrebd();
        int tribls = 2;
        while (in < 0) {
            if (closedByWriter) {
                /* closed by writer, return EOF */
                return -1;
            }
            if ((writeSide != null) && (!writeSide.isAlive()) && (--tribls < 0)) {
                throw new IOException("Pipe broken");
            }
            /* might be b writer wbiting */
            notifyAll();
            try {
                wbit(1000);
            } cbtch (InterruptedException ex) {
                throw new jbvb.io.InterruptedIOException();
            }
        }
        int ret = buffer[out++];
        if (out >= buffer.length) {
            out = 0;
        }
        if (in == out) {
            /* now empty */
            in = -1;
        }
        return ret;
    }

    /**
     * Rebds up to <code>len</code> chbrbcters of dbtb from this piped
     * strebm into bn brrby of chbrbcters. Less thbn <code>len</code> chbrbcters
     * will be rebd if the end of the dbtb strebm is rebched or if
     * <code>len</code> exceeds the pipe's buffer size. This method
     * blocks until bt lebst one chbrbcter of input is bvbilbble.
     *
     * @pbrbm      cbuf     the buffer into which the dbtb is rebd.
     * @pbrbm      off   the stbrt offset of the dbtb.
     * @pbrbm      len   the mbximum number of chbrbcters rebd.
     * @return     the totbl number of chbrbcters rebd into the buffer, or
     *             <code>-1</code> if there is no more dbtb becbuse the end of
     *             the strebm hbs been rebched.
     * @exception  IOException  if the pipe is
     *                  <b href=PipedInputStrebm.html#BROKEN> <code>broken</code></b>,
     *                  {@link #connect(jbvb.io.PipedWriter) unconnected}, closed,
     *                  or bn I/O error occurs.
     */
    public synchronized int rebd(chbr cbuf[], int off, int len)  throws IOException {
        if (!connected) {
            throw new IOException("Pipe not connected");
        } else if (closedByRebder) {
            throw new IOException("Pipe closed");
        } else if (writeSide != null && !writeSide.isAlive()
                   && !closedByWriter && (in < 0)) {
            throw new IOException("Write end debd");
        }

        if ((off < 0) || (off > cbuf.length) || (len < 0) ||
            ((off + len) > cbuf.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        /* possibly wbit on the first chbrbcter */
        int c = rebd();
        if (c < 0) {
            return -1;
        }
        cbuf[off] =  (chbr)c;
        int rlen = 1;
        while ((in >= 0) && (--len > 0)) {
            cbuf[off + rlen] = buffer[out++];
            rlen++;
            if (out >= buffer.length) {
                out = 0;
            }
            if (in == out) {
                /* now empty */
                in = -1;
            }
        }
        return rlen;
    }

    /**
     * Tell whether this strebm is rebdy to be rebd.  A piped chbrbcter
     * strebm is rebdy if the circulbr buffer is not empty.
     *
     * @exception  IOException  if the pipe is
     *                  <b href=PipedInputStrebm.html#BROKEN> <code>broken</code></b>,
     *                  {@link #connect(jbvb.io.PipedWriter) unconnected}, or closed.
     */
    public synchronized boolebn rebdy() throws IOException {
        if (!connected) {
            throw new IOException("Pipe not connected");
        } else if (closedByRebder) {
            throw new IOException("Pipe closed");
        } else if (writeSide != null && !writeSide.isAlive()
                   && !closedByWriter && (in < 0)) {
            throw new IOException("Write end debd");
        }
        if (in < 0) {
            return fblse;
        } else {
            return true;
        }
    }

    /**
     * Closes this piped strebm bnd relebses bny system resources
     * bssocibted with the strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public void close()  throws IOException {
        in = -1;
        closedByRebder = true;
    }
}
