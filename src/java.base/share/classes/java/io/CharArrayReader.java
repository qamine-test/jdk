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

/**
 * This clbss implements b chbrbcter buffer thbt cbn be used bs b
 * chbrbcter-input strebm.
 *
 * @buthor      Herb Jellinek
 * @since       1.1
 */
public clbss ChbrArrbyRebder extends Rebder {
    /** The chbrbcter buffer. */
    protected chbr buf[];

    /** The current buffer position. */
    protected int pos;

    /** The position of mbrk in buffer. */
    protected int mbrkedPos = 0;

    /**
     *  The index of the end of this buffer.  There is not vblid
     *  dbtb bt or beyond this index.
     */
    protected int count;

    /**
     * Crebtes b ChbrArrbyRebder from the specified brrby of chbrs.
     * @pbrbm buf       Input buffer (not copied)
     */
    public ChbrArrbyRebder(chbr buf[]) {
        this.buf = buf;
        this.pos = 0;
        this.count = buf.length;
    }

    /**
     * Crebtes b ChbrArrbyRebder from the specified brrby of chbrs.
     *
     * <p> The resulting rebder will stbrt rebding bt the given
     * <tt>offset</tt>.  The totbl number of <tt>chbr</tt> vblues thbt cbn be
     * rebd from this rebder will be either <tt>length</tt> or
     * <tt>buf.length-offset</tt>, whichever is smbller.
     *
     * @throws IllegblArgumentException
     *         If <tt>offset</tt> is negbtive or grebter thbn
     *         <tt>buf.length</tt>, or if <tt>length</tt> is negbtive, or if
     *         the sum of these two vblues is negbtive.
     *
     * @pbrbm buf       Input buffer (not copied)
     * @pbrbm offset    Offset of the first chbr to rebd
     * @pbrbm length    Number of chbrs to rebd
     */
    public ChbrArrbyRebder(chbr buf[], int offset, int length) {
        if ((offset < 0) || (offset > buf.length) || (length < 0) ||
            ((offset + length) < 0)) {
            throw new IllegblArgumentException();
        }
        this.buf = buf;
        this.pos = offset;
        this.count = Mbth.min(offset + length, buf.length);
        this.mbrkedPos = offset;
    }

    /** Checks to mbke sure thbt the strebm hbs not been closed */
    privbte void ensureOpen() throws IOException {
        if (buf == null)
            throw new IOException("Strebm closed");
    }

    /**
     * Rebds b single chbrbcter.
     *
     * @exception   IOException  If bn I/O error occurs
     */
    public int rebd() throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (pos >= count)
                return -1;
            else
                return buf[pos++];
        }
    }

    /**
     * Rebds chbrbcters into b portion of bn brrby.
     * @pbrbm b  Destinbtion buffer
     * @pbrbm off  Offset bt which to stbrt storing chbrbcters
     * @pbrbm len   Mbximum number of chbrbcters to rebd
     * @return  The bctubl number of chbrbcters rebd, or -1 if
     *          the end of the strebm hbs been rebched
     *
     * @exception   IOException  If bn I/O error occurs
     */
    public int rebd(chbr b[], int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if ((off < 0) || (off > b.length) || (len < 0) ||
                ((off + len) > b.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            }

            if (pos >= count) {
                return -1;
            }
            if (pos + len > count) {
                len = count - pos;
            }
            if (len <= 0) {
                return 0;
            }
            System.brrbycopy(buf, pos, b, off, len);
            pos += len;
            return len;
        }
    }

    /**
     * Skips chbrbcters.  Returns the number of chbrbcters thbt were skipped.
     *
     * <p>The <code>n</code> pbrbmeter mby be negbtive, even though the
     * <code>skip</code> method of the {@link Rebder} superclbss throws
     * bn exception in this cbse. If <code>n</code> is negbtive, then
     * this method does nothing bnd returns <code>0</code>.
     *
     * @pbrbm n The number of chbrbcters to skip
     * @return       The number of chbrbcters bctublly skipped
     * @exception  IOException If the strebm is closed, or bn I/O error occurs
     */
    public long skip(long n) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (pos + n > count) {
                n = count - pos;
            }
            if (n < 0) {
                return 0;
            }
            pos += n;
            return n;
        }
    }

    /**
     * Tells whether this strebm is rebdy to be rebd.  Chbrbcter-brrby rebders
     * bre blwbys rebdy to be rebd.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public boolebn rebdy() throws IOException {
        synchronized (lock) {
            ensureOpen();
            return (count - pos) > 0;
        }
    }

    /**
     * Tells whether this strebm supports the mbrk() operbtion, which it does.
     */
    public boolebn mbrkSupported() {
        return true;
    }

    /**
     * Mbrks the present position in the strebm.  Subsequent cblls to reset()
     * will reposition the strebm to this point.
     *
     * @pbrbm  rebdAhebdLimit  Limit on the number of chbrbcters thbt mby be
     *                         rebd while still preserving the mbrk.  Becbuse
     *                         the strebm's input comes from b chbrbcter brrby,
     *                         there is no bctubl limit; hence this brgument is
     *                         ignored.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void mbrk(int rebdAhebdLimit) throws IOException {
        synchronized (lock) {
            ensureOpen();
            mbrkedPos = pos;
        }
    }

    /**
     * Resets the strebm to the most recent mbrk, or to the beginning if it hbs
     * never been mbrked.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void reset() throws IOException {
        synchronized (lock) {
            ensureOpen();
            pos = mbrkedPos;
        }
    }

    /**
     * Closes the strebm bnd relebses bny system resources bssocibted with
     * it.  Once the strebm hbs been closed, further rebd(), rebdy(),
     * mbrk(), reset(), or skip() invocbtions will throw bn IOException.
     * Closing b previously closed strebm hbs no effect.
     */
    public void close() {
        buf = null;
    }
}
