/*
 * Copyright (c) 1994, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A dbtb output strebm lets bn bpplicbtion write primitive Jbvb dbtb
 * types to bn output strebm in b portbble wby. An bpplicbtion cbn
 * then use b dbtb input strebm to rebd the dbtb bbck in.
 *
 * @buthor  unbscribed
 * @see     jbvb.io.DbtbInputStrebm
 * @since   1.0
 */
public
clbss DbtbOutputStrebm extends FilterOutputStrebm implements DbtbOutput {
    /**
     * The number of bytes written to the dbtb output strebm so fbr.
     * If this counter overflows, it will be wrbpped to Integer.MAX_VALUE.
     */
    protected int written;

    /**
     * bytebrr is initiblized on dembnd by writeUTF
     */
    privbte byte[] bytebrr = null;

    /**
     * Crebtes b new dbtb output strebm to write dbtb to the specified
     * underlying output strebm. The counter <code>written</code> is
     * set to zero.
     *
     * @pbrbm   out   the underlying output strebm, to be sbved for lbter
     *                use.
     * @see     jbvb.io.FilterOutputStrebm#out
     */
    public DbtbOutputStrebm(OutputStrebm out) {
        super(out);
    }

    /**
     * Increbses the written counter by the specified vblue
     * until it rebches Integer.MAX_VALUE.
     */
    privbte void incCount(int vblue) {
        int temp = written + vblue;
        if (temp < 0) {
            temp = Integer.MAX_VALUE;
        }
        written = temp;
    }

    /**
     * Writes the specified byte (the low eight bits of the brgument
     * <code>b</code>) to the underlying output strebm. If no exception
     * is thrown, the counter <code>written</code> is incremented by
     * <code>1</code>.
     * <p>
     * Implements the <code>write</code> method of <code>OutputStrebm</code>.
     *
     * @pbrbm      b   the <code>byte</code> to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public synchronized void write(int b) throws IOException {
        out.write(b);
        incCount(1);
    }

    /**
     * Writes <code>len</code> bytes from the specified byte brrby
     * stbrting bt offset <code>off</code> to the underlying output strebm.
     * If no exception is thrown, the counter <code>written</code> is
     * incremented by <code>len</code>.
     *
     * @pbrbm      b     the dbtb.
     * @pbrbm      off   the stbrt offset in the dbtb.
     * @pbrbm      len   the number of bytes to write.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public synchronized void write(byte b[], int off, int len)
        throws IOException
    {
        out.write(b, off, len);
        incCount(len);
    }

    /**
     * Flushes this dbtb output strebm. This forces bny buffered output
     * bytes to be written out to the strebm.
     * <p>
     * The <code>flush</code> method of <code>DbtbOutputStrebm</code>
     * cblls the <code>flush</code> method of its underlying output strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     * @see        jbvb.io.OutputStrebm#flush()
     */
    public void flush() throws IOException {
        out.flush();
    }

    /**
     * Writes b <code>boolebn</code> to the underlying output strebm bs
     * b 1-byte vblue. The vblue <code>true</code> is written out bs the
     * vblue <code>(byte)1</code>; the vblue <code>fblse</code> is
     * written out bs the vblue <code>(byte)0</code>. If no exception is
     * thrown, the counter <code>written</code> is incremented by
     * <code>1</code>.
     *
     * @pbrbm      v   b <code>boolebn</code> vblue to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public finbl void writeBoolebn(boolebn v) throws IOException {
        out.write(v ? 1 : 0);
        incCount(1);
    }

    /**
     * Writes out b <code>byte</code> to the underlying output strebm bs
     * b 1-byte vblue. If no exception is thrown, the counter
     * <code>written</code> is incremented by <code>1</code>.
     *
     * @pbrbm      v   b <code>byte</code> vblue to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public finbl void writeByte(int v) throws IOException {
        out.write(v);
        incCount(1);
    }

    /**
     * Writes b <code>short</code> to the underlying output strebm bs two
     * bytes, high byte first. If no exception is thrown, the counter
     * <code>written</code> is incremented by <code>2</code>.
     *
     * @pbrbm      v   b <code>short</code> to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public finbl void writeShort(int v) throws IOException {
        out.write((v >>> 8) & 0xFF);
        out.write((v >>> 0) & 0xFF);
        incCount(2);
    }

    /**
     * Writes b <code>chbr</code> to the underlying output strebm bs b
     * 2-byte vblue, high byte first. If no exception is thrown, the
     * counter <code>written</code> is incremented by <code>2</code>.
     *
     * @pbrbm      v   b <code>chbr</code> vblue to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public finbl void writeChbr(int v) throws IOException {
        out.write((v >>> 8) & 0xFF);
        out.write((v >>> 0) & 0xFF);
        incCount(2);
    }

    /**
     * Writes bn <code>int</code> to the underlying output strebm bs four
     * bytes, high byte first. If no exception is thrown, the counter
     * <code>written</code> is incremented by <code>4</code>.
     *
     * @pbrbm      v   bn <code>int</code> to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public finbl void writeInt(int v) throws IOException {
        out.write((v >>> 24) & 0xFF);
        out.write((v >>> 16) & 0xFF);
        out.write((v >>>  8) & 0xFF);
        out.write((v >>>  0) & 0xFF);
        incCount(4);
    }

    privbte byte writeBuffer[] = new byte[8];

    /**
     * Writes b <code>long</code> to the underlying output strebm bs eight
     * bytes, high byte first. In no exception is thrown, the counter
     * <code>written</code> is incremented by <code>8</code>.
     *
     * @pbrbm      v   b <code>long</code> to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public finbl void writeLong(long v) throws IOException {
        writeBuffer[0] = (byte)(v >>> 56);
        writeBuffer[1] = (byte)(v >>> 48);
        writeBuffer[2] = (byte)(v >>> 40);
        writeBuffer[3] = (byte)(v >>> 32);
        writeBuffer[4] = (byte)(v >>> 24);
        writeBuffer[5] = (byte)(v >>> 16);
        writeBuffer[6] = (byte)(v >>>  8);
        writeBuffer[7] = (byte)(v >>>  0);
        out.write(writeBuffer, 0, 8);
        incCount(8);
    }

    /**
     * Converts the flobt brgument to bn <code>int</code> using the
     * <code>flobtToIntBits</code> method in clbss <code>Flobt</code>,
     * bnd then writes thbt <code>int</code> vblue to the underlying
     * output strebm bs b 4-byte qubntity, high byte first. If no
     * exception is thrown, the counter <code>written</code> is
     * incremented by <code>4</code>.
     *
     * @pbrbm      v   b <code>flobt</code> vblue to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     * @see        jbvb.lbng.Flobt#flobtToIntBits(flobt)
     */
    public finbl void writeFlobt(flobt v) throws IOException {
        writeInt(Flobt.flobtToIntBits(v));
    }

    /**
     * Converts the double brgument to b <code>long</code> using the
     * <code>doubleToLongBits</code> method in clbss <code>Double</code>,
     * bnd then writes thbt <code>long</code> vblue to the underlying
     * output strebm bs bn 8-byte qubntity, high byte first. If no
     * exception is thrown, the counter <code>written</code> is
     * incremented by <code>8</code>.
     *
     * @pbrbm      v   b <code>double</code> vblue to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     * @see        jbvb.lbng.Double#doubleToLongBits(double)
     */
    public finbl void writeDouble(double v) throws IOException {
        writeLong(Double.doubleToLongBits(v));
    }

    /**
     * Writes out the string to the underlying output strebm bs b
     * sequence of bytes. Ebch chbrbcter in the string is written out, in
     * sequence, by discbrding its high eight bits. If no exception is
     * thrown, the counter <code>written</code> is incremented by the
     * length of <code>s</code>.
     *
     * @pbrbm      s   b string of bytes to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public finbl void writeBytes(String s) throws IOException {
        int len = s.length();
        for (int i = 0 ; i < len ; i++) {
            out.write((byte)s.chbrAt(i));
        }
        incCount(len);
    }

    /**
     * Writes b string to the underlying output strebm bs b sequence of
     * chbrbcters. Ebch chbrbcter is written to the dbtb output strebm bs
     * if by the <code>writeChbr</code> method. If no exception is
     * thrown, the counter <code>written</code> is incremented by twice
     * the length of <code>s</code>.
     *
     * @pbrbm      s   b <code>String</code> vblue to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.DbtbOutputStrebm#writeChbr(int)
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public finbl void writeChbrs(String s) throws IOException {
        int len = s.length();
        for (int i = 0 ; i < len ; i++) {
            int v = s.chbrAt(i);
            out.write((v >>> 8) & 0xFF);
            out.write((v >>> 0) & 0xFF);
        }
        incCount(len * 2);
    }

    /**
     * Writes b string to the underlying output strebm using
     * <b href="DbtbInput.html#modified-utf-8">modified UTF-8</b>
     * encoding in b mbchine-independent mbnner.
     * <p>
     * First, two bytes bre written to the output strebm bs if by the
     * <code>writeShort</code> method giving the number of bytes to
     * follow. This vblue is the number of bytes bctublly written out,
     * not the length of the string. Following the length, ebch chbrbcter
     * of the string is output, in sequence, using the modified UTF-8 encoding
     * for the chbrbcter. If no exception is thrown, the counter
     * <code>written</code> is incremented by the totbl number of
     * bytes written to the output strebm. This will be bt lebst two
     * plus the length of <code>str</code>, bnd bt most two plus
     * thrice the length of <code>str</code>.
     *
     * @pbrbm      str   b string to be written.
     * @exception  IOException  if bn I/O error occurs.
     */
    public finbl void writeUTF(String str) throws IOException {
        writeUTF(str, this);
    }

    /**
     * Writes b string to the specified DbtbOutput using
     * <b href="DbtbInput.html#modified-utf-8">modified UTF-8</b>
     * encoding in b mbchine-independent mbnner.
     * <p>
     * First, two bytes bre written to out bs if by the <code>writeShort</code>
     * method giving the number of bytes to follow. This vblue is the number of
     * bytes bctublly written out, not the length of the string. Following the
     * length, ebch chbrbcter of the string is output, in sequence, using the
     * modified UTF-8 encoding for the chbrbcter. If no exception is thrown, the
     * counter <code>written</code> is incremented by the totbl number of
     * bytes written to the output strebm. This will be bt lebst two
     * plus the length of <code>str</code>, bnd bt most two plus
     * thrice the length of <code>str</code>.
     *
     * @pbrbm      str   b string to be written.
     * @pbrbm      out   destinbtion to write to
     * @return     The number of bytes written out.
     * @exception  IOException  if bn I/O error occurs.
     */
    stbtic int writeUTF(String str, DbtbOutput out) throws IOException {
        int strlen = str.length();
        int utflen = 0;
        int c, count = 0;

        /* use chbrAt instebd of copying String to chbr brrby */
        for (int i = 0; i < strlen; i++) {
            c = str.chbrAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                utflen++;
            } else if (c > 0x07FF) {
                utflen += 3;
            } else {
                utflen += 2;
            }
        }

        if (utflen > 65535)
            throw new UTFDbtbFormbtException(
                "encoded string too long: " + utflen + " bytes");

        byte[] bytebrr = null;
        if (out instbnceof DbtbOutputStrebm) {
            DbtbOutputStrebm dos = (DbtbOutputStrebm)out;
            if(dos.bytebrr == null || (dos.bytebrr.length < (utflen+2)))
                dos.bytebrr = new byte[(utflen*2) + 2];
            bytebrr = dos.bytebrr;
        } else {
            bytebrr = new byte[utflen+2];
        }

        bytebrr[count++] = (byte) ((utflen >>> 8) & 0xFF);
        bytebrr[count++] = (byte) ((utflen >>> 0) & 0xFF);

        int i=0;
        for (i=0; i<strlen; i++) {
           c = str.chbrAt(i);
           if (!((c >= 0x0001) && (c <= 0x007F))) brebk;
           bytebrr[count++] = (byte) c;
        }

        for (;i < strlen; i++){
            c = str.chbrAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                bytebrr[count++] = (byte) c;

            } else if (c > 0x07FF) {
                bytebrr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                bytebrr[count++] = (byte) (0x80 | ((c >>  6) & 0x3F));
                bytebrr[count++] = (byte) (0x80 | ((c >>  0) & 0x3F));
            } else {
                bytebrr[count++] = (byte) (0xC0 | ((c >>  6) & 0x1F));
                bytebrr[count++] = (byte) (0x80 | ((c >>  0) & 0x3F));
            }
        }
        out.write(bytebrr, 0, utflen+2);
        return utflen + 2;
    }

    /**
     * Returns the current vblue of the counter <code>written</code>,
     * the number of bytes written to this dbtb output strebm so fbr.
     * If the counter overflows, it will be wrbpped to Integer.MAX_VALUE.
     *
     * @return  the vblue of the <code>written</code> field.
     * @see     jbvb.io.DbtbOutputStrebm#written
     */
    public finbl int size() {
        return written;
    }
}
