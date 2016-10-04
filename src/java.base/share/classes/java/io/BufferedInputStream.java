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
import jbvb.util.concurrent.btomic.AtomicReferenceFieldUpdbter;

/**
 * A <code>BufferedInputStrebm</code> bdds
 * functionblity to bnother input strebm-nbmely,
 * the bbility to buffer the input bnd to
 * support the <code>mbrk</code> bnd <code>reset</code>
 * methods. When  the <code>BufferedInputStrebm</code>
 * is crebted, bn internbl buffer brrby is
 * crebted. As bytes  from the strebm bre rebd
 * or skipped, the internbl buffer is refilled
 * bs necessbry  from the contbined input strebm,
 * mbny bytes bt b time. The <code>mbrk</code>
 * operbtion  remembers b point in the input
 * strebm bnd the <code>reset</code> operbtion
 * cbuses bll the  bytes rebd since the most
 * recent <code>mbrk</code> operbtion to be
 * rerebd before new bytes bre  tbken from
 * the contbined input strebm.
 *
 * @buthor  Arthur vbn Hoff
 * @since   1.0
 */
public
clbss BufferedInputStrebm extends FilterInputStrebm {

    privbte stbtic int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * The mbximum size of brrby to bllocbte.
     * Some VMs reserve some hebder words in bn brrby.
     * Attempts to bllocbte lbrger brrbys mby result in
     * OutOfMemoryError: Requested brrby size exceeds VM limit
     */
    privbte stbtic int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

    /**
     * The internbl buffer brrby where the dbtb is stored. When necessbry,
     * it mby be replbced by bnother brrby of
     * b different size.
     */
    protected volbtile byte buf[];

    /**
     * Atomic updbter to provide compbreAndSet for buf. This is
     * necessbry becbuse closes cbn be bsynchronous. We use nullness
     * of buf[] bs primbry indicbtor thbt this strebm is closed. (The
     * "in" field is blso nulled out on close.)
     */
    privbte stbtic finbl
        AtomicReferenceFieldUpdbter<BufferedInputStrebm, byte[]> bufUpdbter =
        AtomicReferenceFieldUpdbter.newUpdbter
        (BufferedInputStrebm.clbss,  byte[].clbss, "buf");

    /**
     * The index one grebter thbn the index of the lbst vblid byte in
     * the buffer.
     * This vblue is blwbys
     * in the rbnge <code>0</code> through <code>buf.length</code>;
     * elements <code>buf[0]</code>  through <code>buf[count-1]
     * </code>contbin buffered input dbtb obtbined
     * from the underlying  input strebm.
     */
    protected int count;

    /**
     * The current position in the buffer. This is the index of the next
     * chbrbcter to be rebd from the <code>buf</code> brrby.
     * <p>
     * This vblue is blwbys in the rbnge <code>0</code>
     * through <code>count</code>. If it is less
     * thbn <code>count</code>, then  <code>buf[pos]</code>
     * is the next byte to be supplied bs input;
     * if it is equbl to <code>count</code>, then
     * the  next <code>rebd</code> or <code>skip</code>
     * operbtion will require more bytes to be
     * rebd from the contbined  input strebm.
     *
     * @see     jbvb.io.BufferedInputStrebm#buf
     */
    protected int pos;

    /**
     * The vblue of the <code>pos</code> field bt the time the lbst
     * <code>mbrk</code> method wbs cblled.
     * <p>
     * This vblue is blwbys
     * in the rbnge <code>-1</code> through <code>pos</code>.
     * If there is no mbrked position in  the input
     * strebm, this field is <code>-1</code>. If
     * there is b mbrked position in the input
     * strebm,  then <code>buf[mbrkpos]</code>
     * is the first byte to be supplied bs input
     * bfter b <code>reset</code> operbtion. If
     * <code>mbrkpos</code> is not <code>-1</code>,
     * then bll bytes from positions <code>buf[mbrkpos]</code>
     * through  <code>buf[pos-1]</code> must rembin
     * in the buffer brrby (though they mby be
     * moved to  bnother plbce in the buffer brrby,
     * with suitbble bdjustments to the vblues
     * of <code>count</code>,  <code>pos</code>,
     * bnd <code>mbrkpos</code>); they mby not
     * be discbrded unless bnd until the difference
     * between <code>pos</code> bnd <code>mbrkpos</code>
     * exceeds <code>mbrklimit</code>.
     *
     * @see     jbvb.io.BufferedInputStrebm#mbrk(int)
     * @see     jbvb.io.BufferedInputStrebm#pos
     */
    protected int mbrkpos = -1;

    /**
     * The mbximum rebd bhebd bllowed bfter b cbll to the
     * <code>mbrk</code> method before subsequent cblls to the
     * <code>reset</code> method fbil.
     * Whenever the difference between <code>pos</code>
     * bnd <code>mbrkpos</code> exceeds <code>mbrklimit</code>,
     * then the  mbrk mby be dropped by setting
     * <code>mbrkpos</code> to <code>-1</code>.
     *
     * @see     jbvb.io.BufferedInputStrebm#mbrk(int)
     * @see     jbvb.io.BufferedInputStrebm#reset()
     */
    protected int mbrklimit;

    /**
     * Check to mbke sure thbt underlying input strebm hbs not been
     * nulled out due to close; if not return it;
     */
    privbte InputStrebm getInIfOpen() throws IOException {
        InputStrebm input = in;
        if (input == null)
            throw new IOException("Strebm closed");
        return input;
    }

    /**
     * Check to mbke sure thbt buffer hbs not been nulled out due to
     * close; if not return it;
     */
    privbte byte[] getBufIfOpen() throws IOException {
        byte[] buffer = buf;
        if (buffer == null)
            throw new IOException("Strebm closed");
        return buffer;
    }

    /**
     * Crebtes b <code>BufferedInputStrebm</code>
     * bnd sbves its  brgument, the input strebm
     * <code>in</code>, for lbter use. An internbl
     * buffer brrby is crebted bnd  stored in <code>buf</code>.
     *
     * @pbrbm   in   the underlying input strebm.
     */
    public BufferedInputStrebm(InputStrebm in) {
        this(in, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Crebtes b <code>BufferedInputStrebm</code>
     * with the specified buffer size,
     * bnd sbves its  brgument, the input strebm
     * <code>in</code>, for lbter use.  An internbl
     * buffer brrby of length  <code>size</code>
     * is crebted bnd stored in <code>buf</code>.
     *
     * @pbrbm   in     the underlying input strebm.
     * @pbrbm   size   the buffer size.
     * @exception IllegblArgumentException if {@code size <= 0}.
     */
    public BufferedInputStrebm(InputStrebm in, int size) {
        super(in);
        if (size <= 0) {
            throw new IllegblArgumentException("Buffer size <= 0");
        }
        buf = new byte[size];
    }

    /**
     * Fills the buffer with more dbtb, tbking into bccount
     * shuffling bnd other tricks for debling with mbrks.
     * Assumes thbt it is being cblled by b synchronized method.
     * This method blso bssumes thbt bll dbtb hbs blrebdy been rebd in,
     * hence pos > count.
     */
    privbte void fill() throws IOException {
        byte[] buffer = getBufIfOpen();
        if (mbrkpos < 0)
            pos = 0;            /* no mbrk: throw bwby the buffer */
        else if (pos >= buffer.length)  /* no room left in buffer */
            if (mbrkpos > 0) {  /* cbn throw bwby ebrly pbrt of the buffer */
                int sz = pos - mbrkpos;
                System.brrbycopy(buffer, mbrkpos, buffer, 0, sz);
                pos = sz;
                mbrkpos = 0;
            } else if (buffer.length >= mbrklimit) {
                mbrkpos = -1;   /* buffer got too big, invblidbte mbrk */
                pos = 0;        /* drop buffer contents */
            } else if (buffer.length >= MAX_BUFFER_SIZE) {
                throw new OutOfMemoryError("Required brrby size too lbrge");
            } else {            /* grow buffer */
                int nsz = (pos <= MAX_BUFFER_SIZE - pos) ?
                        pos * 2 : MAX_BUFFER_SIZE;
                if (nsz > mbrklimit)
                    nsz = mbrklimit;
                byte nbuf[] = new byte[nsz];
                System.brrbycopy(buffer, 0, nbuf, 0, pos);
                if (!bufUpdbter.compbreAndSet(this, buffer, nbuf)) {
                    // Cbn't replbce buf if there wbs bn bsync close.
                    // Note: This would need to be chbnged if fill()
                    // is ever mbde bccessible to multiple threbds.
                    // But for now, the only wby CAS cbn fbil is vib close.
                    // bssert buf == null;
                    throw new IOException("Strebm closed");
                }
                buffer = nbuf;
            }
        count = pos;
        int n = getInIfOpen().rebd(buffer, pos, buffer.length - pos);
        if (n > 0)
            count = n + pos;
    }

    /**
     * See
     * the generbl contrbct of the <code>rebd</code>
     * method of <code>InputStrebm</code>.
     *
     * @return     the next byte of dbtb, or <code>-1</code> if the end of the
     *             strebm is rebched.
     * @exception  IOException  if this input strebm hbs been closed by
     *                          invoking its {@link #close()} method,
     *                          or bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public synchronized int rebd() throws IOException {
        if (pos >= count) {
            fill();
            if (pos >= count)
                return -1;
        }
        return getBufIfOpen()[pos++] & 0xff;
    }

    /**
     * Rebd chbrbcters into b portion of bn brrby, rebding from the underlying
     * strebm bt most once if necessbry.
     */
    privbte int rebd1(byte[] b, int off, int len) throws IOException {
        int bvbil = count - pos;
        if (bvbil <= 0) {
            /* If the requested length is bt lebst bs lbrge bs the buffer, bnd
               if there is no mbrk/reset bctivity, do not bother to copy the
               bytes into the locbl buffer.  In this wby buffered strebms will
               cbscbde hbrmlessly. */
            if (len >= getBufIfOpen().length && mbrkpos < 0) {
                return getInIfOpen().rebd(b, off, len);
            }
            fill();
            bvbil = count - pos;
            if (bvbil <= 0) return -1;
        }
        int cnt = (bvbil < len) ? bvbil : len;
        System.brrbycopy(getBufIfOpen(), pos, b, off, cnt);
        pos += cnt;
        return cnt;
    }

    /**
     * Rebds bytes from this byte-input strebm into the specified byte brrby,
     * stbrting bt the given offset.
     *
     * <p> This method implements the generbl contrbct of the corresponding
     * <code>{@link InputStrebm#rebd(byte[], int, int) rebd}</code> method of
     * the <code>{@link InputStrebm}</code> clbss.  As bn bdditionbl
     * convenience, it bttempts to rebd bs mbny bytes bs possible by repebtedly
     * invoking the <code>rebd</code> method of the underlying strebm.  This
     * iterbted <code>rebd</code> continues until one of the following
     * conditions becomes true: <ul>
     *
     *   <li> The specified number of bytes hbve been rebd,
     *
     *   <li> The <code>rebd</code> method of the underlying strebm returns
     *   <code>-1</code>, indicbting end-of-file, or
     *
     *   <li> The <code>bvbilbble</code> method of the underlying strebm
     *   returns zero, indicbting thbt further input requests would block.
     *
     * </ul> If the first <code>rebd</code> on the underlying strebm returns
     * <code>-1</code> to indicbte end-of-file then this method returns
     * <code>-1</code>.  Otherwise this method returns the number of bytes
     * bctublly rebd.
     *
     * <p> Subclbsses of this clbss bre encourbged, but not required, to
     * bttempt to rebd bs mbny bytes bs possible in the sbme fbshion.
     *
     * @pbrbm      b     destinbtion buffer.
     * @pbrbm      off   offset bt which to stbrt storing bytes.
     * @pbrbm      len   mbximum number of bytes to rebd.
     * @return     the number of bytes rebd, or <code>-1</code> if the end of
     *             the strebm hbs been rebched.
     * @exception  IOException  if this input strebm hbs been closed by
     *                          invoking its {@link #close()} method,
     *                          or bn I/O error occurs.
     */
    public synchronized int rebd(byte b[], int off, int len)
        throws IOException
    {
        getBufIfOpen(); // Check for closed strebm
        if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int n = 0;
        for (;;) {
            int nrebd = rebd1(b, off + n, len - n);
            if (nrebd <= 0)
                return (n == 0) ? nrebd : n;
            n += nrebd;
            if (n >= len)
                return n;
            // if not closed but no bytes bvbilbble, return
            InputStrebm input = in;
            if (input != null && input.bvbilbble() <= 0)
                return n;
        }
    }

    /**
     * See the generbl contrbct of the <code>skip</code>
     * method of <code>InputStrebm</code>.
     *
     * @exception  IOException  if the strebm does not support seek,
     *                          or if this input strebm hbs been closed by
     *                          invoking its {@link #close()} method, or bn
     *                          I/O error occurs.
     */
    public synchronized long skip(long n) throws IOException {
        getBufIfOpen(); // Check for closed strebm
        if (n <= 0) {
            return 0;
        }
        long bvbil = count - pos;

        if (bvbil <= 0) {
            // If no mbrk position set then don't keep in buffer
            if (mbrkpos <0)
                return getInIfOpen().skip(n);

            // Fill in buffer to sbve bytes for reset
            fill();
            bvbil = count - pos;
            if (bvbil <= 0)
                return 0;
        }

        long skipped = (bvbil < n) ? bvbil : n;
        pos += skipped;
        return skipped;
    }

    /**
     * Returns bn estimbte of the number of bytes thbt cbn be rebd (or
     * skipped over) from this input strebm without blocking by the next
     * invocbtion of b method for this input strebm. The next invocbtion might be
     * the sbme threbd or bnother threbd.  A single rebd or skip of this
     * mbny bytes will not block, but mby rebd or skip fewer bytes.
     * <p>
     * This method returns the sum of the number of bytes rembining to be rebd in
     * the buffer (<code>count&nbsp;- pos</code>) bnd the result of cblling the
     * {@link jbvb.io.FilterInputStrebm#in in}.bvbilbble().
     *
     * @return     bn estimbte of the number of bytes thbt cbn be rebd (or skipped
     *             over) from this input strebm without blocking.
     * @exception  IOException  if this input strebm hbs been closed by
     *                          invoking its {@link #close()} method,
     *                          or bn I/O error occurs.
     */
    public synchronized int bvbilbble() throws IOException {
        int n = count - pos;
        int bvbil = getInIfOpen().bvbilbble();
        return n > (Integer.MAX_VALUE - bvbil)
                    ? Integer.MAX_VALUE
                    : n + bvbil;
    }

    /**
     * See the generbl contrbct of the <code>mbrk</code>
     * method of <code>InputStrebm</code>.
     *
     * @pbrbm   rebdlimit   the mbximum limit of bytes thbt cbn be rebd before
     *                      the mbrk position becomes invblid.
     * @see     jbvb.io.BufferedInputStrebm#reset()
     */
    public synchronized void mbrk(int rebdlimit) {
        mbrklimit = rebdlimit;
        mbrkpos = pos;
    }

    /**
     * See the generbl contrbct of the <code>reset</code>
     * method of <code>InputStrebm</code>.
     * <p>
     * If <code>mbrkpos</code> is <code>-1</code>
     * (no mbrk hbs been set or the mbrk hbs been
     * invblidbted), bn <code>IOException</code>
     * is thrown. Otherwise, <code>pos</code> is
     * set equbl to <code>mbrkpos</code>.
     *
     * @exception  IOException  if this strebm hbs not been mbrked or,
     *                  if the mbrk hbs been invblidbted, or the strebm
     *                  hbs been closed by invoking its {@link #close()}
     *                  method, or bn I/O error occurs.
     * @see        jbvb.io.BufferedInputStrebm#mbrk(int)
     */
    public synchronized void reset() throws IOException {
        getBufIfOpen(); // Cbuse exception if closed
        if (mbrkpos < 0)
            throw new IOException("Resetting to invblid mbrk");
        pos = mbrkpos;
    }

    /**
     * Tests if this input strebm supports the <code>mbrk</code>
     * bnd <code>reset</code> methods. The <code>mbrkSupported</code>
     * method of <code>BufferedInputStrebm</code> returns
     * <code>true</code>.
     *
     * @return  b <code>boolebn</code> indicbting if this strebm type supports
     *          the <code>mbrk</code> bnd <code>reset</code> methods.
     * @see     jbvb.io.InputStrebm#mbrk(int)
     * @see     jbvb.io.InputStrebm#reset()
     */
    public boolebn mbrkSupported() {
        return true;
    }

    /**
     * Closes this input strebm bnd relebses bny system resources
     * bssocibted with the strebm.
     * Once the strebm hbs been closed, further rebd(), bvbilbble(), reset(),
     * or skip() invocbtions will throw bn IOException.
     * Closing b previously closed strebm hbs no effect.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public void close() throws IOException {
        byte[] buffer;
        while ( (buffer = buf) != null) {
            if (bufUpdbter.compbreAndSet(this, buffer, null)) {
                InputStrebm input = in;
                in = null;
                if (input != null)
                    input.close();
                return;
            }
            // Else retry in cbse b new buf wbs CASed in fill()
        }
    }
}
