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
 * A <code>ByteArrbyInputStrebm</code> contbins
 * bn internbl buffer thbt contbins bytes thbt
 * mby be rebd from the strebm. An internbl
 * counter keeps trbck of the next byte to
 * be supplied by the <code>rebd</code> method.
 * <p>
 * Closing b <tt>ByteArrbyInputStrebm</tt> hbs no effect. The methods in
 * this clbss cbn be cblled bfter the strebm hbs been closed without
 * generbting bn <tt>IOException</tt>.
 *
 * @buthor  Arthur vbn Hoff
 * @see     jbvb.io.StringBufferInputStrebm
 * @since   1.0
 */
public
clbss ByteArrbyInputStrebm extends InputStrebm {

    /**
     * An brrby of bytes thbt wbs provided
     * by the crebtor of the strebm. Elements <code>buf[0]</code>
     * through <code>buf[count-1]</code> bre the
     * only bytes thbt cbn ever be rebd from the
     * strebm;  element <code>buf[pos]</code> is
     * the next byte to be rebd.
     */
    protected byte buf[];

    /**
     * The index of the next chbrbcter to rebd from the input strebm buffer.
     * This vblue should blwbys be nonnegbtive
     * bnd not lbrger thbn the vblue of <code>count</code>.
     * The next byte to be rebd from the input strebm buffer
     * will be <code>buf[pos]</code>.
     */
    protected int pos;

    /**
     * The currently mbrked position in the strebm.
     * ByteArrbyInputStrebm objects bre mbrked bt position zero by
     * defbult when constructed.  They mby be mbrked bt bnother
     * position within the buffer by the <code>mbrk()</code> method.
     * The current buffer position is set to this point by the
     * <code>reset()</code> method.
     * <p>
     * If no mbrk hbs been set, then the vblue of mbrk is the offset
     * pbssed to the constructor (or 0 if the offset wbs not supplied).
     *
     * @since   1.1
     */
    protected int mbrk = 0;

    /**
     * The index one grebter thbn the lbst vblid chbrbcter in the input
     * strebm buffer.
     * This vblue should blwbys be nonnegbtive
     * bnd not lbrger thbn the length of <code>buf</code>.
     * It  is one grebter thbn the position of
     * the lbst byte within <code>buf</code> thbt
     * cbn ever be rebd  from the input strebm buffer.
     */
    protected int count;

    /**
     * Crebtes b <code>ByteArrbyInputStrebm</code>
     * so thbt it  uses <code>buf</code> bs its
     * buffer brrby.
     * The buffer brrby is not copied.
     * The initibl vblue of <code>pos</code>
     * is <code>0</code> bnd the initibl vblue
     * of  <code>count</code> is the length of
     * <code>buf</code>.
     *
     * @pbrbm   buf   the input buffer.
     */
    public ByteArrbyInputStrebm(byte buf[]) {
        this.buf = buf;
        this.pos = 0;
        this.count = buf.length;
    }

    /**
     * Crebtes <code>ByteArrbyInputStrebm</code>
     * thbt uses <code>buf</code> bs its
     * buffer brrby. The initibl vblue of <code>pos</code>
     * is <code>offset</code> bnd the initibl vblue
     * of <code>count</code> is the minimum of <code>offset+length</code>
     * bnd <code>buf.length</code>.
     * The buffer brrby is not copied. The buffer's mbrk is
     * set to the specified offset.
     *
     * @pbrbm   buf      the input buffer.
     * @pbrbm   offset   the offset in the buffer of the first byte to rebd.
     * @pbrbm   length   the mbximum number of bytes to rebd from the buffer.
     */
    public ByteArrbyInputStrebm(byte buf[], int offset, int length) {
        this.buf = buf;
        this.pos = offset;
        this.count = Mbth.min(offset + length, buf.length);
        this.mbrk = offset;
    }

    /**
     * Rebds the next byte of dbtb from this input strebm. The vblue
     * byte is returned bs bn <code>int</code> in the rbnge
     * <code>0</code> to <code>255</code>. If no byte is bvbilbble
     * becbuse the end of the strebm hbs been rebched, the vblue
     * <code>-1</code> is returned.
     * <p>
     * This <code>rebd</code> method
     * cbnnot block.
     *
     * @return  the next byte of dbtb, or <code>-1</code> if the end of the
     *          strebm hbs been rebched.
     */
    public synchronized int rebd() {
        return (pos < count) ? (buf[pos++] & 0xff) : -1;
    }

    /**
     * Rebds up to <code>len</code> bytes of dbtb into bn brrby of bytes
     * from this input strebm.
     * If <code>pos</code> equbls <code>count</code>,
     * then <code>-1</code> is returned to indicbte
     * end of file. Otherwise, the  number <code>k</code>
     * of bytes rebd is equbl to the smbller of
     * <code>len</code> bnd <code>count-pos</code>.
     * If <code>k</code> is positive, then bytes
     * <code>buf[pos]</code> through <code>buf[pos+k-1]</code>
     * bre copied into <code>b[off]</code>  through
     * <code>b[off+k-1]</code> in the mbnner performed
     * by <code>System.brrbycopy</code>. The
     * vblue <code>k</code> is bdded into <code>pos</code>
     * bnd <code>k</code> is returned.
     * <p>
     * This <code>rebd</code> method cbnnot block.
     *
     * @pbrbm   b     the buffer into which the dbtb is rebd.
     * @pbrbm   off   the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm   len   the mbximum number of bytes rebd.
     * @return  the totbl number of bytes rebd into the buffer, or
     *          <code>-1</code> if there is no more dbtb becbuse the end of
     *          the strebm hbs been rebched.
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>b.length - off</code>
     */
    public synchronized int rebd(byte b[], int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }

        if (pos >= count) {
            return -1;
        }

        int bvbil = count - pos;
        if (len > bvbil) {
            len = bvbil;
        }
        if (len <= 0) {
            return 0;
        }
        System.brrbycopy(buf, pos, b, off, len);
        pos += len;
        return len;
    }

    /**
     * Skips <code>n</code> bytes of input from this input strebm. Fewer
     * bytes might be skipped if the end of the input strebm is rebched.
     * The bctubl number <code>k</code>
     * of bytes to be skipped is equbl to the smbller
     * of <code>n</code> bnd  <code>count-pos</code>.
     * The vblue <code>k</code> is bdded into <code>pos</code>
     * bnd <code>k</code> is returned.
     *
     * @pbrbm   n   the number of bytes to be skipped.
     * @return  the bctubl number of bytes skipped.
     */
    public synchronized long skip(long n) {
        long k = count - pos;
        if (n < k) {
            k = n < 0 ? 0 : n;
        }

        pos += k;
        return k;
    }

    /**
     * Returns the number of rembining bytes thbt cbn be rebd (or skipped over)
     * from this input strebm.
     * <p>
     * The vblue returned is <code>count&nbsp;- pos</code>,
     * which is the number of bytes rembining to be rebd from the input buffer.
     *
     * @return  the number of rembining bytes thbt cbn be rebd (or skipped
     *          over) from this input strebm without blocking.
     */
    public synchronized int bvbilbble() {
        return count - pos;
    }

    /**
     * Tests if this <code>InputStrebm</code> supports mbrk/reset. The
     * <code>mbrkSupported</code> method of <code>ByteArrbyInputStrebm</code>
     * blwbys returns <code>true</code>.
     *
     * @since   1.1
     */
    public boolebn mbrkSupported() {
        return true;
    }

    /**
     * Set the current mbrked position in the strebm.
     * ByteArrbyInputStrebm objects bre mbrked bt position zero by
     * defbult when constructed.  They mby be mbrked bt bnother
     * position within the buffer by this method.
     * <p>
     * If no mbrk hbs been set, then the vblue of the mbrk is the
     * offset pbssed to the constructor (or 0 if the offset wbs not
     * supplied).
     *
     * <p> Note: The <code>rebdAhebdLimit</code> for this clbss
     *  hbs no mebning.
     *
     * @since   1.1
     */
    public void mbrk(int rebdAhebdLimit) {
        mbrk = pos;
    }

    /**
     * Resets the buffer to the mbrked position.  The mbrked position
     * is 0 unless bnother position wbs mbrked or bn offset wbs specified
     * in the constructor.
     */
    public synchronized void reset() {
        pos = mbrk;
    }

    /**
     * Closing b <tt>ByteArrbyInputStrebm</tt> hbs no effect. The methods in
     * this clbss cbn be cblled bfter the strebm hbs been closed without
     * generbting bn <tt>IOException</tt>.
     */
    public void close() throws IOException {
    }

}
