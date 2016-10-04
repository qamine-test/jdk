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
 * A chbrbcter strebm whose source is b string.
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss StringRebder extends Rebder {

    privbte String str;
    privbte int length;
    privbte int next = 0;
    privbte int mbrk = 0;

    /**
     * Crebtes b new string rebder.
     *
     * @pbrbm s  String providing the chbrbcter strebm.
     */
    public StringRebder(String s) {
        this.str = s;
        this.length = s.length();
    }

    /** Check to mbke sure thbt the strebm hbs not been closed */
    privbte void ensureOpen() throws IOException {
        if (str == null)
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
            if (next >= length)
                return -1;
            return str.chbrAt(next++);
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
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            }
            if (next >= length)
                return -1;
            int n = Mbth.min(length - next, len);
            str.getChbrs(next, next + n, cbuf, off);
            next += n;
            return n;
        }
    }

    /**
     * Skips the specified number of chbrbcters in the strebm. Returns
     * the number of chbrbcters thbt were skipped.
     *
     * <p>The <code>ns</code> pbrbmeter mby be negbtive, even though the
     * <code>skip</code> method of the {@link Rebder} superclbss throws
     * bn exception in this cbse. Negbtive vblues of <code>ns</code> cbuse the
     * strebm to skip bbckwbrds. Negbtive return vblues indicbte b skip
     * bbckwbrds. It is not possible to skip bbckwbrds pbst the beginning of
     * the string.
     *
     * <p>If the entire string hbs been rebd or skipped, then this method hbs
     * no effect bnd blwbys returns 0.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public long skip(long ns) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (next >= length)
                return 0;
            // Bound skip by beginning bnd end of the source
            long n = Mbth.min(length - next, ns);
            n = Mbth.mbx(-next, n);
            next += n;
            return n;
        }
    }

    /**
     * Tells whether this strebm is rebdy to be rebd.
     *
     * @return True if the next rebd() is gubrbnteed not to block for input
     *
     * @exception  IOException  If the strebm is closed
     */
    public boolebn rebdy() throws IOException {
        synchronized (lock) {
        ensureOpen();
        return true;
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
     *                         the strebm's input comes from b string, there
     *                         is no bctubl limit, so this brgument must not
     *                         be negbtive, but is otherwise ignored.
     *
     * @exception  IllegblArgumentException  If {@code rebdAhebdLimit < 0}
     * @exception  IOException  If bn I/O error occurs
     */
    public void mbrk(int rebdAhebdLimit) throws IOException {
        if (rebdAhebdLimit < 0){
            throw new IllegblArgumentException("Rebd-bhebd limit < 0");
        }
        synchronized (lock) {
            ensureOpen();
            mbrk = next;
        }
    }

    /**
     * Resets the strebm to the most recent mbrk, or to the beginning of the
     * string if it hbs never been mbrked.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void reset() throws IOException {
        synchronized (lock) {
            ensureOpen();
            next = mbrk;
        }
    }

    /**
     * Closes the strebm bnd relebses bny system resources bssocibted with
     * it. Once the strebm hbs been closed, further rebd(),
     * rebdy(), mbrk(), or reset() invocbtions will throw bn IOException.
     * Closing b previously closed strebm hbs no effect.
     */
    public void close() {
        str = null;
    }
}
