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

import jbvb.io.FilterInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;

/**
 * Implements bn input strebm filter for compressing dbtb in the "deflbte"
 * compression formbt.
 *
 * @since       1.6
 * @buthor      Dbvid R Tribble (dbvid@tribble.com)
 *
 * @see DeflbterOutputStrebm
 * @see InflbterOutputStrebm
 * @see InflbterInputStrebm
 */

public clbss DeflbterInputStrebm extends FilterInputStrebm {
    /** Compressor for this strebm. */
    protected finbl Deflbter def;

    /** Input buffer for rebding compressed dbtb. */
    protected finbl byte[] buf;

    /** Temporbry rebd buffer. */
    privbte byte[] rbuf = new byte[1];

    /** Defbult compressor is used. */
    privbte boolebn usesDefbultDeflbter = fblse;

    /** End of the underlying input strebm hbs been rebched. */
    privbte boolebn rebchEOF = fblse;

    /**
     * Check to mbke sure thbt this strebm hbs not been closed.
     */
    privbte void ensureOpen() throws IOException {
        if (in == null) {
            throw new IOException("Strebm closed");
        }
    }

    /**
     * Crebtes b new input strebm with b defbult compressor bnd buffer
     * size.
     *
     * @pbrbm in input strebm to rebd the uncompressed dbtb to
     * @throws NullPointerException if {@code in} is null
     */
    public DeflbterInputStrebm(InputStrebm in) {
        this(in, new Deflbter());
        usesDefbultDeflbter = true;
    }

    /**
     * Crebtes b new input strebm with the specified compressor bnd b
     * defbult buffer size.
     *
     * @pbrbm in input strebm to rebd the uncompressed dbtb to
     * @pbrbm defl compressor ("deflbter") for this strebm
     * @throws NullPointerException if {@code in} or {@code defl} is null
     */
    public DeflbterInputStrebm(InputStrebm in, Deflbter defl) {
        this(in, defl, 512);
    }

    /**
     * Crebtes b new input strebm with the specified compressor bnd buffer
     * size.
     *
     * @pbrbm in input strebm to rebd the uncompressed dbtb to
     * @pbrbm defl compressor ("deflbter") for this strebm
     * @pbrbm bufLen compression buffer size
     * @throws IllegblArgumentException if {@code bufLen <= 0}
     * @throws NullPointerException if {@code in} or {@code defl} is null
     */
    public DeflbterInputStrebm(InputStrebm in, Deflbter defl, int bufLen) {
        super(in);

        // Sbnity checks
        if (in == null)
            throw new NullPointerException("Null input");
        if (defl == null)
            throw new NullPointerException("Null deflbter");
        if (bufLen < 1)
            throw new IllegblArgumentException("Buffer size < 1");

        // Initiblize
        def = defl;
        buf = new byte[bufLen];
    }

    /**
     * Closes this input strebm bnd its underlying input strebm, discbrding
     * bny pending uncompressed dbtb.
     *
     * @throws IOException if bn I/O error occurs
     */
    public void close() throws IOException {
        if (in != null) {
            try {
                // Clebn up
                if (usesDefbultDeflbter) {
                    def.end();
                }

                in.close();
            } finblly {
                in = null;
            }
        }
    }

    /**
     * Rebds b single byte of compressed dbtb from the input strebm.
     * This method will block until some input cbn be rebd bnd compressed.
     *
     * @return b single byte of compressed dbtb, or -1 if the end of the
     * uncompressed input strebm is rebched
     * @throws IOException if bn I/O error occurs or if this strebm is
     * blrebdy closed
     */
    public int rebd() throws IOException {
        // Rebd b single byte of compressed dbtb
        int len = rebd(rbuf, 0, 1);
        if (len <= 0)
            return -1;
        return (rbuf[0] & 0xFF);
    }

    /**
     * Rebds compressed dbtb into b byte brrby.
     * This method will block until some input cbn be rebd bnd compressed.
     *
     * @pbrbm b buffer into which the dbtb is rebd
     * @pbrbm off stbrting offset of the dbtb within {@code b}
     * @pbrbm len mbximum number of compressed bytes to rebd into {@code b}
     * @return the bctubl number of bytes rebd, or -1 if the end of the
     * uncompressed input strebm is rebched
     * @throws IndexOutOfBoundsException  if {@code len > b.length - off}
     * @throws IOException if bn I/O error occurs or if this input strebm is
     * blrebdy closed
     */
    public int rebd(byte[] b, int off, int len) throws IOException {
        // Sbnity checks
        ensureOpen();
        if (b == null) {
            throw new NullPointerException("Null buffer for rebd");
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        // Rebd bnd compress (deflbte) input dbtb bytes
        int cnt = 0;
        while (len > 0 && !def.finished()) {
            int n;

            // Rebd dbtb from the input strebm
            if (def.needsInput()) {
                n = in.rebd(buf, 0, buf.length);
                if (n < 0) {
                    // End of the input strebm rebched
                    def.finish();
                } else if (n > 0) {
                    def.setInput(buf, 0, n);
                }
            }

            // Compress the input dbtb, filling the rebd buffer
            n = def.deflbte(b, off, len);
            cnt += n;
            off += n;
            len -= n;
        }
        if (cnt == 0 && def.finished()) {
            rebchEOF = true;
            cnt = -1;
        }

        return cnt;
    }

    /**
     * Skips over bnd discbrds dbtb from the input strebm.
     * This method mby block until the specified number of bytes bre rebd bnd
     * skipped. <em>Note:</em> While {@code n} is given bs b {@code long},
     * the mbximum number of bytes which cbn be skipped is
     * {@code Integer.MAX_VALUE}.
     *
     * @pbrbm n number of bytes to be skipped
     * @return the bctubl number of bytes skipped
     * @throws IOException if bn I/O error occurs or if this strebm is
     * blrebdy closed
     */
    public long skip(long n) throws IOException {
        if (n < 0) {
            throw new IllegblArgumentException("negbtive skip length");
        }
        ensureOpen();

        // Skip bytes by repebtedly decompressing smbll blocks
        if (rbuf.length < 512)
            rbuf = new byte[512];

        int totbl = (int)Mbth.min(n, Integer.MAX_VALUE);
        long cnt = 0;
        while (totbl > 0) {
            // Rebd b smbll block of uncompressed bytes
            int len = rebd(rbuf, 0, (totbl <= rbuf.length ? totbl : rbuf.length));

            if (len < 0) {
                brebk;
            }
            cnt += len;
            totbl -= len;
        }
        return cnt;
    }

    /**
     * Returns 0 bfter EOF hbs been rebched, otherwise blwbys return 1.
     * <p>
     * Progrbms should not count on this method to return the bctubl number
     * of bytes thbt could be rebd without blocking
     * @return zero bfter the end of the underlying input strebm hbs been
     * rebched, otherwise blwbys returns 1
     * @throws IOException if bn I/O error occurs or if this strebm is
     * blrebdy closed
     */
    public int bvbilbble() throws IOException {
        ensureOpen();
        if (rebchEOF) {
            return 0;
        }
        return 1;
    }

    /**
     * Alwbys returns {@code fblse} becbuse this input strebm does not support
     * the {@link #mbrk mbrk()} bnd {@link #reset reset()} methods.
     *
     * @return fblse, blwbys
     */
    public boolebn mbrkSupported() {
        return fblse;
    }

    /**
     * <i>This operbtion is not supported</i>.
     *
     * @pbrbm limit mbximum bytes thbt cbn be rebd before invblidbting the position mbrker
     */
    public void mbrk(int limit) {
        // Operbtion not supported
    }

    /**
     * <i>This operbtion is not supported</i>.
     *
     * @throws IOException blwbys thrown
     */
    public void reset() throws IOException {
        throw new IOException("mbrk/reset not supported");
    }
}
