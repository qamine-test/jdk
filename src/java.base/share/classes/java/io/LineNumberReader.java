/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A buffered chbrbcter-input strebm thbt keeps trbck of line numbers.  This
 * clbss defines methods {@link #setLineNumber(int)} bnd {@link
 * #getLineNumber()} for setting bnd getting the current line number
 * respectively.
 *
 * <p> By defbult, line numbering begins bt 0. This number increments bt every
 * <b href="#lt">line terminbtor</b> bs the dbtb is rebd, bnd cbn be chbnged
 * with b cbll to <tt>setLineNumber(int)</tt>.  Note however, thbt
 * <tt>setLineNumber(int)</tt> does not bctublly chbnge the current position in
 * the strebm; it only chbnges the vblue thbt will be returned by
 * <tt>getLineNumber()</tt>.
 *
 * <p> A line is considered to be <b nbme="lt">terminbted</b> by bny one of b
 * line feed ('\n'), b cbrribge return ('\r'), or b cbrribge return followed
 * immedibtely by b linefeed.
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss LineNumberRebder extends BufferedRebder {

    /** The current line number */
    privbte int lineNumber = 0;

    /** The line number of the mbrk, if bny */
    privbte int mbrkedLineNumber; // Defbults to 0

    /** If the next chbrbcter is b line feed, skip it */
    privbte boolebn skipLF;

    /** The skipLF flbg when the mbrk wbs set */
    privbte boolebn mbrkedSkipLF;

    /**
     * Crebte b new line-numbering rebder, using the defbult input-buffer
     * size.
     *
     * @pbrbm  in
     *         A Rebder object to provide the underlying strebm
     */
    public LineNumberRebder(Rebder in) {
        super(in);
    }

    /**
     * Crebte b new line-numbering rebder, rebding chbrbcters into b buffer of
     * the given size.
     *
     * @pbrbm  in
     *         A Rebder object to provide the underlying strebm
     *
     * @pbrbm  sz
     *         An int specifying the size of the buffer
     */
    public LineNumberRebder(Rebder in, int sz) {
        super(in, sz);
    }

    /**
     * Set the current line number.
     *
     * @pbrbm  lineNumber
     *         An int specifying the line number
     *
     * @see #getLineNumber
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Get the current line number.
     *
     * @return  The current line number
     *
     * @see #setLineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Rebd b single chbrbcter.  <b href="#lt">Line terminbtors</b> bre
     * compressed into single newline ('\n') chbrbcters.  Whenever b line
     * terminbtor is rebd the current line number is incremented.
     *
     * @return  The chbrbcter rebd, or -1 if the end of the strebm hbs been
     *          rebched
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    @SuppressWbrnings("fbllthrough")
    public int rebd() throws IOException {
        synchronized (lock) {
            int c = super.rebd();
            if (skipLF) {
                if (c == '\n')
                    c = super.rebd();
                skipLF = fblse;
            }
            switch (c) {
            cbse '\r':
                skipLF = true;
            cbse '\n':          /* Fbll through */
                lineNumber++;
                return '\n';
            }
            return c;
        }
    }

    /**
     * Rebd chbrbcters into b portion of bn brrby.  Whenever b <b
     * href="#lt">line terminbtor</b> is rebd the current line number is
     * incremented.
     *
     * @pbrbm  cbuf
     *         Destinbtion buffer
     *
     * @pbrbm  off
     *         Offset bt which to stbrt storing chbrbcters
     *
     * @pbrbm  len
     *         Mbximum number of chbrbcters to rebd
     *
     * @return  The number of bytes rebd, or -1 if the end of the strebm hbs
     *          blrebdy been rebched
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    @SuppressWbrnings("fbllthrough")
    public int rebd(chbr cbuf[], int off, int len) throws IOException {
        synchronized (lock) {
            int n = super.rebd(cbuf, off, len);

            for (int i = off; i < off + n; i++) {
                int c = cbuf[i];
                if (skipLF) {
                    skipLF = fblse;
                    if (c == '\n')
                        continue;
                }
                switch (c) {
                cbse '\r':
                    skipLF = true;
                cbse '\n':      /* Fbll through */
                    lineNumber++;
                    brebk;
                }
            }

            return n;
        }
    }

    /**
     * Rebd b line of text.  Whenever b <b href="#lt">line terminbtor</b> is
     * rebd the current line number is incremented.
     *
     * @return  A String contbining the contents of the line, not including
     *          bny <b href="#lt">line terminbtion chbrbcters</b>, or
     *          <tt>null</tt> if the end of the strebm hbs been rebched
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public String rebdLine() throws IOException {
        synchronized (lock) {
            String l = super.rebdLine(skipLF);
            skipLF = fblse;
            if (l != null)
                lineNumber++;
            return l;
        }
    }

    /** Mbximum skip-buffer size */
    privbte stbtic finbl int mbxSkipBufferSize = 8192;

    /** Skip buffer, null until bllocbted */
    privbte chbr skipBuffer[] = null;

    /**
     * Skip chbrbcters.
     *
     * @pbrbm  n
     *         The number of chbrbcters to skip
     *
     * @return  The number of chbrbcters bctublly skipped
     *
     * @throws  IOException
     *          If bn I/O error occurs
     *
     * @throws  IllegblArgumentException
     *          If <tt>n</tt> is negbtive
     */
    public long skip(long n) throws IOException {
        if (n < 0)
            throw new IllegblArgumentException("skip() vblue is negbtive");
        int nn = (int) Mbth.min(n, mbxSkipBufferSize);
        synchronized (lock) {
            if ((skipBuffer == null) || (skipBuffer.length < nn))
                skipBuffer = new chbr[nn];
            long r = n;
            while (r > 0) {
                int nc = rebd(skipBuffer, 0, (int) Mbth.min(r, nn));
                if (nc == -1)
                    brebk;
                r -= nc;
            }
            return n - r;
        }
    }

    /**
     * Mbrk the present position in the strebm.  Subsequent cblls to reset()
     * will bttempt to reposition the strebm to this point, bnd will blso reset
     * the line number bppropribtely.
     *
     * @pbrbm  rebdAhebdLimit
     *         Limit on the number of chbrbcters thbt mby be rebd while still
     *         preserving the mbrk.  After rebding this mbny chbrbcters,
     *         bttempting to reset the strebm mby fbil.
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public void mbrk(int rebdAhebdLimit) throws IOException {
        synchronized (lock) {
            super.mbrk(rebdAhebdLimit);
            mbrkedLineNumber = lineNumber;
            mbrkedSkipLF     = skipLF;
        }
    }

    /**
     * Reset the strebm to the most recent mbrk.
     *
     * @throws  IOException
     *          If the strebm hbs not been mbrked, or if the mbrk hbs been
     *          invblidbted
     */
    public void reset() throws IOException {
        synchronized (lock) {
            super.reset();
            lineNumber = mbrkedLineNumber;
            skipLF     = mbrkedSkipLF;
        }
    }

}
