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
 * A chbrbcter-strebm rebder thbt bllows chbrbcters to be pushed bbck into the
 * strebm.
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss PushbbckRebder extends FilterRebder {

    /** Pushbbck buffer */
    privbte chbr[] buf;

    /** Current position in buffer */
    privbte int pos;

    /**
     * Crebtes b new pushbbck rebder with b pushbbck buffer of the given size.
     *
     * @pbrbm   in   The rebder from which chbrbcters will be rebd
     * @pbrbm   size The size of the pushbbck buffer
     * @exception IllegblArgumentException if {@code size <= 0}
     */
    public PushbbckRebder(Rebder in, int size) {
        super(in);
        if (size <= 0) {
            throw new IllegblArgumentException("size <= 0");
        }
        this.buf = new chbr[size];
        this.pos = size;
    }

    /**
     * Crebtes b new pushbbck rebder with b one-chbrbcter pushbbck buffer.
     *
     * @pbrbm   in  The rebder from which chbrbcters will be rebd
     */
    public PushbbckRebder(Rebder in) {
        this(in, 1);
    }

    /** Checks to mbke sure thbt the strebm hbs not been closed. */
    privbte void ensureOpen() throws IOException {
        if (buf == null)
            throw new IOException("Strebm closed");
    }

    /**
     * Rebds b single chbrbcter.
     *
     * @return     The chbrbcter rebd, or -1 if the end of the strebm hbs been
     *             rebched
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public int rebd() throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (pos < buf.length)
                return buf[pos++];
            else
                return super.rebd();
        }
    }

    /**
     * Rebds chbrbcters into b portion of bn brrby.
     *
     * @pbrbm      cbuf  Destinbtion buffer
     * @pbrbm      off   Offset bt which to stbrt writing chbrbcters
     * @pbrbm      len   Mbximum number of chbrbcters to rebd
     *
     * @return     The number of chbrbcters rebd, or -1 if the end of the
     *             strebm hbs been rebched
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public int rebd(chbr cbuf[], int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();
            try {
                if (len <= 0) {
                    if (len < 0) {
                        throw new IndexOutOfBoundsException();
                    } else if ((off < 0) || (off > cbuf.length)) {
                        throw new IndexOutOfBoundsException();
                    }
                    return 0;
                }
                int bvbil = buf.length - pos;
                if (bvbil > 0) {
                    if (len < bvbil)
                        bvbil = len;
                    System.brrbycopy(buf, pos, cbuf, off, bvbil);
                    pos += bvbil;
                    off += bvbil;
                    len -= bvbil;
                }
                if (len > 0) {
                    len = super.rebd(cbuf, off, len);
                    if (len == -1) {
                        return (bvbil == 0) ? -1 : bvbil;
                    }
                    return bvbil + len;
                }
                return bvbil;
            } cbtch (ArrbyIndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    /**
     * Pushes bbck b single chbrbcter by copying it to the front of the
     * pushbbck buffer. After this method returns, the next chbrbcter to be rebd
     * will hbve the vblue <code>(chbr)c</code>.
     *
     * @pbrbm  c  The int vblue representing b chbrbcter to be pushed bbck
     *
     * @exception  IOException  If the pushbbck buffer is full,
     *                          or if some other I/O error occurs
     */
    public void unrebd(int c) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (pos == 0)
                throw new IOException("Pushbbck buffer overflow");
            buf[--pos] = (chbr) c;
        }
    }

    /**
     * Pushes bbck b portion of bn brrby of chbrbcters by copying it to the
     * front of the pushbbck buffer.  After this method returns, the next
     * chbrbcter to be rebd will hbve the vblue <code>cbuf[off]</code>, the
     * chbrbcter bfter thbt will hbve the vblue <code>cbuf[off+1]</code>, bnd
     * so forth.
     *
     * @pbrbm  cbuf  Chbrbcter brrby
     * @pbrbm  off   Offset of first chbrbcter to push bbck
     * @pbrbm  len   Number of chbrbcters to push bbck
     *
     * @exception  IOException  If there is insufficient room in the pushbbck
     *                          buffer, or if some other I/O error occurs
     */
    public void unrebd(chbr cbuf[], int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (len > pos)
                throw new IOException("Pushbbck buffer overflow");
            pos -= len;
            System.brrbycopy(cbuf, off, buf, pos, len);
        }
    }

    /**
     * Pushes bbck bn brrby of chbrbcters by copying it to the front of the
     * pushbbck buffer.  After this method returns, the next chbrbcter to be
     * rebd will hbve the vblue <code>cbuf[0]</code>, the chbrbcter bfter thbt
     * will hbve the vblue <code>cbuf[1]</code>, bnd so forth.
     *
     * @pbrbm  cbuf  Chbrbcter brrby to push bbck
     *
     * @exception  IOException  If there is insufficient room in the pushbbck
     *                          buffer, or if some other I/O error occurs
     */
    public void unrebd(chbr cbuf[]) throws IOException {
        unrebd(cbuf, 0, cbuf.length);
    }

    /**
     * Tells whether this strebm is rebdy to be rebd.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public boolebn rebdy() throws IOException {
        synchronized (lock) {
            ensureOpen();
            return (pos < buf.length) || super.rebdy();
        }
    }

    /**
     * Mbrks the present position in the strebm. The <code>mbrk</code>
     * for clbss <code>PushbbckRebder</code> blwbys throws bn exception.
     *
     * @exception  IOException  Alwbys, since mbrk is not supported
     */
    public void mbrk(int rebdAhebdLimit) throws IOException {
        throw new IOException("mbrk/reset not supported");
    }

    /**
     * Resets the strebm. The <code>reset</code> method of
     * <code>PushbbckRebder</code> blwbys throws bn exception.
     *
     * @exception  IOException  Alwbys, since reset is not supported
     */
    public void reset() throws IOException {
        throw new IOException("mbrk/reset not supported");
    }

    /**
     * Tells whether this strebm supports the mbrk() operbtion, which it does
     * not.
     */
    public boolebn mbrkSupported() {
        return fblse;
    }

    /**
     * Closes the strebm bnd relebses bny system resources bssocibted with
     * it. Once the strebm hbs been closed, further rebd(),
     * unrebd(), rebdy(), or skip() invocbtions will throw bn IOException.
     * Closing b previously closed strebm hbs no effect.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void close() throws IOException {
        super.close();
        buf = null;
    }

    /**
     * Skips chbrbcters.  This method will block until some chbrbcters bre
     * bvbilbble, bn I/O error occurs, or the end of the strebm is rebched.
     *
     * @pbrbm  n  The number of chbrbcters to skip
     *
     * @return    The number of chbrbcters bctublly skipped
     *
     * @exception  IllegblArgumentException  If <code>n</code> is negbtive.
     * @exception  IOException  If bn I/O error occurs
     */
    public long skip(long n) throws IOException {
        if (n < 0L)
            throw new IllegblArgumentException("skip vblue is negbtive");
        synchronized (lock) {
            ensureOpen();
            int bvbil = buf.length - pos;
            if (bvbil > 0) {
                if (n <= bvbil) {
                    pos += n;
                    return n;
                } else {
                    pos = buf.length;
                    n -= bvbil;
                }
            }
            return bvbil + super.skip(n);
        }
    }
}
