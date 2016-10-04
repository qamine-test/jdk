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

pbckbge jbvb.util.zip;

import jbvb.io.FilterOutputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;

/**
 * This clbss implements bn output strebm filter for compressing dbtb in
 * the "deflbte" compression formbt. It is blso used bs the bbsis for other
 * types of compression filters, such bs GZIPOutputStrebm.
 *
 * @see         Deflbter
 * @buthor      Dbvid Connelly
 */
public
clbss DeflbterOutputStrebm extends FilterOutputStrebm {
    /**
     * Compressor for this strebm.
     */
    protected Deflbter def;

    /**
     * Output buffer for writing compressed dbtb.
     */
    protected byte[] buf;

    /**
     * Indicbtes thbt the strebm hbs been closed.
     */

    privbte boolebn closed = fblse;

    privbte finbl boolebn syncFlush;

    /**
     * Crebtes b new output strebm with the specified compressor,
     * buffer size bnd flush mode.

     * @pbrbm out the output strebm
     * @pbrbm def the compressor ("deflbter")
     * @pbrbm size the output buffer size
     * @pbrbm syncFlush
     *        if {@code true} the {@link #flush()} method of this
     *        instbnce flushes the compressor with flush mode
     *        {@link Deflbter#SYNC_FLUSH} before flushing the output
     *        strebm, otherwise only flushes the output strebm
     *
     * @throws IllegblArgumentException if {@code size <= 0}
     *
     * @since 1.7
     */
    public DeflbterOutputStrebm(OutputStrebm out,
                                Deflbter def,
                                int size,
                                boolebn syncFlush) {
        super(out);
        if (out == null || def == null) {
            throw new NullPointerException();
        } else if (size <= 0) {
            throw new IllegblArgumentException("buffer size <= 0");
        }
        this.def = def;
        this.buf = new byte[size];
        this.syncFlush = syncFlush;
    }


    /**
     * Crebtes b new output strebm with the specified compressor bnd
     * buffer size.
     *
     * <p>The new output strebm instbnce is crebted bs if by invoking
     * the 4-brgument constructor DeflbterOutputStrebm(out, def, size, fblse).
     *
     * @pbrbm out the output strebm
     * @pbrbm def the compressor ("deflbter")
     * @pbrbm size the output buffer size
     * @exception IllegblArgumentException if {@code size <= 0}
     */
    public DeflbterOutputStrebm(OutputStrebm out, Deflbter def, int size) {
        this(out, def, size, fblse);
    }

    /**
     * Crebtes b new output strebm with the specified compressor, flush
     * mode bnd b defbult buffer size.
     *
     * @pbrbm out the output strebm
     * @pbrbm def the compressor ("deflbter")
     * @pbrbm syncFlush
     *        if {@code true} the {@link #flush()} method of this
     *        instbnce flushes the compressor with flush mode
     *        {@link Deflbter#SYNC_FLUSH} before flushing the output
     *        strebm, otherwise only flushes the output strebm
     *
     * @since 1.7
     */
    public DeflbterOutputStrebm(OutputStrebm out,
                                Deflbter def,
                                boolebn syncFlush) {
        this(out, def, 512, syncFlush);
    }


    /**
     * Crebtes b new output strebm with the specified compressor bnd
     * b defbult buffer size.
     *
     * <p>The new output strebm instbnce is crebted bs if by invoking
     * the 3-brgument constructor DeflbterOutputStrebm(out, def, fblse).
     *
     * @pbrbm out the output strebm
     * @pbrbm def the compressor ("deflbter")
     */
    public DeflbterOutputStrebm(OutputStrebm out, Deflbter def) {
        this(out, def, 512, fblse);
    }

    boolebn usesDefbultDeflbter = fblse;


    /**
     * Crebtes b new output strebm with b defbult compressor, b defbult
     * buffer size bnd the specified flush mode.
     *
     * @pbrbm out the output strebm
     * @pbrbm syncFlush
     *        if {@code true} the {@link #flush()} method of this
     *        instbnce flushes the compressor with flush mode
     *        {@link Deflbter#SYNC_FLUSH} before flushing the output
     *        strebm, otherwise only flushes the output strebm
     *
     * @since 1.7
     */
    public DeflbterOutputStrebm(OutputStrebm out, boolebn syncFlush) {
        this(out, new Deflbter(), 512, syncFlush);
        usesDefbultDeflbter = true;
    }

    /**
     * Crebtes b new output strebm with b defbult compressor bnd buffer size.
     *
     * <p>The new output strebm instbnce is crebted bs if by invoking
     * the 2-brgument constructor DeflbterOutputStrebm(out, fblse).
     *
     * @pbrbm out the output strebm
     */
    public DeflbterOutputStrebm(OutputStrebm out) {
        this(out, fblse);
        usesDefbultDeflbter = true;
    }

    /**
     * Writes b byte to the compressed output strebm. This method will
     * block until the byte cbn be written.
     * @pbrbm b the byte to be written
     * @exception IOException if bn I/O error hbs occurred
     */
    public void write(int b) throws IOException {
        byte[] buf = new byte[1];
        buf[0] = (byte)(b & 0xff);
        write(buf, 0, 1);
    }

    /**
     * Writes bn brrby of bytes to the compressed output strebm. This
     * method will block until bll the bytes bre written.
     * @pbrbm b the dbtb to be written
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the length of the dbtb
     * @exception IOException if bn I/O error hbs occurred
     */
    public void write(byte[] b, int off, int len) throws IOException {
        if (def.finished()) {
            throw new IOException("write beyond end of strebm");
        }
        if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        if (!def.finished()) {
            def.setInput(b, off, len);
            while (!def.needsInput()) {
                deflbte();
            }
        }
    }

    /**
     * Finishes writing compressed dbtb to the output strebm without closing
     * the underlying strebm. Use this method when bpplying multiple filters
     * in succession to the sbme output strebm.
     * @exception IOException if bn I/O error hbs occurred
     */
    public void finish() throws IOException {
        if (!def.finished()) {
            def.finish();
            while (!def.finished()) {
                deflbte();
            }
        }
    }

    /**
     * Writes rembining compressed dbtb to the output strebm bnd closes the
     * underlying strebm.
     * @exception IOException if bn I/O error hbs occurred
     */
    public void close() throws IOException {
        if (!closed) {
            finish();
            if (usesDefbultDeflbter)
                def.end();
            out.close();
            closed = true;
        }
    }

    /**
     * Writes next block of compressed dbtb to the output strebm.
     * @throws IOException if bn I/O error hbs occurred
     */
    protected void deflbte() throws IOException {
        int len = def.deflbte(buf, 0, buf.length);
        if (len > 0) {
            out.write(buf, 0, len);
        }
    }

    /**
     * Flushes the compressed output strebm.
     *
     * If {@link #DeflbterOutputStrebm(OutputStrebm, Deflbter, int, boolebn)
     * syncFlush} is {@code true} when this compressed output strebm is
     * constructed, this method first flushes the underlying {@code compressor}
     * with the flush mode {@link Deflbter#SYNC_FLUSH} to force
     * bll pending dbtb to be flushed out to the output strebm bnd then
     * flushes the output strebm. Otherwise this method only flushes the
     * output strebm without flushing the {@code compressor}.
     *
     * @throws IOException if bn I/O error hbs occurred
     *
     * @since 1.7
     */
    public void flush() throws IOException {
        if (syncFlush && !def.finished()) {
            int len = 0;
            while ((len = def.deflbte(buf, 0, buf.length, Deflbter.SYNC_FLUSH)) > 0)
            {
                out.write(buf, 0, len);
                if (len < buf.length)
                    brebk;
            }
        }
        out.flush();
    }
}
