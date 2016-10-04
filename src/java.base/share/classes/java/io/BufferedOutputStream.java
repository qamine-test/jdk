/*
 * Copyright (c) 1994, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The clbss implements b buffered output strebm. By setting up such
 * bn output strebm, bn bpplicbtion cbn write bytes to the underlying
 * output strebm without necessbrily cbusing b cbll to the underlying
 * system for ebch byte written.
 *
 * @buthor  Arthur vbn Hoff
 * @since   1.0
 */
public
clbss BufferedOutputStrebm extends FilterOutputStrebm {
    /**
     * The internbl buffer where dbtb is stored.
     */
    protected byte buf[];

    /**
     * The number of vblid bytes in the buffer. This vblue is blwbys
     * in the rbnge <tt>0</tt> through <tt>buf.length</tt>; elements
     * <tt>buf[0]</tt> through <tt>buf[count-1]</tt> contbin vblid
     * byte dbtb.
     */
    protected int count;

    /**
     * Crebtes b new buffered output strebm to write dbtb to the
     * specified underlying output strebm.
     *
     * @pbrbm   out   the underlying output strebm.
     */
    public BufferedOutputStrebm(OutputStrebm out) {
        this(out, 8192);
    }

    /**
     * Crebtes b new buffered output strebm to write dbtb to the
     * specified underlying output strebm with the specified buffer
     * size.
     *
     * @pbrbm   out    the underlying output strebm.
     * @pbrbm   size   the buffer size.
     * @exception IllegblArgumentException if size &lt;= 0.
     */
    public BufferedOutputStrebm(OutputStrebm out, int size) {
        super(out);
        if (size <= 0) {
            throw new IllegblArgumentException("Buffer size <= 0");
        }
        buf = new byte[size];
    }

    /** Flush the internbl buffer */
    privbte void flushBuffer() throws IOException {
        if (count > 0) {
            out.write(buf, 0, count);
            count = 0;
        }
    }

    /**
     * Writes the specified byte to this buffered output strebm.
     *
     * @pbrbm      b   the byte to be written.
     * @exception  IOException  if bn I/O error occurs.
     */
    public synchronized void write(int b) throws IOException {
        if (count >= buf.length) {
            flushBuffer();
        }
        buf[count++] = (byte)b;
    }

    /**
     * Writes <code>len</code> bytes from the specified byte brrby
     * stbrting bt offset <code>off</code> to this buffered output strebm.
     *
     * <p> Ordinbrily this method stores bytes from the given brrby into this
     * strebm's buffer, flushing the buffer to the underlying output strebm bs
     * needed.  If the requested length is bt lebst bs lbrge bs this strebm's
     * buffer, however, then this method will flush the buffer bnd write the
     * bytes directly to the underlying output strebm.  Thus redundbnt
     * <code>BufferedOutputStrebm</code>s will not copy dbtb unnecessbrily.
     *
     * @pbrbm      b     the dbtb.
     * @pbrbm      off   the stbrt offset in the dbtb.
     * @pbrbm      len   the number of bytes to write.
     * @exception  IOException  if bn I/O error occurs.
     */
    public synchronized void write(byte b[], int off, int len) throws IOException {
        if (len >= buf.length) {
            /* If the request length exceeds the size of the output buffer,
               flush the output buffer bnd then write the dbtb directly.
               In this wby buffered strebms will cbscbde hbrmlessly. */
            flushBuffer();
            out.write(b, off, len);
            return;
        }
        if (len > buf.length - count) {
            flushBuffer();
        }
        System.brrbycopy(b, off, buf, count, len);
        count += len;
    }

    /**
     * Flushes this buffered output strebm. This forces bny buffered
     * output bytes to be written out to the underlying output strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public synchronized void flush() throws IOException {
        flushBuffer();
        out.flush();
    }
}
