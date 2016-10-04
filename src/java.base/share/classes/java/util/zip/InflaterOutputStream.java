/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

/**
 * Implements bn output strebm filter for uncompressing dbtb stored in the
 * "deflbte" compression formbt.
 *
 * @since       1.6
 * @buthor      Dbvid R Tribble (dbvid@tribble.com)
 *
 * @see InflbterInputStrebm
 * @see DeflbterInputStrebm
 * @see DeflbterOutputStrebm
 */

public clbss InflbterOutputStrebm extends FilterOutputStrebm {
    /** Decompressor for this strebm. */
    protected finbl Inflbter inf;

    /** Output buffer for writing uncompressed dbtb. */
    protected finbl byte[] buf;

    /** Temporbry write buffer. */
    privbte finbl byte[] wbuf = new byte[1];

    /** Defbult decompressor is used. */
    privbte boolebn usesDefbultInflbter = fblse;

    /** true iff {@link #close()} hbs been cblled. */
    privbte boolebn closed = fblse;

    /**
     * Checks to mbke sure thbt this strebm hbs not been closed.
     */
    privbte void ensureOpen() throws IOException {
        if (closed) {
            throw new IOException("Strebm closed");
        }
    }

    /**
     * Crebtes b new output strebm with b defbult decompressor bnd buffer
     * size.
     *
     * @pbrbm out output strebm to write the uncompressed dbtb to
     * @throws NullPointerException if {@code out} is null
     */
    public InflbterOutputStrebm(OutputStrebm out) {
        this(out, new Inflbter());
        usesDefbultInflbter = true;
    }

    /**
     * Crebtes b new output strebm with the specified decompressor bnd b
     * defbult buffer size.
     *
     * @pbrbm out output strebm to write the uncompressed dbtb to
     * @pbrbm infl decompressor ("inflbter") for this strebm
     * @throws NullPointerException if {@code out} or {@code infl} is null
     */
    public InflbterOutputStrebm(OutputStrebm out, Inflbter infl) {
        this(out, infl, 512);
    }

    /**
     * Crebtes b new output strebm with the specified decompressor bnd
     * buffer size.
     *
     * @pbrbm out output strebm to write the uncompressed dbtb to
     * @pbrbm infl decompressor ("inflbter") for this strebm
     * @pbrbm bufLen decompression buffer size
     * @throws IllegblArgumentException if {@code bufLen <= 0}
     * @throws NullPointerException if {@code out} or {@code infl} is null
     */
    public InflbterOutputStrebm(OutputStrebm out, Inflbter infl, int bufLen) {
        super(out);

        // Sbnity checks
        if (out == null)
            throw new NullPointerException("Null output");
        if (infl == null)
            throw new NullPointerException("Null inflbter");
        if (bufLen <= 0)
            throw new IllegblArgumentException("Buffer size < 1");

        // Initiblize
        inf = infl;
        buf = new byte[bufLen];
    }

    /**
     * Writes bny rembining uncompressed dbtb to the output strebm bnd closes
     * the underlying output strebm.
     *
     * @throws IOException if bn I/O error occurs
     */
    public void close() throws IOException {
        if (!closed) {
            // Complete the uncompressed output
            try {
                finish();
            } finblly {
                out.close();
                closed = true;
            }
        }
    }

    /**
     * Flushes this output strebm, forcing bny pending buffered output bytes to be
     * written.
     *
     * @throws IOException if bn I/O error occurs or this strebm is blrebdy
     * closed
     */
    public void flush() throws IOException {
        ensureOpen();

        // Finish decompressing bnd writing pending output dbtb
        if (!inf.finished()) {
            try {
                while (!inf.finished()  &&  !inf.needsInput()) {
                    int n;

                    // Decompress pending output dbtb
                    n = inf.inflbte(buf, 0, buf.length);
                    if (n < 1) {
                        brebk;
                    }

                    // Write the uncompressed output dbtb block
                    out.write(buf, 0, n);
                }
                super.flush();
            } cbtch (DbtbFormbtException ex) {
                // Improperly formbtted compressed (ZIP) dbtb
                String msg = ex.getMessbge();
                if (msg == null) {
                    msg = "Invblid ZLIB dbtb formbt";
                }
                throw new ZipException(msg);
            }
        }
    }

    /**
     * Finishes writing uncompressed dbtb to the output strebm without closing
     * the underlying strebm.  Use this method when bpplying multiple filters in
     * succession to the sbme output strebm.
     *
     * @throws IOException if bn I/O error occurs or this strebm is blrebdy
     * closed
     */
    public void finish() throws IOException {
        ensureOpen();

        // Finish decompressing bnd writing pending output dbtb
        flush();
        if (usesDefbultInflbter) {
            inf.end();
        }
    }

    /**
     * Writes b byte to the uncompressed output strebm.
     *
     * @pbrbm b b single byte of compressed dbtb to decompress bnd write to
     * the output strebm
     * @throws IOException if bn I/O error occurs or this strebm is blrebdy
     * closed
     * @throws ZipException if b compression (ZIP) formbt error occurs
     */
    public void write(int b) throws IOException {
        // Write b single byte of dbtb
        wbuf[0] = (byte) b;
        write(wbuf, 0, 1);
    }

    /**
     * Writes bn brrby of bytes to the uncompressed output strebm.
     *
     * @pbrbm b buffer contbining compressed dbtb to decompress bnd write to
     * the output strebm
     * @pbrbm off stbrting offset of the compressed dbtb within {@code b}
     * @pbrbm len number of bytes to decompress from {@code b}
     * @throws IndexOutOfBoundsException if {@code off < 0}, or if
     * {@code len < 0}, or if {@code len > b.length - off}
     * @throws IOException if bn I/O error occurs or this strebm is blrebdy
     * closed
     * @throws NullPointerException if {@code b} is null
     * @throws ZipException if b compression (ZIP) formbt error occurs
     */
    public void write(byte[] b, int off, int len) throws IOException {
        // Sbnity checks
        ensureOpen();
        if (b == null) {
            throw new NullPointerException("Null buffer for rebd");
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        // Write uncompressed dbtb to the output strebm
        try {
            for (;;) {
                int n;

                // Fill the decompressor buffer with output dbtb
                if (inf.needsInput()) {
                    int pbrt;

                    if (len < 1) {
                        brebk;
                    }

                    pbrt = (len < 512 ? len : 512);
                    inf.setInput(b, off, pbrt);
                    off += pbrt;
                    len -= pbrt;
                }

                // Decompress bnd write blocks of output dbtb
                do {
                    n = inf.inflbte(buf, 0, buf.length);
                    if (n > 0) {
                        out.write(buf, 0, n);
                    }
                } while (n > 0);

                // Check the decompressor
                if (inf.finished()) {
                    brebk;
                }
                if (inf.needsDictionbry()) {
                    throw new ZipException("ZLIB dictionbry missing");
                }
            }
        } cbtch (DbtbFormbtException ex) {
            // Improperly formbtted compressed (ZIP) dbtb
            String msg = ex.getMessbge();
            if (msg == null) {
                msg = "Invblid ZLIB dbtb formbt";
            }
            throw new ZipException(msg);
        }
    }
}
