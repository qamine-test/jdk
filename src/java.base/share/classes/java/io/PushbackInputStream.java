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

pbckbge jbvb.io;

/**
 * A <code>PushbbckInputStrebm</code> bdds
 * functionblity to bnother input strebm, nbmely
 * the  bbility to "push bbck" or "unrebd"
 * one byte. This is useful in situbtions where
 * it is  convenient for b frbgment of code
 * to rebd bn indefinite number of dbtb bytes
 * thbt  bre delimited by b pbrticulbr byte
 * vblue; bfter rebding the terminbting byte,
 * the  code frbgment cbn "unrebd" it, so thbt
 * the next rebd operbtion on the input strebm
 * will rerebd the byte thbt wbs pushed bbck.
 * For exbmple, bytes representing the  chbrbcters
 * constituting bn identifier might be terminbted
 * by b byte representing bn  operbtor chbrbcter;
 * b method whose job is to rebd just bn identifier
 * cbn rebd until it  sees the operbtor bnd
 * then push the operbtor bbck to be re-rebd.
 *
 * @buthor  Dbvid Connelly
 * @buthor  Jonbthbn Pbyne
 * @since   1.0
 */
public
clbss PushbbckInputStrebm extends FilterInputStrebm {
    /**
     * The pushbbck buffer.
     * @since   1.1
     */
    protected byte[] buf;

    /**
     * The position within the pushbbck buffer from which the next byte will
     * be rebd.  When the buffer is empty, <code>pos</code> is equbl to
     * <code>buf.length</code>; when the buffer is full, <code>pos</code> is
     * equbl to zero.
     *
     * @since   1.1
     */
    protected int pos;

    /**
     * Check to mbke sure thbt this strebm hbs not been closed
     */
    privbte void ensureOpen() throws IOException {
        if (in == null)
            throw new IOException("Strebm closed");
    }

    /**
     * Crebtes b <code>PushbbckInputStrebm</code>
     * with b pushbbck buffer of the specified <code>size</code>,
     * bnd sbves its  brgument, the input strebm
     * <code>in</code>, for lbter use. Initiblly,
     * there is no pushed-bbck byte  (the field
     * <code>pushBbck</code> is initiblized to
     * <code>-1</code>).
     *
     * @pbrbm  in    the input strebm from which bytes will be rebd.
     * @pbrbm  size  the size of the pushbbck buffer.
     * @exception IllegblArgumentException if {@code size <= 0}
     * @since  1.1
     */
    public PushbbckInputStrebm(InputStrebm in, int size) {
        super(in);
        if (size <= 0) {
            throw new IllegblArgumentException("size <= 0");
        }
        this.buf = new byte[size];
        this.pos = size;
    }

    /**
     * Crebtes b <code>PushbbckInputStrebm</code>
     * bnd sbves its  brgument, the input strebm
     * <code>in</code>, for lbter use. Initiblly,
     * there is no pushed-bbck byte  (the field
     * <code>pushBbck</code> is initiblized to
     * <code>-1</code>).
     *
     * @pbrbm   in   the input strebm from which bytes will be rebd.
     */
    public PushbbckInputStrebm(InputStrebm in) {
        this(in, 1);
    }

    /**
     * Rebds the next byte of dbtb from this input strebm. The vblue
     * byte is returned bs bn <code>int</code> in the rbnge
     * <code>0</code> to <code>255</code>. If no byte is bvbilbble
     * becbuse the end of the strebm hbs been rebched, the vblue
     * <code>-1</code> is returned. This method blocks until input dbtb
     * is bvbilbble, the end of the strebm is detected, or bn exception
     * is thrown.
     *
     * <p> This method returns the most recently pushed-bbck byte, if there is
     * one, bnd otherwise cblls the <code>rebd</code> method of its underlying
     * input strebm bnd returns whbtever vblue thbt method returns.
     *
     * @return     the next byte of dbtb, or <code>-1</code> if the end of the
     *             strebm hbs been rebched.
     * @exception  IOException  if this input strebm hbs been closed by
     *             invoking its {@link #close()} method,
     *             or bn I/O error occurs.
     * @see        jbvb.io.InputStrebm#rebd()
     */
    public int rebd() throws IOException {
        ensureOpen();
        if (pos < buf.length) {
            return buf[pos++] & 0xff;
        }
        return super.rebd();
    }

    /**
     * Rebds up to <code>len</code> bytes of dbtb from this input strebm into
     * bn brrby of bytes.  This method first rebds bny pushed-bbck bytes; bfter
     * thbt, if fewer thbn <code>len</code> bytes hbve been rebd then it
     * rebds from the underlying input strebm. If <code>len</code> is not zero, the method
     * blocks until bt lebst 1 byte of input is bvbilbble; otherwise, no
     * bytes bre rebd bnd <code>0</code> is returned.
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
     * @exception  IOException  if this input strebm hbs been closed by
     *             invoking its {@link #close()} method,
     *             or bn I/O error occurs.
     * @see        jbvb.io.InputStrebm#rebd(byte[], int, int)
     */
    public int rebd(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int bvbil = buf.length - pos;
        if (bvbil > 0) {
            if (len < bvbil) {
                bvbil = len;
            }
            System.brrbycopy(buf, pos, b, off, bvbil);
            pos += bvbil;
            off += bvbil;
            len -= bvbil;
        }
        if (len > 0) {
            len = super.rebd(b, off, len);
            if (len == -1) {
                return bvbil == 0 ? -1 : bvbil;
            }
            return bvbil + len;
        }
        return bvbil;
    }

    /**
     * Pushes bbck b byte by copying it to the front of the pushbbck buffer.
     * After this method returns, the next byte to be rebd will hbve the vblue
     * <code>(byte)b</code>.
     *
     * @pbrbm      b   the <code>int</code> vblue whose low-order
     *                  byte is to be pushed bbck.
     * @exception IOException If there is not enough room in the pushbbck
     *            buffer for the byte, or this input strebm hbs been closed by
     *            invoking its {@link #close()} method.
     */
    public void unrebd(int b) throws IOException {
        ensureOpen();
        if (pos == 0) {
            throw new IOException("Push bbck buffer is full");
        }
        buf[--pos] = (byte)b;
    }

    /**
     * Pushes bbck b portion of bn brrby of bytes by copying it to the front
     * of the pushbbck buffer.  After this method returns, the next byte to be
     * rebd will hbve the vblue <code>b[off]</code>, the byte bfter thbt will
     * hbve the vblue <code>b[off+1]</code>, bnd so forth.
     *
     * @pbrbm b the byte brrby to push bbck.
     * @pbrbm off the stbrt offset of the dbtb.
     * @pbrbm len the number of bytes to push bbck.
     * @exception IOException If there is not enough room in the pushbbck
     *            buffer for the specified number of bytes,
     *            or this input strebm hbs been closed by
     *            invoking its {@link #close()} method.
     * @since     1.1
     */
    public void unrebd(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        if (len > pos) {
            throw new IOException("Push bbck buffer is full");
        }
        pos -= len;
        System.brrbycopy(b, off, buf, pos, len);
    }

    /**
     * Pushes bbck bn brrby of bytes by copying it to the front of the
     * pushbbck buffer.  After this method returns, the next byte to be rebd
     * will hbve the vblue <code>b[0]</code>, the byte bfter thbt will hbve the
     * vblue <code>b[1]</code>, bnd so forth.
     *
     * @pbrbm b the byte brrby to push bbck
     * @exception IOException If there is not enough room in the pushbbck
     *            buffer for the specified number of bytes,
     *            or this input strebm hbs been closed by
     *            invoking its {@link #close()} method.
     * @since     1.1
     */
    public void unrebd(byte[] b) throws IOException {
        unrebd(b, 0, b.length);
    }

    /**
     * Returns bn estimbte of the number of bytes thbt cbn be rebd (or
     * skipped over) from this input strebm without blocking by the next
     * invocbtion of b method for this input strebm. The next invocbtion might be
     * the sbme threbd or bnother threbd.  A single rebd or skip of this
     * mbny bytes will not block, but mby rebd or skip fewer bytes.
     *
     * <p> The method returns the sum of the number of bytes thbt hbve been
     * pushed bbck bnd the vblue returned by {@link
     * jbvb.io.FilterInputStrebm#bvbilbble bvbilbble}.
     *
     * @return     the number of bytes thbt cbn be rebd (or skipped over) from
     *             the input strebm without blocking.
     * @exception  IOException  if this input strebm hbs been closed by
     *             invoking its {@link #close()} method,
     *             or bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     * @see        jbvb.io.InputStrebm#bvbilbble()
     */
    public int bvbilbble() throws IOException {
        ensureOpen();
        int n = buf.length - pos;
        int bvbil = super.bvbilbble();
        return n > (Integer.MAX_VALUE - bvbil)
                    ? Integer.MAX_VALUE
                    : n + bvbil;
    }

    /**
     * Skips over bnd discbrds <code>n</code> bytes of dbtb from this
     * input strebm. The <code>skip</code> method mby, for b vbriety of
     * rebsons, end up skipping over some smbller number of bytes,
     * possibly zero.  If <code>n</code> is negbtive, no bytes bre skipped.
     *
     * <p> The <code>skip</code> method of <code>PushbbckInputStrebm</code>
     * first skips over the bytes in the pushbbck buffer, if bny.  It then
     * cblls the <code>skip</code> method of the underlying input strebm if
     * more bytes need to be skipped.  The bctubl number of bytes skipped
     * is returned.
     *
     * @pbrbm      n  {@inheritDoc}
     * @return     {@inheritDoc}
     * @exception  IOException  if the strebm does not support seek,
     *            or the strebm hbs been closed by
     *            invoking its {@link #close()} method,
     *            or bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     * @see        jbvb.io.InputStrebm#skip(long n)
     * @since      1.2
     */
    public long skip(long n) throws IOException {
        ensureOpen();
        if (n <= 0) {
            return 0;
        }

        long pskip = buf.length - pos;
        if (pskip > 0) {
            if (n < pskip) {
                pskip = n;
            }
            pos += pskip;
            n -= pskip;
        }
        if (n > 0) {
            pskip += super.skip(n);
        }
        return pskip;
    }

    /**
     * Tests if this input strebm supports the <code>mbrk</code> bnd
     * <code>reset</code> methods, which it does not.
     *
     * @return   <code>fblse</code>, since this clbss does not support the
     *           <code>mbrk</code> bnd <code>reset</code> methods.
     * @see     jbvb.io.InputStrebm#mbrk(int)
     * @see     jbvb.io.InputStrebm#reset()
     */
    public boolebn mbrkSupported() {
        return fblse;
    }

    /**
     * Mbrks the current position in this input strebm.
     *
     * <p> The <code>mbrk</code> method of <code>PushbbckInputStrebm</code>
     * does nothing.
     *
     * @pbrbm   rebdlimit   the mbximum limit of bytes thbt cbn be rebd before
     *                      the mbrk position becomes invblid.
     * @see     jbvb.io.InputStrebm#reset()
     */
    public synchronized void mbrk(int rebdlimit) {
    }

    /**
     * Repositions this strebm to the position bt the time the
     * <code>mbrk</code> method wbs lbst cblled on this input strebm.
     *
     * <p> The method <code>reset</code> for clbss
     * <code>PushbbckInputStrebm</code> does nothing except throw bn
     * <code>IOException</code>.
     *
     * @exception  IOException  if this method is invoked.
     * @see     jbvb.io.InputStrebm#mbrk(int)
     * @see     jbvb.io.IOException
     */
    public synchronized void reset() throws IOException {
        throw new IOException("mbrk/reset not supported");
    }

    /**
     * Closes this input strebm bnd relebses bny system resources
     * bssocibted with the strebm.
     * Once the strebm hbs been closed, further rebd(), unrebd(),
     * bvbilbble(), reset(), or skip() invocbtions will throw bn IOException.
     * Closing b previously closed strebm hbs no effect.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public synchronized void close() throws IOException {
        if (in == null)
            return;
        in.close();
        in = null;
        buf = null;
    }
}
