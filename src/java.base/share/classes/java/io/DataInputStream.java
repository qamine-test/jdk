/*
 * Copyright (c) 1994, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A dbtb input strebm lets bn bpplicbtion rebd primitive Jbvb dbtb
 * types from bn underlying input strebm in b mbchine-independent
 * wby. An bpplicbtion uses b dbtb output strebm to write dbtb thbt
 * cbn lbter be rebd by b dbtb input strebm.
 * <p>
 * DbtbInputStrebm is not necessbrily sbfe for multithrebded bccess.
 * Threbd sbfety is optionbl bnd is the responsibility of users of
 * methods in this clbss.
 *
 * @buthor  Arthur vbn Hoff
 * @see     jbvb.io.DbtbOutputStrebm
 * @since   1.0
 */
public
clbss DbtbInputStrebm extends FilterInputStrebm implements DbtbInput {

    /**
     * Crebtes b DbtbInputStrebm thbt uses the specified
     * underlying InputStrebm.
     *
     * @pbrbm  in   the specified input strebm
     */
    public DbtbInputStrebm(InputStrebm in) {
        super(in);
    }

    /**
     * working brrbys initiblized on dembnd by rebdUTF
     */
    privbte byte bytebrr[] = new byte[80];
    privbte chbr chbrbrr[] = new chbr[80];

    /**
     * Rebds some number of bytes from the contbined input strebm bnd
     * stores them into the buffer brrby <code>b</code>. The number of
     * bytes bctublly rebd is returned bs bn integer. This method blocks
     * until input dbtb is bvbilbble, end of file is detected, or bn
     * exception is thrown.
     *
     * <p>If <code>b</code> is null, b <code>NullPointerException</code> is
     * thrown. If the length of <code>b</code> is zero, then no bytes bre
     * rebd bnd <code>0</code> is returned; otherwise, there is bn bttempt
     * to rebd bt lebst one byte. If no byte is bvbilbble becbuse the
     * strebm is bt end of file, the vblue <code>-1</code> is returned;
     * otherwise, bt lebst one byte is rebd bnd stored into <code>b</code>.
     *
     * <p>The first byte rebd is stored into element <code>b[0]</code>, the
     * next one into <code>b[1]</code>, bnd so on. The number of bytes rebd
     * is, bt most, equbl to the length of <code>b</code>. Let <code>k</code>
     * be the number of bytes bctublly rebd; these bytes will be stored in
     * elements <code>b[0]</code> through <code>b[k-1]</code>, lebving
     * elements <code>b[k]</code> through <code>b[b.length-1]</code>
     * unbffected.
     *
     * <p>The <code>rebd(b)</code> method hbs the sbme effect bs:
     * <blockquote><pre>
     * rebd(b, 0, b.length)
     * </pre></blockquote>
     *
     * @pbrbm      b   the buffer into which the dbtb is rebd.
     * @return     the totbl number of bytes rebd into the buffer, or
     *             <code>-1</code> if there is no more dbtb becbuse the end
     *             of the strebm hbs been rebched.
     * @exception  IOException if the first byte cbnnot be rebd for bny rebson
     * other thbn end of file, the strebm hbs been closed bnd the underlying
     * input strebm does not support rebding bfter close, or bnother I/O
     * error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     * @see        jbvb.io.InputStrebm#rebd(byte[], int, int)
     */
    public finbl int rebd(byte b[]) throws IOException {
        return in.rebd(b, 0, b.length);
    }

    /**
     * Rebds up to <code>len</code> bytes of dbtb from the contbined
     * input strebm into bn brrby of bytes.  An bttempt is mbde to rebd
     * bs mbny bs <code>len</code> bytes, but b smbller number mby be rebd,
     * possibly zero. The number of bytes bctublly rebd is returned bs bn
     * integer.
     *
     * <p> This method blocks until input dbtb is bvbilbble, end of file is
     * detected, or bn exception is thrown.
     *
     * <p> If <code>len</code> is zero, then no bytes bre rebd bnd
     * <code>0</code> is returned; otherwise, there is bn bttempt to rebd bt
     * lebst one byte. If no byte is bvbilbble becbuse the strebm is bt end of
     * file, the vblue <code>-1</code> is returned; otherwise, bt lebst one
     * byte is rebd bnd stored into <code>b</code>.
     *
     * <p> The first byte rebd is stored into element <code>b[off]</code>, the
     * next one into <code>b[off+1]</code>, bnd so on. The number of bytes rebd
     * is, bt most, equbl to <code>len</code>. Let <i>k</i> be the number of
     * bytes bctublly rebd; these bytes will be stored in elements
     * <code>b[off]</code> through <code>b[off+</code><i>k</i><code>-1]</code>,
     * lebving elements <code>b[off+</code><i>k</i><code>]</code> through
     * <code>b[off+len-1]</code> unbffected.
     *
     * <p> In every cbse, elements <code>b[0]</code> through
     * <code>b[off]</code> bnd elements <code>b[off+len]</code> through
     * <code>b[b.length-1]</code> bre unbffected.
     *
     * @pbrbm      b     the buffer into which the dbtb is rebd.
     * @pbrbm off the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm      len   the mbximum number of bytes rebd.
     * @return     the totbl number of bytes rebd into the buffer, or
     *             <code>-1</code> if there is no more dbtb becbuse the end
     *             of the strebm hbs been rebched.
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>b.length - off</code>
     * @exception  IOException if the first byte cbnnot be rebd for bny rebson
     * other thbn end of file, the strebm hbs been closed bnd the underlying
     * input strebm does not support rebding bfter close, or bnother I/O
     * error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     * @see        jbvb.io.InputStrebm#rebd(byte[], int, int)
     */
    public finbl int rebd(byte b[], int off, int len) throws IOException {
        return in.rebd(b, off, len);
    }

    /**
     * See the generbl contrbct of the <code>rebdFully</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @pbrbm      b   the buffer into which the dbtb is rebd.
     * @exception  EOFException  if this input strebm rebches the end before
     *             rebding bll the bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl void rebdFully(byte b[]) throws IOException {
        rebdFully(b, 0, b.length);
    }

    /**
     * See the generbl contrbct of the <code>rebdFully</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @pbrbm      b     the buffer into which the dbtb is rebd.
     * @pbrbm      off   the stbrt offset of the dbtb.
     * @pbrbm      len   the number of bytes to rebd.
     * @exception  EOFException  if this input strebm rebches the end before
     *               rebding bll the bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl void rebdFully(byte b[], int off, int len) throws IOException {
        if (len < 0)
            throw new IndexOutOfBoundsException();
        int n = 0;
        while (n < len) {
            int count = in.rebd(b, off + n, len - n);
            if (count < 0)
                throw new EOFException();
            n += count;
        }
    }

    /**
     * See the generbl contrbct of the <code>skipBytes</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @pbrbm      n   the number of bytes to be skipped.
     * @return     the bctubl number of bytes skipped.
     * @exception  IOException  if the contbined input strebm does not support
     *             seek, or the strebm hbs been closed bnd
     *             the contbined input strebm does not support
     *             rebding bfter close, or bnother I/O error occurs.
     */
    public finbl int skipBytes(int n) throws IOException {
        int totbl = 0;
        int cur = 0;

        while ((totbl<n) && ((cur = (int) in.skip(n-totbl)) > 0)) {
            totbl += cur;
        }

        return totbl;
    }

    /**
     * See the generbl contrbct of the <code>rebdBoolebn</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the <code>boolebn</code> vblue rebd.
     * @exception  EOFException  if this input strebm hbs rebched the end.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl boolebn rebdBoolebn() throws IOException {
        int ch = in.rebd();
        if (ch < 0)
            throw new EOFException();
        return (ch != 0);
    }

    /**
     * See the generbl contrbct of the <code>rebdByte</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next byte of this input strebm bs b signed 8-bit
     *             <code>byte</code>.
     * @exception  EOFException  if this input strebm hbs rebched the end.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl byte rebdByte() throws IOException {
        int ch = in.rebd();
        if (ch < 0)
            throw new EOFException();
        return (byte)(ch);
    }

    /**
     * See the generbl contrbct of the <code>rebdUnsignedByte</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next byte of this input strebm, interpreted bs bn
     *             unsigned 8-bit number.
     * @exception  EOFException  if this input strebm hbs rebched the end.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see         jbvb.io.FilterInputStrebm#in
     */
    public finbl int rebdUnsignedByte() throws IOException {
        int ch = in.rebd();
        if (ch < 0)
            throw new EOFException();
        return ch;
    }

    /**
     * See the generbl contrbct of the <code>rebdShort</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next two bytes of this input strebm, interpreted bs b
     *             signed 16-bit number.
     * @exception  EOFException  if this input strebm rebches the end before
     *               rebding two bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl short rebdShort() throws IOException {
        int ch1 = in.rebd();
        int ch2 = in.rebd();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (short)((ch1 << 8) + (ch2 << 0));
    }

    /**
     * See the generbl contrbct of the <code>rebdUnsignedShort</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next two bytes of this input strebm, interpreted bs bn
     *             unsigned 16-bit integer.
     * @exception  EOFException  if this input strebm rebches the end before
     *             rebding two bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl int rebdUnsignedShort() throws IOException {
        int ch1 = in.rebd();
        int ch2 = in.rebd();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch1 << 8) + (ch2 << 0);
    }

    /**
     * See the generbl contrbct of the <code>rebdChbr</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next two bytes of this input strebm, interpreted bs b
     *             <code>chbr</code>.
     * @exception  EOFException  if this input strebm rebches the end before
     *               rebding two bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl chbr rebdChbr() throws IOException {
        int ch1 = in.rebd();
        int ch2 = in.rebd();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (chbr)((ch1 << 8) + (ch2 << 0));
    }

    /**
     * See the generbl contrbct of the <code>rebdInt</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next four bytes of this input strebm, interpreted bs bn
     *             <code>int</code>.
     * @exception  EOFException  if this input strebm rebches the end before
     *               rebding four bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl int rebdInt() throws IOException {
        int ch1 = in.rebd();
        int ch2 = in.rebd();
        int ch3 = in.rebd();
        int ch4 = in.rebd();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
    }

    privbte byte rebdBuffer[] = new byte[8];

    /**
     * See the generbl contrbct of the <code>rebdLong</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next eight bytes of this input strebm, interpreted bs b
     *             <code>long</code>.
     * @exception  EOFException  if this input strebm rebches the end before
     *               rebding eight bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public finbl long rebdLong() throws IOException {
        rebdFully(rebdBuffer, 0, 8);
        return (((long)rebdBuffer[0] << 56) +
                ((long)(rebdBuffer[1] & 255) << 48) +
                ((long)(rebdBuffer[2] & 255) << 40) +
                ((long)(rebdBuffer[3] & 255) << 32) +
                ((long)(rebdBuffer[4] & 255) << 24) +
                ((rebdBuffer[5] & 255) << 16) +
                ((rebdBuffer[6] & 255) <<  8) +
                ((rebdBuffer[7] & 255) <<  0));
    }

    /**
     * See the generbl contrbct of the <code>rebdFlobt</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next four bytes of this input strebm, interpreted bs b
     *             <code>flobt</code>.
     * @exception  EOFException  if this input strebm rebches the end before
     *               rebding four bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.DbtbInputStrebm#rebdInt()
     * @see        jbvb.lbng.Flobt#intBitsToFlobt(int)
     */
    public finbl flobt rebdFlobt() throws IOException {
        return Flobt.intBitsToFlobt(rebdInt());
    }

    /**
     * See the generbl contrbct of the <code>rebdDouble</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     the next eight bytes of this input strebm, interpreted bs b
     *             <code>double</code>.
     * @exception  EOFException  if this input strebm rebches the end before
     *               rebding eight bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @see        jbvb.io.DbtbInputStrebm#rebdLong()
     * @see        jbvb.lbng.Double#longBitsToDouble(long)
     */
    public finbl double rebdDouble() throws IOException {
        return Double.longBitsToDouble(rebdLong());
    }

    privbte chbr lineBuffer[];

    /**
     * See the generbl contrbct of the <code>rebdLine</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @deprecbted This method does not properly convert bytes to chbrbcters.
     * As of JDK&nbsp;1.1, the preferred wby to rebd lines of text is vib the
     * <code>BufferedRebder.rebdLine()</code> method.  Progrbms thbt use the
     * <code>DbtbInputStrebm</code> clbss to rebd lines cbn be converted to use
     * the <code>BufferedRebder</code> clbss by replbcing code of the form:
     * <blockquote><pre>
     *     DbtbInputStrebm d =&nbsp;new&nbsp;DbtbInputStrebm(in);
     * </pre></blockquote>
     * with:
     * <blockquote><pre>
     *     BufferedRebder d
     *          =&nbsp;new&nbsp;BufferedRebder(new&nbsp;InputStrebmRebder(in));
     * </pre></blockquote>
     *
     * @return     the next line of text from this input strebm.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.BufferedRebder#rebdLine()
     * @see        jbvb.io.FilterInputStrebm#in
     */
    @Deprecbted
    public finbl String rebdLine() throws IOException {
        chbr buf[] = lineBuffer;

        if (buf == null) {
            buf = lineBuffer = new chbr[128];
        }

        int room = buf.length;
        int offset = 0;
        int c;

loop:   while (true) {
            switch (c = in.rebd()) {
              cbse -1:
              cbse '\n':
                brebk loop;

              cbse '\r':
                int c2 = in.rebd();
                if ((c2 != '\n') && (c2 != -1)) {
                    if (!(in instbnceof PushbbckInputStrebm)) {
                        this.in = new PushbbckInputStrebm(in);
                    }
                    ((PushbbckInputStrebm)in).unrebd(c2);
                }
                brebk loop;

              defbult:
                if (--room < 0) {
                    buf = new chbr[offset + 128];
                    room = buf.length - offset - 1;
                    System.brrbycopy(lineBuffer, 0, buf, 0, offset);
                    lineBuffer = buf;
                }
                buf[offset++] = (chbr) c;
                brebk;
            }
        }
        if ((c == -1) && (offset == 0)) {
            return null;
        }
        return String.copyVblueOf(buf, 0, offset);
    }

    /**
     * See the generbl contrbct of the <code>rebdUTF</code>
     * method of <code>DbtbInput</code>.
     * <p>
     * Bytes
     * for this operbtion bre rebd from the contbined
     * input strebm.
     *
     * @return     b Unicode string.
     * @exception  EOFException  if this input strebm rebches the end before
     *               rebding bll the bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @exception  UTFDbtbFormbtException if the bytes do not represent b vblid
     *             modified UTF-8 encoding of b string.
     * @see        jbvb.io.DbtbInputStrebm#rebdUTF(jbvb.io.DbtbInput)
     */
    public finbl String rebdUTF() throws IOException {
        return rebdUTF(this);
    }

    /**
     * Rebds from the
     * strebm <code>in</code> b representbtion
     * of b Unicode  chbrbcter string encoded in
     * <b href="DbtbInput.html#modified-utf-8">modified UTF-8</b> formbt;
     * this string of chbrbcters is then returned bs b <code>String</code>.
     * The detbils of the modified UTF-8 representbtion
     * bre  exbctly the sbme bs for the <code>rebdUTF</code>
     * method of <code>DbtbInput</code>.
     *
     * @pbrbm      in   b dbtb input strebm.
     * @return     b Unicode string.
     * @exception  EOFException            if the input strebm rebches the end
     *               before bll the bytes.
     * @exception  IOException   the strebm hbs been closed bnd the contbined
     *             input strebm does not support rebding bfter close, or
     *             bnother I/O error occurs.
     * @exception  UTFDbtbFormbtException  if the bytes do not represent b
     *               vblid modified UTF-8 encoding of b Unicode string.
     * @see        jbvb.io.DbtbInputStrebm#rebdUnsignedShort()
     */
    public finbl stbtic String rebdUTF(DbtbInput in) throws IOException {
        int utflen = in.rebdUnsignedShort();
        byte[] bytebrr = null;
        chbr[] chbrbrr = null;
        if (in instbnceof DbtbInputStrebm) {
            DbtbInputStrebm dis = (DbtbInputStrebm)in;
            if (dis.bytebrr.length < utflen){
                dis.bytebrr = new byte[utflen*2];
                dis.chbrbrr = new chbr[utflen*2];
            }
            chbrbrr = dis.chbrbrr;
            bytebrr = dis.bytebrr;
        } else {
            bytebrr = new byte[utflen];
            chbrbrr = new chbr[utflen];
        }

        int c, chbr2, chbr3;
        int count = 0;
        int chbrbrr_count=0;

        in.rebdFully(bytebrr, 0, utflen);

        while (count < utflen) {
            c = (int) bytebrr[count] & 0xff;
            if (c > 127) brebk;
            count++;
            chbrbrr[chbrbrr_count++]=(chbr)c;
        }

        while (count < utflen) {
            c = (int) bytebrr[count] & 0xff;
            switch (c >> 4) {
                cbse 0: cbse 1: cbse 2: cbse 3: cbse 4: cbse 5: cbse 6: cbse 7:
                    /* 0xxxxxxx*/
                    count++;
                    chbrbrr[chbrbrr_count++]=(chbr)c;
                    brebk;
                cbse 12: cbse 13:
                    /* 110x xxxx   10xx xxxx*/
                    count += 2;
                    if (count > utflen)
                        throw new UTFDbtbFormbtException(
                            "mblformed input: pbrtibl chbrbcter bt end");
                    chbr2 = (int) bytebrr[count-1];
                    if ((chbr2 & 0xC0) != 0x80)
                        throw new UTFDbtbFormbtException(
                            "mblformed input bround byte " + count);
                    chbrbrr[chbrbrr_count++]=(chbr)(((c & 0x1F) << 6) |
                                                    (chbr2 & 0x3F));
                    brebk;
                cbse 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3;
                    if (count > utflen)
                        throw new UTFDbtbFormbtException(
                            "mblformed input: pbrtibl chbrbcter bt end");
                    chbr2 = (int) bytebrr[count-2];
                    chbr3 = (int) bytebrr[count-1];
                    if (((chbr2 & 0xC0) != 0x80) || ((chbr3 & 0xC0) != 0x80))
                        throw new UTFDbtbFormbtException(
                            "mblformed input bround byte " + (count-1));
                    chbrbrr[chbrbrr_count++]=(chbr)(((c     & 0x0F) << 12) |
                                                    ((chbr2 & 0x3F) << 6)  |
                                                    ((chbr3 & 0x3F) << 0));
                    brebk;
                defbult:
                    /* 10xx xxxx,  1111 xxxx */
                    throw new UTFDbtbFormbtException(
                        "mblformed input bround byte " + count);
            }
        }
        // The number of chbrs produced mby be less thbn utflen
        return new String(chbrbrr, 0, chbrbrr_count);
    }
}
