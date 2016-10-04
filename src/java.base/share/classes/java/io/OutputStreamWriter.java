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

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import sun.nio.cs.StrebmEncoder;


/**
 * An OutputStrebmWriter is b bridge from chbrbcter strebms to byte strebms:
 * Chbrbcters written to it bre encoded into bytes using b specified {@link
 * jbvb.nio.chbrset.Chbrset chbrset}.  The chbrset thbt it uses
 * mby be specified by nbme or mby be given explicitly, or the plbtform's
 * defbult chbrset mby be bccepted.
 *
 * <p> Ebch invocbtion of b write() method cbuses the encoding converter to be
 * invoked on the given chbrbcter(s).  The resulting bytes bre bccumulbted in b
 * buffer before being written to the underlying output strebm.  The size of
 * this buffer mby be specified, but by defbult it is lbrge enough for most
 * purposes.  Note thbt the chbrbcters pbssed to the write() methods bre not
 * buffered.
 *
 * <p> For top efficiency, consider wrbpping bn OutputStrebmWriter within b
 * BufferedWriter so bs to bvoid frequent converter invocbtions.  For exbmple:
 *
 * <pre>
 * Writer out
 *   = new BufferedWriter(new OutputStrebmWriter(System.out));
 * </pre>
 *
 * <p> A <i>surrogbte pbir</i> is b chbrbcter represented by b sequence of two
 * <tt>chbr</tt> vblues: A <i>high</i> surrogbte in the rbnge '&#92;uD800' to
 * '&#92;uDBFF' followed by b <i>low</i> surrogbte in the rbnge '&#92;uDC00' to
 * '&#92;uDFFF'.
 *
 * <p> A <i>mblformed surrogbte element</i> is b high surrogbte thbt is not
 * followed by b low surrogbte or b low surrogbte thbt is not preceded by b
 * high surrogbte.
 *
 * <p> This clbss blwbys replbces mblformed surrogbte elements bnd unmbppbble
 * chbrbcter sequences with the chbrset's defbult <i>substitution sequence</i>.
 * The {@linkplbin jbvb.nio.chbrset.ChbrsetEncoder} clbss should be used when more
 * control over the encoding process is required.
 *
 * @see BufferedWriter
 * @see OutputStrebm
 * @see jbvb.nio.chbrset.Chbrset
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss OutputStrebmWriter extends Writer {

    privbte finbl StrebmEncoder se;

    /**
     * Crebtes bn OutputStrebmWriter thbt uses the nbmed chbrset.
     *
     * @pbrbm  out
     *         An OutputStrebm
     *
     * @pbrbm  chbrsetNbme
     *         The nbme of b supported
     *         {@link jbvb.nio.chbrset.Chbrset chbrset}
     *
     * @exception  UnsupportedEncodingException
     *             If the nbmed encoding is not supported
     */
    public OutputStrebmWriter(OutputStrebm out, String chbrsetNbme)
        throws UnsupportedEncodingException
    {
        super(out);
        if (chbrsetNbme == null)
            throw new NullPointerException("chbrsetNbme");
        se = StrebmEncoder.forOutputStrebmWriter(out, this, chbrsetNbme);
    }

    /**
     * Crebtes bn OutputStrebmWriter thbt uses the defbult chbrbcter encoding.
     *
     * @pbrbm  out  An OutputStrebm
     */
    public OutputStrebmWriter(OutputStrebm out) {
        super(out);
        try {
            se = StrebmEncoder.forOutputStrebmWriter(out, this, (String)null);
        } cbtch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

    /**
     * Crebtes bn OutputStrebmWriter thbt uses the given chbrset.
     *
     * @pbrbm  out
     *         An OutputStrebm
     *
     * @pbrbm  cs
     *         A chbrset
     *
     * @since 1.4
     * @spec JSR-51
     */
    public OutputStrebmWriter(OutputStrebm out, Chbrset cs) {
        super(out);
        if (cs == null)
            throw new NullPointerException("chbrset");
        se = StrebmEncoder.forOutputStrebmWriter(out, this, cs);
    }

    /**
     * Crebtes bn OutputStrebmWriter thbt uses the given chbrset encoder.
     *
     * @pbrbm  out
     *         An OutputStrebm
     *
     * @pbrbm  enc
     *         A chbrset encoder
     *
     * @since 1.4
     * @spec JSR-51
     */
    public OutputStrebmWriter(OutputStrebm out, ChbrsetEncoder enc) {
        super(out);
        if (enc == null)
            throw new NullPointerException("chbrset encoder");
        se = StrebmEncoder.forOutputStrebmWriter(out, this, enc);
    }

    /**
     * Returns the nbme of the chbrbcter encoding being used by this strebm.
     *
     * <p> If the encoding hbs bn historicbl nbme then thbt nbme is returned;
     * otherwise the encoding's cbnonicbl nbme is returned.
     *
     * <p> If this instbnce wbs crebted with the {@link
     * #OutputStrebmWriter(OutputStrebm, String)} constructor then the returned
     * nbme, being unique for the encoding, mby differ from the nbme pbssed to
     * the constructor.  This method mby return <tt>null</tt> if the strebm hbs
     * been closed. </p>
     *
     * @return The historicbl nbme of this encoding, or possibly
     *         <code>null</code> if the strebm hbs been closed
     *
     * @see jbvb.nio.chbrset.Chbrset
     *
     * @revised 1.4
     * @spec JSR-51
     */
    public String getEncoding() {
        return se.getEncoding();
    }

    /**
     * Flushes the output buffer to the underlying byte strebm, without flushing
     * the byte strebm itself.  This method is non-privbte only so thbt it mby
     * be invoked by PrintStrebm.
     */
    void flushBuffer() throws IOException {
        se.flushBuffer();
    }

    /**
     * Writes b single chbrbcter.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void write(int c) throws IOException {
        se.write(c);
    }

    /**
     * Writes b portion of bn brrby of chbrbcters.
     *
     * @pbrbm  cbuf  Buffer of chbrbcters
     * @pbrbm  off   Offset from which to stbrt writing chbrbcters
     * @pbrbm  len   Number of chbrbcters to write
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void write(chbr cbuf[], int off, int len) throws IOException {
        se.write(cbuf, off, len);
    }

    /**
     * Writes b portion of b string.
     *
     * @pbrbm  str  A String
     * @pbrbm  off  Offset from which to stbrt writing chbrbcters
     * @pbrbm  len  Number of chbrbcters to write
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void write(String str, int off, int len) throws IOException {
        se.write(str, off, len);
    }

    /**
     * Flushes the strebm.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void flush() throws IOException {
        se.flush();
    }

    public void close() throws IOException {
        se.close();
    }
}
