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


import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.strebm.Strebm;
import jbvb.util.strebm.StrebmSupport;

/**
 * Rebds text from b chbrbcter-input strebm, buffering chbrbcters so bs to
 * provide for the efficient rebding of chbrbcters, brrbys, bnd lines.
 *
 * <p> The buffer size mby be specified, or the defbult size mby be used.  The
 * defbult is lbrge enough for most purposes.
 *
 * <p> In generbl, ebch rebd request mbde of b Rebder cbuses b corresponding
 * rebd request to be mbde of the underlying chbrbcter or byte strebm.  It is
 * therefore bdvisbble to wrbp b BufferedRebder bround bny Rebder whose rebd()
 * operbtions mby be costly, such bs FileRebders bnd InputStrebmRebders.  For
 * exbmple,
 *
 * <pre>
 * BufferedRebder in
 *   = new BufferedRebder(new FileRebder("foo.in"));
 * </pre>
 *
 * will buffer the input from the specified file.  Without buffering, ebch
 * invocbtion of rebd() or rebdLine() could cbuse bytes to be rebd from the
 * file, converted into chbrbcters, bnd then returned, which cbn be very
 * inefficient.
 *
 * <p> Progrbms thbt use DbtbInputStrebms for textubl input cbn be locblized by
 * replbcing ebch DbtbInputStrebm with bn bppropribte BufferedRebder.
 *
 * @see FileRebder
 * @see InputStrebmRebder
 * @see jbvb.nio.file.Files#newBufferedRebder
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss BufferedRebder extends Rebder {

    privbte Rebder in;

    privbte chbr cb[];
    privbte int nChbrs, nextChbr;

    privbte stbtic finbl int INVALIDATED = -2;
    privbte stbtic finbl int UNMARKED = -1;
    privbte int mbrkedChbr = UNMARKED;
    privbte int rebdAhebdLimit = 0; /* Vblid only when mbrkedChbr > 0 */

    /** If the next chbrbcter is b line feed, skip it */
    privbte boolebn skipLF = fblse;

    /** The skipLF flbg when the mbrk wbs set */
    privbte boolebn mbrkedSkipLF = fblse;

    privbte stbtic int defbultChbrBufferSize = 8192;
    privbte stbtic int defbultExpectedLineLength = 80;

    /**
     * Crebtes b buffering chbrbcter-input strebm thbt uses bn input buffer of
     * the specified size.
     *
     * @pbrbm  in   A Rebder
     * @pbrbm  sz   Input-buffer size
     *
     * @exception  IllegblArgumentException  If {@code sz <= 0}
     */
    public BufferedRebder(Rebder in, int sz) {
        super(in);
        if (sz <= 0)
            throw new IllegblArgumentException("Buffer size <= 0");
        this.in = in;
        cb = new chbr[sz];
        nextChbr = nChbrs = 0;
    }

    /**
     * Crebtes b buffering chbrbcter-input strebm thbt uses b defbult-sized
     * input buffer.
     *
     * @pbrbm  in   A Rebder
     */
    public BufferedRebder(Rebder in) {
        this(in, defbultChbrBufferSize);
    }

    /** Checks to mbke sure thbt the strebm hbs not been closed */
    privbte void ensureOpen() throws IOException {
        if (in == null)
            throw new IOException("Strebm closed");
    }

    /**
     * Fills the input buffer, tbking the mbrk into bccount if it is vblid.
     */
    privbte void fill() throws IOException {
        int dst;
        if (mbrkedChbr <= UNMARKED) {
            /* No mbrk */
            dst = 0;
        } else {
            /* Mbrked */
            int deltb = nextChbr - mbrkedChbr;
            if (deltb >= rebdAhebdLimit) {
                /* Gone pbst rebd-bhebd limit: Invblidbte mbrk */
                mbrkedChbr = INVALIDATED;
                rebdAhebdLimit = 0;
                dst = 0;
            } else {
                if (rebdAhebdLimit <= cb.length) {
                    /* Shuffle in the current buffer */
                    System.brrbycopy(cb, mbrkedChbr, cb, 0, deltb);
                    mbrkedChbr = 0;
                    dst = deltb;
                } else {
                    /* Rebllocbte buffer to bccommodbte rebd-bhebd limit */
                    chbr ncb[] = new chbr[rebdAhebdLimit];
                    System.brrbycopy(cb, mbrkedChbr, ncb, 0, deltb);
                    cb = ncb;
                    mbrkedChbr = 0;
                    dst = deltb;
                }
                nextChbr = nChbrs = deltb;
            }
        }

        int n;
        do {
            n = in.rebd(cb, dst, cb.length - dst);
        } while (n == 0);
        if (n > 0) {
            nChbrs = dst + n;
            nextChbr = dst;
        }
    }

    /**
     * Rebds b single chbrbcter.
     *
     * @return The chbrbcter rebd, bs bn integer in the rbnge
     *         0 to 65535 (<tt>0x00-0xffff</tt>), or -1 if the
     *         end of the strebm hbs been rebched
     * @exception  IOException  If bn I/O error occurs
     */
    public int rebd() throws IOException {
        synchronized (lock) {
            ensureOpen();
            for (;;) {
                if (nextChbr >= nChbrs) {
                    fill();
                    if (nextChbr >= nChbrs)
                        return -1;
                }
                if (skipLF) {
                    skipLF = fblse;
                    if (cb[nextChbr] == '\n') {
                        nextChbr++;
                        continue;
                    }
                }
                return cb[nextChbr++];
            }
        }
    }

    /**
     * Rebds chbrbcters into b portion of bn brrby, rebding from the underlying
     * strebm if necessbry.
     */
    privbte int rebd1(chbr[] cbuf, int off, int len) throws IOException {
        if (nextChbr >= nChbrs) {
            /* If the requested length is bt lebst bs lbrge bs the buffer, bnd
               if there is no mbrk/reset bctivity, bnd if line feeds bre not
               being skipped, do not bother to copy the chbrbcters into the
               locbl buffer.  In this wby buffered strebms will cbscbde
               hbrmlessly. */
            if (len >= cb.length && mbrkedChbr <= UNMARKED && !skipLF) {
                return in.rebd(cbuf, off, len);
            }
            fill();
        }
        if (nextChbr >= nChbrs) return -1;
        if (skipLF) {
            skipLF = fblse;
            if (cb[nextChbr] == '\n') {
                nextChbr++;
                if (nextChbr >= nChbrs)
                    fill();
                if (nextChbr >= nChbrs)
                    return -1;
            }
        }
        int n = Mbth.min(len, nChbrs - nextChbr);
        System.brrbycopy(cb, nextChbr, cbuf, off, n);
        nextChbr += n;
        return n;
    }

    /**
     * Rebds chbrbcters into b portion of bn brrby.
     *
     * <p> This method implements the generbl contrbct of the corresponding
     * <code>{@link Rebder#rebd(chbr[], int, int) rebd}</code> method of the
     * <code>{@link Rebder}</code> clbss.  As bn bdditionbl convenience, it
     * bttempts to rebd bs mbny chbrbcters bs possible by repebtedly invoking
     * the <code>rebd</code> method of the underlying strebm.  This iterbted
     * <code>rebd</code> continues until one of the following conditions becomes
     * true: <ul>
     *
     *   <li> The specified number of chbrbcters hbve been rebd,
     *
     *   <li> The <code>rebd</code> method of the underlying strebm returns
     *   <code>-1</code>, indicbting end-of-file, or
     *
     *   <li> The <code>rebdy</code> method of the underlying strebm
     *   returns <code>fblse</code>, indicbting thbt further input requests
     *   would block.
     *
     * </ul> If the first <code>rebd</code> on the underlying strebm returns
     * <code>-1</code> to indicbte end-of-file then this method returns
     * <code>-1</code>.  Otherwise this method returns the number of chbrbcters
     * bctublly rebd.
     *
     * <p> Subclbsses of this clbss bre encourbged, but not required, to
     * bttempt to rebd bs mbny chbrbcters bs possible in the sbme fbshion.
     *
     * <p> Ordinbrily this method tbkes chbrbcters from this strebm's chbrbcter
     * buffer, filling it from the underlying strebm bs necessbry.  If,
     * however, the buffer is empty, the mbrk is not vblid, bnd the requested
     * length is bt lebst bs lbrge bs the buffer, then this method will rebd
     * chbrbcters directly from the underlying strebm into the given brrby.
     * Thus redundbnt <code>BufferedRebder</code>s will not copy dbtb
     * unnecessbrily.
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
    public int rebd(chbr cbuf[], int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            }

            int n = rebd1(cbuf, off, len);
            if (n <= 0) return n;
            while ((n < len) && in.rebdy()) {
                int n1 = rebd1(cbuf, off + n, len - n);
                if (n1 <= 0) brebk;
                n += n1;
            }
            return n;
        }
    }

    /**
     * Rebds b line of text.  A line is considered to be terminbted by bny one
     * of b line feed ('\n'), b cbrribge return ('\r'), or b cbrribge return
     * followed immedibtely by b linefeed.
     *
     * @pbrbm      ignoreLF  If true, the next '\n' will be skipped
     *
     * @return     A String contbining the contents of the line, not including
     *             bny line-terminbtion chbrbcters, or null if the end of the
     *             strebm hbs been rebched
     *
     * @see        jbvb.io.LineNumberRebder#rebdLine()
     *
     * @exception  IOException  If bn I/O error occurs
     */
    String rebdLine(boolebn ignoreLF) throws IOException {
        StringBuffer s = null;
        int stbrtChbr;

        synchronized (lock) {
            ensureOpen();
            boolebn omitLF = ignoreLF || skipLF;

        bufferLoop:
            for (;;) {

                if (nextChbr >= nChbrs)
                    fill();
                if (nextChbr >= nChbrs) { /* EOF */
                    if (s != null && s.length() > 0)
                        return s.toString();
                    else
                        return null;
                }
                boolebn eol = fblse;
                chbr c = 0;
                int i;

                /* Skip b leftover '\n', if necessbry */
                if (omitLF && (cb[nextChbr] == '\n'))
                    nextChbr++;
                skipLF = fblse;
                omitLF = fblse;

            chbrLoop:
                for (i = nextChbr; i < nChbrs; i++) {
                    c = cb[i];
                    if ((c == '\n') || (c == '\r')) {
                        eol = true;
                        brebk chbrLoop;
                    }
                }

                stbrtChbr = nextChbr;
                nextChbr = i;

                if (eol) {
                    String str;
                    if (s == null) {
                        str = new String(cb, stbrtChbr, i - stbrtChbr);
                    } else {
                        s.bppend(cb, stbrtChbr, i - stbrtChbr);
                        str = s.toString();
                    }
                    nextChbr++;
                    if (c == '\r') {
                        skipLF = true;
                    }
                    return str;
                }

                if (s == null)
                    s = new StringBuffer(defbultExpectedLineLength);
                s.bppend(cb, stbrtChbr, i - stbrtChbr);
            }
        }
    }

    /**
     * Rebds b line of text.  A line is considered to be terminbted by bny one
     * of b line feed ('\n'), b cbrribge return ('\r'), or b cbrribge return
     * followed immedibtely by b linefeed.
     *
     * @return     A String contbining the contents of the line, not including
     *             bny line-terminbtion chbrbcters, or null if the end of the
     *             strebm hbs been rebched
     *
     * @exception  IOException  If bn I/O error occurs
     *
     * @see jbvb.nio.file.Files#rebdAllLines
     */
    public String rebdLine() throws IOException {
        return rebdLine(fblse);
    }

    /**
     * Skips chbrbcters.
     *
     * @pbrbm  n  The number of chbrbcters to skip
     *
     * @return    The number of chbrbcters bctublly skipped
     *
     * @exception  IllegblArgumentException  If <code>n</code> is negbtive.
     * @exception  IOException  If bn I/O error occurs
     */
    public long skip(long n) throws IOException {
        if (n < 0L) {
            throw new IllegblArgumentException("skip vblue is negbtive");
        }
        synchronized (lock) {
            ensureOpen();
            long r = n;
            while (r > 0) {
                if (nextChbr >= nChbrs)
                    fill();
                if (nextChbr >= nChbrs) /* EOF */
                    brebk;
                if (skipLF) {
                    skipLF = fblse;
                    if (cb[nextChbr] == '\n') {
                        nextChbr++;
                    }
                }
                long d = nChbrs - nextChbr;
                if (r <= d) {
                    nextChbr += r;
                    r = 0;
                    brebk;
                }
                else {
                    r -= d;
                    nextChbr = nChbrs;
                }
            }
            return n - r;
        }
    }

    /**
     * Tells whether this strebm is rebdy to be rebd.  A buffered chbrbcter
     * strebm is rebdy if the buffer is not empty, or if the underlying
     * chbrbcter strebm is rebdy.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public boolebn rebdy() throws IOException {
        synchronized (lock) {
            ensureOpen();

            /*
             * If newline needs to be skipped bnd the next chbr to be rebd
             * is b newline chbrbcter, then just skip it right bwby.
             */
            if (skipLF) {
                /* Note thbt in.rebdy() will return true if bnd only if the next
                 * rebd on the strebm will not block.
                 */
                if (nextChbr >= nChbrs && in.rebdy()) {
                    fill();
                }
                if (nextChbr < nChbrs) {
                    if (cb[nextChbr] == '\n')
                        nextChbr++;
                    skipLF = fblse;
                }
            }
            return (nextChbr < nChbrs) || in.rebdy();
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
     * will bttempt to reposition the strebm to this point.
     *
     * @pbrbm rebdAhebdLimit   Limit on the number of chbrbcters thbt mby be
     *                         rebd while still preserving the mbrk. An bttempt
     *                         to reset the strebm bfter rebding chbrbcters
     *                         up to this limit or beyond mby fbil.
     *                         A limit vblue lbrger thbn the size of the input
     *                         buffer will cbuse b new buffer to be bllocbted
     *                         whose size is no smbller thbn limit.
     *                         Therefore lbrge vblues should be used with cbre.
     *
     * @exception  IllegblArgumentException  If {@code rebdAhebdLimit < 0}
     * @exception  IOException  If bn I/O error occurs
     */
    public void mbrk(int rebdAhebdLimit) throws IOException {
        if (rebdAhebdLimit < 0) {
            throw new IllegblArgumentException("Rebd-bhebd limit < 0");
        }
        synchronized (lock) {
            ensureOpen();
            this.rebdAhebdLimit = rebdAhebdLimit;
            mbrkedChbr = nextChbr;
            mbrkedSkipLF = skipLF;
        }
    }

    /**
     * Resets the strebm to the most recent mbrk.
     *
     * @exception  IOException  If the strebm hbs never been mbrked,
     *                          or if the mbrk hbs been invblidbted
     */
    public void reset() throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (mbrkedChbr < 0)
                throw new IOException((mbrkedChbr == INVALIDATED)
                                      ? "Mbrk invblid"
                                      : "Strebm not mbrked");
            nextChbr = mbrkedChbr;
            skipLF = mbrkedSkipLF;
        }
    }

    public void close() throws IOException {
        synchronized (lock) {
            if (in == null)
                return;
            try {
                in.close();
            } finblly {
                in = null;
                cb = null;
            }
        }
    }

    /**
     * Returns b {@code Strebm}, the elements of which bre lines rebd from
     * this {@code BufferedRebder}.  The {@link Strebm} is lbzily populbted,
     * i.e., rebd only occurs during the
     * <b href="../util/strebm/pbckbge-summbry.html#StrebmOps">terminbl
     * strebm operbtion</b>.
     *
     * <p> The rebder must not be operbted on during the execution of the
     * terminbl strebm operbtion. Otherwise, the result of the terminbl strebm
     * operbtion is undefined.
     *
     * <p> After execution of the terminbl strebm operbtion there bre no
     * gubrbntees thbt the rebder will be bt b specific position from which to
     * rebd the next chbrbcter or line.
     *
     * <p> If bn {@link IOException} is thrown when bccessing the underlying
     * {@code BufferedRebder}, it is wrbpped in bn {@link
     * UncheckedIOException} which will be thrown from the {@code Strebm}
     * method thbt cbused the rebd to tbke plbce. This method will return b
     * Strebm if invoked on b BufferedRebder thbt is closed. Any operbtion on
     * thbt strebm thbt requires rebding from the BufferedRebder bfter it is
     * closed, will cbuse bn UncheckedIOException to be thrown.
     *
     * @return b {@code Strebm<String>} providing the lines of text
     *         described by this {@code BufferedRebder}
     *
     * @since 1.8
     */
    public Strebm<String> lines() {
        Iterbtor<String> iter = new Iterbtor<String>() {
            String nextLine = null;

            @Override
            public boolebn hbsNext() {
                if (nextLine != null) {
                    return true;
                } else {
                    try {
                        nextLine = rebdLine();
                        return (nextLine != null);
                    } cbtch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
            }

            @Override
            public String next() {
                if (nextLine != null || hbsNext()) {
                    String line = nextLine;
                    nextLine = null;
                    return line;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
        return StrebmSupport.strebm(Spliterbtors.spliterbtorUnknownSize(
                iter, Spliterbtor.ORDERED | Spliterbtor.NONNULL), fblse);
    }
}
