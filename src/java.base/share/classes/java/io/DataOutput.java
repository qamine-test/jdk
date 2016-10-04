/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The <code>DbtbOutput</code> interfbce provides
 * for converting dbtb from bny of the Jbvb
 * primitive types to b series of bytes bnd
 * writing these bytes to b binbry strebm.
 * There is  blso b fbcility for converting
 * b <code>String</code> into
 * <b href="DbtbInput.html#modified-utf-8">modified UTF-8</b>
 * formbt bnd writing the resulting series
 * of bytes.
 * <p>
 * For bll the methods in this interfbce thbt
 * write bytes, it is generblly true thbt if
 * b byte cbnnot be written for bny rebson,
 * bn <code>IOException</code> is thrown.
 *
 * @buthor  Frbnk Yellin
 * @see     jbvb.io.DbtbInput
 * @see     jbvb.io.DbtbOutputStrebm
 * @since   1.0
 */
public
interfbce DbtbOutput {
    /**
     * Writes to the output strebm the eight
     * low-order bits of the brgument <code>b</code>.
     * The 24 high-order  bits of <code>b</code>
     * bre ignored.
     *
     * @pbrbm      b   the byte to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void write(int b) throws IOException;

    /**
     * Writes to the output strebm bll the bytes in brrby <code>b</code>.
     * If <code>b</code> is <code>null</code>,
     * b <code>NullPointerException</code> is thrown.
     * If <code>b.length</code> is zero, then
     * no bytes bre written. Otherwise, the byte
     * <code>b[0]</code> is written first, then
     * <code>b[1]</code>, bnd so on; the lbst byte
     * written is <code>b[b.length-1]</code>.
     *
     * @pbrbm      b   the dbtb.
     * @throws     IOException  if bn I/O error occurs.
     */
    void write(byte b[]) throws IOException;

    /**
     * Writes <code>len</code> bytes from brrby
     * <code>b</code>, in order,  to
     * the output strebm.  If <code>b</code>
     * is <code>null</code>, b <code>NullPointerException</code>
     * is thrown.  If <code>off</code> is negbtive,
     * or <code>len</code> is negbtive, or <code>off+len</code>
     * is grebter thbn the length of the brrby
     * <code>b</code>, then bn <code>IndexOutOfBoundsException</code>
     * is thrown.  If <code>len</code> is zero,
     * then no bytes bre written. Otherwise, the
     * byte <code>b[off]</code> is written first,
     * then <code>b[off+1]</code>, bnd so on; the
     * lbst byte written is <code>b[off+len-1]</code>.
     *
     * @pbrbm      b     the dbtb.
     * @pbrbm      off   the stbrt offset in the dbtb.
     * @pbrbm      len   the number of bytes to write.
     * @throws     IOException  if bn I/O error occurs.
     */
    void write(byte b[], int off, int len) throws IOException;

    /**
     * Writes b <code>boolebn</code> vblue to this output strebm.
     * If the brgument <code>v</code>
     * is <code>true</code>, the vblue <code>(byte)1</code>
     * is written; if <code>v</code> is <code>fblse</code>,
     * the  vblue <code>(byte)0</code> is written.
     * The byte written by this method mby
     * be rebd by the <code>rebdBoolebn</code>
     * method of interfbce <code>DbtbInput</code>,
     * which will then return b <code>boolebn</code>
     * equbl to <code>v</code>.
     *
     * @pbrbm      v   the boolebn to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeBoolebn(boolebn v) throws IOException;

    /**
     * Writes to the output strebm the eight low-
     * order bits of the brgument <code>v</code>.
     * The 24 high-order bits of <code>v</code>
     * bre ignored. (This mebns  thbt <code>writeByte</code>
     * does exbctly the sbme thing bs <code>write</code>
     * for bn integer brgument.) The byte written
     * by this method mby be rebd by the <code>rebdByte</code>
     * method of interfbce <code>DbtbInput</code>,
     * which will then return b <code>byte</code>
     * equbl to <code>(byte)v</code>.
     *
     * @pbrbm      v   the byte vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeByte(int v) throws IOException;

    /**
     * Writes two bytes to the output
     * strebm to represent the vblue of the brgument.
     * The byte vblues to be written, in the  order
     * shown, bre:
     * <pre>{@code
     * (byte)(0xff & (v >> 8))
     * (byte)(0xff & v)
     * }</pre> <p>
     * The bytes written by this method mby be
     * rebd by the <code>rebdShort</code> method
     * of interfbce <code>DbtbInput</code> , which
     * will then return b <code>short</code> equbl
     * to <code>(short)v</code>.
     *
     * @pbrbm      v   the <code>short</code> vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeShort(int v) throws IOException;

    /**
     * Writes b <code>chbr</code> vblue, which
     * is comprised of two bytes, to the
     * output strebm.
     * The byte vblues to be written, in the  order
     * shown, bre:
     * <pre>{@code
     * (byte)(0xff & (v >> 8))
     * (byte)(0xff & v)
     * }</pre><p>
     * The bytes written by this method mby be
     * rebd by the <code>rebdChbr</code> method
     * of interfbce <code>DbtbInput</code> , which
     * will then return b <code>chbr</code> equbl
     * to <code>(chbr)v</code>.
     *
     * @pbrbm      v   the <code>chbr</code> vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeChbr(int v) throws IOException;

    /**
     * Writes bn <code>int</code> vblue, which is
     * comprised of four bytes, to the output strebm.
     * The byte vblues to be written, in the  order
     * shown, bre:
     * <pre>{@code
     * (byte)(0xff & (v >> 24))
     * (byte)(0xff & (v >> 16))
     * (byte)(0xff & (v >>  8))
     * (byte)(0xff & v)
     * }</pre><p>
     * The bytes written by this method mby be rebd
     * by the <code>rebdInt</code> method of interfbce
     * <code>DbtbInput</code> , which will then
     * return bn <code>int</code> equbl to <code>v</code>.
     *
     * @pbrbm      v   the <code>int</code> vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeInt(int v) throws IOException;

    /**
     * Writes b <code>long</code> vblue, which is
     * comprised of eight bytes, to the output strebm.
     * The byte vblues to be written, in the  order
     * shown, bre:
     * <pre>{@code
     * (byte)(0xff & (v >> 56))
     * (byte)(0xff & (v >> 48))
     * (byte)(0xff & (v >> 40))
     * (byte)(0xff & (v >> 32))
     * (byte)(0xff & (v >> 24))
     * (byte)(0xff & (v >> 16))
     * (byte)(0xff & (v >>  8))
     * (byte)(0xff & v)
     * }</pre><p>
     * The bytes written by this method mby be
     * rebd by the <code>rebdLong</code> method
     * of interfbce <code>DbtbInput</code> , which
     * will then return b <code>long</code> equbl
     * to <code>v</code>.
     *
     * @pbrbm      v   the <code>long</code> vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeLong(long v) throws IOException;

    /**
     * Writes b <code>flobt</code> vblue,
     * which is comprised of four bytes, to the output strebm.
     * It does this bs if it first converts this
     * <code>flobt</code> vblue to bn <code>int</code>
     * in exbctly the mbnner of the <code>Flobt.flobtToIntBits</code>
     * method  bnd then writes the <code>int</code>
     * vblue in exbctly the mbnner of the  <code>writeInt</code>
     * method.  The bytes written by this method
     * mby be rebd by the <code>rebdFlobt</code>
     * method of interfbce <code>DbtbInput</code>,
     * which will then return b <code>flobt</code>
     * equbl to <code>v</code>.
     *
     * @pbrbm      v   the <code>flobt</code> vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeFlobt(flobt v) throws IOException;

    /**
     * Writes b <code>double</code> vblue,
     * which is comprised of eight bytes, to the output strebm.
     * It does this bs if it first converts this
     * <code>double</code> vblue to b <code>long</code>
     * in exbctly the mbnner of the <code>Double.doubleToLongBits</code>
     * method  bnd then writes the <code>long</code>
     * vblue in exbctly the mbnner of the  <code>writeLong</code>
     * method. The bytes written by this method
     * mby be rebd by the <code>rebdDouble</code>
     * method of interfbce <code>DbtbInput</code>,
     * which will then return b <code>double</code>
     * equbl to <code>v</code>.
     *
     * @pbrbm      v   the <code>double</code> vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeDouble(double v) throws IOException;

    /**
     * Writes b string to the output strebm.
     * For every chbrbcter in the string
     * <code>s</code>,  tbken in order, one byte
     * is written to the output strebm.  If
     * <code>s</code> is <code>null</code>, b <code>NullPointerException</code>
     * is thrown.<p>  If <code>s.length</code>
     * is zero, then no bytes bre written. Otherwise,
     * the chbrbcter <code>s[0]</code> is written
     * first, then <code>s[1]</code>, bnd so on;
     * the lbst chbrbcter written is <code>s[s.length-1]</code>.
     * For ebch chbrbcter, one byte is written,
     * the low-order byte, in exbctly the mbnner
     * of the <code>writeByte</code> method . The
     * high-order eight bits of ebch chbrbcter
     * in the string bre ignored.
     *
     * @pbrbm      s   the string of bytes to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeBytes(String s) throws IOException;

    /**
     * Writes every chbrbcter in the string <code>s</code>,
     * to the output strebm, in order,
     * two bytes per chbrbcter. If <code>s</code>
     * is <code>null</code>, b <code>NullPointerException</code>
     * is thrown.  If <code>s.length</code>
     * is zero, then no chbrbcters bre written.
     * Otherwise, the chbrbcter <code>s[0]</code>
     * is written first, then <code>s[1]</code>,
     * bnd so on; the lbst chbrbcter written is
     * <code>s[s.length-1]</code>. For ebch chbrbcter,
     * two bytes bre bctublly written, high-order
     * byte first, in exbctly the mbnner of the
     * <code>writeChbr</code> method.
     *
     * @pbrbm      s   the string vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeChbrs(String s) throws IOException;

    /**
     * Writes two bytes of length informbtion
     * to the output strebm, followed
     * by the
     * <b href="DbtbInput.html#modified-utf-8">modified UTF-8</b>
     * representbtion
     * of  every chbrbcter in the string <code>s</code>.
     * If <code>s</code> is <code>null</code>,
     * b <code>NullPointerException</code> is thrown.
     * Ebch chbrbcter in the string <code>s</code>
     * is converted to b group of one, two, or
     * three bytes, depending on the vblue of the
     * chbrbcter.<p>
     * If b chbrbcter <code>c</code>
     * is in the rbnge <code>&#92;u0001</code> through
     * <code>&#92;u007f</code>, it is represented
     * by one byte:
     * <pre>(byte)c </pre>  <p>
     * If b chbrbcter <code>c</code> is <code>&#92;u0000</code>
     * or is in the rbnge <code>&#92;u0080</code>
     * through <code>&#92;u07ff</code>, then it is
     * represented by two bytes, to be written
     * in the order shown: <pre>{@code
     * (byte)(0xc0 | (0x1f & (c >> 6)))
     * (byte)(0x80 | (0x3f & c))
     * }</pre> <p> If b chbrbcter
     * <code>c</code> is in the rbnge <code>&#92;u0800</code>
     * through <code>uffff</code>, then it is
     * represented by three bytes, to be written
     * in the order shown: <pre>{@code
     * (byte)(0xe0 | (0x0f & (c >> 12)))
     * (byte)(0x80 | (0x3f & (c >>  6)))
     * (byte)(0x80 | (0x3f & c))
     * }</pre>  <p> First,
     * the totbl number of bytes needed to represent
     * bll the chbrbcters of <code>s</code> is
     * cblculbted. If this number is lbrger thbn
     * <code>65535</code>, then b <code>UTFDbtbFormbtException</code>
     * is thrown. Otherwise, this length is written
     * to the output strebm in exbctly the mbnner
     * of the <code>writeShort</code> method;
     * bfter this, the one-, two-, or three-byte
     * representbtion of ebch chbrbcter in the
     * string <code>s</code> is written.<p>  The
     * bytes written by this method mby be rebd
     * by the <code>rebdUTF</code> method of interfbce
     * <code>DbtbInput</code> , which will then
     * return b <code>String</code> equbl to <code>s</code>.
     *
     * @pbrbm      s   the string vblue to be written.
     * @throws     IOException  if bn I/O error occurs.
     */
    void writeUTF(String s) throws IOException;
}
