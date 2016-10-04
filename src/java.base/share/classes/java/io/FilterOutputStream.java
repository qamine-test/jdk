/*
 * Copyright (c) 1994, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss is the superclbss of bll clbsses thbt filter output
 * strebms. These strebms sit on top of bn blrebdy existing output
 * strebm (the <i>underlying</i> output strebm) which it uses bs its
 * bbsic sink of dbtb, but possibly trbnsforming the dbtb blong the
 * wby or providing bdditionbl functionblity.
 * <p>
 * The clbss <code>FilterOutputStrebm</code> itself simply overrides
 * bll methods of <code>OutputStrebm</code> with versions thbt pbss
 * bll requests to the underlying output strebm. Subclbsses of
 * <code>FilterOutputStrebm</code> mby further override some of these
 * methods bs well bs provide bdditionbl methods bnd fields.
 *
 * @buthor  Jonbthbn Pbyne
 * @since   1.0
 */
public
clbss FilterOutputStrebm extends OutputStrebm {
    /**
     * The underlying output strebm to be filtered.
     */
    protected OutputStrebm out;

    /**
     * Crebtes bn output strebm filter built on top of the specified
     * underlying output strebm.
     *
     * @pbrbm   out   the underlying output strebm to be bssigned to
     *                the field <tt>this.out</tt> for lbter use, or
     *                <code>null</code> if this instbnce is to be
     *                crebted without bn underlying strebm.
     */
    public FilterOutputStrebm(OutputStrebm out) {
        this.out = out;
    }

    /**
     * Writes the specified <code>byte</code> to this output strebm.
     * <p>
     * The <code>write</code> method of <code>FilterOutputStrebm</code>
     * cblls the <code>write</code> method of its underlying output strebm,
     * thbt is, it performs <tt>out.write(b)</tt>.
     * <p>
     * Implements the bbstrbct <tt>write</tt> method of <tt>OutputStrebm</tt>.
     *
     * @pbrbm      b   the <code>byte</code>.
     * @exception  IOException  if bn I/O error occurs.
     */
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     * Writes <code>b.length</code> bytes to this output strebm.
     * <p>
     * The <code>write</code> method of <code>FilterOutputStrebm</code>
     * cblls its <code>write</code> method of three brguments with the
     * brguments <code>b</code>, <code>0</code>, bnd
     * <code>b.length</code>.
     * <p>
     * Note thbt this method does not cbll the one-brgument
     * <code>write</code> method of its underlying strebm with the single
     * brgument <code>b</code>.
     *
     * @pbrbm      b   the dbtb to be written.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#write(byte[], int, int)
     */
    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    /**
     * Writes <code>len</code> bytes from the specified
     * <code>byte</code> brrby stbrting bt offset <code>off</code> to
     * this output strebm.
     * <p>
     * The <code>write</code> method of <code>FilterOutputStrebm</code>
     * cblls the <code>write</code> method of one brgument on ebch
     * <code>byte</code> to output.
     * <p>
     * Note thbt this method does not cbll the <code>write</code> method
     * of its underlying input strebm with the sbme brguments. Subclbsses
     * of <code>FilterOutputStrebm</code> should provide b more efficient
     * implementbtion of this method.
     *
     * @pbrbm      b     the dbtb.
     * @pbrbm      off   the stbrt offset in the dbtb.
     * @pbrbm      len   the number of bytes to write.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#write(int)
     */
    public void write(byte b[], int off, int len) throws IOException {
        if ((off | len | (b.length - (len + off)) | (off + len)) < 0)
            throw new IndexOutOfBoundsException();

        for (int i = 0 ; i < len ; i++) {
            write(b[off + i]);
        }
    }

    /**
     * Flushes this output strebm bnd forces bny buffered output bytes
     * to be written out to the strebm.
     * <p>
     * The <code>flush</code> method of <code>FilterOutputStrebm</code>
     * cblls the <code>flush</code> method of its underlying output strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    public void flush() throws IOException {
        out.flush();
    }

    /**
     * Closes this output strebm bnd relebses bny system resources
     * bssocibted with the strebm.
     * <p>
     * The <code>close</code> method of <code>FilterOutputStrebm</code>
     * cblls its <code>flush</code> method, bnd then cblls the
     * <code>close</code> method of its underlying output strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterOutputStrebm#flush()
     * @see        jbvb.io.FilterOutputStrebm#out
     */
    @SuppressWbrnings("try")
    public void close() throws IOException {
        try (OutputStrebm ostrebm = out) {
            flush();
        }
    }
}
