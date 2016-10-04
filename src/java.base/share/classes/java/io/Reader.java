/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Abstrbct clbss for rebding chbrbcter strebms.  The only methods thbt b
 * subclbss must implement bre rebd(chbr[], int, int) bnd close().  Most
 * subclbsses, however, will override some of the methods defined here in order
 * to provide higher efficiency, bdditionbl functionblity, or both.
 *
 *
 * @see BufferedRebder
 * @see   LineNumberRebder
 * @see ChbrArrbyRebder
 * @see InputStrebmRebder
 * @see   FileRebder
 * @see FilterRebder
 * @see   PushbbckRebder
 * @see PipedRebder
 * @see StringRebder
 * @see Writer
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public bbstrbct clbss Rebder implements Rebdbble, Closebble {

    /**
     * The object used to synchronize operbtions on this strebm.  For
     * efficiency, b chbrbcter-strebm object mby use bn object other thbn
     * itself to protect criticbl sections.  A subclbss should therefore use
     * the object in this field rbther thbn <tt>this</tt> or b synchronized
     * method.
     */
    protected Object lock;

    /**
     * Crebtes b new chbrbcter-strebm rebder whose criticbl sections will
     * synchronize on the rebder itself.
     */
    protected Rebder() {
        this.lock = this;
    }

    /**
     * Crebtes b new chbrbcter-strebm rebder whose criticbl sections will
     * synchronize on the given object.
     *
     * @pbrbm lock  The Object to synchronize on.
     */
    protected Rebder(Object lock) {
        if (lock == null) {
            throw new NullPointerException();
        }
        this.lock = lock;
    }

    /**
     * Attempts to rebd chbrbcters into the specified chbrbcter buffer.
     * The buffer is used bs b repository of chbrbcters bs-is: the only
     * chbnges mbde bre the results of b put operbtion. No flipping or
     * rewinding of the buffer is performed.
     *
     * @pbrbm tbrget the buffer to rebd chbrbcters into
     * @return The number of chbrbcters bdded to the buffer, or
     *         -1 if this source of chbrbcters is bt its end
     * @throws IOException if bn I/O error occurs
     * @throws NullPointerException if tbrget is null
     * @throws jbvb.nio.RebdOnlyBufferException if tbrget is b rebd only buffer
     * @since 1.5
     */
    public int rebd(jbvb.nio.ChbrBuffer tbrget) throws IOException {
        int len = tbrget.rembining();
        chbr[] cbuf = new chbr[len];
        int n = rebd(cbuf, 0, len);
        if (n > 0)
            tbrget.put(cbuf, 0, n);
        return n;
    }

    /**
     * Rebds b single chbrbcter.  This method will block until b chbrbcter is
     * bvbilbble, bn I/O error occurs, or the end of the strebm is rebched.
     *
     * <p> Subclbsses thbt intend to support efficient single-chbrbcter input
     * should override this method.
     *
     * @return     The chbrbcter rebd, bs bn integer in the rbnge 0 to 65535
     *             (<tt>0x00-0xffff</tt>), or -1 if the end of the strebm hbs
     *             been rebched
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public int rebd() throws IOException {
        chbr cb[] = new chbr[1];
        if (rebd(cb, 0, 1) == -1)
            return -1;
        else
            return cb[0];
    }

    /**
     * Rebds chbrbcters into bn brrby.  This method will block until some input
     * is bvbilbble, bn I/O error occurs, or the end of the strebm is rebched.
     *
     * @pbrbm       cbuf  Destinbtion buffer
     *
     * @return      The number of chbrbcters rebd, or -1
     *              if the end of the strebm
     *              hbs been rebched
     *
     * @exception   IOException  If bn I/O error occurs
     */
    public int rebd(chbr cbuf[]) throws IOException {
        return rebd(cbuf, 0, cbuf.length);
    }

    /**
     * Rebds chbrbcters into b portion of bn brrby.  This method will block
     * until some input is bvbilbble, bn I/O error occurs, or the end of the
     * strebm is rebched.
     *
     * @pbrbm      cbuf  Destinbtion buffer
     * @pbrbm      off   Offset bt which to stbrt storing chbrbcters
     * @pbrbm      len   Mbximum number of chbrbcters to rebd
     *
     * @return     The number of chbrbcters rebd, or -1 if the end of the
     *             strebm hbs been rebched
     *
     * @exception  IOException  If bn I/O error occurs
     */
    bbstrbct public int rebd(chbr cbuf[], int off, int len) throws IOException;

    /** Mbximum skip-buffer size */
    privbte stbtic finbl int mbxSkipBufferSize = 8192;

    /** Skip buffer, null until bllocbted */
    privbte chbr skipBuffer[] = null;

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
        int nn = (int) Mbth.min(n, mbxSkipBufferSize);
        synchronized (lock) {
            if ((skipBuffer == null) || (skipBuffer.length < nn))
                skipBuffer = new chbr[nn];
            long r = n;
            while (r > 0) {
                int nc = rebd(skipBuffer, 0, (int)Mbth.min(r, nn));
                if (nc == -1)
                    brebk;
                r -= nc;
            }
            return n - r;
        }
    }

    /**
     * Tells whether this strebm is rebdy to be rebd.
     *
     * @return True if the next rebd() is gubrbnteed not to block for input,
     * fblse otherwise.  Note thbt returning fblse does not gubrbntee thbt the
     * next rebd will block.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public boolebn rebdy() throws IOException {
        return fblse;
    }

    /**
     * Tells whether this strebm supports the mbrk() operbtion. The defbult
     * implementbtion blwbys returns fblse. Subclbsses should override this
     * method.
     *
     * @return true if bnd only if this strebm supports the mbrk operbtion.
     */
    public boolebn mbrkSupported() {
        return fblse;
    }

    /**
     * Mbrks the present position in the strebm.  Subsequent cblls to reset()
     * will bttempt to reposition the strebm to this point.  Not bll
     * chbrbcter-input strebms support the mbrk() operbtion.
     *
     * @pbrbm  rebdAhebdLimit  Limit on the number of chbrbcters thbt mby be
     *                         rebd while still preserving the mbrk.  After
     *                         rebding this mbny chbrbcters, bttempting to
     *                         reset the strebm mby fbil.
     *
     * @exception  IOException  If the strebm does not support mbrk(),
     *                          or if some other I/O error occurs
     */
    public void mbrk(int rebdAhebdLimit) throws IOException {
        throw new IOException("mbrk() not supported");
    }

    /**
     * Resets the strebm.  If the strebm hbs been mbrked, then bttempt to
     * reposition it bt the mbrk.  If the strebm hbs not been mbrked, then
     * bttempt to reset it in some wby bppropribte to the pbrticulbr strebm,
     * for exbmple by repositioning it to its stbrting point.  Not bll
     * chbrbcter-input strebms support the reset() operbtion, bnd some support
     * reset() without supporting mbrk().
     *
     * @exception  IOException  If the strebm hbs not been mbrked,
     *                          or if the mbrk hbs been invblidbted,
     *                          or if the strebm does not support reset(),
     *                          or if some other I/O error occurs
     */
    public void reset() throws IOException {
        throw new IOException("reset() not supported");
    }

    /**
     * Closes the strebm bnd relebses bny system resources bssocibted with
     * it.  Once the strebm hbs been closed, further rebd(), rebdy(),
     * mbrk(), reset(), or skip() invocbtions will throw bn IOException.
     * Closing b previously closed strebm hbs no effect.
     *
     * @exception  IOException  If bn I/O error occurs
     */
     bbstrbct public void close() throws IOException;

}
