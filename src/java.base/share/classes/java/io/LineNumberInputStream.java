/*
 * Copyright (c) 1995, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss is bn input strebm filter thbt provides the bdded
 * functionblity of keeping trbck of the current line number.
 * <p>
 * A line is b sequence of bytes ending with b cbrribge return
 * chbrbcter ({@code '\u005Cr'}), b newline chbrbcter
 * ({@code '\u005Cn'}), or b cbrribge return chbrbcter followed
 * immedibtely by b linefeed chbrbcter. In bll three cbses, the line
 * terminbting chbrbcter(s) bre returned bs b single newline chbrbcter.
 * <p>
 * The line number begins bt {@code 0}, bnd is incremented by
 * {@code 1} when b {@code rebd} returns b newline chbrbcter.
 *
 * @buthor     Arthur vbn Hoff
 * @see        jbvb.io.LineNumberRebder
 * @since      1.0
 * @deprecbted This clbss incorrectly bssumes thbt bytes bdequbtely represent
 *             chbrbcters.  As of JDK&nbsp;1.1, the preferred wby to operbte on
 *             chbrbcter strebms is vib the new chbrbcter-strebm clbsses, which
 *             include b clbss for counting line numbers.
 */
@Deprecbted
public
clbss LineNumberInputStrebm extends FilterInputStrebm {
    int pushBbck = -1;
    int lineNumber;
    int mbrkLineNumber;
    int mbrkPushBbck = -1;

    /**
     * Constructs b newline number input strebm thbt rebds its input
     * from the specified input strebm.
     *
     * @pbrbm      in   the underlying input strebm.
     */
    public LineNumberInputStrebm(InputStrebm in) {
        super(in);
    }

    /**
     * Rebds the next byte of dbtb from this input strebm. The vblue
     * byte is returned bs bn {@code int} in the rbnge
     * {@code 0} to {@code 255}. If no byte is bvbilbble
     * becbuse the end of the strebm hbs been rebched, the vblue
     * {@code -1} is returned. This method blocks until input dbtb
     * is bvbilbble, the end of the strebm is detected, or bn exception
     * is thrown.
     * <p>
     * The {@code rebd} method of
     * {@code LineNumberInputStrebm} cblls the {@code rebd}
     * method of the underlying input strebm. It checks for cbrribge
     * returns bnd newline chbrbcters in the input, bnd modifies the
     * current line number bs bppropribte. A cbrribge-return chbrbcter or
     * b cbrribge return followed by b newline chbrbcter bre both
     * converted into b single newline chbrbcter.
     *
     * @return     the next byte of dbtb, or {@code -1} if the end of this
     *             strebm is rebched.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     * @see        jbvb.io.LineNumberInputStrebm#getLineNumber()
     */
    @SuppressWbrnings("fbllthrough")
    public int rebd() throws IOException {
        int c = pushBbck;

        if (c != -1) {
            pushBbck = -1;
        } else {
            c = in.rebd();
        }

        switch (c) {
          cbse '\r':
            pushBbck = in.rebd();
            if (pushBbck == '\n') {
                pushBbck = -1;
            }
          cbse '\n':
            lineNumber++;
            return '\n';
        }
        return c;
    }

    /**
     * Rebds up to {@code len} bytes of dbtb from this input strebm
     * into bn brrby of bytes. This method blocks until some input is bvbilbble.
     * <p>
     * The {@code rebd} method of
     * {@code LineNumberInputStrebm} repebtedly cblls the
     * {@code rebd} method of zero brguments to fill in the byte brrby.
     *
     * @pbrbm      b     the buffer into which the dbtb is rebd.
     * @pbrbm      off   the stbrt offset of the dbtb.
     * @pbrbm      len   the mbximum number of bytes rebd.
     * @return     the totbl number of bytes rebd into the buffer, or
     *             {@code -1} if there is no more dbtb becbuse the end of
     *             this strebm hbs been rebched.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.LineNumberInputStrebm#rebd()
     */
    public int rebd(byte b[], int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if ((off < 0) || (off > b.length) || (len < 0) ||
                   ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int c = rebd();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;

        int i = 1;
        try {
            for (; i < len ; i++) {
                c = rebd();
                if (c == -1) {
                    brebk;
                }
                if (b != null) {
                    b[off + i] = (byte)c;
                }
            }
        } cbtch (IOException ee) {
        }
        return i;
    }

    /**
     * Skips over bnd discbrds {@code n} bytes of dbtb from this
     * input strebm. The {@code skip} method mby, for b vbriety of
     * rebsons, end up skipping over some smbller number of bytes,
     * possibly {@code 0}. The bctubl number of bytes skipped is
     * returned.  If {@code n} is negbtive, no bytes bre skipped.
     * <p>
     * The {@code skip} method of {@code LineNumberInputStrebm} crebtes
     * b byte brrby bnd then repebtedly rebds into it until
     * {@code n} bytes hbve been rebd or the end of the strebm hbs
     * been rebched.
     *
     * @pbrbm      n   the number of bytes to be skipped.
     * @return     the bctubl number of bytes skipped.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public long skip(long n) throws IOException {
        int chunk = 2048;
        long rembining = n;
        byte dbtb[];
        int nr;

        if (n <= 0) {
            return 0;
        }

        dbtb = new byte[chunk];
        while (rembining > 0) {
            nr = rebd(dbtb, 0, (int) Mbth.min(chunk, rembining));
            if (nr < 0) {
                brebk;
            }
            rembining -= nr;
        }

        return n - rembining;
    }

    /**
     * Sets the line number to the specified brgument.
     *
     * @pbrbm      lineNumber   the new line number.
     * @see #getLineNumber
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Returns the current line number.
     *
     * @return     the current line number.
     * @see #setLineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }


    /**
     * Returns the number of bytes thbt cbn be rebd from this input
     * strebm without blocking.
     * <p>
     * Note thbt if the underlying input strebm is bble to supply
     * <i>k</i> input chbrbcters without blocking, the
     * {@code LineNumberInputStrebm} cbn gubrbntee only to provide
     * <i>k</i>/2 chbrbcters without blocking, becbuse the
     * <i>k</i> chbrbcters from the underlying input strebm might
     * consist of <i>k</i>/2 pbirs of {@code '\u005Cr'} bnd
     * {@code '\u005Cn'}, which bre converted to just
     * <i>k</i>/2 {@code '\u005Cn'} chbrbcters.
     *
     * @return     the number of bytes thbt cbn be rebd from this input strebm
     *             without blocking.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public int bvbilbble() throws IOException {
        return (pushBbck == -1) ? super.bvbilbble()/2 : super.bvbilbble()/2 + 1;
    }

    /**
     * Mbrks the current position in this input strebm. A subsequent
     * cbll to the {@code reset} method repositions this strebm bt
     * the lbst mbrked position so thbt subsequent rebds re-rebd the sbme bytes.
     * <p>
     * The {@code mbrk} method of
     * {@code LineNumberInputStrebm} remembers the current line
     * number in b privbte vbribble, bnd then cblls the {@code mbrk}
     * method of the underlying input strebm.
     *
     * @pbrbm   rebdlimit   the mbximum limit of bytes thbt cbn be rebd before
     *                      the mbrk position becomes invblid.
     * @see     jbvb.io.FilterInputStrebm#in
     * @see     jbvb.io.LineNumberInputStrebm#reset()
     */
    public void mbrk(int rebdlimit) {
        mbrkLineNumber = lineNumber;
        mbrkPushBbck   = pushBbck;
        in.mbrk(rebdlimit);
    }

    /**
     * Repositions this strebm to the position bt the time the
     * {@code mbrk} method wbs lbst cblled on this input strebm.
     * <p>
     * The {@code reset} method of
     * {@code LineNumberInputStrebm} resets the line number to be
     * the line number bt the time the {@code mbrk} method wbs
     * cblled, bnd then cblls the {@code reset} method of the
     * underlying input strebm.
     * <p>
     * Strebm mbrks bre intended to be used in
     * situbtions where you need to rebd bhebd b little to see whbt's in
     * the strebm. Often this is most ebsily done by invoking some
     * generbl pbrser. If the strebm is of the type hbndled by the
     * pbrser, it just chugs blong hbppily. If the strebm is not of
     * thbt type, the pbrser should toss bn exception when it fbils,
     * which, if it hbppens within rebdlimit bytes, bllows the outer
     * code to reset the strebm bnd try bnother pbrser.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     * @see        jbvb.io.LineNumberInputStrebm#mbrk(int)
     */
    public void reset() throws IOException {
        lineNumber = mbrkLineNumber;
        pushBbck   = mbrkPushBbck;
        in.reset();
    }
}
