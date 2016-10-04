/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A piped input strebm should be connected
 * to b piped output strebm; the piped  input
 * strebm then provides whbtever dbtb bytes
 * bre written to the piped output  strebm.
 * Typicblly, dbtb is rebd from b <code>PipedInputStrebm</code>
 * object by one threbd  bnd dbtb is written
 * to the corresponding <code>PipedOutputStrebm</code>
 * by some  other threbd. Attempting to use
 * both objects from b single threbd is not
 * recommended, bs it mby debdlock the threbd.
 * The piped input strebm contbins b buffer,
 * decoupling rebd operbtions from write operbtions,
 * within limits.
 * A pipe is sbid to be <b nbme="BROKEN"> <i>broken</i> </b> if b
 * threbd thbt wbs providing dbtb bytes to the connected
 * piped output strebm is no longer blive.
 *
 * @buthor  Jbmes Gosling
 * @see     jbvb.io.PipedOutputStrebm
 * @since   1.0
 */
public clbss PipedInputStrebm extends InputStrebm {
    boolebn closedByWriter = fblse;
    volbtile boolebn closedByRebder = fblse;
    boolebn connected = fblse;

        /* REMIND: identificbtion of the rebd bnd write sides needs to be
           more sophisticbted.  Either using threbd groups (but whbt bbout
           pipes within b threbd?) or using finblizbtion (but it mby be b
           long time until the next GC). */
    Threbd rebdSide;
    Threbd writeSide;

    privbte stbtic finbl int DEFAULT_PIPE_SIZE = 1024;

    /**
     * The defbult size of the pipe's circulbr input buffer.
     * @since   1.1
     */
    // This used to be b constbnt before the pipe size wbs bllowed
    // to chbnge. This field will continue to be mbintbined
    // for bbckwbrd compbtibility.
    protected stbtic finbl int PIPE_SIZE = DEFAULT_PIPE_SIZE;

    /**
     * The circulbr buffer into which incoming dbtb is plbced.
     * @since   1.1
     */
    protected byte buffer[];

    /**
     * The index of the position in the circulbr buffer bt which the
     * next byte of dbtb will be stored when received from the connected
     * piped output strebm. <code>in&lt;0</code> implies the buffer is empty,
     * <code>in==out</code> implies the buffer is full
     * @since   1.1
     */
    protected int in = -1;

    /**
     * The index of the position in the circulbr buffer bt which the next
     * byte of dbtb will be rebd by this piped input strebm.
     * @since   1.1
     */
    protected int out = 0;

    /**
     * Crebtes b <code>PipedInputStrebm</code> so
     * thbt it is connected to the piped output
     * strebm <code>src</code>. Dbtb bytes written
     * to <code>src</code> will then be  bvbilbble
     * bs input from this strebm.
     *
     * @pbrbm      src   the strebm to connect to.
     * @exception  IOException  if bn I/O error occurs.
     */
    public PipedInputStrebm(PipedOutputStrebm src) throws IOException {
        this(src, DEFAULT_PIPE_SIZE);
    }

    /**
     * Crebtes b <code>PipedInputStrebm</code> so thbt it is
     * connected to the piped output strebm
     * <code>src</code> bnd uses the specified pipe size for
     * the pipe's buffer.
     * Dbtb bytes written to <code>src</code> will then
     * be bvbilbble bs input from this strebm.
     *
     * @pbrbm      src   the strebm to connect to.
     * @pbrbm      pipeSize the size of the pipe's buffer.
     * @exception  IOException  if bn I/O error occurs.
     * @exception  IllegblArgumentException if {@code pipeSize <= 0}.
     * @since      1.6
     */
    public PipedInputStrebm(PipedOutputStrebm src, int pipeSize)
            throws IOException {
         initPipe(pipeSize);
         connect(src);
    }

    /**
     * Crebtes b <code>PipedInputStrebm</code> so
     * thbt it is not yet {@linkplbin #connect(jbvb.io.PipedOutputStrebm)
     * connected}.
     * It must be {@linkplbin jbvb.io.PipedOutputStrebm#connect(
     * jbvb.io.PipedInputStrebm) connected} to b
     * <code>PipedOutputStrebm</code> before being used.
     */
    public PipedInputStrebm() {
        initPipe(DEFAULT_PIPE_SIZE);
    }

    /**
     * Crebtes b <code>PipedInputStrebm</code> so thbt it is not yet
     * {@linkplbin #connect(jbvb.io.PipedOutputStrebm) connected} bnd
     * uses the specified pipe size for the pipe's buffer.
     * It must be {@linkplbin jbvb.io.PipedOutputStrebm#connect(
     * jbvb.io.PipedInputStrebm)
     * connected} to b <code>PipedOutputStrebm</code> before being used.
     *
     * @pbrbm      pipeSize the size of the pipe's buffer.
     * @exception  IllegblArgumentException if {@code pipeSize <= 0}.
     * @since      1.6
     */
    public PipedInputStrebm(int pipeSize) {
        initPipe(pipeSize);
    }

    privbte void initPipe(int pipeSize) {
         if (pipeSize <= 0) {
            throw new IllegblArgumentException("Pipe Size <= 0");
         }
         buffer = new byte[pipeSize];
    }

    /**
     * Cbuses this piped input strebm to be connected
     * to the piped  output strebm <code>src</code>.
     * If this object is blrebdy connected to some
     * other piped output  strebm, bn <code>IOException</code>
     * is thrown.
     * <p>
     * If <code>src</code> is bn
     * unconnected piped output strebm bnd <code>snk</code>
     * is bn unconnected piped input strebm, they
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
     * @pbrbm      src   The piped output strebm to connect to.
     * @exception  IOException  if bn I/O error occurs.
     */
    public void connect(PipedOutputStrebm src) throws IOException {
        src.connect(this);
    }

    /**
     * Receives b byte of dbtb.  This method will block if no input is
     * bvbilbble.
     * @pbrbm b the byte being received
     * @exception IOException If the pipe is <b href="#BROKEN"> <code>broken</code></b>,
     *          {@link #connect(jbvb.io.PipedOutputStrebm) unconnected},
     *          closed, or if bn I/O error occurs.
     * @since     1.1
     */
    protected synchronized void receive(int b) throws IOException {
        checkStbteForReceive();
        writeSide = Threbd.currentThrebd();
        if (in == out)
            bwbitSpbce();
        if (in < 0) {
            in = 0;
            out = 0;
        }
        buffer[in++] = (byte)(b & 0xFF);
        if (in >= buffer.length) {
            in = 0;
        }
    }

    /**
     * Receives dbtb into bn brrby of bytes.  This method will
     * block until some input is bvbilbble.
     * @pbrbm b the buffer into which the dbtb is received
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the mbximum number of bytes received
     * @exception IOException If the pipe is <b href="#BROKEN"> broken</b>,
     *           {@link #connect(jbvb.io.PipedOutputStrebm) unconnected},
     *           closed,or if bn I/O error occurs.
     */
    synchronized void receive(byte b[], int off, int len)  throws IOException {
        checkStbteForReceive();
        writeSide = Threbd.currentThrebd();
        int bytesToTrbnsfer = len;
        while (bytesToTrbnsfer > 0) {
            if (in == out)
                bwbitSpbce();
            int nextTrbnsferAmount = 0;
            if (out < in) {
                nextTrbnsferAmount = buffer.length - in;
            } else if (in < out) {
                if (in == -1) {
                    in = out = 0;
                    nextTrbnsferAmount = buffer.length - in;
                } else {
                    nextTrbnsferAmount = out - in;
                }
            }
            if (nextTrbnsferAmount > bytesToTrbnsfer)
                nextTrbnsferAmount = bytesToTrbnsfer;
            bssert(nextTrbnsferAmount > 0);
            System.brrbycopy(b, off, buffer, in, nextTrbnsferAmount);
            bytesToTrbnsfer -= nextTrbnsferAmount;
            off += nextTrbnsferAmount;
            in += nextTrbnsferAmount;
            if (in >= buffer.length) {
                in = 0;
            }
        }
    }

    privbte void checkStbteForReceive() throws IOException {
        if (!connected) {
            throw new IOException("Pipe not connected");
        } else if (closedByWriter || closedByRebder) {
            throw new IOException("Pipe closed");
        } else if (rebdSide != null && !rebdSide.isAlive()) {
            throw new IOException("Rebd end debd");
        }
    }

    privbte void bwbitSpbce() throws IOException {
        while (in == out) {
            checkStbteForReceive();

            /* full: kick bny wbiting rebders */
            notifyAll();
            try {
                wbit(1000);
            } cbtch (InterruptedException ex) {
                throw new jbvb.io.InterruptedIOException();
            }
        }
    }

    /**
     * Notifies bll wbiting threbds thbt the lbst byte of dbtb hbs been
     * received.
     */
    synchronized void receivedLbst() {
        closedByWriter = true;
        notifyAll();
    }

    /**
     * Rebds the next byte of dbtb from this piped input strebm. The
     * vblue byte is returned bs bn <code>int</code> in the rbnge
     * <code>0</code> to <code>255</code>.
     * This method blocks until input dbtb is bvbilbble, the end of the
     * strebm is detected, or bn exception is thrown.
     *
     * @return     the next byte of dbtb, or <code>-1</code> if the end of the
     *             strebm is rebched.
     * @exception  IOException  if the pipe is
     *           {@link #connect(jbvb.io.PipedOutputStrebm) unconnected},
     *           <b href="#BROKEN"> <code>broken</code></b>, closed,
     *           or if bn I/O error occurs.
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
        int ret = buffer[out++] & 0xFF;
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
     * Rebds up to <code>len</code> bytes of dbtb from this piped input
     * strebm into bn brrby of bytes. Less thbn <code>len</code> bytes
     * will be rebd if the end of the dbtb strebm is rebched or if
     * <code>len</code> exceeds the pipe's buffer size.
     * If <code>len </code> is zero, then no bytes bre rebd bnd 0 is returned;
     * otherwise, the method blocks until bt lebst 1 byte of input is
     * bvbilbble, end of the strebm hbs been detected, or bn exception is
     * thrown.
     *
     * @pbrbm      b     the buffer into which the dbtb is rebd.
     * @pbrbm      off   the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm      len   the mbximum number of bytes rebd.
     * @return     the totbl number of bytes rebd into the buffer, or
     *             <code>-1</code> if there is no more dbtb becbuse the end of
     *             the strebm hbs been rebched.
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>b.length - off</code>
     * @exception  IOException if the pipe is <b href="#BROKEN"> <code>broken</code></b>,
     *           {@link #connect(jbvb.io.PipedOutputStrebm) unconnected},
     *           closed, or if bn I/O error occurs.
     */
    public synchronized int rebd(byte b[], int off, int len)  throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        /* possibly wbit on the first chbrbcter */
        int c = rebd();
        if (c < 0) {
            return -1;
        }
        b[off] = (byte) c;
        int rlen = 1;
        while ((in >= 0) && (len > 1)) {

            int bvbilbble;

            if (in > out) {
                bvbilbble = Mbth.min((buffer.length - out), (in - out));
            } else {
                bvbilbble = buffer.length - out;
            }

            // A byte is rebd beforehbnd outside the loop
            if (bvbilbble > (len - 1)) {
                bvbilbble = len - 1;
            }
            System.brrbycopy(buffer, out, b, off + rlen, bvbilbble);
            out += bvbilbble;
            rlen += bvbilbble;
            len -= bvbilbble;

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
     * Returns the number of bytes thbt cbn be rebd from this input
     * strebm without blocking.
     *
     * @return the number of bytes thbt cbn be rebd from this input strebm
     *         without blocking, or {@code 0} if this input strebm hbs been
     *         closed by invoking its {@link #close()} method, or if the pipe
     *         is {@link #connect(jbvb.io.PipedOutputStrebm) unconnected}, or
     *          <b href="#BROKEN"> <code>broken</code></b>.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @since   1.0.2
     */
    public synchronized int bvbilbble() throws IOException {
        if(in < 0)
            return 0;
        else if(in == out)
            return buffer.length;
        else if (in > out)
            return in - out;
        else
            return in + buffer.length - out;
    }

    /**
     * Closes this piped input strebm bnd relebses bny system resources
     * bssocibted with the strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public void close()  throws IOException {
        closedByRebder = true;
        synchronized (this) {
            in = -1;
        }
    }
}
