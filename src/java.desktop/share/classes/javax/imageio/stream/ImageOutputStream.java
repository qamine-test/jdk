/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.strebm;

import jbvb.io.DbtbOutput;
import jbvb.io.IOException;

/**
 * A seekbble output strebm interfbce for use by
 * <code>ImbgeWriter</code>s.  Vbrious output destinbtions, such bs
 * <code>OutputStrebm</code>s bnd <code>File</code>s, bs well bs
 * future fbst I/O destinbtions mby be "wrbpped" by b suitbble
 * implementbtion of this interfbce for use by the Imbge I/O API.
 *
 * <p> Unlike b stbndbrd <code>OutputStrebm</code>, ImbgeOutputStrebm
 * extends its counterpbrt, <code>ImbgeInputStrebm</code>.  Thus it is
 * possible to rebd from the strebm bs it is being written.  The sbme
 * seek bnd flush positions bpply to both rebding bnd writing, blthough
 * the sembntics for debling with b non-zero bit offset before b byte-bligned
 * write bre necessbrily different from the sembntics for debling with
 * b non-zero bit offset before b byte-bligned rebd.  When rebding bytes,
 * bny bit offset is set to 0 before the rebd; when writing bytes, b
 * non-zero bit offset cbuses the rembining bits in the byte to be written
 * bs 0s.  The byte-bligned write then stbrts bt the next byte position.
 *
 * @see ImbgeInputStrebm
 *
 */
public interfbce ImbgeOutputStrebm extends ImbgeInputStrebm, DbtbOutput {

    /**
     * Writes b single byte to the strebm bt the current position.
     * The 24 high-order bits of <code>b</code> bre ignored.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.  Implementers cbn use the
     * {@link ImbgeOutputStrebmImpl#flushBits flushBits}
     * method of {@link ImbgeOutputStrebmImpl ImbgeOutputStrebmImpl}
     * to gubrbntee this.
     *
     * @pbrbm b bn <code>int</code> whose lower 8 bits bre to be
     * written.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void write(int b) throws IOException;

    /**
     * Writes b sequence of bytes to the strebm bt the current
     * position.  If <code>b.length</code> is 0, nothing is written.
     * The byte <code>b[0]</code> is written first, then the byte
     * <code>b[1]</code>, bnd so on.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm b bn brrby of <code>byte</code>s to be written.
     *
     * @exception NullPointerException if <code>b</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void write(byte b[]) throws IOException;

    /**
     * Writes b sequence of bytes to the strebm bt the current
     * position.  If <code>len</code> is 0, nothing is written.
     * The byte <code>b[off]</code> is written first, then the byte
     * <code>b[off + 1]</code>, bnd so on.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.  Implementers cbn use the
     * {@link ImbgeOutputStrebmImpl#flushBits flushBits}
     * method of {@link ImbgeOutputStrebmImpl ImbgeOutputStrebmImpl}
     * to gubrbntee this.
     *
     * @pbrbm b bn brrby of <code>byte</code>s to be written.
     * @pbrbm off the stbrt offset in the dbtb.
     * @pbrbm len the number of <code>byte</code>s to write.
     *
     * @exception IndexOutOfBoundsException if <code>off</code> is
     * negbtive, <code>len</code> is negbtive, or <code>off +
     * len</code> is grebter thbn <code>b.length</code>.
     * @exception NullPointerException if <code>b</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void write(byte b[], int off, int len) throws IOException;

    /**
     * Writes b <code>boolebn</code> vblue to the strebm.  If
     * <code>v</code> is true, the vblue <code>(byte)1</code> is
     * written; if <code>v</code> is fblse, the vblue
     * <code>(byte)0</code> is written.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm v the <code>boolebn</code> to be written.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void writeBoolebn(boolebn v) throws IOException;

    /**
     * Writes the 8 low-order bits of <code>v</code> to the
     * strebm. The 24 high-order bits of <code>v</code> bre ignored.
     * (This mebns thbt <code>writeByte</code> does exbctly the sbme
     * thing bs <code>write</code> for bn integer brgument.)
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm v bn <code>int</code> contbining the byte vblue to be
     * written.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void writeByte(int v) throws IOException;

    /**
     * Writes the 16 low-order bits of <code>v</code> to the
     * strebm. The 16 high-order bits of <code>v</code> bre ignored.
     * If the strebm uses network byte order, the bytes written, in
     * order, will be:
     *
     * <pre>
     * (byte)((v &gt;&gt; 8) &bmp; 0xff)
     * (byte)(v &bmp; 0xff)
     * </pre>
     *
     * Otherwise, the bytes written will be:
     *
     * <pre>
     * (byte)(v &bmp; 0xff)
     * (byte)((v &gt;&gt; 8) &bmp; 0xff)
     * </pre>
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm v bn <code>int</code> contbining the short vblue to be
     * written.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void writeShort(int v) throws IOException;

    /**
     * This method is b synonym for {@link #writeShort writeShort}.
     *
     * @pbrbm v bn <code>int</code> contbining the chbr (unsigned
     * short) vblue to be written.
     *
     * @exception IOException if bn I/O error occurs.
     *
     * @see #writeShort(int)
     */
    void writeChbr(int v) throws IOException;

    /**
     * Writes the 32 bits of <code>v</code> to the strebm.  If the
     * strebm uses network byte order, the bytes written, in order,
     * will be:
     *
     * <pre>
     * (byte)((v &gt;&gt; 24) &bmp; 0xff)
     * (byte)((v &gt;&gt; 16) &bmp; 0xff)
     * (byte)((v &gt;&gt; 8) &bmp; 0xff)
     * (byte)(v &bmp; 0xff)
     * </pre>
     *
     * Otheriwse, the bytes written will be:
     *
     * <pre>
     * (byte)(v &bmp; 0xff)
     * (byte)((v &gt;&gt; 8) &bmp; 0xff)
     * (byte)((v &gt;&gt; 16) &bmp; 0xff)
     * (byte)((v &gt;&gt; 24) &bmp; 0xff)
     * </pre>
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm v bn <code>int</code> contbining the vblue to be
     * written.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void writeInt(int v) throws IOException;

    /**
     * Writes the 64 bits of <code>v</code> to the strebm.  If the
     * strebm uses network byte order, the bytes written, in order,
     * will be:
     *
     * <pre>
     * (byte)((v &gt;&gt; 56) &bmp; 0xff)
     * (byte)((v &gt;&gt; 48) &bmp; 0xff)
     * (byte)((v &gt;&gt; 40) &bmp; 0xff)
     * (byte)((v &gt;&gt; 32) &bmp; 0xff)
     * (byte)((v &gt;&gt; 24) &bmp; 0xff)
     * (byte)((v &gt;&gt; 16) &bmp; 0xff)
     * (byte)((v &gt;&gt; 8) &bmp; 0xff)
     * (byte)(v &bmp; 0xff)
     * </pre>
     *
     * Otherwise, the bytes written will be:
     *
     * <pre>
     * (byte)(v &bmp; 0xff)
     * (byte)((v &gt;&gt; 8) &bmp; 0xff)
     * (byte)((v &gt;&gt; 16) &bmp; 0xff)
     * (byte)((v &gt;&gt; 24) &bmp; 0xff)
     * (byte)((v &gt;&gt; 32) &bmp; 0xff)
     * (byte)((v &gt;&gt; 40) &bmp; 0xff)
     * (byte)((v &gt;&gt; 48) &bmp; 0xff)
     * (byte)((v &gt;&gt; 56) &bmp; 0xff)
     * </pre>
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm v b <code>long</code> contbining the vblue to be
     * written.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void writeLong(long v) throws IOException;

    /**
     * Writes b <code>flobt</code> vblue, which is comprised of four
     * bytes, to the output strebm. It does this bs if it first
     * converts this <code>flobt</code> vblue to bn <code>int</code>
     * in exbctly the mbnner of the <code>Flobt.flobtToIntBits</code>
     * method bnd then writes the int vblue in exbctly the mbnner of
     * the <code>writeInt</code> method.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm v b <code>flobt</code> contbining the vblue to be
     * written.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void writeFlobt(flobt v) throws IOException;

    /**
     * Writes b <code>double</code> vblue, which is comprised of four
     * bytes, to the output strebm. It does this bs if it first
     * converts this <code>double</code> vblue to bn <code>long</code>
     * in exbctly the mbnner of the
     * <code>Double.doubleToLongBits</code> method bnd then writes the
     * long vblue in exbctly the mbnner of the <code>writeLong</code>
     * method.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm v b <code>double</code> contbining the vblue to be
     * written.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void writeDouble(double v) throws IOException;

    /**
     * Writes b string to the output strebm. For every chbrbcter in
     * the string <code>s</code>, tbken in order, one byte is written
     * to the output strebm. If <code>s</code> is <code>null</code>, b
     * <code>NullPointerException</code> is thrown.
     *
     * <p> If <code>s.length</code> is zero, then no bytes bre
     * written. Otherwise, the chbrbcter <code>s[0]</code> is written
     * first, then <code>s[1]</code>, bnd so on; the lbst chbrbcter
     * written is <code>s[s.length-1]</code>. For ebch chbrbcter, one
     * byte is written, the low-order byte, in exbctly the mbnner of
     * the <code>writeByte</code> method. The high-order eight bits of
     * ebch chbrbcter in the string bre ignored.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm s b <code>String</code> contbining the vblue to be
     * written.
     *
     * @exception NullPointerException if <code>s</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void writeBytes(String s) throws IOException;

    /**
     * Writes b string to the output strebm. For every chbrbcter in
     * the string <code>s</code>, tbken in order, two bytes bre
     * written to the output strebm, ordered bccording to the current
     * byte order setting.  If network byte order is being used, the
     * high-order byte is written first; the order is reversed
     * otherwise.  If <code>s</code> is <code>null</code>, b
     * <code>NullPointerException</code> is thrown.
     *
     * <p> If <code>s.length</code> is zero, then no bytes bre
     * written. Otherwise, the chbrbcter <code>s[0]</code> is written
     * first, then <code>s[1]</code>, bnd so on; the lbst chbrbcter
     * written is <code>s[s.length-1]</code>.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm s b <code>String</code> contbining the vblue to be
     * written.
     *
     * @exception NullPointerException if <code>s</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void writeChbrs(String s) throws IOException;

    /**
     * Writes two bytes of length informbtion to the output strebm in
     * network byte order, followed by the
     * <b href="../../../jbvb/io/DbtbInput.html#modified-utf-8">modified
     * UTF-8</b>
     * representbtion of every chbrbcter in the string <code>s</code>.
     * If <code>s</code> is <code>null</code>, b
     * <code>NullPointerException</code> is thrown.  Ebch chbrbcter in
     * the string <code>s</code> is converted to b group of one, two,
     * or three bytes, depending on the vblue of the chbrbcter.
     *
     * <p> If b chbrbcter <code>c</code> is in the rbnge
     * <code>&#92;u0001</code> through <code>&#92;u007f</code>, it is
     * represented by one byte:
     *
     * <pre>
     * (byte)c
     * </pre>
     *
     * <p> If b chbrbcter <code>c</code> is <code>&#92;u0000</code> or
     * is in the rbnge <code>&#92;u0080</code> through
     * <code>&#92;u07ff</code>, then it is represented by two bytes,
     * to be written in the order shown:
     *
     * <pre><code>
     * (byte)(0xc0 | (0x1f &bmp; (c &gt;&gt; 6)))
     * (byte)(0x80 | (0x3f &bmp; c))
     * </code></pre>
     *
     * <p> If b chbrbcter <code>c</code> is in the rbnge
     * <code>&#92;u0800</code> through <code>uffff</code>, then it is
     * represented by three bytes, to be written in the order shown:
     *
     * <pre><code>
     * (byte)(0xe0 | (0x0f &bmp; (c &gt;&gt; 12)))
     * (byte)(0x80 | (0x3f &bmp; (c &gt;&gt; 6)))
     * (byte)(0x80 | (0x3f &bmp; c))
     * </code></pre>
     *
     * <p> First, the totbl number of bytes needed to represent bll
     * the chbrbcters of <code>s</code> is cblculbted. If this number
     * is lbrger thbn <code>65535</code>, then b
     * <code>UTFDbtbFormbtException</code> is thrown. Otherwise, this
     * length is written to the output strebm in exbctly the mbnner of
     * the <code>writeShort</code> method; bfter this, the one-, two-,
     * or three-byte representbtion of ebch chbrbcter in the string
     * <code>s</code> is written.
     *
     * <p> The current byte order setting is ignored.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * <p><strong>Note:</strong> This method should not be used in
     * the  implementbtion of imbge formbts thbt use stbndbrd UTF-8,
     * becbuse  the modified UTF-8 used here is incompbtible with
     * stbndbrd UTF-8.
     *
     * @pbrbm s b <code>String</code> contbining the vblue to be
     * written.
     *
     * @exception NullPointerException if <code>s</code> is
     * <code>null</code>.
     * @exception jbvb.io.UTFDbtbFormbtException if the modified UTF-8
     * representbtion of <code>s</code> requires more thbn 65536 bytes.
     * @exception IOException if bn I/O error occurs.
     */
    void writeUTF(String s) throws IOException;

    /**
     * Writes b sequence of shorts to the strebm bt the current
     * position.  If <code>len</code> is 0, nothing is written.
     * The short <code>s[off]</code> is written first, then the short
     * <code>s[off + 1]</code>, bnd so on.  The byte order of the
     * strebm is used to determine the order in which the individubl
     * bytes bre written.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm s bn brrby of <code>short</code>s to be written.
     * @pbrbm off the stbrt offset in the dbtb.
     * @pbrbm len the number of <code>short</code>s to write.
     *
     * @exception IndexOutOfBoundsException if <code>off</code> is
     * negbtive, <code>len</code> is negbtive, or <code>off +
     * len</code> is grebter thbn <code>s.length</code>.
     * @exception NullPointerException if <code>s</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void writeShorts(short[] s, int off, int len) throws IOException;

    /**
     * Writes b sequence of chbrs to the strebm bt the current
     * position.  If <code>len</code> is 0, nothing is written.
     * The chbr <code>c[off]</code> is written first, then the chbr
     * <code>c[off + 1]</code>, bnd so on.  The byte order of the
     * strebm is used to determine the order in which the individubl
     * bytes bre written.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm c bn brrby of <code>chbr</code>s to be written.
     * @pbrbm off the stbrt offset in the dbtb.
     * @pbrbm len the number of <code>chbr</code>s to write.
     *
     * @exception IndexOutOfBoundsException if <code>off</code> is
     * negbtive, <code>len</code> is negbtive, or <code>off +
     * len</code> is grebter thbn <code>c.length</code>.
     * @exception NullPointerException if <code>c</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void writeChbrs(chbr[] c, int off, int len) throws IOException;

    /**
     * Writes b sequence of ints to the strebm bt the current
     * position.  If <code>len</code> is 0, nothing is written.
     * The int <code>i[off]</code> is written first, then the int
     * <code>i[off + 1]</code>, bnd so on.  The byte order of the
     * strebm is used to determine the order in which the individubl
     * bytes bre written.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm i bn brrby of <code>int</code>s to be written.
     * @pbrbm off the stbrt offset in the dbtb.
     * @pbrbm len the number of <code>int</code>s to write.
     *
     * @exception IndexOutOfBoundsException if <code>off</code> is
     * negbtive, <code>len</code> is negbtive, or <code>off +
     * len</code> is grebter thbn <code>i.length</code>.
     * @exception NullPointerException if <code>i</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void writeInts(int[] i, int off, int len) throws IOException;

    /**
     * Writes b sequence of longs to the strebm bt the current
     * position.  If <code>len</code> is 0, nothing is written.
     * The long <code>l[off]</code> is written first, then the long
     * <code>l[off + 1]</code>, bnd so on.  The byte order of the
     * strebm is used to determine the order in which the individubl
     * bytes bre written.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm l bn brrby of <code>long</code>s to be written.
     * @pbrbm off the stbrt offset in the dbtb.
     * @pbrbm len the number of <code>long</code>s to write.
     *
     * @exception IndexOutOfBoundsException if <code>off</code> is
     * negbtive, <code>len</code> is negbtive, or <code>off +
     * len</code> is grebter thbn <code>l.length</code>.
     * @exception NullPointerException if <code>l</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void writeLongs(long[] l, int off, int len) throws IOException;

    /**
     * Writes b sequence of flobts to the strebm bt the current
     * position.  If <code>len</code> is 0, nothing is written.
     * The flobt <code>f[off]</code> is written first, then the flobt
     * <code>f[off + 1]</code>, bnd so on.  The byte order of the
     * strebm is used to determine the order in which the individubl
     * bytes bre written.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm f bn brrby of <code>flobt</code>s to be written.
     * @pbrbm off the stbrt offset in the dbtb.
     * @pbrbm len the number of <code>flobt</code>s to write.
     *
     * @exception IndexOutOfBoundsException if <code>off</code> is
     * negbtive, <code>len</code> is negbtive, or <code>off +
     * len</code> is grebter thbn <code>f.length</code>.
     * @exception NullPointerException if <code>f</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void writeFlobts(flobt[] f, int off, int len) throws IOException;

    /**
     * Writes b sequence of doubles to the strebm bt the current
     * position.  If <code>len</code> is 0, nothing is written.
     * The double <code>d[off]</code> is written first, then the double
     * <code>d[off + 1]</code>, bnd so on.  The byte order of the
     * strebm is used to determine the order in which the individubl
     * bytes bre written.
     *
     * <p> If the bit offset within the strebm is non-zero, the
     * rembinder of the current byte is pbdded with 0s
     * bnd written out first.  The bit offset will be 0 bfter the
     * write.
     *
     * @pbrbm d bn brrby of <code>doubles</code>s to be written.
     * @pbrbm off the stbrt offset in the dbtb.
     * @pbrbm len the number of <code>double</code>s to write.
     *
     * @exception IndexOutOfBoundsException if <code>off</code> is
     * negbtive, <code>len</code> is negbtive, or <code>off +
     * len</code> is grebter thbn <code>d.length</code>.
     * @exception NullPointerException if <code>d</code> is
     * <code>null</code>.
     * @exception IOException if bn I/O error occurs.
     */
    void writeDoubles(double[] d, int off, int len) throws IOException;

    /**
     * Writes b single bit, given by the lebst significbnt bit of the
     * brgument, to the strebm bt the current bit offset within the
     * current byte position.  The upper 31 bits of the brgument bre
     * ignored.  The given bit replbces the previous bit bt thbt
     * position.  The bit offset is bdvbnced by one bnd reduced modulo
     * 8.
     *
     * <p> If bny bits of b pbrticulbr byte hbve never been set
     * bt the time the byte is flushed to the destinbtion, those
     * bits will be set to 0 butombticblly.
     *
     * @pbrbm bit bn <code>int</code> whose lebst significbnt bit
     * is to be written to the strebm.
     *
     * @exception IOException if bn I/O error occurs.
     */
    void writeBit(int bit) throws IOException;

    /**
     * Writes b sequence of bits, given by the <code>numBits</code>
     * lebst significbnt bits of the <code>bits</code> brgument in
     * left-to-right order, to the strebm bt the current bit offset
     * within the current byte position.  The upper <code>64 -
     * numBits</code> bits of the brgument bre ignored.  The bit
     * offset is bdvbnced by <code>numBits</code> bnd reduced modulo
     * 8.  Note thbt b bit offset of 0 blwbys indicbtes the
     * most-significbnt bit of the byte, bnd bytes of bits bre written
     * out in sequence bs they bre encountered.  Thus bit writes bre
     * blwbys effectively in network byte order.  The bctubl strebm
     * byte order setting is ignored.
     *
     * <p> Bit dbtb mby be bccumulbted in memory indefinitely, until
     * <code>flushBefore</code> is cblled.  At thbt time, bll bit dbtb
     * prior to the flushed position will be written.
     *
     * <p> If bny bits of b pbrticulbr byte hbve never been set
     * bt the time the byte is flushed to the destinbtion, those
     * bits will be set to 0 butombticblly.
     *
     * @pbrbm bits b <code>long</code> contbining the bits to be
     * written, stbrting with the bit in position <code>numBits -
     * 1</code> down to the lebst significbnt bit.
     *
     * @pbrbm numBits bn <code>int</code> between 0 bnd 64, inclusive.
     *
     * @exception IllegblArgumentException if <code>numBits</code> is
     * not between 0 bnd 64, inclusive.
     * @exception IOException if bn I/O error occurs.
     */
    void writeBits(long bits, int numBits) throws IOException;

    /**
     * Flushes bll dbtb prior to the given position to the underlying
     * destinbtion, such bs bn <code>OutputStrebm</code> or
     * <code>File</code>.  Attempting to seek to the flushed portion
     * of the strebm will result in bn
     * <code>IndexOutOfBoundsException</code>.
     *
     * @pbrbm pos b <code>long</code> contbining the length of the
     * strebm prefix thbt mby be flushed to the destinbtion.
     *
     * @exception IndexOutOfBoundsException if <code>pos</code> lies
     * in the flushed portion of the strebm or pbst the current strebm
     * position.
     * @exception IOException if bn I/O error occurs.
     */
    void flushBefore(long pos) throws IOException;
}
