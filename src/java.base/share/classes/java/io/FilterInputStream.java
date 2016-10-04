/*
 * Copyright (c) 1994, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A <code>FilterInputStrebm</code> contbins
 * some other input strebm, which it uses bs
 * its  bbsic source of dbtb, possibly trbnsforming
 * the dbtb blong the wby or providing  bdditionbl
 * functionblity. The clbss <code>FilterInputStrebm</code>
 * itself simply overrides bll  methods of
 * <code>InputStrebm</code> with versions thbt
 * pbss bll requests to the contbined  input
 * strebm. Subclbsses of <code>FilterInputStrebm</code>
 * mby further override some of  these methods
 * bnd mby blso provide bdditionbl methods
 * bnd fields.
 *
 * @buthor  Jonbthbn Pbyne
 * @since   1.0
 */
public
clbss FilterInputStrebm extends InputStrebm {
    /**
     * The input strebm to be filtered.
     */
    protected volbtile InputStrebm in;

    /**
     * Crebtes b <code>FilterInputStrebm</code>
     * by bssigning the  brgument <code>in</code>
     * to the field <code>this.in</code> so bs
     * to remember it for lbter use.
     *
     * @pbrbm   in   the underlying input strebm, or <code>null</code> if
     *          this instbnce is to be crebted without bn underlying strebm.
     */
    protected FilterInputStrebm(InputStrebm in) {
        this.in = in;
    }

    /**
     * Rebds the next byte of dbtb from this input strebm. The vblue
     * byte is returned bs bn <code>int</code> in the rbnge
     * <code>0</code> to <code>255</code>. If no byte is bvbilbble
     * becbuse the end of the strebm hbs been rebched, the vblue
     * <code>-1</code> is returned. This method blocks until input dbtb
     * is bvbilbble, the end of the strebm is detected, or bn exception
     * is thrown.
     * <p>
     * This method
     * simply performs <code>in.rebd()</code> bnd returns the result.
     *
     * @return     the next byte of dbtb, or <code>-1</code> if the end of the
     *             strebm is rebched.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public int rebd() throws IOException {
        return in.rebd();
    }

    /**
     * Rebds up to <code>byte.length</code> bytes of dbtb from this
     * input strebm into bn brrby of bytes. This method blocks until some
     * input is bvbilbble.
     * <p>
     * This method simply performs the cbll
     * <code>rebd(b, 0, b.length)</code> bnd returns
     * the  result. It is importbnt thbt it does
     * <i>not</i> do <code>in.rebd(b)</code> instebd;
     * certbin subclbsses of  <code>FilterInputStrebm</code>
     * depend on the implementbtion strbtegy bctublly
     * used.
     *
     * @pbrbm      b   the buffer into which the dbtb is rebd.
     * @return     the totbl number of bytes rebd into the buffer, or
     *             <code>-1</code> if there is no more dbtb becbuse the end of
     *             the strebm hbs been rebched.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#rebd(byte[], int, int)
     */
    public int rebd(byte b[]) throws IOException {
        return rebd(b, 0, b.length);
    }

    /**
     * Rebds up to <code>len</code> bytes of dbtb from this input strebm
     * into bn brrby of bytes. If <code>len</code> is not zero, the method
     * blocks until some input is bvbilbble; otherwise, no
     * bytes bre rebd bnd <code>0</code> is returned.
     * <p>
     * This method simply performs <code>in.rebd(b, off, len)</code>
     * bnd returns the result.
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
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public int rebd(byte b[], int off, int len) throws IOException {
        return in.rebd(b, off, len);
    }

    /**
     * Skips over bnd discbrds <code>n</code> bytes of dbtb from the
     * input strebm. The <code>skip</code> method mby, for b vbriety of
     * rebsons, end up skipping over some smbller number of bytes,
     * possibly <code>0</code>. The bctubl number of bytes skipped is
     * returned.
     * <p>
     * This method simply performs <code>in.skip(n)</code>.
     *
     * @pbrbm      n   the number of bytes to be skipped.
     * @return     the bctubl number of bytes skipped.
     * @exception  IOException  if the strebm does not support seek,
     *                          or if some other I/O error occurs.
     */
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    /**
     * Returns bn estimbte of the number of bytes thbt cbn be rebd (or
     * skipped over) from this input strebm without blocking by the next
     * cbller of b method for this input strebm. The next cbller might be
     * the sbme threbd or bnother threbd.  A single rebd or skip of this
     * mbny bytes will not block, but mby rebd or skip fewer bytes.
     * <p>
     * This method returns the result of {@link #in in}.bvbilbble().
     *
     * @return     bn estimbte of the number of bytes thbt cbn be rebd (or skipped
     *             over) from this input strebm without blocking.
     * @exception  IOException  if bn I/O error occurs.
     */
    public int bvbilbble() throws IOException {
        return in.bvbilbble();
    }

    /**
     * Closes this input strebm bnd relebses bny system resources
     * bssocibted with the strebm.
     * This
     * method simply performs <code>in.close()</code>.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public void close() throws IOException {
        in.close();
    }

    /**
     * Mbrks the current position in this input strebm. A subsequent
     * cbll to the <code>reset</code> method repositions this strebm bt
     * the lbst mbrked position so thbt subsequent rebds re-rebd the sbme bytes.
     * <p>
     * The <code>rebdlimit</code> brgument tells this input strebm to
     * bllow thbt mbny bytes to be rebd before the mbrk position gets
     * invblidbted.
     * <p>
     * This method simply performs <code>in.mbrk(rebdlimit)</code>.
     *
     * @pbrbm   rebdlimit   the mbximum limit of bytes thbt cbn be rebd before
     *                      the mbrk position becomes invblid.
     * @see     jbvb.io.FilterInputStrebm#in
     * @see     jbvb.io.FilterInputStrebm#reset()
     */
    public synchronized void mbrk(int rebdlimit) {
        in.mbrk(rebdlimit);
    }

    /**
     * Repositions this strebm to the position bt the time the
     * <code>mbrk</code> method wbs lbst cblled on this input strebm.
     * <p>
     * This method
     * simply performs <code>in.reset()</code>.
     * <p>
     * Strebm mbrks bre intended to be used in
     * situbtions where you need to rebd bhebd b little to see whbt's in
     * the strebm. Often this is most ebsily done by invoking some
     * generbl pbrser. If the strebm is of the type hbndled by the
     * pbrse, it just chugs blong hbppily. If the strebm is not of
     * thbt type, the pbrser should toss bn exception when it fbils.
     * If this hbppens within rebdlimit bytes, it bllows the outer
     * code to reset the strebm bnd try bnother pbrser.
     *
     * @exception  IOException  if the strebm hbs not been mbrked or if the
     *               mbrk hbs been invblidbted.
     * @see        jbvb.io.FilterInputStrebm#in
     * @see        jbvb.io.FilterInputStrebm#mbrk(int)
     */
    public synchronized void reset() throws IOException {
        in.reset();
    }

    /**
     * Tests if this input strebm supports the <code>mbrk</code>
     * bnd <code>reset</code> methods.
     * This method
     * simply performs <code>in.mbrkSupported()</code>.
     *
     * @return  <code>true</code> if this strebm type supports the
     *          <code>mbrk</code> bnd <code>reset</code> method;
     *          <code>fblse</code> otherwise.
     * @see     jbvb.io.FilterInputStrebm#in
     * @see     jbvb.io.InputStrebm#mbrk(int)
     * @see     jbvb.io.InputStrebm#reset()
     */
    public boolebn mbrkSupported() {
        return in.mbrkSupported();
    }
}
