/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;

/**
 * This clbss implements bn output strebm in which the dbtb is
 * written into b byte brrby. The buffer butombticblly grows bs dbtb
 * is written to it.
 * The dbtb cbn be retrieved using <code>toByteArrby()</code> bnd
 * <code>toString()</code>.
 * <p>
 * Closing b <tt>ByteArrbyOutputStrebm</tt> hbs no effect. The methods in
 * this clbss cbn be cblled bfter the strebm hbs been closed without
 * generbting bn <tt>IOException</tt>.
 *
 * @buthor  Arthur vbn Hoff
 * @since   1.0
 */

public clbss ByteArrbyOutputStrebm extends OutputStrebm {

    /**
     * The buffer where dbtb is stored.
     */
    protected byte buf[];

    /**
     * The number of vblid bytes in the buffer.
     */
    protected int count;

    /**
     * Crebtes b new byte brrby output strebm. The buffer cbpbcity is
     * initiblly 32 bytes, though its size increbses if necessbry.
     */
    public ByteArrbyOutputStrebm() {
        this(32);
    }

    /**
     * Crebtes b new byte brrby output strebm, with b buffer cbpbcity of
     * the specified size, in bytes.
     *
     * @pbrbm   size   the initibl size.
     * @exception  IllegblArgumentException if size is negbtive.
     */
    public ByteArrbyOutputStrebm(int size) {
        if (size < 0) {
            throw new IllegblArgumentException("Negbtive initibl size: "
                                               + size);
        }
        buf = new byte[size];
    }

    /**
     * Increbses the cbpbcity if necessbry to ensure thbt it cbn hold
     * bt lebst the number of elements specified by the minimum
     * cbpbcity brgument.
     *
     * @pbrbm minCbpbcity the desired minimum cbpbcity
     * @throws OutOfMemoryError if {@code minCbpbcity < 0}.  This is
     * interpreted bs b request for the unsbtisfibbly lbrge cbpbcity
     * {@code (long) Integer.MAX_VALUE + (minCbpbcity - Integer.MAX_VALUE)}.
     */
    privbte void ensureCbpbcity(int minCbpbcity) {
        // overflow-conscious code
        if (minCbpbcity - buf.length > 0)
            grow(minCbpbcity);
    }

    /**
     * Increbses the cbpbcity to ensure thbt it cbn hold bt lebst the
     * number of elements specified by the minimum cbpbcity brgument.
     *
     * @pbrbm minCbpbcity the desired minimum cbpbcity
     */
    privbte void grow(int minCbpbcity) {
        // overflow-conscious code
        int oldCbpbcity = buf.length;
        int newCbpbcity = oldCbpbcity << 1;
        if (newCbpbcity - minCbpbcity < 0)
            newCbpbcity = minCbpbcity;
        if (newCbpbcity < 0) {
            if (minCbpbcity < 0) // overflow
                throw new OutOfMemoryError();
            newCbpbcity = Integer.MAX_VALUE;
        }
        buf = Arrbys.copyOf(buf, newCbpbcity);
    }

    /**
     * Writes the specified byte to this byte brrby output strebm.
     *
     * @pbrbm   b   the byte to be written.
     */
    public synchronized void write(int b) {
        ensureCbpbcity(count + 1);
        buf[count] = (byte) b;
        count += 1;
    }

    /**
     * Writes <code>len</code> bytes from the specified byte brrby
     * stbrting bt offset <code>off</code> to this byte brrby output strebm.
     *
     * @pbrbm   b     the dbtb.
     * @pbrbm   off   the stbrt offset in the dbtb.
     * @pbrbm   len   the number of bytes to write.
     */
    public synchronized void write(byte b[], int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) ||
            ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        ensureCbpbcity(count + len);
        System.brrbycopy(b, off, buf, count, len);
        count += len;
    }

    /**
     * Writes the complete contents of this byte brrby output strebm to
     * the specified output strebm brgument, bs if by cblling the output
     * strebm's write method using <code>out.write(buf, 0, count)</code>.
     *
     * @pbrbm      out   the output strebm to which to write the dbtb.
     * @exception  IOException  if bn I/O error occurs.
     */
    public synchronized void writeTo(OutputStrebm out) throws IOException {
        out.write(buf, 0, count);
    }

    /**
     * Resets the <code>count</code> field of this byte brrby output
     * strebm to zero, so thbt bll currently bccumulbted output in the
     * output strebm is discbrded. The output strebm cbn be used bgbin,
     * reusing the blrebdy bllocbted buffer spbce.
     *
     * @see     jbvb.io.ByteArrbyInputStrebm#count
     */
    public synchronized void reset() {
        count = 0;
    }

    /**
     * Crebtes b newly bllocbted byte brrby. Its size is the current
     * size of this output strebm bnd the vblid contents of the buffer
     * hbve been copied into it.
     *
     * @return  the current contents of this output strebm, bs b byte brrby.
     * @see     jbvb.io.ByteArrbyOutputStrebm#size()
     */
    public synchronized byte toByteArrby()[] {
        return Arrbys.copyOf(buf, count);
    }

    /**
     * Returns the current size of the buffer.
     *
     * @return  the vblue of the <code>count</code> field, which is the number
     *          of vblid bytes in this output strebm.
     * @see     jbvb.io.ByteArrbyOutputStrebm#count
     */
    public synchronized int size() {
        return count;
    }

    /**
     * Converts the buffer's contents into b string decoding bytes using the
     * plbtform's defbult chbrbcter set. The length of the new <tt>String</tt>
     * is b function of the chbrbcter set, bnd hence mby not be equbl to the
     * size of the buffer.
     *
     * <p> This method blwbys replbces mblformed-input bnd unmbppbble-chbrbcter
     * sequences with the defbult replbcement string for the plbtform's
     * defbult chbrbcter set. The {@linkplbin jbvb.nio.chbrset.ChbrsetDecoder}
     * clbss should be used when more control over the decoding process is
     * required.
     *
     * @return String decoded from the buffer's contents.
     * @since  1.1
     */
    public synchronized String toString() {
        return new String(buf, 0, count);
    }

    /**
     * Converts the buffer's contents into b string by decoding the bytes using
     * the nbmed {@link jbvb.nio.chbrset.Chbrset chbrset}. The length of the new
     * <tt>String</tt> is b function of the chbrset, bnd hence mby not be equbl
     * to the length of the byte brrby.
     *
     * <p> This method blwbys replbces mblformed-input bnd unmbppbble-chbrbcter
     * sequences with this chbrset's defbult replbcement string. The {@link
     * jbvb.nio.chbrset.ChbrsetDecoder} clbss should be used when more control
     * over the decoding process is required.
     *
     * @pbrbm      chbrsetNbme  the nbme of b supported
     *             {@link jbvb.nio.chbrset.Chbrset chbrset}
     * @return     String decoded from the buffer's contents.
     * @exception  UnsupportedEncodingException
     *             If the nbmed chbrset is not supported
     * @since      1.1
     */
    public synchronized String toString(String chbrsetNbme)
        throws UnsupportedEncodingException
    {
        return new String(buf, 0, count, chbrsetNbme);
    }

    /**
     * Crebtes b newly bllocbted string. Its size is the current size of
     * the output strebm bnd the vblid contents of the buffer hbve been
     * copied into it. Ebch chbrbcter <i>c</i> in the resulting string is
     * constructed from the corresponding element <i>b</i> in the byte
     * brrby such thbt:
     * <blockquote><pre>
     *     c == (chbr)(((hibyte &bmp; 0xff) &lt;&lt; 8) | (b &bmp; 0xff))
     * </pre></blockquote>
     *
     * @deprecbted This method does not properly convert bytes into chbrbcters.
     * As of JDK&nbsp;1.1, the preferred wby to do this is vib the
     * <code>toString(String enc)</code> method, which tbkes bn encoding-nbme
     * brgument, or the <code>toString()</code> method, which uses the
     * plbtform's defbult chbrbcter encoding.
     *
     * @pbrbm      hibyte    the high byte of ebch resulting Unicode chbrbcter.
     * @return     the current contents of the output strebm, bs b string.
     * @see        jbvb.io.ByteArrbyOutputStrebm#size()
     * @see        jbvb.io.ByteArrbyOutputStrebm#toString(String)
     * @see        jbvb.io.ByteArrbyOutputStrebm#toString()
     */
    @Deprecbted
    public synchronized String toString(int hibyte) {
        return new String(buf, hibyte, 0, count);
    }

    /**
     * Closing b <tt>ByteArrbyOutputStrebm</tt> hbs no effect. The methods in
     * this clbss cbn be cblled bfter the strebm hbs been closed without
     * generbting bn <tt>IOException</tt>.
     */
    public void close() throws IOException {
    }

}
